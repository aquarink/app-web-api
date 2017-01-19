// @SOURCE:/Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/conf/routes
// @HASH:3042f729bd64a68c57a07f25723db69c21a309c3
// @DATE:Wed Jan 18 18:12:20 WIB 2017


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


// @LINE:11
private[this] lazy val controllers_Main_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:12
private[this] lazy val controllers_Dashboard_index1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard"))))
        

// @LINE:13
private[this] lazy val controllers_Dashboard_userPerformances2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard/statistics/"),DynamicPart("id", """[^/]+""",true),StaticPart("/performances.json"))))
        

// @LINE:14
private[this] lazy val controllers_Dashboard_userVolumes3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard/statistics/"),DynamicPart("id", """[^/]+""",true),StaticPart("/volumes.json"))))
        

// @LINE:15
private[this] lazy val controllers_Dashboard_statistics4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard/statistics/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:16
private[this] lazy val controllers_Dashboard_news5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/content/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:20
private[this] lazy val controllers_Login_login6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:21
private[this] lazy val controllers_Login_authenticate7 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:22
private[this] lazy val controllers_Login_logout8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:23
private[this] lazy val controllers_Login_forgetPassword9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("forget-password"))))
        

// @LINE:24
private[this] lazy val controllers_Login_resetPassword10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("reset-password/"),DynamicPart("key", """[^/]+""",true))))
        

// @LINE:25
private[this] lazy val controllers_Login_resetPasswordSubmit11 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("reset-password"))))
        

// @LINE:26
private[this] lazy val controllers_Login_setLang12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("changeLang/"),DynamicPart("langId", """[^/]+""",true),StaticPart("/login"))))
        

// @LINE:27
private[this] lazy val controllers_Login_setLangAdmin13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("changeLang/"),DynamicPart("langId", """[^/]+""",true),StaticPart("/admin/login"))))
        

// @LINE:30
private[this] lazy val controllers_User_index14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/me"))))
        

// @LINE:31
private[this] lazy val controllers_User_list15 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/users"))))
        

// @LINE:32
private[this] lazy val controllers_User_newCreate16 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/new/"),DynamicPart("role", """[^/]+""",true))))
        

// @LINE:33
private[this] lazy val controllers_User_edit17 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:34
private[this] lazy val controllers_User_create18 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users"))))
        

// @LINE:35
private[this] lazy val controllers_User_update19 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:36
private[this] lazy val controllers_User_destroy20 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:37
private[this] lazy val controllers_User_show21 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:38
private[this] lazy val controllers_User_changePassword22 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/password"))))
        

// @LINE:39
private[this] lazy val controllers_User_saveChangePassword23 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/password"))))
        

// @LINE:40
private[this] lazy val controllers_User_updateStatus24 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/status"))))
        

// @LINE:41
private[this] lazy val controllers_User_updateRolePromote25 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/role/promote"))))
        

// @LINE:42
private[this] lazy val controllers_User_updateRoleDemote26 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/role/demote"))))
        

// @LINE:43
private[this] lazy val controllers_User_updateRoleDemoteForm27 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/role/demoteForm"))))
        

// @LINE:47
private[this] lazy val controllers_Customer_list28 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer"))))
        

// @LINE:48
private[this] lazy val controllers_Customer_csv29 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer.csv"))))
        

// @LINE:51
private[this] lazy val controllers_Customer_create30 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/create"))))
        

// @LINE:52
private[this] lazy val controllers_Customer_save31 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/create"))))
        

// @LINE:53
private[this] lazy val controllers_Customer_summary32 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/"),DynamicPart("id", """[^/]+""",true),StaticPart("/summary"))))
        

// @LINE:54
private[this] lazy val controllers_Customer_edit33 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:55
private[this] lazy val controllers_Customer_update34 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:57
private[this] lazy val controllers_Customer_relate35 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/relate"))))
        

// @LINE:58
private[this] lazy val controllers_Customer_addRelate36 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/addrelate"))))
        

// @LINE:59
private[this] lazy val controllers_Customer_addRelateAction37 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/addrelateaction"))))
        

// @LINE:60
private[this] lazy val controllers_Customer_deleteRelate38 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customer/deleterelate"))))
        

// @LINE:63
private[this] lazy val controllers_Send_create39 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send"))))
        

// @LINE:64
private[this] lazy val controllers_Send_inquiry40 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/inquiry"))))
        

// @LINE:65
private[this] lazy val controllers_Send_inquiryBillPayment41 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/inquiryBillPayment"))))
        

// @LINE:66
private[this] lazy val controllers_Send_customerLookup42 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/customer-lookup"))))
        

// @LINE:67
private[this] lazy val controllers_Send_customerLookupRelate43 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/customer-lookupRelate"))))
        

// @LINE:68
private[this] lazy val controllers_Send_verify44 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/verify"))))
        

// @LINE:69
private[this] lazy val controllers_Send_confirm45 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/confirm"))))
        

// @LINE:70
private[this] lazy val controllers_Send_confirmBillPayment46 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/confirmBillPayment"))))
        

// @LINE:71
private[this] lazy val controllers_Send_process47 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/process"))))
        

// @LINE:72
private[this] lazy val controllers_Send_processBillPayment48 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/processBillPayment"))))
        

// @LINE:73
private[this] lazy val controllers_Send_receipt49 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receipt"))))
        

// @LINE:74
private[this] lazy val controllers_Send_receiptBillPayment50 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receiptBillPayment"))))
        

// @LINE:75
private[this] lazy val controllers_Send_sendTransactionViaEmail51 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/"),DynamicPart("id", """[^/]+""",true),StaticPart("/send-email"))))
        

// @LINE:76
private[this] lazy val controllers_Send_sendTransactionViaEmail52 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/"),DynamicPart("id", """[^/]+""",true),StaticPart("/send-email/"),DynamicPart("recipient", """[^/]+""",true))))
        

// @LINE:77
private[this] lazy val controllers_Send_summary53 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/summary"))))
        

// @LINE:78
private[this] lazy val controllers_Send_customerNew54 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/customer/new"))))
        

// @LINE:79
private[this] lazy val controllers_Send_customerSearch55 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/customer/search/"),DynamicPart("query", """[^/]+""",true))))
        

// @LINE:80
private[this] lazy val controllers_Send_customerEdit56 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/customer/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:81
private[this] lazy val controllers_Send_customerSave57 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/customer/save"))))
        

// @LINE:83
private[this] lazy val controllers_Send_receiptPrint58 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receipt-print"))))
        

// @LINE:84
private[this] lazy val controllers_Send_receiptEmail59 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("send/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receipt-email"))))
        

// @LINE:87
private[this] lazy val controllers_Receive_create60 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive"))))
        

// @LINE:88
private[this] lazy val controllers_Receive_inquiry61 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:89
private[this] lazy val controllers_Receive_verify62 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/verify"))))
        

// @LINE:90
private[this] lazy val controllers_Receive_validate63 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/validate"))))
        

// @LINE:91
private[this] lazy val controllers_Receive_process64 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/process"))))
        

// @LINE:92
private[this] lazy val controllers_Receive_receipt65 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receipt"))))
        

// @LINE:93
private[this] lazy val controllers_Receive_receiptPrint66 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receipt-print"))))
        

// @LINE:94
private[this] lazy val controllers_Receive_receiptEmail67 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/receipt-email"))))
        

// @LINE:95
private[this] lazy val controllers_Receive_sendTransactionViaEmail68 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("receive/"),DynamicPart("id", """[^/]+""",true),StaticPart("/send-email"))))
        

// @LINE:100
private[this] lazy val controllers_Transaction_list69 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction"))))
        

// @LINE:101
private[this] lazy val controllers_Transaction_refund70 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/refund"))))
        

// @LINE:102
private[this] lazy val controllers_Transaction_refundDetail71 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/refund/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:103
private[this] lazy val controllers_Transaction_refundProcess72 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/refund/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:104
private[this] lazy val controllers_Transaction_refundReceipt73 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/refund/"),DynamicPart("idToken", """[^/]+""",true),StaticPart("/receipt"))))
        

// @LINE:105
private[this] lazy val controllers_Transaction_change74 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/change"))))
        

// @LINE:106
private[this] lazy val controllers_Transaction_changeDetail75 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/change/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:107
private[this] lazy val controllers_Transaction_changeProcess76 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/change/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:108
private[this] lazy val controllers_Transaction_changeReceipt77 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/change/"),DynamicPart("idToken", """[^/]+""",true),StaticPart("/receipt"))))
        

// @LINE:109
private[this] lazy val controllers_Transaction_unlock78 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/unlock"))))
        

// @LINE:110
private[this] lazy val controllers_Transaction_unlockDetail79 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/unlock/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:111
private[this] lazy val controllers_Transaction_unlockProcess80 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/unlock/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:112
private[this] lazy val controllers_Transaction_show81 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transaction/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:115
private[this] lazy val controllers_Report_list82 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("report"))))
        

// @LINE:116
private[this] lazy val controllers_Report_pdf83 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("report/pdf"))))
        

// @LINE:117
private[this] lazy val controllers_Report_excel84 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("report/excel"))))
        

// @LINE:122
private[this] lazy val controllers_Tools_checkRate85 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tools/checkRate"))))
        

// @LINE:123
private[this] lazy val controllers_Tools_checkRateSubmit86 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tools/checkRate"))))
        

// @LINE:126
private[this] lazy val controllers_Tools_feedback87 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("feedback"))))
        

// @LINE:127
private[this] lazy val controllers_Tools_feedbackSubmit88 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("feedback"))))
        

// @LINE:128
private[this] lazy val controllers_Tools_documentation89 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("documentation"))))
        

// @LINE:131
private[this] lazy val controllers_News_list90 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/list"))))
        

// @LINE:132
private[this] lazy val controllers_News_create91 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/create"))))
        

// @LINE:133
private[this] lazy val controllers_News_save92 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/save"))))
        

// @LINE:134
private[this] lazy val controllers_News_edit93 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/edit/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:135
private[this] lazy val controllers_News_update94 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/update/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:136
private[this] lazy val controllers_News_status95 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("news/status/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("status", """[^/]+""",true))))
        

// @LINE:139
private[this] lazy val controllers_Forex_table96 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("forexs/"),DynamicPart("c", """[^/]+""",true),StaticPart("/table"))))
        

// @LINE:141
private[this] lazy val controllers_Corporate_logo97 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("id", """[^/]+""",true),StaticPart("/logo"))))
        

// @LINE:142
private[this] lazy val controllers_User_photo98 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/photo"))))
        

// @LINE:143
private[this] lazy val controllers_User_css99 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("id", """[^/]+""",true),StaticPart("/css"))))
        

// @LINE:144
private[this] lazy val controllers_Helpers_currencies100 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("helpers/currencies.json"))))
        

// @LINE:145
private[this] lazy val controllers_Helpers_cities101 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("helpers/cities.json"))))
        

// @LINE:146
private[this] lazy val controllers_Helpers_banks102 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("helpers/banks.json"))))
        

// @LINE:147
private[this] lazy val controllers_Helpers_banks_detail103 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("helpers/banks/"),DynamicPart("id", """[^/]+""",true),StaticPart(".json"))))
        

// @LINE:155
private[this] lazy val controllers_Main_admin_index104 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin"))))
        

// @LINE:156
private[this] lazy val controllers_Dashboard_admin_index105 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/dashboard"))))
        

// @LINE:160
private[this] lazy val controllers_Login_admin_login106 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/login"))))
        

// @LINE:161
private[this] lazy val controllers_Login_admin_authenticate107 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/login"))))
        

// @LINE:162
private[this] lazy val controllers_Login_admin_logout108 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/logout"))))
        

// @LINE:166
private[this] lazy val controllers_Corporate_admin_index109 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates"))))
        

// @LINE:167
private[this] lazy val controllers_Corporate_admin_new110 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/new"))))
        

// @LINE:168
private[this] lazy val controllers_Corporate_admin_create111 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates"))))
        

// @LINE:169
private[this] lazy val controllers_Corporate_show112 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:170
private[this] lazy val controllers_Corporate_admin_edit113 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:171
private[this] lazy val controllers_Corporate_admin_update114 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:178
private[this] lazy val controllers_Fee_admin_index115 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/fees"))))
        

// @LINE:179
private[this] lazy val controllers_Fee_create116 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/fees"))))
        

// @LINE:180
private[this] lazy val controllers_Fee_destroy117 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/fees/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:183
private[this] lazy val controllers_Credit_admin_index118 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/credit"))))
        

// @LINE:184
private[this] lazy val controllers_Credit_admin_update119 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/credit"))))
        

// @LINE:187
private[this] lazy val controllers_Forex_admin_index_main120 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/DOK/forex"))))
        

// @LINE:188
private[this] lazy val controllers_Forex_admin_index121 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/forex"))))
        

// @LINE:189
private[this] lazy val controllers_Forex_admin_create122 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/forex"))))
        

// @LINE:190
private[this] lazy val controllers_Forex_admin_update123 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/forex/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:191
private[this] lazy val controllers_Forex_admin_destroy124 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/forex/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:194
private[this] lazy val controllers_Audit_admin_index125 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/audit"))))
        

// @LINE:197
private[this] lazy val controllers_CustomerBan_admin_list126 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/customerbans"))))
        

// @LINE:198
private[this] lazy val controllers_CustomerBan_admin_add127 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/customerbans"))))
        

// @LINE:199
private[this] lazy val controllers_CustomerBan_admin_unban128 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/customerbans/"),DynamicPart("id", """[^/]+""",true),StaticPart("/unban"))))
        

// @LINE:200
private[this] lazy val controllers_CustomerBan_admin_interdictions129 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/customerbans/interdictions"))))
        

// @LINE:201
private[this] lazy val controllers_CustomerBan_admin_interdictionsUpload130 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/customerbans/interdictions"))))
        

// @LINE:202
private[this] lazy val controllers_CustomerBan_admin_interdictionsDelete131 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/customerbans/interdictions/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:203
private[this] lazy val controllers_Suspicious_admin_index132 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/suspicious"))))
        

// @LINE:204
private[this] lazy val controllers_Suspicious_csv133 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/suspicious.csv"))))
        

// @LINE:208
private[this] lazy val controllers_Transaction_admin_list134 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/transactions"))))
        

// @LINE:211
private[this] lazy val controllers_Transaction_admin_refund135 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/transaction/refund"))))
        

// @LINE:212
private[this] lazy val controllers_Transaction_admin_refundDetail136 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/transaction/refund/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:213
private[this] lazy val controllers_Transaction_admin_refundProcess137 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/transaction/refund/"),DynamicPart("idToken", """[^/]+""",true))))
        

// @LINE:219
private[this] lazy val controllers_Assets_at138 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:225
private[this] lazy val controllers_SetupShareSetting_view139 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/viewsharesetting"))))
        

// @LINE:226
private[this] lazy val controllers_SetupShareSetting_save140 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/savesharesetting"))))
        

// @LINE:227
private[this] lazy val controllers_SetupCutOffTime_view141 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/viewcutofftime"))))
        

// @LINE:228
private[this] lazy val controllers_SetupCutOffTime_save142 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/savecutofftime"))))
        

// @LINE:229
private[this] lazy val controllers_SettlementReport_list143 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/settlementreport"))))
        

// @LINE:230
private[this] lazy val controllers_SettlementReport_listView144 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/settlementreport"))))
        

// @LINE:231
private[this] lazy val controllers_SettlementReport_download145 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/settlementreport/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:232
private[this] lazy val controllers_SettlementReport_generateById146 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("admin/settlementreports/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:235
private[this] lazy val controllers_Translate_getPinyin147 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("translate/pinyin"))))
        

// @LINE:236
private[this] lazy val controllers_Translate_getTCode148 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("translate/tcode"))))
        

// @LINE:238
private[this] lazy val controllers_GetBank_getProvinceBank149 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getprovincebank"))))
        

// @LINE:239
private[this] lazy val controllers_GetBank_getCityBank150 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getcitybank"))))
        

// @LINE:240
private[this] lazy val controllers_GetBank_getBank151 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getbank"))))
        

// @LINE:241
private[this] lazy val controllers_GetBank_getGroupBank152 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getcountrybank"))))
        

// @LINE:242
private[this] lazy val controllers_ValidateVoucher_getVoucher153 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("validatevoucher"))))
        

// @LINE:245
private[this] lazy val controllers_User_indexOperasional154 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/usersoperasional"))))
        

// @LINE:246
private[this] lazy val controllers_User_listOperasional155 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/usersoperasional/users"))))
        

// @LINE:247
private[this] lazy val controllers_User_updateOperasional156 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/usersoperasional/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:248
private[this] lazy val controllers_User_editOperasional157 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("corporates/"),DynamicPart("c", """[^/]+""",true),StaticPart("/usersoperasional/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:251
private[this] lazy val controllers_Send_createBillPayment158 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sendBillPayment"))))
        

// @LINE:254
private[this] lazy val controllers_Banks_list159 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks"))))
        

// @LINE:255
private[this] lazy val controllers_Banks_csv160 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks.csv"))))
        

// @LINE:256
private[this] lazy val controllers_Banks_summary161 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks/"),DynamicPart("id", """[^/]+""",true),StaticPart("/summary"))))
        

// @LINE:257
private[this] lazy val controllers_Banks_edit162 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:258
private[this] lazy val controllers_Banks_update163 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks/"),DynamicPart("id", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:259
private[this] lazy val controllers_Banks_create164 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks/create"))))
        

// @LINE:260
private[this] lazy val controllers_Banks_save165 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks/create"))))
        

// @LINE:261
private[this] lazy val controllers_Banks_delete166 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("banks/delete/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:264
private[this] lazy val controllers_Channels_list167 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("channels"))))
        

// @LINE:265
private[this] lazy val controllers_Channels_summary168 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("channels/"),DynamicPart("code", """[^/]+""",true),StaticPart("/summary"))))
        

// @LINE:266
private[this] lazy val controllers_Channels_edit169 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("channels/"),DynamicPart("code", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:267
private[this] lazy val controllers_Channels_update170 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("channels/"),DynamicPart("code", """[^/]+""",true),StaticPart("/edit"))))
        

// @LINE:268
private[this] lazy val controllers_Channels_delete171 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("channels/delete/"),DynamicPart("code", """[^/]+""",true))))
        

// @LINE:270
private[this] lazy val controllers_Login_captcha172 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("kaptcha"))))
        

// @LINE:272
private[this] lazy val controllers_BatchUpload_viewUpload173 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("batchupload"))))
        

// @LINE:273
private[this] lazy val controllers_BatchUpload_actionUpload174 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dobatchupload"))))
        

// @LINE:274
private[this] lazy val controllers_Transaction_listBatchInquiry175 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transactionInquiry"))))
        

// @LINE:275
private[this] lazy val controllers_Transaction_showBatch176 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("batch/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:276
private[this] lazy val controllers_Transaction_showBatchDetail177 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("batchDetail/"),DynamicPart("batchId", """[^/]+""",true),StaticPart("/"),DynamicPart("m", """[^/]+""",true))))
        

// @LINE:277
private[this] lazy val controllers_BatchUpload_actionRemit178 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dobatchremit/"),DynamicPart("token", """[^/]+""",true),StaticPart("/"),DynamicPart("batchId", """[^/]+""",true))))
        

// @LINE:278
private[this] lazy val controllers_BatchUpload_viewResultBatchUpload179 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewbatchupload/"),DynamicPart("batchId", """[^/]+""",true))))
        

// @LINE:279
private[this] lazy val controllers_BatchUpload_viewResultBatchUploadFailed180 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewbatchuploadfailed"))))
        

// @LINE:281
private[this] lazy val controllers_Transaction_showBatchDetailTransaction181 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("transactionbatch/"),DynamicPart("batchId", """[^/]+""",true))))
        

// @LINE:283
private[this] lazy val controllers_Transaction_receipt182 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("print/"),DynamicPart("id", """[^/]+""",true),StaticPart("/"),DynamicPart("type", """[^/]+""",true))))
        
def documentation = List(("""GET""", prefix,"""controllers.Main.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard""","""controllers.Dashboard.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard/statistics/$id<[^/]+>/performances.json""","""controllers.Dashboard.userPerformances(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard/statistics/$id<[^/]+>/volumes.json""","""controllers.Dashboard.userVolumes(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard/statistics/$id<[^/]+>""","""controllers.Dashboard.statistics(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/content/$id<[^/]+>""","""controllers.Dashboard.news(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Login.login()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Login.authenticate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Login.logout()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """forget-password""","""controllers.Login.forgetPassword()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """reset-password/$key<[^/]+>""","""controllers.Login.resetPassword(key:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """reset-password""","""controllers.Login.resetPasswordSubmit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """changeLang/$langId<[^/]+>/login""","""controllers.Login.setLang(langId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """changeLang/$langId<[^/]+>/admin/login""","""controllers.Login.setLangAdmin(langId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/me""","""controllers.User.index(c:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/users""","""controllers.User.list(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/new/$role<[^/]+>""","""controllers.User.newCreate(c:String, role:String, supervisorId:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/edit""","""controllers.User.edit(c:String, id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users""","""controllers.User.create(c:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>""","""controllers.User.update(c:String, id:Long)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>""","""controllers.User.destroy(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>""","""controllers.User.show(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/password""","""controllers.User.changePassword(c:String, id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/password""","""controllers.User.saveChangePassword(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/status""","""controllers.User.updateStatus(c:String, id:Long, ta:Boolean ?= true)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/promote""","""controllers.User.updateRolePromote(c:String, id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/demote""","""controllers.User.updateRoleDemote(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/demoteForm""","""controllers.User.updateRoleDemoteForm(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer""","""controllers.Customer.list(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer.csv""","""controllers.Customer.csv(s:String ?= "id", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/create""","""controllers.Customer.create()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/create""","""controllers.Customer.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/$id<[^/]+>/summary""","""controllers.Customer.summary(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/$id<[^/]+>/edit""","""controllers.Customer.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/$id<[^/]+>/edit""","""controllers.Customer.update(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/relate""","""controllers.Customer.relate(id:Long, p:Int ?= 0, s:String ?= "id", o:String ?= "desc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/addrelate""","""controllers.Customer.addRelate(id:Long, p:Int ?= 0, s:String ?= "id", o:String ?= "desc", f:String ?= "")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/addrelateaction""","""controllers.Customer.addRelateAction()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customer/deleterelate""","""controllers.Customer.deleteRelate(id:Long, idRelate:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send""","""controllers.Send.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/inquiry""","""controllers.Send.inquiry()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/inquiryBillPayment""","""controllers.Send.inquiryBillPayment()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/customer-lookup""","""controllers.Send.customerLookup()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/customer-lookupRelate""","""controllers.Send.customerLookupRelate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/verify""","""controllers.Send.verify()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/confirm""","""controllers.Send.confirm()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/confirmBillPayment""","""controllers.Send.confirmBillPayment()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/process""","""controllers.Send.process()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/processBillPayment""","""controllers.Send.processBillPayment()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/$id<[^/]+>/receipt""","""controllers.Send.receipt(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/$id<[^/]+>/receiptBillPayment""","""controllers.Send.receiptBillPayment(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/$id<[^/]+>/send-email""","""controllers.Send.sendTransactionViaEmail(id:String, recipient:String = null)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/$id<[^/]+>/send-email/$recipient<[^/]+>""","""controllers.Send.sendTransactionViaEmail(id:String, recipient:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/summary""","""controllers.Send.summary()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/customer/new""","""controllers.Send.customerNew()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/customer/search/$query<[^/]+>""","""controllers.Send.customerSearch(query:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/customer/$id<[^/]+>/edit""","""controllers.Send.customerEdit(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/customer/save""","""controllers.Send.customerSave()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/$id<[^/]+>/receipt-print""","""controllers.Send.receiptPrint(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """send/$id<[^/]+>/receipt-email""","""controllers.Send.receiptEmail(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive""","""controllers.Receive.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>""","""controllers.Receive.inquiry(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/verify""","""controllers.Receive.verify(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/validate""","""controllers.Receive.validate(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/process""","""controllers.Receive.process(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/receipt""","""controllers.Receive.receipt(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/receipt-print""","""controllers.Receive.receiptPrint(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/receipt-email""","""controllers.Receive.receiptEmail(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """receive/$id<[^/]+>/send-email""","""controllers.Receive.sendTransactionViaEmail(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction""","""controllers.Transaction.list(p:Int ?= 0, s:String ?= "cashInTime", o:String ?= "desc", filterTransId:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/refund""","""controllers.Transaction.refund()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/refund/$idToken<[^/]+>""","""controllers.Transaction.refundDetail(idToken:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/refund/$idToken<[^/]+>""","""controllers.Transaction.refundProcess(idToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/refund/$idToken<[^/]+>/receipt""","""controllers.Transaction.refundReceipt(idToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/change""","""controllers.Transaction.change()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/change/$idToken<[^/]+>""","""controllers.Transaction.changeDetail(idToken:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/change/$idToken<[^/]+>""","""controllers.Transaction.changeProcess(idToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/change/$idToken<[^/]+>/receipt""","""controllers.Transaction.changeReceipt(idToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/unlock""","""controllers.Transaction.unlock()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/unlock/$idToken<[^/]+>""","""controllers.Transaction.unlockDetail(idToken:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/unlock/$idToken<[^/]+>""","""controllers.Transaction.unlockProcess(idToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transaction/$id<[^/]+>""","""controllers.Transaction.show(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """report""","""controllers.Report.list(p:Int ?= 0, s:String ?= "id", o:String ?= "desc")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """report/pdf""","""controllers.Report.pdf()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """report/excel""","""controllers.Report.excel()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tools/checkRate""","""controllers.Tools.checkRate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tools/checkRate""","""controllers.Tools.checkRateSubmit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """feedback""","""controllers.Tools.feedback()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """feedback""","""controllers.Tools.feedbackSubmit()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """documentation""","""controllers.Tools.documentation()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/list""","""controllers.News.list(p:Int ?= 0, s:String ?= "id", o:String ?= "desc")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/create""","""controllers.News.create()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/save""","""controllers.News.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/edit/$id<[^/]+>""","""controllers.News.edit(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/update/$id<[^/]+>""","""controllers.News.update(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """news/status/$id<[^/]+>/$status<[^/]+>""","""controllers.News.status(id:Long, status:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """forexs/$c<[^/]+>/table""","""controllers.Forex.table(c:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$id<[^/]+>/logo""","""controllers.Corporate.logo(id:String, s:String ?= "medium")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$id<[^/]+>/photo""","""controllers.User.photo(id:Long, s:String ?= "medium")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$id<[^/]+>/css""","""controllers.User.css(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """helpers/currencies.json""","""controllers.Helpers.currencies()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """helpers/cities.json""","""controllers.Helpers.cities()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """helpers/banks.json""","""controllers.Helpers.banks()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """helpers/banks/$id<[^/]+>.json""","""controllers.Helpers.banks_detail(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin""","""controllers.Main.admin_index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/dashboard""","""controllers.Dashboard.admin_index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/login""","""controllers.Login.admin_login()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/login""","""controllers.Login.admin_authenticate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/logout""","""controllers.Login.admin_logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates""","""controllers.Corporate.admin_index(p:Int ?= 0, s:String ?= "code", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/new""","""controllers.Corporate.admin_new()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates""","""controllers.Corporate.admin_create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$id<[^/]+>""","""controllers.Corporate.show(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$id<[^/]+>/edit""","""controllers.Corporate.admin_edit(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$id<[^/]+>/edit""","""controllers.Corporate.admin_update(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/fees""","""controllers.Fee.admin_index(c:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/fees""","""controllers.Fee.create(c:String)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/fees/$id<[^/]+>""","""controllers.Fee.destroy(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/credit""","""controllers.Credit.admin_index(c:String)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/credit""","""controllers.Credit.admin_update(c:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/DOK/forex""","""controllers.Forex.admin_index_main()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/forex""","""controllers.Forex.admin_index(c:String, origin:String ?= "", destination:String ?= "")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/forex""","""controllers.Forex.admin_create(c:String)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/forex/$id<[^/]+>""","""controllers.Forex.admin_update(c:String, id:Long)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/corporates/$c<[^/]+>/forex/$id<[^/]+>""","""controllers.Forex.admin_destroy(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/audit""","""controllers.Audit.admin_index(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", filterUserId:String ?= null, filterTag:String ?= null)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/customerbans""","""controllers.CustomerBan.admin_list(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", f:String ?= "")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/customerbans""","""controllers.CustomerBan.admin_add()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/customerbans/$id<[^/]+>/unban""","""controllers.CustomerBan.admin_unban(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/customerbans/interdictions""","""controllers.CustomerBan.admin_interdictions(p:Int ?= 0, s:String ?= "country.code", o:String ?= "asc", f:String ?= "")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/customerbans/interdictions""","""controllers.CustomerBan.admin_interdictionsUpload()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/customerbans/interdictions/$id<[^/]+>""","""controllers.CustomerBan.admin_interdictionsDelete(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/suspicious""","""controllers.Suspicious.admin_index(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/suspicious.csv""","""controllers.Suspicious.csv(s:String ?= "id", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/transactions""","""controllers.Transaction.admin_list(p:Int ?= 0, s:String ?= "cashInTime", o:String ?= "desc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/transaction/refund""","""controllers.Transaction.admin_refund()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/transaction/refund/$idToken<[^/]+>""","""controllers.Transaction.admin_refundDetail(idToken:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/transaction/refund/$idToken<[^/]+>""","""controllers.Transaction.admin_refundProcess(idToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/viewsharesetting""","""controllers.SetupShareSetting.view(name:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/savesharesetting""","""controllers.SetupShareSetting.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/viewcutofftime""","""controllers.SetupCutOffTime.view()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/savecutofftime""","""controllers.SetupCutOffTime.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/settlementreport""","""controllers.SettlementReport.list()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/settlementreport""","""controllers.SettlementReport.listView()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/settlementreport/$name<[^/]+>""","""controllers.SettlementReport.download(name:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """admin/settlementreports/$id<[^/]+>""","""controllers.SettlementReport.generateById(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """translate/pinyin""","""controllers.Translate.getPinyin()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """translate/tcode""","""controllers.Translate.getTCode()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getprovincebank""","""controllers.GetBank.getProvinceBank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getcitybank""","""controllers.GetBank.getCityBank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getbank""","""controllers.GetBank.getBank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getcountrybank""","""controllers.GetBank.getGroupBank()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """validatevoucher""","""controllers.ValidateVoucher.getVoucher()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/usersoperasional""","""controllers.User.indexOperasional(c:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/usersoperasional/users""","""controllers.User.listOperasional(c:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/usersoperasional/$id<[^/]+>""","""controllers.User.updateOperasional(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """corporates/$c<[^/]+>/usersoperasional/$id<[^/]+>/edit""","""controllers.User.editOperasional(c:String, id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sendBillPayment""","""controllers.Send.createBillPayment()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks""","""controllers.Banks.list(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks.csv""","""controllers.Banks.csv(s:String ?= "id", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks/$id<[^/]+>/summary""","""controllers.Banks.summary(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks/$id<[^/]+>/edit""","""controllers.Banks.edit(id:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks/$id<[^/]+>/edit""","""controllers.Banks.update(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks/create""","""controllers.Banks.create()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks/create""","""controllers.Banks.save()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """banks/delete/$id<[^/]+>""","""controllers.Banks.delete(id:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """channels""","""controllers.Channels.list(p:Int ?= 0, s:String ?= "code", o:String ?= "asc", f:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """channels/$code<[^/]+>/summary""","""controllers.Channels.summary(code:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """channels/$code<[^/]+>/edit""","""controllers.Channels.edit(code:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """channels/$code<[^/]+>/edit""","""controllers.Channels.update(code:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """channels/delete/$code<[^/]+>""","""controllers.Channels.delete(code:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """kaptcha""","""controllers.Login.captcha()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """batchupload""","""controllers.BatchUpload.viewUpload()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dobatchupload""","""controllers.BatchUpload.actionUpload()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transactionInquiry""","""controllers.Transaction.listBatchInquiry(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", filter:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """batch/$id<[^/]+>""","""controllers.Transaction.showBatch(id:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """batchDetail/$batchId<[^/]+>/$m<[^/]+>""","""controllers.Transaction.showBatchDetail(batchId:Int, p:Int ?= 0, filter:String ?= "", m:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dobatchremit/$token<[^/]+>/$batchId<[^/]+>""","""controllers.BatchUpload.actionRemit(token:String, batchId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewbatchupload/$batchId<[^/]+>""","""controllers.BatchUpload.viewResultBatchUpload(batchId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewbatchuploadfailed""","""controllers.BatchUpload.viewResultBatchUploadFailed(fileName:String ?= "", totalRow:Int ?= 0, description:String ?= "")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """transactionbatch/$batchId<[^/]+>""","""controllers.Transaction.showBatchDetailTransaction(p:Int ?= 0, s:String ?= "id", o:String ?= "desc", filterTransId:String ?= "", batchId:Int)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """print/$id<[^/]+>/$type<[^/]+>""","""controllers.Transaction.receipt(id:String, type:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:11
case controllers_Main_index0(params) => {
   call { 
        invokeHandler(controllers.Main.index(), HandlerDef(this, "controllers.Main", "index", Nil,"GET", """# Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:12
case controllers_Dashboard_index1(params) => {
   call { 
        invokeHandler(controllers.Dashboard.index(), HandlerDef(this, "controllers.Dashboard", "index", Nil,"GET", """""", Routes.prefix + """dashboard"""))
   }
}
        

// @LINE:13
case controllers_Dashboard_userPerformances2(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Dashboard.userPerformances(id), HandlerDef(this, "controllers.Dashboard", "userPerformances", Seq(classOf[String]),"GET", """""", Routes.prefix + """dashboard/statistics/$id<[^/]+>/performances.json"""))
   }
}
        

// @LINE:14
case controllers_Dashboard_userVolumes3(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Dashboard.userVolumes(id), HandlerDef(this, "controllers.Dashboard", "userVolumes", Seq(classOf[String]),"GET", """""", Routes.prefix + """dashboard/statistics/$id<[^/]+>/volumes.json"""))
   }
}
        

// @LINE:15
case controllers_Dashboard_statistics4(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Dashboard.statistics(id), HandlerDef(this, "controllers.Dashboard", "statistics", Seq(classOf[String]),"GET", """""", Routes.prefix + """dashboard/statistics/$id<[^/]+>"""))
   }
}
        

// @LINE:16
case controllers_Dashboard_news5(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Dashboard.news(id), HandlerDef(this, "controllers.Dashboard", "news", Seq(classOf[Long]),"GET", """""", Routes.prefix + """news/content/$id<[^/]+>"""))
   }
}
        

// @LINE:20
case controllers_Login_login6(params) => {
   call { 
        invokeHandler(controllers.Login.login(), HandlerDef(this, "controllers.Login", "login", Nil,"GET", """# Authentication""", Routes.prefix + """login"""))
   }
}
        

// @LINE:21
case controllers_Login_authenticate7(params) => {
   call { 
        invokeHandler(controllers.Login.authenticate(), HandlerDef(this, "controllers.Login", "authenticate", Nil,"POST", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:22
case controllers_Login_logout8(params) => {
   call { 
        invokeHandler(controllers.Login.logout(), HandlerDef(this, "controllers.Login", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:23
case controllers_Login_forgetPassword9(params) => {
   call { 
        invokeHandler(controllers.Login.forgetPassword(), HandlerDef(this, "controllers.Login", "forgetPassword", Nil,"POST", """""", Routes.prefix + """forget-password"""))
   }
}
        

// @LINE:24
case controllers_Login_resetPassword10(params) => {
   call(params.fromPath[String]("key", None)) { (key) =>
        invokeHandler(controllers.Login.resetPassword(key), HandlerDef(this, "controllers.Login", "resetPassword", Seq(classOf[String]),"GET", """""", Routes.prefix + """reset-password/$key<[^/]+>"""))
   }
}
        

// @LINE:25
case controllers_Login_resetPasswordSubmit11(params) => {
   call { 
        invokeHandler(controllers.Login.resetPasswordSubmit(), HandlerDef(this, "controllers.Login", "resetPasswordSubmit", Nil,"POST", """""", Routes.prefix + """reset-password"""))
   }
}
        

// @LINE:26
case controllers_Login_setLang12(params) => {
   call(params.fromPath[String]("langId", None)) { (langId) =>
        invokeHandler(controllers.Login.setLang(langId), HandlerDef(this, "controllers.Login", "setLang", Seq(classOf[String]),"GET", """""", Routes.prefix + """changeLang/$langId<[^/]+>/login"""))
   }
}
        

// @LINE:27
case controllers_Login_setLangAdmin13(params) => {
   call(params.fromPath[String]("langId", None)) { (langId) =>
        invokeHandler(controllers.Login.setLangAdmin(langId), HandlerDef(this, "controllers.Login", "setLangAdmin", Seq(classOf[String]),"GET", """""", Routes.prefix + """changeLang/$langId<[^/]+>/admin/login"""))
   }
}
        

// @LINE:30
case controllers_User_index14(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.User.index(c), HandlerDef(this, "controllers.User", "index", Seq(classOf[String]),"GET", """# User Management""", Routes.prefix + """corporates/$c<[^/]+>/users/me"""))
   }
}
        

// @LINE:31
case controllers_User_list15(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.list(c, id), HandlerDef(this, "controllers.User", "list", Seq(classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/users"""))
   }
}
        

// @LINE:32
case controllers_User_newCreate16(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[String]("role", None), params.fromQuery[Long]("supervisorId", None)) { (c, role, supervisorId) =>
        invokeHandler(controllers.User.newCreate(c, role, supervisorId), HandlerDef(this, "controllers.User", "newCreate", Seq(classOf[String], classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/new/$role<[^/]+>"""))
   }
}
        

// @LINE:33
case controllers_User_edit17(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.edit(c, id), HandlerDef(this, "controllers.User", "edit", Seq(classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:34
case controllers_User_create18(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.User.create(c), HandlerDef(this, "controllers.User", "create", Seq(classOf[String]),"POST", """""", Routes.prefix + """corporates/$c<[^/]+>/users"""))
   }
}
        

// @LINE:35
case controllers_User_update19(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.update(c, id), HandlerDef(this, "controllers.User", "update", Seq(classOf[String], classOf[Long]),"POST", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>"""))
   }
}
        

// @LINE:36
case controllers_User_destroy20(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.destroy(c, id), HandlerDef(this, "controllers.User", "destroy", Seq(classOf[String], classOf[Long]),"DELETE", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>"""))
   }
}
        

// @LINE:37
case controllers_User_show21(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.show(c, id), HandlerDef(this, "controllers.User", "show", Seq(classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>"""))
   }
}
        

// @LINE:38
case controllers_User_changePassword22(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.changePassword(c, id), HandlerDef(this, "controllers.User", "changePassword", Seq(classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/password"""))
   }
}
        

// @LINE:39
case controllers_User_saveChangePassword23(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.saveChangePassword(c, id), HandlerDef(this, "controllers.User", "saveChangePassword", Seq(classOf[String], classOf[Long]),"POST", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/password"""))
   }
}
        

// @LINE:40
case controllers_User_updateStatus24(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None), params.fromQuery[Boolean]("ta", Some(true))) { (c, id, ta) =>
        invokeHandler(controllers.User.updateStatus(c, id, ta), HandlerDef(this, "controllers.User", "updateStatus", Seq(classOf[String], classOf[Long], classOf[Boolean]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/status"""))
   }
}
        

// @LINE:41
case controllers_User_updateRolePromote25(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.updateRolePromote(c, id), HandlerDef(this, "controllers.User", "updateRolePromote", Seq(classOf[String], classOf[Long]),"POST", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/promote"""))
   }
}
        

// @LINE:42
case controllers_User_updateRoleDemote26(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.updateRoleDemote(c, id), HandlerDef(this, "controllers.User", "updateRoleDemote", Seq(classOf[String], classOf[Long]),"POST", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/demote"""))
   }
}
        

// @LINE:43
case controllers_User_updateRoleDemoteForm27(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.updateRoleDemoteForm(c, id), HandlerDef(this, "controllers.User", "updateRoleDemoteForm", Seq(classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/demoteForm"""))
   }
}
        

// @LINE:47
case controllers_Customer_list28(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Customer.list(p, s, o, f), HandlerDef(this, "controllers.Customer", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Customer Management""", Routes.prefix + """customer"""))
   }
}
        

// @LINE:48
case controllers_Customer_csv29(params) => {
   call(params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (s, o, f) =>
        invokeHandler(controllers.Customer.csv(s, o, f), HandlerDef(this, "controllers.Customer", "csv", Seq(classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """customer.csv"""))
   }
}
        

// @LINE:51
case controllers_Customer_create30(params) => {
   call { 
        invokeHandler(controllers.Customer.create(), HandlerDef(this, "controllers.Customer", "create", Nil,"GET", """ Create""", Routes.prefix + """customer/create"""))
   }
}
        

// @LINE:52
case controllers_Customer_save31(params) => {
   call { 
        invokeHandler(controllers.Customer.save(), HandlerDef(this, "controllers.Customer", "save", Nil,"POST", """""", Routes.prefix + """customer/create"""))
   }
}
        

// @LINE:53
case controllers_Customer_summary32(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Customer.summary(id), HandlerDef(this, "controllers.Customer", "summary", Seq(classOf[Long]),"GET", """""", Routes.prefix + """customer/$id<[^/]+>/summary"""))
   }
}
        

// @LINE:54
case controllers_Customer_edit33(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Customer.edit(id), HandlerDef(this, "controllers.Customer", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """customer/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:55
case controllers_Customer_update34(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Customer.update(id), HandlerDef(this, "controllers.Customer", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """customer/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:57
case controllers_Customer_relate35(params) => {
   call(params.fromQuery[Long]("id", None), params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (id, p, s, o, f) =>
        invokeHandler(controllers.Customer.relate(id, p, s, o, f), HandlerDef(this, "controllers.Customer", "relate", Seq(classOf[Long], classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """POST    /customer/:id/delete                controllers.Customer.delete(id:Long)""", Routes.prefix + """customer/relate"""))
   }
}
        

// @LINE:58
case controllers_Customer_addRelate36(params) => {
   call(params.fromQuery[Long]("id", None), params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (id, p, s, o, f) =>
        invokeHandler(controllers.Customer.addRelate(id, p, s, o, f), HandlerDef(this, "controllers.Customer", "addRelate", Seq(classOf[Long], classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """customer/addrelate"""))
   }
}
        

// @LINE:59
case controllers_Customer_addRelateAction37(params) => {
   call { 
        invokeHandler(controllers.Customer.addRelateAction(), HandlerDef(this, "controllers.Customer", "addRelateAction", Nil,"POST", """""", Routes.prefix + """customer/addrelateaction"""))
   }
}
        

// @LINE:60
case controllers_Customer_deleteRelate38(params) => {
   call(params.fromQuery[Long]("id", None), params.fromQuery[Long]("idRelate", None)) { (id, idRelate) =>
        invokeHandler(controllers.Customer.deleteRelate(id, idRelate), HandlerDef(this, "controllers.Customer", "deleteRelate", Seq(classOf[Long], classOf[Long]),"GET", """""", Routes.prefix + """customer/deleterelate"""))
   }
}
        

// @LINE:63
case controllers_Send_create39(params) => {
   call { 
        invokeHandler(controllers.Send.create(), HandlerDef(this, "controllers.Send", "create", Nil,"GET", """# Transaction Send Money""", Routes.prefix + """send"""))
   }
}
        

// @LINE:64
case controllers_Send_inquiry40(params) => {
   call { 
        invokeHandler(controllers.Send.inquiry(), HandlerDef(this, "controllers.Send", "inquiry", Nil,"GET", """""", Routes.prefix + """send/inquiry"""))
   }
}
        

// @LINE:65
case controllers_Send_inquiryBillPayment41(params) => {
   call { 
        invokeHandler(controllers.Send.inquiryBillPayment(), HandlerDef(this, "controllers.Send", "inquiryBillPayment", Nil,"GET", """""", Routes.prefix + """send/inquiryBillPayment"""))
   }
}
        

// @LINE:66
case controllers_Send_customerLookup42(params) => {
   call { 
        invokeHandler(controllers.Send.customerLookup(), HandlerDef(this, "controllers.Send", "customerLookup", Nil,"GET", """""", Routes.prefix + """send/customer-lookup"""))
   }
}
        

// @LINE:67
case controllers_Send_customerLookupRelate43(params) => {
   call { 
        invokeHandler(controllers.Send.customerLookupRelate(), HandlerDef(this, "controllers.Send", "customerLookupRelate", Nil,"GET", """""", Routes.prefix + """send/customer-lookupRelate"""))
   }
}
        

// @LINE:68
case controllers_Send_verify44(params) => {
   call { 
        invokeHandler(controllers.Send.verify(), HandlerDef(this, "controllers.Send", "verify", Nil,"POST", """""", Routes.prefix + """send/verify"""))
   }
}
        

// @LINE:69
case controllers_Send_confirm45(params) => {
   call { 
        invokeHandler(controllers.Send.confirm(), HandlerDef(this, "controllers.Send", "confirm", Nil,"POST", """""", Routes.prefix + """send/confirm"""))
   }
}
        

// @LINE:70
case controllers_Send_confirmBillPayment46(params) => {
   call { 
        invokeHandler(controllers.Send.confirmBillPayment(), HandlerDef(this, "controllers.Send", "confirmBillPayment", Nil,"POST", """""", Routes.prefix + """send/confirmBillPayment"""))
   }
}
        

// @LINE:71
case controllers_Send_process47(params) => {
   call { 
        invokeHandler(controllers.Send.process(), HandlerDef(this, "controllers.Send", "process", Nil,"POST", """""", Routes.prefix + """send/process"""))
   }
}
        

// @LINE:72
case controllers_Send_processBillPayment48(params) => {
   call { 
        invokeHandler(controllers.Send.processBillPayment(), HandlerDef(this, "controllers.Send", "processBillPayment", Nil,"POST", """""", Routes.prefix + """send/processBillPayment"""))
   }
}
        

// @LINE:73
case controllers_Send_receipt49(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Send.receipt(id), HandlerDef(this, "controllers.Send", "receipt", Seq(classOf[String]),"GET", """""", Routes.prefix + """send/$id<[^/]+>/receipt"""))
   }
}
        

// @LINE:74
case controllers_Send_receiptBillPayment50(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Send.receiptBillPayment(id), HandlerDef(this, "controllers.Send", "receiptBillPayment", Seq(classOf[String]),"GET", """""", Routes.prefix + """send/$id<[^/]+>/receiptBillPayment"""))
   }
}
        

// @LINE:75
case controllers_Send_sendTransactionViaEmail51(params) => {
   call(params.fromPath[String]("id", None), Param[String]("recipient", Right(null))) { (id, recipient) =>
        invokeHandler(controllers.Send.sendTransactionViaEmail(id, recipient), HandlerDef(this, "controllers.Send", "sendTransactionViaEmail", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """send/$id<[^/]+>/send-email"""))
   }
}
        

// @LINE:76
case controllers_Send_sendTransactionViaEmail52(params) => {
   call(params.fromPath[String]("id", None), params.fromPath[String]("recipient", None)) { (id, recipient) =>
        invokeHandler(controllers.Send.sendTransactionViaEmail(id, recipient), HandlerDef(this, "controllers.Send", "sendTransactionViaEmail", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """send/$id<[^/]+>/send-email/$recipient<[^/]+>"""))
   }
}
        

// @LINE:77
case controllers_Send_summary53(params) => {
   call { 
        invokeHandler(controllers.Send.summary(), HandlerDef(this, "controllers.Send", "summary", Nil,"POST", """""", Routes.prefix + """send/summary"""))
   }
}
        

// @LINE:78
case controllers_Send_customerNew54(params) => {
   call { 
        invokeHandler(controllers.Send.customerNew(), HandlerDef(this, "controllers.Send", "customerNew", Nil,"GET", """""", Routes.prefix + """send/customer/new"""))
   }
}
        

// @LINE:79
case controllers_Send_customerSearch55(params) => {
   call(params.fromPath[String]("query", None)) { (query) =>
        invokeHandler(controllers.Send.customerSearch(query), HandlerDef(this, "controllers.Send", "customerSearch", Seq(classOf[String]),"GET", """""", Routes.prefix + """send/customer/search/$query<[^/]+>"""))
   }
}
        

// @LINE:80
case controllers_Send_customerEdit56(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Send.customerEdit(id), HandlerDef(this, "controllers.Send", "customerEdit", Seq(classOf[String]),"GET", """""", Routes.prefix + """send/customer/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:81
case controllers_Send_customerSave57(params) => {
   call { 
        invokeHandler(controllers.Send.customerSave(), HandlerDef(this, "controllers.Send", "customerSave", Nil,"POST", """""", Routes.prefix + """send/customer/save"""))
   }
}
        

// @LINE:83
case controllers_Send_receiptPrint58(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Send.receiptPrint(id), HandlerDef(this, "controllers.Send", "receiptPrint", Seq(classOf[String]),"GET", """PUT     /send/customer/save                controllers.Send.customerSave()""", Routes.prefix + """send/$id<[^/]+>/receipt-print"""))
   }
}
        

// @LINE:84
case controllers_Send_receiptEmail59(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Send.receiptEmail(id), HandlerDef(this, "controllers.Send", "receiptEmail", Seq(classOf[String]),"GET", """""", Routes.prefix + """send/$id<[^/]+>/receipt-email"""))
   }
}
        

// @LINE:87
case controllers_Receive_create60(params) => {
   call { 
        invokeHandler(controllers.Receive.create(), HandlerDef(this, "controllers.Receive", "create", Nil,"GET", """# Transaction Receive Money""", Routes.prefix + """receive"""))
   }
}
        

// @LINE:88
case controllers_Receive_inquiry61(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.inquiry(id), HandlerDef(this, "controllers.Receive", "inquiry", Seq(classOf[String]),"GET", """""", Routes.prefix + """receive/$id<[^/]+>"""))
   }
}
        

// @LINE:89
case controllers_Receive_verify62(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.verify(id), HandlerDef(this, "controllers.Receive", "verify", Seq(classOf[String]),"POST", """""", Routes.prefix + """receive/$id<[^/]+>/verify"""))
   }
}
        

// @LINE:90
case controllers_Receive_validate63(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.validate(id), HandlerDef(this, "controllers.Receive", "validate", Seq(classOf[String]),"POST", """""", Routes.prefix + """receive/$id<[^/]+>/validate"""))
   }
}
        

// @LINE:91
case controllers_Receive_process64(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.process(id), HandlerDef(this, "controllers.Receive", "process", Seq(classOf[String]),"POST", """""", Routes.prefix + """receive/$id<[^/]+>/process"""))
   }
}
        

// @LINE:92
case controllers_Receive_receipt65(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.receipt(id), HandlerDef(this, "controllers.Receive", "receipt", Seq(classOf[String]),"GET", """""", Routes.prefix + """receive/$id<[^/]+>/receipt"""))
   }
}
        

// @LINE:93
case controllers_Receive_receiptPrint66(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.receiptPrint(id), HandlerDef(this, "controllers.Receive", "receiptPrint", Seq(classOf[String]),"GET", """""", Routes.prefix + """receive/$id<[^/]+>/receipt-print"""))
   }
}
        

// @LINE:94
case controllers_Receive_receiptEmail67(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.receiptEmail(id), HandlerDef(this, "controllers.Receive", "receiptEmail", Seq(classOf[String]),"GET", """""", Routes.prefix + """receive/$id<[^/]+>/receipt-email"""))
   }
}
        

// @LINE:95
case controllers_Receive_sendTransactionViaEmail68(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Receive.sendTransactionViaEmail(id), HandlerDef(this, "controllers.Receive", "sendTransactionViaEmail", Seq(classOf[String]),"GET", """""", Routes.prefix + """receive/$id<[^/]+>/send-email"""))
   }
}
        

