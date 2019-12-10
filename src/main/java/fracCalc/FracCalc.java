package fracCalc;

import java.util.*;

public class FracCalc {
	// read input from user and call produce answer with an equation, running
	// continuously until user enters "quit"
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		String equation = console.nextLine();
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = console.nextLine();
		}
		console.close();
	}

	// split equation, then call methods to solve equation and produce solution
	public static String produceAnswer(String input) {
		String firstOperand = "";
		String operator = "";
		String secondOperand = "";
		int split = input.indexOf(" ");
		String output = "";
		firstOperand = input.substring(0, split);
		operator = input.substring(split + 1, split + 2);
		secondOperand = input.substring(split + 3);
		int d1 = findDenominator(firstOperand);
		int d2 = findDenominator(secondOperand);
		int n1 = convertNumerator(findWhole(firstOperand), findNumerator(firstOperand), d1);
		int n2 = convertNumerator(findWhole(secondOperand), findNumerator(secondOperand), d2);
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

	// split the operand and return the whole number
	public static int findWhole(String operand) {
		int splitWhole = operand.indexOf("_");
		int splitFrac = operand.indexOf("/");
		int whole;
		if (splitWhole != -1) {
			whole = Integer.parseInt(operand.substring(0, splitWhole));
		} else if (splitFrac != -1) {
			whole = 0;
		} else {
			whole = Integer.parseInt(operand);
		}
		return whole;
	}

	// split the operand and return the numerator
	public static int findNumerator(String operand) {
		int splitWhole = operand.indexOf("_");
		int splitFrac = operand.indexOf("/");
		int numerator;
		if (splitWhole != -1) {
			numerator = Integer.parseInt(operand.substring(splitWhole + 1, splitFrac));
		} else if (splitFrac != -1) {
			numerator = Integer.parseInt(operand.substring(0, splitFrac));
		} else {
			numerator = 0;
		}
		return numerator;
	}

	// split the operand and return the denominator
	public static int findDenominator(String operand) {
		int splitFrac = operand.indexOf("/");
		int denominator;
		if (splitFrac != -1) {
			denominator = Integer.parseInt(operand.substring(splitFrac + 1));
		} else {
			denominator = 1;
		}
		return denominator;
	}

	// find greatest common factor of numerator and denominator to simplify fraction
	// return simplified output, accounting for cases with 0s and negatives
	public static String simplifyFraction(int numerator, int denominator) {
		int factor = 1;
		String simple = "error";
		int w = numerator / denominator;
		int n = numerator % denominator;
		int d = denominator;
		Boolean neg = false;
		if (n < 0) {
			n *= -1;
			neg = true;
		}
		if (d < 0) {
			d *= -1;
			neg = true;
		}
		for (int i = 1; i <= n; i++) {
			if (n % i == 0 && d % i == 0) {
				factor = i;
			}
		}
		n = n / factor;
		d = d / factor;
		if (w != 0) {
			if (n != 0) {
				simple = w + "_" + n + "/" + d;
			} else if (n == 0) {
				simple = Integer.toString(w);
			}
		} else if (w == 0) {
			if (n != 0 && neg == false) {
				simple = n + "/" + d;
			} else if (n == 0) {
				simple = "0";
			} else if (neg == true) {
				simple = "-" + n + "/" + d;
			}
		}
		return simple;
	}

	// add fractions and simplify
	public static String addFrac(int n1, int d1, int n2, int d2) {
		int numerator = (n1 * d2) + (n2 * d1);
		int denominator = (d1 * d2);
		return simplifyFraction(numerator, denominator);
	}

	// subtract fractions and simplify
	public static String subFrac(int n1, int d1, int n2, int d2) {
		int numerator = (n1 * d2) - (n2 * d1);
		int denominator = (d1 * d2);
		return simplifyFraction(numerator, denominator);
	}

	// multiply fractions and simplify
	public static String multFrac(int n1, int d1, int n2, int d2) {
		int numerator = n1 * n2;
		int denominator = d1 * d2;
		return simplifyFraction(numerator, denominator);
	}

	// divide fractions and simplify
	public static String divFrac(int n1, int d1, int n2, int d2) {
		int numerator = n1 * d2;
		int denominator = d1 * n2;
		return simplifyFraction(numerator, denominator);
	}

	// convert whole number and numerator in fraction to one numerator
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