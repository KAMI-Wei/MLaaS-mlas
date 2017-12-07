package com.ekansrm.mlas.controller.about;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

/**
 * 配置WebSocket
 */
@Configuration
@EnableWebSocket
public class AboutWebSocketConfig implements WebSocketConfigurer{

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketHandler(), "/about").setAllowedOrigins("*").withSockJS();
  }

  @Bean
  public WebMvcConfigurer webMvcConfig() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

  @Bean
  public WebSocketHandler webSocketHandler() {
    return new PerConnectionWebSocketHandler(AboutWebSocketHandler.class);
  }

}
