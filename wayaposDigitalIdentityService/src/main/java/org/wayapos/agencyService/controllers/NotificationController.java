package org.wayapos.agencyService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wayapos.agencyService.model.EmailNotificationRequest;
import org.wayapos.agencyService.model.EmailNotificationResponse;
import org.wayapos.agencyService.servicesImpl.WayaPosAgencyServiceImpl;

@RestController
@RequestMapping("/wayapos/notification")
public class NotificationController {
	
	@Autowired
	WayaPosAgencyServiceImpl notificationService;
	
	@PostMapping("/email")
	public EmailNotificationResponse signup(@RequestBody EmailNotificationRequest emailNotificationRequest) {
		return notificationService.sendEmailNotification(emailNotificationRequest);
	}
	

}
