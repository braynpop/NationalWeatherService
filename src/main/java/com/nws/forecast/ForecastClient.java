package com.nws.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.nws.forecast.beans.ForecastDetail;


public class ForecastClient {
	
	public static List<String> getGridPoints(String latitude, String longitude) {
		System.out.println(ClientBuilder.class.getClassLoader());
		String url = "https://api.weather.gov/points/" + latitude + "," + longitude;
		System.out.println("test" +url);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.weather.gov/points/" + latitude + "," + longitude);
		JsonObject gridPoints = target.request(MediaType.APPLICATION_JSON_TYPE).header("UserAgent", "TMEPL").get(JsonObject.class);
		int gridX = gridPoints.getJsonObject("properties").getInt("gridX");
		int gridY = gridPoints.getJsonObject("properties").getInt("gridY");
		String office = gridPoints.getJsonObject("properties").getString("cwa");
		System.out.println("gridX " + gridX + ", gridY " + gridY + ", office " + office);
		return getForecast(gridX, gridY, office);
		
	}

	public static List<String> getForecast(int gridX, int gridY, String office) {
		Client client = ClientBuilder.newClient();
		String url = "https://api.weather.gov/gridpoints/" + office + "/" + String.valueOf(gridX) + "," + String.valueOf(gridY) + "/forecast";
		System.out.println("test" +url);
		
		WebTarget target = client.target("https://api.weather.gov/gridpoints/" + office + "/" + String.valueOf(gridX) + "," + String.valueOf(gridY) + "/forecast");
		JsonObject forecastResponse = target.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);
		//String forecastResponse = target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		//System.out.println(forecastResponse.toString());
		JsonArray forecast = forecastResponse.getJsonObject("properties").getJsonArray("periods");
		return convertToForecastDetail(forecast);
	}
	
	public static List<String> convertToForecastDetail(JsonArray forecast) {
		List<String> forecastDetails = new ArrayList<String>();
		
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
				fEndTime = jsonobject.getString("endTime").split("T")[1].split("-")[0]; 		
			}
			fStartTime = jsonobject.getString("startTime").split("T")[1].split("-")[0];
			detailForecast.add(fStartTime + " to " + fEndTime + " : " + detailedForecast);
			if(flag && fDay<=5) {
				flag = false;
				ForecastDetail nwsForecast = new ForecastDetail(fDate, detailForecast);
				System.out.println("Inside client" + nwsForecast.toString());
				forecastDetails.add(nwsForecast.toString());
				detailForecast.clear();
				detailForecast.add(startTime + " to " + jsonobject.getString("endTime").split("T")[1].split("-")[0] + " : " + detailedForecast);
				
			}	
		}
		return forecastDetails;
	}
	
	
}
