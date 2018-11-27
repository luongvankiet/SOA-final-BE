package com.finalproject.Notification;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("notification")
public class NotificationServices {
	NotificationDAO noDao = new NotificationDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotification(){
		return noDao.getAllNotification();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotification(@PathParam("id") int id){
		return noDao.getNotification(id);
	}
}
