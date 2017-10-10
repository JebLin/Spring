package indi.sword.spring._01helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
	ApplicationContext 的主要实现类：
	ClassPathXmlApplicationContext：从 类路径下加载配置文件
	FileSystemXmlApplicationContext: 从文件系统中加载配置文件
	ConfigurableApplicationContext 扩展于 ApplicationContext，新增加两个主要方法：refresh() 和 close()， 让 ApplicationContext 具有启动、刷新和关闭上下文的能力
	ApplicationContext 在初始化上下文时就实例化所有单例的 Bean。
	WebApplicationContext 是专门为 WEB 应用而准备的，它允许从相对于 WEB 根目录的路径中完成初始化工作

	ApplicationContext
	-- | ConfigurableApplicationContext
		-- | ClassPathXmlApplicationContext
		-- | FileSystemXmlApplicationContext
 */
public class Main {

	public static void main(String[] args) {

		// 世界上最原始的方法
//		HelloWorld helloWorld = new HelloWorld();
//		helloWorld.setUser("Tom");
//		helloWorld.hello();

		//1. 创建 Spring 的 IOC 容器
		// 单单下面的这一句初始化ctx，就已经去初始化配置的全部bean了。
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("------ 全部 bean 已经初始化完毕 --------");

		//2. 从 IOC 容器中获取 bean 的实例
		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld3");
		helloWorld.hello();

		//根据类型来获取 bean 的实例: 要求在  IOC 容器中只有一个与之类型匹配的 bean, 若有多个则会抛出异常.
		//一般情况下, 该方法可用, 因为一般情况下, 在一个 IOC 容器中一个类型对应的 bean 也只有一个.
//		HelloWorld helloWorld1 = ctx.getBean(HelloWorld.class);
		// 上面这玩意会抛异常，报错


		Car car = (Car) ctx.getBean("car");
		System.out.println(car);

		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);
		System.out.println("---------------------");


		/*
			Spring IOC 容器可以自动装配 Bean. 需要做的仅仅是在 <bean> 的 autowire 属性里指定自动装配的模式 .
			byType(根据类型自动装配): 若 IOC 容器中有多个与目标 Bean 类型一致的 Bean. 在这种情况下, Spring 将无法判定哪个 Bean 最合适该属性, 所以不能执行自动装配.
			byName(根据名称自动装配): 必须将目标 Bean 的名称和属性名设置的完全相同.
			constructor(通过构造器自动装配): 当 Bean 中存在多个构造器时, 此种自动装配方式将会很复杂. 不推荐使用 .
		 */
		// 3、测试autowire
		ScopeInfo scopeInfo = (ScopeInfo) ctx.getBean(ScopeInfo.class);
		scopeInfo.hello();

		System.out.println("---------------------");
		//4. 测试使用集合属性
		User user = (User) ctx.getBean("user6");
		System.out.println(user);

	}

}
