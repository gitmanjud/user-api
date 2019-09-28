package com.abc.api.user.model;

import java.util.Date;

import com.abc.api.user.entity.LocationEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocationModel {

	private String country;
	private String state;
	private String dist;
	private String tq;
	private String region;
	private String desc;
	
}
