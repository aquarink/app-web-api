
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
object message extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(errType: String, err: String, debug: String = ""):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.52*/("""

<div id="alert-container">
	"""),_display_(Seq[Any](/*4.3*/if(errType.equals("error"))/*4.30*/ {_display_(Seq[Any](format.raw/*4.32*/("""
	<div class="row-fluid">
		<div class="span12 alert alert-error">
			"""),_display_(Seq[Any](/*7.5*/Html(err))),format.raw/*7.14*/("""
		</div>
	</div>
	""")))})),format.raw/*10.3*/("""
	"""),_display_(Seq[Any](/*11.3*/if(errType.equals("success"))/*11.32*/ {_display_(Seq[Any](format.raw/*11.34*/("""
	<div class="row-fluid">
		<div class="span12 alert alert-success">
			"""),_display_(Seq[Any](/*14.5*/Html(err))),format.raw/*14.14*/("""
		</div>
	</div>
	""")))})),format.raw/*17.3*/("""
	"""),_display_(Seq[Any](/*18.3*/if(errType.equals("warn"))/*18.29*/ {_display_(Seq[Any](format.raw/*18.31*/("""
	<div class="row-fluid">
		<div class="span12 alert alert-warning">
			"""),_display_(Seq[Any](/*21.5*/Html(err))),format.raw/*21.14*/("""
		</div>
	</div>
	""")))})),format.raw/*24.3*/("""
</div>
"""))}
    }
    
    def render(errType:String,err:String,debug:String): play.api.templates.Html = apply(errType,err,debug)
    
    def f:((String,String,String) => play.api.templates.Html) = (errType,err,debug) => apply(errType,err,debug)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/partial/message.scala.html
                    HASH: eac0909fdb567d02a6ed2a730d7a466585460de0
                    MATRIX: 747->1|874->51|939->82|974->109|1013->111|1118->182|1148->191|1199->211|1237->214|1275->243|1315->245|1423->318|1454->327|1505->347|1543->350|1578->376|1618->378|1726->451|1757->460|1808->480
                    LINES: 26->1|29->1|32->4|32->4|32->4|35->7|35->7|38->10|39->11|39->11|39->11|42->14|42->14|45->17|46->18|46->18|46->18|49->21|49->21|52->24
                    -- GENERATED --
                */
            