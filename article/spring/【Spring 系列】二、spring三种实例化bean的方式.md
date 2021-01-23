

## 【Spring 系列】二、spring三种实例化bean的方式

- beans.xml中配置

    ```xml
     <!-- 三种实例化bean的方式 -->
     
      <!-- 1.使用类构造器实例化 -->
     <bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean"></bean>
     
     <!-- 2.使用静态工厂方法实例化 -->
     <bean id="personService2" class="com.liuyong666.service.impl.PersonServiceBeanFactory" factory-method="createPersonServiceBean"></bean>
     
     <!-- 3.使用实例工厂方法实例化 -->
     <bean id="personServiceFactory" class="com.liuyong666.service.impl.PersonServiceBeanFactory"></bean>
     <bean id="personService3" factory-bean="personServiceFactory" factory-method="createPersonServiceBean2"></bean>
    ```

   - PersonServiceBeanFactory类
     ```java
     package com.liuyong666.service.impl;
     public class PersonServiceBeanFactory {
     	
     	public static PersonServiceBean createPersonServiceBean(){
     		return new PersonServiceBean();
     	}
     	
     	public PersonServiceBean createPersonServiceBean2(){
     		return new PersonServiceBean();
     	}
     }
     ```

   - 测试类

   ```java
     public class TestSpring {
     		/**
     		 * 使用spring框架获取bean
     		 */
     		@Test
     		public void getBeanBySpring(){
     			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
     			PersonService bean = (PersonService) context.getBean("personService");
     			bean.save();
     		}
     		/**
     		 * 使用静态工厂方法获取bean
     		 */
     		@Test
     		public void getBeanByStaticFactory(){
     			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
     			PersonService bean = (PersonService) context.getBean("personService2");
     			bean.save();
     		}
     		
   
   
     	/**
     	 * 使用实例工厂方法获取bean
     	 */
     	@Test
     	public void getBeanByInstantceFactory(){
     		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
     		PersonService bean = (PersonService) context.getBean("personService3");
     		bean.save();
     	}
     
     }
   ```


<p></p>

---
<center>



<div align="center" style="color: rgb(212, 137, 88); font-size: x-large; font-family: 楷体; ">欢迎关注微信公众号，技术，思维，心理，带给你认知的全方位成长。<br/>



![](https://ws1.sinaimg.cn/large/006tNbRwgy1fvibc07tuqj30hs07q0u7.jpg)


你的关注，就是对我最大的肯定，我会努力产出的，我们一起成长~ 

本文由 **永伦的小屋** 原创。
转载请**注明作者及出处**,本文作者为 永伦的小屋。

</div>
</center>