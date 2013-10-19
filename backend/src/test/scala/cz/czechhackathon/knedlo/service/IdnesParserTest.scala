package cz.czechhackathon.knedlo.service

import org.scalatest.FunSuite
import java.io.FileInputStream

class IdnesParserTest extends FunSuite{

  test("idnes parser") {
    val parser = new IdnesParser
    val articles = parser.parse(new FileInputStream("src/test/resources/idnes.xml"))
    assert(articles.size === 25)
    val article = articles(0)
    assert(article.title === "Desetibojařský král Eaton čeká na soupeře, mezitím bude běhat překážky")
    assert(article.category === "Sport")
    assert(article.link === "http://sport.idnes.cz/desetibojar-eaton-bude-behat-400-metru-prekazek-foz-/atletika.aspx?c=A131019_142053_atletika_tp#utm_source=rss&utm_medium=feed&utm_campaign=idnes&utm_content=main")
  }

}