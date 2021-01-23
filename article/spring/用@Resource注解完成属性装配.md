


- bean的注入方式有3种:
	- 第一种:使用构造器注入
	- 第二钟:使用属性setter方法注入
	- 第三种:使用Field注入（用于注解方式）


## 使用注解的方式完成bean的注入
- 在java代码中使用@Autowired或@Resource注解方式进行装配，这两个注解的区别是：
	- @Autowired 默认按类型装配。**是spring的api**。
	- @Resource默认按名称装配，当找不到与名称匹配的bean才会按类型装配。但一旦指定了name属性，就只能按名称装配了。**是jdk的api**。使用它可以不依赖spring的特性。

## @Resource

### 1.修改beans.xml

	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	           http://www.springframework.org/schema/context 
	           http://www.springframework.org/schema/context/spring-context-2.5.xsd">
	     <context:annotation-config/>
	
		<bean id="MypersonDao" class="com.liuyong666.dao.impl.PersonDaoBean"></bean>
		<bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean">
		</bean>
	
	</beans>

- 这个配置隐式注册了多个对注释进行解析处理的处理器:
	- AutowiredAnnotationBeanPostProcessor，
	- CommonAnnotationBeanPostProcessor，
	- PersistenceAnnotationBeanPostProcessor，
	- RequiredAnnotationBeanPostProcessor
- 注： @Resource注解在spring安装目录的lib\j2ee\common-annotations.jar
- **<context:annotation-config/>**  必不可少

### 使用@Resource来注入我们在beans.xml种声明的bean

- 用于字段上

		@Resource(name="MypersonDao") private PersonDao personDao; 
	
- 用于属性的setter方法上


		@Resource(name="MypersonDao")
	    public void setPersonDao(PersonDao personDao) { 
	        this.personDao = personDao; 
	    } 



