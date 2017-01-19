package kirimdoku.helpers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import models.Channel;
import models.Corporate;
import models.Country;
import models.Currency;
import models.Customer;
import models.CustomerAccount;
import models.ForexReference;
import models.TransactionFee;
import models.User;
import play.data.validation.Constraints;

public class TransactionRemit {

    public Long id;
    public String idToken;
    public Corporate corporate;
    public User agent;

    @Constraints.Required
    public Channel channel;

    public Date createdTime;
    public Date modifiedTime;

    @Constraints.Required
    public int status;
    public Date cashInTime;
    public Date cashOutTime;
    public Customer sender;

    @Constraints.Required
    public Country senderCountry;

    @Constraints.Required
    public Currency senderCurrency;
    public BigDecimal senderAmount;
    public Customer beneficiary;

    @Constraints.Required
    public Country beneficiaryCountry;

    @Constraints.Required
    public Currency beneficiaryCurrency;
    public BigDecimal beneficiaryAmount;
    public ForexReference forexReference;
    public String beneficiaryCity;
    public List<TransactionFee> fees;
    public String auth1;
    public String auth2;
    public String senderNote;
    public User beneficiaryAgent;
    public CustomerAccount beneficiaryAccount;
    public String sendTrxId;
    public String receiveTrxId;
    public String voucherCode;
    public String beneficiaryWalletId;
    public String beneficiaryWalletName;

}
