package controllers;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import controllers.helpers.ApiHelper;
import controllers.helpers.ApiHelper.ApiException;
import controllers.tokens.TransactionToken;
import models.Customer;
import org.apache.commons.mail.EmailException;
import org.codehaus.jackson.JsonNode;
import play.*;
import play.libs.Json;
import play.mvc.*;
import play.api.templates.Html;
import play.data.*;
import static play.data.Form.*;
import views.html.receive.create;
import views.html.receive.inquiry;
import views.html.receive.verify;
import views.html.receive.receipt;
import views.html.receive.receipt_print;
import views.html.receive.receipt_email;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("finance"), @And("mainagent"), @And("supervisor"), @And("operator")})
public class Receive extends Controller {

    public static Result create() {
        Form<Receive.FilterForm> filterForm = form(Receive.FilterForm.class).bindFromRequest();

        if(filterForm.get().idToken != null) {
    		return redirect(routes.Receive.inquiry(filterForm.get().idToken));
    	}

        return ok(create.render(filterForm));
    }


    public static Result inquiry(String idToken) {
		try {
			JsonNode result = ApiHelper.CashOut.inquiry(idToken);
			models.TransactionInquiry transactionInquiry = Json.fromJson(result.path("inquiry"), models.TransactionInquiry.class);
			Form<models.TransactionInquiry> transactionForm = form(models.TransactionInquiry.class).fill(transactionInquiry);
			
			System.out.println("amount to receive : "+transactionForm.get().transaction.beneficiaryAmount.setScale(6));
			
			return ok(inquiry.render(null, transactionForm));
		} catch (ApiException e) {
			return ok(inquiry.render(e.getMessage(), null));
		}
    }

    public static Result verify(String idToken) {
        Form<models.TransactionInquiry> trxForm = form(models.TransactionInquiry.class).bindFromRequest();
        
		if (true) {
			models.Customer beneficiary = models.Customer.findByToken(trxForm.get().transaction.beneficiary.idToken);
			if(beneficiary != null) {
				models.CustomerBan ban1 = models.CustomerBan.findByCustomer(beneficiary);
				if(ban1 != null) {
					return ok(verify.render("error", "Sender customer "+ban1.customer.fullName()+" is in banned list, please contact your supervisor"));
				}
			}
			if(beneficiary.getStatistic(3).hasExceedBeneficiaryLimit(trxForm.get().transaction.beneficiaryAmount)) {
				return ok(verify.render("error", "Receiver customer "+beneficiary.fullName()+" has exceeded its customer limit"));
			}
			
			models.CustomerBan ban2 = models.CustomerBan.findPositiveMatch(trxForm.get().transaction.beneficiary);
			if(ban2 != null) {
				return ok(verify.render("error", "Receiver with a name "+ban2.firstName+" "+ban2.lastName+" is an positive match, please contact your supervisor"));
			}
		}
//		if(trxForm.get().transaction.beneficiary.personalIdIssueDate.after(trxForm.get().transaction.beneficiary.personalIdExpireDate)) {
//			return ok(verify.render("error", "Receiver ID issue issue/expired date shall be correct (within justified range and expired date shall no less date than issue date)"));
//		}
    	return ok();
    }
    
