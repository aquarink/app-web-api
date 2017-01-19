(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    var $feeContainerEl, $formEl;
    $feeContainerEl = $(".fee-container-inner");
    $formEl = $("#fee-form-factory");
    $(".btn-add-fee").click(function(e) {
      var $newEl;
      e.preventDefault();
      $newEl = $formEl.clone();
      $newEl.find('form').validate();
      $newEl.fadeIn('slow');
      return $feeContainerEl.append($newEl);
    });
    $(".form-fee").on('submit', function(e) {
      console.log("attempt to submit");
      if (!$(this).valid()) {
        return false;
      }
      return true;
    });
    $(".form-fee").on('change', function(e) {
      console.log("Form change");
      return $(this).find("button[type=submit]").removeClass('disabled');
    });
    $(".form-fee").on('ajax:success', function(e, data) {
      var $anchor, $form, $inputId;
      console.log("Ajax success", e);
      $anchor = $(e.target);
      $form = $(e.currentTarget);
      $inputId = $form.find("input[name=id]");
      if ($anchor.hasClass('btn-delete')) {
        return $form.find('.alert-container').alert("success", "Fee has just been deleted", 3000, function() {
          return $form.fadeOut('slow');
        });
      } else {
        if (data.id) {
          $inputId.val(data.id).trigger('change');
        }
        $form.find('.alert-container').alert("success", "Fee has been updated", 3000);
        return $form.find('button[type=submit]').addClass('disabled');
      }
    });
    $(".form-fee").on('ajax:error', function(e, data) {
      var $form;
      $form = $(e.currentTarget);
      return $form.find('.alert-container').alert("error", "Fee couldn't be saved", 3000);
    });
    $("a.btn-delete").on('ajax:success', function(e, data) {
      return $("#alert-container").alert("success", "Fee has been deleted", 3000);
    });
    return $("a.btn-delete").on('ajax:error', function(err) {
      return $("#alert-container").alert("success", "Fee could not deleted", 3000);
    });
  }, this));
}).call(this);
