package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hook = Action(parse.tolerantJson) { request =>
    val name = request.headers("X-Github-Event")
    println(name + "\n" + Json.prettyPrint(request.body))
    Ok("OK")
  }

}