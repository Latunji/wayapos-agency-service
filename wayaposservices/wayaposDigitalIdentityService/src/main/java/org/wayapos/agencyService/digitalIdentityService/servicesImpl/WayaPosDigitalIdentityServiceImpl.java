package org.wayapos.agencyService.digitalIdentityService.servicesImpl;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import org.wayapos.agencyService.digitalIdentityService.UtilityClass;
import org.wayapos.agencyService.digitalIdentityService.entities.Customer;
import org.wayapos.agencyService.digitalIdentityService.model.LoginRequest;
import org.wayapos.agencyService.digitalIdentityService.model.OtpRequest;
import org.wayapos.agencyService.digitalIdentityService.model.PasswordChange2Request;
import org.wayapos.agencyService.digitalIdentityService.model.PasswordChangeRequest;
import org.wayapos.agencyService.digitalIdentityService.model.ResetPasswordRequest;
import org.wayapos.agencyService.digitalIdentityService.model.Response;
import org.wayapos.agencyService.digitalIdentityService.resttemplate.errorHandler.RestTemplateResponseErrorHandler;
import org.wayapos.agencyService.digitalIdentityService.services.WayaPosDigitalIdentityServices;

@Service
public class WayaPosDigitalIdentityServiceImpl implements WayaPosDigitalIdentityServices {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UtilityClass utilityClass;

	@Override
	public Response signup(Customer customer) {

		Gson gson = new Gson();
		String gsonString = gson.toJson(customer);
		System.out.println(gsonString);

		String url = "https://services.staging.mywayapay.com/auth-service/api/v1/auth/create-corporate";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		String responseString = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

		// System.err.println(responseString);

		Gson responseGson = new Gson();
		Response response = responseGson.fromJson(responseString, Response.class);

		return response;
	}

	@Override
	public Response sendOtp(OtpRequest otpRequest) {

		Gson gson = new Gson();
		String gsonString = gson.toJson(otpRequest);
		System.out.println(gsonString);

		String url = "https://services.staging.mywayapay.com/auth-service/api/v1/auth/verify-otp";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<OtpRequest> entity = new HttpEntity<OtpRequest>(otpRequest, headers);

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		String responseString = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

		Gson responseGson = new Gson();
		Response response = responseGson.fromJson(responseString, Response.class);

		return response;

	}

	@Override
	public Response login(LoginRequest loginRequest) {

		Gson gson = new Gson();
		String gsonString = gson.toJson(loginRequest);
		System.out.println(gsonString);

		String url = "https://services.staging.mywayapay.com/auth-service/api/v1/auth/login";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LoginRequest> entity = new HttpEntity<LoginRequest>(loginRequest, headers);

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		String responseString = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

		Gson responseGson = new Gson();
		Response response = responseGson.fromJson(responseString, Response.class);

		return response;

	}

	@Override
	public Response passwordChange(PasswordChangeRequest passwordChangeRequest) {

		Gson gson = new Gson();
		String gsonString = gson.toJson(passwordChangeRequest);
		System.out.println(gsonString);

		String url = "https://services.staging.mywayapay.com/auth-service/api/v1/password/change";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PasswordChangeRequest> entity = new HttpEntity<PasswordChangeRequest>(passwordChangeRequest,
				headers);

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		String responseString = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

		Gson responseGson = new Gson();
		Response response = responseGson.fromJson(responseString, Response.class);

		return response;

	}

	@Override
	public Response passwordChange2(PasswordChange2Request passwordChangeRequest2) {

		Gson gson = new Gson();
		String gsonString = gson.toJson(passwordChangeRequest2);
		System.out.println(gsonString);

		String url = "https://services.staging.mywayapay.com/auth-service/api/v1/password/change-password";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PasswordChange2Request> entity = new HttpEntity<PasswordChange2Request>(passwordChangeRequest2,
				headers);

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		String responseString = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

		Gson responseGson = new Gson();
		Response response = responseGson.fromJson(responseString, Response.class);

		return response;

	}

	@Override
	public Response changePasswordSendOtp(String email, String redirectUrl) {
		
		String reqUrl = "";
		
		if(utilityClass.validateEmail(email)) {
			reqUrl = "https://services.staging.mywayapay.com/auth-service/api/v1/password/change-password/byEmail?email="
					+ email + "&redirectUrl=" + redirectUrl + "";
		}else {
			reqUrl = "https://services.staging.mywayapay.com/auth-service/api/v1/password/change-password/byPhone?phoneNumber="
					+ email + "&redirectUrl=" + redirectUrl + "";
		}
		
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<String> response = restTemplate.getForEntity(reqUrl, String.class);

		Gson responseGson = new Gson();
		Response responsez = responseGson.fromJson(response.getBody(), Response.class);

		return responsez;

	}
	
	@Override
	public Response resetPassword(ResetPasswordRequest resetPasswordRequest) {

		Gson gson = new Gson();
		String gsonString = gson.toJson(resetPasswordRequest);
		System.out.println(gsonString);

		String url = "http://services.staging.mywayapay.com/auth-service/api/v1/password/forgot-password";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ResetPasswordRequest> entity = new HttpEntity<ResetPasswordRequest>(resetPasswordRequest,
				headers);

		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		String responseString = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

		Gson responseGson = new Gson();
		Response response = responseGson.fromJson(responseString, Response.class);

		return response;

	}

}
