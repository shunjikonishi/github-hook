package github.events

import play.api.libs.json.JsValue
import github.GitHubEvent
import github.models.Comment
import github.models.Issue

case class IssueCommentEvent(name: String, value: JsValue) extends GitHubEvent {
  lazy val issue = Issue(value \ "issue")
  lazy val comment = Comment(value \ "comment")
}
