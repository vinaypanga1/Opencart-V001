package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;

public class TC_003_Login_DDT extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = Dataproviders.class)
	public void verify_loginDDT(String email, String password, String exp) {

		logger.info("----Starting TC_003 LoginTest_DDT---");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLoginBtn();

			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetpage = myacc.validateAfterLogin();

			if (exp.equalsIgnoreCase("valid")) {
				if (targetpage) {
					myacc.clickonLogout();
					Assert.assertTrue(true);
				} else {
					Assert.fail("Login failed for valid credentials.");
				}
			} else {
				if (!targetpage) {
					Assert.assertTrue(true);
				} else {
					myacc.clickonLogout();
					Assert.fail("Login succeeded for invalid credentials.");
				}
			}

		} catch (Exception e) {
			Assert.fail("Exception occurred: " + e.getMessage());
		} finally {
			logger.info("---Completed TC_003_Login_DDT----");
		}
	}
}
