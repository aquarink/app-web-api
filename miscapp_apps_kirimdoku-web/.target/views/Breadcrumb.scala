package views

import play.api.Logger

object Breadcrumb {
  val _mapBreadcrumb = Map(
    "/" -> "Home",
    "/dashboard" -> "Dashboard",
    "/account" -> "Account",
    "/transaction" -> "Transactions management",
    "/send" -> "Send money",
    "/send/confirm" -> "Send money>Confirmation Page",
    "/sendBillPayment" -> "Bill Payment",
    "/send/confirmBillPayment" -> "Bill Payment>Bill Payment Confirmation Page",
    "/receive" -> "Receive money",
    "/transaction/refund" -> "Transaction refund",
    "/transaction/change" -> "Transaction change",
    "/transaction/unlock" -> "Transaction unlock",
    "/report" -> "Report",
    "/help" -> "Help",
    "/tools/checkRate" -> "Check rate & fee",
    "/customer" -> "Customers management",
    "/customer/create" -> "Customers management>Add customer",
    "/user/agent/list" -> "Operators management",
    "/user/agent/create" -> "Operators management>Add agent",
    "/user/agent/edit" -> "Operators management>Edit agent",
    "/documentation" -> "Help",
    "/feedback" -> "Help>Feedback",
    "/news/list" -> "News management",
    "/admin/dashboard" -> "Admin dashboard",
    "/admin/corporates" -> "Corporates management",
    "/admin/corporates/new" -> "Corporates management>Add new corporate",
    "/admin/forex" -> "Exchange rate",
    "/admin/audit" -> "Audit",
    "/admin/customerbans" -> "Banned list",
    "/admin/customerbans/interdictions" -> "Interdictions list",
    "/admin/suspicious" -> "Suspicious list"
  )

  val _mapLink = Map(
    "Home" -> "/",
    "Dashboard" -> "/dashboard",
    "Account" -> "/account",
    "Transactions management" -> "/transaction",
    "Send money" -> "/send",
    "Confirmation Page" -> "/send/confirm",
    "Receive money" -> "/receive",
    "Bill Payment" -> "/sendBillPayment",
    "Bill Payment Confirmation Page" -> "/send/confirmBillPayment",
    "Transaction refund" -> "/transaction/refund",
    "Transaction change" -> "/transaction/change",
    "Transaction unlock" -> "/transaction/unlock",
    "Report" -> "/report",
    "Help" -> "Help",
    "Customers management" -> "/customer",
    "Add customer" -> "/customer/create",
    "Operators management" -> "/user/agent/list",
    "Add agent" -> "/user/agent/create",
    "Edit agent" -> "/user/agent/edit",
    "Check rate & fee" -> "/tools/checkRate",
    "Help" -> "/documentation",
    "Feedback" -> "/feedback",
    "News management" -> "/news/list",
    "Admin dashboard" -> "/admin/dashboard",
    "Corporates management" -> "/admin/corporates",
    "Add new corporate" -> "/admin/corporates/new",
    "Exchange rate" -> "/admin/forex",
    "Audit" -> "/admin/audit",
    "Banned list" -> "/admin/customerbans",
    "Interdictions list" -> "/admin/customerbans/interdictions",
    "Suspicious list" -> "/admin/suspicious"
  )

  val _mapBreadLang = Map(
    "Home" -> "LANG024", 
    "Dashboard" -> "LANG024", 
    "Account" -> "LANG022", 
    "Transactions management" -> "LANG015", 
    "Send money" -> "LANG009", 
    "Confirmation Page" -> "LANG071", 
    "Receive money" -> "LANG182", 
    "Bill Payment" -> "LANG010", 
    "Bill Payment Confirmation Page" -> "LANG222", 
    "Transaction refund" -> "LANG012", 
    "Transaction change" -> "LANG013", 
    "Transaction unlock" -> "LANG014", 
    "Report" -> "LANG017", 
    "Help" -> "LANG018", 
    "Check rate & fee" -> "LANG011", 
    "Customers management" -> "LANG016", 
    "Add customer" -> "LANG183", 
    "Operators management" -> "LANG184", 
    "Add agent" -> "LANG185", 
    "Edit agent" -> "LANG186", 
    "Help" -> "LANG018", 
    "Feedback" -> "LANG020", 
    "News management" -> "LANG188", 
    "Admin dashboard" -> "LANG189", 
    "Corporates management" -> "LANG190", 
    "Add new corporate" -> "LANG191", 
    "Exchange rate" -> "LANG045", 
    "Audit" -> "LANG192", 
    "Banned list" -> "LANG193", 
    "Interdictions list" -> "LANG194", 
    "Suspicious list" -> "LANG195"
  )

  def parse(uri: String):String = {
    if(_mapBreadcrumb contains uri ){
      val array = _mapBreadcrumb(uri).split(">")
      var result:String = "<ul class='breadcrumb'>"
      for(i <- 0 until array.length) {
        if (i == array.length) result += "<li class='active'>"
        else result += "<li>"

        val label = array(i).trim
        var link = _mapLink(label)
        var lang = _mapBreadLang(label)
        var labelLang = doku.kirimdoku.util.MultiLanguage.getLanguage(lang,label)
        if (i == (array.length-1)) {
        	result += labelLang
        } else {
        	result += "<a href='"+link+"'>"+labelLang+"</a>"
        }

        if(i != array.length - 1) result += "<span class='divider'>></span>"
        result += "</li>"
      }
      result += "</ul>"
      result
    }else{
      "<ul class='breadcrumb'><li> </li></ul>"
    }
  }

  def build(links:Seq[(String, String)]):String = {
    var result:String = "<ul class='breadcrumb'>"

    var firstLink:Boolean = true
    for((key, value) <- links) {
      result += "<li>"
      if(!firstLink) result += "<span class='divider'>></span>"
      if(key != null && key.length()>0) {
        result += "<a href='"+key+"'>"+value+"</a>"
      } else {
        result += value
      }
      result += "</li>"
      firstLink = false
    }
    result += "</ul>"

    result
  }
}
