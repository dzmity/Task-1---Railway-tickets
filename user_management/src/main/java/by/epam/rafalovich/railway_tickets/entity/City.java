package by.epam.rafalovich.railway_tickets.entity;

public class City extends Entity{
	
	private String cityName;
	private long regionId;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public long getRegionId() {
		return regionId;
	}
	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}
	
	

}
