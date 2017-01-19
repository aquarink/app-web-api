
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
object receipt_email extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[org.codehaus.jackson.JsonNode,org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(result: org.codehaus.jackson.JsonNode, customer: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.82*/("""
<div style="font-family: helvetica; font-size: 14px; padding: 3em;">

	<div>
		"""),_display_(Seq[Any](/*5.4*/defining(result.path("transaction"))/*5.40*/ { trx =>_display_(Seq[Any](format.raw/*5.49*/("""
		<p>Dear """),_display_(Seq[Any](/*6.12*/customer/*6.20*/.path("firstName").getTextValue)),format.raw/*6.51*/(""" """),_display_(Seq[Any](/*6.53*/customer/*6.61*/.path("lastName").getTextValue)),format.raw/*6.91*/(""", </p>
		<p>Thank you for using our service.</p>
		<p>Congratulations! By this email we would like to inform you that disbursement has been succesfully.</p>

		<p>To facilitate of Disbursement process just mention your  <b>ID Member</b> for next transaction.</p><br/>
		<h3>Member ID &nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;
			<font color="#FF0000">"""),_display_(Seq[Any](/*12.27*/customer/*12.35*/.path("id").getTextValue)),format.raw/*12.59*/("""</font>&nbsp;&nbsp;&nbsp;<span style="font-size: 12px; font-weight: normal;"><sup>*Please be sure to keep your ID.</sup></span></h3>
		<br/>
		"""),_display_(Seq[Any](/*14.4*/defining(trx.path("fund"))/*14.30*/ { fund =>_display_(Seq[Any](format.raw/*14.40*/("""
		<h4>Disbursement</h4><hr style="margin-top: -13px;"/>
		<table style="width: 60%; font-family: helveticaneue-light; font-size: 12px;">
			<tr>
				<td width="160px">Agent ID</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*20.10*/trx/*20.13*/.path("agent").path("code").getTextValue)),format.raw/*20.53*/("""</td>
			</tr>
			<tr>
				<td>Transaction Code</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*25.10*/trx/*25.13*/.path("id").getTextValue)),format.raw/*25.37*/("""</td>
			</tr>

			<tr>
				<td>Send Purpose</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*31.10*/trx/*31.13*/.path("senderNote").getTextValue)),format.raw/*31.45*/("""</td>

			</tr>
			<tr>
				<td>Date of Sending</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*37.10*/models/*37.16*/.Transaction.formatDate(new java.util.Date(trx.path("cashInTime").getLongValue)))),format.raw/*37.96*/("""</td>
			</tr>
			<tr>
				<td>Date of Disbursement</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*42.10*/models/*42.16*/.Transaction.formatDate(new java.util.Date(trx.path("cashOutTime").getLongValue)))),format.raw/*42.97*/("""</td>
			</tr>
			<tr>
				<td>Status</td>
				<td align="center">:</td>
				<td><strong>"""),_display_(Seq[Any](/*47.18*/models/*47.24*/.Transaction.statusText(trx.path("status").getIntValue).toUpperCase())),format.raw/*47.93*/("""</strong></td>
			</tr>
		</table>

		<h4>Amount</h4><hr style="margin-top: -13px;"/>
		<table style="width: 60%; font-family: helveticaneue-light; font-size: 12px;">
			<tr>
				<td width="160px">Send Amount</td>
				<td align="center">:</td>
				<td align="right" width="160px">"""),_display_(Seq[Any](/*56.38*/models/*56.44*/.Currency.format(
					fund.path("origin").path("amount").getNumberValue,
					fund.path("origin").path("currency").getTextValue))),format.raw/*58.56*/("""
				</td>
				<td>"""),_display_(Seq[Any](/*60.10*/fund/*60.14*/.path("origin").path("currency").getTextValue)),format.raw/*60.59*/("""</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Receive Amount</td>
				<td align="center">:</td>
				<td align="right">"""),_display_(Seq[Any](/*66.24*/models/*66.30*/.Currency.format(
					fund.path("destination").path("amount").getNumberValue,
					fund.path("destination").path("currency").getTextValue))),format.raw/*68.61*/("""
				</td>
				<td>"""),_display_(Seq[Any](/*70.10*/fund/*70.14*/.path("destination").path("currency").getTextValue)),format.raw/*70.64*/("""</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		""")))})),format.raw/*74.4*/("""
		<br/>
		"""),_display_(Seq[Any](/*76.4*/defining(trx.path("sender"))/*76.32*/ { c =>_display_(Seq[Any](format.raw/*76.39*/("""
		<h4>Sender</h4><hr style="margin-top: -13px;"/>
		<table style="width: 60%; font-family: helveticaneue-light; font-size: 12px;">

			<tr>
				<td width="160px">Full name</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*83.10*/c/*83.11*/.path("firstName").getTextValue)),format.raw/*83.42*/(""" """),_display_(Seq[Any](/*83.44*/c/*83.45*/.path("lastName").getTextValue)),format.raw/*83.75*/("""</td>
			</tr>
			<tr>
				<td>Address</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*88.10*/c/*88.11*/.path("address").getTextValue)),format.raw/*88.40*/("""</td>
			</tr>
			<tr>
				<td>Mobile Phone</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*93.10*/c/*93.11*/.path("phoneNumber").getTextValue)),format.raw/*93.44*/("""</td>
			</tr>
			<tr>
				<td>Email</td>
				<td align="center">:</td>
				<td>"""),_display_(Seq[Any](/*98.10*/c/*98.11*/.path("email").getTextValue)),format.raw/*98.38*/("""</td>
			</tr>
		</table>
		""")))})),format.raw/*101.4*/("""
		""")))})),format.raw/*102.4*/("""
	</div>

		<br/>
		<br/>
		<br/>
	<div>
		<center><p style="text-shadow:#666666; font-size:16px ; font-weight:bold"><i>Send Money Made Easy</i></p></center><p align="center" style="font-size:10px; margin-top:-15px;">"Thank you for using our services and have a nice day"</p>
		<p align="right"><b>Customer Care DOKU </b> +62 21 555 666 (Monday - Sunday)</p>
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
    
    def render(result:org.codehaus.jackson.JsonNode,customer:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(result,customer)
    
    def f:((org.codehaus.jackson.JsonNode,org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (result,customer) => apply(result,customer)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:28 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/receive/receipt_email.scala.html
                    HASH: 0947aa21edae5ecd4bf16b16b06c34eeefd734d2
                    MATRIX: 792->1|949->81|1064->162|1108->198|1154->207|1201->219|1217->227|1269->258|1306->260|1322->268|1373->298|1753->642|1770->650|1816->674|1995->818|2030->844|2078->854|2335->1075|2347->1078|2409->1118|2537->1210|2549->1213|2595->1237|2720->1326|2732->1329|2786->1361|2914->1453|2929->1459|3031->1539|3163->1635|3178->1641|3281->1722|3407->1812|3422->1818|3513->1887|3830->2168|3845->2174|3996->2303|4052->2323|4065->2327|4132->2372|4292->2496|4307->2502|4468->2641|4524->2661|4537->2665|4609->2715|4689->2764|4736->2776|4773->2804|4818->2811|5071->3028|5081->3029|5134->3060|5172->3062|5182->3063|5234->3093|5353->3176|5363->3177|5414->3206|5538->3294|5548->3295|5603->3328|5720->3409|5730->3410|5779->3437|5840->3466|5876->3470
                    LINES: 26->1|29->1|33->5|33->5|33->5|34->6|34->6|34->6|34->6|34->6|34->6|40->12|40->12|40->12|42->14|42->14|42->14|48->20|48->20|48->20|53->25|53->25|53->25|59->31|59->31|59->31|65->37|65->37|65->37|70->42|70->42|70->42|75->47|75->47|75->47|84->56|84->56|86->58|88->60|88->60|88->60|94->66|94->66|96->68|98->70|98->70|98->70|102->74|104->76|104->76|104->76|111->83|111->83|111->83|111->83|111->83|111->83|116->88|116->88|116->88|121->93|121->93|121->93|126->98|126->98|126->98|129->101|130->102
                    -- GENERATED --
                */
            