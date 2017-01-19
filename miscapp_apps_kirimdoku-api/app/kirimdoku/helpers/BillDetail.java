package kirimdoku.helpers;

import java.util.List;

public class BillDetail {
	
    public double adminfee;
    public java.lang.String billid;
    public java.lang.String currency;
    public BillDetailContact contact;
    public List<BillDetailDescription> descriptions;
    public Object pax;
    public java.lang.String title;
    public double totalamount;
    public double originTotalAmount;
	
    public BillDetail(
    		double adminfee, 
    		String billid, 
    		String currency, 
    		BillDetailContact contact, 
    		List<BillDetailDescription> descriptions,
    		Object pax,
    		String title,
    		double totalamount) {
		this.adminfee = adminfee;
		this.billid = billid;
		this.currency = currency;
		this.contact = contact;
		this.descriptions = descriptions;
		this.pax = pax;
		this.title = title;
		this.totalamount = totalamount;
	}
    
    public BillDetail() {
		// TODO Auto-generated constructor stub
	}
}
