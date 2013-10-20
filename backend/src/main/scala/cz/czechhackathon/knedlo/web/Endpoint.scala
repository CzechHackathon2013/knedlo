package cz.czechhackathon.knedlo.web

import com.google.api.server.spi.config.{ApiMethod, Api}
import com.google.api.server.spi.config.ApiMethod.HttpMethod
import cz.czechhackathon.knedlo.model.{Badge, Article}
import com.google.appengine.api.users.User
import javax.inject.Named
import javax.annotation.Nullable
import cz.czechhackathon.knedlo.util.Logging
import cz.czechhackathon.knedlo.service.{UserService, FeedService}
import cz.czechhackathon.knedlo.dao.{UserDao, CategoryDao}

@Api(name = "knedlo", version = "v1", clientIds = Array(
  "338711060290.apps.googleusercontent.com", // iOS
  "338711060290-5hmko3leeamlt9us7vcpdg7l2a5q163n.apps.googleusercontent.com", // Android
  "338711060290-ihdh6ls1fbut48l5nt2aahonnkgokd1c.apps.googleusercontent.com"), // Web (OAuth)
  audiences = Array("338711060290-ihdh6ls1fbut48l5nt2aahonnkgokd1c.apps.googleusercontent.com")
)
class Endpoint extends Logging {

  private val userDao = new UserDao
  private val categoryDao = new CategoryDao
  private val userService = new UserService(userDao)
  private val feedService = new FeedService(userDao = userDao, categoryDao = categoryDao)
  private val dummy = new User("test@gmail.com", "testDomain")

  /**
   * Get paged article feed for the given user
   * @param page page number (first page if not entered)
   * @param u user
   * @return list of articles or empty list
   */
  @ApiMethod(name = "feed", path = "feed", httpMethod = HttpMethod.GET)
  def feed(@Named("page") @Nullable page: Integer, u: User): Array[Article] = {
    //TODO throw new OAuthRequestException("Unauthorized")
    val user = if (u != null) u else dummy
    log.info(s"${user.toString} - email: ${user.getEmail}")
    if (userService.saveIfMissing(user)) {
      feedService.onboard(user.getEmail)
    }
    feedService.getFeed(user.getEmail)
  }

  /**
   * Record client action
   * @param action action id (read, save, ignore,...)
   * @param articleLink link of affected article
   * @param u user
   * @return list of newly awarded badges or empty list
   */
  @ApiMethod(name = "action", path = "action", httpMethod = HttpMethod.POST)
  def action(@Named("action") action: String, @Named("articleLink") articleLink: String, u: User):Array[Badge] = {
    //TODO throw new OAuthRequestException("Unauthorized")
    val user = if (u != null) u else dummy
    log.info(s"${user.toString} - email: ${user.getEmail}")
    feedService.action(action, articleLink, user.getEmail)
  }

  /**
   * Get badges for the given user
   * @param u user
   * @return list of badges or empty list
   */
  @ApiMethod(name = "badges", path = "badges", httpMethod = HttpMethod.GET)
  def badges(u: User): Array[Badge] = {
    //TODO throw new OAuthRequestException("Unauthorized")
    val user = if (u != null) u else dummy
    log.info(s"${user.toString} - email: ${user.getEmail}")
    categoryDao.find(user.getEmail)
  }
}
