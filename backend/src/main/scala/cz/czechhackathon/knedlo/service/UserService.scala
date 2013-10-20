package cz.czechhackathon.knedlo.service

import cz.czechhackathon.knedlo.dao.{CategoryDao, FeedDao, UserDao}
import cz.czechhackathon.knedlo.model.{Badge, Article}
import com.google.appengine.api.users.User

class UserService(val userDao: UserDao = new UserDao) {

  def saveIfMissing(user: User) {
    if (userDao.get(user.getEmail).isEmpty) {
      userDao.save(user)
    }
  }

}