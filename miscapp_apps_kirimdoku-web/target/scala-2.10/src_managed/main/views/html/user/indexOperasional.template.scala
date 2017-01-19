
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
object indexOperasional extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[models.Corporate,models.User,models.SecurityRole,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate:models.Corporate, user:models.User, role:models.SecurityRole, filter: String = ""):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

def /*6.2*/breadcrumbs/*6.13*/(user:models.User) = {{
	if(user.hasRole("admin_operasional")) {
		Seq(
			routes.Corporate.admin_index(0).toString() -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management")
			, "" -> corporate.name
			, "" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG357","Operator management")
		)
	} else {
		Seq(
			routes.Corporate.admin_index(0).toString() -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management")
			, "" -> corporate.name
			, "" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG358","Management")
		)
	}
}};
Seq[Any](format.raw/*1.95*/("""

"""),format.raw/*5.1*/("""
"""),format.raw/*20.2*/("""

"""),_display_(Seq[Any](/*22.2*/layout(role.description+" Users Management", breadcrumbs(user))/*22.65*/ {_display_(Seq[Any](format.raw/*22.67*/("""
<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*24.10*/role/*24.14*/.description)),format.raw/*24.26*/(""" Management</h1>
</div>
	"""),_display_(Seq[Any](/*26.3*/partial/*26.10*/.flash_alert())),format.raw/*26.24*/("""

<div class="container-users">
	<div class="row">
		<div class="span12 well">
			<form action=""""),_display_(Seq[Any](/*31.19*/routes/*31.25*/.User.indexOperasional(corporate.code))),format.raw/*31.63*/("""" method="GET" class="form-search">
			<div class="input-append">
				<input type="search" id="searchbox" class="search-query span4" name="filter" value="" placeholder="Filter by name..."><button type="submit" id="searchsubmit" value="Search" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*33.219*/doku/*33.223*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*33.284*/("""</button>
			</div>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="span7">
			<div class="carousel slide">
				<div class="carousel-inner">
                    <div class="item active" data-url=""""),_display_(Seq[Any](/*43.57*/{routes.User.listOperasional(corporate.code).toString()+"?filter="})),_display_(Seq[Any](/*43.125*/filter/*43.131*/.replace(' ', '+'))),format.raw/*43.149*/("""">
					</div>
				</div>
			</div>
		</div>
		<div class="span5">
			<div id="detail-container" class="well">
				"""),_display_(Seq[Any](/*50.6*/doku/*50.10*/.kirimdoku.util.MultiLanguage.getLanguage("LANG359","You can view detail of user by clicking 'Detail' on the desired row"))),format.raw/*50.132*/("""
			</div>
		</div>
	</div>
</div>

<div class="modal fade hide" id="modal-ban">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Confirmation</h3>
    </div>
	<form method="GET" class="form-horizontal" data-remote="true">
		<div class="modal-body">
			<div class="control-groups">
				<p class="ban">"""),_display_(Seq[Any](/*64.21*/doku/*64.25*/.kirimdoku.util.MultiLanguage.getLanguage("LANG361","Are you sure you want to ban this user?"))),format.raw/*64.119*/("""</p>
				<p class="unban">"""),_display_(Seq[Any](/*65.23*/doku/*65.27*/.kirimdoku.util.MultiLanguage.getLanguage("LANG362","Are you sure you want to unban this user?"))),format.raw/*65.123*/("""</p>

				<dl>
					<dt><label>"""),_display_(Seq[Any](/*68.18*/doku/*68.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG363","Give a specific reason"))),format.raw/*68.99*/("""</label></dt>
					<dd><input type="text" class="input-xlarge" name="reason" value="" placeholder=""></dd>
				</dl>
			</div>
			<p class="container-error"></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*75.50*/doku/*75.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*75.115*/("""</a>
			<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*76.51*/doku/*76.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*76.117*/("""</button>
		</div>
	</form>
</div>

<div class="modal fade hide" id="modal-promote">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>"""),_display_(Seq[Any](/*84.14*/doku/*84.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG360","Confirmation"))),format.raw/*84.85*/("""</h3>
    </div>
	<form method="POST" class="form-horizontal">
		<div class="modal-body">
			<div class="control-groups">
				<p>"""),_display_(Seq[Any](/*89.9*/doku/*89.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG306","Are you sure you want to promote this user?"))),format.raw/*89.111*/("""</p>

				<dl>
					<dt><label>"""),_display_(Seq[Any](/*92.18*/doku/*92.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG363","Give a specific reason"))),format.raw/*92.99*/("""</label></dt>
					<dd><input type="text" class="input-xlarge" name="reason" value="" placeholder=""></dd>
				</dl>
			</div>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*98.50*/doku/*98.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*98.115*/("""</a>
			<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*99.51*/doku/*99.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*99.117*/("""</button>
		</div>
	</form>
</div>

<div class="modal fade hide" id="modal-demote">
	<div class="loading"></div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*108.38*/routes/*108.44*/.Assets.at("javascripts/user.js"))),format.raw/*108.77*/("""'></script>
""")))})),format.raw/*109.2*/("""
"""))}
    }
    
    def render(corporate:models.Corporate,user:models.User,role:models.SecurityRole,filter:String): play.api.templates.Html = apply(corporate,user,role,filter)
    
    def f:((models.Corporate,models.User,models.SecurityRole,String) => play.api.templates.Html) = (corporate,user,role,filter) => apply(corporate,user,role,filter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/indexOperasional.scala.html
                    HASH: 0208d53b4ca0d24f334d887dc0b2bb9f3b407079
                    MATRIX: 788->1|1045->203|1064->214|1676->94|1704->201|1732->796|1770->799|1842->862|1882->864|1954->900|1967->904|2001->916|2062->942|2078->949|2114->963|2247->1060|2262->1066|2322->1104|2643->1388|2657->1392|2741->1453|2988->1664|3087->1732|3103->1738|3144->1756|3295->1872|3308->1876|3453->1998|3861->2370|3874->2374|3991->2468|4054->2495|4067->2499|4186->2595|4254->2627|4267->2631|4366->2708|4651->2957|4664->2961|4748->3022|4839->3077|4852->3081|4937->3143|5178->3348|5191->3352|5280->3419|5445->3549|5458->3553|5579->3651|5647->3683|5660->3687|5759->3764|6009->3978|6022->3982|6106->4043|6197->4098|6210->4102|6295->4164|6490->4322|6506->4328|6562->4361|6607->4374
                    LINES: 26->1|31->6|31->6|46->1|48->5|49->20|51->22|51->22|51->22|53->24|53->24|53->24|55->26|55->26|55->26|60->31|60->31|60->31|62->33|62->33|62->33|72->43|72->43|72->43|72->43|79->50|79->50|79->50|93->64|93->64|93->64|94->65|94->65|94->65|97->68|97->68|97->68|104->75|104->75|104->75|105->76|105->76|105->76|113->84|113->84|113->84|118->89|118->89|118->89|121->92|121->92|121->92|127->98|127->98|127->98|128->99|128->99|128->99|137->108|137->108|137->108|138->109
                    -- GENERATED --
                */
            