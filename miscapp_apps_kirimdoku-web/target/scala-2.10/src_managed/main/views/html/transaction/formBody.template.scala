
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
object formBody extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,models.Transaction,Form[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction, transactionForm: Form[models.Transaction]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.91*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Transaction Refund")/*5.30*/{_display_(Seq[Any](format.raw/*5.31*/("""

"""),_display_(Seq[Any](/*7.2*/partial/*7.9*/.flash_alert())),format.raw/*7.23*/("""

<div class="container-refund-inner">
"""),_display_(Seq[Any](/*10.2*/if(err)/*10.9*/ {_display_(Seq[Any](format.raw/*10.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert-error">"""),_display_(Seq[Any](/*13.30*/err)),format.raw/*13.33*/("""</div>
		</div>
	</div>
""")))}/*16.3*/else/*16.8*/{_display_(Seq[Any](format.raw/*16.9*/("""
	"""),_display_(Seq[Any](/*17.3*/helper/*17.9*/.form(action = routes.Transaction.refund, 'class -> "row form-horizontal")/*17.83*/ {_display_(Seq[Any](format.raw/*17.85*/("""
		<fieldset class="span12">
			<legend>Transaction information</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*22.7*/helper/*22.13*/.inputText(
						transactionForm("idToken"),
						'_label -> "Service",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*27.7*/("""
				</div>
				<div class="span4">
				</div>
				<div class="span4">
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*36.7*/helper/*36.13*/.select(
						transactionForm("beneficiary.country.code"),
						options(models.Country.options),
						'_label -> "Receive country", 
						'_default -> "-- Choose a country --",
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*44.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*47.7*/helper/*47.13*/.inputText(
						transactionForm("beneficiary.cityName"),
						'_label -> "Receive city",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*52.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*55.7*/helper/*55.13*/.inputText(
						transactionForm("forexReference.rate"),
						'_label -> "Exchange rate",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*60.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*65.7*/helper/*65.13*/.inputText(
						transactionForm("senderAmount"),
						'_label -> "Sending amount",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*70.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*73.7*/helper/*73.13*/.inputText(
						transactionForm("beneficiaryAmount"),
						'_label -> "Amount to receive",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*78.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*81.7*/helper/*81.13*/.inputText(
						transactionForm("idToken"),
						'_label -> "Fee",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*86.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*91.7*/helper/*91.13*/.inputText(
						transactionForm("senderAmountCollected"),
						'_label -> "Amount to collect",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*96.7*/("""
				</div>
				<div class="span8">
					"""),_display_(Seq[Any](/*99.7*/helper/*99.13*/.inputText(
						transactionForm("senderNote"),
						'_label -> "Sending purpose",
						'_showConstraints -> false,
						'class -> "input-xlarge",
						'style -> "width: 79.5%"
					))),format.raw/*105.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>Sender information</legend>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*114.7*/helper/*114.13*/.inputText(
						transactionForm("sender.idToken"),
						'_label -> "Customer ID",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*119.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*122.7*/helper/*122.13*/.inputText(
						transactionForm("sender.firstName"),
						'_label -> "First name",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*127.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*130.7*/helper/*130.13*/.inputText(
						transactionForm("sender.lastName"),
						'_label -> "Last name",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*135.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*140.7*/helper/*140.13*/.inputText(
						transactionForm("sender.phoneNumber"),
						'_label -> "Phone number",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*145.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*148.7*/helper/*148.13*/.inputText(
						transactionForm("sender.address"),
						'_label -> "Address",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*153.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*156.7*/helper/*156.13*/.select(
						transactionForm("sender.country.code"),
						options(models.Country.options),
						'_label -> "Nationality", 
						'_default -> "-- Choose a country --",
						'_showConstraints -> false,
						'disabled -> "disabled",
						'class -> "required"
					))),format.raw/*164.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*169.7*/helper/*169.13*/.inputText(
						transactionForm("sender.personalIdType"),
						'_label -> "ID type",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*174.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*177.7*/helper/*177.13*/.inputText(
						transactionForm("sender.personalId"),
						'_label -> "ID number",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*182.7*/("""
				</div>
				<div class="span4">
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*189.7*/helper/*189.13*/.inputText(
						transactionForm("sender.personalidIssueDate"),
						'_label -> "Issue date",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*194.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*197.7*/helper/*197.13*/.inputText(
						transactionForm("sender.personalIdExpireDate"),
						'_label -> "Expired date",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*202.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*205.7*/helper/*205.13*/.select(
						transactionForm("sender.personalIdCountry.code"),
						options(models.Country.options),
						'_label -> "Issuing country", 
						'_default -> "-- Choose a country --",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*212.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*217.7*/helper/*217.13*/.inputText(
						transactionForm("sender.occupation"),
						'_label -> "Occupation",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*222.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*225.7*/helper/*225.13*/.inputText(
						transactionForm("sender.cityName"),
						'_label -> "City",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*230.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*233.7*/helper/*233.13*/.inputText(
						transactionForm("sender.birthDate"),
						'_label -> "Date of birth",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*238.7*/("""
				</div>
			</div>
		</fieldset>
		
		<fieldset class="span12">
			<legend>Receiver information</legend>
				<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*247.7*/helper/*247.13*/.inputText(
						transactionForm("beneficiary.idToken"),
						'_label -> "Customer ID",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*252.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*255.7*/helper/*255.13*/.inputText(
						transactionForm("beneficiary.firstName"),
						'_label -> "First name",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*260.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*263.7*/helper/*263.13*/.inputText(
						transactionForm("beneficiary.lastName"),
						'_label -> "Last name",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*268.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span4">
					"""),_display_(Seq[Any](/*273.7*/helper/*273.13*/.inputText(
						transactionForm("beneficiary.phoneNumber"),
						'_label -> "Phone number",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*278.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*281.7*/helper/*281.13*/.inputText(
						transactionForm("beneficiary.address"),
						'_label -> "Address",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*286.7*/("""
				</div>
				<div class="span4">
					"""),_display_(Seq[Any](/*289.7*/helper/*289.13*/.select(
						transactionForm("beneficiary.country.code"),
						options(models.Country.options),
						'_label -> "Nationality", 
						'_default -> "-- Choose a country --",
						'_showConstraints -> false,
						'class -> "required"
					))),format.raw/*296.7*/("""
				</div>
			</div>
		</fieldset>
	""")))})),format.raw/*300.3*/("""
""")))})),format.raw/*301.2*/("""
</div>
""")))})),format.raw/*303.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction,transactionForm:Form[models.Transaction]): play.api.templates.Html = apply(err,transaction,transactionForm)
    
    def f:((String,models.Transaction,Form[models.Transaction]) => play.api.templates.Html) = (err,transaction,transactionForm) => apply(err,transaction,transactionForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:32 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/formBody.scala.html
                    HASH: a1772b1383a95883a0b1f7eb7be8dbf3e2095302
                    MATRIX: 782->1|998->90|1025->142|1061->144|1097->172|1135->173|1172->176|1186->183|1221->197|1296->237|1311->244|1351->246|1459->318|1484->321|1527->347|1539->352|1577->353|1615->356|1629->362|1712->436|1752->438|1911->562|1926->568|2088->709|2266->852|2281->858|2581->1137|2658->1179|2673->1185|2853->1344|2930->1386|2945->1392|3125->1551|3233->1624|3248->1630|3422->1783|3499->1825|3514->1831|3696->1992|3773->2034|3788->2040|3946->2177|4054->2250|4069->2256|4255->2421|4332->2463|4347->2469|4557->2657|4750->2814|4766->2820|4940->2972|5018->3014|5034->3020|5209->3173|5287->3215|5303->3221|5476->3372|5585->3445|5601->3451|5780->3608|5858->3650|5874->3656|6044->3804|6122->3846|6138->3852|6430->4122|6539->4195|6555->4201|6732->4356|6810->4398|6826->4404|7001->4557|7145->4665|7161->4671|7346->4834|7424->4876|7440->4882|7628->5048|7706->5090|7722->5096|7997->5349|8106->5422|8122->5428|8298->5582|8376->5624|8392->5630|8560->5776|8638->5818|8654->5824|8832->5980|9028->6140|9044->6146|9223->6303|9301->6345|9317->6351|9497->6509|9575->6551|9591->6557|9769->6713|9878->6786|9894->6792|10078->6954|10156->6996|10172->7002|10347->7155|10425->7197|10441->7203|10707->7447|10777->7485|10811->7487|10852->7496
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|36->7|36->7|36->7|39->10|39->10|39->10|42->13|42->13|45->16|45->16|45->16|46->17|46->17|46->17|46->17|51->22|51->22|56->27|65->36|65->36|73->44|76->47|76->47|81->52|84->55|84->55|89->60|94->65|94->65|99->70|102->73|102->73|107->78|110->81|110->81|115->86|120->91|120->91|125->96|128->99|128->99|134->105|143->114|143->114|148->119|151->122|151->122|156->127|159->130|159->130|164->135|169->140|169->140|174->145|177->148|177->148|182->153|185->156|185->156|193->164|198->169|198->169|203->174|206->177|206->177|211->182|218->189|218->189|223->194|226->197|226->197|231->202|234->205|234->205|241->212|246->217|246->217|251->222|254->225|254->225|259->230|262->233|262->233|267->238|276->247|276->247|281->252|284->255|284->255|289->260|292->263|292->263|297->268|302->273|302->273|307->278|310->281|310->281|315->286|318->289|318->289|325->296|329->300|330->301|332->303
                    -- GENERATED --
                */
            