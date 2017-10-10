package indi.sword.spring._03annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		
		UserAction userAction = ctx.getBean(UserAction.class);
		userAction.execute();
	}
	
}
