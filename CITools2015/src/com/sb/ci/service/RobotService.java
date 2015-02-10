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

import com.sb.ci.model.Competition;
import com.sb.ci.model.Robot;
import com.sb.ci.model.Round;
import com.sb.ci.model.Team;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/robot")
public class RobotService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getRobots() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection output = find.findEverything("robot");

		query.close();
		return output;

	}
	
	@GET
	@Path("/{teamNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getRobot(@PathParam("teamNumber") String teamNumber) throws Exception {
		
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Team> data = find.findSomething("team", "teamNumber", teamNumber);
		
		Team team = new Team();
		if(data.size() >0){
			 team = data.iterator().next();
		}
		
		String teamId = team.getTeamId();
		
		Collection<Competition> data2 = find.findSomething("competition", "current", "1");
		
		Competition competition = new Competition();
		if(data.size() >0){
			competition = data2.iterator().next();
		}
		
		String gameId = competition.getGameId();
		
		Collection<Robot> output = find.findSomething("robot", new String[] {"teamId", "gameId"}, new String[] {teamId, gameId}, false, 0);
		
		query.close();
		return output;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertRobot(Robot robot) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Team> data = find.findSomething("team", "teamNumber", robot.getTeamNumber());
		Team team = data.iterator().next();
		robot.setTeamId(team.getTeamId());
		
		Collection<Competition> data2 = find.findSomething("competition", "current", "1");
		Competition comp = data2.iterator().next();
		robot.setGameId(comp.getGameId());
		

		if ((robot.getTeamId() != null || !robot.getTeamId().equals(""))
				&& (robot.getGameId() != null || !robot.getGameId()
						.equals(""))) {
			Insert insert = new Insert(query);
			String id = insert.insertSomething(robot);
			result = Response.ok(id).build();
		}

		query.close();

		return result;
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRobot(Robot robot) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(robot);
		result = Response.ok(id).build();

		query.close();

		return result;
	}


}
