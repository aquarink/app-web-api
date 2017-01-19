import play.libs.*;
import models.*;
import play.Application;
import play.Logger;
import com.avaje.ebean.*;
import controllers.helpers.EncryptionHelper;
import java.util.Map;
import java.util.List;

public class GlobalInitialData {

    public static void insert(Application app) {
        Logger.debug("Initial Data insert ");

        Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

        if ((Currency.find.findRowCount() == 0)) {
            Ebean.save(all.get("currencies"));
        }

        if (Country.find.findRowCount() == 0) {
            Ebean.save(all.get("countries"));
        }

        if (City.find.findRowCount() == 0) {
            Ebean.save(all.get("cities"));
        }

        if (ForexSetting.find.findRowCount() == 0) {
        	Ebean.save(all.get("forex_settings"));
        }

        if (Forex.find.findRowCount() == 0) {
            Ebean.save(all.get("forex"));
        }
        
        if (ForexReference.find.findRowCount() == 0) {
            Ebean.save(all.get("forex_references"));
        }

        if (Channel.find.findRowCount() == 0) {
            Ebean.save(all.get("channels"));
        }

        if (Corporate.find.findRowCount() == 0) {
            Ebean.save(all.get("corporates"));
        }

        if (SecurityRole.find.findRowCount() == 0) {
            Ebean.save(all.get("security_roles"));
        }

        if (User.find.findRowCount() == 0) {
            for(Object obj: all.get("users")) {
            	models.User user = (models.User) obj;
            	try {
					user.password = EncryptionHelper.encrypt(user.password);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	Ebean.save(user);
                Ebean.saveManyToManyAssociations(user, "roles");
            }
        }

        if (Customer.find.findRowCount() == 0) {
            Ebean.save(all.get("customers"));
        }
        
        if (Fee.find.findRowCount() == 0) {
            Ebean.save(all.get("fees"));
        }

        if (News.find.findRowCount() == 0) {
            Ebean.save(all.get("news"));
        }

        if (models.Transaction.find.findRowCount() == 0) {
            initDummyTransactions();
        }


    }

    private static void initDummyTransactions() {
        // Create 100 dummy transactions
        for (int i=0; i<10; i++) {
			// Transaction trx = new Transaction();
			// trx.createdTime = new Date();
			// trx.corporate = Corporate
			// trx.user = form.get().user;
			// trx.channel = form.get().channel;
			// trx.sender = form.get().sender;
			// trx.senderCountry = form.get().senderCountry;
			// trx.senderCurrency = form.get().senderCurrency;
			// trx.senderAmount = form.get().senderAmount;
			// trx.beneficiary = Customer.find.byId(new Random().nextInt(1)+1)
			// trx.beneficiaryCountry = Country.
			// trx.beneficiaryCurrency = form.get().beneficiaryCurrency;
			// trx.beneficiaryAmount = form.get().beneficiaryAmount;
			// trx.forexReference = form.get().forexRef;
			// trx.beneficiaryCity = form.get().beneficiaryCity;
			// trx.feeIncluded = form.get().feeIncluded;
			// trx.save();
        }
    }
}

