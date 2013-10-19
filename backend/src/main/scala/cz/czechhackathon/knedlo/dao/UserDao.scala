package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.Property._

class UserDao {

  def findAll(): Seq[String] = {
    User.query().asIterator().map[String](_.email).toSeq
  }

}