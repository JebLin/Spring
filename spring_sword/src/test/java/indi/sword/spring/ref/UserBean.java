package indi.sword.spring.ref;


import indi.sword.spring.helloworld.Car;
import indi.sword.spring.helloworld.User;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.List;

/*	  通过调用静态工厂方法创建 Bean
		  调用静态工厂方法创建 Bean是将对象创建的过程封装到静态方法中. 当客户端需要对象时, 只需要简单地调用静态方法, 而不同关心创建对象的细节.
		  要声明通过静态方法创建的 Bean, 需要在 Bean 的 class 属性里指定拥有该工厂的方法的类, 同时在 factory-method 属性里指定工厂方法的名称. 最后, 使用 <constrctor-arg> 元素为该方法传递方法参数.
   		  实例工厂方法: 将对象的创建过程封装到另外一个对象实例的方法里. 当客户端需要请求对象时, 只需要简单的调用该实例方法而不需要关心对象的创建细节.

	   实现 FactoryBean 接口在 Spring IOC 容器中配置 Bean
			Spring 中有两种类型的 Bean, 一种是普通Bean, 另一种是工厂Bean, 即FactoryBean.
			工厂 Bean 跟普通Bean不同, 其返回的对象不是指定类的一个实例, 其返回的是该工厂 Bean 的 getObject 方法所返回的对象
*/
public class UserBean implements FactoryBean<User> {

	/**
	 * 返回的 bean 的实例
	 */
	@Override
	public User getObject() throws Exception {
		User user = new User();
		user.setUserName("abc");
		user.setWifeName("ABC");

		List<Car> cars = new ArrayList<>();
		cars.add(new Car("ShangHai", "BuiKe", 180, 300000));
		cars.add(new Car("ShangHai", "CRUZE", 130, 150000));

		user.setCars(cars);
		return user;
	}

	/**
	 * 返回的 bean 的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	/**
	 * 返回的 bean 是否为单例的
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
