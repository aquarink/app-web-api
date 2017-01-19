package job;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Channel;
import models.ChannelBank;
import models.Country;
import models.Currency;
import models.ForexReference;
import models.TransactionInquiry;
import models.TransactionInquiry.TransactionInquiryState;
import play.Logger;
import au.com.bytecode.opencsv.CSVReader;
import controllers.helpers.BatchUploadDetailHelper;
import controllers.helpers.BatchUploadHelper;
import controllers.helpers.SessionHelper;
import doku.kirimdoku.util.CommonUtil;

public class ParserBatchUpload {
	
	public static final String INPUT_PATTERN = "^[a-zA-Z0-9.\\-\\/+,=_ ]*$";
	public static Map<String, String> countrySender = new HashMap<String, String>();
	public static Map<String, String> currencySender = new HashMap<String, String>();
	public static Map<String, String> countryBeneficiary = new HashMap<String, String>();
	public static Map<String, String> currencyBeneficiary = new HashMap<String, String>();
	public static Map<String, String> channelCodeList = new HashMap<String, String>();
	public static Map<String, String> countryCodeList = new HashMap<String, String>();
	public static Map<String, String> bankId = new HashMap<String, String>();
	public static Map<String, String> bankCode = new HashMap<String, String>();
	public static Map<String, String> bankIdCode = new HashMap<String, String>();
	public static Map<String, Map<String, String>> channelBankId = new HashMap<String, Map<String, String>>();
	public static Map<String, Map<String, String>> channelBankCode = new HashMap<String, Map<String, String>>();
	public static Map<String, Integer> sendTypeCode = new HashMap<String, Integer>();
	public static Map<String, ForexReference> forexReference = new HashMap<String, ForexReference>();
	public static Map<String, String> personalIdType = new HashMap<String, String>();
	
