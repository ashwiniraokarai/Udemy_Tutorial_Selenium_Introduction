package packageDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Basics03_InvokeBrowser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Ash\\Selenium JARs and Bindings\\geckodriver-v0.19.1-win64\\geckodriver.exe");

		// Invoke firefox (blank page)
		WebDriver driver = new FirefoxDriver();

		/*
		 * Type driver. to explore possible actions on your "driver" interface objects
		 * (methods of the WebDriver interface implemented in the Firefox class
		 * encapsulated in the object instance "driver") TIP: Further, if you do a
		 * driver. and type get, intellisense will list all associated methods beginning
		 * with get. Cool, or what?
		 */

		// Launch a URL
		driver.get("http://www.qaclickacademy.com");

		// Get title of the current page
		System.out.println(driver.getTitle());

		/*
		 * Handy when you want to check whether the URL was redirected to another URL
		 * (undesirably) or stayed on after intended launch
		 */

		System.out.println(driver.getCurrentUrl());

		/*
		 * driver.getClass();
		 * driver.getWindowHandle();
		 * driver.getWindowHandles();
		 * 
		 * //Handy when the page's security does not allow right-clicks to view source
		 * code driver.getPageSource()
		 * 
		 */

		driver.quit();

	}

}
