package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.Property._
import com.github.hexx.gaeds.Datastore
import com.google.appengine.api.users.{User => AEUser}

class UserDao {

  def save(user: AEUser) {
    Datastore.put(new User(user.getEmail))
  }

  def get(userEmail: String): Option[User] = {
    val i = User.query().filter(_.email #== userEmail).asIterator()
    if (i.hasNext) Some(i.next()) else None
  }

  def findAll(): Seq[String] = {
    User.query().asIterator().map[String](_.email).toSeq
  }

}