
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
object confirm_bill_payment extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.forms.CashInForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transactionForm: Form[models.forms.CashInForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import partial._


Seq[Any](format.raw/*1.63*/("""
"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Confirmation")/*6.24*/{_display_(Seq[Any](format.raw/*6.25*/("""
<div class="page-header">
   <h2>"""),_display_(Seq[Any](/*8.9*/doku/*8.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG222","Bill Payment Confirmation Page"))),format.raw/*8.98*/("""</h2>
</div>

"""),_display_(Seq[Any](/*11.2*/partial/*11.9*/.flash_alert())),format.raw/*11.23*/("""

<div class="container-transaction-detail-inner">
"""),_display_(Seq[Any](/*14.2*/if(err != null)/*14.17*/ {_display_(Seq[Any](format.raw/*14.19*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*17.36*/err)),format.raw/*17.39*/("""</div>
		</div>
	</div>
		<div class="form-actions">
			<form action=""""),_display_(Seq[Any](/*21.19*/routes/*21.25*/.Send.createBillPayment())),format.raw/*21.50*/("""" method="post" enctype="application/x-www-form-urlencoded">
			<input type="hidden" name="corporate.code" value=""/>
			<input type="hidden" name="agent.idToken" value=""/>
			<input type="hidden" name="channel.code" value=""/>
			<input type="hidden" name="senderCountry.code" value=""/>
			<input type="hidden" name="senderCurrency.code" value=""/>
			<input type="hidden" name="beneficiaryCountry.code" value=""/>
			<input type="hidden" name="beneficiaryCity" value=""/>
			<input type="hidden" name="beneficiaryCurrency.code" value=""/>
			<input type="hidden" name="senderAmount" value=""/>
			<input type="hidden" name="beneficiaryAmount" value=""/>
			<input type="hidden" name="inquiry.idToken" value=""/>
			<input type="hidden" name="senderNote" value=""/>
			<input type="hidden" name="sendType" value=""/>
			<input type="hidden" name="voucherCode" value=""/>
			
			<input type="hidden" name="sender.idToken" value=""/>
			<input type="hidden" name="sender.firstName" value=""/>
			<input type="hidden" name="sender.lastName" value=""/>
			<input type="hidden" name="sender.phoneNumber" value=""/>
			<input type="hidden" name="sender.address" value=""/>
			<input type="hidden" name="sender.country.code" value=""/>
			<input type="hidden" name="sender.personalIdType" value=""/>
			<input type="hidden" name="sender.personalId" value=""/>
			<input type="hidden" name="sender.personalIdIssueDate" value=""/>
			<input type="hidden" name="sender.personalIdExpireDate" value=""/>
			<input type="hidden" name="sender.personalIdCountry.code" value=""/>
			<input type="hidden" name="sender.gender" value=""/>
			<input type="hidden" name="sender.occupation" value=""/>
			<input type="hidden" name="sender.cityName" value=""/>
			<input type="hidden" name="sender.birthDate" value=""/>
		
			<input type="hidden" name="beneficiary.idToken" value=""/>
			<input type="hidden" name="beneficiary.firstName" value=""/>
			<input type="hidden" name="beneficiary.lastName" value=""/>
			<input type="hidden" name="beneficiary.phoneNumber" value=""/>
			<input type="hidden" name="beneficiary.address" value=""/>
			<input type="hidden" name="beneficiary.country.code" value=""/>
			<button type="submit" class="btn btn-back"><i class="icon-arrow-left"></i> """),_display_(Seq[Any](/*59.80*/doku/*59.84*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*59.145*/("""</button>
			</form>
		</div>
""")))}/*62.3*/else/*62.8*/{_display_(Seq[Any](format.raw/*62.9*/("""
<div>
	"""),_display_(Seq[Any](/*64.3*/helper/*64.9*/.form(action = routes.Send.processBillPayment(), 'id -> "send-confirmation-form", 'class -> "row")/*64.107*/ {_display_(Seq[Any](format.raw/*64.109*/("""
	
	<input type="hidden" name="corporate.code" value=""""),_display_(Seq[Any](/*66.53*/transactionForm("corporate.code")/*66.86*/.value)),format.raw/*66.92*/(""""/>
	<input type="hidden" name="agent.idToken" value=""""),_display_(Seq[Any](/*67.52*/transactionForm("agent.idToken")/*67.84*/.value)),format.raw/*67.90*/(""""/>
	<input type="hidden" name="channel.code" value=""""),_display_(Seq[Any](/*68.51*/transactionForm("channel.code")/*68.82*/.value)),format.raw/*68.88*/(""""/>
	<input type="hidden" name="senderCountry.code" value=""""),_display_(Seq[Any](/*69.57*/transactionForm("senderCountry.code")/*69.94*/.value)),format.raw/*69.100*/(""""/>
	<input type="hidden" name="senderCurrency.code" value=""""),_display_(Seq[Any](/*70.58*/transactionForm("senderCurrency.code")/*70.96*/.value)),format.raw/*70.102*/(""""/>
	<input type="hidden" name="beneficiaryCountry.code" value=""""),_display_(Seq[Any](/*71.62*/transactionForm("beneficiaryCountry.code")/*71.104*/.value)),format.raw/*71.110*/(""""/>
	<input type="hidden" name="beneficiaryCity" value=""""),_display_(Seq[Any](/*72.54*/transactionForm("beneficiaryCity")/*72.88*/.value)),format.raw/*72.94*/(""""/>
	<input type="hidden" name="beneficiaryCurrency.code" value=""""),_display_(Seq[Any](/*73.63*/transactionForm("beneficiaryCurrency.code")/*73.106*/.value)),format.raw/*73.112*/(""""/>
	<input type="hidden" name="senderAmount" value=""""),_display_(Seq[Any](/*74.51*/transactionForm("senderAmount")/*74.82*/.value)),format.raw/*74.88*/(""""/>
	<input type="hidden" name="beneficiaryAmount" value=""""),_display_(Seq[Any](/*75.56*/transactionForm("beneficiaryAmount")/*75.92*/.value)),format.raw/*75.98*/(""""/>
	<input type="hidden" name="inquiry.idToken" value=""""),_display_(Seq[Any](/*76.54*/transactionForm("inquiry.idToken")/*76.88*/.value)),format.raw/*76.94*/(""""/>
	<input type="hidden" name="senderNote" value=""""),_display_(Seq[Any](/*77.49*/transactionForm("senderNote")/*77.78*/.value)),format.raw/*77.84*/(""""/>
	<input type="hidden" name="sendType" value=""""),_display_(Seq[Any](/*78.47*/transactionForm("sendType")/*78.74*/.value)),format.raw/*78.80*/(""""/>
	
	<input type="hidden" name="sender.idToken" value=""""),_display_(Seq[Any](/*80.53*/transactionForm("sender.idToken")/*80.86*/.value)),format.raw/*80.92*/(""""/>
	<input type="hidden" name="sender.firstName" value=""""),_display_(Seq[Any](/*81.55*/transactionForm("sender.firstName")/*81.90*/.value)),format.raw/*81.96*/(""""/>
	<input type="hidden" name="sender.lastName" value=""""),_display_(Seq[Any](/*82.54*/transactionForm("sender.lastName")/*82.88*/.value)),format.raw/*82.94*/(""""/>
	<input type="hidden" name="sender.phoneNumber" value=""""),_display_(Seq[Any](/*83.57*/transactionForm("sender.phoneNumber")/*83.94*/.value)),format.raw/*83.100*/(""""/>
	<input type="hidden" name="sender.address" value=""""),_display_(Seq[Any](/*84.53*/transactionForm("sender.address")/*84.86*/.value)),format.raw/*84.92*/(""""/>
	<input type="hidden" name="sender.country.code" value=""""),_display_(Seq[Any](/*85.58*/transactionForm("sender.country.code")/*85.96*/.value)),format.raw/*85.102*/(""""/>
	<input type="hidden" name="sender.personalIdType" value=""""),_display_(Seq[Any](/*86.60*/transactionForm("sender.personalIdType")/*86.100*/.value)),format.raw/*86.106*/(""""/>
	<input type="hidden" name="sender.personalId" value=""""),_display_(Seq[Any](/*87.56*/transactionForm("sender.personalId")/*87.92*/.value)),format.raw/*87.98*/(""""/>
	<input type="hidden" name="sender.personalIdIssueDate" value=""""),_display_(Seq[Any](/*88.65*/transactionForm("sender.personalIdIssueDate")/*88.110*/.value)),format.raw/*88.116*/(""""/>
	<input type="hidden" name="sender.personalIdExpireDate" value=""""),_display_(Seq[Any](/*89.66*/transactionForm("sender.personalIdExpireDate")/*89.112*/.value)),format.raw/*89.118*/(""""/>
	<input type="hidden" name="sender.personalIdCountry.code" value=""""),_display_(Seq[Any](/*90.68*/transactionForm("sender.personalIdCountry.code")/*90.116*/.value)),format.raw/*90.122*/(""""/>
	<input type="hidden" name="sender.gender" value=""""),_display_(Seq[Any](/*91.52*/transactionForm("sender.gender")/*91.84*/.value)),format.raw/*91.90*/(""""/>
	<input type="hidden" name="sender.occupation" value=""""),_display_(Seq[Any](/*92.56*/transactionForm("sender.occupation")/*92.92*/.value)),format.raw/*92.98*/(""""/>
	<input type="hidden" name="sender.cityName" value=""""),_display_(Seq[Any](/*93.54*/transactionForm("sender.cityName")/*93.88*/.value)),format.raw/*93.94*/(""""/>
	<input type="hidden" name="sender.birthDate" value=""""),_display_(Seq[Any](/*94.55*/transactionForm("sender.birthDate")/*94.90*/.value)),format.raw/*94.96*/(""""/>

	<input type="hidden" name="beneficiary.idToken" value=""""),_display_(Seq[Any](/*96.58*/transactionForm("beneficiary.idToken")/*96.96*/.value)),format.raw/*96.102*/(""""/>
	<input type="hidden" name="beneficiary.firstName" value=""""),_display_(Seq[Any](/*97.60*/transactionForm("beneficiary.firstName")/*97.100*/.value)),format.raw/*97.106*/(""""/>
	<input type="hidden" name="beneficiary.lastName" value=""""),_display_(Seq[Any](/*98.59*/transactionForm("beneficiary.lastName")/*98.98*/.value)),format.raw/*98.104*/(""""/>
	<input type="hidden" name="beneficiary.phoneNumber" value=""""),_display_(Seq[Any](/*99.62*/transactionForm("beneficiary.phoneNumber")/*99.104*/.value)),format.raw/*99.110*/(""""/>
	<input type="hidden" name="beneficiary.address" value=""""),_display_(Seq[Any](/*100.58*/transactionForm("beneficiary.address")/*100.96*/.value)),format.raw/*100.102*/(""""/>
	<input type="hidden" name="beneficiary.country.code" value=""""),_display_(Seq[Any](/*101.63*/transactionForm("beneficiary.country.code")/*101.106*/.value)),format.raw/*101.112*/(""""/>
	<input type="hidden" name="beneficiary.personalIdType" value=""""),_display_(Seq[Any](/*102.65*/transactionForm("beneficiary.personalIdType")/*102.110*/.value)),format.raw/*102.116*/(""""/>
	<input type="hidden" name="beneficiary.personalId" value=""""),_display_(Seq[Any](/*103.61*/transactionForm("beneficiary.personalId")/*103.102*/.value)),format.raw/*103.108*/(""""/>
	<input type="hidden" name="beneficiary.cityName" value=""""),_display_(Seq[Any](/*104.59*/transactionForm("beneficiary.cityName")/*104.98*/.value)),format.raw/*104.104*/(""""/>
	<input type="hidden" name="beneficiary.birthDate" value=""""),_display_(Seq[Any](/*105.60*/transactionForm("beneficiary.birthDate")/*105.100*/.value)),format.raw/*105.106*/(""""/>
	<input type="hidden" name="beneficiary.address" value=""""),_display_(Seq[Any](/*106.58*/transactionForm("beneficiary.address")/*106.96*/.value)),format.raw/*106.102*/(""""/>
	<input type="hidden" name="beneficiary.occupation" value=""""),_display_(Seq[Any](/*107.61*/transactionForm("beneficiary.occupation")/*107.102*/.value)),format.raw/*107.108*/(""""/>
	
	<input type="hidden" name="billPayment.billPaymentService" value=""""),_display_(Seq[Any](/*109.69*/transactionForm("billPayment.billPaymentService")/*109.118*/.value)),format.raw/*109.124*/(""""/>
	<input type="hidden" name="billPayment.selectBill" value=""""),_display_(Seq[Any](/*110.61*/transactionForm("billPayment.selectBill")/*110.102*/.value)),format.raw/*110.108*/(""""/>
	<input type="hidden" name="billPayment.selectOperator" value=""""),_display_(Seq[Any](/*111.65*/transactionForm("billPayment.selectOperator")/*111.110*/.value)),format.raw/*111.116*/(""""/>
	<input type="hidden" name="billPayment.selectDenom" value=""""),_display_(Seq[Any](/*112.62*/transactionForm("billPayment.selectDenom")/*112.104*/.value)),format.raw/*112.110*/(""""/>
	<input type="hidden" name="billPayment.accountNumber" value=""""),_display_(Seq[Any](/*113.64*/transactionForm("billPayment.accountNumber")/*113.108*/.value)),format.raw/*113.114*/(""""/>
	<input type="hidden" name="billPayment.accountName" value=""""),_display_(Seq[Any](/*114.62*/transactionForm("billPayment.accountName")/*114.104*/.value)),format.raw/*114.110*/(""""/>
	<input type="hidden" name="billPayment.selectOperatorName" value=""""),_display_(Seq[Any](/*115.69*/transactionForm("billPayment.selectOperatorName")/*115.118*/.value)),format.raw/*115.124*/(""""/>
	<input type="hidden" name="billPayment.selectDenomName" value=""""),_display_(Seq[Any](/*116.66*/transactionForm("billPayment.selectDenomName")/*116.112*/.value)),format.raw/*116.118*/(""""/>
	<input type="hidden" name="billPayment.amount" value=""""),_display_(Seq[Any](/*117.57*/transactionForm("billPayment.amount")/*117.94*/.value)),format.raw/*117.100*/(""""/>
	
	"""),_display_(Seq[Any](/*119.3*/defining(transactionForm.get)/*119.32*/ { transaction =>_display_(Seq[Any](format.raw/*119.49*/("""
	<fieldset class="span12">
			<!--legend>Transaction information</legend-->
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*125.12*/doku/*125.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*125.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*126.12*/transaction/*126.23*/.channel.name)),format.raw/*126.36*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*127.12*/doku/*127.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG104","Receiver country"))),format.raw/*127.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*128.12*/transaction/*128.23*/.beneficiaryCountry.name)),format.raw/*128.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*129.12*/doku/*129.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG223","Receiver city"))),format.raw/*129.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*130.12*/transaction/*130.23*/.beneficiaryCity)),format.raw/*130.39*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*131.12*/doku/*131.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*131.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*132.12*/transaction/*132.23*/.senderAmountFormat)),format.raw/*132.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*133.12*/doku/*133.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"))),format.raw/*133.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*134.12*/transaction/*134.23*/.beneficiaryAmountFormat)),format.raw/*134.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*135.12*/doku/*135.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"))),format.raw/*135.74*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*136.12*/transaction/*136.23*/.feesTotal)),format.raw/*136.33*/(""" """),_display_(Seq[Any](/*136.35*/transaction/*136.46*/.senderCurrency.code)),format.raw/*136.66*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*137.12*/doku/*137.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"))),format.raw/*137.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*138.12*/transaction/*138.23*/.additionalFee)),format.raw/*138.37*/(""" """),_display_(Seq[Any](/*138.39*/transaction/*138.50*/.beneficiaryCurrency.code)),format.raw/*138.75*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*139.12*/doku/*139.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"))),format.raw/*139.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*140.12*/transaction/*140.23*/.rate)),format.raw/*140.28*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*141.12*/doku/*141.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"))),format.raw/*141.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*142.12*/transaction/*142.23*/.collectAmount.add(transaction.feesTotal))),format.raw/*142.64*/(""" """),_display_(Seq[Any](/*142.66*/transaction/*142.77*/.senderCurrency.code)),format.raw/*142.97*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*143.12*/doku/*143.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"))),format.raw/*143.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*144.12*/transaction/*144.23*/.senderNote)),format.raw/*144.34*/("""</dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*151.12*/doku/*151.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG082","Bill Payment Service"))),format.raw/*151.91*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*152.12*/transactionForm("billPayment.billPaymentService")/*152.61*/.value)),format.raw/*152.67*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*153.12*/doku/*153.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG083","Selected Bill"))),format.raw/*153.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*154.12*/transactionForm("billPayment.selectBill")/*154.53*/.value)),format.raw/*154.59*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*155.12*/doku/*155.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG085","Selected Operator"))),format.raw/*155.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*156.12*/transactionForm("billPayment.selectOperatorName")/*156.61*/.value)),format.raw/*156.67*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*157.12*/doku/*157.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG086","Selected Denom"))),format.raw/*157.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*158.12*/transactionForm("billPayment.selectDenomName")/*158.58*/.value)),format.raw/*158.64*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*159.12*/doku/*159.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG084","Phone Number/Subscriber ID"))),format.raw/*159.97*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*160.12*/transactionForm("billPayment.accountNumber")/*160.56*/.value)),format.raw/*160.62*/("""</dd>
						"""),_display_(Seq[Any](/*161.8*/if(transactionForm("billPayment.selectOperator").value.equals("9950102"))/*161.81*/{_display_(Seq[Any](format.raw/*161.82*/("""
							<dt>"""),_display_(Seq[Any](/*162.13*/doku/*162.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG224","Subscriber Name"))),format.raw/*162.87*/("""</dt>
							<dd>"""),_display_(Seq[Any](/*163.13*/transactionForm("billPayment.accountName")/*163.55*/.value)),format.raw/*163.61*/("""</dd>
						""")))})),format.raw/*164.8*/("""
					</dl>
				</div>
			</div>
		</fieldset>

		<div class="span12">
			<div class="form-actions">
				<button type="button" class="btn btn-primary btn-forward">"""),_display_(Seq[Any](/*172.64*/doku/*172.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*172.130*/(""" <i class="icon-arrow-right"></i></button>
			</div>
		</div>

		<div class="modal fade hide" id="send-confirmation-dialog">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Send confirmation</h3>
			</div>
			<div class="modal-body">
				<div class="control-groups">
					<p>In order to securing your transaction, we are highly advice to ask the customer to directly input their PIN on the form below</p>
				</div>
				<hr/>
				<div class="">
					<p style="text-align: center;"><center>
						<label>Please input new PIN below:</label>
						<div class="control-group">
							<div class="input">
								<input id="auth1" type="password" name="auth1" class="required" minlength="4" maxlength="4" placeholder="New PIN" style="text-align: center;"/>
							</div>
						</div>
						<br/>
						<div class="control-group">
							<div class="input">
								<input id="auth2" type="password" name="auth2" class="required" minlength="4" maxlength="4" placeholder="Confirm new PIN" style="text-align: center;"/>
							</div>
						</div>
						<div id="auth2_field">
						</div>
					</center></p>
				</div>
				<p class="container-error">
					
				</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Cancel</a>
				<button type="submit" class="btn btn-primary">Proceed <i class="icon-arrow-right"></i></button>
			</div>
		</div>

		<div class="modal fade hide" id="send-authorization-dialog">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Send require authorization</h3>
			</div>
			<div class="modal-body">
				<div class="control-groups">
					<p>In order to proceed, please ask your supervisor to enter their password in the form below</p>
				</div>
				<hr/>
				<div class="">
					<p style="text-align: center;"><center>
						<label>Supervisor</label>
						<div class="control-group">
							<div class="input">
								<input id="author1" type="text" name="supervisor.email" class="disabled" value=""""),_display_(Seq[Any](/*229.90*/transaction/*229.101*/.supervisor.email)),format.raw/*229.118*/("""" readonly="readonly"/>
							</div>
						</div>
						<br/>
						<div class="control-group">
							<div class="input">
								<input id="author2" type="password" name="supervisor.password" placeholder="Enter supervisor password"/>
							</div>
						</div>
						<div id="author2_field">
						</div>
					</center></p>
				</div>
				<p class="container-error">
					
				</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*247.51*/doku/*247.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*247.116*/("""</a>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*248.52*/doku/*248.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG221","Authorize & Proceed"))),format.raw/*248.130*/("""  <i class="icon-arrow-right"></i></button>
			</div>
		</div>
	""")))})),format.raw/*251.3*/("""

</div>
	""")))})),format.raw/*254.3*/("""
""")))})),format.raw/*255.2*/("""

""")))}/*257.2*/ {_display_(Seq[Any](format.raw/*257.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*258.38*/routes/*258.44*/.Assets.at("javascripts/send.js"))),format.raw/*258.77*/("""'></script>
""")))})),format.raw/*259.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.forms.CashInForm]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.forms.CashInForm]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/confirm_bill_payment.scala.html
                    HASH: e1a1663886d202c088fc14ee7caee02e7e435786
                    MATRIX: 773->1|979->62|1006->132|1042->134|1072->156|1110->157|1179->192|1191->196|1297->281|1347->296|1362->303|1398->317|1485->369|1509->384|1549->386|1663->464|1688->467|1795->538|1810->544|1857->569|4159->2835|4172->2839|4256->2900|4305->2932|4317->2937|4355->2938|4399->2947|4413->2953|4521->3051|4562->3053|4653->3108|4695->3141|4723->3147|4814->3202|4855->3234|4883->3240|4973->3294|5013->3325|5041->3331|5137->3391|5183->3428|5212->3434|5309->3495|5356->3533|5385->3539|5486->3604|5538->3646|5567->3652|5660->3709|5703->3743|5731->3749|5833->3815|5886->3858|5915->3864|6005->3918|6045->3949|6073->3955|6168->4014|6213->4050|6241->4056|6334->4113|6377->4147|6405->4153|6493->4205|6531->4234|6559->4240|6645->4290|6681->4317|6709->4323|6803->4381|6845->4414|6873->4420|6967->4478|7011->4513|7039->4519|7132->4576|7175->4610|7203->4616|7299->4676|7345->4713|7374->4719|7466->4775|7508->4808|7536->4814|7633->4875|7680->4913|7709->4919|7808->4982|7858->5022|7887->5028|7982->5087|8027->5123|8055->5129|8159->5197|8214->5242|8243->5248|8348->5317|8404->5363|8433->5369|8540->5440|8598->5488|8627->5494|8718->5549|8759->5581|8787->5587|8882->5646|8927->5682|8955->5688|9048->5745|9091->5779|9119->5785|9213->5843|9257->5878|9285->5884|9383->5946|9430->5984|9459->5990|9558->6053|9608->6093|9637->6099|9735->6161|9783->6200|9812->6206|9913->6271|9965->6313|9994->6319|10092->6380|10140->6418|10170->6424|10273->6490|10327->6533|10357->6539|10462->6607|10518->6652|10548->6658|10649->6722|10701->6763|10731->6769|10830->6831|10879->6870|10909->6876|11009->6939|11060->6979|11090->6985|11188->7046|11236->7084|11266->7090|11367->7154|11419->7195|11449->7201|11560->7275|11620->7324|11650->7330|11751->7394|11803->7435|11833->7441|11938->7509|11994->7554|12024->7560|12126->7625|12179->7667|12209->7673|12313->7740|12368->7784|12398->7790|12500->7855|12553->7897|12583->7903|12692->7975|12752->8024|12782->8030|12888->8099|12945->8145|12975->8151|13072->8211|13119->8248|13149->8254|13193->8262|13232->8291|13288->8308|13501->8484|13515->8488|13600->8550|13654->8567|13675->8578|13711->8591|13765->8608|13779->8612|13873->8683|13927->8700|13948->8711|13995->8735|14049->8752|14063->8756|14154->8824|14208->8841|14229->8852|14268->8868|14322->8885|14336->8889|14428->8958|14482->8975|14503->8986|14545->9005|14599->9022|14613->9026|14708->9098|14762->9115|14783->9126|14830->9150|14884->9167|14898->9171|14979->9229|15033->9246|15054->9257|15087->9267|15126->9269|15147->9280|15190->9300|15244->9317|15258->9321|15350->9390|15404->9407|15425->9418|15462->9432|15501->9434|15522->9445|15570->9470|15624->9487|15638->9491|15729->9559|15783->9576|15804->9587|15832->9592|15886->9609|15900->9613|15995->9685|16049->9702|16070->9713|16134->9754|16173->9756|16194->9767|16237->9787|16291->9804|16305->9808|16398->9878|16452->9895|16473->9906|16507->9917|16681->10054|16695->10058|16793->10133|16847->10150|16906->10199|16935->10205|16989->10222|17003->10226|17094->10294|17148->10311|17199->10352|17228->10358|17282->10375|17296->10379|17391->10451|17445->10468|17504->10517|17533->10523|17587->10540|17601->10544|17693->10613|17747->10630|17803->10676|17832->10682|17886->10699|17900->10703|18004->10784|18058->10801|18112->10845|18141->10851|18190->10864|18273->10937|18313->10938|18363->10951|18377->10955|18470->11025|18525->11043|18577->11085|18606->11091|18651->11104|18852->11268|18866->11272|18952->11334|21074->13419|21096->13430|21137->13447|21649->13922|21663->13926|21748->13987|21841->14043|21855->14047|21953->14121|22050->14186|22093->14197|22127->14199|22149->14202|22189->14204|22264->14242|22280->14248|22336->14281|22381->14294
                    LINES: 26->1|34->1|35->5|36->6|36->6|36->6|38->8|38->8|38->8|41->11|41->11|41->11|44->14|44->14|44->14|47->17|47->17|51->21|51->21|51->21|89->59|89->59|89->59|92->62|92->62|92->62|94->64|94->64|94->64|94->64|96->66|96->66|96->66|97->67|97->67|97->67|98->68|98->68|98->68|99->69|99->69|99->69|100->70|100->70|100->70|101->71|101->71|101->71|102->72|102->72|102->72|103->73|103->73|103->73|104->74|104->74|104->74|105->75|105->75|105->75|106->76|106->76|106->76|107->77|107->77|107->77|108->78|108->78|108->78|110->80|110->80|110->80|111->81|111->81|111->81|112->82|112->82|112->82|113->83|113->83|113->83|114->84|114->84|114->84|115->85|115->85|115->85|116->86|116->86|116->86|117->87|117->87|117->87|118->88|118->88|118->88|119->89|119->89|119->89|120->90|120->90|120->90|121->91|121->91|121->91|122->92|122->92|122->92|123->93|123->93|123->93|124->94|124->94|124->94|126->96|126->96|126->96|127->97|127->97|127->97|128->98|128->98|128->98|129->99|129->99|129->99|130->100|130->100|130->100|131->101|131->101|131->101|132->102|132->102|132->102|133->103|133->103|133->103|134->104|134->104|134->104|135->105|135->105|135->105|136->106|136->106|136->106|137->107|137->107|137->107|139->109|139->109|139->109|140->110|140->110|140->110|141->111|141->111|141->111|142->112|142->112|142->112|143->113|143->113|143->113|144->114|144->114|144->114|145->115|145->115|145->115|146->116|146->116|146->116|147->117|147->117|147->117|149->119|149->119|149->119|155->125|155->125|155->125|156->126|156->126|156->126|157->127|157->127|157->127|158->128|158->128|158->128|159->129|159->129|159->129|160->130|160->130|160->130|161->131|161->131|161->131|162->132|162->132|162->132|163->133|163->133|163->133|164->134|164->134|164->134|165->135|165->135|165->135|166->136|166->136|166->136|166->136|166->136|166->136|167->137|167->137|167->137|168->138|168->138|168->138|168->138|168->138|168->138|169->139|169->139|169->139|170->140|170->140|170->140|171->141|171->141|171->141|172->142|172->142|172->142|172->142|172->142|172->142|173->143|173->143|173->143|174->144|174->144|174->144|181->151|181->151|181->151|182->152|182->152|182->152|183->153|183->153|183->153|184->154|184->154|184->154|185->155|185->155|185->155|186->156|186->156|186->156|187->157|187->157|187->157|188->158|188->158|188->158|189->159|189->159|189->159|190->160|190->160|190->160|191->161|191->161|191->161|192->162|192->162|192->162|193->163|193->163|193->163|194->164|202->172|202->172|202->172|259->229|259->229|259->229|277->247|277->247|277->247|278->248|278->248|278->248|281->251|284->254|285->255|287->257|287->257|288->258|288->258|288->258|289->259
                    -- GENERATED --
                */
            