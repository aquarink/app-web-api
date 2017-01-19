
package views.html.tools

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
object check_rate extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.forms.CashInInquiryForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.forms.CashInInquiryForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Check Rate & Fee")/*5.28*/{_display_(Seq[Any](format.raw/*5.29*/("""
<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*7.10*/doku/*7.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG011","Check Rate & Fee"))),format.raw/*7.85*/("""</h1>
</div>

<div class="row">
    <div class="span5 offset2">
        """),_display_(Seq[Any](/*12.10*/helper/*12.16*/.form(action = routes.Tools.checkRateSubmit, 'id -> "checkRateForm")/*12.84*/ {_display_(Seq[Any](format.raw/*12.86*/("""
		<div class="control-group row-fluid">
			<div class="span12">
				"""),_display_(Seq[Any](/*15.6*/select(
				myForm("channel.code"),
				options(models.Channel.optionsUser),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"),
				'_showConstraints -> false, 'class -> "required"
				))),format.raw/*20.6*/("""
			</div>
		</div>
		<div class="control-group row-fluid">
			<div class="span12">
				"""),_display_(Seq[Any](/*25.6*/select(
				myForm("senderCountry.code"),
				options(models.Country.options),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"),
				'_showConstraints -> false, 'class -> "required"
				))),format.raw/*30.6*/("""
			</div>
		</div>
		<div class="control-group row-fluid">
			<div class="span12">
				"""),_display_(Seq[Any](/*35.6*/select(
				myForm("senderCurrency.code"),
				options(models.Currency.optionsByCountryCode(myForm("senderCountry.code").value)),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG087","Currency"),
				'class -> "currency"
				))),format.raw/*40.6*/("""
			</div>
		</div>

		<div class="control-group row-fluid">
			<div class="span12">
				"""),_display_(Seq[Any](/*46.6*/select(
				myForm("beneficiaryCountry.code"),
				options(models.Country.options),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG088","Destination Country"), 
				'_default -> "-- Choose a country --",
				'_showConstraints -> false,
				'class -> "required country_id"
				))),format.raw/*53.6*/("""
			</div>
		</div>
		<div class="control-group row-fluid">
			<div class="span6">
				"""),_display_(Seq[Any](/*58.6*/select(myForm("beneficiaryCurrency.code"),
					options(""->"-- Please select country first --"),
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG089","Destination Currency"),
					'class -> "currency"))),format.raw/*61.27*/("""
			</div>
		</div>
		<div class="control-group row-fluid">
			<div class="span12">
				"""),_display_(Seq[Any](/*66.6*/inputText(myForm("senderAmount"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG090","Amount")
				, 'class -> "required number amount input-xlarge"
				, 'placeholder -> "Amount"
				, 'autocomplete -> "off"
				, '_showConstraints -> false))),format.raw/*71.34*/("""

			</div>
		</div>

		<div class="form-actions">
			<input type="submit" id="checkRateRateFormSubmit" class="btn btn-primary" value='"""),_display_(Seq[Any](/*77.86*/doku/*77.90*/.kirimdoku.util.MultiLanguage.getLanguage("LANG093","Check Rate"))),format.raw/*77.155*/("""'/>
		</div>
        """)))})),format.raw/*79.10*/("""
    </div>
    <div class="span5">
        <div id="summary" class="well boxed">
            <!-- SUMMARY SIDEBAR HERE -->
			"""),_display_(Seq[Any](/*84.5*/doku/*84.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG238","Check rate & fee give operator the easiest way to calculate foreign exchange rate"))),format.raw/*84.145*/("""
			<br/>
			"""),_display_(Seq[Any](/*86.5*/doku/*86.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG239","Exchange Rates will be updated by MTS Financial Administrator of PT. Nusa Satu Inti Artha everyday."))),format.raw/*86.163*/("""
			<br/>
			"""),_display_(Seq[Any](/*88.5*/doku/*88.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG240","If you have any question all about Exchange Rates, please write down your feedback or call us on +62 2261807511 (Monday-Saturday)"))),format.raw/*88.193*/("""
        </div>
    </div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*93.38*/routes/*93.44*/.Assets.at("javascripts/tools.js"))),format.raw/*93.78*/("""'></script>
<script type="text/javascript" src='"""),_display_(Seq[Any](/*94.38*/routes/*94.44*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*94.94*/("""'></script>
<script type="text/javascript">
	$(function() """),format.raw/*96.15*/("""{"""),format.raw/*96.16*/("""
			initCheckRateForm('#checkRateForm');
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/(""");
</script>
""")))})),format.raw/*100.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.forms.CashInInquiryForm]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[models.forms.CashInInquiryForm]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/check_rate.scala.html
                    HASH: 4e47273784c37b4cf715e38e7660df8ec10b2dfe
                    MATRIX: 764->1|937->47|964->99|1000->101|1034->127|1072->128|1143->164|1155->168|1247->239|1356->312|1371->318|1448->386|1488->388|1593->458|1832->676|1956->765|2205->993|2329->1082|2594->1326|2719->1416|3036->1712|3159->1800|3402->2021|3526->2110|3810->2372|3982->2508|3995->2512|4083->2577|4137->2599|4300->2727|4312->2731|4471->2867|4520->2881|4532->2885|4709->3039|4758->3053|4770->3057|4977->3241|5085->3313|5100->3319|5156->3353|5241->3402|5256->3408|5328->3458|5414->3516|5443->3517|5512->3559|5540->3560|5586->3574
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|36->7|36->7|36->7|41->12|41->12|41->12|41->12|44->15|49->20|54->25|59->30|64->35|69->40|75->46|82->53|87->58|90->61|95->66|100->71|106->77|106->77|106->77|108->79|113->84|113->84|113->84|115->86|115->86|115->86|117->88|117->88|117->88|122->93|122->93|122->93|123->94|123->94|123->94|125->96|125->96|127->98|127->98|129->100
                    -- GENERATED --
                */
            