package by.epam.rafalovich.railway_tickets.entity;

public class Region extends Entity {

	private long countryId;
	private String regionName;
	
	public long getCountryId() {
		return countryId;
	}
	
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	
	public String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}	
	
}
