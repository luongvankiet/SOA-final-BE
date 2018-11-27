package com.finalproject.Semester;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("semester")
public class SemesterServices {
	SemesterDAO semDao = new SemesterDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSemester(){
		return semDao.getAllSemester();
	}
}
