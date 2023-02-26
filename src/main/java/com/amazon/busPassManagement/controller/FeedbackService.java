package com.amazon.busPassManagement.controller;

import java.util.List;
import com.amazon.busPassManagement.BusPassSession;
import com.amazon.busPassManagement.model.Feedback;

public class FeedbackService extends Management{

	private FeedbackService() {
	}
	
	// Create it as a Singleton 
		private static FeedbackService manageFeedbacks = new FeedbackService();
		
		public static FeedbackService getInstance() {
			return manageFeedbacks;
		}
		
		public void manageFeedback() {
			
			while(true) {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("1: View Feedbacks");
				System.out.println("2: View Feedbacks by User");
				System.out.println("3: Delete Feedbacks");
				System.out.println("4: Quit Managing Feedbacks");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("Enter Your Choice: ");
				int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
				boolean quit = false;
				switch(choice) {
				case 1:
					viewFeedbacks();
					break;
				case 2:
					System.out.println("Enter User ID: ");
					int userID = Integer.parseInt(scanner.nextLine());//scanner.nexInt();
					viewFeedbacksByUser(userID);
					break;
				case 3:
					deleteFeedback();
					break;
				case 4:
					quit = true;
					break;
					
				default:
					System.err.println("Invalid Choice");
				}
				
				if(quit)
					break;
			} 	
		}
		
		// Handler for the Feedback
		public void createFeedback() {
			
			feedbacks.getDetails();
			
			// Add the User ID Implicitly.
			feedbacks.userID = BusPassSession.user.id;
			feedbacks.raisedBy = BusPassSession.user.email;
			
			int result = feedbackdao.insert(feedbacks);
			String message = (result > 0) ? "Feedback Created Successfully" : "Creating Feedback Failed. Try Again.."; 
			System.out.println(message);
		}
		
		public void deleteFeedback() {
			viewFeedbacks();
			System.out.println("Enter Feedback ID to be deleted: ");
			feedbacks.feedbackID = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
			int result = feedbackdao.delete(feedbacks);
			String message = (result > 0) ? "Feedback Deleted Successfully" : "Deleting Feedback Failed. Try Again.."; 
			System.out.println(message);
		}
		
		public void viewFeedbacks() {
			List<Feedback> feedbacks = feedbackdao.retrieve();
			for(Feedback object : feedbacks) {
				object.prettyPrint();
			}
		}
		
		public void viewFeedbacksByUser(int userID) {
			
			String sql = "SELECT * from Feedbacks where userID = "+userID;
			
			List<Feedback> feedbacks = feedbackdao.retrieve(sql);
			
			for(Feedback object : feedbacks) {
				object.prettyPrint();
			}
		}
}

