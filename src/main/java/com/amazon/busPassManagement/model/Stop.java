package com.amazon.busPassManagement.model;

import java.util.Scanner;

public class Stop {
	
	//Attributes
	public int stopID;
	public String address;
	public int sequenceOrder;
	public int routeID;
	public int adminID;
	public String createdOn;

	public Stop() {
		
	}
	
	public Stop(int stopID, String address, int sequenceOrder, int routeID, int adminID, String createdOn) {
		this.stopID = stopID;
		this.address = address;
		this.sequenceOrder = sequenceOrder;
		this.routeID = routeID;
		this.adminID = adminID;
		this.createdOn = createdOn;
	}
	
	public void getDetails(boolean updateMode) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Capturing Stop Details....");
		
		System.out.println("Enter Stop Address:");
		address = scanner.nextLine();
		
		System.out.println("Enter Sequence Order:");
		sequenceOrder = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Route ID:");
		routeID = Integer.parseInt(scanner.nextLine());
		
		if(updateMode) {
			System.out.println("Enter Stop ID to update:");
			stopID = Integer.parseInt(scanner.nextLine());
		}
	}
		
	public void prettyPrintForAdmin(Stop stops) {
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Stop ID:\t\t"+stops.stopID);
		System.out.println("Stop address:\t\t"+stops.address);
		System.out.println("Stop sequenceOrder:\t"+stops.sequenceOrder);
		System.out.println("Stop routeID:\t\t"+stops.routeID);
		System.out.println("Admin ID:\t\t"+stops.adminID);
		System.out.println("Created On:\t\t"+stops.createdOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	
	
	public void prettyPrintForUser(Stop stops) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Stop address:\t\t"+stops.address);
		System.out.println("Stop SequenceOrder:\t\t"+stops.sequenceOrder);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	@Override
	public String toString() {
		return "Stops [stopID=" + stopID + ", address=" + address + ", sequenceOrder=" + sequenceOrder + ", routeID="
				+ routeID + ", adminID=" + adminID + ", createdOn=" + createdOn + "]";
	}
}
