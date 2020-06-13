package com.nws.beans;

import java.util.ArrayList;


public class ForecastDetail {
	private String fDate;
	private ArrayList<String> forecast = new ArrayList<String>();
	
	public ForecastDetail() {
	}
	
	public ForecastDetail(String fDate, ArrayList<String> forecast) {
		this.fDate = fDate;
		this.forecast = forecast;
	}
	
	public String getfDate() {
		return fDate;
	}


	public void setfDate(String fDate) {
		this.fDate = fDate;
	}


	public ArrayList<String> getForecast() {
		return forecast;
	}


	public void setForecast(ArrayList<String> forecast) {
		this.forecast = forecast;
	}
	
	
	@Override
    public String toString() 
    { 
        return "ForecastDetail [date="
            + fDate 
            + ", detailedForecast="
            + forecast + "]"; 
    } 

}
