
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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Report.FilterForm], currentPage: com.avaje.ebean.Page[models.Transaction], currentSortBy: String, currentOrder: String, userCorporateCountry: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import java.util.Calendar

import java.text.SimpleDateFormat;

import java.math.BigInteger; var is_restrict = 0

def /*54.2*/header/*54.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*54.38*/("""
<th class=""""),_display_(Seq[Any](/*55.13*/key)),format.raw/*55.16*/("""" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
	"""),_display_(Seq[Any](/*56.3*/if(key && key.length > 0)/*56.28*/ {_display_(Seq[Any](format.raw/*56.30*/("""
	<a href=""""),_display_(Seq[Any](/*57.12*/link(currentPage.getPageIndex, key))),format.raw/*57.47*/("""">"""),_display_(Seq[Any](/*57.50*/title)),format.raw/*57.55*/("""</a>
	""")))}/*58.4*/else/*58.9*/{_display_(Seq[Any](format.raw/*58.10*/("""
	"""),_display_(Seq[Any](/*59.3*/title)),format.raw/*59.8*/("""
	""")))})),format.raw/*60.3*/("""
</th>
""")))};def /*13.2*/urlFilterize/*13.14*/(url:String) = {{
var newUrl = url
if(!url.contains("?")) {
	newUrl = newUrl + "?"
}
newUrl +
"&corporate.code="+filterForm("corporate.code").value +
"&agentCode="+filterForm("agentCode").value +
"&trxStartDate="+filterForm("trxStartDate").value +
"&trxEndDate="+filterForm("trxEndDate").value +
"&senderCountry.code="+filterForm("senderCountry.code").value +
"&beneficiaryCountry.code="+filterForm("beneficiaryCountry.code").value +
"&transactionsStatus="+filterForm("transactionsStatus").value
}};def /*28.2*/link/*28.6*/(newPage:Int, newSortBy:String) = {{

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

	urlFilterize(routes.Report.list(newPage, sortBy, order).url)
}};def /*49.2*/dateFormat/*49.12*/(d:Date) = {{
    new SimpleDateFormat("yyyy-MM-dd") format d
}};
Seq[Any](format.raw/*1.181*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*7.1*/("""
"""),format.raw/*10.1*/("""
"""),format.raw/*12.1*/("""
"""),format.raw/*26.2*/("""

"""),format.raw/*47.2*/("""

"""),format.raw/*51.2*/("""


"""),format.raw/*62.2*/("""

"""),_display_(Seq[Any](/*64.2*/layout("Reporting")/*64.21*/{_display_(Seq[Any](format.raw/*64.22*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*67.10*/doku/*67.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG017","Report"))),format.raw/*67.75*/("""</h1>
</div>

<div>
	"""),_display_(Seq[Any](/*71.3*/partial/*71.10*/.flash_alert())),format.raw/*71.24*/("""

    <div id="filter" class="well">
        <form action=""""),_display_(Seq[Any](/*74.24*/link(0, "id"))),format.raw/*74.37*/("""" method="GET" id="form-filter">
        <div class="row-fluid">
            <div class="span4">
				"""),_display_(Seq[Any](/*77.6*/select(filterForm("corporate.code"),
				options(models.Corporate.options),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG133","Corporate")
				))),format.raw/*81.6*/("""
            </div>
            <div class="span4">
				"""),_display_(Seq[Any](/*84.6*/restrict(la(as("admin"), as("finance"), as("mainagent"), as("supervisor")))/*84.81*/ {_display_(Seq[Any](format.raw/*84.83*/("""
				"""),_display_(Seq[Any](/*85.6*/inputText(filterForm("agentCode"),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG102","Agent ID"),
				'placeholder -> "Input agent code.."
				))),format.raw/*89.6*/("""
				""")))})),format.raw/*90.6*/("""
            </div>
        </div>
        <div class="row-fluid">
            <div class="span4">
            	<!-- Stevano Edited 18 Jun 2015 -->
            	"""),_display_(Seq[Any](/*96.15*/restrict(la(as("mainagent"), as("supervisor"), as("operator")))/*96.78*/ {_display_(Seq[Any](format.raw/*96.80*/("""
					"""),_display_(Seq[Any](/*97.7*/{is_restrict=1})),format.raw/*97.22*/("""
				""")))})),format.raw/*98.6*/("""
            	
				"""),_display_(Seq[Any](/*100.6*/if(is_restrict == 1)/*100.26*/{_display_(Seq[Any](format.raw/*100.27*/("""
					"""),_display_(Seq[Any](/*101.7*/if(controllers.helpers.SessionHelper.getUser().corporate.permission == 2)/*101.80*/ {_display_(Seq[Any](format.raw/*101.82*/("""
						<label for="senderCountry_code">"""),_display_(Seq[Any](/*102.40*/doku/*102.44*/.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"))),format.raw/*102.114*/("""</label>
						<div class="input">
							<select id="senderCountry_code" name="senderCountry.code">
							"""),_display_(Seq[Any](/*105.9*/for((code, name) <- models.Country.options) yield /*105.52*/ {_display_(Seq[Any](format.raw/*105.54*/("""
								"""),_display_(Seq[Any](/*106.10*/if(code == userCorporateCountry)/*106.42*/{_display_(Seq[Any](format.raw/*106.43*/("""
								  <option value=""""),_display_(Seq[Any](/*107.27*/code)),format.raw/*107.31*/("""">"""),_display_(Seq[Any](/*107.34*/name)),format.raw/*107.38*/("""</option>
								""")))})),format.raw/*108.10*/("""
							""")))})),format.raw/*109.9*/("""
							</select>
						</div>
					""")))})),format.raw/*112.7*/("""
					"""),_display_(Seq[Any](/*113.7*/if(controllers.helpers.SessionHelper.getUser().corporate.permission == 4 || controllers.helpers.SessionHelper.getUser().corporate.permission == 6)/*113.153*/ {_display_(Seq[Any](format.raw/*113.155*/("""
						"""),_display_(Seq[Any](/*114.8*/select(filterForm("senderCountry.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country")
						))),format.raw/*117.8*/("""
					""")))})),format.raw/*118.7*/("""
				""")))}/*119.6*/else/*119.11*/{_display_(Seq[Any](format.raw/*119.12*/("""
					"""),_display_(Seq[Any](/*120.7*/select(filterForm("senderCountry.code"),
					options(models.Country.options),
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country")
					))),format.raw/*123.7*/("""
				""")))})),format.raw/*124.6*/("""
				<!-- End Edited -->
			</div>
            <div class="span4">
				"""),_display_(Seq[Any](/*128.6*/if(is_restrict == 1)/*128.26*/{_display_(Seq[Any](format.raw/*128.27*/("""
					"""),_display_(Seq[Any](/*129.7*/if(controllers.helpers.SessionHelper.getUser().corporate.permission == 4)/*129.80*/ {_display_(Seq[Any](format.raw/*129.82*/("""
						<label for="senderCountry_code">"""),_display_(Seq[Any](/*130.40*/doku/*130.44*/.kirimdoku.util.MultiLanguage.getLanguage("LANG134","Receiving country"))),format.raw/*130.116*/("""</label>
						<div class="input">
							<select id="senderCountry_code" name="senderCountry.code">
							"""),_display_(Seq[Any](/*133.9*/for((code, name) <- models.Country.options) yield /*133.52*/ {_display_(Seq[Any](format.raw/*133.54*/("""
								"""),_display_(Seq[Any](/*134.10*/if(code == userCorporateCountry)/*134.42*/{_display_(Seq[Any](format.raw/*134.43*/("""
								  <option value=""""),_display_(Seq[Any](/*135.27*/code)),format.raw/*135.31*/("""">"""),_display_(Seq[Any](/*135.34*/name)),format.raw/*135.38*/("""</option>
								""")))})),format.raw/*136.10*/("""
							""")))})),format.raw/*137.9*/("""
							</select>
						</div>
					""")))})),format.raw/*140.7*/("""
					"""),_display_(Seq[Any](/*141.7*/if(controllers.helpers.SessionHelper.getUser().corporate.permission == 2 || controllers.helpers.SessionHelper.getUser().corporate.permission == 6)/*141.153*/ {_display_(Seq[Any](format.raw/*141.155*/("""
						"""),_display_(Seq[Any](/*142.8*/select(filterForm("beneficiaryCountry.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG134","Receiving country")
						))),format.raw/*145.8*/("""
					""")))})),format.raw/*146.7*/("""
				""")))}/*147.6*/else/*147.11*/{_display_(Seq[Any](format.raw/*147.12*/("""
					"""),_display_(Seq[Any](/*148.7*/select(filterForm("beneficiaryCountry.code"),
					options(models.Country.options),
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG134","Receiving country")
					))),format.raw/*151.7*/("""
				""")))})),format.raw/*152.6*/("""
			</div>
        </div>
        <div class="row-fluid">
            <div class="span4">
				"""),_display_(Seq[Any](/*157.6*/inputDate(filterForm("trxStartDate"), 
				'_showConstraints -> false, 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG135","Transaction Start Date"), 
				'_class -> "", 'class -> "datepicker"))),format.raw/*160.43*/("""
            </div>
            <div class="span4">
				"""),_display_(Seq[Any](/*163.6*/inputDate(filterForm("trxEndDate"), 
				'_showConstraints -> false, 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG136","Transaction End Date"), 
				'_class -> "", 'class -> "datepicker"))),format.raw/*166.43*/("""
            </div>
        </div>
        <div class="row-fluid">
            <div class="span4">
				<div id="transactions_status" class="clearfix  ">
				    <label for="transactions_status">"""),_display_(Seq[Any](/*172.43*/doku/*172.47*/.kirimdoku.util.MultiLanguage.getLanguage("LANG132","Transaction Status"))),format.raw/*172.120*/("""</label>
				    <div class="input">
						<select id="transactionsStatus" name="transactionsStatus">
							<option></option>
				            """),_display_(Seq[Any](/*176.18*/for(list <- filterForm.get().transactionStatusList) yield /*176.69*/ {_display_(Seq[Any](format.raw/*176.71*/("""
				            	<option value=""""),_display_(Seq[Any](/*177.34*/list/*177.38*/.id)),format.raw/*177.41*/("""" """),_display_(Seq[Any](/*177.44*/if(filterForm.get().transactionsStatus == list.id)/*177.94*/{_display_(Seq[Any](format.raw/*177.95*/(""" selected="selected" """)))})),format.raw/*177.117*/(""">"""),_display_(Seq[Any](/*177.119*/list/*177.123*/.status)),format.raw/*177.130*/("""</option>
				            """)))})),format.raw/*178.18*/("""
			            </select>
				        <span class="help-inline"></span>
				        <span class="help-block"></span> 
				    </div>
				</div>
			</div>
            <div class="span4">
				&nbsp;
			</div>
        </div>
		<div id="filter-action" class="form-actions">
			<a class="btn" href=""""),_display_(Seq[Any](/*190.26*/routes/*190.32*/.Report.list())),format.raw/*190.46*/(""""><i class="icon-repeat"></i> """),_display_(Seq[Any](/*190.77*/doku/*190.81*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*190.140*/("""</a>
			<button type="submit" class="btn"><i class="icon-filter"></i> """),_display_(Seq[Any](/*191.67*/doku/*191.71*/.kirimdoku.util.MultiLanguage.getLanguage("LANG130","Filter"))),format.raw/*191.132*/("""</button>
		</div>
		</form>
	</div>

	"""),_display_(Seq[Any](/*196.3*/if(currentPage != null)/*196.26*/ {_display_(Seq[Any](format.raw/*196.28*/("""
    """),_display_(Seq[Any](/*197.6*/if(currentPage.getTotalRowCount == 0)/*197.43*/{_display_(Seq[Any](format.raw/*197.44*/("""
    <div class="well">
        <em>"""),_display_(Seq[Any](/*199.14*/doku/*199.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG286","Results are empty"))),format.raw/*199.90*/("""</em>
    </div>
    """)))}/*201.7*/else/*201.12*/{_display_(Seq[Any](format.raw/*201.13*/("""
	<div class="row">
		<div class="span12">
		"""),_display_(Seq[Any](/*204.4*/if(filterForm("agent.id").value != null)/*204.44*/ {_display_(Seq[Any](format.raw/*204.46*/("""
		<span class="">"""),_display_(Seq[Any](/*205.19*/doku/*205.23*/.kirimdoku.util.MultiLanguage.getLanguage("LANG287","List reports from user"))),format.raw/*205.100*/(""" <strong>"""),_display_(Seq[Any](/*205.110*/filterForm("agent.fullName")/*205.138*/.value)),format.raw/*205.144*/("""</strong> """),_display_(Seq[Any](/*205.155*/doku/*205.159*/.kirimdoku.util.MultiLanguage.getLanguage("LANG288","and agents under it"))),format.raw/*205.233*/("""</span>
		""")))})),format.raw/*206.4*/("""
			<div class="pull-right">
				<!--a class="btn" href=""""),_display_(Seq[Any](/*208.30*/urlFilterize(routes.Report.pdf().toString()))),format.raw/*208.74*/(""""><i class="icon-download-alt"></i> Download as PDF</a-->
				<a class="btn" href=""""),_display_(Seq[Any](/*209.27*/urlFilterize(routes.Report.excel().toString()))),format.raw/*209.73*/(""""><i class="icon-download-alt"></i> """),_display_(Seq[Any](/*209.110*/doku/*209.114*/.kirimdoku.util.MultiLanguage.getLanguage("LANG137","Download as Excel"))),format.raw/*209.186*/("""</a>
			</div>
		</div>
	</div>
	<br/>
    <table class="transaction table-bordered table-striped table-condensed" style="width:100%;text-align: center;">
        <thead>
        <tr>
            """),_display_(Seq[Any](/*217.14*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG138","Transaction ID")))),format.raw/*217.101*/("""
            """),_display_(Seq[Any](/*218.14*/header("user", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG102","Agent ID")))),format.raw/*218.97*/("""
            """),_display_(Seq[Any](/*219.14*/header("channel", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG139","Channel")))),format.raw/*219.99*/("""
            """),_display_(Seq[Any](/*220.14*/header("sender", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG140","Sender ID")))),format.raw/*220.100*/("""
            """),_display_(Seq[Any](/*221.14*/header("senderCountry", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG103","Sender Country")))),format.raw/*221.112*/("""
            """),_display_(Seq[Any](/*222.14*/header("senderAmount", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG141","Sender Amount")))),format.raw/*222.110*/("""
            """),_display_(Seq[Any](/*223.14*/header("beneficiary", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG142","Receiver ID")))),format.raw/*223.107*/("""
            """),_display_(Seq[Any](/*224.14*/header("beneficiaryCountry", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG104","Receiver Country")))),format.raw/*224.119*/("""
            """),_display_(Seq[Any](/*225.14*/header("beneficiaryAmount", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG143","Receiver Amount")))),format.raw/*225.117*/("""
            """),_display_(Seq[Any](/*226.14*/header("rate", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG144","Rate")))),format.raw/*226.93*/("""
            """),_display_(Seq[Any](/*227.14*/header("fee", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG145","Fee")))),format.raw/*227.91*/("""
            """),_display_(Seq[Any](/*228.14*/header("status", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG130","Status")))),format.raw/*228.97*/("""
            """),_display_(Seq[Any](/*229.14*/header("createdTime", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG146","Date")))),format.raw/*229.100*/("""
        </tr>
        </thead>
        <tbody>
        """),_display_(Seq[Any](/*233.10*/for(transaction <- currentPage.getList) yield /*233.49*/ {_display_(Seq[Any](format.raw/*233.51*/("""
        <tr>
            <td class="left">"""),_display_(Seq[Any](/*235.31*/transaction/*235.42*/.getIdToken)),format.raw/*235.53*/("""</td>
			<td>"""),_display_(Seq[Any](/*236.9*/transaction/*236.20*/.agent.getIdToken)),format.raw/*236.37*/("""</td>
			<td>"""),_display_(Seq[Any](/*237.9*/transaction/*237.20*/.channel.name)),format.raw/*237.33*/("""</td>
            <td>"""),_display_(Seq[Any](/*238.18*/transaction/*238.29*/.sender.getIdToken)),format.raw/*238.47*/("""</td>
            <td>"""),_display_(Seq[Any](/*239.18*/transaction/*239.29*/.senderCountry.name)),format.raw/*239.48*/("""</td>
            <td class="right" nowrap>"""),_display_(Seq[Any](/*240.39*/transaction/*240.50*/.senderAmountFormat)),format.raw/*240.69*/("""</td>
            <td>"""),_display_(Seq[Any](/*241.18*/transaction/*241.29*/.beneficiary.getIdToken)),format.raw/*241.52*/("""</td>
            <td>"""),_display_(Seq[Any](/*242.18*/transaction/*242.29*/.beneficiaryCountry.name)),format.raw/*242.53*/("""</td>
            <td class="right" nowrap>"""),_display_(Seq[Any](/*243.39*/transaction/*243.50*/.beneficiaryAmountFormat)),format.raw/*243.74*/("""</td>
            <td>"""),_display_(Seq[Any](/*244.18*/transaction/*244.29*/.forexReference.rateFormat)),format.raw/*244.55*/("""</td>
            <td class="right" nowrap>"""),_display_(Seq[Any](/*245.39*/transaction/*245.50*/.feesTotalFormat)),format.raw/*245.66*/("""</td>
            <td>"""),_display_(Seq[Any](/*246.18*/transaction/*246.29*/.statusText)),format.raw/*246.40*/("""</td>
            <td>"""),_display_(Seq[Any](/*247.18*/models/*247.24*/.Transaction.formatDate(transaction.createdTime, true))),format.raw/*247.78*/("""</td>
        </tr>
        """)))})),format.raw/*249.10*/("""
        </tbody>
    </table>

    <div id="pagination" class="pagination pagination-right">
        <ul>
            """),_display_(Seq[Any](/*255.14*/if(currentPage.hasPrev)/*255.37*/ {_display_(Seq[Any](format.raw/*255.39*/("""
            <li class="prev">
                <a href=""""),_display_(Seq[Any](/*257.27*/link(currentPage.getPageIndex - 1, null))),format.raw/*257.67*/("""">&larr; """),_display_(Seq[Any](/*257.77*/doku/*257.81*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*257.144*/("""</a>
            </li>
            """)))}/*259.15*/else/*259.20*/{_display_(Seq[Any](format.raw/*259.21*/("""
            <li class="prev">
                <a>&larr; """),_display_(Seq[Any](/*261.28*/doku/*261.32*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*261.95*/("""</a>
            </li>
            """)))})),format.raw/*263.14*/("""

            <li class="current">
                <a>Displaying """),_display_(Seq[Any](/*266.32*/currentPage/*266.43*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*266.77*/("""</a>
            </li>

            """),_display_(Seq[Any](/*269.14*/if(currentPage.hasNext)/*269.37*/ {_display_(Seq[Any](format.raw/*269.39*/("""
            <li class="next">
                <a href=""""),_display_(Seq[Any](/*271.27*/link(currentPage.getPageIndex + 1, null))),format.raw/*271.67*/("""">"""),_display_(Seq[Any](/*271.70*/doku/*271.74*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*271.133*/(""" &rarr;</a>
            </li>
            """)))}/*273.15*/else/*273.20*/{_display_(Seq[Any](format.raw/*273.21*/("""
            <li class="next">
                 <a>"""),_display_(Seq[Any](/*275.22*/doku/*275.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*275.85*/(""" &rarr;</a>
            </li>
            """)))})),format.raw/*277.14*/("""
        </ul>
    </div>
    """)))})),format.raw/*280.6*/("""
	""")))})),format.raw/*281.3*/("""
</div>
<div class="span5" id="viewContainer">
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*286.38*/routes/*286.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*286.84*/("""'></script>
""")))})),format.raw/*287.2*/("""
"""))}
    }
    
    def render(filterForm:Form[controllers.Report.FilterForm],currentPage:com.avaje.ebean.Page[models.Transaction],currentSortBy:String,currentOrder:String,userCorporateCountry:String): play.api.templates.Html = apply(filterForm,currentPage,currentSortBy,currentOrder,userCorporateCountry)
    
    def f:((Form[controllers.Report.FilterForm],com.avaje.ebean.Page[models.Transaction],String,String,String) => play.api.templates.Html) = (filterForm,currentPage,currentSortBy,currentOrder,userCorporateCountry) => apply(filterForm,currentPage,currentSortBy,currentOrder,userCorporateCountry)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:28 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/report/list.scala.html
                    HASH: 87a56657115e6f4a424f9fe3f180b4c763b3e1c6
                    MATRIX: 820->1|1328->1505|1342->1511|1436->1541|1485->1554|1510->1557|1622->1634|1656->1659|1696->1661|1744->1673|1801->1708|1840->1711|1867->1716|1892->1724|1904->1729|1943->1730|1981->1733|2007->1738|2041->1741|2072->455|2093->467|2604->967|2616->971|3084->1428|3103->1438|3197->180|3224->232|3251->338|3279->402|3307->453|3335->964|3364->1425|3393->1501|3423->1749|3461->1752|3489->1771|3528->1772|3601->1809|3614->1813|3697->1874|3754->1896|3770->1903|3806->1917|3902->1977|3937->1990|4074->2092|4292->2289|4384->2346|4468->2421|4508->2423|4549->2429|4767->2626|4804->2632|5002->2794|5074->2857|5114->2859|5156->2866|5193->2881|5230->2887|5286->2907|5316->2927|5356->2928|5399->2935|5482->3008|5523->3010|5600->3050|5614->3054|5708->3124|5853->3233|5913->3276|5954->3278|6001->3288|6043->3320|6083->3321|6147->3348|6174->3352|6214->3355|6241->3359|6293->3378|6334->3387|6403->3424|6446->3431|6603->3577|6645->3579|6689->3587|6890->3766|6929->3773|6954->3779|6968->3784|7008->3785|7051->3792|7249->3968|7287->3974|7395->4046|7425->4066|7465->4067|7508->4074|7591->4147|7632->4149|7709->4189|7723->4193|7819->4265|7964->4374|8024->4417|8065->4419|8112->4429|8154->4461|8194->4462|8258->4489|8285->4493|8325->4496|8352->4500|8404->4519|8445->4528|8514->4565|8557->4572|8714->4718|8756->4720|8800->4728|9008->4914|9047->4921|9072->4927|9086->4932|9126->4933|9169->4940|9374->5123|9412->5129|9543->5224|9779->5437|9872->5494|10104->5703|10336->5898|10350->5902|10447->5975|10628->6119|10696->6170|10737->6172|10808->6206|10822->6210|10848->6213|10888->6216|10948->6266|10988->6267|11044->6289|11084->6291|11099->6295|11130->6302|11190->6329|11522->6624|11538->6630|11575->6644|11643->6675|11657->6679|11740->6738|11848->6809|11862->6813|11947->6874|12023->6914|12056->6937|12097->6939|12139->6945|12186->6982|12226->6983|12300->7020|12314->7024|12409->7096|12450->7119|12464->7124|12504->7125|12586->7171|12636->7211|12677->7213|12733->7232|12747->7236|12848->7313|12896->7323|12935->7351|12965->7357|13014->7368|13029->7372|13127->7446|13170->7457|13265->7515|13332->7559|13453->7643|13522->7689|13597->7726|13612->7730|13708->7802|13942->7999|14053->8086|14104->8100|14210->8183|14261->8197|14369->8282|14420->8296|14530->8382|14581->8396|14703->8494|14754->8508|14874->8604|14925->8618|15042->8711|15093->8725|15222->8830|15273->8844|15400->8947|15451->8961|15553->9040|15604->9054|15704->9131|15755->9145|15861->9228|15912->9242|16022->9328|16116->9385|16172->9424|16213->9426|16294->9470|16315->9481|16349->9492|16399->9506|16420->9517|16460->9534|16510->9548|16531->9559|16567->9572|16627->9595|16648->9606|16689->9624|16749->9647|16770->9658|16812->9677|16893->9721|16914->9732|16956->9751|17016->9774|17037->9785|17083->9808|17143->9831|17164->9842|17211->9866|17292->9910|17313->9921|17360->9945|17420->9968|17441->9979|17490->10005|17571->10049|17592->10060|17631->10076|17691->10099|17712->10110|17746->10121|17806->10144|17822->10150|17899->10204|17961->10233|18118->10353|18151->10376|18192->10378|18286->10435|18349->10475|18396->10485|18410->10489|18497->10552|18553->10589|18567->10594|18607->10595|18702->10653|18716->10657|18802->10720|18871->10756|18974->10822|18995->10833|19052->10867|19126->10904|19159->10927|19200->10929|19294->10986|19357->11026|19397->11029|19411->11033|19494->11092|19557->11136|19571->11141|19611->11142|19700->11194|19714->11198|19796->11257|19872->11300|19935->11331|19970->11334|20099->11426|20115->11432|20178->11472|20223->11485
                    LINES: 26->1|41->54|41->54|43->54|44->55|44->55|45->56|45->56|45->56|46->57|46->57|46->57|46->57|47->58|47->58|47->58|48->59|48->59|49->60|51->13|51->13|64->28|64->28|83->49|83->49|86->1|87->4|88->7|89->10|90->12|91->26|93->47|95->51|98->62|100->64|100->64|100->64|103->67|103->67|103->67|107->71|107->71|107->71|110->74|110->74|113->77|117->81|120->84|120->84|120->84|121->85|125->89|126->90|132->96|132->96|132->96|133->97|133->97|134->98|136->100|136->100|136->100|137->101|137->101|137->101|138->102|138->102|138->102|141->105|141->105|141->105|142->106|142->106|142->106|143->107|143->107|143->107|143->107|144->108|145->109|148->112|149->113|149->113|149->113|150->114|153->117|154->118|155->119|155->119|155->119|156->120|159->123|160->124|164->128|164->128|164->128|165->129|165->129|165->129|166->130|166->130|166->130|169->133|169->133|169->133|170->134|170->134|170->134|171->135|171->135|171->135|171->135|172->136|173->137|176->140|177->141|177->141|177->141|178->142|181->145|182->146|183->147|183->147|183->147|184->148|187->151|188->152|193->157|196->160|199->163|202->166|208->172|208->172|208->172|212->176|212->176|212->176|213->177|213->177|213->177|213->177|213->177|213->177|213->177|213->177|213->177|213->177|214->178|226->190|226->190|226->190|226->190|226->190|226->190|227->191|227->191|227->191|232->196|232->196|232->196|233->197|233->197|233->197|235->199|235->199|235->199|237->201|237->201|237->201|240->204|240->204|240->204|241->205|241->205|241->205|241->205|241->205|241->205|241->205|241->205|241->205|242->206|244->208|244->208|245->209|245->209|245->209|245->209|245->209|253->217|253->217|254->218|254->218|255->219|255->219|256->220|256->220|257->221|257->221|258->222|258->222|259->223|259->223|260->224|260->224|261->225|261->225|262->226|262->226|263->227|263->227|264->228|264->228|265->229|265->229|269->233|269->233|269->233|271->235|271->235|271->235|272->236|272->236|272->236|273->237|273->237|273->237|274->238|274->238|274->238|275->239|275->239|275->239|276->240|276->240|276->240|277->241|277->241|277->241|278->242|278->242|278->242|279->243|279->243|279->243|280->244|280->244|280->244|281->245|281->245|281->245|282->246|282->246|282->246|283->247|283->247|283->247|285->249|291->255|291->255|291->255|293->257|293->257|293->257|293->257|293->257|295->259|295->259|295->259|297->261|297->261|297->261|299->263|302->266|302->266|302->266|305->269|305->269|305->269|307->271|307->271|307->271|307->271|307->271|309->273|309->273|309->273|311->275|311->275|311->275|313->277|316->280|317->281|322->286|322->286|322->286|323->287
                    -- GENERATED --
                */
            