package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageObjects {
	WebDriver poDriver;
	
	public PageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH, using="//input[@title='Search']")
	private WebElement inputSearch;
	
	public void enterTextIntoSearchArea(String txt) {
		inputSearch.sendKeys(txt);
	}
	
	@FindBy(how=How.ID, using = "TitleId")
	private WebElement ddlTitle;
	
	public void selectDropDown(String text) {
		Select select = new Select(ddlTitle);
		select.selectByVisibleText(text);
	}
	
	public WebElement getTitleDropDown() {
		return ddlTitle;
	}
	
	@FindBy(how=How.ID, using = "FirstName")
	public WebElement txtFirstName;
	
	public void enterText(String text) {
		txtFirstName.sendKeys(text);
	}
	
	public String getTxtValue() {
		return txtFirstName.getAttribute("value");
	}
	
	@FindBy(how=How.NAME, using = "Save")
	private WebElement btnSave;
	
	public void clickBtnSave() {
		btnSave.click();
	}
}