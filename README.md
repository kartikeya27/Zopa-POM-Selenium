# **Page Object model Test automation framework using Selenium with Java, TestNG and Maven** 

## **This is a sample project to demonstrate what is page object model framework and how it can used in selenium to automate any application. TestNG is used as test framework**

**(1) First we have to copy git repository on our local system and below command from command line:**

```
git clone https://github.com/kartikeya27/SeleniumHybridFramework.git
cd SeleniumHybridFramework
mvn clean compile test

```
**(2) Ncessary Libraries and Software:** 

```
(1) Install maven on MAC and add maven path into environment path inside ./bashrc_profile.
(2) Install jdk1.8 on mac and add path into environment path inside ./bashrc_profile.
(3) Type java --version and mvn --version.
(4) Latest chromedirver and make it executable by chmod +x chromdriver and geckodriver. I can also use
    WebDriverManager but in this case I have created chromedriver and geckodriver folder and put executable
    inside there.
(5) It is maven based porject so all necessary dependecnies will download it automatically once you import
    this project in any preferable IDE.

```
**(3) run test from different area:** 
```
* Once we copy this project on local system either we can run the test cases from command line 
  or from any IDE of your preference. In Eclipse IDE we can right click on textng.xml inside 
  /src/main/resources folder and run as a TestNG suites.
* We can also run from LoginTest.java file right click on it and run as a TestNG suites.


```
### **Structure about this framework:**
```
1. Testbase package is the place where we can put all commom functionalty of the testcase as well as how to 
   launch the browser.

2. config package with global variable for example testing url.

3. page package where all id, name or xpath locator with common method dscribe here.

4. testdata package holds a test data for login test.

5. util package have xls file reader, webeventlistner for logs and data common utility.

6. resouces packages holds testng.xnl file for this project.

7. test package where we define our testcases.

8. ExtentReportListener package for extent report functionality.

9. FailedTestsScreenshots will generate automatiacally if any of the test cases fail.

10. chromdirver folder for chromedriver executbale.

11. geckodriver folder for geckodriver executbale.
 
``` 

``` 
### **Project Structure diagram of this framework:**   

   
   ├── FailedTestsScreenshots
├── JavaSelenium.iml
├── README.md
├── chrome-driver
│   └── chromedriver
├── gecko-driver
│   └── geckodriver
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── qa
│   │   │           ├── ExtentReportListener
│   │   │           │   └── ExtentReportListener.java
│   │   │           ├── base
│   │   │           │   └── TestBase.java
│   │   │           ├── config
│   │   │           │   └── config.properties
│   │   │           ├── pages
│   │   │           │   ├── HomePage.java
│   │   │           │   └── LoginPage.java
│   │   │           ├── testdata
│   │   │           │   └── Data.xlsx
│   │   │           └── util
│   │   │               ├── DataUtil.java
│   │   │               ├── WebEventListener.java
│   │   │               └── Xls_Reader.java
│   │   └── resources
│   │       ├── log4j.properties
│   │       └── testng.xml
│   └── test
│       └── java
│           └── com
│               └── qa
│                   └── tests
│                       └── LoginPageTest.java

``` 
# **Note:**
## **Please do let me know, if you have any issue with running test locally or any questions about this framework.**








