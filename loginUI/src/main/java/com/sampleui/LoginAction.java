package com.sampleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.WinHttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sampleui.pojo.UserInfo;

public class LoginAction {

	private String name;
	private String address;
	private String contactNum;
	
	public String execute() throws Exception {
		try{
			sendRequestToService();
	      return "success";
		}catch (Exception e){
			return "error";
		}
	 }
	
	private String sendRequestToService() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://localhost:8080/sampleui-services/sample/loginservice/create");
		httpPost.addHeader("accept","text/plain");
		httpPost.addHeader("content-type","text/plain");
		
		StringEntity params = new StringEntity(buildData(),"UTF-8");
		httpPost.setEntity(params);
		
		HttpResponse httpResponse = WinHttpClients.custom().build().execute(httpPost);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String line;
		StringBuffer output = new StringBuffer();
		while((line=br.readLine())!=null) {
			output.append(line);
		}
		return output.toString();
	}
	private String buildData() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setAddress(address);
		userInfo.setContactNum(contactNum);
		
		Gson gson = new GsonBuilder().create();
		String data = gson.toJson(userInfo);
		return data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
}
