
package views.html.tools

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
object setupcutofftime extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(cutOffTime: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import java.util.Calendar

import java.text.SimpleDateFormat

def /*11.2*/dateFormat/*11.12*/(d:Date) = {{
    new SimpleDateFormat("yyyy-MM-dd") format d
}};
Seq[Any](format.raw/*1.22*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*7.1*/("""
"""),format.raw/*10.1*/("""
"""),format.raw/*13.2*/("""

"""),_display_(Seq[Any](/*15.2*/layout("Setup Cut-Off Time", Seq(
routes.SetupCutOffTime.view().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG442","Setup Cut-Off Time")
))/*17.3*/{_display_(Seq[Any](format.raw/*17.4*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*20.10*/doku/*20.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG442","Setup Cut-Off Time"))),format.raw/*20.87*/("""</h1>
</div>

<div>
	"""),_display_(Seq[Any](/*24.3*/partial/*24.10*/.flash_alert())),format.raw/*24.24*/("""
	
	<div class="well">
		<form action=""""),_display_(Seq[Any](/*27.18*/routes/*27.24*/.SetupCutOffTime.save())),format.raw/*27.47*/("""" enctype="application/x-www-form-urlencoded" method="post">
		<table cellpadding="3" cellspacing="2">
			<tr>
				<td colspan="2"><b>"""),_display_(Seq[Any](/*30.25*/doku/*30.29*/.kirimdoku.util.MultiLanguage.getLanguage("LANG442","Setup Cut-Off Time"))),format.raw/*30.102*/("""</b></td>
			</tr>
			<tr>
				<td>
					"""),_display_(Seq[Any](/*34.7*/doku/*34.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG443","Cut Off Time"))),format.raw/*34.78*/("""
				</td>
				<td>
					<input type="text" name="cutOffTime" value=""""),_display_(Seq[Any](/*37.51*/cutOffTime)),format.raw/*37.61*/("""" style="width: 130px;">&nbsp;GMT + 7.00  <font color="#0000ff" style="font-size: 9pt;">( <b>ex.</b> 16:25:00 )</font>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<button class="btn btn-primary" type="submit"><i class="icon-ok icon-white"></i> """),_display_(Seq[Any](/*45.88*/doku/*45.92*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*45.151*/("""</button>
					<a class="btn" href=""""),_display_(Seq[Any](/*46.28*/routes/*46.34*/.SetupCutOffTime.view())),format.raw/*46.57*/(""""><i class="icon-repeat"></i> """),_display_(Seq[Any](/*46.88*/doku/*46.92*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*46.153*/("""</a>
				</td>
			</tr>
		</table>
		</form>
		<br>&nbsp;
	</div>
	<br>&nbsp;<br>&nbsp;
</div>
<div class="span5" id="viewContainer">
</div>

""")))})),format.raw/*58.2*/("""
"""))}
    }
    
    def render(cutOffTime:String): play.api.templates.Html = apply(cutOffTime)
    
    def f:((String) => play.api.templates.Html) = (cutOffTime) => apply(cutOffTime)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/setupcutofftime.scala.html
                    HASH: 480c347f0d870471903d37634119273c0167560b
                    MATRIX: 739->1|1037->244|1056->254|1149->21|1176->73|1203->179|1231->242|1259->317|1297->320|1461->476|1499->477|1572->514|1585->518|1680->591|1737->613|1753->620|1789->634|1865->674|1880->680|1925->703|2096->838|2109->842|2205->915|2282->957|2295->961|2384->1028|2490->1098|2522->1108|2831->1381|2844->1385|2926->1444|2999->1481|3014->1487|3059->1510|3126->1541|3139->1545|3223->1606|3397->1749
                    LINES: 26->1|39->11|39->11|42->1|43->4|44->7|45->10|46->13|48->15|50->17|50->17|53->20|53->20|53->20|57->24|57->24|57->24|60->27|60->27|60->27|63->30|63->30|63->30|67->34|67->34|67->34|70->37|70->37|78->45|78->45|78->45|79->46|79->46|79->46|79->46|79->46|79->46|91->58
                    -- GENERATED --
                */
            