package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends HomePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement ipEmail;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement ipPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnlogin;

	public void setEmail(String email) {
		ipEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		ipPassword.sendKeys(password);
	}

	public void clickLoginBtn() {
		btnlogin.click();
	}

}
