package com.techgig.hotcity.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgig.hotcity.entity.RequestResponseEntity;
import com.techgig.hotcity.repository.RequestResponseRepository;
import com.techgig.hotcity.request.InnerWeatherObj;
import com.techgig.hotcity.request.OpenWeatherAPIResponse;
import com.techgig.hotcity.request.WeatherRequest;
import com.techgig.hotcity.request.WeatherResponse;
import com.techgig.hotcity.utils.RestAPIUtils;

@Service
public class HotCityService {

	@Autowired
	private RestAPIUtils restApiUtils;

	@Autowired
	private RequestResponseRepository reqRespRepository;

	public WeatherResponse getWeatherOfCity(WeatherRequest request) {

		RequestResponseEntity reqResEntity = new RequestResponseEntity();
		reqResEntity.setWeatherRequest(request);

		WeatherResponse response = new WeatherResponse();
		reqResEntity.setWeatherResponse(response);

		List<String> cityList = request.getCityList();
		LocalDateTime fromDtTime = request.getFromDateTime();
		LocalDateTime toDtTime = request.getToDateTime();

		for (String cityName : cityList) {
			try {
				OpenWeatherAPIResponse resp = (OpenWeatherAPIResponse)restApiUtils.getWeatherResponse(cityName);
				List<InnerWeatherObj> list = resp.getList();
				Map<String, Float> cityTemp = new HashMap<>();
				if (fromDtTime != null && toDtTime != null) {
					Date frDt = Date.from(fromDtTime.atZone(ZoneId.systemDefault()).toInstant());
					Date toDt = Date.from(toDtTime.atZone(ZoneId.systemDefault()).toInstant());
					Float temp = list.stream().filter(e -> e.getDt().before(frDt) && e.getDt().after(toDt))
							.map(InnerWeatherObj::getMain).findFirst().get().getTemp();

					cityTemp.put(cityName, temp);
				}else {
					Float temp = list.stream().map(InnerWeatherObj::getMain).findFirst().get().getTemp();
					cityTemp.put(cityName, temp);
				}
				response.setCityTemp(cityTemp);
			} catch (Exception e) {
				// to be logged
			}
		}
		reqRespRepository.saveAndFlush(reqResEntity);
		return response;
	}

}
