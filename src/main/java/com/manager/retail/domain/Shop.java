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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (shopAddress == null) {
			if (other.shopAddress != null)
				return false;
		} else if (!shopAddress.equals(other.shopAddress))
			return false;
		if (shopLatitude == null) {
			if (other.shopLatitude != null)
				return false;
		} else if (!shopLatitude.equals(other.shopLatitude))
			return false;
		if (shopLongitude == null) {
			if (other.shopLongitude != null)
				return false;
		} else if (!shopLongitude.equals(other.shopLongitude))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shopAddress == null) ? 0 : shopAddress.hashCode());
		result = prime * result + ((shopLatitude == null) ? 0 : shopLatitude.hashCode());
		result = prime * result + ((shopLongitude == null) ? 0 : shopLongitude.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		return result;
	}

}
