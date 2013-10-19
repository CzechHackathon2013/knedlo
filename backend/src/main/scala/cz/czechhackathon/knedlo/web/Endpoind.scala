package cz.czechhackathon.knedlo.web

import com.google.api.server.spi.config.{ApiMethod, Api}
import com.google.api.server.spi.config.ApiMethod.HttpMethod
import cz.czechhackathon.knedlo.model.Article
import com.google.appengine.api.users.User
import javax.inject.Named
import javax.annotation.Nullable

@Api(version = "v1", clientIds = Array(
  "338711060290.apps.googleusercontent.com", // iOS
  "338711060290-5hmko3leeamlt9us7vcpdg7l2a5q163n.apps.googleusercontent.com" // Android
))
class Endpoind {

  @ApiMethod(name = "feed", path = "feed", httpMethod = HttpMethod.GET)
  def feed(@Named @Nullable page: Integer,  user: User):Array[Article] = {
    Array(new Article("foo", "http://foo"))
  }
}
