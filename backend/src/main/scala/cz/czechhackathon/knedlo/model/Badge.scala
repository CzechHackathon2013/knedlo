package cz.czechhackathon.knedlo.model

import scala.beans.BeanProperty

class Badge(@BeanProperty val title: String,
            @BeanProperty val description: String,
            @BeanProperty val imageLink: String
           ) {

}

object Badge {
  def apply(title: String) = new Badge(title, null, null)
}