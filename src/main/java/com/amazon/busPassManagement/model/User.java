package com.amazon.busPassManagement.model;

public class User {

	// Attributes
		public int id;
		public String name;
		public String phone;
		public String email;
		public String password;
		public String address;
		public String department;
		public int type;
		public String createdOn;
		
		public User() {
			
		}
		
		public User(int id, String name, String phone, String email, String password, String address, String department,
				int type, String createdOn) {
			this.id = id;
			this.name = name;
			this.phone = phone;
			this.email = email;
			this.password = password;
			this.address = address;
			this.department = department;
			this.type = type;
			this.createdOn = createdOn;
		}

		public void prettyPrint(User user) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Name:\t\t"+user.name);
			System.out.println("Phone:\t\t"+user.phone);
			System.out.println("Email:\t\t"+user.email);
			System.out.println("Address:\t"+user.address);
			System.out.println("Department:\t"+user.department);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
					+ ", address=" + address + ", department=" + department + ", type=" + type + ", createdOn=" + createdOn
					+ "]";
		}
		
}
