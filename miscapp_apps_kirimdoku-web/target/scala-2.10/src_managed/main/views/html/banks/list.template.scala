
package views.html.banks

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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[com.avaje.ebean.Page[models.Bank],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Bank], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {
def /*23.2*/header/*23.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*23.38*/("""
<th class=""""),_display_(Seq[Any](/*24.13*/key)),format.raw/*24.16*/("""" style="text-align: center;">
	"""),_display_(Seq[Any](/*25.3*/if(key && key.length > 0)/*25.28*/ {_display_(Seq[Any](format.raw/*25.30*/("""
	<a href=""""),_display_(Seq[Any](/*26.12*/link(currentPage.getPageIndex, key))),format.raw/*26.47*/("""" style="white-space: nowrap;">"""),_display_(Seq[Any](/*26.79*/title)),format.raw/*26.84*/("""</a>
	""")))}/*27.4*/else/*27.9*/{_display_(Seq[Any](format.raw/*27.10*/("""
	"""),_display_(Seq[Any](/*28.3*/title)),format.raw/*28.8*/("""
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
    routes.Banks.list(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.118*/("""

"""),format.raw/*21.2*/("""

"""),format.raw/*31.2*/("""

"""),_display_(Seq[Any](/*33.2*/layout("Manage List of Bank")/*33.31*/{_display_(Seq[Any](format.raw/*33.32*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*36.10*/doku/*36.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG391","Manage List of Bank"))),format.raw/*36.88*/("""</h1>
</div>

<div class="row">
	<div class="span12">
		<div class="well">
			<form action=""""),_display_(Seq[Any](/*42.19*/link(0, "name"))),format.raw/*42.34*/("""" method="GET" class="form-search">
				<div class="input-append">
					<input type="search" id="searchbox" name="f" class="search-query span4" value=""""),_display_(Seq[Any](/*44.86*/currentFilter)),format.raw/*44.99*/("""" placeholder='"""),_display_(Seq[Any](/*44.115*/doku/*44.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG488","Search by Bank ID / Swift Code / Name..."))),format.raw/*44.214*/("""'><button type="submit" class="btn" style="border-radius:0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*44.306*/doku/*44.310*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*44.371*/("""</button>
					<a class="btn success" href=""""),_display_(Seq[Any](/*45.36*/routes/*45.42*/.Banks.create().toString())),format.raw/*45.68*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*45.97*/doku/*45.101*/.kirimdoku.util.MultiLanguage.getLanguage("LANG392","Add New Bank"))),format.raw/*45.168*/("""</a>
				</div>
			</form>
		</div>
	</div>
</div>

"""),_display_(Seq[Any](/*52.2*/partial/*52.9*/.flash_alert())),format.raw/*52.23*/("""

<div class="row">
    <div class="span7">

        """),_display_(Seq[Any](/*57.10*/if(currentPage.getTotalRowCount == 0)/*57.47*/{_display_(Seq[Any](format.raw/*57.48*/("""
            <div class="well">
                <em>"""),_display_(Seq[Any](/*59.22*/doku/*59.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG280","Data results are not found"))),format.raw/*59.107*/("""</em>
            </div>
        """)))}/*61.11*/else/*61.16*/{_display_(Seq[Any](format.raw/*61.17*/("""
            <table class="bank table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*65.26*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG365","ID")))),format.raw/*65.101*/("""
                        """),_display_(Seq[Any](/*66.26*/header("code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG064","Swift Code")))),format.raw/*66.111*/("""
                        """),_display_(Seq[Any](/*67.26*/header("name", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank Name")))),format.raw/*67.110*/("""
                        """),_display_(Seq[Any](/*68.26*/header("", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*68.103*/("""
                    </tr>
                </thead>
                <tbody>
                    """),_display_(Seq[Any](/*72.22*/for(bank <- currentPage.getList) yield /*72.54*/ {_display_(Seq[Any](format.raw/*72.56*/("""
                        <tr>
                            <td style="text-align: center;">"""),_display_(Seq[Any](/*74.62*/bank/*74.66*/.id)),format.raw/*74.69*/("""</td>
							<td style="text-align: center;">"""),_display_(Seq[Any](/*75.41*/bank/*75.45*/.code)),format.raw/*75.50*/("""</td>
                            <td>"""),_display_(Seq[Any](/*76.34*/bank/*76.38*/.name)),format.raw/*76.43*/("""</td>
							<td class="actions" style="text-align: center;;">
								<a href=""""),_display_(Seq[Any](/*78.19*/routes/*78.25*/.Banks.summary(bank.id))),format.raw/*78.48*/("""" class="btn-detail btn-bank-detail" data-remote="true">"""),_display_(Seq[Any](/*78.105*/doku/*78.109*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*78.170*/("""</a>
								<a href=""""),_display_(Seq[Any](/*79.19*/routes/*79.25*/.Banks.edit(bank.id))),format.raw/*79.45*/("""">"""),_display_(Seq[Any](/*79.48*/doku/*79.52*/.kirimdoku.util.MultiLanguage.getLanguage("LANG112","Edit"))),format.raw/*79.111*/("""</a>
							</td>
                        </tr>
                    """)))})),format.raw/*82.22*/("""
                </tbody>
            </table>

            <div id="pagination" class="pagination pagination-right">
                <ul>
					<li>
						<a class="btn" href=""""),_display_(Seq[Any](/*89.29*/routes/*89.35*/.Banks.csv().toString())),format.raw/*89.58*/(""""><i class="icon-download-alt"></i> """),_display_(Seq[Any](/*89.95*/doku/*89.99*/.kirimdoku.util.MultiLanguage.getLanguage("LANG128","Export as CSV"))),format.raw/*89.167*/("""</a>
					</li>
                    """),_display_(Seq[Any](/*91.22*/if(currentPage.hasPrev)/*91.45*/ {_display_(Seq[Any](format.raw/*91.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*93.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*93.75*/("""">&larr; """),_display_(Seq[Any](/*93.85*/doku/*93.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*93.152*/("""</a>
                    </li>
                    """)))}/*95.23*/else/*95.28*/{_display_(Seq[Any](format.raw/*95.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*97.36*/doku/*97.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*97.103*/("""</a>
                    </li>
                    """)))})),format.raw/*99.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*102.39*/currentPage/*102.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*102.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*105.22*/if(currentPage.hasNext)/*105.45*/ {_display_(Seq[Any](format.raw/*105.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*107.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*107.75*/("""">"""),_display_(Seq[Any](/*107.78*/doku/*107.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*107.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*109.23*/else/*109.28*/{_display_(Seq[Any](format.raw/*109.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*111.29*/doku/*111.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*111.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*113.22*/("""
                </ul>
            </div>
        """)))})),format.raw/*116.10*/("""
    </div>
	<div class="span5">
	   <div class="well" id="bank-detail-container">
			"""),_display_(Seq[Any](/*120.5*/doku/*120.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG393","You can view detail of bank by clicking 'Detail' on the desired row of bank"))),format.raw/*120.139*/("""
		</div>
    </div>
</div>

<script type="text/javascript">
<!--
	(function() """),format.raw/*127.14*/("""{"""),format.raw/*127.15*/("""
	  var __bind = function(fn, me)"""),format.raw/*128.33*/("""{"""),format.raw/*128.34*/(""" return function()"""),format.raw/*128.52*/("""{"""),format.raw/*128.53*/(""" return fn.apply(me, arguments); """),format.raw/*128.86*/("""}"""),format.raw/*128.87*/("""; """),format.raw/*128.89*/("""}"""),format.raw/*128.90*/(""";
	  $(__bind(function() """),format.raw/*129.24*/("""{"""),format.raw/*129.25*/("""
	    var $detailContainer;
	    $detailContainer = $("#bank-detail-container");
	    return $('a.btn-bank-detail').on('ajax:beforeSend', function() """),format.raw/*132.69*/("""{"""),format.raw/*132.70*/("""
	      return $detailContainer.loading();
	    """),format.raw/*134.6*/("""}"""),format.raw/*134.7*/(""").on('ajax:success', function(e, data) """),format.raw/*134.46*/("""{"""),format.raw/*134.47*/("""
	      return $detailContainer.html(data);
	    """),format.raw/*136.6*/("""}"""),format.raw/*136.7*/(""").on('ajax:error', function(e, err) """),format.raw/*136.43*/("""{"""),format.raw/*136.44*/("""
	      return $detailContainer.html(e);
	    """),format.raw/*138.6*/("""}"""),format.raw/*138.7*/(""");
	  """),format.raw/*139.4*/("""}"""),format.raw/*139.5*/(""", this));
	"""),format.raw/*140.2*/("""}"""),format.raw/*140.3*/(""").call(this);
//-->
</script>

""")))})),format.raw/*144.2*/("""
"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Bank],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((com.avaje.ebean.Page[models.Bank],String,String,String) => play.api.templates.Html) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/banks/list.scala.html
                    HASH: e43e45863e32890903ac5288daa37f884b353e25
                    MATRIX: 776->1|953->575|967->581|1061->611|1110->624|1135->627|1203->660|1237->685|1277->687|1325->699|1382->734|1450->766|1477->771|1502->779|1514->784|1553->785|1591->788|1617->793|1651->796|1681->120|1692->124|2171->117|2200->572|2229->804|2267->807|2305->836|2344->837|2417->874|2430->878|2526->952|2655->1045|2692->1060|2880->1212|2915->1225|2968->1241|2982->1245|3100->1340|3229->1432|3243->1436|3327->1497|3408->1542|3423->1548|3471->1574|3536->1603|3550->1607|3640->1674|3728->1727|3743->1734|3779->1748|3869->1802|3915->1839|3954->1840|4043->1893|4056->1897|4160->1978|4213->2013|4226->2018|4265->2019|4460->2178|4558->2253|4620->2279|4728->2364|4790->2390|4897->2474|4959->2500|5059->2577|5192->2674|5240->2706|5280->2708|5407->2799|5420->2803|5445->2806|5527->2852|5540->2856|5567->2861|5642->2900|5655->2904|5682->2909|5799->2990|5814->2996|5859->3019|5953->3076|5967->3080|6051->3141|6110->3164|6125->3170|6167->3190|6206->3193|6219->3197|6301->3256|6402->3325|6615->3502|6630->3508|6675->3531|6748->3568|6761->3572|6852->3640|6925->3677|6957->3700|6997->3702|7106->3775|7168->3815|7214->3825|7227->3829|7313->3892|7384->3945|7397->3950|7436->3951|7546->4025|7559->4029|7645->4092|7729->4144|7846->4224|7867->4235|7924->4269|8013->4321|8046->4344|8087->4346|8197->4419|8260->4459|8300->4462|8314->4466|8397->4525|8476->4585|8490->4590|8530->4591|8634->4658|8648->4662|8730->4721|8822->4780|8906->4831|9029->4918|9042->4922|9196->5052|9304->5131|9334->5132|9396->5165|9426->5166|9473->5184|9503->5185|9565->5218|9595->5219|9626->5221|9656->5222|9710->5247|9740->5248|9918->5397|9948->5398|10024->5446|10053->5447|10121->5486|10151->5487|10228->5536|10257->5537|10322->5573|10352->5574|10426->5620|10455->5621|10489->5627|10518->5628|10557->5639|10586->5640|10650->5672
                    LINES: 26->1|28->23|28->23|30->23|31->24|31->24|32->25|32->25|32->25|33->26|33->26|33->26|33->26|34->27|34->27|34->27|35->28|35->28|36->29|38->3|38->3|57->1|59->21|61->31|63->33|63->33|63->33|66->36|66->36|66->36|72->42|72->42|74->44|74->44|74->44|74->44|74->44|74->44|74->44|74->44|75->45|75->45|75->45|75->45|75->45|75->45|82->52|82->52|82->52|87->57|87->57|87->57|89->59|89->59|89->59|91->61|91->61|91->61|95->65|95->65|96->66|96->66|97->67|97->67|98->68|98->68|102->72|102->72|102->72|104->74|104->74|104->74|105->75|105->75|105->75|106->76|106->76|106->76|108->78|108->78|108->78|108->78|108->78|108->78|109->79|109->79|109->79|109->79|109->79|109->79|112->82|119->89|119->89|119->89|119->89|119->89|119->89|121->91|121->91|121->91|123->93|123->93|123->93|123->93|123->93|125->95|125->95|125->95|127->97|127->97|127->97|129->99|132->102|132->102|132->102|135->105|135->105|135->105|137->107|137->107|137->107|137->107|137->107|139->109|139->109|139->109|141->111|141->111|141->111|143->113|146->116|150->120|150->120|150->120|157->127|157->127|158->128|158->128|158->128|158->128|158->128|158->128|158->128|158->128|159->129|159->129|162->132|162->132|164->134|164->134|164->134|164->134|166->136|166->136|166->136|166->136|168->138|168->138|169->139|169->139|170->140|170->140|174->144
                    -- GENERATED --
                */
            