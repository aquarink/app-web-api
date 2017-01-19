
package views.html.send

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
object create_bill_payment extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Form[models.forms.CashInForm],String,TreeMap[String, String],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(transactionForm: Form[models.forms.CashInForm], channelCode: String, channels: TreeMap[String, String]):play.api.templates.Html = {
        _display_ {import helper._

import helper.twitterBootstrap._

import partial._


Seq[Any](format.raw/*1.106*/("""

"""),format.raw/*6.1*/("""
"""),_display_(Seq[Any](/*7.2*/layout("Bill Payment")/*7.24*/ {_display_(Seq[Any](format.raw/*7.26*/("""

<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*10.7*/doku/*10.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG010","Bill Payment"))),format.raw/*10.78*/("""</h1>
</div>

"""),_display_(Seq[Any](/*13.2*/partial/*13.9*/.flash_alert())),format.raw/*13.23*/("""

<style>
	.ui-autocomplete """),format.raw/*16.19*/("""{"""),format.raw/*16.20*/("""
		max-height: 200px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
	"""),format.raw/*21.2*/("""}"""),format.raw/*21.3*/("""
	
	/* IE 6 doesn't support max-height
	* we use height instead, but this forces the menu to always be this tall
	*/
	* html .ui-autocomplete """),format.raw/*26.26*/("""{"""),format.raw/*26.27*/("""
		height: 200px;
	"""),format.raw/*28.2*/("""}"""),format.raw/*28.3*/("""
	.ui-autocomplete-loading """),format.raw/*29.27*/("""{"""),format.raw/*29.28*/("""
		background: white url(""""),_display_(Seq[Any](/*30.27*/routes/*30.33*/.Assets.at("images/ui-anim_basic_16x16.gif"))),format.raw/*30.77*/("""") right center no-repeat;
	"""),format.raw/*31.2*/("""}"""),format.raw/*31.3*/("""
</style>
<div class="container-transaction-send-inner">
	"""),_display_(Seq[Any](/*34.3*/helper/*34.9*/.form(routes.Send.confirmBillPayment, 'id -> "form-cashin", 'autocomplete -> "on", 'class -> "row form form-horizontal", Symbol("data-url-verify") -> routes.Send.verify.url)/*34.182*/ {_display_(Seq[Any](format.raw/*34.184*/("""
		<div class="hide">
			<input type="hidden" name="trackingId" id="trackingId" value=""""),_display_(Seq[Any](/*36.67*/transactionForm/*36.82*/.get().trackingId)),format.raw/*36.99*/("""" />
			<input type="hidden" name="sender.id" id="sender_id" />
			<input type="hidden" name="beneficiary.id" id="beneficiary_id" />
			<input type="hidden" name="agent.id" id="user_id" value='"""),_display_(Seq[Any](/*39.62*/session/*39.69*/.get("userId"))),format.raw/*39.83*/("""' />
			<input type="hidden" name="corporate.code" id="corporate_code" value='"""),_display_(Seq[Any](/*40.75*/session/*40.82*/.get("corporateCode"))),format.raw/*40.103*/("""' />
			<input type="hidden" name="billPayment.selectOperatorName" id="billPayment_selectOperatorName" value='' />
			<input type="hidden" name="billPayment.selectDenomName" id="billPayment_selectDenomName" value='' />
			<input type="hidden" name="billPayment.amount" id="billPayment_amount" value='' />
			<input type="hidden" name="billPayment.accountName" id="billPayment_accountName" value='' />
		</div>
		<div class="span12">
			<p>
			"""),_display_(Seq[Any](/*48.5*/doku/*48.9*/.kirimdoku.util.MultiLanguage.getLanguage("LANG037","Please fill all the mandatory fields highlighted with the bold labels below"))),format.raw/*48.139*/("""
			</p>
		</div>

		<fieldset class="span12 form-cashin-info">
			<legend>"""),_display_(Seq[Any](/*53.13*/doku/*53.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG038","Transaction information"))),format.raw/*53.95*/("""</legend>
			<div class="row hide">
				<div class="control-group span4 well">
					<input type="text" name="idToken" class="required"/>
					"""),_display_(Seq[Any](/*57.7*/select(transactionForm("beneficiaryCurrency.code"),
						options("IDR" -> "IDR"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG220","Receive currency"),
						'class -> "currency",
						'_showConstraints -> false))),format.raw/*61.34*/("""
					"""),_display_(Seq[Any](/*62.7*/inputText(transactionForm("fundSource"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG219","Source of fund"),
						'class -> "input-large",
						'_help -> "(Optional field)"
					))),format.raw/*66.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="span12 container-info"></div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*72.7*/helper/*72.13*/.select(transactionForm("channel.code"),
						options(channels), 
							'_class -> "",
							'readOnly -> true,
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG039","Service")
						))),format.raw/*77.8*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*80.7*/select(
						transactionForm("senderCountry.code"),
						options(transactionForm("senderCountry.code").value -> transactionForm("senderCountry.name").value),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG040","Sending country"),
						'readOnly -> true,
						'_showConstraints -> false, 'class -> "required"
					))),format.raw/*86.7*/("""
				</div>
				<div class="control-group span4 hide">
					"""),_display_(Seq[Any](/*89.7*/select(transactionForm("senderCurrency.code"),
						options(transactionForm("senderCurrency.code").value -> transactionForm("senderCurrency.code").value),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG218","Sender currency"),
						'class -> "currency disabled",
						'readOnly -> true,
						'_showConstraints -> false))),format.raw/*94.34*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*97.7*/helper/*97.13*/.inputText(transactionForm("rate"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG045","Exchange rate"),
						'_class -> "",
						'class -> "rate amount",
						'readOnly -> "true",
						'_help -> "",
						'alt -> "amount",
						'tabindex -> "-1",
						'_showConstraints -> false
					))),format.raw/*106.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*111.7*/helper/*111.13*/.select(transactionForm("beneficiaryCountry.code"),
						options(models.Country.optionsBeneficiaryCountryBillPayment(transactionForm("senderCountry.code").value)),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG027","Receive country"),
						'_showConstraints -> false,
						'class -> "country_id",
						'_class -> "",
						'class -> "required",
						'readOnly -> "true"
					))),format.raw/*119.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*122.7*/helper/*122.13*/.input(transactionForm("beneficiaryCity"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG217","Receive city"),
						'class -> "",
						'_help -> "",
						'value -> "Jakarta",
						'readonly -> "true",
						'_showConstraints -> false
					)/*129.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*129.36*/("""
							<input type="text" id=""""),_display_(Seq[Any](/*130.32*/id)),format.raw/*130.34*/("""" name=""""),_display_(Seq[Any](/*130.43*/name)),format.raw/*130.47*/("""" """),_display_(Seq[Any](/*130.50*/toHtmlArgs(args))),format.raw/*130.66*/(""">
					""")))})),format.raw/*131.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*134.7*/helper/*134.13*/.inputText(transactionForm("feesTotal"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG145","Fee"),
						'_class -> "",
						'class -> "amount",
						'readOnly -> "readonly",
						'tabindex -> "-1",
						'_help -> "",
						'_showConstraints -> false
					))),format.raw/*142.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*147.7*/helper/*147.13*/.input(transactionForm("senderAmount")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG042","Sending amount")
						, '_class -> "required"
						, 'class -> "required amount input-block-level"
						, 'readOnly -> true
						, '_help -> ""
						, '_showConstraints -> false
						, 'maxLength -> 14
					)/*155.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*155.36*/("""
			            <div class="input-prepend input-append">
			            	<span class="add-on"></span>
							<input type="text" id=""""),_display_(Seq[Any](/*158.32*/id)),format.raw/*158.34*/("""" name=""""),_display_(Seq[Any](/*158.43*/name)),format.raw/*158.47*/("""" value=""""),_display_(Seq[Any](/*158.57*/value)),format.raw/*158.62*/("""" read """),_display_(Seq[Any](/*158.70*/toHtmlArgs(args))),format.raw/*158.86*/(""">
							<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*159.67*/transactionForm("senderCurrency.code")/*159.105*/.value)),format.raw/*159.111*/("""</span>
						</div>
					""")))})),format.raw/*161.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*164.7*/helper/*164.13*/.input(transactionForm("beneficiaryAmount")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG043","Receive amount")
						, '_class -> "required"
						, 'class -> "amount"
						, 'style -> "width:71%"
						, 'readOnly -> true
						, '_help -> ""
						, '_showConstraints -> false
						, 'maxLength -> 14
					)/*173.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*173.36*/("""
			            <div class="input-prepend input-append">
							<span class="add-on"><input type="hidden" name="sendType" value="beneficiaryAmount"/></span>
							<input type="text" id=""""),_display_(Seq[Any](/*176.32*/id)),format.raw/*176.34*/("""" name=""""),_display_(Seq[Any](/*176.43*/name)),format.raw/*176.47*/("""" value=""""),_display_(Seq[Any](/*176.57*/value)),format.raw/*176.62*/("""" """),_display_(Seq[Any](/*176.65*/toHtmlArgs(args))),format.raw/*176.81*/(""">
							<span class="add-on currency-code" style="font-size:11px">IDR</span>
						</div>
					""")))})),format.raw/*179.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*182.7*/helper/*182.13*/.inputText(transactionForm("additionalFee"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG047","Additional Fee"),
						'_class -> "",
						'class -> "amount",
						'readOnly -> "readonly",
						'tabindex -> "-1",
						'_help -> "",
						'_showConstraints -> false
					))),format.raw/*190.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*195.7*/helper/*195.13*/.input(transactionForm("collectAmount")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG044","Amount to collect")
						, '_class -> "required"
						, 'class -> "required"
						, 'class -> "amount input-block-level"
						, 'readOnly -> "true"
						, 'style -> "width:95%"
						, '_help -> ""
						, 'tabindex -> "-1"
						, '_showConstraints -> false
					)/*205.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*205.36*/("""
			            <div class="input-append">
							<input type="text" id=""""),_display_(Seq[Any](/*207.32*/id)),format.raw/*207.34*/("""" name=""""),_display_(Seq[Any](/*207.43*/name)),format.raw/*207.47*/("""" value=""""),_display_(Seq[Any](/*207.57*/value)),format.raw/*207.62*/("""" """),_display_(Seq[Any](/*207.65*/toHtmlArgs(args))),format.raw/*207.81*/(""">
							<span class="add-on currency-code" style="font-size:11px">"""),_display_(Seq[Any](/*208.67*/transactionForm("senderCurrency.code")/*208.105*/.value)),format.raw/*208.111*/("""</span>
						</div>
					""")))})),format.raw/*210.7*/("""
				</div>
				<div class="control-group span8">
					"""),_display_(Seq[Any](/*213.7*/helper/*213.13*/.input(transactionForm("senderNote"),
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG048","Sending purpose"),
						'class -> "input-xlarge",
						'style -> "width: 95%",
						'value -> "Beli Pulsa & Bayar Tagihan",
						'readOnly -> "true"
					)/*219.7*/ { (id, name, value, args) =>_display_(Seq[Any](format.raw/*219.36*/("""
							<input type="text" id=""""),_display_(Seq[Any](/*220.32*/id)),format.raw/*220.34*/("""" name=""""),_display_(Seq[Any](/*220.43*/name)),format.raw/*220.47*/("""" """),_display_(Seq[Any](/*220.50*/toHtmlArgs(args))),format.raw/*220.66*/(""">
					""")))})),format.raw/*221.7*/("""
				</div>
			</div>

			<legend>"""),_display_(Seq[Any](/*225.13*/doku/*225.17*/.kirimdoku.util.MultiLanguage.getLanguage("LANG081","Bill information"))),format.raw/*225.88*/("""</legend>
			<div class="row">
				<div class="span12 container-info"></div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*229.7*/helper/*229.13*/.select(transactionForm("billPayment.billPaymentService"),
						options(
							"" -> "-- Bill Payment Service --"
						), 
							'_class -> "required",
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG082","Bill Payment Service")
						))),format.raw/*235.8*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*238.7*/helper/*238.13*/.select(transactionForm("billPayment.selectBill"),
						options(), 
							'_class -> "required",
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG083","Select Bill"),
							'autofocus -> "true"
						))),format.raw/*243.8*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*246.7*/helper/*246.13*/.inputText(
						transactionForm("billPayment.accountNumber")
						, '_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG084","Phone Number / Subscriber ID")
						, '_showConstraints -> false
						, '_class -> "required"
						, 'class -> "required"
						, 'style -> "width:80%"
						, 'type -> "tel"
						, 'value -> ""
						, 'onkeypress -> "return numbersonly(this, event)"
					))),format.raw/*256.7*/("""
				</div>
			</div>
			<div class="row">
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*261.7*/helper/*261.13*/.select(transactionForm("billPayment.selectOperator"),
						options(), 
						'_class -> "required",
						'class -> "required",
						'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG085","Select Operator")
					))),format.raw/*266.7*/("""
				</div>
				<div class="control-group span4">
					"""),_display_(Seq[Any](/*269.7*/helper/*269.13*/.select(transactionForm("billPayment.selectDenom"),
						options(), 
							'_class -> "required",
							'class -> "required",
							'_label -> doku.kirimdoku.util.MultiLanguage.getLanguage("LANG086","Select Denom")
						))),format.raw/*274.8*/("""
				</div>
			</div>
		</fieldset>

		<div class="span12">
            <div class="form-actions">
				<button type="reset" class="btn"><i class="icon-repeat"></i> """),_display_(Seq[Any](/*281.67*/doku/*281.71*/.kirimdoku.util.MultiLanguage.getLanguage("LANG068","Reset"))),format.raw/*281.131*/("""</button>
				<button type="submit" id="btn-send-primary" class="btn btn-primary btn-next" data-loading-text="Loading..." data-disable-with="Sending..." data-enable-with="Sent">"""),_display_(Seq[Any](/*282.169*/doku/*282.173*/.kirimdoku.util.MultiLanguage.getLanguage("LANG067","Send"))),format.raw/*282.232*/(""" <i class="icon-white icon-arrow-right"></i></button>
            </div>
		</div>
	""")))})),format.raw/*285.3*/("""
	

	<div id="sendModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Send dialog" aria-hidden="true">
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

	<div id="checkPinyin" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="Check Code Pinyin" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">Check Code Pinyin</h3>
		</div>
		<div class="modal-body">
			<p id="pageCheckPinyin"></p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
	</div>

    </div>
</div>
""")))}/*316.2*/ {_display_(Seq[Any](format.raw/*316.4*/("""
<script src=""""),_display_(Seq[Any](/*317.15*/routes/*317.21*/.Assets.at("javascripts/numbersonly.js"))),format.raw/*317.61*/("""" type="text/javascript"></script>
<script type="text/javascript">
<!--
	var denomList = null;
	var listBiller = '"""),_display_(Seq[Any](/*321.21*/transactionForm/*321.36*/.get().channel.additionalParam)),format.raw/*321.66*/("""';
	var arrSelectorBillPayment = jQuery.parseJSON(listBiller.replace(/&quot;/g,'"'));
	
	(function() """),format.raw/*324.14*/("""{"""),format.raw/*324.15*/("""
	  var __bind = function(fn, me)"""),format.raw/*325.33*/("""{"""),format.raw/*325.34*/(""" return function()"""),format.raw/*325.52*/("""{"""),format.raw/*325.53*/(""" return fn.apply(me, arguments); """),format.raw/*325.86*/("""}"""),format.raw/*325.87*/("""; """),format.raw/*325.89*/("""}"""),format.raw/*325.90*/(""";
	  $.fn.infoForm = function() """),format.raw/*326.31*/("""{"""),format.raw/*326.32*/("""
	    var additionalFeeField, beneficiaryAmountField, beneficiaryCountryField, beneficiaryCurrencyField, channelField, collectAmountField, feeField, inquiryIdField, rateField, sendAmountField;
	    channelField = this.find("select[name='channel.code']");
	    beneficiaryCountryField = this.find("select[name='beneficiaryCountry.code']");
	    beneficiaryCurrencyField = this.find("select[name='beneficiaryCurrency.code']");
	    sendAmountField = this.find("input[name='senderAmount']");
	    beneficiaryAmountField = this.find("input[name='beneficiaryAmount']");
	    inquiryIdField = this.find("input[name='idToken']");
	    rateField = this.find("input[name='rate']");
	    feeField = this.find("input[name='feesTotal']");
	    additionalFeeField = this.find("input[name='additionalFee']");
	    collectAmountField = this.find("input[name='collectAmount']");
	    
	    billPayment_billPaymentService = $('#billPayment_billPaymentService');
	    billPayment_selectBill = $('#billPayment_selectBill');
	    billPayment_selectDenom = $('#billPayment_selectDenom');
	    billPayment_selectDenomName = $('#billPayment_selectDenomName');
	    billPayment_selectOperator = $('#billPayment_selectOperator');
	    billPayment_selectOperatorName = $('#billPayment_selectOperatorName');
	    billPayment_amount = $('#billPayment_amount');
	    billPayment_accountNumber = $('#billPayment_accountNumber');
	    billPayment_accountName = $('#billPayment_accountName');
	    
		$.each(arrSelectorBillPayment.billPaymentService, function(index, items) """),format.raw/*349.76*/("""{"""),format.raw/*349.77*/("""   
			billPayment_billPaymentService
		         .append($("<option></option>")
		                    .attr("value", items.billPaymentServiceName)
		                    .text(items.billPaymentServiceName)); 
		     console.log( index +" " + items.billPaymentServiceName)
		"""),format.raw/*355.3*/("""}"""),format.raw/*355.4*/(""");
	    
	    var inquiryType = "";
	    this.submitInquiry = __bind(function(params, cb) """),format.raw/*358.55*/("""{"""),format.raw/*358.56*/("""
	      this.updateInquiry(null);
	      return $.ajax("""),format.raw/*360.22*/("""{"""),format.raw/*360.23*/("""
	        type: 'GET',
	        url: '/send/inquiryBillPayment',
	        data: $(this).serialize(),
			error: __bind(function(err, msg) """),format.raw/*364.37*/("""{"""),format.raw/*364.38*/("""
			    return billPayment_selectDenom.removeClass('ui-autocomplete-loading');
			"""),format.raw/*366.4*/("""}"""),format.raw/*366.5*/(""", this),
			beforeSend: __bind(function(xhr) """),format.raw/*367.37*/("""{"""),format.raw/*367.38*/("""
			    return billPayment_selectDenom.addClass('ui-autocomplete-loading');
			"""),format.raw/*369.4*/("""}"""),format.raw/*369.5*/(""", this),
	        success: __bind(function(result) """),format.raw/*370.43*/("""{"""),format.raw/*370.44*/("""
	        	billPayment_selectDenom.removeClass('ui-autocomplete-loading');
	          	return cb(null, result);
	        """),format.raw/*373.10*/("""}"""),format.raw/*373.11*/(""", this)
	      """),format.raw/*374.8*/("""}"""),format.raw/*374.9*/(""");
	    """),format.raw/*375.6*/("""}"""),format.raw/*375.7*/(""", this);
	    this.updateInquiry = function(result) """),format.raw/*376.44*/("""{"""),format.raw/*376.45*/("""
	      var collectAmount;
	      if (result && result.inquiry && result.status == 0) """),format.raw/*378.60*/("""{"""),format.raw/*378.61*/("""
	        inquiryIdField.val(result.inquiry.idToken);
	        rateField.val(Number(result.inquiry.forexReference.rate).toFixed(6)).trigger('keyup');
	        feeField.val(result.inquiry.fund.fees.total);
	        feeField.inputmask("money");
	        additionalFeeField.val(result.inquiry.fund.fees.additionalFee);
	        //additionalFeeField.inputmask("money");
	        sendAmountField.val(result.inquiry.fund.origin.amount);
	        beneficiaryAmountField.val(Number(result.inquiry.fund.destination.amount));
	        collectAmount = Number(result.inquiry.fund.origin.amount) + Number(result.inquiry.fund.fees.total);
	        collectAmountField.val(collectAmount).trigger('keyup');
	        billPayment_accountName.val(result.billPayment.subscriber_NAME);
	        
	        return collectAmountField.inputmask("money");
	      """),format.raw/*392.8*/("""}"""),format.raw/*392.9*/(""" else if (result && result.message) """),format.raw/*392.45*/("""{"""),format.raw/*392.46*/("""
	        $(this).find('.container-info').alert('error', result.message, 3000);
	        rateField.val("");
	        return feeField.val("");
	      """),format.raw/*396.8*/("""}"""),format.raw/*396.9*/("""
	    """),format.raw/*397.6*/("""}"""),format.raw/*397.7*/(""";
	    
	    
	    this.updateInquiryBillPayment = function(result) """),format.raw/*400.55*/("""{"""),format.raw/*400.56*/("""
	      var collectAmount;
	      if (result && result.inquiry) """),format.raw/*402.38*/("""{"""),format.raw/*402.39*/("""
	        if (result.billPayment != null) """),format.raw/*403.42*/("""{"""),format.raw/*403.43*/("""
	        	inquiryIdField.val(result.inquiry.idToken);
	        	denom = result.billPayment.biller_NAME;
	        	billPayment_selectDenom.append('<option selected="selected" value="">Select Denom '+denom+'</option>');
		        $.each(result.billPayment.billdetails, function(index, items) """),format.raw/*407.73*/("""{"""),format.raw/*407.74*/("""   
		        	billPayment_selectDenom
				         .append($("<option></option>")
				                    .attr("value", index)
				                    .text(items.title)); 
				     console.log( items.billid + " " + items.title)
				"""),format.raw/*413.5*/("""}"""),format.raw/*413.6*/(""");
		        denomList = result.billPayment.billdetails;
		        rateField.val(Number(result.inquiry.forexReference.rate).toFixed(6)).trigger('keyup');
		        feeField.val(result.inquiry.fund.fees.total);
		        feeField.inputmask("money");
		        additionalFeeField.val(result.inquiry.fund.fees.additionalFee);
	        """),format.raw/*419.10*/("""}"""),format.raw/*419.11*/(""" else """),format.raw/*419.17*/("""{"""),format.raw/*419.18*/("""
	        	billPayment_selectDenom.append('<option selected="selected" value="">No Available Denom</option>')
	        """),format.raw/*421.10*/("""}"""),format.raw/*421.11*/("""
	        return collectAmountField.inputmask("money");
	      """),format.raw/*423.8*/("""}"""),format.raw/*423.9*/(""" else if (result && result.message) """),format.raw/*423.45*/("""{"""),format.raw/*423.46*/("""
	        $(this).find('.container-info').alert('error', result.message, 3000);
	        rateField.val("");
	        return feeField.val("");
	      """),format.raw/*427.8*/("""}"""),format.raw/*427.9*/("""
	    """),format.raw/*428.6*/("""}"""),format.raw/*428.7*/(""";
	    
	    billPayment_billPaymentService.on('change', __bind(function(e) """),format.raw/*430.69*/("""{"""),format.raw/*430.70*/("""
	    	billPayment_selectBill.empty();
	    	billPayment_selectOperator.empty();
	    	billPayment_selectDenom.empty();
	    	if (billPayment_billPaymentService.val() != '') """),format.raw/*434.55*/("""{"""),format.raw/*434.56*/("""
		    	billPayment_selectBill.append('<option selected="selected" value="">-- Choose an Bill --</option>');
		        $.each(arrSelectorBillPayment.billPaymentService[$("#billPayment_billPaymentService option:selected").index()-1].biller, function(index, items) """),format.raw/*436.155*/("""{"""),format.raw/*436.156*/("""    
		        	billPayment_selectBill
				         .append($("<option></option>")
				                    .attr("value", items.billName)
				                    .text(items.billName)); 
				    console.log( index +" " + items.billName)
				"""),format.raw/*442.5*/("""}"""),format.raw/*442.6*/(""");
		        billPayment_selectBill.focus();
	  		"""),format.raw/*444.6*/("""}"""),format.raw/*444.7*/("""
		"""),format.raw/*445.3*/("""}"""),format.raw/*445.4*/(""", this));
	    
	    billPayment_selectBill.on('change', __bind(function(e) """),format.raw/*447.61*/("""{"""),format.raw/*447.62*/("""
	    	billPayment_selectOperator.empty();
	    	billPayment_selectDenom.empty();
	    	if (billPayment_selectBill.val() != '') """),format.raw/*450.47*/("""{"""),format.raw/*450.48*/("""
		    	billPayment_selectOperator.append('<option selected="selected" value="">-- Choose an Operator --</option>');
		        $.each(arrSelectorBillPayment.billPaymentService[$("#billPayment_billPaymentService option:selected").index()-1].biller[$("#billPayment_selectBill option:selected").index()-1].operator, function(index, items) """),format.raw/*452.220*/("""{"""),format.raw/*452.221*/("""    
		        	billPayment_selectOperator
				         .append($("<option></option>")
				                    .attr("value", items.billerCode)
				                    .text(items.operatorName)); 
				    console.log( index +" " + items.billerCode + " " + items.operatorName)
				"""),format.raw/*458.5*/("""}"""),format.raw/*458.6*/(""");
		    	billPayment_accountNumber.focus();
	    	"""),format.raw/*460.7*/("""}"""),format.raw/*460.8*/("""
		"""),format.raw/*461.3*/("""}"""),format.raw/*461.4*/(""", this));
	    
	    billPayment_selectOperator.on('change', __bind(function(e) """),format.raw/*463.65*/("""{"""),format.raw/*463.66*/("""
	    	billPayment_selectDenom.empty();
			sendAmountField.val('');
	    	collectAmountField.val('');
			beneficiaryAmountField.val('');
			billPayment_selectOperatorName.val('');
			denomList = null;
			if ($.trim( billPayment_accountNumber.val() ) == '') """),format.raw/*470.57*/("""{"""),format.raw/*470.58*/("""
				billPayment_selectOperator.val('');
	    		billPayment_selectDenom.val('');
	    		alert("Input phone number / subscriber ID first then select operator");
	    		billPayment_accountNumber.focus();
			"""),format.raw/*475.4*/("""}"""),format.raw/*475.5*/(""" else """),format.raw/*475.11*/("""{"""),format.raw/*475.12*/("""
			    	if (billPayment_selectOperator.val() != "") """),format.raw/*476.53*/("""{"""),format.raw/*476.54*/("""
			    	  beneficiaryAmountField.val('1');
			    	  var datas = $(this).serialize();
			    	  beneficiaryAmountField.val('');
			    	  console.log('Operator '+$('#billPayment_selectOperator option:selected' ).text())
			    	  billPayment_selectOperatorName.val($('#billPayment_selectOperator option:selected' ).text());
			    	  $.ajax("""),format.raw/*482.18*/("""{"""),format.raw/*482.19*/("""
					        type: 'GET',
					        url: '/send/inquiryBillPayment',
					        data: datas,
							error: __bind(function(err, msg) """),format.raw/*486.41*/("""{"""),format.raw/*486.42*/("""
							    return billPayment_selectDenom.removeClass('ui-autocomplete-loading');
							"""),format.raw/*488.8*/("""}"""),format.raw/*488.9*/(""", this),
							beforeSend: __bind(function(xhr) """),format.raw/*489.41*/("""{"""),format.raw/*489.42*/("""
							    return billPayment_selectDenom.addClass('ui-autocomplete-loading');
							"""),format.raw/*491.8*/("""}"""),format.raw/*491.9*/(""", this),
					        success: __bind(function(result) """),format.raw/*492.47*/("""{"""),format.raw/*492.48*/("""
					        	billPayment_selectDenom.removeClass('ui-autocomplete-loading');
					        	this.updateInquiryBillPayment(result);
					        """),format.raw/*495.14*/("""}"""),format.raw/*495.15*/(""", this)
					      """),format.raw/*496.12*/("""}"""),format.raw/*496.13*/(""");
			    	"""),format.raw/*497.9*/("""}"""),format.raw/*497.10*/("""
			"""),format.raw/*498.4*/("""}"""),format.raw/*498.5*/("""
		"""),format.raw/*499.3*/("""}"""),format.raw/*499.4*/(""", this));
	    
	    
	    billPayment_selectDenom.on('change', __bind(function(e) """),format.raw/*502.62*/("""{"""),format.raw/*502.63*/("""
	    	if ($('#billPayment_selectDenom option:selected' ).val() != "") """),format.raw/*503.71*/("""{"""),format.raw/*503.72*/("""
		    	if (billPayment_accountNumber.val() != "") """),format.raw/*504.51*/("""{"""),format.raw/*504.52*/("""
			    	console.log('billid             : '+denomList[$('#billPayment_selectDenom option:selected' ).val()].billid);
			    	console.log('descriptions key   : '+denomList[$('#billPayment_selectDenom option:selected' ).val()].descriptions[0].key);
			    	console.log('descriptions value : '+denomList[$('#billPayment_selectDenom option:selected' ).val()].descriptions[0].value);
			    	console.log('title              : '+denomList[$('#billPayment_selectDenom option:selected' ).val()].title);
			    	console.log('totalamount        : '+denomList[$('#billPayment_selectDenom option:selected' ).val()].totalamount);
			    	console.log('originTotalAmount  : '+denomList[$('#billPayment_selectDenom option:selected' ).val()].originTotalAmount);
			    	
			    	beneficiaryAmountField.val(Number(denomList[$('#billPayment_selectDenom option:selected').val()].totalamount));
			    	billPayment_selectDenomName.val(denomList[$('#billPayment_selectDenom option:selected' ).val()].title);
			    	billPayment_amount.val(denomList[$('#billPayment_selectDenom option:selected' ).val()].totalamount);
			    	sendAmountField.val('');
				    return this.submitInquiry("""),format.raw/*516.35*/("""{"""),format.raw/*516.36*/("""}"""),format.raw/*516.37*/(""", __bind(function(err, result) """),format.raw/*516.68*/("""{"""),format.raw/*516.69*/("""
				        if (err) """),format.raw/*517.22*/("""{"""),format.raw/*517.23*/("""
				          console.log("submit inq err", err);
				          console.log("submit inq res", result);
				          return $(this).find('.container-info').alert('error', err, 5000);
				        """),format.raw/*521.13*/("""}"""),format.raw/*521.14*/(""" else """),format.raw/*521.20*/("""{"""),format.raw/*521.21*/("""
				          this.updateInquiry(result);
				        """),format.raw/*523.13*/("""}"""),format.raw/*523.14*/("""
				      """),format.raw/*524.11*/("""}"""),format.raw/*524.12*/(""", this));
		    	"""),format.raw/*525.8*/("""}"""),format.raw/*525.9*/(""" else """),format.raw/*525.15*/("""{"""),format.raw/*525.16*/("""
		    		billPayment_selectDenom.val('');
		    		alert("Input phone number / subscriber ID first");
		    		billPayment_accountNumber.focus();
		    	"""),format.raw/*529.8*/("""}"""),format.raw/*529.9*/("""
	    	"""),format.raw/*530.7*/("""}"""),format.raw/*530.8*/("""
		"""),format.raw/*531.3*/("""}"""),format.raw/*531.4*/(""", this));
	    
	    this.isValid = function() """),format.raw/*533.32*/("""{"""),format.raw/*533.33*/("""
	      return inquiryIdField.val().length > 0;
	    """),format.raw/*535.6*/("""}"""),format.raw/*535.7*/(""";
	    return this;
	  """),format.raw/*537.4*/("""}"""),format.raw/*537.5*/(""";
	  $(function() """),format.raw/*538.17*/("""{"""),format.raw/*538.18*/("""
	    var extractLast, extractLastPy, infoForm, split, splitpy, splitzh;
	    infoForm = $("form#form-cashin .form-cashin-info").infoForm();
	    split = function(val) """),format.raw/*541.28*/("""{"""),format.raw/*541.29*/("""
	      return val.split(/,\s*/);
	    """),format.raw/*543.6*/("""}"""),format.raw/*543.7*/(""";
	    splitpy = function(val) """),format.raw/*544.30*/("""{"""),format.raw/*544.31*/("""
	      return val.split(';');
	    """),format.raw/*546.6*/("""}"""),format.raw/*546.7*/(""";
	    splitzh = function(val) """),format.raw/*547.30*/("""{"""),format.raw/*547.31*/("""
	      return val.split('');
	    """),format.raw/*549.6*/("""}"""),format.raw/*549.7*/(""";
	    extractLast = function(term) """),format.raw/*550.35*/("""{"""),format.raw/*550.36*/("""
	      return split(term).pop();
	    """),format.raw/*552.6*/("""}"""),format.raw/*552.7*/(""";
	    extractLastPy = function(term) """),format.raw/*553.37*/("""{"""),format.raw/*553.38*/("""
	      return splitpy(term).pop();
	    """),format.raw/*555.6*/("""}"""),format.raw/*555.7*/(""";
	  """),format.raw/*556.4*/("""}"""),format.raw/*556.5*/(""");
	"""),format.raw/*557.2*/("""}"""),format.raw/*557.3*/(""").call(this);

	
	
//-->
</script>
""")))})),format.raw/*563.2*/("""
"""))}
    }
    
    def render(transactionForm:Form[models.forms.CashInForm],channelCode:String,channels:TreeMap[String, String]): play.api.templates.Html = apply(transactionForm,channelCode,channels)
    
    def f:((Form[models.forms.CashInForm],String,TreeMap[String, String]) => play.api.templates.Html) = (transactionForm,channelCode,channels) => apply(transactionForm,channelCode,channels)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/create_bill_payment.scala.html
                    HASH: 280400da237ba31917e487b0587645ae80c1fe1d
                    MATRIX: 796->1|1046->105|1074->176|1110->178|1140->200|1179->202|1248->236|1261->240|1350->307|1400->322|1415->329|1451->343|1507->371|1536->372|1665->474|1693->475|1863->617|1892->618|1938->637|1966->638|2021->665|2050->666|2113->693|2128->699|2194->743|2249->771|2277->772|2371->831|2385->837|2568->1010|2609->1012|2733->1100|2757->1115|2796->1132|3026->1326|3042->1333|3078->1347|3193->1426|3209->1433|3253->1454|3732->1898|3744->1902|3897->2032|4009->2108|4022->2112|4122->2190|4300->2333|4560->2571|4602->2578|4828->2783|4996->2916|5011->2922|5239->3129|5330->3185|5690->3524|5786->3585|6152->3929|6243->3985|6258->3991|6594->4305|6717->4392|6733->4398|7158->4801|7250->4857|7266->4863|7541->5129|7609->5158|7678->5190|7703->5192|7749->5201|7776->5205|7816->5208|7855->5224|7895->5232|7987->5288|8003->5294|8309->5578|8432->5665|8448->5671|8786->6000|8854->6029|9024->6162|9049->6164|9095->6173|9122->6177|9169->6187|9197->6192|9242->6200|9281->6216|9386->6284|9435->6322|9465->6328|9524->6355|9616->6411|9632->6417|9978->6754|10046->6783|10271->6971|10296->6973|10342->6982|10369->6986|10416->6996|10444->7001|10484->7004|10523->7020|10652->7117|10744->7173|10760->7179|11081->7478|11204->7565|11220->7571|11615->7957|11683->7986|11794->8060|11819->8062|11865->8071|11892->8075|11939->8085|11967->8090|12007->8093|12046->8109|12151->8177|12200->8215|12230->8221|12289->8248|12381->8304|12397->8310|12677->8581|12745->8610|12814->8642|12839->8644|12885->8653|12912->8657|12952->8660|12991->8676|13031->8684|13103->8719|13117->8723|13211->8794|13368->8915|13384->8921|13667->9182|13759->9238|13775->9244|14021->9468|14113->9524|14129->9530|14552->9931|14675->10018|14691->10024|14941->10252|15033->10308|15049->10314|15297->10540|15499->10705|15513->10709|15597->10769|15813->10947|15828->10951|15911->11010|16027->11094|17057->12105|17097->12107|17149->12122|17165->12128|17228->12168|17380->12283|17405->12298|17458->12328|17588->12429|17618->12430|17680->12463|17710->12464|17757->12482|17787->12483|17849->12516|17879->12517|17910->12519|17940->12520|18001->12552|18031->12553|19602->14095|19632->14096|19933->14369|19962->14370|20081->14460|20111->14461|20195->14516|20225->14517|20391->14654|20421->14655|20531->14737|20560->14738|20634->14783|20664->14784|20771->14863|20800->14864|20880->14915|20910->14916|21060->15037|21090->15038|21133->15053|21162->15054|21198->15062|21227->15063|21308->15115|21338->15116|21453->15202|21483->15203|22347->16039|22376->16040|22441->16076|22471->16077|22648->16226|22677->16227|22711->16233|22740->16234|22837->16302|22867->16303|22960->16367|22990->16368|23061->16410|23091->16411|23411->16702|23441->16703|23704->16938|23733->16939|24094->17271|24124->17272|24159->17278|24189->17279|24337->17398|24367->17399|24458->17462|24487->17463|24552->17499|24582->17500|24759->17649|24788->17650|24822->17656|24851->17657|24956->17733|24986->17734|25189->17908|25219->17909|25512->18172|25543->18173|25812->18414|25841->18415|25919->18465|25948->18466|25979->18469|26008->18470|26113->18546|26143->18547|26300->18675|26330->18676|26696->19012|26727->19013|27035->19293|27064->19294|27143->19345|27172->19346|27203->19349|27232->19350|27341->19430|27371->19431|27657->19688|27687->19689|27920->19894|27949->19895|27984->19901|28014->19902|28096->19955|28126->19956|28497->20298|28527->20299|28695->20438|28725->20439|28843->20529|28872->20530|28950->20579|28980->20580|29095->20667|29124->20668|29208->20723|29238->20724|29412->20869|29442->20870|29490->20889|29520->20890|29559->20901|29589->20902|29621->20906|29650->20907|29681->20910|29710->20911|29822->20994|29852->20995|29952->21066|29982->21067|30062->21118|30092->21119|31284->22282|31314->22283|31344->22284|31404->22315|31434->22316|31485->22338|31515->22339|31740->22535|31770->22536|31805->22542|31835->22543|31919->22598|31949->22599|31989->22610|32019->22611|32064->22628|32093->22629|32128->22635|32158->22636|32337->22787|32366->22788|32401->22795|32430->22796|32461->22799|32490->22800|32566->22847|32596->22848|32677->22901|32706->22902|32757->22925|32786->22926|32833->22944|32863->22945|33060->23113|33090->23114|33157->23153|33186->23154|33246->23185|33276->23186|33340->23222|33369->23223|33429->23254|33459->23255|33522->23290|33551->23291|33616->23327|33646->23328|33713->23367|33742->23368|33809->23406|33839->23407|33908->23448|33937->23449|33970->23454|33999->23455|34031->23459|34060->23460|34128->23496
                    LINES: 26->1|34->1|36->6|37->7|37->7|37->7|40->10|40->10|40->10|43->13|43->13|43->13|46->16|46->16|51->21|51->21|56->26|56->26|58->28|58->28|59->29|59->29|60->30|60->30|60->30|61->31|61->31|64->34|64->34|64->34|64->34|66->36|66->36|66->36|69->39|69->39|69->39|70->40|70->40|70->40|78->48|78->48|78->48|83->53|83->53|83->53|87->57|91->61|92->62|96->66|102->72|102->72|107->77|110->80|116->86|119->89|124->94|127->97|127->97|136->106|141->111|141->111|149->119|152->122|152->122|159->129|159->129|160->130|160->130|160->130|160->130|160->130|160->130|161->131|164->134|164->134|172->142|177->147|177->147|185->155|185->155|188->158|188->158|188->158|188->158|188->158|188->158|188->158|188->158|189->159|189->159|189->159|191->161|194->164|194->164|203->173|203->173|206->176|206->176|206->176|206->176|206->176|206->176|206->176|206->176|209->179|212->182|212->182|220->190|225->195|225->195|235->205|235->205|237->207|237->207|237->207|237->207|237->207|237->207|237->207|237->207|238->208|238->208|238->208|240->210|243->213|243->213|249->219|249->219|250->220|250->220|250->220|250->220|250->220|250->220|251->221|255->225|255->225|255->225|259->229|259->229|265->235|268->238|268->238|273->243|276->246|276->246|286->256|291->261|291->261|296->266|299->269|299->269|304->274|311->281|311->281|311->281|312->282|312->282|312->282|315->285|346->316|346->316|347->317|347->317|347->317|351->321|351->321|351->321|354->324|354->324|355->325|355->325|355->325|355->325|355->325|355->325|355->325|355->325|356->326|356->326|379->349|379->349|385->355|385->355|388->358|388->358|390->360|390->360|394->364|394->364|396->366|396->366|397->367|397->367|399->369|399->369|400->370|400->370|403->373|403->373|404->374|404->374|405->375|405->375|406->376|406->376|408->378|408->378|422->392|422->392|422->392|422->392|426->396|426->396|427->397|427->397|430->400|430->400|432->402|432->402|433->403|433->403|437->407|437->407|443->413|443->413|449->419|449->419|449->419|449->419|451->421|451->421|453->423|453->423|453->423|453->423|457->427|457->427|458->428|458->428|460->430|460->430|464->434|464->434|466->436|466->436|472->442|472->442|474->444|474->444|475->445|475->445|477->447|477->447|480->450|480->450|482->452|482->452|488->458|488->458|490->460|490->460|491->461|491->461|493->463|493->463|500->470|500->470|505->475|505->475|505->475|505->475|506->476|506->476|512->482|512->482|516->486|516->486|518->488|518->488|519->489|519->489|521->491|521->491|522->492|522->492|525->495|525->495|526->496|526->496|527->497|527->497|528->498|528->498|529->499|529->499|532->502|532->502|533->503|533->503|534->504|534->504|546->516|546->516|546->516|546->516|546->516|547->517|547->517|551->521|551->521|551->521|551->521|553->523|553->523|554->524|554->524|555->525|555->525|555->525|555->525|559->529|559->529|560->530|560->530|561->531|561->531|563->533|563->533|565->535|565->535|567->537|567->537|568->538|568->538|571->541|571->541|573->543|573->543|574->544|574->544|576->546|576->546|577->547|577->547|579->549|579->549|580->550|580->550|582->552|582->552|583->553|583->553|585->555|585->555|586->556|586->556|587->557|587->557|593->563
                    -- GENERATED --
                */
            