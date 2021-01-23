

- 使用Spring，可以使用里面的控制反转把依赖对象交给Spring管理，并把依赖对象通过容器注入到组件内部。
- 那么在Spring里面，该如何把对象注入到组件内部呢？
创建一个PersonDao对象，并把这个对象注入到PersonServiceBean中

## 依赖注入使用

### PersonDaoBean

	package com.liuyong666.dao.impl;
	import com.liuyong666.dao.PersonDao;
	
	public class PersonDaoBean implements PersonDao{
		@Override
		public void add(){ 
	        System.out.println("执行PersonDaoBean里的add()方法"); 
	    }
	
	}

### 抽取接口
	package com.liuyong666.dao;
	
	public interface PersonDao {
	
		public abstract void add();
	
	}

### 说明
- 接口跟实现类不要放一块，接下来，如何将PersonDaoBean对象注入进PersonServiceBean，注入方式有两种：
	- 一种是构造器参数，
	- 另一种是通过属性的set方法注入。 
- 下面介绍通过属性的set方法我们该如何注入PersonDaoBean对象

### PersonServiceBean

	package com.liuyong666.service.impl;
	
	import com.liuyong666.dao.PersonDao;
	import com.liuyong666.service.PersonService;
	
	public class PersonServiceBean implements PersonService {
		private PersonDao personDao; 


​	    
		public PersonServiceBean(){
			System.out.println("我被实例化了");
		}
		
		public PersonDao getPersonDao() { 
	        return personDao; 
	    } 
	 
	    public void setPersonDao(PersonDao personDao) { 
	        this.personDao = personDao; 
	    } 
		
		@Override
		public void save(){
			personDao.add();
		}
	
	}

### 说明
- 可以看到，在服务层的这个类里面，我们并没有看到PersonDaoBean的身影，也就是说我们并不关心这个实现类是谁，我们通过PersonDao这个接口去引用注入进来的对象，再通过接口调用它的方法。这样的话，服务层的组件和DAO层的组件已经进行彻底的解耦了。
- 看下在beans.xml里如何为personDao这个属性注入PersonDaoBean这个bean呢？ 首先要把personDao这个bean配置在Spring中

### beans.xml

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd" >
	
	    <bean id="personDao" class="com.liuyong666.dao.impl.PersonDaoBean"></bean>
	    <bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean" >
	    	<property name="personDao" ref="personDao"></property>
	    </bean>


​	    
	</beans>

- property这个元素就是用于为属性注入值
	- name填写的是属性的名称
	- ref填写的值就是我们要注入的bean的名称。Spring会根据这个名称从Spring容器里面得到这个bean，因为这个bean默认在Spring容器实例化后就会被实例化，所以它在容器里面根据ref里的名称得到相应的bean，然后把这个bean通过反射技术就赋给了里面的属性。这就是Spring执行的过程。
- 我们看下我们注入的personDao这个bean是否能够成功注入呢？判断是否能够成功注入很简单，在PersonServiceBean.java里的save方法，调用了personDao.add()方法，
	- 如果注入不成功的话，就会出现空指针异常；
	- 如果能输出add方法里面打印的那句话，就代表注入是成功的

			@Test
			public void getBeanBySpring(){
				ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
				PersonService bean = (PersonService) context.getBean("personService");
				bean.save();
			}

### 说明
- 这时候，思考下控制反转，原先我们对象的创建是由应用本身创建的。现在对象的创建是由容器帮我们创建，并且由容器注入进来，这时候控制权发生了转移，这就是所谓的控制反转。


## 自定义类模拟Spring的注入功能，解剖Spring实现这个过程的内部细节

