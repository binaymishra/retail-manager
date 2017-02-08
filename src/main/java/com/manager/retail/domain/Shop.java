package com.manager.retail.domain;

import java.math.BigDecimal;

/**
 * @author Binay Mishra
 *
 */
public class Shop {
	
	String shopName;
	
	ShopAddress shopAddress;
	
	BigDecimal shopLongitude;
	
	BigDecimal shopLatitude;
	
	public Shop() {
		// Default constructor.
	}

	public Shop(String shopName, ShopAddress shopAddress) {
		super();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	public BigDecimal getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(BigDecimal shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public BigDecimal getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(BigDecimal shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", shopAddress=" + shopAddress + ", shopLongitude=" + shopLongitude + ", shopLatitude=" + shopLatitude + "]";
	}
}
