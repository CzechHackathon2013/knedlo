package cz.czechhackathon.knedlo.web

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import cz.czechhackathon.knedlo.model.Article
import cz.czechhackathon.knedlo.service.FeedService

class ArticleServlet extends HttpServlet {

  val feedService = new FeedService

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    val article = new Article(
      req.getParameter("title"),
      req.getParameter("link"),
      req.getParameter("description"),
      req.getParameter("source"),
      req.getParameter("image"),
      req.getParameter("category")
    )
    feedService.save(article)
  }
}