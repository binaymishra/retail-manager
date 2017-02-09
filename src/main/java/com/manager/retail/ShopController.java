package com.manager.retail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manager.retail.domain.Shop;

/**
 * @author Binay Mishra
 *
 */
@RestController
public class ShopController {
	
	@Autowired
	@Qualifier("retailsShopServiceImpl")
	RetailsShopService retailsShopService;
	
	
	/**
	 * @param shop
	 * @return
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
	 * @return
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
	 * @return
	 */
	private static boolean validate(final Shop shop){
		if(Objects.isNull(shop))
			return true;
		if(Objects.isNull(shop.getShopAddress()))
			return true;
		if(Objects.isNull(shop.getShopAddress().getNumber()) 
				|| Objects.isNull(shop.getShopAddress().getPostCode()))
			return true;
		return false;
	}
}
