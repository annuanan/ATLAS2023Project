package com.amazon.busPassManagement.controller;

import java.util.ArrayList;

import java.util.List;
import com.amazon.busPassManagement.model.Route;
import com.amazon.busPassManagement.model.Stop;
import com.amazon.busPassManagement.model.Vehicle;
import com.amazon.busPassManagement.model.BusPass;
import com.amazon.busPassManagement.BusPassSession;

public class RouteService extends Management{

	public RouteService(){
		// TODO Auto-generated constructor stub
	}
	private static  RouteService manageRoutes = new RouteService();
	
	public static RouteService getInstance() {
		return manageRoutes;
	}
	public void manageRoute() {
		
		while(true) {
			System.out.println("+++++++++++++++++++++++");
			System.out.println("1. Create new Route");
			System.out.println("2. Retrieve a Route");
			System.out.println("3. Update a route");
			System.out.println("4. Remove a route");
			System.out.println("5. QUIT");
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(scanner.nextLine());
			boolean quit = false;
			switch(ch) {
			case 1:
				if (createRoute())
					System.out.println("Route Added");
				else 
					System.err.println("Something went wrong");
				break;
				
			case 2:
				retrieveRoute();
				break;
				
			case 3:
				int update = updateRoute();
				if (update == 1)
					System.out.println("Route updated");
				
				else if (update == 2)
					System.err.println("Something went wrong");
				
				else
					System.out.println("Discarded Operation");
				
				break;

			case 4:
				if (deleteRoute())
					System.out.println("Route Deleted Successfully");
				else
					System.err.println("Deleting Route Failed. Try Again..");
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
	public void addDetails() {
		//scanner.nextLine();
		System.out.println("Enter the route title:");
		route.title = scanner.nextLine();
		System.out.println("Enter route description");
		route.description = scanner.nextLine();
		route.adminID = BusPassSession.user.id;
	}
	
	public boolean createRoute() {
		
        addDetails();
        
		// Add the admin ID Implicitly.
		route.adminID = BusPassSession.user.id;
		
		int result = routedao.insert(route);
		String message = (result > 0) ? "Route Added Successfully" : "Adding Route Failed. Try Again.."; 
		System.out.println(message);
		
		if(result>0) 
			return true;
		else
			return false;
	}
	
	public void retrieveRoute() {
		
		//scanner.nextLine();
		
		System.out.println("Do you want to see all routes or a particular route?");
		System.out.println("1. All Routes");
		System.out.println("2. Specific Route");
		int choice = Integer.parseInt(scanner.nextLine());
		
		if (choice == 1) {
			List<Route> getRoutes = new ArrayList<Route>();
			getRoutes = routedao.retrieve();
			
			for (Route route : getRoutes) {
				route.prettyPrintForAdmin(route);
			}
		}
		else if (choice == 2){
			scanner.nextLine();
			System.out.println("Enter the Route Title you want to search");
			route.title = scanner.nextLine();
			String sql = "SELECT * FROM Routes WHERE title = '"+route.title+"'";
			List<Route> getRoutes = routedao.retrieve(sql);
			Route route = getRoutes.get(0);
				route.prettyPrintForAdmin(route);
				//manageStops.retrieveStops(route);
				//manageVehicle.retrieveVehicles(route);
		}
		
		else
			System.err.println("Wrong Choice");
	}
	
	public void displayRoutes() {
		System.out.println("Enter the Route Title you want to search");
		route.title = scanner.nextLine();
		
		String sql = "SELECT * FROM Routes WHERE title = '"+route.title+"'";
		List<Route> getRoutes = routedao.retrieve(sql);
		
		int routeID = 0;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Available Routes");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		for (Route route1 : getRoutes) {
			
			routeID = route1.routeID;
			route.prettyPrintForUser(route1);
		}
		sql = "SELECT * FROM Stops WHERE routeID = "+routeID;
		List <Stop> getStops = stopsdao.retrieve(sql);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Stops on this route");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		for (Stop stop : getStops) {
			
			stops.prettyPrintForUser(stop);
		}
		
		sql = "SELECT * FROM Vehicles WHERE routeID = "+routeID;
		List<Vehicle> getVehicle = vehiclesDAO.retrieve(sql);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Vehicles on this route");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		for(Vehicle vehicle : getVehicle) {
			
			vehicles.prettyPrintForUser(vehicle);
		}
		
		if(getRoutes.size() == 0) {
			System.err.println("No available routes with this title");
		}
	}
	
	public int updateRoute() {
		
		//display the routes table
		//System.out.println(routedao.retrieve());
		List<Route> routedetails = routedao.retrieve();
		for(Route route1 : routedetails) {
			route.prettyPrintForAdmin(route1);
		}
		
		System.out.println("Enter the Route ID you want to modify");
		route.routeID = Integer.parseInt(scanner.nextLine());
		
		addDetails();
		
		System.out.println("Title : " +route.title);
		System.out.println("Description : "+route.description);
		System.out.println("Route ID : "+route.routeID);
		System.out.println("Do you wish to update? \n 1: Yes 2: No");
		int choice = Integer.parseInt(scanner.nextLine());
		//scanner.nextLine();
		if (choice == 1)
			if (routedao.update(route) > 0)
				return 1;
			else 
				return 2;
		
		else
			return 10;
	}
	
	public boolean deleteRoute() {
		//scanner.nextLine();
				
		System.out.println("Enter Route ID to be deleted: ");
		route.routeID = Integer.parseInt(scanner.nextLine());
				
		String sql = "Select * from BusPass where routeID = "+route.routeID;
		
		List<BusPass> buspasslist = busPassdao.retrieve(sql);
		for(BusPass buspass : buspasslist) {
			busPassdao.delete(buspass);
		}
		
		String sql2 = "Select * from Vehicles where routeID = "+route.routeID;
		List<Vehicle> vehiclelist = vehiclesDAO.retrieve(sql2);
		for(Vehicle vehicle : vehiclelist) {
			vehiclesDAO.delete(vehicle);
		}
		
		String sql3 = "Select * from Stops where routeID = "+route.routeID;
		List<Stop> stoplist = stopsdao.retrieve(sql3);
		for(Stop stop : stoplist) {
			stopsdao.delete(stop);
		}
		
		int result = routedao.delete(route);
		
		//String message = (result > 0) ? "Route Deleted Successfully" : "Deleting Route Failed. Try Again.."; 
		//System.out.print(message);

		
        if (result > 0)
        	return true;
        else
			return false;
	}
}