package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;

public class LotteryApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		StandardLotteryService lotteryService = new StandardLotteryService();
		var props = new Properties();
		props.load(new FileInputStream(new File("src","application.properties")));
		var level = QualityLevel.valueOf(props.getProperty("randomNumberServiceType").toString());
		var service = RandomNumberService.getRandomNumberService(level);
		
		if (service.isPresent()) {
			RandomNumberService randomNumberService = service.get();
			System.err.println(randomNumberService.getClass());
			lotteryService.setRandomNumberService(randomNumberService );
			lotteryService.draw(60, 6, 10).forEach(System.out::println);			
		}
	}

}
