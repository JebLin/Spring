package indi.sword.spring.ref;

import java.sql.SQLException;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) throws SQLException {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springContext2.xml");
		System.out.println("---------------------------------");

		//测试 bean 的作用域 scope="prototype" | scope="singleton"
		Dao dao_prototype1 = (Dao) ctx.getBean("dao_prototype");
		Dao dao_prototype2 = (Dao) ctx.getBean("dao_prototype");
		System.out.println("scope = prototype ? " + (dao_prototype1 == dao_prototype2));

		Dao dao_singleton1 = (Dao) ctx.getBean("dao");
		Dao dao_singleton2 = (Dao) ctx.getBean("dao");
		System.out.println("scope = singleton ? " + (dao_singleton1 == dao_singleton2));

		System.out.println("---------------------------------");
		Service service = (Service)ctx.getBean("service");
		service.save();
		System.out.println("---------------------------------");
		Action action = (Action)ctx.getBean("action");
		action.execute();
	}

}