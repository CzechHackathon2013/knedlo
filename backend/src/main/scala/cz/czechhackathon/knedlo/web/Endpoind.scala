package cz.czechhackathon.knedlo.web

import com.google.api.server.spi.config.{ApiMethod, Api}
import com.google.api.server.spi.config.ApiMethod.HttpMethod
import cz.czechhackathon.knedlo.model.{Badge, Article}
import com.google.appengine.api.users.User
import javax.inject.Named
import javax.annotation.Nullable

@Api(name = "knedlo", version = "v1", clientIds = Array(
  "338711060290.apps.googleusercontent.com", // iOS
  "338711060290-5hmko3leeamlt9us7vcpdg7l2a5q163n.apps.googleusercontent.com" // Android
))
class Endpoind {

  /**
   * Get paged article feed for the given user
   * @param page page number (first page if not entered)
   * @param user user
   * @return list of articles or empty list
   */
  @ApiMethod(name = "feed", path = "feed", httpMethod = HttpMethod.GET)
  def feed(@Named("page") @Nullable page: Integer, user: User):Array[Article] = {
    Array(new Article("foo", "http://foo"))
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
    Array()
  }

  /**
   * Get badges for the given user
   * @param user user
   * @return list of badges or empty list
   */
  @ApiMethod(name = "badges", path = "badges", httpMethod = HttpMethod.GET)
  def badges(user: User):Array[Badge] = {
    Array(new Badge("foo", "foo description", "http://foo"))
  }


}
