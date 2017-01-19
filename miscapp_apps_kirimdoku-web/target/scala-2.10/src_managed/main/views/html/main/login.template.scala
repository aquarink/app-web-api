
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
object login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Login.LoginForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(loginForm: Form[Login.LoginForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.36*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layoutLogin/*5.13*/ {_display_(Seq[Any](format.raw/*5.15*/("""
"""),_display_(Seq[Any](/*6.2*/partial/*6.9*/.flash_alert())),format.raw/*6.23*/("""

    """),_display_(Seq[Any](/*8.6*/if(loginForm.hasGlobalErrors)/*8.35*/ {_display_(Seq[Any](format.raw/*8.37*/("""
    <div class="alert alert-error">
        """),_display_(Seq[Any](/*10.10*/loginForm/*10.19*/.globalError().message)),format.raw/*10.41*/("""
    </div>
    """)))})),format.raw/*12.6*/("""

    <script type="text/javascript">
        var RecaptchaOptions = """),format.raw/*15.32*/("""{"""),format.raw/*15.33*/("""
            theme : 'white'
        """),format.raw/*17.9*/("""}"""),format.raw/*17.10*/(""";
    </script>

    """),_display_(Seq[Any](/*20.6*/helper/*20.12*/.form(routes.Login.authenticate, args= 'class->"login-form")/*20.72*/ {_display_(Seq[Any](format.raw/*20.74*/("""
    <div class="control-group row-fluid">
        """),_display_(Seq[Any](/*22.10*/inputText(loginForm("email"), '_label -> "Email:", 'class -> "required email input-xlarge span12", 
        'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG003","Your email address"), 
        'value -> ""))),format.raw/*24.22*/("""
    </div>
    <div class="control-group row-fluid">
        """),_display_(Seq[Any](/*27.10*/inputPassword(loginForm("password"), '_label -> "Password:", 'class -> "required input-xlarge span12", 
        'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG005","Your password")
        ))),format.raw/*29.10*/("""
		<div class="pull-right" style="margin-top: -10px"><a data-toggle="modal" href="#forgetPassword" >"""),_display_(Seq[Any](/*30.101*/doku/*30.105*/.kirimdoku.util.MultiLanguage.getLanguage("LANG006","Forgot your password?"))),format.raw/*30.181*/("""</a></div>
    </div>
    <div class="form-controls">

        <div class="pull-right">
            <button type="submit" class="btn btn-primary" data-disable-with='"""),_display_(Seq[Any](/*35.79*/doku/*35.83*/.kirimdoku.util.MultiLanguage.getLanguage("LANG490","Logging in"))),format.raw/*35.148*/("""'>"""),_display_(Seq[Any](/*35.151*/doku/*35.155*/.kirimdoku.util.MultiLanguage.getLanguage("LANG007","Sign In"))),format.raw/*35.217*/("""</button>
        </div>

	</div>
	
	<blockquote class="copyright" style="margin-top: 40px;">
        Powered by  <a href="http://www.doku.com"><img src="/assets/images/doku_small.png" height="25px" style="margin-top: -15px;"/></a>
	</blockquote>
    """)))})),format.raw/*43.6*/("""

    <div class="modal hide" id="forgetPassword">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>"""),_display_(Seq[Any](/*48.18*/doku/*48.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG006","Forgot your password?"))),format.raw/*48.98*/("""</h3>
        </div>
        """),_display_(Seq[Any](/*50.10*/form(routes.Login.forgetPassword,
        'id -> "forgetPassword-form",
        Symbol("data-remote") -> "true",
        Symbol("data-type") -> "text",
        'class -> "form-horizontal")/*54.37*/ {_display_(Seq[Any](format.raw/*54.39*/("""
        <div class="modal-body">
            <div id="formForgetPassword">
                <div class="control-group">
					"""),_display_(Seq[Any](/*58.7*/inputText(loginForm("forget-email"),
					'id -> "forget-email",
				   	'class -> "span5 required email",
					'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG306","Please input your email address below"),
					'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG330","Email address..")
					))),format.raw/*63.7*/("""
                </div>
            </div>
			<div class="alert-container"></div>
            <div id="confirmForgetPassword" class="well" style="display:none;">
                """),_display_(Seq[Any](/*68.18*/doku/*68.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG307","A confirmation link to for your forgot password  request has been sent to your email. Please check your email inbox and follow the instructions."))),format.raw/*68.221*/("""
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary" data-disable-with="Processing...">"""),_display_(Seq[Any](/*72.94*/doku/*72.98*/.kirimdoku.util.MultiLanguage.getLanguage("LANG308","Submit"))),format.raw/*72.159*/("""</button>
        </div>
		""")))})),format.raw/*74.4*/("""
    </div>
""")))})),format.raw/*76.2*/("""
"""))}
    }
    
    def render(loginForm:Form[Login.LoginForm]): play.api.templates.Html = apply(loginForm)
    
    def f:((Form[Login.LoginForm]) => play.api.templates.Html) = (loginForm) => apply(loginForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/main/login.scala.html
                    HASH: aa326f5ddd3e354bca387184e765ba1288fbd71e
                    MATRIX: 743->1|904->35|931->87|967->89|986->100|1025->102|1061->104|1075->111|1110->125|1151->132|1188->161|1227->163|1309->209|1327->218|1371->240|1419->257|1516->326|1545->327|1609->364|1638->365|1695->387|1710->393|1779->453|1819->455|1907->507|2154->732|2253->795|2485->1005|2623->1106|2637->1110|2736->1186|2938->1352|2951->1356|3039->1421|3079->1424|3093->1428|3178->1490|3461->1742|3680->1925|3693->1929|3791->2005|3857->2035|4054->2223|4094->2225|4255->2351|4599->2674|4814->2853|4827->2857|5049->3056|5248->3219|5261->3223|5345->3284|5404->3312|5448->3325
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|35->6|35->6|35->6|37->8|37->8|37->8|39->10|39->10|39->10|41->12|44->15|44->15|46->17|46->17|49->20|49->20|49->20|49->20|51->22|53->24|56->27|58->29|59->30|59->30|59->30|64->35|64->35|64->35|64->35|64->35|64->35|72->43|77->48|77->48|77->48|79->50|83->54|83->54|87->58|92->63|97->68|97->68|97->68|101->72|101->72|101->72|103->74|105->76
                    -- GENERATED --
                */
            