package basePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class BaseClass {
	
public static WebDriver driver;
	
	public static void openChrome()
	{
		System.setProperty("webdriver.chrome.driver","//Users//revantha//Downloads//chromedriver-3");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}
	public static void openURL() throws IOException {

		String location ="//Users//revantha/eclipse-workspace//TestngDemo//Test.txt";
		FileReader fileReader = new FileReader(location);
		BufferedReader reader = new BufferedReader(fileReader);
		String currentLine = reader.readLine();
		driver.manage().window().maximize();
		driver.get(currentLine);
	}
	public static void openStudentURL() throws IOException {

		String location ="/Users/revantha/eclipse-workspace/TestngDemo/texturl.txt";
		FileReader fileReader = new FileReader(location);
		BufferedReader reader = new BufferedReader(fileReader);
		String studentURL = reader.readLine();
		driver.get(studentURL);
	}
	
}
