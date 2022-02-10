package week5.day2Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssignIncident extends BaseService{
	
	@BeforeClass
	public void setValue() {
		fileName="assignIncident";
	}

	@Test(dataProvider="sendData")
	public void assignIncident(String AssignmentGroup,String Description) throws InterruptedException {
		
		// click on open and Search for the existing incident and click on  the incident
		Thread.sleep(5000);
		//driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//div[text()='Open']")).click();
		//driver.switchTo().defaultContent();
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("(//table[@id='incident_table']//tr[2])[2]/td[3]/a")).click();
		driver.switchTo().defaultContent();
		
		// Assign the incident to  Software group
		driver.switchTo().frame("gsft_main");
		WebElement assign = driver.findElement(By.xpath("//button[@id='lookup.incident.assignment_group']"));
		assign.click();
		driver.switchTo().defaultContent();
		Set<String> w = driver.getWindowHandles();
		List<String>list=new ArrayList<String>(w);
		driver.switchTo().window(list.get(1));
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(AssignmentGroup,Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='"+AssignmentGroup+"']")).click();
		driver.switchTo().window(list.get(0));
		
		
		
		
		// Update the incident with Work Notes
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//textarea[@id='activity-stream-textarea']")).sendKeys(Description);
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("gsft_main");
		String text = driver.findElement(By.xpath("//input[@id='sys_display.incident.assignment_group']")).getAttribute("value");
		System.out.println(text);
		driver.switchTo().defaultContent();
		// Verify the Assignment group and Assigned for the incident
		if(text.contains(AssignmentGroup))
			System.out.println("Verified that Assignment group is "+ AssignmentGroup);
		else
			System.out.println("You have assigned wrong group");
		
		
	}

}
