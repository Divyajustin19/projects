package webelementPackage_scenario2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;

public class StudentLogin extends BaseClass{
	@FindBy(xpath="//input[@id='login-student-id']")
	public static WebElement userID;

	@FindBy(xpath="//input[@id='login-mobile']")
	public static WebElement Mobileno;

	@FindBy(xpath="//button[contains(text(),'Log in')]")
	public static WebElement login;

	@FindBy(xpath="//button[contains(text(),'Submit')]")
	public static WebElement submit;

	public StudentLogin()
	{	

		PageFactory.initElements(driver ,this);
	}

	public void Studlogin() throws InterruptedException 
	{
		userID.sendKeys("3PECSC0603");
		Mobileno.sendKeys("9025445405");
		login.click();
		Thread.sleep(2000);
		WebElement otp = driver.findElement(By.xpath("//div[@class='card-body']//child::p[@id='test_otp']"));
		String generate = otp.getText();
		WebElement otpfield = driver.findElement(By.xpath("//div[@class='card-body']//following::input[@type='text'][@id='login-otp'][@name='login_otp']"));	
		otpfield.sendKeys(generate);
		submit.click();
	}
	public void OTPLogin() throws IOException, InterruptedException {
		String filepath = "./student-data/student.xlsx";
		File file = new File(filepath);
		file.createNewFile();
		FileInputStream input = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet1");
		userID.sendKeys("3PECSC0603");
		Mobileno.sendKeys("9025445405");
		login.click();
		Thread.sleep(2000);
		WebElement otp = driver.findElement(By.xpath("//div[@class='card-body']//child::p[@id='test_otp']"));
		String generate = otp.getText();
		WebElement otpfield = driver.findElement(By.xpath("//div[@class='card-body']//following::input[@type='text'][@id='login-otp'][@name='login_otp']"));	
		otpfield.sendKeys(generate);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String date1= dateFormat.format(date);
		System.out.println("OTP login " + generate + " \t " + "Current date and time is " + date1);

		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue( generate);
		Cell cell2 = row.createCell(1);
		cell2.setCellValue(date1);
		FileOutputStream outstream = new FileOutputStream(file);
		workbook.write(outstream);	
		submit.click();
	}
}
