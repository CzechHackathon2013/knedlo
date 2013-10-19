package cz.czechhackathon.knedlo.model

import scala.beans.BeanProperty

class Article(@BeanProperty val title: String,
              @BeanProperty val link: String,
              @BeanProperty val description: String = null,
              @BeanProperty val source: String = null,
              @BeanProperty val image: String = null,
              @BeanProperty val category: String = null
             ) {

  override def toString: String = s"$title $link"
}
