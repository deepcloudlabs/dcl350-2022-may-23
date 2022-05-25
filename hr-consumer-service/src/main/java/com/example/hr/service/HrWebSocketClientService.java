package com.example.hr.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class HrWebSocketClientService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;

	public HrWebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connectToHrWebSocketService() {
		webSocketClient.doHandshake(this, "ws://localhost:8200/hr/api/v1/events")
				.addCallback(session -> System.err.println(session.getId()), e -> System.err.println(e.getMessage()));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the hr websocket server.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err
				.println("New hr event has arrived from the websocket: %s".formatted(message.getPayload().toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println("An error has occured: %s".formatted(exception.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Websocket connection is closed.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
