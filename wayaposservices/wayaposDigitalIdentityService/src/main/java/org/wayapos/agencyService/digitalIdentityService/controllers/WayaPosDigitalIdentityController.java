package org.wayapos.agencyService.digitalIdentityService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wayapos.agencyService.digitalIdentityService.entities.Customer;
import org.wayapos.agencyService.digitalIdentityService.model.ChangePasswordSendOtpRequest;
import org.wayapos.agencyService.digitalIdentityService.model.LoginRequest;
import org.wayapos.agencyService.digitalIdentityService.model.OtpRequest;
import org.wayapos.agencyService.digitalIdentityService.model.PasswordChange2Request;
import org.wayapos.agencyService.digitalIdentityService.model.PasswordChangeRequest;
import org.wayapos.agencyService.digitalIdentityService.model.ResetPasswordRequest;
import org.wayapos.agencyService.digitalIdentityService.model.Response;
import org.wayapos.agencyService.digitalIdentityService.servicesImpl.WayaPosDigitalIdentityServiceImpl;

@RestController
@RequestMapping("/wayapos/digitalIdentity")
public class WayaPosDigitalIdentityController {

	@Autowired
	WayaPosDigitalIdentityServiceImpl wayaPosDigitalIdentityService;

	@PostMapping("/signup")
	public Response signup(@RequestBody Customer customer) {
		return wayaPosDigitalIdentityService.signup(customer);
	}

	@PostMapping("/sendOtp")
	public Response sendOtp(@RequestBody OtpRequest otpRequest) {
		return wayaPosDigitalIdentityService.sendOtp(otpRequest);
	}

	@PostMapping("/login")
	public Response sendOtp(@RequestBody LoginRequest loginRequest) {
		return wayaPosDigitalIdentityService.login(loginRequest);
	}

	@PostMapping("/passwordChange")
	public Response passwordChange(@RequestBody PasswordChangeRequest passwordChangeRequest) {
		return wayaPosDigitalIdentityService.passwordChange(passwordChangeRequest);
	}

	@PostMapping("/passwordChange2")
	public Response passwordChange2(@RequestBody PasswordChange2Request passwordChange2Request) {
		return wayaPosDigitalIdentityService.passwordChange2(passwordChange2Request);
	}

	@PostMapping("/changePasswordSendOtp")
	public Response changePasswordSendOtp(@RequestBody ChangePasswordSendOtpRequest changePasswordSendOtpRequest) {
		return wayaPosDigitalIdentityService.changePasswordSendOtp(changePasswordSendOtpRequest.getEmailOrPhoneNumber(),
				changePasswordSendOtpRequest.getRedirectUrl());
	}
	
	@PostMapping("/resetPassword")
	public Response resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
		return wayaPosDigitalIdentityService.resetPassword(resetPasswordRequest);
	}

}
