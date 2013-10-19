package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.Mapper

class User(val email: String) extends Mapper[User] {
  def this() = this("")
}

object User extends User