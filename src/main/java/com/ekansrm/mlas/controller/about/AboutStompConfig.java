package com.ekansrm.mlas.controller.about;

import com.ekansrm.mlas.controller.dto.principal.PrincipalUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * 配置WebSocket
 */
@Configuration
@EnableWebSocketMessageBroker
public class AboutStompConfig extends AbstractWebSocketMessageBrokerConfigurer{

  @Override
  public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    stompEndpointRegistry.addEndpoint("/about/stomp")
      .setHandshakeHandler(new DefaultHandshakeHandler(){
        @Override
        protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
          ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
          return new PrincipalUser(serverRequest.getServletRequest().getSession().getId());
        }
      })
      .setAllowedOrigins("*")
    .withSockJS();

  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.setApplicationDestinationPrefixes("/app");
    config.enableSimpleBroker("/topic", "/queue");
    config.setUserDestinationPrefix("/user/");
  }


}
