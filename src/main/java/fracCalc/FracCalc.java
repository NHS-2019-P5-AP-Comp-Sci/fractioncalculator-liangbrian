/**
 * @author Mr. Rasmussen
 */

// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		
		// instantiate scanner
		Scanner console = new Scanner(System.in);
		// take next line of input
		String equation = console.nextLine();
		// print output of method with the equation parameter
		System.out.println(produceAnswer(equation));
		// close scanner
		console.close();
	}

	
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		
		//instantiate operand variable
		String secondOperand = "";
		//cycle through characters in equation
		for (int i = 0; i < input.length(); i++) {
			//when the character in the equation is the final space, set the second operand variable to the substring following the final space
			if (input.charAt(i) == ' ') {				
				secondOperand = input.substring(i + 1);				
			}
		}
		// method returns the second operand as a string
		return secondOperand;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
