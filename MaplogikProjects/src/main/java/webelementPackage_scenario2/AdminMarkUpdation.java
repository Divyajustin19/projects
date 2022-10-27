package webelementPackage_scenario2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import basePackage.BaseClass;

public class AdminMarkUpdation extends BaseClass {
	@FindBy(xpath="//*[contains(text(),'Panimalar college of Engineering')]/following::a[4]")
	public static WebElement importmark;

	@FindBy(xpath="//div[@class='card-body']//following::button[@id='submitbtn']")
	public static WebElement submit;
	
	public AdminMarkUpdation()
	{
		PageFactory.initElements(driver,this);
	}

	public void importmarks() throws IOException, CsvException
	{
		importmark.sendKeys(Keys.ENTER); 

		WebElement type = driver.findElement(By.xpath("//div[@class='card-body']//following::select[@id='course_type']"));
		Select typedd = new Select(type);
		typedd.selectByValue("UG");

		WebElement course = driver.findElement(By.xpath("//div[@class='card-body']//following::select[@id='course_name']"));
		Select coursedd = new Select(course);
		coursedd.selectByVisibleText("B.E Civil Engineering");

		WebElement semester = driver.findElement(By.xpath("//div[@class='card-body']//following-sibling::select[@name='semester'][@class='form-control']"));
		Select semesterdd = new Select(semester);
		semesterdd.selectByIndex(4);

		WebElement uploadcsv = driver.findElement(By.xpath("//div[@class='card-body']//following::input[@id='import_file']"));

		CSVReader csvreader = new CSVReader(new FileReader(new File("/Users/revantha/eclipse-workspace/MaplogikProjects/semester_4.csv")));
		List<String[]> alldata = csvreader.readAll();
		alldata.get(1)[1] = "7";

		CSVWriter csvwriter = new CSVWriter(new FileWriter(new File("/Users/revantha/eclipse-workspace/MaplogikProjects/semester_4.csv")));
		csvwriter.writeAll(alldata);
		csvwriter.flush();

		uploadcsv.sendKeys("/Users/revantha/eclipse-workspace/MaplogikProjects/semester_4.csv");

		submit.click();
	}
}
