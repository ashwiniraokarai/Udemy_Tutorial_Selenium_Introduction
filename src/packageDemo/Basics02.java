package packageDemo;

public class Basics02 {

	/*
	 * Commenting (removing) MAIN methods as it is serves no purpose on this class.
	 * This class will NOT BE EXECUTED from this class file itself Method calls to
	 * this class will be made from another class - Basics01.java public static void
	 * main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	/*
	 * A reusable method to validate headers of multiple web pages of a hypothetical
	 * web application wherein the header remains the same across all those pages
	 */
	public void validateHeader() {
		//Insert code for validating header
		System.out.println("Hello from Header Validation!");
		
	}
	
	public String validateFooter() {
		System.out.println("Hello from Footer Validation!");
		return "Returning Pass";
		/*NOTICE: Return type of the method was changed from void to String after
		*return statement was included in the method
		*/
	}
}
