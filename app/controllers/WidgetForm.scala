package controllers

import play.api.data._
import play.api.data.Forms._


object WidgetForm {
  case class User (firstname: String, lastname: String)

  val userForm = Form(
    mapping(
      "firstname" -> text,
      "lastname"  -> text
    )(WidgetForm.User.apply)(WidgetForm.User.unapply)
  )
}
