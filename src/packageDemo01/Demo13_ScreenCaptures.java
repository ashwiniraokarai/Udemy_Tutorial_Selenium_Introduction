package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class Demo13_ScreenCaptures {

	/*DEMO:
	 * Screen captures
	 * Kill background process
	 * Read the associated word document to refresh your clarity around the type-casting concept
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
		
		/*Results in "unknown command" exception
		 * File fileObject = driver.switchTo().activeElement().getScreenshotAs(OutputType.FILE);
		 */
		
		java.io.File fileObject = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		/*The space between (TakesScreenShot) and driver is NECESSARY for eclipse to recognize the INTERFACE
		 * The general value returned by getScreenshotAs is of type OutputType<X>
		 * The specific value returned by getScreenshotAs (when passed with argument of type java.io.File), 
		 * 												is of type java.io.File or simply File 
		 * If you simply mention "File" as the type of the returned object, then make sure to import the package java.io.File
		 */
		
		System.out.println(fileObject.getAbsolutePath());
		File target = new File("C:\\Users\\Ash\\Selenium JARs and Bindings\\ScreenCaptures\\Test.png");
		try {
			FileUtils.copyFile(fileObject,target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
