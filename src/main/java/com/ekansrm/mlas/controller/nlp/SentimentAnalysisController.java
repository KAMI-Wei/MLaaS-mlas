package com.ekansrm.mlas.controller.nlp;

import com.ekansrm.mlas.controller.dto.response.Response;
import com.ekansrm.mlas.service.nlp.SentimentAnalysisService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.xmlrpc.XmlRpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("NLP/SentimentAnalysis")
public class SentimentAnalysisController {

  static private Gson gson;
  static {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @Autowired
  private SentimentAnalysisService sentimentAnalysisService;

  @RequestMapping(
      value = "/predict",
      method = RequestMethod.POST,
      produces = "application/json;charset=UTF-8"
  )
  public String predict(@RequestBody Map<String, Object> body) {
    try {

      Assert.notNull(body.get("text"), "输入文本不能为空");
      String text = body.get("text").toString();
      return gson.toJson(Response.successResponse(sentimentAnalysisService.predict(text)));
    } catch (IllegalArgumentException | XmlRpcException e) {
      e.printStackTrace();
      return gson.toJson(Response.errorResponse(-1,e.getMessage()));
    }
  }

}
