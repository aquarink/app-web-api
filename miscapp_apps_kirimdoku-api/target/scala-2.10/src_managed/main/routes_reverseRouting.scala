// @SOURCE:/Users/pebri/Documents/play-2.1.1/miscapp_apps_kirimdoku-api/conf/routes
// @HASH:2898bd96ef8ea1671a412e4b9e702108ecb1820f
// @DATE:Mon Jan 16 10:17:34 WIB 2017

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._
import java.net.URLEncoder

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:93
// @LINE:92
// @LINE:89
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:52
// @LINE:51
// @LINE:50
package kirimdoku.interfaces {

// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
class ReverseTransaction {
    

// @LINE:65
def getTransactionChange(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getTransactionChange")
}
                                                

// @LINE:66
def getTransactionLocked(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getTransactionLocked")
}
                                                

// @LINE:63
def getTransaction(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "transactionLookup")
}
                                                

// @LINE:67
def getTransactionList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "transactionList")
}
                                                

// @LINE:64
def getTransactionRefund(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getTransactionRefund")
}
                                                
    
}
                          

// @LINE:56
// @LINE:55
// @LINE:54
class ReverseCashOut {
    

// @LINE:55
def validate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashoutValidate")
}
                                                

// @LINE:56
def collect(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashoutCollect")
}
                                                

// @LINE:54
def inquiry(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashoutInquiry")
}
                                                
    
}
                          

// @LINE:52
// @LINE:51
class ReverseCashIn {
    

// @LINE:52
def remit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashinRemit")
}
                                                

// @LINE:51
def inquiry(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashinInquiry")
}
                                                
    
}
                          

// @LINE:83
class ReverseTranslate {
    

// @LINE:83
def doTranslate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "doTranslate")
}
                                                
    
}
                          

// @LINE:70
class ReverseRefundTransaction {
    

// @LINE:70
def process(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "refundTransaction")
}
                                                
    
}
                          

// @LINE:50
class ReverseLogin {
    

// @LINE:50
def index(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          

// @LINE:69
class ReverseCheckRate {
    

// @LINE:69
def process(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "checkRates")
}
                                                
    
}
                          

// @LINE:81
// @LINE:80
class ReverseCountries {
    

// @LINE:80
def getList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getListCountry")
}
                                                

// @LINE:81
def getReceiveList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getReceiveListCountry")
}
                                                
    
}
                          

// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
class ReverseCustomer {
    

// @LINE:58
def getCustomer(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customerLookup")
}
                                                

// @LINE:59
def getCustomerList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customerList")
}
                                                

// @LINE:60
def addCustomer(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customerAdd")
}
                                                

// @LINE:61
def getCustomerSenderList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "customerSenderList")
}
                                                
    
}
                          

// @LINE:71
class ReverseUnlockTransaction {
    

// @LINE:71
def process(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "unlockTransaction")
}
                                                
    
}
                          

// @LINE:85
class ReverseInquiryBillPayment {
    

// @LINE:85
def doInquiry(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "inquiryBillPayment")
}
                                                
    
}
                          

// @LINE:84
class ReverseUser {
    

// @LINE:84
def getInformation(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "userInformation")
}
                                                
    
}
                          

// @LINE:72
class ReverseChangeTransaction {
    

// @LINE:72
def process(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "changeTransaction")
}
                                                
    
}
                          

// @LINE:89
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
class ReverseBanks {
    

// @LINE:79
def getList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getListBank")
}
                                                

// @LINE:78
def getCityBankList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getCityBankList")
}
                                                

// @LINE:77
def getProvinceBankList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getProvinceBankList")
}
                                                

// @LINE:76
def getGroupBankList(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getGroupBankList")
}
                                                

// @LINE:89
def getDataBank(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "getdatabank")
}
                                                
    
}
                          

// @LINE:93
// @LINE:92
class ReverseCashInBatch {
    

// @LINE:93
def remit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashin/remitbatch")
}
                                                

