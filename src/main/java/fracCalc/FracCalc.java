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

// import packages
package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		// initialize scanner
		Scanner console = new Scanner(System.in);
		// take user input as string
		String equation = console.nextLine();
		// program runs continuously, terminate when user input is quit
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = console.nextLine();
		}

		console.close();
	}

	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input

		// initialize variables to split equation into, variable for where to split, and
		// variable for final output
		String firstOperand = "";
		String operator = "";
		String secondOperand = "";
		int split = input.indexOf(" ");
		String output = "";

		// split equation into variables (can simplify)
		firstOperand = input.substring(0, split);
		operator = input.substring(split + 1, split + 2);
		secondOperand = input.substring(split + 3);

		// call methods to split operands into the whole number, numerator, and
		// denominator
		int d1 = findDenominator(firstOperand);
		int d2 = findDenominator(secondOperand);
		int n1 = convertNumerator(findWhole(firstOperand), findNumerator(firstOperand), d1);
		int n2 = convertNumerator(findWhole(secondOperand), findNumerator(secondOperand), d2);

		// call methods to solve the equation using the split operands
		if (operator.equals("+")) {
			output = addFrac(n1, d1, n2, d2);
		} else if (operator.equals("-")) {
			output = subFrac(n1, d1, n2, d2);
		} else if (operator.equals("*")) {
			output = multFrac(n1, d1, n2, d2);
		} else if (operator.equals("/")) {
			output = divFrac(n1, d1, n2, d2);
		} else if (operator.equals("-")) {
			output = subFrac(n1, d1, n2, d2);
		}

		return output;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need
	public static int findWhole(String operand) {

		// find where the operand has _ and /
		int splitWhole = operand.indexOf("_");
		int splitFrac = operand.indexOf("/");
		int whole;

		// split for the whole number if it exists
		if (splitWhole != -1) {
			whole = Integer.parseInt(operand.substring(0, splitWhole));
		} else if (splitFrac != -1) {
			whole = 0;
		} else {
			whole = Integer.parseInt(operand);
		}

		return whole;
	}

	public static int findNumerator(String operand) {

		// find where the operand has _ and /
		int splitWhole = operand.indexOf("_");
		int splitFrac = operand.indexOf("/");
		int numerator;

		// split for numerator if it exists, otherwise set it to 0
		if (splitWhole != -1) {
			numerator = Integer.parseInt(operand.substring(splitWhole + 1, splitFrac));
		} else if (splitFrac != -1) {
			numerator = Integer.parseInt(operand.substring(0, splitFrac));
		} else {
			numerator = 0;
		}

		return numerator;
	}

	public static int findDenominator(String operand) {

		// find where the operand has _ and /
		int splitWhole = operand.indexOf("_");
		int splitFrac = operand.indexOf("/");
		int denominator;

		// split for denominator if it exists, otherwise set it to 1
		if (splitFrac != -1) {
			denominator = Integer.parseInt(operand.substring(splitFrac + 1));
		} else {
			denominator = 1;
		}

		return denominator;
	}

	public static String simplifyFraction(int numerator, int denominator) {

		// initialize variables
		int factor = 1;
		String simple = "error";

		// convert into whole numbers
		int w = numerator / denominator;
		int n = numerator % denominator;
		int d = denominator;

		for (int i = 1; i <= n; i++) {
			if (n % i == 0 && d % i == 0) {
				factor = i;
			}
		}

		n = n / factor;
		if (n < 0) {
			n *= -1;
		}
		d = d / factor;
		if (d < 0) {
			d *= -1;
		}
		if (w != 0) {
			if (n != 0) {
				simple = w + "_" + n + "/" + d;
			} else if (n == 0) {
				simple = Integer.toString(w);
			}
		} else if (w == 0) {
			if (n != 0) {
				simple = n + "/" + d;
			} else if (n == 0) {
				simple = "0";
			}
		}
		return simple;

	}

	// operator algorithms
	public static String addFrac(int n1, int d1, int n2, int d2) {
		int numerator = (n1 * d2) + (n2 * d1);
		int denominator = (d1 * d2);
		return simplifyFraction(numerator, denominator);
	}

	public static String subFrac(int n1, int d1, int n2, int d2) {
		int numerator = (n1 * d2) - (n2 * d1);
		int denominator = (d1 * d2);
		return simplifyFraction(numerator, denominator);
	}

	public static String multFrac(int n1, int d1, int n2, int d2) {
		int numerator = n1 * n2;
		int denominator = d1 * d2;
		return simplifyFraction(numerator, denominator);
	}

	public static String divFrac(int n1, int d1, int n2, int d2) {
		int numerator = n1 * d2;
		int denominator = d1 * n2;
		return simplifyFraction(numerator, denominator);
	}

	public static int convertNumerator(int whole, int numerator, int denominator) {
		int converted;
		if (whole > 0) {
			converted = whole * denominator + numerator;
		} else if (whole < 0) {
			converted = -1 * ((-1 * whole) * denominator + numerator);
		} else {
			converted = numerator;
		}
		return converted;
	}
}
