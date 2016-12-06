package github.models

import play.api.libs.json.JsValue

case class PullRequest(value: JsValue) {
  def number = (value \ "number").as[Long]
  def body = (value \ "body").as[String]  
  lazy val user = User(value \ "user")
}