### Springʵ��AOP��4�ַ�ʽ
 
####  AOP���������:
```
1.֪ͨ(Advice):
֪ͨ������������ʲô�Լ���ʱʹ�á�����������Ҫ��ɵĹ����ͺ�ʱ��Ҫִ�����������

2.���ӵ�(Joinpoint):
�����ܹ�Ӧ��֪ͨ��һ����ʱ��������Щ��ʱ�����������ӵ㣬���緽��������ʱ���쳣���׳�ʱ�ȵȡ�

3.�����(Pointcut)
֪ͨ(Advice)����������Ҫ�����ġ����¡���ʱ�䣬��ô�����Ͷ����ˡ����¡������ĵص㣬����ĳ����򷽷������ƣ�Spring���������Ƿ������������ʽ��ָ��
    @Pointcut("execution(public * com.bjsxt.service..*.add(..))")
    public void myMethod(){};  

4.����(Aspect)
֪ͨ(Advice)�������(Pointcut)��ͬ���������(Aspect)��ʱ�䡢�ص��Ҫ�����ġ����¡�
    @Aspect
    @Component
    public class LogInterceptor {}

5.����(Introduction)
�����������������е�������µķ���������(Spring�ṩ��һ������ע��Ĺ��ܣ�

6.Ŀ��(Target)
����֪ͨ�Ķ������û��AOP,��ô�����߼���Ҫ�����������߼�������AOP֮��������ֻ��ע�Լ�Ҫ�����£�AOP�������������£�

7.����(proxy)
Ӧ��֪ͨ�Ķ�����ϸ���ݲμ����ģʽ����Ĵ���ģʽ

8.֯��(Weaving)
������Ӧ�õ�Ŀ������������µĴ������Ĺ��̣�֯��һ�㷢�������¼���ʱ��:
(1)����ʱ����һ�����ļ�������ʱ����֯�룬����Ҫ����ı������ſ������ĵ�������AspectJ��֯�������
(2)�����ʱ��ʹ�������ClassLoader��Ŀ���౻���ص�����֮ǰ��ǿ����ֽڴ���
(3)����ʱ�����������е�ĳ��ʱ�̱�֯��,SpringAOP���������ַ�ʽ֯������ģ�ԭ��Ӧ����ʹ����JDK�Ķ�̬������

```
#### Spring�ṩ��4��ʵ��AOP�ķ�ʽ��
```
1.����Ļ��ڴ����AOP
2.@AspectJע������������
3.��POJO����
4.ע��ʽAspectJ����
```

####  Spring֧���������͵�֪ͨ(Advice)��
```
Before(ǰ)  org.apringframework.aop.MethodBeforeAdvice
After-returning(���غ�) org.springframework.aop.AfterReturningAdvice
After-throwing(�׳���) org.springframework.aop.ThrowsAdvice
Arround(��Χ) org.aopaliance.intercept.MethodInterceptor
Introduction(����) org.springframework.aop.IntroductionInterceptor
```
ֵ��˵��������Χ֪ͨArround��������AOP Alliance�еĽӿڶ���Ķ���Spring,��Χ֪ͨ�൱��ǰ֪ͨ�����غ�֪ͨ���׳���֪ͨ�Ľ�ϣ���˵�е���ȫ�壿�ðɣ��ҿ��պͿ����ˣ���������֪ͨ��ô���һ�û������������������ʱ������

�ⶫ����ô�棿��ô�������裺
1.����֪ͨ��ʵ���⼸���ӿڣ������еķ���ʵ����
2.�����е��֪ͨ�ߣ���Spring�����ļ���������Щ��Ϣ
3.ʹ��ProxyFactoryBean�����ɴ���

�������������������ϵľ;ٸ�˯�������Ӱ�:

