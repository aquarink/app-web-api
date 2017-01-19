
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
object addrelate extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[models.Customer,com.avaje.ebean.Page[models.Customer],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(customer: models.Customer, currentPage: com.avaje.ebean.Page[models.Customer], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
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
    routes.Customer.addRelate(customer.id, newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.149*/("""

"""),format.raw/*21.2*/("""

"""),format.raw/*31.2*/("""

"""),_display_(Seq[Any](/*33.2*/layout("Add Customers Relate")/*33.32*/{_display_(Seq[Any](format.raw/*33.33*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*36.10*/doku/*36.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG529","Add Customers Relate"))),format.raw/*36.89*/(""" """),_display_(Seq[Any](/*36.91*/customer/*36.99*/.firstName)),format.raw/*36.109*/(""" """),_display_(Seq[Any](/*36.111*/customer/*36.119*/.lastName)),format.raw/*36.128*/("""</h1>
</div>

<div class="row">
	<div class="span12">
		<div class="well">
			<form action=""""),_display_(Seq[Any](/*42.19*/link(0, "name"))),format.raw/*42.34*/("""" method="GET" class="form-search">
			<input type="hidden" value=""""),_display_(Seq[Any](/*43.33*/customer/*43.41*/.id)),format.raw/*43.44*/("""" name="id">
				<div class="input-append">
					<input type="search" id="searchbox" name="f" class="search-query span4" value=""""),_display_(Seq[Any](/*45.86*/currentFilter)),format.raw/*45.99*/("""" placeholder='"""),_display_(Seq[Any](/*45.115*/doku/*45.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG109","Filter by Personal ID / Name..."))),format.raw/*45.205*/("""'>
					<button type="submit" class="btn" style="border-radius:0px;"><i class="icon-search"></i> """),_display_(Seq[Any](/*46.96*/doku/*46.100*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*46.161*/("""</button>
					<button type="button" class="btn" style="border-radius:0px;" id="relate"><i class="icon-plus"></i> """),_display_(Seq[Any](/*47.106*/doku/*47.110*/.kirimdoku.util.MultiLanguage.getLanguage("LANG522","Relate"))),format.raw/*47.171*/("""</button>
					<a class="btn success" href=""""),_display_(Seq[Any](/*48.36*/routes/*48.42*/.Customer.relate(customer.id))),format.raw/*48.71*/(""""><i class="icon-repeat"></i> """),_display_(Seq[Any](/*48.102*/doku/*48.106*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*48.165*/("""</a>
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
        	<form action="./" method="post" id="customerList">
        	<input type="hidden" value=""""),_display_(Seq[Any](/*66.39*/customer/*66.47*/.id)),format.raw/*66.50*/("""" name="id">
            <table class="customer table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                    	<th style="text-align: center;">
					        <input type="checkbox" id="checkAll">
					    </th>
                        """),_display_(Seq[Any](/*73.26*/header("id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID")))),format.raw/*73.110*/("""
                        """),_display_(Seq[Any](/*74.26*/header("country", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")))),format.raw/*74.115*/("""
                        """),_display_(Seq[Any](/*75.26*/header("firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First Name")))),format.raw/*75.116*/("""
                        """),_display_(Seq[Any](/*76.26*/header("lastName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last Name")))),format.raw/*76.114*/("""
                        """),_display_(Seq[Any](/*77.26*/header("phoneNumber", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone Number")))),format.raw/*77.120*/("""
                        """),_display_(Seq[Any](/*78.26*/header("action", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action")))),format.raw/*78.109*/("""
                    </tr>
                </thead>
                <tbody>
                    """),_display_(Seq[Any](/*82.22*/for(customerRelate <- currentPage.getList) yield /*82.64*/ {_display_(Seq[Any](format.raw/*82.66*/("""
                        <tr>
                        	<td style="text-align: center;">
                        		<input type="checkbox" class="chk" name="customerId[]" value=""""),_display_(Seq[Any](/*85.90*/customerRelate/*85.104*/.id)),format.raw/*85.107*/("""">
                        	</td>
                            <td>"""),_display_(Seq[Any](/*87.34*/customerRelate/*87.48*/.getIdToken)),format.raw/*87.59*/("""</td>
							<td>"""),_display_(Seq[Any](/*88.13*/customerRelate/*88.27*/.country.name)),format.raw/*88.40*/("""</td>
                            <td>"""),_display_(Seq[Any](/*89.34*/customerRelate/*89.48*/.firstName)),format.raw/*89.58*/("""</td>
                            <td>"""),_display_(Seq[Any](/*90.34*/customerRelate/*90.48*/.lastName)),format.raw/*90.57*/("""</td>
                            <td>"""),_display_(Seq[Any](/*91.34*/customerRelate/*91.48*/.phoneNumber)),format.raw/*91.60*/("""</td>
							<td class="actions">
								<a href=""""),_display_(Seq[Any](/*93.19*/routes/*93.25*/.Customer.summary(customerRelate.id))),format.raw/*93.61*/("""" class="btn-detail btn-customer-detail" data-remote="true">"""),_display_(Seq[Any](/*93.122*/doku/*93.126*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*93.187*/("""</a>
							</td>
                        </tr>
                    """)))})),format.raw/*96.22*/("""
                </tbody>
            </table>
			</form>
            <div id="pagination" class="pagination pagination-right">
                <ul>
					<li>
						<a class="btn" href=""""),_display_(Seq[Any](/*103.29*/routes/*103.35*/.Customer.csv().toString())),format.raw/*103.61*/(""""><i class="icon-download-alt"></i> """),_display_(Seq[Any](/*103.98*/doku/*103.102*/.kirimdoku.util.MultiLanguage.getLanguage("LANG128","Export as CSV"))),format.raw/*103.170*/("""</a>
					</li>
                    """),_display_(Seq[Any](/*105.22*/if(currentPage.hasPrev)/*105.45*/ {_display_(Seq[Any](format.raw/*105.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*107.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*107.75*/("""">&larr; """),_display_(Seq[Any](/*107.85*/doku/*107.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*107.152*/("""</a>
                    </li>
                    """)))}/*109.23*/else/*109.28*/{_display_(Seq[Any](format.raw/*109.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*111.36*/doku/*111.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*111.103*/("""</a>
                    </li>
                    """)))})),format.raw/*113.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*116.39*/currentPage/*116.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*116.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*119.22*/if(currentPage.hasNext)/*119.45*/ {_display_(Seq[Any](format.raw/*119.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*121.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*121.75*/("""">"""),_display_(Seq[Any](/*121.78*/doku/*121.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*121.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*123.23*/else/*123.28*/{_display_(Seq[Any](format.raw/*123.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*125.29*/doku/*125.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*125.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*127.22*/("""
                </ul>
            </div>
        """)))})),format.raw/*130.10*/("""
    </div>
	<div class="span5">
	   <div class="well" id="customer-detail-container">
			"""),_display_(Seq[Any](/*134.5*/doku/*134.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG281","You can view detail of customer by clicking 'Detail' on the desired row of customer"))),format.raw/*134.147*/("""
		</div>
    </div>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <center id="myModalLabel">
	    <h1 class="icon-ok"></h1>
	    <h3 >Customer Relation Successfully</h3>
    </center>
  </div>
  <div class="modal-body">
    <center><button id="okayClose" class="btn btn-primary">OK</button></center>
  </div>
</div>
<script type="text/javascript">
<!--
	$('#checkAll').change(function() """),format.raw/*151.35*/("""{"""),format.raw/*151.36*/("""
		if(this.checked) """),format.raw/*152.20*/("""{"""),format.raw/*152.21*/("""
			$('.chk').prop('checked', true);
		"""),format.raw/*154.3*/("""}"""),format.raw/*154.4*/(""" else """),format.raw/*154.10*/("""{"""),format.raw/*154.11*/("""
			$('.chk').prop('checked', false);
		"""),format.raw/*156.3*/("""}"""),format.raw/*156.4*/("""
	"""),format.raw/*157.2*/("""}"""),format.raw/*157.3*/(""");
	
	$('#myForm').on('submit', function(e)"""),format.raw/*159.39*/("""{"""),format.raw/*159.40*/("""
		var statusSubmit = false;
		$(".chk").each(function() """),format.raw/*161.29*/("""{"""),format.raw/*161.30*/("""
			if(this.checked) """),format.raw/*162.21*/("""{"""),format.raw/*162.22*/("""
				statusSubmit = true;
				return false;
			"""),format.raw/*165.4*/("""}"""),format.raw/*165.5*/("""
		"""),format.raw/*166.3*/("""}"""),format.raw/*166.4*/(""");
		if (!statusSubmit) """),format.raw/*167.22*/("""{"""),format.raw/*167.23*/("""
			alert('Please check one or more customer');
			return false;
		"""),format.raw/*170.3*/("""}"""),format.raw/*170.4*/(""" else """),format.raw/*170.10*/("""{"""),format.raw/*170.11*/("""
			return true;
		"""),format.raw/*172.3*/("""}"""),format.raw/*172.4*/("""
	"""),format.raw/*173.2*/("""}"""),format.raw/*173.3*/(""");
	
	$('#relate').on('click', function(e)"""),format.raw/*175.38*/("""{"""),format.raw/*175.39*/("""
		var statusSubmit = false;
		$(".chk").each(function() """),format.raw/*177.29*/("""{"""),format.raw/*177.30*/("""
			if(this.checked) """),format.raw/*178.21*/("""{"""),format.raw/*178.22*/("""
				statusSubmit = true;
				return false;
			"""),format.raw/*181.4*/("""}"""),format.raw/*181.5*/("""
		"""),format.raw/*182.3*/("""}"""),format.raw/*182.4*/(""");
		if (!statusSubmit) """),format.raw/*183.22*/("""{"""),format.raw/*183.23*/("""
			alert('Please check one or more customer');
			return false;
		"""),format.raw/*186.3*/("""}"""),format.raw/*186.4*/(""" else """),format.raw/*186.10*/("""{"""),format.raw/*186.11*/("""
			console.log('id : '+$('#customerList').serialize())
			
			$.ajax("""),format.raw/*189.11*/("""{"""),format.raw/*189.12*/("""
			    type: 'POST',
			    url: '"""),_display_(Seq[Any](/*191.15*/routes/*191.21*/.Customer.addRelateAction())),format.raw/*191.48*/("""',
			    data: $('#customerList').serialize(),
			    error: function( xhr ) """),format.raw/*193.31*/("""{"""),format.raw/*193.32*/("""
			    	alert('Failed Add Relate');
			    """),format.raw/*195.8*/("""}"""),format.raw/*195.9*/(""",
			    beforeSend: function( xhr ) """),format.raw/*196.36*/("""{"""),format.raw/*196.37*/("""
			
			    """),format.raw/*198.8*/("""}"""),format.raw/*198.9*/(""",
			    success: function( xhr ) """),format.raw/*199.33*/("""{"""),format.raw/*199.34*/("""
			        console.log('result : '+xhr)
			        if (xhr == 'SUCCESS') """),format.raw/*201.34*/("""{"""),format.raw/*201.35*/("""
			        	$("#myModal").modal();
			        	$("#okayClose").click(function()"""),format.raw/*203.45*/("""{"""),format.raw/*203.46*/("""
			        		location.reload();
			            """),format.raw/*205.16*/("""}"""),format.raw/*205.17*/(""");
			        """),format.raw/*206.12*/("""}"""),format.raw/*206.13*/("""
			    """),format.raw/*207.8*/("""}"""),format.raw/*207.9*/("""
			"""),format.raw/*208.4*/("""}"""),format.raw/*208.5*/(""");
			
			return true;
		"""),format.raw/*211.3*/("""}"""),format.raw/*211.4*/("""
	"""),format.raw/*212.2*/("""}"""),format.raw/*212.3*/(""");
	
//-->
</script>
<script type="text/javascript" src='"""),_display_(Seq[Any](/*216.38*/routes/*216.44*/.Assets.at("javascripts/customer.js"))),format.raw/*216.81*/("""'></script>
""")))})),format.raw/*217.2*/("""
"""))}
    }
    
    def render(customer:models.Customer,currentPage:com.avaje.ebean.Page[models.Customer],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(customer,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((models.Customer,com.avaje.ebean.Page[models.Customer],String,String,String) => play.api.templates.Html) = (customer,currentPage,currentSortBy,currentOrder,currentFilter) => apply(customer,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/addrelate.scala.html
                    HASH: b154baba69e21fb47a03b808c231bbcde3b6922b
                    MATRIX: 804->1|1012->627|1026->633|1120->663|1169->676|1194->679|1262->712|1296->737|1336->739|1385->752|1442->787|1481->790|1508->795|1533->803|1545->808|1584->809|1623->813|1649->818|1683->821|1713->151|1724->155|2224->148|2253->624|2282->829|2320->832|2359->862|2398->863|2471->900|2484->904|2581->979|2619->981|2636->989|2669->999|2708->1001|2726->1009|2758->1018|2887->1111|2924->1126|3028->1194|3045->1202|3070->1205|3235->1334|3270->1347|3323->1363|3337->1367|3446->1453|3580->1551|3594->1555|3678->1616|3830->1731|3844->1735|3928->1796|4009->1841|4024->1847|4075->1876|4143->1907|4157->1911|4239->1970|4327->2023|4342->2030|4378->2044|4468->2098|4514->2135|4553->2136|4642->2189|4655->2193|4759->2274|4812->2309|4825->2314|4864->2315|4999->2414|5016->2422|5041->2425|5372->2720|5479->2804|5541->2830|5653->2919|5715->2945|5828->3035|5890->3061|6001->3149|6063->3175|6180->3269|6242->3295|6348->3378|6481->3475|6539->3517|6579->3519|6792->3696|6816->3710|6842->3713|6945->3780|6968->3794|7001->3805|7055->3823|7078->3837|7113->3850|7188->3889|7211->3903|7243->3913|7318->3952|7341->3966|7372->3975|7447->4014|7470->4028|7504->4040|7592->4092|7607->4098|7665->4134|7763->4195|7777->4199|7861->4260|7962->4329|8186->4516|8202->4522|8251->4548|8325->4585|8340->4589|8432->4657|8506->4694|8539->4717|8580->4719|8690->4792|8753->4832|8800->4842|8814->4846|8901->4909|8973->4962|8987->4967|9027->4968|9138->5042|9152->5046|9239->5109|9324->5161|9441->5241|9462->5252|9519->5286|9608->5338|9641->5361|9682->5363|9792->5436|9855->5476|9895->5479|9909->5483|9992->5542|10071->5602|10085->5607|10125->5608|10229->5675|10243->5679|10325->5738|10417->5797|10501->5848|10628->5939|10641->5943|10803->6081|11335->6584|11365->6585|11414->6605|11444->6606|11511->6645|11540->6646|11575->6652|11605->6653|11673->6693|11702->6694|11732->6696|11761->6697|11833->6740|11863->6741|11949->6798|11979->6799|12029->6820|12059->6821|12134->6868|12163->6869|12194->6872|12223->6873|12276->6897|12306->6898|12401->6965|12430->6966|12465->6972|12495->6973|12542->6992|12571->6993|12601->6995|12630->6996|12701->7038|12731->7039|12817->7096|12847->7097|12897->7118|12927->7119|13002->7166|13031->7167|13062->7170|13091->7171|13144->7195|13174->7196|13269->7263|13298->7264|13333->7270|13363->7271|13462->7341|13492->7342|13565->7378|13581->7384|13631->7411|13738->7489|13768->7490|13840->7534|13869->7535|13935->7572|13965->7573|14005->7585|14034->7586|14097->7620|14127->7621|14230->7695|14260->7696|14369->7776|14399->7777|14476->7825|14506->7826|14549->7840|14579->7841|14615->7849|14644->7850|14676->7854|14705->7855|14758->7880|14787->7881|14817->7883|14846->7884|14941->7942|14957->7948|15017->7985|15062->7998
                    LINES: 26->1|28->23|28->23|30->23|31->24|31->24|32->25|32->25|32->25|33->26|33->26|33->26|33->26|34->27|34->27|34->27|35->28|35->28|36->29|38->3|38->3|57->1|59->21|61->31|63->33|63->33|63->33|66->36|66->36|66->36|66->36|66->36|66->36|66->36|66->36|66->36|72->42|72->42|73->43|73->43|73->43|75->45|75->45|75->45|75->45|75->45|76->46|76->46|76->46|77->47|77->47|77->47|78->48|78->48|78->48|78->48|78->48|78->48|85->55|85->55|85->55|90->60|90->60|90->60|92->62|92->62|92->62|94->64|94->64|94->64|96->66|96->66|96->66|103->73|103->73|104->74|104->74|105->75|105->75|106->76|106->76|107->77|107->77|108->78|108->78|112->82|112->82|112->82|115->85|115->85|115->85|117->87|117->87|117->87|118->88|118->88|118->88|119->89|119->89|119->89|120->90|120->90|120->90|121->91|121->91|121->91|123->93|123->93|123->93|123->93|123->93|123->93|126->96|133->103|133->103|133->103|133->103|133->103|133->103|135->105|135->105|135->105|137->107|137->107|137->107|137->107|137->107|139->109|139->109|139->109|141->111|141->111|141->111|143->113|146->116|146->116|146->116|149->119|149->119|149->119|151->121|151->121|151->121|151->121|151->121|153->123|153->123|153->123|155->125|155->125|155->125|157->127|160->130|164->134|164->134|164->134|181->151|181->151|182->152|182->152|184->154|184->154|184->154|184->154|186->156|186->156|187->157|187->157|189->159|189->159|191->161|191->161|192->162|192->162|195->165|195->165|196->166|196->166|197->167|197->167|200->170|200->170|200->170|200->170|202->172|202->172|203->173|203->173|205->175|205->175|207->177|207->177|208->178|208->178|211->181|211->181|212->182|212->182|213->183|213->183|216->186|216->186|216->186|216->186|219->189|219->189|221->191|221->191|221->191|223->193|223->193|225->195|225->195|226->196|226->196|228->198|228->198|229->199|229->199|231->201|231->201|233->203|233->203|235->205|235->205|236->206|236->206|237->207|237->207|238->208|238->208|241->211|241->211|242->212|242->212|246->216|246->216|246->216|247->217
                    -- GENERATED --
                */
            