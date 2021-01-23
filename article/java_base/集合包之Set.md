## HashSet

- 实现：
	- 为了不允许元素重复，基于HashMap实现

- HashSet()
	- 创建HashMap对象


- add(E)
	- 调用map的put(O,O)，需要增加的元素作为map中的key，value传入一个已创建的final Object对象

<!--more-->

- remove(E)
	- 调用map的remove(E)

- contains(E)
	- 调用map.containsKey(E)

- iterator()
	- 调用map.keySet的iterator方法

> HashSet不支持get(int)获取指定位置的元素，只能自行通过iterator方法来获取

### HashSet要点
- 基于HashMap实现，无容量限制
- HashSet是非线程安全

## TreeSet

- 实现
	- 区别于HashSet支持排序，TreeSet基于TreeMap实现


- TreeSet()
	- 创建TreeMap对象

- add(E)
	- 调用TreeMap.put(O,O)

- remove(E)
	- 调用TreeMap.remove(E)

- iterator()
	- 调navigableKeySet的iterator方法

> TreeSet提供了排序方面的支持。例如，传入Comparator实现，descendingSet及descendingIterator实现

### TreeSet要点
- 基于TreeMap实现，支持排序
- TreeSet是非线程安全的



