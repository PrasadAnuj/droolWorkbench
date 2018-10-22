package org;

import java.sql.*;

public class FirstExample {
   static final String JDBC_DRIVER = "org.h2.Driver";  
   static final String DB_URL = "35.232.144.64";

   static final String USER = "anuj";
   static final String PASS = "anuj";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
     /* sql="CREATE TABLE EMPLOYEES(ID INT,first varchar(50),last varchar(50),age int)";
      stmt.execute(sql);
      sql = "insert into EMPLOYEES values(1,'anuj','prasad',20)";
      stmt.execute(sql);
      sql = "insert into EMPLOYEES values(3,'raju','prasad',25)";
      stmt.execute(sql);*/
      sql = "SELECT id, first, last, age FROM EMPLOYEES";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Age: " + age);
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
   
 public void test() {
	 try{  
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection(
			 "jdbc:mysql://google/%s?cloudSqlInstance=pid-gousenapb-noti-res02:us-central1:gnf-instance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false/AJ"
				,"root","root"); 		  
			 Statement stmt=con.createStatement();  
			 ResultSet rs=stmt.executeQuery("select * from Employees");  
			 while(rs.next())  
			 System.out.println("ID : "+rs.getInt(1)+" Name : "+rs.getString(2));  
			 con.close();  
		 }catch(Exception e){ System.out.println(e);}  
}  
 
}//end FirstExample
