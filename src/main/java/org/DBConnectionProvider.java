package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
	private Connection connection;

	public DBConnectionProvider() throws SQLException {
		String jdbcUrl = formJdbcConnectionUrl();
		initializeJdbcDriver();
		setConnection(jdbcUrl);
	}

	private void setConnection(String jdbcUrl) throws SQLException {
		String user = "root";
		String passwd = "root";
		connection = DriverManager.getConnection(jdbcUrl, user, passwd);
	}

	private void initializeJdbcDriver() {
		try {
			String jdbcDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(jdbcDriver).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/** Form CloudSQL JDBC URL using the parameters - Database name & Cloud SQL Instance details
	 * @return
	 */
	private String formJdbcConnectionUrl() {

		String databaseName = "AJ";
		String cloudSqlInstance = "pid-gousenapb-noti-res02:us-central1:gnf-instance";

		String jdbcUrl = String.format(
				"jdbc:mysql://google/%s?cloudSqlInstance=%s"
						+ "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
				databaseName, cloudSqlInstance);
		return jdbcUrl;
	}

	public Connection getConnection() {
		return connection;
	}
}
