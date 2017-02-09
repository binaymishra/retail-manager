package com.manager.retail;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.manager.retail.domain.Shop;
import com.manager.retail.domain.ShopAddress;

/**
 * @author Binay Mishra
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerIntegrationTests {
	
	@LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;
    
    @MockBean
	RetailsShopService retailsShopService;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }
    
    @Test
    public void findNearestShopFound() throws Exception {
        
        Shop shop = new Shop("Home Town", new ShopAddress(1600, 700156));
		shop.setShopLatitude(BigDecimal.valueOf(22.5818985));
		shop.setShopLongitude(BigDecimal.valueOf(88.4529769));
		
        given(this.retailsShopService.findNearestShops(BigDecimal.valueOf(88.4529769), BigDecimal.valueOf(22.5818985)))
		.willReturn(Arrays.asList(shop));
    	
        ResponseEntity<Shop> response = template.getForEntity(base.toString()+"/shops?customerLatitude=22.5818985&customerLongitude=88.4529769",Shop.class);
        assertThat(response.getBody(), is(shop));
    }
    
    @Test
    public void findNearestShopNotFound() throws Exception {
        given(this.retailsShopService.findNearestShops(BigDecimal.valueOf(88.4529769), BigDecimal.valueOf(22.5818985))).willReturn(Collections.emptyList());
        ResponseEntity<Shop> response = template.getForEntity(base.toString()+"/shops?customerLatitude=22.00&customerLongitude=88.00",Shop.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(response.getBody());
    }
    
    @Test
    public void findNearestShopBadRequest() throws Exception {
        ResponseEntity<Shop> response = template.getForEntity(base.toString()+"/shops",Shop.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    

}
