package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class PasswordChange2Request {

  private String newPassword;
  private String oldPassword;
  private String otp;
  private String phoneOrEmail;

}
