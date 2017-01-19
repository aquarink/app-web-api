
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
object modal_demote extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Form[controllers.User.DemoteForm],models.User,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(form: Form[controllers.User.DemoteForm], user: models.User):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.62*/("""

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">×</button>
	<h3>Confirmation</h3>
</div>
"""),_display_(Seq[Any](/*7.2*/helper/*7.8*/.form(routes.User.updateRoleDemote(user.corporate.code, user.id))/*7.73*/ {_display_(Seq[Any](format.raw/*7.75*/("""
	<div class="modal-body">
		<div class="control-groups">
			<p>Are you sure you want to demote this user? Give a reason</p>

			"""),_display_(Seq[Any](/*12.5*/helper/*12.11*/.inputText(form("reason"),
			'_label -> "Give a reason",
			'class -> "required",
			'placeholder -> "",
			'_showConstraints -> false))),format.raw/*16.31*/("""
		</div>
		<div class="control-groups">
			"""),_display_(Seq[Any](/*19.5*/helper/*19.11*/.select(
			form("parentSupervisor.id"),
			helper.options(models.User.optionsSupervisorAlternativeForUser(user)),
			'_label -> "His/her new supervisor",
			'_showConstraints -> false,
			'class -> "required"
			))),format.raw/*25.5*/("""
		</div>
		<div class="control-groups">
			"""),_display_(Seq[Any](/*28.5*/helper/*28.11*/.select(
			form("agentSupervisor.id"),
			helper.options(models.User.optionsSupervisorAlternativeForUser(user)),
			'_label -> "Agents under his/her will be moved to supervisor",
			'_showConstraints -> false,
			'class -> "required"
			))),format.raw/*34.5*/("""
		</div>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Cancel</a>
		<button type="submit" class="btn btn-primary">Proceed</button>
	</div>
""")))})),format.raw/*41.2*/("""
"""))}
    }
    
    def render(form:Form[controllers.User.DemoteForm],user:models.User): play.api.templates.Html = apply(form,user)
    
    def f:((Form[controllers.User.DemoteForm],models.User) => play.api.templates.Html) = (form,user) => apply(form,user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/modal_demote.scala.html
                    HASH: 531aea9d9570e3503e3bb3466a5987fccc431ac1
                    MATRIX: 774->1|911->61|1074->190|1087->196|1160->261|1199->263|1364->393|1379->399|1537->535|1617->580|1632->586|1867->800|1947->845|1962->851|2222->1090|2431->1268
                    LINES: 26->1|29->1|35->7|35->7|35->7|35->7|40->12|40->12|44->16|47->19|47->19|53->25|56->28|56->28|62->34|69->41
                    -- GENERATED --
                */
            