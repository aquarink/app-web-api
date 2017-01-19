
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
object create extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.Corporate,models.SecurityRole,Form[models.User],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate:models.Corporate, role: models.SecurityRole, myForm: Form[models.User]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.84*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Create User", Seq(
    routes.Corporate.admin_index().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate Management")
    , "" -> corporate.name
    , "" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG374","Add new user")
))/*10.3*/ {_display_(Seq[Any](format.raw/*10.5*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*12.7*/doku/*12.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG375","Add new"))),format.raw/*12.73*/(""" """),_display_(Seq[Any](/*12.75*/role/*12.79*/.description)),format.raw/*12.91*/("""</h1>
</div>

"""),_display_(Seq[Any](/*15.2*/partial/*15.9*/.flash_alert())),format.raw/*15.23*/("""

"""),_display_(Seq[Any](/*17.2*/form(routes.User.create(corporate.code),
'class -> "form-vertical form-user form-horizontal",
'enctype -> "multipart/form-data",
'autocomplete -> "off"
)/*21.2*/ {_display_(Seq[Any](format.raw/*21.4*/("""
<input type="hidden" name="id" value=""""),_display_(Seq[Any](/*22.40*/myForm("id")/*22.52*/.value)),format.raw/*22.58*/(""""/>

"""),_display_(Seq[Any](/*24.2*/user/*24.6*/.formBody(role, myForm, true))),format.raw/*24.35*/("""

""")))})),format.raw/*26.2*/("""
""")))}/*27.2*/ {_display_(Seq[Any](format.raw/*27.4*/("""
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*28.39*/routes/*28.45*/.Assets.at("javascripts/user.js"))),format.raw/*28.78*/("""'></script>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*29.39*/routes/*29.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*29.95*/("""'></script>
""")))})),format.raw/*30.2*/("""
"""))}
    }
    
    def render(corporate:models.Corporate,role:models.SecurityRole,myForm:Form[models.User]): play.api.templates.Html = apply(corporate,role,myForm)
    
    def f:((models.Corporate,models.SecurityRole,Form[models.User]) => play.api.templates.Html) = (corporate,role,myForm) => apply(corporate,role,myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/create.scala.html
                    HASH: 1bcacdee5f895115966d5782e741afd25a6a6e5f
                    MATRIX: 777->1|986->83|1014->136|1050->138|1325->405|1364->407|1432->440|1445->444|1529->506|1567->508|1580->512|1614->524|1664->539|1679->546|1715->560|1753->563|1914->716|1953->718|2029->758|2050->770|2078->776|2119->782|2131->786|2182->815|2216->818|2236->820|2275->822|2350->861|2365->867|2420->900|2506->950|2521->956|2593->1006|2637->1019
                    LINES: 26->1|32->1|34->5|35->6|39->10|39->10|41->12|41->12|41->12|41->12|41->12|41->12|44->15|44->15|44->15|46->17|50->21|50->21|51->22|51->22|51->22|53->24|53->24|53->24|55->26|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30
                    -- GENERATED --
                */
            