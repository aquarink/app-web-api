
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
object confirm extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Form[models.forms.CashInForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transactionForm: Form[models.forms.CashInForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import partial._


Seq[Any](format.raw/*1.63*/("""
"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Confirmation")/*6.24*/{_display_(Seq[Any](format.raw/*6.25*/("""
<div class="page-header">
   <h2>"""),_display_(Seq[Any](/*8.9*/doku/*8.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG071","Confirmation Page"))),format.raw/*8.85*/("""</h2>
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
			<form action=""""),_display_(Seq[Any](/*21.19*/routes/*21.25*/.Send.create())),format.raw/*21.39*/("""" method="post" enctype="application/x-www-form-urlencoded">
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
	"""),_display_(Seq[Any](/*64.3*/helper/*64.9*/.form(action = routes.Send.process(), 'id -> "send-confirmation-form", 'class -> "row")/*64.96*/ {_display_(Seq[Any](format.raw/*64.98*/("""
	
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
	
	<input type="hidden" name="sender.idToken" value=""""),_display_(Seq[Any](/*80.53*/transactionForm/*80.68*/.get.sender.getIdToken)),format.raw/*80.90*/(""""/>
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

	<input type="hidden" name="beneficiary.idToken" value=""""),_display_(Seq[Any](/*96.58*/transactionForm/*96.73*/.get.beneficiary.getIdToken)),format.raw/*96.100*/(""""/>
	<input type="hidden" name="beneficiary.firstName" value=""""),_display_(Seq[Any](/*97.60*/transactionForm("beneficiary.firstName")/*97.100*/.value)),format.raw/*97.106*/(""""/>
	<input type="hidden" name="beneficiary.lastName" value=""""),_display_(Seq[Any](/*98.59*/transactionForm("beneficiary.lastName")/*98.98*/.value)),format.raw/*98.104*/(""""/>
	<input type="hidden" name="beneficiary.phoneNumber" value=""""),_display_(Seq[Any](/*99.62*/transactionForm("beneficiary.phoneNumber")/*99.104*/.value)),format.raw/*99.110*/(""""/>
	<input type="hidden" name="beneficiary.address" value=""""),_display_(Seq[Any](/*100.58*/transactionForm("beneficiary.address")/*100.96*/.value)),format.raw/*100.102*/(""""/>
	<input type="hidden" name="beneficiary.country.code" value=""""),_display_(Seq[Any](/*101.63*/transactionForm("beneficiary.country.code")/*101.106*/.value)),format.raw/*101.112*/(""""/>

	"""),_display_(Seq[Any](/*103.3*/if(transactionForm("channel.code").value == "06" || transactionForm("channel.code").value == "05" || transactionForm("channel.code").value == "22" || transactionForm("channel.code").value == "07")/*103.199*/ {_display_(Seq[Any](format.raw/*103.201*/("""
		<input type="hidden" name="beneficiaryAccount.bank.code" value=""""),_display_(Seq[Any](/*104.68*/transactionForm("beneficiaryAccount.bank.code")/*104.115*/.value)),format.raw/*104.121*/(""""/>
		<input type="hidden" name="beneficiaryAccount.bank.name" value=""""),_display_(Seq[Any](/*105.68*/transactionForm("beneficiaryAccount.bank.name")/*105.115*/.value)),format.raw/*105.121*/(""""/>
		<input type="hidden" name="beneficiaryAccount.bank.countryCode" value=""""),_display_(Seq[Any](/*106.75*/transactionForm("beneficiary.country.code")/*106.118*/.value)),format.raw/*106.124*/(""""/>
		<input type="hidden" name="beneficiaryAccount.number" value=""""),_display_(Seq[Any](/*107.65*/transactionForm("beneficiaryAccount.number")/*107.109*/.value)),format.raw/*107.115*/(""""/>
		"""),_display_(Seq[Any](/*108.4*/if(transactionForm("beneficiaryAccount.bank.code").value == "BNIAIDJA")/*108.75*/ {_display_(Seq[Any](format.raw/*108.77*/("""
			"""),_display_(Seq[Any](/*109.5*/if(transactionForm("beneficiary.firstName").value != transactionForm("beneficiary.lastName").value)/*109.104*/ {_display_(Seq[Any](format.raw/*109.106*/("""
				<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*110.65*/transactionForm("beneficiary.firstName")/*110.105*/.value)),format.raw/*110.111*/(""" """),_display_(Seq[Any](/*110.113*/transactionForm("beneficiary.lastName")/*110.152*/.value)),format.raw/*110.158*/(""""/>
			""")))}/*111.5*/else/*111.9*/{_display_(Seq[Any](format.raw/*111.10*/("""
				<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*112.65*/transactionForm("beneficiary.firstName")/*112.105*/.value)),format.raw/*112.111*/(""""/>
			""")))})),format.raw/*113.5*/("""
		""")))}/*114.5*/else/*114.10*/{_display_(Seq[Any](format.raw/*114.11*/("""
			"""),_display_(Seq[Any](/*115.5*/if(transactionForm("channel.code").value == "06" || transactionForm("channel.code").value == "07")/*115.103*/ {_display_(Seq[Any](format.raw/*115.105*/("""
				<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*116.65*/transactionForm("beneficiaryAccount.name")/*116.107*/.value)),format.raw/*116.113*/(""""/>
			""")))}/*117.6*/else/*117.11*/{_display_(Seq[Any](format.raw/*117.12*/("""
				"""),_display_(Seq[Any](/*118.6*/if(transactionForm("beneficiary.firstName").value != transactionForm("beneficiary.lastName").value)/*118.105*/ {_display_(Seq[Any](format.raw/*118.107*/("""
					<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*119.66*/transactionForm("beneficiary.firstName")/*119.106*/.value)),format.raw/*119.112*/(""" """),_display_(Seq[Any](/*119.114*/transactionForm("beneficiary.lastName")/*119.153*/.value)),format.raw/*119.159*/(""""/>
				""")))}/*120.6*/else/*120.10*/{_display_(Seq[Any](format.raw/*120.11*/("""
					<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*121.66*/transactionForm("beneficiary.firstName")/*121.106*/.value)),format.raw/*121.112*/(""""/>
				""")))})),format.raw/*122.6*/("""
			""")))})),format.raw/*123.5*/("""
		""")))})),format.raw/*124.4*/("""
		<input type="hidden" name="beneficiaryAccount.city" value=""""),_display_(Seq[Any](/*125.63*/transactionForm("beneficiaryAccount.city")/*125.105*/.value)),format.raw/*125.111*/(""""/>
		<input type="hidden" name="beneficiaryAccount.address" value=""""),_display_(Seq[Any](/*126.66*/transactionForm("beneficiaryAccount.address")/*126.111*/.value)),format.raw/*126.117*/(""""/>
	""")))})),format.raw/*127.3*/("""
	
	"""),_display_(Seq[Any](/*129.3*/if(transactionForm("channel.code").value == "21" || transactionForm("channel.code").value == "22")/*129.101*/ {_display_(Seq[Any](format.raw/*129.103*/("""
		<input type="hidden" name="voucherCode" value=""""),_display_(Seq[Any](/*130.51*/transactionForm("voucherCode")/*130.81*/.value)),format.raw/*130.87*/(""""/>
	""")))})),format.raw/*131.3*/("""
	
	"""),_display_(Seq[Any](/*133.3*/if(transactionForm("channel.code").value == "04")/*133.52*/ {_display_(Seq[Any](format.raw/*133.54*/("""
		<input type="hidden" name="beneficiaryWalletId" value=""""),_display_(Seq[Any](/*134.59*/transactionForm("beneficiaryWalletId")/*134.97*/.value)),format.raw/*134.103*/(""""/>
		<input type="hidden" name="beneficiaryWalletName" value=""""),_display_(Seq[Any](/*135.61*/transactionForm("beneficiaryWalletName")/*135.101*/.value)),format.raw/*135.107*/(""""/>
		<input type="hidden" name="trackingId" value=""""),_display_(Seq[Any](/*136.50*/transactionForm("trackingId")/*136.79*/.value)),format.raw/*136.85*/(""""/>
	""")))})),format.raw/*137.3*/("""
	
	"""),_display_(Seq[Any](/*139.3*/if(transactionForm("channel.code").value == "03")/*139.52*/ {_display_(Seq[Any](format.raw/*139.54*/("""
		<input type="hidden" name="beneficiaryAccount.bank.code" value=""""),_display_(Seq[Any](/*140.68*/transactionForm("beneficiaryAccount.bank.code")/*140.115*/.value)),format.raw/*140.121*/(""""/>
		<input type="hidden" name="beneficiaryAccount.bank.name" value=""""),_display_(Seq[Any](/*141.68*/transactionForm("beneficiaryAccount.bank.name")/*141.115*/.value)),format.raw/*141.121*/(""""/>
		<input type="hidden" name="beneficiaryAccount.bank.countryCode" value=""""),_display_(Seq[Any](/*142.75*/transactionForm("beneficiary.country.code")/*142.118*/.value)),format.raw/*142.124*/(""""/>
		<input type="hidden" name="beneficiaryAccount.number" value=""""),_display_(Seq[Any](/*143.65*/transactionForm("beneficiaryAccount.number")/*143.109*/.value)),format.raw/*143.115*/(""""/>
		<input type="hidden" name="beneficiaryAccount.city" value=""""),_display_(Seq[Any](/*144.63*/transactionForm("beneficiaryAccount.city")/*144.105*/.value)),format.raw/*144.111*/(""""/>
		<input type="hidden" name="beneficiaryAccount.address" value=""""),_display_(Seq[Any](/*145.66*/transactionForm("beneficiaryAccount.address")/*145.111*/.value)),format.raw/*145.117*/(""""/>
		<input type="hidden" name="beneficiaryAccount.inputMode" value=""""),_display_(Seq[Any](/*146.68*/transactionForm("beneficiary.inputMode")/*146.108*/.value)),format.raw/*146.114*/(""""/>
		"""),_display_(Seq[Any](/*147.4*/if(transactionForm("beneficiary.inputMode").value == "PINYIN")/*147.66*/ {_display_(Seq[Any](format.raw/*147.68*/("""
		<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*148.63*/transactionForm("beneficiary.firstName")/*148.103*/.value)),format.raw/*148.109*/(""""/>
		""")))}/*149.4*/else/*149.8*/{_display_(Seq[Any](format.raw/*149.9*/(""" 
			"""),_display_(Seq[Any](/*150.5*/if(transactionForm("beneficiary.inputMode").value == "TCODE")/*150.66*/ {_display_(Seq[Any](format.raw/*150.68*/("""
				"""),_display_(Seq[Any](/*151.6*/transactionForm/*151.21*/.data().put("beneficiaryAccount.name",transactionForm("beneficiary.tcode").value.replace(";", ",")))),format.raw/*151.120*/("""
				"""),_display_(Seq[Any](/*152.6*/if(transactionForm("beneficiaryAccount.name").value.endsWith(","))/*152.72*/ {_display_(Seq[Any](format.raw/*152.74*/("""
		<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*153.63*/transactionForm("beneficiaryAccount.name")/*153.105*/.value.substring(0,transactionForm("beneficiaryAccount.name").value.length()-1))),format.raw/*153.184*/(""""/>
				""")))}/*154.6*/else/*154.10*/{_display_(Seq[Any](format.raw/*154.11*/("""
		<input type="hidden" name="beneficiaryAccount.name" value=""""),_display_(Seq[Any](/*155.63*/transactionForm("beneficiaryAccount.name")/*155.105*/.value)),format.raw/*155.111*/(""""/>
				""")))})),format.raw/*156.6*/("""
			""")))})),format.raw/*157.5*/("""
		""")))})),format.raw/*158.4*/("""
		<input type="hidden" name="beneficiary.tcode" value=""""),_display_(Seq[Any](/*159.57*/transactionForm("beneficiary.tcode")/*159.93*/.value)),format.raw/*159.99*/(""""/>
		<input type="hidden" name="beneficiary.inputMode" value=""""),_display_(Seq[Any](/*160.61*/transactionForm("beneficiary.inputMode")/*160.101*/.value)),format.raw/*160.107*/(""""/>
		<input type="hidden" name="beneficiary.pinyin" value=""""),_display_(Seq[Any](/*161.58*/transactionForm("beneficiary.pinyin")/*161.95*/.value)),format.raw/*161.101*/(""""/>
	""")))})),format.raw/*162.3*/("""
	
	"""),_display_(Seq[Any](/*164.3*/defining(transactionForm.get)/*164.32*/ { transaction =>_display_(Seq[Any](format.raw/*164.49*/("""
	<fieldset class="span12">
			<!--legend>Transaction information</legend-->
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*170.12*/doku/*170.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*170.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*171.12*/transaction/*171.23*/.channel.name)),format.raw/*171.36*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*172.12*/doku/*172.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG041","Receiver country"))),format.raw/*172.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*173.12*/transaction/*173.23*/.beneficiaryCountry.name)),format.raw/*173.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*174.12*/doku/*174.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*174.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*175.12*/transaction/*175.23*/.senderAmountFormat)),format.raw/*175.42*/(""" """),_display_(Seq[Any](/*175.44*/transaction/*175.55*/.senderCurrency.code)),format.raw/*175.75*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*176.12*/doku/*176.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Amount to receive"))),format.raw/*176.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*177.12*/transaction/*177.23*/.beneficiaryAmountFormat)),format.raw/*177.47*/(""" """),_display_(Seq[Any](/*177.49*/transaction/*177.60*/.beneficiaryCurrency.code)),format.raw/*177.85*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*178.12*/doku/*178.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG046","Fee"))),format.raw/*178.74*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*179.12*/transaction/*179.23*/.feesTotal)),format.raw/*179.33*/(""" """),_display_(Seq[Any](/*179.35*/transaction/*179.46*/.senderCurrency.code)),format.raw/*179.66*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*180.12*/doku/*180.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"))),format.raw/*180.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*181.12*/models/*181.18*/.Transaction.formatNumber(transaction.additionalFee))),format.raw/*181.70*/(""" """),_display_(Seq[Any](/*181.72*/transaction/*181.83*/.beneficiaryCurrency.code)),format.raw/*181.108*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*182.12*/doku/*182.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"))),format.raw/*182.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*183.12*/models/*183.18*/.Transaction.formatNumber(transaction.rate))),format.raw/*183.61*/(""" """),_display_(Seq[Any](/*183.63*/transaction/*183.74*/.beneficiaryCurrency.code)),format.raw/*183.99*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*184.12*/doku/*184.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect"))),format.raw/*184.88*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*185.12*/models/*185.18*/.Transaction.formatNumber(transaction.collectAmount.add(transaction.feesTotal)))),format.raw/*185.97*/(""" """),_display_(Seq[Any](/*185.99*/transaction/*185.110*/.senderCurrency.code)),format.raw/*185.130*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*186.12*/doku/*186.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"))),format.raw/*186.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*187.12*/transaction/*187.23*/.senderNote)),format.raw/*187.34*/("""</dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*194.12*/doku/*194.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG072","Customer ID of sender"))),format.raw/*194.92*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*195.12*/transaction/*195.23*/.sender.getIdToken())),format.raw/*195.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*196.12*/doku/*196.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*196.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*197.12*/transaction/*197.23*/.sender.fullName)),format.raw/*197.39*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*198.12*/doku/*198.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG200","Sender phone number"))),format.raw/*198.90*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*199.12*/transaction/*199.23*/.sender.phoneNumber)),format.raw/*199.42*/("""</dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*206.12*/doku/*206.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG073","Customer ID of receiver"))),format.raw/*206.94*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*207.12*/transaction/*207.23*/.beneficiary.getIdToken())),format.raw/*207.48*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*208.12*/doku/*208.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*208.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*209.12*/transaction/*209.23*/.beneficiary.fullName)),format.raw/*209.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*210.12*/doku/*210.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG202","Receiver phone number"))),format.raw/*210.92*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*211.12*/transaction/*211.23*/.beneficiary.phoneNumber)),format.raw/*211.47*/("""</dd>
					</dl>
				</div>
			</div>
			
			"""),_display_(Seq[Any](/*216.5*/if(transaction.channel.code == "06" || transaction.channel.code == "05" || transaction.channel.code == "07")/*216.113*/ {_display_(Seq[Any](format.raw/*216.115*/("""
			"""),_display_(Seq[Any](/*217.5*/defining(transaction.beneficiaryAccount)/*217.45*/ { account =>_display_(Seq[Any](format.raw/*217.58*/("""
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*221.12*/doku/*221.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG496","Bank"))),format.raw/*221.75*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*222.12*/account/*222.19*/.bank.code)),format.raw/*222.29*/(""" - """),_display_(Seq[Any](/*222.33*/account/*222.40*/.bank.name)),format.raw/*222.50*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*223.12*/doku/*223.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG338","Bank address"))),format.raw/*223.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*224.12*/account/*224.19*/.city)),format.raw/*224.24*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*225.12*/doku/*225.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account number"))),format.raw/*225.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*226.12*/account/*226.19*/.number)),format.raw/*226.26*/("""</dd>
						"""),_display_(Seq[Any](/*227.8*/if(transactionForm("channel.code").value == "06")/*227.57*/ {_display_(Seq[Any](format.raw/*227.59*/("""
							"""),_display_(Seq[Any](/*228.9*/if(transaction.beneficiaryAccount.bank.code != "BNIAIDJA")/*228.67*/ {_display_(Seq[Any](format.raw/*228.69*/("""
								<dt>"""),_display_(Seq[Any](/*229.14*/doku/*229.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG336","Account name"))),format.raw/*229.85*/("""</dt>
								<dd>"""),_display_(Seq[Any](/*230.14*/account/*230.21*/.name)),format.raw/*230.26*/("""</dd>
							""")))})),format.raw/*231.9*/("""
						""")))}/*232.8*/else/*232.13*/{_display_(Seq[Any](format.raw/*232.14*/("""
							"""),_display_(Seq[Any](/*233.9*/if(transactionForm("channel.code").value == "07")/*233.58*/ {_display_(Seq[Any](format.raw/*233.60*/("""
								<dt>"""),_display_(Seq[Any](/*234.14*/doku/*234.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG336","Account name"))),format.raw/*234.85*/("""</dt>
								<dd>"""),_display_(Seq[Any](/*235.14*/account/*235.21*/.name)),format.raw/*235.26*/("""</dd>
							""")))})),format.raw/*236.9*/("""
						""")))})),format.raw/*237.8*/("""
					</dl>
				</div>
			</div>
			""")))})),format.raw/*241.5*/("""
			""")))})),format.raw/*242.5*/("""
			"""),_display_(Seq[Any](/*243.5*/if(transactionForm("channel.code").value == "22")/*243.54*/ {_display_(Seq[Any](format.raw/*243.56*/("""
			"""),_display_(Seq[Any](/*244.5*/defining(transaction.beneficiaryAccount)/*244.45*/ { account =>_display_(Seq[Any](format.raw/*244.58*/("""
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*248.12*/doku/*248.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG496","Bank"))),format.raw/*248.75*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*249.12*/account/*249.19*/.bank.code)),format.raw/*249.29*/(""" - """),_display_(Seq[Any](/*249.33*/account/*249.40*/.bank.name)),format.raw/*249.50*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*250.12*/doku/*250.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG338","Bank address"))),format.raw/*250.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*251.12*/account/*251.19*/.address)),format.raw/*251.27*/(""" - """),_display_(Seq[Any](/*251.31*/account/*251.38*/.city)),format.raw/*251.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*252.12*/doku/*252.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account number"))),format.raw/*252.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*253.12*/account/*253.19*/.number)),format.raw/*253.26*/("""</dd>
					</dl>
				</div>
			</div>
			""")))})),format.raw/*257.5*/("""
			""")))})),format.raw/*258.5*/("""
			"""),_display_(Seq[Any](/*259.5*/if(transaction.channel.code == "21" || transaction.channel.code == "22")/*259.77*/ {_display_(Seq[Any](format.raw/*259.79*/("""
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*263.12*/doku/*263.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG214","Voucher Code"))),format.raw/*263.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*264.12*/transaction/*264.23*/.voucherCode)),format.raw/*264.35*/("""</dd>
					</dl>
				</div>
			</div>
			""")))})),format.raw/*268.5*/("""
			
			"""),_display_(Seq[Any](/*270.5*/if(transaction.channel.code == "04")/*270.41*/ {_display_(Seq[Any](format.raw/*270.43*/("""
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*274.12*/doku/*274.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG215","Doku Wallet Id"))),format.raw/*274.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*275.12*/transaction/*275.23*/.beneficiaryWalletId)),format.raw/*275.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*276.12*/doku/*276.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG216","Doku Wallet Name"))),format.raw/*276.87*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*277.12*/transaction/*277.23*/.beneficiaryWalletName)),format.raw/*277.45*/("""</dd>
					</dl>
				</div>
			</div>
			""")))})),format.raw/*281.5*/("""
		</fieldset>

		<div class="span12">
			<div class="form-actions">
				<!--button onclick="window.history.back()" class="btn btn-back" type="reset"><i class="icon-arrow-left"></i> Back</button-->
				<button type="button" class="btn btn-primary btn-forward">"""),_display_(Seq[Any](/*287.64*/doku/*287.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*287.130*/(""" <i class="icon-arrow-right"></i></button>
			</div>
		</div>

		<div class="modal fade hide" id="send-confirmation-dialog">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>"""),_display_(Seq[Any](/*294.10*/doku/*294.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG203","Send confirmation"))),format.raw/*294.86*/("""</h3>
			</div>
			<div class="modal-body">
				<div class="control-groups">
					<p>"""),_display_(Seq[Any](/*298.10*/doku/*298.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG204","In order to securing your transaction, we are highly advice to ask the customer to directly input their PIN on the form below"))),format.raw/*298.194*/("""</p>
				</div>
				<hr/>
				<div class="">
					<p style="text-align: center;"><center>
						<label>"""),_display_(Seq[Any](/*303.15*/doku/*303.19*/.kirimdoku.util.MultiLanguage.getLanguage("LANG205","Please input new PIN below:"))),format.raw/*303.101*/("""</label>
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
				<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*324.51*/doku/*324.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*324.116*/("""</a>
				<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*325.52*/doku/*325.56*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*325.118*/(""" <i class="icon-arrow-right"></i></button>
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
								<input id="author1" type="text" name="supervisor.email" class="disabled" value=""""),_display_(Seq[Any](/*344.90*/transaction/*344.101*/.supervisor.email)),format.raw/*344.118*/("""" readonly="readonly"/>
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
				<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*362.51*/doku/*362.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*362.116*/("""</a>
				<button type="submit" class="btn btn-primary">Authorize &amp; """),_display_(Seq[Any](/*363.68*/doku/*363.72*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*363.134*/("""  <i class="icon-arrow-right"></i></button>
			</div>
		</div>
	""")))})),format.raw/*366.3*/("""

</div>
	""")))})),format.raw/*369.3*/("""
""")))})),format.raw/*370.2*/("""

""")))}/*372.2*/ {_display_(Seq[Any](format.raw/*372.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*373.38*/routes/*373.44*/.Assets.at("javascripts/send.js"))),format.raw/*373.77*/("""'></script>
""")))})),format.raw/*374.2*/("""
"""))}
    }
    
    def render(err:String,transactionForm:Form[models.forms.CashInForm]): play.api.templates.Html = apply(err,transactionForm)
    
    def f:((String,Form[models.forms.CashInForm]) => play.api.templates.Html) = (err,transactionForm) => apply(err,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:29 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/confirm.scala.html
                    HASH: 6db636d9783f00dc36630b65ca3be0c7eb1d8b84
                    MATRIX: 760->1|966->62|993->132|1029->134|1059->156|1097->157|1166->192|1178->196|1271->268|1321->283|1336->290|1372->304|1459->356|1483->371|1523->373|1637->451|1662->454|1769->525|1784->531|1820->545|4122->2811|4135->2815|4219->2876|4268->2908|4280->2913|4318->2914|4362->2923|4376->2929|4472->3016|4512->3018|4603->3073|4645->3106|4673->3112|4764->3167|4805->3199|4833->3205|4923->3259|4963->3290|4991->3296|5087->3356|5133->3393|5162->3399|5259->3460|5306->3498|5335->3504|5436->3569|5488->3611|5517->3617|5610->3674|5653->3708|5681->3714|5783->3780|5836->3823|5865->3829|5955->3883|5995->3914|6023->3920|6118->3979|6163->4015|6191->4021|6284->4078|6327->4112|6355->4118|6443->4170|6481->4199|6509->4205|6595->4255|6631->4282|6659->4288|6753->4346|6777->4361|6821->4383|6915->4441|6959->4476|6987->4482|7080->4539|7123->4573|7151->4579|7247->4639|7293->4676|7322->4682|7414->4738|7456->4771|7484->4777|7581->4838|7628->4876|7657->4882|7756->4945|7806->4985|7835->4991|7930->5050|7975->5086|8003->5092|8107->5160|8162->5205|8191->5211|8296->5280|8352->5326|8381->5332|8488->5403|8546->5451|8575->5457|8666->5512|8707->5544|8735->5550|8830->5609|8875->5645|8903->5651|8996->5708|9039->5742|9067->5748|9161->5806|9205->5841|9233->5847|9331->5909|9355->5924|9405->5951|9504->6014|9554->6054|9583->6060|9681->6122|9729->6161|9758->6167|9859->6232|9911->6274|9940->6280|10038->6341|10086->6379|10116->6385|10219->6451|10273->6494|10303->6500|10346->6507|10553->6703|10595->6705|10700->6773|10758->6820|10788->6826|10896->6897|10954->6944|10984->6950|11099->7028|11153->7071|11183->7077|11288->7145|11343->7189|11373->7195|11416->7202|11497->7273|11538->7275|11579->7280|11689->7379|11731->7381|11833->7446|11884->7486|11914->7492|11954->7494|12004->7533|12034->7539|12061->7547|12074->7551|12114->7552|12216->7617|12267->7657|12297->7663|12337->7671|12360->7676|12374->7681|12414->7682|12455->7687|12564->7785|12606->7787|12708->7852|12761->7894|12791->7900|12818->7909|12832->7914|12872->7915|12914->7921|13024->8020|13066->8022|13169->8088|13220->8128|13250->8134|13290->8136|13340->8175|13370->8181|13398->8190|13412->8194|13452->8195|13555->8261|13606->8301|13636->8307|13677->8316|13714->8321|13750->8325|13850->8388|13903->8430|13933->8436|14039->8505|14095->8550|14125->8556|14163->8562|14204->8567|14313->8665|14355->8667|14443->8718|14483->8748|14512->8754|14550->8760|14591->8765|14650->8814|14691->8816|14787->8875|14835->8913|14865->8919|14966->8983|15017->9023|15047->9029|15137->9082|15176->9111|15205->9117|15243->9123|15284->9128|15343->9177|15384->9179|15489->9247|15547->9294|15577->9300|15685->9371|15743->9418|15773->9424|15888->9502|15942->9545|15972->9551|16077->9619|16132->9663|16162->9669|16265->9735|16318->9777|16348->9783|16454->9852|16510->9897|16540->9903|16648->9974|16699->10014|16729->10020|16772->10027|16844->10089|16885->10091|16985->10154|17036->10194|17066->10200|17092->10207|17105->10211|17144->10212|17186->10218|17257->10279|17298->10281|17340->10287|17365->10302|17488->10401|17530->10407|17606->10473|17647->10475|17747->10538|17800->10580|17903->10659|17931->10668|17945->10672|17985->10673|18085->10736|18138->10778|18168->10784|18209->10793|18246->10798|18282->10802|18376->10859|18422->10895|18451->10901|18552->10965|18603->11005|18633->11011|18731->11072|18778->11109|18808->11115|18846->11121|18887->11126|18926->11155|18982->11172|19195->11348|19209->11352|19294->11414|19348->11431|19369->11442|19405->11455|19459->11472|19473->11476|19567->11547|19621->11564|19642->11575|19689->11599|19743->11616|19757->11620|19849->11689|19903->11706|19924->11717|19966->11736|20005->11738|20026->11749|20069->11769|20123->11786|20137->11790|20232->11862|20286->11879|20307->11890|20354->11914|20393->11916|20414->11927|20462->11952|20516->11969|20530->11973|20611->12031|20665->12048|20686->12059|20719->12069|20758->12071|20779->12082|20822->12102|20876->12119|20890->12123|20982->12192|21036->12209|21052->12215|21127->12267|21166->12269|21187->12280|21236->12305|21290->12322|21304->12326|21395->12394|21449->12411|21465->12417|21531->12460|21570->12462|21591->12473|21639->12498|21693->12515|21707->12519|21802->12591|21856->12608|21872->12614|21974->12693|22013->12695|22035->12706|22079->12726|22133->12743|22147->12747|22240->12817|22294->12834|22315->12845|22349->12856|22523->12993|22537->12997|22636->13073|22690->13090|22711->13101|22754->13121|22808->13138|22822->13142|22911->13208|22965->13225|22986->13236|23025->13252|23079->13269|23093->13273|23190->13347|23244->13364|23265->13375|23307->13394|23481->13531|23495->13535|23596->13613|23650->13630|23671->13641|23719->13666|23773->13683|23787->13687|23878->13755|23932->13772|23953->13783|23997->13804|24051->13821|24065->13825|24164->13901|24218->13918|24239->13929|24286->13953|24368->13999|24487->14107|24529->14109|24570->14114|24620->14154|24672->14167|24809->14267|24823->14271|24905->14330|24959->14347|24976->14354|25009->14364|25050->14368|25067->14375|25100->14385|25154->14402|25168->14406|25258->14473|25312->14490|25329->14497|25357->14502|25411->14519|25425->14523|25517->14592|25571->14609|25588->14616|25618->14623|25667->14636|25726->14685|25767->14687|25812->14696|25880->14754|25921->14756|25972->14770|25986->14774|26076->14841|26132->14860|26149->14867|26177->14872|26223->14886|26250->14894|26264->14899|26304->14900|26349->14909|26408->14958|26449->14960|26500->14974|26514->14978|26604->15045|26660->15064|26677->15071|26705->15076|26751->15090|26791->15098|26860->15135|26897->15140|26938->15145|26997->15194|27038->15196|27079->15201|27129->15241|27181->15254|27318->15354|27332->15358|27414->15417|27468->15434|27485->15441|27518->15451|27559->15455|27576->15462|27609->15472|27663->15489|27677->15493|27767->15560|27821->15577|27838->15584|27869->15592|27910->15596|27927->15603|27955->15608|28009->15625|28023->15629|28115->15698|28169->15715|28186->15722|28216->15729|28290->15771|28327->15776|28368->15781|28450->15853|28491->15855|28628->15955|28642->15959|28732->16026|28786->16043|28807->16054|28842->16066|28916->16108|28961->16117|29007->16153|29048->16155|29185->16255|29199->16259|29291->16328|29345->16345|29366->16356|29409->16376|29463->16393|29477->16397|29571->16468|29625->16485|29646->16496|29691->16518|29765->16560|30063->16821|30077->16825|30163->16887|30436->17123|30450->17127|30545->17199|30668->17285|30682->17289|30886->17469|31027->17573|31041->17577|31147->17659|31902->18377|31916->18381|32001->18442|32094->18498|32108->18502|32194->18564|32939->19272|32961->19283|33002->19300|33514->19775|33528->19779|33613->19840|33722->19912|33736->19916|33822->19978|33919->20043|33962->20054|33996->20056|34018->20059|34058->20061|34133->20099|34149->20105|34205->20138|34250->20151
                    LINES: 26->1|34->1|35->5|36->6|36->6|36->6|38->8|38->8|38->8|41->11|41->11|41->11|44->14|44->14|44->14|47->17|47->17|51->21|51->21|51->21|89->59|89->59|89->59|92->62|92->62|92->62|94->64|94->64|94->64|94->64|96->66|96->66|96->66|97->67|97->67|97->67|98->68|98->68|98->68|99->69|99->69|99->69|100->70|100->70|100->70|101->71|101->71|101->71|102->72|102->72|102->72|103->73|103->73|103->73|104->74|104->74|104->74|105->75|105->75|105->75|106->76|106->76|106->76|107->77|107->77|107->77|108->78|108->78|108->78|110->80|110->80|110->80|111->81|111->81|111->81|112->82|112->82|112->82|113->83|113->83|113->83|114->84|114->84|114->84|115->85|115->85|115->85|116->86|116->86|116->86|117->87|117->87|117->87|118->88|118->88|118->88|119->89|119->89|119->89|120->90|120->90|120->90|121->91|121->91|121->91|122->92|122->92|122->92|123->93|123->93|123->93|124->94|124->94|124->94|126->96|126->96|126->96|127->97|127->97|127->97|128->98|128->98|128->98|129->99|129->99|129->99|130->100|130->100|130->100|131->101|131->101|131->101|133->103|133->103|133->103|134->104|134->104|134->104|135->105|135->105|135->105|136->106|136->106|136->106|137->107|137->107|137->107|138->108|138->108|138->108|139->109|139->109|139->109|140->110|140->110|140->110|140->110|140->110|140->110|141->111|141->111|141->111|142->112|142->112|142->112|143->113|144->114|144->114|144->114|145->115|145->115|145->115|146->116|146->116|146->116|147->117|147->117|147->117|148->118|148->118|148->118|149->119|149->119|149->119|149->119|149->119|149->119|150->120|150->120|150->120|151->121|151->121|151->121|152->122|153->123|154->124|155->125|155->125|155->125|156->126|156->126|156->126|157->127|159->129|159->129|159->129|160->130|160->130|160->130|161->131|163->133|163->133|163->133|164->134|164->134|164->134|165->135|165->135|165->135|166->136|166->136|166->136|167->137|169->139|169->139|169->139|170->140|170->140|170->140|171->141|171->141|171->141|172->142|172->142|172->142|173->143|173->143|173->143|174->144|174->144|174->144|175->145|175->145|175->145|176->146|176->146|176->146|177->147|177->147|177->147|178->148|178->148|178->148|179->149|179->149|179->149|180->150|180->150|180->150|181->151|181->151|181->151|182->152|182->152|182->152|183->153|183->153|183->153|184->154|184->154|184->154|185->155|185->155|185->155|186->156|187->157|188->158|189->159|189->159|189->159|190->160|190->160|190->160|191->161|191->161|191->161|192->162|194->164|194->164|194->164|200->170|200->170|200->170|201->171|201->171|201->171|202->172|202->172|202->172|203->173|203->173|203->173|204->174|204->174|204->174|205->175|205->175|205->175|205->175|205->175|205->175|206->176|206->176|206->176|207->177|207->177|207->177|207->177|207->177|207->177|208->178|208->178|208->178|209->179|209->179|209->179|209->179|209->179|209->179|210->180|210->180|210->180|211->181|211->181|211->181|211->181|211->181|211->181|212->182|212->182|212->182|213->183|213->183|213->183|213->183|213->183|213->183|214->184|214->184|214->184|215->185|215->185|215->185|215->185|215->185|215->185|216->186|216->186|216->186|217->187|217->187|217->187|224->194|224->194|224->194|225->195|225->195|225->195|226->196|226->196|226->196|227->197|227->197|227->197|228->198|228->198|228->198|229->199|229->199|229->199|236->206|236->206|236->206|237->207|237->207|237->207|238->208|238->208|238->208|239->209|239->209|239->209|240->210|240->210|240->210|241->211|241->211|241->211|246->216|246->216|246->216|247->217|247->217|247->217|251->221|251->221|251->221|252->222|252->222|252->222|252->222|252->222|252->222|253->223|253->223|253->223|254->224|254->224|254->224|255->225|255->225|255->225|256->226|256->226|256->226|257->227|257->227|257->227|258->228|258->228|258->228|259->229|259->229|259->229|260->230|260->230|260->230|261->231|262->232|262->232|262->232|263->233|263->233|263->233|264->234|264->234|264->234|265->235|265->235|265->235|266->236|267->237|271->241|272->242|273->243|273->243|273->243|274->244|274->244|274->244|278->248|278->248|278->248|279->249|279->249|279->249|279->249|279->249|279->249|280->250|280->250|280->250|281->251|281->251|281->251|281->251|281->251|281->251|282->252|282->252|282->252|283->253|283->253|283->253|287->257|288->258|289->259|289->259|289->259|293->263|293->263|293->263|294->264|294->264|294->264|298->268|300->270|300->270|300->270|304->274|304->274|304->274|305->275|305->275|305->275|306->276|306->276|306->276|307->277|307->277|307->277|311->281|317->287|317->287|317->287|324->294|324->294|324->294|328->298|328->298|328->298|333->303|333->303|333->303|354->324|354->324|354->324|355->325|355->325|355->325|374->344|374->344|374->344|392->362|392->362|392->362|393->363|393->363|393->363|396->366|399->369|400->370|402->372|402->372|403->373|403->373|403->373|404->374
                    -- GENERATED --
                */
            