package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pageObjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public WebDriver initializeDriver () throws IOException {
		
		Properties prop = new Properties();
		String file = System.getProperty("user.dir") + "\\resources\\GlobalData.properties";
		FileInputStream fileInputStream  = new FileInputStream(file );
		prop.load(fileInputStream);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
		
		
		if (browserName.equalsIgnoreCase("chrome")) {			
			driver = new ChromeDriver();	
		}
		
		else if (browserName.equalsIgnoreCase("chromeheadless")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);			
		}
		
		else {
			System.out.println("Browesr Object Not Found");
		}
		
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;		
	}
	
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		LoginPage loginPage = new LoginPage (driver);
		return loginPage;		
	}
	
	public String captureScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	}

}
