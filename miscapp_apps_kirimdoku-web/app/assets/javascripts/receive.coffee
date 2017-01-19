$ =>
	$detailContainer = $("#transaction-detail-container")
	
	$('a.btn-transaction-detail')
		.on 'ajax:beforeSend', ->
			$detailContainer.loading()
		.on 'ajax:success', (e, data) ->
			$detailContainer.html data
		.on 'ajax:error', (e, err) ->
			$detailContainer.html e
	$("form.form-transaction-filter").on 'ajax:beforeSend', (e, data) ->
		$detailContainer.loading()
	$("form.form-transaction-filter").on 'ajax:error', (e, data) ->
		$detailContainer.html("").alert 'error', "Error retrieving from remittance engine"
	$("form.form-transaction-filter").on 'ajax:success', (e, data) ->
		$detailContainer.html data
		$detailContainer.formReceive()

$.fn.formReceive = () ->
	$(this).find('form#receive-form').on 'ajax:beforeSend', (e) ->
		console.log "beforeSend", e
		inputVerify = $(this).find('input[name="verifyId"]')
		inputAuth = $(this).find('input[name="auth1"]')
		inputValidationId = $(this).find('input[name="validationId"]')
		refNum = $(this).find('#verifyRefNum')
		if $(this).find('#receive-dialog-refnum').attr('class') is 'modal fade hide'
			refNum.val('')
		if inputVerify.val().length is 0
			$.post $(this).data('url-verify'), $(this).serialize(), (result) =>
				if result.length is 0
					inputVerify.val(1882721)
					$(this).find("#receive-dialog").modal show: true
					$(this).find("#receive-dialog").on 'hidden', -> inputVerify.val("")
				else
					$("#verify-modal").find('.modal-body').html(result)
					$("#verify-modal").modal show: true
			.fail ->
				$("#verify-modal").find('.modal-body').html("Fail to communicate to server, please try again")
				$("#verify-modal").modal show: true
			return false
		else if inputAuth.val().length is 0
			$(this).find("#receive-dialog").modal show: true
			refNum.focus()
			return false
		else if inputValidationId.val().length is 0
			console.log "Validate first?"
			loadingEl = $(this).find('.container-info').loading()
			$.ajax
				type: 'POST'
				url: $(this).attr('data-url-validate')
				data: $(this).serialize()
				success: (result) =>
					if (result.status == 0)
						$(this).find('.container-info').alert 'success', "Validation success, You may proceed"
						inputValidationId.val(result.validation.validationId).trigger 'change'
					else
						$(this).find('.container-info').alert 'error', result.message, 3000
						inputAuth.val("")
				error: (err, msg) =>
					inputAuth.val("")
					$(this).find('.container-info').alert 'error', "Validation failed: "+err.statusText, 3000
				complete: ->
					loadingEl.find('.loading').remove()
			return false
		else if refNum.val().length is 0
			$(this).find('.container-info').loading().find('.loading').remove()
			$(this).find('#receive-dialog-refnum').modal show: true
			$(this).find('#verifyRefNum').val('1234')
			return false
		if $(this).valid()
			loadingEl = $(this).find('.container-info').loading()
			return true
		return false
	$(this).find('form#receive-form').on 'ajax:error', (e, err) ->
		$(this).find('.container-info').alert 'error', err.statusText, 3000
	$(this).find('form#receive-form').on 'ajax:success', (e, result) ->
		console.log "form receive success", result
		if result and result.status is 0
			console.log "redirect to receipt?"
			window.location.replace $(this).data('url-receipt')
		else
			$(this).find('.container-info').loading().find('.loading').remove()
			$(this).find('.container-info').alert 'error', result.message
	return false


oldUnused = () =>
	$(".btn-back").click =>
		window.history.back()

	$("#btn-process-cashout").click ->
		$(this).parent().hide()
		$("#process-container").removeClass('hidden').show('slide')
		formContainerEl = $('#customer-container')
		customerId = formContainerEl.attr 'data-customerId'
		formContainerEl.load '/send/customer/'+customerId+'/edit', ->
			$(this).find('form').submit (e) ->
				e.preventDefault()
				if $(this).valid()
					$.ajax
						type: 'PUT'
						url: $(this).attr('action')
						data: $(this).serialize()
						success: (result) ->
							if (result.status == 0)
								formContainerEl.alert 'success', "Customer data has been saved", 3000
							else
								formContainerEl.alert 'error', "Customer data not saved", 3000
						error: (err, msg) ->
							formContainerEl.alert 'error', "Connection failure, please retry", 3000

	containerEl = $("#process-container")
	inputValidationId = containerEl.find('input[name=validationId]')
	inputValidationId.change (e, d) ->
		console.log "Validation id change"
		if $(this).val()
			$(containerEl).find("form .btn-next").removeAttr 'disabled'
	
	containerEl.find("#form-receive-confirmation").bind 'submit', (e) ->
		if $(this).valid()
			if inputValidationId.val()
				$(this).find(".btn-next").attr 'disabled', 'disabled'
				$(this).find(".btn-next").after $('<div class="loading">Processing...</div>')
				return true

			inputAuth = $(this).find('input[name=pin]')
			$.ajax
				type: 'POST'
				url: $(this).attr('data-validate-action')
				data: $(this).serialize()
				success: (result) =>
					if (result.status == 0)
						$(this).find('.input').alert 'success', "Validation success, You may proceed"
						inputValidationId.val(result.validation.validationId).trigger 'change'
					else
						$(this).find('.input').alert 'error', result.message, 3000
						inputAuth.val("")
				error: (err, msg) =>
					inputAuth.val("")
					$(this).find('.input').alert 'error', "Validation failed: "+err.statusText, 3000
			return false
		else
			e.preventDefault()
			console.log "no valid"
			return false

	$("#form-receive-beneficiary").bind 'submit', (e) ->
		e.preventDefault()
		if $(this).valid()
			console.log "submitting to "+$(this).attr('action')+" with data "+$(this).serialize()
			return false
		return false
