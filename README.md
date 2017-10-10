### B\S


 ###  Spring 是什么
```
Spring 是一个开源框架. 
Spring 为简化企业级应用开发而生. 使用 Spring 可以使简单的 JavaBean 实现以前只有 EJB 才能实现的功能. (J2ee without ejb )
Spring 是一个 IOC(DI) 和 AOP 容器框架. 

具体描述 Spring: 
轻量级：Spring 是非侵入性的 - 基于 Spring 开发的应用中的对象可以不依赖于 Spring 的 API .
依赖注入(DI --- dependency injection、IOC) .
面向切面编程(AOP --- aspect oriented programming) .
容器: Spring 是一个容器, 因为它包含并且管理应用对象的生命周期 .
框架: Spring 实现了使用简单的组件配置组合成一个复杂的应用. 在 Spring 中可以使用 XML 和 Java 注解组合这些对象.
一站式：在 IOC 和 AOP 的基础上可以整合各种企业应用的开源框架和优秀的第三方类库 （实际上 Spring 自身也提供了展现层的 SpringMVC 和 持久层的 Spring JDBC） .
```

### Spring 模块

#### IOC 和 DI
```
IOC(Inversion of Control) 控制反转：其思想是反转资源获取的方向. 传统的资源查找方式要求组件向容器发起请求查找资源. 作为回应, 容器适时的返回资源. 而应用了 IOC 之后, 则是容器主动地将资源推送给它所管理的组件, 组件所要做的仅是选择一种合适的方式来接受资源. 这种行为也被称为查找的被动形式.
DI(Dependency Injection) 依赖注入： — IOC 的另一种表述方式：即组件以一些预先定义好的方式(例如: setter 方法)接受来自如容器的资源注入. 相对于 IOC 而言，这种表述更直接 .

``` 

#### Spring 中的 Bean 配置
```
配置形式：基于 XML 文件的方式；基于注解的方式 。
Bean 的配置方式：通过全类名（反射）、通过工厂方法（静态工厂方法 & 实例工厂方法）、FactoryBean 。
IOC 容器 BeanFactory & ApplicationContext 概述。
依赖注入的方式：属性注入；构造器注入 。
注入属性值细节 。
自动转配 。
bean 之间的关系：继承；依赖。
bean 的作用域：singleton；prototype；WEB 环境作用域 。
使用外部属性文件。
spEL 。
IOC 容器中 Bean 的生命周期 。
Spring 4.x 新特性：泛型依赖注入 。
```

 

 
 
 #### 在 Spring 的 IOC 容器里配置 Bean
```
在 xml 文件中通过 bean 节点来配置 bean .
<!-- 通过全类名来配置bean -->
<bean id = "helloworld"
        class = "indi.sword.spring.helloworld.HelloWorld">
</bean>
id：Bean 的名称。
在 IOC 容器中必须是唯一的 。
若 id 没有指定，Spring 自动将权限定性类名作为 Bean 的名字 。
id 可以指定多个名字，名字之间可用逗号、分号、或空格分隔 。
```

#### Spring 容器
```
在 Spring IOC 容器读取 Bean 配置创建 Bean 实例之前, 必须对它进行实例化. 只有在容器实例化后, 才可以从 IOC 容器里获取 Bean 实例并使用.

Spring 提供了两种类型的 IOC 容器实现. 
BeanFactory: IOC 容器的基本实现. 
ApplicationContext: 提供了更多的高级特性. 是 BeanFactory 的子接口. 
BeanFactory 是 Spring 框架的基础设施，面向 Spring 本身；ApplicationContext 面向使用 Spring 框架的开发者，几乎所有的应用场合都直接使用 ApplicationContext 而非底层的 BeanFactory .
无论使用何种方式, 配置文件时相同的. 
```
#### ApplicationContext
```
ApplicationContext 的主要实现类： 
ClassPathXmlApplicationContext：从 类路径下加载配置文件 .
FileSystemXmlApplicationContext: 从文件系统中加载配置文件 .

ConfigurableApplicationContext 扩展于 ApplicationContext，新增加两个主要方法：
refresh() 和 close()， 让 ApplicationContext 具有启动、刷新和关闭上下文的能力.
ApplicationContext 在初始化上下文时就实例化所有单例的 Bean。 
WebApplicationContext 是专门为 WEB 应用而准备的，它允许从相对于 WEB 根目录的路径中完成初始化工作 .
```


