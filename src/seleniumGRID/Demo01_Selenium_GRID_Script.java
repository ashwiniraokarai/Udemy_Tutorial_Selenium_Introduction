package seleniumGRID;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;

public class Demo01_Selenium_GRID_Script {

	//Read the GRID Word Doc for reference
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*PREREQUISITES:
		 * ENSURE your machine is REGISTERED AS HUB and VERIFIED (refer to Word Document)
		 * Another machine has all the prerequisites set up and has been REGISTERED as NODE
		 *  of this HUB and VERIFIED (refer to Word Document)
		 * STANDALONE SERVER .JAR FILE IS IMPORTED ON THE PROJECT BEFORE YOU BEGIN SCRIPTING
		 */
		
		/*REQUEST capabilities on the GRID for A NODE (ALL REGISTERED NODES will be LOOKED UP for MATCHING CONFIGURATION, depending on
		how they were REGISTERED)*/
		DesiredCapabilities desCape = new DesiredCapabilities();
		desCape.setBrowserName("chrome");
		desCape.setPlatform(Platform.LINUX);
	
		/*Invoke the BROWSER DRIVER on the REMOTE NODE
		 * Notice that we won't do a System.setProperty("webdriver.chrome.driver"."");
		 * This is taken care of at the time of registration of the node in the CLI via
		 *  -Dwebdriver.driver.chrome="<path to driver.exe>"
		 */

		WebDriver rmotDriver=null;
		try {
			rmotDriver = new RemoteWebDriver(new URL("http://192.168.56.50:4444/wd/hub"),desCape);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rmotDriver.get("http://www.google.com");

	}

}
