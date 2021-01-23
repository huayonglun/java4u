### PersonServiceBean

	package com.liuyong666.service.impl;
	import java.util.List;
	import javax.sql.DataSource;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.transaction.annotation.Propagation;
	import org.springframework.transaction.annotation.Transactional;
	import com.liuyong666.bean.Person;
	import com.liuyong666.service.PersonService;


​	
	public class PersonServiceBean implements PersonService {
		private JdbcTemplate jdbcTemplate;
		
		public void setDataSource(DataSource dataSource) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		public void delete(Integer personid){
			//第二个语句sql故意插入错误语法，若为同一个事务，第一个语句会回滚，执行失败
			jdbcTemplate.update("delete from person where id=?", new Object[]{personid},
					new int[]{java.sql.Types.INTEGER});
			jdbcTemplate.update("delete from persons where id=3" );
		}
		
		public Person getPerson(Integer personid) {		
			return (Person)jdbcTemplate.queryForObject("select * from person where id=?", new Object[]{personid}, 
					new int[]{java.sql.Types.INTEGER}, new PersonRowMapper());
		}


​		
		@SuppressWarnings("unchecked")
		public List<Person> getPersons() {
			return (List<Person>)jdbcTemplate.query("select * from person", new PersonRowMapper());
		}
	
		public void save(Person person) {
			jdbcTemplate.update("insert into person(name) values(?)", new Object[]{person.getName()},
					new int[]{java.sql.Types.VARCHAR});
		}
	
		public void update(Person person) {
			jdbcTemplate.update("update person set name=? where id=?", new Object[]{person.getName(), person.getId()},
					new int[]{java.sql.Types.VARCHAR, java.sql.Types.INTEGER});
		}
	}


### XML配置

- 由于我们要拦截PersonServiceBean中的方法，因此我们需要在配置文件中配置信息，在配置文件中使用了AOP技术来拦截方法。

			<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		  	   <property name="dataSource" ref="dataSource"/>
		    </bean>
		    
			<aop:config>
			  	<aop:pointcut id="transactionPointcut" expression="execution(* com.liuyong666.service..*.*(..))"/>
			  	<aop:advisor advice-ref="txAdvice" pointcut-ref="transactionPointcut"/>
			</aop:config> 
			<tx:advice id="txAdvice" transaction-manager="txManager">
				  <tx:attributes>
				    <tx:method name="get*" read-only="true" propagation="NOT_SUPPORTED"/>
				    <tx:method name="*"/>
				  </tx:attributes>
			</tx:advice>    
			
			<bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean">
				<property name="dataSource" ref="dataSource"/>
			</bean>

- 这样spring就能对这个类进行事务管理。

### 测试数据库操作是否在同一事务中执行

	public void delete(Integer personid){
		//第二个语句sql故意插入错误语法，若为同一个事务，第一个语句会回滚，执行失败
		jdbcTemplate.update("delete from person where id=?", new Object[]{personid},
				new int[]{java.sql.Types.INTEGER});
		jdbcTemplate.update("delete from persons where id=3" );
	}

- 在第二条删除语句中，persons 表是不存在的
	- 如果两次update语句是在两个事务中执行，则第一条能成功执行，并且数据库中该id的记录已经被删除，
而第二条由于不存在该表不能正常删除。
	- 如果在同一事务中执行，由于第二条update出错，数据库中不能删除任何记录。

### 测试结果

	@Test
	public void delete(){
		personService.delete(2);
	}

- 程序报错，同时id=2的记录没有被删除，这说明我们的事务配置成功。
- 如果我们把配置文件中关于事务配置的信息给注释掉，再次测试，程序同样报错，但是id=2的记录被成功删除掉，这说明这两条update语句是在两个不同的事务中运行。

>在平时开发中，Spring团队建议使用注解的方式进行配置，这样配置文件显得精简，同时也会做到精确控制。 

