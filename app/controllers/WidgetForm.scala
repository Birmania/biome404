package controllers

import com.fasterxml.jackson.databind.JsonNode
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.{JsPath, JsValue, Json, Reads, Writes}
import play.api.libs.functional.syntax._

object WidgetForm {
  case class User (firstname: String, lastname: String)

  val userForm = Form(
    mapping(
      "firstname" -> text,
      "lastname"  -> text
    )(WidgetForm.User.apply)(WidgetForm.User.unapply)
  )

  def emptyUserForm = userForm.fill(WidgetForm.User("", ""))
}

object JsonConverter {
  implicit val usersWrites = new Writes[WidgetForm.User] {
    def writes(u: WidgetForm.User) = Json.obj(
      "firstname" -> u.firstname,
      "lastname" -> u.lastname,
    )
  }

  implicit val usersReads: Reads[WidgetForm.User] = (
    (JsPath \ "firstname").read[String] and
      (JsPath \ "lastname").read[String]
  ) (WidgetForm.User.apply _)

  def toJson(users: Seq[WidgetForm.User]): JsValue = Json.toJson(users)

  def toJsonOne(user: WidgetForm.User): JsValue = Json.toJson(user)

  def fromJsonOne(json: JsValue): WidgetForm.User = Json.fromJson(json).get
}
