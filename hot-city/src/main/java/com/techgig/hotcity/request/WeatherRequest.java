package com.techgig.hotcity.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class WeatherRequest implements Serializable {

	private static final long serialVersionUID = 9000395924971080566L;

	private List<String> cityList;
	private LocalDateTime fromDateTime;
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