// @LINE:92
def inquiry(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashin/inquirybatch")
}
                                                
    
}
                          
}
                  

// @LINE:87
// @LINE:74
// @LINE:46
// @LINE:45
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:87
// @LINE:25
// @LINE:23
// @LINE:22
class ReverseTransaction {
    

// @LINE:25
def search(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transactions")
}
                                                

// @LINE:87
def callback(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "callback")
}
                                                

// @LINE:23
def summary(id:String): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "transaction/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:22
def show(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "transaction/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                
    
}
                          

// @LINE:19
// @LINE:18
// @LINE:17
class ReverseCashOut {
    

// @LINE:18
def validate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashout/validate")
}
                                                

// @LINE:19
def collect(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashout/collect")
}
                                                

// @LINE:17
def inquiry(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashout/inquiry")
}
                                                
    
}
                          

// @LINE:14
// @LINE:13
class ReverseCashIn {
    

// @LINE:14
def remit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashin/remit")
}
                                                

// @LINE:13
def inquiry(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "cashin/inquiry")
}
                                                
    
}
                          

// @LINE:46
// @LINE:45
class ReverseTranslate {
    

// @LINE:46
def QueryTC(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "translate/tcode")
}
                                                

// @LINE:45
def QueryPinyin(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "translate/pinyin")
}
                                                
    
}
                          

// @LINE:10
// @LINE:9
class ReverseNetwork {
    

// @LINE:9
def ping(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "ping")
}
                                                

// @LINE:10
def pings(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "ping")
}
                                                
    
}
                          

// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:6
class ReverseLab {
    

// @LINE:39
def test1(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/1")
}
                                                

// @LINE:40
def test2(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/2")
}
                                                

// @LINE:37
def suspiciousCheck(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/check/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                

// @LINE:36
def settlement(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/settlement")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:38
def hello(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/hello")
}
                                                

// @LINE:42
def test4(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/4")
}
                                                

// @LINE:41
def test3(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "lab/3")
}
                                                
    
}
                          

// @LINE:30
class ReverseCustomer {
    

// @LINE:30
def show(id:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customers/" + implicitly[PathBindable[String]].unbind("id", URLEncoder.encode(id, "utf-8")))
}
                                                
    
}
                          

// @LINE:74
class ReverseLogo {
    

// @LINE:74
def index(code:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logo/" + implicitly[PathBindable[String]].unbind("code", URLEncoder.encode(code, "utf-8")))
}
                                                
    
}
                          
}
                  


// @LINE:93
// @LINE:92
// @LINE:89
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:52
// @LINE:51
// @LINE:50
package kirimdoku.interfaces.javascript {

// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
class ReverseTransaction {
    

// @LINE:65
def getTransactionChange : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Transaction.getTransactionChange",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getTransactionChange"})
      }
   """
)
                        

// @LINE:66
def getTransactionLocked : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Transaction.getTransactionLocked",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getTransactionLocked"})
      }
   """
)
                        

// @LINE:63
def getTransaction : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Transaction.getTransaction",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "transactionLookup"})
      }
   """
)
                        

// @LINE:67
def getTransactionList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Transaction.getTransactionList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "transactionList"})
      }
   """
)
                        

// @LINE:64
def getTransactionRefund : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Transaction.getTransactionRefund",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getTransactionRefund"})
      }
   """
)
                        
    
}
              

// @LINE:56
// @LINE:55
// @LINE:54
class ReverseCashOut {
    

// @LINE:55
def validate : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashOut.validate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashoutValidate"})
      }
   """
)
                        

// @LINE:56
def collect : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashOut.collect",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashoutCollect"})
      }
   """
)
                        

// @LINE:54
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashOut.inquiry",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashoutInquiry"})
      }
   """
)
                        
    
}
              

