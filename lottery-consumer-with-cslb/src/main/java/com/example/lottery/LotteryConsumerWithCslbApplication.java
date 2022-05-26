package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(basePackages = "com.example.lottery.service")
public class LotteryConsumerWithCslbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryConsumerWithCslbApplication.class, args);
	}

}
