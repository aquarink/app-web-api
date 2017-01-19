
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
object summary extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[models.User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: models.User):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._


Seq[Any](format.raw/*1.21*/("""
"""),format.raw/*4.1*/("""
<fieldset>
	"""),_display_(Seq[Any](/*6.3*/dynamicOr("editUser", String.valueOf(user.id))/*6.49*/ {_display_(Seq[Any](format.raw/*6.51*/("""
		"""),_display_(Seq[Any](/*7.4*/restrictOr(la(as("admin_operasional")))/*7.43*/ {_display_(Seq[Any](format.raw/*7.45*/("""
			<legend>"""),_display_(Seq[Any](/*8.13*/doku/*8.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG289","Account information"))),format.raw/*8.91*/(""" <span class="pull-right"><a class="btn btn-link" href=""""),_display_(Seq[Any](/*8.148*/routes/*8.154*/.User.editOperasional(user.corporate.code, user.id))),format.raw/*8.205*/("""">"""),_display_(Seq[Any](/*8.208*/doku/*8.212*/.kirimdoku.util.MultiLanguage.getLanguage("LANG290","Edit user profile"))),format.raw/*8.284*/("""</a></span></legend>
		""")))}/*9.4*/ {_display_(Seq[Any](format.raw/*9.6*/("""
			<legend>"""),_display_(Seq[Any](/*10.13*/doku/*10.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG289","Account information"))),format.raw/*10.91*/(""" <span class="pull-right"><a class="btn btn-link" href=""""),_display_(Seq[Any](/*10.148*/routes/*10.154*/.User.edit(user.corporate.code, user.id))),format.raw/*10.194*/("""">"""),_display_(Seq[Any](/*10.197*/doku/*10.201*/.kirimdoku.util.MultiLanguage.getLanguage("LANG290","Edit user profile"))),format.raw/*10.273*/("""</a></span></legend>
		""")))})),format.raw/*11.4*/("""
	""")))}/*12.3*/ {_display_(Seq[Any](format.raw/*12.5*/("""
			"""),_display_(Seq[Any](/*13.5*/restrictOr(la(as("finance")))/*13.34*/ {_display_(Seq[Any](format.raw/*13.36*/("""
			<legend>"""),_display_(Seq[Any](/*14.13*/doku/*14.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG289","Account information"))),format.raw/*14.91*/(""" <span class="pull-right"><a class="btn btn-link" href=""""),_display_(Seq[Any](/*14.148*/routes/*14.154*/.User.editOperasional(user.corporate.code, user.id))),format.raw/*14.205*/("""">"""),_display_(Seq[Any](/*14.208*/doku/*14.212*/.kirimdoku.util.MultiLanguage.getLanguage("LANG290","Edit user profile"))),format.raw/*14.284*/("""</a></span></legend>
			""")))}/*15.5*/ {_display_(Seq[Any](format.raw/*15.7*/("""
			<legend>"""),_display_(Seq[Any](/*16.13*/doku/*16.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG289","Account information"))),format.raw/*16.91*/("""</legend>
			""")))})),format.raw/*17.5*/("""
	""")))})),format.raw/*18.3*/("""
	<img src=""""),_display_(Seq[Any](/*19.13*/routes/*19.19*/.User.photo(user.id))),format.raw/*19.39*/("""" width="120" height="120"/>
	<dl class="dl-horizontal dl-aligned dl-aligned-right">
		<dt>"""),_display_(Seq[Any](/*21.8*/doku/*21.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG102","Agent ID"))),format.raw/*21.75*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*22.8*/user/*22.12*/.getIdToken)),format.raw/*22.23*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*23.8*/doku/*23.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*23.73*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*24.8*/user/*24.12*/.status)),format.raw/*24.19*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*25.8*/doku/*25.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG291","Username"))),format.raw/*25.75*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*26.8*/user/*26.12*/.email)),format.raw/*26.18*/("""</dd>
		<dt>"""),_display_(Seq[Any](/*27.8*/doku/*27.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG004","Password"))),format.raw/*27.75*/("""</dt>
		<dd>***** 
		"""),_display_(Seq[Any](/*29.4*/dynamic("changePassword", String.valueOf(user.id))/*29.54*/ {_display_(Seq[Any](format.raw/*29.56*/("""
		<a href=""""),_display_(Seq[Any](/*30.13*/routes/*30.19*/.User.changePassword(user.corporate.code, user.id))),format.raw/*30.69*/("""">("""),_display_(Seq[Any](/*30.73*/doku/*30.77*/.kirimdoku.util.MultiLanguage.getLanguage("LANG292","Change password"))),format.raw/*30.147*/(""")</a>
		""")))})),format.raw/*31.4*/("""
		</dd>
	</dl>
</fieldset>

<fieldset>
	<legend>"""),_display_(Seq[Any](/*37.11*/doku/*37.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG293","Personal information"))),format.raw/*37.90*/(""" <!-- <small><a href=""""),_display_(Seq[Any](/*37.113*/routes/*37.119*/.User.edit(user.corporate.code, user.id))),format.raw/*37.159*/("""">(Change profile)</a></small>  --></legend>
	<dl class="dl-horizontal dl-aligned dl-aligned-right">
		<dt>"""),_display_(Seq[Any](/*39.8*/doku/*39.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG294","Username / Email"))),format.raw/*39.83*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*40.8*/user/*40.12*/.email)),format.raw/*40.18*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*42.8*/doku/*42.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG295","Name"))),format.raw/*42.71*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*43.8*/user/*43.12*/.fullName)),format.raw/*43.21*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*45.8*/doku/*45.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG056","Nationality"))),format.raw/*45.78*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*46.8*/user/*46.12*/.country.name)),format.raw/*46.25*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*48.8*/doku/*48.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG296","Personal ID Type / Number"))),format.raw/*48.92*/("""</dt>
		<dd>("""),_display_(Seq[Any](/*49.9*/user/*49.13*/.personalIdType)),format.raw/*49.28*/(""") """),_display_(Seq[Any](/*49.31*/user/*49.35*/.personalId)),format.raw/*49.46*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*51.8*/doku/*51.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG062","City"))),format.raw/*51.71*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*52.8*/user/*52.12*/.cityName)),format.raw/*52.21*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*54.8*/doku/*54.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*54.79*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*55.8*/user/*55.12*/.phoneNumber)),format.raw/*55.24*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*57.8*/doku/*57.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG297","Mobile number"))),format.raw/*57.80*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*58.8*/user/*58.12*/.mobileNumber)),format.raw/*58.25*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*60.8*/doku/*60.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"))),format.raw/*60.74*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*61.8*/user/*61.12*/.address)),format.raw/*61.20*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*63.8*/doku/*63.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG123","Postal code"))),format.raw/*63.78*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*64.8*/user/*64.12*/.postalCode)),format.raw/*64.23*/("""</dd>

		<dt>"""),_display_(Seq[Any](/*66.8*/doku/*66.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG298","Birth date"))),format.raw/*66.77*/("""</dt>
		<dd>"""),_display_(Seq[Any](/*67.8*/if(user.birthDate != null)/*67.34*/ {_display_(Seq[Any](_display_(Seq[Any](/*67.37*/user/*67.41*/.getBirthDateFormatted))))}/*67.65*/else/*67.70*/{})),format.raw/*67.72*/("""</dd>
	</dl>
</fieldset>
"""))}
    }
    
    def render(user:models.User): play.api.templates.Html = apply(user)
    
    def f:((models.User) => play.api.templates.Html) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/summary.scala.html
                    HASH: 1f520b56b5b979b0025af4ce12c6f3264ae0220c
                    MATRIX: 735->1|935->20|962->126|1010->140|1064->186|1103->188|1141->192|1188->231|1227->233|1275->246|1287->250|1382->324|1475->381|1490->387|1563->438|1602->441|1615->445|1709->517|1750->541|1788->543|1837->556|1850->560|1946->634|2040->691|2056->697|2119->737|2159->740|2173->744|2268->816|2323->840|2344->843|2383->845|2423->850|2461->879|2501->881|2550->894|2563->898|2659->972|2753->1029|2769->1035|2843->1086|2883->1089|2897->1093|2992->1165|3035->1190|3074->1192|3123->1205|3136->1209|3232->1283|3277->1297|3311->1300|3360->1313|3375->1319|3417->1339|3544->1431|3557->1435|3642->1498|3690->1511|3703->1515|3736->1526|3784->1539|3797->1543|3880->1604|3928->1617|3941->1621|3970->1628|4018->1641|4031->1645|4116->1708|4164->1721|4177->1725|4205->1731|4253->1744|4266->1748|4351->1811|4408->1833|4467->1883|4507->1885|4556->1898|4571->1904|4643->1954|4683->1958|4696->1962|4789->2032|4829->2041|4915->2091|4928->2095|5025->2170|5085->2193|5101->2199|5164->2239|5307->2347|5320->2351|5413->2422|5461->2435|5474->2439|5502->2445|5551->2459|5564->2463|5645->2522|5693->2535|5706->2539|5737->2548|5786->2562|5799->2566|5887->2632|5935->2645|5948->2649|5983->2662|6032->2676|6045->2680|6147->2760|6196->2774|6209->2778|6246->2793|6285->2796|6298->2800|6331->2811|6380->2825|6393->2829|6474->2888|6522->2901|6535->2905|6566->2914|6615->2928|6628->2932|6717->2999|6765->3012|6778->3016|6812->3028|6861->3042|6874->3046|6964->3114|7012->3127|7025->3131|7060->3144|7109->3158|7122->3162|7206->3224|7254->3237|7267->3241|7297->3249|7346->3263|7359->3267|7447->3333|7495->3346|7508->3350|7541->3361|7590->3375|7603->3379|7690->3444|7738->3457|7773->3483|7822->3486|7835->3490|7871->3514|7884->3519|7908->3521
                    LINES: 26->1|32->1|33->4|35->6|35->6|35->6|36->7|36->7|36->7|37->8|37->8|37->8|37->8|37->8|37->8|37->8|37->8|37->8|38->9|38->9|39->10|39->10|39->10|39->10|39->10|39->10|39->10|39->10|39->10|40->11|41->12|41->12|42->13|42->13|42->13|43->14|43->14|43->14|43->14|43->14|43->14|43->14|43->14|43->14|44->15|44->15|45->16|45->16|45->16|46->17|47->18|48->19|48->19|48->19|50->21|50->21|50->21|51->22|51->22|51->22|52->23|52->23|52->23|53->24|53->24|53->24|54->25|54->25|54->25|55->26|55->26|55->26|56->27|56->27|56->27|58->29|58->29|58->29|59->30|59->30|59->30|59->30|59->30|59->30|60->31|66->37|66->37|66->37|66->37|66->37|66->37|68->39|68->39|68->39|69->40|69->40|69->40|71->42|71->42|71->42|72->43|72->43|72->43|74->45|74->45|74->45|75->46|75->46|75->46|77->48|77->48|77->48|78->49|78->49|78->49|78->49|78->49|78->49|80->51|80->51|80->51|81->52|81->52|81->52|83->54|83->54|83->54|84->55|84->55|84->55|86->57|86->57|86->57|87->58|87->58|87->58|89->60|89->60|89->60|90->61|90->61|90->61|92->63|92->63|92->63|93->64|93->64|93->64|95->66|95->66|95->66|96->67|96->67|96->67|96->67|96->67|96->67|96->67
                    -- GENERATED --
                */
            