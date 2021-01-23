

## 概述

并发包包括：

- 高性能的线程安全的集合对象
- 原子操作类
- 避免并发时资源冲突的的Lock及Condition

## ConcurrentHashMap

### 实现方式

<!--more-->

　　线程安全的HashMap实现

### ConcurrentHashMap(initialCapacity , loadfactor, concurrencyLevel)

　　参数默认 initialCapacity 为 16，loadfactor 为 0.75， concurrencyLevel 为16

　　基于以下方式计算 ssize

	int sshift = 0;
	int ssize = 1;
	while(ssize < concurrencyLevel){
		++sshiftl;
		ssize <<= 1;
	}

　　在 concurrencyLevel 为 16 的情况下，最终计算出的 ssize 为 16，并使用此 ssize 作为参数传入 Segment 的 newArray 方法，创建大小为 16 的 Segment 对象数组，接着采用如下方法计算 cap 变量的值：

	int c = initialCapacity / ssize;
	if(c * ssize < initialCapacity)
		++c;
	int cap = 1;
	while(cap < c)
		cap <<= 1;

### put(key, value)

　　没加 synchronized 关键字
　　

- 判断 value 是否为 null
	- 是，抛异常
	- 否，根据 key 的 hashCode 获取 Segment，调用 Segment 的 put
		- lock
		- 个数 +1，判断 threshold
		- 若超过大小，扩大两倍，重 hash，转移

- 接下来与 HashMap 一致
	- hash 值与数组长度 -1 按位与，得到位置，
		- 有相同 key 覆盖 value
		- 无，建新 HashEntry，赋值给对应位置，构成链表，释放锁

　　默认允许 16 个线程并发无阻塞操作集合对象，尽可能减少并发时的阻塞现象

### remove(Object key)

　　key.hash ——> Segment ——> 调用 remove

- remove 加锁，hash 值和数组大小 -1 按位与，得到位置，遍历对象及 next对象，找到和传入的 hash 值相等的 hash值，以及 key 和传入的 key equals 的对象。
	- 未找到，返回null
	- 找到，重建删除元素之前的 HashEntry

- 释放锁

### get(Object key)

　　hash ——> Segment ——> 调 get

- get 判断对象数组是否为 0
	- 是，返回 null
	- 否，hash 与 len - 1 按位与，获得 HashEntry，遍历，找 hash 等及 key equal 的对象
		- 若找到，
			- 若 value 为 null，调 readValueUnderlock
				- lock
				- 返回 value
				- 释放锁
			- 该步骤，防止了并发读到默认值
			- 若 value 不为 null ，返回 value

> 仅寻找到对象 value 为 null 时，才上锁，即在读数据时，大部分情况下没有才用锁

> 对象数组变量 volatile，对象数组大小发生改变，读操作可看到最新数组大小。

### containsKey(Object key)

　　同 get，没加锁

### keySet().interator()

　　不加锁，不抛异常







