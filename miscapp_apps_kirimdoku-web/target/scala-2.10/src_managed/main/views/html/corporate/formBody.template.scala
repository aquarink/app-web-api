
package views.html.corporate

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
object formBody extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[models.Corporate],Boolean,List[models.Channel],Map[String, String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Corporate], isEditable: Boolean, channels: List[models.Channel], mapChannel: Map[String, String]):play.api.templates.Html = {
        _display_ {import helper._

import twitterBootstrap._

import java.math.BigInteger


Seq[Any](format.raw/*1.120*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*6.1*/("""
<div class="hidden">
"""),_display_(Seq[Any](/*8.2*/input(myForm("creditLimit"))/*8.30*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*8.59*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*9.29*/name)),format.raw/*9.33*/("""" value=""""),_display_(Seq[Any](/*9.43*/value)),format.raw/*9.48*/(""""/>
""")))})),format.raw/*10.2*/("""
"""),_display_(Seq[Any](/*11.2*/input(myForm("creditAlertLimit"))/*11.35*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*11.64*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*12.29*/name)),format.raw/*12.33*/("""" value=""""),_display_(Seq[Any](/*12.43*/value)),format.raw/*12.48*/(""""/>
""")))})),format.raw/*13.2*/("""
"""),_display_(Seq[Any](/*14.2*/input(myForm("customerSendLimit"))/*14.36*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*14.65*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*15.29*/name)),format.raw/*15.33*/("""" value=""""),_display_(Seq[Any](/*15.43*/value)),format.raw/*15.48*/(""""/>
""")))})),format.raw/*16.2*/("""
"""),_display_(Seq[Any](/*17.2*/input(myForm("operation.id"))/*17.31*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*17.60*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*18.29*/name)),format.raw/*18.33*/("""" value=""""),_display_(Seq[Any](/*18.43*/value)),format.raw/*18.48*/(""""/>
""")))})),format.raw/*19.2*/("""
"""),_display_(Seq[Any](/*20.2*/input(myForm("finance.id"))/*20.29*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*20.58*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*21.29*/name)),format.raw/*21.33*/("""" value=""""),_display_(Seq[Any](/*21.43*/value)),format.raw/*21.48*/(""""/>
""")))})),format.raw/*22.2*/("""
"""),_display_(Seq[Any](/*23.2*/input(myForm("settlement.id"))/*23.32*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*23.61*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*24.29*/name)),format.raw/*24.33*/("""" value=""""),_display_(Seq[Any](/*24.43*/value)),format.raw/*24.48*/(""""/>
""")))})),format.raw/*25.2*/("""
</div>

<ul class="nav nav-tabs">
	<li class="active"><a href="#basic" data-toggle="tab">"""),_display_(Seq[Any](/*29.57*/doku/*29.61*/.kirimdoku.util.MultiLanguage.getLanguage("LANG318","Corporate information"))),format.raw/*29.137*/("""</a></li>
	<li><a href="#operation" data-toggle="tab">"""),_display_(Seq[Any](/*30.46*/doku/*30.50*/.kirimdoku.util.MultiLanguage.getLanguage("LANG319","Operation information"))),format.raw/*30.126*/("""</a></li>
	<li><a href="#finance" data-toggle="tab">"""),_display_(Seq[Any](/*31.44*/doku/*31.48*/.kirimdoku.util.MultiLanguage.getLanguage("LANG320","Finance information"))),format.raw/*31.122*/("""</a></li>
	<li><a href="#settlement" data-toggle="tab">"""),_display_(Seq[Any](/*32.47*/doku/*32.51*/.kirimdoku.util.MultiLanguage.getLanguage("LANG321","Bank information"))),format.raw/*32.122*/("""</a></li>
	<li><a href="#configuration" data-toggle="tab">"""),_display_(Seq[Any](/*33.50*/doku/*33.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG322","Configuration"))),format.raw/*33.122*/("""</a></li>
</ul>

<div class="tab-content">
	<div class="tab-pane active" id="basic">
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*40.6*/inputText(myForm("code"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG316","Corporate code"), 
				'_showConstraints -> false, '_class -> "required", 'class -> "code required", 'minlength ->"3", 'maxlength -> "3", 'placeholder -> "3 Char identifier Corporate Code", 'readOnly -> true))),format.raw/*42.190*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*45.6*/inputText(myForm("encryptionKey"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG323","Encryption Key"), 
				'_showConstraints -> false, '_class -> "", 'class -> "code required", 'placeholder -> "Encryption Key", 'readOnly -> true))),format.raw/*47.127*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*52.6*/inputText(myForm("name"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG324","Legal name"), 
				'_showConstraints -> false, '_class -> "required", 'class -> "required", 'minlength ->"3", 'maxlength -> "64", 'placeholder -> "Corporate name"))),format.raw/*54.149*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*57.6*/inputText(myForm("tradeName"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG325","Trade name"), 
				'_showConstraints -> false, 'class -> "", 'minlength ->"3", 'maxlength -> "64", 'placeholder -> "(optional)"))),format.raw/*59.114*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*64.6*/inputText(myForm("businessType"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG326","Business type"), 
				'_showConstraints -> false, 'class -> "", 'minlength ->"3", 'maxlength -> "64"))),format.raw/*66.84*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*71.6*/select(myForm("licenseType"), options("REMITTANCELICENSE" -> "Remittance License"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG327","License type"), 
				'class -> "", '_showConstraints -> false))),format.raw/*73.46*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*78.6*/inputText(myForm("licenseNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG328","License number"), 
				'_showConstraints -> false, 'class -> ""))),format.raw/*80.46*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*85.6*/select(myForm("country.code"), options(models.Country.options), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"), 
				'class -> "country", '_showConstraints -> false))),format.raw/*87.53*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*90.6*/inputText(myForm("cityName"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG119","City"), 
				'_showConstraints -> false, 'class -> "", 'minlength ->"3", 'maxlength -> "64"))),format.raw/*92.84*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*97.6*/select(myForm("currency.code"), options(models.Currency.options), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG087","Currency"), 
				'class -> "currency", '_showConstraints -> false))),format.raw/*99.54*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*104.6*/textarea(myForm("address"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"), 
				'_showConstraints -> false, 'class -> "", 'minlength ->"3", 'maxlength -> "64"))),format.raw/*106.84*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*109.6*/inputText(myForm("postalCode"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal code"), 
				'_showConstraints -> false, 'class -> "postcode", 'minlength ->"4", 'maxlength -> "6"))),format.raw/*111.91*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*116.6*/inputText(myForm("phoneNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone Number"), 
				'_showConstraints -> false, 'class -> "phone", 'minlength ->"4"))),format.raw/*118.69*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*121.6*/inputText(myForm("faxNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG329","Fax Number"), 
				'_showConstraints -> false, 'class -> "phone", 'minlength ->"4"))),format.raw/*123.69*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*128.6*/inputText(myForm("email"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG330","Email address"), 
				'_showConstraints -> false, 'class -> "", 'minlength ->"4", 'maxlength -> "46"))),format.raw/*130.84*/("""
			</div>
		</div>
	</div>

	<div class="tab-pane" id="operation">
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*138.6*/inputText(myForm("operation.name"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG331","PIC name"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*140.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*145.6*/inputText(myForm("operation.phoneNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> "(+62) 21345678"))),format.raw/*147.100*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*150.6*/inputText(myForm("operation.mobileNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG297","Mobile number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> "(+62) 812345667"))),format.raw/*152.101*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*157.6*/inputText(myForm("operation.emailAddress"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG330","Email address"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*159.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*162.6*/inputText(myForm("operation.faxNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG329","Fax number"), 
				'_showConstraints -> false, 
				'_class -> "phone", 'class -> "phone", 'placeholder -> "(+62) 21345678"))),format.raw/*165.77*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*170.6*/select(myForm("operation.country.code"), options(models.Country.options), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*172.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*175.6*/inputText(myForm("operation.city"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG119","City"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*177.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*182.6*/textarea(myForm("operation.address"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*184.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*187.6*/inputText(myForm("operation.postalCode"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal code"), 
				'_showConstraints -> false, '_class -> "", 'class -> "postcode", 'placeholder -> ""))),format.raw/*189.89*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*194.6*/inputText(myForm("operation.csPhoneNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG332","CS Phone number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*196.86*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*199.6*/inputText(myForm("operation.tollPhoneNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG333","CS Toll number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*201.86*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*206.6*/inputText(myForm("operation.csFaxNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG334","CS Fax number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*208.86*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*211.6*/inputText(myForm("operation.csEmailAddress"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG335","CS Email address"), 
				'_showConstraints -> false, '_class -> "", 'class -> "email", 'placeholder -> ""))),format.raw/*213.86*/("""
			</div>
		</div>
	</div>
	<div class="tab-pane" id="finance">
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*220.6*/inputText(myForm("finance.name"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG331","PIC name"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*222.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*227.6*/inputText(myForm("finance.phoneNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*229.86*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*232.6*/inputText(myForm("finance.mobileNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG297","Mobile number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*234.86*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*239.6*/inputText(myForm("finance.emailAddress"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG330","Email address"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*241.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*244.6*/inputText(myForm("finance.faxNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG329","Fax number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*246.86*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*251.6*/select(myForm("finance.country.code"), options(models.Country.options), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*253.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*256.6*/inputText(myForm("finance.city"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG119","City"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*258.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*263.6*/textarea(myForm("finance.address"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*265.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*268.6*/inputText(myForm("finance.postalCode"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal code"), 
				'_showConstraints -> false, '_class -> "", 'class -> "postcode", 'placeholder -> ""))),format.raw/*270.89*/("""
			</div>
		</div>
	</div>
	<div class="tab-pane" id="settlement">
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*277.6*/inputText(myForm("settlement.bankName"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank name"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*279.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*282.6*/inputText(myForm("settlement.accountName"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG336","Account name"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*284.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*289.6*/inputText(myForm("settlement.accountNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*291.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*294.6*/inputText(myForm("settlement.swiftCode"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG064","Swift code"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*296.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*301.6*/inputText(myForm("settlement.name"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG331","PIC name"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*303.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*308.6*/inputText(myForm("settlement.phoneNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*310.86*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*313.6*/inputText(myForm("settlement.faxNumber"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG329","Fax number"), 
				'_showConstraints -> false, '_class -> "", 'class -> "phone", 'placeholder -> ""))),format.raw/*315.86*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*320.6*/select(myForm("settlement.currency.code"), options(models.Currency.options), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG337","Settlement currency"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*322.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*327.6*/textarea(myForm("settlement.bankAddress"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG338","Bank address"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*329.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*334.6*/select(myForm("settlement.country.code"), options(models.Country.options), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*336.81*/("""
			</div>
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*339.6*/inputText(myForm("settlement.province"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG061","Province"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*341.81*/("""
			</div>
		</div>
		<div class="row">
			<div class="span6 control-group">
				"""),_display_(Seq[Any](/*346.6*/inputText(myForm("settlement.city"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"), 
				'_showConstraints -> false, '_class -> "", 'class -> "", 'placeholder -> ""))),format.raw/*348.81*/("""
			</div>
		</div>
	</div>
	<div class="tab-pane" id="configuration">
		<div class="control-group">
			<div class="clearfix" id="configuration_field">
				<label for="configuration">"""),_display_(Seq[Any](/*355.33*/doku/*355.37*/.kirimdoku.util.MultiLanguage.getLanguage("LANG339","Channel option"))),format.raw/*355.106*/("""</label>
				<div class="input">
					"""),_display_(Seq[Any](/*357.7*/for(channel <- channels) yield /*357.31*/{_display_(Seq[Any](format.raw/*357.32*/("""
						"""),_display_(Seq[Any](/*358.8*/if(channel.code.equals("10"))/*358.37*/ {_display_(Seq[Any](format.raw/*358.39*/("""
							<div style="width: 100%;float: left; height: 5px;">&nbsp;</div>
						""")))})),format.raw/*360.8*/("""
						<label class="checkbox inline">
							<input type="checkbox" name="channel[]" id="channel"""),_display_(Seq[Any](/*362.60*/channel/*362.67*/.code)),format.raw/*362.72*/("""" value=""""),_display_(Seq[Any](/*362.82*/channel/*362.89*/.code)),format.raw/*362.94*/("""" """),_display_(Seq[Any](/*362.97*/if(mapChannel.containsKey(channel.code))/*362.137*/{_display_(Seq[Any](format.raw/*362.138*/(""" checked="checked" """)))})),format.raw/*362.158*/("""/>"""),_display_(Seq[Any](/*362.161*/channel/*362.168*/.name)),format.raw/*362.173*/("""
						</label>
					""")))})),format.raw/*364.7*/("""
				</div>
			</div>
		</div>
		
		<div class="control-group">
			"""),_display_(Seq[Any](/*370.5*/input(myForm("permission"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG340","Permission"), 
			'class -> "")/*372.17*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*372.46*/("""
				<label class="checkbox inline">
					<input type="checkbox" name="permission_send" value=""""),_display_(Seq[Any](/*374.60*/models/*374.66*/.Corporate.PERMISSION_SEND)),format.raw/*374.92*/("""" """),_display_(Seq[Any](/*374.95*/{if(value && (Integer.parseInt(value.get) & models.Corporate.PERMISSION_SEND) != 0) "checked=\"checked\""})),format.raw/*374.201*/("""> Sender agent
				</label>
				<label class="checkbox inline">
					<input type="checkbox" name="permission_receive" value=""""),_display_(Seq[Any](/*377.63*/models/*377.69*/.Corporate.PERMISSION_RECEIVE)),format.raw/*377.98*/("""" """),_display_(Seq[Any](/*377.101*/{if(value && (Integer.parseInt(value.get) & models.Corporate.PERMISSION_RECEIVE) != 0) "checked=\"checked\""})),format.raw/*377.210*/("""> Receiver agent
				</label>
			""")))})),format.raw/*379.5*/("""
		</div>

		<div class="control-group">
			"""),_display_(Seq[Any](/*383.5*/checkbox(myForm("configuration.allowHighValueTransfer"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG341","High Value Transfer"), 
			'class -> "", '_text -> "Allow"))),format.raw/*385.36*/("""
		</div>

		<div class="control-group">
			"""),_display_(Seq[Any](/*389.5*/select(myForm("settlement.settlementPeriodDay"), options("1" -> "Daily", "7" -> "Weekly", "30" -> "Monthly"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG342","Settlement option"), 
			'class -> "", '_showConstraints -> false))),format.raw/*391.45*/("""
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*394.5*/inputDate(myForm("settlement.agreementDate"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG343","Agreement date"), 
			'class -> "date", 'maxlength -> "10", '_showConstraints -> false))),format.raw/*396.69*/("""
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*399.5*/input(myForm("settlement.agreementYear"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG344","Years terms"), 
			'class -> "", '_showConstraints -> false)/*401.45*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*401.74*/("""
				<input type="number" name=""""),_display_(Seq[Any](/*402.33*/name)),format.raw/*402.37*/("""" value=""""),_display_(Seq[Any](/*402.47*/value)),format.raw/*402.52*/("""" """),_display_(Seq[Any](/*402.55*/toHtmlArgs(args))),format.raw/*402.71*/("""/>
			""")))})),format.raw/*403.5*/("""
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*406.5*/inputDate(myForm("settlement.agreementExpiredDate"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired date"), 
			'class -> "", 'readOnly -> true, '_showConstraints -> false))),format.raw/*408.64*/("""
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*411.5*/inputDate(myForm("settlement.agreementReminderDate"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG345","Agreement Reminder date"), 
			'class -> "date",  'maxlength -> "10", '_showConstraints -> false))),format.raw/*413.70*/("""
		</div>
		<div class="control-group">
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*418.5*/input(myForm("configuration.notifyUrl"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG346","Outgoing Callback URL"), 
			'class -> "", '_showConstraints -> false)/*420.45*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*420.74*/("""
				<input type="text" name=""""),_display_(Seq[Any](/*421.31*/name)),format.raw/*421.35*/("""" value=""""),_display_(Seq[Any](/*421.45*/value)),format.raw/*421.50*/("""" """),_display_(Seq[Any](/*421.53*/toHtmlArgs(args))),format.raw/*421.69*/(""" style="width: 500px;"/>
			""")))})),format.raw/*422.5*/("""
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*425.5*/input(myForm("configuration.sharedKey"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG347","Shared Key Callback"), 
			'class -> "", '_showConstraints -> false)/*427.45*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*427.74*/("""
				<input type="text" name=""""),_display_(Seq[Any](/*428.31*/name)),format.raw/*428.35*/("""" value=""""),_display_(Seq[Any](/*428.45*/value)),format.raw/*428.50*/("""" """),_display_(Seq[Any](/*428.53*/toHtmlArgs(args))),format.raw/*428.69*/("""/>
			""")))})),format.raw/*429.5*/("""
		</div>
		<div class="control-group">
			"""),_display_(Seq[Any](/*432.5*/input(myForm("configuration.primaryColor"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG348","Primary color")
			)/*434.5*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*434.34*/("""
				<div class="input-append color colorpicker" data-color=""""),_display_(Seq[Any](/*435.62*/value)),format.raw/*435.67*/("""" data-color-format="hex">
					<input type="text" id=""""),_display_(Seq[Any](/*436.30*/id)),format.raw/*436.32*/("""" class="" name=""""),_display_(Seq[Any](/*436.50*/name)),format.raw/*436.54*/("""" class="span2" value=""""),_display_(Seq[Any](/*436.78*/value)),format.raw/*436.83*/(""""/>
					<span class="add-on"><i style="background-color: #333333"></i></span>
				</div>
				<span class="help-block">("""),_display_(Seq[Any](/*439.32*/doku/*439.36*/.kirimdoku.util.MultiLanguage.getLanguage("LANG349","used mostly by navigation header colors, etc"))),format.raw/*439.135*/(""")</span>
			""")))})),format.raw/*440.5*/("""
		</div>

		<div class="control-group">
			"""),_display_(Seq[Any](/*444.5*/input(myForm("configuration.secondaryColor"), 'class -> "required", 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG350","Secondary color")
			)/*446.5*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*446.34*/("""
				<div class="input-append color colorpicker" data-color=""""),_display_(Seq[Any](/*447.62*/value)),format.raw/*447.67*/("""" data-color-format="hex">
					<input type="text" id=""""),_display_(Seq[Any](/*448.30*/id)),format.raw/*448.32*/("""" class="" name=""""),_display_(Seq[Any](/*448.50*/name)),format.raw/*448.54*/("""" class="span2" value=""""),_display_(Seq[Any](/*448.78*/value)),format.raw/*448.83*/(""""/>
					<span class="add-on"><i style="background-color: #333333"></i></span>
				</div>
				<span class="help-block">("""),_display_(Seq[Any](/*451.32*/doku/*451.36*/.kirimdoku.util.MultiLanguage.getLanguage("LANG349","used mostly by navigation font colors, etc"))),format.raw/*451.133*/(""")</span>
			""")))})),format.raw/*452.5*/("""
		</div>

		<div class="control-group">
			"""),_display_(Seq[Any](/*456.5*/inputFile(myForm("configuration.logoPath"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG351","Logo File"), 
			'class -> "", '_help -> "(optimized size for logo is 120 x 120)"))),format.raw/*458.69*/("""
		</div>
	</div>
</div>


<div class="form-actions">
	<button class="btn btn-inverse btn-back"><i class="icon-remove icon-white"></i> """),_display_(Seq[Any](/*465.83*/doku/*465.87*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*465.148*/("""</button>
	<button class="btn btn-primary" type="submit"><i class="icon-plus icon-white"></i> """),_display_(Seq[Any](/*466.86*/doku/*466.90*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*466.149*/("""</button>
</div>

<script src=""""),_display_(Seq[Any](/*469.15*/routes/*469.21*/.Assets.at("javascripts/corporate.js"))),format.raw/*469.59*/("""" type="text/javascript"></script>
"""))}
    }
    
    def render(myForm:Form[models.Corporate],isEditable:Boolean,channels:List[models.Channel],mapChannel:Map[String, String]): play.api.templates.Html = apply(myForm,isEditable,channels,mapChannel)
    
    def f:((Form[models.Corporate],Boolean,List[models.Channel],Map[String, String]) => play.api.templates.Html) = (myForm,isEditable,channels,mapChannel) => apply(myForm,isEditable,channels,mapChannel)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/corporate/formBody.scala.html
                    HASH: 3c8dd8626e4a683cf11f25def3209ae1e4bfdb05
                    MATRIX: 801->1|1069->119|1096->164|1123->194|1180->217|1216->245|1282->274|1346->303|1371->307|1416->317|1442->322|1478->327|1515->329|1557->362|1624->391|1689->420|1715->424|1761->434|1788->439|1824->444|1861->446|1904->480|1971->509|2036->538|2062->542|2108->552|2135->557|2171->562|2208->564|2246->593|2313->622|2378->651|2404->655|2450->665|2477->670|2513->675|2550->677|2586->704|2653->733|2718->762|2744->766|2790->776|2817->781|2853->786|2890->788|2929->818|2996->847|3061->876|3087->880|3133->890|3160->895|3196->900|3323->991|3336->995|3435->1071|3526->1126|3539->1130|3638->1206|3727->1259|3740->1263|3837->1337|3929->1393|3942->1397|4036->1468|4131->1527|4144->1531|4235->1599|4417->1746|4747->2053|4835->2106|5111->2359|5228->2441|5513->2703|5601->2756|5856->2988|5973->3070|6203->3278|6320->3360|6561->3579|6678->3661|6872->3833|6989->3915|7212->4116|7300->4169|7517->4364|7634->4446|7861->4651|7979->4733|8198->4929|8287->4982|8521->5193|8639->5275|8853->5466|8942->5519|9152->5706|9270->5788|9494->5989|9660->6119|9885->6321|10003->6403|10259->6635|10348->6688|10607->6923|10725->7005|10963->7220|11052->7273|11313->7511|11431->7593|11693->7832|11782->7885|12003->8083|12121->8165|12347->8368|12436->8421|12678->8640|12796->8722|13042->8945|13131->8998|13378->9222|13496->9304|13738->9523|13827->9576|14075->9801|14238->9928|14461->10128|14579->10210|14818->10426|14907->10479|15148->10697|15266->10779|15502->10992|15591->11045|15826->11257|15944->11339|16204->11576|16293->11629|16512->11825|16630->11907|16854->12108|16943->12161|17183->12378|17349->12508|17580->12716|17669->12769|17906->12983|18024->13065|18265->13283|18354->13336|18587->13546|18705->13628|18931->13831|19049->13913|19291->14132|19380->14185|19618->14400|19736->14482|20013->14736|20131->14818|20367->15031|20485->15113|20748->15353|20837->15406|21067->15613|21185->15695|21407->15894|21628->16078|21642->16082|21735->16151|21810->16190|21851->16214|21891->16215|21935->16223|21974->16252|22015->16254|22126->16333|22261->16431|22278->16438|22306->16443|22353->16453|22370->16460|22398->16465|22438->16468|22489->16508|22530->16509|22584->16529|22625->16532|22643->16539|22672->16544|22726->16566|22830->16634|22971->16765|23039->16794|23172->16890|23188->16896|23237->16922|23277->16925|23407->17031|23570->17157|23586->17163|23638->17192|23679->17195|23812->17304|23878->17338|23959->17383|24170->17571|24251->17616|24522->17864|24602->17908|24830->18113|24910->18157|25094->18331|25162->18360|25232->18393|25259->18397|25306->18407|25334->18412|25374->18415|25413->18431|25452->18438|25532->18482|25760->18687|25840->18731|26086->18954|26205->19037|26398->19220|26466->19249|26534->19280|26561->19284|26608->19294|26636->19299|26676->19302|26715->19318|26776->19347|26856->19391|27047->19572|27115->19601|27183->19632|27210->19636|27257->19646|27285->19651|27325->19654|27364->19670|27403->19677|27483->19721|27628->19857|27696->19886|27795->19948|27823->19953|27916->20009|27941->20011|27996->20029|28023->20033|28084->20057|28112->20062|28270->20183|28284->20187|28407->20286|28452->20299|28533->20344|28704->20506|28772->20535|28871->20597|28899->20602|28992->20658|29017->20660|29072->20678|29099->20682|29160->20706|29188->20711|29346->20832|29360->20836|29481->20933|29526->20946|29607->20991|29828->21189|30001->21325|30015->21329|30100->21390|30232->21485|30246->21489|30329->21548|30398->21580|30414->21586|30475->21624
                    LINES: 26->1|34->1|35->4|36->6|38->8|38->8|38->8|39->9|39->9|39->9|39->9|40->10|41->11|41->11|41->11|42->12|42->12|42->12|42->12|43->13|44->14|44->14|44->14|45->15|45->15|45->15|45->15|46->16|47->17|47->17|47->17|48->18|48->18|48->18|48->18|49->19|50->20|50->20|50->20|51->21|51->21|51->21|51->21|52->22|53->23|53->23|53->23|54->24|54->24|54->24|54->24|55->25|59->29|59->29|59->29|60->30|60->30|60->30|61->31|61->31|61->31|62->32|62->32|62->32|63->33|63->33|63->33|70->40|72->42|75->45|77->47|82->52|84->54|87->57|89->59|94->64|96->66|101->71|103->73|108->78|110->80|115->85|117->87|120->90|122->92|127->97|129->99|134->104|136->106|139->109|141->111|146->116|148->118|151->121|153->123|158->128|160->130|168->138|170->140|175->145|177->147|180->150|182->152|187->157|189->159|192->162|195->165|200->170|202->172|205->175|207->177|212->182|214->184|217->187|219->189|224->194|226->196|229->199|231->201|236->206|238->208|241->211|243->213|250->220|252->222|257->227|259->229|262->232|264->234|269->239|271->241|274->244|276->246|281->251|283->253|286->256|288->258|293->263|295->265|298->268|300->270|307->277|309->279|312->282|314->284|319->289|321->291|324->294|326->296|331->301|333->303|338->308|340->310|343->313|345->315|350->320|352->322|357->327|359->329|364->334|366->336|369->339|371->341|376->346|378->348|385->355|385->355|385->355|387->357|387->357|387->357|388->358|388->358|388->358|390->360|392->362|392->362|392->362|392->362|392->362|392->362|392->362|392->362|392->362|392->362|392->362|392->362|392->362|394->364|400->370|402->372|402->372|404->374|404->374|404->374|404->374|404->374|407->377|407->377|407->377|407->377|407->377|409->379|413->383|415->385|419->389|421->391|424->394|426->396|429->399|431->401|431->401|432->402|432->402|432->402|432->402|432->402|432->402|433->403|436->406|438->408|441->411|443->413|448->418|450->420|450->420|451->421|451->421|451->421|451->421|451->421|451->421|452->422|455->425|457->427|457->427|458->428|458->428|458->428|458->428|458->428|458->428|459->429|462->432|464->434|464->434|465->435|465->435|466->436|466->436|466->436|466->436|466->436|466->436|469->439|469->439|469->439|470->440|474->444|476->446|476->446|477->447|477->447|478->448|478->448|478->448|478->448|478->448|478->448|481->451|481->451|481->451|482->452|486->456|488->458|495->465|495->465|495->465|496->466|496->466|496->466|499->469|499->469|499->469
                    -- GENERATED --
                */
            