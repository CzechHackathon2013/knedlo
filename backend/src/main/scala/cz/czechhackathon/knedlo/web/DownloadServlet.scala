package cz.czechhackathon.knedlo.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import cz.czechhackathon.knedlo.service.Downloader

class DownloadServlet extends HttpServlet {

  val downloader = new Downloader

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
    downloader.download
  }
}