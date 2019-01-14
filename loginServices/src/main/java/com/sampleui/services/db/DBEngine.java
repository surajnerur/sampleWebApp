package com.sampleui.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sampleui.services.management.PropertyFile;

public class DBEngine {
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	PropertyFile propertyFile = new PropertyFile();
	String connectionString = propertyFile.getProperty("connectionString");
	String userId = propertyFile.getProperty("userId");
	String password = propertyFile.getProperty("password");
	String driver = propertyFile.getProperty("driver");

	/**
	 * Fetch from Database
	 * @param sqlQuery
	 * @return
	 */
	public ResultSet dbConnection(String sqlQuery) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionString, userId, password);
			System.out.println(sqlQuery); 
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * Insert to Database and return the genearted Keys
	 * @param sqlQuery
	 * @return
	 */
	public String dbInsertANDReturnGeneratedKey(String sqlQuery)throws Exception {
		int result = -1;
		String idCreate_Modified=null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionString, userId, password);
			statement = connection.createStatement();
			result = statement.executeUpdate(sqlQuery,Statement.RETURN_GENERATED_KEYS);		
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		       if (generatedKeys.next()) {
		    	   idCreate_Modified= generatedKeys.getString(1);
		          }else {
		            throw new SQLException("Nothing is Inserted");
		           }
		        }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			closeConnection();
		}
		return idCreate_Modified;
	}
	
	
	public Integer dbInsert(String sqlQuery) {
		int result = -1;		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionString, userId, password);
			statement = connection.createStatement();
			result = statement.executeUpdate(sqlQuery,Statement.RETURN_GENERATED_KEYS);					
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return result;
	}
	
	/**
	 * Delete from Database
	 * @param query
	 * @return
	 */
	public Boolean dbDelete(String query) {
		boolean result = false;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionString, userId, password);
			statement = connection.createStatement();
			result = statement.execute(query);				
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		return result;
		
	}
	
	public void closeConnection() {
		if(null!=resultSet){
			try {
				resultSet.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		if(null!=statement){
			try{
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
