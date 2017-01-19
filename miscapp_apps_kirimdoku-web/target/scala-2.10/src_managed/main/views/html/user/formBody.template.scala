
package views.html.user

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
object formBody extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.SecurityRole,Form[models.User],scala.Boolean,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(role:models.SecurityRole, myForm: Form[models.User], isEditable:scala.Boolean=false):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._


Seq[Any](format.raw/*1.87*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*7.1*/("""

<fieldset>
	<legend>"""),_display_(Seq[Any](/*10.11*/doku/*10.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG376","Basic information"))),format.raw/*10.87*/("""</legend>
	<div class="row">
		<div class="span6 control-group">
			<input type="hidden" name="primaryRole" value=""""),_display_(Seq[Any](/*13.52*/myForm("primaryRole")/*13.73*/.value)),format.raw/*13.79*/(""""/>
			"""),_display_(Seq[Any](/*14.5*/select(
				myForm("primaryRole")
				, options(models.User.optionsRoles)
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG377","Privilege")
				, '_class -> "required"
				, 'class -> "required"
				, 'readOnly -> "true"
				, 'disabled -> "true"
			))),format.raw/*23.5*/("""
			"""),_display_(Seq[Any](/*24.5*/restrictOr(la(as("admin")))/*24.32*/ {_display_(Seq[Any](format.raw/*24.34*/("""
			"""),_display_(Seq[Any](/*25.5*/select(
				myForm("status")
				, options("INACTIVE" -> "Inactive", "ACTIVE" -> "Active", "WEAKPASSWORD" -> "Active WeakPassword")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status")
				, '_class -> "required"
				, 'class -> "required"
			))),format.raw/*32.5*/("""
			""")))}/*33.5*/ {_display_(Seq[Any](format.raw/*33.7*/("""
			<input type="hidden" name="status" value=""""),_display_(Seq[Any](/*34.47*/myForm("status")/*34.63*/.value)),format.raw/*34.69*/(""""/>
			""")))})),format.raw/*35.5*/("""
		</div>
		<div class="span6 control-group">
			<input type="hidden" name="corporate.code" value=""""),_display_(Seq[Any](/*38.55*/myForm("corporate.code")/*38.79*/.value)),format.raw/*38.85*/(""""/>
			"""),_display_(Seq[Any](/*39.5*/select(
				myForm("corporate.code")
				, options(models.Corporate.options)
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG133","Corporate")
				, '_class -> "required"
				, 'class -> "required"
				, 'placeHolder -> ""
				, 'disabled -> "true"
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*49.5*/("""

			"""),_display_(Seq[Any](/*51.5*/restrict(la(as("admin")))/*51.30*/ {_display_(Seq[Any](format.raw/*51.32*/("""
			"""),_display_(Seq[Any](/*52.5*/role/*52.9*/.role/*52.14*/ match/*52.20*/ {/*53.4*/case "admin" =>/*53.19*/ {_display_(Seq[Any](format.raw/*53.21*/("""
			""")))}/*55.4*/case "admin_finance" =>/*55.27*/ {_display_(Seq[Any](format.raw/*55.29*/("""
			""")))}/*57.4*/case "admin_operasional" =>/*57.31*/ {_display_(Seq[Any](format.raw/*57.33*/("""
			""")))}/*59.4*/case "finance" =>/*59.21*/ {_display_(Seq[Any](format.raw/*59.23*/("""
			""")))}/*61.4*/case "mainagent" =>/*61.23*/ {_display_(Seq[Any](format.raw/*61.25*/("""
				<input type="hidden" name="supervisor.id" value=""""),_display_(Seq[Any](/*62.55*/session/*62.62*/.get("userId"))),format.raw/*62.76*/(""""/>
			""")))}/*64.4*/case "supervisor" =>/*64.24*/ {_display_(Seq[Any](format.raw/*64.26*/("""
				"""),_display_(Seq[Any](/*65.6*/select(
					myForm("supervisor.id")
					, options(models.User.optionsMainAgentForUser(myForm.get))
					, '_showConstraints -> false
					, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG379","Main agent")
					, '_class -> "required"
					, 'class -> "required"
					))),format.raw/*72.7*/("""
			""")))}/*74.4*/case "operator" =>/*74.22*/ {_display_(Seq[Any](format.raw/*74.24*/("""
				"""),_display_(Seq[Any](/*75.6*/select(
					myForm("supervisor.id")
					, options(models.User.optionsSupervisorForUser(myForm.get))
					, '_showConstraints -> false
					, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG380","Supervisor")
					, '_class -> "required"
					, 'class -> "required"
					))),format.raw/*82.7*/("""
			""")))}})),format.raw/*84.5*/("""
			""")))})),format.raw/*85.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*90.5*/inputText(
				myForm("email")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG294","Username / Email")
				, '_class -> "required"
				, 'class -> "required"
				, 'placeHolder -> "Email address"
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*98.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*101.5*/if(isEditable)/*101.19*/ {_display_(Seq[Any](format.raw/*101.21*/("""
			"""),_display_(Seq[Any](/*102.5*/inputPassword(
				myForm("password")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG004","Password")
				, '_class -> "required"
				, 'class -> "required"
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
				, Symbol(isEditable match { case true => "" case _ => "disabled"}) -> "true"
			))),format.raw/*111.5*/("""
			""")))})),format.raw/*112.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*117.5*/inputText(
				myForm("firstName")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name")
				, '_class -> "required"
				, 'class -> "required"
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*125.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*128.5*/inputText(
				myForm("lastName")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name")
				, '_class -> "required"
				, 'class -> "required"
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*136.5*/("""
		</div>
	</div>
	<div class="row">
        """),_display_(Seq[Any](/*140.10*/restrict(la(as("admin"), as("admin_operasional"), as("finance")))/*140.75*/ {_display_(Seq[Any](format.raw/*140.77*/("""
		"""),_display_(Seq[Any](/*141.4*/role/*141.8*/.role/*141.13*/ match/*141.19*/ {/*142.3*/case "operator" =>/*142.21*/ {_display_(Seq[Any](format.raw/*142.23*/("""
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*144.5*/input(
				myForm("creditLimit"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG381","Operator limit")
				, '_showConstraints -> false
				, 'class -> "required amount"
				)/*149.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*149.35*/("""
				<div class="input-append">
					<input type="text" name=""""),_display_(Seq[Any](/*151.32*/name)),format.raw/*151.36*/("""" value=""""),_display_(Seq[Any](/*151.46*/value)),format.raw/*151.51*/("""" """),_display_(Seq[Any](/*151.54*/toHtmlArgs(args))),format.raw/*151.70*/("""/>
					<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*152.65*/myForm("corporate.currency.code")/*152.98*/.value)),format.raw/*152.104*/("""</span>
				</div>
				""")))})),format.raw/*154.6*/("""
			"""),_display_(Seq[Any](/*155.5*/input(
				myForm("creditAlertLimit"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG382","Operator Alert limit")
				, '_showConstraints -> false
				, 'class -> "required amount"
				)/*160.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*160.35*/("""
				<div class="input-append">
					<input type="text" name=""""),_display_(Seq[Any](/*162.32*/name)),format.raw/*162.36*/("""" value=""""),_display_(Seq[Any](/*162.46*/value)),format.raw/*162.51*/("""" """),_display_(Seq[Any](/*162.54*/toHtmlArgs(args))),format.raw/*162.70*/("""/>
					<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*163.65*/myForm("corporate.currency.code")/*163.98*/.value)),format.raw/*163.104*/("""</span>
				</div>
				""")))})),format.raw/*165.6*/("""
			"""),_display_(Seq[Any](/*166.5*/input(
				myForm("creditLastBalance"), 
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG383","Operator Last Balance")
				, '_showConstraints -> false
				, 'class -> "required amount"
				)/*171.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*171.35*/("""
				<div class="input-append">
					<input type="text" name=""""),_display_(Seq[Any](/*173.32*/name)),format.raw/*173.36*/("""" value=""""),_display_(Seq[Any](/*173.46*/value)),format.raw/*173.51*/("""" """),_display_(Seq[Any](/*173.54*/toHtmlArgs(args))),format.raw/*173.70*/("""/>
					<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*174.65*/myForm("corporate.currency.code")/*174.98*/.value)),format.raw/*174.104*/("""</span>
				</div>
				""")))})),format.raw/*176.6*/("""
		</div>
		
		""")))}/*180.3*/case _ =>/*180.12*/ {_display_(Seq[Any](format.raw/*180.14*/("""
		""")))}})),format.raw/*182.4*/("""
		""")))})),format.raw/*183.4*/("""
	</div>
</fieldset>
"""),_display_(Seq[Any](/*186.2*/restrictOr(la(as("admin_operasional"), as("finance")))/*186.56*/ {_display_(Seq[Any](format.raw/*186.58*/("""
<fieldset>
	<legend>"""),_display_(Seq[Any](/*188.11*/doku/*188.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG384","Additional information"))),format.raw/*188.92*/("""</legend>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*191.5*/inputText(
				myForm("personalIdType")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG283","Personal ID")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*199.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*202.5*/inputText(
				myForm("personalId")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*210.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*215.5*/inputDate(
				myForm("birthDate")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth")
				, '_class -> ""
				, 'class -> ""
				, 'maxlength -> 10
				, 'min -> "1900-01-01"
				, 'max -> "2099-12-12"
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*225.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*228.5*/inputText(
				myForm("gender")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG117","Gender")
				, '_class -> ""
				, 'class -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*235.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*240.5*/inputText(
				myForm("country.code")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")
				, '_class -> ""
				, 'class -> "country"
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*247.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*250.5*/inputText(
				myForm("cityName")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City")
				, '_class -> ""
				, 'class -> "city"
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*258.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*263.5*/textarea(
				myForm("address")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*271.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*274.5*/inputText(
				myForm("postalCode")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal code")
				, '_class -> ""
				, 'class -> "postcode"
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*282.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*287.5*/inputText(
				myForm("phoneNumber")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone number")
				, '_class -> ""
				, 'class -> "phone"
				, 'placeHolder -> ""
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*295.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*298.5*/inputText(
				myForm("mobileNumber")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG297","Mobile number")
				, '_class -> ""
				, 'class -> "phone"
				, Symbol(isEditable match { case true => "" case _ => "readOnly"}) -> "true"
			))),format.raw/*305.5*/("""
		</div>
	</div>
</fieldset>
""")))}/*309.2*/ {_display_(Seq[Any](format.raw/*309.4*/("""
<fieldset>
	<legend>"""),_display_(Seq[Any](/*311.11*/doku/*311.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG384","Additional information"))),format.raw/*311.92*/("""</legend>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*314.5*/select(
				myForm("personalIdType")
				, options(models.Customer.optionsPersonalIdType)
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG283","Personal ID")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
			))),format.raw/*322.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*325.5*/inputText(
				myForm("personalId")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID number")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
			))),format.raw/*332.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*337.5*/inputDate(
				myForm("birthDate")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth")
				, '_class -> ""
				, 'class -> ""
				, 'maxlength -> 10
				, 'min -> "1900-01-01"
				, 'max -> "2099-12-12"
			))),format.raw/*346.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*349.5*/select(
				myForm("gender")
				, options(models.Customer.Gender.values().toList.map(_.toString))
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG117","Gender")
				, '_class -> ""
				, 'class -> ""
			))),format.raw/*356.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*361.5*/select(
				myForm("country.code")
				, options(models.Country.options)
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality")
				, '_class -> ""
				, 'class -> "country"
			))),format.raw/*368.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*371.5*/inputText(
				myForm("cityName")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City")
				, '_class -> ""
				, 'class -> "city"
				, 'placeHolder -> ""
			))),format.raw/*378.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*383.5*/textarea(
				myForm("address")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
			))),format.raw/*390.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*393.5*/inputText(
				myForm("postalCode")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal code")
				, '_class -> ""
				, 'class -> "postcode"
				, 'placeHolder -> ""
			))),format.raw/*400.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*405.5*/inputText(
				myForm("phoneNumber")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone number")
				, '_class -> ""
				, 'class -> "phone"
				, 'placeHolder -> ""
			))),format.raw/*412.5*/("""
		</div>
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*415.5*/inputText(
				myForm("mobileNumber")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG297","Mobile number")
				, '_class -> ""
				, 'class -> "phone"
			))),format.raw/*421.5*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*426.5*/inputFile(
				myForm("picture")
				, '_showConstraints -> false
				, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG385","Profile picture")
				, '_class -> ""
				, 'class -> ""
				, 'placeHolder -> ""
			))),format.raw/*433.5*/("""
		</div>
	</div>
</fieldset>
""")))})),format.raw/*437.2*/("""
<div class="form-actions">
	<a class="btn btn-inverse btn-back"><i class="icon-remove icon-white"></i> """),_display_(Seq[Any](/*439.78*/doku/*439.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*439.143*/("""</a>
	<button class="btn btn-primary" type="submit"><i class="icon-plus icon-white"></i> """),_display_(Seq[Any](/*440.86*/doku/*440.90*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*440.149*/("""</button>
</div>


"""),format.raw/*648.5*/("""

<script type="text/javascript" src='"""),_display_(Seq[Any](/*650.38*/routes/*650.44*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*650.94*/("""'></script>
"""))}
    }
    
    def render(role:models.SecurityRole,myForm:Form[models.User],isEditable:scala.Boolean): play.api.templates.Html = apply(role,myForm,isEditable)
    
    def f:((models.SecurityRole,Form[models.User],scala.Boolean) => play.api.templates.Html) = (role,myForm,isEditable) => apply(role,myForm,isEditable)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/formBody.scala.html
                    HASH: 1ce6ac58de1a212a63fb7d3a2d4d258401fb7486
                    MATRIX: 776->1|1093->86|1120->138|1147->244|1206->267|1219->271|1313->343|1465->459|1495->480|1523->486|1566->494|1891->798|1931->803|1967->830|2007->832|2047->837|2375->1144|2398->1149|2437->1151|2520->1198|2545->1214|2573->1220|2612->1228|2748->1328|2781->1352|2809->1358|2852->1366|3260->1753|3301->1759|3335->1784|3375->1786|3415->1791|3427->1795|3441->1800|3456->1806|3466->1812|3490->1827|3530->1829|3553->1838|3585->1861|3625->1863|3648->1872|3684->1899|3724->1901|3747->1910|3773->1927|3813->1929|3836->1938|3864->1957|3904->1959|3995->2014|4011->2021|4047->2035|4073->2047|4102->2067|4142->2069|4183->2075|4490->2361|4513->2370|4540->2388|4580->2390|4621->2396|4929->2683|4966->2693|5002->2698|5114->2775|5470->3110|5556->3160|5580->3174|5621->3176|5662->3181|6086->3583|6123->3588|6236->3665|6578->3985|6664->4035|7004->4353|7087->4399|7162->4464|7203->4466|7243->4470|7256->4474|7271->4479|7287->4485|7298->4490|7326->4508|7367->4510|7444->4551|7649->4747|7717->4776|7817->4839|7844->4843|7891->4853|7919->4858|7959->4861|7998->4877|8102->4944|8145->4977|8175->4983|8231->5007|8272->5012|8488->5219|8556->5248|8656->5311|8683->5315|8730->5325|8758->5330|8798->5333|8837->5349|8941->5416|8984->5449|9014->5455|9070->5479|9111->5484|9329->5693|9397->5722|9497->5785|9524->5789|9571->5799|9599->5804|9639->5807|9678->5823|9782->5890|9825->5923|9855->5929|9911->5953|9946->5972|9965->5981|10006->5983|10043->5991|10079->5995|10137->6017|10201->6071|10242->6073|10301->6095|10315->6099|10415->6176|10520->6245|10852->6555|10938->6605|11264->6909|11377->6986|11758->7345|11844->7395|12138->7667|12251->7744|12563->8034|12649->8084|12972->8385|13085->8462|13405->8760|13491->8810|13827->9124|13940->9201|14275->9514|14361->9564|14673->9854|14723->9885|14763->9887|14822->9909|14836->9913|14936->9990|15041->10059|15342->10338|15428->10388|15673->10611|15786->10688|16086->10966|16172->11016|16452->11274|16565->11351|16831->11595|16917->11645|17159->11865|17272->11942|17511->12159|17597->12209|17852->12442|17965->12519|18219->12751|18305->12801|18536->13010|18649->13087|18897->13313|18960->13344|19102->13449|19116->13453|19201->13514|19328->13604|19342->13608|19425->13667|19472->21149|19548->21188|19564->21194|19637->21244
                    LINES: 26->1|36->1|37->4|38->7|41->10|41->10|41->10|44->13|44->13|44->13|45->14|54->23|55->24|55->24|55->24|56->25|63->32|64->33|64->33|65->34|65->34|65->34|66->35|69->38|69->38|69->38|70->39|80->49|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->53|83->53|83->53|84->55|84->55|84->55|85->57|85->57|85->57|86->59|86->59|86->59|87->61|87->61|87->61|88->62|88->62|88->62|89->64|89->64|89->64|90->65|97->72|98->74|98->74|98->74|99->75|106->82|107->84|108->85|113->90|121->98|124->101|124->101|124->101|125->102|134->111|135->112|140->117|148->125|151->128|159->136|163->140|163->140|163->140|164->141|164->141|164->141|164->141|164->142|164->142|164->142|166->144|171->149|171->149|173->151|173->151|173->151|173->151|173->151|173->151|174->152|174->152|174->152|176->154|177->155|182->160|182->160|184->162|184->162|184->162|184->162|184->162|184->162|185->163|185->163|185->163|187->165|188->166|193->171|193->171|195->173|195->173|195->173|195->173|195->173|195->173|196->174|196->174|196->174|198->176|201->180|201->180|201->180|202->182|203->183|206->186|206->186|206->186|208->188|208->188|208->188|211->191|219->199|222->202|230->210|235->215|245->225|248->228|255->235|260->240|267->247|270->250|278->258|283->263|291->271|294->274|302->282|307->287|315->295|318->298|325->305|329->309|329->309|331->311|331->311|331->311|334->314|342->322|345->325|352->332|357->337|366->346|369->349|376->356|381->361|388->368|391->371|398->378|403->383|410->390|413->393|420->400|425->405|432->412|435->415|441->421|446->426|453->433|457->437|459->439|459->439|459->439|460->440|460->440|460->440|464->648|466->650|466->650|466->650
                    -- GENERATED --
                */
            