package com.abc.api.user.entity;

public class LocationEntity {

	private String country;
	private String state;
	private String dist;
	private String tq;
	private String region;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getTq() {
		return tq;
	}
	public void setTq(String tq) {
		this.tq = tq;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "LocationEntity [country=" + country + ", state=" + state + ", dist=" + dist + ", tq=" + tq + ", region="
				+ region + "]";
	}
	
	
}
