(function() {
  var initForexs, initNews, initStatistics, initTopAgent;
  $(function() {
    initTopAgent();
    initNews();
    initStatistics();
    return initForexs();
  });
  initTopAgent = function() {
    return $("#topagents .carousel").bzCarousel();
  };
  initNews = function() {
    $("#news .carousel").bzCarousel();
    return $(".news-content").each(function(i, el) {
      $(el).on('click', function(e) {
        var url;
        e.preventDefault();
        url = $(el).find('a.muted').attr('href');
        return $('#newsModal').modal('show').find('.news-content').load(url);
      });
      return $(el).hover(function(e, isFocus) {
        return $(el).find('.muted').fadeIn('slow');
      }, function() {
        return $(el).find('.muted').fadeOut('slow');
      });
    });
  };
  initStatistics = function() {
    return $('.dashboard-statistics').each(function() {
      var loadUrl, userId;
      userId = $(this).attr('data-user');
      loadUrl = '/dashboard/statistics/' + userId;
      return $(this).load(loadUrl);
    });
  };
  initForexs = function() {
    return $('.dashboard-forex').each(function() {
      var corporateCode;
      corporateCode = $(this).attr('data-corporate');
      return $(this).load('/forexs/' + corporateCode + '/table');
    });
  };
  $.fn.bzCarousel = function() {
    $(this).carousel({
      interval: false
    });
    return $(this).bind('slid', function(e, i) {
      var active, activeIndex, nav, newSelected, oldSelected;
      active = $(e.target).find('.active');
      activeIndex = $(active.parent().children()).index(active);
      nav = $(e.target).find('ul.nav-indicator');
      oldSelected = nav.children('.selected');
      newSelected = $(nav.children().get(activeIndex));
      oldSelected.removeClass('selected');
      return newSelected.addClass('selected');
    });
  };
}).call(this);
