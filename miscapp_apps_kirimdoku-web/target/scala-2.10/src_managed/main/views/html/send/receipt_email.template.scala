
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
object receipt_email extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(result: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.41*/("""
<div style="font-family:helveticaneue-light; font-size: 14px; padding: 3em; color: #000;">

	<div>
		"""),_display_(Seq[Any](/*5.4*/defining(result.path("transaction"))/*5.40*/ { trx =>_display_(Seq[Any](format.raw/*5.49*/("""
		"""),_display_(Seq[Any](/*6.4*/defining(trx.path("sender"))/*6.32*/ { sender =>_display_(Seq[Any](format.raw/*6.44*/("""
		<div>
			<p>Dear """),_display_(Seq[Any](/*8.13*/sender/*8.19*/.path("firstName").getTextValue)),format.raw/*8.50*/(""" """),_display_(Seq[Any](/*8.52*/sender/*8.58*/.path("lastName").getTextValue)),format.raw/*8.88*/(""", </p>
			<br/>
			<p>Thank you for using our service</p>
			<p>By this email, we would like to inform that we have received the money that you were sent,
				and our operator at destination country is ready to disbursement of money.</p>
	
			<p>Congratulations! You just got the unique number from us to facilitate of sending money.
				Just mention your <strong>Member ID</strong> for next transaction.</p><br/>
			<p style="font-weight: bold;">Member ID &nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;
			<span style="color: red;">"""),_display_(Seq[Any](/*17.31*/sender/*17.37*/.path("id").getTextValue)),format.raw/*17.61*/("""</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="font-size: 12px; font-weight: normal;"><sup>*Please be sure to keep your ID.</sup></span></p>
		</div>
		""")))})),format.raw/*20.4*/("""
		<br/>
		"""),_display_(Seq[Any](/*22.4*/defining(trx.path("fund"))/*22.30*/ { fund =>_display_(Seq[Any](format.raw/*22.40*/("""
		<h4>Sending Money</h4><hr style="margin-top: -13px;"/>
		<table style="width: 60%; font-family: helveticaneue-light; font-size: 12px;">
			<tr>
				<td width="160px">Agent ID</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*28.10*/trx/*28.13*/.path("agent").path("code").getTextValue)),format.raw/*28.53*/("""</td>
			</tr>
			<tr>
				<td>Transaction Code</td>
				<td align="center">:</td>
				<td><b>"""),_display_(Seq[Any](/*33.13*/trx/*33.16*/.path("id").getTextValue)),format.raw/*33.40*/("""</b></td>
			</tr>
				<td>Send Purpose</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*37.10*/trx/*37.13*/.path("senderNote").getTextValue())),format.raw/*37.47*/("""</td>
			</tr>
			<tr>
				<td>Status</td>
				<td align="center">:</td>
				<td><strong>"""),_display_(Seq[Any](/*42.18*/models/*42.24*/.Transaction.statusText(trx.path("status").getIntValue).toUpperCase())),format.raw/*42.93*/("""</strong></td>
			</tr>
		</table>


		<h4>Amount</h4><hr style="margin-top: -13px;"/>
		<table style="width: 60%; font-family: helveticaneue-light; font-size: 12px;">
		<tr>
			<td width="160px">Sent Amount</td>
			<td align="center">:</td>
			<td align="right" width="160px">"""),_display_(Seq[Any](/*52.37*/models/*52.43*/.Currency.format(
				fund.path("origin").path("amount").getNumberValue,
				fund.path("origin").path("currency").getTextValue))),format.raw/*54.55*/("""
			</td>
			<td>"""),_display_(Seq[Any](/*56.9*/fund/*56.13*/.path("origin").path("currency").getTextValue)),format.raw/*56.58*/("""</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Fee</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*62.23*/models/*62.29*/.Currency.format(fund.path("fees").path("total").getNumberValue, fund.path("fees").path("currency").getTextValue))),format.raw/*62.142*/("""</td>
			<td>"""),_display_(Seq[Any](/*63.9*/fund/*63.13*/.path("fees").path("currency").getTextValue)),format.raw/*63.56*/("""</td>
		</tr>
		<tr>
			<td>Total Amount</td>
			<td align="center">:</td>
			"""),_display_(Seq[Any](/*68.5*/defining(fund.path("origin").path("amount").getDoubleValue()+fund.path("fees").path("total").getDoubleValue())/*68.115*/ { total =>_display_(Seq[Any](format.raw/*68.126*/("""
			<td align="right">"""),_display_(Seq[Any](/*69.23*/models/*69.29*/.Currency.format(total, trx.path("fund").path("origin").path("currency").getTextValue()))),format.raw/*69.117*/("""</td>
			<td>"""),_display_(Seq[Any](/*70.9*/fund/*70.13*/.path("origin").path("currency").getTextValue())),format.raw/*70.60*/("""</td>
			""")))})),format.raw/*71.5*/("""
		</tr>
		<tr>
			<td>Receive Ammount</td>
			<td align="center">:</td>
			<td align="right">"""),_display_(Seq[Any](/*76.23*/models/*76.29*/.Currency.format(
				fund.path("destination").path("amount").getNumberValue,
				fund.path("destination").path("currency").getTextValue))),format.raw/*78.60*/("""
			</td>
			<td>"""),_display_(Seq[Any](/*80.9*/fund/*80.13*/.path("destination").path("currency").getTextValue)),format.raw/*80.63*/("""</td>
		</tr>
		<tr>
		</table>
		<br/>
		<h4>Receiver</h4><hr style="margin-top: -13px;"/>
		<table style="width: 60%; font-family: helveticaneue-light; font-size: 12px;">
			<tr>
				<td width="160px">Full Name</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*90.10*/trx/*90.13*/.path("beneficiary").path("firstName").getTextValue)),format.raw/*90.64*/(""" """),_display_(Seq[Any](/*90.66*/trx/*90.69*/.path("beneficiary").path("lastName").getTextValue)),format.raw/*90.119*/("""</td>
			</tr>
			<tr>
				<td>Address</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*95.10*/trx/*95.13*/.path("beneficiary").path("address").getTextValue)),format.raw/*95.62*/("""</td>
			</tr>
			<tr>
				<td>Mobile Phone</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*100.10*/trx/*100.13*/.path("beneficiary").path("phoneNumber").getTextValue)),format.raw/*100.66*/("""</td>
			</tr>
			<tr>
				<td>Email</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*105.10*/trx/*105.13*/.path("beneficiary").path("email").getTextValue)),format.raw/*105.60*/("""</td>
			</tr>
		</table>
		""")))})),format.raw/*108.4*/("""
		""")))})),format.raw/*109.4*/("""
	</div>
	
	<br/>
	<br/>
	<br/>
	<div>
		<center><p style="font-weight: bold; font-size: 18px;">Send Money Made Easy</p></center> <br/>
		<p align="center" style="margin-top: -30px; font-size: 11px; font-weight: normal;">"Thank you for using our services and have a nice day"</p>
		<p align="right"><b>Customer Care DOKU</b>  +62 21 555 666 (Monday - Sunday)</p>
		<hr/>
		<p style="font-size: 11px;"><b>Copyright @ 2012 DOKU</b> Please be sure to keep your <b>transaction and verification code (PIN)</b>.
			Make sure you send the codes to the person that registered in the data sender or receiver. <b>Strictly prohibited</b> to give
			<b>the transaction code and verification code (PIN)</b> to unregistered person in the data sender or receiver. All kinds of offenses or omission will be dealt according to company rules and remittance regulations.
			Content of this email and all attachment is intended for the email recipient only. Any opinions that contained in this email, is the personal opinion
			of the sender and not the representation of <b>DOKU</b>. If you are not the recepient of this email, you have no right to copying and showing this email to another person.
			Please contact the sender of this email, if you are sure that this email is not intende for you.
			</p><hr/>

	</div>
</div>

"""))}
    }
    
    def render(result:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(result)
    
    def f:((org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (result) => apply(result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/receipt_email.scala.html
                    HASH: eab6e4b4b3a5317d3da376adfd8a6252bd0a573c
                    MATRIX: 759->1|875->40|1012->143|1056->179|1102->188|1140->192|1176->220|1225->232|1281->253|1295->259|1347->290|1384->292|1398->298|1449->328|2006->849|2021->855|2067->879|2252->1033|2299->1045|2334->1071|2382->1081|2640->1303|2652->1306|2714->1346|2845->1441|2857->1444|2903->1468|3023->1552|3035->1555|3091->1589|3217->1679|3232->1685|3323->1754|3637->2032|3652->2038|3801->2165|3854->2183|3867->2187|3934->2232|4077->2339|4092->2345|4228->2458|4277->2472|4290->2476|4355->2519|4469->2598|4589->2708|4639->2719|4698->2742|4713->2748|4824->2836|4873->2850|4886->2854|4955->2901|4996->2911|5127->3006|5142->3012|5301->3149|5354->3167|5367->3171|5439->3221|5732->3478|5744->3481|5817->3532|5855->3534|5867->3537|5940->3587|6059->3670|6071->3673|6142->3722|6267->3810|6280->3813|6356->3866|6474->3947|6487->3950|6557->3997|6618->4026|6654->4030
                    LINES: 26->1|29->1|33->5|33->5|33->5|34->6|34->6|34->6|36->8|36->8|36->8|36->8|36->8|36->8|45->17|45->17|45->17|48->20|50->22|50->22|50->22|56->28|56->28|56->28|61->33|61->33|61->33|65->37|65->37|65->37|70->42|70->42|70->42|80->52|80->52|82->54|84->56|84->56|84->56|90->62|90->62|90->62|91->63|91->63|91->63|96->68|96->68|96->68|97->69|97->69|97->69|98->70|98->70|98->70|99->71|104->76|104->76|106->78|108->80|108->80|108->80|118->90|118->90|118->90|118->90|118->90|118->90|123->95|123->95|123->95|128->100|128->100|128->100|133->105|133->105|133->105|136->108|137->109
                    -- GENERATED --
                */
            