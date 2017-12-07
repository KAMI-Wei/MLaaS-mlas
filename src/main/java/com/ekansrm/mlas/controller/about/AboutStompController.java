package com.ekansrm.mlas.controller.about;

import com.ekansrm.mlas.controller.dto.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class AboutStompController {

  static private Gson gson;
  static {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @MessageMapping("/announce")
  @SendTo("/topic/announce")
  public String announce(String name) {
    // 会被序列化
    return gson.toJson(Response.successResponse(name));
  }

  @MessageMapping("/register")
  @SendToUser(destinations = "/queue/msg", broadcast = false)
  public String register(String name){
    return gson.toJson(Response.successResponse("Hello, " + name + ". "));
  }

}
