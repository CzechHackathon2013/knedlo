package cz.czechhackathon.knedlo.dao

import cz.czechhackathon.knedlo.model.Badge

class CategoryDao(val articlesToGetABadge:Int = 2) {

  def find(email: String): Array[Badge] = {
    Category.query()
      .filter(c => {
      (c.email #== email) and (c.count #>= articlesToGetABadge)
    }).asIterator()
      .map(c => Badge(c.category))
      .toArray
  }

  def update(email: String, category: String, status: Long) {
    val i = Category.query()
      .filter(c => (c.category #== category) and (c.email #== email))
      .asIterator()
    val cat = if (i.hasNext) i.next() else new Category(email, category, 0)
    cat.count = status + cat.count
    cat.put()
  }

}