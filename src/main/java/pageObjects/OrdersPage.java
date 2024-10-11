package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrdersPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	By orders = By.cssSelector("button[routerlink$='/dashboard/myorders']");
	
	@FindBy (css= "button[routerlink$='/dashboard/myorders']")
	WebElement  ordersLink;		
	
	
	@FindBy (css= "tbody tr td:nth-child(3)")
	List <WebElement>  productInTheOrderTable;	
	

	public void clickOrdersLink () {	
	waitforElementToBeClickable(orders);
	ordersLink.click();	
}
	
	public List<WebElement> getProductsInTheOrder() {
		return productInTheOrderTable;
}
	
	public boolean checkProductsInTheOrder (String productName) {
		return getProductsInTheOrder().stream().anyMatch(cartProduct->cartProduct.getText().contentEquals(productName));
	}

}
