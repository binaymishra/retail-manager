package com.manager.retail;


import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.manager.retail.domain.Shop;
import com.manager.retail.domain.ShopAddress;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RetailManagerApplicationTests {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	RetailsShopService retailsShopService;

	@Test
	public void findNearestShopFound() throws Exception {
		Shop shop = new Shop("Home Town", new ShopAddress(1600, 700156));
		shop.setShopLatitude(BigDecimal.valueOf(22.5818985));
		shop.setShopLongitude(BigDecimal.valueOf(88.4529769));

		given(this.retailsShopService.findNearestShops(BigDecimal.valueOf(88.4529769), BigDecimal.valueOf(22.5818985)))
				.willReturn(Arrays.asList(shop));

		mvc.perform(MockMvcRequestBuilders
				.get("/shops?customerLatitude=22.5818985&customerLongitude=88.4529769")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(
						"{\"shopName\": \"Home Town\", \"shopAddress\": {\"number\": 1600, \"postCode\": 700156 },\"shopLongitude\": 88.4529769,\"shopLatitude\": 22.5818985 }"));
	}
	
	@Test
	public void findNearestShopFoundBadRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/shops").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void findNearestShopFoundNotFound() throws Exception {
		given(this.retailsShopService.findNearestShops(BigDecimal.valueOf(88.4529769), BigDecimal.valueOf(22.5818985)))
        .willReturn(Collections.emptyList());
		
		mvc.perform(MockMvcRequestBuilders.get("/shops").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addShopOk() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/shops")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{ \"shopName\":\"Home Town\", \"shopAddress\": { \"number\":1600, \"postCode\":700156 } }"))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void addShopBadRequest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/shops")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{ }"))
				.andExpect(status().isBadRequest());
	}
}
