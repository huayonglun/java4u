### 使用场景

- 通过JDK中的Proxy技术实现AOP功能，目标对象是实现了接口的，用JDK的Proxy技术可以使其生成代理对象，但有些情况下，目标对象是没有实现接口的，那么我们就要通过另外的方法来实现了。
- 首先需要导入cglib-nodep-x.x_x.jar这个包。

### 步骤

分三步：

1.       创建Enhancer

2.       设置目标对象为超类

3.       创建对象

这样就产生了一个目标对象的子类


### PersonServiceBean

	package com.liuyong666.service.impl;
	
	import com.liuyong666.service.PersonService;
	/**
	 * 业务需求：
	 * 		1. 拦截所有业务方法
	 * 		2. 判断用户是否有权限
	 * 				如果用户为null，没有权限调用业务方法
	 * 				如果用户不为null，则有权限调用
	 * @author Administrator
	 *
	 */
	public class PersonServiceBean （这里不必实现接口）{
		
		private String user = null;


​		
​		
		public String getUser() {
			return user;
		}
	
		public PersonServiceBean() {
			super();
		}
	
		public PersonServiceBean(String user){
			this.user = user;
		}


​	
		public void save(String name) {
			System.out.println("我是save()方法！");
	
		}
	
		public void update(String name, Integer personid) {
			System.out.println("我是update()方法！");
		}
	
		public String getPersonName(Integer personid) {
			System.out.println("我是getPersonName()方法！");
			return "666";
		}
	
	}

### CGlibProxyFactory

	package com.liuyong666.aop;
	import java.lang.reflect.Method;
	import com.liuyong666.service.impl.PersonServiceBean;
	import net.sf.cglib.proxy.Callback;
	import net.sf.cglib.proxy.Enhancer;
	import net.sf.cglib.proxy.MethodInterceptor;
	import net.sf.cglib.proxy.MethodProxy;
	
	public class CGlibProxyFactory implements MethodInterceptor {
	
		private Object targetObjeact;//代理的目标对象
		
		public Object createProxyInstance(Object targetObjeact){
			
			this.targetObjeact = targetObjeact;
			
			Enhancer enhancer = new Enhancer();//该类用于生成代理对象
			enhancer.setSuperclass(this.targetObjeact.getClass());//非final
			enhancer.setCallback(this);//设置回调对象为本身
			return enhancer.create();//创建代理对象
		}
	
		/**
		 * 当代理对象的业务方法被调用的时候，会回调这个方法
		 * @param proxy   代理对象
		 * @param method  被拦截到的方法
		 * @param args	  方法的输入参数
		 * @param methodProxy  方法的代理对象
		 * @return 
		 * @throws Throwable
		 */
		@Override 
		public Object intercept(Object proxy, Method method, Object[] args,
				MethodProxy methodProxy) throws Throwable {
	
			PersonServiceBean bean = (PersonServiceBean) this.targetObjeact;
			Object  result = null;
			if(bean.getUser() != null){
				result = methodProxy.invoke(targetObjeact, args);//把方法的调用委派给目标对象
			}


​			
			return result;
		}
	}

- 可以在调用方法前，后，以及出现例外时处理，无论抛不抛异常都会执行的方法，分别为前置通知，后置通知，例外通知，最终通知
- 还有一种叫做环绕通知，允许在目标类方法调用前后织入横切逻辑
	

### 测试

	@Test
	public void proxyTestByCGlib(){
		CGlibProxyFactory factory = new CGlibProxyFactory();
		PersonServiceBean service = (PersonServiceBean) factory.createProxyInstance(new PersonServiceBean("xxx"));
		service.save("6666");
	}

- 也一样会打印出“我是save()方法”。
-  spring框架的一些实现就是通过类似这样的方法实现的，根据判断目标对象有没实现接口，决定用哪个方法生成代理对象。



### AOP概念


- Aspect(切面)：指横切性关注点的抽象即为切面，它与类很相似，只是两者的关注点不一样，类是对物体特征的抽象，而切面是横切性关注点的抽象。
- Joinpoint(连结点)：所谓连结点是指那些被拦截到的点。在spring中，这些点指的是方法，因为spring只支持方法类型的连结点，实际上joinpoint还可以是field或类构造器。
- Pointcut(切入点)：所谓切入点是指我们要对那些joinpoint进行拦截的定义。
- Advice(通知)：所谓通知是指拦截到joinpoint之后要做的事情就是通知。通知分为前置通知，后置通知，异常通知，最终通知，环绕通知。
- Target(目标对象)：代理的目标对象。
- Weave(织入)：指将aspects应用到target对象并导致proxy对象创建的过程为织入。
- Introduction(引入)：再不修改类代码的前提下，Introduction可以在运行期为类动态添加一些方法或Field.


