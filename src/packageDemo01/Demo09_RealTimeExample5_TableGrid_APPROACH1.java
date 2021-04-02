package packageDemo01;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Demo09_RealTimeExample5_TableGrid_APPROACH1 {
	
	/*APPROACH 1: Using XPATH based ancestor-child relationship  (two other approaches in next exercise)
	 *------PIECE1: Loop through, extract the runs and keep adding the runs (exclude the first extract "R")
	 *------PIECE2: Extract the Extras and add it to runs
	 *------PIECE3: Extract the total (but do NOT add to runs!) and compare to runs total
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		driver.get("http://www.cricbuzz.com/live-cricket-scorecard/17688/aus-vs-eng-1st-test-the-ashes-2017-18");
		
		/*Temporary check point*/
		WebElement superElement1 = driver.findElement(By.xpath("//div[@class='ng-scope'][1]"));
		System.out.println(superElement1.getAttribute("id")); 
		//superElement1 is not used as-is (as a variable) anywhere in the code. It is embedded in superElement2's description
		
		/*Temporary check point
		*List<WebElement> superElement2 = driver.findElements(By.xpath("//div[@class='ng-scope'][1]//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]"));
		*System.out.println(superElement2.size());
		*Returns 1. Confirmed only one element is being recognized as expected.
		*/
		
		/*
		*WebElement superElement2 = driver.findElement(By.xpath("//div[@class='ng-scope'][1]//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]"));
		*System.out.println(superElement2.findElements(By.xpath("//div[@class='cb-col cb-col-8 text-right text-bold']")).size());
		Retuns 60! This is unexpected. Expected value is 12. Not sure why. NEED TO FIGURE THIS ONE OUT.
		
		Figured out from attached documentation :)
		 	findElements java.util.List<WebElement> findElements(By by)

			Find all elements within the current context using the given mechanism. When using xpath be aware that webdriver follows standard conventions: 
			a search prefixed with "//" will search the entire document, not just the children of this current node. 
			Use ".//" to limit your search to the children of this WebElement.
		*/
		
		WebElement superElement2 = driver.findElement(By.xpath("//div[@class='ng-scope'][1]//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]"));
		
		/* Temporary check point
		 * WebElement runElement = superElement2.findElement(By.xpath("//div[@class='cb-col cb-col-8 text-right text-bold']"));
		 * System.out.println(runElement.getText)); 
		*/ 
		
		List<WebElement> runElements = superElement2.findElements(By.xpath(".//div[@class='cb-col cb-col-8 text-right text-bold']"));
		//The above statements equate to: driver.findElements(By.xpath("//div[@class='ng-scope'][1]//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]//div[@class='cb-col cb-col-8 text-right text-bold']"))
		
		/*Temporary check point
		System.out.println("Count of elements representing runs: " +runElements.size());
		*/
		
		//------PIECE1: Loop through, extract the runs and keep adding the runs (exclude the first extract "R")	
		int intRuns = 0;
		ListIterator<WebElement> singlRunElement = runElements.listIterator();
		while(singlRunElement.hasNext()) {
			String runs = singlRunElement.next().getText();
			System.out.println("Runs scored: "+runs); //Temporary check point
			if(runs.matches("[0-9]+")) { 			//Ensure that you exclude non-numbers such as "R" in your calculation
				intRuns += Integer.parseInt(runs);
				System.out.println("Runs subtotal: "+intRuns); //Temporary check point
			}else {
				System.out.println("No numeric values were found that could qualify for addition to total runs"); //Temporary check point
			}
				
		}
			
		System.out.println("Total runs NOT including extras: "+intRuns);
		
		
		//------PIECE2: Extract the Extras and add it to runs
		String extras = superElement2.findElement(By.xpath(".//div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']")).getText();
		//This line above is the equivalent of "//div[@class='ng-scope'][1]//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]//div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']"
		System.out.println("Extras: "+extras); //Temporary check point
		
		intRuns += Integer.parseInt(extras);
		System.out.println("Total runs including extras: "+intRuns);//Temporary check point
		
		//------PIECE3:Extract the total (but do NOT add to runs!) and compare to runs total
		String total = superElement2.findElement(By.xpath(".//div[@class='cb-col cb-col-8 text-bold text-black text-right']")).getText();
		//The line above is equivalent of "//div[@class='ng-scope'][1]//div[@class='cb-col cb-col-100 cb-ltst-wgt-hdr'][1]//div[@class='cb-col cb-col-8 text-bold text-black text-right']"
		
		System.out.println("Total as retrieved on screen: "+total);//Temporary check point
		
		if(Integer.parseInt(total) == intRuns) {
			System.out.println("Passed. Expected and actual totals tally.");
			
		}else {
			System.out.println("Expected and actual totals DO NOT tally.");
		}
		
	}

}
