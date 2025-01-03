package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	// Declare variables for ExtentSparkReporter, ExtentReports, and ExtentTest
	public ExtentSparkReporter sparkReporter; // Reporter for creating the HTML report
	public ExtentReports extent; // ExtentReports object to manage the test report
	public ExtentTest test; // ExtentTest object to log individual test details
	String repName;

	// This method is executed before the start of the test suite
	public void onStart(ITestContext context) {
		// Initialize ExtentSparkReporter with the path where the report will be saved
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		// Configure the SparkReporter to customize the report's title, name, and theme
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of the report
		sparkReporter.config().setReportName("OpencartFunctional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK); // Dark theme for the report

		// Initialize the ExtentReports object
		extent = new ExtentReports();

		// Attach the reporter to ExtentReports
		extent.attachReporter(sparkReporter);

		// Set additional system information to be displayed in the report's summary
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin"); // 
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}

	// This method is executed when a test case passes
	public void onTestSuccess(ITestResult result) {
		// Create a new test entry in the report for the passed test case
		test = extent.createTest(result.getName());

		// Log a PASS status along with the name of the test case
		test.log(Status.PASS, "Test case PASSED is: " + result.getName());
	}

	// This method is executed when a test case fails
	public void onTestFailure(ITestResult result) {
		// Create a new test entry in the report for the failed test case
		test = extent.createTest(result.getName());

		// Log a FAIL status along with the name of the test case
		test.log(Status.FAIL, "Test case FAILED is: " + result.getName());

		// Log the exception or error message that caused the failure
		test.log(Status.FAIL, "Test case FAILED due to: " + result.getThrowable());

		// Capture the screenshot on failure and get the path of the saved image
		try {
			String imgPath = new BaseClass().captureScreenshot(result.getTestClass().getName(), result.getName());
			
			// Add the screenshot path to the report
			if (imgPath != null) {
				test.addScreenCaptureFromPath(imgPath); // Add screenshot to Extent report
			}
		} catch (IOException e) {
			e.printStackTrace();  // Log the exception if screenshot capture fails
		}
	}

	// This method is executed when a test case is skipped
	public void onTestSkipped(ITestResult result) {
		// Create a new test entry in the report for the skipped test case
		test = extent.createTest(result.getName());

		// Log a SKIP status along with the name of the test case
		test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
	}

	// This method is executed after all tests have finished in the suite
	public void onFinish(ITestContext context) {
		// Write the report to disk by flushing the collected data into the report file
		extent.flush(); // This generates and saves the final report
	}
}
