package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
public class Demo12_MaximizeBrowser_And_DeleteCookies {

	/*DEMO:
	 * Maximize window
	 * Delete cookies
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//Maximize browser
		driver.manage().window().maximize();
		//Delete cookies
		driver.manage().deleteAllCookies();
		//driver.manage().deleteCookieNamed("session-id");
		driver.get("http://www.amazon.com");
		
		
		

	}

}
