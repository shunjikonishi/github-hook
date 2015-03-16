package github.events

import play.api.libs.json.JsValue
import github.GitHubEvent

case class DefaultEvent(name: String, value: JsValue) extends GitHubEvent