    public static Result validate(String id) {
    	try { Thread.sleep(1000);}catch(InterruptedException e) {}
        Form<models.TransactionInquiry> confirmationForm = form(models.TransactionInquiry.class).bindFromRequest();
        
        if (confirmationForm.hasErrors()) {
            Logger.debug("Vallidation form has error "+confirmationForm.errorsAsJson());
            return badRequest(confirmationForm.errors().toString());
        }
        
        try {
	        JsonNode valResult = ApiHelper.CashOut.validate(id, confirmationForm.get().idToken, confirmationForm.apply("auth1").value());
	        Logger.debug("Validate result "+valResult);
	        return ok(valResult);
        } catch(ApiException e) {
        	return badRequest(e.getMessage());
        }
    }
    
    
    public static Result process(String id) {
        Form<models.TransactionInquiry> confirmationForm = form(models.TransactionInquiry.class).bindFromRequest();

        if (confirmationForm.hasErrors()) {
            Logger.debug("Has error "+confirmationForm.errorsAsJson());
            return badRequest(confirmationForm.errors().toString());
        }

        try {
        	if(!confirmationForm.get().transaction.beneficiary.idToken.isEmpty()) {
	        	Customer beneficiary = models.Customer.findByToken(confirmationForm.get().transaction.beneficiary.idToken);
	        	if(beneficiary != null) {
		        	beneficiary.personalIdType = confirmationForm.get().transaction.beneficiary.personalIdType;
		        	beneficiary.personalId = confirmationForm.get().transaction.beneficiary.personalId;
		        	beneficiary.personalIdIssueDate = confirmationForm.get().transaction.beneficiary.personalIdIssueDate;
		        	beneficiary.personalIdExpireDate = confirmationForm.get().transaction.beneficiary.personalIdExpireDate;
		        	if (confirmationForm.get().transaction.beneficiary.personalIdCountry != null && !confirmationForm.get().transaction.beneficiary.personalIdCountry.code.equals("")) {
		        		beneficiary.personalIdCountry = confirmationForm.get().transaction.beneficiary.personalIdCountry;
		        	}
		        	beneficiary.occupation = confirmationForm.get().transaction.beneficiary.occupation;
		        	beneficiary.cityName = confirmationForm.get().transaction.beneficiary.cityName;
		        	beneficiary.birthDate = confirmationForm.get().transaction.beneficiary.birthDate;
		        	Logger.debug("Beneficiary id "+beneficiary.id+" "+beneficiary.personalId);
		        	beneficiary.update();
	        	} else {
	        		confirmationForm.get().transaction.beneficiary.save();
	        	}
        	}
	        JsonNode result = ApiHelper.CashOut.collect("NO"+id, confirmationForm.get().idToken, confirmationForm.get().validationId, confirmationForm.apply("auth1").value(), confirmationForm.apply("receiveTrxId").value());
	        Logger.debug("Cashout collect result "+result);
	        return ok(result);
//	        if(result.path("status").getIntValue() == 0) {
//	//	        return redirect(routes.Receive.sendTransactionViaEmail(id));
//		        return redirect(routes.Receive.receipt(id));
//	        } else {
//	        	return badRequest(result.path("message").getTextValue());
//	        }
        } catch(ApiException e) {
        	return badRequest(e.getMessage());
        }
    }

    public static Result receipt(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			return ok(receipt.render(null, transaction));
		} catch (ApiException e) {
			return ok(receipt.render(e.getMessage(), null));
		}
    }

    public static Result receiptPrint(String id) {
        JsonNode result = ApiHelper.transactionDetail(id);
        return ok(receipt_print.render(result));
    }

    public static Result receiptEmail(String id) {
		JsonNode result = ApiHelper.transactionDetail(id);
        return ok(receipt_email.render(result, result.path("transaction").path("beneficiary")));
    }
    
	public static Result sendTransactionViaEmail(String id) {
		try {
			JsonNode result = ApiHelper.transactionDetail(id);
			
			if(result.path("status").getIntValue() == 0) {
				JsonNode sender = result.path("transaction").path("sender");
				if (sender.has("email")) {
					InternetAddress[] tos = InternetAddress.parse(sender.path("email").getTextValue());
					if ((tos.length>0)) {
						Html html = receipt_email.render(result, sender);
						ApiHelper.sendHtmlEmail(null, tos[0], "Bukti transaksi penerimaan uang", html);
						flash("success", "Transaction email has been sent to sender email");
					}
				}
				JsonNode beneficiary = result.path("transaction").path("beneficiary");
				if(beneficiary.has("email")) {
					InternetAddress[] tos = InternetAddress.parse(beneficiary.path("email").getTextValue());
					if ((tos.length>0)) {
						Html html = receipt_email.render(result, beneficiary);
						ApiHelper.sendHtmlEmail(null, tos[0], "Bukti transaksi penerimaan uang", html);
						flash("success", "Transaction email has been sent to receiver email");
					}
				}
			} else {
				flash("error", "Couldn't send any email to destined transaction");
			}
		} catch (EmailException e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			flash("error", "Unable to send email: "+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			flash("error", "Unable to send email: "+e.getMessage());
		} catch (AddressException e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
			flash("error", "Unable to send email: "+e.getMessage());
		}
		
		return redirect(routes.Receive.receipt(id));
	}

	public static class FilterForm {
		public String idToken;
		public Long id;
		
	    public final String validate() {
	    	if((this.idToken != null) && (!this.idToken.isEmpty())) {
	    		try {
	    			TransactionToken token = TransactionToken.fromString(idToken);
		    		if (token != null) {
		    			this.id = token.transactionId;
		    		}
	    		} catch(Exception e) {
	    		} finally {
	    			if (this.id == null) {
	    				this.id = 0L;
	    			}
	    		}
	    	}
	    	return null;
	    }
	}
}
