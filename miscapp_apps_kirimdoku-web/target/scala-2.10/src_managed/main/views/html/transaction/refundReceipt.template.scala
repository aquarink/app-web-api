
package views.html.transaction

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
object refundReceipt extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,models.Transaction,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Transaction refund receipt")/*5.38*/{_display_(Seq[Any](format.raw/*5.39*/("""

<div id="refund-receipt">
<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*9.10*/doku/*9.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG256","Refund receipt"))),format.raw/*9.83*/("""</h1>
</div>

"""),_display_(Seq[Any](/*12.2*/partial/*12.9*/.flash_alert())),format.raw/*12.23*/("""

<div class="container-refund-inner">
"""),_display_(Seq[Any](/*15.2*/if(err)/*15.9*/ {_display_(Seq[Any](format.raw/*15.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*18.36*/err)),format.raw/*18.39*/("""</div>
		</div>
	</div>
""")))}/*21.3*/else/*21.8*/{_display_(Seq[Any](format.raw/*21.9*/("""
	<div id="refund-print" class="container-refund-print">
		<div class="receipt-head">
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*27.12*/doku/*27.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Corporate name"))),format.raw/*27.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*28.12*/transaction/*28.23*/.agent.corporate.name)),format.raw/*28.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*29.12*/doku/*29.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG226","Operator ID"))),format.raw/*29.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*30.12*/transaction/*30.23*/.agent.idToken)),format.raw/*30.37*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*31.12*/doku/*31.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG227","Operator name"))),format.raw/*31.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*32.12*/transaction/*32.23*/.agent.fullName)),format.raw/*32.38*/("""</dd>
					</dl>
				</div>
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*37.12*/doku/*37.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"))),format.raw/*37.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*38.12*/transaction/*38.23*/.agent.corporate.address)),format.raw/*38.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*39.12*/doku/*39.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone Number"))),format.raw/*39.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*40.12*/transaction/*40.23*/.agent.corporate.phoneNumber)),format.raw/*40.51*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*41.12*/doku/*41.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG255","Fax"))),format.raw/*41.74*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*42.12*/transaction/*42.23*/.agent.corporate.faxNumber)),format.raw/*42.49*/("""</dd>
					</dl>
				</div>
			</div>
		</div>
		
		<div class="receipt-body">
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*52.12*/doku/*52.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG228","Transaction type"))),format.raw/*52.87*/("""</dt>
						<dd>Refund transaction</dd>
						<dt>"""),_display_(Seq[Any](/*54.12*/doku/*54.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"))),format.raw/*54.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*55.12*/transaction/*55.23*/.sender.country.name)),format.raw/*55.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*56.12*/doku/*56.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"))),format.raw/*56.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*57.12*/transaction/*57.23*/.beneficiary.country.name)),format.raw/*57.48*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*58.12*/doku/*58.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*58.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*59.12*/transaction/*59.23*/.channel.name)),format.raw/*59.36*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*60.12*/doku/*60.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG230","Date / Time"))),format.raw/*60.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*61.12*/transaction/*61.23*/.createdFormat)),format.raw/*61.37*/("""</dd>
					</dl>
					
				</div>
				<div class="span6">
					<div class="well well-receipt well-print">
						<div class="well-inner">
							<label>"""),_display_(Seq[Any](/*68.16*/doku/*68.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*68.89*/(""":</label>
							<h1>"""),_display_(Seq[Any](/*69.13*/transaction/*69.24*/.idToken)),format.raw/*69.32*/("""</h1>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*78.12*/doku/*78.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG072","Customer ID of sender"))),format.raw/*78.92*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*79.12*/transaction/*79.23*/.sender.idToken)),format.raw/*79.38*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*80.12*/doku/*80.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*80.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*81.12*/transaction/*81.23*/.sender.fullName)),format.raw/*81.39*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*82.12*/doku/*82.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*82.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*83.12*/transaction/*83.23*/.sender.phoneNumber)),format.raw/*83.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*84.12*/doku/*84.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*84.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*85.12*/transaction/*85.23*/.senderAmountFormat)),format.raw/*85.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*86.12*/doku/*86.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG254","Fee amount"))),format.raw/*86.81*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*87.12*/models/*87.18*/.Currency.format(transaction.feesTotal, transaction.senderCurrency.code))),format.raw/*87.90*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*88.12*/doku/*88.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG253","Collected amount"))),format.raw/*88.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*89.12*/transaction/*89.23*/.collectAmountFormat)),format.raw/*89.43*/("""</dd>
					</dl>
				</div>
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*94.12*/doku/*94.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG073","Customer ID of receiver"))),format.raw/*94.94*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*95.12*/transaction/*95.23*/.beneficiary.idToken)),format.raw/*95.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*96.12*/doku/*96.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*96.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*97.12*/transaction/*97.23*/.beneficiary.fullName)),format.raw/*97.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*98.12*/doku/*98.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*98.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*99.12*/transaction/*99.23*/.beneficiary.phoneNumber)),format.raw/*99.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*100.12*/doku/*100.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG252","Receiver address"))),format.raw/*100.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*101.12*/transaction/*101.23*/.beneficiary.address)),format.raw/*101.43*/("""</dd>
					</dl>
				</div>
			</div>
			
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*109.12*/doku/*109.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*109.77*/("""</dt>
						<dd><strong>"""),_display_(Seq[Any](/*110.20*/transaction/*110.31*/.statusText)),format.raw/*110.42*/("""</strong></dd>
						<dt>"""),_display_(Seq[Any](/*111.12*/doku/*111.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG251","Amount to refund"))),format.raw/*111.87*/("""</dt>
						<dd><strong>
						"""),_display_(Seq[Any](/*113.8*/if(transaction.status == models.Transaction.STATUS_FULLREFUNDED)/*113.72*/ {_display_(Seq[Any](format.raw/*113.74*/("""
						"""),_display_(Seq[Any](/*114.8*/models/*114.14*/.Currency.format(transaction.senderAmount.add(transaction.feesTotal), transaction.senderCurrency.code))),format.raw/*114.116*/("""
						""")))}/*115.9*/else/*115.14*/{_display_(Seq[Any](format.raw/*115.15*/("""
						"""),_display_(Seq[Any](/*116.8*/transaction/*116.19*/.senderAmountFormat)),format.raw/*116.38*/("""
						""")))})),format.raw/*117.8*/("""
						</strong></dd>
					</dl>
				</div>
				<div class="span6">
				</div>
			</div>
			<div class="row">
				<div class="span12">
					<div class="form-actions invisible-print">
						<button class="btn btn-print" type="print" data-target="#refund-receipt"><i class="icon-print"></i> """),_display_(Seq[Any](/*127.108*/doku/*127.112*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*127.172*/("""</button>
					</div>
				</div>
			</div>
		</div>
	</div>
""")))})),format.raw/*133.2*/("""
</div>
</div>
""")))}/*136.2*/ {_display_(Seq[Any](format.raw/*136.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*137.38*/routes/*137.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*137.84*/("""'></script>
""")))})),format.raw/*138.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction): play.api.templates.Html = apply(err,transaction)
    
    def f:((String,models.Transaction) => play.api.templates.Html) = (err,transaction) => apply(err,transaction)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:33 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/refundReceipt.scala.html
                    HASH: f18657c1a337d2e4d656d538c2b3cc7ae1a16c64
                    MATRIX: 762->1|935->47|962->99|998->101|1042->137|1080->138|1178->201|1190->205|1280->274|1330->289|1345->296|1381->310|1456->350|1471->357|1511->359|1625->437|1650->440|1693->466|1705->471|1743->472|1964->657|1977->661|2068->730|2121->747|2141->758|2184->779|2237->796|2250->800|2338->866|2391->883|2411->894|2447->908|2500->925|2513->929|2603->997|2656->1014|2676->1025|2713->1040|2855->1146|2868->1150|2952->1212|3005->1229|3025->1240|3071->1264|3124->1281|3137->1285|3226->1352|3279->1369|3299->1380|3349->1408|3402->1425|3415->1429|3495->1487|3548->1504|3568->1515|3616->1541|3830->1719|3843->1723|3936->1794|4023->1845|4036->1849|4128->1919|4181->1936|4201->1947|4243->1967|4296->1984|4309->1988|4401->2058|4454->2075|4474->2086|4521->2111|4574->2128|4587->2132|4671->2194|4724->2211|4744->2222|4779->2235|4832->2252|4845->2256|4933->2322|4986->2339|5006->2350|5042->2364|5230->2516|5243->2520|5334->2589|5392->2611|5412->2622|5442->2630|5633->2785|5646->2789|5744->2865|5797->2882|5817->2893|5854->2908|5907->2925|5920->2929|6008->2995|6061->3012|6081->3023|6119->3039|6172->3056|6185->3060|6274->3127|6327->3144|6347->3155|6388->3174|6441->3191|6454->3195|6545->3264|6598->3281|6618->3292|6659->3311|6712->3328|6725->3332|6812->3397|6865->3414|6880->3420|6974->3492|7027->3509|7040->3513|7133->3584|7186->3601|7206->3612|7248->3632|7390->3738|7403->3742|7503->3820|7556->3837|7576->3848|7618->3868|7671->3885|7684->3889|7774->3957|7827->3974|7847->3985|7890->4006|7943->4023|7956->4027|8045->4094|8098->4111|8118->4122|8164->4146|8218->4163|8232->4167|8326->4238|8380->4255|8401->4266|8444->4286|8622->4427|8636->4431|8720->4492|8782->4517|8803->4528|8837->4539|8900->4565|8914->4569|9008->4640|9076->4672|9150->4736|9191->4738|9235->4746|9251->4752|9377->4854|9404->4863|9418->4868|9458->4869|9502->4877|9523->4888|9565->4907|9605->4915|9933->5205|9948->5209|10032->5269|10125->5330|10160->5346|10200->5348|10275->5386|10291->5392|10354->5432|10399->5445
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|38->9|38->9|38->9|41->12|41->12|41->12|44->15|44->15|44->15|47->18|47->18|50->21|50->21|50->21|56->27|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30|59->30|59->30|60->31|60->31|60->31|61->32|61->32|61->32|66->37|66->37|66->37|67->38|67->38|67->38|68->39|68->39|68->39|69->40|69->40|69->40|70->41|70->41|70->41|71->42|71->42|71->42|81->52|81->52|81->52|83->54|83->54|83->54|84->55|84->55|84->55|85->56|85->56|85->56|86->57|86->57|86->57|87->58|87->58|87->58|88->59|88->59|88->59|89->60|89->60|89->60|90->61|90->61|90->61|97->68|97->68|97->68|98->69|98->69|98->69|107->78|107->78|107->78|108->79|108->79|108->79|109->80|109->80|109->80|110->81|110->81|110->81|111->82|111->82|111->82|112->83|112->83|112->83|113->84|113->84|113->84|114->85|114->85|114->85|115->86|115->86|115->86|116->87|116->87|116->87|117->88|117->88|117->88|118->89|118->89|118->89|123->94|123->94|123->94|124->95|124->95|124->95|125->96|125->96|125->96|126->97|126->97|126->97|127->98|127->98|127->98|128->99|128->99|128->99|129->100|129->100|129->100|130->101|130->101|130->101|138->109|138->109|138->109|139->110|139->110|139->110|140->111|140->111|140->111|142->113|142->113|142->113|143->114|143->114|143->114|144->115|144->115|144->115|145->116|145->116|145->116|146->117|156->127|156->127|156->127|162->133|165->136|165->136|166->137|166->137|166->137|167->138
                    -- GENERATED --
                */
            