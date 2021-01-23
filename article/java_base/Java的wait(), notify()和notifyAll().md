## 官方解释

- wait(),notify()和notifyAll()都是java.lang.Object的方法：
- wait(): Causes the current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object.
- notify(): Wakes up a single thread that is waiting on this object's monitor.
- notifyAll(): Wakes up all threads that are waiting on this object's monitor.
## 作用
- 实现线程间阻塞(Blocking)
- 控制进程内调度(inter-process communication)

## 调用前提
- 必须先获得锁
- 必须锁定该对象

## 不获得锁会怎样
		public static void main(String[] args) throws InterruptedException {
	    	Object obj = new Object();
	    	obj.wait();
	    	obj.notifyAll();
		}
- 抛出java.lang.IllegalMonitorStateException的异常

## 不获得该对象的锁会怎样
		public static void main(String[] args) throws InterruptedException {
	    	Object obj = new Object();
	    	Object lock = new Object();
	    	synchronized (lock) {
	        	obj.wait();
	        	obj.notifyAll();
	    }
	}
- 抛出java.lang.IllegalMonitorStateException的异常

## 为什么必须获得该对象的锁
- 没有锁，wait和notify有可能会产生竞态条件(Race Condition)
- 所以，JVM通过在执行的时候抛出IllegalMonitorStateException的异常，来确保wait, notify时，获得了对象的锁，从而消除隐藏的Race Condition。