// @LINE:100
case controllers_Transaction_list69(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("cashInTime")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("filterTransId", Some(""))) { (p, s, o, filterTransId) =>
        invokeHandler(controllers.Transaction.list(p, s, o, filterTransId), HandlerDef(this, "controllers.Transaction", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Transaction View Status""", Routes.prefix + """transaction"""))
   }
}
        

// @LINE:101
case controllers_Transaction_refund70(params) => {
   call { 
        invokeHandler(controllers.Transaction.refund(), HandlerDef(this, "controllers.Transaction", "refund", Nil,"GET", """""", Routes.prefix + """transaction/refund"""))
   }
}
        

// @LINE:102
case controllers_Transaction_refundDetail71(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.refundDetail(idToken), HandlerDef(this, "controllers.Transaction", "refundDetail", Seq(classOf[String]),"GET", """""", Routes.prefix + """transaction/refund/$idToken<[^/]+>"""))
   }
}
        

// @LINE:103
case controllers_Transaction_refundProcess72(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.refundProcess(idToken), HandlerDef(this, "controllers.Transaction", "refundProcess", Seq(classOf[String]),"POST", """""", Routes.prefix + """transaction/refund/$idToken<[^/]+>"""))
   }
}
        

// @LINE:104
case controllers_Transaction_refundReceipt73(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.refundReceipt(idToken), HandlerDef(this, "controllers.Transaction", "refundReceipt", Seq(classOf[String]),"GET", """""", Routes.prefix + """transaction/refund/$idToken<[^/]+>/receipt"""))
   }
}
        

// @LINE:105
case controllers_Transaction_change74(params) => {
   call { 
        invokeHandler(controllers.Transaction.change(), HandlerDef(this, "controllers.Transaction", "change", Nil,"GET", """""", Routes.prefix + """transaction/change"""))
   }
}
        

// @LINE:106
case controllers_Transaction_changeDetail75(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.changeDetail(idToken), HandlerDef(this, "controllers.Transaction", "changeDetail", Seq(classOf[String]),"GET", """""", Routes.prefix + """transaction/change/$idToken<[^/]+>"""))
   }
}
        

// @LINE:107
case controllers_Transaction_changeProcess76(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.changeProcess(idToken), HandlerDef(this, "controllers.Transaction", "changeProcess", Seq(classOf[String]),"POST", """""", Routes.prefix + """transaction/change/$idToken<[^/]+>"""))
   }
}
        

// @LINE:108
case controllers_Transaction_changeReceipt77(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.changeReceipt(idToken), HandlerDef(this, "controllers.Transaction", "changeReceipt", Seq(classOf[String]),"GET", """""", Routes.prefix + """transaction/change/$idToken<[^/]+>/receipt"""))
   }
}
        

// @LINE:109
case controllers_Transaction_unlock78(params) => {
   call { 
        invokeHandler(controllers.Transaction.unlock(), HandlerDef(this, "controllers.Transaction", "unlock", Nil,"GET", """""", Routes.prefix + """transaction/unlock"""))
   }
}
        

// @LINE:110
case controllers_Transaction_unlockDetail79(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.unlockDetail(idToken), HandlerDef(this, "controllers.Transaction", "unlockDetail", Seq(classOf[String]),"GET", """""", Routes.prefix + """transaction/unlock/$idToken<[^/]+>"""))
   }
}
        

// @LINE:111
case controllers_Transaction_unlockProcess80(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.unlockProcess(idToken), HandlerDef(this, "controllers.Transaction", "unlockProcess", Seq(classOf[String]),"POST", """""", Routes.prefix + """transaction/unlock/$idToken<[^/]+>"""))
   }
}
        

// @LINE:112
case controllers_Transaction_show81(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Transaction.show(id), HandlerDef(this, "controllers.Transaction", "show", Seq(classOf[String]),"GET", """""", Routes.prefix + """transaction/$id<[^/]+>"""))
   }
}
        

// @LINE:115
case controllers_Report_list82(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc"))) { (p, s, o) =>
        invokeHandler(controllers.Report.list(p, s, o), HandlerDef(this, "controllers.Report", "list", Seq(classOf[Int], classOf[String], classOf[String]),"GET", """# Report""", Routes.prefix + """report"""))
   }
}
        

// @LINE:116
case controllers_Report_pdf83(params) => {
   call { 
        invokeHandler(controllers.Report.pdf(), HandlerDef(this, "controllers.Report", "pdf", Nil,"GET", """""", Routes.prefix + """report/pdf"""))
   }
}
        

// @LINE:117
case controllers_Report_excel84(params) => {
   call { 
        invokeHandler(controllers.Report.excel(), HandlerDef(this, "controllers.Report", "excel", Nil,"GET", """""", Routes.prefix + """report/excel"""))
   }
}
        

// @LINE:122
case controllers_Tools_checkRate85(params) => {
   call { 
        invokeHandler(controllers.Tools.checkRate(), HandlerDef(this, "controllers.Tools", "checkRate", Nil,"GET", """ Some tools""", Routes.prefix + """tools/checkRate"""))
   }
}
        

// @LINE:123
case controllers_Tools_checkRateSubmit86(params) => {
   call { 
        invokeHandler(controllers.Tools.checkRateSubmit(), HandlerDef(this, "controllers.Tools", "checkRateSubmit", Nil,"POST", """""", Routes.prefix + """tools/checkRate"""))
   }
}
        

// @LINE:126
case controllers_Tools_feedback87(params) => {
   call { 
        invokeHandler(controllers.Tools.feedback(), HandlerDef(this, "controllers.Tools", "feedback", Nil,"GET", """# Help""", Routes.prefix + """feedback"""))
   }
}
        

// @LINE:127
case controllers_Tools_feedbackSubmit88(params) => {
   call { 
        invokeHandler(controllers.Tools.feedbackSubmit(), HandlerDef(this, "controllers.Tools", "feedbackSubmit", Nil,"POST", """""", Routes.prefix + """feedback"""))
   }
}
        

// @LINE:128
case controllers_Tools_documentation89(params) => {
   call { 
        invokeHandler(controllers.Tools.documentation(), HandlerDef(this, "controllers.Tools", "documentation", Nil,"GET", """""", Routes.prefix + """documentation"""))
   }
}
        

// @LINE:131
case controllers_News_list90(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc"))) { (p, s, o) =>
        invokeHandler(controllers.News.list(p, s, o), HandlerDef(this, "controllers.News", "list", Seq(classOf[Int], classOf[String], classOf[String]),"GET", """# News""", Routes.prefix + """news/list"""))
   }
}
        

