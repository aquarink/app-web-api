package controllers;

import static play.data.Form.form;
import java.util.Date;
import java.util.List;
import java.util.Map;
import be.objectify.deadbolt.java.actions.And;
import be.objectify.deadbolt.java.actions.Restrictions;
import models.Bank;
import models.Channel;
import models.ChannelBank;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.channels.edit;
import views.html.channels.list;
import views.html.channels.summary;

@Security.Authenticated(SecuredMain.class)
@Restrictions({@And("admin"), @And("finance"), @And("admin_finance"), @And("admin_operasional")})
public class Channels extends Controller {

	public static Result GO_HOME = redirect(routes.Channels.list(0, "name", "asc", ""));
	
	public static Result list(int page, String sortBy, String order, String filter) {
		return ok(list.render(models.Channel.page(page, 10, sortBy, order, filter), sortBy, order, filter));
	}
	
	public static Result summary(String code) {
		models.Channel channel = models.Channel.find.where().eq("code", code).findUnique();
		return ok(summary.render(channel));
	}
	
	public static Result edit(String code) {
		try {
			models.Channel channel = models.Channel.find.byId(code);
			Form<models.Channel> form = form(models.Channel.class).fill(channel);
			List<Bank> banks = models.Bank.find.where().eq("countryCode", "ID").orderBy().asc("name").findList();
			Map<String, String> map = ChannelBank.getChannelBankByChannelCode(code);
			return ok(edit.render(code, form, banks, map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_HOME;
	}
	
	public static Result update(String code) {
		try {
			Form<ChannelBankForm> form = form(ChannelBankForm.class).bindFromRequest();
			System.out.println("DATA : "+form.data());
			if (form.hasErrors()) {
				Logger.error(form.errorsAsJson().toString());
				return GO_HOME;
			}
			Channel channel = Channel.find.byId(code);
			ChannelBank.disableLastChannel(channel.code);
			Date createdTime = new Date();
			for (String bankId : form.get().bankId) {
				try {
					Bank bank = Bank.find.byId(bankId);
					ChannelBank channelBank = new ChannelBank();
					channelBank.id = ChannelBank.getSeq();
					channelBank.channel = channel;
					channelBank.bank = bank;
					channelBank.createdTime = createdTime;
					channelBank.status = true;
					channelBank.save();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			flash("success", "Channel " + form.get().channelCode + " has been updated");
		} catch (Exception e) {
			e.printStackTrace();
			flash("failed", "Failed update channel");
		}

		return GO_HOME;
	}
	
	public static Result delete(String code) {
		try {
			models.Channel channel = models.Channel.find.byId(code);
			if (channel == null) {
				Logger.debug("Invalid channel code");
				return badRequest("Invalid channel code");
			}
			channel.delete();
			flash("success", "Channel " + code + " has been deleted");
		} catch (Exception e) {
			e.printStackTrace();
			flash("failed", "Failed delete channel");
		}
		return GO_HOME;
	}
	
	public static class ChannelBankForm {
		@Constraints.Required
		public String channelCode;
		
		public List<String> bankId;
	}
}
