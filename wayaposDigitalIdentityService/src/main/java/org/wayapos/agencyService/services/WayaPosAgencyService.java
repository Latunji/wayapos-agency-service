package org.wayapos.agencyService.services;

import org.wayapos.agencyService.model.Customer;
import org.wayapos.agencyService.model.EmailNotificationRequest;
import org.wayapos.agencyService.model.EmailNotificationResponse;
import org.wayapos.agencyService.model.GetMerchantDetailsResponse;
import org.wayapos.agencyService.model.LoginRequest;
import org.wayapos.agencyService.model.OtpRequest;
import org.wayapos.agencyService.model.PasswordChange2Request;
import org.wayapos.agencyService.model.PasswordChangeRequest;
import org.wayapos.agencyService.model.ResetPasswordRequest;
import org.wayapos.agencyService.model.Response;
import org.wayapos.agencyService.model.SuperAdminAccountCreationReq;

public interface WayaPosAgencyService {
	
	public Response signup(Customer customer);
	public Response sendOtp(OtpRequest optRequest);
	public Response login(LoginRequest loginRequest);
	public Response passwordChange(PasswordChangeRequest passwordChangeRequest);
	public Response passwordChange2(PasswordChange2Request passwordChangeRequest2);
	public Response changePasswordSendOtp(String email, String redirectUrl);
	public Response resetPassword(ResetPasswordRequest resetPasswordRequest);
	public Response superAdminAccountCreation(SuperAdminAccountCreationReq superAdminAccountCreationReq);
	public Response adminLogin(LoginRequest loginRequest);
	public EmailNotificationResponse sendEmailNotification(EmailNotificationRequest emailNotificationRequest);
	public GetMerchantDetailsResponse getMerchantAccountDetails(int userid);

}