// @LINE:132
case controllers_News_create91(params) => {
   call { 
        invokeHandler(controllers.News.create(), HandlerDef(this, "controllers.News", "create", Nil,"GET", """""", Routes.prefix + """news/create"""))
   }
}
        

// @LINE:133
case controllers_News_save92(params) => {
   call { 
        invokeHandler(controllers.News.save(), HandlerDef(this, "controllers.News", "save", Nil,"POST", """""", Routes.prefix + """news/save"""))
   }
}
        

// @LINE:134
case controllers_News_edit93(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.News.edit(id), HandlerDef(this, "controllers.News", "edit", Seq(classOf[Long]),"GET", """""", Routes.prefix + """news/edit/$id<[^/]+>"""))
   }
}
        

// @LINE:135
case controllers_News_update94(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.News.update(id), HandlerDef(this, "controllers.News", "update", Seq(classOf[Long]),"POST", """""", Routes.prefix + """news/update/$id<[^/]+>"""))
   }
}
        

// @LINE:136
case controllers_News_status95(params) => {
   call(params.fromPath[Long]("id", None), params.fromPath[String]("status", None)) { (id, status) =>
        invokeHandler(controllers.News.status(id, status), HandlerDef(this, "controllers.News", "status", Seq(classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """news/status/$id<[^/]+>/$status<[^/]+>"""))
   }
}
        

// @LINE:139
case controllers_Forex_table96(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.Forex.table(c), HandlerDef(this, "controllers.Forex", "table", Seq(classOf[String]),"GET", """# FOrex""", Routes.prefix + """forexs/$c<[^/]+>/table"""))
   }
}
        

