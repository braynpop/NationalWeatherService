package com.nws.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nws.forecast.beans.ForecastResponse;
import com.nws.forecast.beans.Grids;


public class ForecastClientResponse {
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("Enter latitude, longitude");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		String coordinates= reader.readLine();
		String latitude = coordinates.split(",")[0].trim();
		System.out.println("latitude"+ latitude);
		String longitude = coordinates.split(",")[1].trim();
		System.out.println("longitude"+ longitude);
		
		ObjectMapper obj = new ObjectMapper();
		ForecastClient client = new ForecastClient();
		Grids grids = new Grids();
		//List<String> forecastResponse = ForecastClient.getGridPoints(latitude, longitude);
		grids = client.getGridPoints(latitude, longitude);
		//getGridPoints("39.7456", "-97.0892");
		
		List<String> forecastResponse = client.getForecast(grids);
		String jsonResponse = obj.writeValueAsString(convertToJson(latitude, longitude, forecastResponse));
		System.out.println(jsonResponse);

	}
	
	public static ForecastResponse convertToJson(String latitude, String longitude, List<String> clientResponse) {
		ForecastResponse response = new ForecastResponse();
		
		response.setLatitude(latitude);
		response.setLongitude(longitude);
		response.setForecastResponse(clientResponse);
		return response;
	}
	
}
