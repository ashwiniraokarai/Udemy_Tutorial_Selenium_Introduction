package packageDemo;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Basics06_ExploringElements1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.spicejet.com");

		/*DEMO 1: STATIC DROP DOWNS
		 * Exploring static drop down box "Adult"
		 */
		
		/*
		 * Static drop downs are basic drop downs. They do not have dual behavior as
		 * search-edit box/ drop down boxes. Athough you can inspect the static drop
		 * down, upon clicking open a static drop box, you cannot inspect the individual
		 * items inside it.
		 */

		/*
		 * Getting a handle on the drop down. You cannot operate complex elements such
		 * as drop downs with methods such as "click"/"sendKeys" meant for simpler
		 * elements like input boxes and links. Instantiating the SELECT class with your
		 * drop down elements as an argument to its constructor gives you access to
		 * methods to manipulate your drop down box.
		 */
		WebElement dropDown = driver.findElement(By.id("ctl00_mainContent_ddl_Adult"));

		// Leveraging the SELECT class to access several methods that aid in drop down
		// operation
		Select selectObj = new Select(dropDown);
		selectObj.selectByValue("3");
		selectObj.selectByVisibleText("4");
		selectObj.selectByIndex(4); // Selects "5" on the DD. Looks like index values begin with 0.
		// Print the selected value
		System.out.println("Selection based on index: " + selectObj.getFirstSelectedOption().getText() + ".");

		// Desired value to select:
		String valueToSelect = "9";
		int result = 0;
		// will be reset in the if condition in the while loop later

		/*
		 * Voluntary exercise Trying to retrieve all options in the list, comparing the
		 * desired value to select with the current value and then selecting the value
		 * in the drop down
		 */
		List<WebElement> options = selectObj.getOptions();
		Iterator<WebElement> iterator = options.listIterator();
		// Begin looping..
		LOOP: // Label for this loop. Optionally used with break to make code readable. Very
				// handy with multiple loops.
		while (iterator.hasNext()) {
			String currentValue = iterator.next().getText();
			System.out.println("Currently looping through value: " + currentValue + ".");
			/*
			 * Combined one step process of the 2 lines of code above
			 * selectObj.selectByValue(iterator.next().getText());
			 */

			if (currentValue.equals(valueToSelect)) {
				/*
				 * Comparison cannot be done using "=".Results in compilation error. Comparison
				 * is done using "==". Works great for integersBut when it comes to String,
				 * compares the STRING OBJECTS. So, when you debug the two string objects, even
				 * though their values may match their "id" in java (do not confuse with
				 * Selenium element locator id!) will differ and therefore the comparison
				 * evaluates to FALSE, therefore never gets through the condition
				 */

				selectObj.selectByValue(valueToSelect);
				System.out.println(
						"Desired value was found in drop down and selected accordingly: " + valueToSelect + ".");
				result = 1; // Change the flag to 1 (your indicator for success)
				break LOOP; // Break out of the loop. Don't have to look further. And this way result flag
							// contains the latest value
				// and you can use this to perform the check before printing the failure
				// message, after having completed looping
			} // "else" is not needed coz all you need is to let your result flag stay as-is
				// i.e, 0, when the if condition has not met
		} // end while loop

		if (result == 0) {
			System.out
					.println("Desired value NOT found in the drop down. Sorry, cannot select: " + valueToSelect + ".");
		}

		/*DEMO 2: DYNAMIC DROP DOWNS
		 * Playing with 2 dynamic drop downs: "From" and "To"
		 */

		/*Dynamic drop down automation 
		 * The commented code looks perfectly fine but doesn't work on dynamic drop downs. The drop down itself gets inspected as an INPUT tag element. There is a
		 * SLECT tag class above it that looks (very intuitively) as the more suitable code but using it in the scripts gives an "element not visible error". On
		 * the other hand if you decide to stick to the inspected results (INPUT tag class), Selenium's SELCET class will certainly does not take a non-SELECT tag based
		 * element for instantiation as it's constructor argument!See relevant word document that has step-by-step experimentation details.
		 * WebElement dynDropDown =
		 * driver.findElement(By.id("ctl00_mainContent_ddl_originStation1"));
		 * dynDropDown.click(); 
		 * Select selectObj2 = new Select(dynDropDown);
		 * selectObj2.selectByValue("ATQ");
		 */
		
		/*Since you can't leverage Selenium's perfect looking SELECT class on dynamic drop downs, you decide to work with the inspected INPUT tag based element
		 * Inspection identifies the dynamic drop down as:
		 * 		<input id="ctl00_mainContent_ddl_originStation1_CTXT" name="ctl00_mainContent_ddl_originStation1_CTXT" selectedtext="" selectedvalue="" value="" class="select_CTXT" menuselection="false" autocomplete="off" style="width: 250px; height: 43px; border: 1px solid rgb(153, 153, 153);">
		 */
		
		//"Arrival" drop down on spicejet.com
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click(); 
		
		/*Since cannot leverage SELENIUM's SELECT class to select the values, you have to locate the individual value by inspecting it and clicking on it
		 * in the traditional way
		 * Option elements under the drop down do not have individual ID's or classname.LinkText can change. Value attribute would be a better option.
		 * Therefore, going for XPATH
		 */
		driver.findElement(By.xpath("//a[@value='GOP']")).click(); //Note that "value" attribute based XPATH can cause conflicts for options under the "destination" drop down
																	//since it's not bound by it's class or id for recognition. 
		/*Similar logic applied to the "destination" drop down
		 * Note that this drop down clicks on its own after the origin is selected
		 * It does not populate until an origin is seleced
		 * Also, the items populated depend on the origin (i.e, some options may not be available!)
		 */
		
		/*Don't need to do this.
		*driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		*/
		
		/*You expect this to work but Selenium gives you an "element not visible" error:
		 * driver.findElement(By.xpath("//a[@value='UDR']")).click();
		 * because Selenium is trying to click 'UDR' option under the origin drop down even though the currently open drop down is destination due to the
		 * shared code <div id="dropdownGroup1"> under Origin and Destination both
		 * Needs some tweaking to get this to work on the destination drop down by wrapping the xpath and explicitly specifying it as the second instance
		 */
		
		driver.findElement(By.xpath("(//a[@value='UDR'])[2]")).click();
		
		
		/*DEMO 4: CheckBoxes
		 * Playing with the checkbox "Indian Armed Forces"
		 */
		driver.findElement(By.id("ctl00_mainContent_chk_IndArm")).click(); //Check
		//driver.findElement(By.id("ctl00_mainContent_chk_IndArm")).click(); //Uncheck
		
		/*What you truly want is to be able to verify the prevailing state of the checkbox (checked or unchecked)
		 * and take action accordingly.
		 *As one of its basic methods, Selenium's "isSelected" method can be leveraged to accomplish this 
		 */
		boolean checkBoxState = driver.findElement(By.id("ctl00_mainContent_chk_IndArm")).isSelected();
		if (checkBoxState) {
			System.out.println("The check box state is:" +checkBoxState +". Nothing to do.");
		}else {
			System.out.println("The check box state is: "+checkBoxState+". About to make the selection now...");
			driver.findElement(By.id("ctl00_mainContent_chk_IndArm")).click();
			System.out.println("The new checkbox state is: " +driver.findElement(By.id("ctl00_mainContent_chk_IndArm")).isSelected()+".");
		}
		
		/*Note that the dynamic drop down that we explored earlier if left open (clicked open) without choosing any
		*value, it has potential to OVERALAP this and other checkboxes in the vicinity in which case Java will throw
		*a "element not clickable at point(x,y). Other element will receive this click" error. Make sure the element 
		*is visible.
		*/
		
		//Next demo on Radio Buttons. Continued in Basics07_ExploringElements2.java class
	}// end main

}// end class Basics06_ExploringElements1

		
		
