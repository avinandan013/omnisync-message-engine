package com.omnisync.message_engine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketConnectListener(
            SessionConnectEvent event) {

        log.info("WebSocket client connected");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event) {

        log.info("WebSocket client disconnected");
    }
}