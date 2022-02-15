package org.wayapos.agencyService.digitalIdentityService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	
	private Long timeStamp;
	private boolean status;
	private String message;
	private Object data;

}
