
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
object listBatch extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,com.avaje.ebean.Page[models.Batch],String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filter: String, currentPage: com.avaje.ebean.Page[models.Batch], currentSortBy: String, currentOrder: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

def /*30.2*/header/*30.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*30.38*/("""
<th class=""""),_display_(Seq[Any](/*31.13*/key)),format.raw/*31.16*/("""" style="text-align: center; vertical-align: middle;">
	"""),_display_(Seq[Any](/*32.3*/if(key && key.length > 0)/*32.28*/ {_display_(Seq[Any](format.raw/*32.30*/("""
	<a href=""""),_display_(Seq[Any](/*33.12*/link(currentPage.getPageIndex, key))),format.raw/*33.47*/("""">"""),_display_(Seq[Any](/*33.50*/title)),format.raw/*33.55*/("""</a>
	""")))}/*34.4*/else/*34.9*/{_display_(Seq[Any](format.raw/*34.10*/("""
	"""),_display_(Seq[Any](/*35.3*/title)),format.raw/*35.8*/("""
	""")))})),format.raw/*36.3*/("""
</th>
""")))};def /*7.2*/link/*7.6*/(newPage:Int, newSortBy:String) = {{

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

    routes.Transaction.listBatchInquiry(newPage, sortBy, order, filter)

}};
Seq[Any](format.raw/*1.112*/("""
"""),format.raw/*6.1*/("""
"""),format.raw/*27.2*/("""


"""),format.raw/*38.2*/("""

"""),_display_(Seq[Any](/*40.2*/layout("Transactions Inquiry management")/*40.43*/{_display_(Seq[Any](format.raw/*40.44*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*43.10*/doku/*43.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG498","Batch Upload management"))),format.raw/*43.92*/("""</h1>
</div>

"""),_display_(Seq[Any](/*46.2*/partial/*46.9*/.flash_alert())),format.raw/*46.23*/("""

<div class="row">
	<div class="span12">
		<form action=""""),_display_(Seq[Any](/*50.18*/link(0, "name"))),format.raw/*50.33*/("""" method="GET" class="form-search">
			<div id="action" class="well">
                <div class="input-append">
					<input type="search" id="searchbox" name="filter" class="search-query span4" value=""""),_display_(Seq[Any](/*53.91*/filter)),format.raw/*53.97*/("""" placeholder='"""),_display_(Seq[Any](/*53.113*/doku/*53.117*/.kirimdoku.util.MultiLanguage.getLanguage("LANG513","Search by filename"))),format.raw/*53.190*/("""'><button type="submit" class="btn" style="border-radius: 0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*53.283*/doku/*53.287*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*53.348*/("""</button>
					"""),_display_(Seq[Any](/*54.7*/restrict(la(as("operator")))/*54.35*/ {_display_(Seq[Any](format.raw/*54.37*/("""
					<a class="btn" href=""""),_display_(Seq[Any](/*55.28*/routes/*55.34*/.BatchUpload.viewUpload())),format.raw/*55.59*/("""">"""),_display_(Seq[Any](/*55.62*/doku/*55.66*/.kirimdoku.util.MultiLanguage.getLanguage("LANG497","Batch Upload"))),format.raw/*55.133*/("""</a>
					""")))})),format.raw/*56.7*/("""
				</div>
			</div>
		</form>
    </div>
</div>

<div class="row">
    <div class="span10">
		"""),_display_(Seq[Any](/*65.4*/if(currentPage.getTotalRowCount == 0)/*65.41*/{_display_(Seq[Any](format.raw/*65.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*67.9*/doku/*67.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG271","There are no transaction data to be displayed"))),format.raw/*67.113*/("""</em>
		</div>
		""")))}/*69.5*/else/*69.10*/{_display_(Seq[Any](format.raw/*69.11*/("""
		<table class="table transaction table-bordered table-striped table-condensed" style="text-align: center;">
			<thead>
			<tr>
				"""),_display_(Seq[Any](/*73.6*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG365","ID")))),format.raw/*73.81*/("""
				"""),_display_(Seq[Any](/*74.6*/header("createDate", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG500","Create Date")))),format.raw/*74.98*/("""
				"""),_display_(Seq[Any](/*75.6*/header("inquiryUpdateDate", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG502","Inquiry Update Date")))),format.raw/*75.113*/("""
				"""),_display_(Seq[Any](/*76.6*/header("remitUpdateDate", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG509","Remit Update Date")))),format.raw/*76.109*/("""
				"""),_display_(Seq[Any](/*77.6*/header("user", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG102","Agent ID")))),format.raw/*77.89*/("""
				"""),_display_(Seq[Any](/*78.6*/header("fileName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG501","File Name")))),format.raw/*78.94*/("""
				"""),_display_(Seq[Any](/*79.6*/header("totalRow", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG503","Total Row")))),format.raw/*79.94*/("""
				"""),_display_(Seq[Any](/*80.6*/header("totalRowInquiry", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG516","Total Row Inquiry")))),format.raw/*80.109*/("""
				"""),_display_(Seq[Any](/*81.6*/header("totalRowRemit", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG517","Total Row Remit")))),format.raw/*81.105*/("""
				"""),_display_(Seq[Any](/*82.6*/header("description", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG505","Description")))),format.raw/*82.99*/("""
				"""),_display_(Seq[Any](/*83.6*/header("inquiry", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG506","Inquiry")))),format.raw/*83.91*/("""
				"""),_display_(Seq[Any](/*84.6*/header("remit", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG514","Remit")))),format.raw/*84.87*/("""
			</tr>
			</thead>
			<tbody>
			"""),_display_(Seq[Any](/*88.5*/for(batch <- currentPage.getList) yield /*88.38*/ {_display_(Seq[Any](format.raw/*88.40*/("""
			<tr>
				<td style="text-align: center;">"""),_display_(Seq[Any](/*90.38*/batch/*90.43*/.id)),format.raw/*90.46*/("""</td>
				<td style="text-align: center;">"""),_display_(Seq[Any](/*91.38*/doku/*91.42*/.kirimdoku.util.CommonUtil.sdfDateTimes.format(batch.createDate))),format.raw/*91.106*/("""</td>
				<td style="text-align: center;">
					"""),_display_(Seq[Any](/*93.7*/if(batch.inquiryUpdateDate != null)/*93.42*/{_display_(Seq[Any](format.raw/*93.43*/("""
				 		"""),_display_(Seq[Any](/*94.9*/doku/*94.13*/.kirimdoku.util.CommonUtil.sdfDateTimes.format(batch.inquiryUpdateDate))),format.raw/*94.84*/("""
				 	""")))})),format.raw/*95.8*/("""
				</td>
				<td style="text-align: center;">
					"""),_display_(Seq[Any](/*98.7*/if(batch.remitUpdateDate != null)/*98.40*/{_display_(Seq[Any](format.raw/*98.41*/("""
				 		"""),_display_(Seq[Any](/*99.9*/doku/*99.13*/.kirimdoku.util.CommonUtil.sdfDateTimes.format(batch.remitUpdateDate))),format.raw/*99.82*/("""
				 	""")))})),format.raw/*100.8*/("""
				</td>
				<td style="text-align: center;">"""),_display_(Seq[Any](/*102.38*/batch/*102.43*/.user.getIdToken)),format.raw/*102.59*/("""</td>
				<td>"""),_display_(Seq[Any](/*103.10*/batch/*103.15*/.fileName)),format.raw/*103.24*/("""</td>
				<td style="text-align: right;">"""),_display_(Seq[Any](/*104.37*/batch/*104.42*/.totalRow)),format.raw/*104.51*/("""</td>
				<td style="text-align: right;">"""),_display_(Seq[Any](/*105.37*/batch/*105.42*/.totalRowInquiry)),format.raw/*105.58*/("""</td>
				<td style="text-align: right;">"""),_display_(Seq[Any](/*106.37*/batch/*106.42*/.totalRowRemit)),format.raw/*106.56*/("""</td>
				<td style="text-align: center;">
				"""),_display_(Seq[Any](/*108.6*/if(batch.description != "")/*108.33*/{_display_(Seq[Any](format.raw/*108.34*/("""
					<a href=""""),_display_(Seq[Any](/*109.16*/routes/*109.22*/.Transaction.showBatch(batch.id))),format.raw/*109.54*/("""" class="btn-detail btn-transaction-detail" data-remote="true">"""),_display_(Seq[Any](/*109.118*/doku/*109.122*/.kirimdoku.util.MultiLanguage.getLanguage("LANG473","View"))),format.raw/*109.181*/("""</a>
				""")))})),format.raw/*110.6*/("""
				</td>
				<td style="text-align: center;">
				<a href='"""),_display_(Seq[Any](/*113.15*/routes/*113.21*/.Transaction.showBatchDetail(batch.id, 0,"","i"))),format.raw/*113.69*/("""' class="btn-detail btn-transaction-detail">"""),_display_(Seq[Any](/*113.114*/doku/*113.118*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*113.179*/("""</a>
				</td>
				<td style="text-align: center;">
				"""),_display_(Seq[Any](/*116.6*/if(batch.state.equals('C'))/*116.33*/{_display_(Seq[Any](format.raw/*116.34*/("""
					<a href='"""),_display_(Seq[Any](/*117.16*/routes/*117.22*/.Transaction.showBatchDetail(batch.id, 0,"","r"))),format.raw/*117.70*/("""' class="btn-detail btn-transaction-detail">"""),_display_(Seq[Any](/*117.115*/doku/*117.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*117.180*/("""</a>
				""")))})),format.raw/*118.6*/("""
				</td>
			</tr>
			""")))})),format.raw/*121.5*/("""
			</tbody>
		</table>

		<div id="pagination" class="pagination pagination-right">
			<ul>
				"""),_display_(Seq[Any](/*127.6*/if(currentPage.hasPrev)/*127.29*/ {_display_(Seq[Any](format.raw/*127.31*/("""
				<li class="prev">
					<a href=""""),_display_(Seq[Any](/*129.16*/link(currentPage.getPageIndex - 1, null))),format.raw/*129.56*/("""">&larr; """),_display_(Seq[Any](/*129.66*/doku/*129.70*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*129.133*/("""</a>
				</li>
				""")))}/*131.7*/else/*131.12*/{_display_(Seq[Any](format.raw/*131.13*/("""
				<li class="prev">
					<a>&larr; """),_display_(Seq[Any](/*133.17*/doku/*133.21*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*133.84*/("""</a>
				</li>
				""")))})),format.raw/*135.6*/("""

				<li class="current">
					<a>Displaying """),_display_(Seq[Any](/*138.21*/currentPage/*138.32*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*138.66*/("""</a>
				</li>

				"""),_display_(Seq[Any](/*141.6*/if(currentPage.hasNext)/*141.29*/ {_display_(Seq[Any](format.raw/*141.31*/("""
				<li class="next">
					<a href=""""),_display_(Seq[Any](/*143.16*/link(currentPage.getPageIndex + 1, null))),format.raw/*143.56*/("""">"""),_display_(Seq[Any](/*143.59*/doku/*143.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*143.122*/(""" &rarr;</a>
				</li>
				""")))}/*145.7*/else/*145.12*/{_display_(Seq[Any](format.raw/*145.13*/("""
				<li class="next">
					<a>"""),_display_(Seq[Any](/*147.10*/doku/*147.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*147.73*/(""" &rarr;</a>
				</li>
				""")))})),format.raw/*149.6*/("""
			</ul>
		</div>
		""")))})),format.raw/*152.4*/("""
	</div>
	<div class="span2">
		<pre id="transaction-detail-container" class="well" style="font-family: helvetica; font-size: 9pt;">"""),_display_(Seq[Any](/*155.104*/doku/*155.108*/.kirimdoku.util.MultiLanguage.getLanguage("LANG274","Click view detail for transaction detail"))),format.raw/*155.203*/("""</pre>
    </div>
</div>
<script type="text/javascript" src='"""),_display_(Seq[Any](/*158.38*/routes/*158.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*158.84*/("""'></script>
""")))})),format.raw/*159.2*/("""
"""))}
    }
    
    def render(filter:String,currentPage:com.avaje.ebean.Page[models.Batch],currentSortBy:String,currentOrder:String): play.api.templates.Html = apply(filter,currentPage,currentSortBy,currentOrder)
    
    def f:((String,com.avaje.ebean.Page[models.Batch],String,String) => play.api.templates.Html) = (filter,currentPage,currentSortBy,currentOrder) => apply(filter,currentPage,currentSortBy,currentOrder)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:32 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/listBatch.scala.html
                    HASH: 9b903ae08043b1f9d9c516628c20e999fd30d846
                    MATRIX: 788->1|1114->743|1128->749|1222->779|1271->792|1296->795|1388->852|1422->877|1462->879|1510->891|1567->926|1606->929|1633->934|1658->942|1670->947|1709->948|1747->951|1773->956|1807->959|1837->270|1848->274|2344->111|2371->268|2399->739|2429->967|2467->970|2517->1011|2556->1012|2629->1049|2642->1053|2742->1131|2792->1146|2807->1153|2843->1167|2938->1226|2975->1241|3214->1444|3242->1450|3295->1466|3309->1470|3405->1543|3535->1636|3549->1640|3633->1701|3684->1717|3721->1745|3761->1747|3825->1775|3840->1781|3887->1806|3926->1809|3939->1813|4029->1880|4071->1891|4203->1988|4249->2025|4288->2026|4353->2056|4366->2060|4489->2160|4525->2179|4538->2184|4577->2185|4746->2319|4843->2394|4884->2400|4998->2492|5039->2498|5169->2605|5210->2611|5336->2714|5377->2720|5482->2803|5523->2809|5633->2897|5674->2903|5784->2991|5825->2997|5951->3100|5992->3106|6114->3205|6155->3211|6270->3304|6311->3310|6418->3395|6459->3401|6562->3482|6634->3519|6683->3552|6723->3554|6805->3600|6819->3605|6844->3608|6923->3651|6936->3655|7023->3719|7107->3768|7151->3803|7190->3804|7234->3813|7247->3817|7340->3888|7379->3896|7468->3950|7510->3983|7549->3984|7593->3993|7606->3997|7697->4066|7737->4074|7822->4122|7837->4127|7876->4143|7928->4158|7943->4163|7975->4172|8054->4214|8069->4219|8101->4228|8180->4270|8195->4275|8234->4291|8313->4333|8328->4338|8365->4352|8449->4400|8486->4427|8526->4428|8579->4444|8595->4450|8650->4482|8752->4546|8767->4550|8850->4609|8892->4619|8991->4681|9007->4687|9078->4735|9161->4780|9176->4784|9261->4845|9354->4902|9391->4929|9431->4930|9484->4946|9500->4952|9571->5000|9654->5045|9669->5049|9754->5110|9796->5120|9852->5144|9986->5242|10019->5265|10060->5267|10135->5305|10198->5345|10245->5355|10259->5359|10346->5422|10385->5443|10399->5448|10439->5449|10515->5488|10529->5492|10615->5555|10667->5575|10751->5622|10772->5633|10829->5667|10886->5688|10919->5711|10960->5713|11035->5751|11098->5791|11138->5794|11152->5798|11235->5857|11281->5885|11295->5890|11335->5891|11404->5923|11418->5927|11500->5986|11559->6013|11613->6035|11784->6168|11799->6172|11918->6267|12017->6329|12033->6335|12096->6375|12141->6388
                    LINES: 26->1|35->30|35->30|37->30|38->31|38->31|39->32|39->32|39->32|40->33|40->33|40->33|40->33|41->34|41->34|41->34|42->35|42->35|43->36|45->7|45->7|66->1|67->6|68->27|71->38|73->40|73->40|73->40|76->43|76->43|76->43|79->46|79->46|79->46|83->50|83->50|86->53|86->53|86->53|86->53|86->53|86->53|86->53|86->53|87->54|87->54|87->54|88->55|88->55|88->55|88->55|88->55|88->55|89->56|98->65|98->65|98->65|100->67|100->67|100->67|102->69|102->69|102->69|106->73|106->73|107->74|107->74|108->75|108->75|109->76|109->76|110->77|110->77|111->78|111->78|112->79|112->79|113->80|113->80|114->81|114->81|115->82|115->82|116->83|116->83|117->84|117->84|121->88|121->88|121->88|123->90|123->90|123->90|124->91|124->91|124->91|126->93|126->93|126->93|127->94|127->94|127->94|128->95|131->98|131->98|131->98|132->99|132->99|132->99|133->100|135->102|135->102|135->102|136->103|136->103|136->103|137->104|137->104|137->104|138->105|138->105|138->105|139->106|139->106|139->106|141->108|141->108|141->108|142->109|142->109|142->109|142->109|142->109|142->109|143->110|146->113|146->113|146->113|146->113|146->113|146->113|149->116|149->116|149->116|150->117|150->117|150->117|150->117|150->117|150->117|151->118|154->121|160->127|160->127|160->127|162->129|162->129|162->129|162->129|162->129|164->131|164->131|164->131|166->133|166->133|166->133|168->135|171->138|171->138|171->138|174->141|174->141|174->141|176->143|176->143|176->143|176->143|176->143|178->145|178->145|178->145|180->147|180->147|180->147|182->149|185->152|188->155|188->155|188->155|191->158|191->158|191->158|192->159
                    -- GENERATED --
                */
            