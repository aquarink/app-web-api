
package views.html.tools

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
object check_rate_result extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[models.forms.CashInInquiryForm],org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(fxForm: Form[models.forms.CashInInquiryForm], result: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._


Seq[Any](format.raw/*1.87*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Rate result")/*5.23*/ {_display_(Seq[Any](format.raw/*5.25*/("""
"""),_display_(Seq[Any](/*6.2*/if(result.path("status").getIntValue() != 0)/*6.46*/ {_display_(Seq[Any](format.raw/*6.48*/("""
<div class="row-fluid">
	<div class="alert alert-error">
		"""),_display_(Seq[Any](/*9.4*/result/*9.10*/.path("message").getTextValue())),format.raw/*9.41*/("""
	</div>
</div>
""")))}/*12.3*/else/*12.8*/{_display_(Seq[Any](format.raw/*12.9*/("""
"""),_display_(Seq[Any](/*13.2*/defining(result.path("inquiry"))/*13.34*/ { inquiry =>_display_(Seq[Any](format.raw/*13.47*/("""
<div class="row-fluid">
    <div class="span12">
        """),_display_(Seq[Any](/*16.10*/defining(inquiry.path("forexReference"))/*16.50*/ { forex =>_display_(Seq[Any](format.raw/*16.61*/("""

        <h4>"""),_display_(Seq[Any](/*18.14*/doku/*18.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG241","Summary"))),format.raw/*18.80*/("""</h4>
        <table class="table">
            <tr>
                <td width="70%">1 """),_display_(Seq[Any](/*21.36*/forex/*21.41*/.path("forex").path("origin").path("code").getTextValue())),format.raw/*21.98*/("""</td>
                <td class="right"><strong>"""),_display_(Seq[Any](/*22.44*/models/*22.50*/.Forex.format(forex.path("rate").getDoubleValue()))),format.raw/*22.100*/("""</strong></td>
                <td>"""),_display_(Seq[Any](/*23.22*/forex/*23.27*/.path("forex").path("destination").path("code").getTextValue())),format.raw/*23.89*/("""</td>
            </tr>
        </table>
        """)))})),format.raw/*26.10*/("""

        """),_display_(Seq[Any](/*28.10*/defining(inquiry.path("fund"))/*28.40*/ { fund =>_display_(Seq[Any](format.raw/*28.50*/("""
        <table class="table">
			<tr>
                <td>"""),_display_(Seq[Any](/*31.22*/doku/*31.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG028","Send amount"))),format.raw/*31.92*/("""</td>
                <td class="right">"""),_display_(Seq[Any](/*32.36*/models/*32.42*/.Currency.format(fund.path("origin").path("amount").getNumberValue(), fund.path("fees").path("currency").getTextValue()))),format.raw/*32.162*/("""</td>
                <td>"""),_display_(Seq[Any](/*33.22*/fund/*33.26*/.path("origin").path("currency").getTextValue())),format.raw/*33.73*/("""</td>
            </tr>
            <tr class="bordered">
                <td>"""),_display_(Seq[Any](/*36.22*/doku/*36.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"))),format.raw/*36.84*/("""</td>
                <td class="right">"""),_display_(Seq[Any](/*37.36*/models/*37.42*/.Currency.format(fund.path("fees").path("total").getNumberValue(), fund.path("fees").path("currency").getTextValue()))),format.raw/*37.159*/("""</td>
                <td>"""),_display_(Seq[Any](/*38.22*/fund/*38.26*/.path("fees").path("currency").getTextValue())),format.raw/*38.71*/("""</td>
            </tr>
			<tr class="bordered highlight">
                <td>"""),_display_(Seq[Any](/*41.22*/doku/*41.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount"))),format.raw/*41.95*/("""</td>
                <td class="right">"""),_display_(Seq[Any](/*42.36*/models/*42.42*/.Currency.format(fund.path("destination").path("amount").getDoubleValue(), fund.path("destination").path("currency").getTextValue()))),format.raw/*42.174*/("""</td>
                <td>"""),_display_(Seq[Any](/*43.22*/fund/*43.26*/.path("destination").path("currency").getTextValue())),format.raw/*43.78*/("""</td>
            </tr>
        </table>
        """)))})),format.raw/*46.10*/("""
    </div>
</div>

<div class="row-fluid form-controls">
    <div class="span12 right">
		"""),_display_(Seq[Any](/*52.4*/dynamic("sendTransaction", inquiry.toString())/*52.50*/ {_display_(Seq[Any](format.raw/*52.52*/("""
		<a class="btn btn-primary" href=""""),_display_(Seq[Any](/*53.37*/routes/*53.43*/.Send.create())),format.raw/*53.57*/("""?senderCountry.code="""),_display_(Seq[Any](/*53.78*/(fxForm.get().senderCountry.code))),format.raw/*53.111*/("""&senderCurrency.code="""),_display_(Seq[Any](/*53.133*/(fxForm.get().senderCurrency.code))),format.raw/*53.167*/("""&senderAmount="""),_display_(Seq[Any](/*53.182*/(fxForm.get().senderAmount))),format.raw/*53.209*/("""&beneficiaryCountry.code="""),_display_(Seq[Any](/*53.235*/(fxForm.get().beneficiaryCountry.code))),format.raw/*53.273*/("""&beneficiaryCurrency.code="""),_display_(Seq[Any](/*53.300*/(fxForm.get().beneficiaryCurrency.code))),format.raw/*53.339*/("""">"""),_display_(Seq[Any](/*53.342*/doku/*53.346*/.kirimdoku.util.MultiLanguage.getLanguage("LANG009","Send Money"))),format.raw/*53.411*/(""" <i class="icon-white icon-arrow-right"></i></a>
		""")))})),format.raw/*54.4*/("""
    </div>
</div>
""")))})),format.raw/*57.2*/("""

""")))})),format.raw/*59.2*/("""
""")))})),format.raw/*60.2*/("""
"""))}
    }
    
    def render(fxForm:Form[models.forms.CashInInquiryForm],result:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(fxForm,result)
    
    def f:((Form[models.forms.CashInInquiryForm],org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (fxForm,result) => apply(fxForm,result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/check_rate_result.scala.html
                    HASH: 95ce60e5ac0803a927597971157f0e4877b8240c
                    MATRIX: 801->1|1067->86|1094->192|1130->194|1159->215|1198->217|1234->219|1286->263|1325->265|1420->326|1434->332|1486->363|1521->381|1533->386|1571->387|1608->389|1649->421|1700->434|1795->493|1844->533|1893->544|1944->559|1957->563|2041->625|2165->713|2179->718|2258->775|2343->824|2358->830|2431->880|2503->916|2517->921|2601->983|2683->1033|2730->1044|2769->1074|2817->1084|2913->1144|2926->1148|3014->1214|3091->1255|3106->1261|3249->1381|3312->1408|3325->1412|3394->1459|3509->1538|3522->1542|3602->1600|3679->1641|3694->1647|3834->1764|3897->1791|3910->1795|3977->1840|4093->1920|4106->1924|4197->1993|4274->2034|4289->2040|4444->2172|4507->2199|4520->2203|4594->2255|4676->2305|4803->2397|4858->2443|4898->2445|4971->2482|4986->2488|5022->2502|5079->2523|5135->2556|5194->2578|5251->2612|5303->2627|5353->2654|5416->2680|5477->2718|5541->2745|5603->2784|5643->2787|5657->2791|5745->2856|5828->2908|5879->2928|5913->2931|5946->2933
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|35->6|35->6|35->6|38->9|38->9|38->9|41->12|41->12|41->12|42->13|42->13|42->13|45->16|45->16|45->16|47->18|47->18|47->18|50->21|50->21|50->21|51->22|51->22|51->22|52->23|52->23|52->23|55->26|57->28|57->28|57->28|60->31|60->31|60->31|61->32|61->32|61->32|62->33|62->33|62->33|65->36|65->36|65->36|66->37|66->37|66->37|67->38|67->38|67->38|70->41|70->41|70->41|71->42|71->42|71->42|72->43|72->43|72->43|75->46|81->52|81->52|81->52|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|82->53|83->54|86->57|88->59|89->60
                    -- GENERATED --
                */
            