package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.base.TestBase;

public class DataUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 10;
	public static long IMPLICIT_WAIT = 10;

	public static Object[][] getData(String testName, Xls_Reader xls) {
		// String testName="CreateLeadTest";
		String sheetName = "TestCases";

		// Xls_Reader xls = new
		// Xls_Reader(System.getProperty("user.dir")+"//Data.xlsx");
		int testStartRowNum = 1;
		while (!xls.getCellData(sheetName, 0, testStartRowNum).equals(testName)) {
			testStartRowNum++;
		}

		// System.out.println("Row num for test "+ testStartRowNum);
		int colRowNum = testStartRowNum + 1;

		// total cols , rows
		int totalCols = 0;

		while (!xls.getCellData(sheetName, totalCols, colRowNum).equals("")) {
			totalCols++;
		}
		// System.out.println("Total Cols "+ totalCols);

		int dataStartRowNum = colRowNum + 1;
		int totalRows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + totalRows).equals("")) {
			totalRows++;
		}
		// System.out.println("Total Rows "+ totalRows);
		Object testData[][] = new Object[totalRows][1];
		// extract data for the test
		Hashtable<String, String> table = null;
		int i = 0;
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + totalRows; rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < totalCols; cNum++) {
				String data = xls.getCellData(sheetName, cNum, rNum);
				String key = xls.getCellData(sheetName, cNum, colRowNum);
				// System.out.print(key+"-"+data+" --- ");
				table.put(key, data);
			}
			// table will be ready
			testData[i][0] = table;
			i++;
			System.out.println();
		}

		return testData;
	}

	// true - Y
	// false - N
	public static boolean isRunnable(String testName, Xls_Reader xls) {
		String sheetName = "TestCases";
		String Runmode = "Runmode";
		String TCID = "TCID";
		int rows = xls.getRowCount(sheetName);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCaseName = xls.getCellData(sheetName, TCID, rNum);
			if (testName.equals(testCaseName)) {
				String runmode = xls.getCellData(sheetName, Runmode, rNum);
				if (runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

//	public static void takeScreenshotAtEndOfTest() throws IOException {
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String currentDir = System.getProperty("user.dir");
//		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
//	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
