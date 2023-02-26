package com.amazon.busPassManagement.controller;

import com.amazon.busPassManagement.db.passwordEncryptor;

//import java.util.Iterator;
//import java.util.LinkedHashMap;
import java.util.List;
import com.amazon.busPassManagement.model.User;

public class AuthenticationService extends Management{

	private static AuthenticationService service = new AuthenticationService();
	passwordEncryptor encryptor = passwordEncryptor.getInstance();
	
	
	// Dummy DataBase in RAM
	//LinkedHashMap<Integer, User> users = new LinkedHashMap<Integer, User>();
	
	private AuthenticationService(){
		
		/*User user1 = new User();
		user1.id = 1;
		user1.name = "Annu";
		user1.email = "annu@example.com";
		user1.password = "annu123";
		user1.address = "DNV";
		user1.phone = "+99946 0011";
		user1.department = "Admin";
		user1.type = 1;	
		
		User user2 = new User();
		user2.id = 2;
		user2.name = "John flynn";
		user2.email = "john@example.com";
		user2.password = "john123";
		user2.address = "cr park";
		user2.phone = "+99339 0022";
		user2.department = "cse";
		user2.type = 2;
		
		UserDAO userdao = new UserDAO();
		dao.insert(user1);
		dao.insert(user2);
		System.out.println("Users added in Database :)");
		// Dummy Users in the HashMap :)
		//users.put(user1.id, user1);
		//users.put(user2.id, user2);
		
		//System.out.println("DataBase Details:");
		//System.out.println(users);
		*/
	}
	
	public static AuthenticationService getInstance() {
		return service;
	}
	

	public boolean loginUser(User user) {
			
		String sql = "SELECT * FROM Users WHERE email = '"+user.email+"' AND password = '"+encryptor.encryptor(user.password)+"'";
		List<User> users = userdao.retrieve(sql);
			
		if(users.size() > 0) {
			User u = users.get(0);
			user.name = u.name;
			user.type = u.type;
			user.id = u.id;
			return true;
		}
			
		return false; 
	}
	
	public boolean registerUser(User user) {
		
		return userdao.insert(user) > 0;
	}
	
	public boolean updateUser(User user) {
		return userdao.update(user) > 0;
	}
	
	public boolean deleteUser (User user) {
		return userdao.delete(user) > 0;
	}

}

