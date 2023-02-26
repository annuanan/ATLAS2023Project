package com.amazon.busPassManagement.controller;

import java.util.ArrayList;
import java.util.List;
import com.amazon.busPassManagement.BusPassSession;
import com.amazon.busPassManagement.model.Route;
import com.amazon.busPassManagement.model.Stop;

public class StopService extends Management{
	
	private static StopService manageStops = new StopService();
	
	public static StopService getInstance() {
		return manageStops;
	}
	
	public void manageStops() {
		
		while(true) {
			System.out.println("+++++++++++++++++++++++");
			System.out.println("1. Create Stops");
			System.out.println("2. Retrieve Stops");
			System.out.println("3. Update a Stop");
			System.out.println("4. Remove a Stop");
			System.out.println("5. QUIT");
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(scanner.nextLine());
			boolean quit = false;
			switch(ch) {
			case 1:
				addStop();
				break;
				
			case 2:
				displayStops();
				break;
				
			case 3:
				updateStops();
				System.out.println("Stops updated");
				break;

			case 4:
				if (deleteStop())
					System.out.println("Deleted Stops");
				else
					System.err.println("Something went wrong");
				break;
				
			case 5:
				quit = true;
				break;
				
			default:
				System.err.println("Invalid choice");
			}
			
			if (quit)
				break;
		} 	
	}
	
	public void addStop() {
		int sequence = 0;
		int choice = 1;
		System.out.println("Enter routeID");
		stops.routeID = Integer.parseInt(scanner.nextLine());
		stops.adminID = BusPassSession.user.id;
		while(true) {
			
			if (choice == 2)
				break;
			System.out.println("Enter Stop Address :");
			stops.address = scanner.nextLine();
			//scanner.nextLine();
			sequence++;
			stops.sequenceOrder = sequence;
			try {
				stopsdao.insert(stops);
				
			} catch (Exception e) {
				System.err.println("Something went Wrong"+e);
			}
			
			System.out.println("Do you wish to add more stops? /n 1.Yes 2. No");
			choice  = Integer.parseInt(scanner.nextLine());
			//scanner.nextLine();
			
		}
	}
	
	public void insertStop(Stop stops) {
		int sequence = 0;
		while(true) {
			System.out.println("Do you wish to add more stops? /n 1.Yes 2. No");
			int choice  = Integer.parseInt(scanner.nextLine());
			
			if (choice == 2)
				break;
			scanner.nextLine();
			System.out.println("Enter Stop Address :");
			stops.address = scanner.nextLine();
			sequence++;
			stops.sequenceOrder = sequence;
			try {
				stopsdao.insert(stops);
				
			} catch (Exception e) {
				System.err.println("Something went Wrong"+e);
			}
			
			
		}
	}
	public void updateStops() {
		
		Stop stop = new Stop();
		stop.getDetails(true);
		
		// Add the admin ID Implicitly.
		stop.adminID = BusPassSession.user.id;
		
		int result = stopsdao.update(stop);
		String message = (result > 0) ? "Stop Updated Successfully" : "Updating Stop Failed. Try Again.."; 
		System.out.println(message);
		
		
	}
	
	public boolean deleteStop() {
		//scanner.nextLine();
		
		//System.out.println(stopsdao.retrieve());
		
		System.out.println("Enter the route ID to view all stops on that route:");
        int viewstopsonroute = Integer.parseInt(scanner.nextLine());
        
        String sql = "Select * from Stops where routeID ="+viewstopsonroute;
        List<Stop> stoplist = stopsdao.retrieve(sql);
        for (Stop stop1 : stoplist) {
        	stops.prettyPrintForAdmin(stop1);
		}
        
        //scanner.nextLine();
        System.out.println("Enter the stop ID to be deleted");
        stops.stopID = Integer.parseInt(scanner.nextLine());
        
        if (stopsdao.delete(stops) > 0)
        	return true;
        else
			return false;
	}
	public void retrieveStops(Route routes) {
		
		String sql = "SELECT * FROM Stops WHERE routeID = '"+routes.routeID+"'";
		List<Stop> getStops = new ArrayList<Stop>();
		getStops = stopsdao.retrieve(sql);
		//why need loop, try without loop
		for (Stop stop : getStops) {
			stops.prettyPrintForAdmin(stop);
		}
		
	}
	
	public void displayStops() {
		//scanner.nextLine();
		System.out.println("Enter the Route ID for the stops you want to search");
		stops.routeID = Integer.parseInt(scanner.nextLine());
		String sql = "SELECT * FROM Stops WHERE routeID = '"+stops.routeID+"'";
		List <Stop> getStop =  new ArrayList<Stop>();
		getStop = stopsdao.retrieve(sql);
		
		for (Stop stop1 : getStop) {
			stops.prettyPrintForAdmin(stop1);
			
		}
	}
}

