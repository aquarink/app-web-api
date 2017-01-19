(function() {
  var __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };
  $(__bind(function() {
    var $detailContainer;
    $("input.datepicker").each(function(index, el) {
      return $(el).datepicker();
    });
    if ($(".datepicker").length !== 0) {
      $(".datepicker").datepicker();
    }
    $('form select[name="country.code"]').each(function(index, el) {
      return $(el).chainSelect('select[name="city.id"]', '/helpers/cities.json');
    });
    $detailContainer = $("#detail-container").first();
    return $(".container-users").each(function(index, el) {
      return $(el).find('.carousel').each(function(i, el) {
        $(el).dynamicCarousel();
        return $(el).on('ajax:loaded', function(e, data) {
          $(el).find(".table-users a.btn-detail").on('click', function(e) {
            e.preventDefault();
            return $.ajax({
              url: $(this).attr('href'),
              success: function(data) {
                return $detailContainer.html(data);
              }
            });
          });
          $(el).find(".table-users a.btn-op-status").click(function(e, el) {
            var buttonEl, formEl, isBan, modalEl, url;
            e.preventDefault();
            buttonEl = $(e.currentTarget);
            url = buttonEl.attr('href');
            modalEl = $('#modal-ban');
            formEl = modalEl.find('form');
            isBan = buttonEl.hasClass('btn-op-ban');
            if (isBan) {
              modalEl.find('p.ban').removeClass('hide');
              modalEl.find('p.unban').addClass('hide');
            } else {
              modalEl.find('p.unban').removeClass('hide');
              modalEl.find('p.ban').addClass('hide');
            }
            formEl.attr('action', url);
            formEl.off('submit').off('submit').on('submit', function(e) {
              $.rails.handleRemote(formEl);
              return false;
            });
            formEl.off('ajax:error').on('ajax:error', function(e, xhr, status, err) {
              return modalEl.find('.container-error').alert('error', "Connection failure, please retry", 3000);
            });
            formEl.off('ajax:success').on('ajax:success', function(e, data) {
              modalEl.modal('hide');
              if (data.error) {
                $.alert('error', data.error, 3000);
              } else {
                $.alert('success', data.success, 3000);
              }
              buttonEl.fadeOut('slow');
              return formEl.find('input').val("");
            });
            return modalEl.modal({
              show: true
            });
          });
          $(el).find(".table-users a.btn-op-promote").click(function(e, el) {
            var buttonEl, formEl, modalEl;
            e.preventDefault();
            buttonEl = $(e.currentTarget);
            modalEl = $('#modal-promote');
            formEl = modalEl.find('form');
            formEl.attr('action', buttonEl.attr('href'));
            return modalEl.modal({
              show: true
            });
          });
          return $(el).find(".table-users a.btn-op-demote").click(function(e, el) {
            var buttonEl, modalEl;
            e.preventDefault();
            buttonEl = $(e.currentTarget);
            modalEl = $("#modal-demote");
            modalEl.modal({
              show: true
            });
            return modalEl.load(buttonEl.attr('href'));
          });
        });
      });
    });
  }, this));
  $.fn.dynamicCarousel = function(params) {
    var $innerContent, initSlideContent, loadSlideContent;
    $innerContent = $(this).find('.carousel-inner');
    $(this).carousel({
      interval: false
    });
    loadSlideContent = __bind(function(container, url) {
      $(container).html('<div class="loading"></div>');
      return $(container).load(url, __bind(function(data) {
        initSlideContent(container);
        return $(this).trigger('ajax:loaded', container);
      }, this));
    }, this);
    initSlideContent = __bind(function(content) {
      return $(content).find("a.slide").each(__bind(function(i2, a) {
        return $(a).on('click', __bind(function(e) {
          var $newContainer, op, url;
          e.preventDefault();
          url = $(a).attr('href');
          op = $(a).attr('data-slide-operation');
          $newContainer = $('<div class="item"></div>');
          $innerContent.find('.item.active').nextAll().remove();
          if (op === 'next') {
            $innerContent.find('.item.active').after($newContainer);
          }
          $(this).carousel(op);
          if (url) {
            return loadSlideContent($newContainer, url);
          }
        }, this));
      }, this));
    }, this);
    return $(this).find('.item.active').each(function(i, el) {
      if ($(el).attr('data-url')) {
        return loadSlideContent(el, $(el).attr('data-url'));
      } else {
        return initSlideContent(el);
      }
    });
  };
}).call(this);
