package packageDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basics07_ExploringElements2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("http://www.echoecho.com/htmlforms10.htm");
	
	//By default, "Butter" radio button is selected. You want to select another one.
	//"Value" seems to be the only unique attribute. Therefore, taking the XPATH route
	driver.findElement(By.xpath("//input[@value='Milk']")).click();
	
	//Verifying if the intended option was selected (just like check boxes)
	System.out.println(driver.findElement(By.xpath("//input[@value='Milk']")).isSelected());
	
	/*Radio buttons usually have one common attribute with their peers - called a radio button group. 
	*By calling the findElements method you can locate the group using
	*an XPATH expression containing the name of the group. 
	*This comes handy when the radio button/s lack attributes to uniquely identify them
	*The "get()" method taken index as an argument and can be used on the radio button group to operate
	*on it's radio buttons. Indices begin from 0. 
	*For example, if you had to select the 3rd radio button, the index would be 2
	*/
	
	driver.findElements(By.xpath("//input[@name='group1']")).get(2).click();
	
	/*However, you can't leverage the indices much when you do not know the how many radio buttons
	 * are there in the group 
	 * The size() function can then be called upon the radio group webelement identified earlier
	*to retrieve the size of the radio button group(number of radio buttons within the group).
	*The count can be used to loop through the radio buttons in the group via their indices by leveraging the 
	*get() method. Observing/ debugging through this loop helps identify the index of the specific radio button
	*we are concerned with
	*/
	int size = driver.findElements(By.xpath("//input[@name='group1']")).size();
	System.out.println(size);
	
	/*Trying to find the specified radio button in the radio button group (using index). Radio button is clicked
	 * if only a match is found
	 */
	String textToMatch = "Butter";
	int result = 0; //Will serve as a flag that will be reset to 1 (success) if the if-condition within the forLoop is satisfied
	forLoop: //loop label
	for(int i=0;i<size;i++) {
		String text = driver.findElements(By.xpath("//input[@name='group1']")).get(i).getAttribute("value");
		/*Note: driver.findElements(By.xpath("//input[@name='group1']")).get(i).getText() does not actually fetch visible text of the
		*radiobutton. It only returns a blank string; thus, not fulfilling our requirement.
		*Probably works only when an element's tag contains text within it such as in links, labels, input boxes, etc.:
		*E.g.:<span class="link">check boxes</span>
		*	  <div class="headline" align="center">RADIO BUTTON</div>
		*	  <a href="htmlforms11.htm">READ MORE &gt;&gt;</a>
		*/
		if(text.equals(textToMatch)) {
			System.out.println("Found a match for '"+textToMatch+"'. About to select the radio button...");
			driver.findElements(By.xpath("//input[@name='group1']")).get(i).click();	
			//Verifying the selection using isSelected() method just as we did with check boxes
			System.out.println("Status of radio button '"+driver.findElements(By.xpath("//input[@name='group1']")).get(i).getAttribute("value")+"' = "+driver.findElements(By.xpath("//input[@name='group1']")).get(i).isSelected());
			result = 1; ////Marking success on the flag
			break forLoop;
		}
		
	}//end forLoop
	
	if(result==0) { //result = 0 indicates failure
		System.out.println("Sorry, could not find a matching radio button for '"+textToMatch+"'.");
	}
	
	}//end main

}//end class Basics07_ExploringElements2
