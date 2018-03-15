package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import safety.model.*;

public class UserAddressLogsDao {
	protected ConnectionManager connectionManager;

	private static UserAddressLogsDao instance = null;
	protected UserAddressLogsDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserAddressLogsDao getInstance() {
		if(instance == null) {
			instance = new UserAddressLogsDao();
		}
		return instance;
	}

	public UserAddressLogs create(UserAddressLogs userAddressLog) throws SQLException {
		String insertUserAddressLog =
			"INSERT INTO User_address_log(UserName,Location,Start_date,End_date) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserAddressLog,
					 Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, userAddressLog.getEndUser().getUserName());
			insertStmt.setString(2, userAddressLog.getAddress().getLocation());
			insertStmt.setTimestamp(3, new Timestamp(userAddressLog.getStartDate().getTime()));
			insertStmt.setTimestamp(4, new Timestamp(userAddressLog.getEndDate().getTime()));
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int userAddressId = -1;
			if(resultKey.next()) {
				userAddressId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			userAddressLog.setUserAddressId(userAddressId);
			return userAddressLog;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	public UserAddressLogs delete(UserAddressLogs userAddressLog) throws SQLException {
		String deleteUserAddressLog = "DELETE FROM User_address_log WHERE User_address_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserAddressLog);
			deleteStmt.setInt(1, userAddressLog.getUserAddressId());
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

	public UserAddressLogs getUALogsByUserAddressId(int userAddressId) throws SQLException {
		String selectUserAddressLog =
			"SELECT User_address_id,UserName,Location,Start_date,End_date " +
			"FROM User_address_log " +
			"WHERE User_address_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserAddressLog);
			selectStmt.setInt(1, userAddressId);
			results = selectStmt.executeQuery();
			//EndUsersDao endUsersDao = EndUsersDao.getInstance();
			//AddressesDao addressesDao = AddressesDao.getInstance();
			if(results.next()) {
				int resultuserAddressId = results.getInt("User_address_id");
				//EndUsers userName = endUsersDao.get
				//Addresses location = addressesDao.get
				Date start =  new Date(results.getTimestamp("Start_date").getTime());
				Date end =  new Date(results.getTimestamp("End_date").getTime());
				//UserAddressLogs userAddressLog = new UserAddressLog(resultuserAddressId,userName,
				//		location,start,end);
				//return userAddressLog;
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
	
	public List<UserAddressLogs> getUALogsByUserName(String userName) throws SQLException {
		List<UserAddressLogs> userAddressLogs = new ArrayList<UserAddressLogs>();
		String selectUserAddressLog =
			"SELECT User_address_id,UserName,Location,Start_date,End_date " +
					"FROM User_address_log INNER JOIN EndUsers " +
					"  ON User_address_log.UserName = EndUsers.UserName " +
					"WHERE User_address_log.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserAddressLog);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			//EndUsersDao endUsersDao = EndUsersDao.getInstance();
			//AddressesDao addressesDao = AddressesDao.getInstance();
			while(results.next()) {
				int resultuserAddressId = results.getInt("User_address_id");
				//EndUsers userName = endUsersDao.get
				//Addresses location = addressesDao.get
				Date start =  new Date(results.getTimestamp("Start_date").getTime());
				Date end =  new Date(results.getTimestamp("End_date").getTime());
				//UserAddressLogs userAddressLog = new UserAddressLog(resultuserAddressId,userName,
				//		location,start,end);
				//userAddressLogs.add(userAddressLog);
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
		return userAddressLogs;
	}
	
	public List<UserAddressLogs> getUALogsByLocation(String location) throws SQLException {
		List<UserAddressLogs> userAddressLogs = new ArrayList<UserAddressLogs>();
		String selectUserAddressLog =
			"SELECT User_address_id,UserName,Location,Start_date,End_date " +
					"FROM User_address_log INNER JOIN Address " +
					"  ON User_address_log.Location = Address.Location " +
					"WHERE User_address_log.Location=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserAddressLog);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			//EndUsersDao endUsersDao = EndUsersDao.getInstance();
			//AddressesDao addressesDao = AddressesDao.getInstance();
			while(results.next()) {
				int resultuserAddressId = results.getInt("User_address_id");
				//EndUsers userName = endUsersDao.get
				//Addresses location = addressesDao.get
				Date start =  new Date(results.getTimestamp("Start_date").getTime());
				Date end =  new Date(results.getTimestamp("End_date").getTime());
				//UserAddressLogs userAddressLog = new UserAddressLog(resultuserAddressId,userName,
				//		location,start,end);
				//userAddressLogs.add(userAddressLog);
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
		return userAddressLogs;
	}


}
