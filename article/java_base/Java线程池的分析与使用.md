## 为什么需要使用线程池

- 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗
- 提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行
- 提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程次可以进行统一的分配，调优和监控。

## 线程池的创建
- 我们可以通过ThreadPoolExecutor来创建一个线程池。
- `new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, milliseconds,runnableTaskQueue, handler);`
- 需要的几个参数：
 + corePoolSize（线程池的基本大小）：
     - 当提交一个任务到线程池时，线程池会创建一个线程来执行任务，
     - 即使其他空闲的基本线程能够执行新任务也会创建线程，
     - 等到需要执行的任务数大于线程池基本大小时就不再创建。
     - 如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程。
 + runnableTaskQueue（任务队列：用于保存等待执行的任务的阻塞队列。
     - ArrayBlockingQueue：基于数组，有界，FIFO
     - LinkedBlockingQueue：基于链表，FIFO，吞吐量高于ArrayBlockingQueue，静态工厂方法Executors.newFixedThreadPool()使用了这个队列
     - SynchronousQueue：不存储元素，插入操作须等到另一个线程调用移除操作，吞吐量高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列
     - PriorityBlockingQueue：一个具有优先级的无限阻塞队列

 + maximumPoolSize（线程池最大大小）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。无界任务队列无效。
 + ThreadFactory：用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。
 + RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。
     - 以下是JDK1.5提供的四种策略。
         + AbortPolicy：直接抛出异常。
         + CallerRunsPolicy：只用调用者所在线程来运行任务。
         + DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
         + DiscardPolicy：不处理，丢弃掉。
     - 当然也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
 + keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。
 + TimeUnit（线程活动保持时间的单位）：
     - 可选的单位有天（DAYS），小时（HOURS），
     - 分钟（MINUTES），毫秒(MILLISECONDS)，
     - 微秒(MICROSECONDS, 千分之一毫秒)和毫微秒(NANOSECONDS, 千分之一微秒)。

## 向线程池提交任务
- execute
 + 无返回值，无法判断任务是否被线程执行成功

---
	threadsPool.execute(new Runnable() {
	        @Override
	        public void run() {
	
	        }
	});
- submit
 + 返回future，以此判断任务是否执行成功
 + 通过future的get方法来获取返回值，get方法会阻塞直到任务完成
 + get(long timeout, TimeUnit unit)方法则会阻塞一段时间后立即返回，这时有可能任务没有执行完

---
	Future<Object> future = executor.submit(harReturnValuetask);
	try {
		 Object s = future.get();
	} catch (InterruptedException e) {
		// 处理中断异常
	} catch (ExecutionException e) {
		// 处理无法执行任务异常
	} finally {
		// 关闭线程池
		executor.shutdown();
	}

## 线程池的关闭
- shutdown
 + 设置线程池状态为SHUTDOWN，中断暂停任务的线程
- shutdownNow
 + 设置线程池状态为STOP，中断正在执行或暂停任务的线程
 + 返回等待执行任务的列表
- 都是遍历线程池中的工作线程，逐个调用线程的interrupt方法来中断线程，所以无法响应中断的任务可能永远无法终止
- 通常调用shutdown来关闭线程池，如果任务不一定要执行完，则可以调用shutdownNow

## 线程池的分析
- 工作流程如图:
![线程池工作流程图](http://i.imgur.com/V4f7nHh.jpg)
- 提交一个新任务到线程池时，线程池处理流程如下：
 + 首先线程池判断**基本线程池**是否已满？没满，创建一个工作线程来执行任务。满了，则进入下个流程。
 + 其次线程池判断**工作队列**是否已满？没满，则将新提交的任务存储在工作队列里。满了，则进入下个流程。
 + 最后线程池判断**整个线程池**是否已满？没满，则创建一个新的工作线程来执行任务，满了，则交给饱和策略来处理这个任务。
- 源码实现

---
	public void execute(Runnable command) {
	    if (command == null){
	       throw new NullPointerException();
		}
	    //如果线程数小于基本线程数，则创建线程并执行当前任务 
	    if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
	    //如线程数大于等于基本线程数或线程创建失败，则将当前任务放到工作队列中。
	        if (runState == RUNNING && workQueue.offer(command)) {
	            if (runState != RUNNING || poolSize == 0)
	                      ensureQueuedTaskHandled(command);
	        }
	    //如果线程池不处于运行中或任务无法放入队列，并且当前线程数量小于最大允许的线程数量，则创建一个线程执行任务。
	        else if (!addIfUnderMaximumPoolSize(command))
	       	 //抛出RejectedExecutionException异常
	            reject(command);	 // is shutdown or saturated
	    }
	}
