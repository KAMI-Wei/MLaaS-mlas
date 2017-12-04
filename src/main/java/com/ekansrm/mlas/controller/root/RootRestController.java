package com.ekansrm.mlas.controller.root;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RootRestController {

  @RequestMapping(
      value = "/",
      method = RequestMethod.GET
  )
  public String root() {
    return "<h1>Machine Learning as Service</h1>";
  }

}
