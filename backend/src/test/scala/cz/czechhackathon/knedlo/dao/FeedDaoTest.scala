package cz.czechhackathon.knedlo.dao

import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.mock.MockitoSugar
import org.scalatest.matchers.ShouldMatchers
import com.google.appengine.api.datastore.{Entity, DatastoreService}
import cz.czechhackathon.knedlo.model.Article
import org.mockito.Mockito.verify
import org.hamcrest.core.AnyOf.anyOf

class FeedDaoTest extends FunSuite with BeforeAndAfter with MockitoSugar with ShouldMatchers {

  val userId = "blah"
  val title = "tit"
  val link = "link"
  val article = new Article(title, link)

  var ds: DatastoreService = _
  var dao: FeedDao = _

  before {
    ds = mock[DatastoreService]
    dao = new FeedDao(ds)
  }

  test("store") {
    dao.save(article, userId)
    //TODO
  }
}