// @LINE:52
// @LINE:51
class ReverseCashIn {
    

// @LINE:52
def remit : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashIn.remit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashinRemit"})
      }
   """
)
                        

// @LINE:51
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashIn.inquiry",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashinInquiry"})
      }
   """
)
                        
    
}
              

// @LINE:83
class ReverseTranslate {
    

// @LINE:83
def doTranslate : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Translate.doTranslate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "doTranslate"})
      }
   """
)
                        
    
}
              

// @LINE:70
class ReverseRefundTransaction {
    

// @LINE:70
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.RefundTransaction.process",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "refundTransaction"})
      }
   """
)
                        
    
}
              

// @LINE:50
class ReverseLogin {
    

// @LINE:50
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Login.index",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              

// @LINE:69
class ReverseCheckRate {
    

// @LINE:69
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CheckRate.process",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "checkRates"})
      }
   """
)
                        
    
}
              

// @LINE:81
// @LINE:80
class ReverseCountries {
    

// @LINE:80
def getList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Countries.getList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getListCountry"})
      }
   """
)
                        

// @LINE:81
def getReceiveList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Countries.getReceiveList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getReceiveListCountry"})
      }
   """
)
                        
    
}
              

// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
class ReverseCustomer {
    

// @LINE:58
def getCustomer : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Customer.getCustomer",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customerLookup"})
      }
   """
)
                        

// @LINE:59
def getCustomerList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Customer.getCustomerList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customerList"})
      }
   """
)
                        

// @LINE:60
def addCustomer : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Customer.addCustomer",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customerAdd"})
      }
   """
)
                        

// @LINE:61
def getCustomerSenderList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Customer.getCustomerSenderList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "customerSenderList"})
      }
   """
)
                        
    
}
              

// @LINE:71
class ReverseUnlockTransaction {
    

// @LINE:71
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.UnlockTransaction.process",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "unlockTransaction"})
      }
   """
)
                        
    
}
              

// @LINE:85
class ReverseInquiryBillPayment {
    

// @LINE:85
def doInquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.InquiryBillPayment.doInquiry",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "inquiryBillPayment"})
      }
   """
)
                        
    
}
              

// @LINE:84
class ReverseUser {
    

// @LINE:84
def getInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.User.getInformation",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "userInformation"})
      }
   """
)
                        
    
}
              

// @LINE:72
class ReverseChangeTransaction {
    

// @LINE:72
def process : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.ChangeTransaction.process",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "changeTransaction"})
      }
   """
)
                        
    
}
              

// @LINE:89
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
class ReverseBanks {
    

// @LINE:79
def getList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Banks.getList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getListBank"})
      }
   """
)
                        

// @LINE:78
def getCityBankList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Banks.getCityBankList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getCityBankList"})
      }
   """
)
                        

// @LINE:77
def getProvinceBankList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Banks.getProvinceBankList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getProvinceBankList"})
      }
   """
)
                        

// @LINE:76
def getGroupBankList : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Banks.getGroupBankList",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getGroupBankList"})
      }
   """
)
                        

// @LINE:89
def getDataBank : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.Banks.getDataBank",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getdatabank"})
      }
   """
)
                        
    
}
              

// @LINE:93
// @LINE:92
class ReverseCashInBatch {
    

// @LINE:93
def remit : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashInBatch.remit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashin/remitbatch"})
      }
   """
)
                        

// @LINE:92
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "kirimdoku.interfaces.CashInBatch.inquiry",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashin/inquirybatch"})
      }
   """
)
                        
    
}
              
}
        

// @LINE:87
// @LINE:74
// @LINE:46
// @LINE:45
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:87
// @LINE:25
// @LINE:23
// @LINE:22
class ReverseTransaction {
    

// @LINE:25
def search : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.search",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transactions"})
      }
   """
)
                        

// @LINE:87
def callback : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.callback",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "callback"})
      }
   """
)
                        

// @LINE:23
def summary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.summary",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:22
def show : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Transaction.show",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transaction/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        
    
}
              

