package cz.czechhackathon.knedlo.dao

class UserDao {

  def findAll(): Seq[String] = {
    User.query().asIterator().map(_.email).toSeq
  }

}