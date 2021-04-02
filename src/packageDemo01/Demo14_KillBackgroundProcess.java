package packageDemo01;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.WebDriver;

public class Demo14_KillBackgroundProcess {

	/*DEMO:
	 * Kill background process
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Ensure the application you intend to kill is kept open until you run the script
		WindowsUtils.killByName("excel.exe");
	}

}
