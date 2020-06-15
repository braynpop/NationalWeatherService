package com.nws.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nws.forecast.beans.ForecastResponse;
import com.nws.forecast.beans.Grids;


public class ForecastClientResponse {
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("Enter latitude, longitude");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		String coordinates= reader.readLine();
		String splitRegex = "[, ]+";
		String latitude = coordinates.split(splitRegex)[0].trim();
		System.out.println("latitude"+ latitude);
		String longitude = coordinates.split(splitRegex)[1].trim();
		System.out.println("longitude"+ longitude);
		
		String exp ="^[-+]?[\\d)]*\\.?[0-9]+$";
		Pattern p = Pattern.compile(exp); 
		Matcher m = p.matcher(latitude); 
		if(!(m.find() && m.group().equals(latitude)) && !(m.find() && m.group().equals(latitude))){
			System.out.println("Not valid latitude and longitude, please enter numeric values");
        }
	           
		ObjectMapper obj = new ObjectMapper();
		ForecastClient client = new ForecastClient();
		Grids grids = new Grids();
		try {
			grids = client.getGridPoints(latitude, longitude);
			//getGridPoints("39.7456", "-97.0892");
			List<String> forecastResponse = client.getForecast(grids);
			String jsonResponse = obj.writeValueAsString(convertToJson(latitude, longitude, forecastResponse));
			System.out.println(jsonResponse);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static ForecastResponse convertToJson(String latitude, String longitude, List<String> clientResponse) {
		ForecastResponse response = new ForecastResponse();
		
		response.setLatitude(latitude);
		response.setLongitude(longitude);
		response.setForecastResponse(clientResponse);
		return response;
	}
	
}