#### 从 IOC 容器中获取 Bean
```
调用 ApplicationContext 的 getBean() 方法 
```
 

#### 依赖注入的方式
```
Spring 支持 3 种依赖注入的方式 :
属性注入
构造器注入 
工厂方法注入（很少使用，不推荐）

属性注入：
属性注入即通过 setter 方法注入Bean 的属性值或依赖的对象.
属性注入使用 <property> 元素, 使用 name 属性指定 Bean 的属性名称，value 属性或 <value> 子节点指定属性值 .
属性注入是实际应用中最常用的注入方式 .

构造方法注入 ：
通过构造方法注入Bean 的属性值或依赖的对象，它保证了 Bean 实例在实例化后就可以使用。 
构造器注入在 <constructor-arg> 元素里声明属性, <constructor-arg> 中没有 name 属性 。
```
 
 
####  字面值
```
字面值：可用字符串表示的值，可以通过 <value> 元素标签或 value 属性进行注入。
基本数据类型及其封装类、String 等类型都可以采取字面值注入的方式 .
若字面值中包含特殊字符，可以使用 <![CDATA[]]> 把字面值包裹起来。 
```
#### 引用其它 Bean
```
组成应用程序的 Bean 经常需要相互协作以完成应用程序的功能. 要使 Bean 能够相互访问, 就必须在 Bean 配置文件中指定对 Bean 的引用.
在 Bean 的配置文件中, 可以通过 <ref> 元素或 ref  属性为 Bean 的属性或构造器参数指定对 Bean 的引用. 
也可以在属性或构造器里包含 Bean 的声明, 这样的 Bean 称为内部 Bean 
```

 
#### 内部 Bean
```
当 Bean 实例仅仅给一个特定的属性使用时, 可以将其声明为内部 Bean. 内部 Bean 声明直接包含在 <property> 或 <constructor-arg> 元素里, 不需要设置任何 id 或 name 属性.
内部 Bean 不能使用在任何其他地方 .
```

#### 注入参数详解：null 值和级联属性
```
可以使用专用的 <null/> 元素标签为 Bean 的字符串或其它对象类型的属性注入 null 值 .
和 Struts、Hiberante 等框架一样，Spring 支持级联属性的配置。
```

#### 集合属性
```
在 Spring中可以通过一组内置的 xml 标签(例如: <list>, <set> 或 <map>) 来配置集合属性.
配置 java.util.List 类型的属性, 需要指定 <list>  标签, 在标签里包含一些元素. 这些标签可以通过 <value> 指定简单的常量值, 通过 <ref> 指定对其他 Bean 的引用. 通过<bean> 指定内置 Bean 定义. 通过 <null/> 指定空元素. 甚至可以内嵌其他集合. 
数组的定义和 List 一样, 都使用 <list> .
配置 java.util.Set 需要使用 <set> 标签, 定义元素的方法与 List 一样. 
Java.util.Map 通过 <map> 标签定义, <map> 标签里可以使用多个 <entry> 作为子标签. 每个条目包含一个键和一个值. 
必须在 <key> 标签里定义键 .
因为键和值的类型没有限制, 所以可以自由地为它们指定 <value>, <ref>, <bean> 或 <null> 元素. 
可以将 Map 的键和值作为 <entry> 的属性定义: 简单常量使用 key 和 value 来定义; Bean 引用通过 key-ref 和 value-ref 属性定义.
使用 <props> 定义 java.util.Properties, 该标签使用多个 <prop> 作为子标签. 每个 <prop> 标签必须定义 key 属性.
```

