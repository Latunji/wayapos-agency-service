package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class WayaPosCustomerSignupRequest {
	
	private boolean admin;
	private String businessType;
	private String city;
	private String dateOfBirth;
	private String email;
	private String firstName;
	private String gender;
	private String officeAddress;
	private String orgEmail;
	private String orgName;
	private String orgType;
	private String password;
	private String phoneNumber;
	private String referenceCode;
	private String state;
	private String surname;

}
