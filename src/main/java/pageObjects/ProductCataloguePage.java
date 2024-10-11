package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCataloguePage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	By productsBy = By.cssSelector(".mb-3");
	By productToAdd = By.cssSelector("div.card-body button:last-of-type");
	By toaster = By.cssSelector("div#toast-container");
	By cart = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	
	@FindBy (css= ".mb-3")
	List <WebElement>  products;	
	
	
	@FindBy (css= ".ng-animating")
	WebElement  spinner;
	
	@FindBy (xpath= "//button[@routerlink='/dashboard/cart']")
	WebElement  cartIcon;	

	public List<WebElement> getProductList() {
		waitforElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName (String productName) {
		WebElement item = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return item;
		
	}
	
	public void addProductToCart (String productName) throws InterruptedException {		;
		WebElement product = getProductByName(productName);
				product.findElement(productToAdd).click();
				waitforElementToAppear(toaster);
				waitforElementToDisappear();			
	}

	public void clickCart () {		
	waitforElementToBeClickable(cart);
	cartIcon.click();	
}
}
