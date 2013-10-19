package cz.czechhackathon.knedlo.dao

import cz.czechhackathon.knedlo.model.Badge

class CategoryDao {

  def find(email: String): Array[Badge] = {
    Category.query()
      .filter(c => (c.email #== email) and (c.count #>= 10)).asIterator()
      .map(c => Badge(c.category))
      .toArray
  }

  def update(email: String, category: String, status: Long) {
    val cat = Category.query()
      .filter(c => (c.category #== category) and (c.email #== email))
      .asSingle()
    cat.count = status + cat.count
    cat.put()
  }

}