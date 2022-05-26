package com.example.lottery.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
@Validated
@CrossOrigin
public class LotteryController {
	@Value("${server.port}")
	private int port;
	
	private final LotteryService lotteryService;
	
	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	// GET http://localhost:8100/lottery/api/v1/numbers?column=10
	@GetMapping(params = "column")
	public List<List<Integer>> getLotteryNumbers(@Min(3) @Max(20) int column) {
		System.err.println("New request has arrived for the port "+port);
		return lotteryService.draw(column);
	}

}
