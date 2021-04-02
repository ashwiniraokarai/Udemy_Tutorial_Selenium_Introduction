package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Demo05_EndToEnd_Exercise1 {

	// DEMO: Limiting the display of web-links to specific sections on the page:
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://www.ebay.com/");

		System.out.println("Number of links on ebay.com: " + driver.findElements(By.tagName("a")).size() + ".");

		WebElement header = driver.findElement(By.tagName("header"));
		System.out
				.println("Number of links on the header section: " + header.findElements(By.tagName("a")).size() + ".");

		WebElement footer = driver.findElement(By.tagName("footer"));
		System.out
				.println("Number of links on the footer section: " + footer.findElements(By.tagName("a")).size() + ".");

		// Find the count of links on the second column in the footer section ("Sell"
		// and "Tools & apps")
		WebElement footerColSecnd = driver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td[2]/ul"));
		System.out.println("Footer's second column contains " + footerColSecnd.findElements(By.tagName("a")).size()
				+ " links in total.");
		/*
		 * You could save the returned int value from .size() into a variable of in type
		 * if you are going to be using the classic indices based for-loop
		 */

		// Retrieve the text of the links on the second column in the footer section
		// ("Sell" and "Tools & apps")
		List<WebElement> linksFooterColSecnd = footerColSecnd.findElements(By.tagName("a"));
		/*
		 * Breaking the habit of jumping into loop before checking a sample the
		 * operation itself. Commented after verifying:
		 * System.out.println(linksFooterColSecnd.get(1).getAttribute("text"));
		 */
		ListIterator<WebElement> itrtr1 = linksFooterColSecnd.listIterator();

		// Demonstrating the same retrieval of links using different type of commonly
		// seen loops

		// This integer variable will be used to print an auto-incremented serial number
		// alongside each link name
		int slNum = 0;

		System.out.println("-------------------------------------\nUsing while loop-\n");

		whileLoop:
		// Using while loop (most intuitive)
		while (itrtr1.hasNext()) {
			slNum++;
			System.out.println(slNum + ": " + itrtr1.next().getText());
		} // end whileLoop

		// Resetting serial number counter back to 0
		slNum = 0;

		/*
		 * Using for-loop when your data structure implements an iterable and traverse
		 * conditionally "for-iterate over a collection"
		 */
		System.out.println("-------------------------------------\nUsing for-loop type 1-\n");

		forLoop1: for (ListIterator<WebElement> itrtr2 = linksFooterColSecnd.listIterator(); itrtr2.hasNext();) {
			slNum++;
			// You could have also chosen to reuse the iterable that you already generated
			// before the while loop (itrtr1)
			WebElement singleLinkFooterColSecnd1 = itrtr2.next();
			// You can additionally type-cast the returned webelement explicitly(as a good
			// practice) like so: WebElement singlelinkFooterColSecnd =
			// (WebElement)itrtr2.next();

			System.out.println(slNum + ": " + singleLinkFooterColSecnd1.getText());
			/*
			 * You can also combine the above 2 steps into single step if you are doing
			 * nothing other than a getText() on the webelement - as you did in while loop,
			 * like so: System.out.println("Using for loop: "+itrtr2.next().getText());
			 */
		} // end forLoop1

		// Resetting serial number counter back to 0
		slNum = 0;

		/*
		 * Using for-loop when your data structure implements an iterable and traverse
		 * unconditionally to all of them This is the simplest form to iterate over a
		 * collection "foreach-iterate over an array or iterable" a.k.a the for-each
		 * loop
		 */

		System.out.println("-------------------------------------\nUsing for-loop type 2-\n");

		forLoop2: for (WebElement singleLinkFooterColSecnd2 : linksFooterColSecnd) {
			slNum++;
			System.out.println(slNum + ": " + singleLinkFooterColSecnd2.getText());
		} // end forLoop2

		// Using the classic for-loop with indices (array style)

		// Resetting serial number counter back to 0
		slNum = 0;

		/*
		 * Using the classic for-loop when your data structure does not implement an
		 * iterable or you do not want to use the implemented iterable or if looping an
		 * array
		 */

		System.out.println("-------------------------------------\nUsing for-loop type 3-\n");

		forLoop3: for (int i = 0; i < footerColSecnd.findElements(By.tagName("a")).size(); i++) {
			// if this were array you would do i<args[].length instead of a .size() for the upper limit
			
			slNum++;
			System.out.print(slNum + ": "); //Notice the use of print instead of println to have both the sysouts displayed on a single line
			System.out.println(footerColSecnd.findElements(By.tagName("a")).get(i).getText());
		} // end forLoop3

	}// end main

}// end class
