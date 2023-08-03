package Screenshot;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ExampleClass {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // Set up Extent Reports with ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentss-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set the report theme (optional)
        sparkReporter.config().setTheme(Theme.STANDARD);

        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void exampleTest() {
        test = extent.createTest("Example Test", "Description of the test.");

        driver.get("https://thinktime.in/");
        driver.findElement(By.linkText("Services")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        WebElement sss = driver.findElement(By.xpath("//h2[contains(text(),'What We Offer')]"));
        String text = sss.getText();

        // Log the extracted text to the report
        test.log(Status.INFO, "Extracted text: " + text);

        // Assert the text and log the result to the report
        Assert.assertEquals(text, "This is not the expected text"); // Intentionally failing the test
        test.log(Status.PASS, "Assertion passed!");

        // Capture and attach a screenshot to the report
        captureAndAttachScreenshot();

        // Log a message to the report
        test.log(Status.INFO, "Test completed.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();

        // Flush the report and generate it
        extent.flush();
    }

    private void captureAndAttachScreenshot() {
        // Take a screenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        test.fail("Screenshot:",
		        MediaEntityBuilder.createScreenCaptureFromPath(srcFile.getAbsolutePath()).build());
    }
}






