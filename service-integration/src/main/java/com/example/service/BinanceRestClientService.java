package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinanceRestClientService {

	@Value("${binance.rest.api.url}")
	private String binanceRestApiUrl;

	@Scheduled(fixedRate = 3_000)
	public void callRestApi() {
		var restTemplate = new RestTemplate();
		var response = restTemplate.getForEntity(binanceRestApiUrl, String.class).getBody();
		System.out.println(response);
	}
	
}
