
package views.html.banks

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
object summary extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.Bank,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(bank:models.Bank):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.20*/("""
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*2.46*/routes/*2.52*/.Assets.at("stylesheets/customer.css"))),format.raw/*2.90*/("""">

<dl class="dl-horizontal dl-aligned">
	<dt>"""),_display_(Seq[Any](/*5.7*/doku/*5.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG060","Bank Name"))),format.raw/*5.75*/(""" </dt>
	<dd>: """),_display_(Seq[Any](/*6.9*/bank/*6.13*/.name)),format.raw/*6.18*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*7.7*/doku/*7.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG394","Group Name"))),format.raw/*7.76*/(""" </dt>
	<dd>: """),_display_(Seq[Any](/*8.9*/bank/*8.13*/.groupBank)),format.raw/*8.23*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*9.7*/doku/*9.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG365","ID"))),format.raw/*9.68*/("""</dt>
	<dd>: """),_display_(Seq[Any](/*10.9*/bank/*10.13*/.id)),format.raw/*10.16*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*11.7*/doku/*11.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG064","SWIFT Code"))),format.raw/*11.76*/("""</dt>
	<dd>: """),_display_(Seq[Any](/*12.9*/bank/*12.13*/.code)),format.raw/*12.18*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*13.7*/doku/*13.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"))),format.raw/*13.70*/("""</dt>
	<dd>: """),_display_(Seq[Any](/*14.9*/bank/*14.13*/.city)),format.raw/*14.18*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*15.7*/doku/*15.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"))),format.raw/*15.73*/("""</dt>
	<dd>: """),_display_(Seq[Any](/*16.9*/bank/*16.13*/.countryCode)),format.raw/*16.25*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*17.7*/doku/*17.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG061","Province"))),format.raw/*17.74*/("""</dt>
	<dd>: """),_display_(Seq[Any](/*18.9*/bank/*18.13*/.province)),format.raw/*18.22*/("""</dd>
</dl>
"""))}
    }
    
    def render(bank:models.Bank): play.api.templates.Html = apply(bank)
    
    def f:((models.Bank) => play.api.templates.Html) = (bank) => apply(bank)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/banks/summary.scala.html
                    HASH: 7e8380628acac70515bf49f6a4f923a661c6d042
                    MATRIX: 736->1|831->19|912->65|926->71|985->109|1067->157|1079->161|1164->225|1213->240|1225->244|1251->249|1297->261|1309->265|1395->330|1444->345|1456->349|1487->359|1533->371|1545->375|1623->432|1672->446|1685->450|1710->453|1757->465|1770->469|1857->534|1906->548|1919->552|1946->557|1993->569|2006->573|2087->632|2136->646|2149->650|2176->655|2223->667|2236->671|2320->733|2369->747|2382->751|2416->763|2463->775|2476->779|2561->842|2610->856|2623->860|2654->869
                    LINES: 26->1|29->1|30->2|30->2|30->2|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18
                    -- GENERATED --
                */
            