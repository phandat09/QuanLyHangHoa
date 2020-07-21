package com.inventory_manegement.contronller.Ajax;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxResonse {
	private int statusCode;
	private Object data; // annything........

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public AjaxResonse(int statusCode, Object data) {
		super();
		this.statusCode = statusCode;
		
		if(data instanceof String) {
			this.data = data;
		} else {
			try {
				this.data = new ObjectMapper().writeValueAsString(data);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				this.data = data;
			}
		}
			
	}

}
