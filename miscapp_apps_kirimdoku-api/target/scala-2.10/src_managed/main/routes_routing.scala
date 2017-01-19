// @SOURCE:/Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-api/conf/routes
// @HASH:2898bd96ef8ea1671a412e4b9e702108ecb1820f
// @DATE:Mon Jan 16 10:17:34 WIB 2017


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Lab_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Network_ping1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ping"))))
        

// @LINE:10
private[this] lazy val controllers_Network_pings2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ping"))))
        

// @LINE:13
private[this] lazy val controllers_CashIn_inquiry3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashin/inquiry"))))
        

// @LINE:14
private[this] lazy val controllers_CashIn_remit4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashin/remit"))))
        

// @LINE:17
private[this] lazy val controllers_CashOut_inquiry5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashout/inquiry"))))
        

// @LINE:18
private[this] lazy val controllers_CashOut_validate6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashout/validate"))))
        

// @LINE:19
private[this] lazy val controllers_CashOut_collect7 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashout/collect"))))
        

// @LINE:22
private[this] lazy val controllers_Transaction_show8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:23
private[this] lazy val controllers_Transaction_summary9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:25
private[this] lazy val controllers_Transaction_search10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transactions"))))
        

// @LINE:30
private[this] lazy val controllers_Customer_show11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customers/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:36
private[this] lazy val controllers_Lab_settlement12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/settlement"))))
        

// @LINE:37
private[this] lazy val controllers_Lab_suspiciousCheck13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/check/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:38
private[this] lazy val controllers_Lab_hello14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/hello"))))
        

// @LINE:39
private[this] lazy val controllers_Lab_test115 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/1"))))
        

// @LINE:40
private[this] lazy val controllers_Lab_test216 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/2"))))
        

// @LINE:41
private[this] lazy val controllers_Lab_test317 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/3"))))
        

// @LINE:42
private[this] lazy val controllers_Lab_test418 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("lab/4"))))
        

// @LINE:45
private[this] lazy val controllers_Translate_QueryPinyin19 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("translate/pinyin"))))
        

// @LINE:46
private[this] lazy val controllers_Translate_QueryTC20 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("translate/tcode"))))
        

// @LINE:50
private[this] lazy val kirimdoku_interfaces_Login_index21 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:51
private[this] lazy val kirimdoku_interfaces_CashIn_inquiry22 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashinInquiry"))))
        

// @LINE:52
private[this] lazy val kirimdoku_interfaces_CashIn_remit23 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashinRemit"))))
        

// @LINE:54
private[this] lazy val kirimdoku_interfaces_CashOut_inquiry24 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashoutInquiry"))))
        

// @LINE:55
private[this] lazy val kirimdoku_interfaces_CashOut_validate25 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashoutValidate"))))
        

// @LINE:56
private[this] lazy val kirimdoku_interfaces_CashOut_collect26 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashoutCollect"))))
        

// @LINE:58
private[this] lazy val kirimdoku_interfaces_Customer_getCustomer27 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customerLookup"))))
        

// @LINE:59
private[this] lazy val kirimdoku_interfaces_Customer_getCustomerList28 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customerList"))))
        

// @LINE:60
private[this] lazy val kirimdoku_interfaces_Customer_addCustomer29 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customerAdd"))))
        

// @LINE:61
private[this] lazy val kirimdoku_interfaces_Customer_getCustomerSenderList30 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customerSenderList"))))
        

// @LINE:63
private[this] lazy val kirimdoku_interfaces_Transaction_getTransaction31 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transactionLookup"))))
        

// @LINE:64
private[this] lazy val kirimdoku_interfaces_Transaction_getTransactionRefund32 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getTransactionRefund"))))
        

// @LINE:65
private[this] lazy val kirimdoku_interfaces_Transaction_getTransactionChange33 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getTransactionChange"))))
        

// @LINE:66
private[this] lazy val kirimdoku_interfaces_Transaction_getTransactionLocked34 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getTransactionLocked"))))
        

// @LINE:67
private[this] lazy val kirimdoku_interfaces_Transaction_getTransactionList35 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transactionList"))))
        

// @LINE:69
private[this] lazy val kirimdoku_interfaces_CheckRate_process36 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("checkRates"))))
        

