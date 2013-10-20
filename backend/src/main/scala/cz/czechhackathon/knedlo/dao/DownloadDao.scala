package cz.czechhackathon.knedlo.dao

import com.google.appengine.api.datastore._
import cz.czechhackathon.knedlo.model.Article
import java.util.Date
import com.github.hexx.gaeds.{Datastore, Key}
import scala.language.postfixOps

class DownloadDao {

  def save(article: Article): String = {
    val key = Key[DownloadItem](KeyFactory.createKey(DownloadItem.kind, article.link))
    try {
      get(key.toWebSafeString)
      return key.toWebSafeString
    }
    catch {
      case e: EntityNotFoundException =>
    }

    val desc = if (article.description != null) Some(new Text(article.description)) else None
    val text = if (article.text != null) Some(new Text(article.text)) else None
    val item = new DownloadItem(article.title, article.link, desc, text, Option(article.source),
      Option(article.image), article.category, new Date)
    item.key = Option(key)
    //println(s"saving ${key.toWebSafeString}")
    val put = Datastore.put(item)
    put.toWebSafeString
  }

  def get(key: String): DownloadItem = {
    //println(s"geting $key")
    DownloadItem.get(DownloadItem.stringToKey(key))
  }

  def find(): Iterator[DownloadItem] = {
    DownloadItem.query()
      .sort(_.insertDate desc)
      .limit(20)
      .asIterator()
  }
}