
---


### 为什么需要自动扫描

- 通过在classpath自动扫描方式把组件纳入spring容器中管理
- 之前我们都是使用XML的bean定义来配置组件。在一个稍大的项目中，通常会有上百个组件，如果这些组件采用xml的bean定义来配置，显然会增加配置文件的体积，查找以及维护起来也不太方便。

### 如何实现自动扫描

- Spring2.5为我们引入了组件自动扫描机制，他可以在类路径底下寻找标注了@Component,@Service,@Controller,@Repository注解的类，并把这些类纳入进spring容器中管理。
- 它的作用和在xml文件中使用bean节点配置组件时一样的。要使用自动扫描机制，我们需要打开以下配置信息

		<beans xmlns="http://www.springframework.org/schema/beans"
		       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		       xmlns:context="http://www.springframework.org/schema/context"
		       xsi:schemaLocation="http://www.springframework.org/schema/beans
		           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
		           http://www.springframework.org/schema/context 
		           http://www.springframework.org/schema/context/spring-context-2.5.xsd">
			 <!-- 
		     <context:annotation-config/>
			  -->
				
			     <!-- 让Spring自动扫描和管理Bean -->
				 <!-- 当前标签包括了 "<context:annotation-config/>"，所以当前标签存在时，配置注解的标签可以不要-->
			     <context:component-scan base-package="com.liuyong666"/>
		</beans>

- 其中base-package为需要扫描的包（含子包）

### 各个注解的使用
- @Service用于标注业务层组件
- @Controller用于标注控制层组件（如struts中的action）
- @Repository用于标注数据访问组件，即DAO组件
- 而@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注

### 组件标注

- 业务层组件
	
		@Service("newName")
		public class PersonServiceBean implements PersonService {}


- 数据访问组件

		@Repository
		public class PersonDaoBean implements PersonDao {}

- 测试方法

	
		/**
		 * 使用spring自动扫描包获取bean
		 */
		@Test
		public void getBeanBySpringComponentScan(){
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
			/*
			 * getBean的默认名称是类名（头字母小写），如果想自定义，可以@Service(“aaaaa”)这样来指定，
			 * 这种bean默认是单例的，如果想改变，可以使用@Service(“aaaaa”) @Scope(“prototype”)来改变
			 */
			PersonService personService = (PersonService) context.getBean("newName");
			PersonService personService1 = (PersonService) context.getBean("newName");
			System.out.println(personService == personService1);//true

			PersonDao personDao = (PersonDao) context.getBean("personDaoBean");
			System.out.println(personService);
			System.out.println(personDao);
		}	

### 特别操作

- getBean的默认名称是类名（头字母小写），
- 如果想自定义，可以@Service(“aaaaa”)这样来指定，这种bean默认是单例的，
- 如果想改变，可以使用@Service(“aaaaa”) @Scope(“prototype”)来改变。
- 可以使用以下方式指定初始化方法和销毁方法（方法名任意）

	    @PostConstruct
	    public void init() {
	    	//getBean之后
	    }
	    
	    @PreDestroy
	    public void destory() {
	    	//上下文关闭之前
	    }


<p></p>
---
