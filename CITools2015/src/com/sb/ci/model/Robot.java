package com.sb.ci.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.sb.ci.model.Competition;
import com.sb.ci.model.Game;
import com.sb.ci.model.Team;
import com.sb.ci.utility.Text;
import com.sb.database.Find;

public class Robot extends BasePOJO {

	public static String TABLE_NAME = "ROBOT";
	public static String ID_NAME = "ROBOT_ID";

	public static String ROBOT_ID = "ROBOT_ID";
	public static String TEAM_ID = "TEAM_ID";
	public static String GAME_ID = "GAME_ID";
	public static String DRIVE_TRAIN_TYPE = "DRIVE_TRAIN";
	public static String NUM_DRIVE_MOTORS = "DRIVE_TRAIN_MOTORS_NUMBER";
	public static String TYPE_DRIVE_MOTORS = "DRIVE_TRAIN_MOTORS_TYPES";
	public static String NUM_OTHER_MOTORS = "OTHER_MOTORS_NUMBER";
	public static String TYPE_OTHER_MOTORS = "OTHER_MOTORS_TYPES";
	public static String WEIGHT = "WEIGHT";
	public static String PROGRAMMING_LANGUAGE = "PROGRAM_LANGUAGE";
	public static String AUTONOMOUS = "AUTONOMOUS";
	public static String PICTURE = "PICTURE";
	public static String NOTES = "NOTES";
	public static String YEAR = "YEAR";
	public static String TEAM_NUMBER = "TEAM_NUMBER";
	public static String WATCHING = "WATCHING";

	protected String robotId = "";
	protected String teamId = "";
	protected String gameId = "";
	protected String driveTrain = "";
	protected String numDriveTrainMotors = "";
	protected String typeDriveTrainMotors = "";
	protected String numOtherMotors = "";
	protected String typeOtherMotors = "";
	protected String weight = "";
	protected String programmingLanguage = "";
	protected String autonomous = "";
	protected String picture = "";
	protected String notes = "";
	protected String year = "";
	protected String teamNumber = "";
	protected String watching = "";
	

	private static Map attributeMap = new HashMap();

	static {
		attributeMap.put(ROBOT_ID, Text.toCamel(ROBOT_ID));
		attributeMap.put(TEAM_ID, Text.toCamel(TEAM_ID));
		attributeMap.put(GAME_ID, Text.toCamel(GAME_ID));
		attributeMap.put(DRIVE_TRAIN_TYPE, Text.toCamel(DRIVE_TRAIN_TYPE));
		attributeMap.put(NUM_DRIVE_MOTORS, Text.toCamel(NUM_DRIVE_MOTORS));
		attributeMap.put(NUM_OTHER_MOTORS, Text.toCamel(NUM_OTHER_MOTORS));
		attributeMap.put(TYPE_DRIVE_MOTORS, Text.toCamel(TYPE_DRIVE_MOTORS));
		attributeMap.put(TYPE_OTHER_MOTORS, Text.toCamel(TYPE_OTHER_MOTORS));
		attributeMap.put(WEIGHT, Text.toCamel(WEIGHT));
		attributeMap.put(PROGRAMMING_LANGUAGE, Text.toCamel(PROGRAMMING_LANGUAGE));
		attributeMap.put(AUTONOMOUS, Text.toCamel(AUTONOMOUS));
		attributeMap.put(PICTURE, Text.toCamel(PICTURE));
		attributeMap.put(YEAR, Text.toCamel(YEAR));
		attributeMap.put(TEAM_NUMBER, Text.toCamel(TEAM_NUMBER));
		attributeMap.put(NOTES, Text.toCamel(NOTES));
		attributeMap.put(WATCHING, Text.toCamel(WATCHING));
	}

	public Robot() {

	}

	public Robot(Map data) {
		setDataMap(data);
	}

	@XmlTransient
	public String toString() {
		return "Robot ID: " + robotId + ", Drive Type: " + driveTrain + ", Number Of Drive Motors: "
				+ numDriveTrainMotors + ", Type Of Drive Motors: " + typeDriveTrainMotors
				+ ", Number Of Other Motors: " + numOtherMotors + ", Type Of Other Motors: " + typeOtherMotors
				+ ", Weight: " + weight + ", Programming Language: " + programmingLanguage + ", Autonomous: "
				+ autonomous + ", Picture: " + picture + ", Team Number: " + teamNumber + ", Year: " + year;
	}

	public String getRobotId() {
		return robotId;
	}

	public int getRobotIdInteger() {
		return Integer.parseInt(robotId);
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getDriveTrain() {
		return driveTrain;
	}

	public void setDriveTrain(String driveTrain) {
		this.driveTrain = driveTrain;
	}

	public String getNumDriveTrainMotors() {
		return numDriveTrainMotors;
	}

	public void setNumDriveTrainMotors(String numDriveTrainMotors) {
		this.numDriveTrainMotors = numDriveTrainMotors;
	}

	public String getTypeDriveTrainMotors() {
		return typeDriveTrainMotors;
	}

	public void setTypeDriveTrainMotors(String typeDriveTrainMotors) {
		this.typeDriveTrainMotors = typeDriveTrainMotors;
	}

	public String getNumOtherMotors() {
		return numOtherMotors;
	}

	public void setNumOtherMotors(String numOtherMotors) {
		this.numOtherMotors = numOtherMotors;
	}

	public String getTypeOtherMotors() {
		return typeOtherMotors;
	}

	public void setTypeOtherMotors(String typeOtherMotors) {
		this.typeOtherMotors = typeOtherMotors;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	public String getAutonomous() {
		return autonomous;
	}

	public void setAutonomous(String autonomous) {
		this.autonomous = autonomous;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@XmlTransient
	public String getTableName() {
		return TABLE_NAME;
	}

	@XmlTransient
	public String getIDName() {
		return ID_NAME;
	}

	@XmlTransient
	public Map getAtributeMap() {
		return attributeMap;
	}

	@XmlTransient
	public String getId() {
		return getRobotId();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAutonomousYesNo() {
		String out = null;
		if (autonomous.equals("1")) {
			out = "Yes";
		} else if (autonomous.equals("0")) {
			out = "No";
		} else {
			out = "-";
		}
		return out;
	}

	@Override
	public void setId(String id) {
		setRobotId(id);
	}

	public String getWatching() {
		return watching;
	}

	public void setWatching(String watching) {
		this.watching = watching;
	}
}
