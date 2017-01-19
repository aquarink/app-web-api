
package views.html.customer

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
object summary extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.Customer,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(customer:models.Customer):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.28*/("""
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*2.46*/routes/*2.52*/.Assets.at("stylesheets/customer.css"))),format.raw/*2.90*/("""">

<dl class="dl-horizontal dl-aligned">
	<dt>"""),_display_(Seq[Any](/*5.7*/doku/*5.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG050","Customer ID"))),format.raw/*5.77*/("""</dt>
	<dd>"""),_display_(Seq[Any](/*6.7*/customer/*6.15*/.getIdToken)),format.raw/*6.26*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*7.7*/doku/*7.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG051","First name"))),format.raw/*7.76*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*8.7*/customer/*8.15*/.firstName)),format.raw/*8.25*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*9.7*/doku/*9.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG052","Last name"))),format.raw/*9.75*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*10.7*/customer/*10.15*/.lastName)),format.raw/*10.24*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*11.7*/doku/*11.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"))),format.raw/*11.73*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*12.7*/customer/*12.15*/.country.name)),format.raw/*12.28*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*13.7*/doku/*13.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"))),format.raw/*13.70*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*14.7*/customer/*14.15*/.cityName)),format.raw/*14.24*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*15.7*/doku/*15.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG283","Personal ID"))),format.raw/*15.77*/(""": </dt>
	<dd>("""),_display_(Seq[Any](/*16.8*/customer/*16.16*/.personalIdType)),format.raw/*16.31*/(""") """),_display_(Seq[Any](/*16.34*/customer/*16.42*/.personalId)),format.raw/*16.53*/("""</dd>
	"""),_display_(Seq[Any](/*17.3*/if(customer.birthDate != null)/*17.33*/ {_display_(Seq[Any](format.raw/*17.35*/("""
	<dt>"""),_display_(Seq[Any](/*18.7*/doku/*18.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG057","Date of birth"))),format.raw/*18.79*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*19.7*/customer/*19.15*/.getBirthDateFormatted)),format.raw/*19.37*/("""</dd>
	""")))})),format.raw/*20.3*/("""
	<dt>"""),_display_(Seq[Any](/*21.7*/doku/*21.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG117","Gender"))),format.raw/*21.72*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*22.7*/customer/*22.15*/.gender)),format.raw/*22.22*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*23.7*/doku/*23.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*23.78*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*24.7*/customer/*24.15*/.phoneNumber)),format.raw/*24.27*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*25.7*/doku/*25.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG124","Occupation"))),format.raw/*25.76*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*26.7*/customer/*26.15*/.occupation)),format.raw/*26.26*/("""</dd>
	<dt>"""),_display_(Seq[Any](/*27.7*/doku/*27.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"))),format.raw/*27.73*/(""": </dt>
	<dd>"""),_display_(Seq[Any](/*28.7*/customer/*28.15*/.address)),format.raw/*28.23*/("""</dd>
</dl>
"""))}
    }
    
    def render(customer:models.Customer): play.api.templates.Html = apply(customer)
    
    def f:((models.Customer) => play.api.templates.Html) = (customer) => apply(customer)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/customer/summary.scala.html
                    HASH: 8254acb79d5466fe612f5ced48aa4961f88331e4
                    MATRIX: 743->1|846->27|927->73|941->79|1000->117|1082->165|1094->169|1181->235|1227->247|1243->255|1275->266|1321->278|1333->282|1419->347|1467->361|1483->369|1514->379|1560->391|1572->395|1657->459|1706->473|1723->481|1754->490|1801->502|1814->506|1898->568|1947->582|1964->590|1999->603|2046->615|2059->619|2140->678|2189->692|2206->700|2237->709|2284->721|2297->725|2385->791|2435->806|2452->814|2489->829|2528->832|2545->840|2578->851|2621->859|2660->889|2700->891|2742->898|2755->902|2845->970|2894->984|2911->992|2955->1014|2994->1022|3036->1029|3049->1033|3132->1094|3181->1108|3198->1116|3227->1123|3274->1135|3287->1139|3376->1206|3425->1220|3442->1228|3476->1240|3523->1252|3536->1256|3623->1321|3672->1335|3689->1343|3722->1354|3769->1366|3782->1370|3866->1432|3915->1446|3932->1454|3962->1462
                    LINES: 26->1|29->1|30->2|30->2|30->2|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|48->20|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|52->24|52->24|52->24|53->25|53->25|53->25|54->26|54->26|54->26|55->27|55->27|55->27|56->28|56->28|56->28
                    -- GENERATED --
                */
            