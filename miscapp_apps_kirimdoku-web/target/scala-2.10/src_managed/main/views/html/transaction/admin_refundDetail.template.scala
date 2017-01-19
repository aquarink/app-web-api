
package views.html.transaction

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
object admin_refundDetail extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transactionForm: Form[models.Transaction]):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import helper._

import helper.twitterBootstrap._

import partial._

import play.api.data.format.Formats._  


Seq[Any](format.raw/*1.58*/("""
"""),format.raw/*8.1*/("""


"""),_display_(Seq[Any](/*11.2*/layout("Transaction Refund")/*11.30*/{_display_(Seq[Any](format.raw/*11.31*/("""

"""),_display_(Seq[Any](/*13.2*/partial/*13.9*/.flash_alert())),format.raw/*13.23*/("""

<div class="container-refund-inner">
"""),_display_(Seq[Any](/*16.2*/if(err)/*16.9*/ {_display_(Seq[Any](format.raw/*16.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*19.36*/err)),format.raw/*19.39*/("""</div>
		</div>
	</div>
""")))}/*22.3*/else/*22.8*/{_display_(Seq[Any](format.raw/*22.9*/("""
	"""),_display_(Seq[Any](/*23.3*/defining(transactionForm.get)/*23.32*/ { transaction =>_display_(Seq[Any](format.raw/*23.49*/("""
	"""),_display_(Seq[Any](/*24.3*/helper/*24.9*/.form(action = routes.Transaction.admin_refundProcess(transaction.idToken), 'id -> "fullrefund-form", 'class -> "row form-horizontal",Symbol("data-url-receipt") -> routes.Transaction.admin_refund().url, Symbol("data-remote") -> "true")/*24.244*/ {_display_(Seq[Any](format.raw/*24.246*/("""
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*26.13*/doku/*26.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG038","Transaction information"))),format.raw/*26.95*/("""</legend>
			<div class="hide">
				<div class="control-group span4 well">
					<input type="hidden" name="inquiry.idToken"/>
					<input type="hidden" name="user.idToken" value=""""),_display_(Seq[Any](/*30.55*/transaction/*30.66*/.agent.idToken)),format.raw/*30.80*/(""""/>
					<input type="hidden" name="corporate.code" value=""""),_display_(Seq[Any](/*31.57*/transaction/*31.68*/.agent.corporate.code)),format.raw/*31.89*/(""""/>
					"""),_display_(Seq[Any](/*32.7*/select(
						transactionForm("senderCountry.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"), 
						'_default -> "-- Choose a country --",
						'_showConstraints -> false, 'class -> "required"
					))),format.raw/*38.7*/("""
					"""),_display_(Seq[Any](/*39.7*/select(transactionForm("senderCurrency.code"),
						options("" -> "--"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG218","Sender currency"), 
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*43.34*/("""

					"""),_display_(Seq[Any](/*45.7*/select(transactionForm("beneficiaryCurrency.code"),
						options(models.Currency.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG220","Receive currency"),
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*49.34*/("""
					"""),_display_(Seq[Any](/*50.7*/inputText(transactionForm("fundSource"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG219","Source of fund"),
						'class -> "input-large",
						'_help -> "(Optional field)"
					))),format.raw/*54.7*/("""
				</div>
			</div>

			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*60.7*/helper/*60.13*/.inputText(
						transactionForm("channel.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*66.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*69.7*/helper/*69.13*/.input(
						transactionForm("statusText"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*75.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*75.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*76.31*/id)),format.raw/*76.33*/("""" name=""""),_display_(Seq[Any](/*76.42*/name)),format.raw/*76.46*/("""" value=""""),_display_(Seq[Any](/*76.56*/transaction/*76.67*/.statusText)),format.raw/*76.78*/("""" """),_display_(Seq[Any](/*76.81*/toHtmlArgs(args))),format.raw/*76.97*/(""">
					""")))})),format.raw/*77.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*80.7*/helper/*80.13*/.input(
						transactionForm("agent.fullName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG247","Operator"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*86.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*86.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*87.31*/id)),format.raw/*87.33*/("""" name=""""),_display_(Seq[Any](/*87.42*/name)),format.raw/*87.46*/("""" value=""""),_display_(Seq[Any](/*87.56*/transaction/*87.67*/.agent.fullName)),format.raw/*87.82*/("""" """),_display_(Seq[Any](/*87.85*/toHtmlArgs(args))),format.raw/*87.101*/(""">
					""")))})),format.raw/*88.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*93.7*/helper/*93.13*/.inputText(
						transactionForm("beneficiaryCountry.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*99.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*102.7*/helper/*102.13*/.inputText(
						transactionForm("beneficiaryCity"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*108.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*111.7*/helper/*111.13*/.inputText(
						transactionForm("forexReference.rate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*117.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*122.7*/helper/*122.13*/.inputText(
						transactionForm("senderAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*128.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*131.7*/helper/*131.13*/.inputText(
						transactionForm("beneficiaryAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*137.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*140.7*/helper/*140.13*/.input(
						transactionForm("feesTotal"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*146.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*146.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*147.30*/id)),format.raw/*147.32*/("""" name=""""),_display_(Seq[Any](/*147.41*/name)),format.raw/*147.45*/("""" value=""""),_display_(Seq[Any](/*147.55*/transaction/*147.66*/.feesTotal)),format.raw/*147.76*/("""" """),_display_(Seq[Any](/*147.79*/toHtmlArgs(args))),format.raw/*147.95*/(""">
					""")))})),format.raw/*148.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*153.7*/helper/*153.13*/.input(
						transactionForm("collectAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required input"
					)/*159.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*159.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*160.30*/id)),format.raw/*160.32*/("""" name=""""),_display_(Seq[Any](/*160.41*/name)),format.raw/*160.45*/("""" value=""""),_display_(Seq[Any](/*160.55*/transaction/*160.66*/.collectAmount)),format.raw/*160.80*/("""" """),_display_(Seq[Any](/*160.83*/toHtmlArgs(args))),format.raw/*160.99*/(""">
					""")))})),format.raw/*161.7*/("""
				</div>
				<div class="span8">
					"""),_display_(Seq[Any](/*164.7*/helper/*164.13*/.inputText(
						transactionForm("senderNote"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "input-xlarge",
						'style -> "width: 95%"
					))),format.raw/*171.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*177.13*/doku/*177.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG049","Sender information"))),format.raw/*177.90*/("""</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*180.7*/helper/*180.13*/.inputText(
						transactionForm("sender.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "input-xlarge",
						'class -> "required"
					))),format.raw/*187.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*190.7*/helper/*190.13*/.inputText(
						transactionForm("sender.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*196.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*199.7*/helper/*199.13*/.inputText(
						transactionForm("sender.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*205.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*210.7*/helper/*210.13*/.inputText(
						transactionForm("sender.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone number"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*216.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*219.7*/helper/*219.13*/.inputText(
						transactionForm("sender.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*225.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*228.7*/helper/*228.13*/.select(
						transactionForm("sender.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*235.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*240.7*/helper/*240.13*/.inputText(
						transactionForm("sender.personalIdType"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID type"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*246.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*249.7*/helper/*249.13*/.inputText(
						transactionForm("sender.personalId"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*255.7*/("""
				</div>
				<div class="span4">
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*262.7*/helper/*262.13*/.inputDate(
						transactionForm("sender.personalIdIssueDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue date"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*268.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*271.7*/helper/*271.13*/.inputDate(
						transactionForm("sender.personalIdExpireDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired date"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*277.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*280.7*/helper/*280.13*/.select(
						transactionForm("sender.personalIdCountry.name"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG246","Issuing country"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*287.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*292.7*/helper/*292.13*/.inputText(
						transactionForm("sender.occupation"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*298.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*301.7*/helper/*301.13*/.inputText(
						transactionForm("sender.cityName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*307.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*310.7*/helper/*310.13*/.inputDate(
						transactionForm("sender.birthDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*316.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*322.13*/doku/*322.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG058","Receiver information"))),format.raw/*322.92*/("""</legend>
				<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*325.7*/helper/*325.13*/.inputText(
						transactionForm("beneficiary.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*331.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*334.7*/helper/*334.13*/.inputText(
						transactionForm("beneficiary.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","First name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*340.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*343.7*/helper/*343.13*/.inputText(
						transactionForm("beneficiary.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*349.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*354.7*/helper/*354.13*/.inputText(
						transactionForm("beneficiary.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*360.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*363.7*/helper/*363.13*/.inputText(
						transactionForm("beneficiary.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*369.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*372.7*/helper/*372.13*/.select(
						transactionForm("beneficiary.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*379.7*/("""
				</div>
			</div>
		</fieldset>
		<div class="span12">
			"""),_display_(Seq[Any](/*384.5*/if((transaction.status == models.Transaction.STATUS_PENDING))/*384.66*/ {_display_(Seq[Any](format.raw/*384.68*/("""
				<div class="form-actions">
					"""),_display_(Seq[Any](/*386.7*/dynamicOr("editTransaction", String.valueOf(transaction.id))/*386.67*/ {_display_(Seq[Any](format.raw/*386.69*/("""
						<button class="btn btn-refund">"""),_display_(Seq[Any](/*387.39*/doku/*387.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG389","Authorize"))),format.raw/*387.107*/(""" <i class="icon-arrow-right"></i></button>
					""")))}/*388.7*/ {_display_(Seq[Any](format.raw/*388.9*/("""
						"""),_display_(Seq[Any](/*389.8*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG248","Operation not permitted. only unpaid transaction and registered sender operator can authorize full refund this transaction")))),format.raw/*389.206*/("""
					""")))})),format.raw/*390.7*/("""
				</div>
			""")))}/*392.6*/else/*392.11*/{_display_(Seq[Any](format.raw/*392.12*/("""
				"""),_display_(Seq[Any](/*393.6*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG249","Only unpaid transaction and registered sender operator can authorize full refund this transaction")))),format.raw/*393.179*/("""
			""")))})),format.raw/*394.5*/("""
		</div>
		
		<div class="modal fade hide" id="refund-dialog">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">×</button>
		        <h3>"""),_display_(Seq[Any](/*400.16*/doku/*400.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG244","Refund confirmation"))),format.raw/*400.94*/("""</h3>
		    </div>
			<form method="GET" class="form-vertical" data-remote="true">
				<div class="modal-body">
					<div class="control-groups">
						<p class="ban">"""),_display_(Seq[Any](/*405.23*/doku/*405.27*/.kirimdoku.util.MultiLanguage.getLanguage("LANG243","Are you sure you want to refund this transaction?"))),format.raw/*405.131*/("""</p>
						<dl class="dl-horizontal dl-aligned">
							<dt><label>"""),_display_(Seq[Any](/*407.20*/doku/*407.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*407.93*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*408.13*/transaction/*408.24*/.idToken)),format.raw/*408.32*/("""</dd>
							<dt><label>"""),_display_(Seq[Any](/*409.20*/doku/*409.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*409.90*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*410.13*/transaction/*410.24*/.sender.fullName)),format.raw/*410.40*/("""</dd>
							<dt><label>"""),_display_(Seq[Any](/*411.20*/doku/*411.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*411.93*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*412.13*/transaction/*412.24*/.senderAmountFormat)),format.raw/*412.43*/("""</dd>
						</dl>
					</div>
					<hr/>
					<div class="control-groups">
						<center style="text-align: center;">
							<label>"""),_display_(Seq[Any](/*418.16*/doku/*418.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG390","Please input full refund reason below:"))),format.raw/*418.113*/("""</label>
							<div class="control-group">
								<div class="input">
									<textarea name="refund_reason" class="required"></textarea>
								</div>
							</div>
						</center>
					</div>
					<p class="container-info">
					</p>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*430.52*/doku/*430.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*430.117*/("""</a>
					<button type="submit" class="btn btn-primary" data-disable-with="Processing..." data-enable-with="Proceed">"""),_display_(Seq[Any](/*431.114*/doku/*431.118*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*431.180*/("""</button>
				</div>
			</form>
		</div>
	""")))})),format.raw/*435.3*/("""
""")))}))))})),format.raw/*436.3*/("""
</div>
<script type="text/javascript" src='"""),_display_(Seq[Any](/*438.38*/routes/*438.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*438.84*/("""'></script>
""")))})),format.raw/*439.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.Transaction]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.Transaction]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/admin_refundDetail.scala.html
                    HASH: 30b6911326bd8d6172aeb660b242de9c34336784
                    MATRIX: 773->1|1120->57|1147->273|1186->277|1223->305|1262->306|1300->309|1315->316|1351->330|1426->370|1441->377|1481->379|1595->457|1620->460|1663->486|1675->491|1713->492|1751->495|1789->524|1844->541|1882->544|1896->550|2141->785|2182->787|2259->828|2272->832|2372->910|2588->1090|2608->1101|2644->1115|2740->1175|2760->1186|2803->1207|2848->1217|3161->1509|3203->1516|3454->1745|3497->1753|3766->2000|3808->2007|4034->2212|4143->2286|4158->2292|4409->2522|4486->2564|4501->2570|4733->2794|4800->2823|4867->2854|4891->2856|4936->2865|4962->2869|5008->2879|5028->2890|5061->2901|5100->2904|5138->2920|5177->2928|5254->2970|5269->2976|5507->3206|5574->3235|5641->3266|5665->3268|5710->3277|5736->3281|5782->3291|5802->3302|5839->3317|5878->3320|5917->3336|5956->3344|6064->3417|6079->3423|6350->3673|6428->3715|6444->3721|6696->3951|6774->3993|6790->3999|7055->4242|7164->4315|7180->4321|7439->4558|7517->4600|7533->4606|7800->4851|7878->4893|7894->4899|8122->5118|8190->5147|8257->5177|8282->5179|8328->5188|8355->5192|8402->5202|8423->5213|8456->5223|8496->5226|8535->5242|8575->5250|8684->5323|8700->5329|8952->5572|9020->5601|9087->5631|9112->5633|9158->5642|9185->5646|9232->5656|9253->5667|9290->5681|9330->5684|9369->5700|9409->5708|9487->5750|9503->5756|9795->6026|9911->6105|9925->6109|10021->6182|10118->6243|10134->6249|10424->6517|10502->6559|10518->6565|10777->6802|10855->6844|10871->6850|11120->7077|11229->7150|11245->7156|11508->7397|11586->7439|11602->7445|11848->7669|11926->7711|11942->7717|12242->7995|12351->8068|12367->8074|12628->8313|12706->8355|12722->8361|12981->8598|13125->8706|13141->8712|13402->8951|13480->8993|13496->8999|13760->9241|13838->9283|13854->9289|14167->9580|14276->9653|14292->9659|14544->9889|14622->9931|14638->9937|14882->10159|14960->10201|14976->10207|15230->10439|15346->10518|15360->10522|15458->10597|15556->10659|15572->10665|15831->10902|15909->10944|15925->10950|16185->11188|16263->11230|16279->11236|16537->11472|16646->11545|16662->11551|16926->11793|17004->11835|17020->11841|17275->12074|17353->12116|17369->12122|17670->12401|17769->12464|17840->12525|17881->12527|17955->12565|18025->12625|18066->12627|18142->12666|18156->12670|18244->12734|18312->12783|18352->12785|18396->12793|18618->12991|18657->12998|18692->13015|18706->13020|18746->13021|18788->13027|18985->13200|19022->13205|19249->13395|19263->13399|19360->13473|19565->13641|19579->13645|19707->13749|19812->13817|19826->13821|19918->13890|19981->13916|20002->13927|20033->13935|20095->13960|20109->13964|20198->14030|20261->14056|20282->14067|20321->14083|20383->14108|20397->14112|20489->14181|20552->14207|20573->14218|20615->14237|20785->14370|20799->14374|20916->14467|21286->14800|21300->14804|21385->14865|21541->14983|21556->14987|21642->15049|21717->15092|21756->15095|21838->15140|21854->15146|21917->15186|21962->15199
                    LINES: 26->1|40->1|41->8|44->11|44->11|44->11|46->13|46->13|46->13|49->16|49->16|49->16|52->19|52->19|55->22|55->22|55->22|56->23|56->23|56->23|57->24|57->24|57->24|57->24|59->26|59->26|59->26|63->30|63->30|63->30|64->31|64->31|64->31|65->32|71->38|72->39|76->43|78->45|82->49|83->50|87->54|93->60|93->60|99->66|102->69|102->69|108->75|108->75|109->76|109->76|109->76|109->76|109->76|109->76|109->76|109->76|109->76|110->77|113->80|113->80|119->86|119->86|120->87|120->87|120->87|120->87|120->87|120->87|120->87|120->87|120->87|121->88|126->93|126->93|132->99|135->102|135->102|141->108|144->111|144->111|150->117|155->122|155->122|161->128|164->131|164->131|170->137|173->140|173->140|179->146|179->146|180->147|180->147|180->147|180->147|180->147|180->147|180->147|180->147|180->147|181->148|186->153|186->153|192->159|192->159|193->160|193->160|193->160|193->160|193->160|193->160|193->160|193->160|193->160|194->161|197->164|197->164|204->171|210->177|210->177|210->177|213->180|213->180|220->187|223->190|223->190|229->196|232->199|232->199|238->205|243->210|243->210|249->216|252->219|252->219|258->225|261->228|261->228|268->235|273->240|273->240|279->246|282->249|282->249|288->255|295->262|295->262|301->268|304->271|304->271|310->277|313->280|313->280|320->287|325->292|325->292|331->298|334->301|334->301|340->307|343->310|343->310|349->316|355->322|355->322|355->322|358->325|358->325|364->331|367->334|367->334|373->340|376->343|376->343|382->349|387->354|387->354|393->360|396->363|396->363|402->369|405->372|405->372|412->379|417->384|417->384|417->384|419->386|419->386|419->386|420->387|420->387|420->387|421->388|421->388|422->389|422->389|423->390|425->392|425->392|425->392|426->393|426->393|427->394|433->400|433->400|433->400|438->405|438->405|438->405|440->407|440->407|440->407|441->408|441->408|441->408|442->409|442->409|442->409|443->410|443->410|443->410|444->411|444->411|444->411|445->412|445->412|445->412|451->418|451->418|451->418|463->430|463->430|463->430|464->431|464->431|464->431|468->435|469->436|471->438|471->438|471->438|472->439
                    -- GENERATED --
                */
            