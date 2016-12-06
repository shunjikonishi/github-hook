package github.events

import play.api.libs.json.JsValue
import github.GitHubEvent
import github.models.PullRequest
import github.models.Review
import github.enums.PullRequestReviewAction

case class PullRequestReviewEvent(name: String, value: JsValue) extends GitHubEvent {
  lazy val action = PullRequestReviewAction.fromString((value \ "action").as[String])
  lazy val review = Review(value \ "review")
  lazy val pull_request = PullRequest(value \ "pull_request")
}

