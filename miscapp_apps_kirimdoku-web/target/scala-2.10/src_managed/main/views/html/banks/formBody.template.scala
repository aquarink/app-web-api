
package views.html.banks

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
object formBody extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[models.Bank],Boolean,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Bank], edit:Boolean=false):play.api.templates.Html = {
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
Seq[Any](format.raw/*1.49*/("""
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

<div class="well">
	<fieldset class="fieldset">
	<div class="control-group">
		<div class="row-fluid">
			<div class="span6">
				"""),_display_(Seq[Any](/*37.6*/input(myForm("name"),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank Name"),
				'_class -> "required",
				'placeholder -> "Bank Name"
				)/*42.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*42.35*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*43.29*/id)),format.raw/*43.31*/("""" name=""""),_display_(Seq[Any](/*43.40*/name)),format.raw/*43.44*/("""" value=""""),_display_(Seq[Any](/*43.54*/value)),format.raw/*43.59*/("""" """),_display_(Seq[Any](/*43.62*/toHtmlArgs(args))),format.raw/*43.78*/(""">
				""")))})),format.raw/*44.6*/("""
			</div>
			<div class="span6">
				"""),_display_(Seq[Any](/*47.6*/input(myForm("groupBank"),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG394","Group Name"),
				'_class -> "required",
				'placeholder -> "Group Name"
				)/*52.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*52.35*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*53.29*/id)),format.raw/*53.31*/("""" name=""""),_display_(Seq[Any](/*53.40*/name)),format.raw/*53.44*/("""" value=""""),_display_(Seq[Any](/*53.54*/value)),format.raw/*53.59*/("""" """),_display_(Seq[Any](/*53.62*/toHtmlArgs(args))),format.raw/*53.78*/(""">
				""")))})),format.raw/*54.6*/("""
			</div>
		</div>
	</div>
	<div class="control-group">
		<div class="row-fluid">
			<div class="span6">
				"""),_display_(Seq[Any](/*61.6*/input(myForm("id"),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG365","ID"),
				'_class -> "required",
				'placeholder -> "Bank ID"
				)/*66.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*66.35*/("""
				<input type="number" id=""""),_display_(Seq[Any](/*67.31*/id)),format.raw/*67.33*/("""" name=""""),_display_(Seq[Any](/*67.42*/name)),format.raw/*67.46*/("""" value=""""),_display_(Seq[Any](/*67.56*/value)),format.raw/*67.61*/("""" """),_display_(Seq[Any](/*67.64*/readOnly)),format.raw/*67.72*/(""" """),_display_(Seq[Any](/*67.74*/toHtmlArgs(args))),format.raw/*67.90*/(""">
				""")))})),format.raw/*68.6*/("""
			</div>
			<div class="span6">
				"""),_display_(Seq[Any](/*71.6*/input(myForm("code"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG064","SWIFT Code"),
				'_showConstraints -> false,
				'_class -> "required",
				'placeHolder -> "Bank SWIFT Code")/*75.39*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*75.68*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*76.29*/id)),format.raw/*76.31*/("""" name=""""),_display_(Seq[Any](/*76.40*/name)),format.raw/*76.44*/("""" value=""""),_display_(Seq[Any](/*76.54*/value)),format.raw/*76.59*/("""" """),_display_(Seq[Any](/*76.62*/toHtmlArgs(args))),format.raw/*76.78*/(""">
				""")))})),format.raw/*77.6*/("""
			</div>
		</div>
	</div>
	<div class="control-group">
		<div class="row-fluid">
			<div class="span6">
				"""),_display_(Seq[Any](/*84.6*/input(myForm("city"),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"),
				'class -> "",
				'placeholder -> "City"
				)/*89.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*89.35*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*90.29*/id)),format.raw/*90.31*/("""" name=""""),_display_(Seq[Any](/*90.40*/name)),format.raw/*90.44*/("""" value=""""),_display_(Seq[Any](/*90.54*/value)),format.raw/*90.59*/("""" """),_display_(Seq[Any](/*90.62*/toHtmlArgs(args))),format.raw/*90.78*/(""">
				""")))})),format.raw/*91.6*/("""
			</div>
			<div class="span6">
				"""),_display_(Seq[Any](/*94.6*/input(myForm("province"),
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG061","Province"),
				'_showConstraints -> false,
				'class -> "",
				'placeHolder -> "Province")/*98.32*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*98.61*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*99.29*/id)),format.raw/*99.31*/("""" name=""""),_display_(Seq[Any](/*99.40*/name)),format.raw/*99.44*/("""" value=""""),_display_(Seq[Any](/*99.54*/value)),format.raw/*99.59*/("""" """),_display_(Seq[Any](/*99.62*/toHtmlArgs(args))),format.raw/*99.78*/(""">
				""")))})),format.raw/*100.6*/("""
			</div>
		</div>
	</div>
	<div class="control-group">
		<div class="row-fluid">
			<div class="control-group span6">
				"""),_display_(Seq[Any](/*107.6*/select(
					myForm("countryCode"),
					options(models.Country.options),
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"),
					'_showConstraints -> false, 
					'_class -> "required",
					'_default -> "-- Choose a country --"
				))),format.raw/*114.6*/("""
			</div>
		</div>
	</div>
	</fieldset>
</div>

"""))}
    }
    
    def render(myForm:Form[models.Bank],edit:Boolean): play.api.templates.Html = apply(myForm,edit)
    
    def f:((Form[models.Bank],Boolean) => play.api.templates.Html) = (myForm,edit) => apply(myForm,edit)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/banks/formBody.scala.html
                    HASH: 9ce661c4103b4acbd91052b99d7825c3e86c9596
                    MATRIX: 751->1|1068->263|1084->271|1151->275|1189->278|1301->380|1342->382|1384->407|1396->412|1435->413|1469->416|1510->48|1537->100|1564->261|1592->418|1630->421|1665->447|1705->449|1764->473|1779->479|1821->499|1859->506|1897->509|1935->538|1975->540|2036->566|2050->571|2087->586|2125->593|2163->596|2214->638|2280->666|2345->695|2371->699|2417->709|2471->741|2507->746|2675->879|2886->1082|2953->1111|3018->1140|3042->1142|3087->1151|3113->1155|3159->1165|3186->1170|3225->1173|3263->1189|3301->1196|3375->1235|3593->1445|3660->1474|3725->1503|3749->1505|3794->1514|3820->1518|3866->1528|3893->1533|3932->1536|3970->1552|4008->1559|4154->1670|4354->1862|4421->1891|4488->1922|4512->1924|4557->1933|4583->1937|4629->1947|4656->1952|4695->1955|4725->1963|4763->1965|4801->1981|4839->1988|4913->2027|5127->2232|5194->2261|5259->2290|5283->2292|5328->2301|5354->2305|5400->2315|5427->2320|5466->2323|5504->2339|5542->2346|5688->2457|5880->2641|5947->2670|6012->2699|6036->2701|6081->2710|6107->2714|6153->2724|6180->2729|6219->2732|6257->2748|6295->2755|6369->2794|6569->2985|6636->3014|6701->3043|6725->3045|6770->3054|6796->3058|6842->3068|6869->3073|6908->3076|6946->3092|6985->3099|7146->3224|7436->3492
                    LINES: 26->1|37->9|37->9|39->9|40->10|40->10|40->10|42->12|42->12|42->12|43->13|45->1|46->4|47->8|48->14|50->16|50->16|50->16|52->18|52->18|52->18|54->20|56->22|56->22|56->22|58->24|58->24|58->24|60->26|62->28|62->28|62->28|63->29|63->29|63->29|63->29|64->30|71->37|76->42|76->42|77->43|77->43|77->43|77->43|77->43|77->43|77->43|77->43|78->44|81->47|86->52|86->52|87->53|87->53|87->53|87->53|87->53|87->53|87->53|87->53|88->54|95->61|100->66|100->66|101->67|101->67|101->67|101->67|101->67|101->67|101->67|101->67|101->67|101->67|102->68|105->71|109->75|109->75|110->76|110->76|110->76|110->76|110->76|110->76|110->76|110->76|111->77|118->84|123->89|123->89|124->90|124->90|124->90|124->90|124->90|124->90|124->90|124->90|125->91|128->94|132->98|132->98|133->99|133->99|133->99|133->99|133->99|133->99|133->99|133->99|134->100|141->107|148->114
                    -- GENERATED --
                */
            