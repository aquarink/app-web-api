
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
object receipt_print extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(result: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.41*/("""
<html>
    <head>
	    <Title>Print</Title>

    </head>
<body>
<style type="text/css" media="all" >
	* """),format.raw/*9.4*/("""{"""),format.raw/*9.5*/("""
		font-size: 1em;
	"""),format.raw/*11.2*/("""}"""),format.raw/*11.3*/("""

	body """),format.raw/*13.7*/("""{"""),format.raw/*13.8*/("""
		padding: 3em;
		font-family: helveticaneue-light;
	"""),format.raw/*16.2*/("""}"""),format.raw/*16.3*/("""

	table"""),format.raw/*18.7*/("""{"""),format.raw/*18.8*/("""
		/*width: 100%;*/
		border: 1px;
		border-collapse: collapse;

	"""),format.raw/*23.2*/("""}"""),format.raw/*23.3*/("""
	h1 """),format.raw/*24.5*/("""{"""),format.raw/*24.6*/("""
		font-size: 1.3em;
	"""),format.raw/*26.2*/("""}"""),format.raw/*26.3*/("""

	hr """),format.raw/*28.5*/("""{"""),format.raw/*28.6*/("""
		width: 100%;
		line-height: 1em;
	"""),format.raw/*31.2*/("""}"""),format.raw/*31.3*/("""
	.header"""),format.raw/*32.9*/("""{"""),format.raw/*32.10*/("""margin-top: -2em;"""),format.raw/*32.27*/("""}"""),format.raw/*32.28*/("""

	.aa"""),format.raw/*34.5*/("""{"""),format.raw/*34.6*/("""
		padding: 2em;
	"""),format.raw/*36.2*/("""}"""),format.raw/*36.3*/("""


	              .aa.table, th"""),format.raw/*39.29*/("""{"""),format.raw/*39.30*/("""
		              background-color: #2AD1F7;
	              """),format.raw/*41.16*/("""}"""),format.raw/*41.17*/("""

</style>
<div class="header">
	<img src="/assets/images/doku_small.png">&nbsp;&nbsp;<b>Remit</font> </b>
</div>
<hr style="width:100%;"/>
"""),_display_(Seq[Any](/*48.2*/defining(result.path("transaction"))/*48.38*/ { trx =>_display_(Seq[Any](format.raw/*48.47*/("""
<div class="aa">
<p style="font-size: 14pt;"><b>TRANSACTION RECEIPT</b></p>
<table border="1" width="100%">
	<tr bgcolor="#2AD1F7">
		<th style="font-size: 11pt;">
			TRANSACTION NO.</span>
		</th>
		<th style="font-size: 11pt;">
			REMITTANCE DATE
		</th>
		<th style="font-size: 11pt;">
			STATUS
		</th >
	</tr>
	<tr >

		<td style=" background-color: #E5E3E3; font-size: 12pt;" align="center">
			"""),_display_(Seq[Any](/*66.5*/trx/*66.8*/.path("id").getTextValue)),format.raw/*66.32*/("""
		</td>
		<td style=" background-color: #E5E3E3; font-size: 12pt;" align="center">
			"""),_display_(Seq[Any](/*69.5*/defining(new Date(trx.path("createdTime").getLongValue()))/*69.63*/ { trxTimeStr =>_display_(Seq[Any](format.raw/*69.79*/("""
			"""),_display_(Seq[Any](/*70.5*/trxTimeStr)),format.raw/*70.15*/("""
			""")))})),format.raw/*71.5*/("""
		</td>
		<td style=" background-color: #E5E3E3; font-size: 12pt;" align="center">
			"""),_display_(Seq[Any](/*74.5*/models/*74.11*/.Transaction.statusText(trx.path("status").getIntValue))),format.raw/*74.66*/("""
		</td>

	</tr>
</table>

<p style="font-size: 14pt;"><b>Remittance Information</b></p>
<hr style="margin-top: -14px;"/>

"""),_display_(Seq[Any](/*83.2*/defining(trx.path("fund"))/*83.28*/ { fund =>_display_(Seq[Any](format.raw/*83.38*/("""
	<table width="100%" align="left">
		<tr>
			<td width="30%">Sending Country</td>
			<td width="1%" align="center">:</td>
			<td width="40%"><p align="right">"""),_display_(Seq[Any](/*88.38*/trx/*88.41*/.path("fund").path("origin").path("country").path("name").getTextValue)),format.raw/*88.111*/("""</p></td>
			<td width="29%">&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Receiving Country</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*94.26*/trx/*94.29*/.path("fund").path("destination").path("country").path("name").getTextValue)),format.raw/*94.104*/("""</p></td>

		</tr>
		<tr>
			<td>Remit option</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*100.26*/trx/*100.29*/.path("channel").path("name").getTextValue)),format.raw/*100.71*/("""</p></td>

		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;  </td>

		</tr>
		<tr>
			<td>Send Amount</td>
			<td align="center">:</td>
			<td style="text-align: right;">
				"""),_display_(Seq[Any](/*113.6*/models/*113.12*/.Currency.format(fund.path("origin").path("amount").getNumberValue, fund.path("origin").path("currency").getTextValue))),format.raw/*113.130*/("""
				"""),_display_(Seq[Any](/*114.6*/fund/*114.10*/.path("origin").path("currency").getTextValue)),format.raw/*114.55*/("""
			</td>
		</tr>
		<tr>
			<td><p style="color: green;"><strong>Receive Amount</strong></p></td>
			<td align="center"><p style="color: green;"><strong>:</strong></p></td>
			<td style="text-align: right;"><p style="color: green;">
				<strong>"""),_display_(Seq[Any](/*121.14*/models/*121.20*/.Currency.format(fund.path("destination").path("amount").getNumberValue, fund.path("destination").path("currency").getTextValue()))),format.raw/*121.150*/("""
				"""),_display_(Seq[Any](/*122.6*/fund/*122.10*/.path("destination").path("currency").getTextValue)),format.raw/*122.60*/("""</strong></p></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td> &nbsp;</td>
			<td>&nbsp;  </td>

		</tr>
		<tr>
			<td>Send Purpose</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*133.26*/trx/*133.29*/.path("senderNote").getTextValue)),format.raw/*133.61*/("""</p></td>
		</tr>
	</table> <br/><br/><br/><br/>
""")))})),format.raw/*136.2*/("""
<pre>







</pre>
	<p style="font-size: 14pt;"><b>Receiver Information</b></p>
<hr style="margin-top: -14px;">
"""),_display_(Seq[Any](/*148.2*/defining(trx.path("beneficiary"))/*148.35*/ { beneficiary =>_display_(Seq[Any](format.raw/*148.52*/("""
<p style="font-size: 9pt; margin-top: -8px;">Data given by Sender when uncompleted, need to be completed by receiving operator and receiver require to provide proof of valid ID when disbursing money</p>
	<table width="100%" align="left">
		<tr>
			<td width="30%">Member ID</td>
			<td width="1%" align="center">:</td>
			<td width="40%"><p align="right"> """),_display_(Seq[Any](/*154.39*/beneficiary/*154.50*/.path("id").getTextValue)),format.raw/*154.74*/("""</p></td>
			<td width="29%"><p style="font-size: 10px"><sup>*Please be sure to keep your ID.</sup></p></td>

		</tr>
		<tr>
			<td>Full Name</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*161.26*/beneficiary/*161.37*/.path("firstName").getTextValue)),format.raw/*161.68*/(""" """),_display_(Seq[Any](/*161.70*/beneficiary/*161.81*/.path("lastName").getTextValue)),format.raw/*161.111*/("""</p></td>

		</tr>
		<tr>
			<td>Date Of Birth</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*167.26*/beneficiary/*167.37*/.path("birthDate").getTextValue)),format.raw/*167.68*/("""</p></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*172.26*/beneficiary/*172.37*/.path("gender").getTextValue)),format.raw/*172.65*/("""</p></td>
		</tr>
		<tr>
			<td>Country</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*177.26*/beneficiary/*177.37*/.path("country").path("name").getTextValue)),format.raw/*177.79*/("""</p></td>
		</tr>
		<tr>
			<td>City</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*182.26*/beneficiary/*182.37*/.path("city").getTextValue)),format.raw/*182.63*/("""</p></td>
		</tr>
		<tr>
		<td>Phone No.</td>
		<td align="center">:</td>
		<td><p align="right">"""),_display_(Seq[Any](/*187.25*/beneficiary/*187.36*/.path("phoneNumber").getTextValue)),format.raw/*187.69*/("""</p></td>
		</tr>
		<tr>
		<td>Email</td>
		<td align="center">:</td>
		<td><p align="right">"""),_display_(Seq[Any](/*192.25*/beneficiary/*192.36*/.path("email").getTextValue)),format.raw/*192.63*/("""</p></td>
	</table><br/><br/><br/><br/><br/><br/>
""")))})),format.raw/*194.2*/("""
""")))})),format.raw/*195.2*/("""
</div>
<pre>








</pre>
<div>
	<p style="font-size: 10pt;"><b>Copyright @ 2012 DOKU</b> Please be sure to keep your <b>transaction and verification code (PIN)</b>.
		Make sure you send the codes to the person that registered in the data sender or receiver.</p>
</div>
</body>
</html>

<script type="text/javascript">
    window.print();
</script>
"""))}
    }
    
    def render(result:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(result)
    
    def f:((org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (result) => apply(result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/receipt_print.scala.html
                    HASH: 631680e6f380398e27cbbf9187c4da40ffb6fcb3
                    MATRIX: 759->1|875->40|1006->145|1033->146|1080->166|1108->167|1143->175|1171->176|1252->230|1280->231|1315->239|1343->240|1436->306|1464->307|1496->312|1524->313|1573->335|1601->336|1634->342|1662->343|1726->380|1754->381|1790->390|1819->391|1864->408|1893->409|1926->415|1954->416|1999->434|2027->435|2086->466|2115->467|2202->526|2231->527|2407->668|2452->704|2499->713|2937->1116|2948->1119|2994->1143|3117->1231|3184->1289|3238->1305|3278->1310|3310->1320|3346->1325|3469->1413|3484->1419|3561->1474|3720->1598|3755->1624|3803->1634|3999->1794|4011->1797|4104->1867|4286->2013|4298->2016|4396->2091|4538->2196|4551->2199|4616->2241|4846->2435|4862->2441|5004->2559|5046->2565|5060->2569|5128->2614|5411->2860|5427->2866|5581->2996|5623->3002|5637->3006|5710->3056|5936->3245|5949->3248|6004->3280|6086->3330|6237->3445|6280->3478|6336->3495|6731->3853|6752->3864|6799->3888|7037->4089|7058->4100|7112->4131|7151->4133|7172->4144|7226->4174|7369->4280|7390->4291|7444->4322|7579->4420|7600->4431|7651->4459|7787->4558|7808->4569|7873->4611|8006->4707|8027->4718|8076->4744|8211->4842|8232->4853|8288->4886|8419->4980|8440->4991|8490->5018|8573->5069|8607->5071
                    LINES: 26->1|29->1|37->9|37->9|39->11|39->11|41->13|41->13|44->16|44->16|46->18|46->18|51->23|51->23|52->24|52->24|54->26|54->26|56->28|56->28|59->31|59->31|60->32|60->32|60->32|60->32|62->34|62->34|64->36|64->36|67->39|67->39|69->41|69->41|76->48|76->48|76->48|94->66|94->66|94->66|97->69|97->69|97->69|98->70|98->70|99->71|102->74|102->74|102->74|111->83|111->83|111->83|116->88|116->88|116->88|122->94|122->94|122->94|128->100|128->100|128->100|141->113|141->113|141->113|142->114|142->114|142->114|149->121|149->121|149->121|150->122|150->122|150->122|161->133|161->133|161->133|164->136|176->148|176->148|176->148|182->154|182->154|182->154|189->161|189->161|189->161|189->161|189->161|189->161|195->167|195->167|195->167|200->172|200->172|200->172|205->177|205->177|205->177|210->182|210->182|210->182|215->187|215->187|215->187|220->192|220->192|220->192|222->194|223->195
                    -- GENERATED --
                */
            