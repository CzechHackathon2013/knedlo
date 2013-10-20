package cz.czechhackathon.knedlo.service

import com.google.appengine.api.taskqueue.{Queue, QueueFactory}
import com.google.appengine.api.taskqueue.TaskOptions.Builder._
import com.google.appengine.api.taskqueue.TaskOptions.Method
import cz.czechhackathon.knedlo.dao.DownloadDao

class Downloader(val queue: Queue = QueueFactory.getQueue("articles"),
                 val parsers: Seq[Parser] = Seq(new IdnesParser),
                 val dao: DownloadDao = new DownloadDao
                  ) {

  def download() = {
    parsers.foreach(
      _.download.foreach(a => {
        val key = dao.save(a)
        val options = withUrl("/article").method(Method.GET).param("id", key)
        queue.add(options)
      })
    )
  }
}