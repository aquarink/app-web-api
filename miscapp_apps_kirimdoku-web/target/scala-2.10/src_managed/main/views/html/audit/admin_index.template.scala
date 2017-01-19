
package views.html.audit

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
object admin_index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[com.avaje.ebean.Page[models.AuditLog],String,String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(page: com.avaje.ebean.Page[models.AuditLog], currentSortBy: String, currentOrder: String, filterUserId: String, filterTag: String):play.api.templates.Html = {
        _display_ {
def /*24.2*/header/*24.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*24.38*/("""
<th class=""""),_display_(Seq[Any](/*25.13*/key)),format.raw/*25.16*/("""">
	"""),_display_(Seq[Any](/*26.3*/if(key && key.length > 0)/*26.28*/ {_display_(Seq[Any](format.raw/*26.30*/("""
	<a href=""""),_display_(Seq[Any](/*27.12*/link(page.getPageIndex, key))),format.raw/*27.40*/("""">"""),_display_(Seq[Any](/*27.43*/title)),format.raw/*27.48*/("""</a>
	""")))}/*28.4*/else/*28.9*/{_display_(Seq[Any](format.raw/*28.10*/("""
	"""),_display_(Seq[Any](/*29.3*/title)),format.raw/*29.8*/("""
	""")))})),format.raw/*30.3*/("""
</th>
""")))};def /*4.2*/link/*4.6*/(newPage:Int, newSortBy:String) = {{
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

    routes.Audit.admin_index(newPage, sortBy, order, filterUserId, filterTag)
}};
Seq[Any](format.raw/*1.133*/("""


"""),format.raw/*22.2*/("""

"""),format.raw/*32.2*/("""

"""),_display_(Seq[Any](/*34.2*/layout("Audit Log")/*34.21*/{_display_(Seq[Any](format.raw/*34.22*/("""
<div class="page-header">
  <h1>"""),_display_(Seq[Any](/*36.8*/doku/*36.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG470","Backend User Audit Log Viewer"))),format.raw/*36.96*/("""</h1>
</div>

"""),_display_(Seq[Any](/*39.2*/partial/*39.9*/.flash_alert())),format.raw/*39.23*/("""

<div class="filter-container">
	<form action=""""),_display_(Seq[Any](/*42.17*/link(0, "id"))),format.raw/*42.30*/("""" method="GET" id="form-filter">
		<div class="row">
		</div>
		<div id="filter-action" class="form-actions-nowell" style="margin-bottom: 20px;">
			<!-- <a class="btn primary" id="submit-filter">Filter</a>-->
		</div>
	</formaccount.number>
</div>

<div>
    <table class="transaction table-bordered table-striped table-condensed" style="width:100%;text-align: center;">
        <thead>
			<tr>
				"""),_display_(Seq[Any](/*55.6*/header("createdTime", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG474","Time")))),format.raw/*55.92*/("""
				"""),_display_(Seq[Any](/*56.6*/header("user", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG310","User")))),format.raw/*56.85*/("""
				"""),_display_(Seq[Any](/*57.6*/header("tag", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG471","Tag")))),format.raw/*57.83*/("""
				"""),_display_(Seq[Any](/*58.6*/header(null, doku.kirimdoku.util.MultiLanguage.getLanguage("LANG177","Message")))),format.raw/*58.86*/("""
				"""),_display_(Seq[Any](/*59.6*/header(null, doku.kirimdoku.util.MultiLanguage.getLanguage("LANG472","Data")))),format.raw/*59.83*/("""
			</tr>
        </thead>
        <tbody>
			"""),_display_(Seq[Any](/*63.5*/for(audit <- page.getList) yield /*63.31*/ {_display_(Seq[Any](format.raw/*63.33*/("""
			<tr>
				<td class="left">"""),_display_(Seq[Any](/*65.23*/audit/*65.28*/.createdTime)),format.raw/*65.40*/("""</td>
				"""),_display_(Seq[Any](/*66.6*/if(audit.user != null)/*66.28*/ {_display_(Seq[Any](format.raw/*66.30*/("""
				<td class="left">"""),_display_(Seq[Any](/*67.23*/audit/*67.28*/.user.email)),format.raw/*67.39*/("""</td>
				""")))}/*68.7*/else/*68.12*/{_display_(Seq[Any](format.raw/*68.13*/("""
				<td class="left"></td>
				""")))})),format.raw/*70.6*/("""
				<td class="left">"""),_display_(Seq[Any](/*71.23*/audit/*71.28*/.tag)),format.raw/*71.32*/("""</td>
				<td class="left">"""),_display_(Seq[Any](/*72.23*/audit/*72.28*/.message)),format.raw/*72.36*/("""</td>
				<td class="left">
					<a href="#audit-dialog-"""),_display_(Seq[Any](/*74.30*/audit/*74.35*/.id)),format.raw/*74.38*/("""" data-toggle="modal">"""),_display_(Seq[Any](/*74.61*/doku/*74.65*/.kirimdoku.util.MultiLanguage.getLanguage("LANG473","View"))),format.raw/*74.124*/("""</a>
					<div class="modal fade hide" id="audit-dialog-"""),_display_(Seq[Any](/*75.53*/audit/*75.58*/.id)),format.raw/*75.61*/("""">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h3>"""),_display_(Seq[Any](/*78.13*/doku/*78.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG475","Data detail"))),format.raw/*78.83*/("""</h3>
						</div>
						<div class="modal-body">
							<pre>
							"""),_display_(Seq[Any](/*82.9*/audit/*82.14*/.data)),format.raw/*82.19*/("""
							</pre>
						</div>
					</div>
				</td>
			</tr>
			""")))})),format.raw/*88.5*/("""
		</tbody>
	</table>
    <div id="pagination" class="pagination pagination-right">
        <ul>
            """),_display_(Seq[Any](/*93.14*/if(page.hasPrev)/*93.30*/ {_display_(Seq[Any](format.raw/*93.32*/("""
            <li class="prev">
                <a href=""""),_display_(Seq[Any](/*95.27*/link(page.getPageIndex - 1, null))),format.raw/*95.60*/("""">&larr; """),_display_(Seq[Any](/*95.70*/doku/*95.74*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*95.137*/("""</a>
            </li>
            """)))}/*97.15*/else/*97.20*/{_display_(Seq[Any](format.raw/*97.21*/("""
            <li class="prev">
                <a>&larr; """),_display_(Seq[Any](/*99.28*/doku/*99.32*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*99.95*/("""</a>
            </li>
            """)))})),format.raw/*101.14*/("""

            <li class="current">
                <a>Displaying """),_display_(Seq[Any](/*104.32*/page/*104.36*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*104.70*/("""</a>
            </li>

            """),_display_(Seq[Any](/*107.14*/if(page.hasNext)/*107.30*/ {_display_(Seq[Any](format.raw/*107.32*/("""
            <li class="next">
                <a href=""""),_display_(Seq[Any](/*109.27*/link(page.getPageIndex + 1, null))),format.raw/*109.60*/("""">"""),_display_(Seq[Any](/*109.63*/doku/*109.67*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*109.126*/(""" &rarr;</a>
            </li>
            """)))}/*111.15*/else/*111.20*/{_display_(Seq[Any](format.raw/*111.21*/("""
            <li class="next">
                 <a>"""),_display_(Seq[Any](/*113.22*/doku/*113.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*113.85*/(""" &rarr;</a>
            </li>
            """)))})),format.raw/*115.14*/("""
        </ul>
    </div>
</div>
""")))})),format.raw/*119.2*/("""
"""))}
    }
    
    def render(page:com.avaje.ebean.Page[models.AuditLog],currentSortBy:String,currentOrder:String,filterUserId:String,filterTag:String): play.api.templates.Html = apply(page,currentSortBy,currentOrder,filterUserId,filterTag)
    
    def f:((com.avaje.ebean.Page[models.AuditLog],String,String,String,String) => play.api.templates.Html) = (page,currentSortBy,currentOrder,filterUserId,filterTag) => apply(page,currentSortBy,currentOrder,filterUserId,filterTag)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/audit/admin_index.scala.html
                    HASH: ec31055606677dd3e293fda634803434bd546dc4
                    MATRIX: 794->1|986->612|1000->618|1094->648|1143->661|1168->664|1208->669|1242->694|1282->696|1330->708|1380->736|1419->739|1446->744|1471->752|1483->757|1522->758|1560->761|1586->766|1620->769|1650->136|1661->140|2161->132|2191->609|2220->777|2258->780|2286->799|2325->800|2394->834|2407->838|2513->922|2563->937|2578->944|2614->958|2699->1007|2734->1020|3170->1421|3278->1507|3319->1513|3420->1592|3461->1598|3560->1675|3601->1681|3703->1761|3744->1767|3843->1844|3925->1891|3967->1917|4007->1919|4074->1950|4088->1955|4122->1967|4168->1978|4199->2000|4239->2002|4298->2025|4312->2030|4345->2041|4374->2053|4387->2058|4426->2059|4490->2092|4549->2115|4563->2120|4589->2124|4653->2152|4667->2157|4697->2165|4790->2222|4804->2227|4829->2230|4888->2253|4901->2257|4983->2316|5076->2373|5090->2378|5115->2381|5274->2504|5287->2508|5375->2574|5481->2645|5495->2650|5522->2655|5616->2718|5762->2828|5787->2844|5827->2846|5920->2903|5975->2936|6021->2946|6034->2950|6120->3013|6175->3050|6188->3055|6227->3056|6321->3114|6334->3118|6419->3181|6488->3217|6591->3283|6605->3287|6662->3321|6736->3358|6762->3374|6803->3376|6897->3433|6953->3466|6993->3469|7007->3473|7090->3532|7153->3576|7167->3581|7207->3582|7296->3634|7310->3638|7392->3697|7468->3740|7534->3774
                    LINES: 26->1|28->24|28->24|30->24|31->25|31->25|32->26|32->26|32->26|33->27|33->27|33->27|33->27|34->28|34->28|34->28|35->29|35->29|36->30|38->4|38->4|57->1|60->22|62->32|64->34|64->34|64->34|66->36|66->36|66->36|69->39|69->39|69->39|72->42|72->42|85->55|85->55|86->56|86->56|87->57|87->57|88->58|88->58|89->59|89->59|93->63|93->63|93->63|95->65|95->65|95->65|96->66|96->66|96->66|97->67|97->67|97->67|98->68|98->68|98->68|100->70|101->71|101->71|101->71|102->72|102->72|102->72|104->74|104->74|104->74|104->74|104->74|104->74|105->75|105->75|105->75|108->78|108->78|108->78|112->82|112->82|112->82|118->88|123->93|123->93|123->93|125->95|125->95|125->95|125->95|125->95|127->97|127->97|127->97|129->99|129->99|129->99|131->101|134->104|134->104|134->104|137->107|137->107|137->107|139->109|139->109|139->109|139->109|139->109|141->111|141->111|141->111|143->113|143->113|143->113|145->115|149->119
                    -- GENERATED --
                */
            