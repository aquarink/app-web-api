
package views.html.banks

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
object create extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.Bank],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Bank]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.29*/("""
"""),_display_(Seq[Any](/*2.2*/layout("Bank")/*2.16*/ {_display_(Seq[Any](format.raw/*2.18*/("""
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*3.50*/routes/*3.56*/.Assets.at("stylesheets/customer.css"))),format.raw/*3.94*/("""">
	"""),_display_(Seq[Any](/*4.3*/partial/*4.10*/.flash_alert())),format.raw/*4.24*/("""
    <div class="span8 offset2">
		"""),_display_(Seq[Any](/*6.4*/helper/*6.10*/.form(routes.Banks.save, 'autoComplete -> "off")/*6.58*/ {_display_(Seq[Any](format.raw/*6.60*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*8.22*/doku/*8.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG392","Add New Bank"))),format.raw/*8.93*/("""</h3>
            </div>

            """),_display_(Seq[Any](/*11.14*/banks/*11.19*/.formBody(myForm))),format.raw/*11.36*/("""

            <div class="form-actions">
                <a href=""""),_display_(Seq[Any](/*14.27*/routes/*14.33*/.Banks.list())),format.raw/*14.46*/("""" class="btn btn-inverse">"""),_display_(Seq[Any](/*14.73*/doku/*14.77*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*14.138*/("""</a>
                <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*15.64*/doku/*15.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*15.127*/("""</button>
            </div>
        """)))})),format.raw/*17.10*/("""
    </div>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*19.39*/routes/*19.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*19.95*/("""'></script>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*20.39*/routes/*20.45*/.Assets.at("javascripts/customer.js"))),format.raw/*20.82*/("""'></script>
""")))})),format.raw/*21.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.Bank]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[models.Bank]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/banks/create.scala.html
                    HASH: 3449c750f1bfeb403e624fb50f2b0843c6eaa91c
                    MATRIX: 741->1|845->28|881->30|903->44|942->46|1027->96|1041->102|1100->140|1139->145|1154->152|1189->166|1259->202|1273->208|1329->256|1368->258|1466->321|1478->325|1566->392|1641->431|1655->436|1694->453|1797->520|1812->526|1847->539|1910->566|1923->570|2007->631|2111->699|2124->703|2206->762|2276->800|2362->850|2377->856|2449->906|2535->956|2550->962|2609->999|2653->1012
                    LINES: 26->1|29->1|30->2|30->2|30->2|31->3|31->3|31->3|32->4|32->4|32->4|34->6|34->6|34->6|34->6|36->8|36->8|36->8|39->11|39->11|39->11|42->14|42->14|42->14|42->14|42->14|42->14|43->15|43->15|43->15|45->17|47->19|47->19|47->19|48->20|48->20|48->20|49->21
                    -- GENERATED --
                */
            