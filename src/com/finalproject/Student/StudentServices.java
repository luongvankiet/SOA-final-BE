package com.finalproject.Student;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("students")
public class StudentServices {
	StudentDAO studentDAO = new StudentDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudent(){
		return studentDAO.getAllStudents();
	}
	
	@GET
	@Path("{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentByEmail(@PathParam("email") String email){
		return studentDAO.getStudentByEmail(email);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewStudent(Student student){
		return studentDAO.createNewStudent(student);
	}
}
