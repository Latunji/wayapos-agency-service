package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	
	private String newPassword;
	private String otp;
	private String emailOrPassword;
	
	
	

}
