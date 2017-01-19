
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
object admin_refund extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[controllers.Transaction.FilterForm],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(filterForm: Form[controllers.Transaction.FilterForm]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.56*/("""
"""),format.raw/*4.1*/("""

"""),_display_(Seq[Any](/*6.2*/layout("Transaction Operation")/*6.33*/{_display_(Seq[Any](format.raw/*6.34*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*9.10*/doku/*9.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG388","Authorize transaction for Full Refund"))),format.raw/*9.106*/("""</h1>
</div>

"""),_display_(Seq[Any](/*12.2*/partial/*12.9*/.flash_alert())),format.raw/*12.23*/("""

<div class="row">
	<div class="well span12">
		"""),_display_(Seq[Any](/*16.4*/helper/*16.10*/.form(action = routes.Transaction.admin_refund, 'class -> "form-horizontal form-transaction-filter", Symbol("data-remote") -> "true")/*16.143*/ {_display_(Seq[Any](format.raw/*16.145*/("""
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

<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*39.46*/routes/*39.52*/.Assets.at("stylesheets/transaction.css"))),format.raw/*39.93*/("""">
<script type="text/javascript" src='"""),_display_(Seq[Any](/*40.38*/routes/*40.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*40.84*/("""'></script>
""")))})),format.raw/*41.2*/("""
"""))}
    }
    
    def render(filterForm:Form[controllers.Transaction.FilterForm]): play.api.templates.Html = apply(filterForm)
    
    def f:((Form[controllers.Transaction.FilterForm]) => play.api.templates.Html) = (filterForm) => apply(filterForm)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:31 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/admin_refund.scala.html
                    HASH: 30930c6c002b7b1b9268fd6654fc7ac59d09e32e
                    MATRIX: 776->1|957->55|984->107|1021->110|1060->141|1098->142|1170->179|1182->183|1296->275|1346->290|1361->297|1397->311|1482->361|1497->367|1640->500|1681->502|1721->507|1736->513|2040->809|2107->838|2213->908|2237->910|2282->919|2308->923|2354->933|2381->938|2420->941|2458->957|2559->1021|2573->1025|2656->1085|2711->1109|2746->1113|2957->1288|2972->1294|3035->1335|3111->1375|3126->1381|3188->1421|3232->1434
                    LINES: 26->1|32->1|33->4|35->6|35->6|35->6|38->9|38->9|38->9|41->12|41->12|41->12|45->16|45->16|45->16|45->16|46->17|46->17|52->23|52->23|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|54->25|56->27|57->28|68->39|68->39|68->39|69->40|69->40|69->40|70->41
                    -- GENERATED --
                */
            