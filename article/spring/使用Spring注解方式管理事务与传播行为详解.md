

### 遇到异常事务是否会回滚

- Spring容器如果碰到运行期异常(uncheck exception)，也就是 RuntimeException 会回滚.

		public void delete(Integer id) {
				jdbcTemplate.update("delete from person where id=?", new Object[]{id},   
		                new int[]{java.sql.Types.INTEGER});
				
				throw new RuntimeException("运行期例外");
		}
		@Test
		public void testDelete(){   
		        ps.delete(1);   
		}   

- 如果是用户定义的例外则不会回滚(check exception)，这个特性是默认的：

		@Override
		public void delete(Integer id) throws Exception {
			jdbcTemplate.update("delete from person where id=?", new Object[]{id},   
		            new int[]{java.sql.Types.INTEGER});
		}
		@Test
		public void testDelete(){   
			try {
				ps.delete(1);
			} catch (Exception e) {
				e.printStackTrace();
			}   
		} 


### 设置回滚的方式

- 我们可以使用注解来改变回滚的默认方式：
	1. 默认情况发生RuntimeException要回滚，改变为不回滚
		
			@Transactional(noRollbackFor=RuntimeException.class)
			public void delete(Integer personid) {
				jdbcTemplate.update("delete from person where id=?", new Object[]{personid},
						new int[]{java.sql.Types.INTEGER});
				throw new RuntimeException("运行期例外");
			}

	2. 发生非RuntimeException不回滚，改变为回滚

			@Transactional(rollbackFor=Exception.class)

	3. 如果不希望业务方法开启事务，比如select等方法可以

			@Transactional(propagation=Propagation.NOT_SUPPORTED)

	4. 设置事务的超时时间

			@Transactional(timeout=500)  /*依赖与底层数据库的实现*/

### 事务的传播属性

- REQUIRED：业务方法需要在一个事务中运行。如果方法运行时，已经处在一个事务中，那么加入到该事务，否则为自己创建一个新的事务。

- NOT_SUPPORTED：声明方法不需要事务。如果方法没有关联到一个事务，容器不会为它开启事务。如果方法在一个事务中被调用，该事务会被挂起，在方法调用结束后，原先的事务便会恢复执行。

- REQUIRESNEW：属性表明不管是否存在事务，业务方法总会为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务会被挂起，新的事务会被创建，直到方法执行结束，新事务才算结束，原先的事务才会恢复执行。

- MANDATORY：该属性指定业务方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果业务方法在没有事务的环境下调用，容器就会抛出例外。

- SUPPORTS：这一事务属性表明，如果业务方法在某个事务范围内被调用，则方法成为该事务的一部分。如果业务方法在事务范围外被调用，则方法在没有事务的环境下执行。

- Never：指定业务方法绝对不能在事务范围内执行。如果业务方法在某个事务中执行，容器会抛出例外，只有业务方法没有关联到任何事务，才能正常执行。

- NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按REQUIRED属性执行.它使用了一个单独的事务， 这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。它只对DataSourceTransactionManager事务管理器起效。外部事务的回滚会导致整个事务的回滚。

### 数据库系统提供了四种事务隔离级

- 数据库系统提供了四种事务隔离级别供用户选择。不同的隔离级别采用不同的锁类型来实现，在四种隔离级别中，Serializable的隔离级别最高，Read Uncommited的隔离级别最低。大多数据库默认的隔离级别为Read Commited，如SqlServer，当然也有少部分数据库默认的隔离级别为Repeatable Read ，如Mysql

	- Read Uncommited：读未提交数据(会出现脏读,不可重复读和幻读)。
	- Read Commited：读已提交数据(会出现不可重复读和幻读)
	- Repeatable Read：可重复读(会出现幻读)
	- Serializable：串行化


- 脏读：一个事务读取到另一事务未提交的更新新据。
- 不可重复读：在同一事务中，多次读取同一数据返回的结果有所不同。换句话说就是，后续读取可以读到另一事务已提交的更新数据。相反，“可重复读”在同一事务中多次读取数据时，能够保证所读数据一样，也就是，后续读取不能读到另一事务已提交的更新数据。
- 幻读：一个事务读取到另一事务已提交的insert数据。


