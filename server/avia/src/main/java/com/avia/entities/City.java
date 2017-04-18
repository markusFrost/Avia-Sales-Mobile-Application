package com.avia.entities;

import java.util.UUID;

public class City implements Comparable<City> {
	
	private UUID cityId;
	
	private String name;
	
	private String code;

	

	public UUID getCityId() {
		return cityId;
	}

	public void setCityId(UUID cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public int compareTo(City arg0)
	{
		return this.getCityId().compareTo(arg0.getCityId());
	}

}
