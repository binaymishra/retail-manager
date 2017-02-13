package com.manager.retail;

import java.math.BigDecimal;
import java.util.List;

import com.manager.retail.domain.Shop;

public interface RetailsShopService {
	
	/**
	 * This method creates the shop.
	 * @param shop
	 */
	public void createShop(Shop shop);
	
	/**
	 * @param customerLongitude
	 * @param customerLatitude
	 * @return List<Shop>
	 * 
	 * This method finds the shops nearer to customer location.
	 */
	public List<Shop> findNearestShops(BigDecimal customerLongitude,  BigDecimal customerLatitude);

}
