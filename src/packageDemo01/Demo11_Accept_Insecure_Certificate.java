package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Demo11_Accept_Insecure_Certificate {
	
	//TIP: Quickly glancing through the word guide will help refresh clarity around these concepts
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Invoke Desired Capabilities meant for Chrome Driver
		DesiredCapabilities desCap = DesiredCapabilities.chrome();
		//Add the generic capability of your choice desired on your WebDriver
		desCap.acceptInsecureCerts();
		
		//or try this
		/*
		dC.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		dC.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		*/
		
		//Instantiated ChromeOptions has convenient methods for setting ChromeDriver specific capabilities
		ChromeOptions ChrOpt = new ChromeOptions(); 
		
		/*In this case, you don't need to "set" ChromeDriver specific capabilities again. 
		 * Therefore, simply MERGE the OTHER generic WebDriver desired capabilities (you added via Desired Capabilities) 
		 * with ChromeOptions
		*/
		ChrOpt.merge(desCap);
		
		//Pass your ChromeOptions (that has now been merged with the capabilities you desire on WebDriver)
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(ChrOpt);
		driver.get("https://revoked.grc.com/");
	}

}
