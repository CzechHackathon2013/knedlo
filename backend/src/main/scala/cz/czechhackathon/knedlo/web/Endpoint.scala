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
import com.google.appengine.api.oauth.OAuthRequestException

@Api(name = "knedlo", version = "v1", clientIds = Array(
  "338711060290.apps.googleusercontent.com", // iOS
  "338711060290-5hmko3leeamlt9us7vcpdg7l2a5q163n.apps.googleusercontent.com", // Android
  "338711060290-ihdh6ls1fbut48l5nt2aahonnkgokd1c.apps.googleusercontent.com"), // Web (OAuth)
  audiences = Array("338711060290-ihdh6ls1fbut48l5nt2aahonnkgokd1c.apps.googleusercontent.com")
)
class Endpoint extends Logging {

  val userDao = new UserDao
  val categoryDao = new CategoryDao
  val userService = new UserService(userDao)
  val feedService = new FeedService(userDao = userDao, categoryDao = categoryDao)

  /**
   * Get paged article feed for the given user
   * @param page page number (first page if not entered)
   * @param user user
   * @return list of articles or empty list
   */
  @ApiMethod(name = "feed", path = "feed", httpMethod = HttpMethod.GET)
  def feed(@Named("page") @Nullable page: Integer, user: User): Array[Article] = {
    if (user != null) {
      log.info(s"${user.toString} - email: ${user.getEmail}") // TODO remove
      userService.saveIfMissing(user) // TODO tady? a kdy a jak mu pridat clanky?
      feedService.getFeed(user.getEmail)
    } else {
      //TODO throw new OAuthRequestException("Unauthorized")
      log.info(s"user is null")
      val testEmail = "test@gmail.com"
      userService.saveIfMissing(new User(testEmail, "testDomain"))
      // TODO onboarding (fill user's feed)
      feedService.getFeed(testEmail)
    }
  }

  /**
   * Record client action
   * @param action action id (read, save, ignore,...)
   * @param articleLink link of affected article
   * @param user user
   * @return list of newly awarded badges or empty list
   */
  @ApiMethod(name = "action", path = "action", httpMethod = HttpMethod.POST)
  def action(@Named("action") action: String, @Named("articleLink") articleLink: String, user: User):Array[Badge] = {
    if (user != null) {
      feedService.action(action, articleLink, user.getEmail)
    } else {
      //TODO throw new OAuthRequestException("Unauthorized")
      feedService.action(action, articleLink, "test@gmail.com")
      Array(new Badge("Stará bačkora", "Přečetl jsi recenze 10 černobílých filmů", "http://www.nadmerneboty.cz/fotky4936/fotos/_vyr_266BP0773.jpg"))
    }
  }

  /**
   * Get badges for the given user
   * @param user user
   * @return list of badges or empty list
   */
  @ApiMethod(name = "badges", path = "badges", httpMethod = HttpMethod.GET)
  def badges(user: User): Array[Badge] = {
    if (user != null) {
      categoryDao.find(user.getEmail)
    } else {
      throw new OAuthRequestException("Unauthorized")
    }
  }
}
