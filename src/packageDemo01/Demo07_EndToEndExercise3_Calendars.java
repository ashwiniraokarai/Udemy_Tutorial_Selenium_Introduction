package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class Demo07_EndToEndExercise3_Calendars {

	/*
	 * DEMO: Hypothetical Test Case- Click on a specific date (as directed).
	 * The best approach to the problem is to break it down - do not attempt to
	 * achieve everything at the same time or you are setting yourself up for disaster
	 * 1.Figure out the logic to select a certain date of the default month on the calendar 
	 * 	-(Piece1 : probably the longest piece and will serve all months and years) 
	 * and build this code. Test it thoroughly, particularly boundary values
	 * 2. Surround it by the logic to choose year or month whichever makes more sense 
	 * 	-(Piece 2)
	 * 3. Finally include the code to select the year or month (whichever piece is remaining)
	 * -(Piece 3)
	 * That's how you build the logic to select a complete date
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://www.path2usa.com/travel-companions");
		
		//Invoke the calendar element
		driver.findElement(By.id("travel_date")).click();
		
		/**************************************************************************************/
		//Piece 2: Select a certain month (built AFTER Piece 1 was built)
		//Temporary Check Point:System.out.println("Testing the month element: " +driver.findElement(By.xpath("//*[@class='datepicker-days']//*[@class='datepicker-switch']")).getText());
		driver.findElement(By.xpath("//*[@class='datepicker-days']//*[@class='datepicker-switch']")).click();
		
		//Temporary Check Point:System.out.println("Testing the year element: "+driver.findElement(By.xpath("//*[@class='datepicker-months']//*[@class='datepicker-switch']")).getText());
		driver.findElement(By.xpath("//*[@class='datepicker-months']//*[@class='datepicker-switch']")).click();
		
		/*Navigate the "year" elements verify if class is either "year" or "year active" before selecting it
		 *This will ensure only enabled years are clicked - typically only the current year and immediate next year
		 *are enabled. All others are disabled*/
		
		WebElement singlElemDatePickerYear = driver.findElement(By.xpath("//*[@class='datepicker-years']//span[text()='2019']"));
		
		
		System.out.println(singlElemDatePickerYear.getAttribute("class"));
		if(singlElemDatePickerYear.getAttribute("class").matches("year|year active")){
		//Using "matches" allows you to compare multiple strings (regEx) with a string unlike "contentEquals"
			System.out.println("From inside if");
			singlElemDatePickerYear.click();
		}
		
		//Temporary Check Point: System.out.println("Year(s) found: "+driver.findElements(By.xpath("//*[@class='datepicker-years']//*[text()='2019']")).size());
		
		//Temporary Check Point: System.out.println("Month(s) found: "+driver.findElement(By.xpath("//*[@class='datepicker-months']//span[text()='Jan']")).getText());
		
		
		/**************************************************************************************/
		//Piece 3: Select a certain month (Built after piece 2)
		
		driver.findElement(By.xpath("//*[@class='datepicker-months']//span[text()='Jan']")).click();
		
		/**************************************************************************************/
		//Piece 1: Select a certain date of the default/ already chosen year and month 
		List<WebElement> dayElements = driver.findElement(By.className("datepicker-days"))
				.findElements(By.tagName("td"));
		ListIterator<WebElement> lstItrtr = dayElements.listIterator();

		//int found = 0; Was used inside loop to verify potential duplicates in the calendar
		
		//These declarations were added after building and as a result of the following while loop
		WebElement singlDayElement;
		String dayElementText;
		String dayElementClass;
		whileLoop1: while (lstItrtr.hasNext()) {
			singlDayElement = lstItrtr.next();
			dayElementText = singlDayElement.getText();
			System.out.println(dayElementText); //Temporary Check Point

			 /* Some searches were found to result in 2 hits due to
			 * overlapping of dates with previous or subsequent months,
			 * particularly if the desired date lies on the extremities i.e, first and last
			 * week. As a precaution, ensure that you limit your search to the default month
			 * only by extending your search filter/ criteria/ condition (beyond just matching text)
			 * accordingly as below:
			 */

			dayElementClass = singlDayElement.getAttribute("class");
			/*Different inputs, particularly boundary values were tested here (1,2,30,31). 
			 * In your real time test script this value to match would bepassed to the script 
			 * via an input parameter*/
			if (dayElementText.equals("4")
					&& (dayElementClass.contentEquals("day") || dayElementClass.contentEquals("today active day"))) {
				System.out.println("Found");
				
				//found++; WasUsed to track potential duplicates in the calendar 
				
				singlDayElement.click();
				break;
			}//end if 
			/*
			 * do NOT do a lstItrtr.next(); again, out of habit, or you risk missing the
			 * next element. Notice that .next() was done ONLY ONCE and retrieved into a
			 * veriable for all subsequent actions precisely for this reason.
			 */

		} // end whileLoop
		
		//System.out.println("Hits: " + found); Was used to track potential duplicates. Anything less than 0 or more than 1 indicates trouble

	}//end main

}//end class
