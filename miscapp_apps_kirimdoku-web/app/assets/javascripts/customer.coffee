$ =>
	$detailContainer = $("#customer-detail-container")
	$('a.btn-customer-detail')
		.on 'ajax:beforeSend', ->
			$detailContainer.loading()
		.on 'ajax:success', (e, data) ->
			$detailContainer.html data
		.on 'ajax:error', (e, err) ->
			$detailContainer.html e
