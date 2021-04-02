package packageDemo;

public class Basics01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Your SELENIUM code here
		System.out.println("Hello World");
		/*This class will be the hear of the test. Other classes will be called here:
		 * Basics02
		 */
		
		System.out.println("Launched home page");
		System.out.println("Calling validateHeader method : member of class Basics02");
		
		Basics02 objBasics21 = new Basics02();
		objBasics21.validateHeader();
		objBasics21.validateFooter();
		//To print the return value
		System.out.println(objBasics21.validateFooter());
		//Or as a two-step process
		String a = objBasics21.validateFooter();
		System.out.println("The returned value of validateFooter method is: " +a);
	}

}
