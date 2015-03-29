package github

import java.net.URLEncoder
import play.api.Logger
import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global

case class GitHubAPI(oauthToken: String, owner: String, repo: String) {
  private val baseUrl = s"https://api.github.com/repos/$owner/$repo"

  private def exec(method: String, url: String, body: JsValue = JsNull) = {
    Logger.info("API execute: " + url + ", " + body)
    val ws = WS.url(url).withHeaders(
      "Authorization" -> ("token " + oauthToken),
      "Content-Type" -> "application/json"
    )
    val ret = method match {
      case "POST" => ws.post(body)
      case "DELETE" => ws.delete()
    }
    ret.onSuccess {
      case res => Logger.info("API success: " + res.status + ", " + res.body)
    }
    ret.onFailure {
      case e => 
        Logger.error("API failure: ")
        e.printStackTrace
    }
  }

  def addLabels(number: Long, labels: String*) = {
    val url = baseUrl + "/issues/" + number + "/labels"
    val body = JsArray(labels.map(JsString(_)))
    exec("POST", url, body)
  }

  def removeLabel(number: Long, label: String) = {
    val url = baseUrl + "/issues/" + number + "/labels/" + URLEncoder.encode(label, "utf-8").replaceAll("\\+", "%20")
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