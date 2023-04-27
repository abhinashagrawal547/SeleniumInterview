package stepDefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import Utilities.DriverFactory;
import Utilities.ExcelUtility;
import io.cucumber.java.en.*;
import pageObjects.PageObjects;

public class StepDef extends DriverFactory {
	
	WebDriver driver;
	
	public StepDef() {
		driver = DriverFactory.getDriver("GC");
	}
	
	@Given("while i am using my laptop")
	public void while_i_am_using_my_laptop() {
	}
	
	@Given("at the same time using my mobile also")
	public void at_the_same_time_using_my_mobile_also() {
	}
	
	@Given("users browse the url")
	public void users_browse_the_url() throws IOException {
		driver.get("https://demosite.executeautomation.com/index.html");
		String valueToBeChecked = "Abhinash";
		
		//Read excel
			//		Boolean isValuePresent = ExcelUtility.verifyAValueiSPresent(
			//				"C:\\Users\\abhinagr\\eclipse-workspace\\InterviewSelenium\\Resources\\TestData.xlsx", valueToBeChecked);
			//		Assert.assertTrue("Value "+ valueToBeChecked + " not present in excel", isValuePresent);
	}
	
	@When("it enters values into fields")
	public void it_enters_values_into_fields() throws IOException, AWTException, InterruptedException {
		PageObjects obj = new PageObjects(driver);
		obj.selectDropDown("Mr.");
		obj.enterText("Abhinash");
		obj.txtFirstName.click();

		//select get all the options from dropdown
		
			Select sel = new Select(obj.getTitleDropDown());
			List<WebElement> listOfOptions = sel.getOptions();
			for(int i=0; i< listOfOptions.size(); i++) {
				System.out.println(listOfOptions.get(i).getText());
			}
		
		//empty a textbox without clear() method
		
			//		Robot rob;
			//		rob = new Robot();
			//		rob.keyPress(KeyEvent.VK_CONTROL);
			//		Thread.sleep(1000);
			//		rob.keyPress(KeyEvent.VK_A);
			//		Thread.sleep(1000);
			//		rob.keyPress(KeyEvent.VK_BACK_SPACE);
			//		Thread.sleep(1000);
			//		rob.keyRelease(KeyEvent.VK_BACK_SPACE);
			//		Thread.sleep(1000);
			//		rob.keyRelease(KeyEvent.VK_CONTROL);
			//		Thread.sleep(1000);
			//		rob.keyRelease(KeyEvent.VK_A);

		//take screenshot
		
			//File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//Reporter.addScreenCaptureFromPath(file.getAbsolutePath());
			//obj.clickBtnSave();
	}
	
	@Then("verify the textbox value entered is correct")
	public void verify_the_textbox_value_entered_is_correct() {
		PageObjects obj = new PageObjects(driver);
		String value = obj.getTxtValue();
		if(value.equals("Abhinash"))
			System.out.println("Test executed correctly");
	}
	
	
	@Given("user a website and opens new tab and move to previous tab")
	public void user_a_website_and_opens_new_tab_and_move_to_previous_tab() throws AWTException {
		
	    driver.manage().window().maximize();
	    driver.get("https://www.google.com");
	    WebElement elem;
	    
	    try {
			Thread.sleep(5000);
			
//			Implicit wait			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.switchTo().frame("xyz");
			
			String window = driver.getWindowHandle();
			driver.switchTo().window("child windows");
			driver.switchTo().window(window);
			
//			Explicit wait			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			elem = wait.until(ExpectedConditions.
					visibilityOfElementLocated(By.xpath("//input[@title='Search']")));
			
//			Fluent wait
			FluentWait<WebDriver> fw = new FluentWait<WebDriver>(driver);
			fw.withTimeout(Duration.ofSeconds(10));
			fw.pollingEvery(Duration.ofSeconds(1));
			fw.ignoring(NoSuchElementException.class);
			elem = fw.until(ExpectedConditions.
					visibilityOfElementLocated(By.xpath("//input[@title='Search']")));
			
//			alert
			driver.switchTo().alert().accept();
			
			elem.sendKeys("software testing help");
			elem.sendKeys(Keys.ENTER);
	        String a = Keys.chord(Keys.CONTROL,Keys.RETURN);
	        driver.findElement(By.partialLinkText("Software Testing Help - FREE IT Courses and Business")).sendKeys(a);
	        
	        Robot rob;
			rob = new Robot();
			rob.keyPress(KeyEvent.VK_CONTROL);
	        rob.keyPress(KeyEvent.VK_TAB);
	        rob.keyRelease(KeyEvent.VK_CONTROL);
	        Thread.sleep(3000);
	        
	        rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_SHIFT);
			rob.keyPress(KeyEvent.VK_TAB);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_SHIFT);
			
			String s = driver.getWindowHandle();
			
			System.out.println("getWindowHandle() returned = "+ s);
			System.out.println("Shifted to first tab");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//Scenario 'Verify link is already visited'
	
	@Given("user has few links")
	public void user_has_few_links() {
	    driver.get("https://www.siteground.com/kb/check_the_number_of_internal_external_links_on_my_website/");
	    driver.manage().window().maximize();
	    
	    List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL);
		action.sendKeys("s");
		action.build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='History']")));
		action.keyUp(Keys.CONTROL);		
		action.build().perform();
	}
}