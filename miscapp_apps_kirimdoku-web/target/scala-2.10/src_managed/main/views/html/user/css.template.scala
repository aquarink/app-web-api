
package views.html.user

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
object css extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: models.User):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.21*/("""
"""),_display_(Seq[Any](/*2.2*/if(user.corporate.configuration != null)/*2.42*/ {_display_(Seq[Any](format.raw/*2.44*/("""
"""),_display_(Seq[Any](/*3.2*/defining(user.corporate.configuration)/*3.40*/ {configuration =>_display_(Seq[Any](format.raw/*3.58*/("""
.navbar-inner, .navbar .dropdown-menu """),format.raw/*4.39*/("""{"""),format.raw/*4.40*/("""
	background-image: -moz-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*5.56*/configuration/*5.69*/.primaryColor)),format.raw/*5.82*/(""");
	background-image: -ms-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*6.55*/configuration/*6.68*/.secondaryColor)),format.raw/*6.83*/(""");
	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#3C4152), to("""),_display_(Seq[Any](/*7.77*/configuration/*7.90*/.primaryColor)),format.raw/*7.103*/("""));
	background-image: -webkit-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*8.59*/configuration/*8.72*/.primaryColor)),format.raw/*8.85*/(""");
	background-image: -o-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*9.54*/configuration/*9.67*/.primaryColor)),format.raw/*9.80*/(""");
	background-image: linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*10.51*/configuration/*10.64*/.primaryColor)),format.raw/*10.77*/(""");
	filter: progid:dximagetransform.microsoft.gradient(startColorstr='#3C4152', endColorstr='"""),_display_(Seq[Any](/*11.92*/configuration/*11.105*/.primaryColor)),format.raw/*11.118*/(""", GradientType=0);
"""),format.raw/*12.1*/("""}"""),format.raw/*12.2*/("""
 .navbar .dropdown-menu::after """),format.raw/*13.32*/("""{"""),format.raw/*13.33*/("""
	border-bottom: 6px solid """),_display_(Seq[Any](/*14.28*/configuration/*14.41*/.primaryColor)),format.raw/*14.54*/("""
"""),format.raw/*15.1*/("""}"""),format.raw/*15.2*/("""

.navbar .divider-vertical """),format.raw/*17.27*/("""{"""),format.raw/*17.28*/("""
	background-color: """),_display_(Seq[Any](/*18.21*/session/*18.28*/.get("color1"))),format.raw/*18.42*/(""";
	border-right: 1px solid """),_display_(Seq[Any](/*19.27*/configuration/*19.40*/.primaryColor)),format.raw/*19.53*/("""
"""),format.raw/*20.1*/("""}"""),format.raw/*20.2*/("""

.navbar .nav > li a, .role """),format.raw/*22.28*/("""{"""),format.raw/*22.29*/("""
	text-shadow: 0px 0px 0px #000;
	color: """),_display_(Seq[Any](/*24.10*/configuration/*24.23*/.secondaryColor)),format.raw/*24.38*/("""
"""),format.raw/*25.1*/("""}"""),format.raw/*25.2*/("""
""")))})),format.raw/*26.2*/("""
""")))})),format.raw/*27.2*/("""
"""))}
    }
    
    def render(user:models.User): play.api.templates.Html = apply(user)
    
    def f:((models.User) => play.api.templates.Html) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/css.scala.html
                    HASH: caa809ac5b976f0b1b020797c2c8546653ebae6e
                    MATRIX: 731->1|827->20|863->22|911->62|950->64|986->66|1032->104|1087->122|1153->161|1181->162|1272->218|1293->231|1327->244|1419->301|1440->314|1476->329|1590->408|1611->421|1646->434|1743->496|1764->509|1798->522|1889->578|1910->591|1944->604|2033->657|2055->670|2090->683|2220->777|2243->790|2279->803|2325->822|2353->823|2413->855|2442->856|2506->884|2528->897|2563->910|2591->911|2619->912|2675->940|2704->941|2761->962|2777->969|2813->983|2877->1011|2899->1024|2934->1037|2962->1038|2990->1039|3047->1068|3076->1069|3154->1111|3176->1124|3213->1139|3241->1140|3269->1141|3302->1143|3335->1145
                    LINES: 26->1|29->1|30->2|30->2|30->2|31->3|31->3|31->3|32->4|32->4|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|41->13|41->13|42->14|42->14|42->14|43->15|43->15|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|48->20|48->20|50->22|50->22|52->24|52->24|52->24|53->25|53->25|54->26|55->27
                    -- GENERATED --
                */
            