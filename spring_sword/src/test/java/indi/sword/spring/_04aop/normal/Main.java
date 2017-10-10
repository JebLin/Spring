package indi.sword.spring._04aop.normal;


import indi.sword.spring._04aop.xml.ArithmeticCalculatorImpl;

public class Main {
	
	public static void main(String[] args) {

		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();

		arithmeticCalculator =
				new ArithmeticCalculatorLoggingProxy(arithmeticCalculator).getLoggingProxy();

		System.out.println("----------------------------");
		int result = arithmeticCalculator.add(11, 12);
		System.out.println("result:" + result);

		result = arithmeticCalculator.div(21, 3);
		System.out.println("result:" + result);

	}
	
}
