package github.models

import play.api.libs.json.JsValue

case class User(value: JsValue) {
println("*******************" + play.api.libs.json.Json.prettyPrint(value))
  def id = (value \ "id").as[Long]
  def login = (value \ "login").as[String]
}