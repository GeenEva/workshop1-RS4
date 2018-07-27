package rsvier.workshop;

import com.zaxxer.hikari.HikariDataSource;

import rsvier.workshop.controller.MainMenuController;
import rsvier.workshop.utility.DataSource;
import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;

public class App {

	public static boolean hikariEnabled;

	public static void main(String[] args) {
		
		
		
		View view = new MainMenuView();

		// Option to choose connection pool
		
		hikariEnabled = view.printAskUserToEnableHikariOrNot();
		//In DataSource.java, when hikariEnabled is set to true, the hikari config- and source- 
		// fields are initialized cq. the hikari pool is initialized
		
		//Just to do a little check:
		boolean hikCheck = false;
		try { hikCheck = DataSource.getDataSource().isRunning();
			System.out.println(hikCheck);}
		
		catch (NullPointerException ex) {
			System.out.println("Hikari status = " + hikCheck);	
		}
		
		
		// Option to choose database

		view.printAskUserToUseSQLOrMongo();

		MainMenuController mainMenuController = new MainMenuController();
		mainMenuController.runView();
	}
}
