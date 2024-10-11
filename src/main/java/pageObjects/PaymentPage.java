package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	WebDriver driver;
	
	public PaymentPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	By countryList = By.cssSelector("section.ta-results");
	
	@FindBy (css= "input[placeholder='Select Country']")
	WebElement  country;		
	
	@FindBy (xpath= "//button[contains(@class,'ta-item')][2]")
	WebElement countryToBeSelected;	
	
	@FindBy (css= ".actions a")
	WebElement placeOderBtn;		
	
	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		waitforElementToAppear(countryList);
		countryToBeSelected.click();
		}
	
	public OrderConfirmationPage submitOrder () {
		placeOderBtn.click();
		return new OrderConfirmationPage(driver);
		}

}
