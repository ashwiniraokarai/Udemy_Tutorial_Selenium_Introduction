package packageDemo01;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

public class Demo04_Frames_DragAndDrop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ash\\Selenium JARs and Bindings\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://jqueryui.com/droppable/");
		System.out.println(driver.getTitle());
		
		//DEMO1: Switch using one of the options of .frame() method that takes WEBELEMENT as it's argument
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='content']/iframe")));
		System.out.println(driver.getTitle());
		
		Actions action = new Actions(driver);
		
		WebElement src = driver.findElement(By.id("draggable"));
		WebElement trgt = driver.findElement(By.id("droppable"));
		
		action.dragAndDrop(src, trgt).build().perform();
		
		/*DEMO2a: Switch using one of the options of .frame() method that takes INDEX as it's argument
		 *Frame indices BEGIN with zero i.e, Index of Frame 1 = 0
		 *Using this option is less desirable because frame indices are prone to change by web developers
		 */
		//Remember to switch back to parent before trying the .frame(index) option!
		driver.switchTo().parentFrame(); //alternately, use driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		
		/*DEMO2a: 
		 * TIP: It's always BEST to DETERMINE the NUMBER OF FRAMES on your PAGE BEFORE you use the indices
		 * option of .frame() method. That will guide you in DETERMINING the INDEX of the FRAME that you want
		 * to operate on. This is applicable (when deciding to use) to any method that takes indices as argument;
		 * not just to .frame() method.
		 */
		
		//Remember to switch back to parent before trying to determine the number of frames!
		driver.switchTo().parentFrame(); //alternately, use driver.switchTo().defaultContent();
		
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println(frames.size());
		ListIterator<WebElement> itrtr = frames.listIterator();
		
		while(itrtr.hasNext()) {
			
			System.out.println("Inside while loop");
			System.out.println(itrtr.nextIndex());
			itrtr.next();
		
		}
		

	}

}
