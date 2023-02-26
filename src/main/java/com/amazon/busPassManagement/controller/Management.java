package com.amazon.busPassManagement.controller;

import java.util.Scanner;

import com.amazon.busPassManagement.db.BusPassDAO;
import com.amazon.busPassManagement.db.FeedbackDAO;
import com.amazon.busPassManagement.db.RouteDAO;
import com.amazon.busPassManagement.db.StopDAO;
import com.amazon.busPassManagement.db.UserDAO;
import com.amazon.busPassManagement.db.VehicleDAO;
import com.amazon.busPassManagement.model.BusPass;
import com.amazon.busPassManagement.model.Feedback;
import com.amazon.busPassManagement.model.Route;
import com.amazon.busPassManagement.model.Stop;
import com.amazon.busPassManagement.model.Vehicle;

public class Management {
	
	Scanner scanner = new Scanner(System.in);
	
	UserDAO userdao = new UserDAO();  //dao
	RouteDAO routedao = new RouteDAO(); //credao
	StopDAO stopsdao = new StopDAO();
	VehicleDAO vehiclesDAO = new VehicleDAO();
	BusPassDAO busPassdao = new BusPassDAO();
	FeedbackDAO feedbackdao = new FeedbackDAO();
	
	AuthenticationService service = AuthenticationService.getInstance();
	StopService manageStops = StopService.getInstance();
	VehicleService manageVehicle = VehicleService.getInstance();
	RouteService manageRoutes = RouteService.getInstance();
	
	//Route routes = new Route();
	Stop stops = new Stop();
	Vehicle vehicles = new Vehicle();
	BusPass pass = new BusPass();
	Feedback feedbacks = new Feedback();

	Route route = new Route(); //creRoutes
	
	
}

