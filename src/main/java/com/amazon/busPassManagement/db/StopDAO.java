package com.amazon.busPassManagement.db;


import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.amazon.busPassManagement.model.Stop;

public class StopDAO implements DAO<Stop>{

	DB db = DB.getInstance();

	public int insert(Stop object) {
		String sql = "INSERT INTO Stops (address,sequenceOrder,routeID,adminID) VALUES ('"+object.address+"','"+object.sequenceOrder+"','"+object.routeID+"',"+object.adminID+")";
		return db.executeSQL(sql);
	}

	public int update(Stop object) {
		String sql = "UPDATE Stops set address = '"+object.address+"', sequenceOrder='"+object.sequenceOrder+"' , routeID='"+object.routeID +"' , adminID='"+object.adminID +"' WHERE stopID = '"+object.stopID +"'";
		return db.executeSQL(sql);
	}

	public int delete(Stop object) {
		String sql = "DELETE FROM Stops WHERE stopID = '"+object.stopID+"'";
		return db.executeSQL(sql);
	}

	public List<Stop> retrieve() {
		String sql = "SELECT * from Stops";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Stop> stop = new ArrayList<Stop>();
		
		try {
			while(set.next()) {
				
				Stop stops = new Stop();
				
				// Read the row from ResultSet and put the data into User Object
				
				stops.address = set.getString("address");
				stops.sequenceOrder = set.getInt("sequenceOrder");
				stops.routeID = set.getInt("routeID");
				stops.adminID = set.getInt("adminID");
				stops.createdOn = set.getString("createdOn");
				
				stop.add(stops);
			}
		}
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
			
		return stop;
	}

	public List<Stop> retrieve(String sql) {

		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Stop> stop = new ArrayList<Stop>();
		
		try {
			while(set.next()) {
				
				Stop stops = new Stop();
				
				// Read the row from ResultSet and put the data into User Object
				stops.stopID = set.getInt("stopID");
				stops.address = set.getString("address");
				stops.sequenceOrder = set.getInt("sequenceOrder");
				stops.routeID = set.getInt("routeID");
				stops.adminID = set.getInt("adminID");
				stops.createdOn = set.getString("createdOn");
				
				stop.add(stops);
			}
		}
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return stop;
	}

	

}
