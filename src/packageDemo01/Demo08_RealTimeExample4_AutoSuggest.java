package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Demo08_RealTimeExample4_AutoSuggest {

	/*DEMO: Working with
	 *Auto-suggest on text boxes
	 *Navigating Ul-Li (unordered list) elements
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.get("https://fantasycricket.dream11.com/in/");
		driver.findElement(By.id("m_rtxtEmail1")).sendKeys("achuk"); //Triggers auto-suggest
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("mailtip")))); //Inspected the entire auto-suggest box
		
		//Suggestions inside the auto-suggest box are in the form of several unordered list elements
		List<WebElement> liElements = driver.findElements(By.xpath("//ul[@class='mailtip']/li"));
		ListIterator<WebElement> itrtr = liElements.listIterator();
		
		int flag = 0;
		whileLoop:while(itrtr.hasNext()) {
		 WebElement listElement = itrtr.next();
		 if (listElement.getAttribute("title").contains("aol")) { 
			 listElement.click();
			 System.out.println("Your e-mail was entered as: "+driver.findElement(By.id("m_rtxtEmail1")).getAttribute("value"));
			 //Remember getText() won't do the same thing for you!
			 flag = 1;
			 break;
		 }
		}
		
		if(flag==0) {
			System.out.println("Could not find the desired domain of your e-mail account!");
		}
		
	}

}
