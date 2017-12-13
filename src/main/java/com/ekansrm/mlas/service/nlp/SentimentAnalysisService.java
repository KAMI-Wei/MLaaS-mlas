package com.ekansrm.mlas.service.nlp;

import org.apache.xmlrpc.XmlRpcException;

public interface SentimentAnalysisService {

  Double predict(String text) throws XmlRpcException;

}
