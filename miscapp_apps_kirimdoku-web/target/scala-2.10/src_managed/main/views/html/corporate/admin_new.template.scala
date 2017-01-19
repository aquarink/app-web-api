
package views.html.corporate

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
object admin_new extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[models.Corporate],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Corporate]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.34*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Corporate new")/*5.25*/ {_display_(Seq[Any](format.raw/*5.27*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*7.7*/doku/*7.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG191","Add new Corporate"))),format.raw/*7.83*/("""</h1>
</div>

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""

"""),_display_(Seq[Any](/*12.2*/helper/*12.8*/.form(routes.Corporate.admin_create(), 'method -> "POST", 'enctype -> "multipart/form-data", 'class -> "form-vertical")/*12.127*/ {_display_(Seq[Any](format.raw/*12.129*/("""
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*15.5*/inputText(myForm("code"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG316","Corporate code"), 
			'_showConstraints -> false, 'class -> "code required", 'minlength ->"3", 'maxlength -> "3", 'placeholder -> "3 Char identifier Corporate Code"))),format.raw/*17.147*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*22.5*/inputText(myForm("name"), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG295","Name"), 
			'_showConstraints -> false, 'class -> "required", 'minlength ->"3", 'maxlength -> "32", 'placeholder -> "Corporate Name..."))),format.raw/*24.128*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*29.5*/select(myForm("country.code"), options(models.Country.options), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG118","Country"), 
			'class -> "country", '_showConstraints -> false))),format.raw/*31.52*/("""
		</div>
	</div>
	<div class="row">
		<div class="span6 control-group">
			"""),_display_(Seq[Any](/*36.5*/select(myForm("currency.code"), options(models.Currency.options), 
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG087","Currency"), 
			'class -> "currency", '_showConstraints -> false))),format.raw/*38.53*/("""
		</div>
	</div>

	<div class="span12">
		<div class="form-actions">
			<button class="btn btn-inverse btn-back"><i class="icon-remove icon-white"></i> """),_display_(Seq[Any](/*44.85*/doku/*44.89*/.kirimdoku.util.MultiLanguage.getLanguage("LANG127","Cancel"))),format.raw/*44.150*/("""</button>
			<button class="btn btn-primary" type="submit"><i class="icon-plus icon-white"></i> """),_display_(Seq[Any](/*45.88*/doku/*45.92*/.kirimdoku.util.MultiLanguage.getLanguage("LANG353","Add"))),format.raw/*45.150*/("""</button>
		</div>
	</div>
""")))})),format.raw/*48.2*/("""
<script src=""""),_display_(Seq[Any](/*49.15*/routes/*49.21*/.Assets.at("javascripts/corporate.js"))),format.raw/*49.59*/("""" type="text/javascript"></script>
""")))})),format.raw/*50.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.Corporate]): play.api.templates.Html = apply(myForm)
    
    def f:((Form[models.Corporate]) => play.api.templates.Html) = (myForm) => apply(myForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:24 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/corporate/admin_new.scala.html
                    HASH: 8e5973989335ceeb9918c18d7c3480d917092ff1
                    MATRIX: 753->1|912->33|939->85|975->87|1006->110|1045->112|1112->145|1124->149|1217->221|1267->236|1282->243|1318->257|1356->260|1370->266|1499->385|1540->387|1635->447|1921->710|2033->787|2290->1021|2402->1098|2623->1297|2735->1374|2960->1577|3150->1731|3163->1735|3247->1796|3380->1893|3393->1897|3474->1955|3533->1983|3584->1998|3599->2004|3659->2042|3726->2078
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|36->7|36->7|36->7|39->10|39->10|39->10|41->12|41->12|41->12|41->12|44->15|46->17|51->22|53->24|58->29|60->31|65->36|67->38|73->44|73->44|73->44|74->45|74->45|74->45|77->48|78->49|78->49|78->49|79->50
                    -- GENERATED --
                */
            