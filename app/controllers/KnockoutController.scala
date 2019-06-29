package controllers

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import javax.inject.{Inject, Singleton}
import models.UserDoRepository
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, ControllerComponents}
import WidgetForm._
import play.api.libs.json.{Json, Writes}

@Singleton
class KnockoutController @Inject()(userDoRepository: UserDoRepository, cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  def index = Action { implicit request =>
    Ok(views.html.knockout(emptyUserForm))
  }
}
