package search.base.beta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	
	Connection connection;
	Statement statement = null;
	ResultSet resultSet = null;

	public DatabaseHandler(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/searchbase","root","");
			statement = connection.createStatement();
		}catch(Exception e){
			System.out.println(" Exception  : "+e.getMessage());
		}
		
	}
	
	public ResultSet getCate(){
		
		try {
			 resultSet = statement.executeQuery("SELECT * FROM cate");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return resultSet;
	}
	
	public  ResultSet query(String query){
		

		try{
			resultSet = statement.executeQuery(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void close(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
