package com.example.hr.service.business;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.domain.event.EmployeeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherWebSocketService implements WebSocketHandler {
	private final Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;
	
	public EventPublisherWebSocketService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error has occured at the session (%s): %s".formatted(session.getId(),e.getMessage()));	
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session.getId());	
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	@EventListener
	public void listenDomainEvent(EmployeeEvent event) throws Exception{
			var payload = objectMapper.writeValueAsString(event);
			for (var session : sessions.values() ) {
				synchronized (session) {
					session.sendMessage(new TextMessage(payload));					
				}
			}
	}
}
