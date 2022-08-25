package org.wayapos.agencyService.model;

import java.util.List;

import lombok.Data;

@Data
public class NotificationData {
	
	private String message;
	private List<NamesObj> names; 
	private String eventCategory;
	private String eventType;
	private String initiator;
	private String productType;

}
