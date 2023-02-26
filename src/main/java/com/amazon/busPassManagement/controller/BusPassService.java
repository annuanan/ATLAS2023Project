package com.amazon.busPassManagement.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


import com.amazon.busPassManagement.BusPassSession;
import com.amazon.busPassManagement.model.BusPass;
import com.amazon.busPassManagement.model.Feedback;
import com.amazon.busPassManagement.controller.DuplicatePassException;
import com.amazon.busPassManagement.controller.DuplicateSuspendedPass;

public class BusPassService extends Management{

	private static BusPassService manageBusPass = new BusPassService();
	
	public static BusPassService getInstance() {
		return manageBusPass;
	}

	Scanner scanner = new Scanner (System.in);
	BusPass pass = new BusPass();
	public void manageBusPass() {
		//Admin
		while(true) {
			System.out.println("+++++++++++++++++++++++");
			System.out.println("1: View Pass Requests");
			System.out.println("2: View Pass Request By userID");
			System.out.println("3: Update Pass Request");
			System.out.println("4: Delete Pass Request");
			System.out.println("5: Quit");
			System.out.println("Enter Your Choice: ");
			int passChoice = Integer.parseInt(scanner.nextLine());

			boolean quit = false;
			switch(passChoice) {
			case 1:
				viewPassRequests();
				break;
				
			case 2:
				
				System.out.println("Enter User ID: ");
				int userID = Integer.parseInt(scanner.nextLine());
				viewPassRequestsByUser(userID);
				break;
				
			case 3:
				approveRejectPassRequest();
				break;

			case 4:
				deletePass();
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
	

		public void requestPass() throws DuplicatePassException, DuplicateSuspendedPass{
			
			BusPass pass = new BusPass();
			pass.getDetails(false);
			
			// Add the User ID Implicitly.
			pass.userID = BusPassSession.user.id;
			
			//String sql1 = "Select * from BusPass where userID ="+pass.userID;
			//List<BusPass> userpass = busPassdao.retrieve(sql1);
			
			String sql = "Select * from BusPass where userID ="+pass.userID;
			List<BusPass> buspasses = busPassdao.retrieve(sql);
			
			for(BusPass abuspass: buspasses) {
				if(abuspass.userID == pass.userID && abuspass.routeID == pass.routeID && abuspass.status == 2) {
					//System.out.println("Inside 1st if");
					throw new DuplicatePassException();
				}
				if(abuspass.userID == pass.userID && abuspass.routeID == pass.routeID && abuspass.status == 4) {
					//System.out.println("Inside 2nd if");

					throw new DuplicateSuspendedPass();
				}
			}
			// As initially record will be inserted by User where it is a request
			pass.status = 1; // initial status as requested :)
			
			int result = busPassdao.insert(pass);
			String message = (result > 0) ? "Pass Requested Successfully" : "Request for Pass Failed. Try Again.."; 
			System.out.println(message);
		}
		
		public void deletePass() {
			BusPass pass = new BusPass();
			System.out.println("Enter Pass ID to be deleted: ");
			pass.buspassID = Integer.parseInt(scanner.nextLine());
			int result = busPassdao.delete(pass);
			String message = (result > 0) ? "Pass Deleted Successfully" : "Deleting Pass Failed. Try Again.."; 
			System.out.println(message);
		}
		
		public void suspendPass(BusPass pass) {
			
			String sql = "Select * from BusPass where buspassID ="+pass.buspassID;
			List<BusPass> passdetails = busPassdao.retrieve(sql);
			BusPass passToBeSuspended = passdetails.get(0);
			passToBeSuspended.status = 4;
			
			int result = busPassdao.update(passToBeSuspended);
			String message = (result > 0) ? "Pass Suspended Successfully" : "Suspension Failed. Try Again.."; 
			System.out.println(message);
		}
		
		
		public void approveRejectPassRequest() {

	        System.out.println("Enter Bus Pass ID: ");
	        pass.buspassID = Integer.parseInt(scanner.nextLine());//scanner.nextInt();

	        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	        System.out.println("2: Approve");
	        System.out.println("3: Cancel");
	        System.out.println("4: Suspend");
	        System.out.println("Enter Approval Choice: ");
	        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	        pass.status = Integer.parseInt(scanner.nextLine());//scanner.nextInt();

	        if(pass.status == 4) {
				suspendPass(pass);
				
			}
	        else {
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		        Calendar calendar = Calendar.getInstance();
		        Date date1 = calendar.getTime();
		        pass.approvedRejectedOn = dateFormat.format(date1);

		        if(pass.status == 2) {
		            calendar.add(Calendar.YEAR, 1);
		            Date date2 = calendar.getTime();
		            pass.validTill = dateFormat.format(date2);
		        }else if(pass.status == 3){
		            pass.validTill = pass.approvedRejectedOn;
		        }

		        int result = busPassdao.update(pass);
		        String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again.."; 
		        System.out.println(message);
	        }
	        
	    }
		
		public void viewPassRequests() {
			
			System.out.println("Enter Route ID to get All the Pass Reqeuests for a Route");
			System.out.println("Or 0 for All Bus Pass Requests");
			System.out.println("Enter Route ID: ");
			
			int routeID = Integer.parseInt(scanner.nextLine());
			
			List<BusPass> objects = null;
			
			if(routeID == 0) {
				objects = busPassdao.retrieve();
			}else {
				String sql = "SELECT * from BusPass where routeID = "+routeID;
				objects = busPassdao.retrieve(sql);
			}
			
			for(BusPass object : objects) {
				object.prettyPrint();
			}
		}
		
		public void viewPassRequestsByUser(int userID) {
			
			String sql = "SELECT * from BusPass where userID = "+userID;
			List<BusPass> objects = busPassdao.retrieve(sql);
			
			for(BusPass object : objects) {
				object.prettyPrint();
			}
		}

		public void requestPassSuspension() {
			
			//BusPass pass = new BusPass();
			System.out.println("Enter pass id to be suspended");
			int suspendPassId = Integer.parseInt(scanner.nextLine());
			
			String sql = "Select * from BusPass where buspassID ="+suspendPassId;
			List<BusPass> passdetails = busPassdao.retrieve(sql);
			BusPass passToBeSuspended = passdetails.get(0);
			passToBeSuspended.status = 5;
			
			//System.out.println("validTill value is"+passToBeSuspended.validTill);
			//passToBeSuspended.validTill = passToBeSuspended.validTill;
			
			int result = busPassdao.update(passToBeSuspended);
			String message = (result > 0) ? "Pass Suspension Requested Successfully" : "Request for Pass Suspension Failed. Try Again.."; 
			System.out.println(message);
		}
		
	
		
}

