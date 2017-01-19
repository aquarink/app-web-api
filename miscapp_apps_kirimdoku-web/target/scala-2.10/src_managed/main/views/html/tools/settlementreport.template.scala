
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
object settlementreport extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[models.SettlementReportRequest],List[models.Corporate],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(settlementReportRequest : Form[models.SettlementReportRequest], corporates : List[models.Corporate]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import java.util.Calendar

import java.text.SimpleDateFormat

def /*47.2*/header/*47.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*47.38*/("""
	<th class=""""),_display_(Seq[Any](/*48.14*/key)),format.raw/*48.17*/("""">
	"""),_display_(Seq[Any](/*49.3*/if(key && key.length > 0)/*49.28*/ {_display_(Seq[Any](format.raw/*49.30*/("""
		<a href="#" onclick=""""),_display_(Seq[Any](/*50.25*/link(settlementReportRequest.get().getSettlementReports().getPageIndex, key))),format.raw/*50.101*/("""">"""),_display_(Seq[Any](/*50.104*/title)),format.raw/*50.109*/("""</a>
	""")))}/*51.4*/else/*51.9*/{_display_(Seq[Any](format.raw/*51.10*/("""
		"""),_display_(Seq[Any](/*52.4*/title)),format.raw/*52.9*/("""
	""")))})),format.raw/*53.3*/("""
	</th>
""")))};def /*11.2*/dateFormat/*11.12*/(d:Date) = {{
    new SimpleDateFormat("yyyy-MM-dd") format d
}};def /*15.2*/minLimit/*15.10*/() = {{
	var newDateCalendar = Calendar.getInstance();
	newDateCalendar.setTime(new Date());
	newDateCalendar.add(Calendar.MONTH, -3);
	dateFormat(newDateCalendar.getTime());
}};def /*22.2*/maxLimit/*22.10*/() = {{
	dateFormat(new Date());
}};def /*26.2*/link/*26.6*/(newPage:Int, newSortBy:String) = {{

    var sortBy = settlementReportRequest.get().getSortBy
    var order = settlementReportRequest.get().getOrder

    if(newSortBy != null) {
        sortBy = newSortBy
        if(settlementReportRequest.get().getSortBy == newSortBy) {
            if(settlementReportRequest.get().getOrder == "asc") {
                order = "desc"
            } else {
                order = "asc"
            }
        } else {
            order = "asc"
        }
    }
	"gotoPage("+newPage+",'"+sortBy+"','"+order+"')"
}};def /*57.2*/pager/*57.7*/(page:Int) = {{
	var pageLink = "";
	
	if (page >= 0){
		pageLink = "gotoPage("+page+",'"+settlementReportRequest.get().getSortBy+"','"+settlementReportRequest.get().getOrder+"')";
	}	
	pageLink
}};
Seq[Any](format.raw/*1.103*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*7.1*/("""
"""),format.raw/*10.1*/("""
"""),format.raw/*13.2*/("""

"""),format.raw/*20.2*/("""

"""),format.raw/*24.2*/("""

"""),format.raw/*44.2*/("""


"""),format.raw/*55.2*/("""

"""),format.raw/*64.2*/("""

"""),_display_(Seq[Any](/*66.2*/layout("Settlement Report", Seq(
routes.SettlementReport.list().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG444","Settlement Report")
))/*68.3*/{_display_(Seq[Any](format.raw/*68.4*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*71.10*/doku/*71.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG444","Settlement Report"))),format.raw/*71.86*/("""</h1>
</div>

<div>
	"""),_display_(Seq[Any](/*75.3*/partial/*75.10*/.flash_alert())),format.raw/*75.24*/("""
	
	<div class="well">
		<form action=""""),_display_(Seq[Any](/*78.18*/routes/*78.24*/.SettlementReport.list())),format.raw/*78.48*/("""" method="post">
		<input type="hidden" name="sortBy" id="sortBy">
		<input type="hidden" name="page" id="page" value="0">
		<input type="hidden" name="order" id="order" value="asc">
		<table cellpadding="3" cellspacing="2">
			<tr>
				<td>
					"""),_display_(Seq[Any](/*85.7*/doku/*85.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Corporate Name"))),format.raw/*85.80*/("""
				</td>
				<td>
					<select class="control-group" name="corporate" id="corporate">
						<option value="">--All Corporate--</option>
					"""),_display_(Seq[Any](/*90.7*/for(corporate <- corporates) yield /*90.35*/ {_display_(Seq[Any](format.raw/*90.37*/("""
						<option value=""""),_display_(Seq[Any](/*91.23*/corporate/*91.32*/.code)),format.raw/*91.37*/("""" """),_display_(Seq[Any](/*91.40*/if(corporate.code == settlementReportRequest.get().getCorporate)/*91.104*/{_display_(Seq[Any](format.raw/*91.105*/(""" selected="selected" """)))})),format.raw/*91.127*/(""" >"""),_display_(Seq[Any](/*91.130*/corporate/*91.139*/.name)),format.raw/*91.144*/("""</option>
					""")))})),format.raw/*92.7*/("""
					</select>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					"""),_display_(Seq[Any](/*101.7*/doku/*101.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG445","Report Date Range"))),format.raw/*101.83*/("""
				</td>
				<td>
					<input type="date" value=""""),_display_(Seq[Any](/*104.33*/settlementReportRequest/*104.56*/.get().getReportDateStart)),format.raw/*104.81*/("""" name="reportDateStart" min=""""),_display_(Seq[Any](/*104.112*/minLimit())),format.raw/*104.122*/("""" max=""""),_display_(Seq[Any](/*104.130*/maxLimit())),format.raw/*104.140*/("""">
				</td>
				<td>
					<input type="date" value=""""),_display_(Seq[Any](/*107.33*/settlementReportRequest/*107.56*/.get().getReportDateEnd)),format.raw/*107.79*/("""" name="reportDateEnd" min=""""),_display_(Seq[Any](/*107.108*/minLimit())),format.raw/*107.118*/("""" max=""""),_display_(Seq[Any](/*107.126*/maxLimit())),format.raw/*107.136*/("""">
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<a class="btn" href=""""),_display_(Seq[Any](/*115.28*/routes/*115.34*/.SettlementReport.list())),format.raw/*115.58*/(""""><i class="icon-repeat"></i> """),_display_(Seq[Any](/*115.89*/doku/*115.93*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*115.153*/("""</a>
					<button class="btn btn-primary" type="submit" id="searchBtn"><i class="icon-search icon-white"></i> """),_display_(Seq[Any](/*116.107*/doku/*116.111*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*116.172*/("""</button>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		</form>
	</div>
	"""),_display_(Seq[Any](/*125.3*/if(settlementReportRequest.get().getSettlementReports != null)/*125.65*/{_display_(Seq[Any](format.raw/*125.66*/("""
    <table class="transaction table-bordered table-striped table-condensed" style="width:100%;text-align: center;">
        <thead>
        <tr>
            """),_display_(Seq[Any](/*129.14*/header("0", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG446","Report Date")))),format.raw/*129.97*/("""
            """),_display_(Seq[Any](/*130.14*/header("1", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG316","Corporate Code")))),format.raw/*130.100*/("""
            """),_display_(Seq[Any](/*131.14*/header("2", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Corporate Name")))),format.raw/*131.100*/("""
            """),_display_(Seq[Any](/*132.14*/header("3", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG447","Filename")))),format.raw/*132.94*/("""
            """),_display_(Seq[Any](/*133.14*/header("4", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*133.92*/("""
        </tr>
        </thead>
        <tbody>
        """),_display_(Seq[Any](/*137.10*/if(settlementReportRequest.get().getSettlementReports != null && settlementReportRequest.get().getSettlementReports().getList != null && settlementReportRequest.get().getSettlementReports().getList().size > 0)/*137.219*/{_display_(Seq[Any](format.raw/*137.220*/("""
        	"""),_display_(Seq[Any](/*138.11*/for(settlementReport <- settlementReportRequest.get().getSettlementReports().getList) yield /*138.96*/ {_display_(Seq[Any](format.raw/*138.98*/("""
        	<tr>
        		<td style="width: 90px;">"""),_display_(Seq[Any](/*140.37*/dateFormat(settlementReport.getReportDate))),format.raw/*140.79*/("""</td>
        		<td style="width: 80px;">"""),_display_(Seq[Any](/*141.37*/settlementReport/*141.53*/.getCorporate.code)),format.raw/*141.71*/("""</td>
        		<td style="width: 200px; text-align: left;">"""),_display_(Seq[Any](/*142.56*/settlementReport/*142.72*/.getCorporate.name)),format.raw/*142.90*/("""</td>
        		<td style="width: 150px; text-align: left;">"""),_display_(Seq[Any](/*143.56*/settlementReport/*143.72*/.getFilename)),format.raw/*143.84*/("""</td>
        		"""),_display_(Seq[Any](/*144.12*/if(settlementReport.getStatus().equals("D"))/*144.56*/{_display_(Seq[Any](format.raw/*144.57*/("""
        			<td style="width: 80px;"><a target="_blank" href=""""),_display_(Seq[Any](/*145.63*/(routes.SettlementReport.list()))),format.raw/*145.95*/("""/"""),_display_(Seq[Any](/*145.97*/(settlementReport.getFilename))),format.raw/*145.127*/(""".xls">"""),_display_(Seq[Any](/*145.134*/doku/*145.138*/.kirimdoku.util.MultiLanguage.getLanguage("LANG448","Download"))),format.raw/*145.201*/("""</a></td>
        		""")))}/*146.12*/else/*146.16*/{_display_(Seq[Any](format.raw/*146.17*/("""
        			<td style="width: 80px;">
        			<!--<form style="margin:0px;" action=""""),_display_(Seq[Any](/*148.51*/routes/*148.57*/.SettlementReport.generateById(settlementReport.getId))),format.raw/*148.111*/("""" method="post">
					<input type="hidden" name="sortBy" id="sortBys" value=""""),_display_(Seq[Any](/*149.62*/settlementReportRequest/*149.85*/.get().getSortBy)),format.raw/*149.101*/("""">
					<input type="hidden" name="page" id="pages" value=""""),_display_(Seq[Any](/*150.58*/settlementReportRequest/*150.81*/.get().getPage)),format.raw/*150.95*/("""">
					<input type="hidden" name="order" id="orders" value=""""),_display_(Seq[Any](/*151.60*/settlementReportRequest/*151.83*/.get().getOrder)),format.raw/*151.98*/("""">
					<input type="hidden" name="corporate" id="corporates" value=""""),_display_(Seq[Any](/*152.68*/settlementReportRequest/*152.91*/.get().getCorporate)),format.raw/*152.110*/("""">
					<input type="hidden" name="reportDateStart" id="reportDateStarts"  value=""""),_display_(Seq[Any](/*153.81*/settlementReportRequest/*153.104*/.get().getReportDateStart)),format.raw/*153.129*/("""">
					<input type="hidden" name="reportDateEnd" id="reportDateEnds" value=""""),_display_(Seq[Any](/*154.76*/settlementReportRequest/*154.99*/.get().getReportDateEnd)),format.raw/*154.122*/("""">
					<button type="submit" class="btn">-->
					"""),_display_(Seq[Any](/*156.7*/doku/*156.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG449","Generate"))),format.raw/*156.74*/("""
					<!--</button>
					</form>-->
        			</td>
        		""")))})),format.raw/*160.12*/("""
        	</tr>
        	""")))})),format.raw/*162.11*/("""
        """)))})),format.raw/*163.10*/("""
        </tbody>
    </table>
	    <script type="text/javascript">
	    	
	    	function gotoPage(page,sortBy,order) """),format.raw/*168.44*/("""{"""),format.raw/*168.45*/("""
	    		$("#page").val(page);
	    		$("#sortBy").val(sortBy);
	    		$("#order").val(order);
	    		$("#searchBtn").click();
	    	"""),format.raw/*173.7*/("""}"""),format.raw/*173.8*/("""
	    
	    </script>
	    <div id="pagination" class="pagination pagination-right">
			<ul>
				"""),_display_(Seq[Any](/*178.6*/if(settlementReportRequest.get().getSettlementReports != null && settlementReportRequest.get().getSettlementReports().hasPrev)/*178.132*/ {_display_(Seq[Any](format.raw/*178.134*/("""
				<li class="prev">
					<a href="#" onclick=""""),_display_(Seq[Any](/*180.28*/pager(settlementReportRequest.get().getSettlementReports().getPageIndex - 1))),format.raw/*180.104*/("""">&larr; """),_display_(Seq[Any](/*180.114*/doku/*180.118*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*180.181*/("""</a>
				</li>
				""")))}/*182.7*/else/*182.12*/{_display_(Seq[Any](format.raw/*182.13*/("""
				<li class="prev">
					<a style="color: #cccccc;">&larr; """),_display_(Seq[Any](/*184.41*/doku/*184.45*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*184.108*/("""</a>
				</li>
				""")))})),format.raw/*186.6*/("""
				"""),_display_(Seq[Any](/*187.6*/if(settlementReportRequest.get().getSettlementReports != null)/*187.68*/{_display_(Seq[Any](format.raw/*187.69*/("""
				<li class="current">
					<a>Displaying """),_display_(Seq[Any](/*189.21*/settlementReportRequest/*189.44*/.get().getSettlementReports().getDisplayXtoYofZ(" to ", " of "))),format.raw/*189.107*/("""</a>
				</li>
				""")))})),format.raw/*191.6*/("""
				"""),_display_(Seq[Any](/*192.6*/if(settlementReportRequest.get().getSettlementReports != null && settlementReportRequest.get().getSettlementReports().hasNext)/*192.132*/ {_display_(Seq[Any](format.raw/*192.134*/("""
				<li class="next">
					<a href="#" onclick=""""),_display_(Seq[Any](/*194.28*/pager(settlementReportRequest.get().getSettlementReports().getPageIndex + 1))),format.raw/*194.104*/("""">"""),_display_(Seq[Any](/*194.107*/doku/*194.111*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*194.170*/(""" &rarr;</a>
				</li>
				""")))}/*196.7*/else/*196.12*/{_display_(Seq[Any](format.raw/*196.13*/("""
				<li class="next">
					<a style="color: #cccccc;">"""),_display_(Seq[Any](/*198.34*/doku/*198.38*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*198.97*/(""" &rarr;</a>
				</li>
				""")))})),format.raw/*200.6*/("""
			</ul>
	    </div>
	""")))})),format.raw/*203.3*/("""
	<br>&nbsp;<br>&nbsp;
</div>
<div class="span5" id="viewContainer">
</div>

""")))})),format.raw/*209.2*/("""
"""))}
    }
    
    def render(settlementReportRequest:Form[models.SettlementReportRequest],corporates:List[models.Corporate]): play.api.templates.Html = apply(settlementReportRequest,corporates)
    
    def f:((Form[models.SettlementReportRequest],List[models.Corporate]) => play.api.templates.Html) = (settlementReportRequest,corporates) => apply(settlementReportRequest,corporates)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/settlementreport.scala.html
                    HASH: 096475209ac6f05c3d110613d40bb6699c05ac6f
                    MATRIX: 793->1|1172->1186|1186->1192|1280->1222|1330->1236|1355->1239|1395->1244|1429->1269|1469->1271|1530->1296|1629->1372|1669->1375|1697->1380|1722->1388|1734->1393|1773->1394|1812->1398|1838->1403|1872->1406|1904->325|1923->335|2000->401|2017->409|2207->588|2224->596|2272->633|2284->637|2843->1418|2856->1423|3083->102|3110->154|3137->260|3165->323|3193->398|3222->585|3251->630|3280->1182|3310->1415|3339->1619|3377->1622|3540->1777|3578->1778|3651->1815|3664->1819|3758->1891|3815->1913|3831->1920|3867->1934|3943->1974|3958->1980|4004->2004|4287->2252|4300->2256|4391->2325|4570->2469|4614->2497|4654->2499|4713->2522|4731->2531|4758->2536|4797->2539|4871->2603|4911->2604|4966->2626|5006->2629|5025->2638|5053->2643|5100->2659|5225->2748|5239->2752|5334->2824|5423->2876|5456->2899|5504->2924|5573->2955|5607->2965|5653->2973|5687->2983|5778->3037|5811->3060|5857->3083|5924->3112|5958->3122|6004->3130|6038->3140|6172->3237|6188->3243|6235->3267|6303->3298|6317->3302|6401->3362|6550->3473|6565->3477|6650->3538|6777->3629|6849->3691|6889->3692|7085->3851|7191->3934|7242->3948|7352->4034|7403->4048|7513->4134|7564->4148|7667->4228|7718->4242|7819->4320|7913->4377|8133->4586|8174->4587|8222->4598|8324->4683|8365->4685|8453->4736|8518->4778|8597->4820|8623->4836|8664->4854|8762->4915|8788->4931|8829->4949|8927->5010|8953->5026|8988->5038|9042->5055|9096->5099|9136->5100|9236->5163|9291->5195|9330->5197|9384->5227|9429->5234|9444->5238|9531->5301|9572->5322|9586->5326|9626->5327|9751->5415|9767->5421|9845->5475|9960->5553|9993->5576|10033->5592|10130->5652|10163->5675|10200->5689|10299->5751|10332->5774|10370->5789|10477->5859|10510->5882|10553->5901|10673->5984|10707->6007|10756->6032|10871->6110|10904->6133|10951->6156|11039->6208|11053->6212|11139->6275|11236->6339|11295->6365|11338->6375|11485->6493|11515->6494|11675->6626|11704->6627|11838->6725|11975->6851|12017->6853|12104->6903|12204->6979|12252->6989|12267->6993|12354->7056|12393->7077|12407->7082|12447->7083|12547->7146|12561->7150|12648->7213|12700->7233|12742->7239|12814->7301|12854->7302|12937->7348|12970->7371|13057->7434|13109->7454|13151->7460|13288->7586|13330->7588|13417->7638|13517->7714|13558->7717|13573->7721|13656->7780|13702->7808|13716->7813|13756->7814|13849->7870|13863->7874|13945->7933|14004->7960|14060->7984|14170->8062
                    LINES: 26->1|39->47|39->47|41->47|42->48|42->48|43->49|43->49|43->49|44->50|44->50|44->50|44->50|45->51|45->51|45->51|46->52|46->52|47->53|49->11|49->11|51->15|51->15|56->22|56->22|58->26|58->26|76->57|76->57|84->1|85->4|86->7|87->10|88->13|90->20|92->24|94->44|97->55|99->64|101->66|103->68|103->68|106->71|106->71|106->71|110->75|110->75|110->75|113->78|113->78|113->78|120->85|120->85|120->85|125->90|125->90|125->90|126->91|126->91|126->91|126->91|126->91|126->91|126->91|126->91|126->91|126->91|127->92|136->101|136->101|136->101|139->104|139->104|139->104|139->104|139->104|139->104|139->104|142->107|142->107|142->107|142->107|142->107|142->107|142->107|150->115|150->115|150->115|150->115|150->115|150->115|151->116|151->116|151->116|160->125|160->125|160->125|164->129|164->129|165->130|165->130|166->131|166->131|167->132|167->132|168->133|168->133|172->137|172->137|172->137|173->138|173->138|173->138|175->140|175->140|176->141|176->141|176->141|177->142|177->142|177->142|178->143|178->143|178->143|179->144|179->144|179->144|180->145|180->145|180->145|180->145|180->145|180->145|180->145|181->146|181->146|181->146|183->148|183->148|183->148|184->149|184->149|184->149|185->150|185->150|185->150|186->151|186->151|186->151|187->152|187->152|187->152|188->153|188->153|188->153|189->154|189->154|189->154|191->156|191->156|191->156|195->160|197->162|198->163|203->168|203->168|208->173|208->173|213->178|213->178|213->178|215->180|215->180|215->180|215->180|215->180|217->182|217->182|217->182|219->184|219->184|219->184|221->186|222->187|222->187|222->187|224->189|224->189|224->189|226->191|227->192|227->192|227->192|229->194|229->194|229->194|229->194|229->194|231->196|231->196|231->196|233->198|233->198|233->198|235->200|238->203|244->209
                    -- GENERATED --
                */
            