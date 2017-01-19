
package views.html.fee

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
object admin_index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[models.Fee],models.Corporate,List[models.Fee],models.Fee,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[models.Fee], corporate: models.Corporate, fees: List[models.Fee], newFee: models.Fee):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*5.2*/formFactory/*5.13*/(fee:models.Fee):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*5.33*/("""
	"""),_display_(Seq[Any](/*6.3*/helper/*6.9*/.form(routes.Fee.create(corporate.code),
	'method -> "POST",
	'class -> "form-inline form-fee",
	'id -> "sendForm",
	Symbol("data-remote") -> "true",
	Symbol("data-type") -> "json",
	'autocomplete -> "off"
	)/*13.3*/ {_display_(Seq[Any](format.raw/*13.5*/("""
	<input type="hidden" name="id" value=""""),_display_(Seq[Any](/*14.41*/(if(fee.id>0) fee.id else "0"))),format.raw/*14.71*/(""""/>
	<input type="hidden" name="corporate.code" value=""""),_display_(Seq[Any](/*15.53*/(if(fee.corporate != null) fee.corporate.code else corporate.code))),format.raw/*15.119*/(""""/>
	<input type="hidden" name="currency.code" value=""""),_display_(Seq[Any](/*16.52*/fee/*16.55*/.currency.code)),format.raw/*16.69*/(""""/>
	<input type="hidden" name="senderCountry.code" value=""""),_display_(Seq[Any](/*17.57*/fee/*17.60*/.senderCountry.code)),format.raw/*17.79*/(""""/>
	<input type="hidden" name="beneficiaryCountry.code" value=""""),_display_(Seq[Any](/*18.62*/fee/*18.65*/.beneficiaryCountry.code)),format.raw/*18.89*/(""""/>
	<input type="hidden" name="beneficiaryCurrency.code" value=""""),_display_(Seq[Any](/*19.63*/fee/*19.66*/.beneficiaryCurrency.code)),format.raw/*19.91*/(""""/>
	<input type="hidden" name="channel.code" value=""""),_display_(Seq[Any](/*20.51*/fee/*20.54*/.channel.code)),format.raw/*20.67*/(""""/>
	
	<div class="row">
		<div class="span3" style="width:220px;">
			<div class="control-group">
				<div class="input-append">
					<input type="number" name="fromAmount" class="input-medium required number amount" value=""""),_display_(Seq[Any](/*26.97*/{fee.fromAmount})),format.raw/*26.113*/("""" placeholder="From amount"/>
					<span class="add-on">"""),_display_(Seq[Any](/*27.28*/fee/*27.31*/.currency.code)),format.raw/*27.45*/("""</span>
				</div>
			</div>
		</div>
		<div class="span3" style="width:220px;">
			<div class="control-group">
				<div class="input-append">
					<input type="number" name="toAmount" class="input-medium required number amount" value=""""),_display_(Seq[Any](/*34.95*/{fee.toAmount})),format.raw/*34.109*/("""" placeholder="To amount"/>
					<span class="add-on">"""),_display_(Seq[Any](/*35.28*/fee/*35.31*/.currency.code)),format.raw/*35.45*/("""</span>
				</div>
			</div>
		</div>
		<div class="span3" style="width:160px;">
			<div class="control-group">
				<div class="input-append">
					<input type="number" name="amount" class="input-small required number amount" value=""""),_display_(Seq[Any](/*42.92*/{fee.amount})),format.raw/*42.104*/("""" placeholder="Fee amount"/>
					<span class="add-on">"""),_display_(Seq[Any](/*43.28*/fee/*43.31*/.currency.code)),format.raw/*43.45*/("""</span>
				</div>
			</div>
		</div>
		<div class="span3" style="width:180px;">
			<div class="control-group">
				<div class="input-append">
					<input type="number" name="additionalFee" class="input-small required number amount" value=""""),_display_(Seq[Any](/*50.99*/{fee.additionalFee})),format.raw/*50.118*/("""" placeholder="Additional Fee amount"/>
					<span class="add-on">"""),_display_(Seq[Any](/*51.28*/fee/*51.31*/.beneficiaryCurrency.code)),format.raw/*51.56*/("""</span>
				</div>
			</div>
		</div>
		<div class="span3">
			<button type="submit" class="btn btn-small"><i class="icon-ok"></i> """),_display_(Seq[Any](/*56.73*/doku/*56.77*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*56.136*/("""</button>
			<a class="btn btn-small" href=""""),_display_(Seq[Any](/*57.36*/routes/*57.42*/.Fee.destroy(corporate.code, fee.id))),format.raw/*57.78*/("""" data-remote="true" data-method="DELETE" data-confirm='"""),_display_(Seq[Any](/*57.135*/doku/*57.139*/.kirimdoku.util.MultiLanguage.getLanguage("LANG411","Are you sure you want to delete this fee record?"))),format.raw/*57.242*/("""'><i class="icon-remove"></i> """),_display_(Seq[Any](/*57.273*/doku/*57.277*/.kirimdoku.util.MultiLanguage.getLanguage("LANG412","Delete"))),format.raw/*57.338*/("""</a>

			<span class="pull-right alert-container"></span>
		</div>
	</div>
	""")))})),format.raw/*62.3*/("""
""")))};
Seq[Any](format.raw/*1.105*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*63.2*/("""



"""),_display_(Seq[Any](/*67.2*/layout("Fee settings", Seq(
routes.Corporate.admin_index().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management"),
"" -> corporate.name,
"" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG424","Fees management")
))/*71.3*/ {_display_(Seq[Any](format.raw/*71.5*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*73.7*/doku/*73.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG415","Fee setting for"))),format.raw/*73.81*/(""" """),_display_(Seq[Any](/*73.83*/corporate/*73.92*/.name)),format.raw/*73.97*/("""</h1>
</div>


<div class="fee-container">

    <div class="well">
        """),_display_(Seq[Any](/*80.10*/helper/*80.16*/.form(routes.Fee.admin_index(corporate.code))/*80.61*/ {_display_(Seq[Any](format.raw/*80.63*/("""
			<div class="hidden">
				"""),_display_(Seq[Any](/*82.6*/input(filterForm("senderCountry.code"), '_label -> "", '_showConstraints -> false)/*82.88*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*82.117*/("""
				<input type="hidden" name=""""),_display_(Seq[Any](/*83.33*/name)),format.raw/*83.37*/("""" value=""""),_display_(Seq[Any](/*83.47*/value)),format.raw/*83.52*/(""""/>
				""")))})),format.raw/*84.6*/("""
			</div>
            <div class="control-group">
                <div class="row-fluid">
                    <div class="span3" style="width:230px;">
						"""),_display_(Seq[Any](/*89.8*/select(filterForm("senderCountry.code"), options(models.Country.options), 
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending Country"), 
						'disabled -> true))),format.raw/*91.25*/("""
                    </div>
                    <div class="span3" style="width:120px;">
						"""),_display_(Seq[Any](/*94.8*/select(filterForm("currency.code"), options(models.Currency.options), 
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG413","Sending Currency"), 
						'style -> "width:80px;"))),format.raw/*96.31*/("""
                    </div>
                    <div class="span3" style="width:230px;">
                        """),_display_(Seq[Any](/*99.26*/select(filterForm("beneficiaryCountry.code"), options(models.Country.options), 
                        '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG134","Receiving Country")
                        ))),format.raw/*101.26*/("""
                    </div>
                    <div class="span3" style="width:150px;">
						"""),_display_(Seq[Any](/*104.8*/select(filterForm("beneficiaryCurrency.code"), options(models.Currency.options), 
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG416","Receiving Currency"), 
						'style -> "width:80px;"))),format.raw/*106.31*/("""
                    </div>
                    <div class="span3">
                        """),_display_(Seq[Any](/*109.26*/select(filterForm("channel.code"), options(models.Channel.options), 
                        '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG139","Channels")
                        ))),format.raw/*111.26*/("""
                    </div>
                    <div class="span3">
                        <label>&nbsp;</label>
                        <button class="btn"><i class="icon-filter"></i> """),_display_(Seq[Any](/*115.74*/doku/*115.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG130","Filter"))),format.raw/*115.139*/("""</button>
                    </div>
                </div>
            </div>
        """)))})),format.raw/*119.10*/("""
    </div>

	"""),_display_(Seq[Any](/*122.3*/if(newFee != null)/*122.21*/ {_display_(Seq[Any](format.raw/*122.23*/("""
	<div class="fee-container-inner">
		<div>
			<p class="alert alert-warn">"""),_display_(Seq[Any](/*125.33*/doku/*125.37*/.kirimdoku.util.MultiLanguage.getLanguage("LANG417","This is advanced features! Please consult administrator for explanation for changing this settings"))),format.raw/*125.190*/("""</p>
		</div>
        """),_display_(Seq[Any](/*127.10*/partial/*127.17*/.flash_alert())),format.raw/*127.31*/("""

        <div class="page-subheader">
			<h3>"""),_display_(Seq[Any](/*130.9*/doku/*130.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG418","Fees tiering settings"))),format.raw/*130.89*/("""</h3>
		</div>

		<p>
			"""),_display_(Seq[Any](/*134.5*/doku/*134.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG419","Please fill 'Higher amount' with value like"))),format.raw/*134.107*/(""" 999999.999999
		</p>
		<div class="row">
			<div class="span3" style="width:220px;">"""),_display_(Seq[Any](/*137.45*/doku/*137.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG420","Lower amount"))),format.raw/*137.116*/("""</div>
			<div class="span3" style="width:220px;">"""),_display_(Seq[Any](/*138.45*/doku/*138.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG421","Higher amount"))),format.raw/*138.117*/("""</div>
			<div class="span3" style="width:160px;">"""),_display_(Seq[Any](/*139.45*/doku/*139.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG254","Fee amount"))),format.raw/*139.114*/("""</div>
			<div class="span3" style="width:180px;">"""),_display_(Seq[Any](/*140.45*/doku/*140.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG422","Additional Fee amount"))),format.raw/*140.125*/("""</div>
		</div>
		<div class="spacer" style="height:10px"></div>

		"""),_display_(Seq[Any](/*144.4*/for(fee <- fees) yield /*144.20*/ {_display_(Seq[Any](format.raw/*144.22*/("""
			"""),_display_(Seq[Any](/*145.5*/formFactory(fee))),format.raw/*145.21*/("""
		""")))})),format.raw/*146.4*/("""
	</div>
	<div>
		<div class="pull-left">
			<a href="#" class="btn-add-fee"><i class="icon-plus"></i> """),_display_(Seq[Any](/*150.63*/doku/*150.67*/.kirimdoku.util.MultiLanguage.getLanguage("LANG423","Add New Fee"))),format.raw/*150.133*/("""</a>
		</div>
	</div>
	""")))})),format.raw/*153.3*/("""
</div>

"""),_display_(Seq[Any](/*156.2*/if(newFee != null)/*156.20*/ {_display_(Seq[Any](format.raw/*156.22*/("""
<div id="fee-form-factory" style="display:none">
	"""),_display_(Seq[Any](/*158.3*/formFactory(newFee))),format.raw/*158.22*/("""
</div>
""")))})),format.raw/*160.2*/("""

<script src=""""),_display_(Seq[Any](/*162.15*/routes/*162.21*/.Assets.at("javascripts/fee.js"))),format.raw/*162.53*/("""" type="text/javascript"></script>

""")))})),format.raw/*164.2*/("""
"""))}
    }
    
    def render(filterForm:Form[models.Fee],corporate:models.Corporate,fees:List[models.Fee],newFee:models.Fee): play.api.templates.Html = apply(filterForm,corporate,fees,newFee)
    
    def f:((Form[models.Fee],models.Corporate,List[models.Fee],models.Fee) => play.api.templates.Html) = (filterForm,corporate,fees,newFee) => apply(filterForm,corporate,fees,newFee)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/fee/admin_index.scala.html
                    HASH: 4c784fc84d7d8bd4d73ced5772001cec787dfa60
                    MATRIX: 788->1|1001->158|1020->169|1103->189|1140->192|1153->198|1369->406|1408->408|1485->449|1537->479|1629->535|1718->601|1809->656|1821->659|1857->673|1953->733|1965->736|2006->755|2107->820|2119->823|2165->847|2267->913|2279->916|2326->941|2416->995|2428->998|2463->1011|2725->1237|2764->1253|2857->1310|2869->1313|2905->1327|3178->1564|3215->1578|3306->1633|3318->1636|3354->1650|3624->1884|3659->1896|3751->1952|3763->1955|3799->1969|4076->2210|4118->2229|4221->2296|4233->2299|4280->2324|4448->2456|4461->2460|4543->2519|4624->2564|4639->2570|4697->2606|4791->2663|4805->2667|4931->2770|4999->2801|5013->2805|5097->2866|5205->2943|5247->104|5274->156|5302->2945|5342->2950|5607->3207|5646->3209|5714->3242|5727->3246|5819->3316|5857->3318|5875->3327|5902->3332|6014->3408|6029->3414|6083->3459|6123->3461|6188->3491|6279->3573|6347->3602|6416->3635|6442->3639|6488->3649|6515->3654|6555->3663|6749->3822|6964->4015|7095->4111|7313->4307|7463->4421|7703->4638|7835->4734|8067->4943|8197->5036|8417->5233|8641->5420|8655->5424|8740->5485|8861->5573|8912->5588|8940->5606|8981->5608|9094->5684|9108->5688|9285->5841|9345->5864|9362->5871|9399->5885|9482->5932|9496->5936|9595->6012|9657->6038|9670->6042|9792->6140|9915->6226|9929->6230|10020->6297|10108->6348|10122->6352|10214->6420|10302->6471|10316->6475|10405->6540|10493->6591|10507->6595|10607->6671|10712->6740|10745->6756|10786->6758|10827->6763|10866->6779|10902->6783|11043->6887|11057->6891|11147->6957|11203->6981|11249->6991|11277->7009|11318->7011|11406->7063|11448->7082|11489->7091|11542->7107|11558->7113|11613->7145|11682->7182
                    LINES: 26->1|31->5|31->5|33->5|34->6|34->6|41->13|41->13|42->14|42->14|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|48->20|48->20|48->20|54->26|54->26|55->27|55->27|55->27|62->34|62->34|63->35|63->35|63->35|70->42|70->42|71->43|71->43|71->43|78->50|78->50|79->51|79->51|79->51|84->56|84->56|84->56|85->57|85->57|85->57|85->57|85->57|85->57|85->57|85->57|85->57|90->62|92->1|93->4|94->63|98->67|102->71|102->71|104->73|104->73|104->73|104->73|104->73|104->73|111->80|111->80|111->80|111->80|113->82|113->82|113->82|114->83|114->83|114->83|114->83|115->84|120->89|122->91|125->94|127->96|130->99|132->101|135->104|137->106|140->109|142->111|146->115|146->115|146->115|150->119|153->122|153->122|153->122|156->125|156->125|156->125|158->127|158->127|158->127|161->130|161->130|161->130|165->134|165->134|165->134|168->137|168->137|168->137|169->138|169->138|169->138|170->139|170->139|170->139|171->140|171->140|171->140|175->144|175->144|175->144|176->145|176->145|177->146|181->150|181->150|181->150|184->153|187->156|187->156|187->156|189->158|189->158|191->160|193->162|193->162|193->162|195->164
                    -- GENERATED --
                */
            