package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory -OR
	@FindBy(name = "user-name")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "login-button")
	WebElement loginBtn;

	@FindBy(xpath = "//body/div[@id='root']/div/div[@class='login_wrapper']/div[@class='login_wrapper-inner']/div[@id='login_button_container']"
			+ "/div[@class='login-box']/form/div[1]/*[1]")
	WebElement usernameIcon;

	@FindBy(xpath = "//body/div[@id='root']/div/div[@class='login_wrapper']/div[@class='login_wrapper-inner']/div[@id='login_button_container']"
			+ "/div[@class='login-box']/form/div[2]/*[1]")
	WebElement passwordIcon;

	@FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
	WebElement errorMessageTxt;

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public void enterUsername(String uname) {
		username.clear();
		username.sendKeys(uname);

	}

	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}

	public void tapLogin(String username, String password, String errorMes) {
		enterUsername(username);
		enterPassword(password);
		loginBtn.click();
		isErrorMsgExist(errorMes);
	}

	public boolean isErrorMsgExist(String errorMessage) {
		List<WebElement> elements = driver.findElements(By.cssSelector("h3[data-test='error']"));

		for (WebElement elem : elements) {
			if (elem.getText().contains(errorMessage)) {
				return true;
			}
		}

		return false;
	}

	public boolean usernameIcon() {
		return usernameIcon.isDisplayed();
	}

	public boolean passwordIcon() {
		return passwordIcon.isDisplayed();
	}

	public HomePage login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		loginBtn.click();
		return new HomePage();
	}

}
