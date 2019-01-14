package com.sampleui.services.serialize;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JSONConverter {
	
	public Object convertToClass(String jsonString, Class className) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Object object = className.newInstance();
			object = mapper.readValue(jsonString, className);
			return object;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object convertJSONToClass(String jsonString, Class className) {
		try {
			return new Gson().fromJson(jsonString, className.newInstance().getClass());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static String convertClassToGson(Object object){		
		return new Gson().toJson(object);
		
		
	}
}