// @LINE:19
// @LINE:18
// @LINE:17
class ReverseCashOut {
    

// @LINE:18
def validate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CashOut.validate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashout/validate"})
      }
   """
)
                        

// @LINE:19
def collect : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CashOut.collect",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashout/collect"})
      }
   """
)
                        

// @LINE:17
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CashOut.inquiry",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashout/inquiry"})
      }
   """
)
                        
    
}
              

// @LINE:14
// @LINE:13
class ReverseCashIn {
    

// @LINE:14
def remit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CashIn.remit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashin/remit"})
      }
   """
)
                        

// @LINE:13
def inquiry : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CashIn.inquiry",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cashin/inquiry"})
      }
   """
)
                        
    
}
              

// @LINE:46
// @LINE:45
class ReverseTranslate {
    

// @LINE:46
def QueryTC : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Translate.QueryTC",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "translate/tcode"})
      }
   """
)
                        

// @LINE:45
def QueryPinyin : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Translate.QueryPinyin",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "translate/pinyin"})
      }
   """
)
                        
    
}
              

// @LINE:10
// @LINE:9
class ReverseNetwork {
    

// @LINE:9
def ping : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Network.ping",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "ping"})
      }
   """
)
                        

// @LINE:10
def pings : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Network.pings",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "ping"})
      }
   """
)
                        
    
}
              

// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:6
class ReverseLab {
    

// @LINE:39
def test1 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.test1",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/1"})
      }
   """
)
                        

// @LINE:40
def test2 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.test2",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/2"})
      }
   """
)
                        

// @LINE:37
def suspiciousCheck : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.suspiciousCheck",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/check/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        

// @LINE:36
def settlement : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.settlement",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/settlement"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:38
def hello : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.hello",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/hello"})
      }
   """
)
                        

// @LINE:42
def test4 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.test4",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/4"})
      }
   """
)
                        

// @LINE:41
def test3 : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Lab.test3",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "lab/3"})
      }
   """
)
                        
    
}
              

