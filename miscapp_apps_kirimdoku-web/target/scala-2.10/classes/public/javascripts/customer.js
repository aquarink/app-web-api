(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    var $detailContainer;
    $detailContainer = $("#customer-detail-container");
    return $('a.btn-customer-detail').on('ajax:beforeSend', function() {
      return $detailContainer.loading();
    }).on('ajax:success', function(e, data) {
      return $detailContainer.html(data);
    }).on('ajax:error', function(e, err) {
      return $detailContainer.html(e);
    });
  }, this));
}).call(this);
