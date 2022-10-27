package testcaseExecution;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basePackage.BaseClass;
import webelementPackage_scenario1.AdminHomepage;
import webelementPackage_scenario1.AdminLoginClass;
import webelementPackage_scenario1.CollegeActivation;
import webelementPackage_scenario1.CollegeFieldpage;

public class TestSuite_CollgeActivation extends BaseClass {
	AdminLoginClass objlogin;
	AdminHomepage objhomepage;
	CollegeActivation objactivationpage;
	CollegeFieldpage objtestdatas;
	Alert alert;

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeTest
	public void startReport() {
		// Create an object of Extent Reports
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Users/revantha/eclipse-workspace/MaplogikProjects/ExtentReport/report.html");
		extent.setSystemInfo("Environment", "TestNG");
		extent.setSystemInfo("User Name", "divya");
		spark.config().setDocumentTitle("Maplogik");
		spark.config().setReportName("College Updation");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
	}

	@Test (priority = 1)
	public void launchBrowser() throws IOException, InterruptedException
	{ 
		openChrome();
		openURL();
		test = extent.createTest("Test case started");
		objlogin = new AdminLoginClass ();
		objlogin.readexcel();
	}

	@Test (priority = 2, dependsOnMethods = "launchBrowser")
	public void clgeactivation() throws IOException, InterruptedException 
	{

		objhomepage = new AdminHomepage();
		objhomepage.clickclgeactivation();

		objactivationpage = new CollegeActivation();
		objactivationpage.clickaddnew();

		objtestdatas = new CollegeFieldpage();
		objtestdatas.testdata();

		test = extent.createTest("To verify college activation is done");
		boolean validateclgeactivation = objhomepage.validateclgeactivation();
		Assert.assertEquals(validateclgeactivation,true);
	}
	@Test (priority = 3, dependsOnMethods = "clgeactivation")
	public void validationicon() throws IOException 
	{
		test = extent.createTest("to Validate the icons before college deletion");
		objhomepage = new AdminHomepage();
		objactivationpage = new CollegeActivation();
		objhomepage.clickclgeactivation();

		
		boolean validateclgelogo = objactivationpage.validatelogo();
		Assert.assertEquals(validateclgelogo,true);

		
		boolean validateclgename = objactivationpage.validatecollegename();
		Assert.assertEquals(validateclgename,true);

		
		boolean validatelocation = objactivationpage.validatelocation();
		Assert.assertEquals(validatelocation,true);

		
		boolean validatedeleteicon = objactivationpage.deleteicon();
		Assert.assertEquals(validatedeleteicon,true);

		
		boolean validateimportstudenticon = objactivationpage.importstudenticon();
		Assert.assertEquals(validateimportstudenticon,true);


		boolean validateimportstudentmarksicon = objactivationpage.importmarksicon();
		Assert.assertEquals(validateimportstudentmarksicon,true);
	}

	@Test (priority = 4, dependsOnMethods = "validationicon")
	public void deleteclgeicon()
	{
		test = extent.createTest("To verify the added college is deleted successfully");
		objactivationpage = new CollegeActivation();
		objactivationpage.delete();
		objactivationpage.alert();
	}

	@Test (priority = 5, dependsOnMethods = "deleteclgeicon")
	public void logout()
	{
		
		objhomepage = new AdminHomepage();
		objhomepage.logout();
		objhomepage.closeapp();
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@AfterTest
	public void endReport() 
	{
		extent.flush();
	}
}
