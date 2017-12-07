package com.ekansrm.mlas.controller.about;

import com.ekansrm.mlas.controller.dto.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AboutStompController {

  static private Gson gson;
  static {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(String name) {
    // 会被序列化
    return gson.toJson(Response.successResponse(name));
  }

}
