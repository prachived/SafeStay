package safety.dal;

import safety.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class IncidentsDao {
	protected ConnectionManager connectionManager;

	private static IncidentsDao instance = null;
	protected IncidentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static IncidentsDao getInstance() {
		if(instance == null) {
			instance = new IncidentsDao();
		}
		return instance;
	}

	public Incidents create(Incidents incident) throws SQLException {
		String insertIncident =
			"INSERT INTO Incidents(IncidentId,OffenseCode,District,ReportingArea,Shooting,OccuredOnDate,"
			+ "DayOfWeek,Hours,UCR,Location) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertIncident);
			insertStmt.setString(1, incident.getIncidentId());
			insertStmt.setInt(2, incident.getOffense().getOffenceCode());
			insertStmt.setString(3, incident.getDistrict());
			insertStmt.setInt(4, incident.getReportingArea());
			insertStmt.setString(5, incident.getShooting().name());
			insertStmt.setTimestamp(6, new Timestamp(incident.getOccuredOnDate().getTime()));
			insertStmt.setString(7, incident.getDayOfWeek());
			insertStmt.setInt(8, incident.getHour());
			insertStmt.setString(9, incident.getUcr().name());
			insertStmt.setString(10, incident.getAddress().getLocation());
			insertStmt.executeUpdate();
			return incident;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Incidents delete(Incidents incident) throws SQLException {
		String deleteIncident = "DELETE FROM Incidents WHERE IncidentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteIncident);
			deleteStmt.setString(1, incident.getIncidentId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Incidents getIncidentByIncidentId(String incidentId) throws SQLException {
		String selectIncident =
			"SELECT IncidentId,OffenseCode,District,ReportingArea,Shooting,OccuredOnDate,"
			+ "DayOfWeek,Hours,UCR,Location " +
			"FROM Incidents " +
			"WHERE IncidentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIncident);
			selectStmt.setString(1, incidentId);
			results = selectStmt.executeQuery();
			//OffensesDao offensesDao = OffensesDao.getInstance();
			//AddressesDao addressesDao = AddressesDao.getInstance();
			if(results.next()) {
				String resultincidentId = results.getString("IncidentId");
				//Offenses offense = offensesDao.get
				String district = results.getString("District");
				int reportingArea = results.getInt("ReportingArea");
				Incidents.Shootings shooting = Incidents.Shootings.valueOf(
						results.getString("Shooting"));
				Date occured =  new Date(results.getTimestamp("OccuredOnDate").getTime());
				String dayOfWeek = results.getString("DayOfWeek");
				int hour = results.getInt("Hours");
				Incidents.UCRs ucr = Incidents.UCRs.valueOf(results.getString("UCR"));
				//Addresses address = addressesDao.get
				//Incidents Incident = new Incidents(resultincidentId,offense,district,reportingArea,
				//		shooting,occured,dayOfWeek,hour,ucr,address);
				//return Incident;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Incidents> getIncidentByOffenceCode(int offenceCode) throws SQLException {
		List<Incidents> incidents = new ArrayList<Incidents>();
		String selectIncidents =
				"SELECT IncidentId,OffenseCode,District,ReportingArea,Shooting,OccuredOnDate,"
						+ "DayOfWeek,Hours,UCR,Location " +
						"FROM Incidents INNER JOIN Offense " +
						"  ON Incidents.OffenseCode = Offense.OffenseCode " +
						"WHERE Incidents.offenceCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIncidents);
			//OffensesDao offensesDao = OffensesDao.getInstance();
			//AddressesDao addressesDao = AddressesDao.getInstance();
			selectStmt.setInt(1, offenceCode);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultincidentId = results.getString("IncidentId");
				//Offenses offense = offensesDao.get
				String district = results.getString("District");
				int reportingArea = results.getInt("ReportingArea");
				Incidents.Shootings shooting = Incidents.Shootings.valueOf(
						results.getString("Shooting"));
				Date occured =  new Date(results.getTimestamp("OccuredOnDate").getTime());
				String dayOfWeek = results.getString("DayOfWeek");
				int hour = results.getInt("Hours");
				Incidents.UCRs ucr = Incidents.UCRs.valueOf(results.getString("UCR"));
				//Addresses address = addressesDao.get
				//Incidents incident = new Incidents(resultincidentId,offense,district,reportingArea,
				//		shooting,occured,dayOfWeek,hour,ucr,address);
				//incidents.add(incident);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return incidents;
	}
	
	public List<Incidents> getIncidentByLocation(String location) throws SQLException {
		List<Incidents> incidents = new ArrayList<Incidents>();
		String selectIncidents =
				"SELECT IncidentId,OffenseCode,District,ReportingArea,Shooting,OccuredOnDate,"
						+ "DayOfWeek,Hours,UCR,Location " +
						"FROM Incidents INNER JOIN Address " +
						"  ON Incidents.Location = Address.Location " +
						"WHERE Incidents.Location=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIncidents);
			//OffensesDao offensesDao = OffensesDao.getInstance();
			//AddressesDao addressesDao = AddressesDao.getInstance();
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultincidentId = results.getString("IncidentId");
				//Offenses offense = offensesDao.get
				String district = results.getString("District");
				int reportingArea = results.getInt("ReportingArea");
				Incidents.Shootings shooting = Incidents.Shootings.valueOf(
						results.getString("Shooting"));
				Date occured =  new Date(results.getTimestamp("OccuredOnDate").getTime());
				String dayOfWeek = results.getString("DayOfWeek");
				int hour = results.getInt("Hours");
				Incidents.UCRs ucr = Incidents.UCRs.valueOf(results.getString("UCR"));
				//Addresses address = addressesDao.get
				//Incidents incident = new Incidents(resultincidentId,offense,district,reportingArea,
				//		shooting,occured,dayOfWeek,hour,ucr,address);
				//incidents.add(incident);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return incidents;
	}

}
