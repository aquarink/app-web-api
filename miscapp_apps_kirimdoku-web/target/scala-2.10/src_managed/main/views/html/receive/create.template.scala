
package views.html.receive

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
object create extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[controllers.Receive.FilterForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Receive.FilterForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import partial._


Seq[Any](format.raw/*1.52*/("""
"""),format.raw/*5.1*/("""
"""),_display_(Seq[Any](/*6.2*/layout("Receive money")/*6.25*/ {_display_(Seq[Any](format.raw/*6.27*/("""
<div class="page-header page-header-step">
	<div class="row">
	  <div class="span4">
		<h1>"""),_display_(Seq[Any](/*10.8*/doku/*10.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG182","Receive Money"))),format.raw/*10.80*/("""</h1>
	  </div>
	</div>
</div>

"""),_display_(Seq[Any](/*15.2*/flash_alert())),format.raw/*15.15*/("""

<div class="row">
	<div class="well span12">
		"""),_display_(Seq[Any](/*19.4*/helper/*19.10*/.form(action = routes.Receive.create, 'class -> "form-horizontal form-transaction-filter", Symbol("data-remote") -> "true")/*19.133*/ {_display_(Seq[Any](format.raw/*19.135*/("""
			"""),_display_(Seq[Any](/*20.5*/helper/*20.11*/.input(
			filterForm("idToken"),
			'_class -> "search-query",
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"),
			'placeHolder -> "Filter by transaction ID...",
			'class -> "required"
			)/*26.5*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*26.34*/("""
            <div class="input-append">
				<input type="search" id=""""),_display_(Seq[Any](/*28.31*/id)),format.raw/*28.33*/("""" name=""""),_display_(Seq[Any](/*28.42*/name)),format.raw/*28.46*/("""" value=""""),_display_(Seq[Any](/*28.56*/value)),format.raw/*28.61*/("""" """),_display_(Seq[Any](/*28.64*/toHtmlArgs(args))),format.raw/*28.80*/("""><button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*28.144*/doku/*28.148*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*28.208*/("""</button>
			</div>
			""")))})),format.raw/*30.5*/("""
		""")))})),format.raw/*31.4*/("""
    </div>
</div>

<div class="row">
	<div class="span12">
		<div id="transaction-detail-container">
		</div>
    </div>
</div>

<div id="verify-modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Send dialog" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">Verification failure</h3>
	</div>
	<div class="modal-body">
		<p>One fine body…</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
""")))}/*54.2*/ {_display_(Seq[Any](format.raw/*54.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*55.38*/routes/*55.44*/.Assets.at("javascripts/receive.js"))),format.raw/*55.80*/("""'></script>
""")))})),format.raw/*56.2*/("""
"""))}
    }
    
    def render(filterForm:Form[controllers.Receive.FilterForm]): play.api.templates.Html = apply(filterForm)
    
    def f:((Form[controllers.Receive.FilterForm]) => play.api.templates.Html) = (filterForm) => apply(filterForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/receive/create.scala.html
                    HASH: a217cd9ae06291c69c1a627fc8159b6722384d2a
                    MATRIX: 762->1|957->51|984->121|1020->123|1051->146|1090->148|1218->241|1231->245|1321->313|1389->346|1424->359|1509->409|1524->415|1657->538|1698->540|1738->545|1753->551|1992->782|2059->811|2165->881|2189->883|2234->892|2260->896|2306->906|2333->911|2372->914|2410->930|2511->994|2525->998|2608->1058|2663->1082|2698->1086|3325->1695|3364->1697|3438->1735|3453->1741|3511->1777|3555->1790
                    LINES: 26->1|34->1|35->5|36->6|36->6|36->6|40->10|40->10|40->10|45->15|45->15|49->19|49->19|49->19|49->19|50->20|50->20|56->26|56->26|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|58->28|60->30|61->31|84->54|84->54|85->55|85->55|85->55|86->56
                    -- GENERATED --
                */
            