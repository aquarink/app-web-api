
package views.html.send

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
object process extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,org.codehaus.jackson.JsonNode,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, result: org.codehaus.jackson.JsonNode):play.api.templates.Html = {
        _display_ {import helper._


Seq[Any](format.raw/*1.54*/("""
"""),format.raw/*3.1*/("""
"""),_display_(Seq[Any](/*4.2*/layout("Confirmation")/*4.24*/{_display_(Seq[Any](format.raw/*4.25*/("""
<div class="page-header">
   <h2>Send money processing</h2>
</div>

<script type="text/javascript">
<!--
	$(window).bind('beforeunload',function()"""),format.raw/*11.42*/("""{"""),format.raw/*11.43*/("""
	
	    //save info somewhere
	
	   return 'are you sure you want to leave?';
	
	"""),format.raw/*17.2*/("""}"""),format.raw/*17.3*/(""");
//-->
</script>


"""),_display_(Seq[Any](/*22.2*/if(err != null)/*22.17*/ {_display_(Seq[Any](format.raw/*22.19*/("""
  <div class="alert alert-block alert-error">
	<h4 style="text-align: center">
		<img src=""""),_display_(Seq[Any](/*25.14*/routes/*25.20*/.Assets.at("images/alert-error.png"))),format.raw/*25.56*/(""""/>
		"""),_display_(Seq[Any](/*26.4*/err)),format.raw/*26.7*/("""
	</h4>
  </div>
""")))})),format.raw/*29.2*/("""

"""),_display_(Seq[Any](/*31.2*/if(result == null)/*31.20*/ {_display_(Seq[Any](format.raw/*31.22*/("""
<a class="btn btn-back" onclick="window.history.back()"><i class="icon-arrow-left"></i> Back</a>
""")))}/*33.3*/else/*33.8*/{_display_(Seq[Any](format.raw/*33.9*/("""

<div class="row">
  <div class="span12">
    <div class="loading">
    </div>
  </div>
</div>

""")))})),format.raw/*42.2*/("""

""")))})),format.raw/*44.2*/("""
"""))}
    }
    
    def render(err:String,result:org.codehaus.jackson.JsonNode): play.api.templates.Html = apply(err,result)
    
    def f:((String,org.codehaus.jackson.JsonNode) => play.api.templates.Html) = (err,result) => apply(err,result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/process.scala.html
                    HASH: fdb68cd3712f4fcb5c01d7c03a58a6daa1372056
                    MATRIX: 760->1|905->53|932->71|968->73|998->95|1036->96|1211->243|1240->244|1348->325|1376->326|1433->348|1457->363|1497->365|1626->458|1641->464|1699->500|1741->507|1765->510|1814->528|1852->531|1879->549|1919->551|2036->651|2048->656|2086->657|2215->755|2249->758
                    LINES: 26->1|30->1|31->3|32->4|32->4|32->4|39->11|39->11|45->17|45->17|50->22|50->22|50->22|53->25|53->25|53->25|54->26|54->26|57->29|59->31|59->31|59->31|61->33|61->33|61->33|70->42|72->44
                    -- GENERATED --
                */
            