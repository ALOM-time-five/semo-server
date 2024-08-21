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
        registry.enableSimpleBroker("/sub"); // 브로커 경로 설정으로 해당 주소를 구독하는 클라이언트에게 메시지를 보낸다. 즉, 인자에는 구독 요청의 prefix를 넣고, 클라이언트에서 1번 채널을 구독하고자 할 때는 /sub/1 형식과 같은 규칙을 따라야 한다.
        registry.setApplicationDestinationPrefixes("/pub"); // 클라이언트의 메시지 경로 설정으로 메시지 발행 요청의 prefix를 넣는다. 즉, /pub로 시작하는 메시지만 해당 Broker에서 받아서 처리한다.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // (5) 클라이언트에서 WebSocket에 접속할 수 있는 endpoint를 지정한다.
        registry.addEndpoint("/stomp/chat") // ex ) ws://localhost:9000/stomp/chat
                .setAllowedOriginPatterns("*").withSockJS();
    }


}