// @LINE:70
private[this] lazy val kirimdoku_interfaces_RefundTransaction_process37 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("refundTransaction"))))
        

// @LINE:71
private[this] lazy val kirimdoku_interfaces_UnlockTransaction_process38 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("unlockTransaction"))))
        

// @LINE:72
private[this] lazy val kirimdoku_interfaces_ChangeTransaction_process39 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("changeTransaction"))))
        

// @LINE:74
private[this] lazy val controllers_Logo_index40 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logo/"),DynamicPart("code", """[^/]+""",true))))
        

// @LINE:76
private[this] lazy val kirimdoku_interfaces_Banks_getGroupBankList41 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getGroupBankList"))))
        

// @LINE:77
private[this] lazy val kirimdoku_interfaces_Banks_getProvinceBankList42 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getProvinceBankList"))))
        

// @LINE:78
private[this] lazy val kirimdoku_interfaces_Banks_getCityBankList43 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCityBankList"))))
        

// @LINE:79
private[this] lazy val kirimdoku_interfaces_Banks_getList44 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getListBank"))))
        

// @LINE:80
private[this] lazy val kirimdoku_interfaces_Countries_getList45 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getListCountry"))))
        

// @LINE:81
private[this] lazy val kirimdoku_interfaces_Countries_getReceiveList46 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getReceiveListCountry"))))
        

// @LINE:83
private[this] lazy val kirimdoku_interfaces_Translate_doTranslate47 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("doTranslate"))))
        

// @LINE:84
private[this] lazy val kirimdoku_interfaces_User_getInformation48 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userInformation"))))
        

// @LINE:85
private[this] lazy val kirimdoku_interfaces_InquiryBillPayment_doInquiry49 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("inquiryBillPayment"))))
        

// @LINE:87
private[this] lazy val controllers_Transaction_callback50 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("callback"))))
        

// @LINE:89
private[this] lazy val kirimdoku_interfaces_Banks_getDataBank51 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getdatabank"))))
        

// @LINE:92
private[this] lazy val kirimdoku_interfaces_CashInBatch_inquiry52 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashin/inquirybatch"))))
        

// @LINE:93
private[this] lazy val kirimdoku_interfaces_CashInBatch_remit53 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cashin/remitbatch"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Lab.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ping""","""controllers.Network.ping()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ping""","""controllers.Network.pings()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashin/inquiry""","""controllers.CashIn.inquiry()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashin/remit""","""controllers.CashIn.remit()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashout/inquiry""","""controllers.CashOut.inquiry()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashout/validate""","""controllers.CashOut.validate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashout/collect""","""controllers.CashOut.collect()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/$id<[^/]+>""","""controllers.Transaction.show(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/$id<[^/]+>""","""controllers.Transaction.summary(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transactions""","""controllers.Transaction.search()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customers/$id<[^/]+>""","""controllers.Customer.show(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/settlement""","""controllers.Lab.settlement()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/check/$id<[^/]+>""","""controllers.Lab.suspiciousCheck(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/hello""","""controllers.Lab.hello()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/1""","""controllers.Lab.test1()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/2""","""controllers.Lab.test2()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/3""","""controllers.Lab.test3()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """lab/4""","""controllers.Lab.test4()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """translate/pinyin""","""controllers.Translate.QueryPinyin()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """translate/tcode""","""controllers.Translate.QueryTC()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""kirimdoku.interfaces.Login.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashinInquiry""","""kirimdoku.interfaces.CashIn.inquiry()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashinRemit""","""kirimdoku.interfaces.CashIn.remit()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashoutInquiry""","""kirimdoku.interfaces.CashOut.inquiry()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashoutValidate""","""kirimdoku.interfaces.CashOut.validate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashoutCollect""","""kirimdoku.interfaces.CashOut.collect()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customerLookup""","""kirimdoku.interfaces.Customer.getCustomer()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customerList""","""kirimdoku.interfaces.Customer.getCustomerList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customerAdd""","""kirimdoku.interfaces.Customer.addCustomer()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customerSenderList""","""kirimdoku.interfaces.Customer.getCustomerSenderList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transactionLookup""","""kirimdoku.interfaces.Transaction.getTransaction()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getTransactionRefund""","""kirimdoku.interfaces.Transaction.getTransactionRefund()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getTransactionChange""","""kirimdoku.interfaces.Transaction.getTransactionChange()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getTransactionLocked""","""kirimdoku.interfaces.Transaction.getTransactionLocked()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transactionList""","""kirimdoku.interfaces.Transaction.getTransactionList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """checkRates""","""kirimdoku.interfaces.CheckRate.process()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """refundTransaction""","""kirimdoku.interfaces.RefundTransaction.process()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """unlockTransaction""","""kirimdoku.interfaces.UnlockTransaction.process()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """changeTransaction""","""kirimdoku.interfaces.ChangeTransaction.process()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logo/$code<[^/]+>""","""controllers.Logo.index(code:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getGroupBankList""","""kirimdoku.interfaces.Banks.getGroupBankList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getProvinceBankList""","""kirimdoku.interfaces.Banks.getProvinceBankList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCityBankList""","""kirimdoku.interfaces.Banks.getCityBankList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getListBank""","""kirimdoku.interfaces.Banks.getList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getListCountry""","""kirimdoku.interfaces.Countries.getList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getReceiveListCountry""","""kirimdoku.interfaces.Countries.getReceiveList()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """doTranslate""","""kirimdoku.interfaces.Translate.doTranslate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userInformation""","""kirimdoku.interfaces.User.getInformation()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """inquiryBillPayment""","""kirimdoku.interfaces.InquiryBillPayment.doInquiry()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """callback""","""controllers.Transaction.callback"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getdatabank""","""kirimdoku.interfaces.Banks.getDataBank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashin/inquirybatch""","""kirimdoku.interfaces.CashInBatch.inquiry()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cashin/remitbatch""","""kirimdoku.interfaces.CashInBatch.remit()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Lab_index0(params) => {
   call { 
        invokeHandler(controllers.Lab.index(), HandlerDef(this, "controllers.Lab", "index", Nil,"GET", """ Default Page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Network_ping1(params) => {
   call { 
        invokeHandler(controllers.Network.ping(), HandlerDef(this, "controllers.Network", "ping", Nil,"GET", """ Network utilities""", Routes.prefix + """ping"""))
   }
}
        

// @LINE:10
case controllers_Network_pings2(params) => {
   call { 
        invokeHandler(controllers.Network.pings(), HandlerDef(this, "controllers.Network", "pings", Nil,"POST", """""", Routes.prefix + """ping"""))
   }
}
        

// @LINE:13
case controllers_CashIn_inquiry3(params) => {
   call { 
        invokeHandler(controllers.CashIn.inquiry(), HandlerDef(this, "controllers.CashIn", "inquiry", Nil,"POST", """ Mechanism for CashIn""", Routes.prefix + """cashin/inquiry"""))
   }
}
        

// @LINE:14
case controllers_CashIn_remit4(params) => {
   call { 
        invokeHandler(controllers.CashIn.remit(), HandlerDef(this, "controllers.CashIn", "remit", Nil,"POST", """""", Routes.prefix + """cashin/remit"""))
   }
}
        

// @LINE:17
case controllers_CashOut_inquiry5(params) => {
   call { 
        invokeHandler(controllers.CashOut.inquiry(), HandlerDef(this, "controllers.CashOut", "inquiry", Nil,"POST", """ Mechanism for CashOut""", Routes.prefix + """cashout/inquiry"""))
   }
}
        

// @LINE:18
case controllers_CashOut_validate6(params) => {
   call { 
        invokeHandler(controllers.CashOut.validate(), HandlerDef(this, "controllers.CashOut", "validate", Nil,"POST", """""", Routes.prefix + """cashout/validate"""))
   }
}
        

// @LINE:19
case controllers_CashOut_collect7(params) => {
   call { 
        invokeHandler(controllers.CashOut.collect(), HandlerDef(this, "controllers.CashOut", "collect", Nil,"POST", """""", Routes.prefix + """cashout/collect"""))
   }
}
        

// @LINE:22
case controllers_Transaction_show8(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Transaction.show(id), HandlerDef(this, "controllers.Transaction", "show", Seq(classOf[String]),"GET", """ Transaction informations""", Routes.prefix + """transaction/$id<[^/]+>"""))
   }
}
        

