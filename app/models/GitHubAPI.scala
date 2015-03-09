package models

import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.json._

class GitHubAPI(oauthToken: String, owner: String, repo: String) {
  private val baseUrl = s"https://api.githbu.com/{owner}/{repo}"

  private def exec(method: String, url: String, body: JsValue = JsNull) = {
    val ws = WS.url(url).withHeaders(
      "Authorization" -> ("token " + oauthToken),
      "Content-Type" -> "application/json"
    )
    method match {
      case "POST" => ws.post(body)
      case "DELETE" => ws.delete()
    }
  }

  def addLabels(id: Long, labels: String*) = {
    val url = baseUrl + "/issues/" + id + "/labels"
    val body = JsObject(
      Seq(
        "labels" -> JsArray(labels.map(JsString(_)))
      )
    )
    exec("POST", url, body)
  }

  def removeLabel(id: Long, label: String) = {
    val url = baseUrl + "/issues/" + id + "/labels/" + label
    exec("DELETE", url)
  }

  def assign(id: Long, assignee: String) = {
    val url = baseUrl + "/issues/" + id
    val body = JsObject(
      Seq(
        "assignee" -> JsString(assignee)
      )
    )
    exec("POST", url, body)
  }
}