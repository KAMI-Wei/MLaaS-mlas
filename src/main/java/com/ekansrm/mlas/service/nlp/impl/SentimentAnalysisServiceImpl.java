package com.ekansrm.mlas.service.nlp.impl;

import com.ekansrm.mlas.service.nlp.SentimentAnalysisService;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@Service("sentimentAnalysisService")
public class SentimentAnalysisServiceImpl implements SentimentAnalysisService {

  @PostConstruct
  public void init() {
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    try {
      config.setServerURL(new URL("http://localhost:8888/RPC2"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    config.setEnabledForExtensions(Boolean.TRUE);
    XmlRpcClient client = new XmlRpcClient();
    client.setConfig(config);
    //注意此时构造的远程python的函数参数
    Object[] params = new Object[]{Boolean.TRUE, 123, 1.23, "abc"};
    try{
      Object[] res = (Object[]) client.execute("testType", params);
      System.out.println(res);//注意访问java的object类型数据的方法
    }
    catch (XmlRpcException e11)
    {
      e11.printStackTrace();
    }
  }

}
