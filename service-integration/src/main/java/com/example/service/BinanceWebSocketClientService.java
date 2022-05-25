package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class BinanceWebSocketClientService implements WebSocketHandler{
	@Value("${binance.ws.api.url}")
	private String binanceWsApiUrl;
	
	private final WebSocketClient webSocketClient;
	
	public BinanceWebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connectToBinanceWsService() {
		webSocketClient.doHandshake(this,binanceWsApiUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the binance ws server.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var payload = message.getPayload().toString();
		System.err.println(payload);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println(e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the binance ws server.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
