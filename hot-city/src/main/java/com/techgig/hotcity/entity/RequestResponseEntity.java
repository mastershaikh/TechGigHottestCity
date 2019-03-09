package com.techgig.hotcity.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techgig.hotcity.request.WeatherRequest;
import com.techgig.hotcity.request.WeatherResponse;
import com.techgig.hotcity.utils.HotCityUtils;

@Entity
@Table(name = "REQ_RES")
public class RequestResponseEntity implements Serializable {

	private static final long serialVersionUID = -775494633932530558L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "REQ")
	@JsonIgnore
	private String request;

	@Column(name = "RES")
	private String response;

	@Column(name = "DT")
	private Timestamp timeOfRequest = new Timestamp(new Date().getTime());

	@Transient
	private WeatherRequest weatherRequest;

	@Transient
	private WeatherResponse weatherResponse;

	public RequestResponseEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public WeatherRequest getWeatherRequest() {
		if (weatherRequest == null) {
			weatherRequest = new WeatherRequest();
			String[] requestArr = request.split("~");
			List<String> cityList = Arrays.asList(requestArr[0].split("#"));
			weatherRequest.setCityList(cityList);

			//Below two params are yet to be implemented when ranges of time is available in Open Weather API package
			weatherRequest.setFromDateTime(HotCityUtils.coverttoDateFromString(requestArr[1]));
			weatherRequest.setToDateTime(HotCityUtils.coverttoDateFromString(requestArr[2]));
		}
		return weatherRequest;
	}

	public void setWeatherRequest(WeatherRequest weatherRequest) {
		if (weatherRequest != null) {
			List<String> cityList = weatherRequest.getCityList();
			StringBuilder request = new StringBuilder();
			cityList.stream().forEach(city -> request.append(city).append("#"));
			request.append("~").append(weatherRequest.getFromDateTime());
			request.append("~").append(weatherRequest.getToDateTime());
		}
		this.weatherRequest = weatherRequest;
	}

	public WeatherResponse getWeatherResponse() {
		if (weatherResponse == null) {
			weatherResponse = new WeatherResponse();
			String[] requestArr = response.split("~");
			List<String> cityTempArr = Arrays.asList(requestArr[0].split("#"));
			Map<String, String> cityTemp = new HashMap<>();
			cityTempArr.stream().forEach(f -> {
				String[] cityTemp1 = f.split("-");
				cityTemp.put(cityTemp1[0], cityTemp1[1]);
			});
			weatherResponse.setCityTemp(cityTemp);

			weatherResponse.setWeatherDate(LocalDate.parse(requestArr[1]));
		}
		return weatherResponse;
	}

	public void setWeatherResponse(WeatherResponse weatherResponse) {
		if(weatherResponse!=null) {
			StringBuilder response = new StringBuilder();
			//Delimiting as per getWeatherResponse method
			weatherResponse.getCityTemp().entrySet().stream().forEach(e -> response.append(e.getKey()).append("-").append(e.getValue()).append("#"));
			response.append("~");
			response.append(weatherResponse.getWeatherDate());
			setResponse(response.toString());
		}
		this.weatherResponse = weatherResponse;
	}

	public Timestamp getTimeOfRequest() {
		return timeOfRequest;
	}

	public void setTimeOfRequest(Timestamp timeOfRequest) {

		this.timeOfRequest = timeOfRequest;
	}

}
