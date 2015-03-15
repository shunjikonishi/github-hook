package models

import play.api.libs.json.Json
import play.api.libs.json.JsValue

trait GitHubMessage {
  val name: String
  val value: JsValue

  def owner = (value \ "repository" \ "owner" \ "name").as[String]
  def repo = (value \ "repository" \ "name").as[String]
  override def toString = name + "\n" + Json.prettyPrint(value)
}

case class DefaultGitHubMessage(name: String, value: JsValue) extends GitHubMessage

case class IssueMessage(name: String, value: JsValue) extends GitHubMessage

case class IssueCommentMessage(name: String, value: JsValue) extends GitHubMessage {
  def comment = (value \ "comment" \ "body").as[String]
}

case class PullRequestMessage(name: String, value: JsValue) extends GitHubMessage {

}

object GitHubMessage {
  def apply(name: String, value: JsValue): GitHubMessage = name match {
    case "issue" => IssueMessage(name, value)
    case "issue_comment" => IssueCommentMessage(name, value)
    case _ => DefaultGitHubMessage(name, value)
  }
}

