package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test

	public void verify_login() {
		logger.info("----Starting TC_002 LoginTest---");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(properties.getProperty("email"));
			lp.setPassword(properties.getProperty("password"));
			lp.clickLoginBtn();

			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetpage = myacc.validateAfterLogin();

			Assert.assertEquals(targetpage, true);

		} catch (Exception e) {
			Assert.fail();
		}
	}

}
