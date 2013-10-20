package cz.czechhackathon.knedlo.dao

import cz.czechhackathon.knedlo.model.Article
import com.github.hexx.gaeds.Datastore
import com.github.hexx.gaeds.Property._
import scala.language.postfixOps

class FeedDao {

  /**
   * Store article to feed of given user.
   * @param item article to be stored
   * @param userEmail user email
   */
  def save(item: DownloadItem, userEmail: String) {
    Datastore.put(
      new Feed(userEmail, item.title, item.link, item.description, item.text, item.source, item.image, item.category,
        item.insertDate, 0))
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
        val text = f.text.__valueOfProperty match {
          case Some(t) => t.toString
          case None => null
        }
        new Article(f.title, f.link, desc, text, f.source.getOrElse(null), f.image.getOrElse(null), f.category)
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
    val i = Feed.query().filter(f => (f.link #== link) and (f.userEmail #== email)).asIterator()
    if (!i.hasNext) {
      throw new IllegalStateException(s"No record found for link $link and user $email")
    }
    val feed = i.next()
    feed.status = status
    feed.put()
    feed
  }
}
