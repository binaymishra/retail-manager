package com.manager.retail;

import java.math.BigDecimal;
import java.util.List;

import com.manager.retail.domain.Shop;

public interface RetailsShopService {
	
	/**
	 * @param shop
	 */
	public void createShop(Shop shop);
	
	/**
	 * @param customerLongitude
	 * @param customerLatitude
	 * @return
	 */
	public List<Shop> findNearestShops(BigDecimal customerLongitude,  BigDecimal customerLatitude);

}
