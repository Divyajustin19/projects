package webelementPackage_scenario1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;

public class AdminLoginClass extends BaseClass{
	@FindBy(xpath="//input[@id='login-email']")
	public static WebElement userName;

	@FindBy(xpath="//input[@id='login-password']")
	public static WebElement passWord;

	@FindBy(xpath="//button[text()='Log in']")
	public static WebElement login;

	public AdminLoginClass()
	{	
		
		PageFactory.initElements(driver,this);
	}
	
	public static void readexcel() throws IOException {

		File src = new File("/Users/revantha/eclipse-workspace/MaplogikProjects/datafiles/sample.xls");
		FileInputStream input = new FileInputStream(src);
		HSSFWorkbook workbook = new HSSFWorkbook(input);
		HSSFSheet sheet = workbook.getSheet("sheet1");
		Row row = sheet.getRow(1);
		Cell cell1 = row.getCell(0);
		Cell cell2 = row.getCell(1);
		String username =cell1.getStringCellValue();
		String password = cell2.getStringCellValue();

		userName.sendKeys(username);
		passWord.sendKeys(password);
		login.click();
	}
}
