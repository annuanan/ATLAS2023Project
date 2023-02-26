package com.amazon.busPassManagement.controller;

import java.util.ArrayList;
import java.util.List;
import com.amazon.busPassManagement.BusPassSession;
import com.amazon.busPassManagement.model.Route;
import com.amazon.busPassManagement.model.Vehicle;

public class VehicleService extends Management {
	
	private static VehicleService manageVehicle = new VehicleService();
	
	public static VehicleService getInstance() {
		return manageVehicle;
	}
	
	public void manageVehicle() {
		
		while(true) {
			System.out.println("+++++++++++++++++++++++");
			System.out.println("1. Add new Vehicle");
			System.out.println("2. Display Vehicle Details");
			System.out.println("3. Update a Vehicle Information");
			System.out.println("4. Remove a Vehicle Service");
			System.out.println("5. QUIT");
			System.out.println("Enter your choice :");
			int ch = Integer.parseInt(scanner.nextLine());
			boolean quit = false;
			switch(ch) {
			case 1:
				if (addVehicle())
					System.out.println("Vehicle Added to service");
				else {
					System.err.println("Some Error occured");
				}
				break;
				
			case 2:
				displayVehicles();
				break;
				
			case 3:
				if (updateVehicle())
					System.out.println("Stops updated");
				
				else
					System.err.println("Something went wrong");
				break;

			case 4:
				if (deleteVehicle())
					System.out.println("Deleted Vehicle");
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

	public void addVehicleDetails(Vehicle vehicles) {
		//scanner.nextLine();
		
		System.out.println("Enter Vehicle Registration Number: ");
		vehicles.regNo = scanner.nextLine();
		System.out.println("Enter Vehicle type: 1) Bus 2) Innova ");
		vehicles.type = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter filled Seats ");
		vehicles.filledSeats = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter Total Number of Seats: ");
		vehicles.totalSeats = Integer.parseInt(scanner.nextLine());
		//scanner.nextLine();
		System.out.println("Enter Start Pickup Time: ");
		vehicles.startPickUpTime = scanner.nextLine();
		System.out.println("Enter Start Drop Off Time: ");
		vehicles.startDropOffTime = scanner.nextLine();
		System.out.println("Enter Vehicle Availability /n 0. In Maintainence /t 1. Available");
		vehicles.vehicleAvailability = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter Driver ID: ");
		vehicles.driverID = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the Route ID: ");
		vehicles.routeID = Integer.parseInt(scanner.nextLine());
	}
	

	public boolean addVehicle() {
		vehicles.adminID = BusPassSession.user.id;;
		addVehicleDetails(vehicles);
		return vehiclesDAO.insert(vehicles) > 0;
	}
	
	public void displayVehicles() {
		List <Vehicle> vehicle = new ArrayList<Vehicle>();
		vehicle = vehiclesDAO.retrieve();
		for (Vehicle vehiclesDetail : vehicle) {
			vehicles.prettyPrintForAdmin(vehiclesDetail);
		}
	}
	
	public void retrieveVehicles(Route routes) {
		List <Vehicle> vehicle = new ArrayList<Vehicle>();
		String sql = "select * from Vehicles where routeID = '"+routes.routeID+"'";
		vehicle = vehiclesDAO.retrieve(sql);
		for (Vehicle vehiclesDetail : vehicle) {
			vehicles.prettyPrintForAdmin(vehiclesDetail);
		}
	}
	public boolean updateVehicle() {
		displayVehicles();
		System.out.println("Enter Vehicle ID to be updated: ");
		vehicles.vehicleID = Integer.parseInt(scanner.nextLine());
		addVehicleDetails(vehicles);
		vehicles.adminID = BusPassSession.user.id;;
		return vehiclesDAO.update(vehicles) > 0;
	}
	
	public boolean deleteVehicle() {
		scanner.nextLine();
		displayVehicles();
		System.out.println("Enter Vehicle Registration Number of vehicle you want to delete: ");
		vehicles.regNo = scanner.nextLine();
		return vehiclesDAO.delete(vehicles) > 0;
	}
	
}


