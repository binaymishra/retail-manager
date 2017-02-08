package com.manager.retail;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
