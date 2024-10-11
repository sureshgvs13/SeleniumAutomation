package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.OrdersPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCataloguePage;
import testComponents.BaseTest;

public class SubmitOrderE2ETest extends BaseTest{
	
	public LoginPage loginPage;
	public String productName = "ADIDAS ORIGINAL";
	public String country = "India";
	
	@BeforeMethod (alwaysRun = true)
	public void setup() throws IOException {
		this.loginPage =launchApplication();
		
	}
	
	@AfterMethod (alwaysRun = true)
	public void teardown() {
	    if (driver != null) {
	        driver.quit();	
	}
 }
	
	@Test (dataProvider = "getData", groups = {"Purchase Test"})
	public void submitOrder(HashMap <String, String> input) throws InterruptedException, IOException {
		loginPage.loginToApplication(input.get("email"), input.get("password"));		
		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);		
		List <WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.clickCart();
//		Thread.sleep(5000);
		MyCartPage myCartPage = new MyCartPage(driver);
		List <WebElement> productsInCart = myCartPage.getProductsInCart();
		boolean selectedProductAddedInCart = myCartPage.checkProductsInCart(input.get("product"));
		Assert.assertTrue(selectedProductAddedInCart);
		myCartPage.clickCheckOutBtn();
		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.selectCountry(country);
		OrderConfirmationPage orderConfirmationPage = paymentPage.submitOrder();		
		String orderSuccessMsg = orderConfirmationPage.getOrderConfirmationMsg();
		Assert.assertEquals(orderSuccessMsg, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider = "getData", groups = {"Purchase Test"})
	public void submitOrderNegativeTest(HashMap <String, String> input) throws InterruptedException, IOException {
		loginPage.loginToApplication(input.get("email"), input.get("password"));		
		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);		
		List <WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.clickCart();
		MyCartPage myCartPage = new MyCartPage(driver);
		List <WebElement> productsInCart = myCartPage.getProductsInCart();
		boolean selectedProductAddedInCart = myCartPage.checkProductsInCart("IPHONE 13 PRO");
		Assert.assertTrue(selectedProductAddedInCart);
	}
	
	@Test (dependsOnMethods = {"submitOrder"}, groups= {"Purchase Test"})
	public void verifyOrderTest() throws InterruptedException, IOException {
		loginPage.loginToApplication("sureshtechh@gmail.com", "Password@123");
		OrdersPage ordersPage = new OrdersPage(driver);
		ordersPage.clickOrdersLink();
		Assert.assertTrue(ordersPage.checkProductsInTheOrder(productName));
	
	}
	
	@DataProvider
	public Object[][] getData() {
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("email", "sureshtechh@gmail.com");
		map.put("password", "Password@123");
		map.put("product", "ADIDAS ORIGINAL");	
		
		HashMap <String, String> map1 = new HashMap<String, String>();
		map1.put("email", "sureshtechh@gmail.com");
		map1.put("password", "Password@123");
		map1.put("product", "IPHONE 13 PRO");	
		
		return new Object [] [] {{map}, {map1}};
	}
	
}




