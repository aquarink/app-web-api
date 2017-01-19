
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
object listBatchTransaction extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String,Int,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Report.FilterForm], currentPage: com.avaje.ebean.Page[models.Transaction], currentSortBy: String, currentOrder: String, batchId:Int):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*29.2*/header/*29.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*29.38*/("""
<th class=""""),_display_(Seq[Any](/*30.13*/key)),format.raw/*30.16*/("""">
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

    routes.Transaction.showBatchDetailTransaction(newPage, sortBy, order, "", batchId)

}};
Seq[Any](format.raw/*1.164*/("""
"""),format.raw/*4.1*/("""

"""),format.raw/*26.2*/("""


"""),format.raw/*37.2*/("""

"""),_display_(Seq[Any](/*39.2*/layout("Batch Transactions management")/*39.41*/{_display_(Seq[Any](format.raw/*39.42*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*42.10*/doku/*42.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG515","Batch Transactions management"))),format.raw/*42.98*/("""</h1>
</div>

"""),_display_(Seq[Any](/*45.2*/partial/*45.9*/.flash_alert())),format.raw/*45.23*/("""

<div class="row">
	<div class="span12">
		<form action=""""),_display_(Seq[Any](/*49.18*/link(0, "name"))),format.raw/*49.33*/("""" method="GET" class="form-search">
			<div id="action" class="well">
                <div class="input-append">
					<input type="search" id="searchbox" name=""""),_display_(Seq[Any](/*52.49*/filterForm("transactionCode")/*52.78*/.name)),format.raw/*52.83*/("""" class="search-query span4" value=""""),_display_(Seq[Any](/*52.120*/filterForm("transactionCode")/*52.149*/.valueOr(""))),format.raw/*52.161*/("""" placeholder='"""),_display_(Seq[Any](/*52.177*/doku/*52.181*/.kirimdoku.util.MultiLanguage.getLanguage("LANG099","Search by Transaction ID / Agent ID..."))),format.raw/*52.274*/("""'><button type="submit" class="btn" style="border-radius:0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*52.366*/doku/*52.370*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*52.431*/("""</button>
					<a class="btn" href=""""),_display_(Seq[Any](/*53.28*/routes/*53.34*/.Transaction.listBatchInquiry())),format.raw/*53.65*/("""">"""),_display_(Seq[Any](/*53.68*/doku/*53.72*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*53.131*/("""</a>
				</div>
			</div>
		</form>
    </div>
</div>

<div class="row">
    <div class="span7">
		"""),_display_(Seq[Any](/*62.4*/if(currentPage.getTotalRowCount == 0)/*62.41*/{_display_(Seq[Any](format.raw/*62.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*64.9*/doku/*64.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG271","There are no transaction data to be displayed"))),format.raw/*64.113*/("""</em>
		</div>
		""")))}/*66.5*/else/*66.10*/{_display_(Seq[Any](format.raw/*66.11*/("""
		<table class="table transaction table-bordered table-striped table-condensed" style="text-align: center;">
			<thead>
			<tr>
				"""),_display_(Seq[Any](/*70.6*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID")))),format.raw/*70.93*/("""
				"""),_display_(Seq[Any](/*71.6*/header("cashInTime", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG101","Sending time")))),format.raw/*71.99*/("""
				"""),_display_(Seq[Any](/*72.6*/header("user", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG102","Agent ID")))),format.raw/*72.89*/("""
				"""),_display_(Seq[Any](/*73.6*/header("senderCountry.code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG103","Sender country")))),format.raw/*73.109*/("""
				"""),_display_(Seq[Any](/*74.6*/header("beneficiaryCountry.code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG104","Receiver country")))),format.raw/*74.116*/("""
				"""),_display_(Seq[Any](/*75.6*/header("status", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status")))),format.raw/*75.89*/("""
				<th colspan="2" style="text-align: center;">"""),_display_(Seq[Any](/*76.50*/doku/*76.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action"))),format.raw/*76.115*/("""</th>
			</tr>
			</thead>
			<tbody>
			"""),_display_(Seq[Any](/*80.5*/for(transaction <- currentPage.getList) yield /*80.44*/ {_display_(Seq[Any](format.raw/*80.46*/("""
			<tr>
				<td>"""),_display_(Seq[Any](/*82.10*/transaction/*82.21*/.getIdToken)),format.raw/*82.32*/("""</td>
				<td nowrap="nowrap">"""),_display_(Seq[Any](/*83.26*/models/*83.32*/.Transaction.formatDate(transaction.cashInTime.getTime, true))),format.raw/*83.93*/("""</td>
				<td>"""),_display_(Seq[Any](/*84.10*/transaction/*84.21*/.agent.getIdToken)),format.raw/*84.38*/("""</td>
				<td>"""),_display_(Seq[Any](/*85.10*/transaction/*85.21*/.senderCountry.name)),format.raw/*85.40*/("""</td>
				<td>"""),_display_(Seq[Any](/*86.10*/transaction/*86.21*/.beneficiaryCountry.name)),format.raw/*86.45*/("""</td>
				<td>"""),_display_(Seq[Any](/*87.10*/transaction/*87.21*/.statusText)),format.raw/*87.32*/("""</td>
				<td class="actions">
					<a href=""""),_display_(Seq[Any](/*89.16*/routes/*89.22*/.Transaction.show(transaction.getIdToken))),format.raw/*89.63*/("""" class="btn-detail btn-transaction-detail" data-remote="true">"""),_display_(Seq[Any](/*89.127*/doku/*89.131*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*89.192*/("""</a>
				</td>
				<td class="actions">
					<a href='"""),_display_(Seq[Any](/*92.16*/routes/*92.22*/.Transaction.receipt(transaction.getIdToken, "b"))),format.raw/*92.71*/("""' class="btn-detail btn-transaction-detail">"""),_display_(Seq[Any](/*92.116*/doku/*92.120*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*92.180*/("""</a>
				</td>
			</tr>
			""")))})),format.raw/*95.5*/("""
			</tbody>
		</table>

		<div id="pagination" class="pagination pagination-right">
			<ul>
				"""),_display_(Seq[Any](/*101.6*/if(currentPage.hasPrev)/*101.29*/ {_display_(Seq[Any](format.raw/*101.31*/("""
				<li class="prev">
					<a href=""""),_display_(Seq[Any](/*103.16*/link(currentPage.getPageIndex - 1, null))),format.raw/*103.56*/("""">&larr; """),_display_(Seq[Any](/*103.66*/doku/*103.70*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*103.133*/("""</a>
				</li>
				""")))}/*105.7*/else/*105.12*/{_display_(Seq[Any](format.raw/*105.13*/("""
				<li class="prev">
					<a>&larr; """),_display_(Seq[Any](/*107.17*/doku/*107.21*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*107.84*/("""</a>
				</li>
				""")))})),format.raw/*109.6*/("""

				<li class="current">
					<a>Displaying """),_display_(Seq[Any](/*112.21*/currentPage/*112.32*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*112.66*/("""</a>
				</li>

				"""),_display_(Seq[Any](/*115.6*/if(currentPage.hasNext)/*115.29*/ {_display_(Seq[Any](format.raw/*115.31*/("""
				<li class="next">
					<a href=""""),_display_(Seq[Any](/*117.16*/link(currentPage.getPageIndex + 1, null))),format.raw/*117.56*/("""">"""),_display_(Seq[Any](/*117.59*/doku/*117.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*117.122*/(""" &rarr;</a>
				</li>
				""")))}/*119.7*/else/*119.12*/{_display_(Seq[Any](format.raw/*119.13*/("""
				<li class="next">
					<a>"""),_display_(Seq[Any](/*121.10*/doku/*121.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*121.73*/(""" &rarr;</a>
				</li>
				""")))})),format.raw/*123.6*/("""
			</ul>
		</div>
		""")))})),format.raw/*126.4*/("""
	</div>
	<div class="span5">
		<div id="transaction-detail-container" class="well">
			"""),_display_(Seq[Any](/*130.5*/doku/*130.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG274","Click view detail for transaction detail"))),format.raw/*130.104*/("""
		</div>
    </div>
    </div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*136.38*/routes/*136.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*136.84*/("""'></script>
""")))})),format.raw/*137.2*/("""
"""))}
    }
    
    def render(filterForm:Form[controllers.Report.FilterForm],currentPage:com.avaje.ebean.Page[models.Transaction],currentSortBy:String,currentOrder:String,batchId:Int): play.api.templates.Html = apply(filterForm,currentPage,currentSortBy,currentOrder,batchId)
    
    def f:((Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String,Int) => play.api.templates.Html) = (filterForm,currentPage,currentSortBy,currentOrder,batchId) => apply(filterForm,currentPage,currentSortBy,currentOrder,batchId)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:33 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/listBatchTransaction.scala.html
                    HASH: 4887f0af70a34d7a8f442062d85535b56c98ac62
                    MATRIX: 838->1|1111->706|1125->712|1219->742|1268->755|1293->758|1333->763|1367->788|1407->790|1455->802|1512->837|1551->840|1578->845|1603->853|1615->858|1654->859|1692->862|1718->867|1752->870|1782->218|1793->222|2304->163|2331->215|2360->702|2390->878|2428->881|2476->920|2515->921|2588->958|2601->962|2707->1046|2757->1061|2772->1068|2808->1082|2903->1141|2940->1156|3137->1317|3175->1346|3202->1351|3276->1388|3315->1417|3350->1429|3403->1445|3417->1449|3533->1542|3662->1634|3676->1638|3760->1699|3833->1736|3848->1742|3901->1773|3940->1776|3953->1780|4035->1839|4170->1939|4216->1976|4255->1977|4320->2007|4333->2011|4456->2111|4492->2130|4505->2135|4544->2136|4713->2270|4822->2357|4863->2363|4978->2456|5019->2462|5124->2545|5165->2551|5291->2654|5332->2660|5465->2770|5506->2776|5611->2859|5697->2909|5710->2913|5794->2974|5871->3016|5926->3055|5966->3057|6020->3075|6040->3086|6073->3097|6140->3128|6155->3134|6238->3195|6289->3210|6309->3221|6348->3238|6399->3253|6419->3264|6460->3283|6511->3298|6531->3309|6577->3333|6628->3348|6648->3359|6681->3370|6763->3416|6778->3422|6841->3463|6942->3527|6956->3531|7040->3592|7131->3647|7146->3653|7217->3702|7299->3747|7313->3751|7396->3811|7455->3839|7589->3937|7622->3960|7663->3962|7738->4000|7801->4040|7848->4050|7862->4054|7949->4117|7988->4138|8002->4143|8042->4144|8118->4183|8132->4187|8218->4250|8270->4270|8354->4317|8375->4328|8432->4362|8489->4383|8522->4406|8563->4408|8638->4446|8701->4486|8741->4489|8755->4493|8838->4552|8884->4580|8898->4585|8938->4586|9007->4618|9021->4622|9103->4681|9162->4708|9216->4730|9341->4819|9354->4823|9473->4918|9587->4995|9603->5001|9666->5041|9711->5054
                    LINES: 26->1|31->29|31->29|33->29|34->30|34->30|35->31|35->31|35->31|36->32|36->32|36->32|36->32|37->33|37->33|37->33|38->34|38->34|39->35|41->6|41->6|62->1|63->4|65->26|68->37|70->39|70->39|70->39|73->42|73->42|73->42|76->45|76->45|76->45|80->49|80->49|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|84->53|84->53|84->53|93->62|93->62|93->62|95->64|95->64|95->64|97->66|97->66|97->66|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|107->76|111->80|111->80|111->80|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|118->87|118->87|120->89|120->89|120->89|120->89|120->89|120->89|123->92|123->92|123->92|123->92|123->92|123->92|126->95|132->101|132->101|132->101|134->103|134->103|134->103|134->103|134->103|136->105|136->105|136->105|138->107|138->107|138->107|140->109|143->112|143->112|143->112|146->115|146->115|146->115|148->117|148->117|148->117|148->117|148->117|150->119|150->119|150->119|152->121|152->121|152->121|154->123|157->126|161->130|161->130|161->130|167->136|167->136|167->136|168->137
                    -- GENERATED --
                */
            