package indi.sword.spring._07_union_hibernate;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
/*
    三个* : 权限、包、方法名
 	匹配所有
       execution("* *.*(..)")

	任意公共方法的执行：
		execution（public * *（..））

	任何一个名字以“set”开始的方法的执行：
		execution（* set*（..））

	AccountService接口定义的任意方法的执行：
		execution（* com.xyz.service.AccountService.*（..））

	匹配指定包下所有的方法
        execution("* com.xyz.service.impl.*(..))

	在service包中定义的任意方法的执行：
		execution（* com.xyz.service.*.*（..））

	在service包或其子包中定义的任意方法的执行：
		execution（* com.xyz.service..*.*（..））

	匹配指定包以及其子包下 参数类型为String 的方法
        execution("* com.david..*(java.lang.String))

	在service包中的任意连接点（在Spring AOP中只是方法执行）：
		within（com.xyz.service.*）

	在service包或其子包中的任意连接点（在Spring AOP中只是方法执行）：
		within（com.xyz.service..*）

	实现了AccountService接口的代理对象的任意连接点 （在Spring AOP中只是方法执行）：
		this（com.xyz.service.AccountService）

	'this'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得代理对象在通知体内可用。
	实现AccountService接口的目标对象的任意连接点 （在Spring AOP中只是方法执行）：
		target（com.xyz.service.AccountService）

	'target'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得目标对象在通知体内可用。
	任何一个只接受一个参数，并且运行时所传入的参数是Serializable 接口的连接点（在Spring AOP中只是方法执行）
		args（java.io.Serializable）

	'args'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得方法参数在通知体内可用。
	请注意在例子中给出的切入点不同于 execution(* *(java.io.Serializable))： args版本只有在动态运行时候传入参数是Serializable时才匹配，而execution版本在方法签名中声明只有一个 Serializable类型的参数时候匹配。
	目标对象中有一个 @Transactional 注解的任意连接点 （在Spring AOP中只是方法执行）

	@target（org.springframework.transaction.annotation.Transactional）
	'@target'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
	任何一个目标对象声明的类型有一个 @Transactional 注解的连接点 （在Spring AOP中只是方法执行）：

	@within（org.springframework.transaction.annotation.Transactional）
	'@within'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
	任何一个执行的方法有一个 @Transactional 注解的连接点 （在Spring AOP中只是方法执行）

	@annotation（org.springframework.transaction.annotation.Transactional）
	'@annotation'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
	任何一个只接受一个参数，并且运行时所传入的参数类型具有@Classified 注解的连接点（在Spring AOP中只是方法执行）

	@args（com.xyz.security.Classified）
	'@args'在绑定表单中更加常用：- 请参见后面的通知一节中了解如何使得注解对象在通知体内可用。
	任何一个在名为'tradeService'的Spring bean之上的连接点 （在Spring AOP中只是方法执行）：

	bean（tradeService）
	任何一个在名字匹配通配符表达式'*Service'的Spring bean之上的连接点 （在Spring AOP中只是方法执行）：
	bean（*Service）
 */
public class Test01 {

    private ApplicationContext context = null;
    private BookService bookService = null;

    {
        context = new ClassPathXmlApplicationContext("applicationContext-hibernate.xml");
        bookService = context.getBean(BookService.class);
    }

    @Test
    public void test() {
        DataSource dataSource = (DataSource) context.getBean(DataSource.class);
        System.out.println(dataSource);
    }

    @Test
    public void test2() {
        String bookName = bookService.findBookById(2);
        System.out.println(bookName);
    }

    @Test
    public void test3() {
        bookService.saveBook(new Book(1, "android anlysis", "1002", 45, 10));
    }
}

