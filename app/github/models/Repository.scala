package github.models

import play.api.libs.json.JsValue

case class Repository(value: JsValue) {
  def id = (value \ "id").as[Long]
  def name = (value \ "name").as[String]

  lazy val owner = User(value \ "owner")
}