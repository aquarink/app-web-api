
package views.html.send

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
object customer_search extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[com.avaje.ebean.Page[models.Customer],play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[models.Customer]):play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.54*/("""

<div class="page-subheader">
	<h4>Search Results (Name - MemberId - PersonalID)</h4>
</div>

"""),_display_(Seq[Any](/*7.2*/if(currentPage.getTotalRowCount<=0)/*7.37*/ {_display_(Seq[Any](format.raw/*7.39*/("""
<div class="alert alert-message">No customers found by specified search</div>
""")))}/*9.3*/else/*9.8*/{_display_(Seq[Any](format.raw/*9.9*/("""
<ul class="list-customers">
"""),_display_(Seq[Any](/*11.2*/for(customer <- currentPage.getList) yield /*11.38*/ {_display_(Seq[Any](format.raw/*11.40*/("""
	<li>
		<div class="pull-right"><a href="#" class="select" data-customerId=""""),_display_(Seq[Any](/*13.72*/customer/*13.80*/.id)),format.raw/*13.83*/("""">Select</a></div>
		"""),_display_(Seq[Any](/*14.4*/customer/*14.12*/.fullName)),format.raw/*14.21*/(""" - """),_display_(Seq[Any](/*14.25*/customer/*14.33*/.id)),format.raw/*14.36*/(""" - """),_display_(Seq[Any](/*14.40*/customer/*14.48*/.personalIdType)),format.raw/*14.63*/(""" """),_display_(Seq[Any](/*14.65*/customer/*14.73*/.personalId)),format.raw/*14.84*/("""
	</li>
""")))})),format.raw/*16.2*/("""
</ul>
""")))})),format.raw/*18.2*/("""
"""))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[models.Customer]): play.api.templates.Html = apply(currentPage)
    
    def f:((com.avaje.ebean.Page[models.Customer]) => play.api.templates.Html) = (currentPage) => apply(currentPage)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/customer_search.scala.html
                    HASH: a3690843fa72901ad52aca2098867800a8b6f966
                    MATRIX: 769->1|898->53|1028->149|1071->184|1110->186|1207->267|1218->272|1255->273|1320->303|1372->339|1412->341|1526->419|1543->427|1568->430|1625->452|1642->460|1673->469|1713->473|1730->481|1755->484|1795->488|1812->496|1849->511|1887->513|1904->521|1937->532|1977->541|2016->549
                    LINES: 26->1|29->1|35->7|35->7|35->7|37->9|37->9|37->9|39->11|39->11|39->11|41->13|41->13|41->13|42->14|42->14|42->14|42->14|42->14|42->14|42->14|42->14|42->14|42->14|42->14|42->14|44->16|46->18
                    -- GENERATED --
                */
            