
package views.html.dashboard

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
object statistics extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,models.User,List[models.Transaction],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, user:models.User, latestTransactions: List[models.Transaction]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.79*/("""

<div class="box box-bordered">
	<div class="page-subheader">
		<h4>"""),_display_(Seq[Any](/*5.8*/doku/*5.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG025","Latest five transactions"))),format.raw/*5.91*/("""</h4>
	</div>
	<div>
		"""),_display_(Seq[Any](/*8.4*/if(latestTransactions.isEmpty)/*8.34*/ {_display_(Seq[Any](format.raw/*8.36*/("""
		<div class="">There are currently no transactions available to display</div>
		""")))}/*10.5*/else/*10.10*/{_display_(Seq[Any](format.raw/*10.11*/("""
		<table class="top-transaction table ">
			<thead>
				<tr>

					<th>"""),_display_(Seq[Any](/*15.11*/doku/*15.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*15.84*/("""</th>
					<th>"""),_display_(Seq[Any](/*16.11*/doku/*16.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"))),format.raw/*16.85*/("""</th>
					<th>"""),_display_(Seq[Any](/*17.11*/doku/*17.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG028","Send amount"))),format.raw/*17.81*/("""</th>
					<th>"""),_display_(Seq[Any](/*18.11*/doku/*18.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG029","Date"))),format.raw/*18.74*/("""</th>
					<th>"""),_display_(Seq[Any](/*19.11*/doku/*19.15*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*19.76*/("""</th>

				</tr>
			</thead>
			<tbody>
			"""),_display_(Seq[Any](/*24.5*/for(t <- latestTransactions) yield /*24.33*/{_display_(Seq[Any](format.raw/*24.34*/("""
				<tr>
					<td>"""),_display_(Seq[Any](/*26.11*/t/*26.12*/.getIdToken)),format.raw/*26.23*/("""</td>
					<td>"""),_display_(Seq[Any](/*27.11*/t/*27.12*/.beneficiaryCountry.name)),format.raw/*27.36*/("""</td>
					<td class="right">"""),_display_(Seq[Any](/*28.25*/t/*28.26*/.senderAmountFormat)),format.raw/*28.45*/("""</td>
					<td nowrap="nowrap">"""),_display_(Seq[Any](/*29.27*/models/*29.33*/.Transaction.formatDate(t.cashInTime.getTime, true))),format.raw/*29.84*/("""</td>
					<td>"""),_display_(Seq[Any](/*30.11*/t/*30.12*/.statusText)),format.raw/*30.23*/("""</td>
				</tr>
			""")))})),format.raw/*32.5*/("""
			</tbody>
		</table>
		""")))})),format.raw/*35.4*/("""
	</div>
</div>
<div class="box box-bordered">
	<div class="page-subheader">
		<h4>"""),_display_(Seq[Any](/*40.8*/doku/*40.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG032","Credit Limit"))),format.raw/*40.79*/("""</h4>
	</div>
	<div class="row">
		<div class="span12">
			<dl class="dl-horizontal">
				<dt>"""),_display_(Seq[Any](/*45.10*/doku/*45.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG033","Credit score"))),format.raw/*45.81*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*46.10*/models/*46.16*/.Currency.format(user.creditLimit, user.corporate.currency.code))),format.raw/*46.80*/("""</dd>
				<dt>"""),_display_(Seq[Any](/*47.10*/doku/*47.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG034","Alert limit"))),format.raw/*47.80*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*48.10*/models/*48.16*/.Currency.format(user.creditAlertLimit, user.corporate.currency.code))),format.raw/*48.85*/("""</dd>
				<dt>"""),_display_(Seq[Any](/*49.10*/doku/*49.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG035","Credit left"))),format.raw/*49.80*/("""</dt>
				<dd>"""),_display_(Seq[Any](/*50.10*/models/*50.16*/.Currency.format(user.creditLastBalance, user.corporate.currency.code))),format.raw/*50.86*/("""</dd>
			</dl>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
		"""),_display_(Seq[Any](/*56.4*/if(err != null)/*56.19*/ {_display_(Seq[Any](format.raw/*56.21*/("""
		  <div class="alert alert-block alert-error">
			<div class="row-fluid">
				<div class="span1">
					<img src=""""),_display_(Seq[Any](/*60.17*/routes/*60.23*/.Assets.at("images/alert-warning.png"))),format.raw/*60.61*/(""""/>
				</div>
				<div class="span11">
					<h4>
					"""),_display_(Seq[Any](/*64.7*/err)),format.raw/*64.10*/("""
					</h4>
				</div>
			</div>
		  </div>
		""")))})),format.raw/*69.4*/("""
		</div>
	</div>
</div>
"""))}
    }
    
    def render(err:String,user:models.User,latestTransactions:List[models.Transaction]): play.api.templates.Html = apply(err,user,latestTransactions)
    
    def f:((String,models.User,List[models.Transaction]) => play.api.templates.Html) = (err,user,latestTransactions) => apply(err,user,latestTransactions)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/dashboard/statistics.scala.html
                    HASH: 27e165ea60cb888af26220083395a8d7ef576dff
                    MATRIX: 775->1|929->78|1033->148|1045->152|1145->231|1203->255|1241->285|1280->287|1381->371|1394->376|1433->377|1542->450|1555->454|1646->523|1698->539|1711->543|1803->613|1855->629|1868->633|1956->699|2008->715|2021->719|2102->778|2154->794|2167->798|2250->859|2329->903|2373->931|2412->932|2468->952|2478->953|2511->964|2563->980|2573->981|2619->1005|2685->1035|2695->1036|2736->1055|2804->1087|2819->1093|2892->1144|2944->1160|2954->1161|2987->1172|3038->1192|3096->1219|3215->1303|3228->1307|3317->1374|3448->1469|3461->1473|3550->1540|3601->1555|3616->1561|3702->1625|3753->1640|3766->1644|3854->1710|3905->1725|3920->1731|4011->1800|4062->1815|4075->1819|4163->1885|4214->1900|4229->1906|4321->1976|4439->2059|4463->2074|4503->2076|4655->2192|4670->2198|4730->2236|4821->2292|4846->2295|4924->2342
                    LINES: 26->1|29->1|33->5|33->5|33->5|36->8|36->8|36->8|38->10|38->10|38->10|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|52->24|52->24|52->24|54->26|54->26|54->26|55->27|55->27|55->27|56->28|56->28|56->28|57->29|57->29|57->29|58->30|58->30|58->30|60->32|63->35|68->40|68->40|68->40|73->45|73->45|73->45|74->46|74->46|74->46|75->47|75->47|75->47|76->48|76->48|76->48|77->49|77->49|77->49|78->50|78->50|78->50|84->56|84->56|84->56|88->60|88->60|88->60|92->64|92->64|97->69
                    -- GENERATED --
                */
            