### PropertyDefinition

	package junit.test;
	/**
	 * 建一个java bean，用来存储property的信息，然后property的信息再通过一个集合存在bean里面
	 * @author Administrator
	 *
	 */
	public class PropertyDefinition {
		private String name;
		private String ref;
		
		public PropertyDefinition(String name, String ref) {
			this.name = name;
			this.ref = ref;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRef() {
			return ref;
		}
		public void setRef(String ref) {
			this.ref = ref;
		}
	}


### BeanDefinition

	package junit.test;
	
	import java.util.ArrayList;
	import java.util.List;
	
	public class BeanDefinition {
		private String id;
		private String className;
		private List<PropertyDefinition> propertys = new ArrayList<>();
		//通过一个集合，来存放property的信息 
		
		public BeanDefinition(String id, String className) {
			this.id = id;
			this.className = className;
		}


​		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public List<PropertyDefinition> getPropertys() {
			return propertys;
		}
		public void setPropertys(List<PropertyDefinition> propertys) {
			this.propertys = propertys;
		}
	}

### MyClassPathXMLApplicationContext1

	package junit.test;
	
	import java.beans.Introspector;
	import java.beans.PropertyDescriptor;
	import java.lang.reflect.Method;
	import java.net.URL;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	
	import org.dom4j.Document;
	import org.dom4j.Element;
	import org.dom4j.XPath;
	import org.dom4j.io.SAXReader;
	/**
	 * 自己的容器
	 * @author Administrator
	 *
	 */
	public class MyClassPathXMLApplicationContext1 {
		private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
		private Map<String, Object> sigletons = new HashMap<String, Object>();
		// 存放bean实例
		
		public MyClassPathXMLApplicationContext1(String filename){
			// 模拟内部的实现，首先要读取配置文件，可以用dom4j 
			this.readXML(filename);
			// 读取完bean之后，Spring要对bean进行实例化，怎么实现实例化呢？ 通过反射机制就很容易做到
			this.instanceBeans();
			this.injectObject();
		}
		
		/**
		 * 为bean对象的属性注入值
		 */
		private void injectObject() {
			for(BeanDefinition beandefinition : beanDefines){
				Object bean = sigletons.get(beandefinition.getId());
				if(bean != null){
					try {
						PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
						for(PropertyDefinition propertyDefinition: beandefinition.getPropertys()){
							for(PropertyDescriptor properdesc : ps){
								if(propertyDefinition.getName().equals(properdesc.getName())){
									Method setter = properdesc.getWriteMethod();//获取属性的setter方法
									if(setter != null){
										Object value = sigletons.get(propertyDefinition.getRef());
										setter.setAccessible(true);//允许访问private方法
										setter.invoke(bean, value);//把引用对象注入到属性
									}
									
								}
							}
						}
					} catch (Exception e) {
					}
				}
			}
			
		}
		/**
		 * 完成bean的实例化
		 */
		private void instanceBeans() {
			for(BeanDefinition beanDefinition : beanDefines){
				try {
					if(beanDefinition.getClassName()!=null && !"".equals(beanDefinition.getClassName().trim()))
						sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		/**
		 * 读取xml配置文件
		 * @param filename
		 */
		private void readXML(String filename) {
		       SAXReader saxReader = new SAXReader();   
		        Document document=null;   
		        try{
		         URL xmlpath = this.getClass().getClassLoader().getResource(filename);
		         document = saxReader.read(xmlpath);
		         Map<String,String> nsMap = new HashMap<String,String>();
		         nsMap.put("ns","http://www.springframework.org/schema/beans");//加入命名空间
		         XPath xsub = document.createXPath("//ns:beans/ns:bean");//创建beans/bean查询路径
		         xsub.setNamespaceURIs(nsMap);//设置命名空间
		         List<Element> beans = xsub.selectNodes(document);//获取文档下所有bean节点 
		         for(Element element: beans){
		            String id = element.attributeValue("id");//获取id属性值
		            String clazz = element.attributeValue("class"); //获取class属性值        
		            BeanDefinition beanDefine = new BeanDefinition(id, clazz);
		            XPath propertysub = element.createXPath("ns:property");
		            propertysub.setNamespaceURIs(nsMap);//设置命名空间
		            List<Element> propertys = propertysub.selectNodes(element);
		            for(Element property : propertys){
		            	String propertyName = property.attributeValue("name");
		            	String propertyRef = property.attributeValue("ref");
		            	System.out.println(propertyName + ";" + propertyRef);
		            	PropertyDefinition propertyDefinition = new PropertyDefinition(propertyName, propertyRef);
		            	beanDefine.getPropertys().add(propertyDefinition);
		            }
		            beanDefines.add(beanDefine);
		         }   
		        }catch(Exception e){   
		            e.printStackTrace();
		        }
		}
		/**
		 * 获取bean实例
		 * @param beanName
		 * @return
		 */
		public Object getBean(String beanName){
			return this.sigletons.get(beanName);
		}
	}


### TestSpring

	@Test
	public void getBeanByMine1(){
		MyClassPathXMLApplicationContext1 context = new MyClassPathXMLApplicationContext1("beans.xml");
		PersonService bean = (PersonService) context.getBean("personService");
		//通过自定义的Spring容器得到这个bean 
		bean.save();
	}

### 说明
- 运行单元测试代码，如果是空指针异常，则注入不成功；如果注入成功，则打印add里面的语句。
- 结果控制台输出的是：
		
		personDao;personDao
		我被实例化了
		执行PersonDaoBean里的add()方法



