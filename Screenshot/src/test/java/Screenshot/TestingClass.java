package Screenshot;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(ListenersImpl.class)
public class TestingClass {
	    public WebDriver driver;
	    public ExtentReports extent = Reports.getReports();

//	  public ListenersImpl testListener; // Declare the listener instance

	    @BeforeMethod
	    public void setUp(ITestContext context) {
	        extent.createTest("Initial Demo");
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	        // Pass the driver instance to the ITestListenerImpl
	        ListenersImpl  testListener = new ListenersImpl();
	        testListener.setDriver(driver);
	        context.setAttribute("driver", driver); // Set the driver instance in the context
	    }

	    @AfterMethod
	    public void tearDown() {
	        driver.quit();
	    }
}