// @LINE:23
case controllers_Transaction_summary9(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Transaction.summary(id), HandlerDef(this, "controllers.Transaction", "summary", Seq(classOf[String]),"POST", """""", Routes.prefix + """transaction/$id<[^/]+>"""))
   }
}
        

// @LINE:25
case controllers_Transaction_search10(params) => {
   call { 
        invokeHandler(controllers.Transaction.search(), HandlerDef(this, "controllers.Transaction", "search", Nil,"GET", """""", Routes.prefix + """transactions"""))
   }
}
        

// @LINE:30
case controllers_Customer_show11(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Customer.show(id), HandlerDef(this, "controllers.Customer", "show", Seq(classOf[String]),"GET", """ Customers
OPTIONS	/customers				controllers.Customer.options()
GET		/customers				controllers.Customer.index()""", Routes.prefix + """customers/$id<[^/]+>"""))
   }
}
        

// @LINE:36
case controllers_Lab_settlement12(params) => {
   call { 
        invokeHandler(controllers.Lab.settlement(), HandlerDef(this, "controllers.Lab", "settlement", Nil,"GET", """ Lab""", Routes.prefix + """lab/settlement"""))
   }
}
        

// @LINE:37
case controllers_Lab_suspiciousCheck13(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Lab.suspiciousCheck(id), HandlerDef(this, "controllers.Lab", "suspiciousCheck", Seq(classOf[String]),"GET", """""", Routes.prefix + """lab/check/$id<[^/]+>"""))
   }
}
        

