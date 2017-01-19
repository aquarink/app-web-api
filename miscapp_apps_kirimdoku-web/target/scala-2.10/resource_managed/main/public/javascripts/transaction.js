(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    var $detailContainer;
    $detailContainer = $("#transaction-detail-container");
    $('a.btn-transaction-detail').on('ajax:beforeSend', function() {
      return $detailContainer.loading();
    }).on('ajax:success', function(e, data) {
      return $detailContainer.html(data);
    }).on('ajax:error', function(e, err) {
      return $detailContainer.html(e);
    });
    $('a.btn-transaction-unlock, a.btn-transaction-lock').on('ajax:success', function(e, data) {
      return $detailContainer.html(data);
    }).on('ajax:error', function(e, err) {
      return $detailContainer.alert('error', err.statusText, 3000);
    });
    $("form.form-transaction-filter").on('ajax:beforeSend', function(e, data) {
      return $detailContainer.loading();
    });
    $("form.form-transaction-filter").on('ajax:error', function(e, data) {
      return $detailContainer.html("").alert('error', "Error retrieving from remittance engine");
    });
    $("form.form-transaction-filter").on('ajax:success', function(e, data) {
      $detailContainer.html(data);
      $detailContainer.find("#refund-form").formRefund();
      $detailContainer.find("#fullrefund-form").formFullRefund();
      $detailContainer.find("#change-form").formChange();
      return $detailContainer.find("#unlock-form").formUnlock();
    });
    return $("#refund-form").formRefund();
  }, this));
  $.fn.extend({
    formRefund: function() {
      var inputAuth;
      inputAuth = $(this).find('input[name="auth1"]');
      $(this).on('ajax:beforeSend', function(e) {
        if (inputAuth.val().length === 0) {
          $(this).find("#refund-dialog").modal({
            show: true
          });
          return false;
        } else {
          if ($(this).valid()) {
            return true;
          }
        }
        return false;
      });
      $(this).on('ajax:error', function(e, err) {
        return $(this).find('.container-info').alert('error', err.responseText, 3000);
      });
      $(this).on('ajax:success', function(e, result) {
        if (result && result.status === 0) {
          $(this).find("[type='submit']").attr('disabled', true);
          return $(this).find('.container-info').alert('success', result.message, 3000, __bind(function() {
            return window.location.replace($(this).data('url-receipt'));
          }, this));
        } else {
          return $(this).find('.container-info').alert('error', result.message, 3000);
        }
      });
      return $(this).on('ajax:complete', function(e, result) {
        return inputAuth.val("");
      });
    },
    formFullRefund: function() {
      var inputReason;
      inputReason = $(this).find('textarea[name="refund_reason"]');
      $(this).on('ajax:beforeSend', function(e) {
        if (inputReason.val().length === 0) {
          $(this).find("#refund-dialog").modal({
            show: true
          });
          return false;
        } else {
          if ($(this).valid()) {
            return true;
          }
        }
        return false;
      });
      $(this).on('ajax:error', function(e, err) {
        return $(this).find('.container-info').alert('error', err.responseText, 3000);
      });
      return $(this).on('ajax:success', function(e, result) {
        if (result && result.status === 0) {
          $(this).find("[type='submit']").attr('disabled', true);
          return $(this).find('.container-info').alert('success', result.message, 3000, __bind(function() {
            return window.location.replace($(this).data('url-receipt'));
          }, this));
        } else {
          return $(this).find('.container-info').alert('error', result.message, 3000);
        }
      });
    },
    formChange: function() {
      var inputAuth;
      $(this).find(".form-change-customer").each(function() {
        return $(this).customerForm();
      });
      inputAuth = $(this).find('input[name="auth1"]');
      $(this).on('ajax:beforeSend', function(e) {
        console.log("before send");
        if (inputAuth.val().length === 0) {
          $(this).find("#change-dialog").modal({
            show: true
          });
          return false;
        } else {
          if ($(this).valid()) {
            return true;
          }
        }
        return false;
      });
      $(this).on('ajax:error', function(e, err) {
        return $(this).find('.container-info').alert('error', err.responseText, 3000);
      });
      $(this).on('ajax:success', function(e, result) {
        if (result && result.status === 0) {
          $(this).find("[type='submit']").attr('disabled', true);
          return $(this).find('.container-info').alert('success', result.message, 3000, __bind(function() {
            return window.location.replace($(this).data('url-receipt'));
          }, this));
        } else {
          return $(this).find('.container-info').alert('error', result.message, 3000);
        }
      });
      return $(this).on('ajax:complete', function(e, result) {
        return inputAuth.val("");
      });
    },
    formUnlock: function() {
      var inputAuth;
      inputAuth = $(this).find('input[name="new_auth1"]');
      $(this).on('ajax:beforeSend', function(e) {
        var pinMatch;
        if (inputAuth.val().length === 0) {
          $(this).find("#unlock-dialog").modal({
            show: true
          });
          return false;
        } else {
          pinMatch = inputAuth.val() === $(this).find("#new_auth2").val();
          if ($(this).valid() && pinMatch) {
            return true;
          } else if (!pinMatch) {
            $(this).find('.container-info').alert('error', "Your confirmation PIN is not match, please try again", 5000);
            inputAuth.val("");
            $(this).find("#new_auth2").val("");
          }
        }
        return false;
      });
      $(this).on('ajax:error', function(e, err) {
        return $(this).find('.container-info').alert('error', err.statusText, 3000);
      });
      $(this).on('ajax:success', function(e, result) {
        if (result && result.status === 0) {
          $(this).find("[type='submit']").attr('disabled', true);
          $(this).find(".modal-footer").remove();
          return $(this).find('.container-info').alert('success', result.message, 3000, __bind(function() {
            $(this).find("#unlock-dialog").modal('hide');
            return $("form.form-transaction-filter").submit();
          }, this));
        } else {
          return $(this).find('.container-info').alert('error', result.message, 3000);
        }
      });
      return $(this).on('ajax:complete', function(e, result) {
        inputAuth.val("");
        return $(this).find("#new_auth2").val("");
      });
    }
  });
}).call(this);
