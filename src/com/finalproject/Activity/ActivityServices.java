package com.finalproject.Activity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("activities")
public class ActivityServices {
	ActivityDAO acDao = new ActivityDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotification(){
		return acDao.getAllActivities();
	}
}
