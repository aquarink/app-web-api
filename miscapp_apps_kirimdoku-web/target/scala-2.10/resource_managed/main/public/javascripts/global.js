(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $.ajaxSetup({
    timeout: 15000
  });
  $(__bind(function() {
    $("form").each(function(index, form) {
      $(form).validate();
      if (Modernizr && !Modernizr.inputtypes.date && $.fn.datepicker) {
        return $(form).find('input[type=date]').datepicker();
      }
      /*
      		$(form).find('input.phone[type=text]').mask('(+99) 999-9999?9999')
      		$(form).find('input.phone[type=text]').mask('(+99) 999-9999?9999')
      		$(form).find('input.percentage[type=text]').mask('~?~~~', {placeholder: " "})
      		$(form).find('input.rate[type=text]').mask('~?~~~~~~~~~~~~~~~~~~~~', {placeholder:" "})*/
    });
    $("form.form-ajax[data-remote='true']").formAjax();
    $(".btn-back").click(function(e) {
      return window.history.back();
    });
    $.extend($.inputmask.defaults.definitions, {
      '#': {
        validator: "[\+\-0-9]",
        cardinality: 1
      }
    });
    $.extend($.inputmask.defaults.aliases, {
      'money': {
        alias: 'decimal',
        repeat: 20,
        radixPoint: ".",
        autoGroup: true,
        groupSeparator: ",",
        groupSize: 3,
        digits: 6
      }
    });
    $("input[type=tel].phone, input[type=text].phone").inputmask("mask", {
      "mask": "(999) 999[-999999999]"
    });
    return $("input[type=text].amount").inputmask("money");
  }, this));
  $.fn.loading = function(message) {
    var $loadingEl;
    $loadingEl = $('<div class="loading">Loading...</div>');
    if (message != null) {
      $loadingEl.html(message);
    }
    return $(this).html($loadingEl);
  };
  $.fn.alert = function(className, message, timeout, cb) {
    var el;
    if (timeout == null) {
      timeout = 0;
    }
    if (cb == null) {
      cb = null;
    }
    el = $('<div class="alert alert-' + className + '"><button class="close" data-dismiss="alert">×</button><span></span></div>');
    el.find('span').html(message);
    $(this).append(el);
    if (timeout) {
      return setTimeout(function() {
        el.fadeOut('slow');
        if (cb) {
          return cb();
        }
      }, timeout);
    }
  };
  $.fn.alertOne = function(className, message, timeout, cb) {
    if (timeout == null) {
      timeout = 0;
    }
    if (cb == null) {
      cb = null;
    }
    $(this).find(".alert").remove();
    return $(this).alert(className, message, timeout, cb);
  };
  $.alert = function(className, message, timeout, cb) {
    if (timeout == null) {
      timeout = 0;
    }
    if (cb == null) {
      cb = null;
    }
    return $('#alert-container').first().alert(className, message, timeout, cb);
  };
  $.fn.formAjax = function(j) {
    $(this).on('ajax:before');
    $(this).on('ajax:error', __bind(function(xhr, status, error) {
      return $.alert('error', status.statusText + " " + status.responseText, 3000);
    }, this));
    return $(this).on('ajax:success', __bind(function(data, status, xhr) {
      return $.alert('success', status, 3000);
    }, this));
  };
}).call(this);
