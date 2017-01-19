
package views.html.batchupload

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
object resultUpload extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[controllers.helpers.BatchUploadHelper,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(helper: controllers.helpers.BatchUploadHelper):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.49*/("""


"""),_display_(Seq[Any](/*4.2*/layout("Batch Upload")/*4.24*/{_display_(Seq[Any](format.raw/*4.25*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*7.10*/doku/*7.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG497","Batch Upload"))),format.raw/*7.81*/("""</h1>
</div>

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""
<div class="row">
	<div class="span12">
		<div id="transaction-detail-container">
			<table>
				<tbody>
					<tr>
						<th style="text-align: left;" colspan="2">Result Summary Batch Upload<br><hr></th>
					</tr>
					<tr>
						<th style="text-align: left; width: 70px;">Filename :</th>
						<td>"""),_display_(Seq[Any](/*21.12*/helper/*21.18*/.getFileUploadFileName())),format.raw/*21.42*/("""</td>
					</tr>
					<tr>
						<th style="text-align: left;">Total row :</th>
						<td>
						"""),_display_(Seq[Any](/*26.8*/if(helper.getTotalRow() > 0)/*26.36*/{_display_(Seq[Any](format.raw/*26.37*/("""
							"""),_display_(Seq[Any](/*27.9*/helper/*27.15*/.getTotalRow())),format.raw/*27.29*/("""						
						""")))})),format.raw/*28.8*/("""</td>
					</tr>
					<tr>
						<th style="text-align: left; vertical-align: top;">List error :</th>
						<td>"""),_display_(Seq[Any](/*32.12*/helper/*32.18*/.getErrorDescription())),format.raw/*32.40*/("""</td>
					</tr>
				</tbody>
			</table>
		</div>
    </div>
	<div class="span12">
		<div class="form-actions invisible-print noprint">
			<a class="btn" href=""""),_display_(Seq[Any](/*40.26*/routes/*40.32*/.BatchUpload.viewUpload())),format.raw/*40.57*/(""""><i class="icon-arrow-left"></i> """),_display_(Seq[Any](/*40.92*/doku/*40.96*/.kirimdoku.util.MultiLanguage.getLanguage("LANG511","Upload another csv"))),format.raw/*40.169*/("""</a>
			"""),_display_(Seq[Any](/*41.5*/if(helper.getBatch() != null)/*41.34*/{_display_(Seq[Any](format.raw/*41.35*/("""
				<a class="btn" href='"""),_display_(Seq[Any](/*42.27*/routes/*42.33*/.Transaction.showBatchDetail(helper.getBatch().id, 0, "", "i"))),format.raw/*42.95*/("""'>"""),_display_(Seq[Any](/*42.98*/doku/*42.102*/.kirimdoku.util.MultiLanguage.getLanguage("LANG512","View Inquiry Data"))),format.raw/*42.174*/(""" <i class="icon-arrow-right"></i></a>
			""")))})),format.raw/*43.5*/("""
		</div>
	</div>
</div>

""")))}/*48.2*/ {_display_(Seq[Any](format.raw/*48.4*/("""
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*49.46*/routes/*49.52*/.Assets.at("stylesheets/transaction.css"))),format.raw/*49.93*/("""">
<script type="text/javascript" src='"""),_display_(Seq[Any](/*50.38*/routes/*50.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*50.84*/("""'></script>
<script type="text/javascript" src='"""),_display_(Seq[Any](/*51.38*/routes/*51.44*/.Assets.at("javascripts/send.js"))),format.raw/*51.77*/("""'></script>
""")))})),format.raw/*52.2*/("""
"""))}
    }
    
    def render(helper:controllers.helpers.BatchUploadHelper): play.api.templates.Html = apply(helper)
    
    def f:((controllers.helpers.BatchUploadHelper) => play.api.templates.Html) = (helper) => apply(helper)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/batchupload/resultUpload.scala.html
                    HASH: f8a4ad2127303697647176ce60380e8ff12f1b7c
                    MATRIX: 773->1|897->48|935->52|965->74|1003->75|1075->112|1087->116|1175->183|1225->198|1240->205|1276->219|1614->521|1629->527|1675->551|1808->649|1845->677|1884->678|1928->687|1943->693|1979->707|2024->721|2173->834|2188->840|2232->862|2430->1024|2445->1030|2492->1055|2563->1090|2576->1094|2672->1167|2716->1176|2754->1205|2793->1206|2856->1233|2871->1239|2955->1301|2994->1304|3008->1308|3103->1380|3176->1422|3221->1449|3260->1451|3342->1497|3357->1503|3420->1544|3496->1584|3511->1590|3573->1630|3658->1679|3673->1685|3728->1718|3772->1731
                    LINES: 26->1|29->1|32->4|32->4|32->4|35->7|35->7|35->7|38->10|38->10|38->10|49->21|49->21|49->21|54->26|54->26|54->26|55->27|55->27|55->27|56->28|60->32|60->32|60->32|68->40|68->40|68->40|68->40|68->40|68->40|69->41|69->41|69->41|70->42|70->42|70->42|70->42|70->42|70->42|71->43|76->48|76->48|77->49|77->49|77->49|78->50|78->50|78->50|79->51|79->51|79->51|80->52
                    -- GENERATED --
                */
            