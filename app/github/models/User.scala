package github.models

import play.api.libs.json.JsValue

case class User(value: JsValue) {
  def name: String = (value \ "name").asOpt[String].getOrElse((value \ "login").as[String])
}