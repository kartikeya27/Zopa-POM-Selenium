package com.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class LoanPage extends TestBase {

	@FindBy(xpath = "(//button[contains(@data-automation,'ZA.button-personal-rate')])[1]")
	WebElement personalizedRatesTxt;

	// Initializing the Page Objects:
	public LoanPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public LoanRegistrationPage tapOnPersonalizedRates() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(personalizedRatesTxt)); 
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", personalizedRatesTxt);
		return new LoanRegistrationPage();
	}
}
