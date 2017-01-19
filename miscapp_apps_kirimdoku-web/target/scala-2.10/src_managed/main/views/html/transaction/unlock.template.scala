
package views.html.transaction

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
object unlock extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[controllers.Transaction.FilterForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Transaction.FilterForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.56*/("""
"""),format.raw/*4.1*/("""

"""),_display_(Seq[Any](/*6.2*/layout("Transaction Operation")/*6.33*/{_display_(Seq[Any](format.raw/*6.34*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*9.10*/doku/*9.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG014","Transaction unlock"))),format.raw/*9.87*/("""</h1>
</div>

"""),_display_(Seq[Any](/*12.2*/partial/*12.9*/.flash_alert())),format.raw/*12.23*/("""

<div class="row">
	<div class="well span12">
		"""),_display_(Seq[Any](/*16.4*/helper/*16.10*/.form(action = routes.Transaction.unlock, 'class -> "form-horizontal form-transaction-filter", Symbol("data-remote") -> "true")/*16.137*/ {_display_(Seq[Any](format.raw/*16.139*/("""
			"""),_display_(Seq[Any](/*17.5*/helper/*17.11*/.input(
			filterForm("transactionCode"),
			'_class -> "search-query",
			'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"),
			'placeHolder -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG263","Filter by transaction ID..."),
			'class -> "required"
			)/*23.5*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*23.34*/("""
            <div class="input-append">
				<input type="search" id=""""),_display_(Seq[Any](/*25.31*/id)),format.raw/*25.33*/("""" name=""""),_display_(Seq[Any](/*25.42*/name)),format.raw/*25.46*/("""" value=""""),_display_(Seq[Any](/*25.56*/value)),format.raw/*25.61*/("""" """),_display_(Seq[Any](/*25.64*/toHtmlArgs(args))),format.raw/*25.80*/("""><button type="submit" class="btn"><i class="icon-search"></i> """),_display_(Seq[Any](/*25.144*/doku/*25.148*/.kirimdoku.util.MultiLanguage.getLanguage("LANG096","Check"))),format.raw/*25.208*/("""</button>
			</div>
			""")))})),format.raw/*27.5*/("""
		""")))})),format.raw/*28.4*/("""
    </div>
</div>

<div class="row">
	<div class="span12">
		<div id="transaction-detail-container">
		</div>
    </div>
</div>

""")))}/*39.2*/ {_display_(Seq[Any](format.raw/*39.4*/("""
<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*40.46*/routes/*40.52*/.Assets.at("stylesheets/transaction.css"))),format.raw/*40.93*/("""">
<script type="text/javascript" src='"""),_display_(Seq[Any](/*41.38*/routes/*41.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*41.84*/("""'></script>
""")))})),format.raw/*42.2*/("""
"""))}
    }
    
    def render(filterForm:Form[controllers.Transaction.FilterForm]): play.api.templates.Html = apply(filterForm)
    
    def f:((Form[controllers.Transaction.FilterForm]) => play.api.templates.Html) = (filterForm) => apply(filterForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:34 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/unlock.scala.html
                    HASH: d77f08c9653aaf827ec5bd94100c27f201026ef0
                    MATRIX: 770->1|951->55|978->107|1015->110|1054->141|1092->142|1164->179|1176->183|1270->256|1320->271|1335->278|1371->292|1456->342|1471->348|1608->475|1649->477|1689->482|1704->488|2008->784|2075->813|2181->883|2205->885|2250->894|2276->898|2322->908|2349->913|2388->916|2426->932|2527->996|2541->1000|2624->1060|2679->1084|2714->1088|2863->1219|2902->1221|2984->1267|2999->1273|3062->1314|3138->1354|3153->1360|3215->1400|3259->1413
                    LINES: 26->1|32->1|33->4|35->6|35->6|35->6|38->9|38->9|38->9|41->12|41->12|41->12|45->16|45->16|45->16|45->16|46->17|46->17|52->23|52->23|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|56->27|57->28|68->39|68->39|69->40|69->40|69->40|70->41|70->41|70->41|71->42
                    -- GENERATED --
                */
            