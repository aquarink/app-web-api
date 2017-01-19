
package views.html.news

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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[com.avaje.ebean.Page[models.News],String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.News], currentSortBy: String, currentOrder: String):play.api.templates.Html = {
        _display_ {
def /*26.2*/header/*26.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*26.38*/("""
<th class=""""),_display_(Seq[Any](/*27.13*/key)),format.raw/*27.16*/("""" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
	"""),_display_(Seq[Any](/*28.3*/if(key && key.length > 0)/*28.28*/ {_display_(Seq[Any](format.raw/*28.30*/("""
	<a href=""""),_display_(Seq[Any](/*29.12*/link(currentPage.getPageIndex, key))),format.raw/*29.47*/("""">"""),_display_(Seq[Any](/*29.50*/title)),format.raw/*29.55*/("""</a>
	""")))}/*30.4*/else/*30.9*/{_display_(Seq[Any](format.raw/*30.10*/("""
	"""),_display_(Seq[Any](/*31.3*/title)),format.raw/*31.8*/("""
	""")))})),format.raw/*32.3*/("""
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

routes.News.list(newPage, sortBy, order)

}};
Seq[Any](format.raw/*1.95*/("""

"""),format.raw/*23.2*/("""


"""),format.raw/*34.2*/("""

"""),_display_(Seq[Any](/*36.2*/layout("News")/*36.16*/{_display_(Seq[Any](format.raw/*36.17*/("""
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*37.46*/routes/*37.52*/.Assets.at("stylesheets/news.css"))),format.raw/*37.86*/("""">

<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*40.7*/doku/*40.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG188","News Management"))),format.raw/*40.81*/("""</h1>
</div>

"""),_display_(Seq[Any](/*43.2*/partial/*43.9*/.flash_alert())),format.raw/*43.23*/("""

<div class="row">
	<div class="span12">
		<div class="well">
			<div class="input-append">
				<a class="btn success" href=""""),_display_(Seq[Any](/*49.35*/routes/*49.41*/.News.create)),format.raw/*49.53*/(""""><i class="icon-plus"></i>"""),_display_(Seq[Any](/*49.81*/doku/*49.85*/.kirimdoku.util.MultiLanguage.getLanguage("LANG458","Add News"))),format.raw/*49.148*/("""</a>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="span12">
		"""),_display_(Seq[Any](/*57.4*/if(currentPage.getTotalRowCount == 0)/*57.41*/{_display_(Seq[Any](format.raw/*57.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*59.9*/doku/*59.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG459","There are no news data to be displayed"))),format.raw/*59.106*/("""</em>
		</div>
		""")))}/*61.5*/else/*61.10*/{_display_(Seq[Any](format.raw/*61.11*/("""
		<table class="table news table-bordered table-striped table-condensed" style="text-align: center;">
			<thead>
			<tr>
				"""),_display_(Seq[Any](/*65.6*/header("", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG460","Content")))),format.raw/*65.84*/("""
				"""),_display_(Seq[Any](/*66.6*/header("", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status")))),format.raw/*66.83*/("""
				"""),_display_(Seq[Any](/*67.6*/header("", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*67.83*/("""
			</tr>
			</thead>
			<tbody>
			"""),_display_(Seq[Any](/*71.5*/for(news <- currentPage.getList) yield /*71.37*/ {_display_(Seq[Any](format.raw/*71.39*/("""
			<tr>
				<td class="news-content">"""),_display_(Seq[Any](/*73.31*/news/*73.35*/.getTitle())),format.raw/*73.46*/("""</td>
				<td nowrap="nowrap">
				"""),_display_(Seq[Any](/*75.6*/if(news.published)/*75.24*/{_display_(Seq[Any](format.raw/*75.25*/("""
					<span class="badge badge-success">Published</span>
				""")))}/*77.6*/else/*77.10*/{_display_(Seq[Any](format.raw/*77.11*/("""
					<span class="badge badge-warning">Not Published</span>
				""")))})),format.raw/*79.6*/("""
				</td>
				<td class="actions">
					<a href=""""),_display_(Seq[Any](/*82.16*/routes/*82.22*/.News.edit(news.id))),format.raw/*82.41*/("""">"""),_display_(Seq[Any](/*82.44*/doku/*82.48*/.kirimdoku.util.MultiLanguage.getLanguage("LANG112","Edit"))),format.raw/*82.107*/("""</a>
					"""),_display_(Seq[Any](/*83.7*/if(news.published)/*83.25*/{_display_(Seq[Any](format.raw/*83.26*/("""
						<a href=""""),_display_(Seq[Any](/*84.17*/routes/*84.23*/.News.status(news.id, "unpublish"))),format.raw/*84.57*/("""" data-confirm='"""),_display_(Seq[Any](/*84.74*/doku/*84.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG463","Unpublish this content?"))),format.raw/*84.156*/("""'>"""),_display_(Seq[Any](/*84.159*/doku/*84.163*/.kirimdoku.util.MultiLanguage.getLanguage("LANG461","Unpublish"))),format.raw/*84.227*/("""</a>
					""")))}/*85.8*/else/*85.13*/{_display_(Seq[Any](format.raw/*85.14*/("""
						<a href=""""),_display_(Seq[Any](/*86.17*/routes/*86.23*/.News.status(news.id, "publish"))),format.raw/*86.55*/("""" data-confirm='"""),_display_(Seq[Any](/*86.72*/doku/*86.76*/.kirimdoku.util.MultiLanguage.getLanguage("LANG464","Publish this content?"))),format.raw/*86.152*/("""'>"""),_display_(Seq[Any](/*86.155*/doku/*86.159*/.kirimdoku.util.MultiLanguage.getLanguage("LANG462","Publish"))),format.raw/*86.221*/("""</a>
					""")))})),format.raw/*87.7*/("""
				</td>
			</tr>
			""")))})),format.raw/*90.5*/("""
			</tbody>
		</table>

		<div id="pagination" class="pagination pagination-right">
			<ul>
				"""),_display_(Seq[Any](/*96.6*/if(currentPage.hasPrev)/*96.29*/ {_display_(Seq[Any](format.raw/*96.31*/("""
				<li class="prev">
					<a href=""""),_display_(Seq[Any](/*98.16*/link(currentPage.getPageIndex - 1, null))),format.raw/*98.56*/("""">&larr; """),_display_(Seq[Any](/*98.66*/doku/*98.70*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*98.133*/("""</a>
				</li>
				""")))}/*100.7*/else/*100.12*/{_display_(Seq[Any](format.raw/*100.13*/("""
				<li class="prev">
					<a>&larr; """),_display_(Seq[Any](/*102.17*/doku/*102.21*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*102.84*/("""</a>
				</li>
				""")))})),format.raw/*104.6*/("""

				<li class="current">
					<a>Displaying """),_display_(Seq[Any](/*107.21*/currentPage/*107.32*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*107.66*/("""</a>
				</li>

				"""),_display_(Seq[Any](/*110.6*/if(currentPage.hasNext)/*110.29*/ {_display_(Seq[Any](format.raw/*110.31*/("""
				<li class="next">
					<a href=""""),_display_(Seq[Any](/*112.16*/link(currentPage.getPageIndex + 1, null))),format.raw/*112.56*/("""">"""),_display_(Seq[Any](/*112.59*/doku/*112.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*112.122*/(""" &rarr;</a>
				</li>
				""")))}/*114.7*/else/*114.12*/{_display_(Seq[Any](format.raw/*114.13*/("""
				<li class="next">
					<a>"""),_display_(Seq[Any](/*116.10*/doku/*116.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*116.73*/(""" &rarr;</a>
				</li>
				""")))})),format.raw/*118.6*/("""
			</ul>
		</div>
		""")))})),format.raw/*121.4*/("""
	</div>
</div>
</div>
""")))})),format.raw/*125.2*/("""

"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.News],currentSortBy:String,currentOrder:String): play.api.templates.Html = apply(currentPage,currentSortBy,currentOrder)
    
    def f:((com.avaje.ebean.Page[models.News],String,String) => play.api.templates.Html) = (currentPage,currentSortBy,currentOrder) => apply(currentPage,currentSortBy,currentOrder)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/news/list.scala.html
                    HASH: 5cd1a6a6351adbd64861ac1a1d8cb48ae6e62677
                    MATRIX: 768->1|922->411|936->417|1030->447|1079->460|1104->463|1216->540|1250->565|1290->567|1338->579|1395->614|1434->617|1461->622|1486->630|1498->635|1537->636|1575->639|1601->644|1635->647|1665->97|1676->101|2012->94|2041->407|2071->655|2109->658|2132->672|2171->673|2253->719|2268->725|2324->759|2395->795|2408->799|2500->869|2550->884|2565->891|2601->905|2764->1032|2779->1038|2813->1050|2877->1078|2890->1082|2976->1145|3094->1228|3140->1265|3179->1266|3244->1296|3257->1300|3373->1393|3409->1412|3422->1417|3461->1418|3623->1545|3723->1623|3764->1629|3863->1706|3904->1712|4003->1789|4075->1826|4123->1858|4163->1860|4238->1899|4251->1903|4284->1914|4355->1950|4382->1968|4421->1969|4501->2031|4514->2035|4553->2036|4650->2102|4737->2153|4752->2159|4793->2178|4832->2181|4845->2185|4927->2244|4973->2255|5000->2273|5039->2274|5092->2291|5107->2297|5163->2331|5216->2348|5229->2352|5330->2430|5370->2433|5384->2437|5471->2501|5500->2513|5513->2518|5552->2519|5605->2536|5620->2542|5674->2574|5727->2591|5740->2595|5839->2671|5879->2674|5893->2678|5978->2740|6020->2751|6075->2775|6208->2873|6240->2896|6280->2898|6354->2936|6416->2976|6462->2986|6475->2990|6561->3053|6600->3074|6614->3079|6654->3080|6730->3119|6744->3123|6830->3186|6882->3206|6966->3253|6987->3264|7044->3298|7101->3319|7134->3342|7175->3344|7250->3382|7313->3422|7353->3425|7367->3429|7450->3488|7496->3516|7510->3521|7550->3522|7619->3554|7633->3558|7715->3617|7774->3644|7828->3666|7884->3690
                    LINES: 26->1|28->26|28->26|30->26|31->27|31->27|32->28|32->28|32->28|33->29|33->29|33->29|33->29|34->30|34->30|34->30|35->31|35->31|36->32|38->3|38->3|59->1|61->23|64->34|66->36|66->36|66->36|67->37|67->37|67->37|70->40|70->40|70->40|73->43|73->43|73->43|79->49|79->49|79->49|79->49|79->49|79->49|87->57|87->57|87->57|89->59|89->59|89->59|91->61|91->61|91->61|95->65|95->65|96->66|96->66|97->67|97->67|101->71|101->71|101->71|103->73|103->73|103->73|105->75|105->75|105->75|107->77|107->77|107->77|109->79|112->82|112->82|112->82|112->82|112->82|112->82|113->83|113->83|113->83|114->84|114->84|114->84|114->84|114->84|114->84|114->84|114->84|114->84|115->85|115->85|115->85|116->86|116->86|116->86|116->86|116->86|116->86|116->86|116->86|116->86|117->87|120->90|126->96|126->96|126->96|128->98|128->98|128->98|128->98|128->98|130->100|130->100|130->100|132->102|132->102|132->102|134->104|137->107|137->107|137->107|140->110|140->110|140->110|142->112|142->112|142->112|142->112|142->112|144->114|144->114|144->114|146->116|146->116|146->116|148->118|151->121|155->125
                    -- GENERATED --
                */
            