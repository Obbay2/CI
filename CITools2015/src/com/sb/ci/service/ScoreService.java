package com.sb.ci.service;

import java.sql.Connection;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Game;
import com.sb.ci.model.Round;
import com.sb.ci.model.Score;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;
import com.sb.database.Update;

@Path("/score")
public class ScoreService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection getScore() throws Exception {

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection output = find.findEverything("score");

		query.close();
		return output;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertScore(Score score) {

		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);

		if ((score.getRoundId() != null || !score.getRoundId().equals("")) && (score.getTeamId() != null || !score.getTeamId().equals("")) && (score.getStage() != null || !score.getStage().equals(""))) {
			Insert insert = new Insert(query);
			String id = insert.insertSomething(score);
			result = Response.ok(id).build();
		}

		query.close();

		return result;
	}
	
	@DELETE
	@Path("{number}")
	public Response deleteScore(@PathParam("number") String roundNumber){
		Response result = null;

		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);
		
		Collection<Competition> data = find.findSomething("competition", "current", "1");
		Competition comp = data.iterator().next();
		
		Collection<Round> data2 = find.findSomething("round", new String[] {"competitionId", "number"} , new String[] {comp.getCompetitionId(), roundNumber}, false, 0);
		String roundId = data2.iterator().next().getRoundId();
		
		query.execute("DELETE * FROM SCORE WHERE ROUND_ID = '" + roundId + "'");
		
		Collection<Score> data3 = find.findSomething("score", "roundId", roundId);
		
		if(data3 == null || data3.size() == 0 || data3.isEmpty()){
			result = Response.ok().build();
		}else{
			result = Response.ok().build();
		}
		return result;
	}

}
