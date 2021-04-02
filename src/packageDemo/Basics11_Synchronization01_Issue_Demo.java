package packageDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basics11_Synchronization01_Issue_Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		System.out.println("Attempt1: With no wait statements");
		driver.get(
				"https://alaskatrips.poweredbygps.com/g/pt/vacationpackages?MDPCID=ALASKA-US.TPS.BRAND.VACATIONPACKAGES.PACKAGE");
		driver.findElement(By.xpath("//a[@data-lob='hotelOnly']")).click();

		/*
		 * There's a certain amount of delay in loading the page due to which selenium
		 * can't find the element: Exception in thread "main"
		 * org.openqa.selenium.NoSuchElementException: no such element: Unable to locate
		 * element: {"method":"id","selector":"H-destination"}
		 */

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.id("H-destination"));
		// Needed to add this click step right before the "sendKeys" because the script
		// was entering only partial text ("yc" out of "nyc" was being entered otherwise
		driver.findElement(By.id("H-destination")).click();
		driver.findElement(By.id("H-destination")).sendKeys("nyc");

		/*
		 * It takes couple "Tab" key press to focus the cursor on the "Check-in"
		 * calendar until hitting "Enter" to launch the results page (hack to get there
		 * with bare minimum form filling actions)
		 * driver.findElement(By.id("FH-origin")).sendKeys(Keys.TAB);
		 * driver.findElement(By.id("H-fromDate")).sendKeys(Keys.Enter); I am choosing
		 * to check the "I don't know my dates" check box option instead
		 */
		driver.findElement(By.id("H-destination")).sendKeys(Keys.TAB);
		driver.findElement(By.id("H-fromDate")).sendKeys(Keys.ENTER);
		/*
		 * On the website you'll see an expected error after hitting enter because you
		 * haven't provided any dates Chose to hit enter on purpose instead of hitting
		 * tab in order to avoid having to deal with choosing dates
		 */
		driver.findElement(By.id("enableDateless")).click();
		// Hitting enter now allows you to navigate to the next page. The point where
		// there is a long load time
		driver.findElement(By.id("enableDateless")).sendKeys(Keys.ENTER);
		/*
		 * On the results page, you want to click on a certain search result
		 * "Warwick New York" on the first page. It has one unique attribute (href) but
		 * its way too long. : <a class="flex-link" target="8736" href=
		 * "https://alaskatrips.poweredbygps.com/New-York-Hotels-Warwick-New-York.h8736.Hotel-Information?chkin=1%2F6%2F2018&amp;chkout=1%2F7%2F2018&amp;rm1=a2&amp;star=0&amp;regionId=6139058&amp;hwrqCacheKey=43777cba-aa26-40e7-9937-a6e1cc666c3aHWRQ1515222894435&amp;vip=false&amp;c=51ef37e2-35c4-444b-b776-21628291f5e6&amp;&amp;exp_dp=140.18&amp;exp_ts=1515222895311&amp;exp_curr=USD&amp;swpToggleOn=false&amp;exp_pg=HSR"
		 * data-track="HOT:SR:HotelModule" tabindex="0"> <span class="visuallyhidden">
		 * Select Warwick New York. Opens in a new window. </span> </a> So we decide use
		 * regular expression (for partial match) with xpath.
		 *driver.findElement(By.xpath("//a[contains(@href,'New-York-Hotels-Warwick-New-York')]")).click();
		 *Unfortunately, this doesn't workout because the search results keep varying based on certain parameters. Therefore,
		 *we simply decide to click on the FIRST RESULT
		 */
		
		driver.findElement(By.xpath("//div[@id='resultsContainer']/section/article[1]/div/div/a")).click();
		
		/*Either way,
		 * You end up with an exception due to the long page load time. Commenting the above line of code avoid termination
		 * of execution.
		 * We will repeat this block of code to see how using wait statements can combat this issue
		 * Exception in thread "main" org.openqa.selenium.NoSuchElementException: no
		 * such element: Unable to locate element: {"method":"xpath","selector":
		 * "//div[@id='resultsContainer']/section/article[1]/div[2]/div/a""}
		 */

	}// end main

}// end class Basics11_Synchronization01_Issue_Demo
