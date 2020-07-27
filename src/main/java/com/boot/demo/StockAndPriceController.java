package com.boot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StockAndPriceController {

	private static final Logger LOG = LoggerFactory.getLogger(StockAndPriceController.class);
	
	@Bean
	public RestTemplate template() {
	    return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(path = "/getStockAndPrice",method = RequestMethod.POST)
	public @ResponseBody StockAndPriceData getStockAndPriceForProd(@RequestBody String productCode)
	{	
		LOG.info("inside getStockAndPriceForProd...");
		
		LOG.info("Fetching Stock for "+productCode);
		
		StockAndPriceData data = new StockAndPriceData();
		
		data.setOfflineStock(1200);
		data.setOnlineStock(700);
		
		
		String stockAndPriceUrl = "http://localhost:8024/pricingMS/getPrice";
		
		ResponseEntity<PriceData> resp = restTemplate.postForEntity(stockAndPriceUrl, productCode,PriceData.class);
		
		LOG.info("resp from price MS  >> "+resp.getBody().getUnitPrice());
		
		data.setUnitPrice(resp.getBody().getUnitPrice());
		data.setUom(resp.getBody().getUom());
		
		return data;
	}
	
}
