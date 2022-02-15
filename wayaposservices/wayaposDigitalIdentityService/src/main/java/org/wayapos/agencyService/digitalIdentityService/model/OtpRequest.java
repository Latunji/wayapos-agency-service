package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class OtpRequest {
	
	private String otp;
	private String phoneOrEmail;
	
}
