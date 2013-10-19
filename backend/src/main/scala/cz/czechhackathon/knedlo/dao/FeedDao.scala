package cz.czechhackathon.knedlo.dao

import cz.czechhackathon.knedlo.model.Article
import com.google.appengine.api.datastore.{DatastoreService, DatastoreServiceFactory}
import java.util.Date
import com.github.hexx.gaeds.Datastore
import com.github.hexx.gaeds.Property._

class FeedDao(val datastore: DatastoreService = DatastoreServiceFactory.getDatastoreService()) {

  /**
   * Store article to feed of given user.
   * @param article article to be stored
   * @param userId user identifier
   */
  def store(article: Article, userId: String) {
    Datastore.put(new Feed(userId, article.title, article.link, Option(article.description), Option(article.source),
      Option(article.image), new Date(), 0))
  }

  /**
   * Get unread articles by user email.
   * @param userId user identifier
   * @return array of articles
   */
  def get(userId: String): Array[Article] = {
    null
  }
}
