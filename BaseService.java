package week5.day2Assignment;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseService {
	public ChromeDriver driver;
	public String fileName;
	
	@Parameters({"url","userName","password"})
	@BeforeMethod
	public void login(String url,String userName,String password) throws InterruptedException {
      WebDriverManager.chromedriver().setup();
		
		//Launch the Driver
		
		 driver=new ChromeDriver();
		
		//Load the URL
		
		driver.get(url);
		
		//Maximise the Browser
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.switchTo().frame("gsft_main");
		//Enter userName and password and click Login
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		driver.switchTo().defaultContent();
		//Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident",Keys.ENTER);
		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void close() {
		driver.close();
	}
	
	@DataProvider
    public String[][] sendData() throws IOException{
    	
    	return ReadExcel.readExcel(fileName);
    }

}
