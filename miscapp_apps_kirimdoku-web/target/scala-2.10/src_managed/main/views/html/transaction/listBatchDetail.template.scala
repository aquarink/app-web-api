
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
object listBatchDetail extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template7[Int,String,com.avaje.ebean.Page[models.TransactionInquiry],String,String,java.util.Map[String, String],String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(batchId: Int, filter: String, currentPage: com.avaje.ebean.Page[models.TransactionInquiry], token: String, status: String, listBank: java.util.Map[String, String], page: String):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

def /*7.2*/link/*7.6*/(newPage:Int, newSortBy:String) = {{

    routes.Transaction.showBatchDetail(batchId,newPage, filter, page)
    
}};
Seq[Any](format.raw/*1.180*/("""
"""),format.raw/*6.1*/("""
"""),format.raw/*11.2*/("""


"""),_display_(Seq[Any](/*14.2*/layout("Transactions Inquiry management")/*14.43*/{_display_(Seq[Any](format.raw/*14.44*/("""

<div class="page-header">
    <h1>"""),_display_(Seq[Any](/*17.10*/doku/*17.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG515","Batch Transactions management"))),format.raw/*17.98*/("""</h1>
</div>

"""),_display_(Seq[Any](/*20.2*/partial/*20.9*/.flash_alert())),format.raw/*20.23*/("""

<form id="myForm" action=""""),_display_(Seq[Any](/*22.28*/routes/*22.34*/.BatchUpload.actionRemit(token,batchId))),format.raw/*22.73*/(""""" method="POST" class="form-horizontal form-transaction-filter">
	<div class="row">
		<div class="span12">
			<div id="action" class="well">
	            <div class="input-append">
		            """),_display_(Seq[Any](/*27.16*/if(currentPage.getTotalRowCount > 0)/*27.52*/{_display_(Seq[Any](format.raw/*27.53*/("""
		               	"""),_display_(Seq[Any](/*28.20*/restrict(la(as("supervisor")))/*28.50*/ {_display_(Seq[Any](format.raw/*28.52*/("""
		               		"""),_display_(Seq[Any](/*29.21*/if(status == "I")/*29.38*/{_display_(Seq[Any](format.raw/*29.39*/("""
								<button type="submit" class="btn" style="border-radius:0px;"><i class="icon-plus"></i> """),_display_(Seq[Any](/*30.97*/doku/*30.101*/.kirimdoku.util.MultiLanguage.getLanguage("LANG510","Execute Remit"))),format.raw/*30.169*/("""</button>
							""")))})),format.raw/*31.9*/("""
						""")))})),format.raw/*32.8*/("""
					""")))})),format.raw/*33.7*/("""
					<a class="btn" href=""""),_display_(Seq[Any](/*34.28*/routes/*34.34*/.Transaction.listBatchInquiry())),format.raw/*34.65*/("""">"""),_display_(Seq[Any](/*34.68*/doku/*34.72*/.kirimdoku.util.MultiLanguage.getLanguage("LANG131","Back"))),format.raw/*34.131*/("""</a>
				</div>
			</div>
	    </div>
	</div>
	
	<div class="row">
	    <div class="span12">
			"""),_display_(Seq[Any](/*42.5*/if(currentPage.getTotalRowCount == 0)/*42.42*/{_display_(Seq[Any](format.raw/*42.43*/("""
			<div class="well">
				<em>"""),_display_(Seq[Any](/*44.10*/doku/*44.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG271","There are no transaction data to be displayed"))),format.raw/*44.114*/("""</em>
			</div>
			""")))}/*46.6*/else/*46.11*/{_display_(Seq[Any](format.raw/*46.12*/("""
			<div class="span12" style="overflow: scroll; margin-left: 0px;">
			<table class="table transaction table-bordered table-striped table-condensed" style="text-align: center;">
				<thead>
				<tr>
					"""),_display_(Seq[Any](/*51.7*/restrict(la(as("supervisor")))/*51.37*/ {_display_(Seq[Any](format.raw/*51.39*/("""
						<th style="text-align:center;white-space:nowrap;">
							"""),_display_(Seq[Any](/*53.9*/if(status == "I")/*53.26*/{_display_(Seq[Any](format.raw/*53.27*/("""
								<input type="checkbox" id="checkAll">
							""")))})),format.raw/*55.9*/("""
						</th>
					""")))})),format.raw/*57.7*/("""
					"""),_display_(Seq[Any](/*58.7*/restrict(la(as("operator")))/*58.35*/ {_display_(Seq[Any](format.raw/*58.37*/("""
						<th style="text-align:center;white-space:nowrap;">
							&nbsp;
						</th>
					""")))})),format.raw/*62.7*/("""
					<th style="text-align:center;white-space:nowrap;">Sender First Name</th>
					<th style="text-align:center;white-space:nowrap;">Sender Last Name</th>
					<th style="text-align:center;white-space:nowrap;">Beneficiary First Name</th>
					<th style="text-align:center;white-space:nowrap;">Beneficiary Last Name</th>
					<th style="text-align:center;white-space:nowrap;">Channel</th>
					<th style="text-align:center;white-space:nowrap;">Bank Name</th>
					<th style="text-align:center;white-space:nowrap;">Bank Account Number</th>
					<th style="text-align:center;white-space:nowrap;">Sending Amount</th>
					<th style="text-align:center;white-space:nowrap;">Receiving Amount</th>
					"""),_display_(Seq[Any](/*72.7*/if(page == "i")/*72.22*/{_display_(Seq[Any](format.raw/*72.23*/("""
						<th style="text-align:center;white-space:nowrap;">RC Inquiry</th>
						<th style="text-align:center;white-space:nowrap;">Response Message Inquiry</th>
					""")))})),format.raw/*75.7*/("""
					"""),_display_(Seq[Any](/*76.7*/if(page == "r")/*76.22*/{_display_(Seq[Any](format.raw/*76.23*/("""
						<th style="text-align:center;white-space:nowrap;">RC Remit</th>
						<th style="text-align:center;white-space:nowrap;">Response Message Remit</th>
					""")))})),format.raw/*79.7*/("""
				</tr>
				</thead>
				<tbody>
				"""),_display_(Seq[Any](/*83.6*/for(inquiry <- currentPage.getList) yield /*83.41*/ {_display_(Seq[Any](format.raw/*83.43*/("""
				<tr>
					"""),_display_(Seq[Any](/*85.7*/restrict(la(as("supervisor")))/*85.37*/ {_display_(Seq[Any](format.raw/*85.39*/("""
						<td>
							"""),_display_(Seq[Any](/*87.9*/if(inquiry.status == 'S' && inquiry.state == 'D' && status == "I")/*87.75*/{_display_(Seq[Any](format.raw/*87.76*/("""
								<input type="checkbox" class="chk" name="inq"""),_display_(Seq[Any](/*88.54*/inquiry/*88.61*/.id)),format.raw/*88.64*/("""" value=""""),_display_(Seq[Any](/*88.74*/inquiry/*88.81*/.id)),format.raw/*88.84*/("""">
							""")))}/*89.9*/else/*89.13*/{_display_(Seq[Any](format.raw/*89.14*/("""
								"""),_display_(Seq[Any](/*90.10*/if(inquiry.status == 'S' && inquiry.state == 'R')/*90.59*/{_display_(Seq[Any](format.raw/*90.60*/("""
									<input type="checkbox" disabled="disabled" checked="checked">
								""")))}/*92.10*/else/*92.14*/{_display_(Seq[Any](format.raw/*92.15*/("""
									"""),_display_(Seq[Any](/*93.11*/if(inquiry.status == 'F')/*93.36*/{_display_(Seq[Any](format.raw/*93.37*/("""
										&nbsp;
									""")))})),format.raw/*95.11*/("""
								""")))})),format.raw/*96.10*/("""
							""")))})),format.raw/*97.9*/("""
						</td>
					""")))})),format.raw/*99.7*/("""
					"""),_display_(Seq[Any](/*100.7*/restrict(la(as("operator")))/*100.35*/ {_display_(Seq[Any](format.raw/*100.37*/("""
						"""),_display_(Seq[Any](/*101.8*/if(inquiry.transaction != null)/*101.39*/{_display_(Seq[Any](format.raw/*101.40*/("""
							<td>
								<input type="checkbox" class="chk" checked="checked" disabled="disabled">
							</td>
						""")))}/*105.9*/else/*105.14*/{_display_(Seq[Any](format.raw/*105.15*/("""
							<td>
								&nbsp;
							</td>
						""")))})),format.raw/*109.8*/("""
					""")))})),format.raw/*110.7*/("""
					<td>"""),_display_(Seq[Any](/*111.11*/inquiry/*111.18*/.senderFirstName)),format.raw/*111.34*/("""</td>
					<td>"""),_display_(Seq[Any](/*112.11*/inquiry/*112.18*/.senderLastName)),format.raw/*112.33*/("""</td>
					<td>"""),_display_(Seq[Any](/*113.11*/inquiry/*113.18*/.beneficiaryFirstName)),format.raw/*113.39*/("""</td>
					<td>"""),_display_(Seq[Any](/*114.11*/inquiry/*114.18*/.beneficiaryLastName)),format.raw/*114.38*/("""</td>
					<td>
					"""),_display_(Seq[Any](/*116.7*/if(inquiry.channel != null)/*116.34*/{_display_(Seq[Any](format.raw/*116.35*/("""
						"""),_display_(Seq[Any](/*117.8*/inquiry/*117.15*/.channel.code)),format.raw/*117.28*/("""
					""")))})),format.raw/*118.7*/("""
					</td>
					<td>"""),_display_(Seq[Any](/*120.11*/listBank/*120.19*/.get(inquiry.accountBankId))),format.raw/*120.46*/("""</td>
					<td>"""),_display_(Seq[Any](/*121.11*/inquiry/*121.18*/.accountId)),format.raw/*121.28*/("""</td>
					<td>"""),_display_(Seq[Any](/*122.11*/inquiry/*122.18*/.sendingAmount)),format.raw/*122.32*/("""</td>
					<td>"""),_display_(Seq[Any](/*123.11*/inquiry/*123.18*/.beneficiaryAmount)),format.raw/*123.36*/("""</td>
					"""),_display_(Seq[Any](/*124.7*/if(page == "i")/*124.22*/{_display_(Seq[Any](format.raw/*124.23*/("""
						<td>"""),_display_(Seq[Any](/*125.12*/inquiry/*125.19*/.inquiryResponseCode)),format.raw/*125.39*/("""</td>
						<td>"""),_display_(Seq[Any](/*126.12*/inquiry/*126.19*/.inquiryResponseMessage)),format.raw/*126.42*/("""</td>
					""")))})),format.raw/*127.7*/("""
					"""),_display_(Seq[Any](/*128.7*/if(page == "r")/*128.22*/{_display_(Seq[Any](format.raw/*128.23*/("""
						"""),_display_(Seq[Any](/*129.8*/if(inquiry.transaction != null)/*129.39*/{_display_(Seq[Any](format.raw/*129.40*/("""
							<td>"""),_display_(Seq[Any](/*130.13*/inquiry/*130.20*/.transaction.remitResponseCode)),format.raw/*130.50*/("""</td>
							<td>"""),_display_(Seq[Any](/*131.13*/inquiry/*131.20*/.transaction.remitResponseMessage)),format.raw/*131.53*/("""</td>
						""")))}/*132.9*/else/*132.14*/{_display_(Seq[Any](format.raw/*132.15*/("""
							<td></td>
							<td></td>
						""")))})),format.raw/*135.8*/("""
					""")))})),format.raw/*136.7*/("""
				</tr>
				""")))})),format.raw/*138.6*/("""
				</tbody>
			</table>
			</div>
			""")))})),format.raw/*142.5*/("""
		</div>
	</div>
</form>
<script type="text/javascript">
<!--
	$('#checkAll').change(function() """),format.raw/*148.35*/("""{"""),format.raw/*148.36*/("""
		if(this.checked) """),format.raw/*149.20*/("""{"""),format.raw/*149.21*/("""
			$('.chk').prop('checked', true);
		"""),format.raw/*151.3*/("""}"""),format.raw/*151.4*/(""" else """),format.raw/*151.10*/("""{"""),format.raw/*151.11*/("""
			$('.chk').prop('checked', false);
		"""),format.raw/*153.3*/("""}"""),format.raw/*153.4*/("""
	"""),format.raw/*154.2*/("""}"""),format.raw/*154.3*/(""");
	
	$('#myForm').on('submit', function(e)"""),format.raw/*156.39*/("""{"""),format.raw/*156.40*/("""
		var statusSubmit = false;
		$(".chk").each(function() """),format.raw/*158.29*/("""{"""),format.raw/*158.30*/("""
			if(this.checked) """),format.raw/*159.21*/("""{"""),format.raw/*159.22*/("""
				statusSubmit = true;
				return false;
			"""),format.raw/*162.4*/("""}"""),format.raw/*162.5*/("""
		"""),format.raw/*163.3*/("""}"""),format.raw/*163.4*/(""");
		if (!statusSubmit) """),format.raw/*164.22*/("""{"""),format.raw/*164.23*/("""
			alert('Please check one or more inquiry row');
			return false;
		"""),format.raw/*167.3*/("""}"""),format.raw/*167.4*/(""" else """),format.raw/*167.10*/("""{"""),format.raw/*167.11*/("""
			return true;
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
	"""),format.raw/*170.2*/("""}"""),format.raw/*170.3*/(""");
	
//-->
</script>
""")))})),format.raw/*174.2*/("""
"""))}
    }
    
    def render(batchId:Int,filter:String,currentPage:com.avaje.ebean.Page[models.TransactionInquiry],token:String,status:String,listBank:java.util.Map[String, String],page:String): play.api.templates.Html = apply(batchId,filter,currentPage,token,status,listBank,page)
    
    def f:((Int,String,com.avaje.ebean.Page[models.TransactionInquiry],String,String,java.util.Map[String, String],String) => play.api.templates.Html) = (batchId,filter,currentPage,token,status,listBank,page) => apply(batchId,filter,currentPage,token,status,listBank,page)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:33 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/transaction/listBatchDetail.scala.html
                    HASH: b160131208809bbd29ac5b492047b59492230ba5
                    MATRIX: 848->1|1241->338|1252->342|1397->179|1424->336|1452->456|1491->460|1541->501|1580->502|1653->539|1666->543|1772->627|1822->642|1837->649|1873->663|1938->692|1953->698|2014->737|2247->934|2292->970|2331->971|2387->991|2426->1021|2466->1023|2523->1044|2549->1061|2588->1062|2721->1159|2735->1163|2826->1231|2875->1249|2914->1257|2952->1264|3016->1292|3031->1298|3084->1329|3123->1332|3136->1336|3218->1395|3350->1492|3396->1529|3435->1530|3503->1562|3516->1566|3639->1666|3677->1687|3690->1692|3729->1693|3970->1899|4009->1929|4049->1931|4150->1997|4176->2014|4215->2015|4301->2070|4351->2089|4393->2096|4430->2124|4470->2126|4591->2216|5323->2913|5347->2928|5386->2929|5582->3094|5624->3101|5648->3116|5687->3117|5879->3278|5955->3319|6006->3354|6046->3356|6097->3372|6136->3402|6176->3404|6231->3424|6306->3490|6345->3491|6435->3545|6451->3552|6476->3555|6522->3565|6538->3572|6563->3575|6592->3586|6605->3590|6644->3591|6690->3601|6748->3650|6787->3651|6887->3732|6900->3736|6939->3737|6986->3748|7020->3773|7059->3774|7119->3802|7161->3812|7201->3821|7251->3840|7294->3847|7332->3875|7373->3877|7417->3885|7458->3916|7498->3917|7632->4033|7646->4038|7686->4039|7766->4087|7805->4094|7853->4105|7870->4112|7909->4128|7962->4144|7979->4151|8017->4166|8070->4182|8087->4189|8131->4210|8184->4226|8201->4233|8244->4253|8302->4275|8339->4302|8379->4303|8423->4311|8440->4318|8476->4331|8515->4338|8574->4360|8592->4368|8642->4395|8695->4411|8712->4418|8745->4428|8798->4444|8815->4451|8852->4465|8905->4481|8922->4488|8963->4506|9011->4518|9036->4533|9076->4534|9125->4546|9142->4553|9185->4573|9239->4590|9256->4597|9302->4620|9346->4632|9389->4639|9414->4654|9454->4655|9498->4663|9539->4694|9579->4695|9629->4708|9646->4715|9699->4745|9754->4763|9771->4770|9827->4803|9859->4817|9873->4822|9913->4823|9987->4865|10026->4872|10074->4888|10146->4928|10272->5025|10302->5026|10351->5046|10381->5047|10448->5086|10477->5087|10512->5093|10542->5094|10610->5134|10639->5135|10669->5137|10698->5138|10770->5181|10800->5182|10886->5239|10916->5240|10966->5261|10996->5262|11071->5309|11100->5310|11131->5313|11160->5314|11213->5338|11243->5339|11341->5409|11370->5410|11405->5416|11435->5417|11482->5436|11511->5437|11541->5439|11570->5440|11624->5462
                    LINES: 26->1|35->7|35->7|40->1|41->6|42->11|45->14|45->14|45->14|48->17|48->17|48->17|51->20|51->20|51->20|53->22|53->22|53->22|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|63->32|64->33|65->34|65->34|65->34|65->34|65->34|65->34|73->42|73->42|73->42|75->44|75->44|75->44|77->46|77->46|77->46|82->51|82->51|82->51|84->53|84->53|84->53|86->55|88->57|89->58|89->58|89->58|93->62|103->72|103->72|103->72|106->75|107->76|107->76|107->76|110->79|114->83|114->83|114->83|116->85|116->85|116->85|118->87|118->87|118->87|119->88|119->88|119->88|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|123->92|123->92|123->92|124->93|124->93|124->93|126->95|127->96|128->97|130->99|131->100|131->100|131->100|132->101|132->101|132->101|136->105|136->105|136->105|140->109|141->110|142->111|142->111|142->111|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|147->116|147->116|147->116|148->117|148->117|148->117|149->118|151->120|151->120|151->120|152->121|152->121|152->121|153->122|153->122|153->122|154->123|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|157->126|157->126|157->126|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|163->132|163->132|163->132|166->135|167->136|169->138|173->142|179->148|179->148|180->149|180->149|182->151|182->151|182->151|182->151|184->153|184->153|185->154|185->154|187->156|187->156|189->158|189->158|190->159|190->159|193->162|193->162|194->163|194->163|195->164|195->164|198->167|198->167|198->167|198->167|200->169|200->169|201->170|201->170|205->174
                    -- GENERATED --
                */
            