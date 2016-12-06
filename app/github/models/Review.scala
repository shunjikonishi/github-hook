package github.models

import play.api.libs.json.JsValue
import github.enums.PullRequestReviewState

case class Review(value: JsValue) {
  lazy val state = PullRequestReviewState.fromString((value \ "state").as[String])
}