package github

import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.json._

case class GitHubAPI(oauthToken: String, owner: String, repo: String) {
  private val baseUrl = s"https://api.githbu.com/$owner/$repo"

  private def exec(method: String, url: String, body: JsValue = JsNull) = {
println("API execute: " + url + ", " + body)
    val ws = WS.url(url).withHeaders(
      "Authorization" -> ("token " + oauthToken),
      "Content-Type" -> "application/json"
    )
    val ret = method match {
      case "POST" => ws.post(body)
      case "DELETE" => ws.delete()
    }
println("API result: " + ret.get)
  }

  def addLabels(number: Long, labels: String*) = {
    val url = baseUrl + "/issues/" + number + "/labels"
    val body = JsObject(
      Seq(
        "labels" -> JsArray(labels.map(JsString(_)))
      )
    )
    exec("POST", url, body)
  }

  def removeLabel(number: Long, label: String) = {
    val url = baseUrl + "/issues/" + number + "/labels/" + label
    exec("DELETE", url)
  }

  def assign(number: Long, assignee: String) = {
    val url = baseUrl + "/issues/" + number
    val body = JsObject(
      Seq(
        "assignee" -> JsString(assignee)
      )
    )
    exec("POST", url, body)
  }
}