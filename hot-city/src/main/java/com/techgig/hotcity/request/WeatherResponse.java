package com.techgig.hotcity.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 570223612839860594L;

	@JsonProperty(value = "city_temp")
	private Map<String, Float> cityTemp;
	
	@JsonProperty(value = "from_date")
	private LocalDateTime fromDateTime;
	
	@JsonProperty(value = "to_date")
	private LocalDateTime toDateTime;

	public Map<String, Float> getCityTemp() {
		return cityTemp;
	}

	public void setCityTemp(Map<String, Float> cityTemp) {
		this.cityTemp = cityTemp;
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
