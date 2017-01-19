
$.fn.infoForm = () ->
	channelField = this.find("select[name='channel.code']")
	beneficiaryCountryField = this.find("select[name='beneficiaryCountry.code']")
	beneficiaryCurrencyField = this.find("select[name='beneficiaryCurrency.code']")
	sendAmountField = this.find("input[name='senderAmount']")
	beneficiaryAmountField = this.find("input[name='beneficiaryAmount']")
	inquiryIdField = this.find("input[name='idToken']")
	rateField = this.find("input[name='rate']")
	feeField = this.find("input[name='feesTotal']")
	additionalFeeField = this.find("input[name='additionalFee']");
	collectAmountField = this.find("input[name='collectAmount']");
	
	senderCountryCode = $('#sender_country_code');
	beneficiaryCountryCode = $('#beneficiary_country_code');
	beneficiaryAccountCity = $('#beneficiaryAccount_city');
	
	senderCountryCode.val('ID');
	beneficiaryCountryCode.val('ID');
	beneficiaryAccountCity.val('Jakarta');
	
	this.submitInquiry = (params, cb) =>
		this.updateInquiry(null)
		$.ajax
			type: 'GET'
			url: '/send/inquiry'
			data: $(this).serialize()
			error: (err, msg) =>
				cb(err)
			success: (result) =>
				cb(null, result)
	this.updateInquiry = (result) ->
		#console.log "Updating inquiry ", result
		if result and result.inquiry
			inquiryIdField.val result.inquiry.idToken
			rateField.val(Number(result.inquiry.forexReference.rate).toFixed(6)).trigger 'keyup'
			feeField.val result.inquiry.fund.fees.total
			feeField.inputmask("money")
			additionalFeeField.val result.inquiry.fund.fees.additionalFee
			additionalFeeField.inputmask("money")
			sendAmountField.val result.inquiry.fund.origin.amount
			#sendAmountField.data('mask').init()
			beneficiaryAmountField.val Number(result.inquiry.fund.destination.amount)
			#beneficiaryAmountField.data('mask').init()
			collectAmount = Number(result.inquiry.fund.origin.amount) + Number(result.inquiry.fund.fees.total)
			collectAmountField.val(collectAmount).trigger 'keyup'
			collectAmountField.inputmask("money")
			#collectAmountField.data('mask').init()
			
			$('#senderCountryName').html(result.inquiry.senderCountry.name)
			$('#beneficiaryCountryName').html(result.inquiry.beneficiaryCountry.name)
			$('#ratee').html(result.inquiry.forexReference.rate)
			$('#originAmount').html(result.inquiry.fund.origin.amount + ' ' + result.inquiry.fund.origin.currency)
			$('#destinationAmount').html(result.inquiry.fund.destination.amount + ' ' + result.inquiry.fund.destination.currency)
			$('#feeTotal').html(result.inquiry.fund.fees.total)
			$('#feeAdditional').html(result.inquiry.fund.fees.additionalFee)
			$('#amountToCollect').html(result.inquiry.fund.origin.amount + ' ' + result.inquiry.fund.origin.currency)
			$('#serviceLbl').html(result.inquiry.channel.name)
		else if result and result.message
			$(this).find('.container-info').alert 'error', result.message, 3000
			rateField.val ""
			feeField.val ""
	
	this.updateInquiry1 = (result) ->
		#console.log "Updating inquiry ", result
		if result and result.inquiry
			inquiryIdField.val result.inquiry.idToken
			rateField.val(Number(result.inquiry.forexReference.rate).toFixed(6)).trigger 'keyup'
			feeField.val result.inquiry.fund.fees.total
			feeField.inputmask("money")
			additionalFeeField.val result.inquiry.fund.fees.additionalFee
			additionalFeeField.inputmask("money")
			sendAmountField.val result.inquiry.fund.origin.amount
			#sendAmountField.data('mask').init()
			beneficiaryAmountField.val Number(result.inquiry.fund.destination.amount)
			#beneficiaryAmountField.data('mask').init()
			collectAmount = Number(result.inquiry.fund.origin.amount) + Number(result.inquiry.fund.fees.total)
			collectAmountField.val(collectAmount).trigger 'keyup'
			collectAmountField.inputmask("money")
			#collectAmountField.data('mask').init()
		else if result and result.message
			$(this).find('.container-info').alert 'error', result.message, 3000
			rateField.val ""
			feeField.val ""
	
	beneficiaryCountryField.on 'change', (e) =>
		if $('#channel_code').val() == '03'
			$('#beneficiaryCountry_code').val('CN')
	
	channelField.on 'change', (e) =>
		console.log "Change to ", $(e.target).val()
		if $(e.target).val() is '05' or $(e.target).val() is '06' or $(e.target).val() is '07'
			$('#row-ctc').hide()
			$('.form-cashin-customer-account').show()
			$('#receiver-bank-info').show()
			$('#beneficiaryCountry_code').removeAttr('readOnly')
			getByCountryBank($('#beneficiaryCountry_code').val())
			$('.form-cashin-voucher-info').hide()
			$('#btn-send-primary').removeAttr('disabled')
			$('#voucherCode').removeClass('required')
			$('.form-cashin-wallet-info').hide()
			$('#beneficiaryWalletId').removeClass('required')
		else if $(e.target).val() is '03'
			$('#row-ctc').show()
			$('.form-cashin-customer-account').show()
			$('#receiver-bank-info').hide()
			$('#beneficiaryCountry_code').val('CN')
			getByCountryBank($('#beneficiaryCountry_code').val())
			$('#beneficiaryCountry_code').attr('readonly', true)
			$('.form-cashin-voucher-info').hide()
			$('#btn-send-primary').removeAttr('disabled')
			$('#voucherCode').removeClass('required')
			$('.form-cashin-wallet-info').hide()
			$('#beneficiaryWalletId').removeClass('required')
		else if $(e.target).val() is '21'
			$('#row-ctc').hide()
			$('.form-cashin-customer-account').hide()
			$('#receiver-bank-info').hide()
			$('#beneficiaryCountry_code').removeAttr('readOnly')
			getByCountryBank($('#beneficiaryCountry_code').val())
			$('.form-cashin-voucher-info').show()
			$('#btn-send-primary').prop("disabled", true)
			$('#voucherCode').addClass('required')
			$('.form-cashin-wallet-info').hide()
			$('#beneficiaryWalletId').removeClass('required')
		else if $(e.target).val() is '22'
			$('#row-ctc').hide()
			$('.form-cashin-customer-account').show()
			$('#receiver-bank-info').show()
			$('#beneficiaryCountry_code').removeAttr('readOnly')
			getByCountryBank($('#beneficiaryCountry_code').val())
			$('.form-cashin-voucher-info').show()
			$('#btn-send-primary').prop("disabled", true)
			$('#voucherCode').addClass('required')
			$('.form-cashin-wallet-info').hide()
			$('#beneficiaryWalletId').removeClass('required')
		else if $(e.target).val() is '04'
			$('#row-ctc').hide()
			$('#receiver-bank-info').show()
			$('.form-cashin-customer-account').hide()
			$('#beneficiaryCountry_code').removeAttr('readOnly')
			getByCountryBank($('#beneficiaryCountry_code').val())
			$('.form-cashin-voucher-info').hide()
			$('#btn-send-primary').removeAttr('disabled')
			$('#voucherCode').removeClass('required')
			$('.form-cashin-wallet-info').show()
			$('#beneficiaryWalletId').addClass('required')
		else
			$('#row-ctc').hide()
			$('#receiver-bank-info').show()
			$('.form-cashin-customer-account').hide()
			$('#beneficiaryCountry_code').removeAttr('readOnly')
			getByCountryBank($('#beneficiaryCountry_code').val())
			$('.form-cashin-voucher-info').hide()
			$('#btn-send-primary').removeAttr('disabled')
			$('#voucherCode').removeClass('required')
			$('.form-cashin-wallet-info').hide()
			$('#beneficiaryWalletId').removeClass('required')
			
	getByCountryBank = ( countryBank ) ->
		console.log this.value
		searchVal = 'countryBank='+countryBank+'&channel.code='+$('#channel_code').val()
		$('#groupBank').html('')
		$('#provinceBank').html('')
		$('#cityBank').html('')
		$('#beneficiaryAccount_bank_code').html('')
		$('#provinceBank').append($('<option>').text('-- Choose Province --').attr('value',''))
		$('#cityBank').append($('<option>').text('-- Choose City --').attr('value', ''))
		$("input[name='beneficiaryAccount.bank.code']").val('')
		$('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''))
		$('#groupBank').append($('<option>').text('-- Choose Group Bank --').attr('value', ''))
		if (this.value != '')
			$.ajax
				type: 'POST'
				url: '/getcountrybank'
				data: searchVal
				dataType: 'json'
				error: (err, msg) =>
					$('#groupBank').removeClass('ui-autocomplete-loading')
				beforeSend: ( xhr ) =>
					$('#groupBank').addClass('ui-autocomplete-loading')
				success: (result) =>
					$('#groupBank').removeClass('ui-autocomplete-loading')
					defaultVal = ''
					$.each(result, (key, value) ->
					 	$('#groupBank').append($('<option>').text(value).attr('value', key))
					 	defaultVal = key
					)
					if defaultVal is 'default'
						$("#groupBank").val(defaultVal)
						$("#groupBank").change()
					return true;
						
		return true
	
	this.find('select[name="senderCountry.code"]').chainSelect 'select[name="senderCurrency.code"]', '/helpers/currencies.json'
	this.find('input[name="sendType"]').on 'change', (e) =>
		$(this).find('input[name="senderAmount"], input[name="beneficiaryAmount"]').each (i, el) ->
			if $(el).attr('name') is $(e.target).val()
				$(el).removeAttr('readOnly').addClass('required')
			else
				$(el).attr('readOnly', true).removeClass('required')
	this.find('input[name="sendType"]').trigger 'change' if not this.find('input[name="sendType"]').attr('disabled')

	beneficiaryCountryField.chainSelect 'select[name="beneficiaryCurrency.code"]', '/helpers/currencies.json', after: (target) =>
		getByCountryBank($('#beneficiaryCountry_code').val())
		$(this).find('input[name="sendType"], input[name="senderAmount"]').each (i, el) ->
			$(el).removeAttr 'readOnly'
			$(el).removeAttr 'disabled'
		this.find('input[name="sendType"]:checked').trigger 'change'
		this.find('#beneficiaryAmount_field .currency-code').html $(target).val()
		$("#beneficiaryCity").typeahead('destroy')
		$("#beneficiaryCity").typeahead
			hint: true
			highlight: true
			minLength: 2
			remote: '/helpers/cities.json?country.code='+beneficiaryCountryField.val()+'&q=%QUERY'
	
	beneficiaryCountryField.trigger 'change' if beneficiaryCountryField.val()
	
	#sendAmountField.mask '000,000,000,000,000.00', reverse:true
	#beneficiaryAmountField.mask '000,000,000,000,000.00', reverse:true
	
	#rateField.mask '000,000,000,000,000.00', reverse:true
	#collectAmountField.mask '000,000,000,000,000.00', reverse:true

	#sendAmountField.inputmask("decimal", { radixPoint: ".", autoGroup: true, groupSeparator: ",", groupSize: 3, digits: 6 })
	
	actUpdate = ''

	sendAmountField.on 'blur', (e) =>
		# if($(e.currentTarget).val().length<=0) then return
		this.submitInquiry {}, (err, result) =>
			if err
				console.log "submit inq err", err
				console.log "submit inq res", result
				$(this).find('.container-info').alert 'error', err, 5000
			else
				if actUpdate == ''
					this.updateInquiry result
				else 
					this.updateInquiry1 result
				actUpdate = ''
	beneficiaryAmountField.on 'change', (e) ->
		actUpdate = 'beneficiaryAmountField'
		sendAmountField.trigger 'blur'
		
		# rateAmount = Number rateField.val()
		# beneficiaryAmount = Number $(this).val().replace(/[a-zA-Z,+]/g, '')
		# sendAmount = beneficiaryAmount / rateAmount
		# sendAmountField.val(sendAmount).trigger 'change'

	this.isValid = ->
		return inquiryIdField.val().length>0

	return this

$.fn.customerForm = () ->
	prefix = $(this).attr('data-prefix')
	tokenField = this.find("input[name='"+prefix+".idToken']")

	this.checkCustomer = (params, cb) ->
		$.ajax
			type: 'GET'
			url: '/send/customer-lookupRelate'
			data: $(this).serialize()
			error: (err, msg) =>
				cb(err, msg)
			success: (result) =>
				cb(null, result)
	
	this.handleCheckCustomer = (err, result) =>
		if err
			if err.status is not 404
				$(this).find('.container-info').alert 'error', err, 5000
		else if result.status is 0
		
			Object.keys(result.customer).forEach (k) =>
				v = result.customer[k]
				if v not instanceof Object
					field = $(this).find("[name='"+prefix+"."+k+"']")
					field.val result.customer[k] if field
			field = $(this).find("[name='"+prefix+".country.code']")
			field.val result.customer.country.code
			field = $(this).find("[name='"+prefix+".personalIdCountry.code']")
			if result.customer.personalIdCountry != null
				field.val result.customer.personalIdCountry.code
			if prefix is 'sender'
				if result.customer.lastName is ''
					$('#sender_lastName').val($('#sender_firstName').val())
				$('#sennameLbl').html(result.customer.firstName + ' ' + result.customer.lastName)
				$('#senpNameLbl').html(result.customer.personalId)
				$('#senpNumberLbl').html(result.customer.phoneNumber)
				
				result.customerReceiver.forEach (key,value) ->
			  		$('#beneficiary_idToken').append('<option value= ' + key.idToken + '>' + key.firstName + ' ' + key.lastName + '</option>')
			if prefix is 'beneficiary'
				if result.customer.inputMode != null
					if result.customer.inputMode is 'TCODE'
						$('#inputModeTCODE').prop("checked",true)
						$('#beneficiary_tcode').removeAttr('readOnly')
						$('#beneficiary_pinyin').attr('readOnly', true)
						$('#inputModePINYIN').removeAttr("checked")
					else if result.customer.inputMode is 'PINYIN'
						$('#inputModePINYIN').prop("checked",true)
						$('#inputModeTCODE').removeAttr("checked")
						$('#beneficiary_pinyin').removeAttr('readOnly')
						$('#beneficiary_tcode').attr('readOnly', true)
					$('#beneficiary_lastName').val($('#beneficiary_firstName').val())
					
				$('#recnameLbl').html(result.customer.firstName + ' ' + result.customer.lastName)
				$('#recpNameLbl').html(result.customer.personalId)
				$('#recpNumberLbl').html(result.customer.phoneNumber)
			return true
		else
			$(this).find('.container-info').alert 'error', result.message, 5000

	this.handleUpdateCustomer = (err, result) =>
		if err
			if err.status is not 404
				$(this).find('.container-info').alert 'error', err, 5000
		else if result.status is 0
			idTokenField = $(this).find("[name='"+prefix+".idToken']")
			idTokenField.val result.customer.idToken
		else
			$(this).find('.container-info').alert 'error', result.message, 5000

	tokenField.on 'change', (e) =>
		if not $(e.currentTarget).val().length then return
		this.checkCustomer {}, this.handleCheckCustomer
	this.find('input.required,select.required').on 'change', (e) =>
		triggerLookup = true
		this.find('input.required,select.required').each (k,f) ->
			triggerLookup = false if not $(f).val().length
		if triggerLookup
			this.checkCustomer {}, this.handleUpdateCustomer

$.fn.bankForm = () ->
	bankCodeField = this.find("input[name='beneficiaryAccount.bank.code']")
	bankCityField = this.find("input[name='beneficiaryAccount.city']")
	this.find("select[name='beneficiaryAccount.bank.code']").on 'change', (e) =>
		bankCode = $(e.currentTarget).val()
		$.get "/helpers/banks/"+bankCode+".json", (data) ->
			bankCodeField.val data.code
			if data.city != null && data.city != ""
				bankCityField.val data.city
			else 
				bankCityField.val "Jakarta"

$ ->
	infoForm = $("form#form-cashin .form-cashin-info").infoForm()
	$("form#form-cashin .form-cashin-customer").each ->
		$(this).customerForm()
	$("form#form-cashin .form-cashin-customer-account").each ->
		$(this).bankForm()

	$("form#form-cashin").submit (e) ->
		if $(this).data('verified') then return true
		if infoForm.isValid()
			if $(this).valid()
				$.post $(this).data('url-verify'), $(this).serialize(), (result) =>
					if result.length is 0
						$(this).data('verified', true)
						$(this).submit()
					else
						$("#sendModal").find('.modal-body').html(result)
						$("#sendModal").modal show: true
				.fail ->
					$("#sendModal").find('.modal-body').html("Fail to communicate to server, please try again")
					$("#sendModal").modal show: true
		return false

	$('#btn-cek-voucher').on 'click', (e) =>
		voucherCode = $('#voucherCode')
		channelCode = $('#channel_code')
		if (voucherCode.val() != '' and voucherCode.val().trim().length is 6 and channelCode.val() != '' and (channelCode.val() is '21' or channelCode.val() is '22'))
			$.ajax
				type: 'POST'
				url: '/validatevoucher'
				data: 'vouchercode='+voucherCode.val()+'&channelcode='+channelCode.val()
				dataType: 'html'
				error: (err, msg) =>
					$('#btn-send-primary').prop("disabled", true)
				beforeSend: ( xhr ) =>
					$('#btn-send-primary').prop("disabled", true)
				success: (result) =>
					console.log result
					if result is 'CONTINUE'
						$('#btn-send-primary').removeAttr('disabled')
						$(this).find('.voucher-info').alert 'success', 'Voucher Valid, You May Proceed', 2000
					else
						$('#btn-send-primary').prop("disabled", true)
						$(this).find('.voucher-info').alert 'error', 'Invalid Voucher Code', 1000
					return true;
		else
			$('#btn-send-primary').prop("disabled", true)

	$('#btn-send-primary').on 'click', (e) =>
		if $('#channel_code').val() == '21' or $('#channel_code').val() == '22'
			if $('#voucherCode').val().length is 6
				return true
			else
				$('#voucherCode').focus()
				return false
	
	$("#voucherCode").on 'change', (e) ->
		$('#btn-send-primary').prop("disabled", true)
		
	$('#inputModeTCODE').on 'change', (e) =>
		$('#beneficiary_tcode').removeAttr('readOnly')
		$('#beneficiary_pinyin').attr('readOnly', true)
		$("#beneficiary_firstName").val("")
		$("#beneficiary_lastName").val("")
		$("#beneficiary_tcode").val("")
		$("#beneficiary_pinyin").val("")
		
	$('#inputModePINYIN').on 'change', (e) =>
		$('#beneficiary_pinyin').removeAttr('readOnly')
		$('#beneficiary_tcode').attr('readOnly', true)				
		$("#beneficiary_firstName").val("")
		$("#beneficiary_lastName").val("")
		$("#beneficiary_tcode").val("")
		$("#beneficiary_pinyin").val("")
		
	split = ( val ) ->
		return val.split( /,\s*/ )
	
	splitpy = ( val ) ->
		return val.split(';')
	
	splitzh = ( val ) ->
		return val.split('')
	
	extractLast = ( term ) ->
		return split( term ).pop()
	
	extractLastPy = ( term ) ->
		return splitpy( term ).pop()
	
	$('.checkTC').on 'click', (e) =>
		dataLookups = splitpy($('#beneficiary_tcode').val())
		
		while ( dataLookups[dataLookups.length-1] == '') 
		    dataLookups.pop()
		
		dataLookups.push('')
		dataLookup = dataLookups.join(',')
		dataRequest = dataLookups.join(';')
		$('#beneficiary_tcode').val(dataRequest)
		dataLast = extractLastPy($('#beneficiary_tcode').val()).trim()
		if (dataLookup != '')
			$('#beneficiary_firstName').val('')
			$("#beneficiary_lastName").val('')
			$("#beneficiary_pinyin").val('')
			dataCheck = 'codeVal='+dataLookup
			$.ajax
				type: 'POST'
				url: '/translate/tcode'
				data: dataCheck
				dataType: 'xml'
				error: (err, msg) =>
					$('#pageCheckPinyin').val(msg)
					$('#beneficiary_tcode').removeClass('ui-autocomplete-loading')
				beforeSend: ( xhr ) =>
					$('#beneficiary_tcode').addClass('ui-autocomplete-loading')
				success: (result) =>
					dataResult = '';
					dataPinyin = '';
					dataTCode = '';
					data = $('result',result).map( ->
						
				        	label: $('tCode', this).text(),
				        	value: $('zhWord', this).text(),
				        	desc: $('pinyin', this).text()
				        
				    ).get()
				    
				    $.each( data, 
				    	( key, val ) ->
				    		dataResult = dataResult + val.value
				    		dataPinyin = dataPinyin + val.desc + ';'
				    		dataTCode = dataTCode + val.label + ';'
				    )
					
					$('#beneficiary_firstName').val(dataResult)
					$("#beneficiary_lastName").val(dataResult)
					$("#beneficiary_pinyin").val(dataPinyin)
					$('#beneficiary_tcode').val(dataTCode)
					$('#beneficiary_tcode').removeClass('ui-autocomplete-loading')
					return true
				
	$("#beneficiary_tcode").on 'keyup', (e) ->
		if (e.keyCode == 40 && e.shiftKey)
			$('.checkTC').click()
		else
			if($("#beneficiary_tcode").val() is "")
				$("#beneficiary_firstName").val("")
				$("#beneficiary_lastName").val("")
				$("#beneficiary_pinyin").val("")
			else
				terms1 = splitpy( $("#beneficiary_tcode").val() )
				terms2 = splitzh( $("#beneficiary_firstName").val() )
				terms3 = splitpy( $("#beneficiary_pinyin").val() )
				if terms2.length >= terms1.length && $("#beneficiary_tcode").attr('readOnly') != 'readonly'
					terms2.pop()
					terms3.pop()
					terms3.pop()
					$("#beneficiary_firstName").val(terms2.join(''))
					$("#beneficiary_lastName").val(terms2.join(''))
					terms3.push('')
					$("#beneficiary_pinyin").val(terms3.join(';'))
				#console.log 'terms1 : '+terms1
				#console.log 'terms2 : '+terms2
		return true
	
	$('.checkPinyin').on 'click', (e) =>
		dataLookup = extractLastPy($('#beneficiary_pinyin').val()).trim()
		if (dataLookup != '')
			dataCheck = 'codeVal='+dataLookup
			$.ajax
				type: 'POST'
				url: '/translate/pinyin'
				data: dataCheck
				dataType: 'xml'
				error: (err, msg) =>
					$('#pageCheckPinyin').val(msg)
					$('#beneficiary_pinyin').removeClass('ui-autocomplete-loading')
				beforeSend: ( xhr ) =>
					$('#beneficiary_pinyin').addClass('ui-autocomplete-loading')
				success: (result) =>
					data = $('result',result).map( ->
						
				        	label: $('pinyin', this).text(),
				        	value: $('zhWord', this).text(),
				        	desc: $('tCode', this).text()
				        
				    ).get()
					$('#beneficiary_pinyin').autocomplete(
						minLength: 0,
						source: data,
						focus: ( event, ui ) ->
							return false
						,
						select: ( event, ui ) ->
							console.log 'Value : ' + ui.item.value
							console.log 'Label : ' + ui.item.label
							console.log 'Desc  : ' + ui.item.desc
							
							terms = splitpy( this.value )
							terms.pop()
							terms.push( ui.item.label )
							terms.push('')
							this.value = terms.join(';')

							zhname = splitzh($('#beneficiary_firstName').val())
							zhname.push( ui.item.value )
							zhnamefull = zhname.join('')
							$('#beneficiary_firstName').val(zhnamefull)
							$("#beneficiary_lastName").val(zhnamefull)

							tcodeName = splitpy($('#beneficiary_tcode').val())
							if ($('#beneficiary_tcode').val().length == 0) 
								tcodeName.pop()
							else if ($('#beneficiary_tcode').val().substring($('#beneficiary_tcode').val().length-1,$('#beneficiary_tcode').val().length) == ';')
								tcodeName.pop()
							tcodeName.push( ui.item.desc )
							tcodeName.push('')
							tcodeNamefull = tcodeName.join(';')
							$('#beneficiary_tcode').val(tcodeNamefull)
							
							return false
					).autocomplete('instance')._renderItem = ( ul, item ) ->
						return $('<li>')
						.append('<a>' + item.label + ' ' + item.value + ' ' + item.desc + '</a>')
						.appendTo( ul )
					$('#beneficiary_pinyin').autocomplete('search',dataLookup)
					$('#beneficiary_pinyin').removeClass('ui-autocomplete-loading')
					return true
				
			
	$("#beneficiary_pinyin").on 'keyup', (e) ->
		if (e.keyCode == 40 && e.shiftKey)
			$('.checkPinyin').click()
		else
			if($("#beneficiary_pinyin").val() is "")
				$("#beneficiary_firstName").val("")
				$("#beneficiary_lastName").val("")
				$("#beneficiary_tcode").val("")
			else
				terms1 = splitpy( $("#beneficiary_pinyin").val() )
				terms2 = splitzh( $("#beneficiary_firstName").val() )
				terms3 = splitpy( $("#beneficiary_tcode").val() )
				if terms2.length >= terms1.length && $("#beneficiary_pinyin").attr('readOnly') != 'readonly'
					terms2.pop()
					terms3.pop()
					terms3.pop()
					$("#beneficiary_firstName").val(terms2.join(''))
					$("#beneficiary_lastName").val(terms2.join(''))
					terms3.push('')
					$("#beneficiary_tcode").val(terms3.join(';'))
				#console.log 'terms1 : '+terms1
				#console.log 'terms2 : '+terms2
		return true
		
	
	if $("input[name='channel.code']").val() is "02" or $("input[name='channel.code']").val() is "21"
		$("#send-confirmation-form .btn-forward").on 'click', (e) ->
			console.log "btn formward onclick", e
			$("#send-confirmation-dialog").modal show: true
			$("#send-confirmation-dialog input:first").focus()
			$("input[name='supervisor.password']").val("")
			return false
	else if $("input[name='channel.code']").val() is "05" or $("input[name='channel.code']").val() is "06" or $("input[name='channel.code']").val() is "07" or $("input[name='channel.code']").val() is "03" or $("input[name='channel.code']").val() is "22" or $("input[name='channel.code']").val() is "04" or $("input[name='channel.code']").val() is "10" 
		$(this).find("#auth1").val('1234')
		$(this).find("#auth2").val('1234')
		$("#send-confirmation-form .btn-forward").on 'click', (e) ->
			$("#send-confirmation-form").submit()
	
	$("#groupBank").on 'change', (e) ->
		console.log this.value
		searchVal = 'countryBank='+$('#beneficiaryCountry_code').val()+'&groupBank='+this.value
		$('#provinceBank').html('')
		$('#cityBank').html('')
		$('#beneficiaryAccount_bank_code').html('')
		$('#provinceBank').append($('<option>').text('-- Choose Province --').attr('value',''))
		$('#cityBank').append($('<option>').text('-- Choose City --').attr('value', ''))
		$("input[name='beneficiaryAccount.bank.code']").val('')
		$('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''))
		if (this.value != '')
			$.ajax
				type: 'POST'
				url: '/getprovincebank'
				data: searchVal
				dataType: 'json'
				error: (err, msg) =>
					$('#provinceBank').removeClass('ui-autocomplete-loading')
				beforeSend: ( xhr ) =>
					$('#provinceBank').addClass('ui-autocomplete-loading')
				success: (result) =>
					$('#provinceBank').removeClass('ui-autocomplete-loading')
					defaultVal = ''
					$.each(result, (key, value) ->
					 	$('#provinceBank').append($('<option>').text(value).attr('value', key))
					 	defaultVal = key
					)
					if defaultVal is 'default'
						$("#provinceBank").val(defaultVal)
						$("#provinceBank").change()
					return true;
						
		return true
	
	$("#provinceBank").on 'change', (e) ->
		console.log this.value
		searchVal = 'countryBank='+$('#beneficiaryCountry_code').val()+'&groupBank='+$('#groupBank').val()+'&provinceBank='+this.value
		$('#cityBank').html('')
		$('#beneficiaryAccount_bank_code').html('')
		$('#cityBank').append($('<option>').text('-- Choose City --').attr('value', ''))
		$("input[name='beneficiaryAccount.bank.code']").val('')
		$('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''))
		if (this.value != '')
			$.ajax
				type: 'POST'
				url: '/getcitybank'
				data: searchVal
				dataType: 'json'
				error: (err, msg) =>
					$('#cityBank').removeClass('ui-autocomplete-loading')
				beforeSend: ( xhr ) =>
					$('#cityBank').addClass('ui-autocomplete-loading')
				success: (result) =>
					$('#cityBank').removeClass('ui-autocomplete-loading')
					defaultVal = ''
					$.each(result, (key, value) ->
					 	$('#cityBank').append($('<option>').text(value).attr('value', key))
					 	defaultVal = key
					)
					if defaultVal is 'default'
						$("#cityBank").val(defaultVal)
						$("#cityBank").change()
					return true;
		return true
	
	$("#cityBank").on 'change', (e) ->
		console.log this.value
		searchVal = 'countryBank='+$('#beneficiaryCountry_code').val()+'&groupBank='+$('#groupBank').val()+'&provinceBank='+$('#provinceBank').val()+'&cityBank='+this.value
		$('#beneficiaryAccount_bank_code').html('')
		$("input[name='beneficiaryAccount.bank.code']").val('')
		$('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''))
		if (this.value != '')
			$.ajax
				type: 'POST'
				url: '/getbank'
				data: searchVal
				dataType: 'json'
				error: (err, msg) =>
					$('#beneficiaryAccount_bank_code').removeClass('ui-autocomplete-loading')
				beforeSend: ( xhr ) =>
					$('#beneficiaryAccount_bank_code').addClass('ui-autocomplete-loading')
				success: (result) =>
					$('#beneficiaryAccount_bank_code').removeClass('ui-autocomplete-loading')
					$.each(result, (key, value) ->
					 	$('#beneficiaryAccount_bank_code').append($('<option>').text(value).attr('value', key))
					)
					$('#beneficiaryAccount_bank_code option').eq(1).prop('selected', true);
					$('#beneficiaryAccount_bank_code').change()
		return true
	
	$("#send-confirmation-form").on 'submit', (e) ->
		pinFilled = $(this).find("#auth1").val()
		pinMatch = $(this).find("#auth1").val() == $(this).find("#auth2").val()
		authorizationFilled = $(this).find("input[name='supervisor.password']").val()

		if $(this).valid() and pinMatch
			# does it need authorization
			#if authorizationFilled and authorizationFilled.length>0
				$(this).find("button[type=submit]").attr 'disabled', 'disabled'
				return true
			#else
			#	$("#send-confirmation-dialog").modal 'hide'
			#	$("#send-authorization-dialog").modal show: true
			#	$("#send-authorization-dialog input[type=password]:first").focus()
			#	e.preventDefault()
			#	return false
		else if not pinMatch
			appendEl = $("#auth2_field")
			appendEl.alert 'error', "Your verification is not match, please try again!", 5000
			$("#auth1").val("")
			$("#auth2").val("")
			$("#auth1").focus()
		e.preventDefault()
		return false