#### 使用 utility scheme 定义集合
```
使用基本的集合标签定义集合时, 不能将集合作为独立的 Bean 定义, 导致其他 Bean 无法引用该集合, 所以无法在不同 Bean 之间共享集合. 
可以使用 util schema 里的集合标签定义独立的集合 Bean. 需要注意的是, 必须在 <beans> 根元素里添加 util schema 定义 .
```
#### 使用 p 命名空间
```
为了简化 XML 文件的配置，越来越多的 XML 文件采用属性而非子元素配置信息。 
Spring 从 2.5 版本开始引入了一个新的 p 命名空间，可以通过 <bean> 元素属性的方式配置 Bean 的属性。 
使用 p 命名空间后，基于 XML 的配置方式将进一步简化.
```
#### XML 配置里的 Bean 自动装配
```
Spring IOC 容器可以自动装配 Bean. 需要做的仅仅是在 <bean> 的 autowire 属性里指定自动装配的模式 .
byType(根据类型自动装配): 若 IOC 容器中有多个与目标 Bean 类型一致的 Bean. 在这种情况下, Spring 将无法判定哪个 Bean 最合适该属性, 所以不能执行自动装配.
byName(根据名称自动装配): 必须将目标 Bean 的名称和属性名设置的完全相同. 
constructor(通过构造器自动装配): 当 Bean 中存在多个构造器时, 此种自动装配方式将会很复杂. 不推荐使用 .
```
#### XML 配置里的 Bean 自动装配的缺点
```
在 Bean 配置文件里设置 autowire 属性进行自动装配将会装配 Bean 的所有属性. 然而, 若只希望装配个别属性时, autowire 属性就不够灵活了.
autowire 属性要么根据类型自动装配, 要么根据名称自动装配, 不能两者兼而有之. 
一般情况下，在实际的项目中很少使用自动装配功能，因为和自动装配功能所带来的好处比起来，明确清晰的配置文档更有说服力一些 .
```
#### 继承 Bean 配置
```
Spring 允许继承 bean 的配置, 被继承的 bean 称为父 bean. 继承这个父 Bean 的 Bean 称为子 Bean.
子 Bean 从父 Bean 中继承配置, 包括 Bean 的属性配置 .
子 Bean 也可以覆盖从父 Bean 继承过来的配置 .
父 Bean 可以作为配置模板, 也可以作为 Bean 实例. 若只想把父 Bean 作为模板, 可以设置 <bean> 的abstract 属性为 true, 这样 Spring 将不会实例化这个 Bean .
并不是 <bean> 元素里的所有属性都会被继承. 比如: autowire, abstract 等. 
也可以忽略父 Bean 的 class 属性, 让子 Bean 指定自己的类, 而共享相同的属性配置. 但此时 abstract 必须设为 true
```
#### 依赖 Bean 配置
```
Spring 允许用户通过 depends-on 属性设定 Bean 前置依赖的Bean，前置依赖的 Bean 会在本 Bean 实例化之前创建好 .
如果前置依赖于多个 Bean，则可以通过逗号，空格或的方式配置 Bean 的名称 .
```
#### Bean 的作用域
```
在 Spring 中, 可以在 <bean> 元素的 scope 属性里设置 Bean 的作用域. 
默认情况下, Spring 只为每个在 IOC 容器里声明的 Bean 创建唯一一个实例, 整个 IOC 容器范围内都能共享该实例：所有后续的 getBean() 调用和 Bean 引用都将返回这个唯一的 Bean 实例.该作用域被称为 singleton, 它是所有 Bean 的默认作用域. 
```

 
#### 使用外部属性文件
```
在配置文件里配置 Bean 时, 有时需要在 Bean 的配置里混入系统部署的细节信息(例如: 文件路径, 数据源配置信息等). 而这些部署细节实际上需要和 Bean 配置相分离 .
Spring 提供了一个 PropertyPlaceholderConfigurer 的 BeanFactory 后置处理器, 这个处理器允许用户将 Bean 配置的部分内容外移到属性文件中. 可以在 Bean 配置文件里使用形式为 ${var} 的变量, PropertyPlaceholderConfigurer 从属性文件里加载属性, 并使用这些属性来替换变量.
Spring 还允许在属性文件中使用 ${propName}，以实现属性之间的相互引用。 
```


 

