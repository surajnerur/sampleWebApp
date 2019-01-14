package com.sampleui.services.resources;

import com.sampleui.services.serialize.JSONConverter;
import com.sampleui.services.db.DBEngine;
import com.sampleui.services.management.PropertyFile;
import com.sampleui.services.management.QueryOperation;
import com.sampleui.services.pojo.UserInfo;

public class LoginResource {

	public UserInfo insertUser(String newuserJson) {
		JSONConverter converter = new JSONConverter();
		UserInfo userInfo = new UserInfo();
		userInfo = (UserInfo)converter.convertJSONToClass(newuserJson,UserInfo.class);
		DBEngine dbengine = new DBEngine();
		String idGenerated;
		try{
			String query = QueryOperation.formulateQuery(PropertyFile.getInstance().getProperty("qr-INSERT-USERINFO"), userInfo.getName(),userInfo.getAddress(),userInfo.getContactNum());
			idGenerated=dbengine.dbInsertANDReturnGeneratedKey(query);
			if(null!=idGenerated){
				userInfo = new UserInfo();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbengine.closeConnection();
		}
		return userInfo;
	}
}
