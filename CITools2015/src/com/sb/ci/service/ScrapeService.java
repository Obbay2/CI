package com.sb.ci.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sb.ci.model.Round;
import com.sb.ci.utility.POJOHelper;
import com.sb.database.ConnectionManager;
import com.sb.database.Find;
import com.sb.database.Insert;
import com.sb.database.Query;

@Path("/add")
public class ScrapeService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rounds")
	public ArrayList<Round> getRounds() {
		POJOHelper helper = new POJOHelper();
		
		String url = "http://www2.usfirst.org/2014comp/events/ORORE/ScheduleQual.html"; // document URL

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements all = doc.select("TD[align=center]"); // get the data table elements
		Elements columns = doc.select("b > span[style]"); // get the column name elements

		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> columnNames = new ArrayList<String>();

		for (int i = 0; i < all.size(); i++) {
			data.add(all.get(i).text()); // transfer the raw data to a string list
		}

		for (int i = 0; i < columns.size(); i++) {
			columnNames.add(columns.get(i).text());
		}

		int timeLoc = 0; // location of the "Time" (of the match) column in the columns list

		for (int i = 0; i < columnNames.size(); i++) {
			if (columnNames.get(i).equals("Time")) {
				timeLoc = i + 1;
			}
		}

		for (int i = 0; i < timeLoc; i++) { // remove all elements before the "Time" column (including "Time")
			columnNames.remove(0);
		}

		ArrayList<String> list = new ArrayList<String>(); // placeholder list for the column names

		for (int i = 0; i < columnNames.size(); i++) {
			if (columnNames.get(i) != null && columnNames.get(i) != "") { // remove any null strings in the columnNames list
				list.add(columnNames.get(i));
			}
		}
		columnNames = list;

		ArrayList<Round> rounds = new ArrayList<Round>();
		int size = data.size();
		for (int i = 0; i < size; i++) {
			Round round = new Round();
			for (int j = 0; j <= columnNames.size() - 1; j++) {
				if (!data.isEmpty()) {

					switch (j) { // organize the data into different lists
					case 0:
						round.setNumber((String) data.get(0));
						data.remove(0);
						break;
					case 1:
						round.setRed1Id((String) data.get(0));
						data.remove(0);
						break;
					case 2:
						round.setRed2Id((String) data.get(0));
						data.remove(0);
						break;
					case 3:
						round.setRed3Id((String) data.get(0));
						data.remove(0);
						break;
					case 4:
						round.setBlue1Id((String) data.get(0));
						data.remove(0);
						break;
					case 5:
						round.setBlue2Id((String) data.get(0));
						data.remove(0);
						break;
					case 6:
						round.setBlue3Id((String) data.get(0));
						data.remove(0);
						break;
					}
				}

			}
			round.setRoundId("999999");
	
			helper.setTeamIds(round);

			rounds.add(round);
		}
		return rounds;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rounds")
	public Response insertRounds() {
		
		Connection connection = ConnectionManager.getConnection();
		Query query = new Query(connection);
		Insert insert = new Insert(query);
		POJOHelper helper = new POJOHelper();
		
		ScrapeService test = new ScrapeService();
		ArrayList<Round> rounds = test.getRounds();

		for (int i = 0; i < rounds.size(); i++) {
			rounds.get(i).setRoundId("");
			helper.setCurrent(rounds.get(i));
			insert.insertRoundsAndTeams(rounds.get(i));
		}

		return Response.ok().build();
	}
}