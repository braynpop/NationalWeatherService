package com.nws.forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nws.forecast.beans.ForecastDetail;
import com.nws.forecast.beans.Grids;


public class ForecastClient {
	
	public Grids getGridPoints(String latitude, String longitude) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.weather.gov/points/").path(latitude + "," + longitude);
		Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).header("UserAgent", "TMEPL").get();
		if(resp.getStatus()!=200) {
			throw new NotFoundException("status:" + resp.getStatus() + "\nmessage:" + resp.getStatusInfo() + 
					"\nerror details:unable to process the request, Please enter valid latitude and longitude values");
		}
		JsonObject gridPoints = resp.readEntity(JsonObject.class);
		
		int gridX = gridPoints.getJsonObject("properties").getInt("gridX");
		int gridY = gridPoints.getJsonObject("properties").getInt("gridY");
		String office = gridPoints.getJsonObject("properties").getString("cwa");
		System.out.println("gridX " + gridX + ", gridY " + gridY + ", office " + office);
		return new Grids(gridX, gridY, office);
		//return getForecast(gridX, gridY, office);
		
	}

	public List<String> getForecast(Grids grids) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.weather.gov/gridpoints/").path(grids.getOffice() + "/" + String.valueOf(grids.getGridX()) + "," + String.valueOf(grids.getGridY()) + "/forecast");
		Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).header("UserAgent", "TMEPL").get();
		System.out.println("status:" + resp.getStatus() + "/n message:" + resp.getStatusInfo());
		if(resp.getStatus()!=200) {
			throw new NotFoundException("status:" + resp.getStatus() + "\nmessage:" + resp.getStatusInfo() + 
					"\nerror details:unable to process the request.");
		}
		JsonObject forecastResponse = resp.readEntity(JsonObject.class);
		JsonArray forecast = forecastResponse.getJsonObject("properties").getJsonArray("periods");
		return convertToForecastDetail(forecast);
	}
	
	public List<String> convertToForecastDetail(JsonArray forecast) {
		List<String> forecastDetails = new ArrayList<String>();
		ArrayList<String> dailyForecast = new ArrayList<String>(3);
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
			dailyForecast.add(fStartTime + " to " + fEndTime + " : " + detailedForecast);
			if(flag && fDay<=5) {
				flag = false;
				ForecastDetail nwsForecast = new ForecastDetail(fDate, dailyForecast);
				//System.out.println("Inside client" + nwsForecast.toString());
				forecastDetails.add(nwsForecast.toString());
				dailyForecast.clear();
				dailyForecast.add(startTime + " to " + jsonobject.getString("endTime").split("T")[1].split("-")[0] + " : " + detailedForecast);
				
			}	
		}
		return forecastDetails;
	}
	
	
}
