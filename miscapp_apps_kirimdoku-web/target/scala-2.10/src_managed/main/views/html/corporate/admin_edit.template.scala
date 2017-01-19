
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
object admin_edit extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Form[models.Corporate],List[models.Channel],Map[String, String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Corporate], channels: List[models.Channel], mapChannel: Map[String, String]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.99*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Corporate", Seq(
routes.Corporate.admin_index().toString -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG190","Corporates management"),
"" -> myForm("name").value.toString(),
"" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG112","Edit")
))/*9.3*/ {_display_(Seq[Any](format.raw/*9.5*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*11.7*/doku/*11.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG352","Edit Corporate"))),format.raw/*11.80*/("""</h1>
</div>

"""),_display_(Seq[Any](/*14.2*/partial/*14.9*/.flash_alert())),format.raw/*14.23*/("""

"""),_display_(Seq[Any](/*16.2*/form(routes.Corporate.admin_update(myForm("code").value), 'enctype -> "multipart/form-data", 'class -> "form form-horizontal")/*16.128*/ {_display_(Seq[Any](format.raw/*16.130*/("""
	"""),_display_(Seq[Any](/*17.3*/if(myForm.hasErrors)/*17.23*/ {_display_(Seq[Any](format.raw/*17.25*/("""
	<p class="alert alert-error">
		"""),_display_(Seq[Any](/*19.4*/myForm/*19.10*/.errors())),format.raw/*19.19*/("""
	</p>
	""")))})),format.raw/*21.3*/("""
	
	<div class="hidden">
		"""),_display_(Seq[Any](/*24.4*/input(myForm("status"), 'showConstraints -> false)/*24.54*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*24.83*/("""
		<input type="hidden" name=""""),_display_(Seq[Any](/*25.31*/name)),format.raw/*25.35*/("""" value=""""),_display_(Seq[Any](/*25.45*/value)),format.raw/*25.50*/(""""/>
		""")))})),format.raw/*26.4*/("""
	</div>

	"""),_display_(Seq[Any](/*29.3*/formBody(myForm, true, channels, mapChannel))),format.raw/*29.47*/("""
""")))})),format.raw/*30.2*/("""
""")))}/*31.2*/ {_display_(Seq[Any](format.raw/*31.4*/("""

<script type="text/javascript">
$(function() """),format.raw/*34.14*/("""{"""),format.raw/*34.15*/("""
	$('.colorpicker').colorpicker();
"""),format.raw/*36.1*/("""}"""),format.raw/*36.2*/(""");
</script>

""")))})),format.raw/*39.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.Corporate],channels:List[models.Channel],mapChannel:Map[String, String]): play.api.templates.Html = apply(myForm,channels,mapChannel)
    
    def f:((Form[models.Corporate],List[models.Channel],Map[String, String]) => play.api.templates.Html) = (myForm,channels,mapChannel) => apply(myForm,channels,mapChannel)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/corporate/admin_edit.scala.html
                    HASH: 4149c1dce019f030b1d1403e96331fde6dac023e
                    MATRIX: 795->1|1019->98|1046->150|1082->152|1350->413|1388->415|1456->448|1469->452|1560->521|1610->536|1625->543|1661->557|1699->560|1835->686|1876->688|1914->691|1943->711|1983->713|2053->748|2068->754|2099->763|2139->772|2202->800|2261->850|2328->879|2395->910|2421->914|2467->924|2494->929|2532->936|2579->948|2645->992|2678->994|2698->996|2737->998|2812->1045|2841->1046|2903->1081|2931->1082|2977->1097
                    LINES: 26->1|32->1|33->4|34->5|38->9|38->9|40->11|40->11|40->11|43->14|43->14|43->14|45->16|45->16|45->16|46->17|46->17|46->17|48->19|48->19|48->19|50->21|53->24|53->24|53->24|54->25|54->25|54->25|54->25|55->26|58->29|58->29|59->30|60->31|60->31|63->34|63->34|65->36|65->36|68->39
                    -- GENERATED --
                */
            