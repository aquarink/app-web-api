
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
object changeReceipt extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,models.Transaction,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Transaction refund receipt")/*5.38*/{_display_(Seq[Any](format.raw/*5.39*/("""

<div id="change-receipt">
<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*9.10*/doku/*9.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG262","Change transaction receipt"))),format.raw/*9.95*/("""</h1>
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
						<dd>"""),_display_(Seq[Any](/*53.12*/doku/*53.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG013","Change transaction"))),format.raw/*53.89*/("""</dd>
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
							<span class="label label-transparent-important">*"""),_display_(Seq[Any](/*70.58*/doku/*70.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG231","Please give this transaction ID to your receiver only"))),format.raw/*70.170*/("""</span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*79.12*/doku/*79.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG072","Customer ID of sender"))),format.raw/*79.92*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*80.12*/transaction/*80.23*/.sender.idToken)),format.raw/*80.38*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*81.12*/doku/*81.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*81.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*82.12*/transaction/*82.23*/.sender.fullName)),format.raw/*82.39*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*83.12*/doku/*83.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*83.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*84.12*/transaction/*84.23*/.sender.phoneNumber)),format.raw/*84.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*85.12*/doku/*85.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amoun"))),format.raw/*85.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*86.12*/transaction/*86.23*/.senderAmountFormat)),format.raw/*86.42*/("""</dd>
					</dl>
				</div>
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*91.12*/doku/*91.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG073","Customer ID of receiver"))),format.raw/*91.94*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*92.12*/transaction/*92.23*/.beneficiary.idToken)),format.raw/*92.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*93.12*/doku/*93.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*93.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*94.12*/transaction/*94.23*/.beneficiary.fullName)),format.raw/*94.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*95.12*/doku/*95.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*95.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*96.12*/transaction/*96.23*/.beneficiary.phoneNumber)),format.raw/*96.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*97.12*/doku/*97.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG252","Receiver address"))),format.raw/*97.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*98.12*/transaction/*98.23*/.beneficiary.address)),format.raw/*98.43*/("""</dd>
					</dl>
				</div>
			</div>
			
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*106.12*/doku/*106.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*106.77*/("""</dt>
						<dd><strong>"""),_display_(Seq[Any](/*107.20*/transaction/*107.31*/.statusText)),format.raw/*107.42*/("""</strong></dd>
						<dt>"""),_display_(Seq[Any](/*108.12*/doku/*108.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG261","Date changed"))),format.raw/*108.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*109.12*/transaction/*109.23*/.modifiedFormat)),format.raw/*109.38*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*110.12*/doku/*110.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"))),format.raw/*110.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*111.12*/transaction/*111.23*/.beneficiaryAmountFormat)),format.raw/*111.47*/("""</dd>
					</dl>
				</div>
				<div class="span6">
				</div>
			</div>
			<div class="row">
				<div class="span12">
					<div class="form-actions invisible-print">
						<button class="btn btn-print" type="print" data-target="#change-receipt"><i class="icon-print"></i> """),_display_(Seq[Any](/*120.108*/doku/*120.112*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*120.172*/("""</button>
					</div>
				</div>
			</div>
		</div>
	</div>
""")))})),format.raw/*126.2*/("""
</div>
</div>
""")))}/*129.2*/ {_display_(Seq[Any](format.raw/*129.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*130.38*/routes/*130.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*130.84*/("""'></script>
""")))})),format.raw/*131.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction): play.api.templates.Html = apply(err,transaction)
    
    def f:((String,models.Transaction) => play.api.templates.Html) = (err,transaction) => apply(err,transaction)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:32 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/changeReceipt.scala.html
                    HASH: ff2396524288120069d0179f82fced4626223b8a
                    MATRIX: 762->1|935->47|962->99|998->101|1042->137|1080->138|1178->201|1190->205|1292->286|1342->301|1357->308|1393->322|1468->362|1483->369|1523->371|1637->449|1662->452|1705->478|1717->483|1755->484|1976->669|1989->673|2080->742|2133->759|2153->770|2196->791|2249->808|2262->812|2350->878|2403->895|2423->906|2459->920|2512->937|2525->941|2615->1009|2668->1026|2688->1037|2725->1052|2867->1158|2880->1162|2964->1224|3017->1241|3037->1252|3083->1276|3136->1293|3149->1297|3238->1364|3291->1381|3311->1392|3361->1420|3414->1437|3427->1441|3507->1499|3560->1516|3580->1527|3628->1553|3842->1731|3855->1735|3948->1806|4001->1823|4014->1827|4109->1900|4162->1917|4175->1921|4267->1991|4320->2008|4340->2019|4382->2039|4435->2056|4448->2060|4540->2130|4593->2147|4613->2158|4660->2183|4713->2200|4726->2204|4810->2266|4863->2283|4883->2294|4918->2307|4971->2324|4984->2328|5072->2394|5125->2411|5145->2422|5181->2436|5369->2588|5382->2592|5473->2661|5531->2683|5551->2694|5581->2702|5680->2765|5693->2769|5824->2877|6017->3034|6030->3038|6128->3114|6181->3131|6201->3142|6238->3157|6291->3174|6304->3178|6392->3244|6445->3261|6465->3272|6503->3288|6556->3305|6569->3309|6658->3376|6711->3393|6731->3404|6772->3423|6825->3440|6838->3444|6928->3512|6981->3529|7001->3540|7042->3559|7184->3665|7197->3669|7297->3747|7350->3764|7370->3775|7412->3795|7465->3812|7478->3816|7568->3884|7621->3901|7641->3912|7684->3933|7737->3950|7750->3954|7839->4021|7892->4038|7912->4049|7958->4073|8011->4090|8024->4094|8117->4165|8170->4182|8190->4193|8232->4213|8410->4354|8424->4358|8508->4419|8570->4444|8591->4455|8625->4466|8688->4492|8702->4496|8792->4563|8846->4580|8867->4591|8905->4606|8959->4623|8973->4627|9068->4699|9122->4716|9143->4727|9190->4751|9502->5025|9517->5029|9601->5089|9694->5150|9729->5166|9769->5168|9844->5206|9860->5212|9923->5252|9968->5265
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|38->9|38->9|38->9|41->12|41->12|41->12|44->15|44->15|44->15|47->18|47->18|50->21|50->21|50->21|56->27|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30|59->30|59->30|60->31|60->31|60->31|61->32|61->32|61->32|66->37|66->37|66->37|67->38|67->38|67->38|68->39|68->39|68->39|69->40|69->40|69->40|70->41|70->41|70->41|71->42|71->42|71->42|81->52|81->52|81->52|82->53|82->53|82->53|83->54|83->54|83->54|84->55|84->55|84->55|85->56|85->56|85->56|86->57|86->57|86->57|87->58|87->58|87->58|88->59|88->59|88->59|89->60|89->60|89->60|90->61|90->61|90->61|97->68|97->68|97->68|98->69|98->69|98->69|99->70|99->70|99->70|108->79|108->79|108->79|109->80|109->80|109->80|110->81|110->81|110->81|111->82|111->82|111->82|112->83|112->83|112->83|113->84|113->84|113->84|114->85|114->85|114->85|115->86|115->86|115->86|120->91|120->91|120->91|121->92|121->92|121->92|122->93|122->93|122->93|123->94|123->94|123->94|124->95|124->95|124->95|125->96|125->96|125->96|126->97|126->97|126->97|127->98|127->98|127->98|135->106|135->106|135->106|136->107|136->107|136->107|137->108|137->108|137->108|138->109|138->109|138->109|139->110|139->110|139->110|140->111|140->111|140->111|149->120|149->120|149->120|155->126|158->129|158->129|159->130|159->130|159->130|160->131
                    -- GENERATED --
                */
            