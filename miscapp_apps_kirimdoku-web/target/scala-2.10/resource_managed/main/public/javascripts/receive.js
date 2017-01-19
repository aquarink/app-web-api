(function() {
  var oldUnused;
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
    $("form.form-transaction-filter").on('ajax:beforeSend', function(e, data) {
      return $detailContainer.loading();
    });
    $("form.form-transaction-filter").on('ajax:error', function(e, data) {
      return $detailContainer.html("").alert('error', "Error retrieving from remittance engine");
    });
    return $("form.form-transaction-filter").on('ajax:success', function(e, data) {
      $detailContainer.html(data);
      return $detailContainer.formReceive();
    });
  }, this));
  $.fn.formReceive = function() {
    $(this).find('form#receive-form').on('ajax:beforeSend', function(e) {
      var inputAuth, inputValidationId, inputVerify, loadingEl, refNum;
      console.log("beforeSend", e);
      inputVerify = $(this).find('input[name="verifyId"]');
      inputAuth = $(this).find('input[name="auth1"]');
      inputValidationId = $(this).find('input[name="validationId"]');
      refNum = $(this).find('#verifyRefNum');
      if ($(this).find('#receive-dialog-refnum').attr('class') === 'modal fade hide') {
        refNum.val('');
      }
      if (inputVerify.val().length === 0) {
        $.post($(this).data('url-verify'), $(this).serialize(), __bind(function(result) {
          if (result.length === 0) {
            inputVerify.val(1882721);
            $(this).find("#receive-dialog").modal({
              show: true
            });
            return $(this).find("#receive-dialog").on('hidden', function() {
              return inputVerify.val("");
            });
          } else {
            $("#verify-modal").find('.modal-body').html(result);
            return $("#verify-modal").modal({
              show: true
            });
          }
        }, this)).fail(function() {
          $("#verify-modal").find('.modal-body').html("Fail to communicate to server, please try again");
          return $("#verify-modal").modal({
            show: true
          });
        });
        return false;
      } else if (inputAuth.val().length === 0) {
        $(this).find("#receive-dialog").modal({
          show: true
        });
        refNum.focus();
        return false;
      } else if (inputValidationId.val().length === 0) {
        console.log("Validate first?");
        loadingEl = $(this).find('.container-info').loading();
        $.ajax({
          type: 'POST',
          url: $(this).attr('data-url-validate'),
          data: $(this).serialize(),
          success: __bind(function(result) {
            if (result.status === 0) {
              $(this).find('.container-info').alert('success', "Validation success, You may proceed");
              return inputValidationId.val(result.validation.validationId).trigger('change');
            } else {
              $(this).find('.container-info').alert('error', result.message, 3000);
              return inputAuth.val("");
            }
          }, this),
          error: __bind(function(err, msg) {
            inputAuth.val("");
            return $(this).find('.container-info').alert('error', "Validation failed: " + err.statusText, 3000);
          }, this),
          complete: function() {
            return loadingEl.find('.loading').remove();
          }
        });
        return false;
      } else if (refNum.val().length === 0) {
        $(this).find('.container-info').loading().find('.loading').remove();
        $(this).find('#receive-dialog-refnum').modal({
          show: true
        });
        $(this).find('#verifyRefNum').val('1234');
        return false;
      }
      if ($(this).valid()) {
        loadingEl = $(this).find('.container-info').loading();
        return true;
      }
      return false;
    });
    $(this).find('form#receive-form').on('ajax:error', function(e, err) {
      return $(this).find('.container-info').alert('error', err.statusText, 3000);
    });
    $(this).find('form#receive-form').on('ajax:success', function(e, result) {
      console.log("form receive success", result);
      if (result && result.status === 0) {
        console.log("redirect to receipt?");
        return window.location.replace($(this).data('url-receipt'));
      } else {
        $(this).find('.container-info').loading().find('.loading').remove();
        return $(this).find('.container-info').alert('error', result.message);
      }
    });
    return false;
  };
  oldUnused = __bind(function() {
    var containerEl, inputValidationId;
    $(".btn-back").click(__bind(function() {
      return window.history.back();
    }, this));
    $("#btn-process-cashout").click(function() {
      var customerId, formContainerEl;
      $(this).parent().hide();
      $("#process-container").removeClass('hidden').show('slide');
      formContainerEl = $('#customer-container');
      customerId = formContainerEl.attr('data-customerId');
      return formContainerEl.load('/send/customer/' + customerId + '/edit', function() {
        return $(this).find('form').submit(function(e) {
          e.preventDefault();
          if ($(this).valid()) {
            return $.ajax({
              type: 'PUT',
              url: $(this).attr('action'),
              data: $(this).serialize(),
              success: function(result) {
                if (result.status === 0) {
                  return formContainerEl.alert('success', "Customer data has been saved", 3000);
                } else {
                  return formContainerEl.alert('error', "Customer data not saved", 3000);
                }
              },
              error: function(err, msg) {
                return formContainerEl.alert('error', "Connection failure, please retry", 3000);
              }
            });
          }
        });
      });
    });
    containerEl = $("#process-container");
    inputValidationId = containerEl.find('input[name=validationId]');
    inputValidationId.change(function(e, d) {
      console.log("Validation id change");
      if ($(this).val()) {
        return $(containerEl).find("form .btn-next").removeAttr('disabled');
      }
    });
    containerEl.find("#form-receive-confirmation").bind('submit', function(e) {
      var inputAuth;
      if ($(this).valid()) {
        if (inputValidationId.val()) {
          $(this).find(".btn-next").attr('disabled', 'disabled');
          $(this).find(".btn-next").after($('<div class="loading">Processing...</div>'));
          return true;
        }
        inputAuth = $(this).find('input[name=pin]');
        $.ajax({
          type: 'POST',
          url: $(this).attr('data-validate-action'),
          data: $(this).serialize(),
          success: __bind(function(result) {
            if (result.status === 0) {
              $(this).find('.input').alert('success', "Validation success, You may proceed");
              return inputValidationId.val(result.validation.validationId).trigger('change');
            } else {
              $(this).find('.input').alert('error', result.message, 3000);
              return inputAuth.val("");
            }
          }, this),
          error: __bind(function(err, msg) {
            inputAuth.val("");
            return $(this).find('.input').alert('error', "Validation failed: " + err.statusText, 3000);
          }, this)
        });
        return false;
      } else {
        e.preventDefault();
        console.log("no valid");
        return false;
      }
    });
    return $("#form-receive-beneficiary").bind('submit', function(e) {
      e.preventDefault();
      if ($(this).valid()) {
        console.log("submitting to " + $(this).attr('action') + " with data " + $(this).serialize());
        return false;
      }
      return false;
    });
  }, this);
}).call(this);
