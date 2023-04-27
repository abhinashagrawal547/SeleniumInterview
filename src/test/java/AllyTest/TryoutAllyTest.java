package AllyTest;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.deque.axe.AXE;

import Utilities.DriverFactory;

public class TryoutAllyTest {
	
	WebDriver driver;
	private static final URL scriptUrl = TryoutAllyTest.class.getResource("/axe.min.js");
	
	@Before
	public void setUp() {
		driver	=	DriverFactory.getDriver("GC");
	}
	
	@Test
	public void tryOutAllyTest() {
		driver.get("https://www.amazon.com");
		
		JSONObject jsonObj = new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray jsonArr=jsonObj.getJSONArray("violations");
		
		if(jsonArr.length()==0)
			System.out.println("No violations found");
		else {
			AXE.writeResults("TryoutAllyTest", jsonArr);
			Assert.assertTrue(AXE.report(jsonArr), false);
		}
		System.out.println("method b");
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}