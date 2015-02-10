package com.sb.ci.service;

import java.sql.Connection;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.RobotDetails;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/robotdetails")
public class RobotDetailsService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getRobotDetails() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection output = find.findEverything("robotDetails");

		query.close();
		return output;

	}
	
	@GET
	@Path("/{robotId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getRobotDetails(@PathParam("robotId") String robotId) throws Exception {
		
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<RobotDetails> output = find.findSomething("robotDetails", "robotId", robotId);
		
		query.close();
		return output;
	}
	
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertRobotDetails(RobotDetails robotDetails) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		
		if(robotDetails.getRobotId() != null || !robotDetails.getRobotId().equals("")){
			Insert insert = new Insert(query);
			String id = insert.insertSomething(robotDetails);
			result = Response.ok(id).build();
		}

		query.close();

		return result;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRobotDetails(RobotDetails robotDetails) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(robotDetails);
		result = Response.ok(id).build();

		query.close();

		return result;
	}
	
}
