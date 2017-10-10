package indi.sword.spring._02ref;

import indi.sword.spring._01helloworld.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/*
	创建 Bean 后置处理器
		Bean 后置处理器允许在调用初始化方法前后对 Bean 进行额外的处理.
		Bean 后置处理器对 IOC 容器里的所有 Bean 实例逐一处理, 而非单一实例. 其典型应用是: 检查 Bean 属性的正确性或根据特定的标准更改 Bean 的属性.
		对Bean 后置处理器而言, 需要实现BeanPostProcessor接口.
		在初始化方法被调用前后, Spring 将把每个 Bean 实例分别传递给上述接口的以下两个方法:
		postProcessAfterInitialization、postProcessBeforeInitialization

	添加 Bean 后置处理器后 Bean 的生命周期
		Spring IOC 容器对 Bean 的生命周期进行管理的过程:
		通过构造器或工厂方法创建 Bean 实例 .
		为 Bean 的属性设置值和对其他 Bean 的引用 .
		将 Bean 实例传递给 Bean 后置处理器的 postProcessBeforeInitialization 方法 .
		调用 Bean 的初始化方法  将 Bean 实例传递给 Bean 后置处理器的 postProcessAfterInitialization方法
		Bean 可以使用了  当容器关闭时, 调用 Bean 的销毁方法 .

 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	//该方法在 init 方法之前被调用
	//可以工作返回的对象来决定最终返回给 getBean 方法的对象是哪一个, 属性值是什么
	/**
	 * @param arg0: 实际要返回的对象
	 * @param arg1: bean 的 id 值
	 */
	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg1.equals("boy"))
			System.out.println("postProcess___Before___Initialization..." + arg0 + "," + arg1);
		return arg0;
	}

	//该方法在 init 方法之后被调用
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg1.equals("boy")){
			System.out.println("postProcess___After___Initialization..." + arg0 + "," + arg1);
			User user = (User) arg0;
			user.setUserName("李大齐");
		}
		return arg0;
	}

}
