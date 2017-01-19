
package views.html.channels

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
object formBody extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[Form[models.Channel],Boolean,java.util.List[models.Bank],java.util.Map[String, String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Channel], edit:Boolean=false, banks: java.util.List[models.Bank], channelBanks: java.util.Map[String, String]):play.api.templates.Html = {
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
Seq[Any](format.raw/*1.133*/("""
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
<input type="hidden" name="channelCode" value=""""),_display_(Seq[Any](/*31.49*/myForm/*31.55*/.get().code)),format.raw/*31.66*/(""""> 
<div class="well">
	<fieldset class="fieldset">
	<div class="control-group">
		<div class="row-fluid">
			<div class="span6">
				"""),_display_(Seq[Any](/*37.6*/input(myForm("name"),
				'_showConstraints -> false,
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG399","Service Name"),
				'_class -> "required",
				'placeholder -> "Name"
				)/*42.6*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*42.35*/("""
				<input type="text" id=""""),_display_(Seq[Any](/*43.29*/id)),format.raw/*43.31*/("""" name=""""),_display_(Seq[Any](/*43.40*/name)),format.raw/*43.44*/("""" value=""""),_display_(Seq[Any](/*43.54*/value)),format.raw/*43.59*/("""" """),_display_(Seq[Any](/*43.62*/readOnly)),format.raw/*43.70*/(""" """),_display_(Seq[Any](/*43.72*/toHtmlArgs(args))),format.raw/*43.88*/(""">
				""")))})),format.raw/*44.6*/("""
			</div>
			<div class="span6">
				&nbsp;
			</div>
		</div>
	</div>
	<div class="control-group">
		<div class="row-fluid">
			<div class="span6">
				<label for="name" style="font-weight: bold;">"""),_display_(Seq[Any](/*54.51*/doku/*54.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG404","Available Bank"))),format.raw/*54.124*/("""</label>
				<ul style="list-style-type: none;columns: 3;padding: 3px;margin: 0px;width: 720px;">
				"""),_display_(Seq[Any](/*56.6*/for(bank <- banks) yield /*56.24*/ {_display_(Seq[Any](format.raw/*56.26*/("""
					<li style="width: 400px; padding: 5px;">
                     	<input type="checkbox" class="bankAvaliable" name="bankId[]" id="bankId_"""),_display_(Seq[Any](/*58.96*/bank/*58.100*/.id)),format.raw/*58.103*/("""" value=""""),_display_(Seq[Any](/*58.113*/bank/*58.117*/.id)),format.raw/*58.120*/("""" """),_display_(Seq[Any](/*58.123*/if(channelBanks.contains(bank.id))/*58.157*/ {_display_(Seq[Any](format.raw/*58.159*/(""" checked="checked" """)))})),format.raw/*58.179*/(""">&nbsp;"""),_display_(Seq[Any](/*58.187*/bank/*58.191*/.name)),format.raw/*58.196*/("""
                	</li>
                """)))})),format.raw/*60.18*/("""
                </ul>
			</div>
		</div>
	</div>
	</fieldset>
</div>
<br>
"""))}
    }
    
    def render(myForm:Form[models.Channel],edit:Boolean,banks:java.util.List[models.Bank],channelBanks:java.util.Map[String, String]): play.api.templates.Html = apply(myForm,edit,banks,channelBanks)
    
    def f:((Form[models.Channel],Boolean,java.util.List[models.Bank],java.util.Map[String, String]) => play.api.templates.Html) = (myForm,edit,banks,channelBanks) => apply(myForm,edit,banks,channelBanks)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/channels/formBody.scala.html
                    HASH: 22e0610377c93386954c3916eb6698612be9baa2
                    MATRIX: 815->1|1216->347|1232->355|1299->359|1337->362|1449->464|1490->466|1532->491|1544->496|1583->497|1617->500|1659->132|1686->184|1713->345|1741->502|1779->505|1814->531|1854->533|1913->557|1928->563|1970->583|2008->590|2046->593|2084->622|2124->624|2185->650|2199->655|2236->670|2274->677|2312->680|2363->722|2429->750|2494->779|2520->783|2566->793|2620->825|2656->830|2741->879|2756->885|2789->896|2959->1031|3168->1232|3235->1261|3300->1290|3324->1292|3369->1301|3395->1305|3441->1315|3468->1320|3507->1323|3537->1331|3575->1333|3613->1349|3651->1356|3887->1556|3900->1560|3992->1629|4130->1732|4164->1750|4204->1752|4382->1894|4396->1898|4422->1901|4469->1911|4483->1915|4509->1918|4549->1921|4593->1955|4634->1957|4687->1977|4732->1985|4746->1989|4774->1994|4847->2035
                    LINES: 26->1|37->9|37->9|39->9|40->10|40->10|40->10|42->12|42->12|42->12|43->13|45->1|46->4|47->8|48->14|50->16|50->16|50->16|52->18|52->18|52->18|54->20|56->22|56->22|56->22|58->24|58->24|58->24|60->26|62->28|62->28|62->28|63->29|63->29|63->29|63->29|64->30|65->31|65->31|65->31|71->37|76->42|76->42|77->43|77->43|77->43|77->43|77->43|77->43|77->43|77->43|77->43|77->43|78->44|88->54|88->54|88->54|90->56|90->56|90->56|92->58|92->58|92->58|92->58|92->58|92->58|92->58|92->58|92->58|92->58|92->58|92->58|92->58|94->60
                    -- GENERATED --
                */
            