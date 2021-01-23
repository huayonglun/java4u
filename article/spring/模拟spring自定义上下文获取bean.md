

## 模拟spring自定义上下文获取bean

- 利用dom4j解析
- 设置两个域，一个List存放BeanDefinition，一个Map存放实例化对象的映射
- 构造器传配置文件进去，依次执行两个方法
	- 方法1，读取配置文件，把解析出的BeanDefinition放入List中
	- 方法2，完成bean的实例化，把实例化对象的映射放入Map中
- 定义获取bean实例的方法，从Map中根据实例名返回Object对象

			import java.net.URL;
			import java.util.ArrayList;
			import java.util.HashMap;
			import java.util.List;
			import java.util.Map;
			
			import org.dom4j.Document;
			import org.dom4j.Element;
			import org.dom4j.XPath;
			import org.dom4j.io.SAXReader;
			/**
			 * 自己的容器
			 * @author Administrator
			 *
			 */
			public class MyClassPathXMLApplicationContext {
				private List<BeanDefinition> beanDefines = new ArrayList<BeanDefinition>();
				private Map<String, Object> sigletons = new HashMap<String, Object>();
				
				public MyClassPathXMLApplicationContext(String filename){
					this.readXML(filename);
					this.instanceBeans();
				}
				/**
				 * 完成bean的实例化
				 */
				private void instanceBeans() {
					for(BeanDefinition beanDefinition : beanDefines){
						try {
							if(beanDefinition.getClassName()!=null && !"".equals(beanDefinition.getClassName().trim()))
								sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				/**
				 * 读取xml配置文件
				 * @param filename
				 */
				private void readXML(String filename) {
				       SAXReader saxReader = new SAXReader();   
				        Document document=null;   
				        try{
				         URL xmlpath = this.getClass().getClassLoader().getResource(filename);
				         document = saxReader.read(xmlpath);
				         Map<String,String> nsMap = new HashMap<String,String>();
				         nsMap.put("ns","http://www.springframework.org/schema/beans");//加入命名空间
				         XPath xsub = document.createXPath("//ns:beans/ns:bean");//创建beans/bean查询路径
				         xsub.setNamespaceURIs(nsMap);//设置命名空间
				         List<Element> beans = xsub.selectNodes(document);//获取文档下所有bean节点 
				         for(Element element: beans){
				            String id = element.attributeValue("id");//获取id属性值
				            String clazz = element.attributeValue("class"); //获取class属性值        
				            BeanDefinition beanDefine = new BeanDefinition(id, clazz);
				            beanDefines.add(beanDefine);
				         }   
				        }catch(Exception e){   
				            e.printStackTrace();
				        }
				}
				/**
				 * 获取bean实例
				 * @param beanName
				 * @return
				 */
				public Object getBean(String beanName){
					return this.sigletons.get(beanName);
				}
			}

