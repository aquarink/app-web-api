$ =>
	$("input.datepicker").each (index, el) ->
		$(el).datepicker()
	if $(".datepicker").length isnt 0
		$(".datepicker").datepicker()
	
	$('form select[name="country.code"]').each (index, el) ->
		$(el).chainSelect 'select[name="city.id"]', '/helpers/cities.json'
	
	# User Managements
	$detailContainer = $("#detail-container").first()
	$(".container-users").each (index, el) ->
		$(el).find('.carousel').each (i, el) ->
			$(el).dynamicCarousel()
			$(el).on 'ajax:loaded', (e, data) ->
				$(el).find(".table-users a.btn-detail").on 'click', (e) ->
					e.preventDefault()
					$.ajax
						url: $(this).attr('href')
						success: (data) ->
							$detailContainer.html data

				# Ban/Unban
				$(el).find(".table-users a.btn-op-status").click (e, el) ->
					e.preventDefault()
					buttonEl = $(e.currentTarget)
					url = buttonEl.attr 'href'
					
					modalEl = $('#modal-ban')
					formEl = modalEl.find('form')

					isBan = buttonEl.hasClass 'btn-op-ban'
					if isBan
						modalEl.find('p.ban').removeClass 'hide'
						modalEl.find('p.unban').addClass 'hide'
					else
						modalEl.find('p.unban').removeClass 'hide'
						modalEl.find('p.ban').addClass 'hide'
					
					formEl.attr 'action', url
					formEl.off('submit').off('submit').on 'submit', (e) ->
						$.rails.handleRemote(formEl)
						return false

					formEl.off('ajax:error').on 'ajax:error', (e, xhr, status, err) ->
						modalEl.find('.container-error').alert 'error', "Connection failure, please retry", 3000

					formEl.off('ajax:success').on 'ajax:success', (e, data) ->
						modalEl.modal 'hide'
						if data.error
							$.alert 'error', data.error, 3000
						else
							$.alert 'success', data.success, 3000
						buttonEl.fadeOut 'slow'
						formEl.find('input').val("")

					modalEl.modal
						show: true


				# Promote
				$(el).find(".table-users a.btn-op-promote").click (e, el) ->
					e.preventDefault()
					buttonEl = $(e.currentTarget)
					modalEl = $('#modal-promote')
					formEl = modalEl.find('form')
					formEl.attr 'action', buttonEl.attr('href')
					modalEl.modal
						show: true

				# Demote
				$(el).find(".table-users a.btn-op-demote").click (e, el) ->
					e.preventDefault()
					buttonEl = $(e.currentTarget)
					modalEl = $("#modal-demote")
					modalEl.modal
						show: true
					modalEl.load buttonEl.attr('href')

$.fn.dynamicCarousel = (params) ->
	$innerContent = $(this).find('.carousel-inner')

	$(this).carousel
		interval: false
	
	# Capture children for any a/button with slide operation
	loadSlideContent = (container, url) =>
		$(container).html '<div class="loading"></div>'
		$(container).load url, (data) =>
			initSlideContent(container)
			$(this).trigger 'ajax:loaded', (container)

	initSlideContent = (content) =>
		$(content).find("a.slide").each (i2, a) =>
			$(a).on 'click', (e) =>
				e.preventDefault()
				url = $(a).attr 'href'
				op = $(a).attr 'data-slide-operation'
				$newContainer = $('<div class="item"></div>')
				$innerContent.find('.item.active').nextAll().remove()
				if op == 'next'
					$innerContent.find('.item.active').after($newContainer)
				$(this).carousel(op)
				if url
					loadSlideContent($newContainer, url)
	$(this).find('.item.active').each (i, el) ->
		if $(el).attr('data-url')
			loadSlideContent el, $(el).attr('data-url')
		else
			initSlideContent(el)

