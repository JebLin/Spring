package indi.sword.spring.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setUser("Tom");
//		helloWorld.hello();

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springContext.xml");

		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");

		helloWorld.hello();
	}

}
