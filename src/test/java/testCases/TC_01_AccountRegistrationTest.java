package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_01_AccountRegistrationTest extends BaseClass {

	@Test
	public void verify_Account_Registration() {

		logger.info("****Starting TC_01_AccountRegistrationTest******");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my account");
		hp.clickRegister();
		logger.info("Clicked on Register");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Entering customer details on Register page");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setPhoneNumber(randomNumber());

		String password = randomAlphaNumeric();

		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.checkAgreePolicy();
		regpage.confirmButtonClick();

		String cnfrmmsg = regpage.getConfirmationMessge();
		logger.info("Verifying message");
		Assert.assertEquals(cnfrmmsg, "Your Account Has Been Created!");
		}
		catch(Exception e){
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
			
		}
	}

}
