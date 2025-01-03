package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	// WebDriver instance to interact with the browser
	public static WebDriver driver;

	// Logger instance for logging test execution details
	public Logger logger;

	// Properties object to load configuration settings (e.g., URL)
	public Properties properties;

	// Screenshot directory path
	private static final String screenshotPath = "./src/test/resources/screenshots/";

	// This method is executed before the tests start, to set up the environment
	@BeforeClass
	public void setUp() throws IOException {
		// Load the configuration file that contains properties like URL
		FileReader file = new FileReader("./src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file); // Load properties from the config file

		// Initialize the logger
		logger = LogManager.getLogger(this.getClass());

		// Instantiate ChromeDriver (browser automation)
		driver = new ChromeDriver();

		// Delete all cookies in the browser to ensure a clean session
		driver.manage().deleteAllCookies();

		// Set an implicit wait of 10 seconds to allow elements time to load
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

		// Open the URL specified in the properties file
		driver.get(properties.getProperty("url"));

		// Maximize the browser window
		driver.manage().window().maximize();
	}

	// This method is executed after all tests are completed to clean up
	@AfterClass
	public void tearDown() {
		// Close the browser and terminate the WebDriver session
		 driver.quit();
	}

	// Method to generate a random string with 5 alphabetic characters
	public String randomString() {
		// Generate a random string of 5 alphabetic characters
		return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5);
	}

	// Method to generate a random number with 10 digits
	public String randomNumber() {
		// Generate a random numeric string of 10 digits
		return org.apache.commons.lang3.RandomStringUtils.randomNumeric(10);
	}

	// Method to generate a random alphanumeric string with 5 letters and 10 digits
	public String randomAlphaNumeric() {
		// Generate a random string of 5 alphabetic characters and 10 numeric characters
		return org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(5) + "@" + org.apache.commons.lang3.RandomStringUtils.randomNumeric(10);
	}

	// Method to capture a screenshot with timestamp and class name
	public String captureScreenshot(String className, String testName) throws IOException {
		// Take screenshot only for failed tests (or always if you prefer)
		TakesScreenshot ts = (TakesScreenshot) driver; // Assuming `driver` is your WebDriver instance
		File source = ts.getScreenshotAs(OutputType.FILE);

		// Create a timestamp for the screenshot name
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

		// Define the destination path with class name and timestamp
		String destinationPath = System.getProperty("user.dir") + "\\screenShots\\" + className + "_" + testName + "_"
				+ timestamp + ".png";
		File destination = new File(destinationPath);

		// Copy the screenshot to the desired destination path
		FileUtils.copyFile(source, destination);

		return destinationPath; // Return the path where the screenshot is saved
	}
}
