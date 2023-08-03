package Screenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AutoScreenShot {
	public static String gettingscreenshot(WebDriver driver,String screenshotName) throws IOException {
		 String pattern = "yyyyMMddHHmmss";
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	     String dateName = LocalDateTime.now().format(formatter);

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
	         //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
//		String destination = "D:\\ImportantProjects\\sreenshot.png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
