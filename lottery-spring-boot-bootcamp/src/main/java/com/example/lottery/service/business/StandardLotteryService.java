package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryService implements LotteryService {
	private final RandomNumberService randomNumberService;
	private final int lotterySize;
	private final int lotteryMax;
	
	// Constructor Injection
	public StandardLotteryService(
			RandomNumberService randomNumberService,
			@Value("${lotteryMax}") int lotteryMax,
			@Value("${lotterySize}") int lotterySize) {
		this.randomNumberService = randomNumberService;
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	@Override
	public List<Integer> draw() {
		return IntStream.generate(() -> randomNumberService.generate(1, lotteryMax))
				        .distinct()
				        .limit(lotterySize)
				        .sorted()
				        .boxed()
				        .toList();
	}

}
