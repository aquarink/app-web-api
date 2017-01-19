(function() {
  var buildGraphicPerformance, buildGraphicVolumes;
  $(function() {
    return $('.graph').each(function(index, el) {
      var type;
      type = $(el).attr('data-graphType');
      switch (type) {
        case "performances":
          return $.get('/dashboard/statistics/' + $(el).attr('data-userId') + '/performances.json', function(data) {
            return buildGraphicPerformance(data);
          });
        case "volumes":
          return $.get('/dashboard/statistics/' + $(el).attr('data-userId') + '/volumes.json', function(data) {
            return buildGraphicVolumes(data);
          });
      }
    });
  });
  buildGraphicPerformance = function(data) {
    var cashIn, cashOut, d, dataNum, _i, _j, _len, _len2, _ref, _ref2;
    if (!data.cashIn.length) {
      return;
    }
    cashIn = [];
    cashOut = [];
    _ref = data.cashIn;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      d = _ref[_i];
      cashIn.push({
        x: d.x,
        y: d.y
      });
    }
    _ref2 = data.cashOut;
    for (_j = 0, _len2 = _ref2.length; _j < _len2; _j++) {
      d = _ref2[_j];
      cashOut.push({
        x: d.x,
        y: d.y
      });
    }
    dataNum = [
      {
        values: cashIn,
        key: 'Send Money',
        color: '#2ca02c'
      }, {
        values: cashOut,
        key: 'Receive Money',
        color: '#ff7f0e'
      }
    ];
    return nv.addGraph(function() {
      var chart;
      chart = nv.models.lineChart();
      chart.xAxis.axisLabel('Month').tickFormat(function(d) {
        return d3.time.format('%d-%b')(new Date(d));
      });
      chart.yAxis.axisLabel('Frequency').tickFormat(d3.format('d'));
      d3.select('#chart1 svg').datum(dataNum).transition().duration(500).call(chart);
      $("#chart1 svg").off("mouseover");
      nv.utils.windowResize(function() {
        return d3.select('#chart1 svg').call(chart);
      });
      return chart;
    });
  };
  buildGraphicVolumes = function(data) {
    var cashIn, cashOut, d, dataNum, _i, _j, _len, _len2, _ref, _ref2;
    if (!data.cashIn.length) {
      return;
    }
    cashIn = [];
    cashOut = [];
    _ref = data.cashIn;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      d = _ref[_i];
      cashIn.push({
        x: d.x,
        y: d.y
      });
    }
    _ref2 = data.cashOut;
    for (_j = 0, _len2 = _ref2.length; _j < _len2; _j++) {
      d = _ref2[_j];
      cashOut.push({
        x: d.x,
        y: d.y
      });
    }
    dataNum = [
      {
        values: cashIn,
        key: 'Send Money',
        color: '#2ca02c'
      }, {
        values: cashOut,
        key: 'Receive Money',
        color: '#ff7f0e'
      }
    ];
    return nv.addGraph(function() {
      var chart;
      chart = nv.models.lineChart();
      chart.xAxis.axisLabel('Month').tickFormat(function(d) {
        return d3.time.format('%d-%b')(new Date(d));
      });
      chart.yAxis.tickFormat(d3.format('d'));
      d3.select('#chart2 svg').datum(dataNum).transition().duration(500).call(chart);
      $("#chart2 svg").off("mouseover");
      nv.utils.windowResize(function() {
        return d3.select('#chart2 svg').call(chart);
      });
      return chart;
    });
  };
}).call(this);
