package com.techgig.hotcity.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.techgig.hotcity.exception.CityNotFoundException;

@Service
public class RestAPIUtils {

	private RestTemplate template;

	@Value("${open.weather.url}")
	private String weatherEndpoint;

	@Value("${open.weather.timeout}")
	private Integer timeOut;

	@PostConstruct
	public void init() {

		HttpComponentsClientHttpRequestFactory conFact = new HttpComponentsClientHttpRequestFactory();
		template = new RestTemplate(conFact);
		template.setMessageConverters(getMessageConverters());
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		jsonMessageConverter.setObjectMapper(mapper);
		messageConverters.add(jsonMessageConverter);
		return messageConverters;
	}

	private HttpHeaders getHttpHeaders() {
		HttpHeaders tmpHeaders = new HttpHeaders();
		tmpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return tmpHeaders;
	}

	public Map<String, Object> getWeatherResponse(String cityName) throws Exception {
		Map<String, Object> response = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityName", cityName);
		params.put("appKey", "7b66d0223cb38764b89fa93386442271");
		params.put("noOfDays", "1");
		try {
			HttpEntity requestEntity = new HttpEntity(getHttpHeaders());
			ResponseEntity<HashMap> responseEntity = template.getForEntity(weatherEndpoint, HashMap.class, params);
			response = responseEntity.getBody();
		} catch (HttpStatusCodeException httpStatusExp) {
			if (httpStatusExp.getStatusCode().is4xxClientError()) {
				throw new CityNotFoundException("This city " + cityName + " weather is missing in OPEN Weather API");
			}
		} catch (RestClientException exp) {
			throw new Exception("Bad response from " + weatherEndpoint, exp);
		}
		return response;
	}
}
