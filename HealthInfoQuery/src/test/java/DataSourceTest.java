import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public class DataSourceTest {
private ApplicationContext ctx = null;
	
	@Before
	public void setup(){
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("setup");
	}
	
	@After
	public void tearDown(){
		ctx = null;
		System.out.println("tearDown");
	}
	
	@Test
	public void testDataSource(){
		System.out.println("testDataSource");
		DataSource dataSource = (DataSource)ctx.getBean("dataSource");
		Assert.notNull(dataSource);
	}
	
	public void testJdbcTemplate(){
		System.out.println("testJDBCTemplate");
		JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
		Assert.notNull(jdbcTemplate);
	}
}
