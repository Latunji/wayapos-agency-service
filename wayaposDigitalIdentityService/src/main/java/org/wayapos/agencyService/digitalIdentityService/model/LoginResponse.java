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
@JsonPropertyOrder({ "corporate", "roles", "pinCreated", "privilege", "user" })
@Generated("jsonschema2pojo")
public class LoginResponse {

	@JsonProperty("corporate")
	private Boolean corporate;
	@JsonProperty("roles")
	private List<String> roles = null;
	@JsonProperty("pinCreated")
	private Boolean pinCreated;
	@JsonProperty("privilege")
	private List<String> privilege = null;
	@JsonProperty("user")
	private User user;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("corporate")
	public Boolean getCorporate() {
		return corporate;
	}

	@JsonProperty("corporate")
	public void setCorporate(Boolean corporate) {
		this.corporate = corporate;
	}

	@JsonProperty("roles")
	public List<String> getRoles() {
		return roles;
	}

	@JsonProperty("roles")
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@JsonProperty("pinCreated")
	public Boolean getPinCreated() {
		return pinCreated;
	}

	@JsonProperty("pinCreated")
	public void setPinCreated(Boolean pinCreated) {
		this.pinCreated = pinCreated;
	}

	@JsonProperty("privilege")
	public List<String> getPrivilege() {
		return privilege;
	}

	@JsonProperty("privilege")
	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	@JsonProperty("user")
	public User getUser() {
		return user;
	}

	@JsonProperty("user")
	public void setUser(User user) {
		this.user = user;
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
