package indi.sword.spring.annotation.generic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-annotation.xml");

		/*
		//若注解没有指定 bean 的 id, 则类名第一个字母小写即为 bean 的 id
		UserService userService = (UserService) ctx.getBean("userService");
		userService.addNew(new User());

		RoleService roleService = (RoleService) ctx.getBean("roleService");
		roleService.addNew(new Role());
		*/
		UserService userService = (UserService) ctx.getBean("userService1");
		userService.addNew(new User());

	}
	
}
