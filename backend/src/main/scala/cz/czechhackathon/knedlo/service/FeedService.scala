package cz.czechhackathon.knedlo.service

import cz.czechhackathon.knedlo.dao.{DownloadDao, CategoryDao, FeedDao, UserDao}
import cz.czechhackathon.knedlo.model.{Badge, Article}
import cz.czechhackathon.knedlo.util.Logging

class FeedService(val userDao: UserDao = new UserDao,
                  val feedDao: FeedDao = new FeedDao,
                  val categoryDao: CategoryDao = new CategoryDao,
                  val downloadDao: DownloadDao = new DownloadDao
                   ) extends Logging {

  def save(articleId: String) {
    val item = downloadDao.get(articleId)
    userDao.findAll().foreach(
      feedDao.save(item, _)
    )
  }

  def getFeed(userEmail: String): Array[Article] = {
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
    val original = categoryDao.find(email).toList

    categoryDao.update(email, feed.category, status)

    val current = categoryDao.find(email).toList
    current.diff(original).toArray
  }

  def onboard(email: String) {
    log.info(s"Onboarding user=$email")
    downloadDao.find().foreach{ i =>
      log.info(s"Onboarding user=$email link=${i.link}")
      feedDao.save(i, email)
    }
  }
}