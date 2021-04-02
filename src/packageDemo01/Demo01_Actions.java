package packageDemo01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Demo01_Actions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com/");
		
		/*Typically you'd follow this format for instantiation of the "Actions" class you need to PASS ARGUMENTS
		*but not when the constructor(s) EXPECT ARGUMENTS of the specified data type. Eclipse won't allow it.
		*Eclipse proposes 3 possible options and arguments. We choose that one that allows us to pass "WebDriver"
		*object as an argument (just the way did with SELECT class when dealing with radio buttons.
		*/
		Actions action = new Actions(driver);
		/*action.moveToElement(target) method looks suitable. It takes webelement as an argument
		Let's write the LOC to find the element: driver.findElement(By.id("nav-link-accountList"));
		Now you end up with the line below:
		*/ 
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))); 
		/*No errors when you run the above LOC but it only does a sort of a "partial mouse over" i.e, it goes there but what shows under
		the element does not cover the entire portion what you expect to see.(Just like it happens when you mouse over manually when the page is still loading).
		HOWEVER this is NO contribution of the method. the partial mouse over happens by default ith get()! 
		Unlike others, these methods expect you to additionally call the method "build" on the action object to generate a composite action object.
		Note that moveToElement does not return a WebElement, instead it returns a self-reference!
		*/
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build();
		/*Still the same result as before with moveToElement
		 Apparently, you need to call a "perform" method on the composite action object instance you just generated using
		 build(), in order see the complete performance, if you will! Phew! Who knew?
		*/
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		/*perform() does not return anything (void). It simply performs the operation (yeah finally)
		 * To summarize (and to commit to memory):
		 * You create an action object (passing your driver object during instantiation)
		 * Move to the webelement of choice and receive a self-reference
		 * Build a composite action object
		 * Finally, perform the action on the composite action
		 */

	}

}
