package com.techgig.hotcity.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherRequest implements Serializable {

	private static final long serialVersionUID = 9000395924971080566L;

	@JsonProperty(value = "city_list", required = true)
	private List<String> cityList;
	
	@JsonProperty(value = "from_date", required = false)
	private LocalDateTime fromDateTime;
	
	@JsonProperty(value = "to_date", required = false)
	private LocalDateTime toDateTime;

	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

}
