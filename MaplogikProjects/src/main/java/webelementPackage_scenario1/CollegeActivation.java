package webelementPackage_scenario1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;


public class CollegeActivation extends BaseClass {
	@FindBy(xpath="//a[@class='dt-button create-new btn btn-primary waves-effect waves-float waves-light']")
	public static WebElement addnew;
	
	@FindBy(xpath="//*[@id='datatable']//following::td[contains(text(),'JPR Engineering College')]//preceding-sibling::td")
	public static WebElement logoicon;
	
	@FindBy(xpath="//td[contains(text(),'JPR Engineering College')]")
	public static WebElement nameicon;
	
	@FindBy(xpath="//td[contains(text(),'JPR Engineering College')]/following::td[1]")
	public static WebElement locationicon;
	
	@FindBy(xpath="//td[contains(text(),'JPR Engineering College')]/following::a[2]")
	public static WebElement deleteicon;

	@FindBy(xpath="//td[contains(text(),'JPR Engineering College')]/following::a[3]")
	public static WebElement importstudenticon;

	@FindBy(xpath="//td[contains(text(),'JPR Engineering College')]/following::a[4]")
	public static WebElement importmarkicon;

	public CollegeActivation ()
	{
		PageFactory.initElements(driver,this);
	}

	public static CollegeFieldpage clickaddnew()
	{
		addnew.click();
		return new CollegeFieldpage ();
	}

	public Boolean validatelogo()
	{
		return logoicon.isDisplayed();
	}
	public Boolean validatecollegename() {

		return nameicon.isDisplayed();
	}

	public Boolean validatelocation()
	{
		return locationicon.isDisplayed();
	}

	public Boolean deleteicon() 
	{

		return deleteicon.isDisplayed();
	}

	public Boolean importstudenticon() 
	{

		return importstudenticon.isDisplayed();
	}

	public Boolean importmarksicon()
	{
		return importmarkicon.isDisplayed();
	}

	public void delete() 
	{
		deleteicon.click();
	}

	public void alert()
	{
		Alert okalert = driver.switchTo().alert();
		okalert.accept();
	}
}