// @LINE:30
class ReverseCustomer {
    

// @LINE:30
def show : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Customer.show",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customers/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("id", encodeURIComponent(id))})
      }
   """
)
                        
    
}
              

// @LINE:74
class ReverseLogo {
    

// @LINE:74
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Logo.index",
   """
      function(code) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logo/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("code", encodeURIComponent(code))})
      }
   """
)
                        
    
}
              
}
        


// @LINE:93
// @LINE:92
// @LINE:89
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:52
// @LINE:51
// @LINE:50
package kirimdoku.interfaces.ref {

// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
class ReverseTransaction {
    

// @LINE:65
def getTransactionChange(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Transaction.getTransactionChange(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionChange", Seq(), "POST", """""", _prefix + """getTransactionChange""")
)
                      

// @LINE:66
def getTransactionLocked(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Transaction.getTransactionLocked(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionLocked", Seq(), "POST", """""", _prefix + """getTransactionLocked""")
)
                      

// @LINE:63
def getTransaction(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Transaction.getTransaction(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransaction", Seq(), "POST", """""", _prefix + """transactionLookup""")
)
                      

// @LINE:67
def getTransactionList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Transaction.getTransactionList(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionList", Seq(), "POST", """""", _prefix + """transactionList""")
)
                      

// @LINE:64
def getTransactionRefund(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Transaction.getTransactionRefund(), HandlerDef(this, "kirimdoku.interfaces.Transaction", "getTransactionRefund", Seq(), "POST", """""", _prefix + """getTransactionRefund""")
)
                      
    
}
                          

// @LINE:56
// @LINE:55
// @LINE:54
class ReverseCashOut {
    

// @LINE:55
def validate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashOut.validate(), HandlerDef(this, "kirimdoku.interfaces.CashOut", "validate", Seq(), "POST", """""", _prefix + """cashoutValidate""")
)
                      

// @LINE:56
def collect(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashOut.collect(), HandlerDef(this, "kirimdoku.interfaces.CashOut", "collect", Seq(), "POST", """""", _prefix + """cashoutCollect""")
)
                      

// @LINE:54
def inquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashOut.inquiry(), HandlerDef(this, "kirimdoku.interfaces.CashOut", "inquiry", Seq(), "POST", """""", _prefix + """cashoutInquiry""")
)
                      
    
}
                          

// @LINE:52
// @LINE:51
class ReverseCashIn {
    

// @LINE:52
def remit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashIn.remit(), HandlerDef(this, "kirimdoku.interfaces.CashIn", "remit", Seq(), "POST", """""", _prefix + """cashinRemit""")
)
                      

// @LINE:51
def inquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashIn.inquiry(), HandlerDef(this, "kirimdoku.interfaces.CashIn", "inquiry", Seq(), "POST", """""", _prefix + """cashinInquiry""")
)
                      
    
}
                          

// @LINE:83
class ReverseTranslate {
    

// @LINE:83
def doTranslate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Translate.doTranslate(), HandlerDef(this, "kirimdoku.interfaces.Translate", "doTranslate", Seq(), "POST", """""", _prefix + """doTranslate""")
)
                      
    
}
                          

// @LINE:70
class ReverseRefundTransaction {
    

// @LINE:70
def process(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.RefundTransaction.process(), HandlerDef(this, "kirimdoku.interfaces.RefundTransaction", "process", Seq(), "POST", """""", _prefix + """refundTransaction""")
)
                      
    
}
                          

// @LINE:50
class ReverseLogin {
    

// @LINE:50
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Login.index(), HandlerDef(this, "kirimdoku.interfaces.Login", "index", Seq(), "POST", """ NEW API MOBILE""", _prefix + """login""")
)
                      
    
}
                          

// @LINE:69
class ReverseCheckRate {
    

// @LINE:69
def process(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CheckRate.process(), HandlerDef(this, "kirimdoku.interfaces.CheckRate", "process", Seq(), "POST", """""", _prefix + """checkRates""")
)
                      
    
}
                          

// @LINE:81
// @LINE:80
class ReverseCountries {
    

// @LINE:80
def getList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Countries.getList(), HandlerDef(this, "kirimdoku.interfaces.Countries", "getList", Seq(), "POST", """""", _prefix + """getListCountry""")
)
                      

// @LINE:81
def getReceiveList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Countries.getReceiveList(), HandlerDef(this, "kirimdoku.interfaces.Countries", "getReceiveList", Seq(), "POST", """""", _prefix + """getReceiveListCountry""")
)
                      
    
}
                          

// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
class ReverseCustomer {
    

// @LINE:58
def getCustomer(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Customer.getCustomer(), HandlerDef(this, "kirimdoku.interfaces.Customer", "getCustomer", Seq(), "POST", """""", _prefix + """customerLookup""")
)
                      

// @LINE:59
def getCustomerList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Customer.getCustomerList(), HandlerDef(this, "kirimdoku.interfaces.Customer", "getCustomerList", Seq(), "POST", """""", _prefix + """customerList""")
)
                      

// @LINE:60
def addCustomer(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Customer.addCustomer(), HandlerDef(this, "kirimdoku.interfaces.Customer", "addCustomer", Seq(), "POST", """""", _prefix + """customerAdd""")
)
                      

// @LINE:61
def getCustomerSenderList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Customer.getCustomerSenderList(), HandlerDef(this, "kirimdoku.interfaces.Customer", "getCustomerSenderList", Seq(), "POST", """""", _prefix + """customerSenderList""")
)
                      
    
}
                          

// @LINE:71
class ReverseUnlockTransaction {
    

// @LINE:71
def process(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.UnlockTransaction.process(), HandlerDef(this, "kirimdoku.interfaces.UnlockTransaction", "process", Seq(), "POST", """""", _prefix + """unlockTransaction""")
)
                      
    
}
                          

// @LINE:85
class ReverseInquiryBillPayment {
    

// @LINE:85
def doInquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.InquiryBillPayment.doInquiry(), HandlerDef(this, "kirimdoku.interfaces.InquiryBillPayment", "doInquiry", Seq(), "POST", """""", _prefix + """inquiryBillPayment""")
)
                      
    
}
                          

// @LINE:84
class ReverseUser {
    

// @LINE:84
def getInformation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.User.getInformation(), HandlerDef(this, "kirimdoku.interfaces.User", "getInformation", Seq(), "POST", """""", _prefix + """userInformation""")
)
                      
    
}
                          

// @LINE:72
class ReverseChangeTransaction {
    

// @LINE:72
def process(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.ChangeTransaction.process(), HandlerDef(this, "kirimdoku.interfaces.ChangeTransaction", "process", Seq(), "POST", """""", _prefix + """changeTransaction""")
)
                      
    
}
                          

// @LINE:89
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
class ReverseBanks {
    

// @LINE:79
def getList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Banks.getList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getList", Seq(), "POST", """""", _prefix + """getListBank""")
)
                      

// @LINE:78
def getCityBankList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Banks.getCityBankList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getCityBankList", Seq(), "POST", """""", _prefix + """getCityBankList""")
)
                      

// @LINE:77
def getProvinceBankList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Banks.getProvinceBankList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getProvinceBankList", Seq(), "POST", """""", _prefix + """getProvinceBankList""")
)
                      

// @LINE:76
def getGroupBankList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Banks.getGroupBankList(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getGroupBankList", Seq(), "POST", """""", _prefix + """getGroupBankList""")
)
                      

// @LINE:89
def getDataBank(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.Banks.getDataBank(), HandlerDef(this, "kirimdoku.interfaces.Banks", "getDataBank", Seq(), "POST", """""", _prefix + """getdatabank""")
)
                      
    
}
                          

// @LINE:93
// @LINE:92
class ReverseCashInBatch {
    

// @LINE:93
def remit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashInBatch.remit(), HandlerDef(this, "kirimdoku.interfaces.CashInBatch", "remit", Seq(), "POST", """""", _prefix + """cashin/remitbatch""")
)
                      

// @LINE:92
def inquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   kirimdoku.interfaces.CashInBatch.inquiry(), HandlerDef(this, "kirimdoku.interfaces.CashInBatch", "inquiry", Seq(), "POST", """ CashIn Batch""", _prefix + """cashin/inquirybatch""")
)
                      
    
}
                          
}
                  

// @LINE:87
// @LINE:74
// @LINE:46
// @LINE:45
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:30
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:14
// @LINE:13
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:87
// @LINE:25
// @LINE:23
// @LINE:22
class ReverseTransaction {
    

// @LINE:25
def search(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.search(), HandlerDef(this, "controllers.Transaction", "search", Seq(), "GET", """""", _prefix + """transactions""")
)
                      

// @LINE:87
def callback(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.callback(), HandlerDef(this, "controllers.Transaction", "callback", Seq(), "POST", """""", _prefix + """callback""")
)
                      

// @LINE:23
def summary(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.summary(id), HandlerDef(this, "controllers.Transaction", "summary", Seq(classOf[String]), "POST", """""", _prefix + """transaction/$id<[^/]+>""")
)
                      

// @LINE:22
def show(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Transaction.show(id), HandlerDef(this, "controllers.Transaction", "show", Seq(classOf[String]), "GET", """ Transaction informations""", _prefix + """transaction/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:19
// @LINE:18
// @LINE:17
class ReverseCashOut {
    

// @LINE:18
def validate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CashOut.validate(), HandlerDef(this, "controllers.CashOut", "validate", Seq(), "POST", """""", _prefix + """cashout/validate""")
)
                      

