package com.qa.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoanPage;
import com.qa.pages.LoanRegistrationPage;
import com.qa.util.DataUtil;
import com.qa.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class LoanRegistrationTest extends TestBase {

	LoanRegistrationPage loanRegistrationPage;
	LoanPage loanPage;

	String sheetName = "TestCases";

	String getLoanRatesTest = "GetLoanRatesTest";
	String loanRegistrationTest = "LoanRegistrationTest";
	String emailValidationTest = "EmailValidationTest";
	String postcodeValidationTest = "PostcodeValidationTest";
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "/src/main/java/com/qa/testdata/Data.xlsx");

	public LoanRegistrationTest() {
		super();
	}

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Extent.html", true);
		extent.addSystemInfo("Host Name", "Kartik Mac");
		extent.addSystemInfo("User Name", "Kartik Bhatt");
		extent.addSystemInfo("Environment", "QA");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loanPage = new LoanPage();
	}

	@Test(priority = 1)
	public void getLoanRatesTest() {
		extentTest = extent.startTest("getLoanRatesTest");
		loanRegistrationPage = loanPage.tapOnPersonalizedRates();
		String text = loanRegistrationPage.verifyWelcomeText();
		Assert.assertEquals(text, "Welcome to Zopa");
	}

	@Test(priority = 2, dataProvider = "getData")
	public void loanRegistrationTest(Hashtable<String, String> data) {
		extentTest = extent.startTest("loanRegistrationTest");
		if (data.get("Runmode").equals("N")) {
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		loanRegistrationPage = loanPage.tapOnPersonalizedRates();
		loanRegistrationPage.selectYourLoan(data.get("loan"));
		loanRegistrationPage.setEmnail(data.get("email"));
		loanRegistrationPage.selectTitle(data.get("title"));
		loanRegistrationPage.setFirstname(data.get("firstname"));
		loanRegistrationPage.setLastname(data.get("lastname"));
		loanRegistrationPage.setDay(data.get("day"));
		loanRegistrationPage.setMonth(data.get("month"));
		loanRegistrationPage.setYear(data.get("year"));
		loanRegistrationPage.selectEmploymentStatus(data.get("status"));
		loanRegistrationPage.setAnnualIncom(data.get("income"));
		loanRegistrationPage.selectHome(data.get("home"));
		loanRegistrationPage.setMonthlyRent(data.get("rent"));
		loanRegistrationPage.setJobTitle(data.get("jobtitle"));
		loanRegistrationPage.setEmployerName(data.get("empname"));
		loanRegistrationPage.setEmployerIndustry(data.get("empindustry"));
		loanRegistrationPage.setPostcode(data.get("postcode"));
		loanRegistrationPage.selectLookUpAddress(data.get("address"));
		loanRegistrationPage.selectMonth(data.get("daymonth"));
		loanRegistrationPage.selectYear(data.get("dayyear"));
		loanRegistrationPage.tapUseThisAddress();
	}

	@Test(priority = 3, dataProvider = "getData")
	public void emailValidationTest(Hashtable<String, String> data) {
		extentTest = extent.startTest("emailValidationTest");
		if (data.get("Runmode").equals("N")) {
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		loanRegistrationPage = loanPage.tapOnPersonalizedRates();
		loanRegistrationPage.setEmnailWrong(data.get("email"), data.get("errorMsg"));
	}

	@Test(priority = 4, dataProvider = "getData")
	public void postcodeValidationTest(Hashtable<String, String> data) {
		extentTest = extent.startTest("postcodeValidationTest");
		if (data.get("Runmode").equals("N")) {
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		loanRegistrationPage = loanPage.tapOnPersonalizedRates();
		loanRegistrationPage.setPostcodeWrong(data.get("postcode"), data.get("errorMsg"));
	}

	@DataProvider()
	public Object[][] getData(Method m) {
		switch (m.getName()) {
		case "loanRegistrationTest":
			return DataUtil.getData(loanRegistrationTest, xls);
		case "emailValidationTest":
			return DataUtil.getData(emailValidationTest, xls);
		case "postcodeValidationTest":
			return DataUtil.getData(postcodeValidationTest, xls);
		}
		return null;
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
																							// extent report

			String screenshotPath = DataUtil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in extent
																							// report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html extent
									// report
		driver.quit();
	}
}
