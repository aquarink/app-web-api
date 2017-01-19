
package views.html.send

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
object receipt_bill_payment extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,models.Transaction,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Transaction send receipt")/*5.36*/{_display_(Seq[Any](format.raw/*5.37*/("""

<div id="send-receipt" class="body-print">
<div class="page-header" style="margin: 0px;">
	<!-- Stevano Edited 17 Jun 2015 -->
	
    <h2 class="center"><img src=""""),_display_(Seq[Any](/*11.35*/routes/*11.41*/.Corporate.logo(session.get("corporateCode"), "small"))),format.raw/*11.95*/("""" style="float:left;height:50px;"/>"""),_display_(Seq[Any](/*11.131*/doku/*11.135*/.kirimdoku.util.MultiLanguage.getLanguage("LANG075","Send money receipt"))),format.raw/*11.208*/("""<img src="/assets/images/doku_small.png" style="float:right;height:50px;"/></h2>
    
    <!-- End Edited -->
</div>
"""),_display_(Seq[Any](/*15.2*/partial/*15.9*/.flash_alert())),format.raw/*15.23*/("""

<div class="container-send-inner">
"""),_display_(Seq[Any](/*18.2*/if(err)/*18.9*/ {_display_(Seq[Any](format.raw/*18.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*21.36*/err)),format.raw/*21.39*/("""</div>
		</div>
	</div>
""")))}/*24.3*/else/*24.8*/{_display_(Seq[Any](format.raw/*24.9*/("""
	<div>
		<div class="receipt-head">
			<div class="row">
				<div class="span6" style="margin-bottom:-15px;">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*30.12*/doku/*30.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Corporate name"))),format.raw/*30.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*31.12*/transaction/*31.23*/.agent.corporate.name)),format.raw/*31.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*32.12*/doku/*32.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG226","Operator ID"))),format.raw/*32.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*33.12*/transaction/*33.23*/.agent.idToken)),format.raw/*33.37*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*34.12*/doku/*34.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG227","Operator name"))),format.raw/*34.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*35.12*/transaction/*35.23*/.agent.fullName)),format.raw/*35.38*/("""</dd>
					</dl>
				</div>
				<div class="span6" style="margin-bottom:-15px;">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*40.12*/doku/*40.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"))),format.raw/*40.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*41.12*/transaction/*41.23*/.corporate.address)),format.raw/*41.41*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*42.12*/doku/*42.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone Number"))),format.raw/*42.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*43.12*/transaction/*43.23*/.corporate.phoneNumber)),format.raw/*43.45*/("""</dd>
					</dl>
				</div>
			</div>
		</div>
		
		<div class="receipt-body">
			<div class="row">
				<div class="span6" style="margin-bottom:-15px;">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*53.12*/doku/*53.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG228","Transaction type"))),format.raw/*53.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*54.12*/doku/*54.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG229","Send transaction"))),format.raw/*54.87*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*55.12*/doku/*55.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"))),format.raw/*55.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*56.12*/transaction/*56.23*/.senderCountry.name)),format.raw/*56.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*57.12*/doku/*57.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG041","Receive country"))),format.raw/*57.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*58.12*/transaction/*58.23*/.beneficiaryCountry.name)),format.raw/*58.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*59.12*/doku/*59.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city"))),format.raw/*59.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*60.12*/transaction/*60.23*/.beneficiaryCity)),format.raw/*60.39*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*61.12*/doku/*61.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*61.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*62.12*/transaction/*62.23*/.channel.name)),format.raw/*62.36*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*63.12*/doku/*63.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG230","Date / Time"))),format.raw/*63.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*64.12*/transaction/*64.23*/.createdFormat)),format.raw/*64.37*/("""</dd>
					</dl>
					
				</div>
				<div class="span6" style="margin-bottom:-15px;">
					<div class="well well-receipt well-print">
						<div class="well-inner">
							<label>"""),_display_(Seq[Any](/*71.16*/doku/*71.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*71.89*/(""":</label>
							<h1>"""),_display_(Seq[Any](/*72.13*/transaction/*72.24*/.idToken)),format.raw/*72.32*/("""</h1>
							<span class="label label-transparent-important">*"""),_display_(Seq[Any](/*73.58*/doku/*73.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG231","Please give this transaction ID to your Receiver only"))),format.raw/*73.170*/("""</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				"""),_display_(Seq[Any](/*79.6*/if(transaction.paymentData != null)/*79.41*/ {_display_(Seq[Any](format.raw/*79.43*/("""
				<div class="span6" style="margin-bottom:-15px;">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*82.12*/doku/*82.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG082","Bill Payment Service"))),format.raw/*82.91*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*83.12*/transaction/*83.23*/.billPayment().billPaymentService)),format.raw/*83.56*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*84.12*/doku/*84.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG083","Selected Bill"))),format.raw/*84.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*85.12*/transaction/*85.23*/.billPayment().selectBill)),format.raw/*85.48*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*86.12*/doku/*86.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG085","Selected Operator"))),format.raw/*86.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*87.12*/transaction/*87.23*/.billPayment().selectOperatorName)),format.raw/*87.56*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*88.12*/doku/*88.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG086","Selected Denom"))),format.raw/*88.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*89.12*/transaction/*89.23*/.billPayment().selectDenomName)),format.raw/*89.53*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*90.12*/doku/*90.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG084","Phone Number/Subscriber ID"))),format.raw/*90.97*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*91.12*/transaction/*91.23*/.billPayment().accountNumber)),format.raw/*91.51*/("""</dd>
						"""),_display_(Seq[Any](/*92.8*/if(transaction.billPayment().selectOperator.equals("9950102"))/*92.70*/ {_display_(Seq[Any](format.raw/*92.72*/("""
							<dt>"""),_display_(Seq[Any](/*93.13*/doku/*93.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG224","Subscriber Name"))),format.raw/*93.87*/("""</dt>
							<dd>"""),_display_(Seq[Any](/*94.13*/transaction/*94.24*/.billPayment().accountName)),format.raw/*94.50*/("""</dd>
							<dt>"""),_display_(Seq[Any](/*95.13*/doku/*95.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG232","Token"))),format.raw/*95.77*/("""</dt>
							<dd>"""),_display_(Seq[Any](/*96.13*/transaction/*96.24*/.billPayment().token)),format.raw/*96.44*/("""</dd>
						""")))})),format.raw/*97.8*/("""
					</dl>
				</div>
				""")))})),format.raw/*100.6*/("""
			</div>
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*105.12*/doku/*105.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*105.77*/("""</dt>
						<dd><strong>
						"""),_display_(Seq[Any](/*107.8*/if(transaction.statusText == "PAID")/*107.44*/{_display_(Seq[Any](format.raw/*107.45*/(""" 
							SUCCESS 
						""")))}/*109.9*/else/*109.14*/{_display_(Seq[Any](format.raw/*109.15*/(""" 
							"""),_display_(Seq[Any](/*110.9*/transaction/*110.20*/.statusText)),format.raw/*110.31*/(""" 
						""")))})),format.raw/*111.8*/("""
						</strong></dd>
						<dt>"""),_display_(Seq[Any](/*113.12*/doku/*113.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"))),format.raw/*113.88*/("""</dt>
						<dd><strong>"""),_display_(Seq[Any](/*114.20*/transaction/*114.31*/.beneficiaryAmountFormat)),format.raw/*114.55*/("""</strong></dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="span6" style="">
					<div class="span2 well" style="width: 70%;" >
						<div style="height:30px"><br>"""),_display_(Seq[Any](/*121.37*/doku/*121.41*/.kirimdoku.util.MultiLanguage.getLanguage("LANG233","Operator's Signature"))),format.raw/*121.116*/("""</div>
					</div>
				</div>
				<div class="span6" style="">
					<div class="span2 well" style="width: 70%;" >
						<div style="height:30px"><br>"""),_display_(Seq[Any](/*126.37*/doku/*126.41*/.kirimdoku.util.MultiLanguage.getLanguage("LANG234","Customer's Signature"))),format.raw/*126.116*/("""</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned" style="font-size: 9pt;">
						<dt style="overflow: visible;">"""),_display_(Seq[Any](/*133.39*/doku/*133.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG235","1. Please check all transaction information before leaving our branch."))),format.raw/*133.168*/("""</dt>
						<dd></dd>
						<dt style="overflow: visible;">"""),_display_(Seq[Any](/*135.39*/doku/*135.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG236","2. This is valid transaction receipt, please keep this carefully as your reference."))),format.raw/*135.181*/("""</dt>
						<dd></dd>
					</dl>
				</div>
			</div>
			
			

		</div>
	</div>
""")))})),format.raw/*145.2*/("""
</div>
</div>
<div class="span12">
	<div class="form-actions invisible-print noprint">
		<button class="btn btn-print" type="print" data-target="#send-receipt"><i class="icon-print"></i> """),_display_(Seq[Any](/*150.102*/doku/*150.106*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*150.166*/("""</button>
		<a class="btn" href=""""),_display_(Seq[Any](/*151.25*/routes/*151.31*/.Send.createBillPayment())),format.raw/*151.56*/("""">"""),_display_(Seq[Any](/*151.59*/doku/*151.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG237","Send another bill payment"))),format.raw/*151.143*/(""" <i class="icon-arrow-right"></i></a>
	</div>
</div>
""")))}/*154.2*/ {_display_(Seq[Any](format.raw/*154.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*155.38*/routes/*155.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*155.84*/("""'></script>
""")))})),format.raw/*156.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction): play.api.templates.Html = apply(err,transaction)
    
    def f:((String,models.Transaction) => play.api.templates.Html) = (err,transaction) => apply(err,transaction)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/receipt_bill_payment.scala.html
                    HASH: b30b17b730bf40fee9e7fce8b626f83988ee5560
                    MATRIX: 762->1|935->47|962->99|998->101|1040->135|1078->136|1279->301|1294->307|1370->361|1443->397|1457->401|1553->474|1706->592|1721->599|1757->613|1830->651|1845->658|1885->660|1999->738|2024->741|2067->767|2079->772|2117->773|2318->938|2331->942|2422->1011|2475->1028|2495->1039|2538->1060|2591->1077|2604->1081|2692->1147|2745->1164|2765->1175|2801->1189|2854->1206|2867->1210|2957->1278|3010->1295|3030->1306|3067->1321|3238->1456|3251->1460|3335->1522|3388->1539|3408->1550|3448->1568|3501->1585|3514->1589|3603->1656|3656->1673|3676->1684|3720->1706|3963->1913|3976->1917|4069->1988|4122->2005|4135->2009|4228->2080|4281->2097|4294->2101|4386->2171|4439->2188|4459->2199|4500->2218|4553->2235|4566->2239|4658->2309|4711->2326|4731->2337|4777->2361|4830->2378|4843->2382|4932->2449|4985->2466|5005->2477|5043->2493|5096->2510|5109->2514|5193->2576|5246->2593|5266->2604|5301->2617|5354->2634|5367->2638|5455->2704|5508->2721|5528->2732|5564->2746|5781->2927|5794->2931|5885->3000|5943->3022|5963->3033|5993->3041|6092->3104|6105->3108|6236->3216|6351->3296|6395->3331|6435->3333|6579->3441|6592->3445|6689->3520|6742->3537|6762->3548|6817->3581|6870->3598|6883->3602|6973->3670|7026->3687|7046->3698|7093->3723|7146->3740|7159->3744|7253->3816|7306->3833|7326->3844|7381->3877|7434->3894|7447->3898|7538->3967|7591->3984|7611->3995|7663->4025|7716->4042|7729->4046|7832->4127|7885->4144|7905->4155|7955->4183|8003->4196|8074->4258|8114->4260|8163->4273|8176->4277|8268->4347|8322->4365|8342->4376|8390->4402|8444->4420|8457->4424|8539->4484|8593->4502|8613->4513|8655->4533|8699->4546|8759->4574|8906->4684|8920->4688|9004->4749|9072->4781|9118->4817|9158->4818|9202->4844|9216->4849|9256->4850|9302->4860|9323->4871|9357->4882|9398->4891|9468->4924|9482->4928|9577->5000|9639->5025|9660->5036|9707->5060|9932->5248|9946->5252|10045->5327|10232->5477|10246->5481|10345->5556|10572->5746|10586->5750|10735->5875|10832->5935|10846->5939|11008->6077|11121->6158|11348->6347|11363->6351|11447->6411|11518->6445|11534->6451|11582->6476|11622->6479|11636->6483|11740->6563|11813->6617|11853->6619|11928->6657|11944->6663|12007->6703|12052->6716
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|40->11|40->11|40->11|40->11|40->11|40->11|44->15|44->15|44->15|47->18|47->18|47->18|50->21|50->21|53->24|53->24|53->24|59->30|59->30|59->30|60->31|60->31|60->31|61->32|61->32|61->32|62->33|62->33|62->33|63->34|63->34|63->34|64->35|64->35|64->35|69->40|69->40|69->40|70->41|70->41|70->41|71->42|71->42|71->42|72->43|72->43|72->43|82->53|82->53|82->53|83->54|83->54|83->54|84->55|84->55|84->55|85->56|85->56|85->56|86->57|86->57|86->57|87->58|87->58|87->58|88->59|88->59|88->59|89->60|89->60|89->60|90->61|90->61|90->61|91->62|91->62|91->62|92->63|92->63|92->63|93->64|93->64|93->64|100->71|100->71|100->71|101->72|101->72|101->72|102->73|102->73|102->73|108->79|108->79|108->79|111->82|111->82|111->82|112->83|112->83|112->83|113->84|113->84|113->84|114->85|114->85|114->85|115->86|115->86|115->86|116->87|116->87|116->87|117->88|117->88|117->88|118->89|118->89|118->89|119->90|119->90|119->90|120->91|120->91|120->91|121->92|121->92|121->92|122->93|122->93|122->93|123->94|123->94|123->94|124->95|124->95|124->95|125->96|125->96|125->96|126->97|129->100|134->105|134->105|134->105|136->107|136->107|136->107|138->109|138->109|138->109|139->110|139->110|139->110|140->111|142->113|142->113|142->113|143->114|143->114|143->114|150->121|150->121|150->121|155->126|155->126|155->126|162->133|162->133|162->133|164->135|164->135|164->135|174->145|179->150|179->150|179->150|180->151|180->151|180->151|180->151|180->151|180->151|183->154|183->154|184->155|184->155|184->155|185->156
                    -- GENERATED --
                */
            