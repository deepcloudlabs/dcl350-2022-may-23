package com.example.hr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.business.EventPublisherWebSocketService;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	private final EventPublisherWebSocketService webSocketService;

	public WebSocketConfiguration(EventPublisherWebSocketService webSocketService) {
		this.webSocketService = webSocketService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketService, "/events").setAllowedOrigins("*");
	}

}
