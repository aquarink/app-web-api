
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
object summary extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[models.forms.CashInForm],org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(transactionForm: Form[models.forms.CashInForm], result: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.89*/("""

"""),_display_(Seq[Any](/*3.2*/if(result.path("status").getIntValue() != 0)/*3.46*/ {_display_(Seq[Any](format.raw/*3.48*/("""
<div class="alert alert-block alert-error">
	<strong>Transaction information failed</strong>
	<br/>
    """),_display_(Seq[Any](/*7.6*/result/*7.12*/.path("message").getTextValue())),format.raw/*7.43*/("""
</div>
""")))}/*9.3*/else/*9.8*/{_display_(Seq[Any](format.raw/*9.9*/("""
<div id="send-summary-inner" class="print">
    """),_display_(Seq[Any](/*11.6*/defining(result.path("inquiry"))/*11.38*/ { trx =>_display_(Seq[Any](format.raw/*11.47*/("""
		<div class="pull-right noprint">
			<!--<a href="#" class="btn-print" data-target="send-summary-inner"><i class="icon-print"></i></a>-->
		</div>
        <h6>Send Money Summary</h6>
        <dl class="dl-horizontal trx-summary">
            <dt>Sending country:</dt>
            <dd>"""),_display_(Seq[Any](/*18.18*/trx/*18.21*/.path("fund").path("origin").path("countryName").getTextValue())),format.raw/*18.84*/("""</dd>
            <dt>Receiving country:</dt>
            <dd>"""),_display_(Seq[Any](/*20.18*/trx/*20.21*/.path("fund").path("destination").path("countryName").getTextValue())),format.raw/*20.89*/("""</dd>
            <dt>Remit options:</dt>
            <dd>"""),_display_(Seq[Any](/*22.18*/trx/*22.21*/.path("channel").path("name").getTextValue())),format.raw/*22.65*/("""</dd>
            <hr/>

            """),_display_(Seq[Any](/*25.14*/defining(trx.path("forex"))/*25.41*/ { forex =>_display_(Seq[Any](format.raw/*25.52*/("""
                <dt>Rate: </dt>
                <dd>"""),_display_(Seq[Any](/*27.22*/models/*27.28*/.Forex.format(forex.path("rate").getDoubleValue()))),format.raw/*27.78*/("""</dd>
            """)))})),format.raw/*28.14*/("""
            """),_display_(Seq[Any](/*29.14*/defining(trx.path("fund"))/*29.40*/ { fund =>_display_(Seq[Any](format.raw/*29.50*/("""
                <dt>Send amount: </dt>
                <dd>"""),_display_(Seq[Any](/*31.22*/models/*31.28*/.Currency.format(fund.path("origin").path("amount").getNumberValue(), trx.path("fund").path("origin").path("currency").getTextValue()))),format.raw/*31.162*/(""" """),_display_(Seq[Any](/*31.164*/trx/*31.167*/.path("fund").path("origin").path("currency").getTextValue())),format.raw/*31.227*/("""</dd>
                <dt>Fee: </dt>
                <dd>"""),_display_(Seq[Any](/*33.22*/models/*33.28*/.Currency.format(fund.path("fees").path("total").getNumberValue(), trx.path("fund").path("fees").path("currency").getTextValue()))),format.raw/*33.157*/(""" """),_display_(Seq[Any](/*33.159*/trx/*33.162*/.path("fund").path("fees").path("currency").getTextValue())),format.raw/*33.220*/("""</dd>
                <dt>Total: </dt>
                """),_display_(Seq[Any](/*35.18*/defining(fund.path("origin").path("amount").getDoubleValue()+fund.path("fees").path("total").getDoubleValue())/*35.128*/ { total =>_display_(Seq[Any](format.raw/*35.139*/("""
                    <dd>"""),_display_(Seq[Any](/*36.26*/models/*36.32*/.Currency.format(total, trx.path("fund").path("origin").path("currency").getTextValue()))),format.raw/*36.120*/(""" """),_display_(Seq[Any](/*36.122*/trx/*36.125*/.path("fund").path("origin").path("currency").getTextValue())),format.raw/*36.185*/("""</dd>
                """)))})),format.raw/*37.18*/("""
                <dt>Receive amount: </dt>
                <dd>"""),_display_(Seq[Any](/*39.22*/models/*39.28*/.Currency.format(
                    fund.path("destination").path("amount").getDoubleValue(),
                    fund.path("destination").path("currency").getTextValue()
                ))),format.raw/*42.18*/("""
                """),_display_(Seq[Any](/*43.18*/trx/*43.21*/.path("fund").path("destination").path("currency").getTextValue())),format.raw/*43.86*/("""</dd>
            """)))})),format.raw/*44.14*/("""

			"""),_display_(Seq[Any](/*46.5*/if(transactionForm.get().sender.id != null)/*46.48*/ {_display_(Seq[Any](format.raw/*46.50*/("""
			""")))})),format.raw/*47.5*/("""
        </dl>
    """)))})),format.raw/*49.6*/("""
</div>
""")))})),format.raw/*51.2*/("""
"""))}
    }
    
    def render(transactionForm:Form[models.forms.CashInForm],result:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(transactionForm,result)
    
    def f:((Form[models.forms.CashInForm],org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (transactionForm,result) => apply(transactionForm,result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/summary.scala.html
                    HASH: 0dc109d927ebe06bd501157c00e8d0c994c180d3
                    MATRIX: 783->1|947->88|984->91|1036->135|1075->137|1215->243|1229->249|1281->280|1307->290|1318->295|1355->296|1440->346|1481->378|1528->387|1851->674|1863->677|1948->740|2047->803|2059->806|2149->874|2244->933|2256->936|2322->980|2396->1018|2432->1045|2481->1056|2571->1110|2586->1116|2658->1166|2709->1185|2759->1199|2794->1225|2842->1235|2939->1296|2954->1302|3111->1436|3150->1438|3163->1441|3246->1501|3340->1559|3355->1565|3507->1694|3546->1696|3559->1699|3640->1757|3732->1813|3852->1923|3902->1934|3964->1960|3979->1966|4090->2054|4129->2056|4142->2059|4225->2119|4280->2142|4380->2206|4395->2212|4607->2402|4661->2420|4673->2423|4760->2488|4811->2507|4852->2513|4904->2556|4944->2558|4980->2563|5031->2583|5071->2592
                    LINES: 26->1|29->1|31->3|31->3|31->3|35->7|35->7|35->7|37->9|37->9|37->9|39->11|39->11|39->11|46->18|46->18|46->18|48->20|48->20|48->20|50->22|50->22|50->22|53->25|53->25|53->25|55->27|55->27|55->27|56->28|57->29|57->29|57->29|59->31|59->31|59->31|59->31|59->31|59->31|61->33|61->33|61->33|61->33|61->33|61->33|63->35|63->35|63->35|64->36|64->36|64->36|64->36|64->36|64->36|65->37|67->39|67->39|70->42|71->43|71->43|71->43|72->44|74->46|74->46|74->46|75->47|77->49|79->51
                    -- GENERATED --
                */
            