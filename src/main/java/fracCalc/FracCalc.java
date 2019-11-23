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

		Scanner console = new Scanner(System.in);
		String equation = console.nextLine();
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = console.nextLine();
		}
		console.close();
	}

	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input

		String firstOperand = "";
		String operator = "";
		String secondOperand = "";
		String secondWhole = "";
		String seocndNumerator = "";
		String secondDenominator = "";
		int split = input.indexOf(" ");

		firstOperand = input.substring(0, split);
		operator = input.substring(split + 1, split + 2);
		secondOperand = input.substring(split + 3);
		
		String output = parseOperand(secondOperand);
		
		return output;
	}
	
	// TODO: Fill in the space below with any helper methods that you think you will
	
	public static String parseOperand(String firstOperand) {

		int firstWhole;
		int firstNumerator;
		int firstDenominator;
		int firstSplit1;
		int firstSplit2;
		
		firstSplit1 = firstOperand.indexOf("_");
		firstSplit2 = firstOperand.indexOf("/");

		if (firstSplit1 != -1) {
			firstWhole = Integer.parseInt(firstOperand.substring(0, firstSplit1));
			firstNumerator = Integer.parseInt(firstOperand.substring(firstSplit1 + 1, firstSplit2));
			firstDenominator = Integer.parseInt(firstOperand.substring(firstSplit2 + 1));
		} else if (firstSplit2 != -1) {
			firstWhole = 0;
			firstNumerator = Integer.parseInt(firstOperand.substring(0, firstSplit2));
			firstDenominator = Integer.parseInt(firstOperand.substring(firstSplit2 + 1));
		} else {
			firstWhole = Integer.parseInt(firstOperand);
			firstNumerator = 0;
			firstDenominator = 1;
		}
		
		
		String output = "whole:" + Integer.toString(firstWhole) + " numerator:" + Integer.toString(firstNumerator) + " denominator:" + Integer.toString(firstDenominator);
		return output;
	}

}
