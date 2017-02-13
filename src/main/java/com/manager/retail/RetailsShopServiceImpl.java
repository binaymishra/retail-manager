package com.manager.retail;


import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.manager.retail.component.GoogleMapComponent;
import com.manager.retail.domain.Shop;


/**
 * @author Binay Mishra
 *
 */
@Service("retailsShopServiceImpl")
public class RetailsShopServiceImpl implements RetailsShopService {
	
	private static final Logger LOG = Logger.getLogger(RetailsShopServiceImpl.class);
	
	private Collection<Shop> shops;
	
	public RetailsShopServiceImpl() {
		// Default constructor.
	}
	
	public RetailsShopServiceImpl(final LinkedList<Shop> shop) {
		shops = new ConcurrentLinkedQueue<Shop>(shop);
	}
	
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

	/* (non-Javadoc)
	 * @see com.manager.retail.RetailsShopService#createShop(com.manager.retail.domain.Shop)
	 * 
	 * This method creates the shop, in the memory.
	 */
	@Override
	public void createShop(Shop shop) {
		googleMapComponent.updateLatitudeAndLongitude(shop);
		shops.add(shop);
		if(LOG.isInfoEnabled())
			LOG.info("shop list size = "+shops.size());
	}

	/* (non-Javadoc)
	 * @see com.manager.retail.RetailsShopService#findNearestShops(java.math.BigDecimal, java.math.BigDecimal)
	 *  This method finds the shops nearer to customer location. By comparing customer location with shop location.
	 */
	@Override
	public List<Shop> findNearestShops(BigDecimal customerLongitude, BigDecimal customerLatitude) {
		final ArrayList<Shop> shops = new ArrayList<>(this.shops);
		Map<Double, Shop> shopMap = new TreeMap<>();
		if(!CollectionUtils.isEmpty(shops)){
			for(Shop shop : shops){
				double distance = Point2D.distance(
						customerLatitude.doubleValue(), 
						customerLongitude.doubleValue(), 
						shop.getShopLatitude().doubleValue(), 
						shop.getShopLongitude().doubleValue());
				
				if(LOG.isTraceEnabled())
					LOG.trace("distance = "+distance+ " ->> "+shop);
				
				shopMap.put(distance, shop);
			}
		}
		if(LOG.isDebugEnabled())
			LOG.debug(shopMap);
		shops.clear();
		shops.addAll(shopMap.values());
		return shops;
	}
}
