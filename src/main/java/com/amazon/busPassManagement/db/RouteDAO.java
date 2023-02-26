package com.amazon.busPassManagement.db;


import java.util.List;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.amazon.busPassManagement.model.Route;

public class RouteDAO implements DAO<Route> {

	DB db = DB.getInstance();

	public int insert(Route object) {
		String sql = "INSERT INTO Routes (title,description,adminID) VALUES ('"+object.title+"','"+object.description+"',"+object.adminID+")";
		return db.executeSQL(sql);
	}

	public int update(Route object) {
		String sql = "UPDATE Routes set title = '"+object.title+"', description='"+object.description+"' , adminID='"+object.adminID +"' WHERE routeID = '"+object.routeID +"'";
		return db.executeSQL(sql);
	}

	public int delete(Route object) {
		String sql = "DELETE FROM Routes WHERE routeID = '"+object.routeID+"'";
		return db.executeSQL(sql);
	}

	public List<Route> retrieve() {
		
		String sql = "SELECT * from Routes";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Route> route = new ArrayList<Route>();
		
		try {
			while(set.next()) {
				
				Route routes = new Route();
				
				// Read the row from ResultSet and put the data into User Object
				routes.routeID = set.getInt("routeID");
				routes.title = set.getString("title");
				routes.description = set.getString("description");
				routes.adminID = set.getInt("adminID");
				routes.createdOn = set.getString("createdOn");
				
				route.add(routes);
			}
		} 
		
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		return route;
	}

	public List<Route> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Route> route = new ArrayList<Route>();
		
		try {
			while(set.next()) {
				
				Route routes = new Route();
				
				// Read the row from ResultSet and put the data into User Object
				routes.routeID = set.getInt("routeID");
				routes.title = set.getString("title");
				routes.description = set.getString("description");
				routes.adminID = set.getInt("adminID");
				routes.createdOn = set.getString("createdOn");
				
				route.add(routes);
			}
		}
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return route;
	}



}
