(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    $('form#forgetPassword-form').on('ajax:success', function(e, result) {
      return $(this).find('.alert-container').alert('success', result, 3000, function() {
        return $("#forgetPassword").modal('hide');
      });
    });
    $('form#forgetPassword-form').on('ajax:error', function(e, xhr, status, err) {
      return $(this).find('.alert-container').alert('error', err, 3000);
    });
    $("form#forgetPassword-form .close").bind("click", function(event) {
      $("#confirmForgetPassword").hide();
      return $("#formForgetPassword").show();
    });
    return $('form#resetPassword-formDIS').submit(function(e) {
      var password1, password2;
      if ($(this).valid()) {
        password1 = $(this).find('input[name=password]');
        password2 = $(this).find('input[name=password2]');
        if (password1.val() === password2.val()) {
          return true;
        } else {
          $("#alert-container").alert('error', "Your confirmation password is not match", 3000);
          return false;
        }
      } else {
        return false;
      }
    });
  }, this));
}).call(this);
