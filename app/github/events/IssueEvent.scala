package github.events

import play.api.libs.json.JsValue
import github.GitHubEvent

case class IssueEvent(name: String, value: JsValue) extends GitHubEvent {
}
