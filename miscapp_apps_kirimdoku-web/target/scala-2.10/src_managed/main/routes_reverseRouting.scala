// @SOURCE:/Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-web/conf/routes
// @HASH:3042f729bd64a68c57a07f25723db69c21a309c3
// @DATE:Wed Jan 18 18:12:20 WIB 2017

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._
import java.net.URLEncoder

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:283
// @LINE:281
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:270
// @LINE:268
// @LINE:267
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:254
// @LINE:251
// @LINE:248
// @LINE:247
// @LINE:246
// @LINE:245
// @LINE:242
// @LINE:241
// @LINE:240
// @LINE:239
// @LINE:238
// @LINE:236
// @LINE:235
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:229
// @LINE:228
// @LINE:227
// @LINE:226
// @LINE:225
// @LINE:219
// @LINE:213
// @LINE:212
// @LINE:211
// @LINE:208
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:201
// @LINE:200
// @LINE:199
// @LINE:198
// @LINE:197
// @LINE:194
// @LINE:191
// @LINE:190
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:184
// @LINE:183
// @LINE:180
// @LINE:179
// @LINE:178
// @LINE:171
// @LINE:170
// @LINE:169
// @LINE:168
// @LINE:167
// @LINE:166
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:156
// @LINE:155
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:144
// @LINE:143
// @LINE:142
// @LINE:141
// @LINE:139
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:132
// @LINE:131
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:123
// @LINE:122
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:112
// @LINE:111
// @LINE:110
// @LINE:109
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:104
// @LINE:103
// @LINE:102
// @LINE:101
// @LINE:100
// @LINE:95
// @LINE:94
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:48
// @LINE:47
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
package controllers {

// @LINE:228
// @LINE:227
class ReverseSetupCutOffTime {
    

// @LINE:228
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/savecutofftime")
}
                                                

// @LINE:227
def view(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/viewcutofftime")
}
                                                
    
}
                          

// @LINE:180
// @LINE:179
// @LINE:178
class ReverseFee {
    

// @LINE:180
def destroy(c:String, id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/fees/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:178
def admin_index(c:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/fees")
}
                                                

// @LINE:179
def create(c:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/fees")
}
                                                
    
}
                          

// @LINE:219
class ReverseAssets {
    

// @LINE:219
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:95
// @LINE:94
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
class ReverseReceive {
    

// @LINE:93
def receiptPrint(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receipt-print")
}
                                                

// @LINE:94
def receiptEmail(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receipt-email")
}
                                                

// @LINE:92
def receipt(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receipt")
}
                                                

// @LINE:89
def verify(id:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/verify")
}
                                                

// @LINE:95
def sendTransactionViaEmail(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/send-email")
}
                                                

// @LINE:88
def inquiry(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:87
def create(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "receive")
}
                                                

// @LINE:90
def validate(id:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/validate")
}
                                                

// @LINE:91
def process(id:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "receive/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/process")
}
                                                
    
}
                          

// @LINE:194
class ReverseAudit {
    

// @LINE:194
def admin_index(p:Int = 0, s:String = "id", o:String = "desc", filterUserId:String = null, filterTag:String = null): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/audit" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(filterUserId == null) None else Some(implicitly[QueryStringBindable[String]].unbind("filterUserId", filterUserId)), if(filterTag == null) None else Some(implicitly[QueryStringBindable[String]].unbind("filterTag", filterTag)))))
}
                                                
    
}
                          

// @LINE:283
// @LINE:281
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:213
// @LINE:212
// @LINE:211
// @LINE:208
// @LINE:112
// @LINE:111
// @LINE:110
// @LINE:109
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:104
// @LINE:103
// @LINE:102
// @LINE:101
// @LINE:100
class ReverseTransaction {
    

// @LINE:213
def admin_refundProcess(idToken:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/transaction/refund/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:102
def refundDetail(idToken:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/refund/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:105
def change(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/change")
}
                                                

// @LINE:111
def unlockProcess(idToken:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "transaction/unlock/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:281
def showBatchDetailTransaction(p:Int = 0, s:String = "id", o:String = "desc", filterTransId:String = "", batchId:Int): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transactionbatch/" + implicitly[PathBindable[Int]].unbind("batchId", batchId) + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(filterTransId == "") None else Some(implicitly[QueryStringBindable[String]].unbind("filterTransId", filterTransId)))))
}
                                                

// @LINE:276
def showBatchDetail(batchId:Int, p:Int = 0, filter:String = "", m:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "batchDetail/" + implicitly[PathBindable[Int]].unbind("batchId", batchId) + "/" + implicitly[PathBindable[String]].unbind("m", URLEncoder.encode(m, "utf-8")) + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(filter == "") None else Some(implicitly[QueryStringBindable[String]].unbind("filter", filter)))))
}
                                                

// @LINE:109
def unlock(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/unlock")
}
                                                

// @LINE:211
def admin_refund(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/transaction/refund")
}
                                                

// @LINE:100
def list(p:Int = 0, s:String = "cashInTime", o:String = "desc", filterTransId:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "cashInTime") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(filterTransId == "") None else Some(implicitly[QueryStringBindable[String]].unbind("filterTransId", filterTransId)))))
}
                                                

// @LINE:104
def refundReceipt(idToken:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/refund/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")) + "/receipt")
}
                                                

// @LINE:112
def show(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:283
def receipt(id:String, playframework_escape_type:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "print/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/" + implicitly[PathBindable[String]].unbind("type", URLEncoder.encode(playframework_escape_type, "utf-8")))
}
                                                

// @LINE:107
def changeProcess(idToken:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "transaction/change/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:275
def showBatch(id:Int): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "batch/" + implicitly[PathBindable[Int]].unbind("id", id))
}
                                                

// @LINE:106
def changeDetail(idToken:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/change/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:212
def admin_refundDetail(idToken:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/transaction/refund/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:101
def refund(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/refund")
}
                                                

// @LINE:108
def changeReceipt(idToken:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/change/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")) + "/receipt")
}
                                                

// @LINE:208
def admin_list(p:Int = 0, s:String = "cashInTime", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/transactions" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "cashInTime") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:110
def unlockDetail(idToken:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/unlock/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:103
def refundProcess(idToken:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "transaction/refund/" + implicitly[PathBindable[String]].unbind("idToken", URLEncoder.encode(idToken, "utf-8")))
}
                                                

// @LINE:274
def listBatchInquiry(p:Int = 0, s:String = "id", o:String = "desc", filter:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transactionInquiry" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(filter == "") None else Some(implicitly[QueryStringBindable[String]].unbind("filter", filter)))))
}
                                                
    
}
                          

// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:123
// @LINE:122
class ReverseTools {
    

// @LINE:126
def feedback(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "feedback")
}
                                                

// @LINE:122
def checkRate(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "tools/checkRate")
}
                                                

// @LINE:128
def documentation(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "documentation")
}
                                                

// @LINE:123
def checkRateSubmit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "tools/checkRate")
}
                                                

// @LINE:127
def feedbackSubmit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "feedback")
}
                                                
    
}
                          

// @LINE:202
// @LINE:201
// @LINE:200
// @LINE:199
// @LINE:198
// @LINE:197
class ReverseCustomerBan {
    

// @LINE:200
def admin_interdictions(p:Int = 0, s:String = "country.code", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/customerbans/interdictions" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "country.code") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:202
def admin_interdictionsDelete(id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "admin/customerbans/interdictions/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:201
def admin_interdictionsUpload(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/customerbans/interdictions")
}
                                                

// @LINE:198
def admin_add(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/customerbans")
}
                                                

// @LINE:199
def admin_unban(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/customerbans/" + implicitly[PathBindable[Long]].unbind("id", id) + "/unban")
}
                                                

// @LINE:197
def admin_list(p:Int = 0, s:String = "id", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/customerbans" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                
    
}
                          

// @LINE:171
// @LINE:170
// @LINE:169
// @LINE:168
// @LINE:167
// @LINE:166
// @LINE:141
class ReverseCorporate {
    

// @LINE:167
def admin_new(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/new")
}
                                                

// @LINE:168
def admin_create(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/corporates")
}
                                                

// @LINE:170
def admin_edit(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/edit")
}
                                                

// @LINE:166
def admin_index(p:Int = 0, s:String = "code", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "code") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:169
def show(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:141
def logo(id:String, s:String = "medium"): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/logo" + queryString(List(if(s == "medium") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)))))
}
                                                

// @LINE:171
def admin_update(id:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/edit")
}
                                                
    
}
                          

// @LINE:236
// @LINE:235
class ReverseTranslate {
    

// @LINE:236
def getTCode(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "translate/tcode")
}
                                                

// @LINE:235
def getPinyin(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "translate/pinyin")
}
                                                
    
}
                          

// @LINE:226
// @LINE:225
class ReverseSetupShareSetting {
    

// @LINE:225
def view(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/viewsharesetting" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("name", name)))))
}
                                                

// @LINE:226
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/savesharesetting")
}
                                                
    
}
                          

// @LINE:270
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
class ReverseLogin {
    

// @LINE:162
def admin_logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/logout")
}
                                                

// @LINE:23
def forgetPassword(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "forget-password")
}
                                                

// @LINE:160
def admin_login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/login")
}
                                                

// @LINE:27
def setLangAdmin(langId:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "changeLang/" + implicitly[PathBindable[String]].unbind("langId", URLEncoder.encode(langId, "utf-8")) + "/admin/login")
}
                                                

// @LINE:20
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:161
def admin_authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/login")
}
                                                

// @LINE:26
def setLang(langId:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "changeLang/" + implicitly[PathBindable[String]].unbind("langId", URLEncoder.encode(langId, "utf-8")) + "/login")
}
                                                

// @LINE:22
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                                                

// @LINE:25
def resetPasswordSubmit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "reset-password")
}
                                                

// @LINE:24
def resetPassword(key:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "reset-password/" + implicitly[PathBindable[String]].unbind("key", URLEncoder.encode(key, "utf-8")))
}
                                                

// @LINE:270
def captcha(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "kaptcha")
}
                                                

// @LINE:21
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          

// @LINE:184
// @LINE:183
class ReverseCredit {
    

// @LINE:183
def admin_index(c:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/credit")
}
                                                

// @LINE:184
def admin_update(c:String): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/credit")
}
                                                
    
}
                          

// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:229
class ReverseSettlementReport {
    

// @LINE:231
def download(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/settlementreport/" + implicitly[PathBindable[String]].unbind("name", URLEncoder.encode(name, "utf-8")))
}
                                                

// @LINE:230
def listView(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/settlementreport")
}
                                                

// @LINE:232
def generateById(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/settlementreports/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:229
def list(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/settlementreport")
}
                                                
    
}
                          

// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:48
// @LINE:47
class ReverseCustomer {
    

// @LINE:58
def addRelate(id:Long, p:Int = 0, s:String = "id", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer/addrelate" + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("id", id)), if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:60
def deleteRelate(id:Long, idRelate:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer/deleterelate" + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("id", id)), Some(implicitly[QueryStringBindable[Long]].unbind("idRelate", idRelate)))))
}
                                                

// @LINE:48
def csv(s:String = "id", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer.csv" + queryString(List(if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:53
def summary(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer/" + implicitly[PathBindable[Long]].unbind("id", id) + "/summary")
}
                                                

// @LINE:51
def create(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer/create")
}
                                                

// @LINE:47
def list(p:Int = 0, s:String = "id", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:57
def relate(id:Long, p:Int = 0, s:String = "id", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer/relate" + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("id", id)), if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:55
def update(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customer/" + implicitly[PathBindable[Long]].unbind("id", id) + "/edit")
}
                                                

// @LINE:52
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customer/create")
}
                                                

// @LINE:54
def edit(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customer/" + implicitly[PathBindable[Long]].unbind("id", id) + "/edit")
}
                                                

// @LINE:59
def addRelateAction(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customer/addrelateaction")
}
                                                
    
}
                          

// @LINE:204
// @LINE:203
class ReverseSuspicious {
    

// @LINE:203
def admin_index(p:Int = 0, s:String = "id", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/suspicious" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:204
def csv(s:String = "id", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/suspicious.csv" + queryString(List(if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                
    
}
                          

// @LINE:268
// @LINE:267
// @LINE:266
// @LINE:265
// @LINE:264
class ReverseChannels {
    

// @LINE:268
def delete(code:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "channels/delete/" + implicitly[PathBindable[String]].unbind("code", URLEncoder.encode(code, "utf-8")))
}
                                                

// @LINE:266
def edit(code:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "channels/" + implicitly[PathBindable[String]].unbind("code", URLEncoder.encode(code, "utf-8")) + "/edit")
}
                                                

// @LINE:264
def list(p:Int = 0, s:String = "code", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "channels" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "code") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:267
def update(code:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "channels/" + implicitly[PathBindable[String]].unbind("code", URLEncoder.encode(code, "utf-8")) + "/edit")
}
                                                

// @LINE:265
def summary(code:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "channels/" + implicitly[PathBindable[String]].unbind("code", URLEncoder.encode(code, "utf-8")) + "/summary")
}
                                                
    
}
                          

// @LINE:248
// @LINE:247
// @LINE:246
// @LINE:245
// @LINE:143
// @LINE:142
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
class ReverseUser {
    

// @LINE:39
def saveChangePassword(c:String, id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/password")
}
                                                

// @LINE:143
def css(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/css")
}
                                                

// @LINE:245
def indexOperasional(c:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/usersoperasional")
}
                                                

// @LINE:34
def create(c:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users")
}
                                                

// @LINE:30
def index(c:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/me")
}
                                                

// @LINE:31
def list(c:String, id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/users")
}
                                                

// @LINE:33
def edit(c:String, id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/edit")
}
                                                

// @LINE:37
def show(c:String, id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:142
def photo(id:Long, s:String = "medium"): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/photo" + queryString(List(if(s == "medium") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)))))
}
                                                

// @LINE:35
def update(c:String, id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:41
def updateRolePromote(c:String, id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/role/promote")
}
                                                

// @LINE:38
def changePassword(c:String, id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/password")
}
                                                

// @LINE:36
def destroy(c:String, id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:246
def listOperasional(c:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/usersoperasional/users")
}
                                                

// @LINE:43
def updateRoleDemoteForm(c:String, id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/role/demoteForm")
}
                                                

// @LINE:32
def newCreate(c:String, role:String, supervisorId:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/new/" + implicitly[PathBindable[String]].unbind("role", URLEncoder.encode(role, "utf-8")) + queryString(List(Some(implicitly[QueryStringBindable[Long]].unbind("supervisorId", supervisorId)))))
}
                                                

// @LINE:42
def updateRoleDemote(c:String, id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/role/demote")
}
                                                

// @LINE:247
def updateOperasional(c:String, id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/usersoperasional/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:248
def editOperasional(c:String, id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/usersoperasional/" + implicitly[PathBindable[Long]].unbind("id", id) + "/edit")
}
                                                

// @LINE:40
def updateStatus(c:String, id:Long, ta:Boolean = true): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/users/" + implicitly[PathBindable[Long]].unbind("id", id) + "/status" + queryString(List(if(ta == true) None else Some(implicitly[QueryStringBindable[Boolean]].unbind("ta", ta)))))
}
                                                
    
}
                          

// @LINE:155
// @LINE:11
class ReverseMain {
    

// @LINE:11
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:155
def admin_index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin")
}
                                                
    
}
                          

// @LINE:241
// @LINE:240
// @LINE:239
// @LINE:238
class ReverseGetBank {
    

// @LINE:240
def getBank(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getbank")
}
                                                

// @LINE:238
def getProvinceBank(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getprovincebank")
}
                                                

// @LINE:241
def getGroupBank(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getcountrybank")
}
                                                

// @LINE:239
def getCityBank(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getcitybank")
}
                                                
    
}
                          

// @LINE:117
// @LINE:116
// @LINE:115
class ReverseReport {
    

// @LINE:117
def excel(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "report/excel")
}
                                                

// @LINE:116
def pdf(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "report/pdf")
}
                                                

// @LINE:115
def list(p:Int = 0, s:String = "id", o:String = "desc"): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "report" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)))))
}
                                                
    
}
                          

// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:273
// @LINE:272
class ReverseBatchUpload {
    

// @LINE:272
def viewUpload(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "batchupload")
}
                                                

// @LINE:273
def actionUpload(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "dobatchupload")
}
                                                

// @LINE:277
def actionRemit(token:String, batchId:Int): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "dobatchremit/" + implicitly[PathBindable[String]].unbind("token", URLEncoder.encode(token, "utf-8")) + "/" + implicitly[PathBindable[Int]].unbind("batchId", batchId))
}
                                                

// @LINE:278
def viewResultBatchUpload(batchId:Int): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "viewbatchupload/" + implicitly[PathBindable[Int]].unbind("batchId", batchId))
}
                                                

// @LINE:279
def viewResultBatchUploadFailed(fileName:String = "", totalRow:Int = 0, description:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "viewbatchuploadfailed" + queryString(List(if(fileName == "") None else Some(implicitly[QueryStringBindable[String]].unbind("fileName", fileName)), if(totalRow == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("totalRow", totalRow)), if(description == "") None else Some(implicitly[QueryStringBindable[String]].unbind("description", description)))))
}
                                                
    
}
                          

// @LINE:191
// @LINE:190
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:139
class ReverseForex {
    

// @LINE:190
def admin_update(c:String, id:Long): Call = {
   Call("PUT", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/forex/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:187
def admin_index_main(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/DOK/forex")
}
                                                

// @LINE:188
def admin_index(c:String, origin:String = "", destination:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/forex" + queryString(List(if(origin == "") None else Some(implicitly[QueryStringBindable[String]].unbind("origin", origin)), if(destination == "") None else Some(implicitly[QueryStringBindable[String]].unbind("destination", destination)))))
}
                                                

// @LINE:139
def table(c:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "forexs/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/table")
}
                                                

// @LINE:191
def admin_destroy(c:String, id:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/forex/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:189
def admin_create(c:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "admin/corporates/" + implicitly[PathBindable[String]].unbind("c", URLEncoder.encode(c, "utf-8")) + "/forex")
}
                                                
    
}
                          

// @LINE:242
class ReverseValidateVoucher {
    

// @LINE:242
def getVoucher(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "validatevoucher")
}
                                                
    
}
                          

// @LINE:251
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
class ReverseSend {
    

// @LINE:69
def confirm(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/confirm")
}
                                                

// @LINE:83
def receiptPrint(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receipt-print")
}
                                                

// @LINE:64
def inquiry(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/inquiry")
}
                                                

// @LINE:84
def receiptEmail(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receipt-email")
}
                                                

// @LINE:73
def receipt(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receipt")
}
                                                

// @LINE:65
def inquiryBillPayment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/inquiryBillPayment")
}
                                                

// @LINE:80
def customerEdit(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/customer/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/edit")
}
                                                

// @LINE:78
def customerNew(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/customer/new")
}
                                                

// @LINE:70
def confirmBillPayment(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/confirmBillPayment")
}
                                                

// @LINE:71
def process(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/process")
}
                                                

// @LINE:77
def summary(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/summary")
}
                                                

// @LINE:79
def customerSearch(query:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/customer/search/" + implicitly[PathBindable[String]].unbind("query", URLEncoder.encode(query, "utf-8")))
}
                                                

// @LINE:67
def customerLookupRelate(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/customer-lookupRelate")
}
                                                

// @LINE:63
def create(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send")
}
                                                

// @LINE:74
def receiptBillPayment(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/receiptBillPayment")
}
                                                

// @LINE:68
def verify(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/verify")
}
                                                

// @LINE:72
def processBillPayment(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/processBillPayment")
}
                                                

// @LINE:81
def customerSave(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "send/customer/save")
}
                                                

// @LINE:66
def customerLookup(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "send/customer-lookup")
}
                                                

// @LINE:251
def createBillPayment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "sendBillPayment")
}
                                                

// @LINE:76
// @LINE:75
def sendTransactionViaEmail(id:String, recipient:String): Call = {
   (id: @unchecked, recipient: @unchecked) match {
// @LINE:75
case (id, recipient) if recipient == null => Call("GET", _prefix + { _defaultPrefix } + "send/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/send-email")
                                                        
// @LINE:76
case (id, recipient) if true => Call("GET", _prefix + { _defaultPrefix } + "send/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/send-email/" + implicitly[PathBindable[String]].unbind("recipient", URLEncoder.encode(recipient, "utf-8")))
                                                        
   }
}
                                                
    
}
                          

// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:254
class ReverseBanks {
    

// @LINE:255
def csv(s:String = "id", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "banks.csv" + queryString(List(if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:261
def delete(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "banks/delete/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:257
def edit(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "banks/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/edit")
}
                                                

// @LINE:259
def create(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "banks/create")
}
                                                

// @LINE:254
def list(p:Int = 0, s:String = "id", o:String = "desc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "banks" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:258
def update(id:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "banks/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/edit")
}
                                                

// @LINE:256
def summary(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "banks/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/summary")
}
                                                

// @LINE:260
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "banks/create")
}
                                                
    
}
                          

// @LINE:156
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
class ReverseDashboard {
    

// @LINE:13
def userPerformances(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "dashboard/statistics/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/performances.json")
}
                                                

// @LINE:14
def userVolumes(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "dashboard/statistics/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + "/volumes.json")
}
                                                

// @LINE:16
def news(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "news/content/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:15
def statistics(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "dashboard/statistics/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:12
def index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "dashboard")
}
                                                

// @LINE:156
def admin_index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "admin/dashboard")
}
                                                
    
}
                          

// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:144
class ReverseHelpers {
    

// @LINE:147
def banks_detail(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "helpers/banks/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")) + ".json")
}
                                                

// @LINE:145
def cities(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "helpers/cities.json")
}
                                                

// @LINE:146
def banks(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "helpers/banks.json")
}
                                                

// @LINE:144
def currencies(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "helpers/currencies.json")
}
                                                
    
}
                          

// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:132
// @LINE:131
class ReverseNews {
    

// @LINE:131
def list(p:Int = 0, s:String = "id", o:String = "desc"): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "news/list" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "id") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "desc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)))))
}
                                                

// @LINE:132
def create(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "news/create")
}
                                                

// @LINE:135
def update(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "news/update/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:133
def save(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "news/save")
}
                                                

// @LINE:134
def edit(id:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "news/edit/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                

// @LINE:136
def status(id:Long, status:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "news/status/" + implicitly[PathBindable[Long]].unbind("id", id) + "/" + implicitly[PathBindable[String]].unbind("status", URLEncoder.encode(status, "utf-8")))
}
                                                
    
}
                          
}
                  


