package controllers

import controllers.WidgetForm._
import javax.inject.{Inject, Singleton}
import models.UserDoRepository
import play.api.i18n.I18nSupport
import play.api.libs.json.JsValue
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class UserRestController @Inject()(userDoRepository: UserDoRepository, cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {



  def read = Action { implicit request =>
    val users : Seq[WidgetForm.User] = userDoRepository.selectUsers

    Ok(JsonConverter.toJson(users))
  }

  def create = Action { implicit request =>
    val jsonValue: JsValue = request.body.asJson.get
    val newUser = JsonConverter.fromJsonOne(jsonValue)

    userDoRepository.insertUser(newUser)

    val users : Seq[WidgetForm.User] = userDoRepository.selectUsers

    Ok(JsonConverter.toJson(users))
  }
}
