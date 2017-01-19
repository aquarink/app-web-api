
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
object layoutUpload extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[controllers.helpers.BatchUploadHelper],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(uploadForm: Form[controllers.helpers.BatchUploadHelper]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.59*/("""
"""),format.raw/*4.1*/("""

"""),_display_(Seq[Any](/*6.2*/layout("Batch Upload")/*6.24*/{_display_(Seq[Any](format.raw/*6.25*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*9.10*/doku/*9.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG497","Batch Upload"))),format.raw/*9.81*/("""</h1>
</div>

"""),_display_(Seq[Any](/*12.2*/partial/*12.9*/.flash_alert())),format.raw/*12.23*/("""

<div class="row">
	<div class="well span12">
		"""),_display_(Seq[Any](/*16.4*/if(uploadForm.get().getErrorDescription() != null && !uploadForm.get().getErrorDescription().equals(""))/*16.108*/{_display_(Seq[Any](format.raw/*16.109*/("""
			<div class="row-fluid">
				<div class="span12 alert alert-error">
					<img src=""""),_display_(Seq[Any](/*19.17*/routes/*19.23*/.Assets.at("images/alert-error.png"))),format.raw/*19.59*/(""""/>
					"""),_display_(Seq[Any](/*20.7*/uploadForm/*20.17*/.get().getErrorDescription())),format.raw/*20.45*/("""
				</div>
			</div>
		""")))})),format.raw/*23.4*/("""
		"""),_display_(Seq[Any](/*24.4*/helper/*24.10*/.form(action = routes.BatchUpload.actionUpload, 'method -> "POST", 'enctype -> "multipart/form-data", 'class -> "form-horizontal form-transaction-filter")/*24.164*/ {_display_(Seq[Any](format.raw/*24.166*/("""
            <input type="hidden" id="token" name="token" value=""""),_display_(Seq[Any](/*25.66*/uploadForm/*25.76*/.get().getToken())),format.raw/*25.93*/("""">
            <div class="input-append">
				<input type="file" name="fileUpload" value="" id="fileUpload"><a class="btn" href=""""),_display_(Seq[Any](/*27.89*/routes/*27.95*/.Transaction.listBatchInquiry())),format.raw/*27.126*/("""">"""),_display_(Seq[Any](/*27.129*/doku/*27.133*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*27.192*/("""</a>
				<button id="uploadBtn" type="submit" class="btn" style="display:none;"><i class="icon-search"></i> """),_display_(Seq[Any](/*28.105*/doku/*28.109*/.kirimdoku.util.MultiLanguage.getLanguage("LANG508","Upload"))),format.raw/*28.170*/("""</button>
			</div>
		""")))})),format.raw/*30.4*/("""

    </div>
</div>

<div class="row">
	<div class="span12">
		<div id="transaction-detail-container">
		</div>
    </div>
</div>
<script type="text/javascript">
<!--
	$("input:file").change(function ()"""),format.raw/*43.36*/("""{"""),format.raw/*43.37*/("""
	    var fileName = $(this).val();
	    if (fileName != "")"""),format.raw/*45.25*/("""{"""),format.raw/*45.26*/("""
	    	$("#uploadBtn").show();
	    """),format.raw/*47.6*/("""}"""),format.raw/*47.7*/(""" else """),format.raw/*47.13*/("""{"""),format.raw/*47.14*/("""
	    	$("#uploadBtn").hide();
	    """),format.raw/*49.6*/("""}"""),format.raw/*49.7*/("""
	"""),format.raw/*50.2*/("""}"""),format.raw/*50.3*/(""");
//-->
</script>
""")))})),format.raw/*53.2*/("""
"""))}
    }
    
    def render(uploadForm:Form[controllers.helpers.BatchUploadHelper]): play.api.templates.Html = apply(uploadForm)
    
    def f:((Form[controllers.helpers.BatchUploadHelper]) => play.api.templates.Html) = (uploadForm) => apply(uploadForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/batchupload/layoutUpload.scala.html
                    HASH: cc4a991a862ac524a7a7feed7e7846318d1e10ea
                    MATRIX: 779->1|963->58|990->110|1027->113|1057->135|1095->136|1167->173|1179->177|1267->244|1317->259|1332->266|1368->280|1453->330|1567->434|1607->435|1730->522|1745->528|1803->564|1848->574|1867->584|1917->612|1973->637|2012->641|2027->647|2191->801|2232->803|2334->869|2353->879|2392->896|2558->1026|2573->1032|2627->1063|2667->1066|2681->1070|2763->1129|2909->1238|2923->1242|3007->1303|3061->1326|3291->1528|3320->1529|3408->1589|3437->1590|3500->1626|3528->1627|3562->1633|3591->1634|3654->1670|3682->1671|3711->1673|3739->1674|3790->1694
                    LINES: 26->1|32->1|33->4|35->6|35->6|35->6|38->9|38->9|38->9|41->12|41->12|41->12|45->16|45->16|45->16|48->19|48->19|48->19|49->20|49->20|49->20|52->23|53->24|53->24|53->24|53->24|54->25|54->25|54->25|56->27|56->27|56->27|56->27|56->27|56->27|57->28|57->28|57->28|59->30|72->43|72->43|74->45|74->45|76->47|76->47|76->47|76->47|78->49|78->49|79->50|79->50|82->53
                    -- GENERATED --
                */
            