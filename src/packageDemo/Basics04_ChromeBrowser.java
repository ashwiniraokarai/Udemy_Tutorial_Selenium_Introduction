package packageDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basics04_ChromeBrowser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Needed to include the LOC on System.setProperty due to: “Exception in thread
		 * "main" java.lang.IllegalStateException: The path to the driver executable
		 * must be set by the webdriver.chrome.driver system property; for more
		 * information, see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver.
		 * The latest version can be download from
		 * http://chromedriver.storage.googleapis.com/index.html”
		 * This class also contains DEMONSTRATIONS OF VARIOUS ELEMENT LOCATOR TECHNIQUES
		 */

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		//Demo 1: Enter username, password and click "Forgot account?"
		driver.get("http://www.facebook.com");

		// Element Locator Technique - ID
		WebElement userName = driver.findElement(By.id("email"));

		// One way to confirm whether the input box (similar to QTP text box) is
		// technically recognized one
		System.out.println(userName.getAttribute("name") + ", " + userName.getTagName());

		userName.sendKeys("don't know");

		/*
		 * Similarly, on the password field, find element by name instead of if but in a
		 * one-step code (without needing to store the "input" WebElement in an Object)
		 */
		// Element Locator Technique - Name

		driver.findElement(By.name("pass")).sendKeys("again, don't know");

		// Element Locator Technique - LinkText
		WebElement forgotAccount = driver.findElement(By.linkText("Forgot account?"));

		System.out.println(forgotAccount.toString()); // readable name of the object
		System.out.println(forgotAccount.isEnabled());
		forgotAccount.click();
		driver.navigate().back();
		driver.navigate().refresh();

		/*Demo 2: Enter username, password and click "Forgot account?"
		* Element Locator Technique - CSS (CSS Selector)
		* You can get the CSS expression either from the right click->"Copy Selector" option (This
		* may or may not have the correct value in complex cases. Not a consistent option.) 
		* or from the lower tool bar of the Console
		*/
		driver.findElement(By.cssSelector("#email")).sendKeys("achukbye@gmail.com");
		
		// Element Locator Technique - XPATH
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("achukbye@gmail.com");
		
		// Element Locator Technique - XPATH
		driver.findElement(By.xpath("//*[@id=\"login_form\"]/table/tbody/tr[3]/td[2]/div/a")).click();
		
		driver.navigate().back();
		driver.navigate().refresh();
		
		//Demo 3:Now try to actually login but with incorrect credentials and capture the error message
		driver.findElement(By.cssSelector("#email")).sendKeys("achukbye@gmail.com");
		
		// Element Locator Technique - XPATH
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("achukbye@gmail.com");
	
		driver.findElement(By.xpath("//input[@value='Log In']")).submit();
		
		String loginErrorText = driver.findElement(By.xpath("//*[@id=\"globalContainer\"]/div[3]/div/div/div")).getText();
		
		System.out.println(loginErrorText);
		/*It's SUPER IMPORTANT TO VERIFY XPAPTH/ CSS SELECTOR PRIOR to using them in your code.
		*Make sure you SEE the WORD document on HOW to VERIFY your XPATH/CSS expressions
		*Also, you WANT to WRITE these expressions on your OWN, in the real world. Follow the same word document
		*/
		
		//Demo 4: Creating Standard and RegEx based XPATH and CSS SELECTOR expressions on your own:
		driver.navigate().to("http://www.rediff.com");
		
		//Element Locator Technique - RegEx in CSS SELECTOR (written manually from scratch)
		driver.findElement(By.cssSelector("a[title*='Sign in']")).click();;
		
		/*Element Locator Technique - Standard-Short CSS Selector - (written manually from scratch, applying the short form
		 * leveraging the availability of *ID attribute's value):
		 */
		driver.findElement(By.cssSelector("input#login1")).sendKeys("Username");
		
		//Element Locator Technique - Standard CSS Selector (written manually from scratch):
		driver.findElement(By.cssSelector("input[id='password']")).sendKeys("Password");
		
		//Element Locator Technique - RegEx in XPATH (written manually from scratch):
		driver.findElement(By.xpath("//input[contains(@name,'proc')]")).click();
		
		/*Demo 5:Creating XPATH of an element starting from Parent and cascading down to the child (element)
		 * Click the "Images" link on Google Home Page (this code works only on the defaut page i.e, 
		 * when NOT signed into any google account)
		 */
		
		driver.navigate().to("http://www.google.com");
		
		/*Element Locator Technique - XPATH Parent to Child traverse (ABSOLUTE PATH)
		 * Absolute path usage is not recommended because web pages are prone to change by developers
		 * as they mature.
		 * Relative paths are "relatively" stable in this case
		 */
		
		driver.findElement(By.xpath("//div[@id='gbw']/div/div/div/div[2]/a")).click();
		
		/*Demo 6:You could also traverse between siblings and find XPATH of one of the siblings based on the XPATH of 
		 * another that has been inspected/ provided, without having to re-inspect the sibling in question
		 */
		
		driver.navigate().to("http://www.qaclickacademy.com/interview.php");
		
		//Given/ Inspected XPATH:
		driver.findElement(By.xpath("//li[@id='tablist1-tab1']")).click();
		
		/*XPATH of subsequent SIBLING, CONSTRUCTED without INSPECTING but by APPENDING to ALREADY EXISTING XPATH instead:
		 * APPEND SYNTAX: 'following-sibling:tagName[sequence.no.]' 
		 * XPATH of first sibling is appended with keywords and expected arguments as per syntax to locate the 
		 * subsequent sibling here:
		 */
		
		driver.findElement(By.xpath("//li[@id='tablist1-tab1']/following-sibling::li[1]")).click();
		
		//You can continue to click other siblings in the same fashion:
		driver.findElement(By.xpath("//li[@id='tablist1-tab1']/following-sibling::li[2]")).click();
		driver.findElement(By.xpath("//li[@id='tablist1-tab1']/following-sibling::li[3]")).click();
		
		/*Demo 7: 
		 * Creating XPATH EXPRESSION to TRAVERSE BACK to PARENT/ ANCESTOR based on CHILD XPATH
		 * (Reverse path)
		 */
		//XPATH of an element using STANDARD SYNTAX:
		driver.findElement(By.xpath("//li[@id='tablist1-tab2']")).click();
		
		//XPATH of the PARENT using an EXTENSION to it's CHILD:
		System.out.println(driver.findElement(By.xpath("//li[@id='tablist1-tab2']/parent::ul")).getText());
		
		/*Demo 8: 
		 * Creating XPATH EXPRESSION using text() method
		 */
		driver.findElement(By.xpath("//*[text()=' Soap UI ']")).click();
	}

}
