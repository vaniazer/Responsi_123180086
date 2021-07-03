package com.example.covid.model.kasus;

import com.google.gson.annotations.SerializedName;

public class KasusResponse{

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("data")
	private Data data;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}