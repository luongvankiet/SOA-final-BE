package com.finalproject.Account;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("auth")
public class AccountServices {
	AccountDao accDao = new AccountDao();
	
	//login service
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Account acc) {
		return accDao.login(acc);
	}
	
	//register service
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(Account acc) {
		return accDao.register(acc);
	}
}
