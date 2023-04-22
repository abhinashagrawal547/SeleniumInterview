package TestRunner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;

import Utilities.DriverFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features/featureFile.feature"
		,glue = "stepDefinition"
		//,tags = "@test"
		//,dryRun = true
		)

public class TestRun {
	
	@BeforeClass
	public static void setUp() {
		ExtentProperties er = ExtentProperties.INSTANCE;
		String reportPath = "C:\\Users\\abhinagr\\Desktop";
		er.setReportPath(reportPath	+ "\\cucumber-html-report-abhinash.html");
	}
	
	@AfterClass
	public static void tearDown() {
		DriverFactory.getDriver("GC").quit();

//		String Path1 = System.getProperty("user.dir") + "\\Resources\\extent-config.xml";
//		Reporter.loadXMLConfig(new File(Path1));
//		Reporter.setSystemInfo("OS", "Windows 10");
	}
}