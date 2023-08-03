package Screenshot;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenersImpl extends TestingClass implements ITestListener  {
	    private ExtentReports extent = Reports.getReports();
	    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	    private WebDriver driver;

	    

	    @Override
	    public void onTestStart(ITestResult result) {
	    	
	        test.set(extent.createTest(result.getMethod().getMethodName()));
	        System.out.println("Test started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.get().pass("Test passed");
	    	
	        System.out.println("Test passed: " + result.getName());
	    }

//	    @Override
//	    public void onTestFailure(ITestResult result) {
//	    	test.get().fail("Test failed");
//
//	        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver"); // Get the driver from the context
//	        String screenshotPath = null;
//	        try {
//	            screenshotPath = AutoScreenShot.gettingscreenshot(driver, result.getMethod().getMethodName());
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        test.get().addScreenCaptureFromPath(screenshotPath);
//	        System.out.println("Test failed: " + result.getName());
//	    }
	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.get().fail("Test failed");
	        try {
	    		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
	    				.get(result.getInstance());
	    	}catch(Exception el) {
	    		el.printStackTrace();
	    	}
	        String screenshotPath = null;
			try {
				screenshotPath = AutoScreenShot.gettingscreenshot(driver, result.getMethod().getMethodName());
			} catch (Exception e) {
				e.printStackTrace();
			}
	        test.get().addScreenCaptureFromPath(screenshotPath);
	       System.out.println("Test failed: " + result.getName());
	    }
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.get().skip("Test skipped");
	    }
	    @Override
	    public void onFinish(ITestContext context) {
	    	extent.flush();
	        System.out.println("Test Suite finished: " + context.getName());
	    }

	    public void setDriver(WebDriver driver) {
	        this.driver = driver;
	    }
}
