package indi.sword.spring._04aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
	代码混乱：越来越多的非业务需求(日志和验证等)加入后, 原有的业务方法急剧膨胀.  每个方法在处理核心逻辑的同时还必须兼顾其他多个关注点.
	代码分散: 以日志需求为例, 只是为了满足这个单一需求, 就不得不在多个模块（方法）里多次重复相同的日志代码. 如果日志需求发生变化, 必须修改所有模块.

	代理设计模式的原理: 使用一个代理将对象包装起来, 然后用该代理对象取代原始对象. 任何对原始对象的调用都要通过代理. 代理对象决定是否以及何时将方法调用转到原始对象上.
 */
public class ArithmeticCalculatorLoggingProxy {

	//要代理的对象
	private ArithmeticCalculator target;

	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		super();
		this.target = target;
	}

	//返回代理对象
	public ArithmeticCalculator getLoggingProxy(){
		ArithmeticCalculator proxy = null;

		ClassLoader loader = target.getClass().getClassLoader();
		Class [] interfaces = new Class[]{ArithmeticCalculator.class};
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy: 代理对象。 一般不使用该对象
			 * method: 正在被调用的方法
			 * args: 调用方法传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				String methodName = method.getName();
				//打印日志
				System.out.println("[before] The method " + methodName + " begins with " + Arrays.asList(args));

				//调用目标方法
				Object result = null;

				try {
					//前置通知
					result = method.invoke(target, args);
					//返回通知, 可以访问到方法的返回值
				} catch (NullPointerException e) {
					e.printStackTrace();
					//异常通知, 可以访问到方法出现的异常
				}

				//后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值

				//打印日志
				System.out.println("[after] The method ends with " + result);

				return result;
			}
		};

		/**
		 * loader: 代理对象使用的类加载器。
		 * interfaces: 指定代理对象的类型. 即代理代理对象中可以有哪些方法.
		 * h: 当具体调用代理对象的方法时, 应该如何进行响应, 实际上就是调用 InvocationHandler 的 invoke 方法
		 */
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);

		return proxy;
	}
}
