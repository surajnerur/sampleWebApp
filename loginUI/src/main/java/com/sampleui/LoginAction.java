package com.sampleui;

public class LoginAction {

	private String name;
	private String address;
	private String contactNum;
	
	public String execute() throws Exception {
	      return "success";
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
