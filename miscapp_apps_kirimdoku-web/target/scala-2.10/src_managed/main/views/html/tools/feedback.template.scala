
package views.html.tools

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
object feedback extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Tools.FeedbackForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[Tools.FeedbackForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.36*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Feedback Form")/*5.25*/{_display_(Seq[Any](format.raw/*5.26*/("""
<div class="page-header">
    <h2>"""),_display_(Seq[Any](/*7.10*/doku/*7.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG174","Feedback Form"))),format.raw/*7.82*/("""</h2>
</div>
<div class="row">

    <div class="span6 offset2" id="sendContainer">
        <div id="transactionFormContainer" class="step1 row">

            """),_display_(Seq[Any](/*14.14*/if(myForm.hasGlobalErrors)/*14.40*/ {_display_(Seq[Any](format.raw/*14.42*/("""
            <p class="alert alert-error">
            """),_display_(Seq[Any](/*16.14*/myForm/*16.20*/.globalError().message)),format.raw/*16.42*/("""
            </p>
            """)))})),format.raw/*18.14*/("""

			"""),_display_(Seq[Any](/*20.5*/if(flash.contains("error"))/*20.32*/ {_display_(Seq[Any](format.raw/*20.34*/("""
            <p class="alert alert-error">
            """),_display_(Seq[Any](/*22.14*/flash/*22.19*/.get("error"))),format.raw/*22.32*/("""
            </p>
            """)))})),format.raw/*24.14*/("""
            
            """),_display_(Seq[Any](/*26.14*/if(flash.contains("success"))/*26.43*/ {_display_(Seq[Any](format.raw/*26.45*/("""
            <p class="alert alert-success">
            """),_display_(Seq[Any](/*28.14*/flash/*28.19*/.get("success"))),format.raw/*28.34*/("""
            </p>
            """)))})),format.raw/*30.14*/("""

            """),_display_(Seq[Any](/*32.14*/helper/*32.20*/.form(routes.Tools.feedbackSubmit, 'id -> "feedbackForm", 'method -> "POST", 'class -> "form-vertical")/*32.123*/ {_display_(Seq[Any](format.raw/*32.125*/("""
                <div class="control-group">
                    """),_display_(Seq[Any](/*34.22*/helper/*34.28*/.inputText(myForm("subject"),
                    '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG175","Subject")
                    , 'class -> "required span6 subject"
                    , 'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG176","Subject your feedback")
                    , 'autocomplete -> "off"
                    , '_showConstraints -> false))),format.raw/*39.50*/("""
                </div>
                <div class="control-group">
                    """),_display_(Seq[Any](/*42.22*/helper/*42.28*/.textarea(myForm("body"),
                    '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG177","Message")
                    , 'rows -> "5"
                    , 'class -> "required span6 body"
					, 'rows -> "8"
                    , 'placeholder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG178","Your message here")
                    , 'autocomplete -> "off"
                    , '_help -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG179","Please define your feedback accurately, so that our customer service can respond to you quickly")
                    , '_showConstraints -> false))),format.raw/*50.50*/("""
                 </div>

                <div class="form-actions">
                    <input type="submit" id="feedbackSubmit" class="btn btn-primary" data-disable-with="Sending..." value='"""),_display_(Seq[Any](/*54.125*/doku/*54.129*/.kirimdoku.util.MultiLanguage.getLanguage("LANG180","Send Feedback"))),format.raw/*54.197*/("""'/>
                </div>
            """)))})),format.raw/*56.14*/("""
        </div>
    </div>
    <div class="span4">
        <div id="summary" class="well boxed">
            <!-- SUMMARY SIDEBAR HERE -->
			"""),_display_(Seq[Any](/*62.5*/doku/*62.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG181","In order to improving our services, we are very welcome if you give us feedback. Please decide the subject (Operational or Technical) and then write your feedback. Feedback will sent to our Customer Service directly, and we will reply your feedback after we analyze as soon as possible."))),format.raw/*62.350*/("""
        </div>
    </div>
</div>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*67.38*/routes/*67.44*/.Assets.at("javascripts/tools.js"))),format.raw/*67.78*/("""'></script>
<script type="text/javascript">
	$(function() """),format.raw/*69.15*/("""{"""),format.raw/*69.16*/("""
			/*  window.initCheckFxRate();*/
	"""),format.raw/*71.2*/("""}"""),format.raw/*71.3*/(""");
</script>
""")))})),format.raw/*73.2*/("""
"""))}
    }
    
    def render(myForm:Form[Tools.FeedbackForm]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[Tools.FeedbackForm]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/tools/feedback.scala.html
                    HASH: 0e18630b2caf44adb442edf359f441f52d264324
                    MATRIX: 750->1|911->35|938->87|974->89|1005->112|1043->113|1114->149|1126->153|1215->221|1410->380|1445->406|1485->408|1577->464|1592->470|1636->492|1699->523|1740->529|1776->556|1816->558|1908->614|1922->619|1957->632|2020->663|2083->690|2121->719|2161->721|2255->779|2269->784|2306->799|2369->830|2420->845|2435->851|2548->954|2589->956|2691->1022|2706->1028|3126->1426|3251->1515|3266->1521|3917->2150|4147->2343|4161->2347|4252->2415|4324->2455|4502->2598|4514->2602|4878->2943|4986->3015|5001->3021|5057->3055|5143->3113|5172->3114|5236->3151|5264->3152|5309->3166
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|36->7|36->7|36->7|43->14|43->14|43->14|45->16|45->16|45->16|47->18|49->20|49->20|49->20|51->22|51->22|51->22|53->24|55->26|55->26|55->26|57->28|57->28|57->28|59->30|61->32|61->32|61->32|61->32|63->34|63->34|68->39|71->42|71->42|79->50|83->54|83->54|83->54|85->56|91->62|91->62|91->62|96->67|96->67|96->67|98->69|98->69|100->71|100->71|102->73
                    -- GENERATED --
                */
            