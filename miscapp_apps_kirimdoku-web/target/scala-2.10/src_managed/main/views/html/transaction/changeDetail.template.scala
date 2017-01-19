
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
object changeDetail extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transactionForm: Form[models.Transaction]):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import helper._

import helper.twitterBootstrap._

import partial._

def /*343.2*/formReceiver/*343.14*/(transaction: models.Transaction, isEditable: scala.Boolean):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*343.78*/("""
<fieldset class="span12 form-change-customer" data-prefix="beneficiary">
	<legend>"""),_display_(Seq[Any](/*345.11*/doku/*345.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG058","Receiver information"))),format.raw/*345.90*/("""</legend>
		<div class="row">
		<div class="control-group span4">
			"""),_display_(Seq[Any](/*348.5*/helper/*348.11*/.inputText(
				transactionForm("beneficiary.idToken"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
				'_showConstraints -> false,
				'class -> ""
				, Symbol(isEditable match { case true => "noreadOnly" case _ => "readOnly"}) -> "true"
			))),format.raw/*354.5*/("""
		</div>
		<div class="control-group span4">
			"""),_display_(Seq[Any](/*357.5*/helper/*357.11*/.inputText(
				transactionForm("beneficiary.firstName"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
				'_showConstraints -> false,
				'_class -> "required",
				'class -> "required"
				, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
			))),format.raw/*364.5*/("""
		</div>
		<div class="control-group span4">
			"""),_display_(Seq[Any](/*367.5*/helper/*367.11*/.inputText(
				transactionForm("beneficiary.lastName"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
				'_showConstraints -> false,
				'_class -> "required",
				'class -> "required"
				, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
			))),format.raw/*374.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="control-group span4">
			"""),_display_(Seq[Any](/*379.5*/helper/*379.11*/.inputText(
				transactionForm("beneficiary.phoneNumber"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
				'_showConstraints -> false,
				'_class -> "required",
				'class -> "required"
				, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
			))),format.raw/*386.5*/("""
		</div>
		<div class="control-group span4">
			"""),_display_(Seq[Any](/*389.5*/helper/*389.11*/.inputText(
				transactionForm("beneficiary.address"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
				'_showConstraints -> false,
				'_class -> "",
				'class -> "disabled"
				, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
			))),format.raw/*396.5*/("""
		</div>
		<div class="control-group span4">
			"""),_display_(Seq[Any](/*399.5*/helper/*399.11*/.select(
				transactionForm("beneficiary.country.code"),
				options(models.Country.options),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
				'_showConstraints -> false,
				'_class -> "required",
				'class -> "disabled"
				, Symbol(isEditable match { case true => "nodisabled" case _ => "disabled"}) -> "disabled"
			))),format.raw/*407.5*/("""
		</div>
	</div>
</fieldset>
<div class="span12">
	"""),_display_(Seq[Any](/*412.3*/if(isEditable)/*412.17*/ {_display_(Seq[Any](format.raw/*412.19*/("""
		<div class="form-actions">
				<input type="reset" class="btn" value='"""),_display_(Seq[Any](/*414.45*/doku/*414.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*414.109*/("""'/>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*415.52*/doku/*415.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG260","Change"))),format.raw/*415.117*/(""" <i class="icon-white icon-arrow-right"></i></button>
		</div>
	""")))}/*417.4*/else/*417.9*/{_display_(Seq[Any](format.raw/*417.10*/("""
		"""),_display_(Seq[Any](/*418.4*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG259","Cannot change transaction, only registered sender operator can change this transaction")))),format.raw/*418.166*/("""
	""")))})),format.raw/*419.3*/("""
</div>

<div class="modal fade hide" id="change-dialog">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>"""),_display_(Seq[Any](/*425.14*/doku/*425.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG258","Change confirmation"))),format.raw/*425.92*/("""</h3>
    </div>
	<form method="GET" class="form-vertical" data-remote="true">
		<div class="modal-body">
			<div class="control-groups">
				<p class="ban">"""),_display_(Seq[Any](/*430.21*/doku/*430.25*/.kirimdoku.util.MultiLanguage.getLanguage("LANG257","Are you sure you want to change this transaction?"))),format.raw/*430.129*/("""</p>
				<dl class="dl-horizontal dl-aligned">
					<dt><label>"""),_display_(Seq[Any](/*432.18*/doku/*432.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*432.91*/("""</label></dt>
					<dd>"""),_display_(Seq[Any](/*433.11*/transaction/*433.22*/.idToken)),format.raw/*433.30*/("""</dd>
					<dt><label>"""),_display_(Seq[Any](/*434.18*/doku/*434.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*434.88*/("""</label></dt>
					<dd>"""),_display_(Seq[Any](/*435.11*/transaction/*435.22*/.sender.fullName)),format.raw/*435.38*/("""</dd>
					<dt><label>"""),_display_(Seq[Any](/*436.18*/doku/*436.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*436.91*/("""</label></dt>
					<dd>"""),_display_(Seq[Any](/*437.11*/transaction/*437.22*/.senderAmountFormat)),format.raw/*437.41*/("""</dd>
				</dl>
			</div>
			<hr/>
			<div class="control-groups">
				<center style="text-align: center;">
					<label>"""),_display_(Seq[Any](/*443.14*/doku/*443.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG242","Please input PIN below:"))),format.raw/*443.96*/("""</label>
					<div class="control-group">
						<div class="input">
							<input id="auth1" type="password" name="auth1" class="required" minlength="4" maxlength="4" placeholder="PIN" style="text-align: center;"/>
						</div>
					</div>
				</center>
			</div>
			<p class="container-info"></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*454.50*/doku/*454.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*454.115*/("""</a>
			<button type="submit" class="btn btn-primary" data-disable-with="Processing..." data-enable-with="Proceed">"""),_display_(Seq[Any](/*455.112*/doku/*455.116*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*455.178*/("""</button>
		</div>
	</form>
</div>
""")))};
Seq[Any](format.raw/*1.58*/("""
"""),format.raw/*7.1*/("""
"""),_display_(Seq[Any](/*8.2*/layout("Transaction change")/*8.30*/{_display_(Seq[Any](format.raw/*8.31*/("""

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""

<div class="container-transaction-detail-inner">
"""),_display_(Seq[Any](/*13.2*/if(err)/*13.9*/ {_display_(Seq[Any](format.raw/*13.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*16.36*/err)),format.raw/*16.39*/("""</div>
		</div>
	</div>
""")))}/*19.3*/else/*19.8*/{_display_(Seq[Any](format.raw/*19.9*/("""
	"""),_display_(Seq[Any](/*20.3*/defining(transactionForm.get)/*20.32*/ { transaction =>_display_(Seq[Any](format.raw/*20.49*/("""
	"""),_display_(Seq[Any](/*21.3*/helper/*21.9*/.form(action = routes.Transaction.changeProcess(transaction.idToken), 'id -> "change-form", 'class -> "row form-horizontal", Symbol("data-url-receipt") -> routes.Transaction.changeReceipt(transaction.idToken).url, Symbol("data-remote") -> "true")/*21.255*/ {_display_(Seq[Any](format.raw/*21.257*/("""
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*23.13*/doku/*23.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG038","Transaction information"))),format.raw/*23.95*/("""</legend>
			<div class="hide">
				<div class="control-group span4 well">
					<input type="hidden" name="inquiry.idToken"/>
					<input type="hidden" name="user.idToken" value=""""),_display_(Seq[Any](/*27.55*/transaction/*27.66*/.agent.idToken)),format.raw/*27.80*/(""""/>
					<input type="hidden" name="corporate.code" value=""""),_display_(Seq[Any](/*28.57*/transaction/*28.68*/.agent.corporate.code)),format.raw/*28.89*/(""""/>
					"""),_display_(Seq[Any](/*29.7*/select(
						transactionForm("senderCountry.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"), 
						'_default -> "-- Choose a country --",
						'_showConstraints -> false, 'class -> "required"
					))),format.raw/*35.7*/("""
					"""),_display_(Seq[Any](/*36.7*/select(transactionForm("senderCurrency.code"),
						options("" -> "--"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG218","Sender currency"), 
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*40.34*/("""

					"""),_display_(Seq[Any](/*42.7*/select(transactionForm("beneficiaryCurrency.code"),
						options(models.Currency.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG220","Receive currency"),
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*46.34*/("""
					"""),_display_(Seq[Any](/*47.7*/inputText(transactionForm("fundSource"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG219","Source of fund"),
						'class -> "input-large",
						'_help -> "(Optional field)"
					))),format.raw/*51.7*/("""
				</div>
			</div>

			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*57.7*/helper/*57.13*/.inputText(
						transactionForm("channel.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*63.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*66.7*/helper/*66.13*/.input(
						transactionForm("statusText"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*72.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*72.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*73.31*/id)),format.raw/*73.33*/("""" name=""""),_display_(Seq[Any](/*73.42*/name)),format.raw/*73.46*/("""" value=""""),_display_(Seq[Any](/*73.56*/transaction/*73.67*/.statusText)),format.raw/*73.78*/("""" """),_display_(Seq[Any](/*73.81*/toHtmlArgs(args))),format.raw/*73.97*/(""">
					""")))})),format.raw/*74.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*77.7*/helper/*77.13*/.input(
						transactionForm("agent.fullName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG247","Operator"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					)/*83.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*83.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*84.31*/id)),format.raw/*84.33*/("""" name=""""),_display_(Seq[Any](/*84.42*/name)),format.raw/*84.46*/("""" value=""""),_display_(Seq[Any](/*84.56*/transaction/*84.67*/.agent.fullName)),format.raw/*84.82*/("""" """),_display_(Seq[Any](/*84.85*/toHtmlArgs(args))),format.raw/*84.101*/(""">
					""")))})),format.raw/*85.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*90.7*/helper/*90.13*/.inputText(
						transactionForm("beneficiaryCountry.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*96.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*99.7*/helper/*99.13*/.inputText(
						transactionForm("beneficiaryCity"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*105.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*108.7*/helper/*108.13*/.input(
						transactionForm("forexReference.rate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'disabled -> "disabled",
						'class -> "required right rate"
					)/*115.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*115.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*116.31*/id)),format.raw/*116.33*/("""" name=""""),_display_(Seq[Any](/*116.42*/name)),format.raw/*116.46*/("""" value=""""),_display_(Seq[Any](/*116.56*/transaction/*116.67*/.forexReference.rateFormat)),format.raw/*116.93*/("""" """),_display_(Seq[Any](/*116.96*/toHtmlArgs(args))),format.raw/*116.112*/(""">
					""")))})),format.raw/*117.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*122.7*/helper/*122.13*/.input(
						transactionForm("senderAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*128.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*128.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*129.30*/id)),format.raw/*129.32*/("""" name=""""),_display_(Seq[Any](/*129.41*/name)),format.raw/*129.45*/("""" value=""""),_display_(Seq[Any](/*129.55*/transaction/*129.66*/.senderAmountFormat)),format.raw/*129.85*/("""" """),_display_(Seq[Any](/*129.88*/toHtmlArgs(args))),format.raw/*129.104*/(""">
					""")))})),format.raw/*130.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*133.7*/helper/*133.13*/.input(
						transactionForm("beneficiaryAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*139.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*139.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*140.30*/id)),format.raw/*140.32*/("""" name=""""),_display_(Seq[Any](/*140.41*/name)),format.raw/*140.45*/("""" value=""""),_display_(Seq[Any](/*140.55*/transaction/*140.66*/.beneficiaryAmountFormat)),format.raw/*140.90*/("""" """),_display_(Seq[Any](/*140.93*/toHtmlArgs(args))),format.raw/*140.109*/(""">
					""")))})),format.raw/*141.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*144.7*/helper/*144.13*/.input(
						transactionForm("feesTotal"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*150.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*150.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*151.30*/id)),format.raw/*151.32*/("""" name=""""),_display_(Seq[Any](/*151.41*/name)),format.raw/*151.45*/("""" value=""""),_display_(Seq[Any](/*151.55*/transaction/*151.66*/.feesTotalFormat)),format.raw/*151.82*/("""" """),_display_(Seq[Any](/*151.85*/toHtmlArgs(args))),format.raw/*151.101*/(""">
					""")))})),format.raw/*152.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*157.7*/helper/*157.13*/.input(
						transactionForm("collectAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required right"
					)/*163.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*163.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*164.30*/id)),format.raw/*164.32*/("""" name=""""),_display_(Seq[Any](/*164.41*/name)),format.raw/*164.45*/("""" value=""""),_display_(Seq[Any](/*164.55*/transaction/*164.66*/.collectAmountFormat)),format.raw/*164.86*/("""" """),_display_(Seq[Any](/*164.89*/toHtmlArgs(args))),format.raw/*164.105*/(""">
					""")))})),format.raw/*165.7*/("""
				</div>
				<div class="span8">
					"""),_display_(Seq[Any](/*168.7*/helper/*168.13*/.inputText(
						transactionForm("senderNote"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "input-xlarge",
						'style -> "width: 95%"
					))),format.raw/*175.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*181.13*/doku/*181.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG049","Sender information"))),format.raw/*181.90*/("""</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*184.7*/helper/*184.13*/.inputText(
						transactionForm("sender.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "input-xlarge",
						'class -> "required"
					))),format.raw/*191.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*194.7*/helper/*194.13*/.inputText(
						transactionForm("sender.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*200.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*203.7*/helper/*203.13*/.inputText(
						transactionForm("sender.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*209.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*214.7*/helper/*214.13*/.inputText(
						transactionForm("sender.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*220.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*223.7*/helper/*223.13*/.inputText(
						transactionForm("sender.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*229.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*232.7*/helper/*232.13*/.select(
						transactionForm("sender.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*239.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*244.7*/helper/*244.13*/.inputText(
						transactionForm("sender.personalIdType"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID type"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*250.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*253.7*/helper/*253.13*/.inputText(
						transactionForm("sender.personalId"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*259.7*/("""
				</div>
				<div class="span4">
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*266.7*/helper/*266.13*/.inputDate(
						transactionForm("sender.personalIdIssueDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue date"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
						, 'maxlength -> 10
					))),format.raw/*273.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*276.7*/helper/*276.13*/.inputDate(
						transactionForm("sender.personalIdExpireDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired date"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
						, 'maxlength -> 10
					))),format.raw/*283.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*286.7*/helper/*286.13*/.select(
						transactionForm("sender.personalIdCountry.name"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG246","Issuing country"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
					))),format.raw/*293.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*298.7*/helper/*298.13*/.inputText(
						transactionForm("sender.occupation"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*304.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*307.7*/helper/*307.13*/.inputText(
						transactionForm("sender.cityName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> ""
					))),format.raw/*313.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*316.7*/helper/*316.13*/.inputDate(
						transactionForm("sender.birthDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"),
						'_showConstraints -> false,
						'readOnly -> "true",
						'class -> "required"
						, 'maxlength -> 10
					))),format.raw/*323.7*/("""
				</div>
			</div>
		</fieldset>
		
		"""),_display_(Seq[Any](/*328.4*/dynamicOr("editTransaction", String.valueOf(transaction.id))/*328.64*/ {_display_(Seq[Any](format.raw/*328.66*/("""
			"""),_display_(Seq[Any](/*329.5*/formReceiver(transaction, true))),format.raw/*329.36*/("""
		""")))}/*330.4*/ {_display_(Seq[Any](format.raw/*330.6*/("""
			"""),_display_(Seq[Any](/*331.5*/formReceiver(transaction, false))),format.raw/*331.37*/("""
		""")))})),format.raw/*332.4*/("""
		
	""")))})),format.raw/*334.3*/("""
""")))}))))})),format.raw/*335.3*/("""
</div>
""")))}/*337.2*/ {_display_(Seq[Any](format.raw/*337.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*338.38*/routes/*338.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*338.84*/("""'></script>
""")))})),format.raw/*339.2*/("""



"""),format.raw/*459.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.Transaction]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.Transaction]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:32 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/changeDetail.scala.html
                    HASH: 8da1949377e23da9e882d7007416ce9d06affb19
                    MATRIX: 767->1|1058->11307|1080->11319|1209->11383|1330->11467|1344->11471|1442->11546|1548->11616|1564->11622|1873->11909|1959->11959|1975->11965|2324->12292|2410->12342|2426->12348|2773->12673|2886->12750|2902->12756|3255->13087|3341->13137|3357->13143|3693->13457|3779->13507|3795->13513|4183->13879|4272->13932|4296->13946|4337->13948|4448->14022|4462->14026|4546->14086|4638->14141|4652->14145|4737->14206|4821->14272|4834->14277|4874->14278|4914->14282|5100->14444|5135->14447|5350->14625|5364->14629|5461->14703|5656->14861|5670->14865|5798->14969|5899->15033|5913->15037|6005->15106|6066->15130|6087->15141|6118->15149|6178->15172|6192->15176|6281->15242|6342->15266|6363->15277|6402->15293|6462->15316|6476->15320|6568->15389|6629->15413|6650->15424|6692->15443|6850->15564|6864->15568|6965->15646|7387->16031|7401->16035|7486->16096|7640->16212|7655->16216|7741->16278|7816->57|7843->232|7879->234|7915->262|7953->263|7991->266|8006->273|8042->287|8129->339|8144->346|8184->348|8298->426|8323->429|8366->455|8378->460|8416->461|8454->464|8492->493|8547->510|8585->513|8599->519|8855->765|8896->767|8973->808|8986->812|9086->890|9302->1070|9322->1081|9358->1095|9454->1155|9474->1166|9517->1187|9562->1197|9875->1489|9917->1496|10168->1725|10211->1733|10480->1980|10522->1987|10748->2192|10857->2266|10872->2272|11123->2502|11200->2544|11215->2550|11447->2774|11514->2803|11581->2834|11605->2836|11650->2845|11676->2849|11722->2859|11742->2870|11775->2881|11814->2884|11852->2900|11891->2908|11968->2950|11983->2956|12221->3186|12288->3215|12355->3246|12379->3248|12424->3257|12450->3261|12496->3271|12516->3282|12553->3297|12592->3300|12631->3316|12670->3324|12778->3397|12793->3403|13064->3653|13141->3695|13156->3701|13408->3931|13486->3973|13502->3979|13792->4260|13860->4289|13928->4320|13953->4322|13999->4331|14026->4335|14073->4345|14094->4356|14143->4382|14183->4385|14223->4401|14263->4409|14372->4482|14388->4488|14636->4727|14704->4756|14771->4786|14796->4788|14842->4797|14869->4801|14916->4811|14937->4822|14979->4841|15019->4844|15059->4860|15099->4868|15177->4910|15193->4916|15449->5163|15517->5192|15584->5222|15609->5224|15655->5233|15682->5237|15729->5247|15750->5258|15797->5282|15837->5285|15877->5301|15917->5309|15995->5351|16011->5357|16245->5582|16313->5611|16380->5641|16405->5643|16451->5652|16478->5656|16525->5666|16546->5677|16585->5693|16625->5696|16665->5712|16705->5720|16814->5793|16830->5799|17082->6042|17150->6071|17217->6101|17242->6103|17288->6112|17315->6116|17362->6126|17383->6137|17426->6157|17466->6160|17506->6176|17546->6184|17624->6226|17640->6232|17932->6502|18048->6581|18062->6585|18158->6658|18255->6719|18271->6725|18561->6993|18639->7035|18655->7041|18914->7278|18992->7320|19008->7326|19257->7553|19366->7626|19382->7632|19645->7873|19723->7915|19739->7921|19985->8145|20063->8187|20079->8193|20379->8471|20488->8544|20504->8550|20765->8789|20843->8831|20859->8837|21118->9074|21262->9182|21278->9188|21564->9452|21642->9494|21658->9500|21947->9767|22025->9809|22041->9815|22354->10106|22463->10179|22479->10185|22731->10415|22809->10457|22825->10463|23069->10685|23147->10727|23163->10733|23450->10998|23528->11040|23598->11100|23639->11102|23680->11107|23734->11138|23757->11142|23797->11144|23838->11149|23893->11181|23929->11185|23967->11191|24006->11194|24034->11203|24074->11205|24149->11243|24165->11249|24228->11289|24273->11302|24305->16314
                    LINES: 26->1|37->343|37->343|39->343|41->345|41->345|41->345|44->348|44->348|50->354|53->357|53->357|60->364|63->367|63->367|70->374|75->379|75->379|82->386|85->389|85->389|92->396|95->399|95->399|103->407|108->412|108->412|108->412|110->414|110->414|110->414|111->415|111->415|111->415|113->417|113->417|113->417|114->418|114->418|115->419|121->425|121->425|121->425|126->430|126->430|126->430|128->432|128->432|128->432|129->433|129->433|129->433|130->434|130->434|130->434|131->435|131->435|131->435|132->436|132->436|132->436|133->437|133->437|133->437|139->443|139->443|139->443|150->454|150->454|150->454|151->455|151->455|151->455|156->1|157->7|158->8|158->8|158->8|160->10|160->10|160->10|163->13|163->13|163->13|166->16|166->16|169->19|169->19|169->19|170->20|170->20|170->20|171->21|171->21|171->21|171->21|173->23|173->23|173->23|177->27|177->27|177->27|178->28|178->28|178->28|179->29|185->35|186->36|190->40|192->42|196->46|197->47|201->51|207->57|207->57|213->63|216->66|216->66|222->72|222->72|223->73|223->73|223->73|223->73|223->73|223->73|223->73|223->73|223->73|224->74|227->77|227->77|233->83|233->83|234->84|234->84|234->84|234->84|234->84|234->84|234->84|234->84|234->84|235->85|240->90|240->90|246->96|249->99|249->99|255->105|258->108|258->108|265->115|265->115|266->116|266->116|266->116|266->116|266->116|266->116|266->116|266->116|266->116|267->117|272->122|272->122|278->128|278->128|279->129|279->129|279->129|279->129|279->129|279->129|279->129|279->129|279->129|280->130|283->133|283->133|289->139|289->139|290->140|290->140|290->140|290->140|290->140|290->140|290->140|290->140|290->140|291->141|294->144|294->144|300->150|300->150|301->151|301->151|301->151|301->151|301->151|301->151|301->151|301->151|301->151|302->152|307->157|307->157|313->163|313->163|314->164|314->164|314->164|314->164|314->164|314->164|314->164|314->164|314->164|315->165|318->168|318->168|325->175|331->181|331->181|331->181|334->184|334->184|341->191|344->194|344->194|350->200|353->203|353->203|359->209|364->214|364->214|370->220|373->223|373->223|379->229|382->232|382->232|389->239|394->244|394->244|400->250|403->253|403->253|409->259|416->266|416->266|423->273|426->276|426->276|433->283|436->286|436->286|443->293|448->298|448->298|454->304|457->307|457->307|463->313|466->316|466->316|473->323|478->328|478->328|478->328|479->329|479->329|480->330|480->330|481->331|481->331|482->332|484->334|485->335|487->337|487->337|488->338|488->338|488->338|489->339|493->459
                    -- GENERATED --
                */
            