����дһ���ӿڽ�Sleepable,����һ��ţX�Ľӿ�,���о���˯�������Ķ���������ʵ�ָýӿڣ�������������ػ�ѡ����������ߣ�
```
package test.spring.aop.bean
public interface Sleepable{
    void sleep(); 
}
```
Ȼ��дһ��Human�࣬��ʵ��������ӿ�
```
package test.spring.aop.bean
public Human implements Sleepable{ 
   /*����Ī�Ǹ����˲�ࣿ
    *����˯��˯�ıȽϺ�֮�������ʲôҲ��������*/
   public void sleep(){
      System.out.println("˯���ˣ���������������");
   }
}
```
���ˣ��������ǣ�����˯��ǰ��Ҫ��Щ���������ģ�����������Ѵ��·���ʧ�ߵ��˻�Ҫ�԰���ҩʲô��,������Щ�����봿���˯����һ��ҵ���߼����ǲ���ɵģ������

��Щ����ȫ�����뵽sleep�����У��ǲ�����Υ��һְ���أ�����ʱ�����Ǿ���ҪAOP�ˡ�

��дһ��SleepHelper�࣬�����������˯���ĸ�������,��AOP������˵����Ӧ����֪ͨAdvice�ˣ�������Ҫʵ������Ľӿ�
```
package test.spring.aop.bean;
import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class SleepHelper implements MethodBeforeAdvice,AfterReturningAdvice{
    public void before(Method mtd, Object[] arg1, Object arg2)
            throws Throwable {
        System.out.println("ͨ�������˯��֮ǰҪ���·���");
    }

    public void afterReturning(Object arg0, Method arg1, Object[] arg2,
            Object arg3) throws Throwable {
        System.out.println("�𴲺�Ҫ�ȴ��·���");
    }
}
```
Ȼ����spring�����ļ��н�������:
```
<bean id="sleepHelper" class="test.spring.aop.bean.SleepHelper">
</bean>
```
OK!���ڴ���֪ͨ�Ĺ����������.

�ڶ����ǽ������ã����Ǻ����˵��۵Ĳ�������������ô�ȵ��죬Spring�ְѶ�����������ļ���ĳ�����Ϊɶ������usr���ַ���أ�

����Ҫ����������һ���е�,��˵�е�ı�ʾ��ʽ��Spring���кü��֣����ǳ��õ�ֻ�����֣�1.ʹ��������ʽ 2.ʹ��AspectJ���ʽ AspectJ�Ҳ��Ǻ���Ϥ(��Ҳ����Ϥ�� or ��ͨ����),�һ���ϰ����������ʽ

Springʹ��org.springframework.aop.support.JdkRegexpMethodPointcut������������ʽ�е�
```
<bean id="spleepPointcut" class="org.springframework.aop.support.JdkRegexpMethodPointcut">
  <property name="pattern" value=".*sleep"/>
</bean>
```
pattern����ָ����������ʽ����ƥ�����е�sleep����

�����(Pointcut)�����Ƕ����˹��·����ĵص㣬���й��·�����ʱ���Լ�����Ҫ�Ĺ��µ�����,����֪ͨ(Advise)�ˣ�������Ҫ��֪ͨ(Advise)�������(Pointcut)�������������Ҫʹ�õ�֪ͨ����:
org.springframework.aop.support.DefaultPointcutAdvisor
```
<bean id="sleepHelperAdvisor" class="org.springframework.aop.support.DefaultPointcutAdvisor">
     <property name="advice" ref="sleepHelper"/>
     <property name="pointcut" ref="sleepPointcut"/>
</bean>
```
������֪ͨ��������ɣ��������õ���ProxyFactoryBean�������������
```
<bean id="humanProxy" class="org.springframework.aop.framework.ProxyFactoryBean">
     <property name="target" ref="human"/>
     <property name="interceptorNames" value="sleepHelperAdvisor" />
     <property name="proxyInterfaces" value="test.spring.aop.bean.Sleepable" />
</bean>
```
ProxyFactoryBean��һ���������ǿ��԰���ת��ΪproxyInterfaces��ָ����ʵ�ָ�interface�Ĵ������:
```
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.spring.aop.bean.Sleepable;

public class Test {
    public static void main(String[] args){
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sleepable sleeper = (Sleepable)appCtx.getBean("humanProxy");
        sleeper.sleep();
    }
}
```
�������в��������
ͨ�������˯��֮ǰҪ���·���
˯����~��������������!
�𴲺�Ҫ�ȴ��·���

