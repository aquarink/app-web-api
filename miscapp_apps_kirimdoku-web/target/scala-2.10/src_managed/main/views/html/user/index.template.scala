
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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[models.Corporate,models.User,models.SecurityRole,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(corporate:models.Corporate, user:models.User, role:models.SecurityRole, filter: String = ""):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

def /*6.2*/breadcrumbs/*6.13*/(user:models.User) = {{
	if(user.hasRole("admin")) {
		Seq(
			routes.Corporate.admin_index(0).toString() -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management")
			, "" -> corporate.name
			, "" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG355","Main agent management")
		)
	} else if(user.hasRole("mainagent")) {
		Seq(
			routes.Corporate.admin_index(0).toString() -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management")
			, routes.Corporate.admin_index(0).toString() -> corporate.name
			, routes.User.index(user.corporate.code).toString() -> user.fullName
			, "" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG356","Supervisor management")
		)
	} else if(user.hasRole("supervisor")) {
		Seq(
			routes.Corporate.admin_index(0).toString() -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG354","Corporate management")
			, routes.Corporate.admin_index(0).toString() -> corporate.name
			/*, routes.User.index(user.supervisor.corporate.code).toString() -> user.supervisor.fullName*/
			, routes.User.index(user.corporate.code).toString() -> user.fullName
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
"""),format.raw/*35.2*/("""

"""),_display_(Seq[Any](/*37.2*/layout(role.description+" Users Management", breadcrumbs(user))/*37.65*/ {_display_(Seq[Any](format.raw/*37.67*/("""
<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*39.10*/role/*39.14*/.description)),format.raw/*39.26*/(""" Management</h1>
</div>
	"""),_display_(Seq[Any](/*41.3*/partial/*41.10*/.flash_alert())),format.raw/*41.24*/("""

<div class="container-users">
	<div class="row">
		<div class="span12 well">
			<form action=""""),_display_(Seq[Any](/*46.19*/routes/*46.25*/.User.index(corporate.code))),format.raw/*46.52*/("""" method="GET" class="form-search">
			<div class="input-append">
				<input type="search" id="searchbox" class="search-query span4" name="filter" value="" placeholder="Filter by name..."><button type="submit" id="searchsubmit" value="Search" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*48.219*/doku/*48.223*/.kirimdoku.util.MultiLanguage.getLanguage("LANG100","Search"))),format.raw/*48.284*/("""</button>
			</div>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="span7">
			<div class="carousel slide">
				<div class="carousel-inner">
                    <div class="item active" data-url=""""),_display_(Seq[Any](/*58.57*/{routes.User.list(corporate.code, user.id).toString()+"?filter="})),_display_(Seq[Any](/*58.123*/filter/*58.129*/.replace(' ', '+'))),format.raw/*58.147*/("""">
					</div>
				</div>
			</div>
		</div>
		<div class="span5">
			<div id="detail-container" class="well">
				"""),_display_(Seq[Any](/*65.6*/doku/*65.10*/.kirimdoku.util.MultiLanguage.getLanguage("LANG359","You can view detail of user by clicking 'Detail' on the desired row"))),format.raw/*65.132*/("""
			</div>
		</div>
	</div>
</div>

<div class="modal fade hide" id="modal-ban">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>"""),_display_(Seq[Any](/*74.14*/doku/*74.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG360","Confirmation"))),format.raw/*74.85*/("""</h3>
    </div>
	<form method="GET" class="form-horizontal" data-remote="true">
		<div class="modal-body">
			<div class="control-groups">
				<p class="ban">"""),_display_(Seq[Any](/*79.21*/doku/*79.25*/.kirimdoku.util.MultiLanguage.getLanguage("LANG361","Are you sure you want to ban this user?"))),format.raw/*79.119*/("""</p>
				<p class="unban">"""),_display_(Seq[Any](/*80.23*/doku/*80.27*/.kirimdoku.util.MultiLanguage.getLanguage("LANG362","Are you sure you want to unban this user?"))),format.raw/*80.123*/("""</p>

				<dl>
					<dt><label>"""),_display_(Seq[Any](/*83.18*/doku/*83.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG363","Give a specific reason"))),format.raw/*83.99*/("""</label></dt>
					<dd><input type="text" class="input-xlarge" name="reason" value="" placeholder=""></dd>
				</dl>
			</div>
			<p class="container-error"></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*90.50*/doku/*90.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*90.115*/("""</a>
			<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*91.51*/doku/*91.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*91.117*/("""</button>
		</div>
	</form>
</div>

<div class="modal fade hide" id="modal-promote">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>"""),_display_(Seq[Any](/*99.14*/doku/*99.18*/.kirimdoku.util.MultiLanguage.getLanguage("LANG360","Confirmation"))),format.raw/*99.85*/("""</h3>
    </div>
	<form method="POST" class="form-horizontal">
		<div class="modal-body">
			<div class="control-groups">
				<p>"""),_display_(Seq[Any](/*104.9*/doku/*104.13*/.kirimdoku.util.MultiLanguage.getLanguage("LANG306","Are you sure you want to promote this user?"))),format.raw/*104.111*/("""</p>

				<dl>
					<dt><label>"""),_display_(Seq[Any](/*107.18*/doku/*107.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG363","Give a specific reason"))),format.raw/*107.99*/("""</label></dt>
					<dd><input type="text" class="input-xlarge" name="reason" value="" placeholder=""></dd>
				</dl>
			</div>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">"""),_display_(Seq[Any](/*113.50*/doku/*113.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*113.115*/("""</a>
			<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*114.51*/doku/*114.55*/.kirimdoku.util.MultiLanguage.getLanguage("LANG074","Proceed"))),format.raw/*114.117*/("""</button>
		</div>
	</form>
</div>

<div class="modal fade hide" id="modal-demote">
	<div class="loading"></div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*123.38*/routes/*123.44*/.Assets.at("javascripts/user.js"))),format.raw/*123.77*/("""'></script>
""")))})),format.raw/*124.2*/("""
"""))}
    }
    
    def render(corporate:models.Corporate,user:models.User,role:models.SecurityRole,filter:String): play.api.templates.Html = apply(corporate,user,role,filter)
    
    def f:((models.Corporate,models.User,models.SecurityRole,String) => play.api.templates.Html) = (corporate,user,role,filter) => apply(corporate,user,role,filter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/index.scala.html
                    HASH: f971b47abfd58fb58684ea47532bde604359d71f
                    MATRIX: 777->1|1034->203|1053->214|2572->94|2600->201|2628->1703|2666->1706|2738->1769|2778->1771|2850->1807|2863->1811|2897->1823|2958->1849|2974->1856|3010->1870|3143->1967|3158->1973|3207->2000|3528->2284|3542->2288|3626->2349|3873->2560|3970->2626|3986->2632|4027->2650|4178->2766|4191->2770|4336->2892|4573->3093|4586->3097|4675->3164|4871->3324|4884->3328|5001->3422|5064->3449|5077->3453|5196->3549|5264->3581|5277->3585|5376->3662|5661->3911|5674->3915|5758->3976|5849->4031|5862->4035|5947->4097|6188->4302|6201->4306|6290->4373|6456->4503|6470->4507|6592->4605|6661->4637|6675->4641|6775->4718|7026->4932|7040->4936|7125->4997|7217->5052|7231->5056|7317->5118|7512->5276|7528->5282|7584->5315|7629->5328
                    LINES: 26->1|31->6|31->6|61->1|63->5|64->35|66->37|66->37|66->37|68->39|68->39|68->39|70->41|70->41|70->41|75->46|75->46|75->46|77->48|77->48|77->48|87->58|87->58|87->58|87->58|94->65|94->65|94->65|103->74|103->74|103->74|108->79|108->79|108->79|109->80|109->80|109->80|112->83|112->83|112->83|119->90|119->90|119->90|120->91|120->91|120->91|128->99|128->99|128->99|133->104|133->104|133->104|136->107|136->107|136->107|142->113|142->113|142->113|143->114|143->114|143->114|152->123|152->123|152->123|153->124
                    -- GENERATED --
                */
            