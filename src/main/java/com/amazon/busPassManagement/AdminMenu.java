package com.amazon.busPassManagement;

import java.util.Date;

import com.amazon.busPassManagement.model.User;

public class AdminMenu extends Menu {
	
	private static AdminMenu adminMenu = new AdminMenu();
	
	public static AdminMenu getInstance() {
		return adminMenu;
	}
	
	public void showMenu() {
		System.out.println("Navigating to Admin Menu...");
		
		// Login Code should come before the Menu becomes Visible to the Admin
		User adminUser = new User();
		
		// An empty scanner.nextLine as we are reading string after int :)
		//scanner.nextLine();
		
		System.out.println("Enter Your Email:");
		adminUser.email = scanner.nextLine();
		
		System.out.println("Enter Your Password:");
		adminUser.password = scanner.nextLine();
		
		boolean result = auth.loginUser(adminUser);
		
		if(result && adminUser.type == 1) {
		
			BusPassSession.user = adminUser;
			System.out.println("*********************");
			System.out.println("Welcome to Admin App");
			System.out.println("Hello, "+adminUser.name);
			System.out.println("Its: "+new Date());
			System.out.println("*********************");
			
			boolean quit = false;
			
			while(true) {
				try {
					System.out.println("*********************");
		        	System.out.println("1: Manage Routes");
		        	System.out.println("2: Manage Stops");
		        	System.out.println("3: Manage Vehicles");
		        	System.out.println("4: Manage Bus Pass");
		        	System.out.println("5: Manage Feedbacks");
		        	System.out.println("6: Quit Admin App");
		        	System.out.println("Select an Option");
		        	
		        	int choice = Integer.parseInt(scanner.nextLine());
		        	
		        	switch (choice) {
						case 1:
							manageRoutes.manageRoute();
							break;
							
						case 2:
							stops.manageStops();
							break;
		
						case 3:
							vehicles.manageVehicle();
							break;
							
						case 4:
							busPass.manageBusPass();
							break;
							
						case 5:
							feedbacks.manageFeedback();
							break;
							
						case 6:
							System.out.println("Thank You for Using Admin App !!");
							quit = true;
							break;
			
						default:
							System.err.println("Invalid Choice...");
							break;
					}
		        	
		        	if(quit) {
		        		break;
		        	}
		        	
				}
				catch (Exception e) {
					System.err.println("Invalid Input:" +e);
				}
			}
		}
		
		else {
			System.err.println("Invalid Credentials. Please Try Again !!");
		}
	}
}