// @LINE:283
// @LINE:281
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:270
// @LINE:268
// @LINE:267
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:254
// @LINE:251
// @LINE:248
// @LINE:247
// @LINE:246
// @LINE:245
// @LINE:242
// @LINE:241
// @LINE:240
// @LINE:239
// @LINE:238
// @LINE:236
// @LINE:235
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:229
// @LINE:228
// @LINE:227
// @LINE:226
// @LINE:225
// @LINE:219
// @LINE:213
// @LINE:212
// @LINE:211
// @LINE:208
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:201
// @LINE:200
// @LINE:199
// @LINE:198
// @LINE:197
// @LINE:194
// @LINE:191
// @LINE:190
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:184
// @LINE:183
// @LINE:180
// @LINE:179
// @LINE:178
// @LINE:171
// @LINE:170
// @LINE:169
// @LINE:168
// @LINE:167
// @LINE:166
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:156
// @LINE:155
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:144
// @LINE:143
// @LINE:142
// @LINE:141
// @LINE:139
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:132
// @LINE:131
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:123
// @LINE:122
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:112
// @LINE:111
// @LINE:110
// @LINE:109
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:104
// @LINE:103
// @LINE:102
// @LINE:101
// @LINE:100
// @LINE:95
// @LINE:94
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:48
// @LINE:47
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
package controllers.javascript {

// @LINE:228
// @LINE:227
class ReverseSetupCutOffTime {
    

// @LINE:228
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SetupCutOffTime.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/savecutofftime"})
      }
   """
)
                        

// @LINE:227
def view : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SetupCutOffTime.view",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/viewcutofftime"})
      }
   """
)
                        
    
}
              

// @LINE:180
// @LINE:179
// @LINE:178
class ReverseFee {
    

// @LINE:180
def destroy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Fee.destroy",
   """
      function(c,id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/fees/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:178
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Fee.admin_index",
   """
      function(c) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/fees"})
      }
   """
)
                        

// @LINE:179
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Fee.create",
   """
      function(c) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/fees"})
      }
   """
)
                        
    
}
              

// @LINE:219
class ReverseAssets {
    

// @LINE:219
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:95
// @LINE:94
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
class ReverseReceive {
    

// @LINE:93
def receiptPrint : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.receiptPrint",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receipt-print"})
      }
   """
)
                        

// @LINE:94
def receiptEmail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.receiptEmail",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receipt-email"})
      }
   """
)
                        

// @LINE:92
def receipt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.receipt",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receipt"})
      }
   """
)
                        

// @LINE:89
def verify : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.verify",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/verify"})
      }
   """
)
                        

// @LINE:95
def sendTransactionViaEmail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.sendTransactionViaEmail",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/send-email"})
      }
   """
)
                        

// @LINE:88
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.inquiry",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:87
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.create",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receive"})
      }
   """
)
                        

// @LINE:90
def validate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.validate",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/validate"})
      }
   """
)
                        

// @LINE:91
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Receive.process",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "receive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/process"})
      }
   """
)
                        
    
}
              

// @LINE:194
class ReverseAudit {
    

// @LINE:194
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Audit.admin_index",
   """
      function(p,s,o,filterUserId,filterTag) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/audit" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (filterUserId == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("filterUserId", filterUserId)), (filterTag == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("filterTag", filterTag))])})
      }
   """
)
                        
    
}
              

// @LINE:283
// @LINE:281
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:213
// @LINE:212
// @LINE:211
// @LINE:208
// @LINE:112
// @LINE:111
// @LINE:110
// @LINE:109
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:104
// @LINE:103
// @LINE:102
// @LINE:101
// @LINE:100
class ReverseTransaction {
    

// @LINE:213
def admin_refundProcess : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.admin_refundProcess",
   """
      function(idToken) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/transaction/refund/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:102
def refundDetail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.refundDetail",
   """
      function(idToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/refund/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:105
def change : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.change",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/change"})
      }
   """
)
                        

// @LINE:111
def unlockProcess : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.unlockProcess",
   """
      function(idToken) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/unlock/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:281
def showBatchDetailTransaction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.showBatchDetailTransaction",
   """
      function(p,s,o,filterTransId,batchId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transactionbatch/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("batchId", batchId) + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (filterTransId == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("filterTransId", filterTransId))])})
      }
   """
)
                        

// @LINE:276
def showBatchDetail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.showBatchDetail",
   """
      function(batchId,p,filter,m) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "batchDetail/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("batchId", batchId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("m", encodeURIComponent(m)) + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (filter == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("filter", filter))])})
      }
   """
)
                        

// @LINE:109
def unlock : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.unlock",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/unlock"})
      }
   """
)
                        

// @LINE:211
def admin_refund : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.admin_refund",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/transaction/refund"})
      }
   """
)
                        

// @LINE:100
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.list",
   """
      function(p,s,o,filterTransId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (filterTransId == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("filterTransId", filterTransId))])})
      }
   """
)
                        

// @LINE:104
def refundReceipt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.refundReceipt",
   """
      function(idToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/refund/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken)) + "/receipt"})
      }
   """
)
                        

// @LINE:112
def show : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.show",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:283
def receipt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.receipt",
   """
      function(id,type) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "print/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("type", encodeURIComponent(type))})
      }
   """
)
                        

// @LINE:107
def changeProcess : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.changeProcess",
   """
      function(idToken) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/change/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:275
def showBatch : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.showBatch",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "batch/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:106
def changeDetail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.changeDetail",
   """
      function(idToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/change/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:212
def admin_refundDetail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.admin_refundDetail",
   """
      function(idToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/transaction/refund/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:101
def refund : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.refund",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/refund"})
      }
   """
)
                        

// @LINE:108
def changeReceipt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.changeReceipt",
   """
      function(idToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/change/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken)) + "/receipt"})
      }
   """
)
                        

// @LINE:208
def admin_list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.admin_list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/transactions" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:110
def unlockDetail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.unlockDetail",
   """
      function(idToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/unlock/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:103
def refundProcess : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.refundProcess",
   """
      function(idToken) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/refund/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("idToken", encodeURIComponent(idToken))})
      }
   """
)
                        

// @LINE:274
def listBatchInquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.listBatchInquiry",
   """
      function(p,s,o,filter) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transactionInquiry" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (filter == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("filter", filter))])})
      }
   """
)
                        
    
}
              

// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:123
// @LINE:122
class ReverseTools {
    

// @LINE:126
def feedback : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Tools.feedback",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "feedback"})
      }
   """
)
                        

// @LINE:122
def checkRate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Tools.checkRate",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tools/checkRate"})
      }
   """
)
                        

// @LINE:128
def documentation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Tools.documentation",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "documentation"})
      }
   """
)
                        

// @LINE:123
def checkRateSubmit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Tools.checkRateSubmit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "tools/checkRate"})
      }
   """
)
                        

// @LINE:127
def feedbackSubmit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Tools.feedbackSubmit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "feedback"})
      }
   """
)
                        
    
}
              

// @LINE:202
// @LINE:201
// @LINE:200
// @LINE:199
// @LINE:198
// @LINE:197
class ReverseCustomerBan {
    

// @LINE:200
def admin_interdictions : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CustomerBan.admin_interdictions",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/customerbans/interdictions" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:202
def admin_interdictionsDelete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CustomerBan.admin_interdictionsDelete",
   """
      function(id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/customerbans/interdictions/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:201
def admin_interdictionsUpload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CustomerBan.admin_interdictionsUpload",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/customerbans/interdictions"})
      }
   """
)
                        

// @LINE:198
def admin_add : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CustomerBan.admin_add",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/customerbans"})
      }
   """
)
                        

// @LINE:199
def admin_unban : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CustomerBan.admin_unban",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/customerbans/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/unban"})
      }
   """
)
                        

// @LINE:197
def admin_list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CustomerBan.admin_list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/customerbans" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        
    
}
              

// @LINE:171
// @LINE:170
// @LINE:169
// @LINE:168
// @LINE:167
// @LINE:166
// @LINE:141
class ReverseCorporate {
    

// @LINE:167
def admin_new : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.admin_new",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/new"})
      }
   """
)
                        

// @LINE:168
def admin_create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.admin_create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates"})
      }
   """
)
                        

// @LINE:170
def admin_edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.admin_edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/edit"})
      }
   """
)
                        

// @LINE:166
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.admin_index",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:169
def show : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.show",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:141
def logo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.logo",
   """
      function(id,s) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/logo" + _qS([(s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s))])})
      }
   """
)
                        

// @LINE:171
def admin_update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Corporate.admin_update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/edit"})
      }
   """
)
                        
    
}
              

// @LINE:236
// @LINE:235
class ReverseTranslate {
    

// @LINE:236
def getTCode : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Translate.getTCode",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "translate/tcode"})
      }
   """
)
                        

// @LINE:235
def getPinyin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Translate.getPinyin",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "translate/pinyin"})
      }
   """
)
                        
    
}
              

// @LINE:226
// @LINE:225
class ReverseSetupShareSetting {
    

// @LINE:225
def view : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SetupShareSetting.view",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/viewsharesetting" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("name", name)])})
      }
   """
)
                        

// @LINE:226
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SetupShareSetting.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/savesharesetting"})
      }
   """
)
                        
    
}
              

// @LINE:270
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
class ReverseLogin {
    

// @LINE:162
def admin_logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.admin_logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/logout"})
      }
   """
)
                        

// @LINE:23
def forgetPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.forgetPassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "forget-password"})
      }
   """
)
                        

// @LINE:160
def admin_login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.admin_login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/login"})
      }
   """
)
                        

// @LINE:27
def setLangAdmin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.setLangAdmin",
   """
      function(langId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "changeLang/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("langId", encodeURIComponent(langId)) + "/admin/login"})
      }
   """
)
                        

// @LINE:20
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:161
def admin_authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.admin_authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/login"})
      }
   """
)
                        

// @LINE:26
def setLang : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.setLang",
   """
      function(langId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "changeLang/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("langId", encodeURIComponent(langId)) + "/login"})
      }
   """
)
                        

// @LINE:22
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:25
def resetPasswordSubmit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.resetPasswordSubmit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reset-password"})
      }
   """
)
                        

// @LINE:24
def resetPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.resetPassword",
   """
      function(key) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "reset-password/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("key", encodeURIComponent(key))})
      }
   """
)
                        

// @LINE:270
def captcha : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.captcha",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "kaptcha"})
      }
   """
)
                        

// @LINE:21
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Login.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              

// @LINE:184
// @LINE:183
class ReverseCredit {
    

// @LINE:183
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Credit.admin_index",
   """
      function(c) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/credit"})
      }
   """
)
                        

// @LINE:184
def admin_update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Credit.admin_update",
   """
      function(c) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/credit"})
      }
   """
)
                        
    
}
              

// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:229
class ReverseSettlementReport {
    

// @LINE:231
def download : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SettlementReport.download",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/settlementreport/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:230
def listView : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SettlementReport.listView",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/settlementreport"})
      }
   """
)
                        

// @LINE:232
def generateById : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SettlementReport.generateById",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/settlementreports/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:229
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.SettlementReport.list",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/settlementreport"})
      }
   """
)
                        
    
}
              

// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:48
// @LINE:47
class ReverseCustomer {
    

// @LINE:58
def addRelate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.addRelate",
   """
      function(id,p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/addrelate" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("id", id), (p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:60
def deleteRelate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.deleteRelate",
   """
      function(id,idRelate) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/deleterelate" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("id", id), (""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("idRelate", idRelate)])})
      }
   """
)
                        

// @LINE:48
def csv : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.csv",
   """
      function(s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer.csv" + _qS([(s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:53
def summary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.summary",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/summary"})
      }
   """
)
                        

// @LINE:51
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.create",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/create"})
      }
   """
)
                        

// @LINE:47
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:57
def relate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.relate",
   """
      function(id,p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/relate" + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("id", id), (p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:55
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/edit"})
      }
   """
)
                        

// @LINE:52
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/create"})
      }
   """
)
                        

// @LINE:54
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/edit"})
      }
   """
)
                        

// @LINE:59
def addRelateAction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.addRelateAction",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customer/addrelateaction"})
      }
   """
)
                        
    
}
              

