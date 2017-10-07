package indi.sword.spring.ref;

import indi.sword.spring.helloworld.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;


public class Main2 {

	public static void main(String[] args) throws SQLException {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("springContext2.xml");
		System.out.println("---------------------------------");
		Action action = ctx.getBean(Action.class);

		action.execute();

		System.out.println("---------------------------------");
		//测试使用外部属性文件
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());

		//测试 spEL
		User boy = (User) ctx.getBean("boy");
		System.out.println(boy.getUserName() + ":" + boy.getWifeName());


		System.out.println("--------------------------------");
//		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat dateFormat = (DateFormat) ctx.getBean("dateFormat");
		System.out.println(dateFormat.format(new Date()));

		System.out.println("---------------------------------");
		Date date = (Date) ctx.getBean("datetime");
		System.out.println(date);
		System.out.println("---------------------------------");
		User user = (User) ctx.getBean("user");
		System.out.println(user);

		ctx.close();
	}

}