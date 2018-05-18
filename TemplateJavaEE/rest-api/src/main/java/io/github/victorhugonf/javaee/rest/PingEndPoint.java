package io.github.victorhugonf.javaee.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("ping")
public class PingEndPoint {

	@GET
	public Response ping(){
		return Response.ok().build();
	}

}