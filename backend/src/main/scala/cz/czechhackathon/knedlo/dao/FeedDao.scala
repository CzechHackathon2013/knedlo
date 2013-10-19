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
   * @param userId user identifier
   */
  def store(article: Article, userId: String) {
    Datastore.put(
      new Feed(userId, article.title, article.link, Option(new Text(article.description)), Option(article.source),
        Option(article.image), article.category, new Date(), 0))
  }

  /**
   * Get unread articles by user identifier in a descending order by insertDate.
   * @param userId user identifier
   * @return array of articles
   */
  def get(userId: String): Array[Article] = {
    Feed.query
      .filter(_.userId #== userId)
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
}
