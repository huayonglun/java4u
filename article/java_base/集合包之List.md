

- 集合包
	- Collection：多个单对象
	- Map：键值对

## ArrayList
- 实现：
	- 数组实现
- add(E)
	- 更新minCapacity为已有数量+1
	- 和Object数组比大小
		- 若minCapacity大，将当前数组赋给一个数组对象，新容量为old * 1.5 + 1
		- 若数组新容量大小仍小于minCapacity，更新minCapacity为新容量值
	- 调用Arrays.CopyOf生成新数组对象
	- 添加元素E进入数组

- remove(E)
	- 判断E是否为null
		- 是，找出null，调用fastRemove完成对象的删除
		- 否，equals比较，调用fastRemove

- remove(int)
	- 相比较remove(E)，多了数组范围检测
	- 少了对象位置查找，性能更好

- get(int)
	- 获取单个对象
		- 先进行数组范围检测
		- 然后直接返回

- iterator()
	- 遍历对象
	- AbstractList的实现
	- hasNext()
	- next()
		- 获取的modCount与当前的modCount比较
			- 不等，集合大小发生改变，抛ConcurrentModificationException
			- 等
				- 若游标cursor >= 集合大小，抛NoSuchElementException
				- 若当前cursor >= 数组长度，抛ConcurrentModificationException
				- cursor + 1，返回元素

- contains(E)
	- 判断对象是否存在
	- 遍历，E为null，==判断；E不为null，E.equals判断

- indexOf(E)
	- 从前向后遍历

- lastIndexOf(E)
	- 从后向前遍历

### ArrayList要点
- 数组实现，无容量限制
- 插入扩容，删除不减容(想缩小可调用trimToSize())
- 查找遍历，非null用equals找
- 非线程安全

## LinkedList

- 实现
	- 双向链表
	- Entry：element，next，privious

- 创建
	- 创建属性均为null的Entry对象，并赋值给全局的header属性
	- 在执行构造器时，LinkedList将header的next及previous都指向header，以形成双向链表所需的闭环

- add(E)
	- 创建Entry，next指向header，previous指向header.previous
	- 不用考虑扩容及复制数组
	- 但每次+1需创建Entry，修改相邻两个元素的属性

- remove(E)
	- 遍历，删除当前，置element，previous及next为null

- get(int)
	- 比ArrayList复杂
	- 判断int合法性
	- 判断是否小于LinkedList的一半
		- 是，从头开始，利用next
		- 否，从尾开始，利用previous
- iterator()
	- 创建ListItr对象，负责保存cursor位置
	- next()，若出现增删，抛异常
	- 也可用hasPrevious()和previous来遍历

### LinkedList要点
- 双向链表实现
- 插入创建新Entry，切换前后引用
- 查找，遍历链表
- 删除，遍历链表，找到删除
- 非线程安全


## Vector
- 实现方式
	- Object数组
- Vector()
	- 大小为10，capacityIncrement = 0
- add(E)
	- 加synchronized关键字
	- 扩容策略与ArrayList不同
		- 若capInc > 0 ，扩大为size + capInc
		- 否则，扩大为 size * 2
	- 比ArrayList更可控
- remove(E)
	- ArrayList上+ synchronized

- get(int)
	- 在ArrayList上+ synchronized

- iterator()
	- 同ArrayList

- contains(E)
	- 方法中调用的indexOf有同步关键字

- Vector除扩大数组方法和ArrayList不同及线程安全外，其他实现完全相同

### Vector要点
- Vector是基于synchronized实现的线程安全的ArrayList
- 扩容机制不同，可通过传入capacityIncrement控制容量补充

## Stack
- 实现方式
	- 继承于Vector，并实现LIFO的弹出及压入操作

- push
	- 调用Vector中add完成

- pop
	- 调用peek获取，同时删除数组最后一个

- peek
	- 获取当前Object数组大小，并获取最后一个元素

### 要点
- Stack基于Vector，支持FIFO



