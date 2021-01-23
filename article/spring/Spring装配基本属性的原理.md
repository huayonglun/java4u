## 依赖注入对象方式二

- 使用内部bean，但该bean不能被其他bean使用


	    <bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean">
	    	<property name="personDao">
	    		<bean class="com.liuyong666.dao.impl.PersonDaoBean"></bean>
	    	</property>
	    </bean>
- 两种方式的优缺点比较
	- 使用ref的方式，引用的bean可以被多个bean引用
	- 而采用内部bean的方式，内部bean只能为那个bean里面那那个属性使用。

## 前面都是注入依赖对象，那么如何注入基本类型呢？
- 例如，在PersonServiceBean里面添加一个String类型的name属性和Integer类型的id属性，可以这样注入值：


    	<property name="name" value="huayonglun"></property>
    	<property name="id" value="22"></property>

### Stpring容器是怎么获取这些值，并转化成相应的类型的呢？
- 首先我们在PropertyDefinition里面多加一个属性value.同时，在读取property里面的属性的值的时候读取value的值。
- 然后在为bean对象的属性注入值的时候要做一下处理。

### PropertyDefinition

	/**
	 * 建一个java bean，用来存储property的信息，然后property的信息再通过一个集合存在bean里面
	 * @author Administrator
	 *
	 */
	public class PropertyDefinition {
		private String name;
		private String ref;
		private String value;


​		
​		
		public PropertyDefinition(String name, String ref) {
			this.name = name;
			this.ref = ref;
		}
	
		public PropertyDefinition(String name, String ref, String value) {
			this.name = name;
			this.ref = ref;
			this.value = value;
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
	
		public String getValue() {
			return value;
		}
	
		public void setValue(String value) {
			this.value = value;
		}
		
	}

### MyClassPathXMLApplicationContext1

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
	            	String propertyValue = property.attributeValue("value");
	            	System.out.println(propertyName + ";" + propertyRef);
	            	PropertyDefinition propertyDefinition = new PropertyDefinition(propertyName, propertyRef, propertyValue);
	            	beanDefine.getPropertys().add(propertyDefinition);
	            }
	            beanDefines.add(beanDefine);
	         }   
	        }catch(Exception e){   
	            e.printStackTrace();
	        }
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
									Object value  = null;
									if(propertyDefinition.getRef() != null && !"".equals(propertyDefinition.getRef().trim())){
										value = sigletons.get(propertyDefinition.getRef());
										
									}else{
										//BeanUtils的工具类把字符串类型的数据转换成指定类型的数据
										//convert需要的值通过属性定义获取，需要的类型通过属性描述符获取
										value = ConvertUtils.convert(propertyDefinition.getValue(), properdesc.getPropertyType());
									}
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

### beans.xml

    <bean id="personDao" class="com.liuyong666.dao.impl.PersonDaoBean"></bean>
    <bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean">
    	<property name="personDao" ref="personDao"></property>
    	<property name="name" value="huayonglun"></property>
    	<property name="id" value="22"></property>
    </bean>

- 测试自定义容器，得到以下输出

>我是save()方法<br/>
>name:huayonglun,id:22



