package packageDemo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Basics09_ElementVerification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//DEMO1: Testing the visibility/invisibility of an element (open mode/ hidden mode)
		driver.get("https://www.makemytrip.com/");
		
		System.out.println(driver.findElement(By.xpath("//input[@id='hp-widget__return']")).isEnabled());
		
		//Testing isDisplayed() when the element "Return" is indeed visible
		System.out.println("Is displayed default?: "+driver.findElement(By.xpath("//input[@id='hp-widget__return']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='hp-widget__return']")).isDisplayed());
		
		/*Trying to get the element "Return" to become invisible by clicking on "Multicity" option
		*Thus, enabling us to test the outcome of isDisplayed() in a negative scenario
		*/
		driver.findElement(By.xpath("//label[@for='switch__input_3']")).click();
		System.out.println("Is still displayed?: "+driver.findElement(By.xpath("//input[@id='hp-widget__return']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='hp-widget__return']")).isDisplayed());
		/*Hypothetically, if you wanted execution to stop here (upon the "return" field no more being visible under the
		 * "Multicity" option) you'd use Assert.assertTrue(driver.findElement(By.xpath("//input[@id='hp-widget__return']")).isDisplayed());
		 * instead of the above line of code. 
		 */
		
		
		/*DEMO2: Testing the presence/absence of an element (open/ hidden)
		*Subtly messing with the xpath expression of "Multicity" element on purpose in order to "absent" the element and 
		*then validate its absence by determining the size() and confirming the returned count is 0 (zero)
		*Remember? size() method is NOT available on web element object returned by findElement. It is only available
		*on web element objects returned by findElements.
		*/
		int count = driver.findElements(By.xpath("//label[@for='switch__input_3']")).size();
		if(count==0) {
			System.out.println("Returned count is: "+count+". Therefore, the element is not present on the web page.");
		}else {
			System.out.println("Returned count is: "+count+".Therefore, the element is present on the web page.");
		}
		
		/*DEMO3: Testing if an element is enabled/ disabled
		*Can't find a disabled element at the moment
		*Therefore, verifying only positive scenario of isEnabled() method
		*/
		
		System.out.println("Is the element enabled?: " +driver.findElement(By.xpath("//label[@for='switch__input_3']")).isEnabled());
		
	}//end main

}//end class Basics09_ElementVerification
