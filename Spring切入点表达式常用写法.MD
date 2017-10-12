# Spring切入点表达式常用写法

自从使用AspectJ风格切面配置，使得Spring的切面配置大大简化，但是AspectJ是另外一个开源项目，其规则表达式的语法也稍稍有些怪异。
 
下面给出一些常见示例的写法：
 
比如，下面是一个对Service包上所有方法的切面配置
```
        <aop:config> 
                <aop:pointcut id="serviceOperation" 
                                            expression="execution(* *..service*..*(..))"/> 
                <aop:advisor pointcut-ref="serviceOperation" 
                                         advice-ref="txAdvice"/> 
        </aop:config>

```
表达式所处位置如上pointcut的位置。

配置这个为了更好控制切面上的事务，下面是一个事物配置的简单例子：
```
        <tx:advice id="txAdvice" transaction-manager="transactionManager"> 
                <tx:attributes> 
                        <tx:method name="delete*" rollback-for="Exception"/> 
                        <tx:method name="save*" rollback-for="Exception"/> 
                        <tx:method name="update*" rollback-for="Exception"/> 
                        <tx:method name="*" read-only="true" rollback-for="Exception"/> 
                </tx:attributes> 
        </tx:advice>
```
通过切面、通知的配置，就为所有的delete/save/update开头的方法添加上了一致性事务，对其他方法添加上了只读事务。
 
 
这个还不够细，如果要写更为详细的控制，就需要研究AspectJ切点配置的语法了，其实研究这些标准，还不如拿几个例子看看，解决实际问题就行了。就像写正则表达式一样，标准明摆着，要写好却不容易，从例子着手就能快速上手和领悟。

Spring AOP 用户可能会经常使用 execution切入点指示符。执行表达式的格式如下：
execution（modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern（param-pattern）
          throws-pattern?）
		  
除了返回类型模式（上面代码片断中的ret-type-pattern），名字模式和参数模式以外， 所有的部分都是可选的。返回类型模式决定了方法的返回类型必须依次匹配一个连接点。 你会使用的最频繁的返回类型模式是*，它代表了匹配任意的返回类型。 一个全限定的类型名将只会匹配返回给定类型的方法。名字模式匹配的是方法名。 你可以使用*通配符作为所有或者部分命名模式。 参数模式稍微有点复杂：()匹配了一个不接受任何参数的方法， 而(..)匹配了一个接受任意数量参数的方法（零或者更多）。 模式(*)匹配了一个接受一个任何类型的参数的方法。 模式(*,String)匹配了一个接受两个参数的方法，第一个可以是任意类型， 第二个则必须是String类型。更多的信息请参阅AspectJ编程指南中 语言语义的部分。


下面给出一些通用切入点表达式的例子。


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