
package views.html.dashboard

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
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template2[models.User,List[models.News],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(user:models.User, news: List[models.News]):play.api.templates.Html = {
        _display_ {import be.objectify.deadbolt.java.views.html._

import be.objectify.deadbolt.core.utils.TemplateUtils._


Seq[Any](format.raw/*1.45*/("""
"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/layout("Dashboard")/*5.21*/{_display_(Seq[Any](format.raw/*5.22*/("""
<div class="page-header">
	<h1>"""),_display_(Seq[Any](/*7.7*/doku/*7.11*/.kirimdoku.util.MultiLanguage.getLanguage("LANG024","Dashboard"))),format.raw/*7.75*/("""</h1>
</div>

"""),_display_(Seq[Any](/*10.2*/partial/*10.9*/.flash_alert())),format.raw/*10.23*/("""

<div class="row dashboard-region">
	<div class="span7 dashboard-statistics" data-user=""""),_display_(Seq[Any](/*13.54*/user/*13.58*/.id)),format.raw/*13.61*/("""">
		<div class="loading"></div>
	</div>
	<div class="span5 dashboard-pane">
		<div class="box box-bordered">
			<div class="page-subheader">
				<h4>"""),_display_(Seq[Any](/*19.10*/doku/*19.14*/.kirimdoku.util.MultiLanguage.getLanguage("LANG031","Current rate"))),format.raw/*19.81*/("""</h4>
			</div>
			<div class="dashboard-forex" data-corporate=""""),_display_(Seq[Any](/*21.50*/user/*21.54*/.corporate.code)),format.raw/*21.69*/("""">
				<div class="loading"></div>
			</div>
		</div>

		<div id="news" class="box box-bordered">
			<div id="news-carousel" class="carousel slide">
				<div class="box-content">
					<div class="page-subheader">
						<h4>"""),_display_(Seq[Any](/*30.12*/doku/*30.16*/.kirimdoku.util.MultiLanguage.getLanguage("LANG036","News"))),format.raw/*30.75*/("""</h4>
					</div>
					<!-- Carousel items -->
					<div class="carousel-inner">
						"""),_display_(Seq[Any](/*34.8*/news/*34.12*/.zipWithIndex.map/*34.29*/{ case (newsItem, i) =>_display_(Seq[Any](format.raw/*34.52*/("""
						<div class=""""),_display_(Seq[Any](/*35.20*/if(i==0)/*35.28*/{_display_(Seq[Any](format.raw/*35.29*/("""active""")))})),format.raw/*35.36*/(""" item news-content">
							<p>
								"""),_display_(Seq[Any](/*37.10*/newsItem/*37.18*/.getSubContent)),format.raw/*37.32*/("""
								<div>
									<a href=""""),_display_(Seq[Any](/*39.20*/routes/*39.26*/.Dashboard.news(newsItem.id))),format.raw/*39.54*/("""" class="pull-right muted" style="display:none">Click to read more...</a>
									&nbsp;
								</div>
							</p>
						</div>
						""")))})),format.raw/*44.8*/("""
					</div>
				</div>
				<div class="box-footer row-fluid">
					<div class="span5">&nbsp;</div>
					<div class="span4">
						<!-- Carousel indicator -->
						<ul class="nav nav-indicator">
							"""),_display_(Seq[Any](/*52.9*/news/*52.13*/.zipWithIndex.map/*52.30*/{ case (obj, i) =>_display_(Seq[Any](format.raw/*52.48*/("""
							<li """),_display_(Seq[Any](/*53.13*/if(i==0)/*53.21*/{_display_(Seq[Any](format.raw/*53.22*/("""class="selected"""")))})),format.raw/*53.39*/("""></li>
							""")))})),format.raw/*54.9*/("""
						</ul>
					</div>
					<div class="span3 right">
						<!-- Carousel nav -->
						<a class="btn btn-small btn-carousel left" href="#news-carousel" data-slide="prev"><i class="icon-chevron-left"></i></a>
						<a class="btn btn-small btn-carousel right" href="#news-carousel" data-slide="next"><i class="icon-chevron-right"></i></a>
					</div>
				</div>
			</div> <!-- Carousel -->
		</div>
	</div>


<div class="modal hide" id="newsModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>"""),_display_(Seq[Any](/*71.8*/doku/*71.12*/.kirimdoku.util.MultiLanguage.getLanguage("LANG036","News"))),format.raw/*71.71*/("""</h3>
	</div>
	<div class="modal-body">
		<p class="news-content">
			Loading...
		</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>


<script src=""""),_display_(Seq[Any](/*84.15*/routes/*84.21*/.Assets.at("javascripts/bootstrap/bootstrap-carousel.js"))),format.raw/*84.78*/(""""></script>
<script src=""""),_display_(Seq[Any](/*85.15*/routes/*85.21*/.Assets.at("javascripts/dashboard.js"))),format.raw/*85.59*/(""""></script>
""")))})),format.raw/*86.2*/("""
"""))}
    }
    
    def render(user:models.User,news:List[models.News]): play.api.templates.Html = apply(user,news)
    
    def f:((models.User,List[models.News]) => play.api.templates.Html) = (user,news) => apply(user,news)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:26 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/dashboard/index.scala.html
                    HASH: 05234f18cd71d11cc04e25f9ed03a69a82c2be6d
                    MATRIX: 756->1|980->44|1007->150|1043->152|1070->171|1108->172|1175->205|1187->209|1272->273|1322->288|1337->295|1373->309|1499->399|1512->403|1537->406|1724->557|1737->561|1826->628|1927->693|1940->697|1977->712|2237->936|2250->940|2331->999|2454->1087|2467->1091|2493->1108|2554->1131|2610->1151|2627->1159|2666->1160|2705->1167|2782->1208|2799->1216|2835->1230|2905->1264|2920->1270|2970->1298|3138->1435|3377->1639|3390->1643|3416->1660|3472->1678|3521->1691|3538->1699|3577->1700|3626->1717|3672->1732|4262->2287|4275->2291|4356->2350|4624->2582|4639->2588|4718->2645|4780->2671|4795->2677|4855->2715|4899->2728
                    LINES: 26->1|32->1|33->4|34->5|34->5|34->5|36->7|36->7|36->7|39->10|39->10|39->10|42->13|42->13|42->13|48->19|48->19|48->19|50->21|50->21|50->21|59->30|59->30|59->30|63->34|63->34|63->34|63->34|64->35|64->35|64->35|64->35|66->37|66->37|66->37|68->39|68->39|68->39|73->44|81->52|81->52|81->52|81->52|82->53|82->53|82->53|82->53|83->54|100->71|100->71|100->71|113->84|113->84|113->84|114->85|114->85|114->85|115->86
                    -- GENERATED --
                */
            