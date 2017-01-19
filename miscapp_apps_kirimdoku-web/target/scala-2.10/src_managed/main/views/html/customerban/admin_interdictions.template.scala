
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
object admin_interdictions extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Form[models.Customer],com.avaje.ebean.Page[models.CustomerBan],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[models.Customer], currentPage: com.avaje.ebean.Page[models.CustomerBan], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*124.2*/header/*124.8*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*124.38*/("""
<th class=""""),_display_(Seq[Any](/*125.13*/key)),format.raw/*125.16*/("""">
	"""),_display_(Seq[Any](/*126.3*/if(key && key.length > 0)/*126.28*/ {_display_(Seq[Any](format.raw/*126.30*/("""
	<a href=""""),_display_(Seq[Any](/*127.12*/link(currentPage.getPageIndex, key))),format.raw/*127.47*/("""">"""),_display_(Seq[Any](/*127.50*/title)),format.raw/*127.55*/("""</a>
	""")))}/*128.4*/else/*128.9*/{_display_(Seq[Any](format.raw/*128.10*/("""
	"""),_display_(Seq[Any](/*129.3*/title)),format.raw/*129.8*/("""
	""")))})),format.raw/*130.3*/("""
</th>
""")))};def /*134.2*/link/*134.6*/(newPage:Int, newSortBy:String) = {{

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
    routes.CustomerBan.admin_interdictions(newPage, sortBy, order, currentFilter)
}};
Seq[Any](format.raw/*1.160*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Interdiction list")/*5.29*/ {_display_(Seq[Any](format.raw/*5.31*/("""
<div class="page-header">
  <h1>"""),_display_(Seq[Any](/*7.8*/doku/*7.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG481","Interdiction list"))),format.raw/*7.84*/("""</h1>
</div>

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""

<div class="common-container">
	<div class="well common-container-filter">
		"""),_display_(Seq[Any](/*14.4*/form(action = routes.CustomerBan.admin_interdictions(), 'id -> "customer-ban-filter-form", 'class -> "form form-horizontal row")/*14.132*/ {_display_(Seq[Any](format.raw/*14.134*/("""
			<div class="control-group span12">
				"""),_display_(Seq[Any](/*16.6*/input(filterForm("f"), '_showConstraints -> false, '_class -> "span4 nolabel", '_label -> "", 'class -> "input-xlarge", 'placeholder -> "Filter by First & Last name")/*16.172*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*16.201*/("""
				<div class="input-append">
					<input type="search" name=""""),_display_(Seq[Any](/*18.34*/name)),format.raw/*18.38*/("""" value=""""),_display_(Seq[Any](/*18.48*/value)),format.raw/*18.53*/("""" """),_display_(Seq[Any](/*18.56*/toHtmlArgs(args))),format.raw/*18.72*/(""">
				</div>
				""")))})),format.raw/*20.6*/("""
				"""),_display_(Seq[Any](/*21.6*/input(filterForm("customer.birthday"), '_showConstraints -> false, '_class -> "span4 nolabel", '_label -> "", 'class -> "datepicker", 'placeholder -> "")/*21.159*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*21.188*/("""
					<div class="input-append">
						<input type="date" name=""""),_display_(Seq[Any](/*23.33*/name)),format.raw/*23.37*/("""" value=""""),_display_(Seq[Any](/*23.47*/value)),format.raw/*23.52*/("""" """),_display_(Seq[Any](/*23.55*/toHtmlArgs(args))),format.raw/*23.71*/(""">
						<button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*24.70*/doku/*24.74*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*24.135*/("""</button>
					</div>
				""")))})),format.raw/*26.6*/("""
			</div>
		""")))})),format.raw/*28.4*/("""

		<div>
			<a href="#interdiction-upload-dialog" class="btn btn-upload" role="button" data-toggle="modal"><i class="icon-plus"></i>"""),_display_(Seq[Any](/*31.125*/doku/*31.129*/.kirimdoku.util.MultiLanguage.getLanguage("LANG482","Upload new interdiction data"))),format.raw/*31.212*/("""</a>
		</div>
	</div>

	<div class="common-container-inner">
		<div class="clearfix"></div>
		"""),_display_(Seq[Any](/*37.4*/if(currentPage.getTotalRowCount == 0)/*37.41*/{_display_(Seq[Any](format.raw/*37.42*/("""
		<div class="well">
			<em>"""),_display_(Seq[Any](/*39.9*/doku/*39.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG459","There are no news data to be displayed"))),format.raw/*39.106*/("""</em>
		</div>
		""")))}/*41.5*/else/*41.10*/{_display_(Seq[Any](format.raw/*41.11*/("""
            <table class="customer table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*45.26*/header("customer.country", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")))),format.raw/*45.124*/("""
                        """),_display_(Seq[Any](/*46.26*/header("customer.firstName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name")))),format.raw/*46.125*/("""
                        """),_display_(Seq[Any](/*47.26*/header("customer.lastName", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name")))),format.raw/*47.123*/("""
                        """),_display_(Seq[Any](/*48.26*/header("customer.birthDate", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth")))),format.raw/*48.128*/("""
                        """),format.raw/*49.55*/("""
                    </tr>
                </thead>
                <tbody>
					"""),_display_(Seq[Any](/*53.7*/for(ban <- currentPage.getList) yield /*53.38*/ {_display_(Seq[Any](format.raw/*53.40*/("""
                        <tr>
							<td>"""),_display_(Seq[Any](/*55.13*/if(ban.country != null)/*55.36*/ {_display_(Seq[Any](format.raw/*55.38*/("""
							"""),_display_(Seq[Any](/*56.9*/ban/*56.12*/.country.code)),format.raw/*56.25*/("""
							""")))})),format.raw/*57.9*/("""</td>
                            <td>"""),_display_(Seq[Any](/*58.34*/ban/*58.37*/.firstName)),format.raw/*58.47*/("""</td>
                            <td>"""),_display_(Seq[Any](/*59.34*/ban/*59.37*/.lastName)),format.raw/*59.46*/("""</td>
                            <td>"""),_display_(Seq[Any](/*60.34*/ban/*60.37*/.birthDateFormat)),format.raw/*60.53*/("""</td>
							<!--td class="actions">
								<a href=""""),_display_(Seq[Any](/*62.19*/routes/*62.25*/.CustomerBan.admin_interdictionsDelete(ban.id))),format.raw/*62.71*/("""" class="btn-delete" data-remote="true" data-method="DELETE" data-confirm="Are you sure you want to remove this interdiction?"><i class="icon-remove"></i>Delete</a>
							</td-->
                        </tr>
                    """)))})),format.raw/*65.22*/("""
                </tbody>
            </table>
		
            <div id="pagination" class="pagination pagination-right">
                <ul>
                    """),_display_(Seq[Any](/*71.22*/if(currentPage.hasPrev)/*71.45*/ {_display_(Seq[Any](format.raw/*71.47*/("""
                    <li class="prev">
                        <a href=""""),_display_(Seq[Any](/*73.35*/link(currentPage.getPageIndex - 1, null))),format.raw/*73.75*/("""">&larr; """),_display_(Seq[Any](/*73.85*/doku/*73.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*73.152*/("""</a>
                    </li>
                    """)))}/*75.23*/else/*75.28*/{_display_(Seq[Any](format.raw/*75.29*/("""
                    <li class="prev">
                        <a>&larr; """),_display_(Seq[Any](/*77.36*/doku/*77.40*/.kirimdoku.util.MultiLanguage.getLanguage("LANG272","Previous"))),format.raw/*77.103*/("""</a>
                    </li>
                    """)))})),format.raw/*79.22*/("""

                   <li class="current">
                       <a>Displaying """),_display_(Seq[Any](/*82.39*/currentPage/*82.50*/.getDisplayXtoYofZ(" to ", " of "))),format.raw/*82.84*/("""</a>
                   </li>

                    """),_display_(Seq[Any](/*85.22*/if(currentPage.hasNext)/*85.45*/ {_display_(Seq[Any](format.raw/*85.47*/("""
                    <li class="next">
                        <a href=""""),_display_(Seq[Any](/*87.35*/link(currentPage.getPageIndex + 1, null))),format.raw/*87.75*/("""">"""),_display_(Seq[Any](/*87.78*/doku/*87.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*87.141*/(""" &rarr;</a>
                    </li>
                    """)))}/*89.23*/else/*89.28*/{_display_(Seq[Any](format.raw/*89.29*/("""
                    <li class="next">
                        <a>"""),_display_(Seq[Any](/*91.29*/doku/*91.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Next"))),format.raw/*91.92*/(""" &rarr;</a>
                    </li>
                    """)))})),format.raw/*93.22*/("""
                </ul>
            </div>
		""")))})),format.raw/*96.4*/("""
	</div>
</div>

<div class="modal fade hide" id="interdiction-upload-dialog">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>"""),_display_(Seq[Any](/*103.14*/doku/*103.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG483","Interdiction upload"))),format.raw/*103.92*/("""</h3>
    </div>
	"""),_display_(Seq[Any](/*105.3*/form(action=routes.CustomerBan.admin_interdictionsUpload, 'class -> "form-horizontal", 'enctype -> "multipart/form-data")/*105.124*/ {_display_(Seq[Any](format.raw/*105.126*/("""
	<div class="modal-body">
		<div class="control-groups">
			<p>"""),_display_(Seq[Any](/*108.8*/doku/*108.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG484","Upload your interdiction file CSV file here, please ensure you have the correct format and template. Only registered country code are allowed in our system"))),format.raw/*108.222*/("""</p>
		</div>
		<hr/>
		<input type="file" name="uploadFile"/>
		<p class="container-info"></p>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*115.49*/doku/*115.53*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*115.114*/("""</a>
		<button type="submit" class="btn btn-primary" data-disable-with="Processing..." data-enable-with="Proceed">"""),_display_(Seq[Any](/*116.111*/doku/*116.115*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*116.177*/("""</button>
	</div>
	""")))})),format.raw/*118.3*/("""
</div>
<script type="text/javascript" src='"""),_display_(Seq[Any](/*120.38*/routes/*120.44*/.Assets.at("javascripts/customer.js"))),format.raw/*120.81*/("""'></script>
""")))})),format.raw/*121.2*/("""


"""),format.raw/*132.2*/("""

"""),format.raw/*152.2*/("""
"""))}
    }
    
    def render(filterForm:Form[models.Customer],currentPage:com.avaje.ebean.Page[models.CustomerBan],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(filterForm,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((Form[models.Customer],com.avaje.ebean.Page[models.CustomerBan],String,String,String) => play.api.templates.Html) = (filterForm,currentPage,currentSortBy,currentOrder,currentFilter) => apply(filterForm,currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customerban/admin_interdictions.scala.html
                    HASH: e16c55331c560a7cfdf6e0f79e605bc835971bf9
                    MATRIX: 826->1|1096->5898|1111->5904|1206->5934|1256->5947|1282->5950|1323->5955|1358->5980|1399->5982|1448->5994|1506->6029|1546->6032|1574->6037|1600->6045|1613->6050|1653->6051|1692->6054|1719->6059|1754->6062|1786->6073|1799->6077|2299->159|2326->211|2362->213|2397->240|2436->242|2504->276|2516->280|2609->352|2659->367|2674->374|2710->388|2825->468|2963->596|3004->598|3083->642|3259->808|3327->837|3428->902|3454->906|3500->916|3527->921|3566->924|3604->940|3653->958|3694->964|3857->1117|3925->1146|4026->1211|4052->1215|4098->1225|4125->1230|4164->1233|4202->1249|4309->1320|4322->1324|4406->1385|4464->1412|4509->1426|4680->1560|4694->1564|4800->1647|4930->1742|4976->1779|5015->1780|5080->1810|5093->1814|5209->1907|5245->1926|5258->1931|5297->1932|5496->2095|5617->2193|5679->2219|5801->2318|5863->2344|5983->2441|6045->2467|6170->2569|6223->2624|6340->2706|6387->2737|6427->2739|6505->2781|6537->2804|6577->2806|6621->2815|6633->2818|6668->2831|6708->2840|6783->2879|6795->2882|6827->2892|6902->2931|6914->2934|6945->2943|7020->2982|7032->2985|7070->3001|7161->3056|7176->3062|7244->3108|7507->3339|7705->3501|7737->3524|7777->3526|7886->3599|7948->3639|7994->3649|8007->3653|8093->3716|8164->3769|8177->3774|8216->3775|8326->3849|8339->3853|8425->3916|8509->3968|8625->4048|8645->4059|8701->4093|8789->4145|8821->4168|8861->4170|8970->4243|9032->4283|9071->4286|9084->4290|9166->4349|9244->4409|9257->4414|9296->4415|9399->4482|9412->4486|9493->4545|9584->4604|9660->4649|9896->4848|9910->4852|10007->4926|10062->4945|10194->5066|10236->5068|10337->5133|10351->5137|10585->5347|10802->5527|10816->5531|10901->5592|11054->5707|11069->5711|11155->5773|11207->5793|11289->5838|11305->5844|11365->5881|11410->5894|11441->6070|11471->6546
                    LINES: 26->1|31->124|31->124|33->124|34->125|34->125|35->126|35->126|35->126|36->127|36->127|36->127|36->127|37->128|37->128|37->128|38->129|38->129|39->130|41->134|41->134|60->1|61->4|62->5|62->5|62->5|64->7|64->7|64->7|67->10|67->10|67->10|71->14|71->14|71->14|73->16|73->16|73->16|75->18|75->18|75->18|75->18|75->18|75->18|77->20|78->21|78->21|78->21|80->23|80->23|80->23|80->23|80->23|80->23|81->24|81->24|81->24|83->26|85->28|88->31|88->31|88->31|94->37|94->37|94->37|96->39|96->39|96->39|98->41|98->41|98->41|102->45|102->45|103->46|103->46|104->47|104->47|105->48|105->48|106->49|110->53|110->53|110->53|112->55|112->55|112->55|113->56|113->56|113->56|114->57|115->58|115->58|115->58|116->59|116->59|116->59|117->60|117->60|117->60|119->62|119->62|119->62|122->65|128->71|128->71|128->71|130->73|130->73|130->73|130->73|130->73|132->75|132->75|132->75|134->77|134->77|134->77|136->79|139->82|139->82|139->82|142->85|142->85|142->85|144->87|144->87|144->87|144->87|144->87|146->89|146->89|146->89|148->91|148->91|148->91|150->93|153->96|160->103|160->103|160->103|162->105|162->105|162->105|165->108|165->108|165->108|172->115|172->115|172->115|173->116|173->116|173->116|175->118|177->120|177->120|177->120|178->121|181->132|183->152
                    -- GENERATED --
                */
            