
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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Report.FilterForm], currentPage: com.avaje.ebean.Page[models.Transaction], currentSortBy: String, currentOrder: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*29.2*/header/*29.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*29.38*/("""
<th class=""""),_display_(Seq[Any](/*30.13*/key)),format.raw/*30.16*/("""" style="vertical-align: middle;">
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

    routes.Transaction.list(newPage, sortBy, order, "")

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
					<input type="search" id="searchbox" name=""""),_display_(Seq[Any](/*52.49*/filterForm("transactionCode")/*52.78*/.name)),format.raw/*52.83*/("""" class="search-query span4" value=""""),_display_(Seq[Any](/*52.120*/filterForm("transactionCode")/*52.149*/.valueOr(""))),format.raw/*52.161*/("""" placeholder='"""),_display_(Seq[Any](/*52.177*/doku/*52.181*/.kirimdoku.util.MultiLanguage.getLanguage("LANG521","Search by Transaction ID..."))),format.raw/*52.263*/("""'><button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*52.328*/doku/*52.332*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*52.393*/("""</button>
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
			"""),_display_(Seq[Any](/*129.5*/doku/*129.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG274","Click view detail for transaction detail"))),format.raw/*129.104*/("""
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
                    DATE: Wed Jan 18 18:12:32 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/list.scala.html
                    HASH: 0a1f27ab7d306c201935b74ac1f5d92b8bda1700
                    MATRIX: 818->1|1078->662|1092->668|1186->698|1235->711|1260->714|1332->751|1366->776|1406->778|1454->790|1511->825|1550->828|1577->833|1602->841|1614->846|1653->847|1691->850|1717->855|1751->858|1781->205|1792->209|2272->150|2299->202|2328->658|2358->866|2396->869|2438->902|2477->903|2550->940|2563->944|2663->1022|2713->1037|2728->1044|2764->1058|2859->1117|2896->1132|3093->1293|3131->1322|3158->1327|3232->1364|3271->1393|3306->1405|3359->1421|3373->1425|3478->1507|3580->1572|3594->1576|3678->1637|3818->1742|3864->1779|3903->1780|3968->1810|3981->1814|4104->1914|4140->1933|4153->1938|4192->1939|4361->2073|4470->2160|4511->2166|4626->2259|4667->2265|4772->2348|4813->2354|4939->2457|4980->2463|5113->2573|5154->2579|5259->2662|5369->2736|5382->2740|5466->2801|5543->2843|5598->2882|5638->2884|5692->2902|5712->2913|5745->2924|5812->2955|5827->2961|5910->3022|5961->3037|5981->3048|6020->3065|6071->3080|6091->3091|6132->3110|6183->3125|6203->3136|6249->3160|6300->3175|6320->3186|6353->3197|6435->3243|6450->3249|6513->3290|6614->3354|6628->3358|6712->3419|6803->3474|6818->3480|6889->3529|6971->3574|6985->3578|7068->3638|7127->3666|7261->3764|7294->3787|7335->3789|7410->3827|7473->3867|7520->3877|7534->3881|7621->3944|7660->3965|7674->3970|7714->3971|7790->4010|7804->4014|7890->4077|7942->4097|8026->4144|8047->4155|8104->4189|8161->4210|8194->4233|8235->4235|8310->4273|8373->4313|8413->4316|8427->4320|8510->4379|8556->4407|8570->4412|8610->4413|8679->4445|8693->4449|8775->4508|8834->4535|8888->4557|9013->4646|9026->4650|9145->4745|9259->4822|9275->4828|9338->4868|9383->4881
                    LINES: 26->1|31->29|31->29|33->29|34->30|34->30|35->31|35->31|35->31|36->32|36->32|36->32|36->32|37->33|37->33|37->33|38->34|38->34|39->35|41->6|41->6|62->1|63->4|65->26|68->37|70->39|70->39|70->39|73->42|73->42|73->42|76->45|76->45|76->45|80->49|80->49|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|92->61|92->61|92->61|94->63|94->63|94->63|96->65|96->65|96->65|100->69|100->69|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|105->74|105->74|106->75|106->75|106->75|110->79|110->79|110->79|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|119->88|119->88|119->88|119->88|119->88|119->88|122->91|122->91|122->91|122->91|122->91|122->91|125->94|131->100|131->100|131->100|133->102|133->102|133->102|133->102|133->102|135->104|135->104|135->104|137->106|137->106|137->106|139->108|142->111|142->111|142->111|145->114|145->114|145->114|147->116|147->116|147->116|147->116|147->116|149->118|149->118|149->118|151->120|151->120|151->120|153->122|156->125|160->129|160->129|160->129|166->135|166->135|166->135|167->136
                    -- GENERATED --
                */
            