package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCataloguePage;
import testComponents.BaseTest;
import testComponents.RetryFailedTest;

public class LoginValidationTest extends BaseTest{
	
	public LoginPage loginPage;
	
	@BeforeMethod
	public void setup() throws IOException {
		this.loginPage =launchApplication();
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();		
	}
	
	@Test
	public void loginSuccessTest()  {
		loginPage.loginToApplication("sureshtest@gmail.com", "Password@123");
		
		Assert.assertEquals(loginPage.getLoginSuccessMsg(), "Login Successfully");		
	}
	
	@Test
	public void loginFailureTest()  {
		loginPage.loginToApplication("sureshtechh4@gmail.com", "Password@1234");
		
		Assert.assertEquals(loginPage.getLoginErrorMsg(), "Incorrect email or password.");		
	}
	
	@Test (retryAnalyzer = RetryFailedTest.class)
	public void loginSuccessNegativeTest()  {
		loginPage.loginToApplication("sureshtechh12@gmail.com", "Password@12312");
		
		Assert.assertEquals(loginPage.getLoginSuccessMsg(), "Incorrect email or password.");		
	}
}

