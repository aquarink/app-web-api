
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
object edit extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.Bank],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(id:String, myForm: Form[models.Bank]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.40*/("""

"""),_display_(Seq[Any](/*3.2*/layout("Bank")/*3.16*/ {_display_(Seq[Any](format.raw/*3.18*/("""
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*4.50*/routes/*4.56*/.Assets.at("stylesheets/customer.css"))),format.raw/*4.94*/("""">
    <div class="span8 offset2">
        """),_display_(Seq[Any](/*6.10*/helper/*6.16*/.form(routes.Banks.update(id), 'autoComplete -> "off")/*6.70*/ {_display_(Seq[Any](format.raw/*6.72*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*8.22*/doku/*8.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG395","Edit Bank"))),format.raw/*8.90*/("""</h3>
            </div>

            """),_display_(Seq[Any](/*11.14*/banks/*11.19*/.formBody(myForm, true))),format.raw/*11.42*/("""

            <a href=""""),_display_(Seq[Any](/*13.23*/routes/*13.29*/.Banks.list())),format.raw/*13.42*/("""" class="btn btn-inverse">"""),_display_(Seq[Any](/*13.69*/doku/*13.73*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*13.134*/("""</a>
            <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*14.60*/doku/*14.64*/.kirimdoku.util.MultiLanguage.getLanguage("LANG396","Update"))),format.raw/*14.125*/("""</button>
        """)))})),format.raw/*15.10*/("""
    </div>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*17.39*/routes/*17.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*17.95*/("""'></script>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*18.39*/routes/*18.45*/.Assets.at("javascripts/customer.js"))),format.raw/*18.82*/("""'></script>
""")))})),format.raw/*19.2*/("""
"""))}
    }
    
    def render(id:String,myForm:Form[models.Bank]): play.api.templates.Html = apply(id,myForm)
    
    def f:((String,Form[models.Bank]) => play.api.templates.Html) = (id,myForm) => apply(id,myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/banks/edit.scala.html
                    HASH: 72583bf6019c51d2a83b310dd49de1dc871a32a0
                    MATRIX: 746->1|861->39|898->42|920->56|959->58|1044->108|1058->114|1117->152|1196->196|1210->202|1272->256|1311->258|1409->321|1421->325|1506->389|1581->428|1595->433|1640->456|1700->480|1715->486|1750->499|1813->526|1826->530|1910->591|2010->655|2023->659|2107->720|2158->739|2244->789|2259->795|2331->845|2417->895|2432->901|2491->938|2535->951
                    LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4|32->4|34->6|34->6|34->6|34->6|36->8|36->8|36->8|39->11|39->11|39->11|41->13|41->13|41->13|41->13|41->13|41->13|42->14|42->14|42->14|43->15|45->17|45->17|45->17|46->18|46->18|46->18|47->19
                    -- GENERATED --
                */
            