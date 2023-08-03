package Screenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	  private static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();

	    public static ExtentReports getReports() {
	        if (extent.get() == null) {
	            String path = System.getProperty("user.dir") + "//test-output/STMExtentReport.html";
	            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	            reporter.config().setReportName("Web Automation Result");
	            reporter.config().setDocumentTitle("Test Result");

	            ExtentReports extentInstance = new ExtentReports();
	            extentInstance.attachReporter(reporter);
	            extentInstance.setSystemInfo("Tester", "Harish");
	            extentInstance.setSystemInfo("Environment", "Automation Testing");

	            extent.set(extentInstance);
	        }
	        return extent.get();
	    }
	}