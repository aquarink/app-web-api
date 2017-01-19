
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
object printTransaction extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,models.Transaction,Int,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction, batchId: Int, tipe:String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.75*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Transaction send receipt")/*5.36*/{_display_(Seq[Any](format.raw/*5.37*/("""

<div id="send-receipt" class="body-print">
<div class="page-header" style="margin: 0px; background: none; border: none; padding: 0px;">
	<!-- Stevano Edited 17 Jun 2015 -->
	
    <h2 class="center" style="margin: 0px;">
    <img src=""""),_display_(Seq[Any](/*12.16*/routes/*12.22*/.Corporate.logo(session.get("corporateCode"), "small"))),format.raw/*12.76*/("""" style="float:left;height:50px; width: auto;"/>
    """),_display_(Seq[Any](/*13.6*/doku/*13.10*/.kirimdoku.util.MultiLanguage.getLanguage("LANG075","SEND MONEY RECEIPT"))),format.raw/*13.83*/("""
    <img src="/assets/images/doku_small.png" style="float:right;height:50px; width: auto;"/>
    </h2>
    
    <!-- End Edited -->
</div>
"""),_display_(Seq[Any](/*19.2*/partial/*19.9*/.flash_alert())),format.raw/*19.23*/("""

<div class="container-send-inner">
"""),_display_(Seq[Any](/*22.2*/if(err)/*22.9*/ {_display_(Seq[Any](format.raw/*22.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*25.36*/err)),format.raw/*25.39*/("""</div>
		</div>
	</div>
