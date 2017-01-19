
package views.html.dashboard

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
object admin_index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user:models.User):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.20*/("""

"""),_display_(Seq[Any](/*3.2*/layout("Admin Dashboard")/*3.27*/ {_display_(Seq[Any](format.raw/*3.29*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*5.7*/doku/*5.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG189","Admin Dashboard"))),format.raw/*5.81*/("""</h1>
</div>
""")))})),format.raw/*7.2*/("""
"""))}
    }
    
    def render(user:models.User): play.api.templates.Html = apply(user)
    
    def f:((models.User) => play.api.templates.Html) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/dashboard/admin_index.scala.html
                    HASH: d68736ffd22d821bf4360fb76705432fd7a6ee35
                    MATRIX: 744->1|839->19|876->22|909->47|948->49|1015->82|1027->86|1118->156|1162->170
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|35->7
                    -- GENERATED --
                */
            