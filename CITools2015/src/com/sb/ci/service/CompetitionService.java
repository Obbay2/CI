package com.sb.ci.service;

import java.sql.Connection;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sb.ci.model.Game;

import com.sb.ci.model.Competition;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/competition")
public class CompetitionService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getCompetitions() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection output = find.findEverything("competition");

		query.close();
		return output;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCompetition(Competition competition) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Game> data = find.findSomething("game", "year", competition.getYear());
		Game game = data.iterator().next();
		competition.setGameId(game.getGameId());

		if ((competition.getCompetitionOrder() != null || !competition
				.getCompetitionOrder().equals(""))
				&& (competition.getGameId() != null || !competition.getGameId()
						.equals(""))) {
			Insert insert = new Insert(query);
			String id = insert.insertSomething(competition);
			result = Response.ok(id).build();
		}

		query.close();

		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCompetition(Competition competition) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(competition);
		result = Response.ok(id).build();

		query.close();

		return result;
	}

}
