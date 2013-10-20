package cz.czechhackathon.knedlo.dao

import com.github.hexx.gaeds.{Mapper, Property}
import com.google.appengine.api.datastore.Text
import java.util.Date

class DownloadItem(val title: Property[String],
                   val link: Property[String],
                   val description: Property[Option[Text]],
                   val text: Property[Option[Text]],
                   val source: Property[Option[String]],
                   val image: Property[Option[String]],
                   val category: Property[String],
                   val insertDate: Property[Date]
                    ) extends Mapper[DownloadItem] {
  def this() = this("", "", None, None, None, None, "", new Date)

}

object DownloadItem extends DownloadItem