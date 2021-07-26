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
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.DataUtil;
import com.qa.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	String sheetName = "TestCases";

	String incorretTestName = "IncorrectLoginTest";
	String correctTestName = "CorrectLoginTest";
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "/src/main/java/com/qa/testdata/Data.xlsx");

	public LoginPageTest() {
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

	// test cases should be separated -- independent with each other
	// before each test case -- launch the browser and login
	// @test -- execute test case
	// after each test case -- close the browser

	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1, dataProvider = "getData")
	public void incorretTestName(Hashtable<String, String> data) {
		extentTest = extent.startTest("incorrectUsernamePassword");
		if (data.get("Runmode").equals("N")) {
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		loginPage.tapLogin(data.get("username"), data.get("password"), data.get("errorMsg"));
		Assert.assertTrue(loginPage.usernameIcon());
		Assert.assertTrue(loginPage.passwordIcon());
		Assert.assertTrue(loginPage.isErrorMsgExist(data.get("errorMsg")));
	}

	@Test(priority = 2, dataProvider = "getData")
	public void correctTestName(Hashtable<String, String> data) {
		extentTest = extent.startTest("correctUsernamePassword");
		if (data.get("Runmode").equals("N")) {
			throw new SkipException("Skipping the test as Runmode was NO");
		}
		homePage = loginPage.login(data.get("username"), data.get("password"));
		Assert.assertTrue(homePage.verifyLogo());
	}

	@DataProvider()
	public Object[][] getData(Method m) {
		switch (m.getName()) {
		case "incorretTestName":
			return DataUtil.getData(incorretTestName, xls);
		case "correctTestName":
			return DataUtil.getData(correctTestName, xls);
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
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screencast/video in extent report
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
