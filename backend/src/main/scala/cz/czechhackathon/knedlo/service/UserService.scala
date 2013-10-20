package cz.czechhackathon.knedlo.service

import cz.czechhackathon.knedlo.dao.{CategoryDao, FeedDao, UserDao}
import cz.czechhackathon.knedlo.model.{Badge, Article}
import com.google.appengine.api.users.User
import cz.czechhackathon.knedlo.util.Logging

class UserService(val userDao: UserDao = new UserDao) extends Logging {

  def saveIfMissing(user: User): Boolean = {
    if (userDao.get(user.getEmail).isEmpty) {
      userDao.save(user)
      log.info(s"Creating user=${user.getEmail}")
      true
    } else {
      log.debug(s"Existing user=${user.getEmail}")
      false
    }

  }

}