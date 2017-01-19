
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
object edit extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.News],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.News]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.29*/("""

"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Add News")/*6.20*/ {_display_(Seq[Any](format.raw/*6.22*/("""
	"""),_display_(Seq[Any](/*7.3*/partial/*7.10*/.flash_alert())),format.raw/*7.24*/("""
    <div class="span6 offset3">
        """),_display_(Seq[Any](/*9.10*/helper/*9.16*/.form(routes.News.update(Long.parseLong(myForm("id").value)))/*9.77*/ {_display_(Seq[Any](format.raw/*9.79*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*11.22*/doku/*11.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG491","Edit News"))),format.raw/*11.90*/("""</h3>
            </div>

			<div class="control-group">
				<div class="row-fluid">
					<div class="span12">
						<label>"""),_display_(Seq[Any](/*17.15*/doku/*17.19*/.kirimdoku.util.MultiLanguage.getLanguage("LANG460","Content"))),format.raw/*17.81*/("""</label>
						<textarea name="content" id="content" class="span12" cols="80" rows="20">"""),_display_(Seq[Any](/*18.81*/myForm("content")/*18.98*/.value)),format.raw/*18.104*/("""</textarea>
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

	<script type="text/javascript">
		$(document).ready(function()"""),format.raw/*45.31*/("""{"""),format.raw/*45.32*/("""
			var published = """),_display_(Seq[Any](/*46.21*/myForm("published")/*46.40*/.value)),format.raw/*46.46*/(""";
			if(published)"""),format.raw/*47.17*/("""{"""),format.raw/*47.18*/("""
				$("#status").val("1");
				console.log("Set Published");
			"""),format.raw/*50.4*/("""}"""),format.raw/*50.5*/("""else"""),format.raw/*50.9*/("""{"""),format.raw/*50.10*/("""
				$("#status").val("0");
				console.log("Set Not Published");
			"""),format.raw/*53.4*/("""}"""),format.raw/*53.5*/("""
		"""),format.raw/*54.3*/("""}"""),format.raw/*54.4*/(""");
	</script>
""")))})),format.raw/*56.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.News]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[models.News]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/news/edit.scala.html
                    HASH: 911bfa979308be9da1e151ad8282872443e8d32f
                    MATRIX: 738->1|892->28|920->81|956->83|982->101|1021->103|1058->106|1073->113|1108->127|1185->169|1199->175|1268->236|1307->238|1406->301|1419->305|1505->369|1666->494|1679->498|1763->560|1888->649|1914->666|1943->672|2120->814|2311->984|2437->1074|2453->1081|2496->1102|2602->1172|2617->1178|2651->1190|2714->1217|2727->1221|2811->1282|2915->1350|2928->1354|3010->1413|3080->1451|3184->1527|3213->1528|3270->1549|3298->1568|3326->1574|3372->1592|3401->1593|3493->1658|3521->1659|3552->1663|3581->1664|3677->1733|3705->1734|3735->1737|3763->1738|3809->1753
                    LINES: 26->1|32->1|34->5|35->6|35->6|35->6|36->7|36->7|36->7|38->9|38->9|38->9|38->9|40->11|40->11|40->11|46->17|46->17|46->17|47->18|47->18|47->18|55->26|61->32|65->36|65->36|65->36|67->38|67->38|67->38|67->38|67->38|67->38|68->39|68->39|68->39|70->41|74->45|74->45|75->46|75->46|75->46|76->47|76->47|79->50|79->50|79->50|79->50|82->53|82->53|83->54|83->54|85->56
                    -- GENERATED --
                */
            