// @LINE:204
// @LINE:203
class ReverseSuspicious {
    

// @LINE:203
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Suspicious.admin_index",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/suspicious" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:204
def csv : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Suspicious.csv",
   """
      function(s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/suspicious.csv" + _qS([(s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        
    
}
              

// @LINE:268
// @LINE:267
// @LINE:266
// @LINE:265
// @LINE:264
class ReverseChannels {
    

// @LINE:268
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Channels.delete",
   """
      function(code) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "channels/delete/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("code", encodeURIComponent(code))})
      }
   """
)
                        

// @LINE:266
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Channels.edit",
   """
      function(code) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "channels/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("code", encodeURIComponent(code)) + "/edit"})
      }
   """
)
                        

// @LINE:264
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Channels.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "channels" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:267
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Channels.update",
   """
      function(code) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "channels/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("code", encodeURIComponent(code)) + "/edit"})
      }
   """
)
                        

// @LINE:265
def summary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Channels.summary",
   """
      function(code) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "channels/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("code", encodeURIComponent(code)) + "/summary"})
      }
   """
)
                        
    
}
              

// @LINE:248
// @LINE:247
// @LINE:246
// @LINE:245
// @LINE:143
// @LINE:142
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
class ReverseUser {
    

// @LINE:39
def saveChangePassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.saveChangePassword",
   """
      function(c,id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/password"})
      }
   """
)
                        

// @LINE:143
def css : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.css",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/css"})
      }
   """
)
                        

// @LINE:245
def indexOperasional : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.indexOperasional",
   """
      function(c) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/usersoperasional"})
      }
   """
)
                        

// @LINE:34
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.create",
   """
      function(c) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users"})
      }
   """
)
                        

// @LINE:30
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.index",
   """
      function(c) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/me"})
      }
   """
)
                        

// @LINE:31
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.list",
   """
      function(c,id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/users"})
      }
   """
)
                        

// @LINE:33
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.edit",
   """
      function(c,id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/edit"})
      }
   """
)
                        

// @LINE:37
def show : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.show",
   """
      function(c,id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:142
def photo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.photo",
   """
      function(id,s) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/photo" + _qS([(s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s))])})
      }
   """
)
                        

// @LINE:35
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.update",
   """
      function(c,id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:41
def updateRolePromote : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.updateRolePromote",
   """
      function(c,id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/role/promote"})
      }
   """
)
                        

// @LINE:38
def changePassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.changePassword",
   """
      function(c,id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/password"})
      }
   """
)
                        

// @LINE:36
def destroy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.destroy",
   """
      function(c,id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:246
def listOperasional : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.listOperasional",
   """
      function(c) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/usersoperasional/users"})
      }
   """
)
                        

// @LINE:43
def updateRoleDemoteForm : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.updateRoleDemoteForm",
   """
      function(c,id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/role/demoteForm"})
      }
   """
)
                        

// @LINE:32
def newCreate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.newCreate",
   """
      function(c,role,supervisorId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/new/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("role", encodeURIComponent(role)) + _qS([(""" + implicitly[QueryStringBindable[Long]].javascriptUnbind + """)("supervisorId", supervisorId)])})
      }
   """
)
                        

// @LINE:42
def updateRoleDemote : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.updateRoleDemote",
   """
      function(c,id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/role/demote"})
      }
   """
)
                        

// @LINE:247
def updateOperasional : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.updateOperasional",
   """
      function(c,id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/usersoperasional/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:248
def editOperasional : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.editOperasional",
   """
      function(c,id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/usersoperasional/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/edit"})
      }
   """
)
                        

// @LINE:40
def updateStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.User.updateStatus",
   """
      function(c,id,ta) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/users/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/status" + _qS([(ta == null ? null : (""" + implicitly[QueryStringBindable[Boolean]].javascriptUnbind + """)("ta", ta))])})
      }
   """
)
                        
    
}
              

// @LINE:155
// @LINE:11
class ReverseMain {
    

// @LINE:11
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Main.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:155
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Main.admin_index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin"})
      }
   """
)
                        
    
}
              

// @LINE:241
// @LINE:240
// @LINE:239
// @LINE:238
class ReverseGetBank {
    

// @LINE:240
def getBank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GetBank.getBank",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getbank"})
      }
   """
)
                        

// @LINE:238
def getProvinceBank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GetBank.getProvinceBank",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getprovincebank"})
      }
   """
)
                        

// @LINE:241
def getGroupBank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GetBank.getGroupBank",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getcountrybank"})
      }
   """
)
                        

// @LINE:239
def getCityBank : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.GetBank.getCityBank",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getcitybank"})
      }
   """
)
                        
    
}
              

// @LINE:117
// @LINE:116
// @LINE:115
class ReverseReport {
    

// @LINE:117
def excel : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Report.excel",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "report/excel"})
      }
   """
)
                        

// @LINE:116
def pdf : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Report.pdf",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "report/pdf"})
      }
   """
)
                        

// @LINE:115
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Report.list",
   """
      function(p,s,o) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "report" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o))])})
      }
   """
)
                        
    
}
              

// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:273
// @LINE:272
class ReverseBatchUpload {
    

// @LINE:272
def viewUpload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BatchUpload.viewUpload",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "batchupload"})
      }
   """
)
                        

// @LINE:273
def actionUpload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BatchUpload.actionUpload",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "dobatchupload"})
      }
   """
)
                        

// @LINE:277
def actionRemit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BatchUpload.actionRemit",
   """
      function(token,batchId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "dobatchremit/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token)) + "/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("batchId", batchId)})
      }
   """
)
                        

// @LINE:278
def viewResultBatchUpload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BatchUpload.viewResultBatchUpload",
   """
      function(batchId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewbatchupload/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("batchId", batchId)})
      }
   """
)
                        

// @LINE:279
def viewResultBatchUploadFailed : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.BatchUpload.viewResultBatchUploadFailed",
   """
      function(fileName,totalRow,description) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewbatchuploadfailed" + _qS([(fileName == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("fileName", fileName)), (totalRow == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("totalRow", totalRow)), (description == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("description", description))])})
      }
   """
)
                        
    
}
              

// @LINE:191
// @LINE:190
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:139
class ReverseForex {
    

// @LINE:190
def admin_update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Forex.admin_update",
   """
      function(c,id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/forex/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:187
def admin_index_main : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Forex.admin_index_main",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/DOK/forex"})
      }
   """
)
                        

// @LINE:188
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Forex.admin_index",
   """
      function(c,origin,destination) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/forex" + _qS([(origin == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("origin", origin)), (destination == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("destination", destination))])})
      }
   """
)
                        

// @LINE:139
def table : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Forex.table",
   """
      function(c) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "forexs/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/table"})
      }
   """
)
                        

// @LINE:191
def admin_destroy : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Forex.admin_destroy",
   """
      function(c,id) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/forex/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:189
def admin_create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Forex.admin_create",
   """
      function(c) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/corporates/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("c", encodeURIComponent(c)) + "/forex"})
      }
   """
)
                        
    
}
              

// @LINE:242
class ReverseValidateVoucher {
    

// @LINE:242
def getVoucher : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.ValidateVoucher.getVoucher",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "validatevoucher"})
      }
   """
)
                        
    
}
              

// @LINE:251
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
class ReverseSend {
    

// @LINE:69
def confirm : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.confirm",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/confirm"})
      }
   """
)
                        

// @LINE:83
def receiptPrint : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.receiptPrint",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receipt-print"})
      }
   """
)
                        

// @LINE:64
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.inquiry",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/inquiry"})
      }
   """
)
                        

// @LINE:84
def receiptEmail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.receiptEmail",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receipt-email"})
      }
   """
)
                        

// @LINE:73
def receipt : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.receipt",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receipt"})
      }
   """
)
                        

// @LINE:65
def inquiryBillPayment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.inquiryBillPayment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/inquiryBillPayment"})
      }
   """
)
                        

// @LINE:80
def customerEdit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.customerEdit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/customer/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/edit"})
      }
   """
)
                        

// @LINE:78
def customerNew : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.customerNew",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/customer/new"})
      }
   """
)
                        

// @LINE:70
def confirmBillPayment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.confirmBillPayment",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/confirmBillPayment"})
      }
   """
)
                        

// @LINE:71
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.process",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/process"})
      }
   """
)
                        

// @LINE:77
def summary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.summary",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/summary"})
      }
   """
)
                        

// @LINE:79
def customerSearch : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.customerSearch",
   """
      function(query) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/customer/search/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("query", encodeURIComponent(query))})
      }
   """
)
                        

// @LINE:67
def customerLookupRelate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.customerLookupRelate",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/customer-lookupRelate"})
      }
   """
)
                        

// @LINE:63
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.create",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send"})
      }
   """
)
                        

// @LINE:74
def receiptBillPayment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.receiptBillPayment",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/receiptBillPayment"})
      }
   """
)
                        

// @LINE:68
def verify : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.verify",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/verify"})
      }
   """
)
                        

// @LINE:72
def processBillPayment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.processBillPayment",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/processBillPayment"})
      }
   """
)
                        

// @LINE:81
def customerSave : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.customerSave",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "send/customer/save"})
      }
   """
)
                        

// @LINE:66
def customerLookup : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.customerLookup",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/customer-lookup"})
      }
   """
)
                        

// @LINE:251
def createBillPayment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.createBillPayment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sendBillPayment"})
      }
   """
)
                        

// @LINE:76
// @LINE:75
def sendTransactionViaEmail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Send.sendTransactionViaEmail",
   """
      function(id, recipient) {
      if (recipient == """ + implicitly[JavascriptLitteral[String]].to(null) + """) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/send-email"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "send/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/send-email/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("recipient", encodeURIComponent(recipient))})
      }
      }
   """
)
                        
    
}
              

// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:254
class ReverseBanks {
    

// @LINE:255
def csv : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.csv",
   """
      function(s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "banks.csv" + _qS([(s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:261
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.delete",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "banks/delete/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:257
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "banks/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/edit"})
      }
   """
)
                        

// @LINE:259
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.create",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "banks/create"})
      }
   """
)
                        

// @LINE:254
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.list",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "banks" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:258
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "banks/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/edit"})
      }
   """
)
                        

// @LINE:256
def summary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.summary",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "banks/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/summary"})
      }
   """
)
                        

// @LINE:260
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Banks.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "banks/create"})
      }
   """
)
                        
    
}
              

// @LINE:156
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
class ReverseDashboard {
    

// @LINE:13
def userPerformances : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Dashboard.userPerformances",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard/statistics/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/performances.json"})
      }
   """
)
                        

// @LINE:14
def userVolumes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Dashboard.userVolumes",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard/statistics/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + "/volumes.json"})
      }
   """
)
                        

// @LINE:16
def news : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Dashboard.news",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "news/content/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:15
def statistics : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Dashboard.statistics",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard/statistics/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:12
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Dashboard.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard"})
      }
   """
)
                        

// @LINE:156
def admin_index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Dashboard.admin_index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "admin/dashboard"})
      }
   """
)
                        
    
}
              

// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:144
class ReverseHelpers {
    

// @LINE:147
def banks_detail : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Helpers.banks_detail",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "helpers/banks/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id)) + ".json"})
      }
   """
)
                        

// @LINE:145
def cities : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Helpers.cities",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "helpers/cities.json"})
      }
   """
)
                        

// @LINE:146
def banks : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Helpers.banks",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "helpers/banks.json"})
      }
   """
)
                        

// @LINE:144
def currencies : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Helpers.currencies",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "helpers/currencies.json"})
      }
   """
)
                        
    
}
              

// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:132
// @LINE:131
class ReverseNews {
    

// @LINE:131
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.News.list",
   """
      function(p,s,o) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "news/list" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o))])})
      }
   """
)
                        

// @LINE:132
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.News.create",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "news/create"})
      }
   """
)
                        

// @LINE:135
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.News.update",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "news/update/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:133
def save : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.News.save",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "news/save"})
      }
   """
)
                        

// @LINE:134
def edit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.News.edit",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "news/edit/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:136
def status : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.News.status",
   """
      function(id,status) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "news/status/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("status", encodeURIComponent(status))})
      }
   """
)
                        
    
}
              
}
        


// @LINE:283
// @LINE:281
// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:273
// @LINE:272
// @LINE:270
// @LINE:268
// @LINE:267
// @LINE:266
// @LINE:265
// @LINE:264
// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:254
// @LINE:251
// @LINE:248
// @LINE:247
// @LINE:246
// @LINE:245
// @LINE:242
// @LINE:241
// @LINE:240
// @LINE:239
// @LINE:238
// @LINE:236
// @LINE:235
// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:229
// @LINE:228
// @LINE:227
// @LINE:226
// @LINE:225
// @LINE:219
// @LINE:213
// @LINE:212
// @LINE:211
// @LINE:208
// @LINE:204
// @LINE:203
// @LINE:202
// @LINE:201
// @LINE:200
// @LINE:199
// @LINE:198
// @LINE:197
// @LINE:194
// @LINE:191
// @LINE:190
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:184
// @LINE:183
// @LINE:180
// @LINE:179
// @LINE:178
// @LINE:171
// @LINE:170
// @LINE:169
// @LINE:168
// @LINE:167
// @LINE:166
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:156
// @LINE:155
// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:144
// @LINE:143
// @LINE:142
// @LINE:141
// @LINE:139
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:132
// @LINE:131
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:123
// @LINE:122
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:112
// @LINE:111
// @LINE:110
// @LINE:109
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:104
// @LINE:103
// @LINE:102
// @LINE:101
// @LINE:100
// @LINE:95
// @LINE:94
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:48
// @LINE:47
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
package controllers.ref {

// @LINE:228
// @LINE:227
class ReverseSetupCutOffTime {
    

// @LINE:228
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SetupCutOffTime.save(), HandlerDef(this, "controllers.SetupCutOffTime", "save", Seq(), "POST", """""", _prefix + """admin/savecutofftime""")
)
                      

// @LINE:227
def view(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SetupCutOffTime.view(), HandlerDef(this, "controllers.SetupCutOffTime", "view", Seq(), "GET", """""", _prefix + """admin/viewcutofftime""")
)
                      
    
}
                          

// @LINE:180
// @LINE:179
// @LINE:178
class ReverseFee {
    

// @LINE:180
def destroy(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Fee.destroy(c, id), HandlerDef(this, "controllers.Fee", "destroy", Seq(classOf[String], classOf[Long]), "DELETE", """""", _prefix + """admin/corporates/$c<[^/]+>/fees/$id<[^/]+>""")
)
                      

// @LINE:178
def admin_index(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Fee.admin_index(c), HandlerDef(this, "controllers.Fee", "admin_index", Seq(classOf[String]), "GET", """# Corporate Fees Management""", _prefix + """admin/corporates/$c<[^/]+>/fees""")
)
                      

// @LINE:179
def create(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Fee.create(c), HandlerDef(this, "controllers.Fee", "create", Seq(classOf[String]), "POST", """""", _prefix + """admin/corporates/$c<[^/]+>/fees""")
)
                      
    
}
                          

// @LINE:219
class ReverseAssets {
    

// @LINE:219
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:95
// @LINE:94
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
class ReverseReceive {
    

// @LINE:93
def receiptPrint(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.receiptPrint(id), HandlerDef(this, "controllers.Receive", "receiptPrint", Seq(classOf[String]), "GET", """""", _prefix + """receive/$id<[^/]+>/receipt-print""")
)
                      

// @LINE:94
def receiptEmail(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.receiptEmail(id), HandlerDef(this, "controllers.Receive", "receiptEmail", Seq(classOf[String]), "GET", """""", _prefix + """receive/$id<[^/]+>/receipt-email""")
)
                      

// @LINE:92
def receipt(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.receipt(id), HandlerDef(this, "controllers.Receive", "receipt", Seq(classOf[String]), "GET", """""", _prefix + """receive/$id<[^/]+>/receipt""")
)
                      

// @LINE:89
def verify(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.verify(id), HandlerDef(this, "controllers.Receive", "verify", Seq(classOf[String]), "POST", """""", _prefix + """receive/$id<[^/]+>/verify""")
)
                      

// @LINE:95
def sendTransactionViaEmail(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.sendTransactionViaEmail(id), HandlerDef(this, "controllers.Receive", "sendTransactionViaEmail", Seq(classOf[String]), "GET", """""", _prefix + """receive/$id<[^/]+>/send-email""")
)
                      

// @LINE:88
def inquiry(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.inquiry(id), HandlerDef(this, "controllers.Receive", "inquiry", Seq(classOf[String]), "GET", """""", _prefix + """receive/$id<[^/]+>""")
)
                      

// @LINE:87
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.create(), HandlerDef(this, "controllers.Receive", "create", Seq(), "GET", """# Transaction Receive Money""", _prefix + """receive""")
)
                      

// @LINE:90
def validate(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.validate(id), HandlerDef(this, "controllers.Receive", "validate", Seq(classOf[String]), "POST", """""", _prefix + """receive/$id<[^/]+>/validate""")
)
                      

// @LINE:91
def process(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Receive.process(id), HandlerDef(this, "controllers.Receive", "process", Seq(classOf[String]), "POST", """""", _prefix + """receive/$id<[^/]+>/process""")
)
                      
    
}
                          

// @LINE:194
class ReverseAudit {
    

// @LINE:194
def admin_index(p:Int, s:String, o:String, filterUserId:String, filterTag:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Audit.admin_index(p, s, o, filterUserId, filterTag), HandlerDef(this, "controllers.Audit", "admin_index", Seq(classOf[Int], classOf[String], classOf[String], classOf[String], classOf[String]), "GET", """# Audit""", _prefix + """admin/audit""")
)
                      
    
}
                          

// @LINE:283
// @LINE:281
// @LINE:276
// @LINE:275
// @LINE:274
// @LINE:213
// @LINE:212
// @LINE:211
// @LINE:208
// @LINE:112
// @LINE:111
// @LINE:110
// @LINE:109
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:104
// @LINE:103
// @LINE:102
// @LINE:101
// @LINE:100
class ReverseTransaction {
    

// @LINE:213
def admin_refundProcess(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.admin_refundProcess(idToken), HandlerDef(this, "controllers.Transaction", "admin_refundProcess", Seq(classOf[String]), "POST", """""", _prefix + """admin/transaction/refund/$idToken<[^/]+>""")
)
                      

// @LINE:102
def refundDetail(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.refundDetail(idToken), HandlerDef(this, "controllers.Transaction", "refundDetail", Seq(classOf[String]), "GET", """""", _prefix + """transaction/refund/$idToken<[^/]+>""")
)
                      

// @LINE:105
def change(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.change(), HandlerDef(this, "controllers.Transaction", "change", Seq(), "GET", """""", _prefix + """transaction/change""")
)
                      

// @LINE:111
def unlockProcess(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.unlockProcess(idToken), HandlerDef(this, "controllers.Transaction", "unlockProcess", Seq(classOf[String]), "POST", """""", _prefix + """transaction/unlock/$idToken<[^/]+>""")
)
                      

// @LINE:281
def showBatchDetailTransaction(p:Int, s:String, o:String, filterTransId:String, batchId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.showBatchDetailTransaction(p, s, o, filterTransId, batchId), HandlerDef(this, "controllers.Transaction", "showBatchDetailTransaction", Seq(classOf[Int], classOf[String], classOf[String], classOf[String], classOf[Int]), "GET", """""", _prefix + """transactionbatch/$batchId<[^/]+>""")
)
                      

// @LINE:276
def showBatchDetail(batchId:Int, p:Int, filter:String, m:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.showBatchDetail(batchId, p, filter, m), HandlerDef(this, "controllers.Transaction", "showBatchDetail", Seq(classOf[Int], classOf[Int], classOf[String], classOf[String]), "GET", """""", _prefix + """batchDetail/$batchId<[^/]+>/$m<[^/]+>""")
)
                      

// @LINE:109
def unlock(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.unlock(), HandlerDef(this, "controllers.Transaction", "unlock", Seq(), "GET", """""", _prefix + """transaction/unlock""")
)
                      

// @LINE:211
def admin_refund(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.admin_refund(), HandlerDef(this, "controllers.Transaction", "admin_refund", Seq(), "GET", """# Full Refund""", _prefix + """admin/transaction/refund""")
)
                      

// @LINE:100
def list(p:Int, s:String, o:String, filterTransId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.list(p, s, o, filterTransId), HandlerDef(this, "controllers.Transaction", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Transaction View Status""", _prefix + """transaction""")
)
                      

// @LINE:104
def refundReceipt(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.refundReceipt(idToken), HandlerDef(this, "controllers.Transaction", "refundReceipt", Seq(classOf[String]), "GET", """""", _prefix + """transaction/refund/$idToken<[^/]+>/receipt""")
)
                      

// @LINE:112
def show(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.show(id), HandlerDef(this, "controllers.Transaction", "show", Seq(classOf[String]), "GET", """""", _prefix + """transaction/$id<[^/]+>""")
)
                      

// @LINE:283
def receipt(id:String, playframework_escape_type:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.receipt(id, playframework_escape_type), HandlerDef(this, "controllers.Transaction", "receipt", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """print/$id<[^/]+>/$type<[^/]+>""")
)
                      

// @LINE:107
def changeProcess(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.changeProcess(idToken), HandlerDef(this, "controllers.Transaction", "changeProcess", Seq(classOf[String]), "POST", """""", _prefix + """transaction/change/$idToken<[^/]+>""")
)
                      

// @LINE:275
def showBatch(id:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.showBatch(id), HandlerDef(this, "controllers.Transaction", "showBatch", Seq(classOf[Int]), "GET", """""", _prefix + """batch/$id<[^/]+>""")
)
                      

// @LINE:106
def changeDetail(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.changeDetail(idToken), HandlerDef(this, "controllers.Transaction", "changeDetail", Seq(classOf[String]), "GET", """""", _prefix + """transaction/change/$idToken<[^/]+>""")
)
                      

// @LINE:212
def admin_refundDetail(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.admin_refundDetail(idToken), HandlerDef(this, "controllers.Transaction", "admin_refundDetail", Seq(classOf[String]), "GET", """""", _prefix + """admin/transaction/refund/$idToken<[^/]+>""")
)
                      

// @LINE:101
def refund(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.refund(), HandlerDef(this, "controllers.Transaction", "refund", Seq(), "GET", """""", _prefix + """transaction/refund""")
)
                      

// @LINE:108
def changeReceipt(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.changeReceipt(idToken), HandlerDef(this, "controllers.Transaction", "changeReceipt", Seq(classOf[String]), "GET", """""", _prefix + """transaction/change/$idToken<[^/]+>/receipt""")
)
                      

// @LINE:208
def admin_list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.admin_list(p, s, o, f), HandlerDef(this, "controllers.Transaction", "admin_list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Transaction management""", _prefix + """admin/transactions""")
)
                      

// @LINE:110
def unlockDetail(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.unlockDetail(idToken), HandlerDef(this, "controllers.Transaction", "unlockDetail", Seq(classOf[String]), "GET", """""", _prefix + """transaction/unlock/$idToken<[^/]+>""")
)
                      

// @LINE:103
def refundProcess(idToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.refundProcess(idToken), HandlerDef(this, "controllers.Transaction", "refundProcess", Seq(classOf[String]), "POST", """""", _prefix + """transaction/refund/$idToken<[^/]+>""")
)
                      

// @LINE:274
def listBatchInquiry(p:Int, s:String, o:String, filter:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.listBatchInquiry(p, s, o, filter), HandlerDef(this, "controllers.Transaction", "listBatchInquiry", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """transactionInquiry""")
)
                      
    
}
                          

// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:123
// @LINE:122
class ReverseTools {
    

// @LINE:126
def feedback(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Tools.feedback(), HandlerDef(this, "controllers.Tools", "feedback", Seq(), "GET", """# Help""", _prefix + """feedback""")
)
                      

// @LINE:122
def checkRate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Tools.checkRate(), HandlerDef(this, "controllers.Tools", "checkRate", Seq(), "GET", """ Some tools""", _prefix + """tools/checkRate""")
)
                      

// @LINE:128
def documentation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Tools.documentation(), HandlerDef(this, "controllers.Tools", "documentation", Seq(), "GET", """""", _prefix + """documentation""")
)
                      

// @LINE:123
def checkRateSubmit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Tools.checkRateSubmit(), HandlerDef(this, "controllers.Tools", "checkRateSubmit", Seq(), "POST", """""", _prefix + """tools/checkRate""")
)
                      

// @LINE:127
def feedbackSubmit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Tools.feedbackSubmit(), HandlerDef(this, "controllers.Tools", "feedbackSubmit", Seq(), "POST", """""", _prefix + """feedback""")
)
                      
    
}
                          

// @LINE:202
// @LINE:201
// @LINE:200
// @LINE:199
// @LINE:198
// @LINE:197
class ReverseCustomerBan {
    

// @LINE:200
def admin_interdictions(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CustomerBan.admin_interdictions(p, s, o, f), HandlerDef(this, "controllers.CustomerBan", "admin_interdictions", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """admin/customerbans/interdictions""")
)
                      

// @LINE:202
def admin_interdictionsDelete(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CustomerBan.admin_interdictionsDelete(id), HandlerDef(this, "controllers.CustomerBan", "admin_interdictionsDelete", Seq(classOf[Long]), "DELETE", """""", _prefix + """admin/customerbans/interdictions/$id<[^/]+>""")
)
                      

// @LINE:201
def admin_interdictionsUpload(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CustomerBan.admin_interdictionsUpload(), HandlerDef(this, "controllers.CustomerBan", "admin_interdictionsUpload", Seq(), "POST", """""", _prefix + """admin/customerbans/interdictions""")
)
                      

// @LINE:198
def admin_add(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CustomerBan.admin_add(), HandlerDef(this, "controllers.CustomerBan", "admin_add", Seq(), "POST", """""", _prefix + """admin/customerbans""")
)
                      

// @LINE:199
def admin_unban(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CustomerBan.admin_unban(id), HandlerDef(this, "controllers.CustomerBan", "admin_unban", Seq(classOf[Long]), "GET", """""", _prefix + """admin/customerbans/$id<[^/]+>/unban""")
)
                      

// @LINE:197
def admin_list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CustomerBan.admin_list(p, s, o, f), HandlerDef(this, "controllers.CustomerBan", "admin_list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Customer ban""", _prefix + """admin/customerbans""")
)
                      
    
}
                          

// @LINE:171
// @LINE:170
// @LINE:169
// @LINE:168
// @LINE:167
// @LINE:166
// @LINE:141
class ReverseCorporate {
    

// @LINE:167
def admin_new(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.admin_new(), HandlerDef(this, "controllers.Corporate", "admin_new", Seq(), "GET", """""", _prefix + """admin/corporates/new""")
)
                      

// @LINE:168
def admin_create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.admin_create(), HandlerDef(this, "controllers.Corporate", "admin_create", Seq(), "POST", """""", _prefix + """admin/corporates""")
)
                      

// @LINE:170
def admin_edit(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.admin_edit(id), HandlerDef(this, "controllers.Corporate", "admin_edit", Seq(classOf[String]), "GET", """""", _prefix + """admin/corporates/$id<[^/]+>/edit""")
)
                      

// @LINE:166
def admin_index(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.admin_index(p, s, o, f), HandlerDef(this, "controllers.Corporate", "admin_index", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Corporate Management""", _prefix + """admin/corporates""")
)
                      

// @LINE:169
def show(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.show(id), HandlerDef(this, "controllers.Corporate", "show", Seq(classOf[String]), "GET", """""", _prefix + """admin/corporates/$id<[^/]+>""")
)
                      

// @LINE:141
def logo(id:String, s:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.logo(id, s), HandlerDef(this, "controllers.Corporate", "logo", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """corporates/$id<[^/]+>/logo""")
)
                      

// @LINE:171
def admin_update(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Corporate.admin_update(id), HandlerDef(this, "controllers.Corporate", "admin_update", Seq(classOf[String]), "POST", """""", _prefix + """admin/corporates/$id<[^/]+>/edit""")
)
                      
    
}
                          

// @LINE:236
// @LINE:235
class ReverseTranslate {
    

// @LINE:236
def getTCode(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Translate.getTCode(), HandlerDef(this, "controllers.Translate", "getTCode", Seq(), "POST", """""", _prefix + """translate/tcode""")
)
                      

// @LINE:235
def getPinyin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Translate.getPinyin(), HandlerDef(this, "controllers.Translate", "getPinyin", Seq(), "POST", """NETELIS""", _prefix + """translate/pinyin""")
)
                      
    
}
                          

// @LINE:226
// @LINE:225
class ReverseSetupShareSetting {
    

// @LINE:225
def view(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SetupShareSetting.view(name), HandlerDef(this, "controllers.SetupShareSetting", "view", Seq(classOf[String]), "GET", """SETLEMENT REPORT""", _prefix + """admin/viewsharesetting""")
)
                      

// @LINE:226
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SetupShareSetting.save(), HandlerDef(this, "controllers.SetupShareSetting", "save", Seq(), "POST", """""", _prefix + """admin/savesharesetting""")
)
                      
    
}
                          

// @LINE:270
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
class ReverseLogin {
    

// @LINE:162
def admin_logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.admin_logout(), HandlerDef(this, "controllers.Login", "admin_logout", Seq(), "GET", """""", _prefix + """admin/logout""")
)
                      

// @LINE:23
def forgetPassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.forgetPassword(), HandlerDef(this, "controllers.Login", "forgetPassword", Seq(), "POST", """""", _prefix + """forget-password""")
)
                      

// @LINE:160
def admin_login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.admin_login(), HandlerDef(this, "controllers.Login", "admin_login", Seq(), "GET", """# Admin Authentication""", _prefix + """admin/login""")
)
                      

// @LINE:27
def setLangAdmin(langId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.setLangAdmin(langId), HandlerDef(this, "controllers.Login", "setLangAdmin", Seq(classOf[String]), "GET", """""", _prefix + """changeLang/$langId<[^/]+>/admin/login""")
)
                      

// @LINE:20
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.login(), HandlerDef(this, "controllers.Login", "login", Seq(), "GET", """# Authentication""", _prefix + """login""")
)
                      

// @LINE:161
def admin_authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.admin_authenticate(), HandlerDef(this, "controllers.Login", "admin_authenticate", Seq(), "POST", """""", _prefix + """admin/login""")
)
                      

// @LINE:26
def setLang(langId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.setLang(langId), HandlerDef(this, "controllers.Login", "setLang", Seq(classOf[String]), "GET", """""", _prefix + """changeLang/$langId<[^/]+>/login""")
)
                      

// @LINE:22
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.logout(), HandlerDef(this, "controllers.Login", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:25
def resetPasswordSubmit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.resetPasswordSubmit(), HandlerDef(this, "controllers.Login", "resetPasswordSubmit", Seq(), "POST", """""", _prefix + """reset-password""")
)
                      

// @LINE:24
def resetPassword(key:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.resetPassword(key), HandlerDef(this, "controllers.Login", "resetPassword", Seq(classOf[String]), "GET", """""", _prefix + """reset-password/$key<[^/]+>""")
)
                      

// @LINE:270
def captcha(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.captcha(), HandlerDef(this, "controllers.Login", "captcha", Seq(), "GET", """""", _prefix + """kaptcha""")
)
                      

// @LINE:21
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Login.authenticate(), HandlerDef(this, "controllers.Login", "authenticate", Seq(), "POST", """""", _prefix + """login""")
)
                      
    
}
                          

// @LINE:184
// @LINE:183
class ReverseCredit {
    

// @LINE:183
def admin_index(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Credit.admin_index(c), HandlerDef(this, "controllers.Credit", "admin_index", Seq(classOf[String]), "GET", """# Corporate Credit Management""", _prefix + """admin/corporates/$c<[^/]+>/credit""")
)
                      

// @LINE:184
def admin_update(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Credit.admin_update(c), HandlerDef(this, "controllers.Credit", "admin_update", Seq(classOf[String]), "PUT", """""", _prefix + """admin/corporates/$c<[^/]+>/credit""")
)
                      
    
}
                          

// @LINE:232
// @LINE:231
// @LINE:230
// @LINE:229
class ReverseSettlementReport {
    

// @LINE:231
def download(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SettlementReport.download(name), HandlerDef(this, "controllers.SettlementReport", "download", Seq(classOf[String]), "GET", """""", _prefix + """admin/settlementreport/$name<[^/]+>""")
)
                      

// @LINE:230
def listView(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SettlementReport.listView(), HandlerDef(this, "controllers.SettlementReport", "listView", Seq(), "POST", """""", _prefix + """admin/settlementreport""")
)
                      

// @LINE:232
def generateById(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SettlementReport.generateById(id), HandlerDef(this, "controllers.SettlementReport", "generateById", Seq(classOf[Long]), "POST", """""", _prefix + """admin/settlementreports/$id<[^/]+>""")
)
                      

// @LINE:229
def list(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.SettlementReport.list(), HandlerDef(this, "controllers.SettlementReport", "list", Seq(), "GET", """""", _prefix + """admin/settlementreport""")
)
                      
    
}
                          

// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:57
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:48
// @LINE:47
class ReverseCustomer {
    

// @LINE:58
def addRelate(id:Long, p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.addRelate(id, p, s, o, f), HandlerDef(this, "controllers.Customer", "addRelate", Seq(classOf[Long], classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """customer/addrelate""")
)
                      

// @LINE:60
def deleteRelate(id:Long, idRelate:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.deleteRelate(id, idRelate), HandlerDef(this, "controllers.Customer", "deleteRelate", Seq(classOf[Long], classOf[Long]), "GET", """""", _prefix + """customer/deleterelate""")
)
                      

// @LINE:48
def csv(s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.csv(s, o, f), HandlerDef(this, "controllers.Customer", "csv", Seq(classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """customer.csv""")
)
                      

// @LINE:53
def summary(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.summary(id), HandlerDef(this, "controllers.Customer", "summary", Seq(classOf[Long]), "GET", """""", _prefix + """customer/$id<[^/]+>/summary""")
)
                      

// @LINE:51
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.create(), HandlerDef(this, "controllers.Customer", "create", Seq(), "GET", """ Create""", _prefix + """customer/create""")
)
                      

// @LINE:47
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.list(p, s, o, f), HandlerDef(this, "controllers.Customer", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Customer Management""", _prefix + """customer""")
)
                      

// @LINE:57
def relate(id:Long, p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.relate(id, p, s, o, f), HandlerDef(this, "controllers.Customer", "relate", Seq(classOf[Long], classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """POST    /customer/:id/delete                controllers.Customer.delete(id:Long)""", _prefix + """customer/relate""")
)
                      

// @LINE:55
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.update(id), HandlerDef(this, "controllers.Customer", "update", Seq(classOf[Long]), "POST", """""", _prefix + """customer/$id<[^/]+>/edit""")
)
                      

// @LINE:52
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.save(), HandlerDef(this, "controllers.Customer", "save", Seq(), "POST", """""", _prefix + """customer/create""")
)
                      

// @LINE:54
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.edit(id), HandlerDef(this, "controllers.Customer", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """customer/$id<[^/]+>/edit""")
)
                      

// @LINE:59
def addRelateAction(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.addRelateAction(), HandlerDef(this, "controllers.Customer", "addRelateAction", Seq(), "POST", """""", _prefix + """customer/addrelateaction""")
)
                      
    
}
                          

// @LINE:204
// @LINE:203
class ReverseSuspicious {
    

// @LINE:203
def admin_index(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Suspicious.admin_index(p, s, o, f), HandlerDef(this, "controllers.Suspicious", "admin_index", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """admin/suspicious""")
)
                      

// @LINE:204
def csv(s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Suspicious.csv(s, o, f), HandlerDef(this, "controllers.Suspicious", "csv", Seq(classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """admin/suspicious.csv""")
)
                      
    
}
                          

// @LINE:268
// @LINE:267
// @LINE:266
// @LINE:265
// @LINE:264
class ReverseChannels {
    

// @LINE:268
def delete(code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Channels.delete(code), HandlerDef(this, "controllers.Channels", "delete", Seq(classOf[String]), "GET", """""", _prefix + """channels/delete/$code<[^/]+>""")
)
                      

// @LINE:266
def edit(code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Channels.edit(code), HandlerDef(this, "controllers.Channels", "edit", Seq(classOf[String]), "GET", """""", _prefix + """channels/$code<[^/]+>/edit""")
)
                      

// @LINE:264
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Channels.list(p, s, o, f), HandlerDef(this, "controllers.Channels", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Channels Management""", _prefix + """channels""")
)
                      

// @LINE:267
def update(code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Channels.update(code), HandlerDef(this, "controllers.Channels", "update", Seq(classOf[String]), "POST", """""", _prefix + """channels/$code<[^/]+>/edit""")
)
                      

// @LINE:265
def summary(code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Channels.summary(code), HandlerDef(this, "controllers.Channels", "summary", Seq(classOf[String]), "GET", """""", _prefix + """channels/$code<[^/]+>/summary""")
)
                      
    
}
                          

// @LINE:248
// @LINE:247
// @LINE:246
// @LINE:245
// @LINE:143
// @LINE:142
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
class ReverseUser {
    

// @LINE:39
def saveChangePassword(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.saveChangePassword(c, id), HandlerDef(this, "controllers.User", "saveChangePassword", Seq(classOf[String], classOf[Long]), "POST", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/password""")
)
                      

// @LINE:143
def css(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.css(id), HandlerDef(this, "controllers.User", "css", Seq(classOf[Long]), "GET", """""", _prefix + """users/$id<[^/]+>/css""")
)
                      

// @LINE:245
def indexOperasional(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.indexOperasional(c), HandlerDef(this, "controllers.User", "indexOperasional", Seq(classOf[String]), "GET", """OPERASIONAL""", _prefix + """corporates/$c<[^/]+>/usersoperasional""")
)
                      

// @LINE:34
def create(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.create(c), HandlerDef(this, "controllers.User", "create", Seq(classOf[String]), "POST", """""", _prefix + """corporates/$c<[^/]+>/users""")
)
                      

// @LINE:30
def index(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.index(c), HandlerDef(this, "controllers.User", "index", Seq(classOf[String]), "GET", """# User Management""", _prefix + """corporates/$c<[^/]+>/users/me""")
)
                      

// @LINE:31
def list(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.list(c, id), HandlerDef(this, "controllers.User", "list", Seq(classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/users""")
)
                      

// @LINE:33
def edit(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.edit(c, id), HandlerDef(this, "controllers.User", "edit", Seq(classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/edit""")
)
                      

// @LINE:37
def show(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.show(c, id), HandlerDef(this, "controllers.User", "show", Seq(classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>""")
)
                      

// @LINE:142
def photo(id:Long, s:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.photo(id, s), HandlerDef(this, "controllers.User", "photo", Seq(classOf[Long], classOf[String]), "GET", """""", _prefix + """users/$id<[^/]+>/photo""")
)
                      

// @LINE:35
def update(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.update(c, id), HandlerDef(this, "controllers.User", "update", Seq(classOf[String], classOf[Long]), "POST", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>""")
)
                      

// @LINE:41
def updateRolePromote(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.updateRolePromote(c, id), HandlerDef(this, "controllers.User", "updateRolePromote", Seq(classOf[String], classOf[Long]), "POST", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/promote""")
)
                      

// @LINE:38
def changePassword(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.changePassword(c, id), HandlerDef(this, "controllers.User", "changePassword", Seq(classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/password""")
)
                      

// @LINE:36
def destroy(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.destroy(c, id), HandlerDef(this, "controllers.User", "destroy", Seq(classOf[String], classOf[Long]), "DELETE", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>""")
)
                      

// @LINE:246
def listOperasional(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.listOperasional(c), HandlerDef(this, "controllers.User", "listOperasional", Seq(classOf[String]), "GET", """""", _prefix + """corporates/$c<[^/]+>/usersoperasional/users""")
)
                      

// @LINE:43
def updateRoleDemoteForm(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.updateRoleDemoteForm(c, id), HandlerDef(this, "controllers.User", "updateRoleDemoteForm", Seq(classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/demoteForm""")
)
                      

// @LINE:32
def newCreate(c:String, role:String, supervisorId:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.newCreate(c, role, supervisorId), HandlerDef(this, "controllers.User", "newCreate", Seq(classOf[String], classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/new/$role<[^/]+>""")
)
                      

// @LINE:42
def updateRoleDemote(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.updateRoleDemote(c, id), HandlerDef(this, "controllers.User", "updateRoleDemote", Seq(classOf[String], classOf[Long]), "POST", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/role/demote""")
)
                      

// @LINE:247
def updateOperasional(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.updateOperasional(c, id), HandlerDef(this, "controllers.User", "updateOperasional", Seq(classOf[String], classOf[Long]), "POST", """""", _prefix + """corporates/$c<[^/]+>/usersoperasional/$id<[^/]+>""")
)
                      

// @LINE:248
def editOperasional(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.editOperasional(c, id), HandlerDef(this, "controllers.User", "editOperasional", Seq(classOf[String], classOf[Long]), "GET", """""", _prefix + """corporates/$c<[^/]+>/usersoperasional/$id<[^/]+>/edit""")
)
                      

// @LINE:40
def updateStatus(c:String, id:Long, ta:Boolean): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.User.updateStatus(c, id, ta), HandlerDef(this, "controllers.User", "updateStatus", Seq(classOf[String], classOf[Long], classOf[Boolean]), "GET", """""", _prefix + """corporates/$c<[^/]+>/users/$id<[^/]+>/status""")
)
                      
    
}
                          

// @LINE:155
// @LINE:11
class ReverseMain {
    

// @LINE:11
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Main.index(), HandlerDef(this, "controllers.Main", "index", Seq(), "GET", """# Home page""", _prefix + """""")
)
                      

// @LINE:155
def admin_index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Main.admin_index(), HandlerDef(this, "controllers.Main", "admin_index", Seq(), "GET", """####################################
# Backend Page
# Admin page""", _prefix + """admin""")
)
                      
    
}
                          

// @LINE:241
// @LINE:240
// @LINE:239
// @LINE:238
class ReverseGetBank {
    

// @LINE:240
def getBank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GetBank.getBank(), HandlerDef(this, "controllers.GetBank", "getBank", Seq(), "POST", """""", _prefix + """getbank""")
)
                      

// @LINE:238
def getProvinceBank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GetBank.getProvinceBank(), HandlerDef(this, "controllers.GetBank", "getProvinceBank", Seq(), "POST", """""", _prefix + """getprovincebank""")
)
                      

// @LINE:241
def getGroupBank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GetBank.getGroupBank(), HandlerDef(this, "controllers.GetBank", "getGroupBank", Seq(), "POST", """""", _prefix + """getcountrybank""")
)
                      

// @LINE:239
def getCityBank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.GetBank.getCityBank(), HandlerDef(this, "controllers.GetBank", "getCityBank", Seq(), "POST", """""", _prefix + """getcitybank""")
)
                      
    
}
                          

// @LINE:117
// @LINE:116
// @LINE:115
class ReverseReport {
    

// @LINE:117
def excel(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Report.excel(), HandlerDef(this, "controllers.Report", "excel", Seq(), "GET", """""", _prefix + """report/excel""")
)
                      

// @LINE:116
def pdf(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Report.pdf(), HandlerDef(this, "controllers.Report", "pdf", Seq(), "GET", """""", _prefix + """report/pdf""")
)
                      

// @LINE:115
def list(p:Int, s:String, o:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Report.list(p, s, o), HandlerDef(this, "controllers.Report", "list", Seq(classOf[Int], classOf[String], classOf[String]), "GET", """# Report""", _prefix + """report""")
)
                      
    
}
                          

// @LINE:279
// @LINE:278
// @LINE:277
// @LINE:273
// @LINE:272
class ReverseBatchUpload {
    

// @LINE:272
def viewUpload(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BatchUpload.viewUpload(), HandlerDef(this, "controllers.BatchUpload", "viewUpload", Seq(), "GET", """""", _prefix + """batchupload""")
)
                      

// @LINE:273
def actionUpload(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BatchUpload.actionUpload(), HandlerDef(this, "controllers.BatchUpload", "actionUpload", Seq(), "POST", """""", _prefix + """dobatchupload""")
)
                      

// @LINE:277
def actionRemit(token:String, batchId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BatchUpload.actionRemit(token, batchId), HandlerDef(this, "controllers.BatchUpload", "actionRemit", Seq(classOf[String], classOf[Int]), "POST", """""", _prefix + """dobatchremit/$token<[^/]+>/$batchId<[^/]+>""")
)
                      

// @LINE:278
def viewResultBatchUpload(batchId:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BatchUpload.viewResultBatchUpload(batchId), HandlerDef(this, "controllers.BatchUpload", "viewResultBatchUpload", Seq(classOf[Int]), "GET", """""", _prefix + """viewbatchupload/$batchId<[^/]+>""")
)
                      

// @LINE:279
def viewResultBatchUploadFailed(fileName:String, totalRow:Int, description:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.BatchUpload.viewResultBatchUploadFailed(fileName, totalRow, description), HandlerDef(this, "controllers.BatchUpload", "viewResultBatchUploadFailed", Seq(classOf[String], classOf[Int], classOf[String]), "GET", """""", _prefix + """viewbatchuploadfailed""")
)
                      
    
}
                          