// @LINE:141
case controllers_Corporate_logo97(params) => {
   call(params.fromPath[String]("id", None), params.fromQuery[String]("s", Some("medium"))) { (id, s) =>
        invokeHandler(controllers.Corporate.logo(id, s), HandlerDef(this, "controllers.Corporate", "logo", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """corporates/$id<[^/]+>/logo"""))
   }
}
        

// @LINE:142
case controllers_User_photo98(params) => {
   call(params.fromPath[Long]("id", None), params.fromQuery[String]("s", Some("medium"))) { (id, s) =>
        invokeHandler(controllers.User.photo(id, s), HandlerDef(this, "controllers.User", "photo", Seq(classOf[Long], classOf[String]),"GET", """""", Routes.prefix + """users/$id<[^/]+>/photo"""))
   }
}
        

// @LINE:143
case controllers_User_css99(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.User.css(id), HandlerDef(this, "controllers.User", "css", Seq(classOf[Long]),"GET", """""", Routes.prefix + """users/$id<[^/]+>/css"""))
   }
}
        

// @LINE:144
case controllers_Helpers_currencies100(params) => {
   call { 
        invokeHandler(controllers.Helpers.currencies(), HandlerDef(this, "controllers.Helpers", "currencies", Nil,"GET", """""", Routes.prefix + """helpers/currencies.json"""))
   }
}
        

