
package views.html.partial

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
object paginate extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template5[Int,Int,Int,_root_.scala.Function1[Int, Call],Int,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(page:Int, pageLength:Int, collectionLength:Int, route:Int => Call, bound:Int = 5):play.api.templates.Html = {
        _display_ {
def /*2.2*/lowbound/*2.10*/() = {{
    ((page.toDouble / bound).floor * bound) toInt
}};def /*5.2*/highbound/*5.11*/() = {{
    if ((lowbound() + bound) * pageLength >= collectionLength)
        collectionLength / pageLength + 1
    else
        lowbound() + bound
}};
Seq[Any](format.raw/*1.84*/("""
"""),format.raw/*4.2*/("""
"""),format.raw/*10.2*/("""
 
<div class="pb_centered">
    <div class="pagination">
        <ul>
            """),_display_(Seq[Any](/*15.14*/if(page == 1)/*15.27*/{_display_(Seq[Any](format.raw/*15.28*/("""
                <li class="disabled"><a href="#">Previous</a></li>
            """)))}/*17.15*/else/*17.20*/{_display_(Seq[Any](format.raw/*17.21*/("""
                <li><a href=""""),_display_(Seq[Any](/*18.31*/route(page-1))),format.raw/*18.44*/("""">Previous</a></li>
 
            """)))})),format.raw/*20.14*/("""
 
            """),_display_(Seq[Any](/*22.14*/if(page < bound)/*22.30*/ {_display_(Seq[Any](format.raw/*22.32*/("""
                <li class="disabled"><a href="#">&laquo;</a></li>
            """)))}/*24.15*/else/*24.20*/{_display_(Seq[Any](format.raw/*24.21*/("""
                """),_display_(Seq[Any](/*25.18*/if(lowbound()-bound <= 0)/*25.43*/ {_display_(Seq[Any](format.raw/*25.45*/("""
                    <li><a href=""""),_display_(Seq[Any](/*26.35*/route(1))),format.raw/*26.43*/("""">&laquo;</a></li>
                """)))}/*27.19*/else/*27.24*/{_display_(Seq[Any](format.raw/*27.25*/("""
                    <li><a href=""""),_display_(Seq[Any](/*28.35*/route(lowbound()-bound))),format.raw/*28.58*/("""">&laquo;</a></li>
                """)))})),format.raw/*29.18*/("""
            """)))})),format.raw/*30.14*/("""
 
            """),_display_(Seq[Any](/*32.14*/for(i <- lowbound().max(1) until page) yield /*32.52*/ {_display_(Seq[Any](format.raw/*32.54*/("""
                <li><a href=""""),_display_(Seq[Any](/*33.31*/route(i))),format.raw/*33.39*/("""">"""),_display_(Seq[Any](/*33.42*/i)),format.raw/*33.43*/("""</a></li>
            """)))})),format.raw/*34.14*/("""
 
            <li class="active"><a href="#">"""),_display_(Seq[Any](/*36.45*/page)),format.raw/*36.49*/("""</a></li>
 
            """),_display_(Seq[Any](/*38.14*/for(i <- page+1 to highbound()) yield /*38.45*/ {_display_(Seq[Any](format.raw/*38.47*/("""
                <li><a href=""""),_display_(Seq[Any](/*39.31*/route(i))),format.raw/*39.39*/("""">"""),_display_(Seq[Any](/*39.42*/i)),format.raw/*39.43*/("""</a></li>
            """)))})),format.raw/*40.14*/("""
 
            """),_display_(Seq[Any](/*42.14*/if(highbound() * pageLength <= collectionLength && highbound() > page && page * pageLength != collectionLength)/*42.125*/ {_display_(Seq[Any](format.raw/*42.127*/("""
                <li><a href=""""),_display_(Seq[Any](/*43.31*/route(highbound()))),format.raw/*43.49*/("""">&raquo;</a></li>
            """)))}/*44.15*/else/*44.20*/{_display_(Seq[Any](format.raw/*44.21*/("""
                <li class="disabled"><a href="#">&raquo;</a></li>
            """)))})),format.raw/*46.14*/("""
 
            """),_display_(Seq[Any](/*48.14*/if(page < highbound())/*48.36*/ {_display_(Seq[Any](format.raw/*48.38*/("""
                <li><a href=""""),_display_(Seq[Any](/*49.31*/route(page+1))),format.raw/*49.44*/("""">Next</a></li>
            """)))}/*50.15*/else/*50.20*/{_display_(Seq[Any](format.raw/*50.21*/("""
                <li class="disabled"><a href="#">Next</a></li>
 
            """)))})),format.raw/*53.14*/("""
        </ul>
    </div>
</div>
"""))}
    }
    
    def render(page:Int,pageLength:Int,collectionLength:Int,route:_root_.scala.Function1[Int, Call],bound:Int): play.api.templates.Html = apply(page,pageLength,collectionLength,route,bound)
    
    def f:((Int,Int,Int,_root_.scala.Function1[Int, Call],Int) => play.api.templates.Html) = (page,pageLength,collectionLength,route,bound) => apply(page,pageLength,collectionLength,route,bound)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:27 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/partial/paginate.scala.html
                    HASH: 7c2d3e66dd40cb02535b3a9862752264bcbff106
                    MATRIX: 777->1|919->85|935->93|1007->154|1024->163|1204->83|1231->152|1259->313|1379->397|1401->410|1440->411|1540->493|1553->498|1592->499|1659->530|1694->543|1761->578|1813->594|1838->610|1878->612|1977->693|1990->698|2029->699|2083->717|2117->742|2157->744|2228->779|2258->787|2313->824|2326->829|2365->830|2436->865|2481->888|2549->924|2595->938|2647->954|2701->992|2741->994|2808->1025|2838->1033|2877->1036|2900->1037|2955->1060|3038->1107|3064->1111|3125->1136|3172->1167|3212->1169|3279->1200|3309->1208|3348->1211|3371->1212|3426->1235|3478->1251|3599->1362|3640->1364|3707->1395|3747->1413|3798->1446|3811->1451|3850->1452|3962->1532|4014->1548|4045->1570|4085->1572|4152->1603|4187->1616|4235->1646|4248->1651|4287->1652|4398->1731
                    LINES: 26->1|28->2|28->2|30->5|30->5|36->1|37->4|38->10|43->15|43->15|43->15|45->17|45->17|45->17|46->18|46->18|48->20|50->22|50->22|50->22|52->24|52->24|52->24|53->25|53->25|53->25|54->26|54->26|55->27|55->27|55->27|56->28|56->28|57->29|58->30|60->32|60->32|60->32|61->33|61->33|61->33|61->33|62->34|64->36|64->36|66->38|66->38|66->38|67->39|67->39|67->39|67->39|68->40|70->42|70->42|70->42|71->43|71->43|72->44|72->44|72->44|74->46|76->48|76->48|76->48|77->49|77->49|78->50|78->50|78->50|81->53
                    -- GENERATED --
                */
            