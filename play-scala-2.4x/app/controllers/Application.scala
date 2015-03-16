package controllers

import play.api._
import play.api.mvc._

import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def ping = Action {
    Ok("pong")
  }

  def echoJSON = Action(parse.json) { implicit request =>
    //println(s"request.body[${request.body.getClass}]")
    Ok(Json.obj("now" -> JsNumber((new java.util.Date).getTime),
                "data" -> request.body))
      .as(JSON)
  }
}