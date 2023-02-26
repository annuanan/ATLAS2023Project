package com.amazon.busPassManagement;

import java.util.Scanner;

import com.amazon.busPassManagement.controller.AuthenticationService;
import com.amazon.busPassManagement.controller.BusPassService;
import com.amazon.busPassManagement.controller.FeedbackService;
import com.amazon.busPassManagement.controller.RouteService;
import com.amazon.busPassManagement.controller.StopService;
import com.amazon.busPassManagement.controller.VehicleService;
import com.amazon.busPassManagement.db.DB;
import com.amazon.busPassManagement.db.UserDAO;

public class Menu {
	
	AuthenticationService auth = AuthenticationService.getInstance(); 
	RouteService manageRoutes = RouteService.getInstance();
	StopService stops = StopService.getInstance();
	VehicleService vehicles = VehicleService.getInstance();
	BusPassService busPass = BusPassService.getInstance();
	FeedbackService feedbacks = FeedbackService.getInstance();
	UserDAO userdao = new UserDAO(); 
	Scanner scanner = new Scanner(System.in);
	
	void showMainMenu() {
		// Initial Menu for the Application
        while(true) {
        	try {
				System.out.println("1: Admin");
				System.out.println("2: User");
				System.out.println("3: Quit");
				System.out.println("Select an Option");
				
				int choice = Integer.parseInt(scanner.nextLine());
			
				if (choice == 3) {
					System.out.println("Thank You For using Bus Pass Management App");
					DB db = DB.getInstance();
					db.closeConnection();
					break;
				}	
				try {
					MenuFactory.getMenu(choice).showMenu();
				}
				catch (Exception e) {
					System.out.println("[main] Error While showing the menu"+e);
				}
        	}
        	catch (Exception e) {
				System.err.println("Invalid Input:" +e);
			}
        }
	}
	
	public void showMenu() {
		System.out.println("Showing the Menu...");
	}

}

