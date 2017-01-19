
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
object list_users extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[models.Corporate,models.User,com.avaje.ebean.Page[models.User],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate:models.Corporate, parent:models.User, currentPage: com.avaje.ebean.Page[models.User]):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._


Seq[Any](format.raw/*1.98*/("""

"""),format.raw/*5.1*/("""
<div class="page-subheader">
	<h4>
		"""),_display_(Seq[Any](/*8.4*/if(parent.id != Long.parseLong(session.get("userId")))/*8.58*/ {_display_(Seq[Any](format.raw/*8.60*/("""
			<small><a class="slide" data-slide-operation="prev" href=""><i class="icon-chevron-left"></i> """),_display_(Seq[Any](/*9.99*/doku/*9.103*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*9.162*/("""</a></small>
		""")))}/*10.5*/else/*10.10*/{_display_(Seq[Any](format.raw/*10.11*/("""
			"""),_display_(Seq[Any](/*11.5*/restrict(la(as("admin"), as("admin_operasional")))/*11.55*/ {_display_(Seq[Any](format.raw/*11.57*/("""
			<small><a href=""""),_display_(Seq[Any](/*12.21*/routes/*12.27*/.Corporate.admin_index(0, "code", "asc", ""))),format.raw/*12.71*/(""""><i class="icon-chevron-left"></i> """),_display_(Seq[Any](/*12.108*/doku/*12.112*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*12.171*/("""</a></small>
			""")))})),format.raw/*13.5*/("""
		""")))})),format.raw/*14.4*/("""
		&nbsp;&nbsp;&nbsp;&nbsp;
		"""),_display_(Seq[Any](/*16.4*/doku/*16.8*/.kirimdoku.util.MultiLanguage.getLanguage("LANG373","List of"))),format.raw/*16.70*/(""" 
		"""),_display_(Seq[Any](/*17.4*/if(parent.hasRole("admin"))/*17.31*/ {_display_(Seq[Any](format.raw/*17.33*/("""MainAgents""")))})),format.raw/*17.44*/("""
		"""),_display_(Seq[Any](/*18.4*/if(parent.hasRole("mainagent"))/*18.35*/ {_display_(Seq[Any](format.raw/*18.37*/("""Supervisors""")))})),format.raw/*18.49*/("""
		"""),_display_(Seq[Any](/*19.4*/if(parent.hasRole("supervisor"))/*19.36*/ {_display_(Seq[Any](format.raw/*19.38*/("""Operators""")))})),format.raw/*19.48*/("""
		"""),_display_(Seq[Any](/*20.4*/if(parent.hasRole("admin_operasional"))/*20.43*/ {_display_(Seq[Any](format.raw/*20.45*/("""Operators""")))})),format.raw/*20.55*/("""
		"""),_display_(Seq[Any](/*21.4*/if(!parent.hasRole("admin") && !parent.hasRole("admin_operasional"))/*21.72*/ {_display_(Seq[Any](format.raw/*21.74*/(""" """),_display_(Seq[Any](/*21.76*/doku/*21.80*/.kirimdoku.util.MultiLanguage.getLanguage("LANG492","under"))),format.raw/*21.140*/(""" """),_display_(Seq[Any](/*21.142*/parent/*21.148*/.fullName)),format.raw/*21.157*/(""" """)))})),format.raw/*21.159*/("""
	</h4>
</div>

<div class="container-user-childrens-inner">
"""),_display_(Seq[Any](/*26.2*/if(currentPage.getTotalRowCount == 0)/*26.39*/{_display_(Seq[Any](format.raw/*26.40*/("""
<div class="well">
	<em>"""),_display_(Seq[Any](/*28.7*/doku/*28.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG286","Results are empty"))),format.raw/*28.83*/("""</em>
</div>
""")))}/*30.3*/else/*30.8*/{_display_(Seq[Any](format.raw/*30.9*/("""
<table class="table-bordered table-striped table-condensed table-users" style="width:100%;">
	<thead>
		<tr>
			<th>"""),_display_(Seq[Any](/*34.9*/doku/*34.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG365","ID"))),format.raw/*34.70*/("""</th>
			<th>"""),_display_(Seq[Any](/*35.9*/doku/*35.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG294","Username / Email"))),format.raw/*35.84*/("""</th>
			<th>"""),_display_(Seq[Any](/*36.9*/doku/*36.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG295","Name"))),format.raw/*36.72*/("""</th>
			<th>"""),_display_(Seq[Any](/*37.9*/doku/*37.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG105","Action"))),format.raw/*37.74*/("""</th>
		</tr>
	</thead>
	<tbody>
	"""),_display_(Seq[Any](/*41.3*/for(user <- currentPage.getList) yield /*41.35*/ {_display_(Seq[Any](format.raw/*41.37*/("""
	<tr>
		<td>"""),_display_(Seq[Any](/*43.8*/user/*43.12*/.getIdToken)),format.raw/*43.23*/("""</td>
		<td>"""),_display_(Seq[Any](/*44.8*/user/*44.12*/.email)),format.raw/*44.18*/("""</td>
		<td>"""),_display_(Seq[Any](/*45.8*/user/*45.12*/.fullName)),format.raw/*45.21*/("""</td>
		<td class="actions">
			<a href=""""),_display_(Seq[Any](/*47.14*/routes/*47.20*/.User.show(corporate.code, user.id))),format.raw/*47.55*/("""" class="btn-link btn-detail">"""),_display_(Seq[Any](/*47.86*/doku/*47.90*/.kirimdoku.util.MultiLanguage.getLanguage("LANG108","Detail"))),format.raw/*47.151*/("""</a>

			"""),_display_(Seq[Any](/*49.5*/restrict(la(as("admin"), as("mainagent")))/*49.47*/ {_display_(Seq[Any](format.raw/*49.49*/("""
			"""),format.raw/*63.8*/("""
			""")))})),format.raw/*64.5*/("""

			"""),_display_(Seq[Any](/*66.5*/if(user.hasRole("mainagent"))/*66.34*/ {_display_(Seq[Any](format.raw/*66.36*/("""
			<a href=""""),_display_(Seq[Any](/*67.14*/routes/*67.20*/.User.list(corporate.code, user.id))),format.raw/*67.55*/("""" class="btn-link slide" data-slide-operation="next">Superv<i class="icon-chevron-right"></i></a>
			""")))})),format.raw/*68.5*/("""
			"""),_display_(Seq[Any](/*69.5*/if(user.hasRole("supervisor"))/*69.35*/ {_display_(Seq[Any](format.raw/*69.37*/("""
			<a href=""""),_display_(Seq[Any](/*70.14*/routes/*70.20*/.User.list(corporate.code, user.id))),format.raw/*70.55*/("""" class="btn-link slide" data-slide-operation="next">Operat<i class="icon-chevron-right"></i></a> 
			""")))})),format.raw/*71.5*/("""
		</td>
	</tr>
	""")))})),format.raw/*74.3*/("""
	</tbody>
</table>

""")))})),format.raw/*78.2*/("""
<div class="form-actions">
	<div class="pull-right">
		"""),_display_(Seq[Any](/*81.4*/dynamic("allowCreateUser")/*81.30*/ {_display_(Seq[Any](format.raw/*81.32*/("""
		"""),_display_(Seq[Any](/*82.4*/if(parent.hasRole("admin"))/*82.31*/ {_display_(Seq[Any](format.raw/*82.33*/("""
			<a class="btn" href=""""),_display_(Seq[Any](/*83.26*/routes/*83.32*/.User.newCreate(corporate.code, "mainagent", parent.id))),format.raw/*83.87*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*83.116*/doku/*83.120*/.kirimdoku.util.MultiLanguage.getLanguage("LANG370","Add New MainAgent"))),format.raw/*83.192*/("""</a>
		""")))})),format.raw/*84.4*/("""
		"""),_display_(Seq[Any](/*85.4*/if(parent.hasRole("mainagent"))/*85.35*/ {_display_(Seq[Any](format.raw/*85.37*/("""
			<a class="btn" href=""""),_display_(Seq[Any](/*86.26*/routes/*86.32*/.User.newCreate(corporate.code, "supervisor", parent.id))),format.raw/*86.88*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*86.117*/doku/*86.121*/.kirimdoku.util.MultiLanguage.getLanguage("LANG371","Add New Supervisor"))),format.raw/*86.194*/("""</a>
		""")))})),format.raw/*87.4*/("""
		"""),_display_(Seq[Any](/*88.4*/if(parent.hasRole("supervisor"))/*88.36*/ {_display_(Seq[Any](format.raw/*88.38*/("""
			<a class="btn" href=""""),_display_(Seq[Any](/*89.26*/routes/*89.32*/.User.newCreate(corporate.code, "operator", parent.id))),format.raw/*89.86*/(""""><i class="icon-plus"></i> """),_display_(Seq[Any](/*89.115*/doku/*89.119*/.kirimdoku.util.MultiLanguage.getLanguage("LANG372","Add New Operator"))),format.raw/*89.190*/("""</a>
		""")))})),format.raw/*90.4*/("""
		""")))})),format.raw/*91.4*/("""
	</div>
</div>
</div>
"""))}
    }
    
    def render(corporate:models.Corporate,parent:models.User,currentPage:com.avaje.ebean.Page[models.User]): play.api.templates.Html = apply(corporate,parent,currentPage)
    
    def f:((models.Corporate,models.User,com.avaje.ebean.Page[models.User]) => play.api.templates.Html) = (corporate,parent,currentPage) => apply(corporate,parent,currentPage)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/list_users.scala.html
                    HASH: 8dc9d8414a38366d5d5dc84dd469feee0bbe1635
                    MATRIX: 789->1|1066->97|1094->204|1167->243|1229->297|1268->299|1402->398|1415->402|1496->461|1530->478|1543->483|1582->484|1622->489|1681->539|1721->541|1778->562|1793->568|1859->612|1933->649|1947->653|2029->712|2077->729|2112->733|2178->764|2190->768|2274->830|2314->835|2350->862|2390->864|2433->875|2472->879|2512->910|2552->912|2596->924|2635->928|2676->960|2716->962|2758->972|2797->976|2845->1015|2885->1017|2927->1027|2966->1031|3043->1099|3083->1101|3121->1103|3134->1107|3217->1167|3256->1169|3272->1175|3304->1184|3339->1186|3436->1248|3482->1285|3521->1286|3582->1312|3595->1316|3689->1388|3721->1403|3733->1408|3771->1409|3924->1527|3937->1531|4016->1588|4065->1602|4078->1606|4171->1677|4220->1691|4233->1695|4314->1754|4363->1768|4376->1772|4459->1833|4529->1868|4577->1900|4617->1902|4666->1916|4679->1920|4712->1931|4760->1944|4773->1948|4801->1954|4849->1967|4862->1971|4893->1980|4971->2022|4986->2028|5043->2063|5110->2094|5123->2098|5207->2159|5252->2169|5303->2211|5343->2213|5374->3052|5410->3057|5451->3063|5489->3092|5529->3094|5579->3108|5594->3114|5651->3149|5784->3251|5824->3256|5863->3286|5903->3288|5953->3302|5968->3308|6025->3343|6159->3446|6208->3464|6261->3486|6353->3543|6388->3569|6428->3571|6467->3575|6503->3602|6543->3604|6605->3630|6620->3636|6697->3691|6763->3720|6777->3724|6872->3796|6911->3804|6950->3808|6990->3839|7030->3841|7092->3867|7107->3873|7185->3929|7251->3958|7265->3962|7361->4035|7400->4043|7439->4047|7480->4079|7520->4081|7582->4107|7597->4113|7673->4167|7739->4196|7753->4200|7847->4271|7886->4279|7921->4283
                    LINES: 26->1|32->1|34->5|37->8|37->8|37->8|38->9|38->9|38->9|39->10|39->10|39->10|40->11|40->11|40->11|41->12|41->12|41->12|41->12|41->12|41->12|42->13|43->14|45->16|45->16|45->16|46->17|46->17|46->17|46->17|47->18|47->18|47->18|47->18|48->19|48->19|48->19|48->19|49->20|49->20|49->20|49->20|50->21|50->21|50->21|50->21|50->21|50->21|50->21|50->21|50->21|50->21|55->26|55->26|55->26|57->28|57->28|57->28|59->30|59->30|59->30|63->34|63->34|63->34|64->35|64->35|64->35|65->36|65->36|65->36|66->37|66->37|66->37|70->41|70->41|70->41|72->43|72->43|72->43|73->44|73->44|73->44|74->45|74->45|74->45|76->47|76->47|76->47|76->47|76->47|76->47|78->49|78->49|78->49|79->63|80->64|82->66|82->66|82->66|83->67|83->67|83->67|84->68|85->69|85->69|85->69|86->70|86->70|86->70|87->71|90->74|94->78|97->81|97->81|97->81|98->82|98->82|98->82|99->83|99->83|99->83|99->83|99->83|99->83|100->84|101->85|101->85|101->85|102->86|102->86|102->86|102->86|102->86|102->86|103->87|104->88|104->88|104->88|105->89|105->89|105->89|105->89|105->89|105->89|106->90|107->91
                    -- GENERATED --
                */
            