// @LINE:191
// @LINE:190
// @LINE:189
// @LINE:188
// @LINE:187
// @LINE:139
class ReverseForex {
    

// @LINE:190
def admin_update(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Forex.admin_update(c, id), HandlerDef(this, "controllers.Forex", "admin_update", Seq(classOf[String], classOf[Long]), "PUT", """""", _prefix + """admin/corporates/$c<[^/]+>/forex/$id<[^/]+>""")
)
                      

// @LINE:187
def admin_index_main(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Forex.admin_index_main(), HandlerDef(this, "controllers.Forex", "admin_index_main", Seq(), "GET", """# Corporate Forex Management""", _prefix + """admin/corporates/DOK/forex""")
)
                      

// @LINE:188
def admin_index(c:String, origin:String, destination:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Forex.admin_index(c, origin, destination), HandlerDef(this, "controllers.Forex", "admin_index", Seq(classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """admin/corporates/$c<[^/]+>/forex""")
)
                      

// @LINE:139
def table(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Forex.table(c), HandlerDef(this, "controllers.Forex", "table", Seq(classOf[String]), "GET", """# FOrex""", _prefix + """forexs/$c<[^/]+>/table""")
)
                      

// @LINE:191
def admin_destroy(c:String, id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Forex.admin_destroy(c, id), HandlerDef(this, "controllers.Forex", "admin_destroy", Seq(classOf[String], classOf[Long]), "DELETE", """""", _prefix + """admin/corporates/$c<[^/]+>/forex/$id<[^/]+>""")
)
                      

// @LINE:189
def admin_create(c:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Forex.admin_create(c), HandlerDef(this, "controllers.Forex", "admin_create", Seq(classOf[String]), "POST", """""", _prefix + """admin/corporates/$c<[^/]+>/forex""")
)
                      
    
}
                          

// @LINE:242
class ReverseValidateVoucher {
    

// @LINE:242
def getVoucher(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.ValidateVoucher.getVoucher(), HandlerDef(this, "controllers.ValidateVoucher", "getVoucher", Seq(), "POST", """""", _prefix + """validatevoucher""")
)
                      
    
}
                          

// @LINE:251
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
class ReverseSend {
    

// @LINE:69
def confirm(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.confirm(), HandlerDef(this, "controllers.Send", "confirm", Seq(), "POST", """""", _prefix + """send/confirm""")
)
                      

// @LINE:83
def receiptPrint(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.receiptPrint(id), HandlerDef(this, "controllers.Send", "receiptPrint", Seq(classOf[String]), "GET", """PUT     /send/customer/save                controllers.Send.customerSave()""", _prefix + """send/$id<[^/]+>/receipt-print""")
)
                      

// @LINE:64
def inquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.inquiry(), HandlerDef(this, "controllers.Send", "inquiry", Seq(), "GET", """""", _prefix + """send/inquiry""")
)
                      

// @LINE:84
def receiptEmail(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.receiptEmail(id), HandlerDef(this, "controllers.Send", "receiptEmail", Seq(classOf[String]), "GET", """""", _prefix + """send/$id<[^/]+>/receipt-email""")
)
                      

// @LINE:73
def receipt(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.receipt(id), HandlerDef(this, "controllers.Send", "receipt", Seq(classOf[String]), "GET", """""", _prefix + """send/$id<[^/]+>/receipt""")
)
                      

// @LINE:65
def inquiryBillPayment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.inquiryBillPayment(), HandlerDef(this, "controllers.Send", "inquiryBillPayment", Seq(), "GET", """""", _prefix + """send/inquiryBillPayment""")
)
                      

// @LINE:80
def customerEdit(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.customerEdit(id), HandlerDef(this, "controllers.Send", "customerEdit", Seq(classOf[String]), "GET", """""", _prefix + """send/customer/$id<[^/]+>/edit""")
)
                      

// @LINE:78
def customerNew(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.customerNew(), HandlerDef(this, "controllers.Send", "customerNew", Seq(), "GET", """""", _prefix + """send/customer/new""")
)
                      

// @LINE:70
def confirmBillPayment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.confirmBillPayment(), HandlerDef(this, "controllers.Send", "confirmBillPayment", Seq(), "POST", """""", _prefix + """send/confirmBillPayment""")
)
                      

// @LINE:71
def process(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.process(), HandlerDef(this, "controllers.Send", "process", Seq(), "POST", """""", _prefix + """send/process""")
)
                      

// @LINE:77
def summary(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.summary(), HandlerDef(this, "controllers.Send", "summary", Seq(), "POST", """""", _prefix + """send/summary""")
)
                      

// @LINE:79
def customerSearch(query:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.customerSearch(query), HandlerDef(this, "controllers.Send", "customerSearch", Seq(classOf[String]), "GET", """""", _prefix + """send/customer/search/$query<[^/]+>""")
)
                      

// @LINE:67
def customerLookupRelate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.customerLookupRelate(), HandlerDef(this, "controllers.Send", "customerLookupRelate", Seq(), "GET", """""", _prefix + """send/customer-lookupRelate""")
)
                      

// @LINE:63
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.create(), HandlerDef(this, "controllers.Send", "create", Seq(), "GET", """# Transaction Send Money""", _prefix + """send""")
)
                      

// @LINE:74
def receiptBillPayment(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.receiptBillPayment(id), HandlerDef(this, "controllers.Send", "receiptBillPayment", Seq(classOf[String]), "GET", """""", _prefix + """send/$id<[^/]+>/receiptBillPayment""")
)
                      

// @LINE:68
def verify(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.verify(), HandlerDef(this, "controllers.Send", "verify", Seq(), "POST", """""", _prefix + """send/verify""")
)
                      

// @LINE:72
def processBillPayment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.processBillPayment(), HandlerDef(this, "controllers.Send", "processBillPayment", Seq(), "POST", """""", _prefix + """send/processBillPayment""")
)
                      

// @LINE:81
def customerSave(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.customerSave(), HandlerDef(this, "controllers.Send", "customerSave", Seq(), "POST", """""", _prefix + """send/customer/save""")
)
                      

// @LINE:66
def customerLookup(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.customerLookup(), HandlerDef(this, "controllers.Send", "customerLookup", Seq(), "GET", """""", _prefix + """send/customer-lookup""")
)
                      

// @LINE:251
def createBillPayment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.createBillPayment(), HandlerDef(this, "controllers.Send", "createBillPayment", Seq(), "GET", """# Transaction Send Money""", _prefix + """sendBillPayment""")
)
                      

// @LINE:75
def sendTransactionViaEmail(id:String, recipient:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Send.sendTransactionViaEmail(id, recipient), HandlerDef(this, "controllers.Send", "sendTransactionViaEmail", Seq(classOf[String], classOf[String]), "GET", """""", _prefix + """send/$id<[^/]+>/send-email""")
)
                      
    
}
                          

// @LINE:261
// @LINE:260
// @LINE:259
// @LINE:258
// @LINE:257
// @LINE:256
// @LINE:255
// @LINE:254
class ReverseBanks {
    

// @LINE:255
def csv(s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.csv(s, o, f), HandlerDef(this, "controllers.Banks", "csv", Seq(classOf[String], classOf[String], classOf[String]), "GET", """""", _prefix + """banks.csv""")
)
                      

// @LINE:261
def delete(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.delete(id), HandlerDef(this, "controllers.Banks", "delete", Seq(classOf[String]), "GET", """""", _prefix + """banks/delete/$id<[^/]+>""")
)
                      

// @LINE:257
def edit(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.edit(id), HandlerDef(this, "controllers.Banks", "edit", Seq(classOf[String]), "GET", """""", _prefix + """banks/$id<[^/]+>/edit""")
)
                      

// @LINE:259
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.create(), HandlerDef(this, "controllers.Banks", "create", Seq(), "GET", """""", _prefix + """banks/create""")
)
                      

// @LINE:254
def list(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.list(p, s, o, f), HandlerDef(this, "controllers.Banks", "list", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """# Banks Management""", _prefix + """banks""")
)
                      

// @LINE:258
def update(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.update(id), HandlerDef(this, "controllers.Banks", "update", Seq(classOf[String]), "POST", """""", _prefix + """banks/$id<[^/]+>/edit""")
)
                      

// @LINE:256
def summary(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.summary(id), HandlerDef(this, "controllers.Banks", "summary", Seq(classOf[String]), "GET", """""", _prefix + """banks/$id<[^/]+>/summary""")
)
                      

// @LINE:260
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Banks.save(), HandlerDef(this, "controllers.Banks", "save", Seq(), "POST", """""", _prefix + """banks/create""")
)
                      
    
}
                          

// @LINE:156
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
class ReverseDashboard {
    

// @LINE:13
def userPerformances(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Dashboard.userPerformances(id), HandlerDef(this, "controllers.Dashboard", "userPerformances", Seq(classOf[String]), "GET", """""", _prefix + """dashboard/statistics/$id<[^/]+>/performances.json""")
)
                      

// @LINE:14
def userVolumes(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Dashboard.userVolumes(id), HandlerDef(this, "controllers.Dashboard", "userVolumes", Seq(classOf[String]), "GET", """""", _prefix + """dashboard/statistics/$id<[^/]+>/volumes.json""")
)
                      

// @LINE:16
def news(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Dashboard.news(id), HandlerDef(this, "controllers.Dashboard", "news", Seq(classOf[Long]), "GET", """""", _prefix + """news/content/$id<[^/]+>""")
)
                      

// @LINE:15
def statistics(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Dashboard.statistics(id), HandlerDef(this, "controllers.Dashboard", "statistics", Seq(classOf[String]), "GET", """""", _prefix + """dashboard/statistics/$id<[^/]+>""")
)
                      

// @LINE:12
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Dashboard.index(), HandlerDef(this, "controllers.Dashboard", "index", Seq(), "GET", """""", _prefix + """dashboard""")
)
                      

// @LINE:156
def admin_index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Dashboard.admin_index(), HandlerDef(this, "controllers.Dashboard", "admin_index", Seq(), "GET", """""", _prefix + """admin/dashboard""")
)
                      
    
}
                          

// @LINE:147
// @LINE:146
// @LINE:145
// @LINE:144
class ReverseHelpers {
    

// @LINE:147
def banks_detail(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Helpers.banks_detail(id), HandlerDef(this, "controllers.Helpers", "banks_detail", Seq(classOf[String]), "GET", """""", _prefix + """helpers/banks/$id<[^/]+>.json""")
)
                      

// @LINE:145
def cities(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Helpers.cities(), HandlerDef(this, "controllers.Helpers", "cities", Seq(), "GET", """""", _prefix + """helpers/cities.json""")
)
                      

// @LINE:146
def banks(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Helpers.banks(), HandlerDef(this, "controllers.Helpers", "banks", Seq(), "GET", """""", _prefix + """helpers/banks.json""")
)
                      

// @LINE:144
def currencies(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Helpers.currencies(), HandlerDef(this, "controllers.Helpers", "currencies", Seq(), "GET", """""", _prefix + """helpers/currencies.json""")
)
                      
    
}
                          

// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:132
// @LINE:131
class ReverseNews {
    

// @LINE:131
def list(p:Int, s:String, o:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.News.list(p, s, o), HandlerDef(this, "controllers.News", "list", Seq(classOf[Int], classOf[String], classOf[String]), "GET", """# News""", _prefix + """news/list""")
)
                      

// @LINE:132
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.News.create(), HandlerDef(this, "controllers.News", "create", Seq(), "GET", """""", _prefix + """news/create""")
)
                      

// @LINE:135
def update(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.News.update(id), HandlerDef(this, "controllers.News", "update", Seq(classOf[Long]), "POST", """""", _prefix + """news/update/$id<[^/]+>""")
)
                      

// @LINE:133
def save(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.News.save(), HandlerDef(this, "controllers.News", "save", Seq(), "POST", """""", _prefix + """news/save""")
)
                      

// @LINE:134
def edit(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.News.edit(id), HandlerDef(this, "controllers.News", "edit", Seq(classOf[Long]), "GET", """""", _prefix + """news/edit/$id<[^/]+>""")
)
                      

// @LINE:136
def status(id:Long, status:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.News.status(id, status), HandlerDef(this, "controllers.News", "status", Seq(classOf[Long], classOf[String]), "GET", """""", _prefix + """news/status/$id<[^/]+>/$status<[^/]+>""")
)
                      
    
}
                          
}
                  
      