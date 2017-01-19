
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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[com.avaje.ebean.Page[models.Customer],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Customer], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
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
    routes.Customer.list(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.122*/("""

"""),format.raw/*21.2*/("""

"""),format.raw/*31.2*/("""

"""),_display_(Seq[Any](/*33.2*/layout("Customers Management")/*33.32*/{_display_(Seq[Any](format.raw/*33.33*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*36.10*/doku/*36.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG278","Customers Management"))),format.raw/*36.89*/("""</h1>
</div>

<div class="row">
	<div class="span12">
		<div class="well">
			<form action=""""),_display_(Seq[Any](/*42.19*/link(0, "name"))),format.raw/*42.34*/("""" method="GET" class="form-search">
				<div class="input-append">
					<input type="search" id="searchbox" name="f" class="search-query span4" value=""""),_display_(Seq[Any](/*44.86*/currentFilter)),format.raw/*44.99*/("""" placeholder='"""),_display_(Seq[Any](/*44.115*/doku/*44.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG109","Filter by Personal ID / Name..."))),format.raw/*44.205*/("""'>
					<button type="submit" class="btn" style="border-radius:0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*45.96*/doku/*45.100*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*45.161*/("""</button>
					<a class="btn success" href=""""),_display_(Seq[Any](/*46.36*/routes/*46.42*/.Customer.create())),format.raw/*46.60*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*46.89*/doku/*46.93*/.kirimdoku.util.MultiLanguage.getLanguage("LANG375","Add new"))),format.raw/*46.155*/("""</a>
				</div>
			</form>
		</div>
	</div>
</div>

"""),_display_(Seq[Any](/*53.2*/partial/*53.9*/.flash_alert())),format.raw/*53.23*/("""

<div class="row">
    <div class="span7">

        """),_display_(Seq[Any](/*58.10*/if(currentPage.getTotalRowCount == 0)/*58.47*/{_display_(Seq[Any](format.raw/*58.48*/("""
            <div class="well">
                <em>"""),_display_(Seq[Any](/*60.22*/doku/*60.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG280","Data results are not found"))),format.raw/*60.107*/("""</em>
            </div>
        """)))}/*62.11*/else/*62.16*/{_display_(Seq[Any](format.raw/*62.17*/("""
            <table class="customer table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*66.26*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID")))),format.raw/*66.110*/("""
                        """),_display_(Seq[Any](/*67.26*/header("country", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")))),format.raw/*67.115*/("""
                        """),_display_(Seq[Any](/*68.26*/header("firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First Name")))),format.raw/*68.116*/("""
                        """),_display_(Seq[Any](/*69.26*/header("lastName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last Name")))),format.raw/*69.114*/("""
                        """),_display_(Seq[Any](/*70.26*/header("phoneNumber", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone Number")))),format.raw/*70.120*/("""
                        """),_display_(Seq[Any](/*71.26*/header("action", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*71.109*/("""
                    </tr>
                </thead>
                <tbody>
                    """),_display_(Seq[Any](/*75.22*/for(customer <- currentPage.getList) yield /*75.58*/ {_display_(Seq[Any](format.raw/*75.60*/("""
                        <tr>
                            <td>"""),_display_(Seq[Any](/*77.34*/customer/*77.42*/.getIdToken)),format.raw/*77.53*/("""</td>
							<td>"""),_display_(Seq[Any](/*78.13*/customer/*78.21*/.country.name)),format.raw/*78.34*/("""</td>
                            <td>"""),_display_(Seq[Any](/*79.34*/customer/*79.42*/.firstName)),format.raw/*79.52*/("""</td>
                            <td>"""),_display_(Seq[Any](/*80.34*/customer/*80.42*/.lastName)),format.raw/*80.51*/("""</td>
                            <td>"""),_display_(Seq[Any](/*81.34*/customer/*81.42*/.phoneNumber)),format.raw/*81.54*/("""</td>
							<td class="actions">
								<a href=""""),_display_(Seq[Any](/*83.19*/routes/*83.25*/.Customer.summary(customer.id))),format.raw/*83.55*/("""" class="btn-detail btn-customer-detail" data-remote="true">"""),_display_(Seq[Any](/*83.116*/doku/*83.120*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*83.181*/("""</a>
								<a href=""""),_display_(Seq[Any](/*84.19*/routes/*84.25*/.Customer.edit(customer.id))),format.raw/*84.52*/("""">"""),_display_(Seq[Any](/*84.55*/doku/*84.59*/.kirimdoku.util.MultiLanguage.getLanguage("LANG112","Edit"))),format.raw/*84.118*/("""</a>
								<a href='"""),_display_(Seq[Any](/*85.19*/routes/*85.25*/.Customer.relate(customer.id, 0, "", "", ""))),format.raw/*85.69*/("""'>"""),_display_(Seq[Any](/*85.72*/doku/*85.76*/.kirimdoku.util.MultiLanguage.getLanguage("LANG522","Relate"))),format.raw/*85.137*/("""</a>
							</td>
                        </tr>
                    """)))})),format.raw/*88.22*/("""
                </tbody>
            </table>

            <div id="pagination" class="pagination pagination-right">
                <ul>
					<li>
						<a class="btn" href=""""),_display_(Seq[Any](/*95.29*/routes/*95.35*/.Customer.csv().toString())),format.raw/*95.61*/(""""><i class="icon-download-alt"></i> """),_display_(Seq[Any](/*95.98*/doku/*95.102*/.kirimdoku.util.MultiLanguage.getLanguage("LANG128","Export as CSV"))),format.raw/*95.170*/("""</a>
					</li>
                    """),_display_(Seq[Any](/*97.22*/if(currentPage.hasPrev)/*97.45*/ {_display_(Seq[Any](format.raw/*97.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*99.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*99.75*/("""">&larr; """),_display_(Seq[Any](/*99.85*/doku/*99.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*99.152*/("""</a>
                    </li>
                    """)))}/*101.23*/else/*101.28*/{_display_(Seq[Any](format.raw/*101.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*103.36*/doku/*103.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*103.103*/("""</a>
                    </li>
                    """)))})),format.raw/*105.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*108.39*/currentPage/*108.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*108.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*111.22*/if(currentPage.hasNext)/*111.45*/ {_display_(Seq[Any](format.raw/*111.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*113.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*113.75*/("""">"""),_display_(Seq[Any](/*113.78*/doku/*113.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*113.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*115.23*/else/*115.28*/{_display_(Seq[Any](format.raw/*115.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*117.29*/doku/*117.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*117.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*119.22*/("""
                </ul>
            </div>
        """)))})),format.raw/*122.10*/("""
    </div>
	<div class="span5">
	   <div class="well" id="customer-detail-container">
			"""),_display_(Seq[Any](/*126.5*/doku/*126.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG281","You can view detail of customer by clicking 'Detail' on the desired row of customer"))),format.raw/*126.147*/("""
		</div>
    </div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*131.38*/routes/*131.44*/.Assets.at("javascripts/customer.js"))),format.raw/*131.81*/("""'></script>
""")))})),format.raw/*132.2*/("""
"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Customer],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((com.avaje.ebean.Page[models.Customer],String,String,String) => play.api.templates.Html) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/list.scala.html
                    HASH: 945ec8d2ce2816c49234fcdfd7aa159bd3afd07f
                    MATRIX: 783->1|964->582|978->588|1072->618|1121->631|1146->634|1214->667|1248->692|1288->694|1337->707|1394->742|1433->745|1460->750|1485->758|1497->763|1536->764|1575->768|1601->773|1635->776|1665->124|1676->128|2158->121|2187->579|2216->784|2254->787|2293->817|2332->818|2405->855|2418->859|2515->934|2644->1027|2681->1042|2869->1194|2904->1207|2957->1223|2971->1227|3080->1313|3214->1411|3228->1415|3312->1476|3393->1521|3408->1527|3448->1545|3513->1574|3526->1578|3611->1640|3699->1693|3714->1700|3750->1714|3840->1768|3886->1805|3925->1806|4014->1859|4027->1863|4131->1944|4184->1979|4197->1984|4236->1985|4435->2148|4542->2232|4604->2258|4716->2347|4778->2373|4891->2463|4953->2489|5064->2577|5126->2603|5243->2697|5305->2723|5411->2806|5544->2903|5596->2939|5636->2941|5735->3004|5752->3012|5785->3023|5839->3041|5856->3049|5891->3062|5966->3101|5983->3109|6015->3119|6090->3158|6107->3166|6138->3175|6213->3214|6230->3222|6264->3234|6352->3286|6367->3292|6419->3322|6517->3383|6531->3387|6615->3448|6674->3471|6689->3477|6738->3504|6777->3507|6790->3511|6872->3570|6931->3593|6946->3599|7012->3643|7051->3646|7064->3650|7148->3711|7249->3780|7462->3957|7477->3963|7525->3989|7598->4026|7612->4030|7703->4098|7776->4135|7808->4158|7848->4160|7957->4233|8019->4273|8065->4283|8078->4287|8164->4350|8236->4403|8250->4408|8290->4409|8401->4483|8415->4487|8502->4550|8587->4602|8704->4682|8725->4693|8782->4727|8871->4779|8904->4802|8945->4804|9055->4877|9118->4917|9158->4920|9172->4924|9255->4983|9334->5043|9348->5048|9388->5049|9492->5116|9506->5120|9588->5179|9680->5238|9764->5289|9891->5380|9904->5384|10066->5522|10169->5588|10185->5594|10245->5631|10290->5644
                    LINES: 26->1|28->23|28->23|30->23|31->24|31->24|32->25|32->25|32->25|33->26|33->26|33->26|33->26|34->27|34->27|34->27|35->28|35->28|36->29|38->3|38->3|57->1|59->21|61->31|63->33|63->33|63->33|66->36|66->36|66->36|72->42|72->42|74->44|74->44|74->44|74->44|74->44|75->45|75->45|75->45|76->46|76->46|76->46|76->46|76->46|76->46|83->53|83->53|83->53|88->58|88->58|88->58|90->60|90->60|90->60|92->62|92->62|92->62|96->66|96->66|97->67|97->67|98->68|98->68|99->69|99->69|100->70|100->70|101->71|101->71|105->75|105->75|105->75|107->77|107->77|107->77|108->78|108->78|108->78|109->79|109->79|109->79|110->80|110->80|110->80|111->81|111->81|111->81|113->83|113->83|113->83|113->83|113->83|113->83|114->84|114->84|114->84|114->84|114->84|114->84|115->85|115->85|115->85|115->85|115->85|115->85|118->88|125->95|125->95|125->95|125->95|125->95|125->95|127->97|127->97|127->97|129->99|129->99|129->99|129->99|129->99|131->101|131->101|131->101|133->103|133->103|133->103|135->105|138->108|138->108|138->108|141->111|141->111|141->111|143->113|143->113|143->113|143->113|143->113|145->115|145->115|145->115|147->117|147->117|147->117|149->119|152->122|156->126|156->126|156->126|161->131|161->131|161->131|162->132
                    -- GENERATED --
                */
            