package com.manager.retail;



import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import com.manager.retail.domain.Shop;
import com.manager.retail.domain.ShopAddress;

public class RetailsShopServiceImplTest {
	
	RetailsShopService retailsShopService;
	
	LinkedList<Shop> shopList;
	
	
	@Before
	public void setup(){

	}
	
	@Test
	public void findNearestShops(){
		Shop shop1 = new Shop("Home Town", new ShopAddress(1600, 700156));
		shop1.setShopLatitude(BigDecimal.valueOf(22.5818985));
		shop1.setShopLongitude(BigDecimal.valueOf(88.4529769));
		
		Shop shop2 = new Shop("Axis Mall", new ShopAddress(1600, 700156));
		shop2.setShopLatitude(BigDecimal.valueOf(22.5795843));
		shop2.setShopLongitude(BigDecimal.valueOf(88.45989399999999));
		
		shopList = new LinkedList<>(Arrays.asList(shop1, shop2));
		
		retailsShopService = new RetailsShopServiceImpl(shopList);
		
		List<Shop> list =  retailsShopService.findNearestShops(BigDecimal.valueOf(88.4586321), BigDecimal.valueOf(22.5829933));
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void findNearestShopsNotFound(){
		shopList = new LinkedList<>();
		retailsShopService = new RetailsShopServiceImpl(shopList);
		List<Shop> list =  retailsShopService.findNearestShops(BigDecimal.valueOf(88.4586321), BigDecimal.valueOf(22.5829933));
		Assert.assertTrue(list.isEmpty());
	}
}
