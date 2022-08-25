package org.wayapos.agencyService.model;

import java.util.List;

import lombok.Data;

@Data
public class AuthEmailNotifReqData {
	
  private String message;
  private List<RecipientNames> names;
  private String eventCategory;
  private String eventType;
  private String initiator;
  private String productType;

}
