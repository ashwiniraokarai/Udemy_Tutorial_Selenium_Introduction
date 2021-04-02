package packageDemo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basics08_ExploringElements3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*DEMO: Handling JavaScript Alert Boxes
		 * Non-HTML pop-ups such as JS Alert Boxes cannot be inspected by the browser
		 * In such case, the driver itself has to be SWITCHED to TargetLocator class using switchTo() method of driver interface object
		 * The resultant targetlocator object can then be used to call the alert() method that returns an object of type Alert (AlertBox)
		 * The Alert object has several methods		
		 * The Alert object has several methods such as .sendKeys(),.accept(),.dismiss() 
		*/
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.tizag.com/javascriptT/javascriptalert.php");
		driver.findElement(By.xpath("//input[@value='Confirmation Alert']")).click();
		
		/*TargetLocator a = driver.switchTo();
		*Alert b = a.alert();
		*This can be combined into one step:
		*driver.switchTo().alert();
		*/
		
		String text = driver.switchTo().alert().getText(); 
		/*Food for thought: How do you validate existence of the alert box?
		*Java throws exception if alert box is not present
		*"Exception in thread "main" org.openqa.selenium.NoAlertPresentException: no alert open"
		*Can we utilize this in some manner (exception handling) to validate existence of the alert box?
		*/
		
		//Validating presence of text in alert box
		if(!text.equals(null)) {
			System.out.println("Encountering a non-HTML alert box carrying a message.");
			if(text.contains("Are you sure you want to give us the deed to your house?")) {
				driver.switchTo().alert().accept(); //works on OK, YES and DONE buttons
													//alternately, you can use .sendKeys() method
													//use .dismiss() method to operate NO and CANCEL buttons
				System.out.println("Accepted the alert box message: '"+text+"'.");
			}else { //inner if
				System.out.println("Unexpected text in alert box: '"+text+"'.");
			}//inner-else
		}else{//outer-if
			System.out.println("No text found inside encountered alert box");
		}
	}//outer-else

}
