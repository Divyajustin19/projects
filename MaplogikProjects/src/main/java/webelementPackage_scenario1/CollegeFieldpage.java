package webelementPackage_scenario1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import basePackage.BaseClass;

public class CollegeFieldpage extends BaseClass{
	static Properties props=new Properties();

	@FindBy(xpath="//input[@id='course_name']")
	public static WebElement Collname;

	@FindBy(xpath="//input[@id='logo']")
	public static WebElement logo;

	@FindBy(xpath="//input[@id='address']")
	public static WebElement address;

	@FindBy(xpath="//input[@id='cnt_name']")
	public static WebElement getadminperson ;

	@FindBy(xpath="//input[@id='cnt_number']")
	public static WebElement getadminno;

	@FindBy(xpath="//input[@id='cnt_email']")
	public static WebElement getadminid;

	@FindBy(id ="cnt_name_plc")
	public static WebElement getplacementperson;

	@FindBy(xpath="//input[@id='cnt_number_plc']")
	public static WebElement getplacementno ;

	@FindBy(xpath="//input[@id='course_email_plc']")
	public static WebElement getplacementid;

	@FindBy(xpath="//button[@id='submitbtn']")
	public static WebElement submit;

	public CollegeFieldpage()
	{
		PageFactory.initElements(driver,this);
	}

	public static void testdata() throws IOException
	{
		FileReader reader=new FileReader("/Users/revantha/eclipse-workspace/MaplogikProjects/configuration/config.properties"); 
		// Properties props=new Properties(); 
		props.load(reader);

		Collname.sendKeys(props.getProperty("collegename"));

		logo.sendKeys(props.getProperty("logo"));

		address.sendKeys(props.getProperty("address"));

		//select district
		WebElement district = driver.findElement(By.xpath("//*[@name='location']"));
		Select distdd = new Select(district);
		distdd.selectByVisibleText("Chennai");

		//select affliation 
		WebElement affliation = driver.findElement(By.xpath("//*[@name='affliation']"));
		Select affliationdd = new Select(affliation);
		affliationdd.selectByIndex(1);

		//select affliated to 
		WebElement affliatedto = driver.findElement(By.xpath("//*[@name='affliated_to']"));
		Select affliatedtodd = new Select(affliatedto);
		affliatedtodd.selectByIndex(1);

		//select type of college
		WebElement collegetype = driver.findElement(By.xpath("//*[@name='college_type']"));
		Select colltypedd = new Select(collegetype);
		colltypedd.selectByValue("Professional");

		//UG Courses
		WebElement ugcourse = driver.findElement(By.xpath("//*[@id='bootstrap-duallistbox-nonselected-list_course[]']"));
		Select ugdd = new Select(ugcourse);
		ugdd.selectByVisibleText("B.E Computer Science and Engineering");

		getadminperson.sendKeys(props.getProperty("adminperson"));

		getadminno.sendKeys(props.getProperty("adminno"));

		getadminid.sendKeys(props.getProperty("adminid"));

		getplacementperson.sendKeys(props.getProperty("placementperson"));

		getplacementno.sendKeys(props.getProperty("placementno"));
		
		getplacementid.sendKeys(props.getProperty("placementid"));

		submit.sendKeys(Keys.ENTER);

	}
}
