
package views.html.suspicious

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
object admin_list extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Form[models.Transaction],com.avaje.ebean.Page[models.Transaction],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[models.Transaction], currentPage: com.avaje.ebean.Page[models.Transaction], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*129.2*/header/*129.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*129.38*/("""
<th class=""""),_display_(Seq[Any](/*130.13*/key)),format.raw/*130.16*/("""">
	"""),_display_(Seq[Any](/*131.3*/if(key && key.length > 0)/*131.28*/ {_display_(Seq[Any](format.raw/*131.30*/("""
	<a href=""""),_display_(Seq[Any](/*132.12*/link(currentPage.getPageIndex, key))),format.raw/*132.47*/("""">"""),_display_(Seq[Any](/*132.50*/title)),format.raw/*132.55*/("""</a>
	""")))}/*133.4*/else/*133.9*/{_display_(Seq[Any](format.raw/*133.10*/("""
	"""),_display_(Seq[Any](/*134.3*/title)),format.raw/*134.8*/("""
	""")))})),format.raw/*135.3*/("""
</th>
""")))};def /*139.2*/link/*139.6*/(newPage:Int, newSortBy:String) = {{

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
    routes.Suspicious.admin_index(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.163*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Suspicious transaction list")/*5.39*/ {_display_(Seq[Any](format.raw/*5.41*/("""
<div class="page-header">
  <h1>"""),_display_(Seq[Any](/*7.8*/doku/*7.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG485","Suspicious transaction list"))),format.raw/*7.94*/("""</h1>
</div>

<div class="common-container">
	<div class="well common-container-filter">
		"""),_display_(Seq[Any](/*12.4*/form(action = routes.Suspicious.admin_index(), 'id -> "customer-ban-filter-form", 'class -> "form form-horizontal row")/*12.123*/ {_display_(Seq[Any](format.raw/*12.125*/("""
			<div class="control-group span8">
				"""),_display_(Seq[Any](/*14.6*/input(filterForm("f"), '_showConstraints -> false, '_class -> "nolabel", '_label -> "", 'class -> "span4", 
				'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG493","Search by ID, name, or email address")
				)/*16.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*16.35*/("""
				<div class="input-append">
					<input type="search" name=""""),_display_(Seq[Any](/*18.34*/name)),format.raw/*18.38*/("""" value=""""),_display_(Seq[Any](/*18.48*/value)),format.raw/*18.53*/("""" """),_display_(Seq[Any](/*18.56*/toHtmlArgs(args))),format.raw/*18.72*/(""">
					<button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*19.69*/doku/*19.73*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*19.134*/("""</button>
				</div>
				""")))})),format.raw/*21.6*/("""
			</div>
			<div class="control-group span3 right">
				<a class="btn" href=""""),_display_(Seq[Any](/*24.27*/routes/*24.33*/.Suspicious.csv().toString())),format.raw/*24.61*/(""""><i class="icon-download-alt"></i> """),_display_(Seq[Any](/*24.98*/doku/*24.102*/.kirimdoku.util.MultiLanguage.getLanguage("LANG128","Export as CSV"))),format.raw/*24.170*/("""</a>
			</div>
		""")))})),format.raw/*26.4*/("""
	</div>
	
	<div class="common-container-inner">
		"""),_display_(Seq[Any](/*30.4*/if(currentPage.getTotalRowCount == 0)/*30.41*/{_display_(Seq[Any](format.raw/*30.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*32.9*/doku/*32.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG459","There are no news data to be displayed"))),format.raw/*32.106*/("""</em>
		</div>
		""")))}/*34.5*/else/*34.10*/{_display_(Seq[Any](format.raw/*34.11*/("""
            <table class="table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*38.26*/header("transaction.id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG138","Transaction ID")))),format.raw/*38.125*/("""
                        """),_display_(Seq[Any](/*39.26*/header("sender.id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG140","Sender ID")))),format.raw/*39.115*/("""
                        """),_display_(Seq[Any](/*40.26*/header("sender.firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name")))),format.raw/*40.124*/("""
                        """),_display_(Seq[Any](/*41.26*/header("beneficiary.id", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG142","Receiver ID")))),format.raw/*41.122*/("""
                        """),_display_(Seq[Any](/*42.26*/header("beneficiary.firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name")))),format.raw/*42.131*/("""
                        """),_display_(Seq[Any](/*43.26*/header("sendAmount", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG028","Send amount")))),format.raw/*43.118*/("""
                        """),_display_(Seq[Any](/*44.26*/header("status", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status")))),format.raw/*44.109*/("""
                        """),_display_(Seq[Any](/*45.26*/header(null, doku.kirimdoku.util.MultiLanguage.getLanguage("LANG486","Suspicious reason")))),format.raw/*45.116*/("""
                    </tr>
                </thead>
                <tbody>
					"""),_display_(Seq[Any](/*49.7*/for(transaction <- currentPage.getList) yield /*49.46*/ {_display_(Seq[Any](format.raw/*49.48*/("""
                        <tr>
                            <td>"""),_display_(Seq[Any](/*51.34*/transaction/*51.45*/.getIdToken)),format.raw/*51.56*/("""</td>
                            <td>"""),_display_(Seq[Any](/*52.34*/transaction/*52.45*/.sender.getIdToken)),format.raw/*52.63*/("""</td>
                            <td>"""),_display_(Seq[Any](/*53.34*/transaction/*53.45*/.sender.fullName)),format.raw/*53.61*/("""</td>
                            <td>"""),_display_(Seq[Any](/*54.34*/transaction/*54.45*/.beneficiary.getIdToken)),format.raw/*54.68*/("""</td>
                            <td>"""),_display_(Seq[Any](/*55.34*/transaction/*55.45*/.beneficiary.fullName)),format.raw/*55.66*/("""</td>
                            <td class="right">"""),_display_(Seq[Any](/*56.48*/transaction/*56.59*/.senderAmountFormat)),format.raw/*56.78*/("""</td>
                            <td>"""),_display_(Seq[Any](/*57.34*/transaction/*57.45*/.statusText)),format.raw/*57.56*/("""</td>
							<td>
								<ul>
								"""),_display_(Seq[Any](/*60.10*/for(label <- transaction.labels) yield /*60.42*/ {_display_(Seq[Any](format.raw/*60.44*/("""
								<li>"""),_display_(Seq[Any](/*61.14*/label/*61.19*/.description)),format.raw/*61.31*/("""</li>
								""")))})),format.raw/*62.10*/("""
								</ul>
							</td>
                        </tr>
                    """)))})),format.raw/*66.22*/("""
                </tbody>
            </table>
		
            <div id="pagination" class="pagination pagination-right">
                <ul>
                    """),_display_(Seq[Any](/*72.22*/if(currentPage.hasPrev)/*72.45*/ {_display_(Seq[Any](format.raw/*72.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*74.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*74.75*/("""">&larr; """),_display_(Seq[Any](/*74.85*/doku/*74.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*74.152*/("""</a>
                    </li>
                    """)))}/*76.23*/else/*76.28*/{_display_(Seq[Any](format.raw/*76.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*78.36*/doku/*78.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*78.103*/("""</a>
                    </li>
                    """)))})),format.raw/*80.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*83.39*/currentPage/*83.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*83.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*86.22*/if(currentPage.hasNext)/*86.45*/ {_display_(Seq[Any](format.raw/*86.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*88.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*88.75*/("""">"""),_display_(Seq[Any](/*88.78*/doku/*88.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*88.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*90.23*/else/*90.28*/{_display_(Seq[Any](format.raw/*90.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*92.29*/doku/*92.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*92.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*94.22*/("""
                </ul>
            </div>
		""")))})),format.raw/*97.4*/("""
	</div>

	<!-- Modal -->
	<div id="ban-add" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		"""),_display_(Seq[Any](/*102.4*/form(action = routes.CustomerBan.admin_add(), 'class -> "form")/*102.67*/ {_display_(Seq[Any](format.raw/*102.69*/("""
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">"""),_display_(Seq[Any](/*105.28*/doku/*105.32*/.kirimdoku.util.MultiLanguage.getLanguage("LANG477","Add banned list"))),format.raw/*105.102*/("""</h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
					"""),_display_(Seq[Any](/*109.7*/inputText(filterForm("customer.idToken"), '_showConstraints -> false, '_class -> "", 
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"), 
					'class -> "required", 'placeholder -> "Enter customer ID"))),format.raw/*111.64*/("""
				</div>
				<div class="control-group">
					"""),_display_(Seq[Any](/*114.7*/textarea(filterForm("reason"), '_showConstraints -> false, '_class -> "", 
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG478","Reason for banning"), 
					'class -> "required", 'placeholder -> "Enter reason"))),format.raw/*116.59*/("""
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">"""),_display_(Seq[Any](/*120.66*/doku/*120.70*/.kirimdoku.util.MultiLanguage.getLanguage("LANG479","Close"))),format.raw/*120.130*/("""</button>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*121.52*/doku/*121.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG308","Submit"))),format.raw/*121.117*/("""</button>
			</div>
		""")))})),format.raw/*123.4*/("""
	</div>
</div>
""")))})),format.raw/*126.2*/("""


"""),format.raw/*137.2*/("""

"""),format.raw/*157.2*/("""
"""))}
    }
    
    def render(filterForm:Form[models.Transaction],currentPage:com.avaje.ebean.Page[models.Transaction],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(filterForm,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((Form[models.Transaction],com.avaje.ebean.Page[models.Transaction],String,String,String) => play.api.templates.Html) = (filterForm,currentPage,currentSortBy,currentOrder,currentFilter) => apply(filterForm,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/suspicious/admin_list.scala.html
                    HASH: aa86cdee8aa8d42360d3fab54f576a6aa43ec499
                    MATRIX: 819->1|1092->6252|1107->6258|1202->6288|1252->6301|1278->6304|1319->6309|1354->6334|1395->6336|1444->6348|1502->6383|1542->6386|1570->6391|1596->6399|1609->6404|1649->6405|1688->6408|1715->6413|1750->6416|1782->6427|1795->6431|2286->162|2313->214|2349->216|2394->253|2433->255|2501->289|2513->293|2616->375|2743->467|2872->586|2913->588|2991->631|3228->860|3295->889|3396->954|3422->958|3468->968|3495->973|3534->976|3572->992|3678->1062|3691->1066|3775->1127|3832->1153|3948->1233|3963->1239|4013->1267|4086->1304|4100->1308|4191->1376|4240->1394|4327->1446|4373->1483|4412->1484|4477->1514|4490->1518|4606->1611|4642->1630|4655->1635|4694->1636|4884->1790|5006->1889|5068->1915|5180->2004|5242->2030|5363->2128|5425->2154|5544->2250|5606->2276|5734->2381|5796->2407|5911->2499|5973->2525|6079->2608|6141->2634|6254->2724|6371->2806|6426->2845|6466->2847|6565->2910|6585->2921|6618->2932|6693->2971|6713->2982|6753->3000|6828->3039|6848->3050|6886->3066|6961->3105|6981->3116|7026->3139|7101->3178|7121->3189|7164->3210|7253->3263|7273->3274|7314->3293|7389->3332|7409->3343|7442->3354|7518->3394|7566->3426|7606->3428|7656->3442|7670->3447|7704->3459|7751->3474|7862->3553|8060->3715|8092->3738|8132->3740|8241->3813|8303->3853|8349->3863|8362->3867|8448->3930|8519->3983|8532->3988|8571->3989|8681->4063|8694->4067|8780->4130|8864->4182|8980->4262|9000->4273|9056->4307|9144->4359|9176->4382|9216->4384|9325->4457|9387->4497|9426->4500|9439->4504|9521->4563|9599->4623|9612->4628|9651->4629|9754->4696|9767->4700|9848->4759|9939->4818|10015->4863|10202->5014|10275->5077|10316->5079|10502->5228|10516->5232|10610->5302|10728->5384|10989->5622|11075->5672|11327->5901|11481->6018|11495->6022|11579->6082|11677->6143|11691->6147|11776->6208|11831->6231|11880->6248|11911->6424|11941->6891
                    LINES: 26->1|31->129|31->129|33->129|34->130|34->130|35->131|35->131|35->131|36->132|36->132|36->132|36->132|37->133|37->133|37->133|38->134|38->134|39->135|41->139|41->139|60->1|61->4|62->5|62->5|62->5|64->7|64->7|64->7|69->12|69->12|69->12|71->14|73->16|73->16|75->18|75->18|75->18|75->18|75->18|75->18|76->19|76->19|76->19|78->21|81->24|81->24|81->24|81->24|81->24|81->24|83->26|87->30|87->30|87->30|89->32|89->32|89->32|91->34|91->34|91->34|95->38|95->38|96->39|96->39|97->40|97->40|98->41|98->41|99->42|99->42|100->43|100->43|101->44|101->44|102->45|102->45|106->49|106->49|106->49|108->51|108->51|108->51|109->52|109->52|109->52|110->53|110->53|110->53|111->54|111->54|111->54|112->55|112->55|112->55|113->56|113->56|113->56|114->57|114->57|114->57|117->60|117->60|117->60|118->61|118->61|118->61|119->62|123->66|129->72|129->72|129->72|131->74|131->74|131->74|131->74|131->74|133->76|133->76|133->76|135->78|135->78|135->78|137->80|140->83|140->83|140->83|143->86|143->86|143->86|145->88|145->88|145->88|145->88|145->88|147->90|147->90|147->90|149->92|149->92|149->92|151->94|154->97|159->102|159->102|159->102|162->105|162->105|162->105|166->109|168->111|171->114|173->116|177->120|177->120|177->120|178->121|178->121|178->121|180->123|183->126|186->137|188->157
                    -- GENERATED --
                */
            