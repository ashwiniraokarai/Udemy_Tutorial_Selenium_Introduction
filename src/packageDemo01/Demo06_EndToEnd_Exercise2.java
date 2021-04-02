package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Demo06_EndToEnd_Exercise2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Hypothetical test case description/ conditions
		1. Get the title of the page
		2. Assuming that the "Site map" link will always continue be listed under the second column of the footer section but can
		dynamically move around within that space. With this requirement, verify that the link exists before you click on it.
		3. Verify the new page has loaded (now having loaded the site map page) by verifying whether it's title is as expected
		4. Get the count of all the links of this page
		*/
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://www.ebay.com/");
		
		System.out.println("Default page title: "+driver.getTitle());
		
		int flag = 0; //Flag to track search hit
		WebElement footerColSecnd = driver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td[2]/ul"));
		forLoop:for (int i = 0; i < footerColSecnd.findElements(By.tagName("a")).size(); i++) {
			
			if(footerColSecnd.findElements(By.tagName("a")).get(i).getText().contentEquals("Site map")) {
				System.out.println("Your search for link ends here.");
				flag = 1;
				footerColSecnd.findElements(By.tagName("a")).get(i).click();
				System.out.println("Page title after clicking on the \"Site map\" link: "+driver.getTitle());
				Assert.assertEquals("Page title verification:", "Sitemap | eBay", driver.getTitle());
				/*Message prints only on failure (incorporated intp the exception), not on success! What a weirdo. Perhaps, because
				there is no inherent reporting mechanism 
				*/
				
				System.out.println("Count of all the pages on the site map page: "+driver.findElements(By.tagName("a")).size());
				break;
			}
		
		}//end forLoop
		
		if(flag==0) {
			System.out.println("Hmm, it looks like the link you are looking for is not found under this section.");
		}
		
	}//end main

}//end class
