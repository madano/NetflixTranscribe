package planetExpress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

	public class SQLAccess {
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  public void readDataBase() throws Exception {
	    try {
	      // this will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // setup the connection with the DB.
	      connect = DriverManager.getConnection("jdbc:mysql://database2.cs.tamu.edu/databasename?" + "user=mysqluser&password=mysqlpw");

	      // statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // resultSet gets the result of the SQL query
	      //resultSet = statement.executeQuery("select * from rmcgarel.Customer");
	     /*Customer*/
	      /*for (int i = 7; i < 100; i++){
		      // preparedStatements can use variables and are more efficient
		      preparedStatement = connect.prepareStatement("insert into  rmcgarel.Customer (account_num, name, address) values (?, ?, ?)");
		      // parameters start with 1
		      preparedStatement.setInt(1, i);
		      preparedStatement.setString(2, Integer.toString(i));
		      preparedStatement.setString(3, Integer.toString(i));
		      preparedStatement.executeUpdate();
	      }*/
	     /*Cargo*/
	      /*for (int i = 7; i < 5000; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Cargo (cargo_num, destination) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setString(2, Integer.toString(i));
	    	  preparedStatement.executeUpdate(); //send it
	      }*/
	     /*Airfields*/
	      /*for (int i = 7; i < 50; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Airfield (airfield_num, name) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setString(2, Integer.toString(i));
	    	  preparedStatement.executeUpdate();
	      }
	     /*Aircraft*/
	      /*for (int i = 7; i < 20; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Aircraft (aircraft_num, destination, location) values (?, ?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setString(2, Integer.toString(i));
	    	  preparedStatement.setString(3, Integer.toString(i));
	    	  preparedStatement.executeUpdate();
	      }
	     /*Crew*/
	      /*for (int i = 7; i < 160; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Crew (crew_num, job, name) values (?, ?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setString(2, Integer.toString(i));
	    	  preparedStatement.setString(3, Integer.toString(i));
	    	  preparedStatement.executeUpdate();
	      }
	     /*Owns*/ 
	      /*for (int i = 40298; i < 50000; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Owns (account_num, cargo_num) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setInt(2, i);
	    	  preparedStatement.executeUpdate();
	      }*/
	     /*Is_Loaded*/
	      for (int i = 7; i < 10000; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Is_Loaded (cargo_num, aircraft_num) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setString(2, Integer.toString(i));
	    	  preparedStatement.executeUpdate();
	      }
	     /*Cargo_At*/
	      for (int i = 7; i < 25000; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Cargo_At (cargo_num, airfield_num) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setInt(2, i);
	    	  preparedStatement.executeUpdate();
	      }
	     /*Is_On_Board*/
	      for (int i = 7; i < 80; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Is_On_Board (crew_num, aircraft_num) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setInt(2, i);
	    	  preparedStatement.executeUpdate();
	      }
	     /*Crew_At*/
	      for (int i = 7; i < 80; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Crew_At (crew_num, airfield_num) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setInt(2, i);
	    	  preparedStatement.executeUpdate();
	      }
	     /*Aircraft_At*/
	      for (int i = 7; i < 80; i++){
	    	  preparedStatement = connect.prepareStatement("insert into  rmcgarel.Aircraft_At (aircraft_num, airfield_num) values (?, ?)");
	    	  
	    	  preparedStatement.setInt(1,i);
	    	  preparedStatement.setInt(2, i);
	    	  preparedStatement.executeUpdate();
	      }
	    } 
	    catch (Exception e) {
	      throw e;
	    } 
	    finally {
	      close();
	    }

	  }

	  private void writeMetaData(ResultSet resultSet) throws SQLException {
	    // now get some metadata from the database
	    System.out.println("The columns in the table are: ");
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	  }


	  // you need to close all three to make sure
	  private void close() {
		try {
			if (resultSet != null){
				resultSet.close(); //close(resultSet);
			}
		    if (statement != null){
		    	statement.close();
		    }
		    if (connect != null){
		    	connect.close();
		    }
		}
		catch (Exception e){ }
	  }
}