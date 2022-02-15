package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String emailOrPhoneNumber;
	private String password;

}
