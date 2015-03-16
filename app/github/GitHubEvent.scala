package github

import play.api.libs.json.Json
import play.api.libs.json.JsValue
import models.Repository

trait GitHubEvent {
  val name: String
  val value: JsValue

  lazy val repository = Repository(value \ "repository")
  override def toString = name + "\n" + Json.prettyPrint(value)
}

object GitHubEvent {
  import events._
  
  def apply(name: String, value: JsValue): GitHubEvent = name match {

    case "issue" => IssueEvent(name, value)
    case "issue_comment" => IssueCommentEvent(name, value)
    case "pull_request" => PullRequestEvent(name, value)
    case _ => DefaultEvent(name, value)
  }
}

