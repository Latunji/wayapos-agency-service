package org.wayapos.agencyService.digitalIdentityService;

import org.springframework.stereotype.Component;

@Component
public class UtilityClass {
	
	public boolean validateEmail( String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	}

}
