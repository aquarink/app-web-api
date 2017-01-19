
package views.html.main

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
object resetPassword extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,models.User,Form[Login.ResetPasswordForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(key: String, user: models.User, resetForm: Form[Login.ResetPasswordForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.76*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layoutLogin/*5.13*/ {_display_(Seq[Any](format.raw/*5.15*/("""
    """),_display_(Seq[Any](/*6.6*/if(resetForm.hasGlobalErrors)/*6.35*/ {_display_(Seq[Any](format.raw/*6.37*/("""
    <div class="alert alert-error">
        """),_display_(Seq[Any](/*8.10*/resetForm/*8.19*/.globalError().message)),format.raw/*8.41*/("""
    </div>
    """)))})),format.raw/*10.6*/("""

	<div class="page-header">
		<h3>Reset Password</h3>
		<small><strong>"""),_display_(Seq[Any](/*14.19*/user/*14.23*/.firstName)),format.raw/*14.33*/("""</strong>, Please follow the instruction below</small>
	</div>

	"""),_display_(Seq[Any](/*17.3*/partial/*17.10*/.flash_alert())),format.raw/*17.24*/("""


	"""),_display_(Seq[Any](/*20.3*/helper/*20.9*/.form(routes.Login.resetPasswordSubmit(),
	'id -> "resetPassword-form",
	'class -> "login-form")/*22.25*/ {_display_(Seq[Any](format.raw/*22.27*/("""
	<input type="hidden" name="user.id" value=""""),_display_(Seq[Any](/*23.46*/{user.id})),format.raw/*23.55*/(""""/>
	<input type="hidden" name="key" value=""""),_display_(Seq[Any](/*24.42*/{key})),format.raw/*24.47*/(""""/>
        <div class="control-group row-fluid">
			"""),_display_(Seq[Any](/*26.5*/inputPassword(resetForm("password"), '_label -> "Please enter your new password:", 'class -> "span12 input-xlarge required password", 'minlength -> "6", 'placeholder -> "Your password"))),format.raw/*26.190*/("""
        </div>
        <div class="control-group row-fluid">
            """),_display_(Seq[Any](/*29.14*/inputPassword(resetForm("password2"), '_label -> "Confirm your new password:", 'class -> "span12 input-xlarge required password", 'minlength -> "6", 'placeholder -> "Repeat Your password"))),format.raw/*29.202*/("""
        </div>

        <div class="form-controls">
            <div class="pull-right">
                <button type="submit" class="btn btn-primary">Reset password</button>
            </div>
        </div>
    """)))})),format.raw/*37.6*/("""
""")))})),format.raw/*38.2*/("""

"""))}
    }
    
    def render(key:String,user:models.User,resetForm:Form[Login.ResetPasswordForm]): play.api.templates.Html = apply(key,user,resetForm)
    
    def f:((String,models.User,Form[Login.ResetPasswordForm]) => play.api.templates.Html) = (key,user,resetForm) => apply(key,user,resetForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/main/resetPassword.scala.html
                    HASH: d83f357be51144bb0e9bc9db125fb34f89a73cac
                    MATRIX: 778->1|979->75|1006->127|1042->129|1061->140|1100->142|1140->148|1177->177|1216->179|1297->225|1314->234|1357->256|1405->273|1514->346|1527->350|1559->360|1660->426|1676->433|1712->447|1752->452|1766->458|1871->554|1911->556|1993->602|2024->611|2105->656|2132->661|2221->715|2429->900|2540->975|2751->1163|2997->1378|3030->1380
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|35->6|35->6|35->6|37->8|37->8|37->8|39->10|43->14|43->14|43->14|46->17|46->17|46->17|49->20|49->20|51->22|51->22|52->23|52->23|53->24|53->24|55->26|55->26|58->29|58->29|66->37|67->38
                    -- GENERATED --
                */
            