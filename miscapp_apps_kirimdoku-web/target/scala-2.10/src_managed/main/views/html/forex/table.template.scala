
package views.html.forex

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
object table extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[models.Corporate,List[models.Currency],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate: models.Corporate, currencies: List[models.Currency]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*31.2*/forexRate/*31.11*/(origin: models.Currency, destination: models.Currency):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*31.70*/("""
"""),_display_(Seq[Any](/*32.2*/if(origin.equals(destination))/*32.32*/ {_display_(Seq[Any](format.raw/*32.34*/("""
	-
""")))}/*34.3*/else/*34.8*/{_display_(Seq[Any](format.raw/*34.9*/("""
"""),_display_(Seq[Any](/*35.2*/defining(models.Forex.findLastForexReference(corporate, origin, destination))/*35.79*/ { (forexRef) =>_display_(Seq[Any](format.raw/*35.95*/("""
"""),_display_(Seq[Any](/*36.2*/if(forexRef != null)/*36.22*/ {_display_(Seq[Any](format.raw/*36.24*/("""
<span title=""""),_display_(Seq[Any](/*37.15*/origin/*37.21*/.code)),format.raw/*37.26*/(""" to """),_display_(Seq[Any](/*37.31*/destination/*37.42*/.code)),format.raw/*37.47*/("""">
	"""),_display_(Seq[Any](/*38.3*/forexRef/*38.11*/.rateFormat)),format.raw/*38.22*/("""
</span>
""")))}/*40.3*/else/*40.8*/{_display_(Seq[Any](format.raw/*40.9*/("""

""")))})),format.raw/*42.2*/("""
""")))})),format.raw/*43.2*/("""
""")))})),format.raw/*44.2*/("""
""")))};
Seq[Any](format.raw/*1.66*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Exchange rates")/*6.26*/ {_display_(Seq[Any](format.raw/*6.28*/("""

<table class="table table-forexs">
	<thead>
		<tr>
			<th>&nbsp;</th>
			"""),_display_(Seq[Any](/*12.5*/for(currency <- currencies) yield /*12.32*/ {_display_(Seq[Any](format.raw/*12.34*/("""
			<th>"""),_display_(Seq[Any](/*13.9*/currency/*13.17*/.code)),format.raw/*13.22*/("""</th>
			""")))})),format.raw/*14.5*/("""
		</tr>
	</thead>
	<tbody>
	"""),_display_(Seq[Any](/*18.3*/for(origin <- currencies) yield /*18.28*/ {_display_(Seq[Any](format.raw/*18.30*/("""
		<tr>
			<td>"""),_display_(Seq[Any](/*20.9*/origin/*20.15*/.code)),format.raw/*20.20*/("""</td>
			"""),_display_(Seq[Any](/*21.5*/for((destination, index) <- currencies.zipWithIndex) yield /*21.57*/ {_display_(Seq[Any](format.raw/*21.59*/("""
			<td>"""),_display_(Seq[Any](/*22.9*/forexRate(origin, destination))),format.raw/*22.39*/("""</td>
			""")))})),format.raw/*23.5*/("""
		</tr>
	""")))})),format.raw/*25.3*/("""
	</tbody>
</table>
""")))})),format.raw/*28.2*/("""


"""),format.raw/*45.2*/("""
"""))}
    }
    
    def render(corporate:models.Corporate,currencies:List[models.Currency]): play.api.templates.Html = apply(corporate,currencies)
    
    def f:((models.Corporate,List[models.Currency]) => play.api.templates.Html) = (corporate,currencies) => apply(corporate,currencies)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/forex/table.scala.html
                    HASH: 5909912776b3e8531716e6668076c45a99c46a91
                    MATRIX: 761->1|936->516|954->525|1077->584|1114->586|1153->616|1193->618|1216->624|1228->629|1266->630|1303->632|1389->709|1443->725|1480->727|1509->747|1549->749|1600->764|1615->770|1642->775|1683->780|1703->791|1730->796|1770->801|1787->809|1820->820|1848->831|1860->836|1898->837|1932->840|1965->842|1998->844|2039->65|2067->118|2103->120|2135->144|2174->146|2285->222|2328->249|2368->251|2412->260|2429->268|2456->273|2497->283|2562->313|2603->338|2643->340|2694->356|2709->362|2736->367|2781->377|2849->429|2889->431|2933->440|2985->470|3026->480|3068->491|3120->512|3150->846
                    LINES: 26->1|31->31|31->31|33->31|34->32|34->32|34->32|36->34|36->34|36->34|37->35|37->35|37->35|38->36|38->36|38->36|39->37|39->37|39->37|39->37|39->37|39->37|40->38|40->38|40->38|42->40|42->40|42->40|44->42|45->43|46->44|48->1|50->5|51->6|51->6|51->6|57->12|57->12|57->12|58->13|58->13|58->13|59->14|63->18|63->18|63->18|65->20|65->20|65->20|66->21|66->21|66->21|67->22|67->22|68->23|70->25|73->28|76->45
                    -- GENERATED --
                */
            