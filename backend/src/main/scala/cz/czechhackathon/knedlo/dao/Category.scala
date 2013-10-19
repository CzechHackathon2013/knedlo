package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.{Mapper, Property}

class Category(val email: Property[String],
               val category: Property[String],
               var count: Property[Long]
                ) extends Mapper[Category] {

  def this() = this("", "", 0)

}

object Category extends Category