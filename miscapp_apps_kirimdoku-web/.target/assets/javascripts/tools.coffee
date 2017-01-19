@initCheckRateForm = (form) ->
	summaryEl = $("#summary")

	$(form).find('#senderCountry_code').chainSelect '#senderCurrency_code', '/helpers/currencies.json'
	$(form).find('#beneficiaryCountry_code').chainSelect '#beneficiaryCurrency_code', '/helpers/currencies.json'

	$(form).submit (e) ->
		e.preventDefault()

		if $(this).valid()
			btnEl = $(this).find("input[type=submit]")
			return if btnEl.attr('disabled')

			# btnEl.attr 'disabled', true
			summaryEl.html '<div class="loading"></div>'
			$.post $(this).attr('action'), $(this).serialize(), (response) ->
				summaryEl.html response
			.error (err) ->
				summaryEl.html("").alert 'error', "Error retrieving from remittance engine"



