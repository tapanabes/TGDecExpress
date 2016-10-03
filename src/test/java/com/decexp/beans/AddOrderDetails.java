package com.decexp.beans;

public class AddOrderDetails {
	public String OrderType = null;
	public String PaymentMethod = null;
	public String ProductType = null;
	public String Product = null;
	public String VatCode = null;
	
	@Override
	public String toString() {
		return "AddOrderDetails [OrderType=" + OrderType + ", PaymentMethod="
				+ PaymentMethod + ", ProductType=" + ProductType + ", Product="
				+ Product + ", VatCode=" + VatCode + "]";
	}
		
}
