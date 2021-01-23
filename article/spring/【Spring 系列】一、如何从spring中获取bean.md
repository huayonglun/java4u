## 【Spring 系列】一、如何从spring中获取bean
- 导包
- spring要管理的类信息

		package com.liuyong666.service.impl;
	​	
		import com.liuyong666.service.PersonService;
		
		public class PersonServiceBean implements PersonService {
			@Override
			public void save(){
				System.out.println("我是save()方法");
			}
		
		}
- spring面向接口编程，该类对应的接口

		public interface PersonService {
	​		public abstract void save();
	​	}
- 配置beans.xml文件，文件在src目录下
```<bean id="personService" class="com.liuyong666.service.impl.PersonServiceBean"></bean>```

- 使用spring获取bean

		@Test
	​	public void getBeanBySpring(){
	​		//参数为数组表明可以加载多个配置文件
	​		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
	​		//面向接口编程，用接口引用实现它的实例对象
	​		PersonService bean = (PersonService) context.getBean("personService");
	​		bean.save();
	​	}



<p></p>
---
<center>

<div align="center" style="color: rgb(212, 137, 88); font-size: x-large; font-family: 楷体; ">欢迎关注微信公众号，技术，思维，心理，带给你认知的全方位成长。<br/>


![](https://ws1.sinaimg.cn/large/006tNbRwgy1fvibc07tuqj30hs07q0u7.jpg)


你的关注，就是对我最大的肯定，我会努力产出的，我们一起成长~ 

本文由 **永伦的小屋** 原创。
转载请**注明作者及出处**,本文作者为 永伦的小屋。

</div>
</center>