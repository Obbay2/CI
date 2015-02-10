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
import com.sb.ci.model.Round;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/round")
public class RoundService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getRounds() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection output = find.findEverything("round");

		query.close();
		return output;

	}
	
	@GET
	@Path("{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getRound(@PathParam("number") String roundNumber) throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Competition> data = find.findSomething("competition", "current", "1");
		Competition comp = data.iterator().next();

		Collection output = find.findSomething("round", new String[] {"competitionId", "number"} , new String[] {comp.getCompetitionId(), roundNumber}, false, 0);

		query.close();
		return output;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertRound(Round round) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Competition> data = find.findSomething("competition", "current", "1");
		Competition comp = data.iterator().next();
		round.setCompetitionId(comp.getCompetitionId());

		if ((round.getCompetitionId() != null || !round.getCompetitionId()
				.equals(""))) {
			Insert insert = new Insert(query);
			String id = insert.insertRoundsAndTeams(round);
			result = Response.ok(id).build();
		}

		query.close();

		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRound(Round round) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(round);
		result = Response.ok(id).build();

		query.close();

		return result;
	}

}
