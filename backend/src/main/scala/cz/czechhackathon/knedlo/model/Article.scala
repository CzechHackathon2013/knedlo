package cz.czechhackathon.knedlo.model

import scala.beans.BeanProperty

class Article(@BeanProperty val title: String,
              @BeanProperty val link: String,
              @BeanProperty val description: String = _,
              @BeanProperty val source: String = _,
              @BeanProperty val image: String = _,
              @BeanProperty val category: String =_
             ) {
}
