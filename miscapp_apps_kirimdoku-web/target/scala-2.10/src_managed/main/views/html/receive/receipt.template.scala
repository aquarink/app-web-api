
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
object receipt extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,models.Transaction,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(err: String, transaction: models.Transaction):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._


Seq[Any](format.raw/*1.48*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Receive money receipt")/*5.33*/{_display_(Seq[Any](format.raw/*5.34*/("""

<div id="receive-receipt" class="body-print">
<div class="page-header">
    <h2 class="center"><img src=""""),_display_(Seq[Any](/*9.35*/routes/*9.41*/.Corporate.logo(session.get("corporateCode"), "small"))),format.raw/*9.95*/("""" style="float:left;height:50px;"/>"""),_display_(Seq[Any](/*9.131*/doku/*9.135*/.kirimdoku.util.MultiLanguage.getLanguage("LANG456","Receive money receipt"))),format.raw/*9.211*/("""<img src="/assets/images/doku_small.png" style="float:right;height:50px;"/></h2>
</div>

"""),_display_(Seq[Any](/*12.2*/partial/*12.9*/.flash_alert())),format.raw/*12.23*/("""

<div class="container-refund-inner">
"""),_display_(Seq[Any](/*15.2*/if(err)/*15.9*/ {_display_(Seq[Any](format.raw/*15.11*/("""
	<div class="row">
		<div class="span12">
			<div class="alert alert-error">"""),_display_(Seq[Any](/*18.36*/err)),format.raw/*18.39*/("""</div>
		</div>
	</div>
""")))}/*21.3*/else/*21.8*/{_display_(Seq[Any](format.raw/*21.9*/("""
	<div>
		<div class="receipt-head">
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*27.12*/doku/*27.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG225","Corporate name"))),format.raw/*27.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*28.12*/transaction/*28.23*/.beneficiaryAgent.corporate.name)),format.raw/*28.55*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*29.12*/doku/*29.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG226","Operator ID"))),format.raw/*29.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*30.12*/transaction/*30.23*/.beneficiaryAgent.idToken)),format.raw/*30.48*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*31.12*/doku/*31.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG227","Operator name"))),format.raw/*31.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*32.12*/transaction/*32.23*/.beneficiaryAgent.fullName)),format.raw/*32.49*/("""</dd>
					</dl>
				</div>
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*37.12*/doku/*37.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG122","Address"))),format.raw/*37.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*38.12*/transaction/*38.23*/.beneficiaryAgent.corporate.address)),format.raw/*38.58*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*39.12*/doku/*39.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone Number"))),format.raw/*39.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*40.12*/transaction/*40.23*/.beneficiaryAgent.corporate.phoneNumber)),format.raw/*40.62*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*41.12*/doku/*41.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG255","Fax"))),format.raw/*41.74*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*42.12*/transaction/*42.23*/.beneficiaryAgent.corporate.faxNumber)),format.raw/*42.60*/("""</dd>
					</dl>
				</div>
			</div>
		</div>
		
		<div class="receipt-body">
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*52.12*/doku/*52.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG228","Transaction type"))),format.raw/*52.87*/("""</dt>
						<dd>Receive money</dd>
						<dt>"""),_display_(Seq[Any](/*54.12*/doku/*54.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"))),format.raw/*54.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*55.12*/transaction/*55.23*/.sender.country.name)),format.raw/*55.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*56.12*/doku/*56.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG041","Receive country"))),format.raw/*56.86*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*57.12*/transaction/*57.23*/.beneficiary.country.name)),format.raw/*57.48*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*58.12*/doku/*58.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service"))),format.raw/*58.78*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*59.12*/transaction/*59.23*/.channel.name)),format.raw/*59.36*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*60.12*/doku/*60.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG230","Date / Time"))),format.raw/*60.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*61.12*/transaction/*61.23*/.createdFormat)),format.raw/*61.37*/("""</dd>
					</dl>
					
				</div>
				<div class="span6">
					<div class="well well-receipt well-print">
						<div class="well-inner">
							<label>"""),_display_(Seq[Any](/*68.16*/doku/*68.20*/.kirimdoku.util.MultiLanguage.getLanguage("LANG026","Transaction ID"))),format.raw/*68.89*/(""":</label>
							<h1>"""),_display_(Seq[Any](/*69.13*/transaction/*69.24*/.idToken)),format.raw/*69.32*/("""</h1>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*78.12*/doku/*78.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG073","Customer ID of receiver"))),format.raw/*78.94*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*79.12*/transaction/*79.23*/.beneficiary.idToken)),format.raw/*79.43*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*80.12*/doku/*80.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG201","Receiver name"))),format.raw/*80.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*81.12*/transaction/*81.23*/.beneficiary.fullName)),format.raw/*81.44*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*82.12*/doku/*82.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*82.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*83.12*/transaction/*83.23*/.beneficiary.phoneNumber)),format.raw/*83.47*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*84.12*/doku/*84.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount"))),format.raw/*84.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*85.12*/transaction/*85.23*/.senderAmountFormat)),format.raw/*85.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*86.12*/doku/*86.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"))),format.raw/*86.84*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*87.12*/transaction/*87.23*/.forexReference.rate)),format.raw/*87.43*/("""</dd>
					</dl>
				</div>
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*92.12*/doku/*92.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG072","Customer ID of sender"))),format.raw/*92.92*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*93.12*/transaction/*93.23*/.sender.idToken)),format.raw/*93.38*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*94.12*/doku/*94.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG199","Sender name"))),format.raw/*94.82*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*95.12*/transaction/*95.23*/.sender.fullName)),format.raw/*95.39*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*96.12*/doku/*96.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG053","Phone number"))),format.raw/*96.83*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*97.12*/transaction/*97.23*/.sender.phoneNumber)),format.raw/*97.42*/("""</dd>
						<dt>"""),_display_(Seq[Any](/*98.12*/doku/*98.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG277","Sender address"))),format.raw/*98.85*/("""</dt>
						<dd>"""),_display_(Seq[Any](/*99.12*/transaction/*99.23*/.sender.address)),format.raw/*99.38*/("""</dd>
					</dl>
				</div>
			</div>
			
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned">
						<dt>"""),_display_(Seq[Any](/*107.12*/doku/*107.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG030","Status"))),format.raw/*107.77*/("""</dt>
						<dd><strong>"""),_display_(Seq[Any](/*108.20*/transaction/*108.31*/.statusText)),format.raw/*108.42*/("""</strong></dd>
						<dt>"""),_display_(Seq[Any](/*109.12*/doku/*109.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG207","Amount to receive"))),format.raw/*109.88*/("""</dt>
						<dd><strong>"""),_display_(Seq[Any](/*110.20*/transaction/*110.31*/.beneficiaryAmountFormat)),format.raw/*110.55*/("""</strong></dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="span6" style="">
					<div class="span2 well" style="width: 70%;" >
						<div style="height:30px"><br>"""),_display_(Seq[Any](/*117.37*/doku/*117.41*/.kirimdoku.util.MultiLanguage.getLanguage("LANG233","Operator's Signature"))),format.raw/*117.116*/("""</div>
					</div>
				</div>
				<div class="span6" style="">
					<div class="span2 well" style="width: 70%;" >
						<div style="height:30px"><br>"""),_display_(Seq[Any](/*122.37*/doku/*122.41*/.kirimdoku.util.MultiLanguage.getLanguage("LANG234","Customer's Signature"))),format.raw/*122.116*/("""</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span6">
					<dl class="dl-horizontal dl-aligned" style="font-size: 9pt;">
						<dt style="overflow: visible;">"""),_display_(Seq[Any](/*129.39*/doku/*129.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG235","1. Please check all transaction information before leaving our branch."))),format.raw/*129.168*/("""</dt>
						<dd></dd>
						<dt style="overflow: visible;">"""),_display_(Seq[Any](/*131.39*/doku/*131.43*/.kirimdoku.util.MultiLanguage.getLanguage("LANG236","2. This is valid transaction receipt, please keep this carefully as your reference."))),format.raw/*131.181*/("""</dt>
						<dd></dd>
					</dl>
				</div>
			</div>
			
			<div class="row">
				<div class="span12">
					<div class="form-actions invisible-print">
						<button class="btn btn-print" type="print" data-target="#receive-receipt"><i class="icon-print"></i> """),_display_(Seq[Any](/*140.109*/doku/*140.113*/.kirimdoku.util.MultiLanguage.getLanguage("LANG079","Print"))),format.raw/*140.173*/("""</button>
						<a href=""""),_display_(Seq[Any](/*141.17*/routes/*141.23*/.Receive.create())),format.raw/*141.40*/("""" class="btn">"""),_display_(Seq[Any](/*141.55*/doku/*141.59*/.kirimdoku.util.MultiLanguage.getLanguage("LANG457","Receive another money"))),format.raw/*141.135*/(""" <i class="icon-arrow-right"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
""")))})),format.raw/*147.2*/("""
</div>
</div>
""")))}/*150.2*/ {_display_(Seq[Any](format.raw/*150.4*/("""
<script type="text/javascript" src='"""),_display_(Seq[Any](/*151.38*/routes/*151.44*/.Assets.at("javascripts/transaction.js"))),format.raw/*151.84*/("""'></script>
""")))})),format.raw/*152.2*/("""
"""))}
    }
    
    def render(err:String,transaction:models.Transaction): play.api.templates.Html = apply(err,transaction)
    
    def f:((String,models.Transaction) => play.api.templates.Html) = (err,transaction) => apply(err,transaction)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:28 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/receive/receipt.scala.html
                    HASH: 82fa49d548f98be901fdb864086145d0463b64de
                    MATRIX: 752->1|925->47|952->99|988->101|1027->132|1065->133|1208->241|1222->247|1297->301|1369->337|1382->341|1480->417|1605->507|1620->514|1656->528|1731->568|1746->575|1786->577|1900->655|1925->658|1968->684|1980->689|2018->690|2190->826|2203->830|2294->899|2347->916|2367->927|2421->959|2474->976|2487->980|2575->1046|2628->1063|2648->1074|2695->1099|2748->1116|2761->1120|2851->1188|2904->1205|2924->1216|2972->1242|3114->1348|3127->1352|3211->1414|3264->1431|3284->1442|3341->1477|3394->1494|3407->1498|3496->1565|3549->1582|3569->1593|3630->1632|3683->1649|3696->1653|3776->1711|3829->1728|3849->1739|3908->1776|4122->1954|4135->1958|4228->2029|4310->2075|4323->2079|4415->2149|4468->2166|4488->2177|4530->2197|4583->2214|4596->2218|4688->2288|4741->2305|4761->2316|4808->2341|4861->2358|4874->2362|4958->2424|5011->2441|5031->2452|5066->2465|5119->2482|5132->2486|5220->2552|5273->2569|5293->2580|5329->2594|5517->2746|5530->2750|5621->2819|5679->2841|5699->2852|5729->2860|5920->3015|5933->3019|6033->3097|6086->3114|6106->3125|6148->3145|6201->3162|6214->3166|6304->3234|6357->3251|6377->3262|6420->3283|6473->3300|6486->3304|6575->3371|6628->3388|6648->3399|6694->3423|6747->3440|6760->3444|6851->3513|6904->3530|6924->3541|6965->3560|7018->3577|7031->3581|7121->3649|7174->3666|7194->3677|7236->3697|7378->3803|7391->3807|7489->3883|7542->3900|7562->3911|7599->3926|7652->3943|7665->3947|7753->4013|7806->4030|7826->4041|7864->4057|7917->4074|7930->4078|8019->4145|8072->4162|8092->4173|8133->4192|8186->4209|8199->4213|8290->4282|8343->4299|8363->4310|8400->4325|8578->4466|8592->4470|8676->4531|8738->4556|8759->4567|8793->4578|8856->4604|8870->4608|8965->4680|9027->4705|9048->4716|9095->4740|9320->4928|9334->4932|9433->5007|9620->5157|9634->5161|9733->5236|9960->5426|9974->5430|10123->5555|10220->5615|10234->5619|10396->5757|10694->6017|10709->6021|10793->6081|10856->6107|10872->6113|10912->6130|10964->6145|10978->6149|11078->6225|11199->6314|11234->6330|11274->6332|11349->6370|11365->6376|11428->6416|11473->6429
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|38->9|38->9|38->9|38->9|38->9|38->9|41->12|41->12|41->12|44->15|44->15|44->15|47->18|47->18|50->21|50->21|50->21|56->27|56->27|56->27|57->28|57->28|57->28|58->29|58->29|58->29|59->30|59->30|59->30|60->31|60->31|60->31|61->32|61->32|61->32|66->37|66->37|66->37|67->38|67->38|67->38|68->39|68->39|68->39|69->40|69->40|69->40|70->41|70->41|70->41|71->42|71->42|71->42|81->52|81->52|81->52|83->54|83->54|83->54|84->55|84->55|84->55|85->56|85->56|85->56|86->57|86->57|86->57|87->58|87->58|87->58|88->59|88->59|88->59|89->60|89->60|89->60|90->61|90->61|90->61|97->68|97->68|97->68|98->69|98->69|98->69|107->78|107->78|107->78|108->79|108->79|108->79|109->80|109->80|109->80|110->81|110->81|110->81|111->82|111->82|111->82|112->83|112->83|112->83|113->84|113->84|113->84|114->85|114->85|114->85|115->86|115->86|115->86|116->87|116->87|116->87|121->92|121->92|121->92|122->93|122->93|122->93|123->94|123->94|123->94|124->95|124->95|124->95|125->96|125->96|125->96|126->97|126->97|126->97|127->98|127->98|127->98|128->99|128->99|128->99|136->107|136->107|136->107|137->108|137->108|137->108|138->109|138->109|138->109|139->110|139->110|139->110|146->117|146->117|146->117|151->122|151->122|151->122|158->129|158->129|158->129|160->131|160->131|160->131|169->140|169->140|169->140|170->141|170->141|170->141|170->141|170->141|170->141|176->147|179->150|179->150|180->151|180->151|180->151|181->152
                    -- GENERATED --
                */
            