
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
object forget_password_email extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[models.User,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user: models.User, link: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.35*/("""
"""),format.raw/*4.1*/("""



<div style="font-family: helvetica; font-size: 14px; padding: 3em;">
	<div>
		<img src="http://www.kirimdoku.com/assets/images/doku_small.png"><br/><br/>
	</div>
	<div>
		Dear <b>"""),_display_(Seq[Any](/*13.12*/user/*13.16*/.firstName)),format.raw/*13.26*/(""" """),_display_(Seq[Any](/*13.28*/user/*13.32*/.lastName)),format.raw/*13.41*/("""</b>,
		<p>We have received your request  to change password on your account.
			In order for us to process your request, please click the link below:</p><br/><br/>
		<p><a href=""""),_display_(Seq[Any](/*16.16*/link)),format.raw/*16.20*/("""">"""),_display_(Seq[Any](/*16.23*/link)),format.raw/*16.27*/("""</a></p>

		<br/><br/>


		<p><strong>Important Note:</strong></p>
			<pre style="font-family: helvetica; font-size: 14px;">       1. Link valid for only 24 hours from the receipt of this email.
       2. For the security of data and information, dont forward this email to anyone..</pre>
		<br/>
		<br/>
		<br/>
		<p>Thank You,</p>
		<p>DOKU Team</p>
		<br/>
		<br/>
		<br/>
		<center><p style="text-shadow:#666666; font-size:16px ; font-weight:bold"><i>Send Money Made Easy</i></p></center><p align="center" style="font-size:10px; margin-top:-15px;">"Thank you for using our services and have a nice day"</p>
		<p align="right"><b>Customer Care DOKU </b> +62 2261807511 (Monday - Saturday)</p>
		<hr/>
		<p style="font-size: 11px;"><b>Copyright @ 2012 DOKU</b> Please be sure to keep your <b>transaction and verification code (PIN)</b>.
			Make sure you send the codes to the person that registered in the data sender or receiver. <b>Strictly prohibited</b> to give
			<b>the transaction code and verification code (PIN)</b> to unregistered person in the data sender or receiver. All kinds of offenses or omission will be dealt according to company rules and remittance regulations.
			Content of this email and all attachment is intended for the email recipient only. Any opinions that contained in this email, is the personal opinion
			of the sender and not the representation of <b>DOKU</b>. If you are not the recepient of this email, you have no right to copying and showing this email to another person.
			Please contact the sender of this email, if you are sure that this email is not intende for you.
		</p>

	</div>
</div>
"""))}
    }
    
    def render(user:models.User,link:String): play.api.templates.Html = apply(user,link)
    
    def f:((models.User,String) => play.api.templates.Html) = (user,link) => apply(user,link)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/main/forget_password_email.scala.html
                    HASH: 04ef06b2e3169031671761f79652bd3ec8ccfe0e
                    MATRIX: 756->1|916->34|943->86|1163->270|1176->274|1208->284|1246->286|1259->290|1290->299|1506->479|1532->483|1571->486|1597->490
                    LINES: 26->1|32->1|33->4|42->13|42->13|42->13|42->13|42->13|42->13|45->16|45->16|45->16|45->16
                    -- GENERATED --
                */
            