// @LINE:145
case controllers_Helpers_cities101(params) => {
   call { 
        invokeHandler(controllers.Helpers.cities(), HandlerDef(this, "controllers.Helpers", "cities", Nil,"GET", """""", Routes.prefix + """helpers/cities.json"""))
   }
}
        

// @LINE:146
case controllers_Helpers_banks102(params) => {
   call { 
        invokeHandler(controllers.Helpers.banks(), HandlerDef(this, "controllers.Helpers", "banks", Nil,"GET", """""", Routes.prefix + """helpers/banks.json"""))
   }
}
        

// @LINE:147
case controllers_Helpers_banks_detail103(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Helpers.banks_detail(id), HandlerDef(this, "controllers.Helpers", "banks_detail", Seq(classOf[String]),"GET", """""", Routes.prefix + """helpers/banks/$id<[^/]+>.json"""))
   }
}
        

// @LINE:155
case controllers_Main_admin_index104(params) => {
   call { 
        invokeHandler(controllers.Main.admin_index(), HandlerDef(this, "controllers.Main", "admin_index", Nil,"GET", """####################################
# Backend Page
# Admin page""", Routes.prefix + """admin"""))
   }
}
        

// @LINE:156
case controllers_Dashboard_admin_index105(params) => {
   call { 
        invokeHandler(controllers.Dashboard.admin_index(), HandlerDef(this, "controllers.Dashboard", "admin_index", Nil,"GET", """""", Routes.prefix + """admin/dashboard"""))
   }
}
        

