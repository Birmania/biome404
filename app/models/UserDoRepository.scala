package models

import anorm.{RowParser, SQL, SqlParser, ~}
import controllers.WidgetForm
import javax.inject.{Inject, Singleton}
import play.api.db.Database
import play.api.mvc.ControllerComponents

@Singleton
class UserDoRepository @Inject()(db: Database) {
  def insertUser(newUser: WidgetForm.User) = {
    db.withConnection { implicit conn =>
      SQL(
        """
          INSERT INTO UserDo (firstname, lastname) VALUES ({firstname}, {lastname});
        """
      ).on("firstname" -> newUser.firstname,
        "lastname" -> newUser.lastname
      ).execute()
    }
  }

  def selectUsers = {
    val rowParser: RowParser[WidgetForm.User] = {
      SqlParser.get[String]("firstname") ~
        SqlParser.get[String]("lastname") map {
        case firstname~lastname => WidgetForm.User(firstname, lastname)
      }
    }

    db.withConnection { implicit conn =>
      SQL(
        """
          SELECT * FROM UserDo;
        """
      ).as(rowParser *)
    }
  }
}
