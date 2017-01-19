package controllers.agents.cashin;


import java.util.Date;
import java.util.concurrent.Callable;

import models.Transaction;
import models.TransactionInquiry;
import models.UserRequest;

import org.codehaus.jackson.node.ObjectNode;

import controllers.CashIn.InquiryForm;
import controllers.CashIn.RemitForm;
import controllers.tokens.TransactionToken;

import play.Logger;
import play.libs.Akka;
import play.libs.F.Promise;
import play.mvc.Http.Request;
import play.mvc.Result;

public abstract class BaseAgent {

	public Promise<Result> ping(final Request request) {
		return Akka.future(new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				return onPing(request);
			}
		});
	};

	public Promise<Result> inquiry(final Request request, final InquiryForm form) {
		return Akka.future(new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				return onInquiry(request, form);
			}
		});
	}

	public Promise<Result> remit(final Request request, final RemitForm form) {
		return Akka.future(new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				return onRemit(request, form);
			}
		});
	}

	public abstract Result onPing(Request request);

	public abstract Result onInquiry(Request request, InquiryForm form);
	
	public abstract Result onRemit(Request request, RemitForm form);
}
