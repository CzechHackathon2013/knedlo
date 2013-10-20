package cz.czechhackathon.knedlo.service

import cz.czechhackathon.knedlo.util.Logging
import cz.czechhackathon.knedlo.model.Article
import java.net.URL
import java.io.InputStream

trait Parser extends Logging {

  protected val url: URL

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

  def parse(in: InputStream): Seq[Article]
}