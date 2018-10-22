package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GCPMain {

	public static void main(String[] args) throws Exception {
		test();

	}
	
	public static  void test() {
		
		System.out.println("******************");
		try{  
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection(
			 "jdbc:mysql://google/AJ?cloudSqlInstance=pid-gousenapb-noti-res02:us-central1:gnf-instance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false"
				,"root","root"); 	
			 System.out.println("********got Connection**********"+con);
			 Statement stmt=con.createStatement();  
			 ResultSet rs=stmt.executeQuery("select * from Employees");  
			 while(rs.next())  
			 System.out.println("ID : "+rs.getInt(1)+" Name : "+rs.getString(2));  
			 con.close();  
		 }catch(Exception e){ System.out.println(e);} 
		System.out.println("##################");
	}
	
	public static void test2() throws Exception {
		DBConnectionProvider dbp = new DBConnectionProvider();
		
		System.out.println("******************");
		try{  
			/* Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection(
			 "jdbc:mysql://35.232.144.64/AJ?autoReconnect=true&useSSL=false"
				,"root","root"); 	*/
			Connection con = dbp.getConnection();
			 System.out.println("********got Connection**********"+con);
			 Statement stmt=con.createStatement();  
			 ResultSet rs=stmt.executeQuery("select * from Employees");  
			 while(rs.next())  
			 System.out.println("ID : "+rs.getInt(1)+" Name : "+rs.getString(2));  
			 con.close();  
		 }catch(Exception e){ System.out.println(e);} 
		System.out.println("##################");
	}

}
