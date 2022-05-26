package com.example.lottery.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryRestFeignClientService {
	private final LotteryService lotteryService;
	
	public LotteryRestFeignClientService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		System.out.println(lotteryService.getLotteryNumbers(5));
	}
}
