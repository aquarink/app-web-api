package kirimdoku.helpers;

import org.hibernate.validator.constraints.Length;

import play.data.validation.Constraints;

public class CustomerLookUpHelper {
	
	@Constraints.Required
	@Length(min = 10, max = 10)
	public String idToken;
	
}
