package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.{Mapper, Property}
import java.util.Date
import com.google.appengine.api.datastore.Text

class Feed(val userEmail: Property[String],
           val title: Property[String],
           val link: Property[String],
           val description: Property[Option[Text]],
           val source: Property[Option[String]],
           val image: Property[Option[String]],
           val category: Property[String],
           val insertDate: Property[Date],
           var status: Property[Long])
  extends Mapper[Feed] {

  def this() = this("", "", "", None, None, None, "", new Date(), 0)

}

object Feed extends Feed
