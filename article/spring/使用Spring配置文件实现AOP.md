### MyInterceptor

	package com.liuyong666.service;
	
	import org.aspectj.lang.ProceedingJoinPoint;
	/**
	 * 切面
	 *
	 */
	public class MyInterceptor {	
		public void doAccessCheck() {
			System.out.println("前置通知");
		}
	
		public void doAfterReturning() {
			System.out.println("后置通知");
		}
		
		public void doAfter() {
			System.out.println("最终通知");
		}
		
		public void doAfterThrowing() {
			System.out.println("例外通知");
		}
		
		public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
			System.out.println("进入方法");
			Object result = pjp.proceed();
			System.out.println("退出方法");
			return result;
		}
		
	}

- 可以看到，在这个切面里面，这只是一个普普通通的Java类，里面没有任何的注解。

### PersonServiceBean

	package com.liuyong666.service.impl;
	import com.liuyong666.service.PersonService;
	
	public class PersonServiceBean implements PersonService {


​	
​	
		public void save(String name) {
	//		throw new RuntimeException("我是例外");
			System.out.println("我是save()方法！");
	
		}
	
		public void update(String name, Integer personid) {
			System.out.println("我是update()方法！");
		}
	
		public String getPersonName(Integer personid) {
			System.out.println("我是getPersonName()方法！");
			return "666";
		}
	
	}

- 如果我们采用基于XML方式来开发AOP应用的话，我们是要在配置文件中对切面进行配置的。现在看一下切面该如何配置

### 基于XML配置方式声明切面——beans.xml

    <aop:aspectj-autoproxy/> 
    
    <bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean"/>
    
    <bean id="aspectbean" class="com.liuyong666.service.MyInterceptor"/>
    
    <aop:config>
    	<aop:aspect id="asp" ref="aspectbean">
        	<aop:pointcut id="mycut" expression="execution (* com.liuyong666.service.impl.PersonServiceBean.*(..))"/>
        	<aop:before pointcut-ref="mycut"  method="doAccessCheck"/>
        	<aop:after-returning pointcut-ref="mycut" method="doAfterReturning"/>
        	<aop:after pointcut-ref="mycut" method="doAfter"/>
        	<aop:after-throwing pointcut-ref="mycut" method="doAfterThrowing"/>
        	<aop:around pointcut-ref="mycut" method="doBasicProfiling"/>
    	</aop:aspect>
    </aop:config>

### 测试

	@Test
	public void interceptorTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) context.getBean("personService");
		personService.save("hhh");
	}

### 结果

	前置通知
	进入方法
	我是save()方法！
	后置通知
	最终通知
	退出方法


