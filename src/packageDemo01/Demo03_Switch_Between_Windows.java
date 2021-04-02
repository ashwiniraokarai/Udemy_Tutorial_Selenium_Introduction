package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;

public class Demo03_Switch_Between_Windows {

	/*DEMO: Switching between 2 web pages when one has orginated from another
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/SignUp");
		driver.findElement(By.linkText("Learn more")).click();
		System.out.println("\nHello from default web driver:"); //Just to monitor o/p
		System.out.println(driver.getTitle()); //Just to monitor o/p
		
		/*The next intuitive thing you want do is call the switchTo() method that returns a target locator object
		 * And then call the "window" method on it just like you did with "Alert" windows. It return to you
		 * the driver focused on the other window you are trying to get a hold of
		 * Combine the 2 steps and you get this:
		 * driver.switchTo().window(?);
		 * Now, the window method needs an argument - name OR handle of the window returned by WebDriver.getWindowHandle()
		 * Name looks like the simpler option but browser tool cannot inspect the window name/ title (try it!)
		 * The only other alternative is get a window handle. So, let's get that out of the way first!
		 */
		Set<String> strHandles = driver.getWindowHandles();
		
		/*You now have handles to all the opened windows originating from the parent but in the form of a SET of STRINGS
		 *The first element (string-handle) in the Set of Strings is RESERVED for Parent (of type string, of course)
		 *to the Parent. Then come(s) the child(ren).
		 *In this case we know for sure its just one child element (because only one additional window opened, duh!)
		 *But that's not always the case (you'll want to iterate over a loop, ideally, for this reason)
		 *The SET object provides you with an iterator() method that helps you iterate through it's elements
		 */
		
		Iterator<String> itrtr = strHandles.iterator();
		
		/*You don't need the next step right now but you have to go past this step in order to reach the child 
		handle. Having a handle on the parent also helps in switching back to parent at a later time.
		*/
		String strParentHandle = itrtr.next(); 
		
		System.out.println(strParentHandle);
		/*Turns out the handle is indeed simply a String that looks like this:
		CDwindow-(8E6FB1DC1F62A86CF5B76E6A3E2DA805). This is just FYI>.
		*/
		
		System.out.println("\nHello from default driver again, before switching:");
		System.out.println(driver.getTitle());
		
		String strChildHandle = itrtr.next();
		/*Finally, you have a handle to your child window. Time to use it in the driver.switchTo().window(?)
		and gain access to the SWITCHED web driver element
		*/
		WebDriver childWindow = driver.switchTo().window(strChildHandle);
		String childWindowTitle = childWindow.getTitle();	//All methods traditionally available to "driver" object are available
		System.out.println("\nHello from child driver after switching to child:\n" +childWindowTitle+".");
		
		/*But you won't need to use the childWindow method. Simply use the "driver" object as before:
		*driver.getTitle()
		*the whole point was to "switch" the driver object from having its default handle on the parent
		*window to the child window and it has been done!
		*/
		System.out.println("\nHello from default driver after switching to child:\n" +driver.getTitle()+".");
		
		//Now switching back to parent! Fun!
		driver.switchTo().window(strParentHandle);
		System.out.println("\nHello from default driver after switching back to parent:\n"+driver.getTitle()+".");
	}

}
