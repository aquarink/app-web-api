
package views.html.receive

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
object inquiry extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.TransactionInquiry],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transactionForm: Form[models.TransactionInquiry]):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import helper._

import helper.twitterBootstrap._

import partial._

def /*31.2*/formReceive/*31.13*/(transactionForm: Form[models.TransactionInquiry], isEditable: scala.Boolean):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*31.94*/("""
	"""),_display_(Seq[Any](/*32.3*/defining(transactionForm.get.transaction)/*32.44*/ { transaction =>_display_(Seq[Any](format.raw/*32.61*/("""
	"""),_display_(Seq[Any](/*33.3*/helper/*33.9*/.form(action = routes.Receive.process(transaction.idToken), 'id -> "receive-form", 'autocomplete -> "off", 'class -> "row form-horizontal", Symbol("data-url-verify") -> routes.Receive.verify(transaction.idToken).url, Symbol("data-url-validate") -> routes.Receive.validate(transaction.idToken).url, Symbol("data-url-receipt") -> routes.Receive.receipt(transaction.idToken).url, Symbol("data-remote") -> "true")/*33.418*/ {_display_(Seq[Any](format.raw/*33.420*/("""
	<input type="hidden" name="idToken" value=""""),_display_(Seq[Any](/*34.46*/transactionForm("idToken")/*34.72*/.value)),format.raw/*34.78*/(""""/>
	<input type="hidden" name="verifyId" value=""""),_display_(Seq[Any](/*35.47*/transactionForm("verifyId")/*35.74*/.value)),format.raw/*35.80*/(""""/>
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*37.13*/doku/*37.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG038","Transaction information"))),format.raw/*37.95*/("""</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*40.7*/helper/*40.13*/.inputText(
						transactionForm("transaction.channel.name")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					))),format.raw/*47.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*50.7*/helper/*50.13*/.input(
						transactionForm("transaction.statusText")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					)/*57.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*57.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*58.31*/id)),format.raw/*58.33*/("""" name=""""),_display_(Seq[Any](/*58.42*/name)),format.raw/*58.46*/("""" value=""""),_display_(Seq[Any](/*58.56*/transaction/*58.67*/.statusText)),format.raw/*58.78*/("""" """),_display_(Seq[Any](/*58.81*/toHtmlArgs(args))),format.raw/*58.97*/(""">
					""")))})),format.raw/*59.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*62.7*/helper/*62.13*/.input(
						transactionForm("transaction.agent.fullName")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG247","Operator")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					)/*69.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*69.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*70.31*/id)),format.raw/*70.33*/("""" name=""""),_display_(Seq[Any](/*70.42*/name)),format.raw/*70.46*/("""" value=""""),_display_(Seq[Any](/*70.56*/transaction/*70.67*/.agent.fullName)),format.raw/*70.82*/("""" """),_display_(Seq[Any](/*70.85*/toHtmlArgs(args))),format.raw/*70.101*/(""">
					""")))})),format.raw/*71.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*76.7*/helper/*76.13*/.inputText(
						transactionForm("transaction.beneficiary.country.name")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					))),format.raw/*83.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*86.7*/helper/*86.13*/.inputText(
						transactionForm("transaction.beneficiaryCity")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
					))),format.raw/*91.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*94.7*/helper/*94.13*/.input(
						transactionForm("transaction.forexReference.rate")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required amount"
					)/*101.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*101.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*102.31*/id)),format.raw/*102.33*/("""" name=""""),_display_(Seq[Any](/*102.42*/name)),format.raw/*102.46*/("""" value=""""),_display_(Seq[Any](/*102.56*/transaction/*102.67*/.forexReference.rateFormat)),format.raw/*102.93*/("""" """),_display_(Seq[Any](/*102.96*/toHtmlArgs(args))),format.raw/*102.112*/(""">
					""")))})),format.raw/*103.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*108.7*/helper/*108.13*/.input(
						transactionForm("transaction.senderAmount")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required amount input-block-level"
						, '_help -> ""
					)/*116.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*116.36*/("""
			            <div class="input-append">
							<input type="text" id=""""),_display_(Seq[Any](/*118.32*/id)),format.raw/*118.34*/("""" name=""""),_display_(Seq[Any](/*118.43*/name)),format.raw/*118.47*/("""" value=""""),_display_(Seq[Any](/*118.57*/transaction/*118.68*/.senderAmount.setScale(6))),format.raw/*118.93*/("""" """),_display_(Seq[Any](/*118.96*/toHtmlArgs(args))),format.raw/*118.112*/(""">
							<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*119.67*/transactionForm("transaction.senderCurrency.code")/*119.117*/.value)),format.raw/*119.123*/("""</span>
						</div>
					""")))})),format.raw/*121.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*124.7*/helper/*124.13*/.input(
						transactionForm("transaction.beneficiaryAmount")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive")
						, '_showConstraints -> false
						, 'readOnly -> true
						, 'class -> "required amount input-block-level"
					)/*130.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*130.36*/("""
			            <div class="input-append">
							<input type="text" id=""""),_display_(Seq[Any](/*132.32*/id)),format.raw/*132.34*/("""" name=""""),_display_(Seq[Any](/*132.43*/name)),format.raw/*132.47*/("""" value=""""),_display_(Seq[Any](/*132.57*/transaction/*132.68*/.beneficiaryAmount.setScale(6))),format.raw/*132.98*/("""" """),_display_(Seq[Any](/*132.101*/toHtmlArgs(args))),format.raw/*132.117*/(""">
							<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*133.67*/transactionForm("transaction.beneficiaryCurrency.code")/*133.122*/.value)),format.raw/*133.128*/("""</span>
						</div>
					""")))})),format.raw/*135.7*/("""
				</div>
				<div class="control-group span4 hide">
					"""),_display_(Seq[Any](/*138.7*/helper/*138.13*/.input(
						transactionForm("transaction.feesTotal")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					)/*145.7*/  { (id, name, value, args) =>_display_(Seq[Any](format.raw/*145.37*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*146.31*/id)),format.raw/*146.33*/("""" name=""""),_display_(Seq[Any](/*146.42*/name)),format.raw/*146.46*/("""" value=""""),_display_(Seq[Any](/*146.56*/transaction/*146.67*/.feesTotal)),format.raw/*146.77*/("""" """),_display_(Seq[Any](/*146.80*/toHtmlArgs(args))),format.raw/*146.96*/(""">
					""")))})),format.raw/*147.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span8">
					"""),_display_(Seq[Any](/*152.7*/helper/*152.13*/.inputText(
						transactionForm("transaction.senderNote")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, 'class -> "input-xlarge"
						, 'style -> "width: 95%"
					))),format.raw/*159.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>Sender information</legend>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*168.7*/helper/*168.13*/.inputText(
						transactionForm("transaction.sender.idToken")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					))),format.raw/*175.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*178.7*/helper/*178.13*/.inputText(
						transactionForm("transaction.sender.firstName")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					))),format.raw/*185.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*188.7*/helper/*188.13*/.inputText(
						transactionForm("transaction.sender.lastName")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> ""
						, 'class -> ""
					))),format.raw/*195.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*200.7*/helper/*200.13*/.inputText(
						transactionForm("transaction.sender.phoneNumber")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					))),format.raw/*207.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*210.7*/helper/*210.13*/.inputText(
						transactionForm("transaction.sender.country.name")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
						, '_class -> "required"
						, 'class -> "required"
					))),format.raw/*217.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*220.7*/helper/*220.13*/.inputText(
						transactionForm("transaction.sender.address")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address")
						, '_showConstraints -> false
						, 'disabled -> "disabled"
					))),format.raw/*225.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>Receiver information</legend>
				<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*234.7*/helper/*234.13*/.inputText(
						transactionForm("transaction.beneficiary.idToken")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID")
						, '_showConstraints -> false
						, '_class -> "required"
						, 'class -> "required"
						, Symbol(isEditable match { case true => "readOnly" case _ => "readOnly"}) -> true
					))),format.raw/*241.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*244.7*/helper/*244.13*/.inputText(
						transactionForm("transaction.beneficiary.firstName")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name")
						, '_showConstraints -> false
						, '_class -> "required"
						, 'class -> "required"
						, Symbol(isEditable match { case true => "readOnly" case _ => "readOnly"}) -> true
					))),format.raw/*251.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*254.7*/helper/*254.13*/.inputText(
						transactionForm("transaction.beneficiary.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'_class -> "",
						'class -> ""
						, Symbol(isEditable match { case true => "readOnly" case _ => "readOnly"}) -> true
					))),format.raw/*261.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*266.7*/helper/*266.13*/.inputText(
						transactionForm("transaction.beneficiary.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
						'_showConstraints -> false,
						'_class -> "required",
						'class -> "required"
						, Symbol(isEditable match { case true => "readOnly" case _ => "readOnly"}) -> true
					))),format.raw/*273.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*276.7*/helper/*276.13*/.inputText(
						transactionForm("transaction.beneficiary.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'class -> "disabled"
						, Symbol(isEditable match { case true => "readOnly" case _ => "readOnly"}) -> true
					))),format.raw/*282.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*285.7*/helper/*285.13*/.select(
						transactionForm("transaction.beneficiary.country.code")
						, options(models.Country.options)
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")
						, '_showConstraints -> false
						, '_class -> "required"
						, 'class -> "disabled"
						, 'disabled -> "disabled"
						, Symbol(isEditable match { case true => "readOnly" case _ => "readOnly"}) -> true
					))),format.raw/*294.7*/("""
					<input type="hidden" name="transaction.beneficiary.country.code" value=""""),_display_(Seq[Any](/*295.79*/transaction/*295.90*/.beneficiary.country.code)),format.raw/*295.115*/(""""/>
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*300.7*/helper/*300.13*/.select(
						transactionForm("transaction.beneficiary.personalIdType")
						, options(models.Customer.optionsPersonalIdType)
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID type")
						, '_showConstraints -> false
						, '_class -> "required"
						, 'class -> ""
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*308.7*/("""
					<input type="hidden" name="transaction.beneficiary.personalIdType" value=""""),_display_(Seq[Any](/*309.81*/transaction/*309.92*/.beneficiary.personalIdType)),format.raw/*309.119*/(""""/>
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*312.7*/helper/*312.13*/.inputText(
						transactionForm("transaction.beneficiary.personalId"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID Number"), 
						'_showConstraints -> false,
						'_class -> "required",
						'class -> "required"
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*319.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*322.7*/helper/*322.13*/.select(
						transactionForm("sender.gender"),
						options(models.Customer.optionsGender),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG117","Gender"),
						'_showConstraints -> false
					))),format.raw/*327.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*332.7*/helper/*332.13*/.inputDate(
						transactionForm("transaction.beneficiary.personalIdIssueDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue date"),
						'_showConstraints -> false
						, 'maxlength -> 10
						, 'min -> "1900-01-01"
						, 'max -> "2099-12-12"
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*340.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*343.7*/helper/*343.13*/.inputDate(
						transactionForm("transaction.beneficiary.personalIdExpireDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired date"),
						'_showConstraints -> false
						, 'maxlength -> 10
						, 'min -> "1900-01-01"
						, 'max -> "2099-12-12"
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*351.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*354.7*/helper/*354.13*/.select(
						transactionForm("transaction.beneficiary.personalIdCountry.code"),
						options(models.Country.options),
						'_default -> "-- Choose a country --",
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG246","Issuing country"),
						'_showConstraints -> false
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*361.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*366.7*/helper/*366.13*/.inputText(
						transactionForm("transaction.beneficiary.occupation"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"),
						'_showConstraints -> false,
						'class -> ""
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*372.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*375.7*/helper/*375.13*/.inputText(
						transactionForm("transaction.beneficiary.cityName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
						'_showConstraints -> false,
						'class -> ""
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*381.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*384.7*/helper/*384.13*/.inputDate(
						transactionForm("transaction.beneficiary.birthDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"),
						'_showConstraints -> false,
						'_class -> "required",
						'class -> "required"
						, 'maxlength -> 10
						, 'min -> "1900-01-01"
						, 'max -> "2099-12-12"
						, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
					))),format.raw/*394.7*/("""
				</div>
			</div>
		</fieldset>
		<div class="span12">
			"""),_display_(Seq[Any](/*399.5*/if(isEditable)/*399.19*/ {_display_(Seq[Any](format.raw/*399.21*/("""
				<div class="form-actions">
					<input type="hidden" name="validationId" value=""/>
					<input type="hidden" name="refNum" value=""/>
					<input type="reset" class="btn" value='"""),_display_(Seq[Any](/*403.46*/doku/*403.50*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*403.110*/("""'/>
					<button class="btn btn-primary btn-change">"""),_display_(Seq[Any](/*404.50*/doku/*404.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*404.116*/(""" <i class="icon-arrow-right"></i></button>
				</div>
			""")))}/*406.6*/else/*406.11*/{_display_(Seq[Any](format.raw/*406.12*/("""
				"""),_display_(Seq[Any](/*407.6*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG450","You don't have privileges to process this transaction")))),format.raw/*407.135*/("""
			""")))})),format.raw/*408.5*/("""
		</div>

		<div class="modal fade hide" id="receive-dialog">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">×</button>
		        <h3>"""),_display_(Seq[Any](/*414.16*/doku/*414.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG451","Receive money confirmation"))),format.raw/*414.101*/("""</h3>
		    </div>
			<div class="modal-body">
				<div class="control-groups">
					<p>"""),_display_(Seq[Any](/*418.10*/doku/*418.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG452","Are you sure you want to receive this transaction?"))),format.raw/*418.119*/("""</p>
					<dl class="dl-horizontal dl-aligned">
						<dt><label>"""),_display_(Seq[Any](/*420.19*/doku/*420.23*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*420.92*/("""</label></dt>
						<dd>"""),_display_(Seq[Any](/*421.12*/transaction/*421.23*/.idToken)),format.raw/*421.31*/("""</dd>
						<dt><label>"""),_display_(Seq[Any](/*422.19*/doku/*422.23*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*422.91*/("""</label></dt>
						<dd>"""),_display_(Seq[Any](/*423.12*/transaction/*423.23*/.beneficiary.fullName)),format.raw/*423.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*424.12*/doku/*424.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount"))),format.raw/*424.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*425.12*/transaction/*425.23*/.beneficiaryAmountFormat)),format.raw/*425.47*/("""</dd>
					</dl>
				</div>
				<hr/>
				<div class="control-groups">
					<p style="text-align: center;"><center>
						<label>"""),_display_(Seq[Any](/*431.15*/doku/*431.19*/.kirimdoku.util.MultiLanguage.getLanguage("LANG242","Please input PIN below:"))),format.raw/*431.97*/("""</label>
						<div class="control-group">
							<div class="input">
								<input id="auth1" type="password" name="auth1" class="required" minlength="4" maxlength="4" placeholder="" style="text-align: center;"/>
							</div>
						</div>
					<center></p>
				</div>
				<div class="container-info">
				</div>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*443.51*/doku/*443.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*443.116*/("""</a>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*444.52*/doku/*444.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*444.118*/("""</button>
			</div>
		</div>
		
		<div class="modal fade hide" id="receive-dialog-refnum">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">×</button>
		        <h3>"""),_display_(Seq[Any](/*451.16*/doku/*451.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG453","Collecting (Required Ref Num)"))),format.raw/*451.104*/("""</h3>
		        <input type="hidden" id="verifyRefNum" name="verifyRefNum" value=""/>
		    </div>
			<div class="modal-body">
				<div class="control-groups">
					<p>"""),_display_(Seq[Any](/*456.10*/doku/*456.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG454","Summary Transaction"))),format.raw/*456.88*/("""</p>
					<dl class="dl-horizontal dl-aligned">
						<dt><label>"""),_display_(Seq[Any](/*458.19*/doku/*458.23*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*458.92*/("""</label></dt>
						<dd>"""),_display_(Seq[Any](/*459.12*/transaction/*459.23*/.idToken)),format.raw/*459.31*/("""</dd>
						<dt><label>"""),_display_(Seq[Any](/*460.19*/doku/*460.23*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*460.91*/("""</label></dt>
						<dd>"""),_display_(Seq[Any](/*461.12*/transaction/*461.23*/.beneficiary.fullName)),format.raw/*461.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*462.12*/doku/*462.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount"))),format.raw/*462.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*463.12*/transaction/*463.23*/.beneficiaryAmountFormat)),format.raw/*463.47*/("""</dd>
					</dl><br>
				</div>
				<hr/>
				<div class="control-groups">
					<p style="text-align: center;"><center><br>
						<label>"""),_display_(Seq[Any](/*469.15*/doku/*469.19*/.kirimdoku.util.MultiLanguage.getLanguage("LANG455","Input Reference Number below:"))),format.raw/*469.103*/("""</label>
						<div class="control-group">
							<div class="input">
								<input id="receiveTrxId" type="text" name="receiveTrxId" maxlength="50" placeholder="" autofocus="autofocus" style="text-align: center;"/>
							</div>
						</div>
					<center></p>
				</div><br>&nbsp;<br>
				<div class="container-info">
				</div>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*481.51*/doku/*481.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*481.116*/("""</a>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*482.52*/doku/*482.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*482.118*/("""</button>
			</div>
		</div>
	""")))})),format.raw/*485.3*/("""
	""")))})),format.raw/*486.3*/("""
""")))};
Seq[Any](format.raw/*1.65*/("""
"""),format.raw/*7.1*/("""
"""),_display_(Seq[Any](/*8.2*/layout("Transaction Receive")/*8.31*/{_display_(Seq[Any](format.raw/*8.32*/("""

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""

<div class="container-transaction-detail-inner">
"""),_display_(Seq[Any](/*13.2*/if(err)/*13.9*/ {_display_(Seq[Any](format.raw/*13.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*16.36*/err)),format.raw/*16.39*/("""</div>
		</div>
	</div>
""")))}/*19.3*/else/*19.8*/{_display_(Seq[Any](format.raw/*19.9*/("""
	"""),_display_(Seq[Any](/*20.3*/dynamicOr("receiveTransaction", String.valueOf(transactionForm.get.transaction.id))/*20.86*/ {_display_(Seq[Any](format.raw/*20.88*/("""
		"""),_display_(Seq[Any](/*21.4*/formReceive(transactionForm, true))),format.raw/*21.38*/("""
	""")))}/*22.3*/ {_display_(Seq[Any](format.raw/*22.5*/("""
		"""),_display_(Seq[Any](/*23.4*/formReceive(transactionForm, false))),format.raw/*23.39*/("""
	""")))})),format.raw/*24.3*/("""
</div>
""")))})),format.raw/*26.2*/("""
""")))}/*27.2*/ {_display_(Seq[Any](format.raw/*27.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*28.38*/routes/*28.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*28.84*/("""'></script>
""")))})),format.raw/*29.2*/("""

"""),format.raw/*487.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.TransactionInquiry]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.TransactionInquiry]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/receive/inquiry.scala.html
                    HASH: b3769e5c9f2d7572fd5b3e86a4a78f8c961ccac2
                    MATRIX: 765->1|1062->755|1082->766|1227->847|1265->850|1315->891|1370->908|1408->911|1422->917|1841->1326|1882->1328|1964->1374|1999->1400|2027->1406|2113->1456|2149->1483|2177->1489|2257->1533|2270->1537|2370->1615|2466->1676|2481->1682|2782->1962|2859->2004|2874->2010|3155->2283|3222->2312|3289->2343|3313->2345|3358->2354|3384->2358|3430->2368|3450->2379|3483->2390|3522->2393|3560->2409|3599->2417|3676->2459|3691->2465|3978->2744|4045->2773|4112->2804|4136->2806|4181->2815|4207->2819|4253->2829|4273->2840|4310->2855|4349->2858|4388->2874|4427->2882|4549->2969|4564->2975|4885->3275|4976->3331|4991->3337|5241->3566|5332->3622|5347->3628|5652->3924|5720->3953|5788->3984|5813->3986|5859->3995|5886->3999|5933->4009|5954->4020|6003->4046|6043->4049|6083->4065|6123->4073|6246->4160|6262->4166|6600->4495|6668->4524|6779->4598|6804->4600|6850->4609|6877->4613|6924->4623|6945->4634|6993->4659|7033->4662|7073->4678|7178->4746|7239->4796|7269->4802|7328->4829|7420->4885|7436->4891|7725->5171|7793->5200|7904->5274|7929->5276|7975->5285|8002->5289|8049->5299|8070->5310|8123->5340|8164->5343|8204->5359|8309->5427|8375->5482|8405->5488|8464->5515|8561->5576|8577->5582|8855->5851|8924->5881|8992->5912|9017->5914|9063->5923|9090->5927|9137->5937|9158->5948|9191->5958|9231->5961|9270->5977|9310->5985|9433->6072|9449->6078|9762->6369|9969->6540|9985->6546|10293->6832|10385->6888|10401->6894|10710->7181|10802->7237|10818->7243|11109->7512|11232->7599|11248->7605|11561->7896|11653->7952|11669->7958|11982->8249|12074->8305|12090->8311|12335->8534|12545->8708|12561->8714|12931->9062|13023->9118|13039->9124|13410->9473|13502->9529|13518->9535|13867->9862|13990->9949|14006->9955|14377->10304|14469->10360|14485->10366|14818->10677|14910->10733|14926->10739|15370->11161|15486->11240|15507->11251|15556->11276|15682->11366|15698->11372|16123->11775|16241->11856|16262->11867|16313->11894|16408->11953|16424->11959|16800->12313|16878->12355|16894->12361|17134->12579|17257->12666|17273->12672|17684->13061|17776->13117|17792->13123|18206->13515|18298->13571|18314->13577|18731->13972|18854->14059|18870->14065|19209->14382|19301->14438|19317->14444|19648->14753|19740->14809|19756->14815|20217->15254|20316->15317|20340->15331|20381->15333|20603->15518|20617->15522|20701->15582|20791->15635|20805->15639|20891->15701|20968->15760|20982->15765|21022->15766|21064->15772|21217->15901|21254->15906|21480->16095|21494->16099|21599->16180|21725->16269|21739->16273|21868->16378|21971->16444|21985->16448|22077->16517|22139->16542|22160->16553|22191->16561|22252->16585|22266->16589|22357->16657|22419->16682|22440->16693|22484->16714|22538->16731|22552->16735|22644->16804|22698->16821|22719->16832|22766->16856|22933->16986|22947->16990|23048->17068|23490->17473|23504->17477|23589->17538|23682->17594|23696->17598|23782->17660|24036->17877|24050->17881|24158->17965|24364->18134|24378->18138|24475->18212|24578->18278|24592->18282|24684->18351|24746->18376|24767->18387|24798->18395|24859->18419|24873->18423|24964->18491|25026->18516|25047->18527|25091->18548|25145->18565|25159->18569|25251->18638|25305->18655|25326->18666|25373->18690|25548->18828|25562->18832|25670->18916|26128->19337|26142->19341|26227->19402|26320->19458|26334->19462|26420->19524|26483->19555|26518->19558|26559->64|26586->239|26622->241|26659->270|26697->271|26735->274|26750->281|26786->295|26873->347|26888->354|26928->356|27042->434|27067->437|27110->463|27122->468|27160->469|27198->472|27290->555|27330->557|27369->561|27425->595|27446->598|27485->600|27524->604|27581->639|27615->642|27655->651|27675->653|27714->655|27788->693|27803->699|27865->739|27909->752|27939->19560
                    LINES: 26->1|37->31|37->31|39->31|40->32|40->32|40->32|41->33|41->33|41->33|41->33|42->34|42->34|42->34|43->35|43->35|43->35|45->37|45->37|45->37|48->40|48->40|55->47|58->50|58->50|65->57|65->57|66->58|66->58|66->58|66->58|66->58|66->58|66->58|66->58|66->58|67->59|70->62|70->62|77->69|77->69|78->70|78->70|78->70|78->70|78->70|78->70|78->70|78->70|78->70|79->71|84->76|84->76|91->83|94->86|94->86|99->91|102->94|102->94|109->101|109->101|110->102|110->102|110->102|110->102|110->102|110->102|110->102|110->102|110->102|111->103|116->108|116->108|124->116|124->116|126->118|126->118|126->118|126->118|126->118|126->118|126->118|126->118|126->118|127->119|127->119|127->119|129->121|132->124|132->124|138->130|138->130|140->132|140->132|140->132|140->132|140->132|140->132|140->132|140->132|140->132|141->133|141->133|141->133|143->135|146->138|146->138|153->145|153->145|154->146|154->146|154->146|154->146|154->146|154->146|154->146|154->146|154->146|155->147|160->152|160->152|167->159|176->168|176->168|183->175|186->178|186->178|193->185|196->188|196->188|203->195|208->200|208->200|215->207|218->210|218->210|225->217|228->220|228->220|233->225|242->234|242->234|249->241|252->244|252->244|259->251|262->254|262->254|269->261|274->266|274->266|281->273|284->276|284->276|290->282|293->285|293->285|302->294|303->295|303->295|303->295|308->300|308->300|316->308|317->309|317->309|317->309|320->312|320->312|327->319|330->322|330->322|335->327|340->332|340->332|348->340|351->343|351->343|359->351|362->354|362->354|369->361|374->366|374->366|380->372|383->375|383->375|389->381|392->384|392->384|402->394|407->399|407->399|407->399|411->403|411->403|411->403|412->404|412->404|412->404|414->406|414->406|414->406|415->407|415->407|416->408|422->414|422->414|422->414|426->418|426->418|426->418|428->420|428->420|428->420|429->421|429->421|429->421|430->422|430->422|430->422|431->423|431->423|431->423|432->424|432->424|432->424|433->425|433->425|433->425|439->431|439->431|439->431|451->443|451->443|451->443|452->444|452->444|452->444|459->451|459->451|459->451|464->456|464->456|464->456|466->458|466->458|466->458|467->459|467->459|467->459|468->460|468->460|468->460|469->461|469->461|469->461|470->462|470->462|470->462|471->463|471->463|471->463|477->469|477->469|477->469|489->481|489->481|489->481|490->482|490->482|490->482|493->485|494->486|496->1|497->7|498->8|498->8|498->8|500->10|500->10|500->10|503->13|503->13|503->13|506->16|506->16|509->19|509->19|509->19|510->20|510->20|510->20|511->21|511->21|512->22|512->22|513->23|513->23|514->24|516->26|517->27|517->27|518->28|518->28|518->28|519->29|521->487
                    -- GENERATED --
                */
            