// @LINE:38
case controllers_Lab_hello14(params) => {
   call { 
        invokeHandler(controllers.Lab.hello(), HandlerDef(this, "controllers.Lab", "hello", Nil,"GET", """""", Routes.prefix + """lab/hello"""))
   }
}
        

// @LINE:39
case controllers_Lab_test115(params) => {
   call { 
        invokeHandler(controllers.Lab.test1(), HandlerDef(this, "controllers.Lab", "test1", Nil,"GET", """""", Routes.prefix + """lab/1"""))
   }
}
        

// @LINE:40
case controllers_Lab_test216(params) => {
   call { 
        invokeHandler(controllers.Lab.test2(), HandlerDef(this, "controllers.Lab", "test2", Nil,"GET", """""", Routes.prefix + """lab/2"""))
   }
}
        

// @LINE:41
case controllers_Lab_test317(params) => {
   call { 
        invokeHandler(controllers.Lab.test3(), HandlerDef(this, "controllers.Lab", "test3", Nil,"GET", """""", Routes.prefix + """lab/3"""))
   }
}
        

// @LINE:42
case controllers_Lab_test418(params) => {
   call { 
        invokeHandler(controllers.Lab.test4(), HandlerDef(this, "controllers.Lab", "test4", Nil,"GET", """""", Routes.prefix + """lab/4"""))
   }
}
        

// @LINE:45
case controllers_Translate_QueryPinyin19(params) => {
   call { 
        invokeHandler(controllers.Translate.QueryPinyin(), HandlerDef(this, "controllers.Translate", "QueryPinyin", Nil,"POST", """netelis""", Routes.prefix + """translate/pinyin"""))
   }
}
        

// @LINE:46
case controllers_Translate_QueryTC20(params) => {
   call { 
        invokeHandler(controllers.Translate.QueryTC(), HandlerDef(this, "controllers.Translate", "QueryTC", Nil,"POST", """""", Routes.prefix + """translate/tcode"""))
   }
}
        

// @LINE:50
case kirimdoku_interfaces_Login_index21(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Login.index(), HandlerDef(this, "kirimdoku.interfaces.Login", "index", Nil,"POST", """ NEW API MOBILE""", Routes.prefix + """login"""))
   }
}
        

// @LINE:51
case kirimdoku_interfaces_CashIn_inquiry22(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashIn.inquiry(), HandlerDef(this, "kirimdoku.interfaces.CashIn", "inquiry", Nil,"POST", """""", Routes.prefix + """cashinInquiry"""))
   }
}
        

