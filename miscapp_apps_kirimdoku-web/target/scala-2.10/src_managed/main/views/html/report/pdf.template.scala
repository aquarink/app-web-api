
package views.html.report

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
object pdf extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[com.avaje.ebean.Page[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Transaction]):play.api.templates.Html = {
        _display_ {
def /*4.2*/header/*4.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*4.38*/("""
<th class=""""),_display_(Seq[Any](/*5.13*/key)),format.raw/*5.16*/("""">
	<bold>"""),_display_(Seq[Any](/*6.9*/title)),format.raw/*6.14*/("""</bold>
</th>
""")))};
Seq[Any](format.raw/*1.57*/("""


"""),format.raw/*8.2*/("""

<html>
<head>
	<style>
		@page"""),format.raw/*13.9*/("""{"""),format.raw/*13.10*/("""
			size: landscape;
		"""),format.raw/*15.3*/("""}"""),format.raw/*15.4*/("""
		body"""),format.raw/*16.7*/("""{"""),format.raw/*16.8*/("""
			font-size: 12px;
		"""),format.raw/*18.3*/("""}"""),format.raw/*18.4*/("""
		#report-table, #report-table th, #report-table td"""),format.raw/*19.52*/("""{"""),format.raw/*19.53*/("""
			border:1px solid black;
			text-align: center;
		"""),format.raw/*22.3*/("""}"""),format.raw/*22.4*/("""
		#report-table"""),format.raw/*23.16*/("""{"""),format.raw/*23.17*/("""
			border-collapse: collapse;
		"""),format.raw/*25.3*/("""}"""),format.raw/*25.4*/("""
	</style>
</head>
<body>

<div class="page-header">
    <h1>Report Transaction</h1>
</div>

<div>
	"""),_display_(Seq[Any](/*35.3*/partial/*35.10*/.flash_alert())),format.raw/*35.24*/("""

    """),_display_(Seq[Any](/*37.6*/if(currentPage.getTotalRowCount == 0)/*37.43*/{_display_(Seq[Any](format.raw/*37.44*/("""
    <div class="well">
        <em>Results are empty</em>
    </div>
    """)))}/*41.7*/else/*41.12*/{_display_(Seq[Any](format.raw/*41.13*/("""
    <table id="report-table">
        <thead>
        <tr>
            """),_display_(Seq[Any](/*45.14*/header("id", "Transaction ID"))),format.raw/*45.44*/("""
            """),_display_(Seq[Any](/*46.14*/header("corporate", "Corporate"))),format.raw/*46.46*/("""
            """),_display_(Seq[Any](/*47.14*/header("channel", "Channel"))),format.raw/*47.42*/("""
            """),_display_(Seq[Any](/*48.14*/header("senderCustomerId", "Sender ID"))),format.raw/*48.53*/("""
            """),_display_(Seq[Any](/*49.14*/header("senderCountry", "Sender Country"))),format.raw/*49.55*/("""
            """),_display_(Seq[Any](/*50.14*/header("senderAmount", "Sender Amount"))),format.raw/*50.53*/("""
            """),_display_(Seq[Any](/*51.14*/header("beneficiaryCustomerId", "Receiver ID"))),format.raw/*51.60*/("""
            """),_display_(Seq[Any](/*52.14*/header("beneficiaryCountry", "Receiver Country"))),format.raw/*52.62*/("""
            """),_display_(Seq[Any](/*53.14*/header("beneficiaryAmount", "Receiver Amount"))),format.raw/*53.60*/("""
            """),_display_(Seq[Any](/*54.14*/header("rate", "Rate"))),format.raw/*54.36*/("""
            """),_display_(Seq[Any](/*55.14*/header("status", "Status"))),format.raw/*55.40*/("""
            """),_display_(Seq[Any](/*56.14*/header("createdTime", "Date"))),format.raw/*56.43*/("""
        </tr>
        </thead>
        <tbody>
        """),_display_(Seq[Any](/*60.10*/for(transaction <- currentPage.getList) yield /*60.49*/ {_display_(Seq[Any](format.raw/*60.51*/("""
        <tr>
            <td class="left">"""),_display_(Seq[Any](/*62.31*/transaction/*62.42*/.getIdToken)),format.raw/*62.53*/("""</td>
			<td>"""),_display_(Seq[Any](/*63.9*/transaction/*63.20*/.corporate.code)),format.raw/*63.35*/("""</td>
			<td>"""),_display_(Seq[Any](/*64.9*/transaction/*64.20*/.channel.name)),format.raw/*64.33*/("""</td>
            <td>"""),_display_(Seq[Any](/*65.18*/transaction/*65.29*/.sender.getIdToken)),format.raw/*65.47*/("""</td>
            <td>"""),_display_(Seq[Any](/*66.18*/transaction/*66.29*/.senderCountry.name)),format.raw/*66.48*/("""</td>
            <td class="right">"""),_display_(Seq[Any](/*67.32*/transaction/*67.43*/.senderAmountFormat)),format.raw/*67.62*/(""" """),_display_(Seq[Any](/*67.64*/transaction/*67.75*/.senderCurrency.code)),format.raw/*67.95*/("""</td>
            <td>"""),_display_(Seq[Any](/*68.18*/transaction/*68.29*/.beneficiary.getIdToken)),format.raw/*68.52*/("""</td>
            <td>"""),_display_(Seq[Any](/*69.18*/transaction/*69.29*/.beneficiaryCountry.name)),format.raw/*69.53*/("""</td>
            <td class="right">"""),_display_(Seq[Any](/*70.32*/transaction/*70.43*/.beneficiaryAmountFormat)),format.raw/*70.67*/(""" """),_display_(Seq[Any](/*70.69*/transaction/*70.80*/.beneficiaryCurrency.code)),format.raw/*70.105*/("""</td>
            <td>"""),_display_(Seq[Any](/*71.18*/transaction/*71.29*/.forexReference.rateFormat)),format.raw/*71.55*/("""</td>
            <td>"""),_display_(Seq[Any](/*72.18*/transaction/*72.29*/.statusText)),format.raw/*72.40*/("""</td>
            <td>"""),_display_(Seq[Any](/*73.18*/models/*73.24*/.Transaction.formatDate(transaction.createdTime, true))),format.raw/*73.78*/("""</td>
        </tr>
        """)))})),format.raw/*75.10*/("""
        </tbody>
    </table>
    """)))})),format.raw/*78.6*/("""
</div>

<div class="span5" id="viewContainer">
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*84.38*/routes/*84.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*84.84*/("""'></script></body>
</html>

"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Transaction]): play.api.templates.Html = apply(currentPage)
    
    def f:((com.avaje.ebean.Page[models.Transaction]) => play.api.templates.Html) = (currentPage) => apply(currentPage)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:28 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/report/pdf.scala.html
                    HASH: 4b61248b9426d17b9b659a9c26a5afc18f80890d
                    MATRIX: 762->1|877->60|890->66|983->96|1031->109|1055->112|1100->123|1126->128|1180->56|1209->143|1268->176|1297->177|1347->200|1375->201|1409->208|1437->209|1487->232|1515->233|1595->285|1624->286|1704->339|1732->340|1776->356|1805->357|1865->390|1893->391|2029->492|2045->499|2081->513|2123->520|2169->557|2208->558|2301->634|2314->639|2353->640|2462->713|2514->743|2564->757|2618->789|2668->803|2718->831|2768->845|2829->884|2879->898|2942->939|2992->953|3053->992|3103->1006|3171->1052|3221->1066|3291->1114|3341->1128|3409->1174|3459->1188|3503->1210|3553->1224|3601->1250|3651->1264|3702->1293|3795->1350|3850->1389|3890->1391|3970->1435|3990->1446|4023->1457|4072->1471|4092->1482|4129->1497|4178->1511|4198->1522|4233->1535|4292->1558|4312->1569|4352->1587|4411->1610|4431->1621|4472->1640|4545->1677|4565->1688|4606->1707|4644->1709|4664->1720|4706->1740|4765->1763|4785->1774|4830->1797|4889->1820|4909->1831|4955->1855|5028->1892|5048->1903|5094->1927|5132->1929|5152->1940|5200->1965|5259->1988|5279->1999|5327->2025|5386->2048|5406->2059|5439->2070|5498->2093|5513->2099|5589->2153|5650->2182|5717->2218|5846->2311|5861->2317|5923->2357
                    LINES: 26->1|28->4|28->4|30->4|31->5|31->5|32->6|32->6|35->1|38->8|43->13|43->13|45->15|45->15|46->16|46->16|48->18|48->18|49->19|49->19|52->22|52->22|53->23|53->23|55->25|55->25|65->35|65->35|65->35|67->37|67->37|67->37|71->41|71->41|71->41|75->45|75->45|76->46|76->46|77->47|77->47|78->48|78->48|79->49|79->49|80->50|80->50|81->51|81->51|82->52|82->52|83->53|83->53|84->54|84->54|85->55|85->55|86->56|86->56|90->60|90->60|90->60|92->62|92->62|92->62|93->63|93->63|93->63|94->64|94->64|94->64|95->65|95->65|95->65|96->66|96->66|96->66|97->67|97->67|97->67|97->67|97->67|97->67|98->68|98->68|98->68|99->69|99->69|99->69|100->70|100->70|100->70|100->70|100->70|100->70|101->71|101->71|101->71|102->72|102->72|102->72|103->73|103->73|103->73|105->75|108->78|114->84|114->84|114->84
                    -- GENERATED --
                */
            