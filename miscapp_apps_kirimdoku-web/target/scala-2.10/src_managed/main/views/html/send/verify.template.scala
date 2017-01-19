
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
object verify extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(classType: String, err: String):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.34*/("""
<p>
	<div class="alert alert-block alert-"""),_display_(Seq[Any](/*3.39*/{classType})),format.raw/*3.50*/(""" fade in out">
		<h5>"""),_display_(Seq[Any](/*4.8*/err)),format.raw/*4.11*/("""</h5>
	</div>
</p>
"""))}
    }
    
    def render(classType:String,err:String): play.api.templates.Html = apply(classType,err)
    
    def f:((String,String) => play.api.templates.Html) = (classType,err) => apply(classType,err)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/verify.scala.html
                    HASH: fde46771f204f538a5c2f8a31421f2807185fc5a
                    MATRIX: 736->1|845->33|923->76|955->87|1011->109|1035->112
                    LINES: 26->1|29->1|31->3|31->3|32->4|32->4
                    -- GENERATED --
                */
            