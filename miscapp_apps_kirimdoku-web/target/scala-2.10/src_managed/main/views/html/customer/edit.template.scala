
package views.html.customer

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
object edit extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Long,Form[models.Customer],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(id:Long, myForm: Form[models.Customer]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.42*/("""

"""),_display_(Seq[Any](/*3.2*/layout("Customer")/*3.20*/ {_display_(Seq[Any](format.raw/*3.22*/("""
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*4.50*/routes/*4.56*/.Assets.at("stylesheets/customer.css"))),format.raw/*4.94*/("""">
    <div class="span8 offset2">
        """),_display_(Seq[Any](/*6.10*/helper/*6.16*/.form(routes.Customer.update(id), 'autoComplete -> "off", 'class -> "row form form-horizontal")/*6.111*/ {_display_(Seq[Any](format.raw/*6.113*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*8.22*/doku/*8.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG113","Edit Customer"))),format.raw/*8.94*/("""</h3>
            </div>

            """),_display_(Seq[Any](/*11.14*/customer/*11.22*/.formBody(myForm, true))),format.raw/*11.45*/("""

            <a href=""""),_display_(Seq[Any](/*13.23*/routes/*13.29*/.Customer.list())),format.raw/*13.45*/("""" class="btn btn-inverse">"""),_display_(Seq[Any](/*13.72*/doku/*13.76*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*13.137*/("""</a>
            <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*14.60*/doku/*14.64*/.kirimdoku.util.MultiLanguage.getLanguage("LANG396","Update"))),format.raw/*14.125*/("""</button>
        """)))})),format.raw/*15.10*/("""
    </div>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*17.39*/routes/*17.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*17.95*/("""'></script>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*18.39*/routes/*18.45*/.Assets.at("javascripts/customer.js"))),format.raw/*18.82*/("""'></script>
""")))})),format.raw/*19.2*/("""
"""))}
    }
    
    def render(id:Long,myForm:Form[models.Customer]): play.api.templates.Html = apply(id,myForm)
    
    def f:((Long,Form[models.Customer]) => play.api.templates.Html) = (id,myForm) => apply(id,myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/edit.scala.html
                    HASH: cb864d4ae2eb01a371ab7675a7225bab8c4639ed
                    MATRIX: 751->1|868->41|905->44|931->62|970->64|1055->114|1069->120|1128->158|1207->202|1221->208|1325->303|1365->305|1463->368|1475->372|1564->440|1639->479|1656->487|1701->510|1761->534|1776->540|1814->556|1877->583|1890->587|1974->648|2074->712|2087->716|2171->777|2222->796|2308->846|2323->852|2395->902|2481->952|2496->958|2555->995|2599->1008
                    LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4|32->4|34->6|34->6|34->6|34->6|36->8|36->8|36->8|39->11|39->11|39->11|41->13|41->13|41->13|41->13|41->13|41->13|42->14|42->14|42->14|43->15|45->17|45->17|45->17|46->18|46->18|46->18|47->19
                    -- GENERATED --
                */
            