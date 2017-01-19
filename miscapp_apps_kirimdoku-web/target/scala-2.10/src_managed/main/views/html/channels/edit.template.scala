
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
object edit extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,Form[models.Channel],java.util.List[models.Bank],java.util.Map[String, String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(code:String, myForm: Form[models.Channel], banks: java.util.List[models.Bank], channelBanks: java.util.Map[String, String]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.126*/("""

"""),_display_(Seq[Any](/*3.2*/layout("Channel")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*4.50*/routes/*4.56*/.Assets.at("stylesheets/customer.css"))),format.raw/*4.94*/("""">
    <div class="span8 offset2">
        """),_display_(Seq[Any](/*6.10*/helper/*6.16*/.form(routes.Channels.update(code), 'autoComplete -> "off")/*6.75*/ {_display_(Seq[Any](format.raw/*6.77*/("""
            <div class="page-subheader">
                <h3>"""),_display_(Seq[Any](/*8.22*/doku/*8.26*/.kirimdoku.util.MultiLanguage.getLanguage("LANG400","Manage Route"))),format.raw/*8.93*/("""</h3>
            </div>

            """),_display_(Seq[Any](/*11.14*/channels/*11.22*/.formBody(myForm, true, banks, channelBanks))),format.raw/*11.66*/("""

            <a href=""""),_display_(Seq[Any](/*13.23*/routes/*13.29*/.Channels.list())),format.raw/*13.45*/("""" class="btn btn-inverse">"""),_display_(Seq[Any](/*13.72*/doku/*13.76*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*13.137*/("""</a>
            <button type="button" class="btn btn-inverse" id="clearAll">"""),_display_(Seq[Any](/*14.74*/doku/*14.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG402","Clear All"))),format.raw/*14.142*/("""</button>
            <button type="button" class="btn btn-inverse" id="selectAll">"""),_display_(Seq[Any](/*15.75*/doku/*15.79*/.kirimdoku.util.MultiLanguage.getLanguage("LANG403","Select All"))),format.raw/*15.144*/("""</button>
            <button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*16.60*/doku/*16.64*/.kirimdoku.util.MultiLanguage.getLanguage("LANG397","Set Bank Routing"))),format.raw/*16.135*/("""</button>
        """)))})),format.raw/*17.10*/("""
    </div>
	<script type="text/javascript" src='"""),_display_(Seq[Any](/*19.39*/routes/*19.45*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*19.95*/("""'></script>
	<script type="text/javascript">
	<!--
		$('#clearAll').click(function() """),format.raw/*22.35*/("""{"""),format.raw/*22.36*/("""
		    $(".bankAvaliable").prop('checked', false);
		"""),format.raw/*24.3*/("""}"""),format.raw/*24.4*/(""");
		$('#selectAll').click(function() """),format.raw/*25.36*/("""{"""),format.raw/*25.37*/("""
		    $(".bankAvaliable").prop('checked', true);
		"""),format.raw/*27.3*/("""}"""),format.raw/*27.4*/(""");
	//-->
	</script>
""")))})),format.raw/*30.2*/("""
"""))}
    }
    
    def render(code:String,myForm:Form[models.Channel],banks:java.util.List[models.Bank],channelBanks:java.util.Map[String, String]): play.api.templates.Html = apply(code,myForm,banks,channelBanks)
    
    def f:((String,Form[models.Channel],java.util.List[models.Bank],java.util.Map[String, String]) => play.api.templates.Html) = (code,myForm,banks,channelBanks) => apply(code,myForm,banks,channelBanks)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/channels/edit.scala.html
                    HASH: ef8693bfc48888da66b0d9df6acb51dce547e4e1
                    MATRIX: 810->1|1012->125|1049->128|1074->145|1113->147|1198->197|1212->203|1271->241|1350->285|1364->291|1431->350|1470->352|1568->415|1580->419|1668->486|1743->525|1760->533|1826->577|1886->601|1901->607|1939->623|2002->650|2015->654|2099->715|2213->793|2226->797|2313->861|2433->945|2446->949|2534->1014|2639->1083|2652->1087|2746->1158|2797->1177|2883->1227|2898->1233|2970->1283|3083->1368|3112->1369|3192->1422|3220->1423|3286->1461|3315->1462|3394->1514|3422->1515|3475->1537
                    LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4|32->4|34->6|34->6|34->6|34->6|36->8|36->8|36->8|39->11|39->11|39->11|41->13|41->13|41->13|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|47->19|47->19|47->19|50->22|50->22|52->24|52->24|53->25|53->25|55->27|55->27|58->30
                    -- GENERATED --
                */
            