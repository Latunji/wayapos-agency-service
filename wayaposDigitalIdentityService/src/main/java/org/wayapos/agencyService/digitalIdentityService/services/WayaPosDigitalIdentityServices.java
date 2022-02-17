package org.wayapos.agencyService.digitalIdentityService.services;

import org.wayapos.agencyService.digitalIdentityService.entities.Customer;
import org.wayapos.agencyService.digitalIdentityService.model.LoginRequest;
import org.wayapos.agencyService.digitalIdentityService.model.LoginResponse;
import org.wayapos.agencyService.digitalIdentityService.model.OtpRequest;
import org.wayapos.agencyService.digitalIdentityService.model.PasswordChange2Request;
import org.wayapos.agencyService.digitalIdentityService.model.PasswordChangeRequest;
import org.wayapos.agencyService.digitalIdentityService.model.ResetPasswordRequest;
import org.wayapos.agencyService.digitalIdentityService.model.Response;
import org.wayapos.agencyService.digitalIdentityService.model.SuperAdminAccountCreationReq;


public interface WayaPosDigitalIdentityServices {
	
	public Response signup(Customer customer);
	public Response sendOtp(OtpRequest optRequest);
	public Response login(LoginRequest loginRequest);
	public Response passwordChange(PasswordChangeRequest passwordChangeRequest);
	public Response passwordChange2(PasswordChange2Request passwordChangeRequest2);
	public Response changePasswordSendOtp(String email, String redirectUrl);
	public Response resetPassword(ResetPasswordRequest resetPasswordRequest);
	public Response superAdminAccountCreation(SuperAdminAccountCreationReq superAdminAccountCreationReq);
	public Response adminLogin(LoginRequest loginRequest);

}
