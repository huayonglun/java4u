## @Autowired注解

1. Autowire 默认是按类型匹配  @ Autowire
2. 如果你要修改 Autowire，按名称匹配可以 @Autowired @Qualifier("MypersonDao") ,如果在beans.xml中没有发现有这个名字的bean,则会有异常
3. @Autowired(required=true) @Qualifier("MypersonDao"), 表示这个bean必须注入值,不然报错

### 用于字段上

	@Autowired 
	private PersonDao  personDao;

### 用于属性的setter方法上

    @Autowired
    public void setPersonDao(PersonDao personDao) { 
        this.personDao = personDao; 
    } 

### 扩展

- @Autowired注解是按类型装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它的required属性为false,如果我们向使用按名称装配，可以结合@Qualifier注解一起使用。如下：

		@Autowired @Qualifier("MypersonDao")
		private PersonDao personDao;


## 自动装配
- 自动装配了解，不推荐使用
- 4种类型的自动装配
	- byName——按名称装配，可以根据属性的名称，在容器中寻找跟该属性名相同的bean，如果没有找到，即属性值为null
	- byType——按类型装配，可以根据属性的类型，在容器中寻找跟该类型匹配的bean，如果没有找到，即属性值为null
	- constructor——与byType的方式类似，不同之处在于它应用于构造器参数。如果容器中没有找到与构造器参数类型一致的bean，那么将会抛出异常。
	- autodetect——通过bean类的自省机制(introspection)来决定是使用constructor还是byType方式进行自动装配。**如果发现默认的构造器，那么将使用byType**。

## 总结

- 使用注解方式允许更细粒度的自动装配，可以选择性地标注某一个属性来对其应用自动装配。
	- Spring容器默认禁用注解装配
	- xml配置中启用注解，利用context命名空间中<context:annotation-config>元素

>注入依赖对象可以采用手工装配或自动装配，在实际应用中建议使用手工装配，因为自动装配会产生未知情况，开发人员无法预见最终的装配结果。


