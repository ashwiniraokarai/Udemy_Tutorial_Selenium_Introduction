package packageDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basics14_Synchronization04_ImplAndExplWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		/*
		 * ============================================================= 
		 * Repeating the code with IMPLICIT WAIT as well as EXPLICIT WAIT statment
		 */
		System.out.println("Attempt4: With IMPLICT and EXPLICIT wait statements");
		//IMPLICIT WAIT (GLOBAL)
		//driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://alaskatrips.poweredbygps.com/g/pt/vacationpackages?MDPCID=ALASKA-US.TPS.BRAND.VACATIONPACKAGES.PACKAGE");
		/*With no implicit wait here, page loading was causing sporadic error of inputing a different search keyword altogether!
		 * Therefore, including an explicit wait right here
		 */
		
		driver.findElement(By.xpath("//a[@data-lob='hotelOnly']")).click();

		/*
		 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * Commenting the above Thread.Sleep code. Explicit wait is expected to take
		 * care of sync trouble.
		 */
		
		//EXPLICIT WAIT
		//WebDriverWait expWait = new WebDriverWait(driver, 3);
		WebDriverWait expWait = new WebDriverWait(driver, 0);
		/* By now you know that the above expWait instantiation works for the immediate following wait step, but encounter an exception at 
		 * next EXPLICIT WAIT  for sure if max wait time is limited to 3 seconds:
		 * "Exception in thread "main" org.openqa.selenium.TimeoutException: Expected condition failed: waiting for visibility
		 * of element located by By.xpath: //div[@id='resultsContainer']/section/article[1]/div[2]/div/a (tried for 3 second(s) 
		 * with 500 MILLISECONDS interval)"
		 * DESPITE knowing this, we leave the duration of EXPLICIT WAIT to 3s (as-is) ON PURPOSE NOR do we freshly instantiate WebDriverWait 
		 * with increased duration (6s) exclusively for the next explicit wait 
		 * This is an experiment to find out if IMPLICIT WAIT will TAKE OVER EXPLICIT WAIT (whenever EXPLICIT WAIT
		 * is INSUFFICIENT, as in this case) and WORK it's MAGIC, IF the page loads within the IMPLICIT time's max duration
		 * Outcome of experiment: Yes, implicit wait took over
		 * =================================================================================================================
		 * If it were the other way, then EXPLICIT wait would take over (resulting in either pass or failure, depending on
		 * the VALUES they (waits) EACH HOLD)
		 *  For e.g.: When tried with a deliberate, super insufficient value on IMPLICIT wait and an insufficient value on
		 *  EXPLICIT wait (but HIGHER THAN IMPLICIT wait), then IMPLICIT WAIT won't (and has NO POINT) TAKE OVER
		 *  		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		 *  WebDriverWait expWait = new WebDriverWait(driver, 2);
		 *  So, you end up with an EXPLICIT WAIT EXCEPTION:
		 *  "Exception in thread "main" org.openqa.selenium.TimeoutException: Expected condition failed: 
		 *  waiting for visibility of element located by By.xpath: //div[@id='resultsContainer']/section/article[1]/div[2]/div/a 
		 *  (tried for 2 second(s) with 500 MILLISECONDS interval)
		 *  at org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:82)"
		 *  You will also end up in similar EXPLICIT WAIT exception with this combination:
		 *  	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 *  	WebDriverWait expWait = new WebDriverWait(driver, 0);
		 *  ...because regardless of IMPLCIT wait's max duration in comparison to EXPICIT wait's max duration, the question 
		 *  to ask WHEN EXPLICIT WAIT IS WITHIN THE INSUFFICIENT WINDOW, IS IMPLICIT WAIT's max duration within the SUFFICIENCY 
		 *  WINDOW? If yes, you can see the IMPLICIT wait's taking over visually. Otherwise, you can't see the taking over
		 *  visually because it does not record as part of whatever exception is thrown (unlike EXPLICIT wait, which clearly
		 *  shows on the exception when it hasn't worked).
		 */
		expWait.until(ExpectedConditions.elementToBeClickable(By.id("H-destination")));
		
		driver.findElement(By.id("H-destination")).click();
		driver.findElement(By.id("H-destination")).sendKeys("nyc");
		driver.findElement(By.id("H-destination")).sendKeys(Keys.TAB);
		driver.findElement(By.id("H-fromDate")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("enableDateless")).click();
		/*
		 * Hitting enter now allows you to navigate to the next page. More importantly,
		 * this is the point where there is a long load time that we are trying to get
		 * past.
		 */
		driver.findElement(By.id("enableDateless")).sendKeys(Keys.ENTER);
		/*
		 * Explicit wait goes here (could also have been potentially used in place of
		 * implicit wait to exclusively take care of the first page load issue at
		 * driver.get(...) step)
		 */
		
		/*You don't need to instantiate WebDriverWait once again. Simply use the handle you created once and reuse it
		 */
		WebElement elemAttmpt4 = expWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='resultsContainer']/section/article[1]/div[2]/div/a")));
		System.out.println(elemAttmpt4.getTagName());
		System.out.println(elemAttmpt4.getAttribute("href"));
		elemAttmpt4.click();

	}

}
