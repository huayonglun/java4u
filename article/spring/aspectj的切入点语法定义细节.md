

### 表达式分析

expression=**"execution(\* com.liuyong666.service.impl.PersonServiceBean.\*(..))"**

- 第一个星号表示 拦截方法的返回值 为任意
	- 如果为 java.lang.String ，表示只拦截 返回值为String 的方法
	- 如果为 void ，则表示只拦截 返回值为 void 的方法
	- 如果为 !void ，则表示只拦截 返回值为 非void的方法
- 如果我们只拦截方法第一个参数为String,剩下的参数类型任意，则可以
　　expression="execution(java.lang.String com.liuyong666.service.impl.PersonServiceBean.*(java.lang.String,..))"



