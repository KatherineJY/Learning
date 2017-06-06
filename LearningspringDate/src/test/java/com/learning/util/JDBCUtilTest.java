package com.learning.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class JDBCUtilTest {
	
	@Test
	public void testGetConnection() throws ClassNotFoundException, SQLException, IOException{
		Connection connection = JDBCUtil.getConnection();
		Assert.assertNotNull(connection);
	}
}
