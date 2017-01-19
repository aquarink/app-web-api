import play.*;
import play.libs.*;
import models.*;
import play.Application;
import play.Logger;

import controllers.helpers.EncryptionHelper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.List;

import javax.persistence.PersistenceException;

import com.avaje.ebean.Ebean;
import com.avaje.ebeaninternal.server.text.csv.CsvUtilReader;

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

//        if (City.find.findRowCount() == 0) {
//            Ebean.save(all.get("cities"));
//        }

        if (Channel.find.findRowCount() == 0) {
            Ebean.save(all.get("channels"));
        }

        if (Corporate.find.findRowCount() == 0) {
            Ebean.save(all.get("corporates"));
        }
        if (Label.find.findRowCount() == 0) {
            Ebean.save(all.get("labels"));
        }
        
        if (Forex.find.findRowCount() == 0) {
            Ebean.save(all.get("forex"));
        }
        
        if (ForexReference.find.findRowCount() == 0) {
            Ebean.save(all.get("forex_references"));
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

        
        insertCSVFiles();

    }

    private static void insertCSVFiles() {
    	try {
    		Logger.info("CSV Loader");
    		if((models.Country.find.findRowCount() == 0) || true) {
				CsvUtilReader reader = new CsvUtilReader(new FileReader(Play.application().path()+"/conf/csv/countries_currencies.csv"), ',',  '"', 1);
				Logger.info("REader "+reader);
				String[] c;
				while( (c = reader.readNext()) != null) {
					models.Currency dc = models.Currency.find.byId(c[2]);
					if (dc == null) {
						dc = new models.Currency();
						dc.code = c[2];
						dc.save();
					}
					
					models.Country b = new models.Country();
					b.code = c[0];
					b.name = c[1];
					b.currency = dc;
					try {
						b.save();
					} catch(Exception e) {}
					
				}
    		}
    		
    		
    		if(models.City.find.findRowCount() == 0 || true) {
				CsvUtilReader reader = new CsvUtilReader(new FileReader(Play.application().path()+"/conf/csv/cities.csv"), ',',  '"', 1);
				String[] c;
				while( (c = reader.readNext()) != null) {
					models.City b = new models.City();
					b.country = models.Country.find.byId(c[0]);
					b.name = c[1];
					try {
						b.save();
					} catch(Exception e) {}
				}
    		}
			
    		
    		if(models.Bank.find.findRowCount() == 0) {
				CsvUtilReader reader = new CsvUtilReader(new FileReader(Play.application().path()+"/conf/csv/banks.csv"), ',',  '"', 0);
				String[] c;
				while( (c = reader.readNext()) != null) {
					models.Bank b = new models.Bank();
					b.id = c[0];
					b.code = c[1];
					b.name = c[2];
					b.countryCode = c[3];
					try {
						b.save();
					} catch(Exception e) {}
				}
    		}
			
    		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

