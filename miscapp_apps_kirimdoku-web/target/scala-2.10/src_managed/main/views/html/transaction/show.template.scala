
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
object show extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,models.Transaction,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Transaction common receipt")/*5.38*/{_display_(Seq[Any](format.raw/*5.39*/("""

"""),_display_(Seq[Any](/*7.2*/partial/*7.9*/.flash_alert())),format.raw/*7.23*/("""

<div class="container-common-inner">
"""),_display_(Seq[Any](/*10.2*/if(err)/*10.9*/ {_display_(Seq[Any](format.raw/*10.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*13.36*/err)),format.raw/*13.39*/("""</div>
		</div>
	</div>
""")))}/*16.3*/else/*16.8*/{_display_(Seq[Any](format.raw/*16.9*/("""
	<dl class="dl-horizontal dl-aligned">
		<dt>"""),_display_(Seq[Any](/*18.8*/doku/*18.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*18.74*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*19.8*/transaction/*19.19*/.channel.name)),format.raw/*19.32*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*20.8*/doku/*20.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG103","Sender country"))),format.raw/*20.81*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*21.8*/transaction/*21.19*/.senderCountry.name)),format.raw/*21.38*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*22.8*/doku/*22.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG104","Receiver country"))),format.raw/*22.83*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*23.8*/transaction/*23.19*/.beneficiaryCountry.name)),format.raw/*23.43*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*24.8*/doku/*24.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG223","Receiver city"))),format.raw/*24.80*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*25.8*/transaction/*25.19*/.beneficiaryCity)),format.raw/*25.35*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*26.8*/doku/*26.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*26.81*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*27.8*/transaction/*27.19*/.senderAmountFormat)),format.raw/*27.38*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*28.8*/doku/*28.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"))),format.raw/*28.84*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*29.8*/transaction/*29.19*/.beneficiaryAmountFormat)),format.raw/*29.43*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*30.8*/doku/*30.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"))),format.raw/*30.80*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*31.8*/transaction/*31.19*/.forexReference.rateFormat)),format.raw/*31.45*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*32.8*/doku/*32.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"))),format.raw/*32.70*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*33.8*/transaction/*33.19*/.feesTotalFormat)),format.raw/*33.35*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*34.8*/doku/*34.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"))),format.raw/*34.81*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*35.8*/transaction/*35.19*/.additionalFeesTotalFormat)),format.raw/*35.45*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*36.8*/doku/*36.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"))),format.raw/*36.84*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*37.8*/transaction/*37.19*/.collectAmountFormat)),format.raw/*37.39*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*38.8*/doku/*38.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"))),format.raw/*38.82*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*39.8*/transaction/*39.19*/.senderNote)),format.raw/*39.30*/("""</dd>
	</dl>

	<dl class="dl-horizontal dl-aligned">
		<dt>"""),_display_(Seq[Any](/*43.8*/doku/*43.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG072","Customer ID of sender"))),format.raw/*43.88*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*44.8*/transaction/*44.19*/.sender.getIdToken())),format.raw/*44.39*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*45.8*/doku/*45.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*45.78*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*46.8*/transaction/*46.19*/.sender.fullName)),format.raw/*46.35*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*47.8*/doku/*47.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG200","Sender phone number"))),format.raw/*47.86*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*48.8*/transaction/*48.19*/.sender.phoneNumber)),format.raw/*48.38*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*49.8*/doku/*49.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG277","Sender address"))),format.raw/*49.81*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*50.8*/transaction/*50.19*/.sender.address)),format.raw/*50.34*/("""</dd>
	</dl>
	<dl class="dl-horizontal dl-aligned">
		<dt>"""),_display_(Seq[Any](/*53.8*/doku/*53.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG073","Customer ID of receiver"))),format.raw/*53.90*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*54.8*/transaction/*54.19*/.beneficiary.getIdToken())),format.raw/*54.44*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*55.8*/doku/*55.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*55.80*/("""</dt>
		<dd><pre>"""),_display_(Seq[Any](/*56.13*/transaction/*56.24*/.beneficiary.fullName)),format.raw/*56.45*/("""</pre></dd>
		<dt>"""),_display_(Seq[Any](/*57.8*/doku/*57.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG202","Receiver phone number"))),format.raw/*57.88*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*58.8*/transaction/*58.19*/.beneficiary.phoneNumber)),format.raw/*58.43*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*59.8*/doku/*59.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG252","Receiver address"))),format.raw/*59.83*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*60.8*/transaction/*60.19*/.beneficiary.address)),format.raw/*60.39*/("""</dd>
		"""),_display_(Seq[Any](/*61.4*/if(transaction.channel.code == "06" || transaction.channel.code == "05" || transaction.channel.code == "03" || transaction.channel.code == "22" || transaction.channel.code == "07")/*61.184*/ {_display_(Seq[Any](format.raw/*61.186*/("""
			"""),_display_(Seq[Any](/*62.5*/if(transaction.beneficiaryAccount != null)/*62.47*/ {_display_(Seq[Any](format.raw/*62.49*/("""
				<dt>"""),_display_(Seq[Any](/*63.10*/doku/*63.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Number"))),format.raw/*63.83*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*64.10*/transaction/*64.21*/.beneficiaryAccount.number)),format.raw/*64.47*/("""</dd>
				<dt>"""),_display_(Seq[Any](/*65.10*/doku/*65.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank Name"))),format.raw/*65.78*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*66.10*/transaction/*66.21*/.beneficiaryAccount.bank.name)),format.raw/*66.50*/("""</dd>
			""")))})),format.raw/*67.5*/("""
		""")))})),format.raw/*68.4*/("""
		"""),_display_(Seq[Any](/*69.4*/if(transaction.channel.code == "10")/*69.40*/ {_display_(Seq[Any](format.raw/*69.42*/("""
			"""),_display_(Seq[Any](/*70.5*/if(transaction.billPayment() != null)/*70.42*/ {_display_(Seq[Any](format.raw/*70.44*/("""
				<dt>"""),_display_(Seq[Any](/*71.10*/doku/*71.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG276","Subscriber ID"))),format.raw/*71.82*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*72.10*/transaction/*72.21*/.billPayment().accountNumber)),format.raw/*72.49*/("""</dd>
				<dt>"""),_display_(Seq[Any](/*73.10*/doku/*73.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG275","Denom"))),format.raw/*73.74*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*74.10*/transaction/*74.21*/.billPayment().selectDenomName)),format.raw/*74.51*/("""</dd>
			""")))})),format.raw/*75.5*/("""
		""")))})),format.raw/*76.4*/("""
	</dl>
	
	"""),_display_(Seq[Any](/*79.3*/if(transaction.channel.code == "21" || transaction.channel.code == "22")/*79.75*/ {_display_(Seq[Any](format.raw/*79.77*/("""
	"""),_display_(Seq[Any](/*80.3*/if(transaction.voucherCode != null)/*80.38*/ {_display_(Seq[Any](format.raw/*80.40*/("""
	<dl class="dl-horizontal dl-aligned">
		<dt>"""),_display_(Seq[Any](/*82.8*/doku/*82.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG214","Voucher Code"))),format.raw/*82.79*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*83.8*/transaction/*83.19*/.voucherCode)),format.raw/*83.31*/("""</dd>
	</dl>
	""")))})),format.raw/*85.3*/("""
	""")))})),format.raw/*86.3*/("""
	<dl class="dl-horizontal dl-aligned">
		<dt>"""),_display_(Seq[Any](/*88.8*/doku/*88.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG518","Response Code"))),format.raw/*88.80*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*89.8*/transaction/*89.19*/.remitResponseCode)),format.raw/*89.37*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*90.8*/doku/*90.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG519","Response Message"))),format.raw/*90.83*/("""</dt>
		<dd><pre>"""),_display_(Seq[Any](/*91.13*/transaction/*91.24*/.remitResponseMessage)),format.raw/*91.45*/("""</pre></dd>
	</dl>
