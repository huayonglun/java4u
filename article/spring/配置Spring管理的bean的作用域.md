## 配置Spring管理的bean的作用域

### .singleton
- 在每一个spring IoC容器中一个bean定义只有一个对象实例。默认情况下会在容器启动时初始化bean，但我们可以指定bean节点的lazy-init = "true"来延迟初始化bean，这时候，只有第一次获取bean才会初始化bean。如下：

		<bean id="persionService" 
			class="com.liuyong666.service.impl.PersionServiceBean"  
			lazy-init = "ture"/>


- 如果想对所有bean都应用延迟初始化，可以在节点beans设置default-lazy-init = "ture" ,如下：

		<beans default-lazy-init = "ture".../>

### .prototype
- 每次从容器获取bean都是新的对象。
- 在第一次获取bean时初始化

		<bean id="persionService" 
			class="com.liuyong666.service.impl.PersionServiceBean" 
			scope="prototype"/>

### .request

### .session

### .global session