// @LINE:160
case controllers_Login_admin_login106(params) => {
   call { 
        invokeHandler(controllers.Login.admin_login(), HandlerDef(this, "controllers.Login", "admin_login", Nil,"GET", """# Admin Authentication""", Routes.prefix + """admin/login"""))
   }
}
        

// @LINE:161
case controllers_Login_admin_authenticate107(params) => {
   call { 
        invokeHandler(controllers.Login.admin_authenticate(), HandlerDef(this, "controllers.Login", "admin_authenticate", Nil,"POST", """""", Routes.prefix + """admin/login"""))
   }
}
        

// @LINE:162
case controllers_Login_admin_logout108(params) => {
   call { 
        invokeHandler(controllers.Login.admin_logout(), HandlerDef(this, "controllers.Login", "admin_logout", Nil,"GET", """""", Routes.prefix + """admin/logout"""))
   }
}
        

// @LINE:166
case controllers_Corporate_admin_index109(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("code")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Corporate.admin_index(p, s, o, f), HandlerDef(this, "controllers.Corporate", "admin_index", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Corporate Management""", Routes.prefix + """admin/corporates"""))
   }
}
        

// @LINE:167
case controllers_Corporate_admin_new110(params) => {
   call { 
        invokeHandler(controllers.Corporate.admin_new(), HandlerDef(this, "controllers.Corporate", "admin_new", Nil,"GET", """""", Routes.prefix + """admin/corporates/new"""))
   }
}
        

// @LINE:168
case controllers_Corporate_admin_create111(params) => {
   call { 
        invokeHandler(controllers.Corporate.admin_create(), HandlerDef(this, "controllers.Corporate", "admin_create", Nil,"POST", """""", Routes.prefix + """admin/corporates"""))
   }
}
        

// @LINE:169
case controllers_Corporate_show112(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Corporate.show(id), HandlerDef(this, "controllers.Corporate", "show", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/corporates/$id<[^/]+>"""))
   }
}
        

