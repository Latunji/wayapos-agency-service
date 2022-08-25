package org.wayapos.agencyService.model;

import lombok.Data;

@Data
public class EmailNotificationRequest {

  private NotificationData data;
  private String eventCategory;
  private String eventType;
  private String initiator;
  private String productType;

}
