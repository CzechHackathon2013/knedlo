package cz.czechhackathon.knedlo.service

import scala.xml._
import java.io.InputStream
import cz.czechhackathon.knedlo.model.Article
import java.net.URL
import cz.czechhackathon.knedlo.util.Logging

class IdnesParser extends Logging {

  val url = new URL("http://servis.idnes.cz/rss.aspx")

  def parse(in: InputStream): Seq[Article] = {
    val root = XML.load(in)
    (root \\ "item").map(node => {
      val title = (node \\ "title").text
      val link = (node \\ "link").text.trim
      val description = (node \\ "description").text.split("<")(0)
      val category = (node \\ "category").text.split('-')(0).trim
      new Article(title, link, description, "idnes", null, category)
    })
  }

  def download: Seq[Article] = {
    log.info(s"Downloading ${getClass.getSimpleName} from $url")
    try {
      parse(url.openStream())
    }
    catch {
      case e: Exception => log.warning(s"Exception fetching $url")
      Seq()
    }
  }
}