package com.sampleui.services;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleui.services.pojo.UserInfo;
import com.sampleui.services.resources.LoginResource;

@Path("/loginservice")
public class LoginService {
	@POST
	@Path("/create")
	public Response insertData(String userDataJSON) {
		LoginResource loginResource = new LoginResource();
		UserInfo userinfo = loginResource.insertUser(userDataJSON);
		try {
			return Response.status(201).entity(new ObjectMapper().writeValueAsString(userinfo)).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
