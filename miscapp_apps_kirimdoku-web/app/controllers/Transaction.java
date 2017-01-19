package controllers;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import models.Bank;
import models.Batch;
import models.Channel;
import models.Customer;
import models.Label;
import models.TransactionInquiry;
import models.TransactionLog;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.Page;

import controllers.helpers.ApiHelper;
import controllers.helpers.ApiHelper.ApiException;
import controllers.helpers.AuditLogHelper;
import controllers.helpers.SessionHelper;
import controllers.tokens.TransactionToken;
import be.objectify.deadbolt.java.actions.Restrictions;
import be.objectify.deadbolt.java.actions.And;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import static play.data.Form.form;
import views.html.transaction.*;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("mainagent"), @And("supervisor"), @And("operator"), @And("finance"), @And("admin_operasional")})
public class Transaction extends Controller {
	
    public static Result list(int page, String sortBy, String order, String filterTransId) {
    	Form<Report.FilterForm> filterForm = form(Report.FilterForm.class).bindFromRequest();
    	Logger.debug("Request transaction list : "+filterForm.data());
        Page<models.Transaction> results = models.Transaction.reportPage(SessionHelper.getUser(), page, 25, sortBy, order, filterForm.get());
        return ok(
                list.render(
                		filterForm,
                		results,
                        sortBy, order
                )
        );
    }
    
    public static Result show(String idToken) {
		Logger.debug("Request transaction : "+idToken);
		try {
			Map<String, String> mapChannel = Channel.getChannelRouteToTpg();
			models.Transaction transaction = models.Transaction.findByIdToken(idToken);
			Logger.debug("Transaction channel code : "+transaction.channel.code);
			if (mapChannel.containsKey(transaction.channel.code)) {
				if (transaction.remitResponseCode == null || transaction.remitResponseCode.equals("")) {
					Logger.debug("Get Transaction log : "+transaction.id);
					TransactionLog transactionLog = TransactionLog.getLastTransactionLogByTransaction(transaction);
					if (transactionLog != null) {
						Logger.debug("Transaction Log : "+transactionLog.message);
						try {
							JsonNode resultJson = Json.parse(transactionLog.message);
							Logger.debug("Get ref status : "+resultJson.path("referenceStatus"));
							transaction.remitResponseCode = resultJson.path("referenceStatus") != null && !resultJson.path("referenceStatus").isMissingNode() ? resultJson.path("referenceStatus").getValueAsText() : (resultJson.path("status") != null && !resultJson.path("status").isMissingNode() ? resultJson.path("status").getValueAsText() : "-1" /* DEF STATUS_UNKNOWN */);
							transaction.remitResponseMessage = resultJson.path("referenceMessage") != null && !resultJson.path("referenceMessage").isMissingNode() ? resultJson.path("referenceMessage").getValueAsText() : (resultJson.path("statusMessage") != null && !resultJson.path("statusMessage").isMissingNode() ? resultJson.path("statusMessage").getValueAsText() : "Failed Transfer");
						} catch (java.lang.RuntimeException e) {
							Logger.debug("Failed parsing message json : "+e.getMessage());
							transaction.remitResponseCode = "-1" /* DEF STATUS_UNKNOWN */;
							transaction.remitResponseMessage = transactionLog.message;
						} catch (Exception e) {
							e.printStackTrace();
							Logger.debug("Failed read transaction log message : "+e.getMessage());
						}
						
					} else {
						transaction.remitResponseCode = "-1" /* DEF STATUS_UNKNOWN */;
						transaction.remitResponseMessage = "Failed Transfer";
					}
				}
				Logger.debug("RefStatus    : "+transaction.remitResponseCode);
				Logger.debug("RefMessage   : "+transaction.remitResponseMessage);
			}
	        return ok(
	                show.render(null, transaction)
	        );
		} catch (Exception e) {
			e.printStackTrace();
			return ok(
	                show.render(null, null)
	        );
		}
    }
    
    
    
    public static Result refund() {
    	Form<Transaction.FilterForm> filterForm = form(Transaction.FilterForm.class).bindFromRequest();
    	if(filterForm.get().transactionCode != null) {
    		return redirect(routes.Transaction.refundDetail(filterForm.get().transactionCode));
    	}
    	return ok(refund.render(filterForm));
    }
    
