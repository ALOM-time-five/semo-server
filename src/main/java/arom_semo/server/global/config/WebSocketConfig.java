package arom_semo.server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        // 메시지 브로커를 활성화하고, "/sub" 경로를 구독한 클라이언트에게 메시지를 전달합니다. 예를 들어, 클라이언트가 /sub/chat/room/1을 구독하면 이 경로로 메시지가 전송
        registry.setApplicationDestinationPrefixes("/pub");
        // 클라이언트가 서버로 메시지를 보낼 때 사용하는 경로의 prefix를 설정합니다. 예를 들어, 클라이언트가 /pub/chat.sendMessage로 메시지를 보내면, 서버는 이 경로로 들어오는 메시지를 처리
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // (5) 클라이언트에서 WebSocket에 접속할 수 있는 endpoint를 지정한다.
        registry.addEndpoint("/stomp/chat") // ex ) ws://localhost:9000/stomp/chat
                .setAllowedOriginPatterns("*"); // 모든 도메인에서 websocket 연결
                //.withSockJS();
    }


}
