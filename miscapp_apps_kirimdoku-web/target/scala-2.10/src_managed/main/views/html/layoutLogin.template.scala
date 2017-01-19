
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
object layoutLogin extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(content: Html):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.17*/("""
"""),format.raw/*4.1*/("""
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*10.50*/routes/*10.56*/.Assets.at("stylesheets/bootstrap.min.css"))),format.raw/*10.99*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*11.50*/routes/*11.56*/.Assets.at("stylesheets/bootstrap-responsive.min.css"))),format.raw/*11.110*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*12.50*/routes/*12.56*/.Assets.at("stylesheets/main.css"))),format.raw/*12.90*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*13.50*/routes/*13.56*/.Assets.at("stylesheets/subnav.css"))),format.raw/*13.92*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*14.50*/routes/*14.56*/.Assets.at("stylesheets/login.css"))),format.raw/*14.91*/("""">
    <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*15.55*/routes/*15.61*/.Assets.at("images/favicon.png"))),format.raw/*15.93*/("""">

	<script src=""""),_display_(Seq[Any](/*17.16*/routes/*17.22*/.Assets.at("javascripts/modernizr.custom.45033.js"))),format.raw/*17.73*/("""" type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*18.19*/routes/*18.25*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*18.70*/("""" type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*19.19*/routes/*19.25*/.Assets.at("javascripts/rails.js"))),format.raw/*19.59*/("""" type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*20.19*/routes/*20.25*/.Assets.at("javascripts/bootstrap/bootstrap-modal.js"))),format.raw/*20.79*/("""" type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*21.19*/routes/*21.25*/.Assets.at("javascripts/bootstrap/bootstrap-dropdown.js"))),format.raw/*21.82*/("""" type="text/javascript"></script>
	<script src=""""),_display_(Seq[Any](/*22.16*/routes/*22.22*/.Assets.at("javascripts/jquery.inputmask.bundle.min.js"))),format.raw/*22.78*/("""" type="text/javascript" charset="utf-8"></script>
	<script src='"""),_display_(Seq[Any](/*23.16*/routes/*23.22*/.Assets.at("javascripts/jquery.validate.min.js"))),format.raw/*23.70*/("""' type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*24.19*/routes/*24.25*/.Assets.at("javascripts/global.js"))),format.raw/*24.60*/("""" type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*25.19*/routes/*25.25*/.Assets.at("javascripts/login.js"))),format.raw/*25.59*/("""" type="text/javascript"></script>
    <script type="text/javascript" src='"""),_display_(Seq[Any](/*26.42*/routes/*26.48*/.Assets.at("javascripts/form.js"))),format.raw/*26.81*/("""'></script>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">

</head>
<body>
<div class="container login-container login-container-large" style="margin-top: 50px;">
    <div class="row">
        <div class="well login-box" style="margin-left: auto; margin-right: auto; margin-bottom: 0px;">
            <div>
                """),_display_(Seq[Any](/*44.18*/content)),format.raw/*44.25*/("""
            </div>
        </div>
    </div>
    <p style="text-align: center; font-size: 10pt; color: #AAAAAA;">language:
		<a style="text-decoration: none; color: #777777; margin-left: 10px; margin-right: 5px; cursor: pointer;" href="/changeLang/EN"""),_display_(Seq[Any](/*49.129*/request/*49.136*/.uri)),format.raw/*49.140*/("""" path=""""),_display_(Seq[Any](/*49.149*/request/*49.156*/.uri)),format.raw/*49.160*/("""">
	    	"""),_display_(Seq[Any](/*50.8*/if(doku.kirimdoku.util.MultiLanguage.getActiveLanguage() == "EN")/*50.73*/ {_display_(Seq[Any](format.raw/*50.75*/(""" ✓""")))})),format.raw/*50.78*/("""english
	    </a>
		<a style="text-decoration: none; color: #777777; margin-left: 5px; margin-right: 5px; cursor: pointer;" href="/changeLang/ID"""),_display_(Seq[Any](/*52.128*/request/*52.135*/.uri)),format.raw/*52.139*/("""" path=""""),_display_(Seq[Any](/*52.148*/request/*52.155*/.uri)),format.raw/*52.159*/("""">
			"""),_display_(Seq[Any](/*53.5*/if(doku.kirimdoku.util.MultiLanguage.getActiveLanguage() == "ID")/*53.70*/ {_display_(Seq[Any](format.raw/*53.72*/(""" ✓""")))})),format.raw/*53.75*/("""bahasa
		</a>
		<a style="text-decoration: none; color: #777777; margin-left: 5px; margin-right: 5px; cursor: pointer;" href="/changeLang/CN"""),_display_(Seq[Any](/*55.128*/request/*55.135*/.uri)),format.raw/*55.139*/("""" path=""""),_display_(Seq[Any](/*55.148*/request/*55.155*/.uri)),format.raw/*55.159*/("""">
			"""),_display_(Seq[Any](/*56.5*/if(doku.kirimdoku.util.MultiLanguage.getActiveLanguage() == "CN")/*56.70*/ {_display_(Seq[Any](format.raw/*56.72*/(""" ✓""")))})),format.raw/*56.75*/("""chinese
		</a>
	</p>
</div>

</body>
</html>"""))}
    }
    
    def render(content:Html): play.api.templates.Html = apply(content)
    
    def f:((Html) => play.api.templates.Html) = (content) => apply(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/layoutLogin.scala.html
                    HASH: 85c705d38f5cfb238d6d566504f4ae6944b26166
                    MATRIX: 727->1|869->16|896->68|1123->259|1138->265|1203->308|1291->360|1306->366|1383->420|1471->472|1486->478|1542->512|1630->564|1645->570|1703->606|1791->658|1806->664|1863->699|1956->756|1971->762|2025->794|2080->813|2095->819|2168->870|2257->923|2272->929|2339->974|2428->1027|2443->1033|2499->1067|2588->1120|2603->1126|2679->1180|2768->1233|2783->1239|2862->1296|2948->1346|2963->1352|3041->1408|3143->1474|3158->1480|3228->1528|3317->1581|3332->1587|3389->1622|3478->1675|3493->1681|3549->1715|3661->1791|3676->1797|3731->1830|4723->2786|4752->2793|5041->3045|5058->3052|5085->3056|5131->3065|5148->3072|5175->3076|5220->3086|5294->3151|5334->3153|5369->3156|5551->3301|5568->3308|5595->3312|5641->3321|5658->3328|5685->3332|5727->3339|5801->3404|5841->3406|5876->3409|6054->3550|6071->3557|6098->3561|6144->3570|6161->3577|6188->3581|6230->3588|6304->3653|6344->3655|6379->3658
                    LINES: 26->1|32->1|33->4|39->10|39->10|39->10|40->11|40->11|40->11|41->12|41->12|41->12|42->13|42->13|42->13|43->14|43->14|43->14|44->15|44->15|44->15|46->17|46->17|46->17|47->18|47->18|47->18|48->19|48->19|48->19|49->20|49->20|49->20|50->21|50->21|50->21|51->22|51->22|51->22|52->23|52->23|52->23|53->24|53->24|53->24|54->25|54->25|54->25|55->26|55->26|55->26|73->44|73->44|78->49|78->49|78->49|78->49|78->49|78->49|79->50|79->50|79->50|79->50|81->52|81->52|81->52|81->52|81->52|81->52|82->53|82->53|82->53|82->53|84->55|84->55|84->55|84->55|84->55|84->55|85->56|85->56|85->56|85->56
                    -- GENERATED --
                */
            