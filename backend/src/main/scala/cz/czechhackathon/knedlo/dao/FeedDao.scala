package cz.czechhackathon.knedlo.dao

import cz.czechhackathon.knedlo.model.Article
import java.util.Date
import com.github.hexx.gaeds.Datastore
import com.github.hexx.gaeds.Property._
import com.google.appengine.api.datastore.Text
import scala.language.postfixOps

class FeedDao {

  /**
   * Store article to feed of given user.
   * @param article article to be stored
   * @param userEmail user email
   */
  def store(article: Article, userEmail: String) {
    Datastore.put(
      new Feed(userEmail, article.title, article.link, Option(new Text(article.description)), Option(article.source),
        Option(article.image), article.category, new Date(), 0))
  }

  /**
   * Get unread articles by user email in a descending order by insertDate.
   * @param userEmail user email
   * @return array of articles
   */
  def get(userEmail: String): Array[Article] = {
    Feed.query
      .filter(_.userEmail #== userEmail)
      .sort(_.insertDate desc)
      .asIterator().map {
      f =>
        val desc = f.description.__valueOfProperty match {
          case Some(d) => d.toString
          case None => null
        }
        new Article(f.title, f.link, desc, f.source.getOrElse(null), f.image.getOrElse(null), f.category)
    }.toArray
  }

  /**
   * Update status for the given link and user
   * @param link link of the article
   * @param email user's email
   * @param status status
   * @return updated entity
   * @throws IllegalStateException if no combination of link and user matches
   */
  def update(link: String, email: String, status: Long): Feed = {
    val feed = Feed.query().filter(f => (f.link #== link) and (f.userEmail #== email)).asSingle()
    if (feed == null) {
      throw new IllegalStateException(s"No record found for link $link and user $email")
    }
    feed.status = status
    feed.put()
    feed
  }
}
