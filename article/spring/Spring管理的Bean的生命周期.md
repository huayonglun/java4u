## Bean实例化之后的初始化工作

### 实现接口
- org.springframework.beans.factory.InitializingBean 接口指定一个单一的方法：

		void afterPropertiesSet() throws Exception;

- 因此，你可以简单地实现上述接口和初始化工作可以在 afterPropertiesSet() 方法中执行，如下所示：

		public class PersionServiceBean implements InitializingBean {
		   public void afterPropertiesSet() {
		      // do some initialization work
		   }
		}




### XML配置
- 在基于 XML 的配置元数据的情况下，你可以使用 init-method 属性来指定带有 void 无参数方法的名称。例如：

		<bean id="persionService"
			class="com.liuyong666.service.impl.PersionServiceBean"
			init-method="init"/>

- 下面是类的定义：

		public class PersionServiceBean {
		   public void init() {
		      // do some initialization work
		   }
		}

## Bean销毁之前的工作

### 实现接口
- org.springframework.beans.factory.DisposableBean 接口指定一个单一的方法：
		
		void destroy() throws Exception;

- 因此，你可以简单地实现上述接口并且结束工作可以在 destroy() 方法中执行，如下所示：

		public class PersionServiceBean implements DisposableBean {
   			public void destroy() {
      		// do some destruction work
   			}
		}




### XML配置
- 在基于 XML 的配置元数据的情况下，你可以使用 destroy-method 属性来指定带有 void 无参数方法的名称。例如：

		<bean id="persionService" 
			class="com.liuyong666.service.impl.PersionServiceBean" 
			destroy-method="destroy"/>	
	
- 下面是类的定义：

		public class PersionServiceBean {
		   public void destroy() {
		      // do some destruction work
		   }
		}

