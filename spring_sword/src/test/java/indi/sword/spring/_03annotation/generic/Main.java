package indi.sword.spring._03annotation.generic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
组件装配:
			当在组件类上使用了特定的注解之后, 还需要在 Spring 的配置文件中声明 <context:component-scan> ：
			base-package 属性指定一个需要扫描的基类包，Spring 容器将会扫描这个基类包里及其子包中的所有类. 当需要扫描多个包时, 可以使用逗号分隔.

			组件扫描(component scanning):  Spring 能够从 classpath 下自动扫描, 侦测和实例化具有特定注解的组件.
			特定组件包括:
			@Component: 基本注解, 标识了一个受 Spring 管理的组件 .
			@Respository: 标识持久层组件 .
			@Service: 标识服务层(业务层)组件 .
			@Controller: 标识表现层组件 .
			对于扫描到的组件, Spring 有默认的命名策略: 使用非限定类名, 第一个字母小写. 也可以在注解中通过 value 属性值标识组件的名称 .
 */

/*
	使用 @Autowired 自动装配 Bean:
		@Autowired 注解自动装配具有兼容类型的单个 Bean属性.
		构造器, 普通字段(即使是非 public), 一切具有参数的方法都可以应用@Autowired 注解.
		默认情况下, 所有使用 @Autowired 注解的属性都需要被设置. 当 Spring 找不到匹配的 Bean 装配属性时, 会抛出异常, 若某一属性允许不被设置, 可以设置 @Autowired注解的 required 属性为 false .
		默认情况下, 当 IOC 容器里存在多个类型兼容的 Bean 时, 通过类型的自动装配将无法工作. 此时可以在 @Qualifier 注解里提供 Bean 的名称. Spring 允许对方法的入参标注 @Qualifiter 已指定注入 Bean 的名称
		.
		@Autowired 注解也可以应用在数组类型的属性上, 此时 Spring 将会把所有匹配的 Bean 进行自动装配.
		@Autowired 注解也可以应用在集合属性上, 此时 Spring 读取该集合的类型信息, 然后自动装配所有与之兼容的 Bean.
		@Autowired 注解用在 java.util.Map 上时, 若该 Map 的键值为 String, 那么 Spring 将自动装配与之 Map 值类型兼容的 Bean, 此时 Bean 的名称作为键值
 */
/*
	使用 @Resource 或 @Inject 自动装配 Bean:
		Spring 还支持 @Resource 和 @Inject 注解，这两个注解和 @Autowired 注解的功用类似.
		@Resource 注解要求提供一个 Bean 名称的属性，若该属性为空，则自动采用标注处的变量或方法名作为 Bean 的名称.
		@Inject 和 @Autowired 注解一样也是按类型匹配注入的 Bean， 但没有 required 属性.
				===========  建议使用 @Autowired 注解   ===================
 */

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");

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
