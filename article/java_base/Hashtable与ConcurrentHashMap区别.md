## ConcurrentHashMap的特点

- 融合了Hashtable和HashMap二者的优势。
 + Hashtable是做了同步的
 + HashMap未考虑同步。
 + HashMap在单线程情况下效率较高
 + Hashtable在的多线程情况下，同步操作能保证程序执行的正确性

- Hashtable每次同步执行的时候都要锁住整个结构
<center>![Hashtable与ConcurrentHashMap的锁结构](http://i.imgur.com/AAFLQpc.jpg)
--
- ConcurrentHashMap正是为了解决这个问题而诞生的
- ConcurrentHashMap锁的方式是稍微细粒度的。 ConcurrentHashMap将hash表分为16个桶（默认值），诸如get,put,remove等常用操作只锁当前需要用到的桶。
- 试想，原来只能一个线程进入，现在却能同时16个写线程进入（写线程才需要锁定，而读线程几乎不受限制，之后会提到），并发性的提升是显而易见的。

## ConcurrentHashMap的迭代方式
- 使用了不同于传统集合的快速失败迭代器的另一种迭代方式，我们称为弱一致迭代器。
- 在这种迭代方式中，当iterator被创建后集合再发生改变就不再是抛出 ConcurrentModificationException，取而代之的是在改变时new新的数据从而不影响原有的数据，iterator完成后再将头指针替换为新的数据
- 这样iterator线程可以使用原来老的数据，而写线程也可以并发的完成改变，更重要的，这保证了多个线程并发执行的连续性和扩展性，是性能提升的关键。