// @LINE:19
def collect(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CashOut.collect(), HandlerDef(this, "controllers.CashOut", "collect", Seq(), "POST", """""", _prefix + """cashout/collect""")
)
                      

// @LINE:17
def inquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CashOut.inquiry(), HandlerDef(this, "controllers.CashOut", "inquiry", Seq(), "POST", """ Mechanism for CashOut""", _prefix + """cashout/inquiry""")
)
                      
    
}
                          

// @LINE:14
// @LINE:13
class ReverseCashIn {
    

// @LINE:14
def remit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CashIn.remit(), HandlerDef(this, "controllers.CashIn", "remit", Seq(), "POST", """""", _prefix + """cashin/remit""")
)
                      

// @LINE:13
def inquiry(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CashIn.inquiry(), HandlerDef(this, "controllers.CashIn", "inquiry", Seq(), "POST", """ Mechanism for CashIn""", _prefix + """cashin/inquiry""")
)
                      
    
}
                          

// @LINE:46
// @LINE:45
class ReverseTranslate {
    

// @LINE:46
def QueryTC(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Translate.QueryTC(), HandlerDef(this, "controllers.Translate", "QueryTC", Seq(), "POST", """""", _prefix + """translate/tcode""")
)
                      

// @LINE:45
def QueryPinyin(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Translate.QueryPinyin(), HandlerDef(this, "controllers.Translate", "QueryPinyin", Seq(), "POST", """netelis""", _prefix + """translate/pinyin""")
)
                      
    
}
                          

