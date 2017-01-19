
package views.html.credit

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
object admin_index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.Corporate],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporateForm: Form[models.Corporate]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.41*/("""
"""),format.raw/*4.1*/("""

"""),_display_(Seq[Any](/*6.2*/layout("Credit setting", Seq(
routes.Corporate.admin_index().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management"),
"" -> corporateForm.get.name,
"" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG425","Credit setting")
))/*10.3*/ {_display_(Seq[Any](format.raw/*10.5*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*12.7*/doku/*12.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG426","Credit setting for"))),format.raw/*12.84*/(""" """),_display_(Seq[Any](/*12.86*/corporateForm/*12.99*/.get.name)),format.raw/*12.108*/("""</h1>
</div>


<div class="common-container">
    <div class="well">
		"""),_display_(Seq[Any](/*18.4*/defining(corporateForm.get)/*18.31*/ {corporate =>_display_(Seq[Any](format.raw/*18.45*/("""
		<dl class="dl-horizontal dl-horizontal-aligned">
			<dt>"""),_display_(Seq[Any](/*20.9*/doku/*20.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG316","Corporate code"))),format.raw/*20.82*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*21.9*/corporate/*21.18*/.code)),format.raw/*21.23*/("""</dd>
			<dt>"""),_display_(Seq[Any](/*22.9*/doku/*22.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Corporate name"))),format.raw/*22.82*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*23.9*/corporate/*23.18*/.name)),format.raw/*23.23*/("""</dd>
			"""),_display_(Seq[Any](/*24.5*/defining(corporate.getOperatorStatistic)/*24.45*/ { (operatorStatistic) =>_display_(Seq[Any](format.raw/*24.70*/("""
			"""),_display_(Seq[Any](/*25.5*/if(operatorStatistic != null)/*25.34*/ {_display_(Seq[Any](format.raw/*25.36*/("""
			<dt>"""),_display_(Seq[Any](/*26.9*/doku/*26.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG427","Number of operators"))),format.raw/*26.87*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*27.9*/operatorStatistic/*27.26*/.totalOperator)),format.raw/*27.40*/("""</dd>
			<dt>"""),_display_(Seq[Any](/*28.9*/doku/*28.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG428","Total credit operators"))),format.raw/*28.90*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*29.9*/models/*29.15*/.Currency.format(operatorStatistic.totalCreditLimit, corporate.currency.code))),format.raw/*29.92*/(""" </dd>
			""")))})),format.raw/*30.5*/("""
			""")))})),format.raw/*31.5*/("""
			<dt>"""),_display_(Seq[Any](/*32.9*/doku/*32.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG032","Credit limit"))),format.raw/*32.80*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*33.9*/models/*33.15*/.Currency.format(corporate.creditLimit, corporate.currency.code))),format.raw/*33.79*/("""</dd>
			<dt>"""),_display_(Seq[Any](/*34.9*/doku/*34.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG429","Credit usage"))),format.raw/*34.80*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*35.9*/models/*35.15*/.Currency.format(corporate.getStatistic.totalUnsettledSenderAmount, corporate.currency.code))),format.raw/*35.107*/("""</dd>
			<dt>"""),_display_(Seq[Any](/*36.9*/doku/*36.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG430","Credit balance"))),format.raw/*36.82*/("""</dt>
			<dd>"""),_display_(Seq[Any](/*37.9*/models/*37.15*/.Currency.format(corporate.getStatistic.remainingLimit, corporate.currency.code))),format.raw/*37.95*/("""</dd>
		</dl>
		""")))})),format.raw/*39.4*/("""
    </div>

	<div class="common-container-inner">
		<div class="row">
			<div class="span6">
				"""),_display_(Seq[Any](/*45.6*/partial/*45.13*/.flash_alert())),format.raw/*45.27*/("""

				"""),_display_(Seq[Any](/*47.6*/helper/*47.12*/.form(action = routes.Credit.admin_update(corporateForm.get.code), 'id -> "corporate-credit-form", 'class -> "form-ajax form-horizontal", Symbol("data-remote") -> "true")/*47.182*/ {_display_(Seq[Any](format.raw/*47.184*/("""
				"""),_display_(Seq[Any](/*48.6*/defining(corporateForm.get)/*48.33*/ {corporate =>_display_(Seq[Any](format.raw/*48.47*/("""
				<input type="hidden" name="code" value=""""),_display_(Seq[Any](/*49.46*/corporateForm("code")/*49.67*/.value)),format.raw/*49.73*/(""""/>
				<input type="hidden" name="name" value=""""),_display_(Seq[Any](/*50.46*/corporateForm("code")/*50.67*/.name)),format.raw/*50.72*/(""""/>
				<div class="control-group">
					"""),_display_(Seq[Any](/*52.7*/input(corporateForm("creditLimit"), 
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG431","Accumulation limit")
						, '_showConstraints -> false
						, 'class -> "required amount"
						)/*56.8*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*56.37*/("""
						<div class="input-append">
							<input type="text" name=""""),_display_(Seq[Any](/*58.34*/name)),format.raw/*58.38*/("""" value=""""),_display_(Seq[Any](/*58.48*/value)),format.raw/*58.53*/("""" """),_display_(Seq[Any](/*58.56*/toHtmlArgs(args))),format.raw/*58.72*/("""/>
							<span class="add-on">"""),_display_(Seq[Any](/*59.30*/corporate/*59.39*/.currency.code)),format.raw/*59.53*/("""</span>
						</div>
						""")))})),format.raw/*61.8*/("""
				</div>
				<div class="control-group">
					"""),_display_(Seq[Any](/*64.7*/input(corporateForm("creditAlertLimit"), 
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG432","Accumulation alert")
						, '_showConstraints -> false
						, 'class -> "required amount"
						)/*68.8*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*68.37*/("""
						<div class="input-append">
							<input type="text" name=""""),_display_(Seq[Any](/*70.34*/name)),format.raw/*70.38*/("""" value=""""),_display_(Seq[Any](/*70.48*/value)),format.raw/*70.53*/("""" """),_display_(Seq[Any](/*70.56*/toHtmlArgs(args))),format.raw/*70.72*/("""/>
							<span class="add-on">"""),_display_(Seq[Any](/*71.30*/corporate/*71.39*/.currency.code)),format.raw/*71.53*/("""</span>
						</div>
						""")))})),format.raw/*73.8*/("""
				</div>
				<div class="control-group">
					"""),_display_(Seq[Any](/*76.7*/input(corporateForm("customerSendLimit"), 
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG433","Customer accumulation limit")
						, '_showConstraints -> false
						, 'class -> "required amount"
						)/*80.8*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*80.37*/("""
						<div class="input-append">
							<input type="text" name=""""),_display_(Seq[Any](/*82.34*/name)),format.raw/*82.38*/("""" value=""""),_display_(Seq[Any](/*82.48*/value)),format.raw/*82.53*/("""" """),_display_(Seq[Any](/*82.56*/toHtmlArgs(args))),format.raw/*82.72*/("""/>
							<span class="add-on">"""),_display_(Seq[Any](/*83.30*/corporate/*83.39*/.currency.code)),format.raw/*83.53*/("""</span>
						</div>
						""")))})),format.raw/*85.8*/("""
				</div>

				<div class="form-actions">
					<button type="reset" class="btn"><i class="icon-repeat"></i> """),_display_(Seq[Any](/*89.68*/doku/*89.72*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*89.132*/("""</button>
					<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*90.53*/doku/*90.57*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*90.116*/("""</button>
				</div>
				""")))})),format.raw/*92.6*/("""
				""")))})),format.raw/*93.6*/("""
			</div>
			<div class="span6">
				<div class="well">
					<p>"""),_display_(Seq[Any](/*97.10*/doku/*97.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG434","Formula for Credit Score: 2 x total credit of operators limit accumulation"))),format.raw/*97.143*/("""</p>
					<p>"""),_display_(Seq[Any](/*98.10*/doku/*98.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG435","Formula for Alert Limit: 1/4 of Credit Score"))),format.raw/*98.113*/("""</p>
					<!--<p>Customer accumulation limit is transaction limit accumulation for Sender or Receiver in 3 days for the same occuring Sender or Receiver</p>-->
				</div>
			</div>
		</div>
	</div>
</div>
""")))}/*105.2*/ {_display_(Seq[Any](format.raw/*105.4*/("""
""")))})),format.raw/*106.2*/("""
"""))}
    }
    
    def render(corporateForm:Form[models.Corporate]): play.api.templates.Html = apply(corporateForm)
    
    def f:((Form[models.Corporate]) => play.api.templates.Html) = (corporateForm) => apply(corporateForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/credit/admin_index.scala.html
                    HASH: 78f42c2beb4de4e80a67e3f8a768ee6db5ce3547
                    MATRIX: 752->1|918->40|945->92|982->95|1256->361|1295->363|1363->396|1376->400|1471->473|1509->475|1531->488|1563->497|1670->569|1706->596|1758->610|1853->670|1866->674|1957->743|2006->757|2024->766|2051->771|2100->785|2113->789|2204->858|2253->872|2271->881|2298->886|2343->896|2392->936|2455->961|2495->966|2533->995|2573->997|2617->1006|2630->1010|2726->1084|2775->1098|2801->1115|2837->1129|2886->1143|2899->1147|2998->1224|3047->1238|3062->1244|3161->1321|3203->1332|3239->1337|3283->1346|3296->1350|3385->1417|3434->1431|3449->1437|3535->1501|3584->1515|3597->1519|3686->1586|3735->1600|3750->1606|3865->1698|3914->1712|3927->1716|4018->1785|4067->1799|4082->1805|4184->1885|4232->1902|4366->2001|4382->2008|4418->2022|4460->2029|4475->2035|4655->2205|4696->2207|4737->2213|4773->2240|4825->2254|4907->2300|4937->2321|4965->2327|5050->2376|5080->2397|5107->2402|5184->2444|5402->2654|5469->2683|5572->2750|5598->2754|5644->2764|5671->2769|5710->2772|5748->2788|5816->2820|5834->2829|5870->2843|5929->2871|6014->2921|6237->3136|6304->3165|6407->3232|6433->3236|6479->3246|6506->3251|6545->3254|6583->3270|6651->3302|6669->3311|6705->3325|6764->3353|6849->3403|7082->3628|7149->3657|7252->3724|7278->3728|7324->3738|7351->3743|7390->3746|7428->3762|7496->3794|7514->3803|7550->3817|7609->3845|7756->3956|7769->3960|7852->4020|7950->4082|7963->4086|8045->4145|8102->4171|8139->4177|8241->4243|8254->4247|8406->4376|8456->4390|8469->4394|8591->4493|8816->4699|8856->4701|8890->4703
                    LINES: 26->1|32->1|33->4|35->6|39->10|39->10|41->12|41->12|41->12|41->12|41->12|41->12|47->18|47->18|47->18|49->20|49->20|49->20|50->21|50->21|50->21|51->22|51->22|51->22|52->23|52->23|52->23|53->24|53->24|53->24|54->25|54->25|54->25|55->26|55->26|55->26|56->27|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30|60->31|61->32|61->32|61->32|62->33|62->33|62->33|63->34|63->34|63->34|64->35|64->35|64->35|65->36|65->36|65->36|66->37|66->37|66->37|68->39|74->45|74->45|74->45|76->47|76->47|76->47|76->47|77->48|77->48|77->48|78->49|78->49|78->49|79->50|79->50|79->50|81->52|85->56|85->56|87->58|87->58|87->58|87->58|87->58|87->58|88->59|88->59|88->59|90->61|93->64|97->68|97->68|99->70|99->70|99->70|99->70|99->70|99->70|100->71|100->71|100->71|102->73|105->76|109->80|109->80|111->82|111->82|111->82|111->82|111->82|111->82|112->83|112->83|112->83|114->85|118->89|118->89|118->89|119->90|119->90|119->90|121->92|122->93|126->97|126->97|126->97|127->98|127->98|127->98|134->105|134->105|135->106
                    -- GENERATED --
                */
            