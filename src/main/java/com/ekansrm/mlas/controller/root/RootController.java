package com.ekansrm.mlas.controller.root;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RootController {

  @RequestMapping(
      value = "/",
      method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8"
  )
  public String root() {
    return "Machine Learning as Service";
  }

}
