package com.ekansrm.mlas.controller.dto.principal;

import lombok.Data;

import java.security.Principal;

@Data
public class PrincipalUser implements Principal{

  private String user;

  public PrincipalUser(String user) {
    this.user = user;
  }

  @Override
  public String getName() {
    return user;
  }

}
