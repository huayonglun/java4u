### 配置数据源


 	 <!-- 配置结点，可以使用占位符 -->
	 <context:property-placeholder location="classpath:jdbc.properties"/>
	 
	 <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
	    <property name="driverClassName" value="${driverClassName}"/>
	    <property name="url" value="${url}"/>
	    <property name="username" value="${username}"/>
	    <property name="password" value="${password}"/>
	     <!-- 连接池启动时的初始值 -->
		 <property name="initialSize" value="${initialSize}"/>
		 <!-- 连接池的最大值 -->
		 <property name="maxActive" value="${maxActive}"/>
		 <!-- 最大空闲值.当经过一个高峰时间后，连接池可以慢慢将已经用不到的连接慢慢释放一部分，一直减少到maxIdle为止 -->
		 <property name="maxIdle" value="${maxIdle}"/>
		 <!--  最小空闲值.当空闲的连接数少于阀值时，连接池就会预申请去一些连接，以免洪峰来时来不及申请 -->
		 <property name="minIdle" value="${minIdle}"/>
	 </bean>

- 当前类路径下，保存jdbc.properties文件。文件内容如下：

		driverClassName=org.gjt.mm.mysql.Driver
		url=jdbc\:mysql\://localhost\:3306/test?useUnicode\=true&characterEncoding\=UTF-8
		username=root
		password=root
		initialSize=1
		maxActive=500
		maxIdle=2
		minIdle=1

### 配置事务

- 配置事务时，需要在xml配置文件中引入用于声明事务的tx命名空间

		xmlns:tx="http://www.springframework.org/schema/tx"
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd

- 事物的配置方式有两种：注解方式和基于xml配置方式


- 使用注解方式配置事务

		<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	  	   <property name="dataSource" ref="dataSource"/>
	    </bean>
		<!--使用基于注解方式配置事务 -->  
		<tx:annotation-driven transaction-manager="txManager"/>
		
		<bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean">
			<property name="dataSource" ref="dataSource"/>
		</bean>

### 采用@Transactional注解方式使用事务

	package com.liuyong666.service.impl;
	
	import java.util.List;
	import javax.sql.DataSource;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.transaction.annotation.Transactional;
	import com.liuyong666.bean.Person;
	import com.liuyong666.service.PersonService;
	
	//表明业务方法受spring事务管理
	@Transactional
	public class PersonServiceBean implements PersonService {
		
		private JdbcTemplate jdbcTemplate;
		
		public void setDataSource(DataSource dataSource){
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
	
		public void save(Person person) {
			jdbcTemplate.update("insert into person(name) values(?)", new Object[]{person.getName()}, 
					new int[]{java.sql.Types.VARCHAR});
		}
	
		public void update(Person person) {
			jdbcTemplate.update("update  person set name=? where id=?", new Object[]{person.getName(),person.getId()}, 
					new int[]{java.sql.Types.VARCHAR, java.sql.Types.INTEGER});
		}
	
		public Person getPerson(Integer personid) {
			return (Person) jdbcTemplate.queryForObject("select * from person where id=?", 
					new Object[]{personid}, new int[]{java.sql.Types.INTEGER}, new PersonRowMapper());
		}
	
		public List<Person> getPersons() {
			return (List<Person>) jdbcTemplate.query("select * from person",new PersonRowMapper());
		}
	
		public void delete(Integer personid) {
			jdbcTemplate.update("delete from  person where id=?", new Object[]{personid}, 
					new int[]{java.sql.Types.INTEGER});
		}
	
	}

### Person

	package com.liuyong666.bean;
	
	public class Person {
		private Integer id;
		private String name;
		
		public Person() {
		}
		
		public Person(String name) {
			super();
			this.name = name;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}


​		
​	
	}

### PersonRowMapper

	package com.liuyong666.service.impl;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import org.springframework.jdbc.core.RowMapper;
	import com.liuyong666.bean.Person;
	
	public class PersonRowMapper implements RowMapper {
		//外部默认next
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			Person person = new Person(rs.getString("name"));
			person.setId(rs.getInt("id"));
			return person;
		}
	} 

### 测试

	public class PersonServiceTest {
		
		private static PersonService personService;
		
		@BeforeClass
		public static void setUpBeforeClass() throws Exception{
			try {
				ApplicationContext context = new ClassPathXmlApplicationContext(
						"beans.xml");
				personService = (PersonService) context.getBean("personService");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test
		public void save(){
			personService.save(new Person("小蓝"));
		}
		
		@Test
		public void getPerson(){
			Person p = personService.getPerson(1);
			System.out.println(p.getName());
		}
		
		@Test
		public void update(){
			Person p = personService.getPerson(1);
			p.setName("大明");
			personService.update(p);
		}
		
		@Test
		public void delete(){
			personService.delete(1);
		}
		
		@Test
		public void getBeans(){
			for(Person person : personService.getPersons()){
				System.out.println(person.getName());
			}
		}
	}



