package com.manager.retail.component;


import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.manager.retail.domain.Shop;

/**
 * @author Binay Mishra
 * 
 * The role of this component bean class is to find the location [Longitude, Latitude]
 * of the shop using google geocode api and update the shop location according to it.
 *
 */
@Component("GoogleMapComponent")
public class GoogleMapComponent {
	
	private static final Logger LOG = Logger.getLogger(GoogleMapComponent.class);
	
	@Autowired
	GeoApiContext geoApiContext;
	
	@Value("${api.key}")
	String apiKey;

	/**
	 * @param shop
	 * 
	 * This method updates the location of the shop by fetching the co-ordinates from google geocode service.
	 */
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
			throw new RuntimeException(String.format(" an exception occurred while calling 'google Geocode service', caused by %s. ", e.getMessage()), e);
		}
	}
	
}
