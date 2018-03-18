package tools;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import SafeStay.dal.ConnectionManager;

/**
 * @author Prachi Ved
 *
 */
public class Inserter {
	private static final Character INFILE_FIELD_SEPARATION_CHAR = ',';
	private static final Character INFILE_ENCLOSED_CHAR = '"';
	protected ConnectionManager connectionManager;

	protected Inserter() {
		connectionManager = new ConnectionManager();
	}

	public static void main(String[] args) throws SQLException, ParseException {

		try {
			Inserter inserter = new Inserter();
			inserter.importData("C:/Users/Prachi/Desktop/DBMS/Offense.csv", "Offense");
			inserter.importData("C:/Users/Prachi/Desktop/DBMS/Incident.csv", "Incidents");
			inserter.importData("C:/Users/Prachi/Desktop/DBMS/Address.csv", "Address");
			System.out.println("Inserted data successfully");
			
			//Test Users

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void importData(String dataFilePath, String tableName) {
		String sql = "LOAD DATA LOCAL INFILE '" + dataFilePath + "' into table " + tableName + " FIELDS TERMINATED BY '"
				+ INFILE_FIELD_SEPARATION_CHAR + "' " + "ENCLOSED BY '" + INFILE_ENCLOSED_CHAR + "' "
				+ "LINES TERMINATED BY '" + System.lineSeparator() + "' " + "IGNORE 1 LINES;";

		Connection conn = null;
		Statement statement = null;
		try {
			conn = connectionManager.getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
