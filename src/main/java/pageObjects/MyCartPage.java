package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public MyCartPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	
	
	@FindBy (css= "div[class='cartSection'] h3")
	List <WebElement>  productsInCart;		

	
	@FindBy (css= ".totalRow button")
	WebElement  checkOutBtn;	
	
	public List<WebElement> getProductsInCart() {
			return productsInCart;
	}
	
	public boolean checkProductsInCart (String productName) {
		return getProductsInCart().stream().anyMatch(cartProduct->cartProduct.getText().contentEquals(productName));
	}
	
	public void clickCheckOutBtn() {
		checkOutBtn.click();
	}

}
