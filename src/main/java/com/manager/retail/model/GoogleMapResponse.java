package com.manager.retail.model;

import java.util.List;

public class GoogleMapResponse {
	
	String status;
	
	List<LocationDetails> results; 

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LocationDetails> getResults() {
		return results;
	}

	public void setResults(List<LocationDetails> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "GoogleMapResponse [status=" + status + ", results=" + results + "]";
	}

}
