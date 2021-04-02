package packageDemo01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Demo09_RealTimeExample5_TableGrid_APPROACH2 {

	/*APPROACH 2: Using CSS Selector based ancestor-child relationship ( :nth-child())
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		driver.get("http://www.cricbuzz.com/live-cricket-scorecard/17688/aus-vs-eng-1st-test-the-ashes-2017-18");
		
		/*IMPORTANT LESSON:
		 * In cases where several elements in the DOM sharing common descriptions/ attributes (such as in this scenario),
		 * unlike XPATH, CSS Selector does NOT locate all elements with identical descriptions/ attributed when tested 
		 * via command line ($() format). Instead, what is returned is the FIRST element that has the common description/ attribute.
		 * For example, $("div[class='cb-col cb-col-100 cb-scrd-itms']") returns only 1 result, eventhough there are at least
		 * 44 elements with exactly identical description. How do we know that number? That's because it's XPATH counter
		 * part returns 44 elements on the command prompt when tested via 
		 * $x("//div[@class='cb-col cb-col-100 cb-scrd-itms']")
		 * Therefore, it is IMPERATIVE that such commands be verified via $$() CSS Selector format instead
		 */
		
		/*List<WebElement> parentOrTables = driver.findElements(By.cssSelector("[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
		 *System.out.println(parentOrTables.size()); 
		 * Temporary Check Point. 
		 * Results in 8, which means there are 8 elements with the identical description. Expected. 
		 * This being CSS Selector, unlike XPATH we don't have to specify the exact index because 
		 * we are interested in the first element only. So, simply go ahead use the same command but pass it to 
		 * findElement() instead of findElements()
		 */

		WebElement parentOrTable = driver.findElement(By.cssSelector("[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
		/*Temporary Check Point
		List<WebElement> childOrRows = parentOrTable.findElements(By.cssSelector("[class='cb-col cb-col-100 cb-scrd-itms']"));
		System.out.println(childOrRows.size()); 
		Returns 13. So you know it fetched the children from a restricted space (parent)
		and not from all the 8 identical (potential parent) elements. Else you would have seen a much bigger number,
		perhaps 13*8-ish times
		*/
		
		/*Temporary Check Point
		 * Returns data under every column of each of the 12 rows (besides the runs data). Expected.
		for(int i=0;i<childOrRows.size();i++) { // while loop welcome as an alternate
			System.out.println(childOrRows.get(i).getText()); 
		}
		*/
		
		/*You want data only from a certain "column" (the runs listed under "R"). Extend your locator to LIMIT to the
		column. One way to achieve this is using parent-child relationship.
		CSS Selector follows a differnt format than that of XPATH:
		<space>:nth-child<<positionOfChild>> 
		*/
		List<WebElement> runsColumnChildRows = parentOrTable.findElements(By.cssSelector("[class='cb-col cb-col-100 cb-scrd-itms'] :nth-child(3)"));
		
		for(int i=0;i<runsColumnChildRows.size();i++) {
			System.out.println("Runs: "+runsColumnChildRows.get(i).getText());
			/*Returns runs from all rows in the table
			(excluding "Extras" and "Total" - need to extract these separately. Expected.)
			Also returns two unwanted values (unexpected) at the end
			Reduce the count/upper limit/list iteration size by 2 to eliminate the latter junk.
			*/
		}
		
		int intRuns = 0;
		System.out.println("\n------------------------");
		for(int j=0;j<runsColumnChildRows.size()-2;j++) {
			String strRuns = runsColumnChildRows.get(j).getText();
			System.out.println("\nRuns: "+runsColumnChildRows.get(j).getText());
			/*Returns runs from all rows in the table
			(excluding "Extras" and "Total" - need to extract these separately. Expected.)
			No more unwanted values 
			*/
			intRuns += Integer.parseInt(strRuns);
			System.out.println("Subtotal Runs: "+intRuns);
		}
		
		System.out.println("Total runs excluding extras: "+intRuns);
		
		/*Extract the Extras and Totals. They are on identical "type of rows"(class) as that of the other runs but under
		 * different columns (children). This is not apparent on the screen but you know that because of the preceeding for loops and also by following up
		on the web console
		*/
		List<WebElement> extrasAndTotal = parentOrTable.findElements(By.cssSelector("[class='cb-col cb-col-100 cb-scrd-itms'] :nth-child(2)"));
				
		String extras = extrasAndTotal.get(extrasAndTotal.size()-2).getText();
		System.out.println("Extras: "+extras);
		intRuns += Integer.parseInt(extras);
		System.out.println("Total Runs:"+intRuns);
		
		String total = extrasAndTotal.get(extrasAndTotal.size()-1).getText();
		System.out.println("Total as seen on screen:"+total);
		
		//Compare the total runs with total as seen on screen
		if(Integer.parseInt(total) == intRuns) {
			System.out.println("Pass. Expected and actual totals tally ");
		}else {
			System.out.println("Fail. Expected and actual totals DO NOT tally ");
		}
		
		}//end main
}//end class
