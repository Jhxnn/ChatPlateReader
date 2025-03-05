package com.ChatPlateReader.webscoket.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {
	
	@Value("${api.security.token.secret}")
	private String secret;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        String username = accessor.getFirstNativeHeader(secret);
        if (username != null) {
            accessor.setUser(() -> username); 
        }
        return message;
    }
}
