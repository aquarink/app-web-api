
package views.html.tools

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
object sharesetting extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template7[Integer,java.math.BigDecimal,java.math.BigDecimal,Integer,java.math.BigDecimal,java.math.BigDecimal,models.Corporate,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(foType:Integer, foSSPercentage:java.math.BigDecimal, foSSFix:java.math.BigDecimal, feType:Integer, feSSPercentage:java.math.BigDecimal, feSSFix:java.math.BigDecimal, corporate:models.Corporate):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import java.util.Calendar

import java.text.SimpleDateFormat

def /*11.2*/dateFormat/*11.12*/(d:Date) = {{
    new SimpleDateFormat("yyyy-MM-dd") format d
}};
Seq[Any](format.raw/*1.196*/("""
"""),format.raw/*4.1*/("""
"""),format.raw/*7.1*/("""
"""),format.raw/*10.1*/("""
"""),format.raw/*13.2*/("""

"""),_display_(Seq[Any](/*15.2*/layout("Share Setting", Seq(
routes.Corporate.admin_index().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management"),
"" -> corporate.name,
"" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG436","Share Setting")
))/*19.3*/{_display_(Seq[Any](format.raw/*19.4*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*22.10*/doku/*22.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG437","Share Setting for @corporate.name"))),format.raw/*22.102*/("""</h1>
</div>

<div>
	"""),_display_(Seq[Any](/*26.3*/partial/*26.10*/.flash_alert())),format.raw/*26.24*/("""
	
	<div class="well">
		<form action=""""),_display_(Seq[Any](/*29.18*/routes/*29.24*/.SetupShareSetting.save())),format.raw/*29.49*/("""" method="post">
		<input type="hidden" name="corporate" value=""""),_display_(Seq[Any](/*30.49*/corporate/*30.58*/.code)),format.raw/*30.63*/("""">
		<table cellpadding="3" cellspacing="2">
			<tr>
				<td colspan="3"><b>"""),_display_(Seq[Any](/*33.25*/doku/*33.29*/.kirimdoku.util.MultiLanguage.getLanguage("LANG438","Forex Share Setting"))),format.raw/*33.103*/("""</b></td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="fo" id="percentage" """),_display_(Seq[Any](/*37.53*/if(foType != 2)/*37.68*/{_display_(Seq[Any](format.raw/*37.69*/(""" checked="checked" """)))})),format.raw/*37.89*/(""" value="1" onchange="
					if (!this.checked)"""),format.raw/*38.24*/("""{"""),format.raw/*38.25*/("""
						$('#foSSPercentage').attr('disabled',true);
						$('#foSSPercentage').val('0.00');
						$('#foSSFix').attr('disabled',false);
					"""),format.raw/*42.6*/("""}"""),format.raw/*42.7*/("""else"""),format.raw/*42.11*/("""{"""),format.raw/*42.12*/("""
						$('#foSSPercentage').attr('disabled',false);
						$('#foSSFix').attr('disabled',true);
						$('#foSSFix').val('0.00');
					"""),format.raw/*46.6*/("""}"""),format.raw/*46.7*/("""">
				</td>
				<td>
					"""),_display_(Seq[Any](/*49.7*/doku/*49.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG439","Percentage"))),format.raw/*49.76*/("""
				</td>
				<td>
					<input type="number" id="foSSPercentage" name="forexShareSettingPercentage" """),_display_(Seq[Any](/*52.83*/if(foType == 2)/*52.98*/{_display_(Seq[Any](format.raw/*52.99*/(""" disabled="disabled" """)))})),format.raw/*52.121*/(""" value=""""),_display_(Seq[Any](/*52.130*/foSSPercentage)),format.raw/*52.144*/("""" style="width: 130px;">&nbsp;%
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="fo" id="fix" """),_display_(Seq[Any](/*57.46*/if(foType == 2)/*57.61*/{_display_(Seq[Any](format.raw/*57.62*/(""" checked="checked" """)))})),format.raw/*57.82*/(""" value="2" onchange="
					if (!this.checked)"""),format.raw/*58.24*/("""{"""),format.raw/*58.25*/("""
						$('#foSSPercentage').attr('disabled',false);
						$('#foSSFix').attr('disabled',true);
						$('#foSSFix').val('0.00');
					"""),format.raw/*62.6*/("""}"""),format.raw/*62.7*/("""else"""),format.raw/*62.11*/("""{"""),format.raw/*62.12*/("""
						$('#foSSPercentage').attr('disabled',true);
						$('#foSSPercentage').val('0.00');
						$('#foSSFix').attr('disabled',false);
					"""),format.raw/*66.6*/("""}"""),format.raw/*66.7*/("""">
				</td>
				<td>
					"""),_display_(Seq[Any](/*69.7*/doku/*69.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG440","Fix"))),format.raw/*69.69*/("""
				</td>
				<td>
					<input type="number" id="foSSFix" name="forexShareSettingFix" """),_display_(Seq[Any](/*72.69*/if(foType != 2)/*72.84*/{_display_(Seq[Any](format.raw/*72.85*/(""" disabled="disabled" """)))})),format.raw/*72.107*/(""" value=""""),_display_(Seq[Any](/*72.116*/foSSFix)),format.raw/*72.123*/("""" style="width: 150px;">
				</td>
			</tr>
			<tr>
				<td colspan="3"><br><b>"""),_display_(Seq[Any](/*76.29*/doku/*76.33*/.kirimdoku.util.MultiLanguage.getLanguage("LANG441","Fee Share Setting"))),format.raw/*76.105*/("""</b></td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="fe" id="percentage" """),_display_(Seq[Any](/*80.53*/if(feType != 2)/*80.68*/{_display_(Seq[Any](format.raw/*80.69*/(""" checked="checked" """)))})),format.raw/*80.89*/(""" value="1" onchange="
					if (!this.checked)"""),format.raw/*81.24*/("""{"""),format.raw/*81.25*/("""
						$('#feSSPercentage').attr('disabled',true);
						$('#feSSPercentage').val('0.00');
						$('#feSSFix').attr('disabled',false);
					"""),format.raw/*85.6*/("""}"""),format.raw/*85.7*/("""else"""),format.raw/*85.11*/("""{"""),format.raw/*85.12*/("""
						$('#feSSPercentage').attr('disabled',false);
						$('#feSSFix').attr('disabled',true);
						$('#feSSFix').val('0.00');
					"""),format.raw/*89.6*/("""}"""),format.raw/*89.7*/("""">
				</td>
				<td>
					"""),_display_(Seq[Any](/*92.7*/doku/*92.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG439","Percentage"))),format.raw/*92.76*/("""
				</td>
				<td>
					<input type="number" id="feSSPercentage" name="feeShareSettingPercentage" """),_display_(Seq[Any](/*95.81*/if(feType == 2)/*95.96*/{_display_(Seq[Any](format.raw/*95.97*/(""" disabled="disabled" """)))})),format.raw/*95.119*/(""" value=""""),_display_(Seq[Any](/*95.128*/feSSPercentage)),format.raw/*95.142*/("""" style="width: 130px;">&nbsp;%
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="fe" id="fix" """),_display_(Seq[Any](/*100.46*/if(feType == 2)/*100.61*/{_display_(Seq[Any](format.raw/*100.62*/(""" checked="checked" """)))})),format.raw/*100.82*/(""" value="2" onchange="
					if (!this.checked)"""),format.raw/*101.24*/("""{"""),format.raw/*101.25*/("""
						$('#feSSPercentage').attr('disabled',false);
						$('#feSSFix').attr('disabled',true);
						$('#feSSFix').val('0.00');
					"""),format.raw/*105.6*/("""}"""),format.raw/*105.7*/("""else"""),format.raw/*105.11*/("""{"""),format.raw/*105.12*/("""
						$('#feSSPercentage').attr('disabled',true);
						$('#feSSPercentage').val('0.00');
						$('#feSSFix').attr('disabled',false);
					"""),format.raw/*109.6*/("""}"""),format.raw/*109.7*/("""">
				</td>
				<td>
					"""),_display_(Seq[Any](/*112.7*/doku/*112.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG440","Fix"))),format.raw/*112.69*/("""
				</td>
				<td>
					<input type="number" id="feSSFix" name="feeShareSettingFix" """),_display_(Seq[Any](/*115.67*/if(feType != 2)/*115.82*/{_display_(Seq[Any](format.raw/*115.83*/(""" disabled="disabled" """)))})),format.raw/*115.105*/(""" value=""""),_display_(Seq[Any](/*115.114*/feSSFix)),format.raw/*115.121*/("""" style="width: 100px;">
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td><br>&nbsp;<br>
					<button class="btn btn-primary" type="submit"><i class="icon-ok icon-white"></i> """),_display_(Seq[Any](/*126.88*/doku/*126.92*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*126.151*/("""</button>
					<a class="btn" href=""""),_display_(Seq[Any](/*127.28*/routes/*127.34*/.Corporate.admin_index())),format.raw/*127.58*/(""""><i class="icon-repeat"></i> """),_display_(Seq[Any](/*127.89*/doku/*127.93*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*127.154*/("""</a>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<br>&nbsp;<br>&nbsp;
</div>
<div class="span5" id="viewContainer">
</div>

""")))})),format.raw/*138.2*/("""
"""))}
    }
    
    def render(foType:Integer,foSSPercentage:java.math.BigDecimal,foSSFix:java.math.BigDecimal,feType:Integer,feSSPercentage:java.math.BigDecimal,feSSFix:java.math.BigDecimal,corporate:models.Corporate): play.api.templates.Html = apply(foType,foSSPercentage,foSSFix,feType,feSSPercentage,feSSFix,corporate)
    
    def f:((Integer,java.math.BigDecimal,java.math.BigDecimal,Integer,java.math.BigDecimal,java.math.BigDecimal,models.Corporate) => play.api.templates.Html) = (foType,foSSPercentage,foSSFix,feType,feSSPercentage,feSSFix,corporate) => apply(foType,foSSPercentage,foSSFix,feType,feSSPercentage,feSSFix,corporate)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/sharesetting.scala.html
                    HASH: 47d90dddc5c5e9b58116f1d6d0dd8a7a4fefc036
                    MATRIX: 846->1|1318->418|1337->428|1431->195|1458->247|1485->353|1513->416|1541->491|1579->494|1843->750|1881->751|1954->788|1967->792|2078->880|2135->902|2151->909|2187->923|2263->963|2278->969|2325->994|2426->1059|2444->1068|2471->1073|2584->1150|2597->1154|2694->1228|2818->1316|2842->1331|2881->1332|2933->1352|3006->1397|3035->1398|3202->1538|3230->1539|3262->1543|3291->1544|3451->1677|3479->1678|3542->1706|3555->1710|3642->1775|3780->1877|3804->1892|3843->1893|3898->1915|3944->1924|3981->1938|4130->2051|4154->2066|4193->2067|4245->2087|4318->2132|4347->2133|4507->2266|4535->2267|4567->2271|4596->2272|4763->2412|4791->2413|4854->2441|4867->2445|4947->2503|5071->2591|5095->2606|5134->2607|5189->2629|5235->2638|5265->2645|5381->2725|5394->2729|5489->2801|5613->2889|5637->2904|5676->2905|5728->2925|5801->2970|5830->2971|5997->3111|6025->3112|6057->3116|6086->3117|6246->3250|6274->3251|6337->3279|6350->3283|6437->3348|6573->3448|6597->3463|6636->3464|6691->3486|6737->3495|6774->3509|6924->3622|6949->3637|6989->3638|7042->3658|7116->3703|7146->3704|7307->3837|7336->3838|7369->3842|7399->3843|7567->3983|7596->3984|7660->4012|7674->4016|7755->4074|7878->4160|7903->4175|7943->4176|7999->4198|8046->4207|8077->4214|8338->4438|8352->4442|8435->4501|8509->4538|8525->4544|8572->4568|8640->4599|8654->4603|8739->4664|8901->4794
                    LINES: 26->1|39->11|39->11|42->1|43->4|44->7|45->10|46->13|48->15|52->19|52->19|55->22|55->22|55->22|59->26|59->26|59->26|62->29|62->29|62->29|63->30|63->30|63->30|66->33|66->33|66->33|70->37|70->37|70->37|70->37|71->38|71->38|75->42|75->42|75->42|75->42|79->46|79->46|82->49|82->49|82->49|85->52|85->52|85->52|85->52|85->52|85->52|90->57|90->57|90->57|90->57|91->58|91->58|95->62|95->62|95->62|95->62|99->66|99->66|102->69|102->69|102->69|105->72|105->72|105->72|105->72|105->72|105->72|109->76|109->76|109->76|113->80|113->80|113->80|113->80|114->81|114->81|118->85|118->85|118->85|118->85|122->89|122->89|125->92|125->92|125->92|128->95|128->95|128->95|128->95|128->95|128->95|133->100|133->100|133->100|133->100|134->101|134->101|138->105|138->105|138->105|138->105|142->109|142->109|145->112|145->112|145->112|148->115|148->115|148->115|148->115|148->115|148->115|159->126|159->126|159->126|160->127|160->127|160->127|160->127|160->127|160->127|171->138
                    -- GENERATED --
                */
            