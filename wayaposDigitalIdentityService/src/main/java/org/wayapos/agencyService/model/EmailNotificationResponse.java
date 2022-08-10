package org.wayapos.agencyService.model;

import lombok.Data;

@Data
public class EmailNotificationResponse {

	private String timeStamp;
	private String message;
	private boolean status;
	private Object data;

}
