package Utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	public static WebDriver driver;
	
	public DriverFactory() {
	}
	
	public static WebDriver getDriver(String browser) {
		if(driver != null)
			return driver;
		else {
			if(browser.equals("GC")) {
				System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\abhinagr\\\\Desktop\\\\chromedriver.exe");
				ChromeOptions ops = new ChromeOptions();
				ops.addArguments("--remote-allow-origins=*");
				driver	= new ChromeDriver(ops);
			}
			else if(browser.equals("RWDC")) {
				try {
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability("os", "Windows");
					caps.setCapability("os_version", "10");
					caps.setCapability("browser", "Chrome");
					caps.setCapability("browser_version", "80");
					caps.setCapability("name", "nehapramodvaidya1's First Test");
					
					driver = new RemoteWebDriver(new URL("browser stakc url"), caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			return driver;
		}
	}
}