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

import com.sb.ci.model.Game;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/game")
public class GameService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getGames() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection output = find.findEverything("game");

		query.close();
		return output;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertGame(Game game) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		Insert insert = new Insert(query);
		String id = insert.insertSomething(game);
		result = Response.ok(id).build();

		query.close();

		return result;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGame(Game game) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		Update update = new Update(query);
		String id = update.updateSomething(game);
		result = Response.ok(id).build();

		query.close();

		return result;
	}

}
