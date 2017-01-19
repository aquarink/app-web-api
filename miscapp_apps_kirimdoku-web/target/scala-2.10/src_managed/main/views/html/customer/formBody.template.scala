
package views.html.customer

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
object formBody extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[models.Customer],Boolean,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Customer], edit:Boolean=false):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.DeadboltViewSupport

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

def /*9.2*/readOnly/*9.10*/:play.api.templates.Html = {_display_(

Seq[Any](format.raw/*9.14*/("""
	"""),_display_(Seq[Any](/*10.3*/if(edit && !DeadboltViewSupport.viewRestrict(la(as("doku"), as("mainagent"), as("supervisor")), null))/*10.105*/ {_display_(Seq[Any](format.raw/*10.107*/("""
	readonly="readonly"
	""")))}/*12.4*/else/*12.9*/{_display_(Seq[Any](format.raw/*12.10*/("""
	""")))})),format.raw/*13.3*/("""
""")))};
Seq[Any](format.raw/*1.53*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*8.1*/("""
"""),format.raw/*14.2*/("""

"""),_display_(Seq[Any](/*16.2*/if(myForm.hasGlobalErrors)/*16.28*/ {_display_(Seq[Any](format.raw/*16.30*/("""
<p class="error">
    """),_display_(Seq[Any](/*18.6*/myForm/*18.12*/.globalError.message)),format.raw/*18.32*/("""
</p>
""")))})),format.raw/*20.2*/("""

"""),_display_(Seq[Any](/*22.2*/if(flash.contains("success"))/*22.31*/ {_display_(Seq[Any](format.raw/*22.33*/("""
<p class="success">
    """),_display_(Seq[Any](/*24.6*/flash/*24.11*/.get("success"))),format.raw/*24.26*/("""
</p>
""")))})),format.raw/*26.2*/("""

"""),_display_(Seq[Any](/*28.2*/input(myForm("agent.id"), '_label -> null)/*28.44*/ {(id, name, value, args) =>_display_(Seq[Any](format.raw/*28.72*/("""
<input type="hidden" name=""""),_display_(Seq[Any](/*29.29*/name)),format.raw/*29.33*/("""" value=""""),_display_(Seq[Any](/*29.43*/{value ?: session.get("userId")})),format.raw/*29.75*/(""""/>
""")))})),format.raw/*30.2*/("""

<fieldset class="fieldset form-cashin-info">
	<div class="control-group span12">
		<div class="clearfix  " id="firstName_field">
		<label>"""),_display_(Seq[Any](/*35.11*/doku/*35.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First Name"))),format.raw/*35.80*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*36.5*/input(myForm("firstName"),
			'_showConstraints -> false,
			'_label -> null,
			'class -> "required firstName",
			'placeholder -> "First name"
			)/*41.5*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*41.34*/("""
			<input type="text" id=""""),_display_(Seq[Any](/*42.28*/id)),format.raw/*42.30*/("""" name=""""),_display_(Seq[Any](/*42.39*/name)),format.raw/*42.43*/("""" value=""""),_display_(Seq[Any](/*42.53*/value)),format.raw/*42.58*/("""" """),_display_(Seq[Any](/*42.61*/readOnly)),format.raw/*42.69*/(""" """),_display_(Seq[Any](/*42.71*/toHtmlArgs(args))),format.raw/*42.87*/(""">
			""")))})),format.raw/*43.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="lastName_field">
		<label>"""),_display_(Seq[Any](/*49.11*/doku/*49.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last Name"))),format.raw/*49.79*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*50.5*/input(myForm("lastName"),
			'_label -> null,
			'_showConstraints -> false,
			'class -> "required lastName",
			'placeHolder -> "Last Name")/*54.32*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*54.61*/("""
			<input type="text" id=""""),_display_(Seq[Any](/*55.28*/id)),format.raw/*55.30*/("""" name=""""),_display_(Seq[Any](/*55.39*/name)),format.raw/*55.43*/("""" value=""""),_display_(Seq[Any](/*55.53*/value)),format.raw/*55.58*/("""" """),_display_(Seq[Any](/*55.61*/readOnly)),format.raw/*55.69*/(""" """),_display_(Seq[Any](/*55.71*/toHtmlArgs(args))),format.raw/*55.87*/(""">
			""")))})),format.raw/*56.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="phoneNumber_field">
			<label>"""),_display_(Seq[Any](/*62.12*/doku/*62.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone Number"))),format.raw/*62.83*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*63.5*/input(myForm("phoneNumber"),
			'_label -> null,
			'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG111","Phone Number"),
			'class -> "required",
			'_showConstraints -> false)/*67.31*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*67.60*/("""
			<input type="text" id=""""),_display_(Seq[Any](/*68.28*/id)),format.raw/*68.30*/("""" name=""""),_display_(Seq[Any](/*68.39*/name)),format.raw/*68.43*/("""" value=""""),_display_(Seq[Any](/*68.53*/value)),format.raw/*68.58*/("""" """),_display_(Seq[Any](/*68.61*/readOnly)),format.raw/*68.69*/(""" """),_display_(Seq[Any](/*68.71*/toHtmlArgs(args))),format.raw/*68.87*/(""">
			""")))})),format.raw/*69.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="personalIdType_field">
			<label>"""),_display_(Seq[Any](/*75.12*/doku/*75.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG054","ID Type"))),format.raw/*75.78*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*76.5*/if(edit && !DeadboltViewSupport.viewRestrict(la(as("doku"), as("mainagent"), as("supervisor")), null))/*76.107*/ {_display_(Seq[Any](format.raw/*76.109*/("""
				"""),_display_(Seq[Any](/*77.6*/input(
				myForm("personalIdType"), 
				'_label -> null, 
				'_showConstraints -> false)/*80.32*/ {(id, name, value, args) =>_display_(Seq[Any](format.raw/*80.60*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*81.29*/id)),format.raw/*81.31*/("""" name=""""),_display_(Seq[Any](/*81.40*/name)),format.raw/*81.44*/("""" value=""""),_display_(Seq[Any](/*81.54*/value)),format.raw/*81.59*/("""" """),_display_(Seq[Any](/*81.62*/readOnly)),format.raw/*81.70*/(""" """),_display_(Seq[Any](/*81.72*/toHtmlArgs(args))),format.raw/*81.88*/(""">
				""")))})),format.raw/*82.6*/("""
			""")))}/*83.6*/else/*83.11*/{_display_(Seq[Any](format.raw/*83.12*/("""
				"""),_display_(Seq[Any](/*84.6*/select(
				myForm("personalIdType"),
				options(models.Customer.optionsPersonalIdType),
				'_label -> null, 
				'_default -> "-- Choose Personal ID Type --",
				'_showConstraints -> false,
				'class -> "personal_id_type required"
				))),format.raw/*91.6*/("""
			""")))})),format.raw/*92.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="personalId_field">
			<label>"""),_display_(Seq[Any](/*98.12*/doku/*98.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID Number"))),format.raw/*98.80*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*99.5*/input(myForm("personalId"),
			'_label -> null,
			'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG055","ID Number"),
			'rel -> "popover")/*102.22*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*102.51*/("""
			<input type="text" id=""""),_display_(Seq[Any](/*103.28*/id)),format.raw/*103.30*/("""" name=""""),_display_(Seq[Any](/*103.39*/name)),format.raw/*103.43*/("""" value=""""),_display_(Seq[Any](/*103.53*/value)),format.raw/*103.58*/("""" """),_display_(Seq[Any](/*103.61*/readOnly)),format.raw/*103.69*/(""" """),_display_(Seq[Any](/*103.71*/toHtmlArgs(args))),format.raw/*103.87*/(""">
			""")))})),format.raw/*104.5*/("""
		</div>
	</div>
	
	<div class="control-group span12 hide">
		"""),_display_(Seq[Any](/*109.4*/if(edit && !DeadboltViewSupport.viewRestrict(la(as("doku"), as("mainagent"), as("supervisor")), null))/*109.106*/ {_display_(Seq[Any](format.raw/*109.108*/("""
			"""),_display_(Seq[Any](/*110.5*/input(myForm("gender"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG117","Gender"), 
			'_showConstraints -> false)/*112.31*/ {(id, name, value, args) =>_display_(Seq[Any](format.raw/*112.59*/("""
			<input type="text" id=""""),_display_(Seq[Any](/*113.28*/id)),format.raw/*113.30*/("""" name=""""),_display_(Seq[Any](/*113.39*/name)),format.raw/*113.43*/("""" value=""""),_display_(Seq[Any](/*113.53*/value)),format.raw/*113.58*/("""" """),_display_(Seq[Any](/*113.61*/readOnly)),format.raw/*113.69*/(""" """),_display_(Seq[Any](/*113.71*/toHtmlArgs(args))),format.raw/*113.87*/(""">
			""")))})),format.raw/*114.5*/("""
		""")))}/*115.5*/else/*115.10*/{_display_(Seq[Any](format.raw/*115.11*/("""
			"""),_display_(Seq[Any](/*116.5*/select(myForm("gender"),
			options(models.Customer.Gender.values().toList.map(_.toString)),
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG117","Gender"),
			'_showConstraints -> false))),format.raw/*119.31*/("""
		""")))})),format.raw/*120.4*/("""
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="country.name_field">
			<label>"""),_display_(Seq[Any](/*125.12*/doku/*125.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG530","Nationality"))),format.raw/*125.82*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*126.5*/if(edit && !DeadboltViewSupport.viewRestrict(la(as("doku"), as("mainagent"), as("supervisor")), null))/*126.107*/ {_display_(Seq[Any](format.raw/*126.109*/("""
				<input type="hidden" name="country.code" value=""""),_display_(Seq[Any](/*127.54*/myForm("country.code")/*127.76*/.value)),format.raw/*127.82*/(""""/>
				"""),_display_(Seq[Any](/*128.6*/input(myForm("country.name"), 
				'_label -> null, 
				'_showConstraints -> false)/*130.32*/ {(id, name, value, args) =>_display_(Seq[Any](format.raw/*130.60*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*131.29*/id)),format.raw/*131.31*/("""" name=""""),_display_(Seq[Any](/*131.40*/name)),format.raw/*131.44*/("""" value=""""),_display_(Seq[Any](/*131.54*/value)),format.raw/*131.59*/("""" """),_display_(Seq[Any](/*131.62*/readOnly)),format.raw/*131.70*/(""" """),_display_(Seq[Any](/*131.72*/toHtmlArgs(args))),format.raw/*131.88*/(""">
				""")))})),format.raw/*132.6*/("""
			""")))}/*133.6*/else/*133.11*/{_display_(Seq[Any](format.raw/*133.12*/("""
				"""),_display_(Seq[Any](/*134.6*/select(
				myForm("country.code"),
				options(models.Country.options),
				'_label -> null, 
				'_default -> "-- Choose a country --",
				'_showConstraints -> false,
				'class -> "country_id required"
				))),format.raw/*141.6*/("""
			""")))})),format.raw/*142.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix" style="margin-left: -20px;" id="birthDate_field">
		    <label style="margin-left: 20px" for="birthDate">"""),_display_(Seq[Any](/*148.57*/doku/*148.61*/.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of Birth"))),format.raw/*148.129*/(""" <b style="color: red">*</b></label>
		    <div class="input">
				<div class="input" id="birthDate" data-date=""""),_display_(Seq[Any](/*150.51*/myForm("birthDate")/*150.70*/.valueOr("1970-01-01"))),format.raw/*150.92*/("""" data-date-format="yyyy-mm-dd">
		    		<input name="birthDate" size="16" type="date" value=""""),_display_(Seq[Any](/*151.63*/myForm("birthDate")/*151.82*/.value)),format.raw/*151.88*/("""" """),_display_(Seq[Any](/*151.91*/readOnly)),format.raw/*151.99*/(""">
		    	</div>
<!-- 		
		        <span class="help-inline"></span>
		        <span class="help-block"></span>  -->
		    </div>
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="occupation_field">
			<label>"""),_display_(Seq[Any](/*162.12*/doku/*162.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"))),format.raw/*162.81*/("""</label>
			"""),_display_(Seq[Any](/*163.5*/inputText(myForm("occupation"), 
			'_label -> null,
			'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation")
			))),format.raw/*166.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="cityName_field">
			<label>"""),_display_(Seq[Any](/*172.12*/doku/*172.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"))),format.raw/*172.81*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*173.5*/input(myForm("cityName"),
			'_label -> null,
			'_showConstraints -> false,
			'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG119","City"),
			'class -> "input-city"
			)/*178.5*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*178.34*/("""
			<input type="text" id=""""),_display_(Seq[Any](/*179.28*/id)),format.raw/*179.30*/("""" name=""""),_display_(Seq[Any](/*179.39*/name)),format.raw/*179.43*/("""" value=""""),_display_(Seq[Any](/*179.53*/value)),format.raw/*179.58*/("""" """),_display_(Seq[Any](/*179.61*/readOnly)),format.raw/*179.69*/(""" """),_display_(Seq[Any](/*179.71*/toHtmlArgs(args))),format.raw/*179.87*/(""">
			""")))})),format.raw/*180.5*/("""
		</div>
	</div>
	
	<div class="control-group span12 hide">
		"""),_display_(Seq[Any](/*185.4*/input(myForm("personalIdIssueDate"),
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue Date"),
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG120","Issue Date"),
		'_showConstraints -> false)/*188.30*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*188.59*/("""
		<input type="text" id=""""),_display_(Seq[Any](/*189.27*/id)),format.raw/*189.29*/("""" name=""""),_display_(Seq[Any](/*189.38*/name)),format.raw/*189.42*/("""" value=""""),_display_(Seq[Any](/*189.52*/value)),format.raw/*189.57*/("""" """),_display_(Seq[Any](/*189.60*/readOnly)),format.raw/*189.68*/(""" """),_display_(Seq[Any](/*189.70*/toHtmlArgs(args))),format.raw/*189.86*/(""">
		""")))})),format.raw/*190.4*/("""
	</div>
	
	<div class="control-group span12 hide">
		"""),_display_(Seq[Any](/*194.4*/input(myForm("personalIdExpireDate"),
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired Date"),
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG121","Expired Date"),
		'_showConstraints -> false)/*197.30*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*197.59*/("""
		<input type="text" id=""""),_display_(Seq[Any](/*198.27*/id)),format.raw/*198.29*/("""" name=""""),_display_(Seq[Any](/*198.38*/name)),format.raw/*198.42*/("""" value=""""),_display_(Seq[Any](/*198.52*/value)),format.raw/*198.57*/("""" """),_display_(Seq[Any](/*198.60*/readOnly)),format.raw/*198.68*/(""" """),_display_(Seq[Any](/*198.70*/toHtmlArgs(args))),format.raw/*198.86*/(""">
		""")))})),format.raw/*199.4*/("""
	</div>
	
	<div class="control-group span12 hide"><legend>"""),_display_(Seq[Any](/*202.50*/doku/*202.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG284","Additional Info"))),format.raw/*202.124*/("""</legend></div>
	
	<div class="control-group span12 hide">
		"""),_display_(Seq[Any](/*205.4*/inputText(myForm("email"), 
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG002","Email"), 
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG002","Email"),
		'class -> "email"))),format.raw/*208.21*/("""
		"""),_display_(Seq[Any](/*209.4*/inputText(myForm("postalCode"), 
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal Code"), 
		'_showConstraints -> false
		))),format.raw/*212.4*/("""
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="address_field">
			<label>"""),_display_(Seq[Any](/*217.12*/doku/*217.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"))),format.raw/*217.78*/(""" <b style="color: red">*</b></label>
			"""),_display_(Seq[Any](/*218.5*/textarea(myForm("address"), 
			'_label -> null, 
			'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"),
			'rows -> "5", 
			'style -> "min-height:90px"
			))),format.raw/*223.5*/("""
		</div>
	</div>
	
	<div class="control-group span12 hide">
		"""),_display_(Seq[Any](/*228.4*/inputText(myForm("taxId"), 
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG125","Tax ID"),
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG125","Tax ID")
		))),format.raw/*231.4*/("""
	</div>
	
	<div class="control-group span12"><legend>"""),_display_(Seq[Any](/*234.45*/doku/*234.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG531","Bank Info"))),format.raw/*234.113*/("""</legend></div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="beneficiaryAccount.bank.codefield">
			<label>"""),_display_(Seq[Any](/*238.12*/doku/*238.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG063","Bank Name"))),format.raw/*238.80*/("""</label>
			"""),_display_(Seq[Any](/*239.5*/select(myForm("beneficiaryAccount.bank.code"),
			options(models.Bank.optionsBankId()),
			'_label -> null,
			'_showConstraints -> false
			))),format.raw/*243.5*/("""
		</div>
	</div>
	
	<div class="control-group span12">
		<div class="clearfix  " id="beneficiaryAccount.bank.codefield">
			<label>"""),_display_(Seq[Any](/*249.12*/doku/*249.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Number"))),format.raw/*249.85*/("""</label>
			"""),_display_(Seq[Any](/*250.5*/inputText(myForm("accountNumber"), 
			'_label -> null, 
			'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG065","Account Number")
			))),format.raw/*253.5*/("""
		</div>
	</div>
	
	<b style="color: red">* ) Mandatory</b>
</fieldset>
"""))}
    }
    
    def render(myForm:Form[models.Customer],edit:Boolean): play.api.templates.Html = apply(myForm,edit)
    
    def f:((Form[models.Customer],Boolean) => play.api.templates.Html) = (myForm,edit) => apply(myForm,edit)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/formBody.scala.html
                    HASH: 9dc97185ff4db90ec76b0b7208b85f4d2d4cf8ae
                    MATRIX: 758->1|1079->267|1095->275|1162->279|1200->282|1312->384|1353->386|1395->411|1407->416|1446->417|1480->420|1521->52|1548->104|1575->265|1603->422|1641->425|1676->451|1716->453|1775->477|1790->483|1832->503|1870->510|1908->513|1946->542|1986->544|2047->570|2061->575|2098->590|2136->597|2174->600|2225->642|2291->670|2356->699|2382->703|2428->713|2482->745|2518->750|2695->891|2708->895|2795->960|2871->1001|3028->1150|3095->1179|3159->1207|3183->1209|3228->1218|3254->1222|3300->1232|3327->1237|3366->1240|3396->1248|3434->1250|3472->1266|3509->1272|3658->1385|3671->1389|3757->1453|3833->1494|3984->1636|4051->1665|4115->1693|4139->1695|4184->1704|4210->1708|4256->1718|4283->1723|4322->1726|4352->1734|4390->1736|4428->1752|4465->1758|4618->1875|4631->1879|4720->1946|4796->1987|5001->2183|5068->2212|5132->2240|5156->2242|5201->2251|5227->2255|5273->2265|5300->2270|5339->2273|5369->2281|5407->2283|5445->2299|5482->2305|5638->2425|5651->2429|5735->2491|5811->2532|5923->2634|5964->2636|6005->2642|6105->2733|6171->2761|6236->2790|6260->2792|6305->2801|6331->2805|6377->2815|6404->2820|6443->2823|6473->2831|6511->2833|6549->2849|6587->2856|6610->2862|6623->2867|6662->2868|6703->2874|6965->3115|7001->3120|7153->3236|7166->3240|7252->3304|7328->3345|7496->3503|7564->3532|7629->3560|7654->3562|7700->3571|7727->3575|7774->3585|7802->3590|7842->3593|7873->3601|7912->3603|7951->3619|7989->3625|8089->3689|8202->3791|8244->3793|8285->3798|8432->3935|8499->3963|8564->3991|8589->3993|8635->4002|8662->4006|8709->4016|8737->4021|8777->4024|8808->4032|8847->4034|8886->4050|8924->4056|8947->4061|8961->4066|9001->4067|9042->4072|9269->4276|9305->4280|9451->4389|9465->4393|9554->4459|9631->4500|9744->4602|9786->4604|9877->4658|9909->4680|9938->4686|9983->4695|10077->4779|10144->4807|10210->4836|10235->4838|10281->4847|10308->4851|10355->4861|10383->4866|10423->4869|10454->4877|10493->4879|10532->4895|10571->4902|10595->4908|10609->4913|10649->4914|10691->4920|10924->5131|10961->5136|11184->5322|11198->5326|11290->5394|11440->5507|11469->5526|11514->5548|11646->5643|11675->5662|11704->5668|11744->5671|11775->5679|12056->5923|12070->5927|12158->5992|12207->6005|12375->6151|12526->6265|12540->6269|12628->6334|12705->6375|12905->6566|12973->6595|13038->6623|13063->6625|13109->6634|13136->6638|13183->6648|13211->6653|13251->6656|13282->6664|13321->6666|13360->6682|13398->6688|13498->6752|13747->6991|13815->7020|13879->7047|13904->7049|13950->7058|13977->7062|14024->7072|14052->7077|14092->7080|14123->7088|14162->7090|14201->7106|14238->7111|14329->7166|14583->7410|14651->7439|14715->7466|14740->7468|14786->7477|14813->7481|14860->7491|14888->7496|14928->7499|14959->7507|14998->7509|15037->7525|15074->7530|15171->7590|15185->7594|15279->7664|15377->7726|15612->7938|15652->7942|15825->8093|15966->8197|15980->8201|16065->8263|16142->8304|16354->8494|16454->8558|16671->8753|16763->8808|16777->8812|16865->8876|17033->9007|17047->9011|17134->9075|17183->9088|17347->9230|17517->9363|17531->9367|17623->9436|17672->9449|17848->9603
                    LINES: 26->1|37->9|37->9|39->9|40->10|40->10|40->10|42->12|42->12|42->12|43->13|45->1|46->4|47->8|48->14|50->16|50->16|50->16|52->18|52->18|52->18|54->20|56->22|56->22|56->22|58->24|58->24|58->24|60->26|62->28|62->28|62->28|63->29|63->29|63->29|63->29|64->30|69->35|69->35|69->35|70->36|75->41|75->41|76->42|76->42|76->42|76->42|76->42|76->42|76->42|76->42|76->42|76->42|77->43|83->49|83->49|83->49|84->50|88->54|88->54|89->55|89->55|89->55|89->55|89->55|89->55|89->55|89->55|89->55|89->55|90->56|96->62|96->62|96->62|97->63|101->67|101->67|102->68|102->68|102->68|102->68|102->68|102->68|102->68|102->68|102->68|102->68|103->69|109->75|109->75|109->75|110->76|110->76|110->76|111->77|114->80|114->80|115->81|115->81|115->81|115->81|115->81|115->81|115->81|115->81|115->81|115->81|116->82|117->83|117->83|117->83|118->84|125->91|126->92|132->98|132->98|132->98|133->99|136->102|136->102|137->103|137->103|137->103|137->103|137->103|137->103|137->103|137->103|137->103|137->103|138->104|143->109|143->109|143->109|144->110|146->112|146->112|147->113|147->113|147->113|147->113|147->113|147->113|147->113|147->113|147->113|147->113|148->114|149->115|149->115|149->115|150->116|153->119|154->120|159->125|159->125|159->125|160->126|160->126|160->126|161->127|161->127|161->127|162->128|164->130|164->130|165->131|165->131|165->131|165->131|165->131|165->131|165->131|165->131|165->131|165->131|166->132|167->133|167->133|167->133|168->134|175->141|176->142|182->148|182->148|182->148|184->150|184->150|184->150|185->151|185->151|185->151|185->151|185->151|196->162|196->162|196->162|197->163|200->166|206->172|206->172|206->172|207->173|212->178|212->178|213->179|213->179|213->179|213->179|213->179|213->179|213->179|213->179|213->179|213->179|214->180|219->185|222->188|222->188|223->189|223->189|223->189|223->189|223->189|223->189|223->189|223->189|223->189|223->189|224->190|228->194|231->197|231->197|232->198|232->198|232->198|232->198|232->198|232->198|232->198|232->198|232->198|232->198|233->199|236->202|236->202|236->202|239->205|242->208|243->209|246->212|251->217|251->217|251->217|252->218|257->223|262->228|265->231|268->234|268->234|268->234|272->238|272->238|272->238|273->239|277->243|283->249|283->249|283->249|284->250|287->253
                    -- GENERATED --
                */
            