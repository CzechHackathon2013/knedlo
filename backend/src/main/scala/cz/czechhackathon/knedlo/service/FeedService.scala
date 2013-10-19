package cz.czechhackathon.knedlo.service

import cz.czechhackathon.knedlo.dao.{FeedDao, UserDao}
import cz.czechhackathon.knedlo.model.{Badge, Article}

class FeedService(val userDao: UserDao = new UserDao,
                  val feedDao: FeedDao = new FeedDao) {

  def save(article: Article) {
    userDao.findAll().foreach(
      feedDao.store(article, _)
    )
  }

  def get(userEmail: String): Array[Article] = {
    feedDao.get(userEmail)
  }

  def action(action: String, articleLink: String, email: String): Array[Badge] = {
    val status: Long = action match {
      case "read" => 1
      case "save" => 0
      case "delete" => -1
      case _ => return Array()
    }
    val feed = feedDao.update(articleLink, email, status)
    feed.category

    // todo count badges
    
    Array()
  }
}