package com.amazon.busPassManagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.busPassManagement.model.User;

public class UserDAO implements DAO<User>{

	DB db = DB.getInstance();
	passwordEncryptor encryptor = passwordEncryptor.getInstance();


	public int insert(User object) {
		String sql = "INSERT INTO Users (name, phone, email, password, address, department, type) VALUES ('"+object.name+"', '"+object.phone+"', '"+object.email+"', '"+encryptor.encryptor(object.password)+"', '"+object.address+"', '"+object.department+"', "+object.type+")";
		return db.executeSQL(sql);
	}

	public int update(User object) {
		String sql = "UPDATE Users set name = '"+object.name+"', phone='"+object.phone+"', password='"+encryptor.encryptor(object.password)+"', address='"+object.address+"', department='"+object.department+"' WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	public int delete(User object) {
		String sql = "DELETE FROM Users WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	public List<User> retrieve() {
		String sql = "SELECT * from Users";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.department = set.getString("department");
				user.type = set.getInt("type");
				user.createdOn = set.getString("createdOn");
				
				users.add(user);
			}
		} 
		
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}

	public List<User> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.department = set.getString("department");
				user.type = set.getInt("type");
				user.createdOn = set.getString("createdOn");
				
				users.add(user);
			}
		} 
		
		catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
}
	


