package cz.czechhackathon.knedlo.service

import cz.czechhackathon.knedlo.dao.{FeedDao, UserDao}
import cz.czechhackathon.knedlo.model.Article

class FeedService(val userDao: UserDao = new UserDao,
                  val feedDao: FeedDao = new FeedDao) {

  def save(article: Article) {
    userDao.findAll().foreach(
      feedDao.store(article, _)
    )
  }

}