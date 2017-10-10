package indi.sword.spring._04aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * AOP 的 helloWorld
 * 1. 加入 jar 包
 * com.springsource.net.sf.cglib-2.2.0.jar
 * com.springsource.org.aopalliance-1.0.0.jar
 * com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
 * spring-aspects-4.0.0.RELEASE.jar
 *
 * 2. 在 Spring 的配置文件中加入 _04aop 的命名空间。
 *
 * 3. 基于注解的方式来使用 AOP
 * 3.1 在配置文件中配置自动扫描的包: <context:component-scan base-package="indi.sword.spring._04aop"></context:component-scan>
 * 3.2 加入使 AspjectJ 注解起作用的配置: <_04aop:aspectj-autoproxy></_04aop:aspectj-autoproxy>
 * 为匹配的类自动生成动态代理对象.
 *
 * 4. 编写切面类:
 * 4.1 一个一般的 Java 类
 * 4.2 在其中添加要额外实现的功能.
 *
 * 5. 配置切面
 * 5.1 切面必须是 IOC 中的 bean: 实际添加了 @Component 注解
 * 5.2 声明是一个切面: 添加 @Aspect
 * 5.3 声明通知: 即额外加入功能对应的方法.
 * 5.3.1 前置通知: @Before("execution(public int indi.sword.spring._04aop.ArithmeticCalculator.*(int, int))")
 * @Before 表示在目标方法执行之前执行 @Before 标记的方法的方法体.
 * @Before 里面的是切入点表达式:
 *
 * 6. 在通知中访问连接细节: 可以在通知方法中添加 JoinPoint 类型的参数, 从中可以访问到方法的签名和方法的参数.
 *
 * 7. @After 表示后置通知: 在方法执行之后执行的代码.
 */
/*
	AspectJ：Java 社区里最完整最流行的 AOP 框架.
	在 Spring2.0 以上版本中, 可以使用基于 AspectJ 注解或基于 XML 配置的 AOP.

	AOP术语：
		切面(Aspect):  横切关注点(跨越应用程序多个模块的功能)被模块化的特殊对象
		通知(Advice):  切面必须要完成的工作
		目标(Target): 被通知的对象
		代理(Proxy): 向目标对象应用通知之后创建的对象
		连接点（Joinpoint）：程序执行的某个特定位置：如类某个方法调用前、调用后、方法抛出异常后等。连接点由两个信息确定：方法表示的程序执行点；相对点表示的方位。例如 ArithmethicCalculator#add() 方法执行前的连接点，执行点为 ArithmethicCalculator#add()； 方位为该方法执行前的位置
		切点（pointcut）：每个类都拥有多个连接点：例如 ArithmethicCalculator 的所有方法实际上都是连接点，即连接点是程序类中客观存在的事务。AOP 通过切点定位到特定的连接点。类比：连接点相当于数据库中的记录，切点相当于查询条件。切点和连接点不是一对一的关系，一个切点匹配多个连接点，切点通过 org.springframework._04aop.Pointcut 接口进行描述，它使用类和方法作为连接点的查询条件。
 */
/*
	在 Spring 中启用 AspectJ 注解支持：
		要在 Spring 应用中使用 AspectJ 注解, 必须在 classpath 下包含 AspectJ 类库: aopalliance.jar、aspectj.weaver.jar 和 spring-aspects.jar
		将 _04aop Schema 添加到 <beans> 根元素中.
		要在 Spring IOC 容器中启用 AspectJ 注解支持, 只要在 Bean 配置文件中定义一个空的 XML 元素 <_04aop:aspectj-autoproxy>
		当 Spring IOC 容器侦测到 Bean 配置文件中的 <_04aop:aspectj-autoproxy> 元素时, 会自动为与 AspectJ 切面匹配的 Bean 创建代理.
 */
/*
	用 AspectJ 注解声明切面：
		要在 Spring 中声明 AspectJ 切面, 只需要在 IOC 容器中将切面声明为 Bean 实例. 当在 Spring IOC 容器中初始化 AspectJ 切面之后, Spring IOC 容器就会为那些与 AspectJ 切面相匹配的 Bean 创建代理.
		在 AspectJ 注解中, 切面只是一个带有 @Aspect 注解的 Java 类.

		通知是标注有某种注解的简单的 Java 方法.
	AspectJ 支持 5 种类型的通知注解:
		@Before: 前置通知, 在方法执行之前执行
		@After: 后置通知, 在方法执行之后执行
		@AfterReturning: 返回通知, 在方法返回结果之后执行
		@AfterThrowing: 异常通知, 在方法抛出异常之后
		@Around: 环绕通知, 围绕着方法执行
 */
/*
	利用方法签名编写 AspectJ 切入点表达式:
		最典型的切入点表达式时根据方法的签名来匹配各种方法:
		execution (* com.atguigu.spring.ArithmeticCalculator.*(..)): 匹配 ArithmeticCalculator 中声明的所有方法,第一个 * 代表任意修饰符及任意返回值. 第二个 * 代表任意方法. .. 匹配任意数量的参数. 若目标类与接口与该切面在同一个包中, 可以省略包名.
		execution (public * ArithmeticCalculator.*(..)): 匹配 ArithmeticCalculator 接口的所有公有方法.
		execution (public double ArithmeticCalculator.*(..)): 匹配 ArithmeticCalculator 中返回 double 类型数值的方法
		execution (public double ArithmeticCalculator.*(double, ..)): 匹配第一个参数为 double 类型的方法, .. 匹配任意数量任意类型的参数
		execution (public double ArithmeticCalculator.*(double, double)): 匹配参数类型为 double, double 类型的方法.

	在 AspectJ 中, 切入点表达式可以通过操作符 &&, ||, ! 结合起来.
		    Spring支持使用如下三个逻辑运算符来组合切入点表达式：
			&&：要求连接点同时匹配两个切点表达式
			||：要求连接点匹配至少一个切入点表达式
			!：要求连接点不匹配指定的切入点表达式
 */

//通过添加 @Aspect 注解声明一个 bean 是一个切面!
@Aspect
@Component
public class LoggingAspect {

	@Before("execution(public int indi.sword.spring._04aop.ArithmeticCalculator.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();

		System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
	}

	@After("execution(* indi.sword.spring._04aop.*.*(..))")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends");
	}

}
