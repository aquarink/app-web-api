
package views.html.tools

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
object documentation extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply():play.api.templates.Html = {
        _display_ {

Seq[Any](_display_(Seq[Any](/*1.2*/layout("Documentation")/*1.25*/{_display_(Seq[Any](format.raw/*1.26*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*3.7*/doku/*3.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG147","Documentation & FAQ"))),format.raw/*3.85*/("""</h1>
</div>

<div id="lipsum">
	<ol>
		<li>
			<strong>"""),_display_(Seq[Any](/*9.13*/doku/*9.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG148","Q: What if Sender or Receiver forgot PIN?"))),format.raw/*9.113*/("""</strong>
			<p>"""),_display_(Seq[Any](/*10.8*/doku/*10.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG149","A: Transaction have to be locked first and then Sender must Unlock Transaction to original Sending Operator."))),format.raw/*10.175*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*13.13*/doku/*13.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG150","Q: What if Sender forgot Transaction ID?"))),format.raw/*13.112*/("""</strong>
			<p>"""),_display_(Seq[Any](/*14.8*/doku/*14.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG151","A: Sender have to ask to Original Sending Operator, and Sending Operator will check on Transaction Management."))),format.raw/*14.177*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*17.13*/doku/*17.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG152","Q: What if Receiver forgot Transaction ID?"))),format.raw/*17.114*/("""</strong>
			<p>"""),_display_(Seq[Any](/*18.8*/doku/*18.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG153","A: Receiver have to ask Transaction ID to Sender."))),format.raw/*18.116*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*21.13*/doku/*21.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG154","Q: What if Sender want to Send Money in high value?"))),format.raw/*21.123*/("""</strong>
			<p>"""),_display_(Seq[Any](/*22.8*/doku/*22.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG155","A: Sender must fill in HVFT Form first. And if Supervisor agreed that, Operator must call MTS Operational Administrator to authorize HVFT request."))),format.raw/*22.213*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*25.13*/doku/*25.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG156","Q: What if Sender is one of Interdiction List Customer?"))),format.raw/*25.127*/("""</strong>
			<p>"""),_display_(Seq[Any](/*26.8*/doku/*26.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG157","A: Operator must tell to Sender, that system is down and will be fixed in few hours. After that, Operator must call MTS Operational Administrator, that there is Sender detected as Interdiction customer."))),format.raw/*26.269*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*29.13*/doku/*29.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG158","Q: What if Sender is one of Banned List Customer?"))),format.raw/*29.121*/("""</strong>
			<p>"""),_display_(Seq[Any](/*30.8*/doku/*30.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG159","A: Operator must tell to Sender, that system is down and will be fixed in few hours. After that, Operator must call MTS Operational Administrator, that there is Sender detected as Banned customer."))),format.raw/*30.263*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*33.13*/doku/*33.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG164","Q: What if Receiver is one of Banned List Customer?"))),format.raw/*33.123*/("""</strong>
			<p>"""),_display_(Seq[Any](/*34.8*/doku/*34.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG165","A: Operator must tell to Receiver, that system is down and will be fixed in few hours. After that, Operator must call MTS Operational Administrator, that there is Receiver detected as Banned customer."))),format.raw/*34.267*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*37.13*/doku/*37.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG160","Q: What if money is taken by undesignated Receiver?"))),format.raw/*37.123*/("""</strong>
			<p>"""),_display_(Seq[Any](/*38.8*/doku/*38.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG161","A: Operator must request designated Receiver ID and if the undesignated Receiver cannot provide designated ID then Receive Money process cannot be processed."))),format.raw/*38.224*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*41.13*/doku/*41.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG162","Q: What if Receiver is one of Interdiction List Customer?"))),format.raw/*41.129*/("""</strong>
			<p>"""),_display_(Seq[Any](/*42.8*/doku/*42.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG163","A: Operator must tell to Receiver, that system is down and will be fixed in few hours. After that, Operator must call MTS Operational Administrator, that there is Receiver detected as Interdiction Customer."))),format.raw/*42.273*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*45.13*/doku/*45.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG166","Q: What if internet connection lost while transaction is ongoing?"))),format.raw/*45.137*/("""</strong>
			<p>"""),_display_(Seq[Any](/*46.8*/doku/*46.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG167","A: Please check on menu Transaction Management, if related transaction is listed on transaction management the Operator don't have to do transaction again. Otherwise, please do transaction again."))),format.raw/*46.262*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*49.13*/doku/*49.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG168","Q: What if Operator cannot access MTS Web Application?"))),format.raw/*49.126*/("""</strong>
			<p>"""),_display_(Seq[Any](/*50.8*/doku/*50.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG169","A: Please make sure that internet connection is going well. Otherwise, Operator can call our Customer Service."))),format.raw/*50.177*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*53.13*/doku/*53.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG170","Q: What if Sender want to do full refund?"))),format.raw/*53.113*/("""</strong>
			<p>"""),_display_(Seq[Any](/*54.8*/doku/*54.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG171","A: Operator must request to MTS Operational Administrator for full refund authorization. Once MTS Operational Administrator has authorized full refund request, Operator can proceed Full Refund."))),format.raw/*54.260*/("""</p>
		</li>
		<li>
			<strong>"""),_display_(Seq[Any](/*57.13*/doku/*57.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG172","Q: What if Sender Change Transaction for three times in one transaction?"))),format.raw/*57.144*/("""</strong>
			<p>"""),_display_(Seq[Any](/*58.8*/doku/*58.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG173","A: Sender can proceed transaction, but that Sender will be listed as Banned List later by MTS Operational Administrator manually."))),format.raw/*58.196*/("""</p>
		</li>
       </ol>
    </div>
""")))})),format.raw/*62.2*/("""

"""))}
    }
    
    def render(): play.api.templates.Html = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/documentation.scala.html
                    HASH: d8e6c17caeedcb4425c21958a442093112149afa
                    MATRIX: 810->1|841->24|879->25|946->58|958->62|1053->136|1145->193|1157->197|1275->293|1327->310|1340->314|1526->477|1594->509|1607->513|1725->608|1777->625|1790->629|1978->794|2046->826|2059->830|2179->927|2231->944|2244->948|2371->1052|2439->1084|2452->1088|2581->1194|2633->1211|2646->1215|2870->1416|2938->1448|2951->1452|3084->1562|3136->1579|3149->1583|3429->1840|3497->1872|3510->1876|3637->1980|3689->1997|3702->2001|3976->2252|4044->2284|4057->2288|4186->2394|4238->2411|4251->2415|4529->2670|4597->2702|4610->2706|4739->2812|4791->2829|4804->2833|5039->3045|5107->3077|5120->3081|5255->3193|5307->3210|5320->3214|5604->3475|5672->3507|5685->3511|5828->3631|5880->3648|5893->3652|6166->3902|6234->3934|6247->3938|6379->4047|6431->4064|6444->4068|6632->4233|6700->4265|6713->4269|6832->4365|6884->4382|6897->4386|7168->4634|7236->4666|7249->4670|7399->4797|7451->4814|7464->4818|7671->5002|7740->5040
                    LINES: 29->1|29->1|29->1|31->3|31->3|31->3|37->9|37->9|37->9|38->10|38->10|38->10|41->13|41->13|41->13|42->14|42->14|42->14|45->17|45->17|45->17|46->18|46->18|46->18|49->21|49->21|49->21|50->22|50->22|50->22|53->25|53->25|53->25|54->26|54->26|54->26|57->29|57->29|57->29|58->30|58->30|58->30|61->33|61->33|61->33|62->34|62->34|62->34|65->37|65->37|65->37|66->38|66->38|66->38|69->41|69->41|69->41|70->42|70->42|70->42|73->45|73->45|73->45|74->46|74->46|74->46|77->49|77->49|77->49|78->50|78->50|78->50|81->53|81->53|81->53|82->54|82->54|82->54|85->57|85->57|85->57|86->58|86->58|86->58|90->62
                    -- GENERATED --
                */
            