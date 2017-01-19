
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
object layoutAdmin extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,Seq[scala.Tuple2[String, String]],Html,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(title:String="", breadcrumbs:Seq[(String, String)]=null)(content: Html):play.api.templates.Html = {
        _display_ {import views.Breadcrumb

import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._


Seq[Any](format.raw/*1.74*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*6.1*/("""

<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Blitzkrieg - """),_display_(Seq[Any](/*14.24*/title)),format.raw/*14.29*/("""</title>
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*15.48*/routes/*15.54*/.Assets.at("stylesheets/bootstrap.min.css"))),format.raw/*15.97*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*16.48*/routes/*16.54*/.Assets.at("stylesheets/bootstrap-responsive.min.css"))),format.raw/*16.108*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*17.48*/routes/*17.54*/.Assets.at("stylesheets/global.css"))),format.raw/*17.90*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*18.48*/routes/*18.54*/.Assets.at("stylesheets/admin.css"))),format.raw/*18.89*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*19.48*/routes/*19.54*/.Assets.at("stylesheets/subnav.css"))),format.raw/*19.90*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*20.48*/routes/*20.54*/.Assets.at("stylesheets/forex.css"))),format.raw/*20.89*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*21.48*/routes/*21.54*/.Assets.at("stylesheets/datepicker.css"))),format.raw/*21.94*/("""">
		<link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*22.48*/routes/*22.54*/.Assets.at("stylesheets/colorpicker.css"))),format.raw/*22.95*/("""">
        <link rel="stylesheet" href=""""),_display_(Seq[Any](/*23.39*/routes/*23.45*/.User.css(Long.parseLong(session.get("userId"))))),format.raw/*23.93*/("""">
		<link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*24.53*/routes/*24.59*/.Assets.at("images/favicon.png"))),format.raw/*24.91*/("""">

		<script src=""""),_display_(Seq[Any](/*26.17*/routes/*26.23*/.Assets.at("javascripts/modernizr.custom.45033.js"))),format.raw/*26.74*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*27.17*/routes/*27.23*/.Assets.at("javascripts/jquery-1.7.1.min.js"))),format.raw/*27.68*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*28.17*/routes/*28.23*/.Assets.at("javascripts/rails.js"))),format.raw/*28.57*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*29.17*/routes/*29.23*/.Assets.at("javascripts/moment.min.js"))),format.raw/*29.62*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*30.17*/routes/*30.23*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*30.65*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*31.17*/routes/*31.23*/.Assets.at("javascripts/bootstrap/bootstrap-colorpicker.js"))),format.raw/*31.83*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*32.17*/routes/*32.23*/.Assets.at("javascripts/jquery.validate.min.js"))),format.raw/*32.71*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*33.17*/routes/*33.23*/.Assets.at("javascripts/jquery.maskedinput.min.js"))),format.raw/*33.74*/("""" type="text/javascript" charset="utf-8"></script>
		<script src=""""),_display_(Seq[Any](/*34.17*/routes/*34.23*/.Assets.at("javascripts/jquery.inputmask.bundle.min.js"))),format.raw/*34.79*/("""" type="text/javascript" charset="utf-8"></script>
		<script src=""""),_display_(Seq[Any](/*35.17*/routes/*35.23*/.Assets.at("javascripts/jquery.chainedSelects.js"))),format.raw/*35.73*/("""" type="text/javascript"></script>
		<script src=""""),_display_(Seq[Any](/*36.17*/routes/*36.23*/.Assets.at("javascripts/form.js"))),format.raw/*36.56*/("""" type="text/javascript"></script>
		<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]--.min

		<!-- Le fav and touch icons -->
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">

		<style type="text/css">
			.navbar-inner, .navbar .dropdown-menu """),format.raw/*49.42*/("""{"""),format.raw/*49.43*/("""
				background-image: -moz-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*50.59*/session/*50.66*/.get("color1"))),format.raw/*50.80*/(""");
				background-image: -ms-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*51.58*/session/*51.65*/.get("color1"))),format.raw/*51.79*/(""");
				background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#3C4152), to("""),_display_(Seq[Any](/*52.80*/session/*52.87*/.get("color1"))),format.raw/*52.101*/("""));
				background-image: -webkit-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*53.62*/session/*53.69*/.get("color1"))),format.raw/*53.83*/(""");
				background-image: -o-linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*54.57*/session/*54.64*/.get("color1"))),format.raw/*54.78*/(""");
				background-image: linear-gradient(top, #3C4152, """),_display_(Seq[Any](/*55.54*/session/*55.61*/.get("color1"))),format.raw/*55.75*/(""");
				filter: progid:dximagetransform.microsoft.gradient(startColorstr='#3C4152', endColorstr='"""),_display_(Seq[Any](/*56.95*/session/*56.102*/.get("color1"))),format.raw/*56.116*/("""', GradientType=0);
			"""),format.raw/*57.4*/("""}"""),format.raw/*57.5*/("""
			.navbar .dropdown-menu::after """),format.raw/*58.34*/("""{"""),format.raw/*58.35*/("""
				border-bottom: 6px solid """),_display_(Seq[Any](/*59.31*/session/*59.38*/.get("color1"))),format.raw/*59.52*/(""";
			"""),format.raw/*60.4*/("""}"""),format.raw/*60.5*/("""

			.navbar .divider-vertical """),format.raw/*62.30*/("""{"""),format.raw/*62.31*/("""
				background-color: """),_display_(Seq[Any](/*63.24*/session/*63.31*/.get("color1"))),format.raw/*63.45*/(""";
				border-right: 1px solid """),_display_(Seq[Any](/*64.30*/session/*64.37*/.get("color1"))),format.raw/*64.51*/(""";
			"""),format.raw/*65.4*/("""}"""),format.raw/*65.5*/("""

			.navbar .nav > li a, .role """),format.raw/*67.31*/("""{"""),format.raw/*67.32*/("""
				text-shadow: 0px 0px 0px #000;
				color: """),_display_(Seq[Any](/*69.13*/session/*69.20*/.get("color2"))),format.raw/*69.34*/(""";
			"""),format.raw/*70.4*/("""}"""),format.raw/*70.5*/("""
		</style>
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

			<a class="brand">
              <img src=""""),_display_(Seq[Any](/*86.26*/routes/*86.32*/.Corporate.logo(session.get("corporateCode"), "small"))),format.raw/*86.86*/("""" width=60/>
            </a>

            <div class="nav-collapse collapse">
                <ul class="nav nav-pills">
                    <li><a href=""""),_display_(Seq[Any](/*91.35*/routes/*91.41*/.Corporate.admin_index())),format.raw/*91.65*/("""">"""),_display_(Seq[Any](/*91.68*/doku/*91.72*/.kirimdoku.util.MultiLanguage.getLanguage("LANG190","Corporates management"))),format.raw/*91.148*/("""</a></li>
					"""),_display_(Seq[Any](/*92.7*/restrict(la(as("admin")))/*92.32*/ {_display_(Seq[Any](format.raw/*92.34*/("""
					<li><a href=""""),_display_(Seq[Any](/*93.20*/routes/*93.26*/.Customer.list())),format.raw/*93.42*/("""">"""),_display_(Seq[Any](/*93.45*/doku/*93.49*/.kirimdoku.util.MultiLanguage.getLanguage("LANG278","Customers management"))),format.raw/*93.124*/("""</a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">"""),_display_(Seq[Any](/*95.83*/doku/*95.87*/.kirimdoku.util.MultiLanguage.getLanguage("LANG469","Operation"))),format.raw/*95.151*/(""" <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href=""""),_display_(Seq[Any](/*97.22*/routes/*97.28*/.Transaction.admin_refund())),format.raw/*97.55*/("""">"""),_display_(Seq[Any](/*97.58*/doku/*97.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG468","Authorize full refund"))),format.raw/*97.138*/("""</a></li>
							<li><a href=""""),_display_(Seq[Any](/*98.22*/routes/*98.28*/.Banks.list())),format.raw/*98.41*/("""">"""),_display_(Seq[Any](/*98.44*/doku/*98.48*/.kirimdoku.util.MultiLanguage.getLanguage("LANG391","Manage List of Bank"))),format.raw/*98.122*/("""</a></li>
							<li><a href=""""),_display_(Seq[Any](/*99.22*/routes/*99.28*/.Channels.list())),format.raw/*99.44*/("""">"""),_display_(Seq[Any](/*99.47*/doku/*99.51*/.kirimdoku.util.MultiLanguage.getLanguage("LANG467","Manage Bank Routing"))),format.raw/*99.125*/("""</a></li>
						</ul>
					</li>
					""")))})),format.raw/*102.7*/("""
                    <li><a href=""""),_display_(Seq[Any](/*103.35*/routes/*103.41*/.Report.list())),format.raw/*103.55*/("""">"""),_display_(Seq[Any](/*103.58*/doku/*103.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG017","Report"))),format.raw/*103.123*/("""</a></li>
                    <li><a href=""""),_display_(Seq[Any](/*104.35*/routes/*104.41*/.Transaction.admin_list())),format.raw/*104.66*/("""">"""),_display_(Seq[Any](/*104.69*/doku/*104.73*/.kirimdoku.util.MultiLanguage.getLanguage("LANG008","Transaction"))),format.raw/*104.139*/("""</a></li>
					"""),_display_(Seq[Any](/*105.7*/restrict(la(as("finance")))/*105.34*/ {_display_(Seq[Any](format.raw/*105.36*/("""
                    
			        <li><a href=""""),_display_(Seq[Any](/*107.26*/routes/*107.32*/.SetupCutOffTime.view())),format.raw/*107.55*/("""">"""),_display_(Seq[Any](/*107.58*/doku/*107.62*/.kirimdoku.util.MultiLanguage.getLanguage("LANG442","Setup Cut-Off Time"))),format.raw/*107.135*/("""</a></li>
			        <li><a href=""""),_display_(Seq[Any](/*108.26*/routes/*108.32*/.SettlementReport.list())),format.raw/*108.56*/("""">"""),_display_(Seq[Any](/*108.59*/doku/*108.63*/.kirimdoku.util.MultiLanguage.getLanguage("LANG444","Settlement Report"))),format.raw/*108.135*/("""</a></li>
					""")))})),format.raw/*109.7*/("""
					"""),_display_(Seq[Any](/*110.7*/restrict(la(as("admin")))/*110.32*/ {_display_(Seq[Any](format.raw/*110.34*/("""
                    <li><a href=""""),_display_(Seq[Any](/*111.35*/routes/*111.41*/.Audit.admin_index())),format.raw/*111.61*/("""">"""),_display_(Seq[Any](/*111.64*/doku/*111.68*/.kirimdoku.util.MultiLanguage.getLanguage("LANG466","User Audit"))),format.raw/*111.133*/("""</a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href=""""),_display_(Seq[Any](/*113.80*/routes/*113.86*/.Dashboard.admin_index())),format.raw/*113.110*/("""">"""),_display_(Seq[Any](/*113.113*/doku/*113.117*/.kirimdoku.util.MultiLanguage.getLanguage("LANG465","Blacklist"))),format.raw/*113.181*/(""" <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href=""""),_display_(Seq[Any](/*115.22*/routes/*115.28*/.CustomerBan.admin_interdictions())),format.raw/*115.62*/("""">"""),_display_(Seq[Any](/*115.65*/doku/*115.69*/.kirimdoku.util.MultiLanguage.getLanguage("LANG194","Interdictions list"))),format.raw/*115.142*/("""</a></li>
							<li><a href=""""),_display_(Seq[Any](/*116.22*/routes/*116.28*/.CustomerBan.admin_list())),format.raw/*116.53*/("""">"""),_display_(Seq[Any](/*116.56*/doku/*116.60*/.kirimdoku.util.MultiLanguage.getLanguage("LANG193","Banned list"))),format.raw/*116.126*/("""</a></li>
							<li><a href=""""),_display_(Seq[Any](/*117.22*/routes/*117.28*/.Suspicious.admin_index())),format.raw/*117.53*/("""">"""),_display_(Seq[Any](/*117.56*/doku/*117.60*/.kirimdoku.util.MultiLanguage.getLanguage("LANG195","Suspicious list"))),format.raw/*117.130*/("""</a></li>
						</ul>
					</li>
					""")))})),format.raw/*120.7*/("""
                </ul>

                <ul class="nav pull-right">
                    <li class="divider-vertical"></li>
                    <li><img src=""""),_display_(Seq[Any](/*125.36*/routes/*125.42*/.User.photo(Long.parseLong(session.get("userId")), "small"))),format.raw/*125.101*/("""" height="35px" width="35px"/></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span style='font-style: italic;'>"""),_display_(Seq[Any](/*127.119*/doku/*127.123*/.kirimdoku.util.MultiLanguage.getLanguage("LANG021","Hello"))),format.raw/*127.183*/(""", </span> """),_display_(Seq[Any](/*127.194*/session/*127.201*/.get("userDisplayName"))),format.raw/*127.224*/("""<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href=""""),_display_(Seq[Any](/*130.43*/routes/*130.49*/.User.show(session.get("corporateCode"), Long.parseLong(session.get("userId"))))),format.raw/*130.128*/(""""><i class="icon-user"></i> """),_display_(Seq[Any](/*130.157*/doku/*130.161*/.kirimdoku.util.MultiLanguage.getLanguage("LANG022","My Account"))),format.raw/*130.226*/("""</a>
                            </li>
                            <li>
                                <a href=""""),_display_(Seq[Any](/*133.43*/routes/*133.49*/.Login.admin_logout)),format.raw/*133.68*/(""""><i class="icon-user"></i> """),_display_(Seq[Any](/*133.97*/doku/*133.101*/.kirimdoku.util.MultiLanguage.getLanguage("LANG023","Sign out"))),format.raw/*133.164*/("""</a>
                            </li>
                        </ul>
						<span class="role">
						"""),_display_(Seq[Any](/*137.8*/restrict(la(as("admin")))/*137.33*/ {_display_(Seq[Any](format.raw/*137.35*/("""
						Admin
						""")))})),format.raw/*139.8*/("""
						"""),_display_(Seq[Any](/*140.8*/restrict(la(as("finance")))/*140.35*/ {_display_(Seq[Any](format.raw/*140.37*/("""
						Finance
						""")))})),format.raw/*142.8*/("""
						"""),_display_(Seq[Any](/*143.8*/doku/*143.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG250","of"))),format.raw/*143.69*/(""" """),_display_(Seq[Any](/*143.71*/session/*143.78*/.get("corporateCode"))),format.raw/*143.99*/("""
						</span>
                    </li>
                </ul>

            </div>

        </div>
    </div>
</div>


<header visible-desktop>
    <div class="pull-right" id="datetimeContainer">
        <span id="datetime">Date</span>
    </div>
	<div>
		"""),_display_(Seq[Any](/*160.4*/if(breadcrumbs != null)/*160.27*/ {_display_(Seq[Any](format.raw/*160.29*/("""
		"""),_display_(Seq[Any](/*161.4*/Html(Breadcrumb.build(breadcrumbs)))),format.raw/*161.39*/("""
		""")))}/*162.5*/else/*162.10*/{_display_(Seq[Any](format.raw/*162.11*/("""
		"""),_display_(Seq[Any](/*163.4*/Html(Breadcrumb.parse(request.uri)))),format.raw/*163.39*/("""
		""")))})),format.raw/*164.4*/("""
    </div>

    <!-- <h1>Group : """),_display_(Seq[Any](/*167.23*/session/*167.30*/.get("group"))),format.raw/*167.43*/("""</h1> -->

</header>

<div class="container">
	<section class="content">
		"""),_display_(Seq[Any](/*173.4*/content)),format.raw/*173.11*/("""
	</section>
</div>
<script src=""""),_display_(Seq[Any](/*176.15*/routes/*176.21*/.Assets.at("javascripts/global.js"))),format.raw/*176.56*/("""" type="text/javascript"></script>
	<script src=""""),_display_(Seq[Any](/*177.16*/routes/*177.22*/.Assets.at("javascripts/typeahead.min.js"))),format.raw/*177.64*/("""" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() """),format.raw/*179.16*/("""{"""),format.raw/*179.17*/("""
			$("#datetime").timeTicker();
		"""),format.raw/*181.3*/("""}"""),format.raw/*181.4*/(""");
		$.fn.timeTicker = function() """),format.raw/*182.32*/("""{"""),format.raw/*182.33*/("""
			this.each(function(i, el) """),format.raw/*183.30*/("""{"""),format.raw/*183.31*/("""
				var updateTime = function() """),format.raw/*184.33*/("""{"""),format.raw/*184.34*/("""
					$(el).html(moment().format('dddd, MMMM Do YYYY, h:mm:ss a'));
					setTimeout(updateTime, 1000);
				"""),format.raw/*187.5*/("""}"""),format.raw/*187.6*/(""";
				updateTime()
			"""),format.raw/*189.4*/("""}"""),format.raw/*189.5*/(""");
		"""),format.raw/*190.3*/("""}"""),format.raw/*190.4*/("""
	</script>
</body>
</html>
"""))}
    }
    
    def render(title:String,breadcrumbs:Seq[scala.Tuple2[String, String]],content:Html): play.api.templates.Html = apply(title,breadcrumbs)(content)
    
    def f:((String,Seq[scala.Tuple2[String, String]]) => (Html) => play.api.templates.Html) = (title,breadcrumbs) => (content) => apply(title,breadcrumbs)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:23 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/layoutAdmin.scala.html
                    HASH: 78a364a18644f20c9c320662ffc5ccb7342080b9
                    MATRIX: 768->1|1046->73|1073->99|1100->205|1291->360|1318->365|1410->421|1425->427|1490->470|1576->520|1591->526|1668->580|1754->630|1769->636|1827->672|1913->722|1928->728|1985->763|2071->813|2086->819|2144->855|2230->905|2245->911|2302->946|2388->996|2403->1002|2465->1042|2551->1092|2566->1098|2629->1139|2706->1180|2721->1186|2791->1234|2882->1289|2897->1295|2951->1327|3007->1347|3022->1353|3095->1404|3182->1455|3197->1461|3264->1506|3351->1557|3366->1563|3422->1597|3509->1648|3524->1654|3585->1693|3672->1744|3687->1750|3751->1792|3838->1843|3853->1849|3935->1909|4022->1960|4037->1966|4107->2014|4194->2065|4209->2071|4282->2122|4385->2189|4400->2195|4478->2251|4581->2318|4596->2324|4668->2374|4755->2425|4770->2431|4825->2464|5620->3231|5649->3232|5744->3291|5760->3298|5796->3312|5892->3372|5908->3379|5944->3393|6062->3475|6078->3482|6115->3496|6216->3561|6232->3568|6268->3582|6363->3641|6379->3648|6415->3662|6507->3718|6523->3725|6559->3739|6692->3836|6709->3843|6746->3857|6796->3880|6824->3881|6886->3915|6915->3916|6982->3947|6998->3954|7034->3968|7066->3973|7094->3974|7153->4005|7182->4006|7242->4030|7258->4037|7294->4051|7361->4082|7377->4089|7413->4103|7445->4108|7473->4109|7533->4141|7562->4142|7646->4190|7662->4197|7698->4211|7730->4216|7758->4217|8244->4667|8259->4673|8335->4727|8527->4883|8542->4889|8588->4913|8627->4916|8640->4920|8739->4996|8790->5012|8824->5037|8864->5039|8920->5059|8935->5065|8973->5081|9012->5084|9025->5088|9123->5163|9278->5282|9291->5286|9378->5350|9507->5443|9522->5449|9571->5476|9610->5479|9623->5483|9722->5559|9789->5590|9804->5596|9839->5609|9878->5612|9891->5616|9988->5690|10055->5721|10070->5727|10108->5743|10147->5746|10160->5750|10257->5824|10328->5863|10400->5898|10416->5904|10453->5918|10493->5921|10507->5925|10592->5986|10673->6030|10689->6036|10737->6061|10777->6064|10791->6068|10881->6134|10933->6150|10970->6177|11011->6179|11095->6226|11111->6232|11157->6255|11197->6258|11211->6262|11308->6335|11380->6370|11396->6376|11443->6400|11483->6403|11497->6407|11593->6479|11641->6495|11684->6502|11719->6527|11760->6529|11832->6564|11848->6570|11891->6590|11931->6593|11945->6597|12034->6662|12187->6778|12203->6784|12251->6808|12292->6811|12307->6815|12395->6879|12525->6972|12541->6978|12598->7012|12638->7015|12652->7019|12749->7092|12817->7123|12833->7129|12881->7154|12921->7157|12935->7161|13025->7227|13093->7258|13109->7264|13157->7289|13197->7292|13211->7296|13305->7366|13376->7405|13571->7563|13587->7569|13670->7628|13904->7824|13919->7828|14003->7888|14052->7899|14070->7906|14117->7929|14306->8081|14322->8087|14425->8166|14492->8195|14507->8199|14596->8264|14747->8378|14763->8384|14805->8403|14871->8432|14886->8436|14973->8499|15111->8601|15146->8626|15187->8628|15239->8648|15283->8656|15320->8683|15361->8685|15415->8707|15459->8715|15473->8719|15553->8776|15592->8778|15609->8785|15653->8806|15946->9063|15979->9086|16020->9088|16060->9092|16118->9127|16141->9132|16155->9137|16195->9138|16235->9142|16293->9177|16329->9181|16401->9216|16418->9223|16454->9236|16566->9312|16596->9319|16667->9353|16683->9359|16741->9394|16828->9444|16844->9450|16909->9492|17021->9575|17051->9576|17114->9611|17143->9612|17206->9646|17236->9647|17295->9677|17325->9678|17387->9711|17417->9712|17552->9819|17581->9820|17631->9842|17660->9843|17693->9848|17722->9849
                    LINES: 26->1|34->1|35->3|36->6|44->14|44->14|45->15|45->15|45->15|46->16|46->16|46->16|47->17|47->17|47->17|48->18|48->18|48->18|49->19|49->19|49->19|50->20|50->20|50->20|51->21|51->21|51->21|52->22|52->22|52->22|53->23|53->23|53->23|54->24|54->24|54->24|56->26|56->26|56->26|57->27|57->27|57->27|58->28|58->28|58->28|59->29|59->29|59->29|60->30|60->30|60->30|61->31|61->31|61->31|62->32|62->32|62->32|63->33|63->33|63->33|64->34|64->34|64->34|65->35|65->35|65->35|66->36|66->36|66->36|79->49|79->49|80->50|80->50|80->50|81->51|81->51|81->51|82->52|82->52|82->52|83->53|83->53|83->53|84->54|84->54|84->54|85->55|85->55|85->55|86->56|86->56|86->56|87->57|87->57|88->58|88->58|89->59|89->59|89->59|90->60|90->60|92->62|92->62|93->63|93->63|93->63|94->64|94->64|94->64|95->65|95->65|97->67|97->67|99->69|99->69|99->69|100->70|100->70|116->86|116->86|116->86|121->91|121->91|121->91|121->91|121->91|121->91|122->92|122->92|122->92|123->93|123->93|123->93|123->93|123->93|123->93|125->95|125->95|125->95|127->97|127->97|127->97|127->97|127->97|127->97|128->98|128->98|128->98|128->98|128->98|128->98|129->99|129->99|129->99|129->99|129->99|129->99|132->102|133->103|133->103|133->103|133->103|133->103|133->103|134->104|134->104|134->104|134->104|134->104|134->104|135->105|135->105|135->105|137->107|137->107|137->107|137->107|137->107|137->107|138->108|138->108|138->108|138->108|138->108|138->108|139->109|140->110|140->110|140->110|141->111|141->111|141->111|141->111|141->111|141->111|143->113|143->113|143->113|143->113|143->113|143->113|145->115|145->115|145->115|145->115|145->115|145->115|146->116|146->116|146->116|146->116|146->116|146->116|147->117|147->117|147->117|147->117|147->117|147->117|150->120|155->125|155->125|155->125|157->127|157->127|157->127|157->127|157->127|157->127|160->130|160->130|160->130|160->130|160->130|160->130|163->133|163->133|163->133|163->133|163->133|163->133|167->137|167->137|167->137|169->139|170->140|170->140|170->140|172->142|173->143|173->143|173->143|173->143|173->143|173->143|190->160|190->160|190->160|191->161|191->161|192->162|192->162|192->162|193->163|193->163|194->164|197->167|197->167|197->167|203->173|203->173|206->176|206->176|206->176|207->177|207->177|207->177|209->179|209->179|211->181|211->181|212->182|212->182|213->183|213->183|214->184|214->184|217->187|217->187|219->189|219->189|220->190|220->190
                    -- GENERATED --
                */
            