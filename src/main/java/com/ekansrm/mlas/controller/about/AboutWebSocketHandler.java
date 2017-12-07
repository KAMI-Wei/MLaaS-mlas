package com.ekansrm.mlas.controller.about;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AboutWebSocketHandler extends TextWebSocketHandler {

  private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  private final Object MONITOR = new Object();

  private void sessionsAdd(WebSocketSession session) {
    synchronized (MONITOR) {
      if (!sessions.contains(session)) {
        sessions.add(session);
      }
      broadcast(String.valueOf(sessions.size()));
    }
  }


  private void sessionsRemove(WebSocketSession session) {
    synchronized (MONITOR) {
      sessions.remove(session);
      broadcast(String.valueOf(sessions.size()));
    }
  }

  private void broadcast(String message) {
    for (WebSocketSession session : sessions) {
      try {
        if (session.isOpen()) session.sendMessage(new TextMessage(message));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessionsAdd(session);
    super.afterConnectionEstablished(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessionsRemove(session);
    super.afterConnectionClosed(session, status);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    System.out.println("GOT INIT MESSAGE: " + message.getPayload());
  }

}
