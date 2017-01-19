
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
object admin_login extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Login.AdminLoginForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(loginForm: Form[Login.AdminLoginForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.41*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layoutLogin/*5.13*/ {_display_(Seq[Any](format.raw/*5.15*/("""
"""),_display_(Seq[Any](/*6.2*/if(loginForm.hasGlobalErrors)/*6.31*/ {_display_(Seq[Any](format.raw/*6.33*/("""
<div class="alert alert-error">
	"""),_display_(Seq[Any](/*8.3*/loginForm/*8.12*/.globalError().message)),format.raw/*8.34*/("""
</div>
""")))})),format.raw/*10.2*/("""

"""),_display_(Seq[Any](/*12.2*/if(flash.contains("success"))/*12.31*/ {_display_(Seq[Any](format.raw/*12.33*/("""
<div class="alert alert-success">
	"""),_display_(Seq[Any](/*14.3*/flash/*14.8*/.get("success"))),format.raw/*14.23*/("""
</div>
""")))})),format.raw/*16.2*/("""

<script type="text/javascript">
	var RecaptchaOptions = """),format.raw/*19.25*/("""{"""),format.raw/*19.26*/("""
		theme : 'white'
	"""),format.raw/*21.2*/("""}"""),format.raw/*21.3*/(""";
</script>

"""),_display_(Seq[Any](/*24.2*/helper/*24.8*/.form(routes.Login.admin_authenticate, args= 'class->"login-form")/*24.74*/ {_display_(Seq[Any](format.raw/*24.76*/("""
<div class="control-group row-fluid">
	"""),_display_(Seq[Any](/*26.3*/inputText(loginForm("email"), 
	'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG002","Email"), 
	'class -> "required input-xlarge span12", 
	'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG003","Your email address"), 
	'value -> ""))),format.raw/*30.15*/("""
</div>
<div class="control-group row-fluid">
	"""),_display_(Seq[Any](/*33.3*/inputPassword(loginForm("password"), 
	'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG004","Password"), 
	'class -> "required input-xlarge span12", 
	'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG005","Your password")
	))),format.raw/*37.3*/("""

	<div class="pull-right" style="margin-top: -10px"><a data-toggle="modal" href="#forgetPassword" >"""),_display_(Seq[Any](/*39.100*/doku/*39.104*/.kirimdoku.util.MultiLanguage.getLanguage("LANG006","Forgot your password?"))),format.raw/*39.180*/("""</a></div>
</div>
<div class="wrapper-code">
    <img src=""""),_display_(Seq[Any](/*42.16*/routes/*42.22*/.Login.captcha)),format.raw/*42.36*/("""" maxLength="8" width="205" height="50" style="margin-bottom: 12px;" alt="SecurityCode" id="kaptchaImage"/>
    <input type="text" autocomplete="off" class="default-input text fright" id="inputsecuritycode" name="recaptcha_challenge_field" placeholder="Security Code...">
    <br/><small>"""),_display_(Seq[Any](/*44.18*/doku/*44.22*/.kirimdoku.util.MultiLanguage.getLanguage("LANG305","Can't read the image? Click it to get a new one."))),format.raw/*44.125*/("""</small>
    <div class="clear"></div>
</div>
<div class="form-controls">

	<div class="pull-right">
		<button type="submit" class="btn btn-primary" data-disable-with='"""),_display_(Seq[Any](/*50.69*/doku/*50.73*/.kirimdoku.util.MultiLanguage.getLanguage("LANG490","Logging in"))),format.raw/*50.138*/("""'>"""),_display_(Seq[Any](/*50.141*/doku/*50.145*/.kirimdoku.util.MultiLanguage.getLanguage("LANG007","Sign In"))),format.raw/*50.207*/("""</button>
	</div>

</div>
    <script>
	$().ready(function() """),format.raw/*55.23*/("""{"""),format.raw/*55.24*/("""
        $('#kaptchaImage').click(function () """),format.raw/*56.46*/("""{"""),format.raw/*56.47*/("""
            $(this).hide()
            .attr('src', '"""),_display_(Seq[Any](/*58.28*/routes/*58.34*/.Login.captcha)),format.raw/*58.48*/("""?' + Math.floor(Math.random()*100) )
            .fadeIn();
        """),format.raw/*60.9*/("""}"""),format.raw/*60.10*/(""");
	"""),format.raw/*61.2*/("""}"""),format.raw/*61.3*/(""");
    </script>
<blockquote class="copyright" style="margin-top: 40px;">
	Powered by  <a href="http://www.doku.com"><img src="/assets/images/doku_small.png" height="25px" style="margin-top: -15px;"/></a>
</blockquote>
""")))})),format.raw/*66.2*/("""

<div class="modal hide" id="forgetPassword">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>"""),_display_(Seq[Any](/*71.8*/doku/*71.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG006","Forgot your password?"))),format.raw/*71.88*/("""</h3>
	</div>
	"""),_display_(Seq[Any](/*73.3*/helper/*73.9*/.form(routes.Login.forgetPassword,
	'id -> "forgetPassword-form",
	Symbol("data-remote") -> "true",
	Symbol("data-type") -> "text",
	'class -> "form-horizontal")/*77.30*/ {_display_(Seq[Any](format.raw/*77.32*/("""
	<div class="alert-container"></div>
	<div class="modal-body">
		<div id="formForgetPassword">
			<div class="control-group">
				"""),_display_(Seq[Any](/*82.6*/inputText(loginForm("forget-email"), 'id -> "forget-email", 'class -> "span5 required email",
				'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG306","Please input your email address below"),
				'placeholder -> "Email address.."
				))),format.raw/*85.6*/("""
			</div>
		</div>
		<div id="confirmForgetPassword" class="well" style="display:none;">
			"""),_display_(Seq[Any](/*89.5*/doku/*89.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG307","A confirmation link to for your forgot password  request has been sent to your email. Please check your email inbox and follow the instructions."))),format.raw/*89.208*/("""
		</div>
	</div>
	<div class="modal-footer">
		<button type="submit" class="btn btn-primary" data-disable-with="Processing...">"""),_display_(Seq[Any](/*93.84*/doku/*93.88*/.kirimdoku.util.MultiLanguage.getLanguage("LANG308","Submit"))),format.raw/*93.149*/("""</button>
	</div>
	""")))})),format.raw/*95.3*/("""
</div>
""")))})),format.raw/*97.2*/("""
"""))}
    }
    
    def render(loginForm:Form[Login.AdminLoginForm]): play.api.templates.Html = apply(loginForm)
    
    def f:((Form[Login.AdminLoginForm]) => play.api.templates.Html) = (loginForm) => apply(loginForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/main/admin_login.scala.html
                    HASH: e9d8865b8c9e14c44fb47fd9f1d2b56bac09c070
                    MATRIX: 754->1|920->40|947->92|983->94|1002->105|1041->107|1077->109|1114->138|1153->140|1222->175|1239->184|1282->206|1322->215|1360->218|1398->247|1438->249|1510->286|1523->291|1560->306|1600->315|1686->373|1715->374|1762->394|1790->395|1839->409|1853->415|1928->481|1968->483|2044->524|2331->789|2414->837|2691->1093|2829->1194|2843->1198|2942->1274|3038->1334|3053->1340|3089->1354|3414->1643|3427->1647|3553->1750|3758->1919|3771->1923|3859->1988|3899->1991|3913->1995|3998->2057|4087->2118|4116->2119|4190->2165|4219->2166|4310->2221|4325->2227|4361->2241|4456->2309|4485->2310|4516->2314|4544->2315|4795->2535|4982->2687|4995->2691|5093->2767|5144->2783|5158->2789|5328->2950|5368->2952|5535->3084|5806->3334|5935->3428|5947->3432|6169->3631|6334->3760|6347->3764|6431->3825|6482->3845|6522->3854
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|35->6|35->6|35->6|37->8|37->8|37->8|39->10|41->12|41->12|41->12|43->14|43->14|43->14|45->16|48->19|48->19|50->21|50->21|53->24|53->24|53->24|53->24|55->26|59->30|62->33|66->37|68->39|68->39|68->39|71->42|71->42|71->42|73->44|73->44|73->44|79->50|79->50|79->50|79->50|79->50|79->50|84->55|84->55|85->56|85->56|87->58|87->58|87->58|89->60|89->60|90->61|90->61|95->66|100->71|100->71|100->71|102->73|102->73|106->77|106->77|111->82|114->85|118->89|118->89|118->89|122->93|122->93|122->93|124->95|126->97
                    -- GENERATED --
                */
            