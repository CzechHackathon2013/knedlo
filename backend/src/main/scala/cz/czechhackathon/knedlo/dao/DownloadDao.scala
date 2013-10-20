package cz.czechhackathon.knedlo.dao

import com.google.appengine.api.datastore._
import cz.czechhackathon.knedlo.model.Article
import java.util.Date
import com.github.hexx.gaeds.{Datastore, Key}

class DownloadDao {

  def save(article: Article): String = {
    val key = Key[DownloadItem](KeyFactory.createKey("article", article.link))
    try {
      get(key.toWebSafeString)
      return key.toWebSafeString
    }
    catch {
      case e: EntityNotFoundException =>
    }

    val desc = if (article.description != null) Some(new Text(article.description)) else None
    val item = new DownloadItem(article.title, article.link, desc, Option(article.source),
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
}