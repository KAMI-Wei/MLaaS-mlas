package com.ekansrm.mlas.controller.about;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AboutStompController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(String name) throws Exception {
    System.out.println("名称:" + name);
    Thread.sleep(1000); // simulated delay
    return "Hello, " + name + "!";
  }

}
