
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
object refundDetail extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.Transaction],play.api.templates.Html] {

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
	"""),_display_(Seq[Any](/*24.3*/helper/*24.9*/.form(action = routes.Transaction.refundProcess(transaction.idToken), 'id -> "refund-form", 'class -> "row form-horizontal", Symbol("data-url-receipt") -> routes.Transaction.refundReceipt(transaction.idToken).url, Symbol("data-remote") -> "true")/*24.255*/ {_display_(Seq[Any](format.raw/*24.257*/("""
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
						'_showConstraints -> false, 
						'class -> "required"
					))),format.raw/*39.7*/("""
					"""),_display_(Seq[Any](/*40.7*/select(transactionForm("senderCurrency.code"),
						options("" -> "--"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG218","Sender currency"), 
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*44.34*/("""

					"""),_display_(Seq[Any](/*46.7*/select(transactionForm("beneficiaryCurrency.code"),
						options(models.Currency.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG220","Receive currency"),
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*50.34*/("""
					"""),_display_(Seq[Any](/*51.7*/inputText(transactionForm("fundSource"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG219","Source of fund"),
						'class -> "input-large",
						'_help -> "(Optional field)"
					))),format.raw/*55.7*/("""
				</div>
			</div>

			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*61.7*/helper/*61.13*/.inputText(
						transactionForm("channel.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*67.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*70.7*/helper/*70.13*/.input(
						transactionForm("statusText"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*76.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*76.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*77.31*/id)),format.raw/*77.33*/("""" name=""""),_display_(Seq[Any](/*77.42*/name)),format.raw/*77.46*/("""" value=""""),_display_(Seq[Any](/*77.56*/transaction/*77.67*/.statusText)),format.raw/*77.78*/("""" """),_display_(Seq[Any](/*77.81*/toHtmlArgs(args))),format.raw/*77.97*/(""">
					""")))})),format.raw/*78.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*81.7*/helper/*81.13*/.input(
						transactionForm("agent.fullName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG247","Operator"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*87.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*87.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*88.31*/id)),format.raw/*88.33*/("""" name=""""),_display_(Seq[Any](/*88.42*/name)),format.raw/*88.46*/("""" value=""""),_display_(Seq[Any](/*88.56*/transaction/*88.67*/.agent.fullName)),format.raw/*88.82*/("""" """),_display_(Seq[Any](/*88.85*/toHtmlArgs(args))),format.raw/*88.101*/(""">
					""")))})),format.raw/*89.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*94.7*/helper/*94.13*/.inputText(
						transactionForm("beneficiaryCountry.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*100.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*103.7*/helper/*103.13*/.inputText(
						transactionForm("beneficiaryCity"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*109.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*112.7*/helper/*112.13*/.input(
						transactionForm("forexReference.rate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required amount rate"
					)/*118.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*118.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*119.30*/id)),format.raw/*119.32*/("""" name=""""),_display_(Seq[Any](/*119.41*/name)),format.raw/*119.45*/("""" value=""""),_display_(Seq[Any](/*119.55*/transaction/*119.66*/.forexReference.rateFormat)),format.raw/*119.92*/("""" """),_display_(Seq[Any](/*119.95*/toHtmlArgs(args))),format.raw/*119.111*/(""">
					""")))})),format.raw/*120.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*125.7*/helper/*125.13*/.input(
						transactionForm("senderAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*131.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*131.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*132.30*/id)),format.raw/*132.32*/("""" name=""""),_display_(Seq[Any](/*132.41*/name)),format.raw/*132.45*/("""" value=""""),_display_(Seq[Any](/*132.55*/transaction/*132.66*/.senderAmountFormat)),format.raw/*132.85*/("""" """),_display_(Seq[Any](/*132.88*/toHtmlArgs(args))),format.raw/*132.104*/(""">
					""")))})),format.raw/*133.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*136.7*/helper/*136.13*/.input(
						transactionForm("beneficiaryAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*142.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*142.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*143.30*/id)),format.raw/*143.32*/("""" name=""""),_display_(Seq[Any](/*143.41*/name)),format.raw/*143.45*/("""" value=""""),_display_(Seq[Any](/*143.55*/transaction/*143.66*/.beneficiaryAmountFormat)),format.raw/*143.90*/("""" """),_display_(Seq[Any](/*143.93*/toHtmlArgs(args))),format.raw/*143.109*/(""">
					""")))})),format.raw/*144.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*147.7*/helper/*147.13*/.input(
						transactionForm("feesTotal"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*153.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*153.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*154.30*/id)),format.raw/*154.32*/("""" name=""""),_display_(Seq[Any](/*154.41*/name)),format.raw/*154.45*/("""" value=""""),_display_(Seq[Any](/*154.55*/transaction/*154.66*/.feesTotalFormat)),format.raw/*154.82*/("""" """),_display_(Seq[Any](/*154.85*/toHtmlArgs(args))),format.raw/*154.101*/(""">
					""")))})),format.raw/*155.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*160.7*/helper/*160.13*/.input(
						transactionForm("collectAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*166.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*166.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*167.30*/id)),format.raw/*167.32*/("""" name=""""),_display_(Seq[Any](/*167.41*/name)),format.raw/*167.45*/("""" value=""""),_display_(Seq[Any](/*167.55*/transaction/*167.66*/.collectAmountFormat)),format.raw/*167.86*/("""" """),_display_(Seq[Any](/*167.89*/toHtmlArgs(args))),format.raw/*167.105*/(""">
					""")))})),format.raw/*168.7*/("""
				</div>
				<div class="span8">
					"""),_display_(Seq[Any](/*171.7*/helper/*171.13*/.inputText(
						transactionForm("senderNote"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "input-xlarge",
						'style -> "width: 95%"
					))),format.raw/*178.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*184.13*/doku/*184.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG049","Sender information"))),format.raw/*184.90*/("""</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*187.7*/helper/*187.13*/.inputText(
						transactionForm("sender.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "input-xlarge",
						'class -> "required"
					))),format.raw/*194.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*197.7*/helper/*197.13*/.inputText(
						transactionForm("sender.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*203.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*206.7*/helper/*206.13*/.inputText(
						transactionForm("sender.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*212.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*217.7*/helper/*217.13*/.inputText(
						transactionForm("sender.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone number"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*223.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*226.7*/helper/*226.13*/.inputText(
						transactionForm("sender.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*232.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*235.7*/helper/*235.13*/.select(
						transactionForm("sender.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*242.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*247.7*/helper/*247.13*/.inputText(
						transactionForm("sender.personalIdType"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID type"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*253.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*256.7*/helper/*256.13*/.inputText(
						transactionForm("sender.personalId"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*262.7*/("""
				</div>
				<div class="span4">
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*269.7*/helper/*269.13*/.inputDate(
						transactionForm("sender.personalIdIssueDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue date"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*275.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*278.7*/helper/*278.13*/.inputDate(
						transactionForm("sender.personalIdExpireDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired date"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*284.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*287.7*/helper/*287.13*/.select(
						transactionForm("sender.personalIdCountry.name"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG246","Issuing country"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*294.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*299.7*/helper/*299.13*/.inputText(
						transactionForm("sender.occupation"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*305.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*308.7*/helper/*308.13*/.inputText(
						transactionForm("sender.cityName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*314.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*317.7*/helper/*317.13*/.inputDate(
						transactionForm("sender.birthDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*323.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*329.13*/doku/*329.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG058","Receiver information"))),format.raw/*329.92*/("""</legend>
				<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*332.7*/helper/*332.13*/.inputText(
						transactionForm("beneficiary.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*338.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*341.7*/helper/*341.13*/.inputText(
						transactionForm("beneficiary.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","First name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*347.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*350.7*/helper/*350.13*/.inputText(
						transactionForm("beneficiary.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*356.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*361.7*/helper/*361.13*/.inputText(
						transactionForm("beneficiary.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*367.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*370.7*/helper/*370.13*/.inputText(
						transactionForm("beneficiary.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*376.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*379.7*/helper/*379.13*/.select(
						transactionForm("beneficiary.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*386.7*/("""
				</div>
			</div>
		</fieldset>
		<div class="span12">
			"""),_display_(Seq[Any](/*391.5*/if((transaction.status == models.Transaction.STATUS_PENDING))/*391.66*/ {_display_(Seq[Any](format.raw/*391.68*/("""
				<div class="form-actions">
					"""),_display_(Seq[Any](/*393.7*/dynamicOr("editTransaction", String.valueOf(transaction.id))/*393.67*/ {_display_(Seq[Any](format.raw/*393.69*/("""
						<button class="btn btn-refund">"""),_display_(Seq[Any](/*394.39*/doku/*394.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG098","Refund"))),format.raw/*394.104*/("""<i class="icon-arrow-right"></i></button>
					""")))}/*395.7*/ {_display_(Seq[Any](format.raw/*395.9*/("""
						"""),_display_(Seq[Any](/*396.8*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG248","Operation not permitted. only unpaid transaction and registered sender operator can refund this transaction")))),format.raw/*396.191*/("""
					""")))})),format.raw/*397.7*/("""
				</div>
			""")))}/*399.6*/else/*399.11*/{_display_(Seq[Any](format.raw/*399.12*/("""
				"""),_display_(Seq[Any](/*400.6*/if((transaction.status == models.Transaction.STATUS_PENDING_REFUND))/*400.74*/ {_display_(Seq[Any](format.raw/*400.76*/("""
				<div class="form-actions">
					"""),_display_(Seq[Any](/*402.7*/dynamicOr("editTransaction", String.valueOf(transaction.id))/*402.67*/ {_display_(Seq[Any](format.raw/*402.69*/("""
						<button class="btn btn-refund">"""),_display_(Seq[Any](/*403.39*/doku/*403.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG245","Full Refund"))),format.raw/*403.109*/(""" <i class="icon-arrow-right"></i></button>
					""")))}/*404.7*/ {_display_(Seq[Any](format.raw/*404.9*/("""
						"""),_display_(Seq[Any](/*405.8*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG248","Operation not permitted. only unpaid transaction and registered sender operator can refund this transaction")))),format.raw/*405.191*/("""
					""")))})),format.raw/*406.7*/("""
				</div>
				""")))}/*408.7*/else/*408.12*/{_display_(Seq[Any](format.raw/*408.13*/("""
					"""),_display_(Seq[Any](/*409.7*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG249","Only unpaid transaction and registered sender operator can refund this transaction")))),format.raw/*409.165*/("""
				""")))})),format.raw/*410.6*/("""
			""")))})),format.raw/*411.5*/("""
		</div>
		
		<div class="modal fade hide" id="refund-dialog">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">×</button>
		        <h3>"""),_display_(Seq[Any](/*417.16*/doku/*417.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG244","Refund confirmation"))),format.raw/*417.94*/("""</h3>
		    </div>
			<form method="GET" class="form-vertical" data-remote="true">
				<div class="modal-body">
					<div class="control-groups">
						<p class="ban">"""),_display_(Seq[Any](/*422.23*/doku/*422.27*/.kirimdoku.util.MultiLanguage.getLanguage("LANG243","Are you sure you want to refund this transaction?"))),format.raw/*422.131*/("""</p>
						<dl class="dl-horizontal dl-aligned">
							<dt><label>"""),_display_(Seq[Any](/*424.20*/doku/*424.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*424.93*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*425.13*/transaction/*425.24*/.idToken)),format.raw/*425.32*/("""</dd>
							<dt><label>"""),_display_(Seq[Any](/*426.20*/doku/*426.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*426.90*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*427.13*/transaction/*427.24*/.sender.fullName)),format.raw/*427.40*/("""</dd>
							<dt><label>"""),_display_(Seq[Any](/*428.20*/doku/*428.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*428.93*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*429.13*/transaction/*429.24*/.senderAmountFormat)),format.raw/*429.43*/("""</dd>
						</dl>
					</div>
					<hr/>
					<div class="control-groups">
						<center style="text-align: center;">
							<label>"""),_display_(Seq[Any](/*435.16*/doku/*435.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG242","Please input PIN below:"))),format.raw/*435.98*/("""</label>
							<div class="control-group">
								<div class="input">
									<input id="auth1" type="password" name="auth1" class="required" minlength="4" maxlength="4" placeholder="" style="text-align: center;"/>
								</div>
							</div>
						</center>
					</div>
					<p class="container-info">
					</p>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*447.52*/doku/*447.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*447.117*/("""</a>
					<button type="submit" class="btn btn-primary" data-disable-with="Processing..." data-enable-with="Proceed">"""),_display_(Seq[Any](/*448.114*/doku/*448.118*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*448.180*/("""</button>
				</div>
			</form>
		</div>
	""")))})),format.raw/*452.3*/("""
""")))}))))})),format.raw/*453.3*/("""
</div>
""")))}/*455.2*/ {_display_(Seq[Any](format.raw/*455.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*456.38*/routes/*456.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*456.84*/("""'></script>
""")))})),format.raw/*457.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.Transaction]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.Transaction]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:33 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/refundDetail.scala.html
                    HASH: c259ca676df2a256a41b9276c4f2dcd3658fdeb3
                    MATRIX: 767->1|1114->57|1141->273|1180->277|1217->305|1256->306|1294->309|1309->316|1345->330|1420->370|1435->377|1475->379|1589->457|1614->460|1657->486|1669->491|1707->492|1745->495|1783->524|1838->541|1876->544|1890->550|2146->796|2187->798|2264->839|2277->843|2377->921|2593->1101|2613->1112|2649->1126|2745->1186|2765->1197|2808->1218|2853->1228|3173->1527|3215->1534|3466->1763|3509->1771|3778->2018|3820->2025|4046->2230|4155->2304|4170->2310|4421->2540|4498->2582|4513->2588|4745->2812|4812->2841|4879->2872|4903->2874|4948->2883|4974->2887|5020->2897|5040->2908|5073->2919|5112->2922|5150->2938|5189->2946|5266->2988|5281->2994|5519->3224|5586->3253|5653->3284|5677->3286|5722->3295|5748->3299|5794->3309|5814->3320|5851->3335|5890->3338|5929->3354|5968->3362|6076->3435|6091->3441|6363->3691|6441->3733|6457->3739|6709->3969|6787->4011|6803->4017|7063->4268|7131->4297|7198->4327|7223->4329|7269->4338|7296->4342|7343->4352|7364->4363|7413->4389|7453->4392|7493->4408|7533->4416|7642->4489|7658->4495|7906->4734|7974->4763|8041->4793|8066->4795|8112->4804|8139->4808|8186->4818|8207->4829|8249->4848|8289->4851|8329->4867|8369->4875|8447->4917|8463->4923|8719->5170|8787->5199|8854->5229|8879->5231|8925->5240|8952->5244|8999->5254|9020->5265|9067->5289|9107->5292|9147->5308|9187->5316|9265->5358|9281->5364|9515->5589|9583->5618|9650->5648|9675->5650|9721->5659|9748->5663|9795->5673|9816->5684|9855->5700|9895->5703|9935->5719|9975->5727|10084->5800|10100->5806|10352->6049|10420->6078|10487->6108|10512->6110|10558->6119|10585->6123|10632->6133|10653->6144|10696->6164|10736->6167|10776->6183|10816->6191|10894->6233|10910->6239|11202->6509|11318->6588|11332->6592|11428->6665|11525->6726|11541->6732|11831->7000|11909->7042|11925->7048|12184->7285|12262->7327|12278->7333|12527->7560|12636->7633|12652->7639|12915->7880|12993->7922|13009->7928|13255->8152|13333->8194|13349->8200|13649->8478|13758->8551|13774->8557|14027->8788|14105->8830|14121->8836|14372->9065|14516->9173|14532->9179|14793->9418|14871->9460|14887->9466|15151->9708|15229->9750|15245->9756|15558->10047|15667->10120|15683->10126|15935->10356|16013->10398|16029->10404|16273->10626|16351->10668|16367->10674|16621->10906|16737->10985|16751->10989|16849->11064|16947->11126|16963->11132|17222->11369|17300->11411|17316->11417|17576->11655|17654->11697|17670->11703|17928->11939|18037->12012|18053->12018|18317->12260|18395->12302|18411->12308|18666->12541|18744->12583|18760->12589|19061->12868|19160->12931|19231->12992|19272->12994|19346->13032|19416->13092|19457->13094|19533->13133|19547->13137|19632->13198|19699->13246|19739->13248|19783->13256|19990->13439|20029->13446|20064->13463|20078->13468|20118->13469|20160->13475|20238->13543|20279->13545|20353->13583|20423->13643|20464->13645|20540->13684|20554->13688|20644->13754|20712->13803|20752->13805|20796->13813|21003->13996|21042->14003|21078->14021|21092->14026|21132->14027|21175->14034|21357->14192|21395->14198|21432->14203|21659->14393|21673->14397|21770->14471|21975->14639|21989->14643|22117->14747|22222->14815|22236->14819|22328->14888|22391->14914|22412->14925|22443->14933|22505->14958|22519->14962|22608->15028|22671->15054|22692->15065|22731->15081|22793->15106|22807->15110|22899->15179|22962->15205|22983->15216|23025->15235|23195->15368|23209->15372|23310->15450|23757->15860|23771->15864|23856->15925|24012->16043|24027->16047|24113->16109|24188->16152|24227->16155|24255->16164|24295->16166|24370->16204|24386->16210|24449->16250|24494->16263
                    LINES: 26->1|40->1|41->8|44->11|44->11|44->11|46->13|46->13|46->13|49->16|49->16|49->16|52->19|52->19|55->22|55->22|55->22|56->23|56->23|56->23|57->24|57->24|57->24|57->24|59->26|59->26|59->26|63->30|63->30|63->30|64->31|64->31|64->31|65->32|72->39|73->40|77->44|79->46|83->50|84->51|88->55|94->61|94->61|100->67|103->70|103->70|109->76|109->76|110->77|110->77|110->77|110->77|110->77|110->77|110->77|110->77|110->77|111->78|114->81|114->81|120->87|120->87|121->88|121->88|121->88|121->88|121->88|121->88|121->88|121->88|121->88|122->89|127->94|127->94|133->100|136->103|136->103|142->109|145->112|145->112|151->118|151->118|152->119|152->119|152->119|152->119|152->119|152->119|152->119|152->119|152->119|153->120|158->125|158->125|164->131|164->131|165->132|165->132|165->132|165->132|165->132|165->132|165->132|165->132|165->132|166->133|169->136|169->136|175->142|175->142|176->143|176->143|176->143|176->143|176->143|176->143|176->143|176->143|176->143|177->144|180->147|180->147|186->153|186->153|187->154|187->154|187->154|187->154|187->154|187->154|187->154|187->154|187->154|188->155|193->160|193->160|199->166|199->166|200->167|200->167|200->167|200->167|200->167|200->167|200->167|200->167|200->167|201->168|204->171|204->171|211->178|217->184|217->184|217->184|220->187|220->187|227->194|230->197|230->197|236->203|239->206|239->206|245->212|250->217|250->217|256->223|259->226|259->226|265->232|268->235|268->235|275->242|280->247|280->247|286->253|289->256|289->256|295->262|302->269|302->269|308->275|311->278|311->278|317->284|320->287|320->287|327->294|332->299|332->299|338->305|341->308|341->308|347->314|350->317|350->317|356->323|362->329|362->329|362->329|365->332|365->332|371->338|374->341|374->341|380->347|383->350|383->350|389->356|394->361|394->361|400->367|403->370|403->370|409->376|412->379|412->379|419->386|424->391|424->391|424->391|426->393|426->393|426->393|427->394|427->394|427->394|428->395|428->395|429->396|429->396|430->397|432->399|432->399|432->399|433->400|433->400|433->400|435->402|435->402|435->402|436->403|436->403|436->403|437->404|437->404|438->405|438->405|439->406|441->408|441->408|441->408|442->409|442->409|443->410|444->411|450->417|450->417|450->417|455->422|455->422|455->422|457->424|457->424|457->424|458->425|458->425|458->425|459->426|459->426|459->426|460->427|460->427|460->427|461->428|461->428|461->428|462->429|462->429|462->429|468->435|468->435|468->435|480->447|480->447|480->447|481->448|481->448|481->448|485->452|486->453|488->455|488->455|489->456|489->456|489->456|490->457
                    -- GENERATED --
                */
            