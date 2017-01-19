
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
object receipt extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,models.Transaction,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
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
						"""),_display_(Seq[Any](/*46.8*/transaction/*46.19*/.agent.idToken)),format.raw/*46.33*/("""
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
									<h3 style="margin: 0px; font-size: 12pt;">"""),_display_(Seq[Any](/*80.53*/transaction/*80.64*/.idToken)),format.raw/*80.72*/("""</h3>
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
							<dd>: """),_display_(Seq[Any](/*110.15*/transaction/*110.26*/.sender.idToken)),format.raw/*110.41*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*111.39*/doku/*111.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG210","Sender ID"))),format.raw/*111.107*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*112.15*/transaction/*112.26*/.sender.personalId)),format.raw/*112.44*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*113.39*/doku/*113.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG211","Sender Phone"))),format.raw/*113.110*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*114.15*/transaction/*114.26*/.sender.phoneNumber)),format.raw/*114.45*/("""</dd>
						</dl>
						<dl class="dl-horizontal dl-aligned" style="margin: 0px;">
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*117.39*/doku/*117.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver Name"))),format.raw/*117.111*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*118.15*/transaction/*118.26*/.beneficiary.fullName)),format.raw/*118.47*/("""</dd>
							<dt style="text-align: left;">"""),_display_(Seq[Any](/*119.39*/doku/*119.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG212","Receiver CID"))),format.raw/*119.110*/("""</dt>
							<dd>: """),_display_(Seq[Any](/*120.15*/transaction/*120.26*/.beneficiary.idToken)),format.raw/*120.46*/("""</dd>
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
						</small>
					</td>
				</tr>
			</table>
		

		</div>
	</div>
""")))})),format.raw/*176.2*/("""
</div>
</div>
<div class="span12" style="margin: 0px;">
	<div class="form-actions invisible-print noprint">
		<button class="btn btn-print" type="print" data-target="#send-receipt"><i class="icon-print"></i> """),_display_(Seq[Any](/*181.102*/doku/*181.106*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*181.166*/("""</button>
		<a class="btn" href=""""),_display_(Seq[Any](/*182.25*/routes/*182.31*/.Send.create())),format.raw/*182.45*/("""">"""),_display_(Seq[Any](/*182.48*/doku/*182.52*/.kirimdoku.util.MultiLanguage.getLanguage("LANG080","Send another money"))),format.raw/*182.125*/(""" <i class="icon-arrow-right"></i></a>
	</div>
</div>
""")))}/*185.2*/ {_display_(Seq[Any](format.raw/*185.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*186.38*/routes/*186.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*186.84*/("""'></script>
""")))})),format.raw/*187.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction): play.api.templates.Html = apply(err,transaction)
    
    def f:((String,models.Transaction) => play.api.templates.Html) = (err,transaction) => apply(err,transaction)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/receipt.scala.html
                    HASH: e374ebb43005040dd56b0349fb9538c8f98bf551
                    MATRIX: 749->1|922->47|949->99|985->101|1027->135|1065->136|1338->373|1353->379|1429->433|1518->487|1531->491|1626->564|1802->705|1817->712|1853->726|1926->764|1941->771|1981->773|2095->851|2120->854|2163->880|2175->885|2213->886|2413->1051|2433->1062|2476->1083|2694->1266|2714->1277|2754->1295|2793->1298|2813->1309|2854->1328|2908->1347|2928->1358|2972->1380|3096->1469|3116->1480|3152->1494|3206->1513|3226->1524|3263->1539|3617->1857|3630->1861|3722->1930|3778->1950|3798->1961|3839->1980|3877->1982|3897->1993|3939->2013|4019->2057|4032->2061|4123->2129|4179->2149|4199->2160|4247->2186|4285->2188|4305->2199|4352->2224|4432->2268|4445->2272|4532->2336|4588->2356|4608->2367|4646->2383|4684->2385|4704->2396|4746->2416|4826->2460|4839->2464|4931->2533|4987->2553|5007->2564|5055->2590|5135->2634|5148->2638|5243->2710|5299->2730|5319->2741|5361->2761|5441->2805|5454->2809|5549->2881|5605->2901|5625->2912|5671->2936|5709->2938|5729->2949|5776->2974|5856->3018|5869->3022|5962->3092|6018->3112|6038->3123|6071->3134|6469->3496|6482->3500|6574->3569|6672->3631|6692->3642|6722->3650|7088->3980|7108->3991|7141->4002|7551->4375|7565->4379|7651->4441|7708->4461|7729->4472|7765->4485|7846->4529|7860->4533|7955->4604|8012->4624|8033->4635|8070->4649|8228->4770|8242->4774|8332->4840|8389->4860|8410->4871|8449->4887|8530->4931|8544->4935|8633->5000|8690->5020|8711->5031|8749->5046|8830->5090|8844->5094|8932->5158|8989->5178|9010->5189|9051->5207|9132->5251|9146->5255|9237->5322|9294->5342|9315->5353|9357->5372|9515->5493|9529->5497|9621->5565|9678->5585|9699->5596|9743->5617|9824->5661|9838->5665|9929->5732|9986->5752|10007->5763|10050->5783|10131->5827|10145->5831|10238->5900|10295->5920|10316->5931|10363->5955|10424->5980|10506->6052|10547->6054|10688->6158|10702->6162|10793->6229|10858->6257|10936->6325|10977->6327|11025->6338|11046->6349|11081->6361|11111->6371|11125->6375|11165->6376|11219->6397|11284->6430|11328->6438|11374->6474|11415->6476|11556->6580|11570->6584|11663->6653|11720->6673|11741->6684|11784->6704|11865->6748|11879->6752|11974->6823|12031->6843|12052->6854|12097->6876|12154->6901|12198->6909|12248->6949|12300->6962|12344->6970|12395->7011|12436->7013|12577->7117|12591->7121|12679->7185|12736->7205|12753->7212|12786->7222|12827->7226|12844->7233|12877->7243|12958->7287|12972->7291|13065->7360|13122->7380|13139->7387|13169->7394|13219->7408|13265->7444|13306->7446|13383->7486|13397->7490|13488->7557|13546->7578|13563->7585|13591->7590|13637->7604|13689->7624|13729->7632|14029->7896|14043->7900|14326->8159|14429->8230|14677->8440|14692->8444|14776->8504|14847->8538|14863->8544|14900->8558|14940->8561|14954->8565|15051->8638|15124->8692|15164->8694|15239->8732|15255->8738|15318->8778|15363->8791
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|41->12|41->12|41->12|42->13|42->13|42->13|48->19|48->19|48->19|51->22|51->22|51->22|54->25|54->25|57->28|57->28|57->28|64->35|64->35|64->35|70->41|70->41|70->41|70->41|70->41|70->41|72->43|72->43|72->43|75->46|75->46|75->46|77->48|77->48|77->48|88->59|88->59|88->59|89->60|89->60|89->60|89->60|89->60|89->60|90->61|90->61|90->61|91->62|91->62|91->62|91->62|91->62|91->62|92->63|92->63|92->63|93->64|93->64|93->64|93->64|93->64|93->64|94->65|94->65|94->65|95->66|95->66|95->66|96->67|96->67|96->67|97->68|97->68|97->68|98->69|98->69|98->69|99->70|99->70|99->70|99->70|99->70|99->70|100->71|100->71|100->71|101->72|101->72|101->72|108->79|108->79|108->79|109->80|109->80|109->80|116->87|116->87|116->87|130->101|130->101|130->101|131->102|131->102|131->102|132->103|132->103|132->103|133->104|133->104|133->104|136->107|136->107|136->107|137->108|137->108|137->108|138->109|138->109|138->109|139->110|139->110|139->110|140->111|140->111|140->111|141->112|141->112|141->112|142->113|142->113|142->113|143->114|143->114|143->114|146->117|146->117|146->117|147->118|147->118|147->118|148->119|148->119|148->119|149->120|149->120|149->120|150->121|150->121|150->121|151->122|151->122|151->122|153->124|153->124|153->124|155->126|155->126|155->126|157->128|157->128|157->128|158->129|158->129|158->129|159->130|159->130|159->130|161->132|164->135|165->136|165->136|165->136|167->138|167->138|167->138|168->139|168->139|168->139|169->140|169->140|169->140|170->141|170->141|170->141|172->143|173->144|173->144|173->144|174->145|174->145|174->145|176->147|176->147|176->147|177->148|177->148|177->148|177->148|177->148|177->148|178->149|178->149|178->149|179->150|179->150|179->150|180->151|180->151|180->151|181->152|181->152|181->152|182->153|182->153|182->153|183->154|185->156|186->157|196->167|196->167|196->167|205->176|210->181|210->181|210->181|211->182|211->182|211->182|211->182|211->182|211->182|214->185|214->185|215->186|215->186|215->186|216->187
                    -- GENERATED --
                */
            