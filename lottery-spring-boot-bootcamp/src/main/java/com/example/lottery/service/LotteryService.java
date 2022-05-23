package com.example.lottery.service;

import java.util.List;
import java.util.stream.IntStream;

public interface LotteryService {
	List<Integer> draw();
	
	default List<List<Integer>> draw(int column){
		return IntStream.range(0, column)
				        .mapToObj( i -> this.draw())
				        .toList();
	}
}
