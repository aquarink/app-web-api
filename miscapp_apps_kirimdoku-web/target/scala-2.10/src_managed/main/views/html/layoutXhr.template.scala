
package views.html

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
object layoutXhr extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(content: Html):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.17*/("""
<!DOCTYPE html>
<html>
    <head>
        <!-- <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*5.59*/routes/*5.65*/.Assets.at("stylesheets/bootstrap.css"))),format.raw/*5.104*/("""">-->
        <!-- <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*6.59*/routes/*6.65*/.Assets.at("stylesheets/bootstrap-responsive.css"))),format.raw/*6.115*/("""">-->
    </head>
    <body>
        <section class="inner-content">
            """),_display_(Seq[Any](/*10.14*/content)),format.raw/*10.21*/("""
        </section>
	
    <script src=""""),_display_(Seq[Any](/*13.19*/routes/*13.25*/.Assets.at("javascripts/global.js"))),format.raw/*13.60*/("""" type="text/javascript"></script>
    </body>
</html>
"""))}
    }
    
    def render(content:Html): play.api.templates.Html = apply(content)
    
    def f:((Html) => play.api.templates.Html) = (content) => apply(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/layoutXhr.scala.html
                    HASH: 95cfa634eb13d56fc7b54e207a902a4ddb73c6a3
                    MATRIX: 725->1|817->16|945->109|959->115|1020->154|1119->218|1133->224|1205->274|1323->356|1352->363|1428->403|1443->409|1500->444
                    LINES: 26->1|29->1|33->5|33->5|33->5|34->6|34->6|34->6|38->10|38->10|41->13|41->13|41->13
                    -- GENERATED --
                */
            