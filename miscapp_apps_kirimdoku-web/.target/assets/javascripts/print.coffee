$ ->
	$(".btn-print[data-target]").on 'click', (e) ->
		console.log "printing..."
		e.preventDefault()
		targetVal = $(this).attr 'data-target'
		console.log $(targetVal)
		$(targetVal).printOnWindow()


$.fn.printOnWindow = (params) ->
	w = window.open('','printwin','left=100,top=100,scrollbars=yes,width=1024,height=600')
	if not w
		console.log "Window popup is blocked"
		window.alert "You need to enable popup in your browser setting"
		return
	w.document.open()
	w.document.write '<link rel="stylesheet" href="/assets/stylesheets/bootstrap.min.css"/>'
	w.document.write '<link rel="stylesheet" media="screen" href="/assets/stylesheets/global.css"/>'
	w.document.write '<link rel="stylesheet" media="print" href="/assets/stylesheets/print.css"/>'
	w.document.write '<body style="height:700px;">'+$(this).html()+'</body>'
	w.document.close()
	
	setTimeout ( =>
	    w.print()
	  ), 600
	#w.close()



