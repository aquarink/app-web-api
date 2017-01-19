
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
object create extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.Customer],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Customer]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.33*/("""
"""),_display_(Seq[Any](/*2.2*/layout("Customer")/*2.20*/ {_display_(Seq[Any](format.raw/*2.22*/("""
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*3.50*/routes/*3.56*/.Assets.at("stylesheets/customer.css"))),format.raw/*3.94*/("""">
	"""),_display_(Seq[Any](/*4.3*/partial/*4.10*/.flash_alert())),format.raw/*4.24*/("""
    <div class="span8 offset2">
		"""),_display_(Seq[Any](/*6.4*/helper/*6.10*/.form(routes.Customer.save, 'autoComplete -> "off", 'class -> "row form form-horizontal")/*6.99*/ {_display_(Seq[Any](format.raw/*6.101*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*8.22*/doku/*8.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG524","Add New Customer"))),format.raw/*8.97*/("""</h3>
            </div>

            """),_display_(Seq[Any](/*11.14*/customer/*11.22*/.formBody(myForm))),format.raw/*11.39*/("""

            <div class="form-actions">
                <a href=""""),_display_(Seq[Any](/*14.27*/routes/*14.33*/.Customer.list())),format.raw/*14.49*/("""" class="btn btn-inverse">"""),_display_(Seq[Any](/*14.76*/doku/*14.80*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*14.141*/("""</a>
                <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*15.64*/doku/*15.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*15.127*/("""</button>
            </div>
        """)))})),format.raw/*17.10*/("""
    </div>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*19.39*/routes/*19.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*19.95*/("""'></script>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*20.39*/routes/*20.45*/.Assets.at("javascripts/customer.js"))),format.raw/*20.82*/("""'></script>
	
	<script>
		$( function() """),format.raw/*23.17*/("""{"""),format.raw/*23.18*/("""
			$( "#personalIdExpireDate" ).datepicker();
			"""),format.raw/*25.4*/("""}"""),format.raw/*25.5*/("""
		);
		$( function() """),format.raw/*27.17*/("""{"""),format.raw/*27.18*/("""
			$( "#personalIdIssueDate" ).datepicker();
			"""),format.raw/*29.4*/("""}"""),format.raw/*29.5*/("""
		);
	</script>
""")))})),format.raw/*32.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.Customer]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[models.Customer]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/create.scala.html
                    HASH: 83a826aa4e1c90d5a28fa6a7eebf81701607ee9e
                    MATRIX: 748->1|856->32|892->34|918->52|957->54|1042->104|1056->110|1115->148|1154->153|1169->160|1204->174|1274->210|1288->216|1385->305|1425->307|1523->370|1535->374|1627->445|1702->484|1719->492|1758->509|1861->576|1876->582|1914->598|1977->625|1990->629|2074->690|2178->758|2191->762|2273->821|2343->859|2429->909|2444->915|2516->965|2602->1015|2617->1021|2676->1058|2744->1098|2773->1099|2850->1149|2878->1150|2928->1172|2957->1173|3033->1222|3061->1223|3110->1241
                    LINES: 26->1|29->1|30->2|30->2|30->2|31->3|31->3|31->3|32->4|32->4|32->4|34->6|34->6|34->6|34->6|36->8|36->8|36->8|39->11|39->11|39->11|42->14|42->14|42->14|42->14|42->14|42->14|43->15|43->15|43->15|45->17|47->19|47->19|47->19|48->20|48->20|48->20|51->23|51->23|53->25|53->25|55->27|55->27|57->29|57->29|60->32
                    -- GENERATED --
                */
            