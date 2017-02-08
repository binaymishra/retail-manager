package com.manager.retail;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.manager.retail.component.GoogleMapComponent;
import com.manager.retail.domain.Shop;
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
		Location location = null;
		long startTime = System.currentTimeMillis();
		try {
			location = googleMapComponent.findLatitudeAndLongitude(shop);
			if(LOG.isInfoEnabled())
				LOG.info("Calling google Geocode service, time elapsed = " + (System.currentTimeMillis() - startTime) + " ms.");
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(String.format(" and exception thrown by method [GoogleMapComponent#findLatitudeAndLongitude()], caused by %s. ", e.getMessage()), e);
		}
		
		//Updating Latitude and Longitude from google's Geocoding API
		shop.setShopLatitude(location.getLatitude());
		shop.setShopLongitude(location.getLongitude());
		
		shops.add(shop);
		
		if(LOG.isInfoEnabled())
			LOG.info("shop list size = "+shops.size());
	}

	@Override
	public List<Shop> findNearestShops(BigDecimal customerLongitude, BigDecimal customerLatitude) {
		final ArrayList<Shop> shops = new ArrayList<>(this.shops);
		Map<Double, Shop> shopMap = new TreeMap<>();
		if(!CollectionUtils.isEmpty(shops)){
			for(Shop shop : shops){
				final double distanceInKm = findDistanceInKm(
							customerLatitude.doubleValue(),
							customerLongitude.doubleValue(), 
							shop.getShopLatitude().doubleValue(),
							shop.getShopLongitude().doubleValue()
						);
				shopMap.put(distanceInKm, shop);
			}
		}
		for(Map.Entry<Double, Shop> entry : shopMap.entrySet()){
			System.out.println(entry.getValue().getShopName() +  " --> distance from New Town Bus Stop = "+entry.getKey());
		}
		return new ArrayList<>(shopMap.values());
	}
	
	/**
	 * @param customerLatitude
	 * @param customerLongitude
	 * @param shopLatitude
	 * @param shopLongitude
	 * @return
	 */
	private static double findDistanceInKm(double customerLatitude, double customerLongitude, double shopLatitude, double shopLongitude){
		double theta = customerLongitude - shopLongitude;
		
		double distance = Math.sin(convertDegreeToRadian(customerLatitude)) * Math.sin(convertDegreeToRadian(shopLatitude)) 
						+ Math.cos(convertDegreeToRadian(customerLatitude)) * Math.cos(convertDegreeToRadian(shopLatitude)) 
						* Math.cos(convertDegreeToRadian(theta));
		
		distance = Math.acos(distance);
		distance = convertRadianToDegree(distance);
		distance = distance * 60 * 1.1515;
		distance = distance * 1.609344; //in KM
		return distance;
	}
	
	/**
	 * @param degree
	 * @return
	 */
	private static double convertDegreeToRadian(double degree) {
		return (degree * Math.PI / 180.0);
	}
	
	/**
	 * @param radian
	 * @return
	 */
	private static double convertRadianToDegree(double radian) {
		return (radian * 180 / Math.PI);
	}

}
