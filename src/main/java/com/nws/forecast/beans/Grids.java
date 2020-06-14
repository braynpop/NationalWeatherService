package com.nws.forecast.beans;

public class Grids {
	private int gridX;
	private int gridY;
	private String office;
	
	public Grids(int gridX, int gridY, String office) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.office = office;
	}
	
	public Grids() {
		// TODO Auto-generated constructor stub
	}

	public int getGridX() {
		return gridX;
	}
	public void setGridX(int gridX) {
		this.gridX = gridX;
	}
	public int getGridY() {
		return gridY;
	}
	public void setGridY(int gridY) {
		this.gridY = gridY;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}

}
