package com.manager.retail.model;

public class Geometry {

	Location location;
	
	public Geometry() {
		// Default Constructor
	}

	public Geometry(Location location) {
		super();
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + "]";
	}
}
