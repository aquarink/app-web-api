
package views.html.dashboard

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
object statistics_with_graph extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[models.User,List[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user:models.User, latestTransactions: List[models.Transaction]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.66*/("""

<div>
	<h3>Latest transactions</h3>
	<div>
		"""),_display_(Seq[Any](/*6.4*/if(latestTransactions.isEmpty)/*6.34*/ {_display_(Seq[Any](format.raw/*6.36*/("""
		<div class="alert alert-message">No transactions data could be found</div>
		""")))}/*8.5*/else/*8.10*/{_display_(Seq[Any](format.raw/*8.11*/("""
		<table class="top-transaction table table-bordered table-striped table-condensed">
			<thead>
				<tr>

					<th style="text-align: center;">"""),_display_(Seq[Any](/*13.39*/doku/*13.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*13.112*/("""</th>
					<th style="text-align: center;">"""),_display_(Seq[Any](/*14.39*/doku/*14.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG103","Sender Country"))),format.raw/*14.112*/("""</th>
					<th style="text-align: center;">"""),_display_(Seq[Any](/*15.39*/doku/*15.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"))),format.raw/*15.113*/("""</th>
					<th style="text-align: center;">"""),_display_(Seq[Any](/*16.39*/doku/*16.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG028","Send amount"))),format.raw/*16.109*/("""</th>
					<th style="text-align: center;">"""),_display_(Seq[Any](/*17.39*/doku/*17.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount"))),format.raw/*17.112*/("""</th>
					<th style="text-align: center;">"""),_display_(Seq[Any](/*18.39*/doku/*18.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG029","Date"))),format.raw/*18.102*/("""</th>

				</tr>
			</thead>
			<tbody>
			"""),_display_(Seq[Any](/*23.5*/for(t <- latestTransactions) yield /*23.33*/{_display_(Seq[Any](format.raw/*23.34*/("""
				<tr>
					<td>"""),_display_(Seq[Any](/*25.11*/t/*25.12*/.getIdToken)),format.raw/*25.23*/("""</td>
					<td>"""),_display_(Seq[Any](/*26.11*/t/*26.12*/.senderCountry.name)),format.raw/*26.31*/("""</td>
					<td>"""),_display_(Seq[Any](/*27.11*/t/*27.12*/.beneficiaryCountry.name)),format.raw/*27.36*/("""</td>
					<td class="right">"""),_display_(Seq[Any](/*28.25*/t/*28.26*/.senderAmountFormat)),format.raw/*28.45*/("""</td>
					<td class="right">"""),_display_(Seq[Any](/*29.25*/t/*29.26*/.beneficiaryAmountFormat)),format.raw/*29.50*/("""</td>
					<td>"""),_display_(Seq[Any](/*30.11*/t/*30.12*/.cashInTime.getTime().format("dd-MMM-yyyy"))),format.raw/*30.55*/("""</td>
				</tr>
			""")))})),format.raw/*32.5*/("""
			</tbody>
		</table>
		""")))})),format.raw/*35.4*/("""
	</div>
</div>
<div class="row-fluid visible-desktop">
	<div class="span12">
		<div class="graph box box-bordered" data-graphType="performances" data-userId=""""),_display_(Seq[Any](/*40.83*/user/*40.87*/.id)),format.raw/*40.90*/("""">
			<h4>Transaction Performance</h4>
			<div id="chart1">
				<svg style="height: 200px;"></svg>
			</div>
		</div>
		<div class="graph box box-bordered" data-graphType="volumes" data-userId=""""),_display_(Seq[Any](/*46.78*/user/*46.82*/.id)),format.raw/*46.85*/("""">
			<h4>Volume Performance</h4>
			<div id="chart2">
				<svg style="height: 200px;"></svg>
			</div>
		</div>
	</div>
</div>



<script src=""""),_display_(Seq[Any](/*57.15*/routes/*57.21*/.Assets.at("javascripts/nvd3/lib/d3.v2.js"))),format.raw/*57.64*/(""""></script>
<script src=""""),_display_(Seq[Any](/*58.15*/routes/*58.21*/.Assets.at("javascripts/nvd3/nv.d3.js"))),format.raw/*58.60*/(""""></script>
<!--script src=""""),_display_(Seq[Any](/*59.18*/routes/*59.24*/.Assets.at("javascripts/nvd3/src/tooltip.js"))),format.raw/*59.69*/(""""></script-->
<script src=""""),_display_(Seq[Any](/*60.15*/routes/*60.21*/.Assets.at("javascripts/nvd3/src/utils.js"))),format.raw/*60.64*/(""""></script>
<script src=""""),_display_(Seq[Any](/*61.15*/routes/*61.21*/.Assets.at("javascripts/nvd3/src/models/legend.js"))),format.raw/*61.72*/(""""></script>
<script src=""""),_display_(Seq[Any](/*62.15*/routes/*62.21*/.Assets.at("javascripts/nvd3/src/models/axis.js"))),format.raw/*62.70*/(""""></script>
<script src=""""),_display_(Seq[Any](/*63.15*/routes/*63.21*/.Assets.at("javascripts/nvd3/src/models/scatter.js"))),format.raw/*63.73*/(""""></script>
<script src=""""),_display_(Seq[Any](/*64.15*/routes/*64.21*/.Assets.at("javascripts/nvd3/src/models/line.js"))),format.raw/*64.70*/(""""></script>
<script src=""""),_display_(Seq[Any](/*65.15*/routes/*65.21*/.Assets.at("javascripts/nvd3/src/models/lineChart.js"))),format.raw/*65.75*/(""""></script>
<script src=""""),_display_(Seq[Any](/*66.15*/routes/*66.21*/.Assets.at("javascripts/dashboard.statistics.js"))),format.raw/*66.70*/(""""></script>
"""))}
    }
    
    def render(user:models.User,latestTransactions:List[models.Transaction]): play.api.templates.Html = apply(user,latestTransactions)
    
    def f:((models.User,List[models.Transaction]) => play.api.templates.Html) = (user,latestTransactions) => apply(user,latestTransactions)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/dashboard/statistics_with_graph.scala.html
                    HASH: 0b61f4ca40edd2065a16a90a4704fb861723b2ed
                    MATRIX: 779->1|920->65|1002->113|1040->143|1079->145|1177->227|1189->232|1227->233|1408->378|1421->382|1513->451|1593->495|1606->499|1698->568|1778->612|1791->616|1884->686|1964->730|1977->734|2066->800|2146->844|2159->848|2251->917|2331->961|2344->965|2426->1024|2505->1068|2549->1096|2588->1097|2644->1117|2654->1118|2687->1129|2739->1145|2749->1146|2790->1165|2842->1181|2852->1182|2898->1206|2964->1236|2974->1237|3015->1256|3081->1286|3091->1287|3137->1311|3189->1327|3199->1328|3264->1371|3315->1391|3373->1418|3569->1578|3582->1582|3607->1585|3838->1780|3851->1784|3876->1787|4057->1932|4072->1938|4137->1981|4199->2007|4214->2013|4275->2052|4340->2081|4355->2087|4422->2132|4486->2160|4501->2166|4566->2209|4628->2235|4643->2241|4716->2292|4778->2318|4793->2324|4864->2373|4926->2399|4941->2405|5015->2457|5077->2483|5092->2489|5163->2538|5225->2564|5240->2570|5316->2624|5378->2650|5393->2656|5464->2705
                    LINES: 26->1|29->1|34->6|34->6|34->6|36->8|36->8|36->8|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|51->23|51->23|51->23|53->25|53->25|53->25|54->26|54->26|54->26|55->27|55->27|55->27|56->28|56->28|56->28|57->29|57->29|57->29|58->30|58->30|58->30|60->32|63->35|68->40|68->40|68->40|74->46|74->46|74->46|85->57|85->57|85->57|86->58|86->58|86->58|87->59|87->59|87->59|88->60|88->60|88->60|89->61|89->61|89->61|90->62|90->62|90->62|91->63|91->63|91->63|92->64|92->64|92->64|93->65|93->65|93->65|94->66|94->66|94->66
                    -- GENERATED --
                */
            