package com.manager.retail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.retail.domain.Shop;

/**
 * @author Binay Mishra
 * 
 * This is a REST controller which is designed to to create resource URI location
 * and defines to operations one is POST to create resource and second is GET operation 
 * with parameter to find the nearest shop.
 * The business logic is served via loosely coupled interface {@link com.manager.retail.RetailsShopService}
 */
@RestController
public class ShopController {
	
	@Autowired
	@Qualifier("retailsShopServiceImpl")
	RetailsShopService retailsShopService;
	
	
	/**
	 * @param shop
	 * @return ResponseEntity<Void>
	 * 
	 * This method is used to create a shop which contains shop name, shop Longitude, shop Latitude and shop address
	 * the shop.address contains postal code and shop number. The business logic is implemented in {@link com.manager.retail.RetailsShopServiceImpl#createShop()}
	 */
	@PostMapping("/shops")
	public ResponseEntity<Void> addShop(@RequestBody Shop shop){
		
		if(validate(shop))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		retailsShopService.createShop(shop);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * @param customerLongitude
	 * @param customerLatitude
	 * @return ResponseEntity<Shop>
	 * 
	 * This method is used to find the shop nearest to the customer location[Longitude, Latitude] by comparing 
	 * customer location with shop locations. The business logic is implemented in {@link com.manager.retail.RetailsShopServiceImpl#findNearestShops()}
	 */
	@GetMapping("/shops")
	public ResponseEntity<Shop> findNearestShop(
			@RequestParam("customerLongitude") BigDecimal customerLongitude, 
			@RequestParam("customerLatitude")  BigDecimal customerLatitude){
		
		//In case of(customerLongitude, customerLatitude) is missing or NULL.
		if(customerLongitude == null || customerLatitude == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		List<Shop> nearestShops = retailsShopService.findNearestShops(customerLongitude, customerLatitude);
		if(nearestShops.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(nearestShops.get(0));
	}

	/**
	 * @param shop
	 * @return boolean
	 * This method is used to validate input.
	 */
	private static boolean validate(final Shop shop){
		if(Objects.isNull(shop))
			return true;
		if(StringUtils.isEmpty(shop.getShopName()))
			return true;
		if(Objects.isNull(shop.getShopAddress()))
			return true;
		if(Objects.isNull(shop.getShopAddress().getNumber()) 
				|| Objects.isNull(shop.getShopAddress().getPostCode()))
			return true;
		return false;
	}
}
