package rsvier.workshop.utility;

import java.util.logging.*;
import java.io.*;

public class LogConnection {


	private final static Logger logger = Logger.getLogger(LogConnection.class.getName());

	
	public LogConnection() {
	}
	

	public static void initLogger() {

		LogManager.getLogManager().reset();
		logger.setLevel(Level.ALL);
		

		FileHandler fh = null;
		SimpleFormatter sf = new SimpleFormatter();
		
		try {


			fh = new FileHandler("log.xml"); 

			fh.setLevel(Level.ALL);
			fh.setFormatter(sf);
			
			
			logger.addHandler(fh);
			
		} catch (SecurityException ex) {
			
			logger.log(Level.WARNING, "Security error ocured", ex.getMessage());

		} catch (IOException e) {
			
			logger.log(Level.SEVERE, "IO exception occured, check log.xml", e.getMessage());

		}

	}
	

	public static Logger getLogger() {

		initLogger();
		return logger;
	}

}
