package com.ekansrm.mlas.controller.about;

import com.ekansrm.mlas.controller.dto.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AboutRestController {

  static private Gson gson;
  static {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @RequestMapping(
      value = "/about",
      method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8"
  )
  public String about() {
    return gson.toJson(Response.successResponse("MLaaS 是实现将机器学习模型部署到线上的一个脚手架"));
  }


}
