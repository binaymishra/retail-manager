package com.manager.retail.component;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.manager.retail.domain.Shop;
import com.manager.retail.domain.ShopAddress;
import com.manager.retail.model.GoogleMapResponse;
import com.manager.retail.model.Location;
import com.manager.retail.model.LocationDetails;

@Component("GoogleMapComponent")
public class GoogleMapComponent {
	
	private static final Logger LOG = Logger.getLogger(GoogleMapComponent.class);
	
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Value("${api.key}")  //AIzaSyAelw4voJokY89NBhPX1NPus5_nQujT-bQ
	String apiKey;
	
	
	@Value("${google.map.baseUrl}")  //https://maps.googleapis.com/maps/api/geocode/json
	String baseUrl;
	

	public Location findLatitudeAndLongitude(Shop shop) throws InterruptedException, ExecutionException{
		
		final GoogleMapResponse response = taskExecutor.submit(new Callable<GoogleMapResponse>() {

			@Override
			public GoogleMapResponse call() throws Exception {
				return restTemplate.getForObject(generateUrl(shop), GoogleMapResponse.class);
			}
		}).get();
		
		return getLocation(response);
	}
	
	
	/**
	 * Keys { latitude, longitude }
	 * @param response
	 * @return
	 */
	private Location getLocation(GoogleMapResponse response){
		LocationDetails locationDetails = response.getResults().get(0);
		Location location = locationDetails.getGeometry().getLocation();
		if(LOG.isInfoEnabled())
			LOG.info(location);
		return location; 
	}
	
	/**
	 * @param shopAddress
	 * @return
	 */
	private String generateUrl(Shop shop){
		String shopName = shop.getShopName();
		ShopAddress shopAddress = shop.getShopAddress();
		String url = String.format(baseUrl+"?address=%s,%s&key=%s", shopName, shopAddress.getPostCode(), apiKey);
		if(LOG.isInfoEnabled())
			LOG.info("Geocode URL : "+url);
		return url;
	}
	
}
