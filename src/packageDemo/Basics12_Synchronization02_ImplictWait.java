package packageDemo;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basics12_Synchronization02_ImplictWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	/*
	 * ============================================================= 
	 * Repeating the code with IMPLICIT wait statement incorporated
	 */
	System.out.println("Attempt2: With IMPLICIT wait statement only");
	//IMPLICIT WAIT (GLOBAL)
	 /*Page loading was causing sporadic error of inputing a different search keyword altogether!
	 * Therefore, doubling the implicit time duration (from 5s to 10s)
	 */
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("https://alaskatrips.poweredbygps.com/g/pt/vacationpackages?MDPCID=ALASKA-US.TPS.BRAND.VACATIONPACKAGES.PACKAGE");
	driver.findElement(By.xpath("//a[@data-lob='hotelOnly']")).click();

	/*
	 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * Commenting the above Thread.Sleep code. Implicit wait is expected to take
	 * care of all sync troubles globally (script level)
	 */
	
	driver.findElement(By.id("H-destination")).click();
	driver.findElement(By.id("H-destination")).sendKeys("nyc");
	driver.findElement(By.id("H-destination")).sendKeys(Keys.TAB);
	driver.findElement(By.id("H-fromDate")).sendKeys(Keys.ENTER);
	driver.findElement(By.id("enableDateless")).click();
	// Hitting enter now allows you to navigate to the next page. The point where
	// there is a long load time that we are trying to get past
	driver.findElement(By.id("enableDateless")).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("//div[@id='resultsContainer']/section/article[1]/div[2]/div/a")).click();

	/* No exception this time! Success! Let's try explicit wait */

	}

}
