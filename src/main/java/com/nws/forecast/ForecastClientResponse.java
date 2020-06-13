package com.nws.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class ForecastClientResponse {
	
	public static void main(String[] args) throws IOException{
		System.out.println("Enter latitude, longitude");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		String coordinates= reader.readLine();
		String latitude = coordinates.split(",")[0].trim();
		System.out.println("latitude"+ latitude);
		String longitude = coordinates.split(",")[1].trim();
		System.out.println("longitude"+ longitude);
		List<String> forecastResponse = ForecastClient.getGridPoints(latitude, longitude);
		//getGridPoints("39.7456", "-97.0892");
		
		//getForecast(31, 80, "TOP");
		System.out.println(convertToJson(latitude, longitude, forecastResponse));

	}
	

	public static String convertToJson(String latitude, String longitude, List<String> clientResponse) {
		String response = "ForecastDetailResponse {latitude:" + latitude + ", longitude:" + longitude + ", detailedForecast:" + clientResponse + "}";
		
		return response;
	}
	
}
