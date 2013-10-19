package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.{Mapper, Property}
import java.util.Date

class Feed(val userId: Property[String],
           val title: Property[String],
           val link: Property[String],
           val description: Property[Option[String]],
           val source: Property[Option[String]],
           val image: Property[Option[String]],
           val insertDate: Property[Date],
           val status: Property[Long])
  extends Mapper[Feed] {

  def this() = this("", "", "", None, None, None, new Date(), 0)

}

object Feed extends Feed
