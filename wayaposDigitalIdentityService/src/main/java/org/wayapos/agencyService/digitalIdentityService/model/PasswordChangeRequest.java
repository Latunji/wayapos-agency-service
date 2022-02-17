package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class PasswordChangeRequest {
	
  private String newPassword;
  private String oldPassword;
  private String phoneOrEmail;

}
