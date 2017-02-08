package com.manager.retail.domain;

/**
 * @author Binay Mishra
 *
 */
public class ShopAddress {
	
	Integer number;
	
	Integer postCode;
	
	public ShopAddress() {
		// Default constructor.
	}

	public ShopAddress(Integer number, Integer postCode) {
		super();
		this.number = number;
		this.postCode = postCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPostCode() {
		return postCode;
	}

	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "ShopAddress [number=" + number + ", postCode=" + postCode + "]";
	}

}
