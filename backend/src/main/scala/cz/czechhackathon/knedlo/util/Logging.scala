package cz.czechhackathon.knedlo.util

import com.twitter.logging.Logger

/**
 * Mixin to use for logging.
 */
trait Logging {
  lazy val log = Logger.get(getClass)
}
