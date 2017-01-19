
package views.html.customer

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
object relate extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[com.avaje.ebean.Page[models.Customer],models.Customer,String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Customer], customer: models.Customer, currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {
def /*23.2*/header/*23.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*23.38*/("""
<th class=""""),_display_(Seq[Any](/*24.13*/key)),format.raw/*24.16*/("""" style="text-align: center;">
	"""),_display_(Seq[Any](/*25.3*/if(key && key.length > 0)/*25.28*/ {_display_(Seq[Any](format.raw/*25.30*/("""
		<a href=""""),_display_(Seq[Any](/*26.13*/link(currentPage.getPageIndex, key))),format.raw/*26.48*/("""">"""),_display_(Seq[Any](/*26.51*/title)),format.raw/*26.56*/("""</a>
	""")))}/*27.4*/else/*27.9*/{_display_(Seq[Any](format.raw/*27.10*/("""
		"""),_display_(Seq[Any](/*28.4*/title)),format.raw/*28.9*/("""
	""")))})),format.raw/*29.3*/("""
</th>
""")))};def /*3.2*/link/*3.6*/(newPage:Int, newSortBy:String) = {{

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
    routes.Customer.relate(customer.id, newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.149*/("""

"""),format.raw/*21.2*/("""

"""),format.raw/*31.2*/("""

"""),_display_(Seq[Any](/*33.2*/layout("Customers Relate")/*33.28*/{_display_(Seq[Any](format.raw/*33.29*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*36.10*/doku/*36.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG523","Customers Relate"))),format.raw/*36.85*/(""" """),_display_(Seq[Any](/*36.87*/customer/*36.95*/.firstName)),format.raw/*36.105*/(""" """),_display_(Seq[Any](/*36.107*/customer/*36.115*/.lastName)),format.raw/*36.124*/("""</h1>
</div>

<div class="row">
	<div class="span12">
		<div class="well">
			<form action=""""),_display_(Seq[Any](/*42.19*/link(0, "name"))),format.raw/*42.34*/("""" method="GET" class="form-search">
				<input type="hidden" value=""""),_display_(Seq[Any](/*43.34*/customer/*43.42*/.id)),format.raw/*43.45*/("""" name="id">
				<div class="input-append">
					<input type="search" id="searchbox" name="f" class="search-query span4" value=""""),_display_(Seq[Any](/*45.86*/currentFilter)),format.raw/*45.99*/("""" placeholder='"""),_display_(Seq[Any](/*45.115*/doku/*45.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG109","Filter by Personal ID / Name..."))),format.raw/*45.205*/("""'>
					<button type="submit" class="btn" style="border-radius:0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*46.96*/doku/*46.100*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*46.161*/("""</button>
					<a class="btn success" style="border-radius:0px;" href=""""),_display_(Seq[Any](/*47.63*/routes/*47.69*/.Customer.addRelate(customer.id))),format.raw/*47.101*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*47.130*/doku/*47.134*/.kirimdoku.util.MultiLanguage.getLanguage("LANG375","Add new"))),format.raw/*47.196*/("""</a>
					<a class="btn success" href=""""),_display_(Seq[Any](/*48.36*/routes/*48.42*/.Customer.list())),format.raw/*48.58*/(""""><i class="icon-repeat"></i> """),_display_(Seq[Any](/*48.89*/doku/*48.93*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*48.152*/("""</a>
				</div>
			</form>
		</div>
	</div>
</div>

"""),_display_(Seq[Any](/*55.2*/partial/*55.9*/.flash_alert())),format.raw/*55.23*/("""

<div class="row">
    <div class="span7">

        """),_display_(Seq[Any](/*60.10*/if(currentPage.getTotalRowCount == 0)/*60.47*/{_display_(Seq[Any](format.raw/*60.48*/("""
            <div class="well">
                <em>"""),_display_(Seq[Any](/*62.22*/doku/*62.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG280","Data results are not found"))),format.raw/*62.107*/("""</em>
            </div>
        """)))}/*64.11*/else/*64.16*/{_display_(Seq[Any](format.raw/*64.17*/("""
            <table class="customer table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*68.26*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID")))),format.raw/*68.110*/("""
                        """),_display_(Seq[Any](/*69.26*/header("country", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")))),format.raw/*69.115*/("""
                        """),_display_(Seq[Any](/*70.26*/header("firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First Name")))),format.raw/*70.116*/("""
                        """),_display_(Seq[Any](/*71.26*/header("lastName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last Name")))),format.raw/*71.114*/("""
                        """),_display_(Seq[Any](/*72.26*/header("phoneNumber", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone Number")))),format.raw/*72.120*/("""
                        """),_display_(Seq[Any](/*73.26*/header("action", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*73.109*/("""
                    </tr>
                </thead>
                <tbody>
                    """),_display_(Seq[Any](/*77.22*/for(customerRelate <- currentPage.getList) yield /*77.64*/ {_display_(Seq[Any](format.raw/*77.66*/("""
                        <tr>
                            <td>"""),_display_(Seq[Any](/*79.34*/customerRelate/*79.48*/.getIdToken)),format.raw/*79.59*/("""</td>
							<td>"""),_display_(Seq[Any](/*80.13*/customerRelate/*80.27*/.country.name)),format.raw/*80.40*/("""</td>
                            <td>"""),_display_(Seq[Any](/*81.34*/customerRelate/*81.48*/.firstName)),format.raw/*81.58*/("""</td>
                            <td>"""),_display_(Seq[Any](/*82.34*/customerRelate/*82.48*/.lastName)),format.raw/*82.57*/("""</td>
                            <td>"""),_display_(Seq[Any](/*83.34*/customerRelate/*83.48*/.phoneNumber)),format.raw/*83.60*/("""</td>
							<td class="actions">
								<a href=""""),_display_(Seq[Any](/*85.19*/routes/*85.25*/.Customer.summary(customerRelate.id))),format.raw/*85.61*/("""" class="btn-detail btn-customer-detail" data-remote="true">"""),_display_(Seq[Any](/*85.122*/doku/*85.126*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*85.187*/("""</a>
								<a href=""""),_display_(Seq[Any](/*86.19*/routes/*86.25*/.Customer.deleteRelate(customer.id, customerRelate.id))),format.raw/*86.79*/("""">"""),_display_(Seq[Any](/*86.82*/doku/*86.86*/.kirimdoku.util.MultiLanguage.getLanguage("LANG412","Delete"))),format.raw/*86.147*/("""</a>
							</td>
                        </tr>
                    """)))})),format.raw/*89.22*/("""
                </tbody>
            </table>

            <div id="pagination" class="pagination pagination-right">
                <ul>
                    """),_display_(Seq[Any](/*95.22*/if(currentPage.hasPrev)/*95.45*/ {_display_(Seq[Any](format.raw/*95.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*97.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*97.75*/("""">&larr; """),_display_(Seq[Any](/*97.85*/doku/*97.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*97.152*/("""</a>
                    </li>
                    """)))}/*99.23*/else/*99.28*/{_display_(Seq[Any](format.raw/*99.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*101.36*/doku/*101.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*101.103*/("""</a>
                    </li>
                    """)))})),format.raw/*103.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*106.39*/currentPage/*106.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*106.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*109.22*/if(currentPage.hasNext)/*109.45*/ {_display_(Seq[Any](format.raw/*109.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*111.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*111.75*/("""">"""),_display_(Seq[Any](/*111.78*/doku/*111.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*111.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*113.23*/else/*113.28*/{_display_(Seq[Any](format.raw/*113.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*115.29*/doku/*115.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*115.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*117.22*/("""
                </ul>
            </div>
        """)))})),format.raw/*120.10*/("""
    </div>
	<div class="span5">
	   <div class="well" id="customer-detail-container">
			"""),_display_(Seq[Any](/*124.5*/doku/*124.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG281","You can view detail of customer by clicking 'Detail' on the desired row of customer"))),format.raw/*124.147*/("""
		</div>
    </div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*129.38*/routes/*129.44*/.Assets.at("javascripts/customer.js"))),format.raw/*129.81*/("""'></script>
""")))})),format.raw/*130.2*/("""
"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Customer],customer:models.Customer,currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(currentPage,customer,currentSortBy,currentOrder,currentFilter)
    
    def f:((com.avaje.ebean.Page[models.Customer],models.Customer,String,String,String) => play.api.templates.Html) = (currentPage,customer,currentSortBy,currentOrder,currentFilter) => apply(currentPage,customer,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/relate.scala.html
                    HASH: 53b57ac3c12d5252663cedd615185e56c5f9b9f2
                    MATRIX: 801->1|1009->624|1023->630|1117->660|1166->673|1191->676|1259->709|1293->734|1333->736|1382->749|1439->784|1478->787|1505->792|1530->800|1542->805|1581->806|1620->810|1646->815|1680->818|1710->151|1721->155|2218->148|2247->621|2276->826|2314->829|2349->855|2388->856|2461->893|2474->897|2567->968|2605->970|2622->978|2655->988|2694->990|2712->998|2744->1007|2873->1100|2910->1115|3015->1184|3032->1192|3057->1195|3222->1324|3257->1337|3310->1353|3324->1357|3433->1443|3567->1541|3581->1545|3665->1606|3773->1678|3788->1684|3843->1716|3909->1745|3923->1749|4008->1811|4084->1851|4099->1857|4137->1873|4204->1904|4217->1908|4299->1967|4387->2020|4402->2027|4438->2041|4528->2095|4574->2132|4613->2133|4702->2186|4715->2190|4819->2271|4872->2306|4885->2311|4924->2312|5123->2475|5230->2559|5292->2585|5404->2674|5466->2700|5579->2790|5641->2816|5752->2904|5814->2930|5931->3024|5993->3050|6099->3133|6232->3230|6290->3272|6330->3274|6429->3337|6452->3351|6485->3362|6539->3380|6562->3394|6597->3407|6672->3446|6695->3460|6727->3470|6802->3509|6825->3523|6856->3532|6931->3571|6954->3585|6988->3597|7076->3649|7091->3655|7149->3691|7247->3752|7261->3756|7345->3817|7404->3840|7419->3846|7495->3900|7534->3903|7547->3907|7631->3968|7732->4037|7928->4197|7960->4220|8000->4222|8109->4295|8171->4335|8217->4345|8230->4349|8316->4412|8387->4465|8400->4470|8439->4471|8550->4545|8564->4549|8651->4612|8736->4664|8853->4744|8874->4755|8931->4789|9020->4841|9053->4864|9094->4866|9204->4939|9267->4979|9307->4982|9321->4986|9404->5045|9483->5105|9497->5110|9537->5111|9641->5178|9655->5182|9737->5241|9829->5300|9913->5351|10040->5442|10053->5446|10215->5584|10318->5650|10334->5656|10394->5693|10439->5706
                    LINES: 26->1|28->23|28->23|30->23|31->24|31->24|32->25|32->25|32->25|33->26|33->26|33->26|33->26|34->27|34->27|34->27|35->28|35->28|36->29|38->3|38->3|57->1|59->21|61->31|63->33|63->33|63->33|66->36|66->36|66->36|66->36|66->36|66->36|66->36|66->36|66->36|72->42|72->42|73->43|73->43|73->43|75->45|75->45|75->45|75->45|75->45|76->46|76->46|76->46|77->47|77->47|77->47|77->47|77->47|77->47|78->48|78->48|78->48|78->48|78->48|78->48|85->55|85->55|85->55|90->60|90->60|90->60|92->62|92->62|92->62|94->64|94->64|94->64|98->68|98->68|99->69|99->69|100->70|100->70|101->71|101->71|102->72|102->72|103->73|103->73|107->77|107->77|107->77|109->79|109->79|109->79|110->80|110->80|110->80|111->81|111->81|111->81|112->82|112->82|112->82|113->83|113->83|113->83|115->85|115->85|115->85|115->85|115->85|115->85|116->86|116->86|116->86|116->86|116->86|116->86|119->89|125->95|125->95|125->95|127->97|127->97|127->97|127->97|127->97|129->99|129->99|129->99|131->101|131->101|131->101|133->103|136->106|136->106|136->106|139->109|139->109|139->109|141->111|141->111|141->111|141->111|141->111|143->113|143->113|143->113|145->115|145->115|145->115|147->117|150->120|154->124|154->124|154->124|159->129|159->129|159->129|160->130
                    -- GENERATED --
                */
            