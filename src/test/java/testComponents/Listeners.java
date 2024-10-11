package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import abstractComponents.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	WebDriver driver;
	ThreadLocal <ExtentTest> threadLocal = new ThreadLocal <ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {	
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);

	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
			ITestListener.super.onTestSuccess(result);
			test.log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
			ITestListener.super.onTestFailure(result);
			test.fail(result.getThrowable());
			((ExtentTest) threadLocal.get()).fail(result.getThrowable());
			
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String screenshotFilePath = null;
			try {
				screenshotFilePath = captureScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				e.printStackTrace();
			}
			((ExtentTest) threadLocal.get()).addScreenCaptureFromPath(screenshotFilePath, result.getMethod().getMethodName());
			test.log(Status.FAIL, "Test Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
