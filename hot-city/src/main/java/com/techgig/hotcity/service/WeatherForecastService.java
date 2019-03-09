package com.techgig.hotcity.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgig.hotcity.entity.RequestResponseEntity;
import com.techgig.hotcity.repository.RequestResponseRepository;
import com.techgig.hotcity.request.WeatherRequest;
import com.techgig.hotcity.request.WeatherResponse;
import com.techgig.hotcity.utils.HotCityUtils;
import com.techgig.hotcity.utils.RestAPIUtils;

@Service
public class WeatherForecastService {

	@Autowired
	private RestAPIUtils restApiUtils;

	@Autowired
	private RequestResponseRepository reqRespRepository;

	public WeatherResponse getWeatherOfCity(WeatherRequest request) {

		RequestResponseEntity reqResEntity = new RequestResponseEntity();
		reqResEntity.setWeatherRequest(request);

		WeatherResponse response = new WeatherResponse();
		List<String> cityList = request.getCityList();
		String hottestCity = "";
		Double maxTemp = 0.0;
		Map<String, String> cityTemp = new HashMap<>();
		for (String cityName : cityList) {
			try {
				Map<String, Object> resp = restApiUtils.getWeatherResponse(cityName);
				Map<String, Object> main = (HashMap) resp.get("main");
				Double temp = (Double) main.get("temp") - 273.15;
				if(temp>maxTemp) {
					maxTemp = temp;
					hottestCity = cityName;
				}
				cityTemp.put(cityName,  HotCityUtils.df2.format(temp) + " Celsius");
			} catch (Exception e) {
				// to be logged
			}
		}
		response.setCityTemp(cityTemp);
		response.setWeatherDate(LocalDate.now().plusDays(1));
		response.setHottestCity(hottestCity);
		reqResEntity.setWeatherResponse(response);
		reqRespRepository.saveAndFlush(reqResEntity);
		return response;
	}

}
