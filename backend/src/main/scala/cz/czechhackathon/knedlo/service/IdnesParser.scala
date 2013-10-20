package cz.czechhackathon.knedlo.service

import scala.xml._
import java.io.InputStream
import cz.czechhackathon.knedlo.model.Article
import java.net.URL
import cz.czechhackathon.knedlo.util.Logging
import scala.util.Random

class IdnesParser extends Logging {

  private val url = new URL("http://servis.idnes.cz/rss.aspx")
  private val images = Seq(
    "http://media.novinky.cz/366/393668-gallery1-bp4jl.jpg",
    "http://media.novinky.cz/761/397612-original1-8pc4q.jpg",
    "http://media.novinky.cz/905/389059-gallery1-u26pz.jpg",
    "http://i.idnes.cz/13/102/cl6/JB4e8a90_sali.jpg",
    "http://i.idnes.cz/12/042/cl6/STK426ff7_pistole_pancer2.jpg",
    "http://i.idnes.cz/12/061/cl6/JB438d93_profimedia_0115774931.jpg"
  )
  private val sources = Seq("super.cz", "moviezone.cz", "blog.respekt.cz")

  def parse(in: InputStream): Seq[Article] = {
    val root = XML.load(in)
    (root \\ "item").map(node => {
      val title = (node \\ "title").text
      val link = (node \\ "link").text.trim
      val description = (node \\ "description").text.split("<")(0)
      val text = s"$description\n\n$description\n\n$description\n\n$description\n\n$description\n\n"
      val category = (node \\ "category").text.split('-')(0).trim
      val img = images(Random.nextInt(images.size))
      val src = sources(Random.nextInt(sources.size))
      new Article(title, link, description, text, src, img, category)
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