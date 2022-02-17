package org.wayapos.agencyService.digitalIdentityService.model;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "email", "isEmailVerified", "phoneNumber", "firstName", "lastName", "isAdmin",
		"isPhoneVerified", "isAccountLocked", "isAccountExpired", "isCredentialsExpired", "isActive",
		"isAccountDeleted", "referenceCode", "pinCreated", "isCorporate", "gender", "middleName", "dateOfBirth",
		"profileImage", "district", "address", "city", "state", "roles", "permits", "links" })
@Generated("jsonschema2pojo")
public class User {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("isEmailVerified")
	private Boolean isEmailVerified;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("isAdmin")
	private Boolean isAdmin;
	@JsonProperty("isPhoneVerified")
	private Boolean isPhoneVerified;
	@JsonProperty("isAccountLocked")
	private Boolean isAccountLocked;
	@JsonProperty("isAccountExpired")
	private Boolean isAccountExpired;
	@JsonProperty("isCredentialsExpired")
	private Boolean isCredentialsExpired;
	@JsonProperty("isActive")
	private Boolean isActive;
	@JsonProperty("isAccountDeleted")
	private Boolean isAccountDeleted;
	@JsonProperty("referenceCode")
	private String referenceCode;
	@JsonProperty("pinCreated")
	private Boolean pinCreated;
	@JsonProperty("isCorporate")
	private Boolean isCorporate;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("profileImage")
	private String profileImage;
	@JsonProperty("district")
	private String district;
	@JsonProperty("address")
	private String address;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("roles")
	private List<String> roles = null;
	@JsonProperty("permits")
	private List<String> permits = null;
	@JsonProperty("links")
	private List<Object> links = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("isEmailVerified")
	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	@JsonProperty("isEmailVerified")
	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("isAdmin")
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	@JsonProperty("isAdmin")
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@JsonProperty("isPhoneVerified")
	public Boolean getIsPhoneVerified() {
		return isPhoneVerified;
	}

	@JsonProperty("isPhoneVerified")
	public void setIsPhoneVerified(Boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	@JsonProperty("isAccountLocked")
	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}

	@JsonProperty("isAccountLocked")
	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	@JsonProperty("isAccountExpired")
	public Boolean getIsAccountExpired() {
		return isAccountExpired;
	}

	@JsonProperty("isAccountExpired")
	public void setIsAccountExpired(Boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}

	@JsonProperty("isCredentialsExpired")
	public Boolean getIsCredentialsExpired() {
		return isCredentialsExpired;
	}

	@JsonProperty("isCredentialsExpired")
	public void setIsCredentialsExpired(Boolean isCredentialsExpired) {
		this.isCredentialsExpired = isCredentialsExpired;
	}

	@JsonProperty("isActive")
	public Boolean getIsActive() {
		return isActive;
	}

	@JsonProperty("isActive")
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@JsonProperty("isAccountDeleted")
	public Boolean getIsAccountDeleted() {
		return isAccountDeleted;
	}

	@JsonProperty("isAccountDeleted")
	public void setIsAccountDeleted(Boolean isAccountDeleted) {
		this.isAccountDeleted = isAccountDeleted;
	}

	@JsonProperty("referenceCode")
	public String getReferenceCode() {
		return referenceCode;
	}

	@JsonProperty("referenceCode")
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	@JsonProperty("pinCreated")
	public Boolean getPinCreated() {
		return pinCreated;
	}

	@JsonProperty("pinCreated")
	public void setPinCreated(Boolean pinCreated) {
		this.pinCreated = pinCreated;
	}

	@JsonProperty("isCorporate")
	public Boolean getIsCorporate() {
		return isCorporate;
	}

	@JsonProperty("isCorporate")
	public void setIsCorporate(Boolean isCorporate) {
		this.isCorporate = isCorporate;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("profileImage")
	public String getProfileImage() {
		return profileImage;
	}

	@JsonProperty("profileImage")
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@JsonProperty("district")
	public String getDistrict() {
		return district;
	}

	@JsonProperty("district")
	public void setDistrict(String district) {
		this.district = district;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("roles")
	public List<String> getRoles() {
		return roles;
	}

	@JsonProperty("roles")
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@JsonProperty("permits")
	public List<String> getPermits() {
		return permits;
	}

	@JsonProperty("permits")
	public void setPermits(List<String> permits) {
		this.permits = permits;
	}

	@JsonProperty("links")
	public List<Object> getLinks() {
		return links;
	}

	@JsonProperty("links")
	public void setLinks(List<Object> links) {
		this.links = links;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