// @LINE:52
case kirimdoku_interfaces_CashIn_remit23(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashIn.remit(), HandlerDef(this, "kirimdoku.interfaces.CashIn", "remit", Nil,"POST", """""", Routes.prefix + """cashinRemit"""))
   }
}
        

// @LINE:54
case kirimdoku_interfaces_CashOut_inquiry24(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashOut.inquiry(), HandlerDef(this, "kirimdoku.interfaces.CashOut", "inquiry", Nil,"POST", """""", Routes.prefix + """cashoutInquiry"""))
   }
}
        

// @LINE:55
case kirimdoku_interfaces_CashOut_validate25(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashOut.validate(), HandlerDef(this, "kirimdoku.interfaces.CashOut", "validate", Nil,"POST", """""", Routes.prefix + """cashoutValidate"""))
   }
}
        

// @LINE:56
case kirimdoku_interfaces_CashOut_collect26(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashOut.collect(), HandlerDef(this, "kirimdoku.interfaces.CashOut", "collect", Nil,"POST", """""", Routes.prefix + """cashoutCollect"""))
   }
}
        

// @LINE:58
case kirimdoku_interfaces_Customer_getCustomer27(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Customer.getCustomer(), HandlerDef(this, "kirimdoku.interfaces.Customer", "getCustomer", Nil,"POST", """""", Routes.prefix + """customerLookup"""))
   }
}
        

// @LINE:59
case kirimdoku_interfaces_Customer_getCustomerList28(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Customer.getCustomerList(), HandlerDef(this, "kirimdoku.interfaces.Customer", "getCustomerList", Nil,"POST", """""", Routes.prefix + """customerList"""))
   }
}
        

// @LINE:60
case kirimdoku_interfaces_Customer_addCustomer29(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Customer.addCustomer(), HandlerDef(this, "kirimdoku.interfaces.Customer", "addCustomer", Nil,"POST", """""", Routes.prefix + """customerAdd"""))
   }
}
        

// @LINE:61
case kirimdoku_interfaces_Customer_getCustomerSenderList30(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Customer.getCustomerSenderList(), HandlerDef(this, "kirimdoku.interfaces.Customer", "getCustomerSenderList", Nil,"POST", """""", Routes.prefix + """customerSenderList"""))
   }
}
        

