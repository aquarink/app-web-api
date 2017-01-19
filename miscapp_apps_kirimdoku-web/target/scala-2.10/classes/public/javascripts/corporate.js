(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    if ($(".datepicker").length !== 0) {
      $(".datepicker").datepicker();
    }
    if ($(".colorpicker").length !== 0) {
      $(".colorpicker").colorpicker();
    }
    $(".container-corporates").each(function(index, el) {
      var $detailContainer;
      $detailContainer = $("#detail-container").first();
      return $(".table-corporates a.btn-detail").on('click', function(e) {
        e.preventDefault();
        return $.ajax({
          url: $(this).attr('href'),
          success: function(data) {
            return $detailContainer.html(data);
          }
        });
      });
    });
    return $("input[name='settlement.agreementDate'], input[name='settlement.agreementYear']").on('change', function(e) {
      var d, expireDate, remindDate;
      if ($("input[name='settlement.agreementYear']").val().length > 0) {
        d = new Date($("input[name='settlement.agreementDate']").val());
        expireDate = new Date(d.getTime() + ((86400 * 1000 * 365) * Number($("input[name='settlement.agreementYear']").val())));
        remindDate = new Date(expireDate.getTime() - (86400 * 1000 * 90));
        $("input[name='settlement.agreementExpiredDate']").val(expireDate.getFullYear() + "-" + ("0" + (expireDate.getMonth() + 1)).slice(-2) + "-" + ("0" + expireDate.getDate()).slice(-2));
        return $("input[name='settlement.agreementReminderDate']").val(remindDate.getFullYear() + "-" + ("0" + (remindDate.getMonth() + 1)).slice(-2) + "-" + ("0" + remindDate.getDate()).slice(-2));
      }
    });
  }, this));
}).call(this);
