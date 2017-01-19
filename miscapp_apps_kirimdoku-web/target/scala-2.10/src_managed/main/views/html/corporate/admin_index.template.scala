
package views.html.corporate

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
object admin_index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[com.avaje.ebean.Page[models.Corporate],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Corporate], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import java.lang.String; var restrictUser = ""

def /*26.2*/header/*26.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*26.38*/("""
<th class=""""),_display_(Seq[Any](/*27.13*/key)),format.raw/*27.16*/("""">
	"""),_display_(Seq[Any](/*28.3*/if(key && key.length > 0)/*28.28*/ {_display_(Seq[Any](format.raw/*28.30*/("""
	<a href=""""),_display_(Seq[Any](/*29.12*/link(currentPage.getPageIndex, key))),format.raw/*29.47*/("""">"""),_display_(Seq[Any](/*29.50*/title)),format.raw/*29.55*/("""</a>
	""")))}/*30.4*/else/*30.9*/{_display_(Seq[Any](format.raw/*30.10*/("""
	"""),_display_(Seq[Any](/*31.3*/title)),format.raw/*31.8*/("""
	""")))})),format.raw/*32.3*/("""
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
	routes.Corporate.admin_index(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.123*/("""

"""),format.raw/*6.1*/("""
"""),format.raw/*24.2*/("""

"""),format.raw/*34.2*/("""

"""),_display_(Seq[Any](/*36.2*/layout("Corporates management")/*36.33*/ {_display_(Seq[Any](format.raw/*36.35*/("""
	<div class="page-header">
		<h1>"""),_display_(Seq[Any](/*38.8*/doku/*38.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG190","Corporates management"))),format.raw/*38.88*/("""</h1>
	</div>
	
	"""),_display_(Seq[Any](/*41.3*/partial/*41.10*/.flash_alert())),format.raw/*41.24*/("""

	<div>
		<div class="well">
			<form action=""""),_display_(Seq[Any](/*45.19*/link(0, "name"))),format.raw/*45.34*/("""" method="GET" class="form-search">
			<div class="input-append">
				<input type="search" id="searchbox" class="search-query span4" name="f" value=""""),_display_(Seq[Any](/*47.85*/currentFilter)),format.raw/*47.98*/("""" placeholder='"""),_display_(Seq[Any](/*47.114*/doku/*47.118*/.kirimdoku.util.MultiLanguage.getLanguage("LANG487","Filter by name..."))),format.raw/*47.190*/("""'><button type="submit" id="searchsubmit" value="Search" class="btn" style="border-radius:0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*47.315*/doku/*47.319*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*47.380*/("""</button>
				"""),_display_(Seq[Any](/*48.6*/restrict(la(as("admin")))/*48.31*/ {_display_(Seq[Any](format.raw/*48.33*/("""
				<a class="btn success" href=""""),_display_(Seq[Any](/*49.35*/routes/*49.41*/.Corporate.admin_new())),format.raw/*49.63*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*49.92*/doku/*49.96*/.kirimdoku.util.MultiLanguage.getLanguage("LANG191","Add New Corporate"))),format.raw/*49.168*/("""</a>
				""")))})),format.raw/*50.6*/("""
			</div>
			</form>
		</div>
	</div>

	<div class="row container-corporates">
		<div class="span7">
			"""),_display_(Seq[Any](/*58.5*/if(currentPage.getTotalRowCount == 0)/*58.42*/{_display_(Seq[Any](format.raw/*58.43*/("""
			<div class="well">
				<em>"""),_display_(Seq[Any](/*60.10*/doku/*60.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG286","Results are empty"))),format.raw/*60.86*/("""</em>
			</div>
			""")))}/*62.6*/else/*62.11*/{_display_(Seq[Any](format.raw/*62.12*/("""
			<table class="table-corporates table-bordered table-striped table-condensed" style="width:100%;">
				<thead>
				<tr>
					"""),_display_(Seq[Any](/*66.7*/header("code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG309","Code")))),format.raw/*66.86*/("""
					"""),_display_(Seq[Any](/*67.7*/header("country.code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country")))),format.raw/*67.97*/("""
					"""),_display_(Seq[Any](/*68.7*/header("name", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG295","Name")))),format.raw/*68.86*/("""
					"""),_display_(Seq[Any](/*69.7*/header("", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*69.84*/("""
				</tr>
				</thead>
				<tbody>
				"""),_display_(Seq[Any](/*73.6*/restrict(la(as("admin")))/*73.31*/ {_display_(Seq[Any](format.raw/*73.33*/("""
					"""),_display_(Seq[Any](/*74.7*/{restrictUser = "admin"})),format.raw/*74.31*/("""
				""")))})),format.raw/*75.6*/("""
				"""),_display_(Seq[Any](/*76.6*/restrict(la(as("finance")))/*76.33*/ {_display_(Seq[Any](format.raw/*76.35*/("""
					"""),_display_(Seq[Any](/*77.7*/{restrictUser = "finance"})),format.raw/*77.33*/("""
				""")))})),format.raw/*78.6*/("""
				"""),_display_(Seq[Any](/*79.6*/restrict(la(as("admin_operasional")))/*79.43*/ {_display_(Seq[Any](format.raw/*79.45*/("""
					"""),_display_(Seq[Any](/*80.7*/{restrictUser = "admin_operasional"})),format.raw/*80.43*/("""
				""")))})),format.raw/*81.6*/("""
				
				"""),_display_(Seq[Any](/*83.6*/for(corporate <- currentPage.getList) yield /*83.43*/ {_display_(Seq[Any](format.raw/*83.45*/("""
				<tr>
					<td>"""),_display_(Seq[Any](/*85.11*/corporate/*85.20*/.code)),format.raw/*85.25*/("""</td>
					<td>"""),_display_(Seq[Any](/*86.11*/if(corporate.country != null)/*86.40*/ {_display_(Seq[Any](_display_(Seq[Any](/*86.43*/corporate/*86.52*/.country.name))))})),format.raw/*86.66*/("""</td>
					<td>"""),_display_(Seq[Any](/*87.11*/corporate/*87.20*/.name)),format.raw/*87.25*/("""</td>
					<td class="actions">
						<a href=""""),_display_(Seq[Any](/*89.17*/routes/*89.23*/.Corporate.show(corporate.code))),format.raw/*89.54*/("""" class="btn-link btn-detail">"""),_display_(Seq[Any](/*89.85*/doku/*89.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*89.150*/("""</a>
						"""),_display_(Seq[Any](/*90.8*/if(restrictUser.equals("admin"))/*90.40*/ {_display_(Seq[Any](format.raw/*90.42*/("""
						<a href=""""),_display_(Seq[Any](/*91.17*/routes/*91.23*/.Corporate.admin_edit(corporate.code))),format.raw/*91.60*/("""" class="btn-link">"""),_display_(Seq[Any](/*91.80*/doku/*91.84*/.kirimdoku.util.MultiLanguage.getLanguage("LANG112","Edit"))),format.raw/*91.143*/("""</a>
						<a href=""""),_display_(Seq[Any](/*92.17*/routes/*92.23*/.User.index(corporate.code))),format.raw/*92.50*/("""">"""),_display_(Seq[Any](/*92.53*/doku/*92.57*/.kirimdoku.util.MultiLanguage.getLanguage("LANG310","Users"))),format.raw/*92.117*/("""</a>
						""")))})),format.raw/*93.8*/("""
						"""),_display_(Seq[Any](/*94.8*/if(restrictUser.equals("finance"))/*94.42*/ {_display_(Seq[Any](format.raw/*94.44*/("""
						<a href=""""),_display_(Seq[Any](/*95.17*/routes/*95.23*/.Forex.admin_index(corporate.code))),format.raw/*95.57*/("""">"""),_display_(Seq[Any](/*95.60*/doku/*95.64*/.kirimdoku.util.MultiLanguage.getLanguage("LANG311","Forex"))),format.raw/*95.124*/("""</a>
						<a href=""""),_display_(Seq[Any](/*96.17*/routes/*96.23*/.Fee.admin_index(corporate.code))),format.raw/*96.55*/("""">"""),_display_(Seq[Any](/*96.58*/doku/*96.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG312","Fees"))),format.raw/*96.121*/("""</a>
						<a href=""""),_display_(Seq[Any](/*97.17*/routes/*97.23*/.Credit.admin_index(corporate.code))),format.raw/*97.58*/("""">"""),_display_(Seq[Any](/*97.61*/doku/*97.65*/.kirimdoku.util.MultiLanguage.getLanguage("LANG313","Credit"))),format.raw/*97.126*/("""</a>
						<a href=""""),_display_(Seq[Any](/*98.17*/routes/*98.23*/.SetupShareSetting.view(corporate.code))),format.raw/*98.62*/("""">"""),_display_(Seq[Any](/*98.65*/doku/*98.69*/.kirimdoku.util.MultiLanguage.getLanguage("LANG314","Share"))),format.raw/*98.129*/("""</a>
						<a href=""""),_display_(Seq[Any](/*99.17*/routes/*99.23*/.User.indexOperasional(corporate.code))),format.raw/*99.61*/("""">"""),_display_(Seq[Any](/*99.64*/doku/*99.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG310","Users"))),format.raw/*99.128*/("""</a>
						""")))})),format.raw/*100.8*/("""
						"""),_display_(Seq[Any](/*101.8*/if(restrictUser.equals("admin_operasional"))/*101.52*/ {_display_(Seq[Any](format.raw/*101.54*/("""
						<a href=""""),_display_(Seq[Any](/*102.17*/routes/*102.23*/.Forex.admin_index(corporate.code))),format.raw/*102.57*/("""">"""),_display_(Seq[Any](/*102.60*/doku/*102.64*/.kirimdoku.util.MultiLanguage.getLanguage("LANG311","Forex"))),format.raw/*102.124*/("""</a>
						<a href=""""),_display_(Seq[Any](/*103.17*/routes/*103.23*/.Fee.admin_index(corporate.code))),format.raw/*103.55*/("""">"""),_display_(Seq[Any](/*103.58*/doku/*103.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG312","Fees"))),format.raw/*103.121*/("""</a>
						<a href=""""),_display_(Seq[Any](/*104.17*/routes/*104.23*/.User.indexOperasional(corporate.code))),format.raw/*104.61*/("""">"""),_display_(Seq[Any](/*104.64*/doku/*104.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG310","Users"))),format.raw/*104.128*/("""</a>
						""")))})),format.raw/*105.8*/("""
					</td>
				</tr>
				""")))})),format.raw/*108.6*/("""
				</tbody>
			</table>

			<div id="pagination" class="pagination pagination-right">
				<ul>
					"""),_display_(Seq[Any](/*114.7*/if(currentPage.hasPrev)/*114.30*/ {_display_(Seq[Any](format.raw/*114.32*/("""
					<li class="prev">
						<a href=""""),_display_(Seq[Any](/*116.17*/link(currentPage.getPageIndex - 1, null))),format.raw/*116.57*/("""">&larr; """),_display_(Seq[Any](/*116.67*/doku/*116.71*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*116.134*/("""</a>
					</li>
					""")))}/*118.8*/else/*118.13*/{_display_(Seq[Any](format.raw/*118.14*/("""
					<li class="prev">
						<a>&larr; """),_display_(Seq[Any](/*120.18*/doku/*120.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*120.85*/("""</a>
					</li>
					""")))})),format.raw/*122.7*/("""

					<li class="current">
						<a>Displaying """),_display_(Seq[Any](/*125.22*/currentPage/*125.33*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*125.67*/("""</a>
					</li>

					"""),_display_(Seq[Any](/*128.7*/if(currentPage.hasNext)/*128.30*/ {_display_(Seq[Any](format.raw/*128.32*/("""
					<li class="next">
						<a href=""""),_display_(Seq[Any](/*130.17*/link(currentPage.getPageIndex + 1, null))),format.raw/*130.57*/("""">"""),_display_(Seq[Any](/*130.60*/doku/*130.64*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*130.123*/(""" &rarr;</a>
					</li>
					""")))}/*132.8*/else/*132.13*/{_display_(Seq[Any](format.raw/*132.14*/("""
					<li class="next">
						<a>"""),_display_(Seq[Any](/*134.11*/doku/*134.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*134.74*/(""" &rarr;</a>
					</li>
					""")))})),format.raw/*136.7*/("""
				</ul>
			</div>
		""")))})),format.raw/*139.4*/("""
		</div>
		<div class="span5">
			<div id="detail-container" class="well">
				"""),_display_(Seq[Any](/*143.6*/doku/*143.10*/.kirimdoku.util.MultiLanguage.getLanguage("LANG315","Please click 'detail' for more information"))),format.raw/*143.107*/("""
			</div>
		</div>
	</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*148.38*/routes/*148.44*/.Assets.at("javascripts/corporate.js"))),format.raw/*148.82*/("""'></script>
""")))})),format.raw/*149.2*/("""
"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Corporate],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((com.avaje.ebean.Page[models.Corporate],String,String,String) => play.api.templates.Html) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/corporate/admin_index.scala.html
                    HASH: 0b25535bc6d204cb3cb8f19154b3c8ed5da6562f
                    MATRIX: 792->1|1126->649|1140->655|1234->685|1283->698|1308->701|1348->706|1382->731|1422->733|1470->745|1527->780|1566->783|1593->788|1618->796|1630->801|1669->802|1707->805|1733->810|1767->813|1797->279|1808->283|2202->122|2230->277|2258->646|2287->821|2325->824|2365->855|2405->857|2475->892|2488->896|2586->972|2639->990|2655->997|2691->1011|2775->1059|2812->1074|2998->1224|3033->1237|3086->1253|3100->1257|3195->1329|3357->1454|3371->1458|3455->1519|3505->1534|3539->1559|3579->1561|3650->1596|3665->1602|3709->1624|3774->1653|3787->1657|3882->1729|3923->1739|4064->1845|4110->1882|4149->1883|4217->1915|4230->1919|4324->1991|4362->2012|4375->2017|4414->2018|4578->2147|4679->2226|4721->2233|4833->2323|4875->2330|4976->2409|5018->2416|5117->2493|5193->2534|5227->2559|5267->2561|5309->2568|5355->2592|5392->2598|5433->2604|5469->2631|5509->2633|5551->2640|5599->2666|5636->2672|5677->2678|5723->2715|5763->2717|5805->2724|5863->2760|5900->2766|5946->2777|5999->2814|6039->2816|6095->2836|6113->2845|6140->2850|6192->2866|6230->2895|6279->2898|6297->2907|6337->2921|6389->2937|6407->2946|6434->2951|6518->2999|6533->3005|6586->3036|6653->3067|6666->3071|6750->3132|6797->3144|6838->3176|6878->3178|6931->3195|6946->3201|7005->3238|7061->3258|7074->3262|7156->3321|7213->3342|7228->3348|7277->3375|7316->3378|7329->3382|7412->3442|7455->3454|7498->3462|7541->3496|7581->3498|7634->3515|7649->3521|7705->3555|7744->3558|7757->3562|7840->3622|7897->3643|7912->3649|7966->3681|8005->3684|8018->3688|8100->3747|8157->3768|8172->3774|8229->3809|8268->3812|8281->3816|8365->3877|8422->3898|8437->3904|8498->3943|8537->3946|8550->3950|8633->4010|8690->4031|8705->4037|8765->4075|8804->4078|8817->4082|8900->4142|8944->4154|8988->4162|9042->4206|9083->4208|9137->4225|9153->4231|9210->4265|9250->4268|9264->4272|9348->4332|9406->4353|9422->4359|9477->4391|9517->4394|9531->4398|9614->4457|9672->4478|9688->4484|9749->4522|9789->4525|9803->4529|9887->4589|9931->4601|9990->4628|10129->4731|10162->4754|10203->4756|10280->4796|10343->4836|10390->4846|10404->4850|10491->4913|10532->4936|10546->4941|10586->4942|10664->4983|10678->4987|10764->5050|10818->5072|10904->5121|10925->5132|10982->5166|11041->5189|11074->5212|11115->5214|11192->5254|11255->5294|11295->5297|11309->5301|11392->5360|11440->5390|11454->5395|11494->5396|11565->5430|11579->5434|11661->5493|11722->5522|11778->5546|11895->5627|11909->5631|12030->5728|12133->5794|12149->5800|12210->5838|12255->5851
                    LINES: 26->1|33->26|33->26|35->26|36->27|36->27|37->28|37->28|37->28|38->29|38->29|38->29|38->29|39->30|39->30|39->30|40->31|40->31|41->32|43->7|43->7|61->1|63->6|64->24|66->34|68->36|68->36|68->36|70->38|70->38|70->38|73->41|73->41|73->41|77->45|77->45|79->47|79->47|79->47|79->47|79->47|79->47|79->47|79->47|80->48|80->48|80->48|81->49|81->49|81->49|81->49|81->49|81->49|82->50|90->58|90->58|90->58|92->60|92->60|92->60|94->62|94->62|94->62|98->66|98->66|99->67|99->67|100->68|100->68|101->69|101->69|105->73|105->73|105->73|106->74|106->74|107->75|108->76|108->76|108->76|109->77|109->77|110->78|111->79|111->79|111->79|112->80|112->80|113->81|115->83|115->83|115->83|117->85|117->85|117->85|118->86|118->86|118->86|118->86|118->86|119->87|119->87|119->87|121->89|121->89|121->89|121->89|121->89|121->89|122->90|122->90|122->90|123->91|123->91|123->91|123->91|123->91|123->91|124->92|124->92|124->92|124->92|124->92|124->92|125->93|126->94|126->94|126->94|127->95|127->95|127->95|127->95|127->95|127->95|128->96|128->96|128->96|128->96|128->96|128->96|129->97|129->97|129->97|129->97|129->97|129->97|130->98|130->98|130->98|130->98|130->98|130->98|131->99|131->99|131->99|131->99|131->99|131->99|132->100|133->101|133->101|133->101|134->102|134->102|134->102|134->102|134->102|134->102|135->103|135->103|135->103|135->103|135->103|135->103|136->104|136->104|136->104|136->104|136->104|136->104|137->105|140->108|146->114|146->114|146->114|148->116|148->116|148->116|148->116|148->116|150->118|150->118|150->118|152->120|152->120|152->120|154->122|157->125|157->125|157->125|160->128|160->128|160->128|162->130|162->130|162->130|162->130|162->130|164->132|164->132|164->132|166->134|166->134|166->134|168->136|171->139|175->143|175->143|175->143|180->148|180->148|180->148|181->149
                    -- GENERATED --
                */
            