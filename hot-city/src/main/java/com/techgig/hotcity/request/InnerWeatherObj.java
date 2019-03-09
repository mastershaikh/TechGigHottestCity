package com.techgig.hotcity.request;

import java.io.Serializable;
import java.util.Date;

public class InnerWeatherObj implements Serializable {

	private static final long serialVersionUID = 1437864414189326821L;
	
	private Date dt;
	private WeatherMain main;

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public WeatherMain getMain() {
		return main;
	}

	public void setMain(WeatherMain main) {
		this.main = main;
	}

}
