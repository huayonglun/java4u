## HashMap

- HashSet()
	- loadFactor默认0.75，threshold为12
	- 并创建一个大小为16的Entry对象数组
	- 可调用另外两个构造器控制初始容量值，loadFactor
	- 数组大小由如下决定：

			int capacity = 1;
			while(capacity < initialCapacity)
				capacity <<= 1;

	- capacity才是创建的Entry对象数组的大小，new HashMap(5,0.6)，则设loadFactor为0.6，并创建一个大小为8的Entry对象数组，threshold则为4( 8 * 0.6 = 4 )

<!--more-->

- put(key, value)
	- key为null,获取第一个Entry对象，并基于Entry的next遍历
		- 找到key为null，更新value，返回
		- 未找到，增加Entry。
			- 增加时获取数组首个Entry：e，并创建Entry对象，key为null，value为传入对象，next为e。
			- 若数组len >= threshold，扩容为2倍，扩容前对所有元素重新hash，并填充数组，最后重设置threshold。
	- key不为null，获取key的hashCode，然后再对此hashCode作hash操作，hash完成后，将hash值与对象数组len按位与，得到key要存储的数组位置。
		- hash冲突：不同key找到相同存储位置，通过调用Entry对象next遍历链表的方式。

- get(Object key)
	- 与put一样，根据key是否为null分别处理
		- key为null --> 首个Entry --> next遍历
		- key非null --> hash和按位与 --> 找到位置 --> 找到Entry --> next遍历

- remove(Object key)
	- 类似get，找到key,当前元素更新为next元素
	- 未找到，遍历链表

- containsKey(key)
	- 调用getEntry，与get基本相同，返回是否为null

- keySet()
	- 用来遍历Map对象，返回KeySet对象实例
	- 调用iterator返回keyIterator，遍历中有增删会抛异常


### HashMap要点
- HashMap采用数组方式存储key,value构成的Entry对象，无容量限制
- HashMap基于key hash寻找Entry对象存放到数组的位置，对hash冲突采用链表的方式来解决
- 插入扩容，扩容时重hash，并复制对象到新的数组中
- 非线程安全

## TreeMap

- 实现
	- 支持排序的Map实现，不同于HashMap

- TreeMap()
	- 将comparator属性为null，若希望控制存储顺序，使用带Comparator参数的构造器

- put(key, value)
	- 判断root是否为null
		- 是，建新Entry，并赋给root
		- 否，判断有无实现Comparator
			- 有，红黑树遍历key，相等替换，不等找到null为止
				- 未找到相同的key，创建新的Entry，将其parent设置成上面所寻找到的元素并根据和parent key比较的情况来设置parent的left或right属性
			- 无，判断key是否为null
				- 是，1.抛NullPointerException，2.并将key造型为Comparable，进行与上面相同过程
				- 否，执行2

> 基于红黑树实现，一定要有key比较，要么传入Comparator实现，要么key对象实现Comparable接口

- get(Object)
	- 红黑树查找，从根开始往下比，一直找到等key，返回其value
	- 和put同样的处理方式，如未传入Comparator实现，当传入的Object为null，抛异常

- remove(O)
	- getEntry，并将Entry从红黑树上删除，并重新调整树上的相关结点。

- containsKey(O)
	- 同getEntry，但containsKey直接判断返回的Entry是否为null

- KeySet()
	- 返回TreeMap的内部类KeySet对象的实例，iterator的遍历从根开始，基于红黑树顺序完成。


### TreeMap要点

- TreeMap基于红黑树实现，无容量限制
- TreeMap非线程安全

## 小结

### 各集合类适用场景

	List  		重复
	Set			不重复
	Map			key-value
	ArrayList	通过位置读
	LinkedList	头尾操作及插入指定位置
	Vector		线程安全的ArrayList
	Stack		线程安全的LIFO
	HashSet		对排序无要求的非重复
	TreeSet		要排序的非重复
	HashMap		key-value存取
	TreeMap		排序，key-value



