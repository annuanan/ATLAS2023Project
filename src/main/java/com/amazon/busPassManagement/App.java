package com.amazon.busPassManagement;
import com.amazon.busPassManagement.db.DB;

public class App {
	
    public static void main( String[] args ){
     
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Bus Pass Management Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //App app = new App();
        Menu menu = new Menu();
        
        if(args.length > 0) {
        	DB.FILEPATH = args[0];
        }

        menu.showMainMenu();
        //AuthenticationService.getInstance();     
    }    
}
