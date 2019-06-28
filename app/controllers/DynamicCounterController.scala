package controllers

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import javax.inject.{Inject, Singleton}
import models.{UserDo, UserDoRepository}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, MessagesRequest}
import WidgetForm._
import play.api.db.Database
import play.api.i18n.I18nSupport
import anorm._

@Singleton
class DynamicCounterController @Inject()(userDoRepository: UserDoRepository, cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) with I18nSupport {

  def index = Action { implicit request =>
    val users : Seq[WidgetForm.User] = userDoRepository.selectUsers

    Ok(views.html.index(users, userForm))
  }

  def post = Action { implicit request =>
    val newUser = userForm.bindFromRequest().get

    userDoRepository.insertUser(newUser)

    Redirect(routes.DynamicCounterController.index)
  }


}
