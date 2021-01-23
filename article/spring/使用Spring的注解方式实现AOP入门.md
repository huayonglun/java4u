### 配置文件

- 使用Spring进行面向切面（AOP）编程
- 要进行AOP编程，首先我们要在Spring的配置文件中引入aop命名空间：

		<beans xmlns="http://www.springframework.org/schema/beans"
		       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		       xmlns:context="http://www.springframework.org/schema/context" 
		       xmlns:aop="http://www.springframework.org/schema/aop"      
		       xsi:schemaLocation="http://www.springframework.org/schema/beans
		           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
		           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-2.5.xsd
		           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd">
		</beans>

### 使用方式

- spring提供两种切面使用方式，实际工作中我们可以选用其中一种：
	- 基于XML配置方式进行AOP开发
	- 基于注解方式进行AOP开发

### 基于注解方式声明切面

- 首先启动对@AspectJ注解的支持：

		<aop:aspectj-autoproxy/>

- 切面

		@Aspect
		public class MyInterceptor {
		
			//Spring只支持方法的拦截
			
			@Pointcut("execution (* com.liuyong666.service.impl.PersonServiceBean.*(..))")
			private void anyMethod(){}//声明一个切入点
			
			@Before("anyMethod()")
			public void doAccessCheck(){
				System.out.println("前置通知");
			}
		}

- beans.xml中添加

	       <bean id="myInterceptor" class="com.liuyong666.service.MyInterceptor"/>
	       <bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean"/>

- 测试
	
		@Test
		public void interceptorTest(){
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			PersonService personService = (PersonService) context.getBean("personService");
			personService.save("hhh");
		}

- 结果

>前置通知
我是save()方法！