// @LINE:170
case controllers_Corporate_admin_edit113(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Corporate.admin_edit(id), HandlerDef(this, "controllers.Corporate", "admin_edit", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/corporates/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:171
case controllers_Corporate_admin_update114(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Corporate.admin_update(id), HandlerDef(this, "controllers.Corporate", "admin_update", Seq(classOf[String]),"POST", """""", Routes.prefix + """admin/corporates/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:178
case controllers_Fee_admin_index115(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.Fee.admin_index(c), HandlerDef(this, "controllers.Fee", "admin_index", Seq(classOf[String]),"GET", """# Corporate Fees Management""", Routes.prefix + """admin/corporates/$c<[^/]+>/fees"""))
   }
}
        

// @LINE:179
case controllers_Fee_create116(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.Fee.create(c), HandlerDef(this, "controllers.Fee", "create", Seq(classOf[String]),"POST", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/fees"""))
   }
}
        

// @LINE:180
case controllers_Fee_destroy117(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.Fee.destroy(c, id), HandlerDef(this, "controllers.Fee", "destroy", Seq(classOf[String], classOf[Long]),"DELETE", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/fees/$id<[^/]+>"""))
   }
}
        

// @LINE:183
case controllers_Credit_admin_index118(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.Credit.admin_index(c), HandlerDef(this, "controllers.Credit", "admin_index", Seq(classOf[String]),"GET", """# Corporate Credit Management""", Routes.prefix + """admin/corporates/$c<[^/]+>/credit"""))
   }
}
        

// @LINE:184
case controllers_Credit_admin_update119(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.Credit.admin_update(c), HandlerDef(this, "controllers.Credit", "admin_update", Seq(classOf[String]),"PUT", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/credit"""))
   }
}
        

// @LINE:187
case controllers_Forex_admin_index_main120(params) => {
   call { 
        invokeHandler(controllers.Forex.admin_index_main(), HandlerDef(this, "controllers.Forex", "admin_index_main", Nil,"GET", """# Corporate Forex Management""", Routes.prefix + """admin/corporates/DOK/forex"""))
   }
}
        

// @LINE:188
case controllers_Forex_admin_index121(params) => {
   call(params.fromPath[String]("c", None), params.fromQuery[String]("origin", Some("")), params.fromQuery[String]("destination", Some(""))) { (c, origin, destination) =>
        invokeHandler(controllers.Forex.admin_index(c, origin, destination), HandlerDef(this, "controllers.Forex", "admin_index", Seq(classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/forex"""))
   }
}
        

// @LINE:189
case controllers_Forex_admin_create122(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.Forex.admin_create(c), HandlerDef(this, "controllers.Forex", "admin_create", Seq(classOf[String]),"POST", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/forex"""))
   }
}
        

// @LINE:190
case controllers_Forex_admin_update123(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.Forex.admin_update(c, id), HandlerDef(this, "controllers.Forex", "admin_update", Seq(classOf[String], classOf[Long]),"PUT", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/forex/$id<[^/]+>"""))
   }
}
        

// @LINE:191
case controllers_Forex_admin_destroy124(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.Forex.admin_destroy(c, id), HandlerDef(this, "controllers.Forex", "admin_destroy", Seq(classOf[String], classOf[Long]),"DELETE", """""", Routes.prefix + """admin/corporates/$c<[^/]+>/forex/$id<[^/]+>"""))
   }
}
        

// @LINE:194
case controllers_Audit_admin_index125(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("filterUserId", Some(null)), params.fromQuery[String]("filterTag", Some(null))) { (p, s, o, filterUserId, filterTag) =>
        invokeHandler(controllers.Audit.admin_index(p, s, o, filterUserId, filterTag), HandlerDef(this, "controllers.Audit", "admin_index", Seq(classOf[Int], classOf[String], classOf[String], classOf[String], classOf[String]),"GET", """# Audit""", Routes.prefix + """admin/audit"""))
   }
}
        

// @LINE:197
case controllers_CustomerBan_admin_list126(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.CustomerBan.admin_list(p, s, o, f), HandlerDef(this, "controllers.CustomerBan", "admin_list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Customer ban""", Routes.prefix + """admin/customerbans"""))
   }
}
        

// @LINE:198
case controllers_CustomerBan_admin_add127(params) => {
   call { 
        invokeHandler(controllers.CustomerBan.admin_add(), HandlerDef(this, "controllers.CustomerBan", "admin_add", Nil,"POST", """""", Routes.prefix + """admin/customerbans"""))
   }
}
        

// @LINE:199
case controllers_CustomerBan_admin_unban128(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.CustomerBan.admin_unban(id), HandlerDef(this, "controllers.CustomerBan", "admin_unban", Seq(classOf[Long]),"GET", """""", Routes.prefix + """admin/customerbans/$id<[^/]+>/unban"""))
   }
}
        

// @LINE:200
case controllers_CustomerBan_admin_interdictions129(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("country.code")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.CustomerBan.admin_interdictions(p, s, o, f), HandlerDef(this, "controllers.CustomerBan", "admin_interdictions", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """admin/customerbans/interdictions"""))
   }
}
        

// @LINE:201
case controllers_CustomerBan_admin_interdictionsUpload130(params) => {
   call { 
        invokeHandler(controllers.CustomerBan.admin_interdictionsUpload(), HandlerDef(this, "controllers.CustomerBan", "admin_interdictionsUpload", Nil,"POST", """""", Routes.prefix + """admin/customerbans/interdictions"""))
   }
}
        

// @LINE:202
case controllers_CustomerBan_admin_interdictionsDelete131(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.CustomerBan.admin_interdictionsDelete(id), HandlerDef(this, "controllers.CustomerBan", "admin_interdictionsDelete", Seq(classOf[Long]),"DELETE", """""", Routes.prefix + """admin/customerbans/interdictions/$id<[^/]+>"""))
   }
}
        

// @LINE:203
case controllers_Suspicious_admin_index132(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Suspicious.admin_index(p, s, o, f), HandlerDef(this, "controllers.Suspicious", "admin_index", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """admin/suspicious"""))
   }
}
        

// @LINE:204
case controllers_Suspicious_csv133(params) => {
   call(params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (s, o, f) =>
        invokeHandler(controllers.Suspicious.csv(s, o, f), HandlerDef(this, "controllers.Suspicious", "csv", Seq(classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """admin/suspicious.csv"""))
   }
}
        

// @LINE:208
case controllers_Transaction_admin_list134(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("cashInTime")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Transaction.admin_list(p, s, o, f), HandlerDef(this, "controllers.Transaction", "admin_list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Transaction management""", Routes.prefix + """admin/transactions"""))
   }
}
        

// @LINE:211
case controllers_Transaction_admin_refund135(params) => {
   call { 
        invokeHandler(controllers.Transaction.admin_refund(), HandlerDef(this, "controllers.Transaction", "admin_refund", Nil,"GET", """# Full Refund""", Routes.prefix + """admin/transaction/refund"""))
   }
}
        

// @LINE:212
case controllers_Transaction_admin_refundDetail136(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.admin_refundDetail(idToken), HandlerDef(this, "controllers.Transaction", "admin_refundDetail", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/transaction/refund/$idToken<[^/]+>"""))
   }
}
        

// @LINE:213
case controllers_Transaction_admin_refundProcess137(params) => {
   call(params.fromPath[String]("idToken", None)) { (idToken) =>
        invokeHandler(controllers.Transaction.admin_refundProcess(idToken), HandlerDef(this, "controllers.Transaction", "admin_refundProcess", Seq(classOf[String]),"POST", """""", Routes.prefix + """admin/transaction/refund/$idToken<[^/]+>"""))
   }
}
        

// @LINE:219
case controllers_Assets_at138(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:225
case controllers_SetupShareSetting_view139(params) => {
   call(params.fromQuery[String]("name", None)) { (name) =>
        invokeHandler(controllers.SetupShareSetting.view(name), HandlerDef(this, "controllers.SetupShareSetting", "view", Seq(classOf[String]),"GET", """SETLEMENT REPORT""", Routes.prefix + """admin/viewsharesetting"""))
   }
}
        

// @LINE:226
case controllers_SetupShareSetting_save140(params) => {
   call { 
        invokeHandler(controllers.SetupShareSetting.save(), HandlerDef(this, "controllers.SetupShareSetting", "save", Nil,"POST", """""", Routes.prefix + """admin/savesharesetting"""))
   }
}
        

// @LINE:227
case controllers_SetupCutOffTime_view141(params) => {
   call { 
        invokeHandler(controllers.SetupCutOffTime.view(), HandlerDef(this, "controllers.SetupCutOffTime", "view", Nil,"GET", """""", Routes.prefix + """admin/viewcutofftime"""))
   }
}
        

// @LINE:228
case controllers_SetupCutOffTime_save142(params) => {
   call { 
        invokeHandler(controllers.SetupCutOffTime.save(), HandlerDef(this, "controllers.SetupCutOffTime", "save", Nil,"POST", """""", Routes.prefix + """admin/savecutofftime"""))
   }
}
        

// @LINE:229
case controllers_SettlementReport_list143(params) => {
   call { 
        invokeHandler(controllers.SettlementReport.list(), HandlerDef(this, "controllers.SettlementReport", "list", Nil,"GET", """""", Routes.prefix + """admin/settlementreport"""))
   }
}
        

// @LINE:230
case controllers_SettlementReport_listView144(params) => {
   call { 
        invokeHandler(controllers.SettlementReport.listView(), HandlerDef(this, "controllers.SettlementReport", "listView", Nil,"POST", """""", Routes.prefix + """admin/settlementreport"""))
   }
}
        

// @LINE:231
case controllers_SettlementReport_download145(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.SettlementReport.download(name), HandlerDef(this, "controllers.SettlementReport", "download", Seq(classOf[String]),"GET", """""", Routes.prefix + """admin/settlementreport/$name<[^/]+>"""))
   }
}
        

// @LINE:232
case controllers_SettlementReport_generateById146(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.SettlementReport.generateById(id), HandlerDef(this, "controllers.SettlementReport", "generateById", Seq(classOf[Long]),"POST", """""", Routes.prefix + """admin/settlementreports/$id<[^/]+>"""))
   }
}
        

// @LINE:235
case controllers_Translate_getPinyin147(params) => {
   call { 
        invokeHandler(controllers.Translate.getPinyin(), HandlerDef(this, "controllers.Translate", "getPinyin", Nil,"POST", """NETELIS""", Routes.prefix + """translate/pinyin"""))
   }
}
        

// @LINE:236
case controllers_Translate_getTCode148(params) => {
   call { 
        invokeHandler(controllers.Translate.getTCode(), HandlerDef(this, "controllers.Translate", "getTCode", Nil,"POST", """""", Routes.prefix + """translate/tcode"""))
   }
}
        

// @LINE:238
case controllers_GetBank_getProvinceBank149(params) => {
   call { 
        invokeHandler(controllers.GetBank.getProvinceBank(), HandlerDef(this, "controllers.GetBank", "getProvinceBank", Nil,"POST", """""", Routes.prefix + """getprovincebank"""))
   }
}
        

// @LINE:239
case controllers_GetBank_getCityBank150(params) => {
   call { 
        invokeHandler(controllers.GetBank.getCityBank(), HandlerDef(this, "controllers.GetBank", "getCityBank", Nil,"POST", """""", Routes.prefix + """getcitybank"""))
   }
}
        

// @LINE:240
case controllers_GetBank_getBank151(params) => {
   call { 
        invokeHandler(controllers.GetBank.getBank(), HandlerDef(this, "controllers.GetBank", "getBank", Nil,"POST", """""", Routes.prefix + """getbank"""))
   }
}
        

// @LINE:241
case controllers_GetBank_getGroupBank152(params) => {
   call { 
        invokeHandler(controllers.GetBank.getGroupBank(), HandlerDef(this, "controllers.GetBank", "getGroupBank", Nil,"POST", """""", Routes.prefix + """getcountrybank"""))
   }
}
        

// @LINE:242
case controllers_ValidateVoucher_getVoucher153(params) => {
   call { 
        invokeHandler(controllers.ValidateVoucher.getVoucher(), HandlerDef(this, "controllers.ValidateVoucher", "getVoucher", Nil,"POST", """""", Routes.prefix + """validatevoucher"""))
   }
}
        

// @LINE:245
case controllers_User_indexOperasional154(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.User.indexOperasional(c), HandlerDef(this, "controllers.User", "indexOperasional", Seq(classOf[String]),"GET", """OPERASIONAL""", Routes.prefix + """corporates/$c<[^/]+>/usersoperasional"""))
   }
}
        

// @LINE:246
case controllers_User_listOperasional155(params) => {
   call(params.fromPath[String]("c", None)) { (c) =>
        invokeHandler(controllers.User.listOperasional(c), HandlerDef(this, "controllers.User", "listOperasional", Seq(classOf[String]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/usersoperasional/users"""))
   }
}
        

