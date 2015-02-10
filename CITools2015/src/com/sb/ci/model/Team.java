package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Team extends BasePOJO {

	public static String TABLE_NAME = "TEAM";

	public static String TEAM_ID = "TEAM_ID";
	public static String TEAM_NUMBER = "TEAM_NUMBER";
	public static String TEAM_NAME = "TEAM_NAME";
	public static String SCHOOL = "SCHOOL";
	public static String CITY = "CITY";
	public static String STATE = "STATE";
	public static String LEADER = "LEADER";
	public static String YEAR_FOUNDED = "YEAR_FOUNDED";
	public static String NOTES = "NOTES";

	public static String PARAMETER_TEAM_ID = "teamId";
	public static String PARAMETER_TEAM_NUMBER = "teamNumber";
	public static String PARAMETER_TEAM_NAME = "teamName";
	public static String PARAMETER_SCHOOL_NAME = "schoolName";
	public static String PARAMETER_CITY = "city";
	public static String PARAMETER_STATE = "state";
	public static String PARAMETER_LEADER_FULL_NAME = "leaderFullName";
	public static String PARAMETER_YEAR_FOUNDED = "yearFounded";
	public static String PARAMETER_NOTES = "notes";

	protected String teamId = "";
	protected String teamNumber = "";
	protected String teamName = "";
	protected String schoolName = "";
	protected String city = "";
	protected String state = "";
	protected String leaderFullName = "";
	protected String yearFounded = "";
	protected String notes = "";

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(TEAM_ID, PARAMETER_TEAM_ID);
		attributeMap.put(TEAM_NUMBER, PARAMETER_TEAM_NUMBER);
		attributeMap.put(TEAM_NAME, PARAMETER_TEAM_NAME);
		attributeMap.put(SCHOOL, PARAMETER_SCHOOL_NAME);
		attributeMap.put(CITY, PARAMETER_CITY);
		attributeMap.put(STATE, PARAMETER_STATE);
		attributeMap.put(LEADER, PARAMETER_LEADER_FULL_NAME);
		attributeMap.put(YEAR_FOUNDED, PARAMETER_YEAR_FOUNDED);
		attributeMap.put(NOTES, PARAMETER_NOTES);
	}

	public String toString() {
		return "TEAM_ID: " + teamId + ", TEAM_NUMBER: " + teamNumber + ", TEAM_NAME: " + teamName + ", SCHOOL: "
				+ schoolName + ", CITY: " + city + ", STATE: " + state + ", YEAR_FOUNDED: " + yearFounded + ", NOTES: "
				+ notes;
	}

	@XmlTransient
	public String[] getData() {
		String[] data = { teamId, teamNumber, teamName, schoolName, city, state, leaderFullName, yearFounded, notes };
		return data;
	}

	public Team() {
	}

	public Team(Map data) {
		setDataMap(data);
	}

	@XmlTransient
	public String getTableName() {
		return TABLE_NAME;
	}

	@XmlTransient
	public Map getAtributeMap() {
		return attributeMap;
	}

	@XmlTransient
	public String getId() {
		return getTeamId();
	}

	@XmlTransient
	public String getIDName() {
		return TEAM_ID;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public int getTeamNumberInteger() {
		return Integer.parseInt(teamNumber);
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLeaderFullName() {
		return leaderFullName;
	}

	public void setLeaderFullName(String leaderFullName) {
		this.leaderFullName = leaderFullName;
	}

	public String getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(String yearFounded) {
		this.yearFounded = yearFounded;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setId(String id) {
		setTeamId(id);
	}
}
