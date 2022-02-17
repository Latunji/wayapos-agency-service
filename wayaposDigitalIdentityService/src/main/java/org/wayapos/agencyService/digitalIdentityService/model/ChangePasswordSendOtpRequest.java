package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class ChangePasswordSendOtpRequest {
	
	private String emailOrPhoneNumber;
	private String redirectUrl;

}
