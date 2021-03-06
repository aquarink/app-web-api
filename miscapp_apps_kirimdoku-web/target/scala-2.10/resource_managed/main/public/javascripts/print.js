(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(function() {
    return $(".btn-print[data-target]").on('click', function(e) {
      var targetVal;
      console.log("printing...");
      e.preventDefault();
      targetVal = $(this).attr('data-target');
      console.log($(targetVal));
      return $(targetVal).printOnWindow();
    });
  });
  $.fn.printOnWindow = function(params) {
    var w;
    w = window.open('', 'printwin', 'left=100,top=100,scrollbars=yes,width=1024,height=600');
    if (!w) {
      console.log("Window popup is blocked");
      window.alert("You need to enable popup in your browser setting");
      return;
    }
    w.document.open();
    w.document.write('<link rel="stylesheet" href="/assets/stylesheets/bootstrap.min.css"/>');
    w.document.write('<link rel="stylesheet" media="screen" href="/assets/stylesheets/global.css"/>');
    w.document.write('<link rel="stylesheet" media="print" href="/assets/stylesheets/print.css"/>');
    w.document.write('<body style="height:700px;">' + $(this).html() + '</body>');
    w.document.close();
    return setTimeout((__bind(function() {
      return w.print();
    }, this)), 600);
  };
}).call(this);
