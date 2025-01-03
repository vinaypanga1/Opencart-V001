package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    // Test method to verify account registration process
    @Test
    public void verify_Account_Registration() {

        // Log the start of the test case
        logger.info("****Starting TC_01_AccountRegistrationTest******");
        
        try {
            // Initialize HomePage object to interact with elements on the Home page
            HomePage hp = new HomePage(driver);
            
            // Click on "My Account" link
            hp.clickMyAccount();
            logger.info("Clicked on my account");
            
            // Click on "Register" link to navigate to the registration page
            hp.clickRegister();
            logger.info("Clicked on Register");

            // Initialize AccountRegistrationPage object to interact with elements on the registration page
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("Entering customer details on Register page");

            // Set random first name (generated using randomString method)
            regpage.setFirstName(randomString().toUpperCase());
            
            // Set random last name (generated using randomString method)
            regpage.setLastName(randomString().toUpperCase());
            
            // Set random email (generated using randomString method) + "@gmail.com"
            regpage.setEmail(randomString() + "@gmail.com");
            
            // Set random phone number (generated using randomNumber method)
            regpage.setPhoneNumber(randomNumber());

            // Generate a random password using randomAlphaNumeric method
            String password = randomAlphaNumeric();

            // Set the password and confirm password fields with the same password
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);
            
            // Agree to the privacy policy by checking the checkbox
            regpage.checkAgreePolicy();
            
            // Click on the confirm button to complete registration
            regpage.confirmButtonClick();

            // Retrieve the confirmation message after successful registration
            String cnfrmmsg = regpage.getConfirmationMessge();
            logger.info("Verifying message");

            // Assert that the confirmation message is as expected
            Assert.assertEquals(cnfrmmsg, "Your Account Has Been Created!");
        }
        catch(Exception e){
            // Log test failure if any exception occurs
            logger.error("Test Failed");
            logger.debug("Debug logs");
            
            // Fail the test case in case of an exception
            Assert.fail();
        }
    }
}
