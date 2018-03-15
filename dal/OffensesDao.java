package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import safety.model.*;

public class OffensesDao {
	protected ConnectionManager connectionManager;

	private static OffensesDao instance = null;
	protected OffensesDao() {
		connectionManager = new ConnectionManager();
	}
	public static OffensesDao getInstance() {
		if(instance == null) {
			instance = new OffensesDao();
		}
		return instance;
	}

	public Offenses create(Offenses offense) throws SQLException {
		String insertOffense =
			"INSERT INTO Offenses(OffenseCode,Description,CodeGroup) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOffense);
			insertStmt.setInt(1, offense.getOffenceCode());
			insertStmt.setString(2, offense.getDescription());
			insertStmt.setString(3, offense.getCodeGroup());
			insertStmt.executeUpdate();
			return offense;
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

	public Offenses delete(Offenses offense) throws SQLException {
		String deleteOffense = "DELETE FROM Offense WHERE OffenseCode=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteOffense);
			deleteStmt.setInt(1, offense.getOffenceCode());
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

	public Offenses getOffenseByOffenseCode(int offenseCode) throws SQLException {
		String selectOffense =
			"SELECT OffenseCode,Description,CodeGroup " +
			"FROM Offenses " +
			"WHERE OffenseCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffense);
			selectStmt.setInt(1, offenseCode);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultOffenseCode = results.getInt("OffenseCode");
				String description = results.getString("Description");
				String codeGroup = results.getString("CodeGroup");
				Offenses offense = new Offenses(resultOffenseCode,description,codeGroup);
				return offense;
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

}
