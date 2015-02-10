package com.sb.ci.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.Team;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/team")
public class TeamService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getTeams() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection output = find.findEverything("team");
		 
		query.close();
		return output;

	}
	
	@GET
	@Path("/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getTeam(@PathParam("number") String teamNumber) throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Team> output = find.findSomething("team", "teamNumber", teamNumber);
		 
		query.close();
		return output;

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertTeam(Team team) {
		
		Response result = null;
		
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection test = find.findSomething("team", "teamNumber", team.getTeamNumber());
		
		if (test == null || test.size() == 0) {
			if (team.getTeamNumber() == null || team.getTeamNumber().equals("")) {
				result = Response.status(400).build();
			} else {
				Insert insert = new Insert(query);
				String id = insert.insertSomething(team);
				result = Response.ok(id).build();
			}
		} else {
			result = Response.ok().build();
		}
		
		query.close();
		
		return result;
	}


}
