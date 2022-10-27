package webelementPackage_scenario2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;

public class StudentAcademicInfo extends BaseClass{

	@FindBy(xpath="//span[@class='menu-title text-truncate' and contains(text(),'Log out')]")
	public static WebElement logOut;


	public StudentAcademicInfo() 
	{
		PageFactory.initElements(driver ,this);
	}

	public void clickacademic() throws InterruptedException, IOException {

		Thread.sleep(3000);
		Actions action = new Actions(driver);
		WebElement academic = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[7]/a/span"));
		action.moveToElement(academic).click().build().perform();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("/Users/revantha/eclipse-workspace/MaplogikProjects/screenshots/beforemarkupdation.png");
		FileUtils.copyFile(src, trg);
	}
	
	public void clickacademicinfo() throws InterruptedException, IOException {

		Thread.sleep(4000);
		Actions action = new Actions(driver);
		WebElement academic = driver.findElement(By.xpath("//*[@id=\"main-menu-navigation\"]/li[7]/a/span"));
		action.moveToElement(academic).click().build().perform();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("/Users/revantha/eclipse-workspace/MaplogikProjects/screenshots/aftermarkupdation.png");
		FileUtils.copyFile(src, trg);
	}

	public void logout() 
	{
		logOut.click();	
	}
	public void closeapp()
	{
		driver.quit();
	}
}
