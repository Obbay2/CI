package com.sb.ci.utility;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

import com.sb.ci.model.BasePOJO;
import com.sb.ci.model.Competition;
import com.sb.ci.model.Game;
import com.sb.ci.model.Robot;
import com.sb.ci.model.Round;
import com.sb.ci.model.Team;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Query;

public class POJOHelper {
	public void setCompetitionYear(Competition competition) {
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection<Game> stuff = find.findSomething("game", "gameId",
				competition.getGameId());
		Game game = stuff.iterator().next();
		competition.setYear(game.getYear());

		query.close();
	}

	public void setMisc(Robot robot) {
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection<Team> data = find.findSomething("team", "teamId",
				robot.getTeamId());
		Team team = data.iterator().next();
		robot.setTeamNumber(team.getTeamNumber());

		// Collection<Game> data2 = find.findSomething("game", "year",
		// robot.getYear());
		// Game game = data2.iterator().next();
		// robot.setYear(game.getYear());

		query.close();
	}

	public void setCurrentYear(Robot robot) {
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection<Competition> data = find.findSomething("competition",
				"current", "1");
		Competition comp = data.iterator().next();
		robot.setGameId(comp.getGameId());

		Collection<Game> data2 = find.findSomething("game", "gameId",
				comp.getGameId());
		Game game = data2.iterator().next();
		robot.setYear(game.getYear());

		query.close();
	}

	public void setTeamNumbers(Round round) {
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection<Team> data = find.findSomething("team", "teamId",
				round.getRed1Id());
		Team team = data.iterator().next();
		round.setRed1Number(team.getTeamNumber());

		Collection<Team> data2 = find.findSomething("team", "teamId",
				round.getRed2Id());
		Team team2 = data2.iterator().next();
		round.setRed2Number(team2.getTeamNumber());

		Collection<Team> data3 = find.findSomething("team", "teamId",
				round.getRed3Id());
		Team team3 = data3.iterator().next();
		round.setRed2Number(team3.getTeamNumber());

		Collection<Team> data4 = find.findSomething("team", "teamId",
				round.getBlue1Id());
		Team team4 = data4.iterator().next();
		round.setRed2Number(team4.getTeamNumber());

		Collection<Team> data5 = find.findSomething("team", "teamId",
				round.getBlue2Id());
		Team team5 = data5.iterator().next();
		round.setRed2Number(team5.getTeamNumber());

		Collection<Team> data6 = find.findSomething("team", "teamId",
				round.getBlue3Id());
		Team team6 = data6.iterator().next();
		round.setRed2Number(team6.getTeamNumber());

		query.close();

	}

	public void setTeamIds(Round round) {
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection<Team> data = find.findSomething(
				"team",
				"SELECT * FROM TEAM WHERE TEAM_NUMBER IN('"
						+ round.getBlue1Number() + "', '"
						+ round.getBlue2Number() + "', '"
						+ round.getBlue3Number() + "', '"
						+ round.getRed1Number() + "', '" + round.getRed2Number()
						+ "', '" + round.getRed3Number() + "')");

		Object[] test = data.toArray();
		Team team = new Team();
		
		for (int i = 0; i < data.size(); i++) {
			team = (Team) test[i];
			
			if(round.getBlue1Number().equals(team.getTeamNumber())){
				round.setBlue1Id(team.getTeamId());
			}
			else if(round.getBlue2Number().equals(team.getTeamNumber())){
				round.setBlue2Id(team.getTeamId());
			}
			else if(round.getBlue3Number().equals(team.getTeamNumber())){
				round.setBlue3Id(team.getTeamId());
			}
			else if(round.getRed1Number().equals(team.getTeamNumber())){
				round.setRed1Id(team.getTeamId());
			}
			else if(round.getRed2Number().equals(team.getTeamNumber())){
				round.setRed2Id(team.getTeamId());
			}
			else if(round.getRed3Number().equals(team.getTeamNumber())){
				round.setRed3Id(team.getTeamId());
			}
		}
		
		query.close();
	}

	public void setCurrent(Round round) {
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Find find = new Find(query);

		Collection<Competition> data = find.findSomething("competition",
				"current", "1");
		Competition comp = data.iterator().next();
		round.setCompetitionId(comp.getCompetitionId());

		query.close();
	}
}
