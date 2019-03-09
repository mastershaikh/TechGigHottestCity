package com.techgig.hotcity.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techgig.hotcity.request.WeatherRequest;
import com.techgig.hotcity.request.WeatherResponse;
import com.techgig.hotcity.service.WeatherForecastService;

@RestController
@RequestMapping(path = "/api/v1/hotcity")
public class HotCityController {
	
	@Autowired
	private WeatherForecastService service;
	
	@PostMapping(path = "/forecast")
	public ResponseEntity<WeatherResponse> postWeatherRequest(@RequestBody WeatherRequest request){
		WeatherResponse response = service.getWeatherOfCity(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
