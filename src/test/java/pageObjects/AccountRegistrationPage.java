package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFisrtName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtPhoneNumber;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkAgreePolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btncontinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement txtConfirmMessage;

	public void setFirstName(String firstName) {
		txtFisrtName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPhoneNumber(String phoneNumber) {
		txtPhoneNumber.sendKeys(phoneNumber);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void setConfirmPassword(String confirmpassword) {
		txtConfirmPassword.sendKeys(confirmpassword);
	}

	public void checkAgreePolicy() {
		chkAgreePolicy.click();
	}

	public void confirmButtonClick() {
		btncontinue.click();
	}

	public String getConfirmationMessge() {

		try {
			return (txtConfirmMessage.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}

	}

}
