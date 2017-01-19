$ ->
	$('.graph').each (index, el) ->
		type = $(el).attr 'data-graphType'
		switch type
			when "performances"
				$.get '/dashboard/statistics/'+$(el).attr('data-userId')+'/performances.json', (data) ->
					buildGraphicPerformance(data)
			when "volumes"
				$.get '/dashboard/statistics/'+$(el).attr('data-userId')+'/volumes.json', (data) ->
					buildGraphicVolumes(data)

buildGraphicPerformance = (data) ->
	return if not data.cashIn.length
	cashIn = []
	cashOut = []

	for d in data.cashIn
		cashIn.push({x: d.x, y: d.y})
	for d in data.cashOut
		cashOut.push({x: d.x, y: d.y})

	dataNum = [
		{
			values: cashIn,
			key: 'Send Money',
			color: '#2ca02c'
		},
		{
			values: cashOut,
			key: 'Receive Money',
			color: '#ff7f0e'
		},
	]

	nv.addGraph () ->
		chart = nv.models.lineChart()
		chart.xAxis.axisLabel('Month').tickFormat (d) ->
			d3.time.format('%d-%b')(new Date(d))
		chart.yAxis.axisLabel('Frequency').tickFormat(d3.format('d'))
		d3.select('#chart1 svg').datum(dataNum).transition().duration(500).call(chart)
		$("#chart1 svg").off "mouseover"
		nv.utils.windowResize () ->
			d3.select('#chart1 svg').call(chart)
		return chart

buildGraphicVolumes = (data) ->
	return if not data.cashIn.length
	cashIn = []
	cashOut = []

	for d in data.cashIn
		cashIn.push({x: d.x, y: d.y})
	for d in data.cashOut
		cashOut.push({x: d.x, y: d.y})

	dataNum = [
		{
			values: cashIn,
			key: 'Send Money',
			color: '#2ca02c'
		},
		{
			values: cashOut,
			key: 'Receive Money',
			color: '#ff7f0e'
		},
	]

	nv.addGraph () ->
		chart = nv.models.lineChart()
		chart.xAxis.axisLabel('Month').tickFormat (d) ->
			d3.time.format('%d-%b')(new Date(d))
		#chart.yAxis.axisLabel('Amount').tickFormat(d3.format('d'))
		chart.yAxis.tickFormat(d3.format('d'))
		d3.select('#chart2 svg').datum(dataNum).transition().duration(500).call(chart)
		$("#chart2 svg").off "mouseover"
		nv.utils.windowResize () ->
			d3.select('#chart2 svg').call(chart)
		return chart