- 工作线程：
 + 线程池创建线程时，会将线程封装成工作线程Worker，Worker在执行完任务后，还会无限循环获取工作队列里的任务来执行。
 + 我们可以从Worker类的run方法里看到这点

---
	public void run() {
	     try {
	           Runnable task = firstTask;
	           firstTask = null;
	            while (task != null || (task = getTask()) != null) {
	                    runTask(task);
	                    task = null;
	            }
	      } finally {
	             workerDone(this);
	      }
	} 

## 合理的配置线程池
- 任务特性
 + 任务的性质：
     - CPU密集型任务
     - IO密集型任务
     - 混合型任务
 + 任务的优先级：高，中，低
 + 任务的执行时间：长，中，短
 + 任务的依赖性：是否依赖其他系统资源，如数据库连接
- 如何为不同特性的任务配置线程池
 + 性质不同，用不同规模的线程池分开处理
     - CPU密集型任务配置尽可能小的线程，如配置Ncpu+1个线程的线程池。
     - IO密集型任务则由于线程并不是一直在执行任务，则配置尽可能多的线程，如2*Ncpu
     - 混合型的任务，如果可以拆分，则将其拆分成一个CPU密集型任务和一个IO密集型任务，只要这两个任务执行的时间相差不是太大，那么分解后执行的吞吐率要高于串行执行的吞吐率，如果这两个任务执行时间相差太大，则没必要进行分解。我们可以通过Runtime.getRuntime().availableProcessors()方法获得当前设备的CPU个数。
 + 优先级不同，用优先级队列PriorityBlockingQueue来处理
     - 让优先级高的任务先得到执行
     - 需要注意的是如果一直有优先级高的任务提交到队列里
     - 那么优先级低的任务可能永远不能执行
 + 执行时间不同，可以交给不同规模的线程池来处理，或者也可以使用优先级队列，让执行时间短的任务先执行。
 + 依赖数据库连接池的任务，因为线程提交SQL后需要等待数据库返回结果，如果等待的时间越长CPU空闲时间就越长，那么线程数应该设置越大，这样才能更好的利用CPU。
 + 建议使用有界队列。
     - 有界队列能增加系统的稳定性和预警能力，可以根据需要设大一点，比如几千

## 线程池的监控
- 通过线程池提供的参数进行监控。线程池里有一些属性在监控线程池的时候可以使用
  + taskCount：线程池需要执行的任务数量
  + completedTaskCount：线程池在运行过程中已完成的任务数量，小于或等于taskCount
  + largestPoolSize：线程池曾经创建过的最大线程数量。通过这个数据可以知道线程池是否满过。如等于线程池的最大大小，则表示线程池曾经满了
  + getPoolSize：线程池的线程数量。如果线程池不销毁的话，池里的线程不会自动销毁，所以这个大小只增不减
  + getActiveCount：获取活动的线程数

- 通过扩展线程池进行监控
 + 通过继承线程池并重写线程池的beforeExecute，afterExecute和terminated方法。
 + 我们可以在任务执行前，执行后和线程池关闭前干一些事情。比如：
     - 监控任务的平均执行时间
     - 最大执行时间
     - 最小执行时间
 + 这几个方法在线程池里是空方法。如：
     - `protected void beforeExecute(Thread t, Runnable r) { }`



