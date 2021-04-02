package packageDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basics13_Synchronization03_ExplicitWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		/*
		 * ============================================================= 
		 * Repeating the code with EXPLICIT WAIT statements only
		 */
		System.out.println("Attempt3: With EXPLICIT wait statements");
		
		//NO IMPLICIT (GLOBAL) WAIT. Therefore, nextLOC commented.
		//driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
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
		/* WebDriverWait expWait = new WebDriverWait(driver, 3);
		 * Although the above expWait instantiation works for the immediate following wait step, you'll see an exception at 
		 * next explicit wait if max wait time is limited to 3 seconds:
		 * "Exception in thread "main" org.openqa.selenium.TimeoutException: Expected condition failed: waiting for visibility
		 * of element located by By.xpath: //div[@id='resultsContainer']/section/article[1]/div[2]/div/a (tried for 3 second(s) 
		 * with 500 MILLISECONDS interval)"
		 * Therefore increasing the wait time to 6 seconds. The other option is to keep the 2 waits separate, in the sense:
		 * Leave this as-is and freshly instantiate WebDriverWait with increased duration (6s) exclusively for the next explicit wait 
		 * Use the resultant new reference at the "driver.until.." step following it.
		 */
		WebDriverWait expWait = new WebDriverWait(driver, 6);
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
		
		/*You don't need to instantiate WebDriverWait once again. Simply use the handle you created once (earlier) and reuse it
		 */
		WebElement elemAttmpt4 = expWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='resultsContainer']/section/article[1]/div[2]/div/a")));
		System.out.println(elemAttmpt4.getTagName());
		System.out.println(elemAttmpt4.getAttribute("href"));
		elemAttmpt4.click();

	}

}
