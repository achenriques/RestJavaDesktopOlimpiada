package com.example.TareaRest;
import java.sql.*;

public class Connect {
  
   private Connection conn = null;

   public Connect()
   {
	if(connect())
		System.out.println("Successful connection to DB"); 
   }
   
   public boolean connect() {
	   try
	   {
	       String userName = "root";
	       String password = "mysql";
	       String url = "jdbc:mysql://localhost:3306/jjoo";
	       Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	       conn = DriverManager.getConnection (url, userName, password);
	       System.out.println ("Database connection established");
	   }
	   catch (Exception e)
	   {
	       System.err.println ("Cannot connect to database server");
	   }
	   if (conn != null) {	           
		   return true;
	   } else
    	   return false;
	       
   }
   
   public Connection getConn() {
	   return conn;
   }
   
   public void closeConnection()
   {
	   if(conn != null)
	   try
       {
           conn.close ();
           System.out.println ("Database connection terminated");
       }
       catch (Exception e) { /* ignore close errors */ }
   }
}