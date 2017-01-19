
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
object summary extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(trx: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {import java.util._


Seq[Any](format.raw/*1.38*/("""

"""),format.raw/*4.1*/("""
<h6>Transaction Summary - <small>"""),_display_(Seq[Any](/*5.35*/trx/*5.38*/.path("idToken").getTextValue())),format.raw/*5.69*/("""</small></h6>
<dl class="dl-horizontal dl-aligned dl-aligned-right trx-summary">
    <dt>Sending Country:</dt>
    <dd>"""),_display_(Seq[Any](/*8.10*/trx/*8.13*/.path("fund").path("origin").path("country").path("name").getTextValue())),format.raw/*8.85*/("""</dd>

    <dt>Receiving Country:</dt>
    <dd>"""),_display_(Seq[Any](/*11.10*/trx/*11.13*/.path("fund").path("destination").path("country").path("name").getTextValue())),format.raw/*11.90*/("""</dd>

    <dt>Remit options:</dt>
    <dd>"""),_display_(Seq[Any](/*14.10*/trx/*14.13*/.path("channel").path("name").getTextValue())),format.raw/*14.57*/("""</dd>

	"""),_display_(Seq[Any](/*16.3*/defining(trx.path("fund").path("origin").path("amount").getNumberValue())/*16.76*/ { total =>_display_(Seq[Any](format.raw/*16.87*/("""
    <dt>Sending Amount</dt>
	<dd>"""),_display_(Seq[Any](/*18.7*/models/*18.13*/.Currency.format(total, trx.path("fund").path("origin").path("currency").getTextValue()))),format.raw/*18.101*/(""" """),_display_(Seq[Any](/*18.103*/trx/*18.106*/.path("fund").path("origin").path("currency").getTextValue())),format.raw/*18.166*/("""</dd>
	""")))})),format.raw/*19.3*/("""
	"""),_display_(Seq[Any](/*20.3*/defining(trx.path("fund").path("destination").path("amount").getNumberValue())/*20.81*/ { total =>_display_(Seq[Any](format.raw/*20.92*/("""
    <dt>Receiving Amount</dt>
	<dd>"""),_display_(Seq[Any](/*22.7*/models/*22.13*/.Currency.format(total, trx.path("fund").path("destination").path("currency").getTextValue()))),format.raw/*22.106*/(""" """),_display_(Seq[Any](/*22.108*/trx/*22.111*/.path("fund").path("destination").path("currency").getTextValue())),format.raw/*22.176*/("""</dd>
	""")))})),format.raw/*23.3*/("""
    <dt>Fee Included</dt>
    <dd>"""),_display_(Seq[Any](/*25.10*/if(trx.path("fund").path("fees").path("feeIncluded").getBooleanValue())/*25.81*/ {_display_(Seq[Any](format.raw/*25.83*/("""Yes""")))}/*25.88*/else/*25.93*/{_display_(Seq[Any](format.raw/*25.94*/("""No""")))})),format.raw/*25.97*/("""</dd>
	"""),format.raw/*33.5*/("""
    <!--dt>Trx Time:</dt>
    """),_display_(Seq[Any](/*35.6*/defining(new Date(trx.path("createdTime").getLongValue()))/*35.64*/ { trxTimeStr =>_display_(Seq[Any](format.raw/*35.80*/("""
    <dd>"""),_display_(Seq[Any](/*36.10*/trxTimeStr)),format.raw/*36.20*/("""</dd-->
    """)))})),format.raw/*37.6*/("""

    <dt>Fund source</dt>
    <dd>"""),_display_(Seq[Any](/*40.10*/trx/*40.13*/.path("fundSource").getTextValue())),format.raw/*40.47*/("""</dd>
    <dt>Send purpose</dt>
    <dd>"""),_display_(Seq[Any](/*42.10*/trx/*42.13*/.path("senderNote").getTextValue())),format.raw/*42.47*/("""</dd>

    """),_display_(Seq[Any](/*44.6*/defining(trx.path("sender"))/*44.34*/ { sender =>_display_(Seq[Any](format.raw/*44.46*/("""
        <hr/>
        <dt>Sender Member Id:</dt>
        <dd>"""),_display_(Seq[Any](/*47.14*/sender/*47.20*/.path("id").getTextValue())),format.raw/*47.46*/("""</dd>
        <dt>First Name: </dt>
        <dd>"""),_display_(Seq[Any](/*49.14*/sender/*49.20*/.path("firstName").getTextValue())),format.raw/*49.53*/("""</dd>
        <dt>Last Name: </dt>
        <dd>"""),_display_(Seq[Any](/*51.14*/sender/*51.20*/.path("lastName").getTextValue())),format.raw/*51.52*/("""</dd>
        <dt>Personal ID: </dt>
        <dd>"""),_display_(Seq[Any](/*53.14*/sender/*53.20*/.path("personalId").getTextValue())),format.raw/*53.54*/("""</dd>
        <dt>Gender: </dt>
        <dd>"""),_display_(Seq[Any](/*55.14*/sender/*55.20*/.path("gender").getTextValue())),format.raw/*55.50*/("""</dd>
        <dt>Date of Birth: </dt>
        <dd>"""),_display_(Seq[Any](/*57.14*/sender/*57.20*/.path("birthDate").getTextValue())),format.raw/*57.53*/("""</dd>
        <dt>Country: </dt>
        <dd>"""),_display_(Seq[Any](/*59.14*/sender/*59.20*/.path("country").path("name").getTextValue())),format.raw/*59.64*/("""</dd>
        <dt>City: </dt>
        <dd>"""),_display_(Seq[Any](/*61.14*/sender/*61.20*/.path("cityName").getTextValue())),format.raw/*61.52*/("""</dd>
        <dt>Phone Number: </dt>
        <dd>"""),_display_(Seq[Any](/*63.14*/sender/*63.20*/.path("phoneNumber").getTextValue())),format.raw/*63.55*/("""</dd>
        <dt>Postal Code: </dt>
        <dd>"""),_display_(Seq[Any](/*65.14*/sender/*65.20*/.path("postalCode").getTextValue())),format.raw/*65.54*/("""</dd>
        <dt>Email: </dt>
        <dd>"""),_display_(Seq[Any](/*67.14*/sender/*67.20*/.path("email").getTextValue())),format.raw/*67.49*/("""</dd>
    """)))})),format.raw/*68.6*/("""

    """),_display_(Seq[Any](/*70.6*/defining(trx.path("beneficiary"))/*70.39*/ { beneficiary =>_display_(Seq[Any](format.raw/*70.56*/("""
        <hr/>
        <dt>Receiver Member Id:</dt>
        <dd>"""),_display_(Seq[Any](/*73.14*/beneficiary/*73.25*/.path("id").getTextValue())),format.raw/*73.51*/("""</dd>
        <dt>First Name: </dt>
        <dd>"""),_display_(Seq[Any](/*75.14*/beneficiary/*75.25*/.path("firstName").getTextValue())),format.raw/*75.58*/("""</dd>
        <dt>Last Name: </dt>
        <dd>"""),_display_(Seq[Any](/*77.14*/beneficiary/*77.25*/.path("lastName").getTextValue())),format.raw/*77.57*/("""</dd>
        <dt>Personal ID: </dt>
        <dd>"""),_display_(Seq[Any](/*79.14*/beneficiary/*79.25*/.path("personalId").getTextValue())),format.raw/*79.59*/("""</dd>
        <dt>Gender: </dt>
        <dd>"""),_display_(Seq[Any](/*81.14*/beneficiary/*81.25*/.path("gender").getTextValue())),format.raw/*81.55*/("""</dd>
        <dt>Date of Birth: </dt>
        <dd>"""),_display_(Seq[Any](/*83.14*/beneficiary/*83.25*/.path("birthDate").getTextValue())),format.raw/*83.58*/("""</dd>
        <dt>Address: </dt>
        <dd>"""),_display_(Seq[Any](/*85.14*/beneficiary/*85.25*/.path("address").getTextValue())),format.raw/*85.56*/("""</dd>
        <dt>Country: </dt>
        <dd>"""),_display_(Seq[Any](/*87.14*/beneficiary/*87.25*/.path("country").path("name").getTextValue())),format.raw/*87.69*/("""</dd>
        <dt>City: </dt>
        <dd>"""),_display_(Seq[Any](/*89.14*/beneficiary/*89.25*/.path("cityName").getTextValue())),format.raw/*89.57*/("""</dd>
        <dt>Phone Number: </dt>
        <dd>"""),_display_(Seq[Any](/*91.14*/beneficiary/*91.25*/.path("phoneNumber").getTextValue())),format.raw/*91.60*/("""</dd>
        <dt>Postal Code: </dt>
        <dd>"""),_display_(Seq[Any](/*93.14*/beneficiary/*93.25*/.path("postalCode").getTextValue())),format.raw/*93.59*/("""</dd>
        <dt>Email: </dt>
        <dd>"""),_display_(Seq[Any](/*95.14*/beneficiary/*95.25*/.path("email").getTextValue())),format.raw/*95.54*/("""</dd>
    """)))})),format.raw/*96.6*/("""
</dl>
"""))}
    }
    
    def render(trx:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(trx)
    
    def f:((org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (trx) => apply(trx)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/summary.scala.html
                    HASH: e7c5d4aff430dbb5b11ddce578d8ac419dec0f62
                    MATRIX: 760->1|892->37|920->59|990->94|1001->97|1053->128|1208->248|1219->251|1312->323|1396->371|1408->374|1507->451|1587->495|1599->498|1665->542|1709->551|1791->624|1840->635|1910->670|1925->676|2036->764|2075->766|2088->769|2171->829|2210->837|2248->840|2335->918|2384->929|2456->966|2471->972|2587->1065|2626->1067|2639->1070|2727->1135|2766->1143|2838->1179|2918->1250|2958->1252|2981->1257|2994->1262|3033->1263|3068->1266|3102->1456|3169->1488|3236->1546|3290->1562|3336->1572|3368->1582|3412->1595|3484->1631|3496->1634|3552->1668|3629->1709|3641->1712|3697->1746|3744->1758|3781->1786|3831->1798|3930->1861|3945->1867|3993->1893|4078->1942|4093->1948|4148->1981|4232->2029|4247->2035|4301->2067|4387->2117|4402->2123|4458->2157|4539->2202|4554->2208|4606->2238|4694->2290|4709->2296|4764->2329|4846->2375|4861->2381|4927->2425|5006->2468|5021->2474|5075->2506|5162->2557|5177->2563|5234->2598|5320->2648|5335->2654|5391->2688|5471->2732|5486->2738|5537->2767|5579->2778|5621->2785|5663->2818|5718->2835|5819->2900|5839->2911|5887->2937|5972->2986|5992->2997|6047->3030|6131->3078|6151->3089|6205->3121|6291->3171|6311->3182|6367->3216|6448->3261|6468->3272|6520->3302|6608->3354|6628->3365|6683->3398|6765->3444|6785->3455|6838->3486|6920->3532|6940->3543|7006->3587|7085->3630|7105->3641|7159->3673|7246->3724|7266->3735|7323->3770|7409->3820|7429->3831|7485->3865|7565->3909|7585->3920|7636->3949|7678->3960
                    LINES: 26->1|30->1|32->4|33->5|33->5|33->5|36->8|36->8|36->8|39->11|39->11|39->11|42->14|42->14|42->14|44->16|44->16|44->16|46->18|46->18|46->18|46->18|46->18|46->18|47->19|48->20|48->20|48->20|50->22|50->22|50->22|50->22|50->22|50->22|51->23|53->25|53->25|53->25|53->25|53->25|53->25|53->25|54->33|56->35|56->35|56->35|57->36|57->36|58->37|61->40|61->40|61->40|63->42|63->42|63->42|65->44|65->44|65->44|68->47|68->47|68->47|70->49|70->49|70->49|72->51|72->51|72->51|74->53|74->53|74->53|76->55|76->55|76->55|78->57|78->57|78->57|80->59|80->59|80->59|82->61|82->61|82->61|84->63|84->63|84->63|86->65|86->65|86->65|88->67|88->67|88->67|89->68|91->70|91->70|91->70|94->73|94->73|94->73|96->75|96->75|96->75|98->77|98->77|98->77|100->79|100->79|100->79|102->81|102->81|102->81|104->83|104->83|104->83|106->85|106->85|106->85|108->87|108->87|108->87|110->89|110->89|110->89|112->91|112->91|112->91|114->93|114->93|114->93|116->95|116->95|116->95|117->96
                    -- GENERATED --
                */
            