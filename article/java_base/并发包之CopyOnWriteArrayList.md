## CopyOnWriteArrayList

### 实现

　　线程安全， 读时无锁。

### CopyOnWriteArrayList()

　　大小为 0 的数组

### add(E)

　　无 synchronized 关键字

　　用 ReentrantLock 

　　新数组 newlen = len + 1

　　新对象放结尾

　　引用切换

### remove(E)

　　ReentrantLock 保证线程安全

　　新数组 newLen = len - 1

　　遍历 newLen 长度

- 若找到 equal ，后边元素前移，，引用切换，返回true
- 未找到，单个赋值给新数组

　　特殊处理最后一个元素

- 是否 equal ，是，删，引用切换，返回true
- 否，返回 false

　　与 ArrayList 相比，锁不同，复制没有调用 System.arrayCopy ，性能有下降

### get(int)

　　直接获取， 无锁保护

　　可能 脏数据 ，性能高

　　适合写少读多且脏数据影响不大

### iterator()

　　创建 CowIterator ，并保存当前数据的快照，不抛异常


## 总结

- CopyOnWriteArrayList 基于 ReentrantLock
- 保证增加、删除互斥
- 读不上锁， 保证读的性能
- 副作用：脏数据