// @LINE:247
case controllers_User_updateOperasional156(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.updateOperasional(c, id), HandlerDef(this, "controllers.User", "updateOperasional", Seq(classOf[String], classOf[Long]),"POST", """""", Routes.prefix + """corporates/$c<[^/]+>/usersoperasional/$id<[^/]+>"""))
   }
}
        

// @LINE:248
case controllers_User_editOperasional157(params) => {
   call(params.fromPath[String]("c", None), params.fromPath[Long]("id", None)) { (c, id) =>
        invokeHandler(controllers.User.editOperasional(c, id), HandlerDef(this, "controllers.User", "editOperasional", Seq(classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """corporates/$c<[^/]+>/usersoperasional/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:251
case controllers_Send_createBillPayment158(params) => {
   call { 
        invokeHandler(controllers.Send.createBillPayment(), HandlerDef(this, "controllers.Send", "createBillPayment", Nil,"GET", """# Transaction Send Money""", Routes.prefix + """sendBillPayment"""))
   }
}
        

// @LINE:254
case controllers_Banks_list159(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Banks.list(p, s, o, f), HandlerDef(this, "controllers.Banks", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Banks Management""", Routes.prefix + """banks"""))
   }
}
        

// @LINE:255
case controllers_Banks_csv160(params) => {
   call(params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (s, o, f) =>
        invokeHandler(controllers.Banks.csv(s, o, f), HandlerDef(this, "controllers.Banks", "csv", Seq(classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """banks.csv"""))
   }
}
        

// @LINE:256
case controllers_Banks_summary161(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Banks.summary(id), HandlerDef(this, "controllers.Banks", "summary", Seq(classOf[String]),"GET", """""", Routes.prefix + """banks/$id<[^/]+>/summary"""))
   }
}
        

// @LINE:257
case controllers_Banks_edit162(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Banks.edit(id), HandlerDef(this, "controllers.Banks", "edit", Seq(classOf[String]),"GET", """""", Routes.prefix + """banks/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:258
case controllers_Banks_update163(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Banks.update(id), HandlerDef(this, "controllers.Banks", "update", Seq(classOf[String]),"POST", """""", Routes.prefix + """banks/$id<[^/]+>/edit"""))
   }
}
        

// @LINE:259
case controllers_Banks_create164(params) => {
   call { 
        invokeHandler(controllers.Banks.create(), HandlerDef(this, "controllers.Banks", "create", Nil,"GET", """""", Routes.prefix + """banks/create"""))
   }
}
        

// @LINE:260
case controllers_Banks_save165(params) => {
   call { 
        invokeHandler(controllers.Banks.save(), HandlerDef(this, "controllers.Banks", "save", Nil,"POST", """""", Routes.prefix + """banks/create"""))
   }
}
        

// @LINE:261
case controllers_Banks_delete166(params) => {
   call(params.fromPath[String]("id", None)) { (id) =>
        invokeHandler(controllers.Banks.delete(id), HandlerDef(this, "controllers.Banks", "delete", Seq(classOf[String]),"GET", """""", Routes.prefix + """banks/delete/$id<[^/]+>"""))
   }
}
        

// @LINE:264
case controllers_Channels_list167(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("code")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Channels.list(p, s, o, f), HandlerDef(this, "controllers.Channels", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """# Channels Management""", Routes.prefix + """channels"""))
   }
}
        

// @LINE:265
case controllers_Channels_summary168(params) => {
   call(params.fromPath[String]("code", None)) { (code) =>
        invokeHandler(controllers.Channels.summary(code), HandlerDef(this, "controllers.Channels", "summary", Seq(classOf[String]),"GET", """""", Routes.prefix + """channels/$code<[^/]+>/summary"""))
   }
}
        

// @LINE:266
case controllers_Channels_edit169(params) => {
   call(params.fromPath[String]("code", None)) { (code) =>
        invokeHandler(controllers.Channels.edit(code), HandlerDef(this, "controllers.Channels", "edit", Seq(classOf[String]),"GET", """""", Routes.prefix + """channels/$code<[^/]+>/edit"""))
   }
}
        

// @LINE:267
case controllers_Channels_update170(params) => {
   call(params.fromPath[String]("code", None)) { (code) =>
        invokeHandler(controllers.Channels.update(code), HandlerDef(this, "controllers.Channels", "update", Seq(classOf[String]),"POST", """""", Routes.prefix + """channels/$code<[^/]+>/edit"""))
   }
}
        

// @LINE:268
case controllers_Channels_delete171(params) => {
   call(params.fromPath[String]("code", None)) { (code) =>
        invokeHandler(controllers.Channels.delete(code), HandlerDef(this, "controllers.Channels", "delete", Seq(classOf[String]),"GET", """""", Routes.prefix + """channels/delete/$code<[^/]+>"""))
   }
}
        

// @LINE:270
case controllers_Login_captcha172(params) => {
   call { 
        invokeHandler(controllers.Login.captcha(), HandlerDef(this, "controllers.Login", "captcha", Nil,"GET", """""", Routes.prefix + """kaptcha"""))
   }
}
        

// @LINE:272
case controllers_BatchUpload_viewUpload173(params) => {
   call { 
        invokeHandler(controllers.BatchUpload.viewUpload(), HandlerDef(this, "controllers.BatchUpload", "viewUpload", Nil,"GET", """""", Routes.prefix + """batchupload"""))
   }
}
        

// @LINE:273
case controllers_BatchUpload_actionUpload174(params) => {
   call { 
        invokeHandler(controllers.BatchUpload.actionUpload(), HandlerDef(this, "controllers.BatchUpload", "actionUpload", Nil,"POST", """""", Routes.prefix + """dobatchupload"""))
   }
}
        

// @LINE:274
case controllers_Transaction_listBatchInquiry175(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("filter", Some(""))) { (p, s, o, filter) =>
        invokeHandler(controllers.Transaction.listBatchInquiry(p, s, o, filter), HandlerDef(this, "controllers.Transaction", "listBatchInquiry", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """transactionInquiry"""))
   }
}
        

// @LINE:275
case controllers_Transaction_showBatch176(params) => {
   call(params.fromPath[Int]("id", None)) { (id) =>
        invokeHandler(controllers.Transaction.showBatch(id), HandlerDef(this, "controllers.Transaction", "showBatch", Seq(classOf[Int]),"GET", """""", Routes.prefix + """batch/$id<[^/]+>"""))
   }
}
        

// @LINE:276
case controllers_Transaction_showBatchDetail177(params) => {
   call(params.fromPath[Int]("batchId", None), params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("filter", Some("")), params.fromPath[String]("m", None)) { (batchId, p, filter, m) =>
        invokeHandler(controllers.Transaction.showBatchDetail(batchId, p, filter, m), HandlerDef(this, "controllers.Transaction", "showBatchDetail", Seq(classOf[Int], classOf[Int], classOf[String], classOf[String]),"GET", """""", Routes.prefix + """batchDetail/$batchId<[^/]+>/$m<[^/]+>"""))
   }
}
        

// @LINE:277
case controllers_BatchUpload_actionRemit178(params) => {
   call(params.fromPath[String]("token", None), params.fromPath[Int]("batchId", None)) { (token, batchId) =>
        invokeHandler(controllers.BatchUpload.actionRemit(token, batchId), HandlerDef(this, "controllers.BatchUpload", "actionRemit", Seq(classOf[String], classOf[Int]),"POST", """""", Routes.prefix + """dobatchremit/$token<[^/]+>/$batchId<[^/]+>"""))
   }
}
        

// @LINE:278
case controllers_BatchUpload_viewResultBatchUpload179(params) => {
   call(params.fromPath[Int]("batchId", None)) { (batchId) =>
        invokeHandler(controllers.BatchUpload.viewResultBatchUpload(batchId), HandlerDef(this, "controllers.BatchUpload", "viewResultBatchUpload", Seq(classOf[Int]),"GET", """""", Routes.prefix + """viewbatchupload/$batchId<[^/]+>"""))
   }
}
        

// @LINE:279
case controllers_BatchUpload_viewResultBatchUploadFailed180(params) => {
   call(params.fromQuery[String]("fileName", Some("")), params.fromQuery[Int]("totalRow", Some(0)), params.fromQuery[String]("description", Some(""))) { (fileName, totalRow, description) =>
        invokeHandler(controllers.BatchUpload.viewResultBatchUploadFailed(fileName, totalRow, description), HandlerDef(this, "controllers.BatchUpload", "viewResultBatchUploadFailed", Seq(classOf[String], classOf[Int], classOf[String]),"GET", """""", Routes.prefix + """viewbatchuploadfailed"""))
   }
}
        

// @LINE:281
case controllers_Transaction_showBatchDetailTransaction181(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("id")), params.fromQuery[String]("o", Some("desc")), params.fromQuery[String]("filterTransId", Some("")), params.fromPath[Int]("batchId", None)) { (p, s, o, filterTransId, batchId) =>
        invokeHandler(controllers.Transaction.showBatchDetailTransaction(p, s, o, filterTransId, batchId), HandlerDef(this, "controllers.Transaction", "showBatchDetailTransaction", Seq(classOf[Int], classOf[String], classOf[String], classOf[String], classOf[Int]),"GET", """""", Routes.prefix + """transactionbatch/$batchId<[^/]+>"""))
   }
}
        

// @LINE:283
case controllers_Transaction_receipt182(params) => {
   call(params.fromPath[String]("id", None), params.fromPath[String]("type", None)) { (id, playframework_escape_type) =>
        invokeHandler(controllers.Transaction.receipt(id, playframework_escape_type), HandlerDef(this, "controllers.Transaction", "receipt", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """print/$id<[^/]+>/$type<[^/]+>"""))
   }
}
        
}
    
}
        