
package views.html.user

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object show extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: models.User):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.21*/("""


"""),_display_(Seq[Any](/*4.2*/layout("User account detail", Seq(
"" -> ""
))/*6.3*/ {_display_(Seq[Any](format.raw/*6.5*/("""
"""),_display_(Seq[Any](/*7.2*/partial/*7.9*/.flash_alert())),format.raw/*7.23*/("""
	"""),_display_(Seq[Any](/*8.3*/summary(user))),format.raw/*8.16*/("""
""")))})),format.raw/*9.2*/("""
"""))}
    }
    
    def render(user:models.User): play.api.templates.Html = apply(user)
    
    def f:((models.User) => play.api.templates.Html) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/show.scala.html
                    HASH: aeb316dd66baefcc6b481f67343d633417975a9f
                    MATRIX: 732->1|828->20|866->24|919->70|957->72|993->74|1007->81|1042->95|1079->98|1113->111|1145->113
                    LINES: 26->1|29->1|32->4|34->6|34->6|35->7|35->7|35->7|36->8|36->8|37->9
                    -- GENERATED --
                */
            