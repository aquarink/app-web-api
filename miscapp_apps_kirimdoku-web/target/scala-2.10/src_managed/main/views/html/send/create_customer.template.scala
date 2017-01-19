
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
object create_customer extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template3[Form[models.Customer],Boolean,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(myForm: Form[models.Customer], isEdit: Boolean, countryCode: String):play.api.templates.Html = {
        _display_ {import views.html.customer.formBody

def /*5.2*/actionUrl/*5.11*/ = {{
	if(isEdit) {
		routes.Customer.update(myForm.get.id)
	} else {
		routes.Customer.save
	}
}};
Seq[Any](format.raw/*1.71*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*11.2*/("""

"""),_display_(Seq[Any](/*13.2*/helper/*13.8*/.form(routes.Send.customerSave)/*13.39*/ {_display_(Seq[Any](format.raw/*13.41*/("""
"""),_display_(Seq[Any](/*14.2*/if(isEdit)/*14.12*/ {_display_(Seq[Any](format.raw/*14.14*/("""
<input type="hidden" name="id" value=""""),_display_(Seq[Any](/*15.40*/myForm/*15.46*/.get().id)),format.raw/*15.55*/(""""/>
""")))}/*16.3*/else/*16.8*/{_display_(Seq[Any](format.raw/*16.9*/("""
<input type="hidden" name="id" value=""/>
""")))})),format.raw/*18.2*/("""

"""),_display_(Seq[Any](/*20.2*/formBody(myForm, isEdit))),format.raw/*20.26*/("""

<input type="hidden" id="countryCodeSelected" name="countryCodeSelected" value=""""),_display_(Seq[Any](/*22.82*/{countryCode})),format.raw/*22.95*/("""" />
<button type="submit" class="btn"><i class="icon-ok"></i> Save</button>

<script type="text/javascript" src='"""),_display_(Seq[Any](/*25.38*/routes/*25.44*/.Assets.at("javascripts/customer.js"))),format.raw/*25.81*/("""'></script>
""")))})),format.raw/*26.2*/("""
"""))}
    }
    
    def render(myForm:Form[models.Customer],isEdit:Boolean,countryCode:String): play.api.templates.Html = apply(myForm,isEdit,countryCode)
    
    def f:((Form[models.Customer],Boolean,String) => play.api.templates.Html) = (myForm,isEdit,countryCode) => apply(myForm,isEdit,countryCode)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Jan 18 18:12:30 WIB 2017
                    SOURCE: /Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/app/views/send/create_customer.scala.html
                    HASH: 804f22f07a2e96a53c05ba7253403069c67457a2
                    MATRIX: 768->1|933->111|950->120|1077->70|1105->109|1133->217|1171->220|1185->226|1225->257|1265->259|1302->261|1321->271|1361->273|1437->313|1452->319|1483->328|1506->334|1518->339|1556->340|1631->384|1669->387|1715->411|1834->494|1869->507|2020->622|2035->628|2094->665|2138->678
                    LINES: 26->1|29->5|29->5|36->1|38->4|39->11|41->13|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|46->18|48->20|48->20|50->22|50->22|53->25|53->25|53->25|54->26
                    -- GENERATED --
                */
            