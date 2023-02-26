package com.amazon.busPassManagement.db;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.busPassManagement.model.BusPass;

public class BusPassDAO implements DAO<BusPass> {

	DB db = DB.getInstance();

	public int insert(BusPass object) {
		String sql = "INSERT INTO BusPass (userID, routeID, status) VALUES ("+object.userID+", "+object.routeID+", "+object.status+")";
		return db.executeSQL(sql);
	}

	public int update(BusPass object) {
		String sql = "UPDATE BusPass set approvedRejectedOn = '"+object.approvedRejectedOn
				+"', validTill = '"+object.validTill+"', status = "+object.status +" WHERE buspassID = "+object.buspassID;
		return db.executeSQL(sql);
	}

	public int delete(BusPass object) {
		String sql = "DELETE FROM BusPass WHERE buspassID = "+object.buspassID;
		return db.executeSQL(sql);
	}

	public List<BusPass> retrieve() {
String sql = "SELECT * from BusPass";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<BusPass> objects = new ArrayList<BusPass>();
		
		try {
			while(set.next()) {
				
				BusPass object = new BusPass();
				
				// Read the row from ResultSet and put the data into Object
				object.buspassID = set.getInt("buspassID");
				object.userID = set.getInt("userID");
				object.routeID = set.getInt("routeID");
				object.requestedOn = set.getString("requestedOn");
				object.approvedRejectedOn = set.getString("approvedRejectedOn");
				object.validTill = set.getString("validTill");
				object.status = set.getInt("status");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

	public List<BusPass> retrieve(String sql) {
ResultSet set = db.executeQuery(sql);
		
		ArrayList<BusPass> objects = new ArrayList<BusPass>();
		
		try {
			while(set.next()) {
				
				BusPass object = new BusPass();
				
				// Read the row from ResultSet and put the data into Object
				object.buspassID = set.getInt("buspassID");
				object.userID = set.getInt("userID");
				object.routeID = set.getInt("routeID");
				object.requestedOn = set.getString("requestedOn");
				object.approvedRejectedOn = set.getString("approvedRejectedOn");
				object.validTill = set.getString("validTill");
				object.status = set.getInt("status");
				object.createdOn = set.getString("createdOn");
				
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	
	}
	

}
