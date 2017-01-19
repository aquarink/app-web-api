
package views.html.send

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
object create extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Form[models.forms.CashInForm],String,TreeMap[String, String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(transactionForm: Form[models.forms.CashInForm], channelCode: String, channels: TreeMap[String, String]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import partial._


Seq[Any](format.raw/*1.106*/("""

"""),format.raw/*6.1*/("""
"""),_display_(Seq[Any](/*7.2*/layout("Send money")/*7.22*/ {_display_(Seq[Any](format.raw/*7.24*/("""

<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*10.7*/doku/*10.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG009","Send money"))),format.raw/*10.76*/("""</h1>
</div>

"""),_display_(Seq[Any](/*13.2*/partial/*13.9*/.flash_alert())),format.raw/*13.23*/("""

<style>
	.ui-autocomplete """),format.raw/*16.19*/("""{"""),format.raw/*16.20*/("""
		max-height: 200px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
	"""),format.raw/*21.2*/("""}"""),format.raw/*21.3*/("""
	
	/* IE 6 doesn't support max-height
	* we use height instead, but this forces the menu to always be this tall
	*/
	* html .ui-autocomplete """),format.raw/*26.26*/("""{"""),format.raw/*26.27*/("""
		height: 200px;
	"""),format.raw/*28.2*/("""}"""),format.raw/*28.3*/("""
	.ui-autocomplete-loading """),format.raw/*29.27*/("""{"""),format.raw/*29.28*/("""
		background: white url(""""),_display_(Seq[Any](/*30.27*/routes/*30.33*/.Assets.at("images/ui-anim_basic_16x16.gif"))),format.raw/*30.77*/("""") right center no-repeat;
	"""),format.raw/*31.2*/("""}"""),format.raw/*31.3*/("""
</style>
<div class="container-transaction-send-inner">
	"""),_display_(Seq[Any](/*34.3*/helper/*34.9*/.form(routes.Send.confirm, 'id -> "form-cashin", 'autocomplete -> "on", 'class -> "row form form-horizontal", Symbol("data-url-verify") -> routes.Send.verify.url)/*34.171*/ {_display_(Seq[Any](format.raw/*34.173*/("""
		<div class="hide">
			<input type="hidden" name="trackingId" id="trackingId" value=""""),_display_(Seq[Any](/*36.67*/transactionForm/*36.82*/.get().trackingId)),format.raw/*36.99*/("""" />
			<input type="hidden" name="sender.id" id="sender_id" />
			<input type="hidden" name="beneficiary.id" id="beneficiary_id" />
			<input type="hidden" name="agent.id" id="user_id" value='"""),_display_(Seq[Any](/*39.62*/session/*39.69*/.get("userId"))),format.raw/*39.83*/("""' />
			<input type="hidden" name="corporate.code" id="corporate_code" value='"""),_display_(Seq[Any](/*40.75*/session/*40.82*/.get("corporateCode"))),format.raw/*40.103*/("""' />
		</div>
		<div class="span12">
			<p>
			"""),_display_(Seq[Any](/*44.5*/doku/*44.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG037","Please filll all the mandatory fields highlighted with the bold labels below"))),format.raw/*44.140*/("""
			</p>
		</div>

		<fieldset class="span12 form-cashin-info">
			<legend style="border-bottom: 0px">"""),_display_(Seq[Any](/*49.40*/doku/*49.44*/.kirimdoku.util.MultiLanguage.getLanguage("LANG038","Transaction information"))),format.raw/*49.122*/("""</legend>
			<div class="row hide">
				<div class="control-group span4 well">
					<input type="text" name="idToken" class="required"/>
					"""),_display_(Seq[Any](/*53.7*/select(transactionForm("beneficiaryCurrency.code"),
						options(models.Currency.options),
						'_label -> "Receive currency",
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*57.34*/("""
					"""),_display_(Seq[Any](/*58.7*/inputText(transactionForm("fundSource"),
						'_label -> "Source of fund",
						'class -> "input-large",
						'_help -> "(Optional field)"
					))),format.raw/*62.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span12 container-info"></div>
				
				<div class="span6">
					<div class="control-group span12">
						"""),_display_(Seq[Any](/*70.8*/helper/*70.14*/.select(transactionForm("channel.code"),
						options(channels), 
							'_class -> "required",
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service")
						))),format.raw/*74.8*/("""
					</div>
					<div class="control-group span12">
						"""),_display_(Seq[Any](/*77.8*/select(
							transactionForm("senderCountry.code"),
							options(transactionForm("senderCountry.code").value -> transactionForm("senderCountry.name").value),
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"),
							'readOnly -> true,
							'_showConstraints -> false, 'class -> "required"
						))),format.raw/*83.8*/("""
					</div>
					<div class="control-group span12">
						"""),_display_(Seq[Any](/*86.8*/helper/*86.14*/.select(transactionForm("beneficiaryCountry.code"),
							options(models.Country.optionsBeneficiaryCountry(transactionForm("senderCountry.code").value)),
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG041","Receive country"),
							'_showConstraints -> false,
							'class -> "country_id",
							'_class -> "required",
							'class -> "required",
							'autofocus -> "true"
						))),format.raw/*94.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*97.8*/select(transactionForm("senderCurrency.code"),
							options(transactionForm("senderCurrency.code").value -> transactionForm("senderCurrency.code").value),
							'_label -> "Sender currency",
							'class -> "currency disabled",
							'readOnly -> true,
							'_showConstraints -> false))),format.raw/*102.35*/("""
					</div>
					<div class="control-group span12">
						"""),_display_(Seq[Any](/*105.8*/helper/*105.14*/.input(transactionForm("senderAmount")
							, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount")
							, '_class -> "required"
							, 'class -> "amount input-block-level"
							, 'readOnly -> true
							, '_help -> ""
							, '_showConstraints -> false
							, 'maxLength -> 14
						)/*113.8*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*113.37*/("""
				            <div class="input-prepend input-append">
								<span class="add-on"><input type="radio" name=" 	" value="senderAmount" checked="true" disabled/></span>
								<input type="text" id=""""),_display_(Seq[Any](/*116.33*/id)),format.raw/*116.35*/("""" name=""""),_display_(Seq[Any](/*116.44*/name)),format.raw/*116.48*/("""" value=""""),_display_(Seq[Any](/*116.58*/value)),format.raw/*116.63*/("""" """),_display_(Seq[Any](/*116.66*/toHtmlArgs(args))),format.raw/*116.82*/(""">
								<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*117.68*/transactionForm("senderCurrency.code")/*117.106*/.value)),format.raw/*117.112*/("""</span>
							</div>
						""")))})),format.raw/*119.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*122.8*/helper/*122.14*/.input(transactionForm("beneficiaryAmount")
							, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount")
							, '_class -> "required"
							, 'class -> "amount"
							, 'readOnly -> true
							, '_help -> ""
							, '_showConstraints -> false
							, 'maxLength -> 14
						)/*130.8*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*130.37*/("""
				            <div class="input-prepend input-append">
								<span class="add-on"><input type="radio" name="sendType" value="beneficiaryAmount"/></span>
								<input type="text" id=""""),_display_(Seq[Any](/*133.33*/id)),format.raw/*133.35*/("""" name=""""),_display_(Seq[Any](/*133.44*/name)),format.raw/*133.48*/("""" value=""""),_display_(Seq[Any](/*133.58*/value)),format.raw/*133.63*/("""" """),_display_(Seq[Any](/*133.66*/toHtmlArgs(args))),format.raw/*133.82*/(""">
								<span class="add-on currency-code" style="font-size:11px">...</span>
							</div>
						""")))})),format.raw/*136.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*139.8*/helper/*139.14*/.input(transactionForm("collectAmount")
							, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect")
							, '_class -> ""
							, 'class -> "amount input-block-level"
							, 'readOnly -> "true"
							, '_help -> ""
							, 'tabindex -> "-1"
							, '_showConstraints -> false
						)/*147.8*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*147.37*/("""
				            <div class="input-append" style="width: 185px;">
								<input type="text" id=""""),_display_(Seq[Any](/*149.33*/id)),format.raw/*149.35*/("""" name=""""),_display_(Seq[Any](/*149.44*/name)),format.raw/*149.48*/("""" value=""""),_display_(Seq[Any](/*149.58*/value)),format.raw/*149.63*/("""" """),_display_(Seq[Any](/*149.66*/toHtmlArgs(args))),format.raw/*149.82*/(""" style="width: 100%;">
								<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*150.68*/transactionForm("senderCurrency.code")/*150.106*/.value)),format.raw/*150.112*/("""</span>
							</div>
						""")))})),format.raw/*152.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*155.8*/helper/*155.14*/.inputText(transactionForm("rate"),
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"),
							'_class -> "",
							'class -> "rate amount",
							'readOnly -> "true",
							'_help -> "",
							'alt -> "amount",
							'tabindex -> "-1",
							'_showConstraints -> false
						))),format.raw/*164.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*167.8*/helper/*167.14*/.inputText(transactionForm("feesTotal"),
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"),
							'_class -> "",
							'class -> "amount",
							'readOnly -> "readonly",
							'tabindex -> "-1",
							'_help -> "",
							'_showConstraints -> false
						))),format.raw/*175.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*178.8*/helper/*178.14*/.inputText(transactionForm("additionalFee"),
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"),
							'_class -> "",
							'class -> "amount",
							'readOnly -> "readonly",
							'tabindex -> "-1",
							'_help -> "",
							'_showConstraints -> false
						))),format.raw/*186.8*/("""
					</div>
					<div class="control-group span12">
						"""),_display_(Seq[Any](/*189.8*/helper/*189.14*/.inputText(transactionForm("senderNote"),
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"),
							'class -> ""
						))),format.raw/*192.8*/("""
					</div>
					<div class="control-group span12 hide">
						"""),_display_(Seq[Any](/*195.8*/helper/*195.14*/.inputText(transactionForm("beneficiaryCity"),
							'_label -> "Receive city",
							'class -> "",
							'_help -> "",
							'_showConstraints -> false
						))),format.raw/*200.8*/("""
					</div>
					
					
					<fieldset class="span12 form-cashin-customer" data-prefix="sender">
						<legend style="border-bottom: 0px">"""),_display_(Seq[Any](/*205.43*/doku/*205.47*/.kirimdoku.util.MultiLanguage.getLanguage("LANG049","Sender information"))),format.raw/*205.120*/("""</legend>
						<div class="row">
							<div class="span12 container-info"></div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*209.10*/helper/*209.16*/.inputText(
									transactionForm("sender.idToken"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
									'_showConstraints -> false,
									'_class -> "",
									'class -> ""
								))),format.raw/*215.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*218.10*/helper/*218.16*/.inputText(
									transactionForm("sender.firstName"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required",
									'onkeypress -> "return !numbersonly(this, event)"
								))),format.raw/*225.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*228.10*/helper/*228.16*/.inputText(
									transactionForm("sender.lastName"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
									'_class -> "required",
									'class -> "required",
									'onkeypress -> "return !numbersonly(this, event)"
								))),format.raw/*234.10*/("""
							</div>
						</div>
						<div class="row">
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*239.10*/helper/*239.16*/.inputText(
									transactionForm("sender.phoneNumber")
									, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number")
									, '_showConstraints -> false
									, '_class -> "required"
									, 'class -> "required phone"
									, 'type -> "tel"
								))),format.raw/*246.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*249.10*/helper/*249.16*/.inputText(
									transactionForm("sender.address"),
									'_label -> "Address",
									'_showConstraints -> false
								))),format.raw/*253.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*256.10*/helper/*256.16*/.select(
									transactionForm("sender.personalIdType"),
									options(models.Customer.optionsPersonalIdType),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID type"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*263.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*266.10*/helper/*266.16*/.inputText(
									transactionForm("sender.personalId"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*272.10*/("""
							</div>
						</div>
						<div class="row">
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*277.10*/helper/*277.16*/.select(
									transactionForm("sender.country.code"),
									options(models.Country.options),
									'_default -> "-- Choose a country --",
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*285.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*288.10*/helper/*288.16*/.inputDate(
									transactionForm("sender.birthDate"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
									, 'maxlength -> 10
									, 'min -> "1900-01-01"
									, 'max -> "2099-12-12"
								))),format.raw/*297.10*/("""
							</div>
							<div class="span12 hide">
								"""),_display_(Seq[Any](/*300.10*/helper/*300.16*/.select(
									transactionForm("sender.gender"),
									options(models.Customer.optionsGender),
									'_label -> "Gender",
									'_showConstraints -> false
								))),format.raw/*305.10*/("""
							</div>
						</div>
						<div class="row">
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*310.10*/helper/*310.16*/.inputDate(
									transactionForm("sender.personalIdIssueDate"),
									'_label -> "Issue date",
									'_showConstraints -> false
									, 'maxlength -> 10
									, 'min -> "1900-01-01"
									, 'max -> "2099-12-12"
								))),format.raw/*317.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*320.10*/helper/*320.16*/.inputDate(
									transactionForm("sender.personalIdExpireDate"),
									'_label -> "Expired date",
									'_showConstraints -> false
									, 'maxlength -> 10
									, 'min -> "1900-01-01"
									, 'max -> "2099-12-12"
								))),format.raw/*327.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*330.10*/helper/*330.16*/.select(
									transactionForm("sender.personalIdCountry.code"),
									options(models.Country.options),
									'_default -> "-- Choose a country --",
									'_label -> "Issuing country",
									'_showConstraints -> false
								))),format.raw/*336.10*/("""
							</div>
						</div>
						<div class="row">
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*341.10*/helper/*341.16*/.inputText(
									transactionForm("sender.occupation"),
									'_label -> "Occupation",
									'_showConstraints -> false,
									'class -> ""
								))),format.raw/*346.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*349.10*/helper/*349.16*/.inputText(
									transactionForm("sender.cityName"),
									'_label -> "City",
									'_showConstraints -> false,
									'class -> ""
								))),format.raw/*354.10*/("""
							</div>
							
						</div>
					</fieldset>
					
					<fieldset class="span12 form-cashin-customer" data-prefix="beneficiary">
						<legend style="border-bottom: 0px">"""),_display_(Seq[Any](/*361.43*/doku/*361.47*/.kirimdoku.util.MultiLanguage.getLanguage("LANG058","Receiver information"))),format.raw/*361.122*/("""</legend>
						<div class="row">
							<div class="span12 container-info"></div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*365.10*/helper/*365.16*/.select(transactionForm("beneficiary.idToken"),
								options(),
								'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
								'_showConstraints -> false,
								'class -> "beneficiary.idToken",
								'_class -> "required",
								'class -> "required",
								'onchange -> "return !numbersonly(this, event)",
								'autofocus -> "true"
								))),format.raw/*374.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*377.10*/helper/*377.16*/.inputText(
									transactionForm("beneficiary.firstName"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
									'_class -> "required",
									'class -> "required",
									'onkeypress -> "return !numbersonly(this, event)"
								))),format.raw/*383.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*386.10*/helper/*386.16*/.inputText(
									transactionForm("beneficiary.lastName"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
									'_class -> "required",
									'class -> "required",
									'onkeypress -> "return !numbersonly(this, event)"
								))),format.raw/*392.10*/("""
							</div>
						</div>
						<div class="row">
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*397.10*/helper/*397.16*/.inputText(
									transactionForm("beneficiary.phoneNumber"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
									'_class -> "required",
									'class -> "required phone"
									, 'type -> "tel"
									, 'autocomplete -> "off"
								))),format.raw/*404.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*407.10*/helper/*407.16*/.inputText(
									transactionForm("beneficiary.address"),
									'_label -> "Address"
								))),format.raw/*410.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*413.10*/helper/*413.16*/.select(
									transactionForm("beneficiary.country.code"),
									options(models.Country.options),
									'_default -> "-- Choose a country --",
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"),
									'_class -> "required",
									'class -> "required"
								))),format.raw/*420.10*/("""
							</div>
						</div>
						<div class="row hide" id="row-ctc">
							<div class="span12 container-info"></div>
							<div class="control-group span12">
								&nbsp;
							</div>
							<div class="control-group span12">
								<div class="clearfix" id="inputTCODE_field">
								    <label for="inputModeTCODE">"""),_display_(Seq[Any](/*430.42*/doku/*430.46*/.kirimdoku.util.MultiLanguage.getLanguage("LANG187","Telegraphic Code"))),format.raw/*430.117*/("""</label>
								    <div class="input">
							            <div class="input-prepend input-append">
											<span class="add-on"><input type="radio" id="inputModeTCODE" name="beneficiary.inputMode" value="TCODE" checked="checked"></span>
											<input type="text" id="beneficiary_tcode" name="beneficiary.tcode" value=""""),_display_(Seq[Any](/*434.87*/transactionForm("beneficiary.tcode")/*434.123*/.value)),format.raw/*434.129*/("""" title="Shift+Down to Check" style="width: 138px;">
											<span class="add-on checkTC" style="font-size:11px; cursor: pointer;border-radius:0px 4px 4px 0px;">"""),_display_(Seq[Any](/*435.113*/doku/*435.117*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*435.177*/("""</span>
											<label class="hide" for="beneficiary_tcode"><small style="font-style: italic;">("""),_display_(Seq[Any](/*436.93*/doku/*436.97*/.kirimdoku.util.MultiLanguage.getLanguage("LANG196","separated by semicolon"))),format.raw/*436.174*/(""" [ ; ])</small></label>
										</div>
								    </div>
								</div>
							</div>
							<div class="control-group span12">
								<div class="clearfix" id="inputPINYIN_field">
								    <label for="inputModePINYIN">"""),_display_(Seq[Any](/*443.43*/doku/*443.47*/.kirimdoku.util.MultiLanguage.getLanguage("LANG197","Pinyin"))),format.raw/*443.108*/("""</label>
								    <div class="input">
							            <div class="input-prepend input-append">
											<span class="add-on"><input type="radio" id="inputModePINYIN" name="beneficiary.inputMode" value="PINYIN"></span>
											<input type="text" id="beneficiary_pinyin" name="beneficiary.pinyin" value=""""),_display_(Seq[Any](/*447.89*/transactionForm("beneficiary.pinyin")/*447.126*/.value)),format.raw/*447.132*/("""" readonly="readonly" title="Shift+Down to Check" style="width: 138px;">
											<span class="add-on checkPinyin" style="font-size:11px; cursor: pointer;border-radius:0px 4px 4px 0px;">"""),_display_(Seq[Any](/*448.117*/doku/*448.121*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*448.181*/("""</span>
											<label class="hide" for="beneficiary_pinyin"><small style="font-style: italic;">("""),_display_(Seq[Any](/*449.94*/doku/*449.98*/.kirimdoku.util.MultiLanguage.getLanguage("LANG196","separated by semicolon"))),format.raw/*449.175*/(""" [ ; ])</small></label>
										</div>
								    </div>
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset class="span12 form-cashin-customer-account hide" data-prefix="beneficiary">
						<legend style="border-bottom: 0px">"""),_display_(Seq[Any](/*457.43*/doku/*457.47*/.kirimdoku.util.MultiLanguage.getLanguage("LANG059","Receiver bank information"))),format.raw/*457.127*/("""</legend>
						<div class="row">
							<div class="span12 container-info"></div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*461.10*/helper/*461.16*/.select(
									transactionForm("groupBank"),
									options(),
									'_default -> "-- Choose Group Bank --",
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank Name"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*469.10*/("""
							</div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*472.10*/helper/*472.16*/.select(
									transactionForm("provinceBank"),
									options(),
									'_default -> "-- Choose Province --",
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG061","Province"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*480.10*/("""
							</div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*483.10*/helper/*483.16*/.select(
									transactionForm("cityBank"),
									options(),
									'_default -> "-- Choose City --",
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*491.10*/("""
							</div>
						</div>
						<div class="row">
							<div class="span12 container-info"></div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*497.10*/helper/*497.16*/.select(
									transactionForm("beneficiaryAccount.bank.code"),
									options(),
									'_default -> "-- Choose a bank --",
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG063","Bank Name"),
									'_showConstraints -> false,
									'_class -> "required",
									'class -> "required"
								))),format.raw/*505.10*/("""
							</div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*508.10*/helper/*508.16*/.inputText(
									transactionForm("beneficiaryAccount.bank.code"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG064","SWIFT Code"),
									'_class -> "required disabled",
									'class -> "required disabled",
									'disabled -> "true"
								))),format.raw/*514.10*/("""
							</div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*517.10*/helper/*517.16*/.inputText(
									transactionForm("beneficiaryAccount.number"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Number"),
									'_class -> "required",
									'class -> "required"
								))),format.raw/*522.10*/("""
							</div>
							
			
						</div>
						<div class="row" id="receiver-bank-info">
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*529.10*/helper/*529.16*/.inputText(
									transactionForm("beneficiaryAccount.city"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG066","Account City"),
									'_class -> "required",
									'class -> "required"
								))),format.raw/*534.10*/("""
							</div>
							<div class="control-group span12 hide">
								"""),_display_(Seq[Any](/*537.10*/helper/*537.16*/.inputText(
									transactionForm("beneficiaryAccount.address"),
									'_label -> "Address",
									'class -> "",
									'style -> ""
								))),format.raw/*542.10*/("""
							</div>
						</div>
					</fieldset>
					
					<fieldset class="span12 form-cashin-voucher-info hide" data-prefix="beneficiary">
						<legend>Voucher information</legend>
						<div class="row">
							<div class="span12 voucher-info"></div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*552.10*/helper/*552.16*/.inputText(
									transactionForm("voucherCode"),
									'_label -> "Voucher Code",
									'_class -> "required",
									'class -> "required",
									'maxLength -> 6
								))),format.raw/*558.10*/("""
							</div>
							<button type="button" class="btn" id="btn-cek-voucher" data-dismiss="modal" aria-hidden="true">"""),_display_(Seq[Any](/*560.104*/doku/*560.108*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*560.168*/("""</button>
						</div>
					</fieldset>
					
					<fieldset class="span12 form-cashin-wallet-info hide" data-prefix="beneficiary">
						<legend>"""),_display_(Seq[Any](/*565.16*/doku/*565.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG198","Doku wallet information"))),format.raw/*565.98*/("""</legend>
						<div class="row">
							<div class="span12 wallet-info"></div>
							<div class="control-group span12">
								"""),_display_(Seq[Any](/*569.10*/helper/*569.16*/.inputText(
									transactionForm("beneficiaryWalletId"),
									'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG069","Wallet Id / Email"),
									'_class -> "required",
									'class -> "required",
									'maxLength -> 100
								))),format.raw/*575.10*/("""
							</div>
						</div>
					</fieldset>
				
				</div>
				
				<div class="span6">
					<div class="hero-unit" style="font-size:12px; padding: 10px">
				  		<table>
				  			<tr>
				  				<td width="200px">"""),_display_(Seq[Any](/*586.30*/doku/*586.34*/.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"))),format.raw/*586.104*/("""</td>
				  				<td>:</td>
				  				<td id="senderCountryName"></td>
				  			</tr>
				  				<tr>
				  				<td>"""),_display_(Seq[Any](/*591.16*/doku/*591.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG041","Receive country"))),format.raw/*591.90*/("""</td>
				  				<td>:</td>
				  				<td id="beneficiaryCountryName"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*596.16*/doku/*596.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"))),format.raw/*596.88*/("""</td>
				  				<td>:</td>
				  				<td id="ratee"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*601.16*/doku/*601.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*601.89*/("""</td>
				  				<td>:</td>
				  				<td id="originAmount"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*606.16*/doku/*606.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount"))),format.raw/*606.89*/("""</td>
				  				<td>:</td>
				  				<td id="destinationAmount"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*611.16*/doku/*611.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"))),format.raw/*611.78*/("""</td>
				  				<td>:</td>
				  				<td id="feeTotal"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*616.16*/doku/*616.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"))),format.raw/*616.89*/("""</td>
				  				<td>:</td>
				  				<td id="feeAdditional"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*621.16*/doku/*621.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"))),format.raw/*621.92*/("""</td>
				  				<td>:</td>
				  				<td id="amountToCollect"></td>
				  			</tr>
				  		</table>
				  		<h5 style="margin-top: 20px; margin-bottom: 0px">"""),_display_(Seq[Any](/*626.59*/doku/*626.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG049","Sender information"))),format.raw/*626.136*/("""</h5>
				  		<table>
				  			<tr>
				  				<td width="200px">"""),_display_(Seq[Any](/*629.30*/doku/*629.34*/.kirimdoku.util.MultiLanguage.getLanguage("LANG527","Sender Name"))),format.raw/*629.100*/("""</td>
				  				<td>:</td>
				  				<td id="sennameLbl"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*634.16*/doku/*634.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*634.87*/("""</td>
				  				<td>:</td>
				  				<td><div id="senpNumberLbl"></div></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*639.16*/doku/*639.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"))),format.raw/*639.86*/("""</td>
				  				<td>:</td>
				  				<td><div id="senpNameLbl"></div></td>
				  			</tr>
				  		</table>
				  		
				  		<h5 style="margin-top: 20px; margin-bottom: 0px">"""),_display_(Seq[Any](/*645.59*/doku/*645.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG058","Receiver information"))),format.raw/*645.138*/("""</h5>
				  		<table>
				  			<tr>
				  				<td width="200px">"""),_display_(Seq[Any](/*648.30*/doku/*648.34*/.kirimdoku.util.MultiLanguage.getLanguage("LANG528","Receiver Name"))),format.raw/*648.102*/("""</td>
				  				<td>:</td>
				  				<td id="recnameLbl"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*653.16*/doku/*653.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*653.87*/("""</td>
				  				<td>:</td>
				  				<td><div id="recpNumberLbl"></div></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*658.16*/doku/*658.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"))),format.raw/*658.86*/("""</td>
				  				<td>:</td>
				  				<td><div id="recpNameLbl"></div></td>
				  			</tr>
				  		</table>
				  		
				  		<table style="margin-top: 20px">
				  			<tr>
				  				<td width="200px">"""),_display_(Seq[Any](/*666.30*/doku/*666.34*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*666.96*/("""</td>
				  				<td>:</td>
				  				<td id="serviceLbl"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*671.16*/doku/*671.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG063","Bank Name"))),format.raw/*671.84*/("""</td>
				  				<td>:</td>
				  				<td id="bankNameLbl"></td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*676.16*/doku/*676.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Number"))),format.raw/*676.89*/("""</td>
				  				<td>:</td>
				  				<td>aa</td>
				  			</tr>
				  			<tr>
				  				<td>"""),_display_(Seq[Any](/*681.16*/doku/*681.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG066","Account City"))),format.raw/*681.87*/("""</td>
				  				<td>:</td>
				  				<td>aa</td>
				  			</tr>
				  		</table>
				  		
				  		<table style="margin-top: 20px">
				  			<tr>
				  				<td width="200px">"""),_display_(Seq[Any](/*689.30*/doku/*689.34*/.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"))),format.raw/*689.104*/("""</td>
				  				<td>:</td>
				  				<td>aa</td>
				  			</tr>
				  		</table>
					</div>				
							
				</div>
				
			</div>
		</fieldset>

		<fieldset class="span12 form-cashin-customer" data-prefix="beneficiary">
		
			<div class="row hide" id="row-ctc">
				<div class="span12 container-info"></div>
				<div class="control-group span4">
					&nbsp;
				</div>
				<div class="control-group span4">
					<div class="clearfix" id="inputTCODE_field">
					    <label for="inputModeTCODE">"""),_display_(Seq[Any](/*710.39*/doku/*710.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG187","Telegraphic Code"))),format.raw/*710.114*/("""</label>
					    <div class="input">
				            <div class="input-prepend input-append">
								<span class="add-on"><input type="radio" id="inputModeTCODE" name="beneficiary.inputMode" value="TCODE" checked="checked"></span>
								<input type="text" id="beneficiary_tcode" name="beneficiary.tcode" value=""""),_display_(Seq[Any](/*714.84*/transactionForm("beneficiary.tcode")/*714.120*/.value)),format.raw/*714.126*/("""" title="Shift+Down to Check" style="width: 138px;">
								<span class="add-on checkTC" style="font-size:11px; cursor: pointer;border-radius:0px 4px 4px 0px;">"""),_display_(Seq[Any](/*715.110*/doku/*715.114*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*715.174*/("""</span>
								<label for="beneficiary_tcode"><small style="font-style: italic;">("""),_display_(Seq[Any](/*716.77*/doku/*716.81*/.kirimdoku.util.MultiLanguage.getLanguage("LANG196","separated by semicolon"))),format.raw/*716.158*/(""" [ ; ])</small></label>
							</div>
					    </div>
					</div>
				</div>
				<div class="control-group span4">
					<div class="clearfix" id="inputPINYIN_field">
					    <label for="inputModePINYIN">"""),_display_(Seq[Any](/*723.40*/doku/*723.44*/.kirimdoku.util.MultiLanguage.getLanguage("LANG197","Pinyin"))),format.raw/*723.105*/("""</label>
					    <div class="input">
				            <div class="input-prepend input-append">
								<span class="add-on"><input type="radio" id="inputModePINYIN" name="beneficiary.inputMode" value="PINYIN"></span>
								<input type="text" id="beneficiary_pinyin" name="beneficiary.pinyin" value=""""),_display_(Seq[Any](/*727.86*/transactionForm("beneficiary.pinyin")/*727.123*/.value)),format.raw/*727.129*/("""" readonly="readonly" title="Shift+Down to Check" style="width: 138px;">
								<span class="add-on checkPinyin" style="font-size:11px; cursor: pointer;border-radius:0px 4px 4px 0px;">"""),_display_(Seq[Any](/*728.114*/doku/*728.118*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*728.178*/("""</span>
								<label for="beneficiary_pinyin"><small style="font-style: italic;">("""),_display_(Seq[Any](/*729.78*/doku/*729.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG196","separated by semicolon"))),format.raw/*729.159*/(""" [ ; ])</small></label>
							</div>
					    </div>
					</div>
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12 form-cashin-voucher-info hide" data-prefix="beneficiary">
			<legend>Voucher information</legend>
			<div class="row">
				<div class="span12 voucher-info"></div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*742.7*/helper/*742.13*/.inputText(
						transactionForm("voucherCode"),
						'_label -> "Voucher Code",
						'_class -> "required",
						'class -> "required",
						'maxLength -> 6
					))),format.raw/*748.7*/("""
				</div>
				<button type="button" class="btn" id="btn-cek-voucher" data-dismiss="modal" aria-hidden="true">"""),_display_(Seq[Any](/*750.101*/doku/*750.105*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*750.165*/("""</button>
			</div>
		</fieldset>
		
		<fieldset class="span12 form-cashin-wallet-info hide" data-prefix="beneficiary">
			<legend>"""),_display_(Seq[Any](/*755.13*/doku/*755.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG198","Doku wallet information"))),format.raw/*755.95*/("""</legend>
			<div class="row">
				<div class="span12 wallet-info"></div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*759.7*/helper/*759.13*/.inputText(
						transactionForm("beneficiaryWalletId"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG069","Wallet Id / Email"),
						'_class -> "required",
						'class -> "required",
						'maxLength -> 100
					))),format.raw/*765.7*/("""
				</div>
			</div>
		</fieldset>

		<div class="span12">
            <div class="form-actions">
				<button type="reset" class="btn"><i class="icon-repeat"></i> """),_display_(Seq[Any](/*772.67*/doku/*772.71*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*772.131*/("""</button>
				<button type="submit" id="btn-send-primary" class="btn btn-primary btn-next" data-loading-text="Loading..." data-disable-with="Sending..." data-enable-with="Sent">"""),_display_(Seq[Any](/*773.169*/doku/*773.173*/.kirimdoku.util.MultiLanguage.getLanguage("LANG067","Send"))),format.raw/*773.232*/(""" <i class="icon-white icon-arrow-right"></i></button>
            </div>
		</div>
	""")))})),format.raw/*776.3*/("""
	

	<div id="sendModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Send dialog" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">Verification failure</h3>
		</div>
		<div class="modal-body">
			<p>One fine body…</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
	</div>

	<div id="checkPinyin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Check Code Pinyin" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">Check Code Pinyin</h3>
		</div>
		<div class="modal-body">
			<p id="pageCheckPinyin"></p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
	</div>

    </div>
</div>
""")))}/*807.2*/ {_display_(Seq[Any](format.raw/*807.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*808.38*/routes/*808.44*/.Assets.at("javascripts/send.js"))),format.raw/*808.77*/("""'></script>
<script src=""""),_display_(Seq[Any](/*809.15*/routes/*809.21*/.Assets.at("javascripts/numbersonly.js"))),format.raw/*809.61*/("""" type="text/javascript"></script>
""")))})),format.raw/*810.2*/("""
"""))}
    }
    
    def render(transactionForm:Form[models.forms.CashInForm],channelCode:String,channels:TreeMap[String, String]): play.api.templates.Html = apply(transactionForm,channelCode,channels)
    
    def f:((Form[models.forms.CashInForm],String,TreeMap[String, String]) => play.api.templates.Html) = (transactionForm,channelCode,channels) => apply(transactionForm,channelCode,channels)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/create.scala.html
                    HASH: 97f37952069ebc96e2ef8b7b1b93239546ab3252
                    MATRIX: 783->1|1033->105|1061->176|1097->178|1125->198|1164->200|1233->234|1246->238|1333->303|1383->318|1398->325|1434->339|1490->367|1519->368|1648->470|1676->471|1846->613|1875->614|1921->633|1949->634|2004->661|2033->662|2096->689|2111->695|2177->739|2232->767|2260->768|2354->827|2368->833|2540->995|2581->997|2705->1085|2729->1100|2768->1117|2998->1311|3014->1318|3050->1332|3165->1411|3181->1418|3225->1439|3308->1487|3320->1491|3474->1622|3613->1725|3626->1729|3727->1807|3905->1950|4117->2140|4159->2147|4328->2295|4528->2460|4543->2466|4753->2655|4848->2715|5214->3060|5309->3120|5324->3126|5754->3535|5854->3600|6169->3892|6265->3952|6281->3958|6618->4286|6686->4315|6926->4518|6951->4520|6997->4529|7024->4533|7071->4543|7099->4548|7139->4551|7178->4567|7284->4636|7333->4674|7363->4680|7424->4709|7525->4774|7541->4780|7865->5095|7933->5124|8160->5314|8185->5316|8231->5325|8258->5329|8305->5339|8333->5344|8373->5347|8412->5363|8544->5463|8645->5528|8661->5534|8997->5861|9065->5890|9200->5988|9225->5990|9271->5999|9298->6003|9345->6013|9373->6018|9413->6021|9452->6037|9579->6127|9628->6165|9658->6171|9719->6200|9820->6265|9836->6271|10181->6594|10282->6659|10298->6665|10612->6957|10713->7022|10729->7028|11058->7335|11154->7395|11170->7401|11355->7564|11456->7629|11472->7635|11658->7799|11835->7939|11849->7943|11946->8016|12117->8150|12133->8156|12396->8396|12504->8467|12520->8473|12860->8790|12968->8861|12984->8867|13285->9145|13430->9253|13446->9259|13766->9556|13874->9627|13890->9633|14045->9765|14153->9836|14169->9842|14505->10155|14613->10226|14629->10232|14909->10489|15054->10597|15070->10603|15442->10952|15550->11023|15566->11029|15941->11381|16035->11438|16051->11444|16250->11620|16395->11728|16411->11734|16673->11973|16781->12044|16797->12050|17062->12292|17170->12363|17186->12369|17451->12611|17596->12719|17612->12725|17796->12886|17904->12957|17920->12963|18096->13116|18312->13295|18326->13299|18425->13374|18596->13508|18612->13514|19026->13905|19134->13976|19150->13982|19458->14267|19566->14338|19582->14344|19888->14627|20033->14735|20049->14741|20367->15036|20475->15107|20491->15113|20614->15213|20722->15284|20738->15290|21077->15606|21440->15932|21454->15936|21549->16007|21914->16335|21961->16371|21991->16377|22194->16542|22209->16546|22293->16606|22430->16706|22444->16710|22545->16787|22809->17014|22823->17018|22908->17079|23259->17393|23307->17430|23337->17436|23564->17625|23579->17629|23663->17689|23801->17790|23815->17794|23916->17871|24205->18123|24219->18127|24323->18207|24494->18341|24510->18347|24848->18662|24951->18728|24967->18734|25305->19049|25408->19115|25424->19121|25750->19424|25939->19576|25955->19582|26308->19912|26411->19978|26427->19984|26730->20264|26833->20330|26849->20336|27105->20569|27281->20708|27297->20714|27549->20943|27657->21014|27673->21020|27849->21173|28189->21476|28205->21482|28414->21668|28570->21786|28585->21790|28669->21850|28853->21997|28867->22001|28968->22079|29136->22210|29152->22216|29433->22474|29686->22690|29700->22694|29794->22764|29946->22879|29960->22883|30053->22953|30209->23072|30223->23076|30314->23144|30453->23246|30467->23250|30559->23319|30705->23428|30719->23432|30811->23501|30962->23615|30976->23619|31057->23677|31199->23782|31213->23786|31305->23855|31452->23965|31466->23969|31561->24041|31756->24199|31770->24203|31867->24276|31969->24341|31983->24345|32073->24411|32217->24518|32231->24522|32321->24589|32479->24710|32493->24714|32582->24780|32793->24954|32807->24958|32906->25033|33008->25098|33022->25102|33114->25170|33258->25277|33272->25281|33362->25348|33520->25469|33534->25473|33623->25539|33860->25739|33874->25743|33959->25805|34103->25912|34117->25916|34204->25980|34349->26088|34363->26092|34455->26161|34585->26254|34599->26258|34689->26325|34900->26499|34914->26503|35008->26573|35541->27069|35555->27073|35650->27144|36003->27460|36050->27496|36080->27502|36280->27664|36295->27668|36379->27728|36500->27812|36514->27816|36615->27893|36857->28098|36871->28102|36956->28163|37295->28465|37343->28502|37373->28508|37597->28694|37612->28698|37696->28758|37818->28843|37832->28847|37933->28924|38306->29261|38322->29267|38512->29435|38662->29547|38677->29551|38761->29611|38930->29743|38944->29747|39045->29825|39199->29943|39215->29949|39477->30189|39679->30354|39693->30358|39777->30418|39993->30596|40008->30600|40091->30659|40207->30743|41237->31754|41277->31756|41352->31794|41368->31800|41424->31833|41487->31859|41503->31865|41566->31905|41634->31941
                    LINES: 26->1|34->1|36->6|37->7|37->7|37->7|40->10|40->10|40->10|43->13|43->13|43->13|46->16|46->16|51->21|51->21|56->26|56->26|58->28|58->28|59->29|59->29|60->30|60->30|60->30|61->31|61->31|64->34|64->34|64->34|64->34|66->36|66->36|66->36|69->39|69->39|69->39|70->40|70->40|70->40|74->44|74->44|74->44|79->49|79->49|79->49|83->53|87->57|88->58|92->62|100->70|100->70|104->74|107->77|113->83|116->86|116->86|124->94|127->97|132->102|135->105|135->105|143->113|143->113|146->116|146->116|146->116|146->116|146->116|146->116|146->116|146->116|147->117|147->117|147->117|149->119|152->122|152->122|160->130|160->130|163->133|163->133|163->133|163->133|163->133|163->133|163->133|163->133|166->136|169->139|169->139|177->147|177->147|179->149|179->149|179->149|179->149|179->149|179->149|179->149|179->149|180->150|180->150|180->150|182->152|185->155|185->155|194->164|197->167|197->167|205->175|208->178|208->178|216->186|219->189|219->189|222->192|225->195|225->195|230->200|235->205|235->205|235->205|239->209|239->209|245->215|248->218|248->218|255->225|258->228|258->228|264->234|269->239|269->239|276->246|279->249|279->249|283->253|286->256|286->256|293->263|296->266|296->266|302->272|307->277|307->277|315->285|318->288|318->288|327->297|330->300|330->300|335->305|340->310|340->310|347->317|350->320|350->320|357->327|360->330|360->330|366->336|371->341|371->341|376->346|379->349|379->349|384->354|391->361|391->361|391->361|395->365|395->365|404->374|407->377|407->377|413->383|416->386|416->386|422->392|427->397|427->397|434->404|437->407|437->407|440->410|443->413|443->413|450->420|460->430|460->430|460->430|464->434|464->434|464->434|465->435|465->435|465->435|466->436|466->436|466->436|473->443|473->443|473->443|477->447|477->447|477->447|478->448|478->448|478->448|479->449|479->449|479->449|487->457|487->457|487->457|491->461|491->461|499->469|502->472|502->472|510->480|513->483|513->483|521->491|527->497|527->497|535->505|538->508|538->508|544->514|547->517|547->517|552->522|559->529|559->529|564->534|567->537|567->537|572->542|582->552|582->552|588->558|590->560|590->560|590->560|595->565|595->565|595->565|599->569|599->569|605->575|616->586|616->586|616->586|621->591|621->591|621->591|626->596|626->596|626->596|631->601|631->601|631->601|636->606|636->606|636->606|641->611|641->611|641->611|646->616|646->616|646->616|651->621|651->621|651->621|656->626|656->626|656->626|659->629|659->629|659->629|664->634|664->634|664->634|669->639|669->639|669->639|675->645|675->645|675->645|678->648|678->648|678->648|683->653|683->653|683->653|688->658|688->658|688->658|696->666|696->666|696->666|701->671|701->671|701->671|706->676|706->676|706->676|711->681|711->681|711->681|719->689|719->689|719->689|740->710|740->710|740->710|744->714|744->714|744->714|745->715|745->715|745->715|746->716|746->716|746->716|753->723|753->723|753->723|757->727|757->727|757->727|758->728|758->728|758->728|759->729|759->729|759->729|772->742|772->742|778->748|780->750|780->750|780->750|785->755|785->755|785->755|789->759|789->759|795->765|802->772|802->772|802->772|803->773|803->773|803->773|806->776|837->807|837->807|838->808|838->808|838->808|839->809|839->809|839->809|840->810
                    -- GENERATED --
                */
            