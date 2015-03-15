package models

import play.api.libs.json.Json
import play.api.libs.json.JsValue

trait GitHubEvent {
  val name: String
  val value: JsValue

  def owner = (value \ "repository" \ "owner" \ "name").as[String]
  def repo = (value \ "repository" \ "name").as[String]
  override def toString = name + "\n" + Json.prettyPrint(value)
}

case class DefaultGitHubEvent(name: String, value: JsValue) extends GitHubEvent

case class IssueEvent(name: String, value: JsValue) extends GitHubEvent

case class IssueCommentEvent(name: String, value: JsValue) extends GitHubEvent {
  def comment = (value \ "comment" \ "body").as[String]
}

case class PullRequestEvent(name: String, value: JsValue) extends GitHubEvent {
  def action = (value \ "action").as[String]
  def number = (value \ "number").as[Long]
}

object PullReqestAction {
  val assigned = "assigned"
  val unassigned = "unassigned"
  val labeled = "labeled"
  val unlabeled = "unlabeled"
  val opened = "opened"
  val closed = "closed"
  val repopened = "repopened"
  val synchronize = "syncronize"
}

object GitHubEvent {
  def apply(name: String, value: JsValue): GitHubEvent = name match {
    case "issue" => IssueEvent(name, value)
    case "issue_comment" => IssueCommentEvent(name, value)
    case _ => DefaultGitHubEvent(name, value)
  }
}

