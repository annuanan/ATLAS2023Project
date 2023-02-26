package com.amazon.busPassManagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.busPassManagement.model.Feedback;

public class FeedbackDAO implements DAO<Feedback> {

	
	DB db = DB.getInstance();
	
	public int insert(Feedback object) {
		String sql = "INSERT INTO Feedbacks (userID, title, description, type, raisedBy) "
				+ "VALUES ( "+object.userID+", '"+object.title+"', '"+object.description+"', "+object.type+", '"+object.raisedBy+"')";
		return db.executeSQL(sql);
	}
	
	// Since it is not required.
	// Feedbacks cannot be altered.
	public int update(Feedback object) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Feedback object) {
		String sql = "DELETE from Feedbacks WHERE feedbackID = "+object.feedbackID;
		return db.executeSQL(sql);
	}

	public List<Feedback> retrieve() {
		String sql = "SELECT * from Feedbacks";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		
		try {
			while(set.next()) {
				
				Feedback feedback = new Feedback();
				
				// Read the row from ResultSet and put the data into Object
				feedback.feedbackID = set.getInt("feedbackID");
				feedback.userID = set.getInt("userID");
				feedback.raisedBy = set.getString("raisedBy");
				feedback.type = set.getInt("type");
				feedback.title = set.getString("title");
				feedback.description = set.getString("description");
				feedback.createdOn = set.getString("createdOn");
				
				feedbacks.add(feedback);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return feedbacks;
	}

	public List<Feedback> retrieve(String sql) {
				
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		
		try {
			while(set.next()) {
				
				Feedback feedback = new Feedback();
				
				// Read the row from ResultSet and put the data into Object
				feedback.feedbackID = set.getInt("feedbackID");
				feedback.userID = set.getInt("userID");
				feedback.raisedBy = set.getString("raisedBy");
				feedback.type = set.getInt("type");
				feedback.title = set.getString("title");
				feedback.description = set.getString("description");
				feedback.createdOn = set.getString("createdOn");
				
				feedbacks.add(feedback);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return feedbacks;
	}

}
 