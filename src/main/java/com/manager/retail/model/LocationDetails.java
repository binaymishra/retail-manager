package com.manager.retail.model;


public class LocationDetails {

	Geometry geometry;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "LocationDetails [geometry=" + geometry + "]";
	}
}
