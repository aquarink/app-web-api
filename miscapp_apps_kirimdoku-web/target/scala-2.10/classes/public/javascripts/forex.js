(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    return $("form.form-forex").each(function(i, formEl) {
      formEl.updateRate = function() {
        var rate, spread;
        rate = Number($(formEl).find("input[name=rate]").val());
        spread = Number($(formEl).find("input[name=spread]").val());
        return $(formEl).find("input[name=finalRate]").val(rate - (rate * (spread / 100)));
      };
      $(formEl).find("input[name=rate]").on('keyup', function(e) {
        return formEl.updateRate();
      });
      $(formEl).find("input[name=spread]").on('keyup', function(e) {
        return formEl.updateRate();
      });
      formEl.updateRate();
      $(formEl).on('ajax:success', function(e, result) {
        if (result.indexOf('deleted') === 0) {
          return $(formEl).fadeOut();
        } else {
          return $.alert('info', result, 3000);
        }
      });
      return $(formEl).on('ajax:error', function(e, xhr) {
        return $.alert('error', xhr.responseText, 3000);
      });
    });
  }, this));
  /*
  $ =>
  	$("form.form-forex").each (i, formEl) ->
  		globalThreshold = 2;
  		$(formEl).find("input.forex").each (index, el) ->
  			$(this).tooltip()
  			$(el).inputAmountThreshold
  				min: parseFloat($(el).val())-globalThreshold
  				max: parseFloat($(el).val())+globalThreshold
  				error: (el, msg) ->
  					errEl = $('<span class="badge badge-important"></span>')
  					errEl.html msg
  					$(".form-errors").html errEl
  					setTimeout ->
  						errEl.fadeOut 'slow'
  					, 6000
  		$(formEl).find("input[type=reset]").click (e) ->
  			$("input.error").removeClass('error')
  			return true;
  		$(formEl).submit (e) ->
  			if $(this).valid()
  				return true
  			return false
  	
  	$("#forex-setting-form").validate
  		rules:
  			spread: {percentage: true}
  
  
  $.fn.inputAmountThreshold = (params) ->
  	if not params? then params = {}
  	if not params.min? then params.min = 0
  	if not params.max? then params.max = 1000000000
  	
  	$input = $(this)
  	defaultVal = parseFloat($(this).val())
  	min = parseFloat(params.min)
  	max = parseFloat(params.max)
  	$input.keyup (e) ->
  		if isNaN($input.val())
  			addError 'Is not valid number'
  			return
  			
  		newVal = parseFloat($input.val())
  		if (newVal > params.max)
  			addError 'Maximum threshold occured'
  		else if (newVal < params.min)
  			addError "Mininum threshold occured"
  		else
  			$input.removeClass 'error'
  			console.log "Still ok val "+newVal+" still in range "+params.min+" and "+params.max
  	
  	addError = (message) ->
  		console.log "Number error : "+message
  		$input.addClass('error')
  		if params.error?
  			params.error $input, message
  		#$input.after '<span class="inline-error">'+message+'</span>'
  	return 0
  
  $.validator.addMethod "percentage", (value, el) ->
  	lvalue = parseFloat(value)
  	if (lvalue > 0) and (lvalue < 100)
  		return true
  	return false
  , "Invalid percentage value"
  */
}).call(this);