	public static void prepareParserData(BatchUploadHelper batchUploadHelper) {
		try {
			countrySender.put(SessionHelper.getUser().corporate.country.code, SessionHelper.getUser().corporate.country.code);
			currencySender.put(SessionHelper.getUser().corporate.currency.code, SessionHelper.getUser().corporate.currency.code);
			countryBeneficiary = Country.optionsBeneficiaryCountryBatchUpload(SessionHelper.getUser().corporate.country.code);
			currencyBeneficiary = Currency.options();
			channelCodeList = Channel.optionsUser();
			countryCodeList = Country.options();
			for (String channelCode : channelCodeList.keySet()) {
				bankId = new HashMap<String, String>();
				bankId = ChannelBank.getChannelBankByChannelCode(channelCode);
				channelBankId.put(channelCode, bankId);
				//bankCode = ChannelBank.bankSwiftCodeByChannelCode(channelCode);
				bankIdCode = ChannelBank.bankIdSwiftCodeByChannelCode(channelCode);
				channelBankCode.put(channelCode, bankCode);
			}
			
			for (String beneficiaryCountry : countryBeneficiary.keySet()) {
				ForexReference reference = ForexReference.findByCorporateCurrencyOriginDestination(SessionHelper.getUser().corporate.code, SessionHelper.getUser().corporate.currency.code, countryBeneficiary.get(beneficiaryCountry));
				forexReference.put(beneficiaryCountry, reference);
			}
			
			sendTypeCode.put("S", 1);
			sendTypeCode.put("B", 2);
			
			personalIdType = models.Customer.optionsPersonalIdType();
        	
			Logger.debug("Country Sender ["+countrySender+"]");
			Logger.debug("Currency Sender ["+currencySender+"]");
			Logger.debug("Country Beneficiary ["+countryBeneficiary+"]");
			Logger.debug("Currency Beneficiary ["+currencyBeneficiary+"]");
			Logger.debug("Channel Code List ["+channelCodeList+"]");
			Logger.debug("Country Code List ["+countryCodeList+"]");
			for (String channelCode : channelBankId.keySet()) {
				Logger.debug("Channel Bank List "+channelCode+" ["+channelBankId.get(channelCode)+"]");
			}
			for (String beneficiaryCountry : forexReference.keySet()) {
				Logger.debug("Forex Beneficiary Country "+beneficiaryCountry+" ["+forexReference.get(beneficiaryCountry)+"]");
			}
			Logger.debug("Send Type Code ["+sendTypeCode+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean doParsingUploadBatch(BatchUploadHelper batchUploadHelper) {
		boolean status = true;
		try {
			Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: START PARSING UPLOAD BATCH ::.");
			CSVReader csvReader = null;
            try {
            	Logger.debug("File >> "+batchUploadHelper.getFileUpload());
                csvReader = new CSVReader(new FileReader(batchUploadHelper.getFileUpload()), ',');
            } catch (Throwable e) {
                e.printStackTrace();
            }
            if (csvReader != null) {
            	String[] headerRow = csvReader.readNext();
            	String[] nextLine = null;
            	batchUploadHelper.setErrorDescription("");
            	BatchUploadDetailHelper detailHelper = null;
            	batchUploadHelper.setBatchUploadDetailHelpers(new ArrayList<BatchUploadDetailHelper>());
            	String errorDescription = "";
            	int rowCSV = 0;
            	int rowCSVFailed = 0;
            	int rowCSVSuccess = 0;
            	while ((nextLine = csvReader.readNext()) != null) {
            		String errorDataDescription = "";
            		boolean statusValidRow = true;
            		detailHelper = new BatchUploadDetailHelper();
            		Logger.debug("rowCSV "+rowCSV);
            		int caseSenderCID = 0;
            		int caseBeneficiaryCID = 0;
            		int caseChannelCode = 0;
            		int caseSendType = 0;
            		
            		/* TODO SENDER --------->>>> */
            		
            		//SENDER CID
            		try {
                        String senderCID = nextLine[0] != null ? nextLine[0].trim() : "";
                        if (!senderCID.equals("")) {
	                        if (!CommonUtil.validCustomerId(senderCID)) {
	                            Logger.debug("ERROR senderCID "+senderCID);
	                            errorDataDescription += "SenderCID ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderCID(senderCID);
	                        	caseSenderCID = 1;
	                        }
                        }
					} catch (Exception e) {
						Logger.debug("ERROR senderCID ");
						errorDataDescription += "SenderCID ";
						statusValidRow = false;
					}
            		
            		switch (caseSenderCID) {
					case 0:
						
						// SENDER FIRST NAME
	                    try {
	                        String senderFirstName = nextLine[1] != null ? nextLine[1].trim() : "";
	                        if (!CommonUtil.validName(senderFirstName)) {
	                            Logger.debug("ERROR senderFirstName " + senderFirstName);
	                            errorDataDescription += "SenderFirstName ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderFirstName(senderFirstName);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderFirstName ");
	                    	errorDataDescription += "SenderFirstName ";
							statusValidRow = false;
						}
	                    
	                    // SENDER LAST NAME
	                    try {
	                        String senderLastName = nextLine[2] != null ? nextLine[2].trim() : "";
	                        if (!CommonUtil.validName(senderLastName)) {
	                            Logger.debug("ERROR senderLastName " + senderLastName);
	                            errorDataDescription += "SenderLastName ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderLastName(senderLastName);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderLastName ");
	                    	errorDataDescription += "SenderLastName ";
							statusValidRow = false;
						}
	                    
	                    // SENDER PHONE NUMBER
	                    try {
	                        String senderPhoneNumber = nextLine[3] != null ? nextLine[3].trim() : "";
	                        if (!CommonUtil.validPhoneNumber(senderPhoneNumber)) {
	                            Logger.debug("ERROR senderPhoneNumber " + senderPhoneNumber);
	                            errorDataDescription += "SenderPhoneNumber ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderPhoneNumber(senderPhoneNumber);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderPhoneNumber ");
	                    	errorDataDescription += "SenderPhoneNumber ";
							statusValidRow = false;
						}
	                    
	                    // SENDER PERSONAL ID TYPE
	                    try {
	                        String senderPersonalIdType = nextLine[4] != null ? nextLine[4].trim() : "";
	                        if (!CommonUtil.validPersonalIdType(senderPersonalIdType, personalIdType)) {
	                            Logger.debug("ERROR senderPersonalIdType " + senderPersonalIdType);
	                            errorDataDescription += "SenderPersonalIdType ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderPersonalIdType(senderPersonalIdType);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderPersonalIdType ");
	                    	errorDataDescription += "SenderPersonalIdType ";
							statusValidRow = false;
						}
	                    
	                    // SENDER PERSONAL ID
	                    try {
	                        String senderPersonalId = nextLine[5] != null ? nextLine[5].trim() : "";
	                        if (!CommonUtil.validString(senderPersonalId, null)) {
	                            Logger.debug("ERROR senderPersonalId " + senderPersonalId);
	                            errorDataDescription += "SenderPersonalId ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderPersonalId(senderPersonalId);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderPersonalId ");
	                    	errorDataDescription += "SenderPersonalId ";
							statusValidRow = false;
						}
	                    
	                    // SENDER COUNTRY CODE
	                    try {
	                        String senderCountryCode = nextLine[6] != null ? nextLine[6].trim() : "";
	                        if (!CommonUtil.validCountryCode(senderCountryCode, countryCodeList)) {
	                            Logger.debug("ERROR senderCountryCode " + senderCountryCode);
	                            errorDataDescription += "SenderCountryCode ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderCountryCode(senderCountryCode);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderCountryCode ");
	                    	errorDataDescription += "SenderCountryCode ";
							statusValidRow = false;
						}
	                    
	                    // SENDER DATE OF BIRTH
	                    try {
	                        String senderDateOfBirth = nextLine[7] != null ? nextLine[7].trim() : "";
	                        if (!CommonUtil.validDate(senderDateOfBirth)) {
	                            Logger.debug("ERROR senderDateOfBirth " + senderDateOfBirth);
	                            errorDataDescription += "SenderDateOfBirth ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setSenderDateOfBirth(senderDateOfBirth);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR senderDateOfBirth ");
	                    	errorDataDescription += "SenderDateOfBirth ";
							statusValidRow = false;
						}
						
						
						break;
					default:
						// GET SENDER BY CUSTOMER ID
						Logger.debug("GET SENDER BY CUSTOMER ID");
						break;
					}
                    
                    
                    
                    /* TODO BENEFICIARY --------->>>> */
            		
            		//BENEFICIARY CID
                    try {
                        String beneficiaryCID = nextLine[8] != null ? nextLine[8].trim() : "";
                        if (!beneficiaryCID.equals("")) {
	                        if (!CommonUtil.validCustomerId(beneficiaryCID)) {
	                            Logger.debug("ERROR beneficiaryCID " + beneficiaryCID);
	                            errorDataDescription += "BeneficiaryCID ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setBeneficiaryCID(beneficiaryCID);
	                        	caseBeneficiaryCID = 1;
	                        }
                        }
                    } catch (Exception e) {
                    	Logger.debug("ERROR beneficiaryCID ");
                    	errorDataDescription += "BeneficiaryCID ";
						statusValidRow = false;
					}
                    
                    switch (caseBeneficiaryCID) {
					case 0:
						// BENEFICIARY FIRST NAME
	                    try {
	                        String beneficiaryFirstName = nextLine[9] != null ? nextLine[9].trim() : "";
	                        if (!CommonUtil.validName(beneficiaryFirstName)) {
	                            Logger.debug("ERROR beneficiaryFirstName " + beneficiaryFirstName);
	                            errorDataDescription += "BeneficiaryFirstName ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setBeneficiaryFirstName(beneficiaryFirstName);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR beneficiaryFirstName ");
	                    	errorDataDescription += "BeneficiaryFirstName ";
							statusValidRow = false;
						}
	                    
	                    // BENEFICIARY LAST NAME
	                    try {
	                        String beneficiaryLastName = nextLine[10] != null ? nextLine[10].trim() : "";
	                        if (!CommonUtil.validName(beneficiaryLastName)) {
	                            Logger.debug("ERROR beneficiaryLastName " + beneficiaryLastName);
	                            errorDataDescription += "BeneficiaryLastName ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setBeneficiaryLastName(beneficiaryLastName);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR beneficiaryLastName ");
	                    	errorDataDescription += "BeneficiaryLastName ";
							statusValidRow = false;
						}
	                    
	                    // BENEFICIARY PHONE NUMBER
	                    try {
	                        String beneficiaryPhoneNumber = nextLine[11] != null ? nextLine[11].trim() : "";
	                        if (!CommonUtil.validPhoneNumber(beneficiaryPhoneNumber)) {
	                            Logger.debug("ERROR beneficiaryPhoneNumber " + beneficiaryPhoneNumber);
	                            errorDataDescription += "BeneficiaryPhoneNumber ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setBeneficiaryPhoneNumber(beneficiaryPhoneNumber);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR beneficiaryPhoneNumber ");
	                    	errorDataDescription += "BeneficiaryPhoneNumber ";
							statusValidRow = false;
						}
	                    
	                    // BENEFICIARY COUNTRY CODE
//	                    try {
//	                        String beneficiaryCountryCode = nextLine[12] != null ? nextLine[12].trim() : "";
//	                        if (!CommonUtil.validCountryCode(beneficiaryCountryCode, countryCodeList)) {
//	                            Logger.debug("ERROR beneficiaryCountryCode " + beneficiaryCountryCode);
//	                            statusValidRow = false;
//	                        } else {
//	                        	detailHelper.setBeneficiaryCountryCode(beneficiaryCountryCode);
//	                        }
//	                    } catch (Exception e) {
//	                    	Logger.debug("ERROR beneficiaryCountryCode ");
//							statusValidRow = false;
//						}
	                    detailHelper.setBeneficiaryCountryCode("ID"); //REM-63
						break;
					default:
						// GET BENEFICIARY BY CUSTOMER ID
						Logger.debug("GET BENEFICIARY BY CUSTOMER ID");
						break;
					}
                    
                    
                    /* TODO SERVICE --------->>>> */
            		
            		//CHANNEL CODE
                    try {
                        String channelCode = nextLine[12] != null ? nextLine[12].trim() : "";
                        if (!CommonUtil.validChannelCode(channelCode, channelCodeList)) {
                            Logger.debug("ERROR channelCode " + channelCode);
                            errorDataDescription += "ChannelCode ";
                            statusValidRow = false;
                        } else {
                        	detailHelper.setChannelCode(channelCode);
                        	caseChannelCode = Integer.parseInt(channelCode);
                        }
                    } catch (Exception e) {
                    	Logger.debug("ERROR channelCode ");
                    	errorDataDescription += "ChannelCode ";
						statusValidRow = false;
					}
                    
                    // SENDING COUNTRY CODE
                    try {
                        String sendingCountryCode = nextLine[13] != null ? nextLine[13].trim() : "";
                        if (!CommonUtil.validCountryCode(sendingCountryCode, countrySender)) {
                            Logger.debug("ERROR sendingCountryCode " + sendingCountryCode);
                            errorDataDescription += "SendingCountryCode ";
                            statusValidRow = false;
                        } else {
                        	detailHelper.setSendingCountryCode(sendingCountryCode);
                        }
                    } catch (Exception e) {
                    	Logger.debug("ERROR sendingCountryCode ");
                    	errorDataDescription += "SendingCountryCode ";
						statusValidRow = false;
					}
                    
                    // SENDING CURRENCY CODE
                    try {
                        String sendingCurrencyCode = nextLine[14] != null ? nextLine[14].trim() : "";
                        if (!CommonUtil.validCurrencyCode(sendingCurrencyCode, currencySender)) {
                            Logger.debug("ERROR sendingCurrencyCode " + sendingCurrencyCode);
                            errorDataDescription += "SendingCurrencyCode ";
                            statusValidRow = false;
                        } else {
                        	detailHelper.setSendingCurrencyCode(sendingCurrencyCode);
                        }
                    } catch (Exception e) {
                    	Logger.debug("ERROR sendingCurrencyCode ");
                    	errorDataDescription += "SendingCurrencyCode ";
						statusValidRow = false;
					}
                    
                    // RECEIVING COUNTRY CODE
//                    try {
//                        String receivingCountryCode = nextLine[16] != null ? nextLine[16].trim() : "";
//                        if (!CommonUtil.validCountryCode(receivingCountryCode, countryBeneficiary)) {
//                            Logger.debug("ERROR receivingCountryCode " + receivingCountryCode);
//                            statusValidRow = false;
//                        } else {
//                        	detailHelper.setReceivingCountryCode(receivingCountryCode);
//                        }
//                    } catch (Exception e) {
//                    	Logger.debug("ERROR receivingCountryCode ");
//						statusValidRow = false;
//					}
                    detailHelper.setReceivingCountryCode("ID");	//REM-63
                    
                    
                    // RECEIVING CURRENCY CODE
//                    try {
//                        String receivingCurrencyCode = nextLine[17] != null ? nextLine[17].trim() : "";
//                        if (!CommonUtil.validCurrencyCode(receivingCurrencyCode, currencyBeneficiary)) {
//                            Logger.debug("ERROR receivingCurrencyCode " + receivingCurrencyCode);
//                            statusValidRow = false;
//                        } else {
//                        	detailHelper.setReceivingCurrencyCode(receivingCurrencyCode);
//                        }
//                    } catch (Exception e) {
//                    	Logger.debug("ERROR receivingCurrencyCode ");
//						statusValidRow = false;
//					}
                    detailHelper.setReceivingCurrencyCode("IDR");	//REM-63
                    
                    /* TODO CUSTOMER ACCOUNT --------->>>> */
                    switch (caseChannelCode) {
					case 6: case 7:
						
						//BANK ID
	                    try {
	                        String bankId = nextLine[15] != null ? nextLine[15].trim() : "";
	                        if (!CommonUtil.validBankId(bankId, channelBankId.get(detailHelper.getChannelCode()))) {
	                            Logger.debug("ERROR bankId " + bankId);
	                            errorDataDescription += "BankId ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setBankId(bankId);
	                        	
	                        	// BANK SWIFT CODE
	                        	detailHelper.setBankSwiftCode(channelBankCode.get(detailHelper.getChannelCode()).get(bankId));	// REM-63
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR bankId ");
	                    	errorDataDescription += "BankId ";
							statusValidRow = false;
						}
	                    
	                    // BANK SWIFT CODE
//	                    try {
//	                        String bankSwiftCode = nextLine[19] != null ? nextLine[19].trim().toUpperCase() : "";
//	                        if (!CommonUtil.validBankSwiftCode(bankSwiftCode, channelBankCode.get(detailHelper.getChannelCode()))) {
//	                            errorDescription += "BANK SWIFT CODE ["+bankSwiftCode+"];";
//	                            Logger.debug("ERROR bankSwiftCode " + bankSwiftCode);
//	                            statusValidRow = false;
//	                        } else {
//	                        	detailHelper.setBankSwiftCode(bankSwiftCode);
//	                        }
//	                    } catch (Exception e) {
//	                    	Logger.debug("ERROR bankSwiftCode ");
//	                    	errorDescription += "BANK SWIFT CODE;";
//							statusValidRow = false;
//						}
	                    
	                    // BANK ACCOUNT NUMBER
	                    try {
	                        String bankAccountNumber = nextLine[16] != null ? nextLine[16].trim() : "";
	                        if (!CommonUtil.validBankAccountNumber(bankAccountNumber)) {
	                            Logger.debug("ERROR bankAccountNumber " + bankAccountNumber);
	                            errorDataDescription += "BankAccountNumber ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setBankAccountNumber(bankAccountNumber);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR bankAccountNumber ");
	                    	errorDataDescription += "BankAccountNumber ";
							statusValidRow = false;
						}
	                    
	                    // BANK CITY
//	                    try {
//	                        String bankCity = nextLine[21] != null ? nextLine[21].trim() : "";
//	                        if (!CommonUtil.validString(bankCity, INPUT_PATTERN)) {
//	                            Logger.debug("ERROR bankCity " + bankCity);
//	                            statusValidRow = false;
//	                        } else {
//	                        	detailHelper.setBankCity(bankCity);
//	                        }
//	                    } catch (Exception e) {
//	                    	Logger.debug("ERROR bankCity ");
//							statusValidRow = false;
//						}
	                    detailHelper.setBankCity("JAKARTA");	//REM-63
						break;
						
					case 4 :
						/* TODO DOKU WALLET ID ------->>> */
	                    
	                    // DOKU WALLET ID
	                    try {
	                        String dokuWalletId = nextLine[17] != null ? nextLine[17].trim() : "";
	                        if (!CommonUtil.validDokuWalletId(dokuWalletId)) {
	                            Logger.debug("ERROR dokuWalletId " + dokuWalletId);
	                            errorDataDescription += "DokuWalletId ";
	                            statusValidRow = false;
	                        } else {
	                        	detailHelper.setDokuWalletId(dokuWalletId);
	                        }
	                    } catch (Exception e) {
	                    	Logger.debug("ERROR dokuWalletId ");
	                    	errorDataDescription += "DokuWalletId ";
							statusValidRow = false;
						}
						break;
					default:
						break;
					}
                    
                    
                    /* TODO AMOUNT ------->>> */
                    
                    // SEND TYPE
//                    try {
//                        String sendType = nextLine[23] != null ? nextLine[23].trim() : "";
//                        if (!CommonUtil.validSendType(sendType, sendTypeCode)) {
//                            Logger.debug("ERROR sendType " + sendType);
//                            statusValidRow = false;
//                        } else {
//                        	detailHelper.setSendType(sendType);
//                        	caseSendType = sendTypeCode.get(sendType);
//                        }
//                    } catch (Exception e) {
//                    	Logger.debug("ERROR sendType ");
//						statusValidRow = false;
//					}
                    detailHelper.setSendType("S");	//REM-63
                    caseSendType = 1;
                    
//                  switch (caseSendType) {
//					case 1:
                    
					// SENDING AMOUNT
                    try {
                        String sendingAmount = nextLine[18] != null ? nextLine[18].trim() : "";
                        if (!CommonUtil.validAmount(sendingAmount)) {
                            Logger.debug("ERROR sendingAmount " + sendingAmount);
                            errorDataDescription += "SendingAmount ";
                            statusValidRow = false;
                        } else {
                        	detailHelper.setSendingAmount(sendingAmount);
                        }
                    } catch (Exception e) {
                    	Logger.debug("ERROR sendingAmount ");
                    	errorDataDescription += "SendingAmount ";
						statusValidRow = false;
					}

//					break;
//				case 2:
//					// RECEIVING AMOUNT
//                    try {
//                        String receivingAmount = nextLine[25] != null ? nextLine[25].trim() : "";
//                        if (!CommonUtil.validAmount(receivingAmount)) {
//                            Logger.debug("ERROR receivingAmount " + receivingAmount);
//                            statusValidRow = false;
//                        } else {
//                        	detailHelper.setReceivingAmount(receivingAmount);
//                        }
//                    } catch (Exception e) {
//                    	Logger.debug("ERROR receivingAmount ");
//						statusValidRow = false;
//					}
//					break;
//				default:
//					Logger.debug("ERROR amount ");
//					break;
//				}
                
                    
                	try {
                		Logger.debug("get forex reference >> "+detailHelper.getReceivingCountryCode());
                		detailHelper.setForexReferenceId((statusValidRow ? forexReference.get(detailHelper.getReceivingCountryCode()).id : 0));
					} catch (Exception e) {
						Logger.debug("ERROR get forex reference ");
						statusValidRow = false;
					}
                	
                    detailHelper.setStatus(statusValidRow);
                    batchUploadHelper.getBatchUploadDetailHelpers().add(detailHelper);
                    
                    try {
                    	int caseOf = statusValidRow ? 1 : 0;
                    	switch (caseOf) {
                    	case 0:
                    		status = false;
                    		rowCSVFailed++;
                    		errorDescription += (rowCSV+1)+":["+errorDataDescription.trim().replace(" ", ",")+"]"+" ";
                    		break;
                    	case 1:
                    		rowCSVSuccess++;
                    		break;
                    	}
					} catch (Exception e) {
						Logger.debug("failed set error list : "+e.getMessage());
					}
                    
                    int caseExceedingCsv = rowCSV;
                    switch (caseExceedingCsv) {
                    	case 100 : 
                    		status = false;
                    		Logger.debug("Exceeding Transaction Record");
                    		batchUploadHelper.setErrorDescription("Exceeding Transaction Record");
                    		throw new Exception("Exceeding Transaction Record");
                    	default :
                    			Logger.debug("Row "+rowCSV);
                    		break;
                    }

                    rowCSV++;
            	}
            	if (!errorDescription.equals("")) {
            		batchUploadHelper.setErrorDescription("Failed parsing row / Special Character Not Allowed (Row "+errorDescription.trim().replace(" ", ", ")+")");
            	}
            	batchUploadHelper.setTotalRow(rowCSV);
            	Logger.debug("Total Row csv ["+rowCSV+"]");
            } else {
            	Logger.debug("Failed extract csv data");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		Logger.debug(CommonUtil.sdfDateTimes.format(new Date())+" :: END PARSING UPLOAD BATCH ::.");
		return status;
	}
	
	public static List<TransactionInquiry> doPrepareBatchRemit(List<TransactionInquiry> inquiries) {
		try {
			List<TransactionInquiry> result = new ArrayList<TransactionInquiry>();
			for (TransactionInquiry transactionInquiry : inquiries) {
				try {
					transactionInquiry.state = TransactionInquiryState.PROCESSING_REMIT.getCode();
					transactionInquiry.remitRequestTime = new Date();
					transactionInquiry.update();
					result.add(transactionInquiry);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.debug("FAILED UPDATE INQUIRY : "+e.getMessage());
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.debug("FAILED DO PREPARE BATCH REMIT : "+e.getMessage());
		}
		return null;
	}
	
	
}
