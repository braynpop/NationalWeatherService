package com.nws.beans;

import javax.json.bind.annotation.JsonbProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class GridPoints {
	@JsonbProperty("gridX")
	private double gridX;
	@JsonbProperty("gridY")
	private double gridY;
	@JsonbProperty("cwa")
	private String office;
	
	@JsonbProperty("gridX")
	public double getGridX() {
		return gridX;
	}
	@JsonbProperty("gridX")
	public void setLatitude(double gridX) {
		this.gridX = gridX;
	}
	@JsonbProperty("gridY")
	public double getGridY() {
		return gridY;
	}
	@JsonbProperty("gridY")
	public void setLongitude(double gridY) {
		this.gridY = gridY;
	}
	@JsonbProperty("cwa")
	public String getOffice() {
		return office;
	}
	@JsonbProperty("cwa")
	public void setOffice(String office) {
		this.office = office;
	}
	

}
