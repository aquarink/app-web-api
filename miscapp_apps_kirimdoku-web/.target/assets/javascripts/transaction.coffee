$ =>
	$detailContainer = $("#transaction-detail-container")
	
	$('a.btn-transaction-detail')
		.on 'ajax:beforeSend', ->
			$detailContainer.loading()
		.on 'ajax:success', (e, data) ->
			$detailContainer.html data
		.on 'ajax:error', (e, err) ->
			$detailContainer.html e

	$('a.btn-transaction-unlock, a.btn-transaction-lock')
		.on 'ajax:success', (e, data) ->
			$detailContainer.html data
		.on 'ajax:error', (e, err) ->
			$detailContainer.alert 'error', err.statusText, 3000
	
	# Change/Refund/Unlock
	$("form.form-transaction-filter").on 'ajax:beforeSend', (e, data) ->
		$detailContainer.loading()
	$("form.form-transaction-filter").on 'ajax:error', (e, data) ->
		$detailContainer.html("").alert 'error', "Error retrieving from remittance engine"
	$("form.form-transaction-filter").on 'ajax:success', (e, data) ->
		$detailContainer.html data
		$detailContainer.find("#refund-form").formRefund()
		$detailContainer.find("#fullrefund-form").formFullRefund()
		$detailContainer.find("#change-form").formChange()
		$detailContainer.find("#unlock-form").formUnlock()
	
	$("#refund-form").formRefund()

$.fn.extend 
	formRefund: () ->
		inputAuth = $(this).find('input[name="auth1"]')
		$(this).on 'ajax:beforeSend', (e) ->
			if inputAuth.val().length is 0
				$(this).find("#refund-dialog").modal show: true
				return false
			else
				if $(this).valid()
					return true
			return false
		$(this).on 'ajax:error', (e, err) ->
			$(this).find('.container-info').alert 'error', err.responseText, 3000
		$(this).on 'ajax:success', (e, result) ->
			if result and result.status is 0
				$(this).find("[type='submit']").attr('disabled', true)
				#$(this).find(".modal-footer").remove()
				$(this).find('.container-info').alert 'success', result.message, 3000, =>
					window.location.replace $(this).data('url-receipt')
			else
				$(this).find('.container-info').alert 'error', result.message, 3000
		$(this).on 'ajax:complete', (e, result) ->
			inputAuth.val("")

	formFullRefund: () ->
		inputReason = $(this).find('textarea[name="refund_reason"]')
		$(this).on 'ajax:beforeSend', (e) ->
			if inputReason.val().length is 0
				$(this).find("#refund-dialog").modal show: true
				return false
			else
				if $(this).valid()
					return true
			return false
		$(this).on 'ajax:error', (e, err) ->
			$(this).find('.container-info').alert 'error', err.responseText, 3000
		$(this).on 'ajax:success', (e, result) ->
			if result and result.status is 0
				$(this).find("[type='submit']").attr('disabled', true)
				#$(this).find(".modal-footer").remove()
				$(this).find('.container-info').alert 'success', result.message, 3000, =>
					window.location.replace $(this).data('url-receipt')
			else
				$(this).find('.container-info').alert 'error', result.message, 3000
	
	formChange: () ->
		$(this).find(".form-change-customer").each ->
			$(this).customerForm()

		inputAuth = $(this).find('input[name="auth1"]')
		$(this).on 'ajax:beforeSend', (e) ->
			console.log "before send"
			if inputAuth.val().length is 0
				$(this).find("#change-dialog").modal show: true
				return false
			else
				if $(this).valid()
					return true
			return false
		$(this).on 'ajax:error', (e, err) ->
			$(this).find('.container-info').alert 'error', err.responseText, 3000
		$(this).on 'ajax:success', (e, result) ->
			if result and result.status is 0
				$(this).find("[type='submit']").attr('disabled', true)
				#$(this).find(".modal-footer").remove()
				$(this).find('.container-info').alert 'success', result.message, 3000, =>
					window.location.replace $(this).data('url-receipt')
			else
				$(this).find('.container-info').alert 'error', result.message, 3000
		$(this).on 'ajax:complete', (e, result) ->
			inputAuth.val("")
	
	formUnlock: () ->
		inputAuth = $(this).find('input[name="new_auth1"]')
		$(this).on 'ajax:beforeSend', (e) ->
			if inputAuth.val().length is 0
				$(this).find("#unlock-dialog").modal show: true
				return false
			else
				pinMatch = inputAuth.val() == $(this).find("#new_auth2").val()
				if $(this).valid() and pinMatch
					return true
				else if not pinMatch
					$(this).find('.container-info').alert 'error', "Your confirmation PIN is not match, please try again", 5000
					inputAuth.val("")
					$(this).find("#new_auth2").val("")
			return false
		$(this).on 'ajax:error', (e, err) ->
			$(this).find('.container-info').alert 'error', err.statusText, 3000
		$(this).on 'ajax:success', (e, result) ->
			if result and result.status is 0
				$(this).find("[type='submit']").attr('disabled', true)
				$(this).find(".modal-footer").remove()
				$(this).find('.container-info').alert 'success', result.message, 3000, =>
					$(this).find("#unlock-dialog").modal 'hide'
					$("form.form-transaction-filter").submit()
			else
				$(this).find('.container-info').alert 'error', result.message, 3000
		$(this).on 'ajax:complete', (e, result) ->
			inputAuth.val("")
			$(this).find("#new_auth2").val("")

						