// @LINE:10
// @LINE:9
class ReverseNetwork {
    

// @LINE:9
def ping(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Network.ping(), HandlerDef(this, "controllers.Network", "ping", Seq(), "GET", """ Network utilities""", _prefix + """ping""")
)
                      

// @LINE:10
def pings(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Network.pings(), HandlerDef(this, "controllers.Network", "pings", Seq(), "POST", """""", _prefix + """ping""")
)
                      
    
}
                          

// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:6
class ReverseLab {
    

// @LINE:39
def test1(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.test1(), HandlerDef(this, "controllers.Lab", "test1", Seq(), "GET", """""", _prefix + """lab/1""")
)
                      

// @LINE:40
def test2(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.test2(), HandlerDef(this, "controllers.Lab", "test2", Seq(), "GET", """""", _prefix + """lab/2""")
)
                      

// @LINE:37
def suspiciousCheck(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.suspiciousCheck(id), HandlerDef(this, "controllers.Lab", "suspiciousCheck", Seq(classOf[String]), "GET", """""", _prefix + """lab/check/$id<[^/]+>""")
)
                      

// @LINE:36
def settlement(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.settlement(), HandlerDef(this, "controllers.Lab", "settlement", Seq(), "GET", """ Lab""", _prefix + """lab/settlement""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.index(), HandlerDef(this, "controllers.Lab", "index", Seq(), "GET", """ Default Page""", _prefix + """""")
)
                      

// @LINE:38
def hello(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.hello(), HandlerDef(this, "controllers.Lab", "hello", Seq(), "GET", """""", _prefix + """lab/hello""")
)
                      

// @LINE:42
def test4(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.test4(), HandlerDef(this, "controllers.Lab", "test4", Seq(), "GET", """""", _prefix + """lab/4""")
)
                      

// @LINE:41
def test3(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Lab.test3(), HandlerDef(this, "controllers.Lab", "test3", Seq(), "GET", """""", _prefix + """lab/3""")
)
                      
    
}
                          

// @LINE:30
class ReverseCustomer {
    

// @LINE:30
def show(id:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Customer.show(id), HandlerDef(this, "controllers.Customer", "show", Seq(classOf[String]), "GET", """ Customers
OPTIONS	/customers				controllers.Customer.options()
GET		/customers				controllers.Customer.index()""", _prefix + """customers/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:74
class ReverseLogo {
    

// @LINE:74
def index(code:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Logo.index(code), HandlerDef(this, "controllers.Logo", "index", Seq(classOf[String]), "GET", """""", _prefix + """logo/$code<[^/]+>""")
)
                      
    
}
                          
}
                  
      