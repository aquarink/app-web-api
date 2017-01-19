
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
object editOperasional extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.Corporate,models.SecurityRole,Form[models.User],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate:models.Corporate, role:models.SecurityRole, myForm: Form[models.User]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.83*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Edit "+role.description, Seq(
routes.User.show(corporate.code, Long.parseLong(myForm("id").value)).toString -> myForm("email").value,
"" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG112","Edit")
))/*9.3*/ {_display_(Seq[Any](format.raw/*9.5*/("""
<div class="page-header">
	<h1>Edit user """),_display_(Seq[Any](/*11.17*/role/*11.21*/.description)),format.raw/*11.33*/("""</h1>
</div>

"""),_display_(Seq[Any](/*14.2*/partial/*14.9*/.flash_alert())),format.raw/*14.23*/("""

"""),_display_(Seq[Any](/*16.2*/form(routes.User.updateOperasional(corporate.code, Long.parseLong(myForm("id").value)),
'class -> "form-vertical form-user form-horizontal",
'enctype -> "multipart/form-data",
'autocomplete -> "off"
)/*20.2*/ {_display_(Seq[Any](format.raw/*20.4*/("""
<input type="hidden" name="id" value=""""),_display_(Seq[Any](/*21.40*/myForm("id")/*21.52*/.value)),format.raw/*21.58*/(""""/>

"""),_display_(Seq[Any](/*23.2*/user/*23.6*/.formBody(role, myForm, false))),format.raw/*23.36*/("""

""")))})),format.raw/*25.2*/("""
""")))}/*26.2*/ {_display_(Seq[Any](format.raw/*26.4*/("""
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*27.39*/routes/*27.45*/.Assets.at("javascripts/user.js"))),format.raw/*27.78*/("""'></script>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*28.39*/routes/*28.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*28.95*/("""'></script>
""")))})),format.raw/*29.2*/("""
"""))}
    }
    
    def render(corporate:models.Corporate,role:models.SecurityRole,myForm:Form[models.User]): play.api.templates.Html = apply(corporate,role,myForm)
    
    def f:((models.Corporate,models.SecurityRole,Form[models.User]) => play.api.templates.Html) = (corporate,role,myForm) => apply(corporate,role,myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/editOperasional.scala.html
                    HASH: 29c027251d7648d65a1922aa20cf8a80518aade9
                    MATRIX: 786->1|994->82|1022->135|1058->137|1279->351|1317->353|1396->396|1409->400|1443->412|1493->427|1508->434|1544->448|1582->451|1790->651|1829->653|1905->693|1926->705|1954->711|1995->717|2007->721|2059->751|2093->754|2113->756|2152->758|2227->797|2242->803|2297->836|2383->886|2398->892|2470->942|2514->955
                    LINES: 26->1|32->1|34->5|35->6|38->9|38->9|40->11|40->11|40->11|43->14|43->14|43->14|45->16|49->20|49->20|50->21|50->21|50->21|52->23|52->23|52->23|54->25|55->26|55->26|56->27|56->27|56->27|57->28|57->28|57->28|58->29
                    -- GENERATED --
                */
            