package com.sampleui.services.management;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyFile {
	private static Properties prop;
	private static URL url;
	private static FileInputStream fs;
	public static final PropertyFile propertyFile=new PropertyFile();
	
	static {
		prop = new Properties();
		url = com.sampleui.services.management.PropertyFile.class.getResource("sample.properties");
		try {
			fs = new FileInputStream(url.getFile());
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static PropertyFile getInstance(){
		return propertyFile;
	}
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
