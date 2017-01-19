$ =>
	#$("input[name='creditLimit']").mask '000,000,000,000,000', reverse:true
	if $(".datepicker").length isnt 0
		$(".datepicker").datepicker()
	if $(".colorpicker").length isnt 0
		$(".colorpicker").colorpicker()
	
	# User Managements
	$(".container-corporates").each (index, el) ->
		$detailContainer = $("#detail-container").first()
		$(".table-corporates a.btn-detail").on 'click', (e) ->
			e.preventDefault()
			$.ajax
				url: $(this).attr('href')
				success: (data) ->
					$detailContainer.html data
	#$("select[name='country.code']").chainSelect "select[name='currency.code']", '/helpers/currencies.json'

	$("input[name='settlement.agreementDate'], input[name='settlement.agreementYear']").on 'change', (e) ->
		if $("input[name='settlement.agreementYear']").val().length > 0
			d = new Date($("input[name='settlement.agreementDate']").val())
			expireDate = new Date(d.getTime() + ((86400 * 1000 * 365)*Number($("input[name='settlement.agreementYear']").val())))
			remindDate = new Date(expireDate.getTime() - (86400 * 1000 * 90))
			$("input[name='settlement.agreementExpiredDate']").val(expireDate.getFullYear()+"-"+("0" + (expireDate.getMonth() + 1)).slice(-2)+"-"+("0" + expireDate.getDate()).slice(-2))
			$("input[name='settlement.agreementReminderDate']").val(remindDate.getFullYear()+"-"+("0" + (remindDate.getMonth() + 1)).slice(-2)+"-"+("0" + remindDate.getDate()).slice(-2))

