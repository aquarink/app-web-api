
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
object admin_list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Report.FilterForm], currentPage: com.avaje.ebean.Page[models.Transaction], currentSortBy: String, currentOrder: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*29.2*/header/*29.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*29.38*/("""
<th class=""""),_display_(Seq[Any](/*30.13*/key)),format.raw/*30.16*/("""" nowrap>
	"""),_display_(Seq[Any](/*31.3*/if(key && key.length > 0)/*31.28*/ {_display_(Seq[Any](format.raw/*31.30*/("""
	<a href=""""),_display_(Seq[Any](/*32.12*/link(currentPage.getPageIndex, key))),format.raw/*32.47*/("""">"""),_display_(Seq[Any](/*32.50*/title)),format.raw/*32.55*/("""</a>
	""")))}/*33.4*/else/*33.9*/{_display_(Seq[Any](format.raw/*33.10*/("""
	"""),_display_(Seq[Any](/*34.3*/title)),format.raw/*34.8*/("""
	""")))})),format.raw/*35.3*/("""
</th>
""")))};def /*6.2*/link/*6.6*/(newPage:Int, newSortBy:String) = {{

    var sortBy = currentSortBy
    var order = currentOrder

    if(newSortBy != null) {
        sortBy = newSortBy
        if(currentSortBy == newSortBy) {
            if(currentOrder == "asc") {
                order = "desc"
            } else {
                order = "asc"
            }
        } else {
            order = "asc"
        }
    }

    routes.Transaction.admin_list(newPage, sortBy, order, "")

}};
Seq[Any](format.raw/*1.151*/("""
"""),format.raw/*4.1*/("""

"""),format.raw/*26.2*/("""


"""),format.raw/*37.2*/("""

"""),_display_(Seq[Any](/*39.2*/layout("Transactions management")/*39.35*/{_display_(Seq[Any](format.raw/*39.36*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*42.10*/doku/*42.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG269","Transactions Management"))),format.raw/*42.92*/("""</h1>
</div>

"""),_display_(Seq[Any](/*45.2*/partial/*45.9*/.flash_alert())),format.raw/*45.23*/("""

<div class="row">
	<div class="span12">
		<form action=""""),_display_(Seq[Any](/*49.18*/link(0, "name"))),format.raw/*49.33*/("""" method="GET" class="form-search">
			<div id="action" class="well">
                <div class="input-append">
					<input type="search" id="searchbox" name=""""),_display_(Seq[Any](/*52.49*/filterForm("transactionCode")/*52.78*/.name)),format.raw/*52.83*/("""" class="search-query span4" value=""""),_display_(Seq[Any](/*52.120*/filterForm("transactionCode")/*52.149*/.valueOr(""))),format.raw/*52.161*/("""" placeholder='"""),_display_(Seq[Any](/*52.177*/doku/*52.181*/.kirimdoku.util.MultiLanguage.getLanguage("LANG099","Search by Transaction ID / Agent ID..."))),format.raw/*52.274*/("""'><button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*52.339*/doku/*52.343*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*52.404*/("""</button>
				</div>
			</div>
		</form>
    </div>
</div>

<div class="row">
    <div class="span7">
		"""),_display_(Seq[Any](/*61.4*/if(currentPage.getTotalRowCount == 0)/*61.41*/{_display_(Seq[Any](format.raw/*61.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*63.9*/doku/*63.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG271","There are no transaction data to be displayed"))),format.raw/*63.113*/("""</em>
		</div>
		""")))}/*65.5*/else/*65.10*/{_display_(Seq[Any](format.raw/*65.11*/("""
		<table class="table transaction table-bordered table-striped table-condensed" style="text-align: center;">
			<thead>
			<tr>
				"""),_display_(Seq[Any](/*69.6*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID")))),format.raw/*69.93*/("""
				"""),_display_(Seq[Any](/*70.6*/header("cashInTime", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG101","Sending time")))),format.raw/*70.99*/("""
				"""),_display_(Seq[Any](/*71.6*/header("user", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG102","Agent ID")))),format.raw/*71.89*/("""
				"""),_display_(Seq[Any](/*72.6*/header("senderCountry.code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG103","Sender country")))),format.raw/*72.109*/("""
				"""),_display_(Seq[Any](/*73.6*/header("beneficiaryCountry.code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG104","Receiver country")))),format.raw/*73.116*/("""
				"""),_display_(Seq[Any](/*74.6*/header("status", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status")))),format.raw/*74.89*/("""
				<th colspan="2" style="text-align: center; vertical-align: middle;">"""),_display_(Seq[Any](/*75.74*/doku/*75.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action"))),format.raw/*75.139*/("""</th>
			</tr>
			</thead>
			<tbody>
			"""),_display_(Seq[Any](/*79.5*/for(transaction <- currentPage.getList) yield /*79.44*/ {_display_(Seq[Any](format.raw/*79.46*/("""
			<tr>
				<td>"""),_display_(Seq[Any](/*81.10*/transaction/*81.21*/.getIdToken)),format.raw/*81.32*/("""</td>
				<td nowrap="nowrap">"""),_display_(Seq[Any](/*82.26*/models/*82.32*/.Transaction.formatDate(transaction.cashInTime.getTime, true))),format.raw/*82.93*/("""</td>
				<td>"""),_display_(Seq[Any](/*83.10*/transaction/*83.21*/.agent.getIdToken)),format.raw/*83.38*/("""</td>
				<td>"""),_display_(Seq[Any](/*84.10*/transaction/*84.21*/.senderCountry.name)),format.raw/*84.40*/("""</td>
				<td>"""),_display_(Seq[Any](/*85.10*/transaction/*85.21*/.beneficiaryCountry.name)),format.raw/*85.45*/("""</td>
				<td>"""),_display_(Seq[Any](/*86.10*/transaction/*86.21*/.statusText)),format.raw/*86.32*/("""</td>
				<td class="actions">
					<a href=""""),_display_(Seq[Any](/*88.16*/routes/*88.22*/.Transaction.show(transaction.getIdToken))),format.raw/*88.63*/("""" class="btn-detail btn-transaction-detail" data-remote="true">"""),_display_(Seq[Any](/*88.127*/doku/*88.131*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*88.192*/("""</a>
				</td>
				<td class="actions">
					<a href='"""),_display_(Seq[Any](/*91.16*/routes/*91.22*/.Transaction.receipt(transaction.getIdToken, "t"))),format.raw/*91.71*/("""' class="btn-detail btn-transaction-detail">"""),_display_(Seq[Any](/*91.116*/doku/*91.120*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*91.180*/("""</a>
				</td>
			</tr>
			""")))})),format.raw/*94.5*/("""
			</tbody>
		</table>

		<div id="pagination" class="pagination pagination-right">
			<ul>
				"""),_display_(Seq[Any](/*100.6*/if(currentPage.hasPrev)/*100.29*/ {_display_(Seq[Any](format.raw/*100.31*/("""
				<li class="prev">
					<a href=""""),_display_(Seq[Any](/*102.16*/link(currentPage.getPageIndex - 1, null))),format.raw/*102.56*/("""">&larr; """),_display_(Seq[Any](/*102.66*/doku/*102.70*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*102.133*/("""</a>
				</li>
				""")))}/*104.7*/else/*104.12*/{_display_(Seq[Any](format.raw/*104.13*/("""
				<li class="prev">
					<a>&larr; """),_display_(Seq[Any](/*106.17*/doku/*106.21*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*106.84*/("""</a>
				</li>
				""")))})),format.raw/*108.6*/("""

				<li class="current">
					<a>Displaying """),_display_(Seq[Any](/*111.21*/currentPage/*111.32*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*111.66*/("""</a>
				</li>

				"""),_display_(Seq[Any](/*114.6*/if(currentPage.hasNext)/*114.29*/ {_display_(Seq[Any](format.raw/*114.31*/("""
				<li class="next">
					<a href=""""),_display_(Seq[Any](/*116.16*/link(currentPage.getPageIndex + 1, null))),format.raw/*116.56*/("""">"""),_display_(Seq[Any](/*116.59*/doku/*116.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*116.122*/(""" &rarr;</a>
				</li>
				""")))}/*118.7*/else/*118.12*/{_display_(Seq[Any](format.raw/*118.13*/("""
				<li class="next">
					<a>"""),_display_(Seq[Any](/*120.10*/doku/*120.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*120.73*/(""" &rarr;</a>
				</li>
				""")))})),format.raw/*122.6*/("""
			</ul>
		</div>
		""")))})),format.raw/*125.4*/("""
	</div>
	<div class="span5">
		<div id="transaction-detail-container" class="well">
		"""),_display_(Seq[Any](/*129.4*/doku/*129.8*/.kirimdoku.util.MultiLanguage.getLanguage("LANG274","Click view detail for transaction detail"))),format.raw/*129.103*/("""
		</div>
    </div>
    </div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*135.38*/routes/*135.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*135.84*/("""'></script>
""")))})),format.raw/*136.2*/("""
"""))}
    }
    
    def render(filterForm:Form[controllers.Report.FilterForm],currentPage:com.avaje.ebean.Page[models.Transaction],currentSortBy:String,currentOrder:String): play.api.templates.Html = apply(filterForm,currentPage,currentSortBy,currentOrder)
    
    def f:((Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String) => play.api.templates.Html) = (filterForm,currentPage,currentSortBy,currentOrder) => apply(filterForm,currentPage,currentSortBy,currentOrder)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/admin_list.scala.html
                    HASH: ff50ecccf6ea21a2179f60b7d4e86f7545e8405f
                    MATRIX: 824->1|1084->668|1098->674|1192->704|1241->717|1266->720|1313->732|1347->757|1387->759|1435->771|1492->806|1531->809|1558->814|1583->822|1595->827|1634->828|1672->831|1698->836|1732->839|1762->205|1773->209|2259->150|2286->202|2315->664|2345->847|2383->850|2425->883|2464->884|2537->921|2550->925|2650->1003|2700->1018|2715->1025|2751->1039|2846->1098|2883->1113|3080->1274|3118->1303|3145->1308|3219->1345|3258->1374|3293->1386|3346->1402|3360->1406|3476->1499|3578->1564|3592->1568|3676->1629|3816->1734|3862->1771|3901->1772|3966->1802|3979->1806|4102->1906|4138->1925|4151->1930|4190->1931|4359->2065|4468->2152|4509->2158|4624->2251|4665->2257|4770->2340|4811->2346|4937->2449|4978->2455|5111->2565|5152->2571|5257->2654|5367->2728|5380->2732|5464->2793|5541->2835|5596->2874|5636->2876|5690->2894|5710->2905|5743->2916|5810->2947|5825->2953|5908->3014|5959->3029|5979->3040|6018->3057|6069->3072|6089->3083|6130->3102|6181->3117|6201->3128|6247->3152|6298->3167|6318->3178|6351->3189|6433->3235|6448->3241|6511->3282|6612->3346|6626->3350|6710->3411|6801->3466|6816->3472|6887->3521|6969->3566|6983->3570|7066->3630|7125->3658|7259->3756|7292->3779|7333->3781|7408->3819|7471->3859|7518->3869|7532->3873|7619->3936|7658->3957|7672->3962|7712->3963|7788->4002|7802->4006|7888->4069|7940->4089|8024->4136|8045->4147|8102->4181|8159->4202|8192->4225|8233->4227|8308->4265|8371->4305|8411->4308|8425->4312|8508->4371|8554->4399|8568->4404|8608->4405|8677->4437|8691->4441|8773->4500|8832->4527|8886->4549|9010->4637|9023->4641|9142->4736|9256->4813|9272->4819|9335->4859|9380->4872
                    LINES: 26->1|31->29|31->29|33->29|34->30|34->30|35->31|35->31|35->31|36->32|36->32|36->32|36->32|37->33|37->33|37->33|38->34|38->34|39->35|41->6|41->6|62->1|63->4|65->26|68->37|70->39|70->39|70->39|73->42|73->42|73->42|76->45|76->45|76->45|80->49|80->49|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|92->61|92->61|92->61|94->63|94->63|94->63|96->65|96->65|96->65|100->69|100->69|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|105->74|105->74|106->75|106->75|106->75|110->79|110->79|110->79|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|119->88|119->88|119->88|119->88|119->88|119->88|122->91|122->91|122->91|122->91|122->91|122->91|125->94|131->100|131->100|131->100|133->102|133->102|133->102|133->102|133->102|135->104|135->104|135->104|137->106|137->106|137->106|139->108|142->111|142->111|142->111|145->114|145->114|145->114|147->116|147->116|147->116|147->116|147->116|149->118|149->118|149->118|151->120|151->120|151->120|153->122|156->125|160->129|160->129|160->129|166->135|166->135|166->135|167->136
                    -- GENERATED --
                */
            