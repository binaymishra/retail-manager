package com.manager.retail;


import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.manager.retail.component.GoogleMapComponent;
import com.manager.retail.domain.Shop;
import com.manager.retail.domain.ShopAddress;
import com.manager.retail.model.Location;


/**
 * @author Binay Mishra
 *
 */
@Service("retailsShopServiceImpl")
public class RetailsShopServiceImpl implements RetailsShopService {
	
	private static final Logger LOG = Logger.getLogger(RetailsShopServiceImpl.class);
	
	private Collection<Shop> shops;
	
	@PostConstruct
	public void init(){
		//Thread safe collection from java.util.concurrent api
		shops = new ConcurrentLinkedQueue<Shop>(new LinkedList<Shop>());
	}
	
	@PreDestroy
	public void destroy(){
		shops.clear();
	}
	
	@Autowired
	GoogleMapComponent googleMapComponent;

	@Override
	public void createShop(Shop shop) {
		ShopAddress shopAddress = shop.getShopAddress();
		Assert.notNull(shopAddress, String.format("shopAddress = %s can not be null.", shopAddress));
		
		Location location = null;
		long startTime = System.currentTimeMillis();
		try {
			location = googleMapComponent.findLatitudeAndLongitude(shopAddress);
			if(LOG.isInfoEnabled())
				LOG.info("Calling google Geocode service, time elapsed = " + (System.currentTimeMillis() - startTime) + " ms.");
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(String.format(" and exception thrown by method [GoogleMapComponent#findLatitudeAndLongitude()], caused by %s. ", e.getMessage()), e);
		}
		
		//Updating Latitude and Longitude from google's Geocoding API
		shop.setShopLatitude(location.getLatitude());
		shop.setShopLongitude(location.getLongitude());
		
		LOG.info(shop);
		
		shops.add(shop);
		
		if(LOG.isInfoEnabled())
			LOG.info("shop list size = "+shops.size());
	}
	
	

}
