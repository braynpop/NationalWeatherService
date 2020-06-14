package com.nws.forecast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import javax.ws.rs.NotFoundException;

import org.junit.Test;

import com.nws.forecast.beans.Grids;

public class ForecastClientTest {
	
	private ForecastClient clientTest = new ForecastClient();
	
	@Test
	public void invalid_coordinates_getGridPoints(){
		clientTest.getGridPoints("xxx", "YYY");
		assertThrows(NotFoundException.class, ()->Double.parseDouble("xxx"));
				
	}
	
	@Test
	public void valid_coordinates_getGridPoints(){
		Grids gridTest = clientTest.getGridPoints("39.7456", "-97.0892");
		assertEquals(gridTest.getOffice(), "TOP");
				
	}

}
