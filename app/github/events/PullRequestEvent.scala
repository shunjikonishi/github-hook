package github.events

import play.api.libs.json.JsValue
import github.GitHubEvent
import github.models.PullRequest
import github.enums.PullRequestAction

case class PullRequestEvent(name: String, value: JsValue) extends GitHubEvent {
  def number = (value \ "number").as[Long]
  
  lazy val action = PullRequestAction.fromString((value \ "action").as[String])
  lazy val pull_request = PullRequest(value \ "pull_request")
}

