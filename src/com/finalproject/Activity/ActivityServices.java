package com.finalproject.Activity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("activities")
public class ActivityServices {
	ActivityDAO acDao = new ActivityDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActivities(){
		return acDao.getAllActivities();
	}
	
	@GET
	@Path("semester/{semesterID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActivitiesBySemesterID(@PathParam("semesterID") int semesterID){
		return acDao.getActivitiesBySemesterID(semesterID);
	}
	
	@GET
	@Path("student/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActivitiesByStudentID(@PathParam("studentID") String studentID){
		return acDao.getActivitiesByStudentID(studentID);
	}
	
	@POST
	@Path("student/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response onParticipateInActivity(@PathParam("studentID") String studentID, Activity activity){
		return acDao.onParticipateInActivity(studentID, activity);
	}
	
	@GET
	@Path("student")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showActivities(){
		return acDao.showActivities();
	}
	
}