// @LINE:63
case kirimdoku_interfaces_Transaction_getTransaction31(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Transaction.getTransaction(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransaction", Nil,"POST", """""", Routes.prefix + """transactionLookup"""))
   }
}
        

// @LINE:64
case kirimdoku_interfaces_Transaction_getTransactionRefund32(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Transaction.getTransactionRefund(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionRefund", Nil,"POST", """""", Routes.prefix + """getTransactionRefund"""))
   }
}
        

// @LINE:65
case kirimdoku_interfaces_Transaction_getTransactionChange33(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Transaction.getTransactionChange(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionChange", Nil,"POST", """""", Routes.prefix + """getTransactionChange"""))
   }
}
        

// @LINE:66
case kirimdoku_interfaces_Transaction_getTransactionLocked34(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Transaction.getTransactionLocked(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionLocked", Nil,"POST", """""", Routes.prefix + """getTransactionLocked"""))
   }
}
        

// @LINE:67
case kirimdoku_interfaces_Transaction_getTransactionList35(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Transaction.getTransactionList(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionList", Nil,"POST", """""", Routes.prefix + """transactionList"""))
   }
}
        

// @LINE:69
case kirimdoku_interfaces_CheckRate_process36(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CheckRate.process(), HandlerDef(this, "kirimdoku.interfaces.CheckRate", "process", Nil,"POST", """""", Routes.prefix + """checkRates"""))
   }
}
        

// @LINE:70
case kirimdoku_interfaces_RefundTransaction_process37(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.RefundTransaction.process(), HandlerDef(this, "kirimdoku.interfaces.RefundTransaction", "process", Nil,"POST", """""", Routes.prefix + """refundTransaction"""))
   }
}
        

// @LINE:71
case kirimdoku_interfaces_UnlockTransaction_process38(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.UnlockTransaction.process(), HandlerDef(this, "kirimdoku.interfaces.UnlockTransaction", "process", Nil,"POST", """""", Routes.prefix + """unlockTransaction"""))
   }
}
        

// @LINE:72
case kirimdoku_interfaces_ChangeTransaction_process39(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.ChangeTransaction.process(), HandlerDef(this, "kirimdoku.interfaces.ChangeTransaction", "process", Nil,"POST", """""", Routes.prefix + """changeTransaction"""))
   }
}
        

// @LINE:74
case controllers_Logo_index40(params) => {
   call(params.fromPath[String]("code", None)) { (code) =>
        invokeHandler(controllers.Logo.index(code), HandlerDef(this, "controllers.Logo", "index", Seq(classOf[String]),"GET", """""", Routes.prefix + """logo/$code<[^/]+>"""))
   }
}
        

// @LINE:76
case kirimdoku_interfaces_Banks_getGroupBankList41(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Banks.getGroupBankList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getGroupBankList", Nil,"POST", """""", Routes.prefix + """getGroupBankList"""))
   }
}
        

// @LINE:77
case kirimdoku_interfaces_Banks_getProvinceBankList42(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Banks.getProvinceBankList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getProvinceBankList", Nil,"POST", """""", Routes.prefix + """getProvinceBankList"""))
   }
}
        

// @LINE:78
case kirimdoku_interfaces_Banks_getCityBankList43(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Banks.getCityBankList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getCityBankList", Nil,"POST", """""", Routes.prefix + """getCityBankList"""))
   }
}
        

// @LINE:79
case kirimdoku_interfaces_Banks_getList44(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Banks.getList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getList", Nil,"POST", """""", Routes.prefix + """getListBank"""))
   }
}
        

// @LINE:80
case kirimdoku_interfaces_Countries_getList45(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Countries.getList(), HandlerDef(this, "kirimdoku.interfaces.Countries", "getList", Nil,"POST", """""", Routes.prefix + """getListCountry"""))
   }
}
        

// @LINE:81
case kirimdoku_interfaces_Countries_getReceiveList46(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Countries.getReceiveList(), HandlerDef(this, "kirimdoku.interfaces.Countries", "getReceiveList", Nil,"POST", """""", Routes.prefix + """getReceiveListCountry"""))
   }
}
        

// @LINE:83
case kirimdoku_interfaces_Translate_doTranslate47(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Translate.doTranslate(), HandlerDef(this, "kirimdoku.interfaces.Translate", "doTranslate", Nil,"POST", """""", Routes.prefix + """doTranslate"""))
   }
}
        

// @LINE:84
case kirimdoku_interfaces_User_getInformation48(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.User.getInformation(), HandlerDef(this, "kirimdoku.interfaces.User", "getInformation", Nil,"POST", """""", Routes.prefix + """userInformation"""))
   }
}
        

// @LINE:85
case kirimdoku_interfaces_InquiryBillPayment_doInquiry49(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.InquiryBillPayment.doInquiry(), HandlerDef(this, "kirimdoku.interfaces.InquiryBillPayment", "doInquiry", Nil,"POST", """""", Routes.prefix + """inquiryBillPayment"""))
   }
}
        

// @LINE:87
case controllers_Transaction_callback50(params) => {
   call { 
        invokeHandler(controllers.Transaction.callback, HandlerDef(this, "controllers.Transaction", "callback", Nil,"POST", """""", Routes.prefix + """callback"""))
   }
}
        

// @LINE:89
case kirimdoku_interfaces_Banks_getDataBank51(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.Banks.getDataBank(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getDataBank", Nil,"POST", """""", Routes.prefix + """getdatabank"""))
   }
}
        

// @LINE:92
case kirimdoku_interfaces_CashInBatch_inquiry52(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashInBatch.inquiry(), HandlerDef(this, "kirimdoku.interfaces.CashInBatch", "inquiry", Nil,"POST", """ CashIn Batch""", Routes.prefix + """cashin/inquirybatch"""))
   }
}
        

// @LINE:93
case kirimdoku_interfaces_CashInBatch_remit53(params) => {
   call { 
        invokeHandler(kirimdoku.interfaces.CashInBatch.remit(), HandlerDef(this, "kirimdoku.interfaces.CashInBatch", "remit", Nil,"POST", """""", Routes.prefix + """cashin/remitbatch"""))
   }
}
        
}
    
}
        