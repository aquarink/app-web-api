
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object layoutFront extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,Html,Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title:String="")(content: Html)(implicit head: Html = Html("")):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._

import views.Breadcrumb


Seq[Any](format.raw/*1.66*/("""

"""),format.raw/*5.1*/("""<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blitzkrieg - """),_display_(Seq[Any](/*11.30*/title)),format.raw/*11.35*/("""</title>
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*12.48*/routes/*12.54*/.Assets.at("stylesheets/bootstrap.min.css"))),format.raw/*12.97*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*13.48*/routes/*13.54*/.Assets.at("stylesheets/bootstrap-responsive.min.css"))),format.raw/*13.108*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*14.54*/routes/*14.60*/.Assets.at("stylesheets/global.css"))),format.raw/*14.96*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*15.54*/routes/*15.60*/.Assets.at("stylesheets/subnav.css"))),format.raw/*15.96*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*16.54*/routes/*16.60*/.Assets.at("stylesheets/dashboard.css"))),format.raw/*16.99*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*17.54*/routes/*17.60*/.Assets.at("stylesheets/datepicker.css"))),format.raw/*17.100*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*18.54*/routes/*18.60*/.Assets.at("stylesheets/nv.d3.css"))),format.raw/*18.95*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*19.54*/routes/*19.60*/.Assets.at("stylesheets/typeahead.js-bootstrap.css"))),format.raw/*19.112*/("""">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*20.54*/routes/*20.60*/.Assets.at("stylesheets/jquery-ui.css"))),format.raw/*20.99*/("""">
        <link rel="stylesheet" href=""""),_display_(Seq[Any](/*21.39*/routes/*21.45*/.User.css(Long.parseLong(session.get("userId"))))),format.raw/*21.93*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*22.59*/routes/*22.65*/.Assets.at("images/favicon.png"))),format.raw/*22.97*/("""">

		<script src=""""),_display_(Seq[Any](/*24.17*/routes/*24.23*/.Assets.at("javascripts/modernizr.custom.45033.js"))),format.raw/*24.74*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*25.17*/routes/*25.23*/.Assets.at("javascripts/jquery.js"))),format.raw/*25.58*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*26.17*/routes/*26.23*/.Assets.at("javascripts/jquery-ui.js"))),format.raw/*26.61*/("""" type="text/javascript"></script>
		<!-- <script src=""""),_display_(Seq[Any](/*27.22*/routes/*27.28*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*27.73*/("""" type="text/javascript"></script> -->
		<script src=""""),_display_(Seq[Any](/*28.17*/routes/*28.23*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*28.65*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*29.17*/routes/*29.23*/.Assets.at("javascripts/rails.js"))),format.raw/*29.57*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*30.17*/routes/*30.23*/.Assets.at("javascripts/jquery.validate.min.js"))),format.raw/*30.71*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*31.17*/routes/*31.23*/.Assets.at("javascripts/moment.min.js"))),format.raw/*31.62*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*32.17*/routes/*32.23*/.Assets.at("javascripts/jquery.maskedinput.min.js"))),format.raw/*32.74*/("""" type="text/javascript" charset="utf-8"></script>
		<script src=""""),_display_(Seq[Any](/*33.17*/routes/*33.23*/.Assets.at("javascripts/jquery.inputmask.bundle.min.js"))),format.raw/*33.79*/("""" type="text/javascript" charset="utf-8"></script>
		<script src=""""),_display_(Seq[Any](/*34.17*/routes/*34.23*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*34.73*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*35.23*/routes/*35.29*/.Assets.at("javascripts/form.js"))),format.raw/*35.62*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*36.23*/routes/*36.29*/.Assets.at("javascripts/print.js"))),format.raw/*36.63*/("""" type="text/javascript"></script>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
        """),_display_(Seq[Any](/*47.10*/head)),format.raw/*47.14*/("""
    </head>
    <body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">

          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>

          <a class="brand" href=""""),_display_(Seq[Any](/*61.35*/routes/*61.41*/.Main.index())),format.raw/*61.54*/("""">
              <img src=""""),_display_(Seq[Any](/*62.26*/routes/*62.32*/.Corporate.logo(session.get("corporateCode"), "small"))),format.raw/*62.86*/("""" width=60/>
          </a>

          <div class="nav-collapse collapse">
            <ul class="nav nav-pills">
                """),_display_(Seq[Any](/*67.18*/restrict(la(as("admin"), as("supervisor"), as("operator")))/*67.77*/ {_display_(Seq[Any](format.raw/*67.79*/("""
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">"""),_display_(Seq[Any](/*69.81*/doku/*69.85*/.kirimdoku.util.MultiLanguage.getLanguage("LANG008","Transaction"))),format.raw/*69.151*/(""" <b class="caret"></b></a>
                    <ul class="dropdown-menu">
						"""),_display_(Seq[Any](/*71.8*/dynamic("sendTransaction")/*71.34*/ {_display_(Seq[Any](format.raw/*71.36*/("""
						"""),_display_(Seq[Any](/*72.8*/restrict(la(as("operator")))/*72.36*/ {_display_(Seq[Any](format.raw/*72.38*/("""
                        <li class=""><a href=""""),_display_(Seq[Any](/*73.48*/routes/*73.54*/.Send.create())),format.raw/*73.68*/("""">"""),_display_(Seq[Any](/*73.71*/doku/*73.75*/.kirimdoku.util.MultiLanguage.getLanguage("LANG009","Send money"))),format.raw/*73.140*/("""</a></li>
                        <li class=""><a href=""""),_display_(Seq[Any](/*74.48*/routes/*74.54*/.Send.createBillPayment())),format.raw/*74.79*/("""">"""),_display_(Seq[Any](/*74.82*/doku/*74.86*/.kirimdoku.util.MultiLanguage.getLanguage("LANG010","Bill Payment"))),format.raw/*74.153*/("""</a></li>
						""")))})),format.raw/*75.8*/("""
						""")))})),format.raw/*76.8*/("""
						"""),_display_(Seq[Any](/*77.8*/dynamic("receiveTransaction")/*77.37*/ {_display_(Seq[Any](format.raw/*77.39*/("""
                        <li class=""><a href=""""),_display_(Seq[Any](/*78.48*/routes/*78.54*/.Receive.create())),format.raw/*78.71*/("""">"""),_display_(Seq[Any](/*78.74*/doku/*78.78*/.kirimdoku.util.MultiLanguage.getLanguage("LANG182","Receive money"))),format.raw/*78.146*/("""</a></li>
						""")))})),format.raw/*79.8*/("""
						"""),_display_(Seq[Any](/*80.8*/dynamic("sendTransaction")/*80.34*/ {_display_(Seq[Any](format.raw/*80.36*/("""
                        <li class=""><a href=""""),_display_(Seq[Any](/*81.48*/routes/*81.54*/.Tools.checkRate())),format.raw/*81.72*/("""">"""),_display_(Seq[Any](/*81.75*/doku/*81.79*/.kirimdoku.util.MultiLanguage.getLanguage("LANG011","Check rate and fee"))),format.raw/*81.152*/("""</a></li>
                        <li class=""><a href=""""),_display_(Seq[Any](/*82.48*/routes/*82.54*/.Transaction.refund())),format.raw/*82.75*/("""">"""),_display_(Seq[Any](/*82.78*/doku/*82.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG012","Refund transaction"))),format.raw/*82.155*/("""</a></li>
                        <li class=""><a href=""""),_display_(Seq[Any](/*83.48*/routes/*83.54*/.Transaction.change())),format.raw/*83.75*/("""">"""),_display_(Seq[Any](/*83.78*/doku/*83.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG013","Change transaction"))),format.raw/*83.155*/("""</a></li>
                        <li class=""><a href=""""),_display_(Seq[Any](/*84.48*/routes/*84.54*/.Transaction.unlock())),format.raw/*84.75*/("""">"""),_display_(Seq[Any](/*84.78*/doku/*84.82*/.kirimdoku.util.MultiLanguage.getLanguage("LANG014","Unlock transaction"))),format.raw/*84.155*/("""</a></li>
						""")))})),format.raw/*85.8*/("""
						"""),_display_(Seq[Any](/*86.8*/if(controllers.helpers.SessionHelper.getUser().corporate.permission == 2)/*86.81*/{_display_(Seq[Any](format.raw/*86.82*/("""
		                	<li class=""><a href=""""),_display_(Seq[Any](/*87.43*/routes/*87.49*/.Transaction.listBatchInquiry())),format.raw/*87.80*/("""">"""),_display_(Seq[Any](/*87.83*/doku/*87.87*/.kirimdoku.util.MultiLanguage.getLanguage("LANG498","Batch Upload"))),format.raw/*87.154*/("""</a></li>
		                """)))})),format.raw/*88.20*/("""
                    </ul>
                </li>
                <li><a href=""""),_display_(Seq[Any](/*91.31*/routes/*91.37*/.Transaction.list())),format.raw/*91.56*/("""">"""),_display_(Seq[Any](/*91.59*/doku/*91.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG015","Transactions management"))),format.raw/*91.141*/("""</a></li>
                
                <li><a href=""""),_display_(Seq[Any](/*93.31*/routes/*93.37*/.Customer.list())),format.raw/*93.53*/("""">"""),_display_(Seq[Any](/*93.56*/doku/*93.60*/.kirimdoku.util.MultiLanguage.getLanguage("LANG016","Customers management"))),format.raw/*93.135*/("""</a></li>
				""")))})),format.raw/*94.6*/("""

                """),_display_(Seq[Any](/*96.18*/restrict(la(as("mainagent")))/*96.47*/ {_display_(Seq[Any](format.raw/*96.49*/("""
					<li><a href=""""),_display_(Seq[Any](/*97.20*/routes/*97.26*/.User.index(session.get("corporateCode")))),format.raw/*97.67*/("""">Supervisors</a></li>
                """)))})),format.raw/*98.18*/("""
                """),_display_(Seq[Any](/*99.18*/restrict(la(as("supervisor")))/*99.48*/ {_display_(Seq[Any](format.raw/*99.50*/("""
					<li><a href=""""),_display_(Seq[Any](/*100.20*/routes/*100.26*/.User.index(session.get("corporateCode")))),format.raw/*100.67*/("""">Operators</a></li>
                """)))})),format.raw/*101.18*/("""

                <li><a href=""""),_display_(Seq[Any](/*103.31*/routes/*103.37*/.Report.list())),format.raw/*103.51*/("""">"""),_display_(Seq[Any](/*103.54*/doku/*103.58*/.kirimdoku.util.MultiLanguage.getLanguage("LANG017","Report"))),format.raw/*103.119*/("""</a></li>

	            """),_display_(Seq[Any](/*105.15*/restrict(la(as("mainagent")))/*105.44*/ {_display_(Seq[Any](format.raw/*105.46*/("""
					<li><a href=""""),_display_(Seq[Any](/*106.20*/routes/*106.26*/.News.list())),format.raw/*106.38*/("""">"""),_display_(Seq[Any](/*106.41*/doku/*106.45*/.kirimdoku.util.MultiLanguage.getLanguage("LANG036","News"))),format.raw/*106.104*/("""</a></li>
	            """)))})),format.raw/*107.15*/("""

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">"""),_display_(Seq[Any](/*110.81*/doku/*110.85*/.kirimdoku.util.MultiLanguage.getLanguage("LANG018","Help"))),format.raw/*110.144*/(""" <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href=""""),_display_(Seq[Any](/*112.39*/routes/*112.45*/.Tools.documentation)),format.raw/*112.65*/("""">"""),_display_(Seq[Any](/*112.68*/doku/*112.72*/.kirimdoku.util.MultiLanguage.getLanguage("LANG019","Documentation"))),format.raw/*112.140*/("""</a></li>
                        <li><a href=""""),_display_(Seq[Any](/*113.39*/routes/*113.45*/.Tools.feedback)),format.raw/*113.60*/("""">"""),_display_(Seq[Any](/*113.63*/doku/*113.67*/.kirimdoku.util.MultiLanguage.getLanguage("LANG020","Feedback"))),format.raw/*113.130*/("""</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav pull-right">
            <li class="divider-vertical"></li>
            <li><img src=""""),_display_(Seq[Any](/*120.28*/routes/*120.34*/.User.photo(Long.parseLong(session.get("userId")), "small"))),format.raw/*120.93*/("""" height="35px" width="35px"/></li>
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span style='font-style: italic;'>"""),_display_(Seq[Any](/*122.108*/doku/*122.112*/.kirimdoku.util.MultiLanguage.getLanguage("LANG021","Hello"))),format.raw/*122.172*/(""", """),_display_(Seq[Any](/*122.175*/session/*122.182*/.get("userDisplayName"))),format.raw/*122.205*/("""<b class="caret"></b></span> </a>
				<ul class="dropdown-menu">
					<li>
						<a href=""""),_display_(Seq[Any](/*125.17*/routes/*125.23*/.User.show(session.get("corporateCode"), Long.parseLong(session.get("userId"))))),format.raw/*125.102*/(""""><i class="icon-user"></i> """),_display_(Seq[Any](/*125.131*/doku/*125.135*/.kirimdoku.util.MultiLanguage.getLanguage("LANG022","My Account"))),format.raw/*125.200*/("""</a>
					</li>
					<li>
						<a href=""""),_display_(Seq[Any](/*128.17*/routes/*128.23*/.Login.logout)),format.raw/*128.36*/(""""><i class="icon-user"></i> """),_display_(Seq[Any](/*128.65*/doku/*128.69*/.kirimdoku.util.MultiLanguage.getLanguage("LANG023","Sign out"))),format.raw/*128.132*/("""</a>
					</li>
				</ul>
				<span class="role">
                """),_display_(Seq[Any](/*132.18*/restrict(la(as("mainagent")))/*132.47*/ {_display_(Seq[Any](format.raw/*132.49*/("""
				MainAgent
				""")))})),format.raw/*134.6*/("""
                """),_display_(Seq[Any](/*135.18*/restrict(la(as("supervisor")))/*135.48*/ {_display_(Seq[Any](format.raw/*135.50*/("""
				Supervisor
				""")))})),format.raw/*137.6*/("""
                """),_display_(Seq[Any](/*138.18*/restrict(la(as("operator")))/*138.46*/ {_display_(Seq[Any](format.raw/*138.48*/("""
				Operator
				""")))})),format.raw/*140.6*/("""
				"""),_display_(Seq[Any](/*141.6*/doku/*141.10*/.kirimdoku.util.MultiLanguage.getLanguage("LANG250","of"))),format.raw/*141.67*/(""" """),_display_(Seq[Any](/*141.69*/session/*141.76*/.get("corporateCode"))),format.raw/*141.97*/("""
				</span>
            </li>
          </ul>

          </div>

        </div>
      </div>
    </div>

		<header class="visible-desktop">
        <div class="pull-right" id="datetimeContainer">
          <span id="datetime">Date</span>
        </div>

        <div>
            """),format.raw/*159.1*/("""            """),_display_(Seq[Any](/*159.14*/Html(Breadcrumb.parse(request.path)))),format.raw/*159.50*/("""
            &nbsp;
        </div>
        <!-- <h1>Group : """),_display_(Seq[Any](/*162.27*/session/*162.34*/.get("group"))),format.raw/*162.47*/("""</h1> -->

    </header>

    <div class="container">
      <section class="content">"""),_display_(Seq[Any](/*167.33*/content)),format.raw/*167.40*/("""</section>
    </div>
    <script src=""""),_display_(Seq[Any](/*169.19*/routes/*169.25*/.Assets.at("javascripts/bootstrap/bootstrap-dropdown.js"))),format.raw/*169.82*/("""" type="text/javascript"></script>
    <script src=""""),_display_(Seq[Any](/*170.19*/routes/*170.25*/.Assets.at("javascripts/global.js"))),format.raw/*170.60*/("""" type="text/javascript"></script>
	<script src=""""),_display_(Seq[Any](/*171.16*/routes/*171.22*/.Assets.at("javascripts/typeahead.min.js"))),format.raw/*171.64*/("""" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() """),format.raw/*173.16*/("""{"""),format.raw/*173.17*/("""
			$("#datetime").timeTicker();
		"""),format.raw/*175.3*/("""}"""),format.raw/*175.4*/(""");
		$.fn.timeTicker = function() """),format.raw/*176.32*/("""{"""),format.raw/*176.33*/("""
			this.each(function(i, el) """),format.raw/*177.30*/("""{"""),format.raw/*177.31*/("""
				var updateTime = function() """),format.raw/*178.33*/("""{"""),format.raw/*178.34*/("""
					$(el).html(moment().format('dddd, MMMM Do YYYY, h:mm:ss a'));
					setTimeout(updateTime, 1000);
				"""),format.raw/*181.5*/("""}"""),format.raw/*181.6*/(""";
				updateTime()
			"""),format.raw/*183.4*/("""}"""),format.raw/*183.5*/(""");
		"""),format.raw/*184.3*/("""}"""),format.raw/*184.4*/("""
	</script>
  </body>
</html>"""))}
    }
    
    def render(title:String,content:Html,head:Html): play.api.templates.Html = apply(title)(content)(head)
    
    def f:((String) => (Html) => (Html) => play.api.templates.Html) = (title) => (content) => (head) => apply(title)(content)(head)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/layoutFront.scala.html
                    HASH: f436b02cd022f4cd10f5e732af2bf4fb419512ba
                    MATRIX: 739->1|1009->65|1037->172|1247->346|1274->351|1366->407|1381->413|1446->456|1532->506|1547->512|1624->566|1716->622|1731->628|1789->664|1881->720|1896->726|1954->762|2046->818|2061->824|2122->863|2214->919|2229->925|2292->965|2384->1021|2399->1027|2456->1062|2548->1118|2563->1124|2638->1176|2730->1232|2745->1238|2806->1277|2883->1318|2898->1324|2968->1372|3065->1433|3080->1439|3134->1471|3190->1491|3205->1497|3278->1548|3365->1599|3380->1605|3437->1640|3524->1691|3539->1697|3599->1735|3691->1791|3706->1797|3773->1842|3864->1897|3879->1903|3943->1945|4030->1996|4045->2002|4101->2036|4188->2087|4203->2093|4273->2141|4360->2192|4375->2198|4436->2237|4523->2288|4538->2294|4611->2345|4714->2412|4729->2418|4807->2474|4910->2541|4925->2547|4997->2597|5090->2654|5105->2660|5160->2693|5253->2750|5268->2756|5324->2790|6085->3515|6111->3519|6571->3943|6586->3949|6621->3962|6685->3990|6700->3996|6776->4050|6943->4181|7011->4240|7051->4242|7206->4361|7219->4365|7308->4431|7424->4512|7459->4538|7499->4540|7542->4548|7579->4576|7619->4578|7703->4626|7718->4632|7754->4646|7793->4649|7806->4653|7894->4718|7987->4775|8002->4781|8049->4806|8088->4809|8101->4813|8191->4880|8239->4897|8278->4905|8321->4913|8359->4942|8399->4944|8483->4992|8498->4998|8537->5015|8576->5018|8589->5022|8680->5090|8728->5107|8771->5115|8806->5141|8846->5143|8930->5191|8945->5197|8985->5215|9024->5218|9037->5222|9133->5295|9226->5352|9241->5358|9284->5379|9323->5382|9336->5386|9432->5459|9525->5516|9540->5522|9583->5543|9622->5546|9635->5550|9731->5623|9824->5680|9839->5686|9882->5707|9921->5710|9934->5714|10030->5787|10078->5804|10121->5812|10203->5885|10242->5886|10321->5929|10336->5935|10389->5966|10428->5969|10441->5973|10531->6040|10592->6069|10707->6148|10722->6154|10763->6173|10802->6176|10815->6180|10916->6258|11009->6315|11024->6321|11062->6337|11101->6340|11114->6344|11212->6419|11258->6434|11313->6453|11351->6482|11391->6484|11447->6504|11462->6510|11525->6551|11597->6591|11651->6609|11690->6639|11730->6641|11787->6661|11803->6667|11867->6708|11938->6746|12007->6778|12023->6784|12060->6798|12100->6801|12114->6805|12199->6866|12261->6891|12300->6920|12341->6922|12398->6942|12414->6948|12449->6960|12489->6963|12503->6967|12586->7026|12643->7050|12800->7170|12814->7174|12897->7233|13046->7345|13062->7351|13105->7371|13145->7374|13159->7378|13251->7446|13336->7494|13352->7500|13390->7515|13430->7518|13444->7522|13531->7585|13759->7776|13775->7782|13857->7841|14072->8018|14087->8022|14171->8082|14212->8085|14230->8092|14277->8115|14405->8206|14421->8212|14524->8291|14591->8320|14606->8324|14695->8389|14774->8431|14790->8437|14826->8450|14892->8479|14906->8483|14993->8546|15097->8613|15136->8642|15177->8644|15229->8664|15284->8682|15324->8712|15365->8714|15418->8735|15473->8753|15511->8781|15552->8783|15603->8802|15645->8808|15659->8812|15739->8869|15778->8871|15795->8878|15839->8899|16148->9205|16198->9218|16257->9254|16355->9315|16372->9322|16408->9335|16531->9421|16561->9428|16638->9468|16654->9474|16734->9531|16824->9584|16840->9590|16898->9625|16985->9675|17001->9681|17066->9723|17178->9806|17208->9807|17271->9842|17300->9843|17363->9877|17393->9878|17452->9908|17482->9909|17544->9942|17574->9943|17709->10050|17738->10051|17788->10073|17817->10074|17850->10079|17879->10080
                    LINES: 26->1|34->1|36->5|42->11|42->11|43->12|43->12|43->12|44->13|44->13|44->13|45->14|45->14|45->14|46->15|46->15|46->15|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|49->18|50->19|50->19|50->19|51->20|51->20|51->20|52->21|52->21|52->21|53->22|53->22|53->22|55->24|55->24|55->24|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|78->47|78->47|92->61|92->61|92->61|93->62|93->62|93->62|98->67|98->67|98->67|100->69|100->69|100->69|102->71|102->71|102->71|103->72|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|105->74|105->74|105->74|105->74|106->75|107->76|108->77|108->77|108->77|109->78|109->78|109->78|109->78|109->78|109->78|110->79|111->80|111->80|111->80|112->81|112->81|112->81|112->81|112->81|112->81|113->82|113->82|113->82|113->82|113->82|113->82|114->83|114->83|114->83|114->83|114->83|114->83|115->84|115->84|115->84|115->84|115->84|115->84|116->85|117->86|117->86|117->86|118->87|118->87|118->87|118->87|118->87|118->87|119->88|122->91|122->91|122->91|122->91|122->91|122->91|124->93|124->93|124->93|124->93|124->93|124->93|125->94|127->96|127->96|127->96|128->97|128->97|128->97|129->98|130->99|130->99|130->99|131->100|131->100|131->100|132->101|134->103|134->103|134->103|134->103|134->103|134->103|136->105|136->105|136->105|137->106|137->106|137->106|137->106|137->106|137->106|138->107|141->110|141->110|141->110|143->112|143->112|143->112|143->112|143->112|143->112|144->113|144->113|144->113|144->113|144->113|144->113|151->120|151->120|151->120|153->122|153->122|153->122|153->122|153->122|153->122|156->125|156->125|156->125|156->125|156->125|156->125|159->128|159->128|159->128|159->128|159->128|159->128|163->132|163->132|163->132|165->134|166->135|166->135|166->135|168->137|169->138|169->138|169->138|171->140|172->141|172->141|172->141|172->141|172->141|172->141|189->159|189->159|189->159|192->162|192->162|192->162|197->167|197->167|199->169|199->169|199->169|200->170|200->170|200->170|201->171|201->171|201->171|203->173|203->173|205->175|205->175|206->176|206->176|207->177|207->177|208->178|208->178|211->181|211->181|213->183|213->183|214->184|214->184
                    -- GENERATED --
                */
            