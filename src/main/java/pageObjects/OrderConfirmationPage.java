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

public class OrderConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderConfirmationPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy (css= "h1.hero-primary")
	WebElement  orderConfirmationMsg;		
	
	public String getOrderConfirmationMsg() {
		return orderConfirmationMsg.getText();
	}

}
