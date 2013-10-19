package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.{Property, Mapper}
import com.github.hexx.gaeds.Property._

class User(val email: Property[String]) extends Mapper[User] {
  def this() = this("")
}

object User extends User