#### Spring表达式语言：SpEL
```
Spring 表达式语言（简称SpEL）：是一个支持运行时查询和操作对象图的强大的表达式语言。
语法类似于 EL：SpEL 使用 #{…} 作为定界符，所有在大框号中的字符都将被认为是 SpEL .
SpEL 为 bean 的属性进行动态赋值提供了便利.
通过 SpEL 可以实现： 
通过 bean 的 id 对 bean 进行引用 .
调用方法以及引用对象中的属性.
计算表达式的值 .
正则表达式的匹配 .
```
#### SpEL：字面量
```
字面量的表示： 
整数：<property name="count" value="#{5}"/> 
小数：<property name="frequency" value="#{89.7}"/> 
科学计数法：<property name="capacity" value="#{1e4}"/> 
String可以使用单引号或者双引号作为字符串的定界符号：
<property name=“name” value="#{'Chuck'}"/> 或 <property name='name' value='#{"Chuck"}'/> 
Boolean：<property name="enabled" value="#{false}"/> 
```


 
 
 

 
#### IOC 容器中 Bean 的生命周期方法
```
Spring IOC 容器可以管理 Bean 的生命周期, Spring 允许在 Bean 生命周期的特定点执行定制的任务. 

Spring IOC 容器对 Bean 的生命周期进行管理的过程: 
通过构造器或工厂方法创建 Bean 实例 .
为 Bean 的属性设置值和对其他 Bean 的引用 .
调用 Bean 的初始化方法 .
Bean 可以使用了.
当容器关闭时, 调用 Bean 的销毁方法.
在 Bean 的声明里设置 init-method 和 destroy-method 属性, 为 Bean 指定初始化和销毁方法. 
```

#### 创建 Bean 后置处理器
```
Bean 后置处理器允许在调用初始化方法前后对 Bean 进行额外的处理.
Bean 后置处理器对 IOC 容器里的所有 Bean 实例逐一处理, 而非单一实例. 其典型应用是: 检查 Bean 属性的正确性或根据特定的标准更改 Bean 的属性. 
对Bean 后置处理器而言, 需要实现 BeanPostProcessor 接口. 在初始化方法被调用前后, Spring 将把每个 Bean 实例分别传递给上述接口的以下两个方法: 
postProcessAfterInitialization 、postProcessBeforeInitialization
```

#### 添加 Bean 后置处理器后 Bean 的生命周期
```
Spring IOC 容器对 Bean 的生命周期进行管理的过程:
通过构造器或工厂方法创建 Bean 实例 .
为 Bean 的属性设置值和对其他 Bean 的引用 .
将 Bean 实例传递给 Bean 后置处理器的 postProcessBeforeInitialization 方法 .
调用 Bean 的初始化方法  将 Bean 实例传递给 Bean 后置处理器的 postProcessAfterInitialization方法 
Bean 可以使用了  当容器关闭时, 调用 Bean 的销毁方法 .
```

#### 通过调用静态工厂方法创建 Bean
```
调用静态工厂方法创建 Bean是将对象创建的过程封装到静态方法中. 当客户端需要对象时, 只需要简单地调用静态方法, 而不同关心创建对象的细节.
要声明通过静态方法创建的 Bean, 需要在 Bean 的 class 属性里指定拥有该工厂的方法的类, 同时在 factory-method 属性里指定工厂方法的名称. 最后, 使用 <constrctor-arg> 元素为该方法传递方法参数. 
实例工厂方法: 将对象的创建过程封装到另外一个对象实例的方法里. 当客户端需要请求对象时, 只需要简单的调用该实例方法而不需要关心对象的创建细节.
```
#### 实现 FactoryBean 接口在 Spring IOC 容器中配置 Bean
```
Spring 中有两种类型的 Bean, 一种是普通Bean, 另一种是工厂Bean, 即FactoryBean. 
工厂 Bean 跟普通Bean不同, 其返回的对象不是指定类的一个实例, 其返回的是该工厂 Bean 的 getObject 方法所返回的对象
```



