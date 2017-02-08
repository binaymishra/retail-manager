package com.manager.retail.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	BigDecimal latitude;
	
	BigDecimal longitude;
	
	public Location() {
		// Default constructor
	}

	public Location(BigDecimal latitude, BigDecimal longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@JsonProperty("lat")
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("lng")
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
