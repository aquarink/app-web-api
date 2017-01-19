
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
object summary extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.Channel,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(channel:models.Channel):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.26*/("""
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*2.46*/routes/*2.52*/.Assets.at("stylesheets/customer.css"))),format.raw/*2.90*/("""">

<dl class="dl-horizontal dl-aligned">
	<dt>"""),_display_(Seq[Any](/*5.7*/doku/*5.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG398","Service Id"))),format.raw/*5.76*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*6.7*/channel/*6.14*/.code)),format.raw/*6.19*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*7.7*/doku/*7.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG399","Service Name"))),format.raw/*7.78*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*8.7*/channel/*8.14*/.name)),format.raw/*8.19*/("""</dd>
	<dt>CFNames: </dt>
	<dd>"""),_display_(Seq[Any](/*10.7*/channel/*10.14*/.cfnames)),format.raw/*10.22*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*11.7*/doku/*11.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*11.72*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*12.7*/channel/*12.14*/.status)),format.raw/*12.21*/("""</dd>
	<dt>CID: </dt>
	<dd>"""),_display_(Seq[Any](/*14.7*/channel/*14.14*/.cid)),format.raw/*14.18*/("""</dd>
	<dt>Acc Id: </dt>
	<dd>"""),_display_(Seq[Any](/*16.7*/channel/*16.14*/.accountId)),format.raw/*16.24*/("""</dd>
</dl>
"""))}
    }
    
    def render(channel:models.Channel): play.api.templates.Html = apply(channel)
    
    def f:((models.Channel) => play.api.templates.Html) = (channel) => apply(channel)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/channels/summary.scala.html
                    HASH: 7f644c78bc926947520704a542b30bb47dbe82f9
                    MATRIX: 742->1|843->25|924->71|938->77|997->115|1079->163|1091->167|1177->232|1225->246|1240->253|1266->258|1312->270|1324->274|1412->341|1460->355|1475->362|1501->367|1568->399|1584->406|1614->414|1661->426|1674->430|1757->491|1806->505|1822->512|1851->519|1914->547|1930->554|1956->558|2022->589|2038->596|2070->606
                    LINES: 26->1|29->1|30->2|30->2|30->2|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|42->14|42->14|42->14|44->16|44->16|44->16
                    -- GENERATED --
                */
            