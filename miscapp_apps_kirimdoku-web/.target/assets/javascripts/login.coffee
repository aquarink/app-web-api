$ =>

	$('form#forgetPassword-form').on 'ajax:success', (e, result) ->
		$(this).find('.alert-container').alert 'success', result, 3000, ->
			$("#forgetPassword").modal 'hide'
	$('form#forgetPassword-form').on 'ajax:error', (e, xhr, status, err) ->
		$(this).find('.alert-container').alert 'error', err, 3000

	$("form#forgetPassword-form .close").bind "click", (event) ->
		$("#confirmForgetPassword").hide()
		$("#formForgetPassword").show()

	# Reset Password
	$('form#resetPassword-formDIS').submit (e) ->
		if $(this).valid()
			# Make sure password and password2 are match
			password1 = $(this).find('input[name=password]')
			password2 = $(this).find('input[name=password2]')
			if password1.val() == password2.val()
				return true
			else
				$("#alert-container").alert 'error', "Your confirmation password is not match", 3000
				return false
		else
			return false

