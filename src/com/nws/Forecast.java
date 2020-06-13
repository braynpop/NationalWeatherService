package com.nws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.nws.beans.ForecastDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;


public class Forecast {
	
	public static void getGridPoints(double latitude, double longitude) {
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("https://api.weather.gov/points/" + String.valueOf(latitude) + "," + String.valueOf(longitude));
		JsonObject gridPoints = target.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);
		
		int gridX = gridPoints.getJsonObject("properties").getInt("gridX");
		int gridY = gridPoints.getJsonObject("properties").getInt("gridY");
		String office = gridPoints.getJsonObject("properties").getString("cwa");
		System.out.println("gridX " + gridX + ", gridY " + gridY + ", office " + office);
		getForecast(gridX, gridY, office);
		
	}

	public static void getForecast(int gridX, int gridY, String office) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.weather.gov/gridpoints/" + office + "/" + String.valueOf(gridX) + "," + String.valueOf(gridY) + "/forecast");
		JsonObject forecastResponse = target.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);
		JsonArray forecast = forecastResponse.getJsonObject("properties").getJsonArray("periods");
		convertToForecastDetail(forecast);
		
	}
	
	public static void convertToForecastDetail(JsonArray forecast) {
		ArrayList<String> detailForecast = new ArrayList<String>(3);
		
		String detailedForecast = "";
		String fDate = "";
		String fStartTime = "";
		String fEndTime = "";
		String startTime = "00:00:00";
		String endTime = "11:59:59";
		int fDay = 0;
		
		for(JsonObject jsonobject : forecast.getValuesAs(JsonObject.class)) {
			boolean flag = false;
			detailedForecast = jsonobject.getString("detailedForecast");
			fDate = jsonobject.getString("startTime").split("T")[0];
			if(!fDate.equals(jsonobject.getString("endTime").split("T")[0])) {
				flag = true;
				fEndTime = endTime;
				fDay++;
			}else {
				fStartTime = jsonobject.getString("startTime").split("T")[1].split("-")[0];
				fEndTime = jsonobject.getString("endTime").split("T")[1].split("-")[0]; 		
			}
			detailForecast.add(fStartTime + " to " + fEndTime + " : " + detailedForecast);
			if(flag && fDay<=5) {
				flag = false;
				ForecastDetail nwsForecast = new ForecastDetail(fDate, detailForecast);
				System.out.println(nwsForecast.toString());
				detailForecast.clear();
				detailForecast.add(startTime + " to " + jsonobject.getString("endTime").split("T")[1].split("-")[0] + " : " + detailedForecast);
				
			}	
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println("Enter latitude value");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		double latitude = Double.parseDouble(reader.readLine());
		System.out.println("Enter longitude value");
		double longitude = Double.parseDouble(reader.readLine());
		System.out.println(latitude + " " + longitude);
		getGridPoints(latitude, longitude);
		//getGridPoints(39.7456, -97.0892);
	}
	

}
