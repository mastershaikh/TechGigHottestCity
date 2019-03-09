package com.techgig.hotcity.request;

import java.io.Serializable;
import java.util.List;

public class OpenWeatherAPIResponse implements Serializable {

	private static final long serialVersionUID = 248502294823034716L;

	private String cod;
	private Double message;
	private Integer cnt;

	private List<InnerWeatherObj> list;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Double getMessage() {
		return message;
	}

	public void setMessage(Double message) {
		this.message = message;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public List<InnerWeatherObj> getList() {
		return list;
	}

	public void setList(List<InnerWeatherObj> list) {
		this.list = list;
	}

}
