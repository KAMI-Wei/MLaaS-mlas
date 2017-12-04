package com.ekansrm.mlas.controller.dto.response;

import lombok.Data;

@Data
public class Response<T> {

  private Integer code;
  private String message;
  private T content;


  static public <T> Response<T> successResponse(T content) {
    Response<T> response = new Response<>();
    response.code = 200;
    response.message = "success";
    response.content = content;
    return response;
  }

  static public <T> Response<T> errorResponse(Integer code, String message) {
    Response<T> response = new Response<>();
    response.code = code;
    response.message = message;
    response.content = null;
    return response;
  }

}
