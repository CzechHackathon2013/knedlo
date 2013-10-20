package cz.czechhackathon.knedlo.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import cz.czechhackathon.knedlo.service.FeedService
import org.apache.commons.lang.Validate.notEmpty

class ArticleServlet extends HttpServlet {

  val feedService = new FeedService

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val id = req.getParameter("id")
    notEmpty(id, "parameter id can't be empty")
    feedService.save(id)
  }
}