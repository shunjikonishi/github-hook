package models

import play.api.libs.json.Json
import play.api.libs.json.JsValue

trait GitHubMessage {
  val name: String
  val value: JsValue
  override def toString = name + "\n" + Json.prettyPrint(value)
}

case class DefaultGitHubMessage(name: String, value: JsValue) extends GitHubMessage
case class Issue(name: String, value: JsValue) extends GitHubMessage

object GitHubMessage {
  def apply(name: String, value: JsValue): GitHubMessage = name match {
    case "issue" => Issue(name, value)
    case _ => DefaultGitHubMessage(name, value)
  }
}

