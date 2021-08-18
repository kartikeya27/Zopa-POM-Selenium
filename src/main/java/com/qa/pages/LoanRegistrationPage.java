package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

public class LoanRegistrationPage extends TestBase {

	// Page Factory -OR
	@FindBy(xpath = "//span[contains(.,'Welcome to Zopa')]")
	WebElement txtWelcome;

	@FindBy(css = "div.sc-ezrdKe.iyrCfc")
	List<WebElement> lstArray;

	@FindBy(id = "text-id-email")
	WebElement txtEmail;

	@FindBy(css = "div.sc-giIncl.eBfiCR")
	List<WebElement> lstTitle;

	@FindBy(id = "text-id-firstName")
	WebElement txtFirstname;

	@FindBy(id = "text-id-lastName")
	WebElement txtLastname;

	@FindBy(id = "text-id-day")
	WebElement txtDay;

	@FindBy(id = "text-id-month")
	WebElement txtMonth;

	@FindBy(id = "text-id-year")
	WebElement txtYear;

	@FindBy(id = "text-id-phone")
	WebElement txtPhone;

	@FindBy(xpath = "//label[@for='radio-id-EMPLOYED_FULL_TIME']")
	WebElement rdoEmploymentStatus;

	@FindBy(id = "annualIncome")
	WebElement txtIncome;

	@FindBy(id = "text-id-rent")
	WebElement txtRent;

	@FindBy(id = "text-id-jobTitle")
	WebElement txtJobTitle;

	@FindBy(id = "text-id-employerName")
	WebElement txtEmployerName;

	@FindBy(id = "text-id-employerSector")
	WebElement txtEmployerIndsutry;

	@FindBy(xpath = "//label[@for='radio-id-OWNER_NO_MORTGAGE']")
	WebElement rdoOwner;

	@FindBy(id = "postCode")
	WebElement txtPostcode;

	@FindBy(xpath = "//button[@type='button'][contains(.,'Look up address')]")
	WebElement btnLookAddress;

	@FindBy(xpath = "//button[contains(text(),'Use this address')]")
	WebElement btnThisAddress;

	// Initializing the Page Objects:
	public LoanRegistrationPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String verifyWelcomeText() {
		return txtWelcome.getText();
	}

	public void selectYourLoan(String loan) {
		List<WebElement> options = lstArray;
		for (WebElement option : options) {
			if (option.getText().equals(loan)) {
				option.click();
				break;
			}
		}
	}

	public void setEmnailWrong(String email, String errorMes) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
		txtEmail.click();
		
		isErrorMsgExist(errorMes);
	}

	public boolean isErrorMsgExist(String errorMessage) {
		List<WebElement> elements = driver.findElements(
				By.xpath("//*[@id=\"main\"]/div/section/div[3]/div/div[2]/div/form/div[2]/div/div[1]/div/span/span"));
		for (WebElement elem : elements) {
			if (elem.getText().contains(errorMessage)) {
				return true;
			}
		}
		return false;
	}

	public void setEmnail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void selectTitle(String titile) {
		List<WebElement> options = lstArray;
		for (WebElement option : options) {
			if (option.getText().equals(titile)) {
				option.click();
				break;
			}
		}
	}

	public void setFirstname(String fName) {
		txtFirstname.clear();
		txtFirstname.sendKeys(fName);
	}

	public void setLastname(String lName) {
		txtLastname.clear();
		txtLastname.sendKeys(lName);
	}

	public void setDay(String day) {
		txtDay.clear();
		txtDay.sendKeys(day);
	}

	public void setMonth(String month) {
		txtMonth.clear();
		txtMonth.sendKeys(month);
	}

	public void setYear(String year) {
		txtYear.clear();
		txtYear.sendKeys(year);
	}

	public void setPhoneNumber(String phoneNumber) {
		txtPhone.clear();
		txtPhone.sendKeys(phoneNumber);
	}

	public void selectEmploymentStatus(String status) {
		List<WebElement> options = lstArray;
		for (WebElement option : options) {
			if (option.getText().equals(status)) {
				option.click();
				break;
			}
		}
	}

	public void setAnnualIncom(String income) {
		txtIncome.clear();
		txtIncome.sendKeys(income);
	}

	public void selectHome(String home) {
		List<WebElement> options = lstArray;
		for (WebElement option : options) {
			if (option.getText().equals(home)) {
				option.click();
				break;
			}
		}
	}

	public void setMonthlyRent(String rent) {
		txtRent.clear();
		txtRent.sendKeys(rent);
	}

	public void setJobTitle(String jobTitle) {
		txtJobTitle.clear();
		txtJobTitle.sendKeys(jobTitle);
	}

	public void setEmployerName(String employerName) {
		txtEmployerName.clear();
		txtEmployerName.sendKeys(employerName);
	}

	public void setEmployerIndustry(String employerIndustry) {
		txtEmployerIndsutry.click();
		txtEmployerIndsutry.sendKeys(employerIndustry);
		txtEmployerIndsutry.sendKeys(Keys.ARROW_DOWN);
		txtEmployerIndsutry.sendKeys(Keys.ENTER);
	}

	public void setPostcode(String postcode) {
		txtPostcode.clear();
		txtPostcode.sendKeys(postcode);
	}

	public boolean isErrorMsgExistPostcode(String errorMessage) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"address-history\"]/div/div/div/span/span"));

		for (WebElement elem : elements) {
			if (elem.getText().contains(errorMessage)) {
				return true;
			}
		}
		return false;
	}

	public void setPostcodeWrong(String postcode, String errorMsg) {
		txtPostcode.clear();
		txtPostcode.sendKeys(postcode);
		btnLookAddress.click();
		isErrorMsgExistPostcode(errorMsg);
	}

	public void selectLookUpAddress(String address) {
		btnLookAddress.click();
		Select se = new Select(driver.findElement(By.id("select-address")));
		List<WebElement> selects = se.getOptions();
		WebElement menuItem = driver.findElement(By.id("select-address"));
		Actions action = new Actions(driver);
		action.moveToElement(menuItem);
		action.perform();
		menuItem.click();

		for (WebElement select : selects) {
			if ((select.getText().equals(address))) {
				select.click();
				break;
			}
		}
	}

	public void selectMonth(String month) {
		Select se = new Select(driver.findElement(By.id("month-select")));
		List<WebElement> selects = se.getOptions();
		WebElement menuItem = driver.findElement(By.id("month-select"));
		Actions action = new Actions(driver);
		action.moveToElement(menuItem);
		action.perform();
		menuItem.click();

		for (WebElement select : selects) {
			if ((select.getText().equals(month))) {
				select.click();
				break;
			}
		}
	}

	public void selectYear(String year) {
		Select se = new Select(driver.findElement(By.id("year-select")));
		List<WebElement> selects = se.getOptions();
		WebElement menuItem = driver.findElement(By.id("year-select"));
		Actions action = new Actions(driver);
		action.moveToElement(menuItem);
		action.perform();
		menuItem.click();

		for (WebElement select : selects) {
			if ((select.getText().equals(year))) {
				select.click();
				menuItem.click();
				break;
			}
		}
	}

	public void tapUseThisAddress() {
		btnThisAddress.click();
	}
}
