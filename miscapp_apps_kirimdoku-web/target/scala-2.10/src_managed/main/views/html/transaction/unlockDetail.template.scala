
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
object unlockDetail extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transactionForm: Form[models.Transaction]):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import helper._

import helper.twitterBootstrap._

import partial._


Seq[Any](format.raw/*1.58*/("""
"""),format.raw/*7.1*/("""
"""),_display_(Seq[Any](/*8.2*/layout("Transaction Refund")/*8.30*/{_display_(Seq[Any](format.raw/*8.31*/("""

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""

<div class="container-refund-inner">
"""),_display_(Seq[Any](/*13.2*/if(err)/*13.9*/ {_display_(Seq[Any](format.raw/*13.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*16.36*/err)),format.raw/*16.39*/("""</div>
		</div>
	</div>
""")))}/*19.3*/else/*19.8*/{_display_(Seq[Any](format.raw/*19.9*/("""
	"""),_display_(Seq[Any](/*20.3*/defining(transactionForm.get)/*20.32*/ { transaction =>_display_(Seq[Any](format.raw/*20.49*/("""
	"""),_display_(Seq[Any](/*21.3*/helper/*21.9*/.form(action = routes.Transaction.unlockProcess(transaction.idToken), 'id -> "unlock-form", 'class -> "row form-horizontal", Symbol("data-remote") -> "true")/*21.166*/ {_display_(Seq[Any](format.raw/*21.168*/("""
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*23.13*/doku/*23.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG038","Transaction information"))),format.raw/*23.95*/("""</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*26.7*/helper/*26.13*/.inputText(
						transactionForm("channel.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'_value -> "TEST",
						'class -> "required"
					))),format.raw/*33.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*36.7*/helper/*36.13*/.input(
						transactionForm("statusText"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					)/*42.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*42.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*43.31*/id)),format.raw/*43.33*/("""" name=""""),_display_(Seq[Any](/*43.42*/name)),format.raw/*43.46*/("""" value=""""),_display_(Seq[Any](/*43.56*/transaction/*43.67*/.statusText)),format.raw/*43.78*/("""" """),_display_(Seq[Any](/*43.81*/toHtmlArgs(args))),format.raw/*43.97*/(""">
					""")))})),format.raw/*44.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*47.7*/helper/*47.13*/.input(
						transactionForm("agent.fullName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG247","Operator"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					)/*53.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*53.36*/("""
						<input type="text" id=""""),_display_(Seq[Any](/*54.31*/id)),format.raw/*54.33*/("""" name=""""),_display_(Seq[Any](/*54.42*/name)),format.raw/*54.46*/("""" value=""""),_display_(Seq[Any](/*54.56*/transaction/*54.67*/.agent.fullName)),format.raw/*54.82*/("""" """),_display_(Seq[Any](/*54.85*/toHtmlArgs(args))),format.raw/*54.101*/(""">
					""")))})),format.raw/*55.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*60.7*/helper/*60.13*/.inputText(
						transactionForm("beneficiary.country.name"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*66.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*69.7*/helper/*69.13*/.inputText(
						transactionForm("beneficiaryCity"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*75.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*78.7*/helper/*78.13*/.input(
						transactionForm("forexReference.rate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required amount"
					)/*84.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*84.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*85.30*/id)),format.raw/*85.32*/("""" name=""""),_display_(Seq[Any](/*85.41*/name)),format.raw/*85.45*/("""" value=""""),_display_(Seq[Any](/*85.55*/transaction/*85.66*/.forexReference.rateFormat)),format.raw/*85.92*/("""" """),_display_(Seq[Any](/*85.95*/toHtmlArgs(args))),format.raw/*85.111*/(""">
					""")))})),format.raw/*86.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*91.7*/helper/*91.13*/.input(
						transactionForm("senderAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required right"
					)/*97.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*97.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*98.30*/id)),format.raw/*98.32*/("""" name=""""),_display_(Seq[Any](/*98.41*/name)),format.raw/*98.45*/("""" value=""""),_display_(Seq[Any](/*98.55*/transaction/*98.66*/.senderAmountFormat)),format.raw/*98.85*/("""" """),_display_(Seq[Any](/*98.88*/toHtmlArgs(args))),format.raw/*98.104*/(""">
					""")))})),format.raw/*99.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*102.7*/helper/*102.13*/.input(
						transactionForm("beneficiaryAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required right"
					)/*108.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*108.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*109.30*/id)),format.raw/*109.32*/("""" name=""""),_display_(Seq[Any](/*109.41*/name)),format.raw/*109.45*/("""" value=""""),_display_(Seq[Any](/*109.55*/transaction/*109.66*/.beneficiaryAmountFormat)),format.raw/*109.90*/("""" """),_display_(Seq[Any](/*109.93*/toHtmlArgs(args))),format.raw/*109.109*/(""">
					""")))})),format.raw/*110.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*113.7*/helper/*113.13*/.input(
						transactionForm("feesTotal"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required right"
					)/*119.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*119.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*120.30*/id)),format.raw/*120.32*/("""" name=""""),_display_(Seq[Any](/*120.41*/name)),format.raw/*120.45*/("""" value=""""),_display_(Seq[Any](/*120.55*/transaction/*120.66*/.feesTotalFormat)),format.raw/*120.82*/("""" """),_display_(Seq[Any](/*120.85*/toHtmlArgs(args))),format.raw/*120.101*/(""">
					""")))})),format.raw/*121.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*126.7*/helper/*126.13*/.input(
						transactionForm("collectAmount"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required right"
					)/*132.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*132.36*/("""
					<input type="text" id=""""),_display_(Seq[Any](/*133.30*/id)),format.raw/*133.32*/("""" name=""""),_display_(Seq[Any](/*133.41*/name)),format.raw/*133.45*/("""" value=""""),_display_(Seq[Any](/*133.55*/transaction/*133.66*/.collectAmountFormat)),format.raw/*133.86*/("""" """),_display_(Seq[Any](/*133.89*/toHtmlArgs(args))),format.raw/*133.105*/(""">
					""")))})),format.raw/*134.7*/("""
				</div>
				<div class="span8">
					"""),_display_(Seq[Any](/*137.7*/helper/*137.13*/.inputText(
						transactionForm("senderNote"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "input-xlarge",
						'style -> "width: 95%"
					))),format.raw/*144.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>Sender information</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*153.7*/helper/*153.13*/.inputText(
						transactionForm("sender.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*159.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*162.7*/helper/*162.13*/.inputText(
						transactionForm("sender.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*168.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*171.7*/helper/*171.13*/.inputText(
						transactionForm("sender.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*177.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*182.7*/helper/*182.13*/.inputText(
						transactionForm("sender.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*188.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*191.7*/helper/*191.13*/.inputText(
						transactionForm("sender.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*197.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*200.7*/helper/*200.13*/.select(
						transactionForm("sender.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*207.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*212.7*/helper/*212.13*/.inputText(
						transactionForm("sender.personalIdType"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID type"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*218.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*221.7*/helper/*221.13*/.inputText(
						transactionForm("sender.personalId"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*227.7*/("""
				</div>
				<div class="span4">
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*234.7*/helper/*234.13*/.inputDate(
						transactionForm("sender.personalIdIssueDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue date"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*240.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*243.7*/helper/*243.13*/.inputDate(
						transactionForm("sender.personalIdExpireDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired date"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*249.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*252.7*/helper/*252.13*/.select(
						transactionForm("sender.personalIdCountry.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG246","Issuing country"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*259.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*264.7*/helper/*264.13*/.inputText(
						transactionForm("sender.occupation"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*270.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*273.7*/helper/*273.13*/.inputText(
						transactionForm("sender.cityName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*279.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*282.7*/helper/*282.13*/.inputDate(
						transactionForm("sender.birthDate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> ""
					))),format.raw/*288.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>"""),_display_(Seq[Any](/*294.13*/doku/*294.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG058","Receiver information"))),format.raw/*294.92*/("""</legend>
				<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*297.7*/helper/*297.13*/.inputText(
						transactionForm("beneficiary.idToken"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*303.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*306.7*/helper/*306.13*/.inputText(
						transactionForm("beneficiary.firstName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*312.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*315.7*/helper/*315.13*/.inputText(
						transactionForm("beneficiary.lastName"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*321.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*326.7*/helper/*326.13*/.inputText(
						transactionForm("beneficiary.phoneNumber"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*332.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*335.7*/helper/*335.13*/.inputText(
						transactionForm("beneficiary.address"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*341.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*344.7*/helper/*344.13*/.select(
						transactionForm("beneficiary.country.code"),
						options(models.Country.options),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"), 
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*351.7*/("""
				</div>
			</div>
		</fieldset>
		<div class="span12">
			"""),_display_(Seq[Any](/*356.5*/if((transaction.status == models.Transaction.STATUS_LOCKED))/*356.65*/ {_display_(Seq[Any](format.raw/*356.67*/("""
				<div class="form-actions">
					"""),_display_(Seq[Any](/*358.7*/dynamicOr("editTransaction", String.valueOf(transaction.id))/*358.67*/ {_display_(Seq[Any](format.raw/*358.69*/("""
						<button type="submit" class="btn btn-refund">"""),_display_(Seq[Any](/*359.53*/doku/*359.57*/.kirimdoku.util.MultiLanguage.getLanguage("LANG097","Unlock"))),format.raw/*359.118*/(""" <i class="icon-arrow-right"></i></button>
					""")))}/*360.7*/ {_display_(Seq[Any](format.raw/*360.9*/("""
						"""),_display_(Seq[Any](/*361.8*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG268","Operation not permitted. Only locked transaction and registered sender operator can unlock this transaction")))),format.raw/*361.191*/("""
					""")))})),format.raw/*362.7*/("""
				</div>
			""")))}/*364.6*/else/*364.11*/{_display_(Seq[Any](format.raw/*364.12*/("""
				"""),_display_(Seq[Any](/*365.6*/message("warn", doku.kirimdoku.util.MultiLanguage.getLanguage("LANG267","Only locked transaction and registered sender operator can unlock this transaction")))),format.raw/*365.164*/("""
			""")))})),format.raw/*366.5*/("""
		</div>
		
		<div class="modal fade hide" id="unlock-dialog">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">×</button>
		        <h3>"""),_display_(Seq[Any](/*372.16*/doku/*372.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG266","Unlock confirmation"))),format.raw/*372.94*/("""</h3>
		    </div>
			<form method="GET" class="form-vertical" data-remote="true">
				<div class="modal-body">
					<div class="control-groups">
						<p class="ban">"""),_display_(Seq[Any](/*377.23*/doku/*377.27*/.kirimdoku.util.MultiLanguage.getLanguage("LANG265","Are you sure you want to unlock this transaction?"))),format.raw/*377.131*/("""</p>
						<dl class="dl-horizontal dl-aligned">
							<dt><label>"""),_display_(Seq[Any](/*379.20*/doku/*379.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*379.93*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*380.13*/transaction/*380.24*/.idToken)),format.raw/*380.32*/("""</dd>
							<dt><label>"""),_display_(Seq[Any](/*381.20*/doku/*381.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*381.90*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*382.13*/transaction/*382.24*/.sender.fullName)),format.raw/*382.40*/("""</dd>
							<dt><label>"""),_display_(Seq[Any](/*383.20*/doku/*383.24*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*383.93*/("""</label></dt>
							<dd>"""),_display_(Seq[Any](/*384.13*/transaction/*384.24*/.senderAmountFormat)),format.raw/*384.43*/("""</dd>
						</dl>
					</div>
					<hr/>
					<div class="control-groups">
						<p style="text-align: center;">
							<center>
								<label>"""),_display_(Seq[Any](/*391.17*/doku/*391.21*/.kirimdoku.util.MultiLanguage.getLanguage("LANG264","Please input previous PIN below:"))),format.raw/*391.108*/("""</label>
								<div class="control-group">
									<div class="input">
										<input id="auth1" type="password" name="auth1" class="required" minlength="4" maxlength="4" placeholder="Previous PIN" style="text-align: center;"/>
									</div>
								</div>
								<label>"""),_display_(Seq[Any](/*397.17*/doku/*397.21*/.kirimdoku.util.MultiLanguage.getLanguage("LANG205","Please input new PIN below:"))),format.raw/*397.103*/("""</label>
								<div class="control-group">
									<div class="input">
										<input id="new_auth1" type="password" name="new_auth1" class="required" minlength="4" maxlength="4" placeholder="New PIN" style="text-align: center;"/>
									</div>
								</div>
								<div class="control-group">
									<div class="input">
										<input id="new_auth2" type="password" name="new_auth2" class="required" minlength="4" maxlength="4" placeholder="Confirm New PIN" style="text-align: center;"/>
									</div>
								</div>
							</center>
						</p>
					</div>
					<p class="container-info">
					</p>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*415.52*/doku/*415.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*415.117*/("""</a>
					<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*416.53*/doku/*416.57*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*416.119*/("""</button>
				</div>
			</form>
		</div>
	""")))})),format.raw/*420.3*/("""
""")))}))))})),format.raw/*421.3*/("""
</div>
""")))}/*423.2*/ {_display_(Seq[Any](format.raw/*423.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*424.38*/routes/*424.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*424.84*/("""'></script>
""")))})),format.raw/*425.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.Transaction]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.Transaction]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/unlockDetail.scala.html
                    HASH: 07888572f9f4a8747c60d4cbcc15fd6e0997e287
                    MATRIX: 767->1|1073->57|1100->232|1136->234|1172->262|1210->263|1248->266|1263->273|1299->287|1374->327|1389->334|1429->336|1543->414|1568->417|1611->443|1623->448|1661->449|1699->452|1737->481|1792->498|1830->501|1844->507|2011->664|2052->666|2129->707|2142->711|2242->789|2338->850|2353->856|2633->1115|2710->1157|2725->1163|2961->1391|3028->1420|3095->1451|3119->1453|3164->1462|3190->1466|3236->1476|3256->1487|3289->1498|3328->1501|3366->1517|3405->1525|3482->1567|3497->1573|3739->1807|3806->1836|3873->1867|3897->1869|3942->1878|3968->1882|4014->1892|4034->1903|4071->1918|4110->1921|4149->1937|4188->1945|4296->2018|4311->2024|4587->2279|4664->2321|4679->2327|4942->2569|5019->2611|5034->2617|5292->2867|5359->2896|5425->2926|5449->2928|5494->2937|5520->2941|5566->2951|5586->2962|5634->2988|5673->2991|5712->3007|5751->3015|5859->3088|5874->3094|6125->3337|6192->3366|6258->3396|6282->3398|6327->3407|6353->3411|6399->3421|6419->3432|6460->3451|6499->3454|6538->3470|6577->3478|6655->3520|6671->3526|6931->3777|6999->3806|7066->3836|7091->3838|7137->3847|7164->3851|7211->3861|7232->3872|7279->3896|7319->3899|7359->3915|7399->3923|7477->3965|7493->3971|7731->4200|7799->4229|7866->4259|7891->4261|7937->4270|7964->4274|8011->4284|8032->4295|8071->4311|8111->4314|8151->4330|8191->4338|8300->4411|8316->4417|8572->4664|8640->4693|8707->4723|8732->4725|8778->4734|8805->4738|8852->4748|8873->4759|8916->4779|8956->4782|8996->4798|9036->4806|9114->4848|9130->4854|9426->5128|9619->5285|9635->5291|9889->5523|9967->5565|9983->5571|10238->5804|10316->5846|10332->5852|10585->6083|10694->6156|10710->6162|10969->6399|11047->6441|11063->6447|11313->6675|11391->6717|11407->6723|11703->6997|11812->7070|11828->7076|12085->7311|12163->7353|12179->7359|12434->7592|12578->7700|12594->7706|12859->7949|12937->7991|12953->7997|13221->8243|13299->8285|13315->8291|13624->8578|13733->8651|13749->8657|14005->8891|14083->8933|14099->8939|14347->9165|14425->9207|14441->9213|14699->9449|14815->9528|14829->9532|14927->9607|15025->9669|15041->9675|15308->9920|15386->9962|15402->9968|15670->10214|15748->10256|15764->10262|16030->10506|16139->10579|16155->10585|16427->10835|16505->10877|16521->10883|16784->11124|16862->11166|16878->11172|17187->11459|17286->11522|17356->11582|17397->11584|17471->11622|17541->11682|17582->11684|17672->11737|17686->11741|17771->11802|17839->11851|17879->11853|17923->11861|18130->12044|18169->12051|18204->12068|18218->12073|18258->12074|18300->12080|18482->12238|18519->12243|18746->12433|18760->12437|18857->12511|19062->12679|19076->12683|19204->12787|19309->12855|19323->12859|19415->12928|19478->12954|19499->12965|19530->12973|19592->12998|19606->13002|19695->13068|19758->13094|19779->13105|19818->13121|19880->13146|19894->13150|19986->13219|20049->13245|20070->13256|20112->13275|20294->13420|20308->13424|20419->13511|20736->13791|20750->13795|20856->13877|21601->14585|21615->14589|21700->14650|21794->14707|21808->14711|21894->14773|21969->14816|22008->14819|22036->14828|22076->14830|22151->14868|22167->14874|22230->14914|22275->14927
                    LINES: 26->1|38->1|39->7|40->8|40->8|40->8|42->10|42->10|42->10|45->13|45->13|45->13|48->16|48->16|51->19|51->19|51->19|52->20|52->20|52->20|53->21|53->21|53->21|53->21|55->23|55->23|55->23|58->26|58->26|65->33|68->36|68->36|74->42|74->42|75->43|75->43|75->43|75->43|75->43|75->43|75->43|75->43|75->43|76->44|79->47|79->47|85->53|85->53|86->54|86->54|86->54|86->54|86->54|86->54|86->54|86->54|86->54|87->55|92->60|92->60|98->66|101->69|101->69|107->75|110->78|110->78|116->84|116->84|117->85|117->85|117->85|117->85|117->85|117->85|117->85|117->85|117->85|118->86|123->91|123->91|129->97|129->97|130->98|130->98|130->98|130->98|130->98|130->98|130->98|130->98|130->98|131->99|134->102|134->102|140->108|140->108|141->109|141->109|141->109|141->109|141->109|141->109|141->109|141->109|141->109|142->110|145->113|145->113|151->119|151->119|152->120|152->120|152->120|152->120|152->120|152->120|152->120|152->120|152->120|153->121|158->126|158->126|164->132|164->132|165->133|165->133|165->133|165->133|165->133|165->133|165->133|165->133|165->133|166->134|169->137|169->137|176->144|185->153|185->153|191->159|194->162|194->162|200->168|203->171|203->171|209->177|214->182|214->182|220->188|223->191|223->191|229->197|232->200|232->200|239->207|244->212|244->212|250->218|253->221|253->221|259->227|266->234|266->234|272->240|275->243|275->243|281->249|284->252|284->252|291->259|296->264|296->264|302->270|305->273|305->273|311->279|314->282|314->282|320->288|326->294|326->294|326->294|329->297|329->297|335->303|338->306|338->306|344->312|347->315|347->315|353->321|358->326|358->326|364->332|367->335|367->335|373->341|376->344|376->344|383->351|388->356|388->356|388->356|390->358|390->358|390->358|391->359|391->359|391->359|392->360|392->360|393->361|393->361|394->362|396->364|396->364|396->364|397->365|397->365|398->366|404->372|404->372|404->372|409->377|409->377|409->377|411->379|411->379|411->379|412->380|412->380|412->380|413->381|413->381|413->381|414->382|414->382|414->382|415->383|415->383|415->383|416->384|416->384|416->384|423->391|423->391|423->391|429->397|429->397|429->397|447->415|447->415|447->415|448->416|448->416|448->416|452->420|453->421|455->423|455->423|456->424|456->424|456->424|457->425
                    -- GENERATED --
                */
            