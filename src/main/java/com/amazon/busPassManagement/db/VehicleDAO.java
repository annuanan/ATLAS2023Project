package com.amazon.busPassManagement.db;


import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.amazon.busPassManagement.model.Vehicle;

public class VehicleDAO implements DAO<Vehicle> {

	DB db = DB.getInstance();

	public int insert(Vehicle object) {
		String sql = "INSERT INTO Vehicles (regNo, type, filledSeats, totalSeats, startPickUpTime, startDropOffTime, vehicleAvailability, driverID, routeID, adminID) VALUES ('"+object.regNo+"', '"+object.type+"', '"+object.filledSeats+"', '"+object.totalSeats+"', '"+object.startPickUpTime+"', '"+object.startDropOffTime+"', "+object.vehicleAvailability+", "+object.driverID+", "+object.routeID+", "+object.adminID+")";
		return db.executeSQL(sql);
	}

	public int update(Vehicle object) {
		String sql = "UPDATE Vehicles set regNo = '"+object.regNo+"', type='"+object.type+"', filledSeats='"+object.filledSeats+"', totalSeats='"+object.totalSeats+"', startPickUpTime='"+object.startPickUpTime+"', startDropOffTime='"+object.startDropOffTime+"', vehicleAvailability='"+object.vehicleAvailability+"', driverID='"+object.driverID+"', routeID='"+object.routeID+"' WHERE vehicleID = '"+object.vehicleID+"'";
		return db.executeSQL(sql);
	}

	public int delete(Vehicle object) {
		String sql = "DELETE FROM Vehicles WHERE regNo = '"+object.regNo+"'";
		return db.executeSQL(sql);
	}

	public List<Vehicle> retrieve() {
String sql = "SELECT * from Vehicles";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();
		
		try {
			while(set.next()) {
				
				Vehicle vehicles = new Vehicle();
				
				// Read the row from ResultSet and put the data into User Object
				vehicles.vehicleID = set.getInt("vehicleID");
				vehicles.regNo = set.getString("regNo");
				vehicles.type = set.getInt("type");
				vehicles.filledSeats = set.getInt("filledSeats");
				vehicles.totalSeats = set.getInt("totalSeats");
				vehicles.startPickUpTime = set.getString("startPickUpTime");
				vehicles.startDropOffTime = set.getString("startDropOffTime");
				vehicles.vehicleAvailability = set.getInt("vehicleAvailability");
				vehicles.driverID = set.getInt("driverID");
				vehicles.routeID = set.getInt("routeID");
				vehicles.adminID = set.getInt("adminID");
				vehicles.createdOn = set.getString("createdOn");
				
				vehicle.add(vehicles);
			}
		} 
		
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return vehicle;
	}

	public List<Vehicle> retrieve(String sql) {
ResultSet set = db.executeQuery(sql);
		
		ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();
		
		try {
			while(set.next()) {
				
				Vehicle vehicles = new Vehicle();
				
				// Read the row from ResultSet and put the data into User Object
				vehicles.vehicleID = set.getInt("vehicleID");
				vehicles.regNo = set.getString("regNo");
				vehicles.type = set.getInt("type");
				vehicles.filledSeats = set.getInt("filledSeats");
				vehicles.totalSeats = set.getInt("totalSeats");
				vehicles.startPickUpTime = set.getString("startPickUpTime");
				vehicles.startDropOffTime = set.getString("startDropOffTime");
				vehicles.vehicleAvailability = set.getInt("vehicleAvailability");
				vehicles.driverID = set.getInt("driverID");
				vehicles.routeID = set.getInt("routeID");
				vehicles.adminID = set.getInt("adminID");
				vehicles.createdOn = set.getString("createdOn");
				
				vehicle.add(vehicles);
			}
		} 
		
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return vehicle;
	}

	
}

