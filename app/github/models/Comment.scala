package github.models

import play.api.libs.json.JsValue

case class Comment(value: JsValue) {
  def body = (value \ "body").as[String]
}