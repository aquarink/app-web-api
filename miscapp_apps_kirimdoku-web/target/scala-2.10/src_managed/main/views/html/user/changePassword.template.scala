
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
object changePassword extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[controllers.User.ChangePassword],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[controllers.User.ChangePassword]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.49*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Change Password", Seq(
"" -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG292","Change password")
))/*7.3*/ {_display_(Seq[Any](format.raw/*7.5*/("""

"""),_display_(Seq[Any](/*9.2*/partial/*9.9*/.flash_alert())),format.raw/*9.23*/("""

	"""),_display_(Seq[Any](/*11.3*/if(myForm.hasGlobalErrors)/*11.29*/ {_display_(Seq[Any](format.raw/*11.31*/("""
	<div class="alert alert-error">
		"""),_display_(Seq[Any](/*13.4*/myForm/*13.10*/.globalError().message)),format.raw/*13.32*/("""
	</div>
	""")))})),format.raw/*15.3*/("""

	"""),_display_(Seq[Any](/*17.3*/helper/*17.9*/.form(routes.User.saveChangePassword(myForm("corporateCode").value, Long.parseLong(myForm("id").valueOr("0"))), args= 'class->"login-form")/*17.148*/ {_display_(Seq[Any](format.raw/*17.150*/("""
	<input type="hidden" name="corporateCode" value=""""),_display_(Seq[Any](/*18.52*/myForm("corporateCode")/*18.75*/.value)),format.raw/*18.81*/(""""/>
	<input type="hidden" name="id" value=""""),_display_(Seq[Any](/*19.41*/myForm("id")/*19.53*/.value)),format.raw/*19.59*/(""""/>

	"""),_display_(Seq[Any](/*21.3*/if(myForm("id").value.equals(session.get("userId")))/*21.55*/ {_display_(Seq[Any](format.raw/*21.57*/("""
	<div class="control-group">
		"""),_display_(Seq[Any](/*23.4*/inputPassword(myForm("currentPassword"), 
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG299","Current password:"), 
		'class -> "required input-xlarge", 
		'minlength -> 6, 
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG300","Your current password")
		))),format.raw/*28.4*/("""
	</div>
	""")))})),format.raw/*30.3*/("""
	<div class="control-group">
		"""),_display_(Seq[Any](/*32.4*/inputPassword(myForm("newPassword"), 
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG301","New password:"), 
		'class -> "required input-xlarge", 
		'minlength -> 6,
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG302","Input new password..")
		))),format.raw/*37.4*/("""
	</div>
	<div class="control-group">
		"""),_display_(Seq[Any](/*40.4*/inputPassword(myForm("newPassword2"), 
		'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG303","Confirm new password:"), 
		'class -> "required input-xlarge",
		'minlength -> 6, 
		'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG304","Repeat input new password..")
		))),format.raw/*45.4*/("""
	</div>

	<div class="form-actions">
		<button type="submit" class="btn btn-primary">"""),_display_(Seq[Any](/*49.50*/doku/*49.54*/.kirimdoku.util.MultiLanguage.getLanguage("LANG292","Change password"))),format.raw/*49.124*/("""</button>
	</div>
	""")))})),format.raw/*51.3*/("""
""")))})),format.raw/*52.2*/("""

"""))}
    }
    
    def render(myForm:Form[controllers.User.ChangePassword]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[controllers.User.ChangePassword]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/user/changePassword.scala.html
                    HASH: b9044a97e7c7bcb40777ad2af30e8930f70f3b61
                    MATRIX: 768->1|942->48|969->100|1005->102|1126->216|1164->218|1201->221|1215->228|1250->242|1289->246|1324->272|1364->274|1436->311|1451->317|1495->339|1537->350|1576->354|1590->360|1739->499|1780->501|1868->553|1900->576|1928->582|2008->626|2029->638|2057->644|2099->651|2160->703|2200->705|2268->738|2583->1032|2625->1043|2693->1076|2998->1360|3074->1401|3395->1701|3518->1788|3531->1792|3624->1862|3675->1882|3708->1884
                    LINES: 26->1|32->1|33->4|34->5|36->7|36->7|38->9|38->9|38->9|40->11|40->11|40->11|42->13|42->13|42->13|44->15|46->17|46->17|46->17|46->17|47->18|47->18|47->18|48->19|48->19|48->19|50->21|50->21|50->21|52->23|57->28|59->30|61->32|66->37|69->40|74->45|78->49|78->49|78->49|80->51|81->52
                    -- GENERATED --
                */
            