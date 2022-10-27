package webelementPackage_scenario1;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;


public class AdminHomepage extends BaseClass{

	@FindBy(xpath="//span[text()='College Activation']")
	public static WebElement clgeactivate;

	@FindBy(xpath="//span[text()='Log out']")
	public static WebElement logout;

	public  AdminHomepage ()
	{
		PageFactory.initElements(driver,this);
	}

	public Boolean validateclgeactivation()
	{
		return clgeactivate.isDisplayed();
	}

	public CollegeActivation clickclgeactivation()
	{
		clgeactivate.click();
		
		return new CollegeActivation();
	}
	
	public void logout() 
	{
		logout.click();	
	}
	public void closeapp()
	{
		driver.quit();
	}
}
