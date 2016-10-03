package com.decexp.beans;

public class AddMemberDetails {
	public String ForeName = null;
	public String SurName = null;
	public String Initials = null;
	public String Title = null;
	public String Mobile = null;
	public String Email = null;
	public String Gender = null;
	public String PostCode = null;
	public String Country = null;
	public String Branch = null;
	public String TermsAndCondition = null;
	public String Product = null;
	public String PaymentMethod = null;

	@Override
	public String toString() {
		return "AddMemberDetails [ForeName=" + ForeName + ", SurName="
				+ SurName + ", Initials=" + Initials + ", Title=" + Title
				+ ", Mobile=" + Mobile + ", Email=" + Email + ", Gender="
				+ Gender + ", PostCode=" + PostCode + ", Country=" + Country
				+ ", Branch=" + Branch + ", TermsAndCondition="
				+ TermsAndCondition + ", Product=" + Product
				+ ", PaymentMethod=" + PaymentMethod + "]";
	}
		
}
