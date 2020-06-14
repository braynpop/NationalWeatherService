package com.nws.forecast.beans;

import java.util.List;

public class ForecastResponse {
	private String latitude;
	private String longitude;
	private List<String> forecastResponse;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public List<String> getForecastResponse() {
		return forecastResponse;
	}
	public void setForecastResponse(List<String> forecastResponse) {
		this.forecastResponse = forecastResponse;
	}
	@Override
	public String toString() {
		return "ForecastResponse [latitude=" + latitude + ", longitude=" + longitude + ", forecastResponse="
				+ forecastResponse + "]";
	}
	
	

}
