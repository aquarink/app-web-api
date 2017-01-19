
package views.html.channels

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
object list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[com.avaje.ebean.Page[models.Channel],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Channel], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
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
    routes.Channels.list(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.121*/("""

"""),format.raw/*21.2*/("""

"""),format.raw/*31.2*/("""

"""),_display_(Seq[Any](/*33.2*/layout("Set Bank Routing")/*33.28*/{_display_(Seq[Any](format.raw/*33.29*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*36.10*/doku/*36.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG397","Set Bank Routing"))),format.raw/*36.85*/("""</h1>
</div>

<div class="row">
	<div class="span12">
		<div class="well">
			<form action=""""),_display_(Seq[Any](/*42.19*/link(0, "name"))),format.raw/*42.34*/("""" method="GET" class="form-search">
				<div class="input-append">
					<input type="search" id="searchbox" name="f" class="search-query span4" value=""""),_display_(Seq[Any](/*44.86*/currentFilter)),format.raw/*44.99*/("""" placeholder='"""),_display_(Seq[Any](/*44.115*/doku/*44.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG489","Search Service Id / Name..."))),format.raw/*44.201*/("""'><button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*44.266*/doku/*44.270*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*44.331*/("""</button>
				</div>
			</form>
		</div>
	</div>
</div>

"""),_display_(Seq[Any](/*51.2*/partial/*51.9*/.flash_alert())),format.raw/*51.23*/("""

<div class="row">
    <div class="span7">

        """),_display_(Seq[Any](/*56.10*/if(currentPage.getTotalRowCount == 0)/*56.47*/{_display_(Seq[Any](format.raw/*56.48*/("""
            <div class="well">
                <em>"""),_display_(Seq[Any](/*58.22*/doku/*58.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG280","Data results are not found"))),format.raw/*58.107*/("""</em>
            </div>
        """)))}/*60.11*/else/*60.16*/{_display_(Seq[Any](format.raw/*60.17*/("""
            <table class="channel table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*64.26*/header("code", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG398","Service ID")))),format.raw/*64.111*/("""
                        """),_display_(Seq[Any](/*65.26*/header("name", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG399","Service Name")))),format.raw/*65.113*/("""
                        """),_display_(Seq[Any](/*66.26*/header("", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*66.103*/("""
                    </tr>
                </thead>
                <tbody>
                    """),_display_(Seq[Any](/*70.22*/for(channel <- currentPage.getList) yield /*70.57*/ {_display_(Seq[Any](format.raw/*70.59*/("""
                        <tr>
							<td style="text-align: center;">"""),_display_(Seq[Any](/*72.41*/channel/*72.48*/.code)),format.raw/*72.53*/("""</td>
                            <td>"""),_display_(Seq[Any](/*73.34*/channel/*73.41*/.name)),format.raw/*73.46*/("""</td>
							<td class="actions" style="text-align: center;">
								<a href=""""),_display_(Seq[Any](/*75.19*/routes/*75.25*/.Channels.edit(channel.code))),format.raw/*75.53*/("""">"""),_display_(Seq[Any](/*75.56*/doku/*75.60*/.kirimdoku.util.MultiLanguage.getLanguage("LANG400","Manage Route"))),format.raw/*75.127*/("""</a>
							</td>
                        </tr>
                    """)))})),format.raw/*78.22*/("""
                </tbody>
            </table>

            <div id="pagination" class="pagination pagination-right">
                <ul>
                    """),_display_(Seq[Any](/*84.22*/if(currentPage.hasPrev)/*84.45*/ {_display_(Seq[Any](format.raw/*84.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*86.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*86.75*/("""">&larr; """),_display_(Seq[Any](/*86.85*/doku/*86.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*86.152*/("""</a>
                    </li>
                    """)))}/*88.23*/else/*88.28*/{_display_(Seq[Any](format.raw/*88.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*90.36*/doku/*90.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*90.103*/("""</a>
                    </li>
                    """)))})),format.raw/*92.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*95.39*/currentPage/*95.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*95.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*98.22*/if(currentPage.hasNext)/*98.45*/ {_display_(Seq[Any](format.raw/*98.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*100.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*100.75*/("""">"""),_display_(Seq[Any](/*100.78*/doku/*100.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*100.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*102.23*/else/*102.28*/{_display_(Seq[Any](format.raw/*102.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*104.29*/doku/*104.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*104.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*106.22*/("""
                </ul>
            </div>
        """)))})),format.raw/*109.10*/("""
    </div>
	<div class="span5">
	   <div class="well" id="channel-detail-container">
			"""),_display_(Seq[Any](/*113.5*/doku/*113.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG401","You can view detail of channel by clicking 'Detail' on the desired row of channel"))),format.raw/*113.145*/("""
		</div>
    </div>
</div>

<script type="text/javascript">
<!--
	(function() """),format.raw/*120.14*/("""{"""),format.raw/*120.15*/("""
	  var __bind = function(fn, me)"""),format.raw/*121.33*/("""{"""),format.raw/*121.34*/(""" return function()"""),format.raw/*121.52*/("""{"""),format.raw/*121.53*/(""" return fn.apply(me, arguments); """),format.raw/*121.86*/("""}"""),format.raw/*121.87*/("""; """),format.raw/*121.89*/("""}"""),format.raw/*121.90*/(""";
	  $(__bind(function() """),format.raw/*122.24*/("""{"""),format.raw/*122.25*/("""
	    var $detailContainer;
	    $detailContainer = $("#channel-detail-container");
	    return $('a.btn-channel-detail').on('ajax:beforeSend', function() """),format.raw/*125.72*/("""{"""),format.raw/*125.73*/("""
	      return $detailContainer.loading();
	    """),format.raw/*127.6*/("""}"""),format.raw/*127.7*/(""").on('ajax:success', function(e, data) """),format.raw/*127.46*/("""{"""),format.raw/*127.47*/("""
	      return $detailContainer.html(data);
	    """),format.raw/*129.6*/("""}"""),format.raw/*129.7*/(""").on('ajax:error', function(e, err) """),format.raw/*129.43*/("""{"""),format.raw/*129.44*/("""
	      return $detailContainer.html(e);
	    """),format.raw/*131.6*/("""}"""),format.raw/*131.7*/(""");
	  """),format.raw/*132.4*/("""}"""),format.raw/*132.5*/(""", this));
	"""),format.raw/*133.2*/("""}"""),format.raw/*133.3*/(""").call(this);
//-->
</script>

""")))})),format.raw/*137.2*/("""
"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Channel],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((com.avaje.ebean.Page[models.Channel],String,String,String) => play.api.templates.Html) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/channels/list.scala.html
                    HASH: fea8f2f7568d12ba09fa162afb1be135d3aedfb9
                    MATRIX: 782->1|962->581|976->587|1070->617|1119->630|1144->633|1212->666|1246->691|1286->693|1334->705|1391->740|1459->772|1486->777|1511->785|1523->790|1562->791|1600->794|1626->799|1660->802|1690->123|1701->127|2183->120|2212->578|2241->810|2279->813|2314->839|2353->840|2426->877|2439->881|2532->952|2661->1045|2698->1060|2886->1212|2921->1225|2974->1241|2988->1245|3093->1327|3195->1392|3209->1396|3293->1457|3386->1515|3401->1522|3437->1536|3527->1590|3573->1627|3612->1628|3701->1681|3714->1685|3818->1766|3871->1801|3884->1806|3923->1807|4121->1969|4229->2054|4291->2080|4401->2167|4463->2193|4563->2270|4696->2367|4747->2402|4787->2404|4893->2474|4909->2481|4936->2486|5011->2525|5027->2532|5054->2537|5170->2617|5185->2623|5235->2651|5274->2654|5287->2658|5377->2725|5478->2794|5674->2954|5706->2977|5746->2979|5855->3052|5917->3092|5963->3102|5976->3106|6062->3169|6133->3222|6146->3227|6185->3228|6295->3302|6308->3306|6394->3369|6478->3421|6594->3501|6614->3512|6670->3546|6758->3598|6790->3621|6830->3623|6940->3696|7003->3736|7043->3739|7057->3743|7140->3802|7219->3862|7233->3867|7273->3868|7377->3935|7391->3939|7473->3998|7565->4057|7649->4108|7775->4198|7788->4202|7948->4338|8056->4417|8086->4418|8148->4451|8178->4452|8225->4470|8255->4471|8317->4504|8347->4505|8378->4507|8408->4508|8462->4533|8492->4534|8676->4689|8706->4690|8782->4738|8811->4739|8879->4778|8909->4779|8986->4828|9015->4829|9080->4865|9110->4866|9184->4912|9213->4913|9247->4919|9276->4920|9315->4931|9344->4932|9408->4964
                    LINES: 26->1|28->23|28->23|30->23|31->24|31->24|32->25|32->25|32->25|33->26|33->26|33->26|33->26|34->27|34->27|34->27|35->28|35->28|36->29|38->3|38->3|57->1|59->21|61->31|63->33|63->33|63->33|66->36|66->36|66->36|72->42|72->42|74->44|74->44|74->44|74->44|74->44|74->44|74->44|74->44|81->51|81->51|81->51|86->56|86->56|86->56|88->58|88->58|88->58|90->60|90->60|90->60|94->64|94->64|95->65|95->65|96->66|96->66|100->70|100->70|100->70|102->72|102->72|102->72|103->73|103->73|103->73|105->75|105->75|105->75|105->75|105->75|105->75|108->78|114->84|114->84|114->84|116->86|116->86|116->86|116->86|116->86|118->88|118->88|118->88|120->90|120->90|120->90|122->92|125->95|125->95|125->95|128->98|128->98|128->98|130->100|130->100|130->100|130->100|130->100|132->102|132->102|132->102|134->104|134->104|134->104|136->106|139->109|143->113|143->113|143->113|150->120|150->120|151->121|151->121|151->121|151->121|151->121|151->121|151->121|151->121|152->122|152->122|155->125|155->125|157->127|157->127|157->127|157->127|159->129|159->129|159->129|159->129|161->131|161->131|162->132|162->132|163->133|163->133|167->137
                    -- GENERATED --
                */
            