    public static Result refundDetail(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			Form<models.Transaction> transactionForm = form(models.Transaction.class).fill(transaction);
			return ok(refundDetail.render(null, transactionForm));
		} catch (ApiException e) {
			return ok(refundDetail.render(e.getMessage(), null));
		}
    }
    
    public static Result refundProcess(String idToken) {
		try {
			Form<models.Transaction> transactionForm = form(models.Transaction.class).bindFromRequest();
			models.Transaction transaction = models.Transaction.find.byId(TransactionToken.fromString(idToken).transactionId);
			if(transactionForm.apply("auth1").value().equals(transaction.auth1)) {
				//TODO 
				ObjectNode result = Json.newObject();
				if(transaction.status == models.Transaction.STATUS_PENDING) {
					transaction.status = models.Transaction.STATUS_REFUNDED;
					transaction.modifiedTime = new Date();
					transaction.update();
			    	result.put("status", 0);
			    	result.put("message", "Transaction has been successfully refunded");
					AuditLogHelper.log("transaction-refund", "Has just refunded transaction "+transaction.getIdToken(), Json.toJson(transaction), SessionHelper.getUser());
				} else if(transaction.status == models.Transaction.STATUS_PENDING_REFUND) {
					transaction.status = models.Transaction.STATUS_FULLREFUNDED;
					transaction.modifiedTime = new Date();
					transaction.update();
			    	result.put("status", 0);
			    	result.put("message", "Transaction has been successfully full refunded");
					AuditLogHelper.log("transaction-refund", "Has just full refunded transaction "+transaction.getIdToken(), Json.toJson(transaction), SessionHelper.getUser());
				} else {
			    	result.put("status", 16);
			    	result.put("message", "Transaction is not refundable");
				}
		    	return ok(result);
			} else {
		    	ObjectNode result = Json.newObject();
		    	result.put("status", 1);
		    	result.put("message", "PIN are invalid");
		    	return ok(result);
			}
		} catch (Exception e) {
			return badRequest(e.toString());
		}
	}



	public static Result refundReceipt(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			return ok(refundReceipt.render(null, transaction));
		} catch (ApiException e) {
			return ok(refundReceipt.render(e.getMessage(), null));
		}
    }
	
	//@Restrictions({@And("admin"), @And("finance")})
	 public static Result admin_list(int page, String sortBy, String order, String filterTransId) {
    	Form<Report.FilterForm> filterForm = form(Report.FilterForm.class).bindFromRequest();
        Page<models.Transaction> results = models.Transaction.reportPage(SessionHelper.getUser(), page, 25, sortBy, order, filterForm.get());
        return ok(
                admin_list.render(
                		filterForm,
                		results,
                        sortBy, order
                )
        );
    }
 
	@Restrictions({@And("admin")})
	public static Result admin_refund() {
    	Form<Transaction.FilterForm> filterForm = form(Transaction.FilterForm.class).bindFromRequest();
    	if(filterForm.get().transactionCode != null) {
    		return redirect(routes.Transaction.admin_refundDetail(filterForm.get().transactionCode));
    	}
    	return ok(admin_refund.render(filterForm));
    }
	
	@Restrictions({@And("admin")})
	public static Result admin_refundDetail(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			Form<models.Transaction> transactionForm = form(models.Transaction.class).fill(transaction);
			return ok(admin_refundDetail.render(null, transactionForm));
		} catch (ApiException e) {
			return ok(admin_refundDetail.render(e.getMessage(), null));
		}
    }
    
	@Restrictions({@And("admin")})
    public static Result admin_refundProcess(String idToken) {
		try {
			Form<models.Transaction> transactionForm = form(models.Transaction.class).bindFromRequest();
			models.Transaction transaction = models.Transaction.find.byId(TransactionToken.fromString(idToken).transactionId);
			
			ObjectNode result = Json.newObject();
			if(transaction.status == models.Transaction.STATUS_PENDING) {
				transaction.status = models.Transaction.STATUS_PENDING_REFUND;
				transaction.modifiedTime = new Date();
				transaction.update();
		    	result.put("status", 0);
		    	result.put("message", "Transaction has been authorized for full refund");
				AuditLogHelper.log("transaction-refund", "Has just authorized transaction for full refund "+transaction.getIdToken()+" with reason "+transactionForm.apply("refund_reason").value(), Json.toJson(transaction), SessionHelper.getUser());
			} else {
		    	result.put("status", 16);
		    	result.put("message", "Transaction is not refundable");
			}
	    	return ok(result);
		} catch (Exception e) {
			return badRequest(e.toString());
		}
	}
    
    public static Result change() {
    	Form<Transaction.FilterForm> filterForm = form(Transaction.FilterForm.class).bindFromRequest();
    	if(filterForm.get().transactionCode != null) {
    		return redirect(routes.Transaction.changeDetail(filterForm.get().transactionCode));
    	}
    	return ok(change.render(filterForm));
    }
    
    public static Result changeDetail(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			Form<models.Transaction> transactionForm = form(models.Transaction.class).fill(transaction);
			return ok(changeDetail.render(null, transactionForm));
		} catch (ApiException e) {
			return ok(changeDetail.render(e.getMessage(), null));
		}
    }
    
    public static Result changeProcess(String idToken) {
		try {
			Form<models.Transaction> transactionForm = form(models.Transaction.class).bindFromRequest();
			if(transactionForm.hasErrors()) {
				return badRequest(transactionForm.errors().toString());
			}
			models.Transaction transaction = models.Transaction.find.byId(TransactionToken.fromString(idToken).transactionId);
			if(transactionForm.apply("auth1").value().equals(transaction.auth1)) {
				models.Customer beneficiary = transactionForm.get().beneficiary;
				if (beneficiary.firstName.equalsIgnoreCase(beneficiary.lastName)) {
					beneficiary.lastName = "";
				}
				Customer oldBeneficiary = transaction.beneficiary;
				transaction.beneficiary = transactionForm.get().beneficiary.save(true, true);
				transaction.modifiedTime = new Date();
				
				transaction.update();
				
				if (transaction.labels == null) transaction.labels = new ArrayList<Label>();
				if (transaction.labels.contains(Label.find.byId(49L)) ) {
					ObjectNode result = Json.newObject();
					result.put("status", 1);
			    	result.put("message", "This transaction is unavailable to change anymore");
			    	return ok(result);
				}
				transaction.labels.add(Label.find.byId(49L));
				transaction.saveManyToManyAssociations("labels");
				
				AuditLogHelper.log("transaction-change", "Has just change transaction beneficiary "+transaction.beneficiary.idToken, Json.toJson(oldBeneficiary), SessionHelper.getUser());
		    	ObjectNode result = Json.newObject();
		    	result.put("status", 0);
		    	result.put("message", "Transaction has been successfully changed");
		    	return ok(result);
			} else {
		    	ObjectNode result = Json.newObject();
		    	result.put("status", 1);
		    	result.put("message", "PIN are invalid");
		    	return ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest(e.toString());
		}
    }
    
    public static Result changeReceipt(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			return ok(changeReceipt.render(null, transaction));
		} catch (ApiException e) {
			return ok(changeReceipt.render(e.getMessage(), null));
		}
    }
    
    public static Result unlock() {
    	Form<Transaction.FilterForm> filterForm = form(Transaction.FilterForm.class).bindFromRequest();
    	if(filterForm.get().transactionCode != null) {
    		return redirect(routes.Transaction.unlockDetail(filterForm.get().transactionCode));
    	}
    	return ok(unlock.render(filterForm));
    }
    
    public static Result unlockDetail(String idToken) {
		try {
			models.Transaction transaction = ApiHelper.getTransaction(idToken);
			Form<models.Transaction> transactionForm = form(models.Transaction.class).fill(transaction);
			return ok(unlockDetail.render(null, transactionForm));
		} catch (ApiException e) {
			return ok(unlockDetail.render(e.getMessage(), null));
		}
    }
    
    public static Result unlockProcess(String idToken) {
		try {
			Form<models.Transaction> transactionForm = form(models.Transaction.class).bindFromRequest();
			models.Transaction transaction = models.Transaction.find.byId(TransactionToken.fromString(idToken).transactionId);
			if(transaction.status == models.Transaction.STATUS_LOCKED) {
				if((transaction.auth1 != null) && (transaction.auth1.equals(transactionForm.apply("auth1").value()))) {
					if(transactionForm.apply("new_auth1").value().equals(transactionForm.apply("new_auth2").value())) {						
						transaction.status = models.Transaction.STATUS_PENDING;
						transaction.auth1 = transactionForm.apply("new_auth1").value();
						transaction.auth2 = transactionForm.apply("new_auth2").value();
						TransactionInquiry.resetInvalidAuthByTransaction(transaction);
						transaction.save();
						ObjectNode result = Json.newObject();
						result.put("status", 0);
						result.put("message", "Transaction has been successfully unlocked");
						AuditLogHelper.log("transaction-unlock", "Has just unlock transaction "+transaction.idToken, Json.toJson(transaction), SessionHelper.getUser());
						return ok(result);
					} else {
						ObjectNode result = Json.newObject();
				    	result.put("status", 1);
				    	result.put("message", "Confirmation new PIN isn't match ");
				    	return ok(result);
					}
				} else {
					ObjectNode result = Json.newObject();
			    	result.put("status", 1);
			    	result.put("message", "Unable to unlock transaction, please check previous PIN input correctly");
			    	return ok(result);
				}
			} else {
		    	ObjectNode result = Json.newObject();
		    	result.put("status", 1);
		    	result.put("message", "Transaction was not in LOCKED status");
		    	return ok(result);
			}
		} catch (Exception e) {
			return ok(unlockDetail.render(e.getMessage(), null));
		}
    }
    
	public static class FilterForm {
		public String transactionCode;
		public Long transactionId;
		
	    public final String validate() {
	    	if((this.transactionCode != null) && (!this.transactionCode.isEmpty())) {
	    		try {
	    			TransactionToken token = TransactionToken.fromString(transactionCode);
		    		if (token != null) {
		    			this.transactionId = token.transactionId;
		    		}
	    		} catch(Exception e) {
	    		} finally {
	    			if (this.transactionId == null) {
	    				this.transactionId = 0L;
	    			}
	    		}
	    	}
	    	return null;
	    }
	}
	
	public static Result listBatchInquiry(int page, String sortBy, String order, String filter) {
		Logger.debug("LIST BATCH INQUIRY");
		try {
			Logger.debug("Request param page : "+page);
			Logger.debug("Request param sortBy : "+sortBy);
			Logger.debug("Request param order : "+order);
			Logger.debug("Request param filter : "+filter);
			
	        Page<models.Batch> results = models.Batch.reportPageBatch(SessionHelper.getUser(), page, 10, sortBy, order, filter);
	        return ok(
	                listBatch.render(
	                		filter,
	                		results,
	                        sortBy, order
	                )
	        );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirect(routes.BatchUpload.viewUpload());
    }
	
	public static Result showBatch(int batchId) {
		Logger.debug("SHOW BATCH");
    	try {
			Logger.debug("Request param batchId : "+batchId);
			if (batchId > 0) {
				Batch batch = Batch.find.byId(batchId);
				String description = "";
				if (batch.description != "" && !batch.description.equals("")) {
					String[] strings = batch.description.split("##");
					for (int i = 0; i < strings.length; i++) {
						description += strings[i]+"\n \n";
					}
				}
				return ok(description);
			} else {
				return ok("Batch not found");
			}
		} catch (Exception e) {
			Logger.debug("Filed get batch : "+e.getMessage());
		}
    	return ok("Batch not found");
    }
	
	public static Result showBatchDetail(int batchId, int p, String filter, String page) {
		Logger.debug("SHOW BATCH DETAIL");
		try {
			Logger.debug("Request param batchId : "+batchId);
			Logger.debug("Request param page : "+p);
			Logger.debug("Request param filter : "+filter);
			String token = UUID.randomUUID().toString();
			SessionHelper.putToken(token);
			Batch batch = Batch.find.byId(batchId);
			String stateRemit = batch != null ? batch.state+"" : "";
			Map<String, String> bankId = Bank.optionsBankId();
	        Page<models.TransactionInquiry> results = models.TransactionInquiry.reportPageBatch(SessionHelper.getUser(), batchId, p, 50000, filter);
	        return ok(
	                listBatchDetail.render(
	                		batchId,
	                		filter,
	                		results,
	                		token,
	                		stateRemit,
	                		bankId,
	                		page
	                		)
	        );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirect(routes.BatchUpload.viewUpload());
    }
	
    public static Result showBatchDetailTransaction(int page, String sortBy, String order, String filter, int batchId) {
    	Page<models.Transaction> results = null;
    	Form<Report.FilterForm> filterForm = form(Report.FilterForm.class).bindFromRequest();
    	try {
			Logger.debug("Request param batchId : "+batchId);
			Logger.debug("Request param page : "+page);
			Logger.debug("Request param filter : "+filter);
	    	Logger.debug("Request transaction list : "+filterForm.data());
	        results = models.Transaction.reportPageBatchTransaction(SessionHelper.getUser(), page, 50000, sortBy, order, filterForm.get(), batchId);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ok(
                listBatchTransaction.render(
                		filterForm,
                		results,
                        sortBy, 
                        order,
                        batchId
                )
        );
    }
	
	public static Result receipt(String idToken, String tipe) {
		try {
			models.Transaction transaction = models.Transaction.findByIdToken(idToken);
			transaction.senderAmount.setScale(6, RoundingMode.FLOOR);
			transaction.beneficiaryAmount.setScale(6, RoundingMode.FLOOR);
			Logger.debug("Batch Id : "+transaction.batch);
			int batchId = transaction.batch != null ? transaction.batch.id : 0;
			return ok(printTransaction.render(null, transaction, batchId, tipe.toLowerCase()));
		} catch (Exception e) {
			return ok(printTransaction.render(e.getMessage(), null, 0, "t"));
		}
	}
    
}
