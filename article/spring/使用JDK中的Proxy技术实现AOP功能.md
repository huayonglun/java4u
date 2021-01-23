

### PersonService接口

	package com.liuyong666.service;
	
	public interface PersonService {
		
		public void save(String name);
		
		public void update(String name, Integer personid);
		
		public String getPersonName(Integer personid);
	
	}

### 实现类PersonServiceBean

	package com.liuyong666.service.impl;
	
	import com.liuyong666.service.PersonService;
	/**
	 * 业务需求：
	 * 		1. 拦截所有业务方法
	 * 		2. 判断用户是否有权限
	 * 				如果用户为null，没有权限调用业务方法
	 * 				如果用户不为null，则有权限调用
	 * @author Administrator
	 *
	 */
	public class PersonServiceBean implements PersonService {
		
		private String user = null;


​		
​		
		public String getUser() {
			return user;
		}
	
		public PersonServiceBean() {
			super();
		}
	
		public PersonServiceBean(String user){
			this.user = user;
		}
	
		@Override
		public void save(String name) {
			System.out.println("我是save()方法！");
	
		}
	
		@Override
		public void update(String name, Integer personid) {
			System.out.println("我是update()方法！");
		}
	
		@Override
		public String getPersonName(Integer personid) {
			System.out.println("我是getPersonName()方法！");
			return "666";
		}
	
	}

### 分析

- 为什么要用到AOP技术呢？试想一下，如果上边的三个方法，要通过权限验证才能使用的话，那么要在每个方法里面都加上“if() {}"来判断吗？没错，这是可行的，但这里仅针对一个类，如果是N个类呢？大家可能想到，拦截器，但如果不是web项目呢？
- 原理图如下：

            客户端————>代理对象————>目标对象

- 客户端要调用目标对象之前，先要通过代理对象，再调用目标对象，代理对象实现了目标对象的所有方法。

### 代理工厂类JDKProxyFactory

	package com.liuyong666.aop;
	
	import java.lang.reflect.InvocationHandler;
	import java.lang.reflect.Method;
	import java.lang.reflect.Proxy;
	
	import com.liuyong666.service.impl.PersonServiceBean;
	
	public class JDKProxyFactory implements InvocationHandler {
		
		private Object targetObject;
		
		public Object createProxyInstance(Object targetObject){
			
			this.targetObject = targetObject;
			
			/*
			 * 1. this.targetObject.getClass().getClassLoader() --目标对象的类装载器
			 * 2. this.targetObject.getClass().getInterfaces()  --代理对象要实现的接口
			 * 3. this  --这个类实例本身
			 */


​			
			return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
					this.targetObject.getClass().getInterfaces(), this);
		}


​	
		/**
		 * 代理对象会执行这个方法
		 * @param proxy   代理对象
		 * @param method  被拦截到的方法
		 * @param args	  方法的输入参数
		 * @return 
		 * @throws Throwable
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {//环绕通知
			PersonServiceBean bean = (PersonServiceBean) this.targetObject;
			Object result = null; 
			if(bean.getUser()!=null){
				//..... advice()-->前置通知
				try {
					result = method.invoke(targetObject, args);
					// afteradvice() -->后置通知
				} catch (RuntimeException e) {
					//exceptionadvice()--> 例外通知
				}finally{
					//finallyadvice(); -->最终通知
				}
			}
			return result;
		}


​		
​	
	}



- 上面的类使用JDK中的Proxy技术实现AOP功能。当我们要调用目标对象PersonServiceImpl的时候，先要通过JDKProxyFactory的createProxyInstance创建代理对象，这个代理对象会执行invoke的方法，里面判断是否有权限，有的话，再通过method.invoke，执行目标对象的方法。


### 测试

	package junit.test;
	
	import org.junit.Test;
	
	import com.liuyong666.aop.JDKProxyFactory;
	import com.liuyong666.service.PersonService;
	import com.liuyong666.service.impl.PersonServiceBean;
	
	public class AOPTest {


​		
		@Test
		public void proxyTest(){
			JDKProxyFactory factory = new JDKProxyFactory();
			PersonService service = (PersonService) factory.createProxyInstance(new PersonServiceBean("xxx"));
			service.save("666");
		}
	
	}

- 上面的测试方法表明，如果传入了user,即user不为空，那么它就有权限执行save方法，打印出“我是save()方法”
- 如果不传入user,即<code>PersonService service = (PersonService)factory.createProxyInstance(new PersonServiceBean());</code>，那么，在代理对象的invoke方法中判断出没权限，不会扫描目标对象的save方法，所以没任何的输出。


