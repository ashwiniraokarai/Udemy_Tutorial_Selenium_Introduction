package packageDemo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Basics10_EndToEnd_Exercise {
	
	//Demo: Calendars Elements
	public static void main(String args[]) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		
		//Trying to click open the calendar for "Depart" element
		driver.findElement(By.xpath("//input[@id='hp-widget__depart']")).click();
		
		//Wrote a sleep to get past the "element not visible" error. Eclipse enforced the try-catch block
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Choosing a date from the first group. 2 calendars open with current and next month.
		*We are trying to choose the one on left
		*Since the same calendar code is rendered on both the calendars, the XPATH needs to begin from an ancestor that
		*will bring uniqueness to the XPATH and thus to the element identification
		*Remember the 'parent-to-child top-down traverse' technique to arrive at the child?
		*/
		
		driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']/table/tbody/tr[4]/td[7]")).click();
		
		/*The day 28 was selected for the default (current month). However, 28th need not fall on the same week or 
		 * same day of the week, thus varying the tr[] and td[] child sequences and clicking a whichever value is present on that terminal of the path (could be day 25)
		*If a specific date needs to be selected, then the other approach in this case seems relying on the 
		*day element (relative value) without traversing (w/o absolute value)
		*while preserving uniqueness such as this xpath: "//*[@fare-date='1519542000000'"
		*Disadvantage: However, knowing this value, needs inspection (it is specific to the month and the value differs)
		*driver.findElement(By.xpath("//*[@fare-date='1517122800000']")).click();
		*/
		driver.findElement(By.id("hp-widget__paxCounter")).click();
		
		/*This an unordered list with elements under it (ul parent tag -li children tag. Yet to figure out how to
		handle this
		Observation: driver.findElements(By.className("adult_counter")).get(0).size() returned 1 even though there are
		9 elements under this ulist
		The line of code below (get(0).click()) clicked on the 5th element!
		Any other apparently valid index such as get(5) results in the exception:
		Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 5, Size: 1
		NEED TO REVISIT:
		driver.findElements(By.className("adult_counter")).get(5).click(); 
		*/
		
		
	}//end main
	

	
	
	
	

}//end class
