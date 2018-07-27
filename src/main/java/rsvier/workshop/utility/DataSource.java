package rsvier.workshop.utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.zaxxer.hikari.*;
import rsvier.workshop.App;

public class DataSource {

	private static HikariConfig hikariConfig;
	private static HikariDataSource dataSource; //The dataSource is specified in hikari.properties

	private static Logger logger = LogConnection.getLogger();

	/*
	 * This static initializer runs as soon as the class is loaded
	 */

	static {

		if (App.hikariEnabled) {
			hikariConfig = new HikariConfig("hikari.properties");
			setDataSource(new HikariDataSource(hikariConfig));
		}
	}

	public static Connection getConnection() {

		if (App.hikariEnabled) {

			try {
				return getDataSource().getConnection();

			} catch (SQLException e) {
				logger.log(Level.WARNING, "SQL Exception occured, connection with hikari connection pool failed", e);

			}
		}
		
		//if user enters 'no' for hikari, the connection is made via the DatabaseConnectionXML class
		try {

			return DatabaseConnectionXML.getConnection();

		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL Exception occured, connection with JDBC connection pool failed", e);
		}
		return null;
	}

	public static HikariDataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(HikariDataSource dataSource) {
		DataSource.dataSource = dataSource;
	}

}
