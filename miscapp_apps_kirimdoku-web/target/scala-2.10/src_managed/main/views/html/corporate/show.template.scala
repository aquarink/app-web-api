
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
object show extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.Corporate,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate: models.Corporate):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.31*/("""

<fieldset>
	<img src=""""),_display_(Seq[Any](/*4.13*/routes/*4.19*/.Corporate.logo(corporate.code))),format.raw/*4.50*/("""" width="120" height="120"/>
	<dl class="dl-horizontal dl-aligned dl-aligned-right">
	<dt>"""),_display_(Seq[Any](/*6.7*/doku/*6.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG316","Corporate Code"))),format.raw/*6.80*/("""</dt>
	<dd>"""),_display_(Seq[Any](/*7.7*/corporate/*7.16*/.code)),format.raw/*7.21*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*8.7*/doku/*8.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG273","Corporate Name"))),format.raw/*8.80*/("""</dt>
	<dd>"""),_display_(Seq[Any](/*9.7*/corporate/*9.16*/.name)),format.raw/*9.21*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*10.7*/doku/*10.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Trade name"))),format.raw/*10.76*/("""</dt>
	<dd>"""),_display_(Seq[Any](/*11.7*/corporate/*11.16*/.tradeName)),format.raw/*11.26*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*12.7*/doku/*12.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"))),format.raw/*12.73*/("""</dt>
	<dd>"""),_display_(Seq[Any](/*13.7*/corporate/*13.16*/.country.code)),format.raw/*13.29*/(""" - """),_display_(Seq[Any](/*13.33*/corporate/*13.42*/.country.name)),format.raw/*13.55*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*14.7*/doku/*14.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG087","Currency"))),format.raw/*14.74*/("""</dt>
	<dd>"""),_display_(Seq[Any](/*15.7*/corporate/*15.16*/.currency.code)),format.raw/*15.30*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*16.7*/doku/*16.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG317","Permission Role"))),format.raw/*16.81*/("""</dt>
	<dd>
		"""),_display_(Seq[Any](/*18.4*/if(corporate.hasPermission(models.Corporate.PERMISSION_SEND))/*18.65*/ {_display_(Seq[Any](format.raw/*18.67*/("""Send""")))})),format.raw/*18.72*/("""
		"""),_display_(Seq[Any](/*19.4*/if(corporate.hasPermission(models.Corporate.PERMISSION_RECEIVE))/*19.68*/ {_display_(Seq[Any](format.raw/*19.70*/("""Receive""")))})),format.raw/*19.78*/("""
	</dd>
	</dl>
</fieldset>
"""))}
    }
    
    def render(corporate:models.Corporate): play.api.templates.Html = apply(corporate)
    
    def f:((models.Corporate) => play.api.templates.Html) = (corporate) => apply(corporate)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:25 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/corporate/show.scala.html
                    HASH: 1ad2a6edc17500c75014ea164802b4c1e36b0bb5
                    MATRIX: 742->1|848->30|908->55|922->61|974->92|1099->183|1111->187|1201->256|1247->268|1264->277|1290->282|1336->294|1348->298|1438->367|1484->379|1501->388|1527->393|1574->405|1587->409|1674->474|1721->486|1739->495|1771->505|1818->517|1831->521|1915->583|1962->595|1980->604|2015->617|2055->621|2073->630|2108->643|2155->655|2168->659|2253->722|2300->734|2318->743|2354->757|2401->769|2414->773|2506->843|2556->858|2626->919|2666->921|2703->926|2742->930|2815->994|2855->996|2895->1004
                    LINES: 26->1|29->1|32->4|32->4|32->4|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|46->18|46->18|46->18|46->18|47->19|47->19|47->19|47->19
                    -- GENERATED --
                */
            