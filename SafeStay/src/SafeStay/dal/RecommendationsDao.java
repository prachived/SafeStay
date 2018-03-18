package SafeStay.dal;

import java.sql.*;

import java.util.*;

import SafeStay.model.Address;
import SafeStay.model.EndUsers;
import SafeStay.model.Recommendations;

public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;

	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}

	public static RecommendationsDao getInstance() {
		if (instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRec = "INSERT INTO Recommendations ( Rating, Petfriendly, Childfriendly, UserName, Location) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRec, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDouble(1, recommendation.getRating());
			insertStmt.setDouble(3, recommendation.getPetfriendly());
			insertStmt.setDouble(3, recommendation.getChildfriendly());
			insertStmt.setString(4, recommendation.getEndusers().getUserName());
			insertStmt.setString(5, recommendation.getAddress().getLocation());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if (resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);
			return recommendation;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Recommendations getRecommendationById(int recomid) throws SQLException {
		String selReview = "SELECT * " + "FROM Recommendations " + "WHERE recomid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selReview);
			selectStmt.setInt(1, recomid);
			results = selectStmt.executeQuery();
			EndUsersDao endusersDao = EndUsersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();

			if (results.next()) {
				int rRecommendationId = results.getInt("RecommendationId");
				String rUserName = results.getString("UserName");
				String rlocation = results.getString("Location");
				EndUsers renduser = endusersDao.getEndUserByUserName(rUserName);
				Address raddress = addressDao.getAddressByLocation(rlocation);
				double rrating = results.getDouble("Rating");
				double rpetfriendly = results.getDouble("petfriendly");
				double rchildfriendly = results.getDouble("Childfriendly");
				Recommendations recomm = new Recommendations(rRecommendationId, rpetfriendly, rchildfriendly, rrating,
						renduser, raddress);

				return recomm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Recommendations> getRecommendationsByLocation(String location) throws SQLException {
		List<Recommendations> recomList = new ArrayList<>();
		String selRecommendations = "SELECT * " + "FROM Recommendations " + "WHERE location=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selRecommendations);
			selectStmt.setString(1, location);
			results = selectStmt.executeQuery();
			EndUsersDao endusersDao = EndUsersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();

			if (results.next()) {
				int rRecommendationId = results.getInt("RecommendationId");
				String rUserName = results.getString("UserName");
				String rlocation = results.getString("Location");
				EndUsers renduser = endusersDao.getEndUserByUserName(rUserName);
				Address raddress = addressDao.getAddressByLocation(rlocation);
				double rrating = results.getDouble("Rating");
				double rpetfriendly = results.getDouble("petfriendly");
				double rchildfriendly = results.getDouble("Childfriendly");
				Recommendations recomm = new Recommendations(rRecommendationId, rpetfriendly, rchildfriendly, rrating,
						renduser, raddress);
				recomList.add(recomm);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return recomList;

	}

	public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
		List<Recommendations> recomList = new ArrayList<>();
		String selReview = "SELECT * " + "FROM Recommendations " + "WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selReview);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			EndUsersDao endusersDao = EndUsersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();

			if (results.next()) {
				int rRecommendationId = results.getInt("RecommendationId");
				String rUserName = results.getString("UserName");
				String rlocation = results.getString("Location");
				EndUsers renduser = endusersDao.getEndUserByUserName(rUserName);
				Address raddress = addressDao.getAddressByLocation(rlocation);
				double rrating = results.getDouble("Rating");
				double rpetfriendly = results.getDouble("Petfriendly");
				double rchildfriendly = results.getDouble("Childfriendly");
				Recommendations recomm = new Recommendations(rRecommendationId, rpetfriendly, rchildfriendly, rrating,
						renduser, raddress);
				recomList.add(recomm);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return recomList;

	}

	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String delRecom = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delRecom);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();
			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
