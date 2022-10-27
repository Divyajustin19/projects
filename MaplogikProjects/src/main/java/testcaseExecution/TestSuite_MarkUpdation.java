package testcaseExecution;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencsv.exceptions.CsvException;

import basePackage.BaseClass;
import webelementPackage_scenario1.AdminLoginClass;
import webelementPackage_scenario2.AdminHomePage;
import webelementPackage_scenario2.AdminMarkUpdation;
import webelementPackage_scenario2.StudentAcademicInfo;
import webelementPackage_scenario2.StudentLogin;


public class TestSuite_MarkUpdation extends BaseClass {


	StudentLogin objstud;
	StudentAcademicInfo objAcad ;
	AdminLoginClass objlogin;
	AdminHomePage objhomepage;
	AdminMarkUpdation objupdate;

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;
	@BeforeTest
	public void startReport() {
		// Create an object of Extent Reports
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Users/revantha/eclipse-workspace/MaplogikProjects/ExtentReport/studentreport.html");
		extent.setSystemInfo("Environment", "TestNG");
		extent.setSystemInfo("User Name", "divya");
		spark.config().setDocumentTitle("Maplogik");
		spark.config().setReportName("College Updation");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
	}

	@Test (priority = 1)
	public void launchstudenturl() throws IOException, InterruptedException
	{
		test = extent.createTest("test started");
		openChrome();
		openStudentURL();
		objstud = new StudentLogin();
		objstud.Studlogin();
	}

	@Test (priority = 2 , dependsOnMethods = "launchstudenturl")
	public void Validatemark() throws InterruptedException, IOException
	{
		test = extent.createTest("to validate student login before mark updation");
		objAcad = new StudentAcademicInfo();
		objAcad.clickacademic();
		objAcad.logout();
		objAcad.closeapp();
	}

	@Test (priority = 3, dependsOnMethods = "Validatemark")
	public void launchBrowser() throws IOException, InterruptedException
	{ 
		openChrome();
		openURL();
		objlogin = new AdminLoginClass();
		objlogin.readexcel();
	}

	@Test (priority =4, dependsOnMethods = "launchBrowser")
	public void markupdation() throws IOException, CsvException 
	{
		test = extent.createTest("to verify the mark is imported successfully");
		objhomepage = new AdminHomePage ();
		objhomepage.clickcollelgeactivation();
		objupdate = new AdminMarkUpdation();
		objupdate.importmarks();
		objhomepage.logout();
		objhomepage.closeapp();
	}

	@Test (priority = 5, dependsOnMethods = "markupdation")
	public void launchStudentApp() throws IOException, InterruptedException
	{
		openChrome();
		openStudentURL();
		objstud = new StudentLogin();
		objstud.OTPLogin();
	}


	@Test (priority = 6 , dependsOnMethods = "launchStudentApp" )
	public void ValidateAcademicmark() throws InterruptedException, IOException
	{
		test = extent.createTest("to validate student marks after mark updation");
		objAcad = new StudentAcademicInfo();
		objAcad.clickacademicinfo();
		objAcad.logout();
		objAcad.closeapp();
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
