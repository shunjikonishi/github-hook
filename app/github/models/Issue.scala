package github.models

import play.api.libs.json.JsValue

case class Issue(value: JsValue) {
  def number = (value \ "number").as[Long]
}