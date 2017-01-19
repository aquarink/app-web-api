
package views.html.receive

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


	.aa.table, th"""),format.raw/*39.15*/("""{"""),format.raw/*39.16*/("""
		background-color: #2AD1F7;
	"""),format.raw/*41.2*/("""}"""),format.raw/*41.3*/("""

</style>

<div class="header">
	<img src="/assets/images/doku_small.png">&nbsp;&nbsp;<b>Remit</font> </b>
</div>
<hr style="width:100%;"/>


"""),_display_(Seq[Any](/*51.2*/defining(result.path("transaction"))/*51.38*/ { trx =>_display_(Seq[Any](format.raw/*51.47*/("""
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
				"""),_display_(Seq[Any](/*69.6*/trx/*69.9*/.path("id").getTextValue)),format.raw/*69.33*/("""
			</td>
			<td style=" background-color: #E5E3E3; font-size: 12pt;" align="center">
				"""),_display_(Seq[Any](/*72.6*/defining(new java.util.Date(trx.path("createdTime").getLongValue()))/*72.74*/ { trxTimeStr =>_display_(Seq[Any](format.raw/*72.90*/("""
				"""),_display_(Seq[Any](/*73.6*/trxTimeStr)),format.raw/*73.16*/("""
				""")))})),format.raw/*74.6*/("""
			</td>
			<td style=" background-color: #E5E3E3; font-size: 12pt;" align="center">
				"""),_display_(Seq[Any](/*77.6*/models/*77.12*/.Transaction.statusText(trx.path("status").getIntValue))),format.raw/*77.67*/("""
			</td>

		</tr>
	</table>
	<pre>

	</pre>

	<p style="font-size: 14pt;"><b>Remittance Information</b></p>
	<hr style="margin-top: -14px;"/>

	"""),_display_(Seq[Any](/*89.3*/defining(trx.path("fund"))/*89.29*/ { fund =>_display_(Seq[Any](format.raw/*89.39*/("""
	<table width="100%" align="left" border="0">
		<tr>
			<td width="30%">Sending Country</td>
			<td width="1%" align="center">:</td>
			<td width="40%"><p align="right">"""),_display_(Seq[Any](/*94.38*/trx/*94.41*/.path("fund").path("origin").path("country").path("name").getTextValue)),format.raw/*94.111*/("""</p></td>
			<td width="29%">&nbsp;</td>
		</tr>
		<tr>
			<td>Receiving Country</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*100.26*/trx/*100.29*/.path("fund").path("destination").path("country").path("name").getTextValue)),format.raw/*100.104*/("""</p></td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Remit option</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*106.26*/trx/*106.29*/.path("channel").path("name").getTextValue)),format.raw/*106.71*/("""</p></td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>  &nbsp;</td>
			<td>&nbsp;  </td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Send Amount</td>
			<td align="center">:</td>
			<td style="text-align: right;">"""),_display_(Seq[Any](/*118.36*/models/*118.42*/.Currency.format(fund.path("origin").path("amount").getNumberValue, fund.path("origin").path("currency").getTextValue))),format.raw/*118.160*/("""&nbsp;&nbsp;&nbsp;"""),_display_(Seq[Any](/*118.179*/fund/*118.183*/.path("origin").path("currency").getTextValue)),format.raw/*118.228*/("""</td>
			<!--<td width="30%" align="right">"""),_display_(Seq[Any](/*119.39*/fund/*119.43*/.path("origin").path("currency").getTextValue)),format.raw/*119.88*/("""</td>!-->
			<td>&nbsp;&nbsp;</td>
		</tr>
		"""),format.raw/*137.7*/("""
		<tr>
			<td><p style="color: green;"><strong>Receive Amount</strong></p></td>
			<td align="center"><p style="color: green;"><strong>:</strong></p></td>
			<td style="text-align: right;"><p style="color: green;"><strong>"""),_display_(Seq[Any](/*141.69*/models/*141.75*/.Currency.format(fund.path("destination").path("amount").getNumberValue, fund.path("destination").path("currency").getTextValue))),format.raw/*141.203*/("""&nbsp;&nbsp;&nbsp;"""),_display_(Seq[Any](/*141.222*/fund/*141.226*/.path("destination").path("currency").getTextValue)),format.raw/*141.276*/("""</strong></p></td>
			<!--<td width="30%" align="right"><p style="color: green;"><strong>"""),_display_(Seq[Any](/*142.72*/fund/*142.76*/.path("destination").path("currency").getTextValue)),format.raw/*142.126*/("""</strong></p></td>!-->
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td> &nbsp;</td>
			<td>&nbsp;  </td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Send Purpose</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*154.26*/trx/*154.29*/.path("senderNote").getTextValue)),format.raw/*154.61*/("""</p></td>
			<td>&nbsp;&nbsp;</td>
		</tr>
	</table>
	""")))})),format.raw/*158.3*/("""

<br><br><br><br><br><br><br><br><br><br>

	<p style="font-size: 14pt;"><b>Receiver Information</b></p>
	<hr style="margin-top: -14px;">
	"""),_display_(Seq[Any](/*164.3*/defining(trx.path("beneficiary"))/*164.36*/ { beneficiary =>_display_(Seq[Any](format.raw/*164.53*/("""
	<p style="font-size: 9pt; margin-top: -8px;">Data given by Sender when uncompleted, need to be completed by receiving operator and receiver require to provide proof of valid ID when disbursing money</p>
	<table width="100%" align="left" border="0">
		<tr>
			<td width="30%">Member ID</td>
			<td width="1%" align="center">:</td>
			<td width="40%" align="right">"""),_display_(Seq[Any](/*170.35*/beneficiary/*170.46*/.path("id").getTextValue)),format.raw/*170.70*/("""</td>
			<td width="29%"><p style="font-size: 10px"><sup>*Please be sure to keep your ID.</sup></p></td>
		</tr>
		<tr>
			<td>Full Name</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*176.23*/beneficiary/*176.34*/.path("firstName").getTextValue)),format.raw/*176.65*/(""" """),_display_(Seq[Any](/*176.67*/beneficiary/*176.78*/.path("lastName").getTextValue)),format.raw/*176.108*/("""</td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Date Of Birth</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*182.23*/beneficiary/*182.34*/.path("birthDate").getTextValue)),format.raw/*182.65*/("""</td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Gender</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*188.23*/beneficiary/*188.34*/.path("gender").getTextValue)),format.raw/*188.62*/("""</td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Country</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*194.23*/beneficiary/*194.34*/.path("country").path("name").getTextValue)),format.raw/*194.76*/("""</td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>City</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*200.23*/beneficiary/*200.34*/.path("city").getTextValue)),format.raw/*200.60*/("""</td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Phone No.</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*206.23*/beneficiary/*206.34*/.path("phoneNumber").getTextValue)),format.raw/*206.67*/("""</td>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td>Email</td>
			<td align="center">:</td>
			<td><p align="right">"""),_display_(Seq[Any](/*212.26*/beneficiary/*212.37*/.path("email").getTextValue)),format.raw/*212.64*/("""</p></td>
			<td>&nbsp;&nbsp;</td>
	</table>     <br/><br/><br/><br/><br/><br/>
	""")))})),format.raw/*215.3*/("""
	""")))})),format.raw/*216.3*/("""
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
                    DATE: Wed Jan 18 18:12:28 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/receive/receipt_print.scala.html
                    HASH: 0fe3aeb1b80467a4e3bd3e147c40947be16c0b6c
                    MATRIX: 762->1|878->40|997->133|1024->134|1071->154|1099->155|1134->163|1162->164|1243->218|1271->219|1306->227|1334->228|1427->294|1455->295|1487->300|1515->301|1564->323|1592->324|1625->330|1653->331|1717->368|1745->369|1781->378|1810->379|1855->396|1884->397|1917->403|1945->404|1990->422|2018->423|2063->440|2092->441|2150->472|2178->473|2357->617|2402->653|2449->662|2903->1081|2914->1084|2960->1108|3086->1199|3163->1267|3217->1283|3258->1289|3290->1299|3327->1305|3453->1396|3468->1402|3545->1457|3726->1603|3761->1629|3809->1639|4016->1810|4028->1813|4121->1883|4298->2023|4311->2026|4410->2101|4576->2230|4589->2233|4654->2275|4930->2514|4946->2520|5088->2638|5145->2657|5160->2661|5229->2706|5310->2750|5324->2754|5392->2799|5465->3747|5726->3971|5742->3977|5894->4105|5951->4124|5966->4128|6040->4178|6167->4268|6181->4272|6255->4322|6534->4564|6547->4567|6602->4599|6689->4654|6865->4794|6908->4827|6964->4844|7367->5210|7388->5221|7435->5245|7665->5438|7686->5449|7740->5480|7779->5482|7800->5493|7854->5523|8014->5646|8035->5657|8089->5688|8242->5804|8263->5815|8314->5843|8468->5960|8489->5971|8554->6013|8705->6127|8726->6138|8775->6164|8931->6283|8952->6294|9008->6327|9163->6445|9184->6456|9234->6483|9348->6565|9383->6568
                    LINES: 26->1|29->1|37->9|37->9|39->11|39->11|41->13|41->13|44->16|44->16|46->18|46->18|51->23|51->23|52->24|52->24|54->26|54->26|56->28|56->28|59->31|59->31|60->32|60->32|60->32|60->32|62->34|62->34|64->36|64->36|67->39|67->39|69->41|69->41|79->51|79->51|79->51|97->69|97->69|97->69|100->72|100->72|100->72|101->73|101->73|102->74|105->77|105->77|105->77|117->89|117->89|117->89|122->94|122->94|122->94|128->100|128->100|128->100|134->106|134->106|134->106|146->118|146->118|146->118|146->118|146->118|146->118|147->119|147->119|147->119|150->137|154->141|154->141|154->141|154->141|154->141|154->141|155->142|155->142|155->142|167->154|167->154|167->154|171->158|177->164|177->164|177->164|183->170|183->170|183->170|189->176|189->176|189->176|189->176|189->176|189->176|195->182|195->182|195->182|201->188|201->188|201->188|207->194|207->194|207->194|213->200|213->200|213->200|219->206|219->206|219->206|225->212|225->212|225->212|228->215|229->216
                    -- GENERATED --
                */
            