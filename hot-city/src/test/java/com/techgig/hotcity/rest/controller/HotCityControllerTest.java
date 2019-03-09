package com.techgig.hotcity.rest.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.techgig.hotcity.request.WeatherRequest;
import com.techgig.hotcity.request.WeatherResponse;
import com.techgig.hotcity.service.WeatherForecastService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = WeatherForecastService.class)
public class HotCityControllerTest {

	@InjectMocks
	private HotCityController controller;
	
	@Mock
	private WeatherForecastService service;
	
	private WeatherRequest request;
	private WeatherResponse response;
	
	@Before
	public void setUp() {
		request = new WeatherRequest();
		List<String> cityList = Arrays.asList("Hyderabad,IN", "Delhi,IN");
		request.setCityList(cityList);
		
		Map<String, String> cityTemp = new HashMap<>();
		cityTemp.put("Hyderabad,IN", "34.04 Celsius");
		response.setCityTemp(cityTemp);
	}
	
	@Test
	public void test() {
		PowerMockito.when(service.getWeatherOfCity(Mockito.any())).thenReturn(response);
		controller.postWeatherRequest(request);
		
	}
}
