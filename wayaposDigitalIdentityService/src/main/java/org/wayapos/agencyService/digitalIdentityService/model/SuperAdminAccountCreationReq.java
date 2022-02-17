package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Data;

@Data
public class SuperAdminAccountCreationReq {
	
 private boolean admin;
 private String dateOfBirth;
 private String email;
 private String firstName;
 private String gender;
 private String password;
 private String phoneNumber;
 private String referenceCode;
 private String surname;
 

}
