package com.boot.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StockAndPriceController {

	
	@RequestMapping(path = "/getStockAndPrice",method = RequestMethod.POST)
	public @ResponseBody StockAndPriceData getStockAndPriceForProd(@RequestBody String productCode)
	{	
		System.out.println("Fetching Stock for "+productCode);
		
		StockAndPriceData data = new StockAndPriceData();
		
		data.setOfflineStock(1200);
		data.setOnlineStock(700);
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		String stockAndPriceUrl = "http://localhost:8024/getPrice";
		
		ResponseEntity<PriceData> resp = restTemplate.postForEntity(stockAndPriceUrl, productCode,PriceData.class);
		
		System.out.println("resp from price MS  >> "+resp.getBody().getUnitPrice());
		
		data.setUnitPrice(resp.getBody().getUnitPrice());
		data.setUom(resp.getBody().getUom());
		
		return data;
	}
	
}
