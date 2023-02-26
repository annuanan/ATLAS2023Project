package com.amazon.busPassManagement.db;

import java.security.MessageDigest;
import java.math.BigInteger;

public class passwordEncryptor {
	
	private static passwordEncryptor passEncryptor = new passwordEncryptor();
	
	public static passwordEncryptor getInstance() {
		return passEncryptor;
	}
	
	public String encryptor (String password){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(password.getBytes());
			
			BigInteger bigInt = new BigInteger(1,messageDigest);
			return bigInt.toString(16);
		}
		catch(Exception e) {
			System.err.println("Exception occured:"+e);
		}
		
		return password; 
		
	}

}
