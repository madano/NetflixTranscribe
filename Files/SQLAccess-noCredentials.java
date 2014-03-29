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
	      connect = DriverManager.getConnection("jdbc:mysql://user/databasename?"
	              + "user=databaseuser&password=databasepwd"); //change credentials to match

	      // statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // resultSet gets the result of the SQL query
	      resultSet = statement.executeQuery("select * from rmcgarel.Customer");
	      writeResultSet(resultSet);
	      for (int i = 0; i < 100; i++){
		      // preparedStatements can use variables and are more efficient
		      preparedStatement = connect.prepareStatement("insert into  rmcgarel.Customer (account_num, name, address) values (?, ?, ?)");
		      // "myuser, webpage, datum, summary, Customers from rmcgarel.Customer");
		      // parameters start with 1
		      preparedStatement.setInt(1, i);
		      preparedStatement.setString(2, Integer.toString(i));
		      preparedStatement.setString(3, Integer.toString(i));
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

	  private void writeResultSet(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      String user = resultSet.getString("myuser");
	      String website = resultSet.getString("webpage");
	      String summary = resultSet.getString("summary");
	      Date date = resultSet.getDate("datum");
	      String comment = resultSet.getString("comments");
	      System.out.println("User: " + user);
	      System.out.println("Website: " + website);
	      System.out.println("Summary: " + summary);
	      System.out.println("Date: " + date);
	      System.out.println("Comment: " + comment);
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