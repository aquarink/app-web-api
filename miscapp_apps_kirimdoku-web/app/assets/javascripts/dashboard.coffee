$ ->
	initTopAgent()
	initNews()
	initStatistics()
	initForexs()

initTopAgent = () ->
	$("#topagents .carousel").bzCarousel()

initNews = () ->
	$("#news .carousel").bzCarousel()
	$(".news-content").each (i, el) ->
		$(el).on 'click', (e) ->
			e.preventDefault()
			url = $(el).find('a.muted').attr('href')
			$('#newsModal').modal('show').find('.news-content').load url
		$(el).hover (e, isFocus) ->
			$(el).find('.muted').fadeIn 'slow'
		, ->
			$(el).find('.muted').fadeOut 'slow'

initStatistics = () ->
	$('.dashboard-statistics').each ->
		userId = $(this).attr 'data-user'
		loadUrl = '/dashboard/statistics/'+userId
		$(this).load loadUrl

initForexs = () ->
	$('.dashboard-forex').each ->
		corporateCode = $(this).attr 'data-corporate'
		$(this).load '/forexs/'+corporateCode+'/table'

$.fn.bzCarousel = () ->
	$(this).carousel
		interval: false
	$(this).bind 'slid', (e, i) ->
		active  = $(e.target).find('.active')
		activeIndex  = $(active.parent().children()).index(active)
		nav  = $(e.target).find('ul.nav-indicator')
		oldSelected = nav.children('.selected')
		newSelected = $(nav.children().get(activeIndex))
		oldSelected.removeClass('selected')
		newSelected.addClass('selected')