""")))})),format.raw/*93.2*/("""
</div>
""")))}/*95.2*/ {_display_(Seq[Any](format.raw/*95.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*96.38*/routes/*96.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*96.84*/("""'></script>
""")))})),format.raw/*97.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction): play.api.templates.Html = apply(err,transaction)
    
    def f:((String,models.Transaction) => play.api.templates.Html) = (err,transaction) => apply(err,transaction)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/show.scala.html
                    HASH: 75507c49be58c204f3f17d4dbf1dc70fafddd884
                    MATRIX: 753->1|926->47|953->99|989->101|1033->137|1071->138|1108->141|1122->148|1157->162|1232->202|1247->209|1287->211|1401->289|1426->292|1469->318|1481->323|1519->324|1601->371|1614->375|1698->437|1746->450|1766->461|1801->474|1849->487|1862->491|1953->560|2001->573|2021->584|2062->603|2110->616|2123->620|2216->691|2264->704|2284->715|2330->739|2378->752|2391->756|2481->824|2529->837|2549->848|2587->864|2635->877|2648->881|2739->950|2787->963|2807->974|2848->993|2896->1006|2909->1010|3003->1082|3051->1095|3071->1106|3117->1130|3165->1143|3178->1147|3268->1215|3316->1228|3336->1239|3384->1265|3432->1278|3445->1282|3525->1340|3573->1353|3593->1364|3631->1380|3679->1393|3692->1397|3783->1466|3831->1479|3851->1490|3899->1516|3947->1529|3960->1533|4054->1605|4102->1618|4122->1629|4164->1649|4212->1662|4225->1666|4317->1736|4365->1749|4385->1760|4418->1771|4513->1831|4526->1835|4624->1911|4672->1924|4692->1935|4734->1955|4782->1968|4795->1972|4883->2038|4931->2051|4951->2062|4989->2078|5037->2091|5050->2095|5146->2169|5194->2182|5214->2193|5255->2212|5303->2225|5316->2229|5407->2298|5455->2311|5475->2322|5512->2337|5606->2396|5619->2400|5719->2478|5767->2491|5787->2502|5834->2527|5882->2540|5895->2544|5985->2612|6039->2630|6059->2641|6102->2662|6156->2681|6169->2685|6267->2761|6315->2774|6335->2785|6381->2809|6429->2822|6442->2826|6535->2897|6583->2910|6603->2921|6645->2941|6689->2950|6879->3130|6920->3132|6960->3137|7011->3179|7051->3181|7097->3191|7110->3195|7201->3264|7252->3279|7272->3290|7320->3316|7371->3331|7384->3335|7470->3399|7521->3414|7541->3425|7592->3454|7633->3464|7668->3468|7707->3472|7752->3508|7792->3510|7832->3515|7878->3552|7918->3554|7964->3564|7977->3568|8067->3636|8118->3651|8138->3662|8188->3690|8239->3705|8252->3709|8334->3769|8385->3784|8405->3795|8457->3825|8498->3835|8533->3839|8580->3851|8661->3923|8701->3925|8739->3928|8783->3963|8823->3965|8905->4012|8918->4016|9007->4083|9055->4096|9075->4107|9109->4119|9155->4134|9189->4137|9271->4184|9284->4188|9374->4256|9422->4269|9442->4280|9482->4298|9530->4311|9543->4315|9636->4386|9690->4404|9710->4415|9753->4436|9804->4456|9831->4465|9870->4467|9944->4505|9959->4511|10021->4551|10065->4564
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|36->7|36->7|36->7|39->10|39->10|39->10|42->13|42->13|45->16|45->16|45->16|47->18|47->18|47->18|48->19|48->19|48->19|49->20|49->20|49->20|50->21|50->21|50->21|51->22|51->22|51->22|52->23|52->23|52->23|53->24|53->24|53->24|54->25|54->25|54->25|55->26|55->26|55->26|56->27|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30|59->30|59->30|60->31|60->31|60->31|61->32|61->32|61->32|62->33|62->33|62->33|63->34|63->34|63->34|64->35|64->35|64->35|65->36|65->36|65->36|66->37|66->37|66->37|67->38|67->38|67->38|68->39|68->39|68->39|72->43|72->43|72->43|73->44|73->44|73->44|74->45|74->45|74->45|75->46|75->46|75->46|76->47|76->47|76->47|77->48|77->48|77->48|78->49|78->49|78->49|79->50|79->50|79->50|82->53|82->53|82->53|83->54|83->54|83->54|84->55|84->55|84->55|85->56|85->56|85->56|86->57|86->57|86->57|87->58|87->58|87->58|88->59|88->59|88->59|89->60|89->60|89->60|90->61|90->61|90->61|91->62|91->62|91->62|92->63|92->63|92->63|93->64|93->64|93->64|94->65|94->65|94->65|95->66|95->66|95->66|96->67|97->68|98->69|98->69|98->69|99->70|99->70|99->70|100->71|100->71|100->71|101->72|101->72|101->72|102->73|102->73|102->73|103->74|103->74|103->74|104->75|105->76|108->79|108->79|108->79|109->80|109->80|109->80|111->82|111->82|111->82|112->83|112->83|112->83|114->85|115->86|117->88|117->88|117->88|118->89|118->89|118->89|119->90|119->90|119->90|120->91|120->91|120->91|122->93|124->95|124->95|125->96|125->96|125->96|126->97
                    -- GENERATED --
                */
            