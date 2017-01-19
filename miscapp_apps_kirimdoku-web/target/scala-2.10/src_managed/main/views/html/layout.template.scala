
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
object layout extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,Seq[scala.Tuple2[String, String]],Html,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title:String = "", breadcrumbs: Seq[(String, String)] = null)(body: Html)(implicit head: Html = Html("")):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.108*/("""

"""),_display_(Seq[Any](/*3.2*/if((request().headers().get("X-Requested-With") != null) && request().headers().get("X-Requested-With")(0).equalsIgnoreCase("XMLHttpRequest"))/*3.144*/ {_display_(Seq[Any](format.raw/*3.146*/("""
	"""),_display_(Seq[Any](/*4.3*/layoutXhr/*4.12*/ {_display_(Seq[Any](format.raw/*4.14*/("""
		"""),_display_(Seq[Any](/*5.4*/body)),format.raw/*5.8*/("""
	""")))})),format.raw/*6.3*/("""
""")))}/*7.3*/else/*7.8*/{_display_(Seq[Any](format.raw/*7.9*/("""
	"""),_display_(Seq[Any](/*8.3*/if(session.get("isAdmin"))/*8.29*/ {_display_(Seq[Any](format.raw/*8.31*/("""
		"""),_display_(Seq[Any](/*9.4*/layoutAdmin(title, breadcrumbs)/*9.35*/ {_display_(Seq[Any](format.raw/*9.37*/("""
			"""),_display_(Seq[Any](/*10.5*/body)),format.raw/*10.9*/("""
		""")))})),format.raw/*11.4*/("""
	""")))}/*12.4*/else/*12.9*/{_display_(Seq[Any](format.raw/*12.10*/("""
		"""),_display_(Seq[Any](/*13.4*/layoutFront(title)/*13.22*/ {_display_(Seq[Any](format.raw/*13.24*/("""
			"""),_display_(Seq[Any](/*14.5*/body)),format.raw/*14.9*/("""
		""")))})),format.raw/*15.4*/("""
	""")))})),format.raw/*16.3*/("""
""")))})),format.raw/*17.2*/("""
"""))}
    }
    
    def render(title:String,breadcrumbs:Seq[scala.Tuple2[String, String]],body:Html,head:Html): play.api.templates.Html = apply(title,breadcrumbs)(body)(head)
    
    def f:((String,Seq[scala.Tuple2[String, String]]) => (Html) => (Html) => play.api.templates.Html) = (title,breadcrumbs) => (body) => (head) => apply(title,breadcrumbs)(body)(head)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:22 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/layout.scala.html
                    HASH: 2cbd0c23e9124a0b4e864a2ad0dd60926c530236
                    MATRIX: 768->1|952->107|989->110|1140->252|1180->254|1217->257|1234->266|1273->268|1311->272|1335->276|1368->279|1387->282|1398->287|1435->288|1472->291|1506->317|1545->319|1583->323|1622->354|1661->356|1701->361|1726->365|1761->369|1782->373|1794->378|1833->379|1872->383|1899->401|1939->403|1979->408|2004->412|2039->416|2073->419|2106->421
                    LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4|32->4|33->5|33->5|34->6|35->7|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|43->15|44->16|45->17
                    -- GENERATED --
                */
            