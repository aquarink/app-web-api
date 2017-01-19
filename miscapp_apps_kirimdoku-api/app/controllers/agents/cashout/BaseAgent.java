package controllers.agents.cashout;


import java.util.Date;
import java.util.concurrent.Callable;

import models.Transaction;
import models.TransactionInquiry;
import models.UserRequest;

import org.codehaus.jackson.node.ObjectNode;

import controllers.CashOut.CollectForm;
import controllers.CashOut.InquiryForm;
import controllers.CashOut.ValidateForm;
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

	public Promise<Result> validate(final Request request, final ValidateForm form, final Transaction transaction) {
		return Akka.future(new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				return onValidate(request, form, transaction);
			}
		});
	}

	public Promise<Result> collect(final Request request, final CollectForm form, final Transaction transaction) {

		return Akka.future(new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				return onCollect(request, form, transaction);
			}
		});
	}

	public abstract Result onPing(Request request);

	public abstract Result onInquiry(Request request, InquiryForm form);
	
	public abstract Result onValidate(Request request, ValidateForm form, Transaction transaction);

	public abstract Result onCollect(Request request, CollectForm form, Transaction transaction);
}