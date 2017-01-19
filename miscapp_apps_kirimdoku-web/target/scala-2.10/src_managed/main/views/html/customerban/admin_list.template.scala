
package views.html.customerban

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
object admin_list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Form[models.Customer],com.avaje.ebean.Page[models.CustomerBan],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[models.Customer], currentPage: com.avaje.ebean.Page[models.CustomerBan], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*125.2*/header/*125.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*125.38*/("""
<th class=""""),_display_(Seq[Any](/*126.13*/key)),format.raw/*126.16*/("""">
	"""),_display_(Seq[Any](/*127.3*/if(key && key.length > 0)/*127.28*/ {_display_(Seq[Any](format.raw/*127.30*/("""
	<a href=""""),_display_(Seq[Any](/*128.12*/link(currentPage.getPageIndex, key))),format.raw/*128.47*/("""">"""),_display_(Seq[Any](/*128.50*/title)),format.raw/*128.55*/("""</a>
	""")))}/*129.4*/else/*129.9*/{_display_(Seq[Any](format.raw/*129.10*/("""
	"""),_display_(Seq[Any](/*130.3*/title)),format.raw/*130.8*/("""
	""")))})),format.raw/*131.3*/("""
</th>
""")))};def /*135.2*/link/*135.6*/(newPage:Int, newSortBy:String) = {{

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
    routes.CustomerBan.admin_list(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.160*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Banned list")/*5.23*/ {_display_(Seq[Any](format.raw/*5.25*/("""
<div class="page-header">
  <h1>"""),_display_(Seq[Any](/*7.8*/doku/*7.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG193","Banned list"))),format.raw/*7.78*/("""</h1>
</div>

<div class="common-container">
	<div class="well common-container-filter">
		"""),_display_(Seq[Any](/*12.4*/form(action = routes.CustomerBan.admin_list(), 'id -> "customer-ban-filter-form", 'class -> "form form-horizontal row")/*12.123*/ {_display_(Seq[Any](format.raw/*12.125*/("""
			<div class="control-group span12">
				"""),_display_(Seq[Any](/*14.6*/input(filterForm("f"), '_showConstraints -> false, '_class -> "nolabel", '_label -> "", 'class -> "span4", 
				'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG493","Search by ID, name, or email address")
				)/*16.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*16.35*/("""
				<div class="input-append">
					<input type="search" name=""""),_display_(Seq[Any](/*18.34*/name)),format.raw/*18.38*/("""" value=""""),_display_(Seq[Any](/*18.48*/value)),format.raw/*18.53*/("""" """),_display_(Seq[Any](/*18.56*/toHtmlArgs(args))),format.raw/*18.72*/(""">
					<button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*19.69*/doku/*19.73*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*19.134*/("""</button>
				</div>
				<a role="add" class="btn" href="#ban-add" data-toggle="modal"><i class="icon-plus"></i> """),_display_(Seq[Any](/*21.94*/doku/*21.98*/.kirimdoku.util.MultiLanguage.getLanguage("LANG375","Add New"))),format.raw/*21.160*/("""</a>
				""")))})),format.raw/*22.6*/("""
			</div>
		""")))})),format.raw/*24.4*/("""
	</div>

	<div class="common-container-inner">
		"""),_display_(Seq[Any](/*28.4*/if(currentPage.getTotalRowCount == 0)/*28.41*/{_display_(Seq[Any](format.raw/*28.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*30.9*/doku/*30.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG459","There are no news data to be displayed"))),format.raw/*30.106*/("""</em>
		</div>
		""")))}/*32.5*/else/*32.10*/{_display_(Seq[Any](format.raw/*32.11*/("""
            <table class="customer table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*36.26*/header("customer.id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID")))),format.raw/*36.119*/("""
                        """),_display_(Seq[Any](/*37.26*/header("customer.country", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country")))),format.raw/*37.120*/("""
                        """),_display_(Seq[Any](/*38.26*/header("customer.firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name")))),format.raw/*38.125*/("""
                        """),_display_(Seq[Any](/*39.26*/header("customer.lastName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name")))),format.raw/*39.123*/("""
                        """),_display_(Seq[Any](/*40.26*/header(null, doku.kirimdoku.util.MultiLanguage.getLanguage("LANG476","Ban reason")))),format.raw/*40.109*/("""
                        """),_display_(Seq[Any](/*41.26*/header(null, doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*41.105*/("""
                    </tr>
                </thead>
                <tbody>
					"""),_display_(Seq[Any](/*45.7*/for(ban <- currentPage.getList) yield /*45.38*/ {_display_(Seq[Any](format.raw/*45.40*/("""
                        <tr>
                            <td>"""),_display_(Seq[Any](/*47.34*/ban/*47.37*/.customer.getIdToken)),format.raw/*47.57*/("""</td>
							<td>"""),_display_(Seq[Any](/*48.13*/if(ban.country != null)/*48.36*/ {_display_(Seq[Any](format.raw/*48.38*/("""
							"""),_display_(Seq[Any](/*49.9*/ban/*49.12*/.country.name)),format.raw/*49.25*/("""
							""")))})),format.raw/*50.9*/("""</td>
                            <td>"""),_display_(Seq[Any](/*51.34*/ban/*51.37*/.firstName)),format.raw/*51.47*/("""</td>
                            <td>"""),_display_(Seq[Any](/*52.34*/ban/*52.37*/.lastName)),format.raw/*52.46*/("""</td>
                            <td>"""),_display_(Seq[Any](/*53.34*/ban/*53.37*/.reason)),format.raw/*53.44*/("""</td>
							<td class="actions">
								<a href=""""),_display_(Seq[Any](/*55.19*/routes/*55.25*/.CustomerBan.admin_unban(ban.id))),format.raw/*55.57*/("""" data-confirm='"""),_display_(Seq[Any](/*55.74*/doku/*55.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG480","Are you sure you want to unban this customer?"))),format.raw/*55.178*/("""'><i class="icon-remove"></i>"""),_display_(Seq[Any](/*55.208*/doku/*55.212*/.kirimdoku.util.MultiLanguage.getLanguage("LANG369","Unban"))),format.raw/*55.272*/("""</a>
							</td>
                        </tr>
                    """)))})),format.raw/*58.22*/("""
                </tbody>
            </table>
		
            <div id="pagination" class="pagination pagination-right">
                <ul>
                    """),_display_(Seq[Any](/*64.22*/if(currentPage.hasPrev)/*64.45*/ {_display_(Seq[Any](format.raw/*64.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*66.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*66.75*/("""">&larr; """),_display_(Seq[Any](/*66.85*/doku/*66.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*66.152*/("""</a>
                    </li>
                    """)))}/*68.23*/else/*68.28*/{_display_(Seq[Any](format.raw/*68.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*70.36*/doku/*70.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*70.103*/("""</a>
                    </li>
                    """)))})),format.raw/*72.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*75.39*/currentPage/*75.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*75.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*78.22*/if(currentPage.hasNext)/*78.45*/ {_display_(Seq[Any](format.raw/*78.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*80.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*80.75*/("""">"""),_display_(Seq[Any](/*80.78*/doku/*80.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*80.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*82.23*/else/*82.28*/{_display_(Seq[Any](format.raw/*82.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*84.29*/doku/*84.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*84.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*86.22*/("""
                </ul>
            </div>
		""")))})),format.raw/*89.4*/("""
	</div>

	<!-- Modal -->
	<div id="ban-add" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		"""),_display_(Seq[Any](/*94.4*/form(action = routes.CustomerBan.admin_add(), 'class -> "form")/*94.67*/ {_display_(Seq[Any](format.raw/*94.69*/("""
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">"""),_display_(Seq[Any](/*97.28*/doku/*97.32*/.kirimdoku.util.MultiLanguage.getLanguage("LANG477","Add banned list"))),format.raw/*97.102*/("""</h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
					"""),_display_(Seq[Any](/*101.7*/inputText(filterForm("customer.idToken"), '_showConstraints -> false, '_class -> "", 
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"), 
					'class -> "required", 
					'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG494","Enter customer ID")
					))),format.raw/*105.7*/("""
				</div>
				<div class="control-group">
					"""),_display_(Seq[Any](/*108.7*/textarea(filterForm("reason"), '_showConstraints -> false, '_class -> "", 
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG478","Reason for banning"), 
					'class -> "required", 
					'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG495","Enter reason")
					))),format.raw/*112.7*/("""
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">"""),_display_(Seq[Any](/*116.66*/doku/*116.70*/.kirimdoku.util.MultiLanguage.getLanguage("LANG479","Close"))),format.raw/*116.130*/("""</button>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*117.52*/doku/*117.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG308","Submit"))),format.raw/*117.117*/("""</button>
			</div>
		""")))})),format.raw/*119.4*/("""
	</div>
</div>
""")))})),format.raw/*122.2*/("""


"""),format.raw/*133.2*/("""

"""),format.raw/*153.2*/("""
"""))}
    }
    
    def render(filterForm:Form[models.Customer],currentPage:com.avaje.ebean.Page[models.CustomerBan],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(filterForm,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((Form[models.Customer],com.avaje.ebean.Page[models.CustomerBan],String,String,String) => play.api.templates.Html) = (filterForm,currentPage,currentSortBy,currentOrder,currentFilter) => apply(filterForm,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customerban/admin_list.scala.html
                    HASH: 1eda591b881337b4a11fed142911479111afb2d1
                    MATRIX: 817->1|1087->6017|1102->6023|1197->6053|1247->6066|1273->6069|1314->6074|1349->6099|1390->6101|1439->6113|1497->6148|1537->6151|1565->6156|1591->6164|1604->6169|1644->6170|1683->6173|1710->6178|1745->6181|1777->6192|1790->6196|2281->159|2308->211|2344->213|2373->234|2412->236|2480->270|2492->274|2579->340|2706->432|2835->551|2876->553|2955->597|3192->826|3259->855|3360->920|3386->924|3432->934|3459->939|3498->942|3536->958|3642->1028|3655->1032|3739->1093|3889->1207|3902->1211|3987->1273|4028->1283|4073->1297|4159->1348|4205->1385|4244->1386|4309->1416|4322->1420|4438->1513|4474->1532|4487->1537|4526->1538|4725->1701|4841->1794|4903->1820|5020->1914|5082->1940|5204->2039|5266->2065|5386->2162|5448->2188|5554->2271|5616->2297|5718->2376|5835->2458|5882->2489|5922->2491|6021->2554|6033->2557|6075->2577|6129->2595|6161->2618|6201->2620|6245->2629|6257->2632|6292->2645|6332->2654|6407->2693|6419->2696|6451->2706|6526->2745|6538->2748|6569->2757|6644->2796|6656->2799|6685->2806|6773->2858|6788->2864|6842->2896|6895->2913|6908->2917|7031->3017|7098->3047|7112->3051|7195->3111|7296->3180|7494->3342|7526->3365|7566->3367|7675->3440|7737->3480|7783->3490|7796->3494|7882->3557|7953->3610|7966->3615|8005->3616|8115->3690|8128->3694|8214->3757|8298->3809|8414->3889|8434->3900|8490->3934|8578->3986|8610->4009|8650->4011|8759->4084|8821->4124|8860->4127|8873->4131|8955->4190|9033->4250|9046->4255|9085->4256|9188->4323|9201->4327|9282->4386|9373->4445|9449->4490|9635->4641|9707->4704|9747->4706|9932->4855|9945->4859|10038->4929|10156->5011|10485->5318|10571->5368|10891->5666|11045->5783|11059->5787|11143->5847|11241->5908|11255->5912|11340->5973|11395->5996|11444->6013|11475->6189|11505->6656
                    LINES: 26->1|31->125|31->125|33->125|34->126|34->126|35->127|35->127|35->127|36->128|36->128|36->128|36->128|37->129|37->129|37->129|38->130|38->130|39->131|41->135|41->135|60->1|61->4|62->5|62->5|62->5|64->7|64->7|64->7|69->12|69->12|69->12|71->14|73->16|73->16|75->18|75->18|75->18|75->18|75->18|75->18|76->19|76->19|76->19|78->21|78->21|78->21|79->22|81->24|85->28|85->28|85->28|87->30|87->30|87->30|89->32|89->32|89->32|93->36|93->36|94->37|94->37|95->38|95->38|96->39|96->39|97->40|97->40|98->41|98->41|102->45|102->45|102->45|104->47|104->47|104->47|105->48|105->48|105->48|106->49|106->49|106->49|107->50|108->51|108->51|108->51|109->52|109->52|109->52|110->53|110->53|110->53|112->55|112->55|112->55|112->55|112->55|112->55|112->55|112->55|112->55|115->58|121->64|121->64|121->64|123->66|123->66|123->66|123->66|123->66|125->68|125->68|125->68|127->70|127->70|127->70|129->72|132->75|132->75|132->75|135->78|135->78|135->78|137->80|137->80|137->80|137->80|137->80|139->82|139->82|139->82|141->84|141->84|141->84|143->86|146->89|151->94|151->94|151->94|154->97|154->97|154->97|158->101|162->105|165->108|169->112|173->116|173->116|173->116|174->117|174->117|174->117|176->119|179->122|182->133|184->153
                    -- GENERATED --
                */
            