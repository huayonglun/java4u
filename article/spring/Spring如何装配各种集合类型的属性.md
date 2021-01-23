### 首先在bean里面声明这些属性，并设置相应的更改器方法和访问器方法

	private Set<String> sets = new HashSet<>();
	private List<String> lists = new ArrayList<>();  
	private Properties properties = new Properties();  
	private Map<String, String> maps = new HashMap<> ();

### 接着在beans.xml做如下配置

	<property name="sets">
		<set>
			<value>set第一个</value>
			<value>set第二个</value>
			<value>set第三个</value>
		</set>
	</property>
	
	<property name="lists">
		<set>
			<value>list第一个</value>
			<value>list第二个</value>
			<value>list第三个</value>
		</set>
	</property>
	
	<property name="properties">  
		<props>  
			<prop key="p1">properties第一个</prop>  
			<prop key="p2">properties第二个</prop>  
			<prop key="p3">properties第三个</prop>  
		</props>  
	</property> 
	
	<property name="maps">  
		<map>  
			<entry key="key1" value="value1"></entry>  
			<entry key="key2" value="value2"></entry>  
			<entry key="key3" value="value3"></entry>  
		</map>  
	</property>

>对于map集合，entry里面的key属性和value属性都可以换成key-ref 和value-ref


### 测试方法
	/**
	 * 装载集合
	 */
	@Test
	public void getCollectionBySpring(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
		PersonService bean = (PersonService) context.getBean("personService");
		System.out.println("-----Set测试--------");
		for(String value: bean.getSets()){
			System.out.println(value);
		}
		
		System.out.println("-----List测试--------");
		for(String value: bean.getLists()){
			System.out.println(value);
		}
		
		System.out.println("-----Properties测试--------");
		for(Object key: bean.getProperties().keySet()){
			System.out.println(key + "=" + bean.getProperties().getProperty((String)key));
		}
		
		System.out.println("-----Map测试--------");
		for(Object key: bean.getMaps().keySet()){
			System.out.println(key + "=" + bean.getMaps().get(key));
		}
	}

### 输出

	-----Set测试--------
	set第一个
	set第二个
	set第三个
	-----List测试--------
	list第一个
	list第二个
	list第三个
	-----Properties测试--------
	p3=properties第三个
	p2=properties第二个
	p1=properties第一个
	-----Map测试--------
	key1=value1
	key2=value2
	key3=value3  





