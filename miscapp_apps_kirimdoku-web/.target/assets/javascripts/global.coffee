# Override default settings
$.ajaxSetup
	timeout: 15000

$ =>
	$("form").each (index, form) ->
		$(form).validate()
		if (Modernizr and !Modernizr.inputtypes.date and $.fn.datepicker)
			$(form).find('input[type=date]').datepicker()
		#$.mask.definitions['~']='[0-9.\+]'
		###
		$(form).find('input.phone[type=text]').mask('(+99) 999-9999?9999')
		$(form).find('input.phone[type=text]').mask('(+99) 999-9999?9999')
		$(form).find('input.percentage[type=text]').mask('~?~~~', {placeholder: " "})
		$(form).find('input.rate[type=text]').mask('~?~~~~~~~~~~~~~~~~~~~~', {placeholder:" "})###
		#$('input[type="text"].amount, input[type="text"].money').mask('000,000,000,000,000.00', {reverse: true});
		#$('input.rate').mask('000,000,000,000,000.00', {reverse: true});
		#$("input[type=text].phone").mask("(~99) 999-9999?99999");
		#$("input[type=text].postcode").mask("9999?99");
		
	$("form.form-ajax[data-remote='true']").formAjax()

	$(".btn-back").click (e) ->
		window.history.back()
	
	$.extend $.inputmask.defaults.definitions,
		'#':
			validator: "[\+\-0-9]",
			cardinality: 1
	
	$.extend $.inputmask.defaults.aliases,
		'money':
			alias: 'decimal'
			repeat: 20, radixPoint: ".", autoGroup: true, groupSeparator: ",", groupSize: 3, digits: 6
	#$(":input").inputmask()
	#$("input[type=text].amount").inputmask("decimal", { radixPoint: ".", autoGroup: true, groupSeparator: ",", groupSize: 3 });

	$("input[type=tel].phone, input[type=text].phone").inputmask("mask", {"mask": "(999) 999[-999999999]"})
	$("input[type=text].amount").inputmask("money")
	

$.fn.loading = (message) ->
	$loadingEl = $('<div class="loading">Loading...</div>')
	if message? then $loadingEl.html message
	$(this).html $loadingEl

$.fn.alert = (className, message, timeout = 0, cb = null) ->
	el = $('<div class="alert alert-'+className+'"><button class="close" data-dismiss="alert">×</button><span></span></div>')
	el.find('span').html message
	$(this).append el
	if timeout
		setTimeout ->
			el.fadeOut 'slow'
			if cb then cb()
		, timeout
$.fn.alertOne = (className, message, timeout = 0, cb = null) ->
	$(this).find(".alert").remove()
	$(this).alert(className, message, timeout, cb)

$.alert = (className, message, timeout = 0, cb = null) ->
	$('#alert-container').first().alert className, message, timeout, cb

$.fn.formAjax = (j) ->
	$(this).on 'ajax:before'
	$(this).on 'ajax:error', (xhr, status, error) =>
		$.alert 'error', status.statusText+" "+status.responseText, 3000
	$(this).on 'ajax:success', (data, status, xhr) =>
		$.alert 'success', status, 3000
