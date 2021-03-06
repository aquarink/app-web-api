(function() {
  this.initCheckRateForm = function(form) {
    var summaryEl;
    summaryEl = $("#summary");
    $(form).find('#senderCountry_code').chainSelect('#senderCurrency_code', '/helpers/currencies.json');
    $(form).find('#beneficiaryCountry_code').chainSelect('#beneficiaryCurrency_code', '/helpers/currencies.json');
    return $(form).submit(function(e) {
      var btnEl;
      e.preventDefault();
      if ($(this).valid()) {
        btnEl = $(this).find("input[type=submit]");
        if (btnEl.attr('disabled')) {
          return;
        }
        summaryEl.html('<div class="loading"></div>');
        return $.post($(this).attr('action'), $(this).serialize(), function(response) {
          return summaryEl.html(response);
        }).error(function(err) {
          return summaryEl.html("").alert('error', "Error retrieving from remittance engine");
        });
      }
    });
  };
}).call(this);
