$ =>
	$feeContainerEl = $(".fee-container-inner")
	$formEl = $("#fee-form-factory")

	$(".btn-add-fee").click (e) ->
		e.preventDefault()
		$newEl = $formEl.clone()
		$newEl.find('form').validate()
		#$newEl.removeClass 'hidden'
		$newEl.fadeIn 'slow'

		$feeContainerEl.append $newEl
	
	$(".form-fee").on 'submit', (e) ->
		console.log "attempt to submit"
		if not $(this).valid()
			return false
		return true

	$(".form-fee").on 'change', (e) ->
		console.log "Form change"
		$(this).find("button[type=submit]").removeClass 'disabled'

	$(".form-fee").on 'ajax:success', (e, data) ->
		console.log "Ajax success", e
		$anchor = $(e.target)
		$form = $(e.currentTarget)
		$inputId = $form.find("input[name=id]")

		if $anchor.hasClass 'btn-delete'
			$form.find('.alert-container').alert "success", "Fee has just been deleted", 3000, ->
				$form.fadeOut 'slow'
		else
			if data.id then	$inputId.val(data.id).trigger 'change'
			$form.find('.alert-container').alert "success", "Fee has been updated", 3000
			$form.find('button[type=submit]').addClass 'disabled'
	
	$(".form-fee").on 'ajax:error', (e, data) ->
		$form = $(e.currentTarget)
		$form.find('.alert-container').alert "error", "Fee couldn't be saved", 3000

	$("a.btn-delete").on 'ajax:success', (e, data) ->
		$("#alert-container").alert "success", "Fee has been deleted", 3000
	$("a.btn-delete").on 'ajax:error', (err) ->
		$("#alert-container").alert "success", "Fee could not deleted", 3000