""")))}/*28.3*/else/*28.8*/{_display_(Seq[Any](format.raw/*28.9*/("""
	<div>
		<div class="receipt-head">
		
			<table style="width: 100%; font-size: 9pt;" border="0">
				<tr>
					<td colspan="2" style="text-align: center;">
						"""),_display_(Seq[Any](/*35.8*/transaction/*35.19*/.agent.corporate.name)),format.raw/*35.40*/("""
						<hr style="margin: 5px 0px;border: 0;border-bottom: 1px dashed #ccc;background: #999;">
					</td>
				</tr>
				<tr>
					<td style="width: 70%; vertical-align: top;">
						"""),_display_(Seq[Any](/*41.8*/transaction/*41.19*/.corporate.address)),format.raw/*41.37*/(""", """),_display_(Seq[Any](/*41.40*/transaction/*41.51*/.agent.country.name)),format.raw/*41.70*/("""
						<br>
						"""),_display_(Seq[Any](/*43.8*/transaction/*43.19*/.corporate.phoneNumber)),format.raw/*43.41*/("""
					</td>
					<td style="text-align: right; width: 30%; vertical-align: top;">
						"""),_display_(Seq[Any](/*46.8*/transaction/*46.19*/.agent.getIdToken())),format.raw/*46.38*/("""
						<br>
						"""),_display_(Seq[Any](/*48.8*/transaction/*48.19*/.agent.fullName)),format.raw/*48.34*/("""
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<hr style="margin: 5px 0px;border: 0;border-bottom: 1px dashed #ccc;background: #999;">
					</td>
				</tr>
				<tr>
					<td>
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*59.39*/doku/*59.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending Amount"))),format.raw/*59.112*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*60.15*/transaction/*60.26*/.senderAmountFormat)),format.raw/*60.45*/(""" """),_display_(Seq[Any](/*60.47*/transaction/*60.58*/.senderCurrency.code)),format.raw/*60.78*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*61.39*/doku/*61.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange Rate"))),format.raw/*61.111*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*62.15*/transaction/*62.26*/.forexReference.rateFormat)),format.raw/*62.52*/(""" """),_display_(Seq[Any](/*62.54*/transaction/*62.65*/.beneficiaryCurrency.code)),format.raw/*62.90*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*63.39*/doku/*63.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG206","Admin Fee"))),format.raw/*63.107*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*64.15*/transaction/*64.26*/.feesTotalFormat)),format.raw/*64.42*/(""" """),_display_(Seq[Any](/*64.44*/transaction/*64.55*/.senderCurrency.code)),format.raw/*64.75*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*65.39*/doku/*65.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"))),format.raw/*65.112*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*66.15*/transaction/*66.26*/.additionalFeesTotalFormat)),format.raw/*66.52*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*67.39*/doku/*67.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to Collect"))),format.raw/*67.115*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*68.15*/transaction/*68.26*/.collectAmountFormat)),format.raw/*68.46*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*69.39*/doku/*69.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to Receive"))),format.raw/*69.115*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*70.15*/transaction/*70.26*/.beneficiaryAmountFormat)),format.raw/*70.50*/(""" """),_display_(Seq[Any](/*70.52*/transaction/*70.63*/.beneficiaryCurrency.code)),format.raw/*70.88*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*71.39*/doku/*71.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending Purpose"))),format.raw/*71.113*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*72.15*/transaction/*72.26*/.senderNote)),format.raw/*72.37*/("""</dd>
						</dl>
					</td>
					<td style="text-align: right; vertical-align: top;">
						<div class="span6" style="margin: 0px; width: 100%;">
							<div class="well well-receipt well-print" style="width: 110px;padding: 7px;height: 50px;display: inline-block;text-align: left;">
								<div class="well-inner">
									<label style="margin-bottom: 0px;">"""),_display_(Seq[Any](/*79.46*/doku/*79.50*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*79.119*/(""":</label>
									<h3 style="margin: 0px; font-size: 12pt;">"""),_display_(Seq[Any](/*80.53*/transaction/*80.64*/.getIdToken())),format.raw/*80.77*/("""</h3>
								</div>
							</div>
						</div>
						<div class="span6" style="margin: 0px; width: 100%;">
							<div class="well well-receipt well-print" style="width: 110px;padding: 7px;height: 40px;display: inline-block;text-align: left;">
								<div class="well-inner">
									<h3 style="margin: 0px; font-size: 12pt;">"""),_display_(Seq[Any](/*87.53*/transaction/*87.64*/.statusText)),format.raw/*87.75*/("""</h3>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<hr style="margin: 0px;border: 0;border-bottom: 1px dashed #ccc;background: #999;">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*101.39*/doku/*101.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*101.105*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*102.15*/transaction/*102.26*/.channel.name)),format.raw/*102.39*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*103.39*/doku/*103.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG208","Transaction Time"))),format.raw/*103.114*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*104.15*/transaction/*104.26*/.createdFormat)),format.raw/*104.40*/("""</dd>
						</dl>
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*107.39*/doku/*107.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender Name"))),format.raw/*107.109*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*108.15*/transaction/*108.26*/.sender.fullName)),format.raw/*108.42*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*109.39*/doku/*109.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG209","Sender CID"))),format.raw/*109.108*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*110.15*/transaction/*110.26*/.sender.getIdToken())),format.raw/*110.46*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*111.39*/doku/*111.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG210","Sender ID"))),format.raw/*111.107*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*112.15*/transaction/*112.26*/.sender.personalId)),format.raw/*112.44*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*113.39*/doku/*113.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG211","Sender Phone"))),format.raw/*113.110*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*114.15*/transaction/*114.26*/.sender.phoneNumber)),format.raw/*114.45*/("""</dd>
						</dl>
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*117.39*/doku/*117.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver Name"))),format.raw/*117.111*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*118.15*/transaction/*118.26*/.beneficiary.fullName)),format.raw/*118.47*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*119.39*/doku/*119.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG212","Receiver CID"))),format.raw/*119.110*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*120.15*/transaction/*120.26*/.beneficiary.getIdToken())),format.raw/*120.51*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*121.39*/doku/*121.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG213","Receiver Phone"))),format.raw/*121.112*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*122.15*/transaction/*122.26*/.beneficiary.phoneNumber)),format.raw/*122.50*/("""</dd>
						</dl>
						"""),_display_(Seq[Any](/*124.8*/if(transaction.channel.code == "21" || transaction.channel.code == "22")/*124.80*/ {_display_(Seq[Any](format.raw/*124.82*/("""
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*126.39*/doku/*126.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG213","Voucher Code"))),format.raw/*126.110*/("""</dt>
							<dd>:
								"""),_display_(Seq[Any](/*128.10*/if(transaction.voucherCode != null && transaction.voucherCode != "")/*128.78*/ {_display_(Seq[Any](format.raw/*128.80*/("""
									"""),_display_(Seq[Any](/*129.11*/transaction/*129.22*/.voucherCode)),format.raw/*129.34*/("""
								""")))}/*130.10*/else/*130.14*/{_display_(Seq[Any](format.raw/*130.15*/("""
									-
								""")))})),format.raw/*132.10*/("""
							</dd>
						</dl>
						""")))})),format.raw/*135.8*/("""
						"""),_display_(Seq[Any](/*136.8*/if(transaction.channel.code == "04")/*136.44*/ {_display_(Seq[Any](format.raw/*136.46*/("""
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*138.39*/doku/*138.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG215","Doku Wallet Id"))),format.raw/*138.112*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*139.15*/transaction/*139.26*/.beneficiaryWalletId)),format.raw/*139.46*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*140.39*/doku/*140.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG216","Doku Wallet Name"))),format.raw/*140.114*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*141.15*/transaction/*141.26*/.beneficiaryWalletName)),format.raw/*141.48*/("""</dd>
						</dl>
						""")))})),format.raw/*143.8*/("""
						"""),_display_(Seq[Any](/*144.8*/defining(transaction.beneficiaryAccount)/*144.48*/ { account =>_display_(Seq[Any](format.raw/*144.61*/("""
						"""),_display_(Seq[Any](/*145.8*/if((account != null) && (account.number))/*145.49*/ {_display_(Seq[Any](format.raw/*145.51*/("""
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*147.39*/doku/*147.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank Name"))),format.raw/*147.107*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*148.15*/account/*148.22*/.bank.code)),format.raw/*148.32*/(""" - """),_display_(Seq[Any](/*148.36*/account/*148.43*/.bank.name)),format.raw/*148.53*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*149.39*/doku/*149.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Number"))),format.raw/*149.112*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*150.15*/account/*150.22*/.number)),format.raw/*150.29*/("""</dd>
							"""),_display_(Seq[Any](/*151.9*/if(transaction.channel.code == "07")/*151.45*/ {_display_(Seq[Any](format.raw/*151.47*/("""
								<dt style="text-align: left;">"""),_display_(Seq[Any](/*152.40*/doku/*152.44*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Name"))),format.raw/*152.111*/("""</dt>
								<dd>: """),_display_(Seq[Any](/*153.16*/account/*153.23*/.name)),format.raw/*153.28*/("""</dd>
							""")))})),format.raw/*154.9*/("""
						</dl>
						""")))})),format.raw/*156.8*/("""
						""")))})),format.raw/*157.8*/("""
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<hr style="margin: 0px;border: 0;border-bottom: 1px dashed #ccc;background: #999;">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;;"><small>
						"""),_display_(Seq[Any](/*167.8*/doku/*167.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG078","We are hereby acknowledge this is valid transaction receipt. Please keep this receipt for your reference If you need further assistance, please call the phone number above and mention your Transaction ID."))),format.raw/*167.271*/("""
						<br>"""),_display_(Seq[Any](/*168.12*/doku/*168.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG520","This is valid copy version of transaction receipt."))),format.raw/*168.121*/("""
						</small>
					</td>
				</tr>
			</table>
		</div>
	</div>
""")))})),format.raw/*175.2*/("""
</div>
</div>
<div class="span12" style="margin: 0px;">
	<div class="form-actions invisible-print noprint">
		"""),_display_(Seq[Any](/*180.4*/if(tipe.equals("b"))/*180.24*/ {_display_(Seq[Any](format.raw/*180.26*/("""
			<a class="btn" href=""""),_display_(Seq[Any](/*181.26*/routes/*181.32*/.Transaction.showBatchDetailTransaction(0, "", "", "", batchId))),format.raw/*181.95*/("""">"""),_display_(Seq[Any](/*181.98*/doku/*181.102*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*181.161*/("""</a>
		""")))}/*182.5*/else/*182.10*/{_display_(Seq[Any](format.raw/*182.11*/("""
			<a class="btn" href=""""),_display_(Seq[Any](/*183.26*/routes/*183.32*/.Transaction.list(0, "", "", ""))),format.raw/*183.64*/("""">"""),_display_(Seq[Any](/*183.67*/doku/*183.71*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*183.130*/("""</a>
		""")))})),format.raw/*184.4*/("""
		<button class="btn btn-print" type="print" data-target="#send-receipt"><i class="icon-print"></i> """),_display_(Seq[Any](/*185.102*/doku/*185.106*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*185.166*/("""</button>
		
	</div>
</div>
""")))}/*189.2*/ {_display_(Seq[Any](format.raw/*189.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*190.38*/routes/*190.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*190.84*/("""'></script>
""")))})),format.raw/*191.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction,batchId:Int,tipe:String): play.api.templates.Html = apply(err,transaction,batchId,tipe)
    
    def f:((String,models.Transaction,Int,String) => play.api.templates.Html) = (err,transaction,batchId,tipe) => apply(err,transaction,batchId,tipe)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:33 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/printTransaction.scala.html
                    HASH: 03ebaf0ce7707cc08299b62beb880a50d3c2a086
                    MATRIX: 776->1|976->74|1003->126|1039->128|1081->162|1119->163|1392->400|1407->406|1483->460|1572->514|1585->518|1680->591|1856->732|1871->739|1907->753|1980->791|1995->798|2035->800|2149->878|2174->881|2217->907|2229->912|2267->913|2467->1078|2487->1089|2530->1110|2748->1293|2768->1304|2808->1322|2847->1325|2867->1336|2908->1355|2962->1374|2982->1385|3026->1407|3150->1496|3170->1507|3211->1526|3265->1545|3285->1556|3322->1571|3676->1889|3689->1893|3781->1962|3837->1982|3857->1993|3898->2012|3936->2014|3956->2025|3998->2045|4078->2089|4091->2093|4182->2161|4238->2181|4258->2192|4306->2218|4344->2220|4364->2231|4411->2256|4491->2300|4504->2304|4591->2368|4647->2388|4667->2399|4705->2415|4743->2417|4763->2428|4805->2448|4885->2492|4898->2496|4990->2565|5046->2585|5066->2596|5114->2622|5194->2666|5207->2670|5302->2742|5358->2762|5378->2773|5420->2793|5500->2837|5513->2841|5608->2913|5664->2933|5684->2944|5730->2968|5768->2970|5788->2981|5835->3006|5915->3050|5928->3054|6021->3124|6077->3144|6097->3155|6130->3166|6528->3528|6541->3532|6633->3601|6731->3663|6751->3674|6786->3687|7152->4017|7172->4028|7205->4039|7615->4412|7629->4416|7715->4478|7772->4498|7793->4509|7829->4522|7910->4566|7924->4570|8019->4641|8076->4661|8097->4672|8134->4686|8292->4807|8306->4811|8396->4877|8453->4897|8474->4908|8513->4924|8594->4968|8608->4972|8697->5037|8754->5057|8775->5068|8818->5088|8899->5132|8913->5136|9001->5200|9058->5220|9079->5231|9120->5249|9201->5293|9215->5297|9306->5364|9363->5384|9384->5395|9426->5414|9584->5535|9598->5539|9690->5607|9747->5627|9768->5638|9812->5659|9893->5703|9907->5707|9998->5774|10055->5794|10076->5805|10124->5830|10205->5874|10219->5878|10312->5947|10369->5967|10390->5978|10437->6002|10498->6027|10580->6099|10621->6101|10762->6205|10776->6209|10867->6276|10932->6304|11010->6372|11051->6374|11099->6385|11120->6396|11155->6408|11185->6418|11199->6422|11239->6423|11293->6444|11358->6477|11402->6485|11448->6521|11489->6523|11630->6627|11644->6631|11737->6700|11794->6720|11815->6731|11858->6751|11939->6795|11953->6799|12048->6870|12105->6890|12126->6901|12171->6923|12228->6948|12272->6956|12322->6996|12374->7009|12418->7017|12469->7058|12510->7060|12651->7164|12665->7168|12753->7232|12810->7252|12827->7259|12860->7269|12901->7273|12918->7280|12951->7290|13032->7334|13046->7338|13139->7407|13196->7427|13213->7434|13243->7441|13293->7455|13339->7491|13380->7493|13457->7533|13471->7537|13562->7604|13620->7625|13637->7632|13665->7637|13711->7651|13763->7671|13803->7679|14103->7943|14117->7947|14400->8206|14449->8218|14463->8222|14592->8327|14691->8394|14839->8506|14869->8526|14910->8528|14973->8554|14989->8560|15075->8623|15115->8626|15130->8630|15213->8689|15240->8698|15254->8703|15294->8704|15357->8730|15373->8736|15428->8768|15468->8771|15482->8775|15565->8834|15605->8842|15745->8944|15760->8948|15844->9008|15892->9037|15932->9039|16007->9077|16023->9083|16086->9123|16131->9136
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|41->12|41->12|41->12|42->13|42->13|42->13|48->19|48->19|48->19|51->22|51->22|51->22|54->25|54->25|57->28|57->28|57->28|64->35|64->35|64->35|70->41|70->41|70->41|70->41|70->41|70->41|72->43|72->43|72->43|75->46|75->46|75->46|77->48|77->48|77->48|88->59|88->59|88->59|89->60|89->60|89->60|89->60|89->60|89->60|90->61|90->61|90->61|91->62|91->62|91->62|91->62|91->62|91->62|92->63|92->63|92->63|93->64|93->64|93->64|93->64|93->64|93->64|94->65|94->65|94->65|95->66|95->66|95->66|96->67|96->67|96->67|97->68|97->68|97->68|98->69|98->69|98->69|99->70|99->70|99->70|99->70|99->70|99->70|100->71|100->71|100->71|101->72|101->72|101->72|108->79|108->79|108->79|109->80|109->80|109->80|116->87|116->87|116->87|130->101|130->101|130->101|131->102|131->102|131->102|132->103|132->103|132->103|133->104|133->104|133->104|136->107|136->107|136->107|137->108|137->108|137->108|138->109|138->109|138->109|139->110|139->110|139->110|140->111|140->111|140->111|141->112|141->112|141->112|142->113|142->113|142->113|143->114|143->114|143->114|146->117|146->117|146->117|147->118|147->118|147->118|148->119|148->119|148->119|149->120|149->120|149->120|150->121|150->121|150->121|151->122|151->122|151->122|153->124|153->124|153->124|155->126|155->126|155->126|157->128|157->128|157->128|158->129|158->129|158->129|159->130|159->130|159->130|161->132|164->135|165->136|165->136|165->136|167->138|167->138|167->138|168->139|168->139|168->139|169->140|169->140|169->140|170->141|170->141|170->141|172->143|173->144|173->144|173->144|174->145|174->145|174->145|176->147|176->147|176->147|177->148|177->148|177->148|177->148|177->148|177->148|178->149|178->149|178->149|179->150|179->150|179->150|180->151|180->151|180->151|181->152|181->152|181->152|182->153|182->153|182->153|183->154|185->156|186->157|196->167|196->167|196->167|197->168|197->168|197->168|204->175|209->180|209->180|209->180|210->181|210->181|210->181|210->181|210->181|210->181|211->182|211->182|211->182|212->183|212->183|212->183|212->183|212->183|212->183|213->184|214->185|214->185|214->185|218->189|218->189|219->190|219->190|219->190|220->191
                    -- GENERATED --
                */
            