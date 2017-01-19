(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $.fn.infoForm = function() {
    var actUpdate, additionalFeeField, beneficiaryAccountCity, beneficiaryAmountField, beneficiaryCountryCode, beneficiaryCountryField, beneficiaryCurrencyField, channelField, collectAmountField, feeField, getByCountryBank, inquiryIdField, rateField, sendAmountField, senderCountryCode;
    channelField = this.find("select[name='channel.code']");
    beneficiaryCountryField = this.find("select[name='beneficiaryCountry.code']");
    beneficiaryCurrencyField = this.find("select[name='beneficiaryCurrency.code']");
    sendAmountField = this.find("input[name='senderAmount']");
    beneficiaryAmountField = this.find("input[name='beneficiaryAmount']");
    inquiryIdField = this.find("input[name='idToken']");
    rateField = this.find("input[name='rate']");
    feeField = this.find("input[name='feesTotal']");
    additionalFeeField = this.find("input[name='additionalFee']");
    collectAmountField = this.find("input[name='collectAmount']");
    senderCountryCode = $('#sender_country_code');
    beneficiaryCountryCode = $('#beneficiary_country_code');
    beneficiaryAccountCity = $('#beneficiaryAccount_city');
    senderCountryCode.val('ID');
    beneficiaryCountryCode.val('ID');
    beneficiaryAccountCity.val('Jakarta');
    this.submitInquiry = __bind(function(params, cb) {
      this.updateInquiry(null);
      return $.ajax({
        type: 'GET',
        url: '/send/inquiry',
        data: $(this).serialize(),
        error: __bind(function(err, msg) {
          return cb(err);
        }, this),
        success: __bind(function(result) {
          return cb(null, result);
        }, this)
      });
    }, this);
    this.updateInquiry = function(result) {
      var collectAmount;
      if (result && result.inquiry) {
        inquiryIdField.val(result.inquiry.idToken);
        rateField.val(Number(result.inquiry.forexReference.rate).toFixed(6)).trigger('keyup');
        feeField.val(result.inquiry.fund.fees.total);
        feeField.inputmask("money");
        additionalFeeField.val(result.inquiry.fund.fees.additionalFee);
        additionalFeeField.inputmask("money");
        sendAmountField.val(result.inquiry.fund.origin.amount);
        beneficiaryAmountField.val(Number(result.inquiry.fund.destination.amount));
        collectAmount = Number(result.inquiry.fund.origin.amount) + Number(result.inquiry.fund.fees.total);
        collectAmountField.val(collectAmount).trigger('keyup');
        collectAmountField.inputmask("money");
        $('#senderCountryName').html(result.inquiry.senderCountry.name);
        $('#beneficiaryCountryName').html(result.inquiry.beneficiaryCountry.name);
        $('#ratee').html(result.inquiry.forexReference.rate);
        $('#originAmount').html(result.inquiry.fund.origin.amount + ' ' + result.inquiry.fund.origin.currency);
        $('#destinationAmount').html(result.inquiry.fund.destination.amount + ' ' + result.inquiry.fund.destination.currency);
        $('#feeTotal').html(result.inquiry.fund.fees.total);
        $('#feeAdditional').html(result.inquiry.fund.fees.additionalFee);
        $('#amountToCollect').html(result.inquiry.fund.origin.amount + ' ' + result.inquiry.fund.origin.currency);
        return $('#serviceLbl').html(result.inquiry.channel.name);
      } else if (result && result.message) {
        $(this).find('.container-info').alert('error', result.message, 3000);
        rateField.val("");
        return feeField.val("");
      }
    };
    this.updateInquiry1 = function(result) {
      var collectAmount;
      if (result && result.inquiry) {
        inquiryIdField.val(result.inquiry.idToken);
        rateField.val(Number(result.inquiry.forexReference.rate).toFixed(6)).trigger('keyup');
        feeField.val(result.inquiry.fund.fees.total);
        feeField.inputmask("money");
        additionalFeeField.val(result.inquiry.fund.fees.additionalFee);
        additionalFeeField.inputmask("money");
        sendAmountField.val(result.inquiry.fund.origin.amount);
        beneficiaryAmountField.val(Number(result.inquiry.fund.destination.amount));
        collectAmount = Number(result.inquiry.fund.origin.amount) + Number(result.inquiry.fund.fees.total);
        collectAmountField.val(collectAmount).trigger('keyup');
        return collectAmountField.inputmask("money");
      } else if (result && result.message) {
        $(this).find('.container-info').alert('error', result.message, 3000);
        rateField.val("");
        return feeField.val("");
      }
    };
    beneficiaryCountryField.on('change', __bind(function(e) {
      if ($('#channel_code').val() === '03') {
        return $('#beneficiaryCountry_code').val('CN');
      }
    }, this));
    channelField.on('change', __bind(function(e) {
      console.log("Change to ", $(e.target).val());
      if ($(e.target).val() === '05' || $(e.target).val() === '06' || $(e.target).val() === '07') {
        $('#row-ctc').hide();
        $('.form-cashin-customer-account').show();
        $('#receiver-bank-info').show();
        $('#beneficiaryCountry_code').removeAttr('readOnly');
        getByCountryBank($('#beneficiaryCountry_code').val());
        $('.form-cashin-voucher-info').hide();
        $('#btn-send-primary').removeAttr('disabled');
        $('#voucherCode').removeClass('required');
        $('.form-cashin-wallet-info').hide();
        return $('#beneficiaryWalletId').removeClass('required');
      } else if ($(e.target).val() === '03') {
        $('#row-ctc').show();
        $('.form-cashin-customer-account').show();
        $('#receiver-bank-info').hide();
        $('#beneficiaryCountry_code').val('CN');
        getByCountryBank($('#beneficiaryCountry_code').val());
        $('#beneficiaryCountry_code').attr('readonly', true);
        $('.form-cashin-voucher-info').hide();
        $('#btn-send-primary').removeAttr('disabled');
        $('#voucherCode').removeClass('required');
        $('.form-cashin-wallet-info').hide();
        return $('#beneficiaryWalletId').removeClass('required');
      } else if ($(e.target).val() === '21') {
        $('#row-ctc').hide();
        $('.form-cashin-customer-account').hide();
        $('#receiver-bank-info').hide();
        $('#beneficiaryCountry_code').removeAttr('readOnly');
        getByCountryBank($('#beneficiaryCountry_code').val());
        $('.form-cashin-voucher-info').show();
        $('#btn-send-primary').prop("disabled", true);
        $('#voucherCode').addClass('required');
        $('.form-cashin-wallet-info').hide();
        return $('#beneficiaryWalletId').removeClass('required');
      } else if ($(e.target).val() === '22') {
        $('#row-ctc').hide();
        $('.form-cashin-customer-account').show();
        $('#receiver-bank-info').show();
        $('#beneficiaryCountry_code').removeAttr('readOnly');
        getByCountryBank($('#beneficiaryCountry_code').val());
        $('.form-cashin-voucher-info').show();
        $('#btn-send-primary').prop("disabled", true);
        $('#voucherCode').addClass('required');
        $('.form-cashin-wallet-info').hide();
        return $('#beneficiaryWalletId').removeClass('required');
      } else if ($(e.target).val() === '04') {
        $('#row-ctc').hide();
        $('#receiver-bank-info').show();
        $('.form-cashin-customer-account').hide();
        $('#beneficiaryCountry_code').removeAttr('readOnly');
        getByCountryBank($('#beneficiaryCountry_code').val());
        $('.form-cashin-voucher-info').hide();
        $('#btn-send-primary').removeAttr('disabled');
        $('#voucherCode').removeClass('required');
        $('.form-cashin-wallet-info').show();
        return $('#beneficiaryWalletId').addClass('required');
      } else {
        $('#row-ctc').hide();
        $('#receiver-bank-info').show();
        $('.form-cashin-customer-account').hide();
        $('#beneficiaryCountry_code').removeAttr('readOnly');
        getByCountryBank($('#beneficiaryCountry_code').val());
        $('.form-cashin-voucher-info').hide();
        $('#btn-send-primary').removeAttr('disabled');
        $('#voucherCode').removeClass('required');
        $('.form-cashin-wallet-info').hide();
        return $('#beneficiaryWalletId').removeClass('required');
      }
    }, this));
    getByCountryBank = function(countryBank) {
      var searchVal;
      console.log(this.value);
      searchVal = 'countryBank=' + countryBank + '&channel.code=' + $('#channel_code').val();
      $('#groupBank').html('');
      $('#provinceBank').html('');
      $('#cityBank').html('');
      $('#beneficiaryAccount_bank_code').html('');
      $('#provinceBank').append($('<option>').text('-- Choose Province --').attr('value', ''));
      $('#cityBank').append($('<option>').text('-- Choose City --').attr('value', ''));
      $("input[name='beneficiaryAccount.bank.code']").val('');
      $('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''));
      $('#groupBank').append($('<option>').text('-- Choose Group Bank --').attr('value', ''));
      if (this.value !== '') {
        $.ajax({
          type: 'POST',
          url: '/getcountrybank',
          data: searchVal,
          dataType: 'json',
          error: __bind(function(err, msg) {
            return $('#groupBank').removeClass('ui-autocomplete-loading');
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#groupBank').addClass('ui-autocomplete-loading');
          }, this),
          success: __bind(function(result) {
            var defaultVal;
            $('#groupBank').removeClass('ui-autocomplete-loading');
            defaultVal = '';
            $.each(result, function(key, value) {
              $('#groupBank').append($('<option>').text(value).attr('value', key));
              return defaultVal = key;
            });
            if (defaultVal === 'default') {
              $("#groupBank").val(defaultVal);
              $("#groupBank").change();
            }
            return true;
          }, this)
        });
      }
      return true;
    };
    this.find('select[name="senderCountry.code"]').chainSelect('select[name="senderCurrency.code"]', '/helpers/currencies.json');
    this.find('input[name="sendType"]').on('change', __bind(function(e) {
      return $(this).find('input[name="senderAmount"], input[name="beneficiaryAmount"]').each(function(i, el) {
        if ($(el).attr('name') === $(e.target).val()) {
          return $(el).removeAttr('readOnly').addClass('required');
        } else {
          return $(el).attr('readOnly', true).removeClass('required');
        }
      });
    }, this));
    if (!this.find('input[name="sendType"]').attr('disabled')) {
      this.find('input[name="sendType"]').trigger('change');
    }
    beneficiaryCountryField.chainSelect('select[name="beneficiaryCurrency.code"]', '/helpers/currencies.json', {
      after: __bind(function(target) {
        getByCountryBank($('#beneficiaryCountry_code').val());
        $(this).find('input[name="sendType"], input[name="senderAmount"]').each(function(i, el) {
          $(el).removeAttr('readOnly');
          return $(el).removeAttr('disabled');
        });
        this.find('input[name="sendType"]:checked').trigger('change');
        this.find('#beneficiaryAmount_field .currency-code').html($(target).val());
        $("#beneficiaryCity").typeahead('destroy');
        return $("#beneficiaryCity").typeahead({
          hint: true,
          highlight: true,
          minLength: 2,
          remote: '/helpers/cities.json?country.code=' + beneficiaryCountryField.val() + '&q=%QUERY'
        });
      }, this)
    });
    if (beneficiaryCountryField.val()) {
      beneficiaryCountryField.trigger('change');
    }
    actUpdate = '';
    sendAmountField.on('blur', __bind(function(e) {
      return this.submitInquiry({}, __bind(function(err, result) {
        if (err) {
          console.log("submit inq err", err);
          console.log("submit inq res", result);
          return $(this).find('.container-info').alert('error', err, 5000);
        } else {
          if (actUpdate === '') {
            this.updateInquiry(result);
          } else {
            this.updateInquiry1(result);
          }
          return actUpdate = '';
        }
      }, this));
    }, this));
    beneficiaryAmountField.on('change', function(e) {
      actUpdate = 'beneficiaryAmountField';
      return sendAmountField.trigger('blur');
    });
    this.isValid = function() {
      return inquiryIdField.val().length > 0;
    };
    return this;
  };
  $.fn.customerForm = function() {
    var prefix, tokenField;
    prefix = $(this).attr('data-prefix');
    tokenField = this.find("input[name='" + prefix + ".idToken']");
    this.checkCustomer = function(params, cb) {
      return $.ajax({
        type: 'GET',
        url: '/send/customer-lookupRelate',
        data: $(this).serialize(),
        error: __bind(function(err, msg) {
          return cb(err, msg);
        }, this),
        success: __bind(function(result) {
          return cb(null, result);
        }, this)
      });
    };
    this.handleCheckCustomer = __bind(function(err, result) {
      var field;
      if (err) {
        if (err.status === !404) {
          return $(this).find('.container-info').alert('error', err, 5000);
        }
      } else if (result.status === 0) {
        Object.keys(result.customer).forEach(__bind(function(k) {
          var field, v;
          v = result.customer[k];
          if (!(v instanceof Object)) {
            field = $(this).find("[name='" + prefix + "." + k + "']");
            if (field) {
              return field.val(result.customer[k]);
            }
          }
        }, this));
        field = $(this).find("[name='" + prefix + ".country.code']");
        field.val(result.customer.country.code);
        field = $(this).find("[name='" + prefix + ".personalIdCountry.code']");
        if (result.customer.personalIdCountry !== null) {
          field.val(result.customer.personalIdCountry.code);
        }
        if (prefix === 'sender') {
          if (result.customer.lastName === '') {
            $('#sender_lastName').val($('#sender_firstName').val());
          }
          $('#sennameLbl').html(result.customer.firstName + ' ' + result.customer.lastName);
          $('#senpNameLbl').html(result.customer.personalId);
          $('#senpNumberLbl').html(result.customer.phoneNumber);
          result.customerReceiver.forEach(function(key, value) {
            return $('#beneficiary_idToken').append('<option value= ' + key.idToken + '>' + key.firstName + ' ' + key.lastName + '</option>');
          });
        }
        if (prefix === 'beneficiary') {
          if (result.customer.inputMode !== null) {
            if (result.customer.inputMode === 'TCODE') {
              $('#inputModeTCODE').prop("checked", true);
              $('#beneficiary_tcode').removeAttr('readOnly');
              $('#beneficiary_pinyin').attr('readOnly', true);
              $('#inputModePINYIN').removeAttr("checked");
            } else if (result.customer.inputMode === 'PINYIN') {
              $('#inputModePINYIN').prop("checked", true);
              $('#inputModeTCODE').removeAttr("checked");
              $('#beneficiary_pinyin').removeAttr('readOnly');
              $('#beneficiary_tcode').attr('readOnly', true);
            }
            $('#beneficiary_lastName').val($('#beneficiary_firstName').val());
          }
          $('#recnameLbl').html(result.customer.firstName + ' ' + result.customer.lastName);
          $('#recpNameLbl').html(result.customer.personalId);
          $('#recpNumberLbl').html(result.customer.phoneNumber);
        }
        return true;
      } else {
        return $(this).find('.container-info').alert('error', result.message, 5000);
      }
    }, this);
    this.handleUpdateCustomer = __bind(function(err, result) {
      var idTokenField;
      if (err) {
        if (err.status === !404) {
          return $(this).find('.container-info').alert('error', err, 5000);
        }
      } else if (result.status === 0) {
        idTokenField = $(this).find("[name='" + prefix + ".idToken']");
        return idTokenField.val(result.customer.idToken);
      } else {
        return $(this).find('.container-info').alert('error', result.message, 5000);
      }
    }, this);
    tokenField.on('change', __bind(function(e) {
      if (!$(e.currentTarget).val().length) {
        return;
      }
      return this.checkCustomer({}, this.handleCheckCustomer);
    }, this));
    return this.find('input.required,select.required').on('change', __bind(function(e) {
      var triggerLookup;
      triggerLookup = true;
      this.find('input.required,select.required').each(function(k, f) {
        if (!$(f).val().length) {
          return triggerLookup = false;
        }
      });
      if (triggerLookup) {
        return this.checkCustomer({}, this.handleUpdateCustomer);
      }
    }, this));
  };
  $.fn.bankForm = function() {
    var bankCityField, bankCodeField;
    bankCodeField = this.find("input[name='beneficiaryAccount.bank.code']");
    bankCityField = this.find("input[name='beneficiaryAccount.city']");
    return this.find("select[name='beneficiaryAccount.bank.code']").on('change', __bind(function(e) {
      var bankCode;
      bankCode = $(e.currentTarget).val();
      return $.get("/helpers/banks/" + bankCode + ".json", function(data) {
        bankCodeField.val(data.code);
        if (data.city !== null && data.city !== "") {
          return bankCityField.val(data.city);
        } else {
          return bankCityField.val("Jakarta");
        }
      });
    }, this));
  };
  $(function() {
    var extractLast, extractLastPy, infoForm, split, splitpy, splitzh;
    infoForm = $("form#form-cashin .form-cashin-info").infoForm();
    $("form#form-cashin .form-cashin-customer").each(function() {
      return $(this).customerForm();
    });
    $("form#form-cashin .form-cashin-customer-account").each(function() {
      return $(this).bankForm();
    });
    $("form#form-cashin").submit(function(e) {
      if ($(this).data('verified')) {
        return true;
      }
      if (infoForm.isValid()) {
        if ($(this).valid()) {
          $.post($(this).data('url-verify'), $(this).serialize(), __bind(function(result) {
            if (result.length === 0) {
              $(this).data('verified', true);
              return $(this).submit();
            } else {
              $("#sendModal").find('.modal-body').html(result);
              return $("#sendModal").modal({
                show: true
              });
            }
          }, this)).fail(function() {
            $("#sendModal").find('.modal-body').html("Fail to communicate to server, please try again");
            return $("#sendModal").modal({
              show: true
            });
          });
        }
      }
      return false;
    });
    $('#btn-cek-voucher').on('click', __bind(function(e) {
      var channelCode, voucherCode;
      voucherCode = $('#voucherCode');
      channelCode = $('#channel_code');
      if (voucherCode.val() !== '' && voucherCode.val().trim().length === 6 && channelCode.val() !== '' && (channelCode.val() === '21' || channelCode.val() === '22')) {
        return $.ajax({
          type: 'POST',
          url: '/validatevoucher',
          data: 'vouchercode=' + voucherCode.val() + '&channelcode=' + channelCode.val(),
          dataType: 'html',
          error: __bind(function(err, msg) {
            return $('#btn-send-primary').prop("disabled", true);
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#btn-send-primary').prop("disabled", true);
          }, this),
          success: __bind(function(result) {
            console.log(result);
            if (result === 'CONTINUE') {
              $('#btn-send-primary').removeAttr('disabled');
              $(this).find('.voucher-info').alert('success', 'Voucher Valid, You May Proceed', 2000);
            } else {
              $('#btn-send-primary').prop("disabled", true);
              $(this).find('.voucher-info').alert('error', 'Invalid Voucher Code', 1000);
            }
            return true;
          }, this)
        });
      } else {
        return $('#btn-send-primary').prop("disabled", true);
      }
    }, this));
    $('#btn-send-primary').on('click', __bind(function(e) {
      if ($('#channel_code').val() === '21' || $('#channel_code').val() === '22') {
        if ($('#voucherCode').val().length === 6) {
          return true;
        } else {
          $('#voucherCode').focus();
          return false;
        }
      }
    }, this));
    $("#voucherCode").on('change', function(e) {
      return $('#btn-send-primary').prop("disabled", true);
    });
    $('#inputModeTCODE').on('change', __bind(function(e) {
      $('#beneficiary_tcode').removeAttr('readOnly');
      $('#beneficiary_pinyin').attr('readOnly', true);
      $("#beneficiary_firstName").val("");
      $("#beneficiary_lastName").val("");
      $("#beneficiary_tcode").val("");
      return $("#beneficiary_pinyin").val("");
    }, this));
    $('#inputModePINYIN').on('change', __bind(function(e) {
      $('#beneficiary_pinyin').removeAttr('readOnly');
      $('#beneficiary_tcode').attr('readOnly', true);
      $("#beneficiary_firstName").val("");
      $("#beneficiary_lastName").val("");
      $("#beneficiary_tcode").val("");
      return $("#beneficiary_pinyin").val("");
    }, this));
    split = function(val) {
      return val.split(/,\s*/);
    };
    splitpy = function(val) {
      return val.split(';');
    };
    splitzh = function(val) {
      return val.split('');
    };
    extractLast = function(term) {
      return split(term).pop();
    };
    extractLastPy = function(term) {
      return splitpy(term).pop();
    };
    $('.checkTC').on('click', __bind(function(e) {
      var dataCheck, dataLast, dataLookup, dataLookups, dataRequest;
      dataLookups = splitpy($('#beneficiary_tcode').val());
      while (dataLookups[dataLookups.length - 1] === '') {
        dataLookups.pop();
      }
      dataLookups.push('');
      dataLookup = dataLookups.join(',');
      dataRequest = dataLookups.join(';');
      $('#beneficiary_tcode').val(dataRequest);
      dataLast = extractLastPy($('#beneficiary_tcode').val()).trim();
      if (dataLookup !== '') {
        $('#beneficiary_firstName').val('');
        $("#beneficiary_lastName").val('');
        $("#beneficiary_pinyin").val('');
        dataCheck = 'codeVal=' + dataLookup;
        return $.ajax({
          type: 'POST',
          url: '/translate/tcode',
          data: dataCheck,
          dataType: 'xml',
          error: __bind(function(err, msg) {
            $('#pageCheckPinyin').val(msg);
            return $('#beneficiary_tcode').removeClass('ui-autocomplete-loading');
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#beneficiary_tcode').addClass('ui-autocomplete-loading');
          }, this),
          success: __bind(function(result) {
            var data, dataPinyin, dataResult, dataTCode;
            dataResult = '';
            dataPinyin = '';
            dataTCode = '';
            data = $('result', result).map(function() {
              return {
                label: $('tCode', this).text(),
                value: $('zhWord', this).text(),
                desc: $('pinyin', this).text()
              };
            }).get();
            $.each(data, function(key, val) {
              dataResult = dataResult + val.value;
              dataPinyin = dataPinyin + val.desc + ';';
              return dataTCode = dataTCode + val.label + ';';
            });
            $('#beneficiary_firstName').val(dataResult);
            $("#beneficiary_lastName").val(dataResult);
            $("#beneficiary_pinyin").val(dataPinyin);
            $('#beneficiary_tcode').val(dataTCode);
            $('#beneficiary_tcode').removeClass('ui-autocomplete-loading');
            return true;
          }, this)
        });
      }
    }, this));
    $("#beneficiary_tcode").on('keyup', function(e) {
      var terms1, terms2, terms3;
      if (e.keyCode === 40 && e.shiftKey) {
        $('.checkTC').click();
      } else {
        if ($("#beneficiary_tcode").val() === "") {
          $("#beneficiary_firstName").val("");
          $("#beneficiary_lastName").val("");
          $("#beneficiary_pinyin").val("");
        } else {
          terms1 = splitpy($("#beneficiary_tcode").val());
          terms2 = splitzh($("#beneficiary_firstName").val());
          terms3 = splitpy($("#beneficiary_pinyin").val());
          if (terms2.length >= terms1.length && $("#beneficiary_tcode").attr('readOnly') !== 'readonly') {
            terms2.pop();
            terms3.pop();
            terms3.pop();
            $("#beneficiary_firstName").val(terms2.join(''));
            $("#beneficiary_lastName").val(terms2.join(''));
            terms3.push('');
            $("#beneficiary_pinyin").val(terms3.join(';'));
          }
        }
      }
      return true;
    });
    $('.checkPinyin').on('click', __bind(function(e) {
      var dataCheck, dataLookup;
      dataLookup = extractLastPy($('#beneficiary_pinyin').val()).trim();
      if (dataLookup !== '') {
        dataCheck = 'codeVal=' + dataLookup;
        return $.ajax({
          type: 'POST',
          url: '/translate/pinyin',
          data: dataCheck,
          dataType: 'xml',
          error: __bind(function(err, msg) {
            $('#pageCheckPinyin').val(msg);
            return $('#beneficiary_pinyin').removeClass('ui-autocomplete-loading');
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#beneficiary_pinyin').addClass('ui-autocomplete-loading');
          }, this),
          success: __bind(function(result) {
            var data;
            data = $('result', result).map(function() {
              return {
                label: $('pinyin', this).text(),
                value: $('zhWord', this).text(),
                desc: $('tCode', this).text()
              };
            }).get();
            $('#beneficiary_pinyin').autocomplete({
              minLength: 0,
              source: data,
              focus: function(event, ui) {
                return false;
              },
              select: function(event, ui) {
                var tcodeName, tcodeNamefull, terms, zhname, zhnamefull;
                console.log('Value : ' + ui.item.value);
                console.log('Label : ' + ui.item.label);
                console.log('Desc  : ' + ui.item.desc);
                terms = splitpy(this.value);
                terms.pop();
                terms.push(ui.item.label);
                terms.push('');
                this.value = terms.join(';');
                zhname = splitzh($('#beneficiary_firstName').val());
                zhname.push(ui.item.value);
                zhnamefull = zhname.join('');
                $('#beneficiary_firstName').val(zhnamefull);
                $("#beneficiary_lastName").val(zhnamefull);
                tcodeName = splitpy($('#beneficiary_tcode').val());
                if ($('#beneficiary_tcode').val().length === 0) {
                  tcodeName.pop();
                } else if ($('#beneficiary_tcode').val().substring($('#beneficiary_tcode').val().length - 1, $('#beneficiary_tcode').val().length) === ';') {
                  tcodeName.pop();
                }
                tcodeName.push(ui.item.desc);
                tcodeName.push('');
                tcodeNamefull = tcodeName.join(';');
                $('#beneficiary_tcode').val(tcodeNamefull);
                return false;
              }
            }).autocomplete('instance')._renderItem = function(ul, item) {
              return $('<li>').append('<a>' + item.label + ' ' + item.value + ' ' + item.desc + '</a>').appendTo(ul);
            };
            $('#beneficiary_pinyin').autocomplete('search', dataLookup);
            $('#beneficiary_pinyin').removeClass('ui-autocomplete-loading');
            return true;
          }, this)
        });
      }
    }, this));
    $("#beneficiary_pinyin").on('keyup', function(e) {
      var terms1, terms2, terms3;
      if (e.keyCode === 40 && e.shiftKey) {
        $('.checkPinyin').click();
      } else {
        if ($("#beneficiary_pinyin").val() === "") {
          $("#beneficiary_firstName").val("");
          $("#beneficiary_lastName").val("");
          $("#beneficiary_tcode").val("");
        } else {
          terms1 = splitpy($("#beneficiary_pinyin").val());
          terms2 = splitzh($("#beneficiary_firstName").val());
          terms3 = splitpy($("#beneficiary_tcode").val());
          if (terms2.length >= terms1.length && $("#beneficiary_pinyin").attr('readOnly') !== 'readonly') {
            terms2.pop();
            terms3.pop();
            terms3.pop();
            $("#beneficiary_firstName").val(terms2.join(''));
            $("#beneficiary_lastName").val(terms2.join(''));
            terms3.push('');
            $("#beneficiary_tcode").val(terms3.join(';'));
          }
        }
      }
      return true;
    });
    if ($("input[name='channel.code']").val() === "02" || $("input[name='channel.code']").val() === "21") {
      $("#send-confirmation-form .btn-forward").on('click', function(e) {
        console.log("btn formward onclick", e);
        $("#send-confirmation-dialog").modal({
          show: true
        });
        $("#send-confirmation-dialog input:first").focus();
        $("input[name='supervisor.password']").val("");
        return false;
      });
    } else if ($("input[name='channel.code']").val() === "05" || $("input[name='channel.code']").val() === "06" || $("input[name='channel.code']").val() === "07" || $("input[name='channel.code']").val() === "03" || $("input[name='channel.code']").val() === "22" || $("input[name='channel.code']").val() === "04" || $("input[name='channel.code']").val() === "10") {
      $(this).find("#auth1").val('1234');
      $(this).find("#auth2").val('1234');
      $("#send-confirmation-form .btn-forward").on('click', function(e) {
        return $("#send-confirmation-form").submit();
      });
    }
    $("#groupBank").on('change', function(e) {
      var searchVal;
      console.log(this.value);
      searchVal = 'countryBank=' + $('#beneficiaryCountry_code').val() + '&groupBank=' + this.value;
      $('#provinceBank').html('');
      $('#cityBank').html('');
      $('#beneficiaryAccount_bank_code').html('');
      $('#provinceBank').append($('<option>').text('-- Choose Province --').attr('value', ''));
      $('#cityBank').append($('<option>').text('-- Choose City --').attr('value', ''));
      $("input[name='beneficiaryAccount.bank.code']").val('');
      $('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''));
      if (this.value !== '') {
        $.ajax({
          type: 'POST',
          url: '/getprovincebank',
          data: searchVal,
          dataType: 'json',
          error: __bind(function(err, msg) {
            return $('#provinceBank').removeClass('ui-autocomplete-loading');
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#provinceBank').addClass('ui-autocomplete-loading');
          }, this),
          success: __bind(function(result) {
            var defaultVal;
            $('#provinceBank').removeClass('ui-autocomplete-loading');
            defaultVal = '';
            $.each(result, function(key, value) {
              $('#provinceBank').append($('<option>').text(value).attr('value', key));
              return defaultVal = key;
            });
            if (defaultVal === 'default') {
              $("#provinceBank").val(defaultVal);
              $("#provinceBank").change();
            }
            return true;
          }, this)
        });
      }
      return true;
    });
    $("#provinceBank").on('change', function(e) {
      var searchVal;
      console.log(this.value);
      searchVal = 'countryBank=' + $('#beneficiaryCountry_code').val() + '&groupBank=' + $('#groupBank').val() + '&provinceBank=' + this.value;
      $('#cityBank').html('');
      $('#beneficiaryAccount_bank_code').html('');
      $('#cityBank').append($('<option>').text('-- Choose City --').attr('value', ''));
      $("input[name='beneficiaryAccount.bank.code']").val('');
      $('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''));
      if (this.value !== '') {
        $.ajax({
          type: 'POST',
          url: '/getcitybank',
          data: searchVal,
          dataType: 'json',
          error: __bind(function(err, msg) {
            return $('#cityBank').removeClass('ui-autocomplete-loading');
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#cityBank').addClass('ui-autocomplete-loading');
          }, this),
          success: __bind(function(result) {
            var defaultVal;
            $('#cityBank').removeClass('ui-autocomplete-loading');
            defaultVal = '';
            $.each(result, function(key, value) {
              $('#cityBank').append($('<option>').text(value).attr('value', key));
              return defaultVal = key;
            });
            if (defaultVal === 'default') {
              $("#cityBank").val(defaultVal);
              $("#cityBank").change();
            }
            return true;
          }, this)
        });
      }
      return true;
    });
    $("#cityBank").on('change', function(e) {
      var searchVal;
      console.log(this.value);
      searchVal = 'countryBank=' + $('#beneficiaryCountry_code').val() + '&groupBank=' + $('#groupBank').val() + '&provinceBank=' + $('#provinceBank').val() + '&cityBank=' + this.value;
      $('#beneficiaryAccount_bank_code').html('');
      $("input[name='beneficiaryAccount.bank.code']").val('');
      $('#beneficiaryAccount_bank_code').append($('<option>').text('-- Choose a bank --').attr('value', ''));
      if (this.value !== '') {
        $.ajax({
          type: 'POST',
          url: '/getbank',
          data: searchVal,
          dataType: 'json',
          error: __bind(function(err, msg) {
            return $('#beneficiaryAccount_bank_code').removeClass('ui-autocomplete-loading');
          }, this),
          beforeSend: __bind(function(xhr) {
            return $('#beneficiaryAccount_bank_code').addClass('ui-autocomplete-loading');
          }, this),
          success: __bind(function(result) {
            $('#beneficiaryAccount_bank_code').removeClass('ui-autocomplete-loading');
            $.each(result, function(key, value) {
              return $('#beneficiaryAccount_bank_code').append($('<option>').text(value).attr('value', key));
            });
            $('#beneficiaryAccount_bank_code option').eq(1).prop('selected', true);
            return $('#beneficiaryAccount_bank_code').change();
          }, this)
        });
      }
      return true;
    });
    return $("#send-confirmation-form").on('submit', function(e) {
      var appendEl, authorizationFilled, pinFilled, pinMatch;
      pinFilled = $(this).find("#auth1").val();
      pinMatch = $(this).find("#auth1").val() === $(this).find("#auth2").val();
      authorizationFilled = $(this).find("input[name='supervisor.password']").val();
      if ($(this).valid() && pinMatch) {
        $(this).find("button[type=submit]").attr('disabled', 'disabled');
        return true;
      } else if (!pinMatch) {
        appendEl = $("#auth2_field");
        appendEl.alert('error', "Your verification is not match, please try again!", 5000);
        $("#auth1").val("");
        $("#auth2").val("");
        $("#auth1").focus();
      }
      e.preventDefault();
      return false;
    });
  });
}).call(this);
