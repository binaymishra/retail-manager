package com.manager.retail.component;


import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.manager.retail.domain.Shop;

@Component("GoogleMapComponent")
public class GoogleMapComponent {
	
	private static final Logger LOG = Logger.getLogger(GoogleMapComponent.class);
	
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	GeoApiContext geoApiContext;
	
	
	@Value("${api.key}")  //AIzaSyAelw4voJokY89NBhPX1NPus5_nQujT-bQ
	String apiKey;
	
	
	@Value("${google.map.baseUrl}")  //https://maps.googleapis.com/maps/api/geocode/json
	String baseUrl;
	

	public void updateLatitudeAndLongitude(final Shop shop){
		geoApiContext.setApiKey(apiKey);
		String shopAddress = String.format("%s,%s", shop.getShopName(), shop.getShopAddress().getPostCode());
		try {
			long startTime = System.currentTimeMillis();
			GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, shopAddress).await();

			if(LOG.isInfoEnabled())
				LOG.info("Calling google Geocode service, time elapsed = " + (System.currentTimeMillis() - startTime) + " ms.");
			double lat = results[0].geometry.location.lat;
			double lng = results[0].geometry.location.lng;
			
			shop.setShopLatitude(BigDecimal.valueOf(lat));
			shop.setShopLongitude(BigDecimal.valueOf(lng));
		} catch (Exception e) {
			throw new RuntimeException(String.format(" and exception thrown by method [GoogleMapComponent#findLatitudeAndLongitude()], caused by %s. ", e.getMessage()), e);
		}
	}
	
}
