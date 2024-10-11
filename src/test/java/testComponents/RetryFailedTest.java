package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer{
	
	int count = 0;
	int retryMaxtCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		
		if (count <retryMaxtCount) {
			count++;
			return true;
		}
		return false;
	}

}
