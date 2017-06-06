package com.learning.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * @author kat
 * JDBC工具类
 * 1）	获取Connection
 * 2）	释放资源
 */
public class JDBCUtil {
	
	/**
	 *	获取Connection
	 *	@return 所获得的JDBC的Connection 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException{
		
		InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		
		String url = properties.getProperty("jdbc.url");
		String user= properties.getProperty("jdbc.user");
		String password= properties.getProperty("jdbc.password");
		String driverClass= properties.getProperty("jdbc.driverClass");
		
		Class.forName(driverClass);
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	/**
	 * 释放DB相关的资源
	 * */
	public static void release(ResultSet resultSet,Statement statement,Connection connection){
		if( resultSet !=null ){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( statement != null ){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( connection != null ){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
