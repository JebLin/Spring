package indi.sword.spring._04aop.annotation;

import indi.sword.spring._04aop.normal.ArithmeticCalculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {


		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-aop-annotation.xml");
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

		System.out.println(arithmeticCalculator.getClass().getName());

		int result = arithmeticCalculator.add(11, 12);
		System.out.println("result:" + result);

		result = arithmeticCalculator.div(21, 3);
		System.out.println("result:" + result);

	}
	
}
