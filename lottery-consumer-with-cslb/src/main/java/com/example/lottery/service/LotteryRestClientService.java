package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Service
public class LotteryRestClientService {
	private final String url = "http://%s:%d/lottery/api/v1/numbers?column=5";
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private final AtomicInteger counter = new AtomicInteger(0);
	
	public LotteryRestClientService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void getLotteryInstances() {
		instances = discoveryClient.getInstances("lottery");
		instances.forEach(instance -> System.out.println("%s:%d".formatted(instance.getHost(),instance.getPort())));
	}
	
	@Scheduled(fixedRate=3_000)
	public void callLotteryService() {
		var rt = new RestTemplate();
		var instance = instances.get(counter.getAndIncrement()%instances.size());
		var response = rt.getForEntity(url.formatted(instance.getHost(),instance.getPort()), String.class).getBody();
		System.out.println(response);
	}
}
