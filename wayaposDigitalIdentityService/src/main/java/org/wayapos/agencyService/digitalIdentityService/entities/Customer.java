package org.wayapos.agencyService.digitalIdentityService.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "signup_entity")
public class Customer {

	@Id
	@SequenceGenerator(
			name = "customer_id_sequence", 
			sequenceName = "customer_id_sequence"
			)
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
	private Long Id;
	private boolean admin;
	private String businessType;
	private String city;
	private String dateOfBirth;
	private String email;
	private String firstName;
	private String gender;
	private String officeAddress;
	private String orgEmail;
	private String orgName;
	private String orgType;
	private String password;
	private String phoneNumber;
	private String referenceCode;
	private String state;
	private String surname;
	private String orgPhone;
}
