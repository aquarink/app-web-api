
package views.html.forex

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
object admin_index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.Corporate,Form[models.Forex],List[Form[models.Forex]],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate: models.Corporate, filterForm: Form[models.Forex], forexs: List[Form[models.Forex]]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

def /*96.2*/tableForex/*96.12*/(corporate: models.Corporate, currencies: List[models.Currency], forexs: java.util.HashMap[models.Currency, List[models.Forex]]):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*96.144*/("""

<div class="row">
    <div class="span12">

        <form method="POST" action=""""),_display_(Seq[Any](/*101.38*/routes/*101.44*/.Forex.admin_create(corporate.code))),format.raw/*101.79*/("""" class="form-forex" autoComplete="off">
            <table border="0" class="table table-striped table-bordered table-forexs" style="width: auto">
                <tr>
                    <td class="column"><div></div></td>
                    """),_display_(Seq[Any](/*105.22*/for(h <- currencies) yield /*105.42*/ {_display_(Seq[Any](format.raw/*105.44*/("""
                    <td class="column"><div>"""),_display_(Seq[Any](/*106.46*/h/*106.47*/.code)),format.raw/*106.52*/("""</div></td>
                    """)))})),format.raw/*107.22*/("""
                </tr>
                """),_display_(Seq[Any](/*109.18*/for(v <- currencies) yield /*109.38*/ {_display_(Seq[Any](format.raw/*109.40*/("""
                <tr>
                    <td class="column"><div>1 """),_display_(Seq[Any](/*111.48*/v/*111.49*/.code)),format.raw/*111.54*/(""" =</div></td>
                    """),_display_(Seq[Any](/*112.22*/for(h <- currencies) yield /*112.42*/ {_display_(Seq[Any](format.raw/*112.44*/("""
                    <td class="column">
	                    <div>
							"""),_display_(Seq[Any](/*115.9*/for(f <- forexs.get(h)) yield /*115.32*/ {_display_(Seq[Any](format.raw/*115.34*/("""

								"""),_display_(Seq[Any](/*117.10*/if(f.origin.code==v.code && f.destination.code==h.code)/*117.65*/ {_display_(Seq[Any](format.raw/*117.67*/("""
								<input type="text" name=""""),_display_(Seq[Any](/*118.35*/f/*118.36*/.id)),format.raw/*118.39*/("""" size="8" class="span1 required number forex" value=""""),_display_(Seq[Any](/*118.94*/models/*118.100*/.Forex.format(f.rate))),format.raw/*118.121*/("""" rel="tooltip" title="Kurs jual untuk 1 """),_display_(Seq[Any](/*118.163*/v/*118.164*/.code)),format.raw/*118.169*/(""" ke """),_display_(Seq[Any](/*118.174*/f/*118.175*/.destination.code)),format.raw/*118.192*/(""" "/>
								""")))})),format.raw/*119.10*/("""
							""")))})),format.raw/*120.9*/("""
						</div>
                    </td>
                    """)))})),format.raw/*123.22*/("""
                </tr>
                """)))})),format.raw/*125.18*/("""
            </table>
			<div class="form-errors">
			</div>
            <div class="form-actions">
				<input type="reset" class="btn" value='"""),_display_(Seq[Any](/*130.45*/doku/*130.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*130.109*/("""'/>
                <input type="submit" value='"""),_display_(Seq[Any](/*131.46*/doku/*131.50*/.kirimdoku.util.MultiLanguage.getLanguage("LANG396","Update"))),format.raw/*131.111*/("""' class="btn btn-primary">
            </div>
        </form>
    </div>
</div>

<script src=""""),_display_(Seq[Any](/*137.15*/routes/*137.21*/.Assets.at("javascripts/forex.js"))),format.raw/*137.55*/("""" type="text/javascript"></script>
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*138.46*/routes/*138.52*/.Assets.at("stylesheets/forex.css"))),format.raw/*138.87*/("""">
""")))};
Seq[Any](format.raw/*1.97*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Forex setting", Seq(
routes.Corporate.admin_index().toString -> "Corporate management",
"" -> corporate.name,
"" -> "Exchange rate setting"
))/*10.3*/{_display_(Seq[Any](format.raw/*10.4*/("""

<div class="page-header">
  <h1>"""),_display_(Seq[Any](/*13.8*/doku/*13.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG387","Exchange rate setting for"))),format.raw/*13.92*/(""" """),_display_(Seq[Any](/*13.94*/corporate/*13.103*/.name)),format.raw/*13.108*/("""</h1>
</div>


<div class="common-container">
	<div class="row">
		<div class="span12 well">
			"""),_display_(Seq[Any](/*20.5*/form(action = routes.Forex.admin_create(corporate.code), 'id -> "forex-form", 'class -> "row")/*20.99*/ {_display_(Seq[Any](format.raw/*20.101*/("""
			<div class="hidden">
				"""),_display_(Seq[Any](/*22.6*/input(filterForm("corporate.code"), '_label -> "", '_showConstraints -> false)/*22.84*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*22.113*/("""
					<input type="hidden" id=""""),_display_(Seq[Any](/*23.32*/id)),format.raw/*23.34*/("""" name=""""),_display_(Seq[Any](/*23.43*/name)),format.raw/*23.47*/("""" value=""""),_display_(Seq[Any](/*23.57*/value)),format.raw/*23.62*/("""" class="input-small"/>
				""")))})),format.raw/*24.6*/("""
			</div>
				"""),_display_(Seq[Any](/*26.6*/input(filterForm("origin.code"), '_label -> "", '_showConstraints -> false)/*26.81*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*26.110*/("""
				<input type="hidden" name="session_"""),_display_(Seq[Any](/*27.41*/name)),format.raw/*27.45*/("""" value=""""),_display_(Seq[Any](/*27.55*/value)),format.raw/*27.60*/(""""/>
				""")))})),format.raw/*28.6*/("""
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*30.7*/select(filterForm("origin.code"), options(models.Currency.options), 
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG413","Sending currency")
					))),format.raw/*32.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*35.7*/select(filterForm("destination.code"), options(models.Currency.options), 
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG414","Pairing destination")
					))),format.raw/*37.7*/("""
				</div>
				<div class="control-group span4">
					<div class="clearfix nolabel">
						<label>&nbsp;</label>
						<div class="input">
							<button type="submit" class="btn btn-next"><i class="icon-plus"></i> """),_display_(Seq[Any](/*43.78*/doku/*43.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG353","Add"))),format.raw/*43.140*/("""</button>
						</div>
					</div>
				</div>
			""")))})),format.raw/*47.5*/("""
		</div>
	</div>

	<div class="common-container-inner">
		"""),_display_(Seq[Any](/*52.4*/partial/*52.11*/.flash_alert())),format.raw/*52.25*/("""
		<div class="row">
			<div class="span2">"""),_display_(Seq[Any](/*54.24*/doku/*54.28*/.kirimdoku.util.MultiLanguage.getLanguage("LANG406","Origin"))),format.raw/*54.89*/("""</div>
			<div class="span2">"""),_display_(Seq[Any](/*55.24*/doku/*55.28*/.kirimdoku.util.MultiLanguage.getLanguage("LANG407","Destination"))),format.raw/*55.94*/("""</div>
			<div class="span2">"""),_display_(Seq[Any](/*56.24*/doku/*56.28*/.kirimdoku.util.MultiLanguage.getLanguage("LANG408","Base rate"))),format.raw/*56.92*/("""</div>
			<div class="span2">"""),_display_(Seq[Any](/*57.24*/doku/*57.28*/.kirimdoku.util.MultiLanguage.getLanguage("LANG409","Spread"))),format.raw/*57.89*/(""" (%)</div>
			<div class="span2">"""),_display_(Seq[Any](/*58.24*/doku/*58.28*/.kirimdoku.util.MultiLanguage.getLanguage("LANG410","Sale rate"))),format.raw/*58.92*/("""</div>
		</div>
		"""),_display_(Seq[Any](/*60.4*/for(forex <- forexs) yield /*60.24*/ {_display_(Seq[Any](format.raw/*60.26*/("""
		"""),_display_(Seq[Any](/*61.4*/form(action = routes.Forex.admin_update(corporate.code, forex.get.id), 'id -> "forex-form", 'class -> "row form-horizontal form-forex", Symbol("data-remote") -> "true")/*61.172*/ {_display_(Seq[Any](format.raw/*61.174*/("""
			<input type="hidden" name="id" value=""""),_display_(Seq[Any](/*62.43*/forex/*62.48*/.get.id)),format.raw/*62.55*/(""""/>
			<input type="hidden" name="corporate.code" value=""""),_display_(Seq[Any](/*63.55*/forex/*63.60*/.get.corporate.code)),format.raw/*63.79*/(""""/>

			<div class="control-group span2">
				"""),_display_(Seq[Any](/*66.6*/inputText(forex("origin.code"), '_label -> "", '_class -> "nolabel", 'readOnly -> "true", 'class -> "disabled input-small"))),format.raw/*66.129*/("""
			</div>
			<div class="control-group span2">
				"""),_display_(Seq[Any](/*69.6*/inputText(forex("destination.code"), '_label -> "", '_class -> "nolabel", 'readOnly -> "true", 'class -> "disabled input-small"))),format.raw/*69.134*/("""
			</div>
			<div class="control-group span2">
				"""),_display_(Seq[Any](/*72.6*/input(forex("rate"), '_label -> "", '_class -> "nolabel", 'class -> "input")/*72.82*/ {  (id, name, value, args) =>_display_(Seq[Any](format.raw/*72.112*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*73.29*/id)),format.raw/*73.31*/("""" name=""""),_display_(Seq[Any](/*73.40*/name)),format.raw/*73.44*/("""" value=""""),_display_(Seq[Any](/*73.54*/forex/*73.59*/.get.rateFormat)),format.raw/*73.74*/("""" class="input-small rate"/>
				""")))})),format.raw/*74.6*/("""
			</div>
			<div class="control-group span2">
				"""),_display_(Seq[Any](/*77.6*/inputText(forex("spread"), '_label -> "", '_class -> "nolabel", 'class -> "input-small percentage"))),format.raw/*77.105*/("""
			</div>
			<div class="control-group span4">
				"""),_display_(Seq[Any](/*80.6*/input(forex("finalRate"), '_label -> "", '_class -> "nolabel", 'class -> "input")/*80.87*/ {  (id, name, value, args) =>_display_(Seq[Any](format.raw/*80.117*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*81.29*/id)),format.raw/*81.31*/("""" name=""""),_display_(Seq[Any](/*81.40*/name)),format.raw/*81.44*/("""" value=""""),_display_(Seq[Any](/*81.54*/forex/*81.59*/.get.rateFormat)),format.raw/*81.74*/("""" class="input-small" readonly="readonly"/>
				<button type="submit" class="btn btn-small"><i class="icon-ok"></i> """),_display_(Seq[Any](/*82.74*/doku/*82.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*82.137*/("""</button>
				<a class="btn btn-small" href=""""),_display_(Seq[Any](/*83.37*/routes/*83.43*/.Forex.admin_destroy(corporate.code, forex.get.id))),format.raw/*83.93*/("""" data-remote="true" data-method="DELETE" data-confirm='"""),_display_(Seq[Any](/*83.150*/doku/*83.154*/.kirimdoku.util.MultiLanguage.getLanguage("LANG411","Are you sure you want to delete this fee record?"))),format.raw/*83.257*/("""'><i class="icon-remove"></i> """),_display_(Seq[Any](/*83.288*/doku/*83.292*/.kirimdoku.util.MultiLanguage.getLanguage("LANG412","Delete"))),format.raw/*83.353*/("""</a>
				""")))})),format.raw/*84.6*/("""
			</div>
		""")))})),format.raw/*86.4*/("""
		""")))})),format.raw/*87.4*/("""
	</div>
</div>


<script src=""""),_display_(Seq[Any](/*92.15*/routes/*92.21*/.Assets.at("javascripts/forex.js"))),format.raw/*92.55*/("""" type="text/javascript"></script>
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*93.46*/routes/*93.52*/.Assets.at("stylesheets/forex.css"))),format.raw/*93.87*/("""">
""")))})),format.raw/*94.2*/("""

"""),format.raw/*139.2*/("""
"""))}
    }
    
    def render(corporate:models.Corporate,filterForm:Form[models.Forex],forexs:List[Form[models.Forex]]): play.api.templates.Html = apply(corporate,filterForm,forexs)
    
    def f:((models.Corporate,Form[models.Forex],List[Form[models.Forex]]) => play.api.templates.Html) = (corporate,filterForm,forexs) => apply(corporate,filterForm,forexs)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/forex/admin_index.scala.html
                    HASH: 82fad42f9287b4d02814e50d61a9e45728150101
                    MATRIX: 789->1|995->4534|1014->4544|1211->4676|1331->4759|1347->4765|1405->4800|1688->5046|1725->5066|1766->5068|1849->5114|1860->5115|1888->5120|1954->5153|2031->5193|2068->5213|2109->5215|2215->5284|2226->5285|2254->5290|2326->5325|2363->5345|2404->5347|2516->5423|2556->5446|2597->5448|2645->5459|2710->5514|2751->5516|2823->5551|2834->5552|2860->5555|2952->5610|2969->5616|3014->5637|3094->5679|3106->5680|3135->5685|3178->5690|3190->5691|3231->5708|3278->5722|3319->5731|3413->5792|3486->5832|3667->5976|3681->5980|3765->6040|3851->6089|3865->6093|3950->6154|4082->6249|4098->6255|4155->6289|4272->6369|4288->6375|4346->6410|4389->96|4417->149|4453->151|4611->301|4649->302|4719->337|4732->341|4834->421|4872->423|4891->432|4919->437|5051->534|5154->628|5195->630|5260->660|5347->738|5415->767|5483->799|5507->801|5552->810|5578->814|5624->824|5651->829|5711->858|5762->874|5846->949|5914->978|5991->1019|6017->1023|6063->1033|6090->1038|6130->1047|6210->1092|6398->1259|6489->1315|6685->1490|6938->1707|6951->1711|7032->1769|7113->1819|7208->1879|7224->1886|7260->1900|7340->1944|7353->1948|7436->2009|7502->2039|7515->2043|7603->2109|7669->2139|7682->2143|7768->2207|7834->2237|7847->2241|7930->2302|8000->2336|8013->2340|8099->2404|8153->2423|8189->2443|8229->2445|8268->2449|8446->2617|8487->2619|8566->2662|8580->2667|8609->2674|8703->2732|8717->2737|8758->2756|8840->2803|8986->2926|9074->2979|9225->3107|9313->3160|9398->3236|9467->3266|9532->3295|9556->3297|9601->3306|9627->3310|9673->3320|9687->3325|9724->3340|9789->3374|9877->3427|9999->3526|10087->3579|10177->3660|10246->3690|10311->3719|10335->3721|10380->3730|10406->3734|10452->3744|10466->3749|10503->3764|10656->3881|10669->3885|10751->3944|10833->3990|10848->3996|10920->4046|11014->4103|11028->4107|11154->4210|11222->4241|11236->4245|11320->4306|11361->4316|11406->4330|11441->4334|11509->4366|11524->4372|11580->4406|11696->4486|11711->4492|11768->4527|11803->4531|11833->6414
                    LINES: 26->1|31->96|31->96|33->96|38->101|38->101|38->101|42->105|42->105|42->105|43->106|43->106|43->106|44->107|46->109|46->109|46->109|48->111|48->111|48->111|49->112|49->112|49->112|52->115|52->115|52->115|54->117|54->117|54->117|55->118|55->118|55->118|55->118|55->118|55->118|55->118|55->118|55->118|55->118|55->118|55->118|56->119|57->120|60->123|62->125|67->130|67->130|67->130|68->131|68->131|68->131|74->137|74->137|74->137|75->138|75->138|75->138|77->1|79->5|80->6|84->10|84->10|87->13|87->13|87->13|87->13|87->13|87->13|94->20|94->20|94->20|96->22|96->22|96->22|97->23|97->23|97->23|97->23|97->23|97->23|98->24|100->26|100->26|100->26|101->27|101->27|101->27|101->27|102->28|104->30|106->32|109->35|111->37|117->43|117->43|117->43|121->47|126->52|126->52|126->52|128->54|128->54|128->54|129->55|129->55|129->55|130->56|130->56|130->56|131->57|131->57|131->57|132->58|132->58|132->58|134->60|134->60|134->60|135->61|135->61|135->61|136->62|136->62|136->62|137->63|137->63|137->63|140->66|140->66|143->69|143->69|146->72|146->72|146->72|147->73|147->73|147->73|147->73|147->73|147->73|147->73|148->74|151->77|151->77|154->80|154->80|154->80|155->81|155->81|155->81|155->81|155->81|155->81|155->81|156->82|156->82|156->82|157->83|157->83|157->83|157->83|157->83|157->83|157->83|157->83|157->83|158->84|160->86|161->87|166->92|166->92|166->92|167->93|167->93|167->93|168->94|170->139
                    -- GENERATED --
                */
            