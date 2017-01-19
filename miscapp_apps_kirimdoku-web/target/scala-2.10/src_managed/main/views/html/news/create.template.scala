
package views.html.news

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
object create extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.News],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.News]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.29*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Add News")/*6.20*/ {_display_(Seq[Any](format.raw/*6.22*/("""
	"""),_display_(Seq[Any](/*7.3*/partial/*7.10*/.flash_alert())),format.raw/*7.24*/("""
    <div class="span6 offset3">
        """),_display_(Seq[Any](/*9.10*/helper/*9.16*/.form(routes.News.save)/*9.39*/ {_display_(Seq[Any](format.raw/*9.41*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*11.22*/doku/*11.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG458","Add News"))),format.raw/*11.89*/("""</h3>
            </div>

			<div class="control-group">
				<div class="row-fluid">
					<div class="span12">
						<label>"""),_display_(Seq[Any](/*17.15*/doku/*17.19*/.kirimdoku.util.MultiLanguage.getLanguage("LANG460","Content"))),format.raw/*17.81*/("""</label>
						<textarea name="content" id="content" class="span12" cols="30" rows="20"></textarea>
					 </div>
				</div>
			</div>

			<!--div class="control-group">
				<div class="row-fluid">
					<div class="span12">
						"""),_display_(Seq[Any](/*26.8*/select(
						myForm("status"),
						options(models.News.options),
						'_label -> "Status",
						'_showConstraints -> false,
						'class -> "status required"
						))),format.raw/*32.8*/("""
					</div>
				</div>
			</div-->
			<input type="hidden" name="corporate.code" value='"""),_display_(Seq[Any](/*36.55*/session/*36.62*/.get("corporateCode"))),format.raw/*36.83*/("""' />
            <div class="form-actions">
                <a href=""""),_display_(Seq[Any](/*38.27*/routes/*38.33*/.News.list())),format.raw/*38.45*/("""" class="btn btn-inverse">"""),_display_(Seq[Any](/*38.72*/doku/*38.76*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*38.137*/("""</a>
                <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*39.64*/doku/*39.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG126","Save"))),format.raw/*39.127*/("""</button>
            </div>
        """)))})),format.raw/*41.10*/("""
    </div>
""")))})),format.raw/*43.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.News]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[models.News]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/news/create.scala.html
                    HASH: 34789a7e91da999be4c11b6a88de5a5adf24856a
                    MATRIX: 740->1|894->28|922->81|958->83|984->101|1023->103|1060->106|1075->113|1110->127|1187->169|1201->175|1232->198|1271->200|1370->263|1383->267|1468->330|1629->455|1642->459|1726->521|1991->751|2182->921|2308->1011|2324->1018|2367->1039|2473->1109|2488->1115|2522->1127|2585->1154|2598->1158|2682->1219|2786->1287|2799->1291|2881->1350|2951->1388|2995->1401
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|36->7|36->7|36->7|38->9|38->9|38->9|38->9|40->11|40->11|40->11|46->17|46->17|46->17|55->26|61->32|65->36|65->36|65->36|67->38|67->38|67->38|67->38|67->38|67->38|68->39|68->39|68->39|70->41|72->43
                    -- GENERATED --
                */
            