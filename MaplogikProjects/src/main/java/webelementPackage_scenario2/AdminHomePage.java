package webelementPackage_scenario2;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;


public class AdminHomePage extends BaseClass {

	@FindBy(xpath="//span[text()='College Activation']")
	public static WebElement clgeactivate;

	@FindBy(xpath="//span[text()='Log out']")
	public static WebElement logout;

	public AdminHomePage()
	{
		PageFactory.initElements(driver,this);
	}

	public Boolean validateclgeactivation()
	{
		return clgeactivate.isDisplayed();
	}

	public AdminMarkUpdation clickcollelgeactivation()
	{
		clgeactivate.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return new AdminMarkUpdation();
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
