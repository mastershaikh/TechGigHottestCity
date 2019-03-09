package com.techgig.hotcity.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 570223612839860594L;

	@JsonProperty(value = "city_temp")
	private Map<String, String> cityTemp;

	@JsonProperty(value = "from_date")
	private LocalDateTime fromDateTime;

	@JsonProperty(value = "to_date")
	private LocalDateTime toDateTime;

	@JsonProperty(value = "weather_forecast_date")
	private LocalDate weatherDate;

	@JsonProperty(value = "hottest_city")
	private String hottestCity;

	public Map<String, String> getCityTemp() {
		return cityTemp;
	}

	public void setCityTemp(Map<String, String> cityTemp) {
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

	public LocalDate getWeatherDate() {
		return weatherDate;
	}

	public void setWeatherDate(LocalDate weatherDate) {
		this.weatherDate = weatherDate;
	}

	public String getHottestCity() {
		return hottestCity;
	}

	public void setHottestCity(String hottestCity) {
		this.hottestCity = hottestCity;
	}

}
