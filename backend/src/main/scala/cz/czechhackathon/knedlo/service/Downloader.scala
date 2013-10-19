package cz.czechhackathon.knedlo.service

import com.google.appengine.api.taskqueue.QueueFactory
import com.google.appengine.api.taskqueue.TaskOptions.Builder._
import com.google.appengine.api.taskqueue.TaskOptions.Method

class Downloader {

  val parsers = Seq(new IdnesParser)

  val queue = QueueFactory.getQueue("articles")

  def download() = {
    parsers.foreach(
      _.download.foreach(a => {
        val options = withUrl("/article").method(Method.POST)
          .param("title", a.title)
          .param("link", a.link)
          .param("description", a.description)
          .param("source", a.source)
          .param("category", a.category)
        if (a.image != null)
          options.param("image", a.image)
        queue.add(options)
      })
    )
  }
}