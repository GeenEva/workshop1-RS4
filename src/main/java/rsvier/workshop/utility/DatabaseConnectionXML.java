package rsvier.workshop.utility;

import java.io.*;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.logging.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.mongodb.*;


public class DatabaseConnectionXML {

	//Connection class for both SQL and Mongo
	
	
	private static Logger logger = LogConnection.getLogger();

	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static String DATABASE_NAME;



	public static void initializeXmlSQL() {

		
		File xmlFile = new File("src/main/java/rsvier/workshop/utility/DCXML.xml");

		if (xmlFile.exists()) {

			try {

				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document document = dBuilder.parse(xmlFile);
				document.getDocumentElement().normalize();
				
				URL = document.getElementsByTagName("url").item(0).getTextContent();
				USER = document.getElementsByTagName("user").item(0).getTextContent();
				PASSWORD = document.getElementsByTagName("password").item(0).getTextContent();
				
				logger.log(Level.CONFIG,"Xml file exist, parsing is succesfull.");
				
			} catch (ParserConfigurationException | SAXException | IOException e) {
				
				logger.log(Level.WARNING, "Parser/Sax/IOexception occured check log", e);

			}
			
		} else {
			
			logger.log(Level.INFO, "xmlFile is not existing.");
		}

	}
	
	public static Connection getConnection() throws SQLException {

		/*
		 * Check if one or more fields of this class are null. If so, first initialize these fields
		 * by parsing the xml in the initializeXmlSQL() method.
		 */
		
		if (URL == null | USER == null | PASSWORD == null) {
			initializeXmlSQL();
		}
		
		Connection conn = null;
		
		try {
			
			/*
			 * Since the fields are now set, you can use their value now to get connection
			 */
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			logger.log(Level.INFO, "Connected to Database");
		

		} catch (SQLException e) {
			
			logger.log(Level.WARNING, "SQL exeception ocurred. Connection with database failed.", e);

		}

		return conn;
	}

	public static void initializeXmlMongoDB() {

		
		File xmlFile = new File("src/main/java/rsvier/workshop/utility/DCXMLMONGO.xml");

		if (xmlFile.exists()) {

			try {

				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document document = dBuilder.parse(xmlFile);
				document.getDocumentElement().normalize();
				
				URL = document.getElementsByTagName("url").item(0).getTextContent();
				DATABASE_NAME = document.getElementsByTagName("databaseName").item(0).getTextContent();
				
				
				logger.log(Level.CONFIG,"Xml file exist, parsing is succesfull.");
				
			} catch (ParserConfigurationException | SAXException | IOException e) {
				
				logger.log(Level.WARNING, "Parser/Sax/IOexception occured. Check log.", e);

			}
			
		} else {
			
			logger.log(Level.INFO, "xmlFile is not existing.");
		}

	}

	
	
	
	public static DB getConnectionMongoDB() throws UnknownHostException {
		
		if (URL == null | DATABASE_NAME == null) {
			initializeXmlMongoDB();
		}
		
		DB db = null;
		
		MongoClientURI mongoClientUri = new MongoClientURI(URL);
		logger.log(Level.INFO, "MongoClientURI set");
		
		MongoClient mongoClient = new MongoClient(mongoClientUri);
		logger.log(Level.INFO, "MongoClient set");
		
		db = (DB) mongoClient.getDB(DATABASE_NAME);
		logger.log(Level.INFO, "Connected to MongoDB Database");
		
		return db;
		
	}
}