#### 在 classpath 中扫描组件
```
组件扫描(component scanning):  Spring 能够从 classpath 下自动扫描, 侦测和实例化具有特定注解的组件. 
特定组件包括: 
@Component: 基本注解, 标识了一个受 Spring 管理的组件 .
@Respository: 标识持久层组件 .
@Service: 标识服务层(业务层)组件 .
@Controller: 标识表现层组件 .
对于扫描到的组件, Spring 有默认的命名策略: 使用非限定类名, 第一个字母小写. 也可以在注解中通过 value 属性值标识组件的名称 .

当在组件类上使用了特定的注解之后, 还需要在 Spring 的配置文件中声明 <context:component-scan> ： 
base-package 属性指定一个需要扫描的基类包，Spring 容器将会扫描这个基类包里及其子包中的所有类. 当需要扫描多个包时, 可以使用逗号分隔. 
<context:component-scan
     base-package="indi.sword.spring.beans"
     resource-pattern="autowire/*/class" />
如果仅希望扫描特定的类而非基包下的所有类，可使用 resource-pattern 属性过滤特定的类，示例： 
<context:include-filter> 子节点表示要包含的目标类 .
<context:exclude-filter> 子节点表示要排除在外的目标类 .
<context:component-scan> 下可以拥有若干个 <context:include-filter> 和 <context:exclude-filter> 子节点
```



### 组件装配
```
<context:component-scan> 元素还会自动注册 AutowiredAnnotationBeanPostProcessor 实例, 该实例可以自动装配具有 @Autowired 和 @Resource 、@Inject注解的属性. 
```
 #### 使用 @Autowired 自动装配 Bean
```
@Autowired 注解自动装配具有兼容类型的单个 Bean属性 .
构造器, 普通字段(即使是非 public), 一切具有参数的方法都可以应用@Authwired 注解.
默认情况下, 所有使用 @Authwired 注解的属性都需要被设置. 当 Spring 找不到匹配的 Bean 装配属性时, 会抛出异常, 若某一属性允许不被设置, 可以设置 @Autowired注解的 required 属性为 false .
默认情况下, 当 IOC 容器里存在多个类型兼容的 Bean 时, 通过类型的自动装配将无法工作. 此时可以在 @Qualifier 注解里提供 Bean 的名称. Spring 允许对方法的入参标注 @Qualifiter 已指定注入 Bean 的名称 .
@Autowired 注解也可以应用在数组类型的属性上, 此时 Spring 将会把所有匹配的 Bean 进行自动装配. 
@Autowired 注解也可以应用在集合属性上, 此时 Spring 读取该集合的类型信息, 然后自动装配所有与之兼容的 Bean. 
@Autowired 注解用在 java.util.Map 上时, 若该 Map 的键值为 String, 那么 Spring 将自动装配与之 Map 值类型兼容的 Bean, 此时 Bean 的名称作为键值 
```
#### 使用 @Resource 或 @Inject 自动装配 Bean
```
Spring 还支持 @Resource 和 @Inject 注解，这两个注解和 @Autowired 注解的功用类似 .
@Resource 注解要求提供一个 Bean 名称的属性，若该属性为空，则自动采用标注处的变量或方法名作为 Bean 的名称.
@Inject 和 @Autowired 注解一样也是按类型匹配注入的 Bean， 但没有 reqired 属性.
===========  建议使用 @Autowired 注解 ===================
```


 
 

### Spring AOP


 
 
#### 问题
```
代码混乱：越来越多的非业务需求(日志和验证等)加入后, 原有的业务方法急剧膨胀.  每个方法在处理核心逻辑的同时还必须兼顾其他多个关注点. 
代码分散: 以日志需求为例, 只是为了满足这个单一需求, 就不得不在多个模块（方法）里多次重复相同的日志代码. 如果日志需求发生变化, 必须修改所有模块. 
```
#### 使用动态代理解决上述问题
```
代理设计模式的原理: 使用一个代理将对象包装起来, 然后用该代理对象取代原始对象. 任何对原始对象的调用都要通过代理. 代理对象决定是否以及何时将方法调用转到原始对象上. 
```