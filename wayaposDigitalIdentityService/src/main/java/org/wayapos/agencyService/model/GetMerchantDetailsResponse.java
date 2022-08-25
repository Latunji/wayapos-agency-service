package org.wayapos.agencyService.model;

import lombok.Data;

@Data
public class GetMerchantDetailsResponse {
	
	/**
	 * {
    "timeStamp": "2022-02-18T07:13:29.383+00:00",
    "status": true,
    "message": "Operation Successful",
    "data": {
        "id": 31,
        "bankName": "WEMA Bank",
        "bankCode": "",
        "accountNumber": "7731646799",
        "accountName": "CEMENT DANGOTE",
        "userId": "45",
        "deleted": false
    }
}
	 */
	
	private String timeStamp;
	private boolean status;
	private String message;
	private MerchabtAccountNumberData data;

}
