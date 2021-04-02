package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

public class Demo02_Actions_Intro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
		
		Actions action = new Actions(driver);
		
		/*We know from last exercise that many methods encapsulate in action object requires the WebElement as an argument
		 * Also you gotta do like 3 method calls just to perform an action using the action object 
		 * Storing the WebElement in a variable will improve readability:
		 */
		
		WebElement moveMouseOver = driver.findElement(By.id("nav-link-accountList"));
		
		action.moveToElement(moveMouseOver).build().perform();
		
		/*Next, GOAL1: To MOUSE OVER and enter HELLO (in upper case) into the search box, as ONE action
		 * This counts as a composite action (made of several actions):
		 * -Finding the element
		 * -Mouse over (although there is no visible text available to show when you mouse over the search box)
		 * -Click the search box (to get the cursor to focus)
		 * -Press down the Shift Key (to enable entry in upper case)
		 * -Enter the search keywords
		 */
		
		WebElement enterSearch = driver.findElement(By.id("twotabsearchtextbox"));
		action.moveToElement(enterSearch).click().keyDown(Keys.SHIFT).sendKeys("hello").build().perform();
		
		/*GOAL2: Do all of GOAL1 followed by selection of the entered search text
		 * - You don't need a separate webelement for this. It's just another action on the webelement you have
		 * been acting upon and is already in focus
		 * - Also, selection can be achieved easily by double clicking on the search text (element in focus)
		 */
		//Using keyDown(Keys.SHIFT) consecutively is causing hello to be entered in lowercase!
		action.moveToElement(enterSearch).click().sendKeys("hello").doubleClick().build().perform();
		
		/*GOAL 3: GOAL2 + Right-click to open the context menu
		 */
		
		//action.moveToElement(enterSearch).click().sendKeys("hello").doubleClick().contextClick().build().perform();
		
		/*Goal 4: GOAL1+Selecting the first prompted keyword
		 */
		//Preempting the search box to prevent conflicts
		action.moveToElement(enterSearch).click().doubleClick().sendKeys(Keys.DELETE).build().perform();
		action.moveToElement(enterSearch).click().sendKeys("hello").build().perform();
		/*There is a slight delay between when the keyword is entered and the prompts based on the keyword. Implicit
		wait can take care of only page loads. Therefore, combating this using Thread.sleep for now. Needs to be replaced
		with explicit wait
		*/
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Goal 5: To mouse over Departments->Fire Tablets->Fire HD 8 and finally click on Fire HD 8
		 * A long one, comin' up!
		 * Note: Clicking on Fire HD 8 opens an ENTIRELY NEW WINDOW which (or a NEW TAB) in web automation space is called a
		 * "CHILD WINDOW". The next tutorial talk about how to switch between the PRIMARY and CHILD WINDOW
		 */
		
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"nav-link-shopall\"]/span[2]"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"nav-flyout-shopAll\"]/div[2]/span[5]/span"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"nav-flyout-shopAll\"]/div[3]/div[5]/div[1]/div/a[2]/span[1]"))).click().build().perform();
		
	}

}
