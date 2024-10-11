package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
//	WebElement email = driver.findElement(By.id("userEmail"));
	//div[aria-label='Incorrect email or password.']
	//div[aria-label='Login Successfully']
	
	@FindBy (id= "userEmail")
	WebElement loginEmail;
	
	@FindBy (id= "userPassword")
	WebElement loginPassword;
	
	@FindBy (name= "login")
	WebElement loginBtn;
	
	@FindBy (css= "div[aria-label='Login Successfully']")
	WebElement loginSuccessMsg;
	
	@FindBy (css= "div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	
	public void loginToApplication (String userName, String password ) {
		loginEmail.sendKeys(userName);
		loginPassword.sendKeys(password);
		loginBtn.click();		
	}
	
	public String getLoginErrorMsg() {
		waitforWebElementToAppear(errorMsg);
		return errorMsg.getText();		
	}
	
	public String getLoginSuccessMsg() {
		waitforWebElementToAppear(loginSuccessMsg);
		return loginSuccessMsg.getText();		
	}
	
}