OK!����������Ҫ�Ľ�������������������ò���е㸴�ӣ������������е��֪ͨ,Spring�ṩ��һ���Զ�����Ĺ��ܣ������е��֪ͨ�Զ�����ƥ�䣬�޸������ļ�����:
```
 <bean id="sleepHelper" class="test.spring.aop.bean.SleepHelper">
  </bean>
  <bean id="sleepAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
    <property name="advice" ref="sleepHelper"/>
    <property name="pattern" value=".*sleep"/>
  </bean>
  <bean id="human" class="test.spring.aop.bean.Human">
  </bean>
  <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
```
ִ�г���
```
public class Test {

    public static void main(String[] args){
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sleepable sleeper = (Sleepable)appCtx.getBean("human");
        sleeper.sleep();
    }
}
```
�ɹ���������ǰ��һ��!
ֻҪ����������org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator(���ո�ȥ�ģ���̫����)����Ϊ����ƥ���bean�Զ���������

������������Ҫ�кܶ๤��Ҫ��,�и��򵥵ķ�ʽ��?�У�

һ�ַ�ʽ��ʹ��AspectJ�ṩ��ע��:
```
package test.mine.spring.bean;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
@Aspect
public class SleepHelper {
    public SleepHelper(){
        
    }
    
    @Pointcut("execution(* *.sleep())")
    public void sleeppoint(){}
    
    @Before("sleeppoint()")
    public void beforeSleep(){
        System.out.println("˯��ǰҪ���·�!");
    }
    
    @AfterReturning("sleeppoint()")
    public void afterSleep(){
        System.out.println("˯����Ҫ���·���");
    }
}
```
��@Aspect��ע������ʶ����,ע�ⲻҪ����©�ˣ�����Spring���������ʱ����Ҳ�����,@Pointcutע��ָ�����е㣬@Before��@AfterReturningָ��������ʱ��֪ͨ��ע�����Ҫ��ע���д����е������

Ȼ��������Spring�����ļ����µ㹦��,����������AOP��XML�����ռ���������schema
�����ռ�:
```
xmlns:aop="http://www.springframework.org/schema/aop"
schema����:
http://www.springframework.org/schema/aop
http://www.springframework.org/schema/aop/spring-aop-2.0.xsd
```
Ȼ����������ǩ:
```
<aop:aspectj-autoproxy/> �������Spring���ܹ��Զ�ɨ�豻@Aspect��ע��������
```
��������У��ܼ򵥷����ˣ�
```
public class Test {

    public static void main(String[] args){
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sleepable human = (Sleepable)appCtx.getBean("human");
        human.sleep();
    }
}
```
���������������һ�ֳ��õ�ʵ��AOP�ķ�ʽ:ʹ��Spring�����崿���POJO����

ǰ�������õ���<aop:aspectj-autoproxy/>��ǩ,Spring��aop�������ռ����滹�ṩ������������Ԫ��:
```
<aop:advisor> ����һ��AOP֪ͨ��
<aop:after> ��֪ͨ
<aop:after-returning> ���غ�֪ͨ
<aop:after-throwing> �׳���֪ͨ
<aop:around> ��Χ֪ͨ
<aop:aspect>����һ������
<aop:before>ǰ֪ͨ
<aop:config>��������Ԫ�أ�������<beans>���ֶ���
<aop:pointcut>����һ���е�
```
������AOP��ǩ��ʵ��˯���������:
���벻�䣬ֻ���޸������ļ�,����AOP���ü���:
```
<aop:config>
    <aop:aspect ref="sleepHelper">
    <aop:before method="beforeSleep" pointcut="execution(* *.sleep(..))"/>
    <aop:after method="afterSleep" pointcut="execution(* *.sleep(..))"/>
    </aop:aspect>
</aop:config>
```
�꣡

OK~~�����Ͼ���ô���˰ɣ�Ҫ���úû��ö��������ڣ���������AspectJ~�����Ǹ��棡

