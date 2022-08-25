package org.wayapos.agencyService.model;

import lombok.Data;

@Data
public class MerchabtAccountNumberData {
	
	/**
	 * {
        "id": 31,
        "bankName": "WEMA Bank",
        "bankCode": "",
        "accountNumber": "7731646799",
        "accountName": "CEMENT DANGOTE",
        "userId": "45",
        "deleted": false
    }
	 */
	
	private int id;
	private String bankName;
	private String bankCode;
	private String accountNumber;
	private String accountName;
	private int userId;
	private boolean deleted;

}
