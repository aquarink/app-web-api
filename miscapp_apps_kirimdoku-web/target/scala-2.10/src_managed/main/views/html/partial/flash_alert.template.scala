
package views.html.partial

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
object flash_alert extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply():play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<div id="alert-container">
	"""),_display_(Seq[Any](/*2.3*/if(flash.contains("error"))/*2.30*/ {_display_(Seq[Any](format.raw/*2.32*/("""
	<div class="row-fluid">
		<div class="span12 alert alert-error">
			<img src=""""),_display_(Seq[Any](/*5.15*/routes/*5.21*/.Assets.at("images/alert-error.png"))),format.raw/*5.57*/(""""/>
			"""),_display_(Seq[Any](/*6.5*/Html(flash.get("error")))),format.raw/*6.29*/("""
		</div>
	</div>
	""")))})),format.raw/*9.3*/("""
	"""),_display_(Seq[Any](/*10.3*/if(flash.contains("success"))/*10.32*/ {_display_(Seq[Any](format.raw/*10.34*/("""
	<div class="row-fluid">
		<div class="span12 alert alert-success">
			<img src=""""),_display_(Seq[Any](/*13.15*/routes/*13.21*/.Assets.at("images/alert-success.png"))),format.raw/*13.59*/(""""/>
			"""),_display_(Seq[Any](/*14.5*/Html(flash.get("success")))),format.raw/*14.31*/("""
		</div>
	</div>
	""")))})),format.raw/*17.3*/("""
	"""),_display_(Seq[Any](/*18.3*/if(flash.contains("warning"))/*18.32*/ {_display_(Seq[Any](format.raw/*18.34*/("""
	<div class="row-fluid">
		<div class="span12 alert alert-warning">
			<img src=""""),_display_(Seq[Any](/*21.15*/routes/*21.21*/.Assets.at("images/alert-warning.png"))),format.raw/*21.59*/(""""/>
			"""),_display_(Seq[Any](/*22.5*/Html(flash.get("warning")))),format.raw/*22.31*/("""
		</div>
	</div>
	""")))})),format.raw/*25.3*/("""
</div>
"""))}
    }
    
    def render(): play.api.templates.Html = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/partial/flash_alert.scala.html
                    HASH: 96bb89d3cb053e5edf517c5204283f76f13b81c3
                    MATRIX: 801->0|864->29|899->56|938->58|1054->139|1068->145|1125->181|1167->189|1212->213|1262->233|1300->236|1338->265|1378->267|1497->350|1512->356|1572->394|1615->402|1663->428|1714->448|1752->451|1790->480|1830->482|1949->565|1964->571|2024->609|2067->617|2115->643|2166->663
                    LINES: 29->1|30->2|30->2|30->2|33->5|33->5|33->5|34->6|34->6|37->9|38->10|38->10|38->10|41->13|41->13|41->13|42->14|42->14|45->17|46->18|46->18|46->18|49->21|49->21|49->21|50->22|50->22|53->25
                    -- GENERATED --
                */
            